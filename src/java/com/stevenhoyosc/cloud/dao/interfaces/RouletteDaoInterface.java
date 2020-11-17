package com.stevenhoyosc.cloud.dao.interfaces;

import com.stevenhoyosc.cloud.data.Roulette;
import com.stevenhoyosc.cloud.dto.BetsInputDTO;
import java.math.BigDecimal;

public interface RouletteDaoInterface {
    Roulette newRoulette();
    Boolean openRoulette(int idRoulette);
    Boolean validateOpenRoulette(int idRoulette);
    BigDecimal moneyOfUser(int idUsr);
    BigDecimal acumMoneyOfBet(int idRlt);
    Boolean updateMoneyUsr(BigDecimal total, int idUsr);
    Boolean insertBet(BetsInputDTO param);
}
