package com.stevenhoyosc.cloud.logic;

import com.stevenhoyosc.cloud.dao.RouletteDao;
import com.stevenhoyosc.cloud.dao.interfaces.RouletteDaoInterface;
import com.stevenhoyosc.cloud.data.Roulette;
import com.stevenhoyosc.cloud.dto.BetsInputDTO;
import com.stevenhoyosc.cloud.logic.interfaces.RouletteInterface;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author stevenhoyosc
 */
public class RouletteLogic implements RouletteInterface{
    private RouletteDaoInterface dao;
    public RouletteLogic() {
        this.dao = new RouletteDao();
    }
    @Override
    public Roulette newRoulette() {
        return dao.newRoulette();
    }
    @Override
    public Boolean openNewRoulette(int idRoulette) {
        return dao.openRoulette(idRoulette);
    }
    @Override
    public Map<String, String> openBets(BetsInputDTO params) {
        Map<String, String> response = new HashMap<>();
        if (dao.validateOpenRoulette(params.getIdRoulette())) {
            if (validateMoney(params)) {
                BigDecimal usrTotalMoney = dao.moneyOfUser(params.getIdUsr()).subtract(params.getMoneyBet());
                dao.updateMoneyUsr(usrTotalMoney, params.getIdUsr());
                response.put("rouletteid", String.valueOf(params.getIdRoulette()));
                response.put("message", "All ok, bet relized");
                
            }else{
                response.put("rouletteid", String.valueOf(params.getIdRoulette()));
                response.put("message", "Problem With money in user or bet");
            }
        }else{
            response.put("rouletteid", String.valueOf(params.getIdRoulette()));
            response.put("message", "Close Roulette to bet");
        }
        return response;
    }
    private boolean validateMoney(BetsInputDTO params){
        boolean validateUserMoney = false;
        boolean validateBetMoney=false;
        validateUserMoney = compareNumber(dao.moneyOfUser(params.getIdUsr()),params.getMoneyBet());
        validateBetMoney= validateBet(params);
        return validateBetMoney && validateUserMoney;
    }
    private boolean compareNumber(BigDecimal i, BigDecimal j){
        boolean response = false;
        int resultComparator = 0;
        resultComparator = i.compareTo(j);
        if (resultComparator == 0 || resultComparator == 1) {
            response = true;
        }
        return response;
    }
    private boolean validateBet(BetsInputDTO params){
        boolean response = false;
        int resultComparator = 0;
        BigDecimal top = new BigDecimal("10000");
        BigDecimal betTotal = dao.acumMoneyOfBet(params.getIdRoulette()).add(params.getMoneyBet());
        resultComparator = top.compareTo(betTotal);
        if (resultComparator == 1) {
            response = true;
        }     
        return response;
    }
}
