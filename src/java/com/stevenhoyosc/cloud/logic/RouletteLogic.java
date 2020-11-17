package com.stevenhoyosc.cloud.logic;

import com.stevenhoyosc.cloud.dao.RouletteDao;
import com.stevenhoyosc.cloud.dao.interfaces.RouletteDaoInterface;
import com.stevenhoyosc.cloud.data.Roulette;
import com.stevenhoyosc.cloud.logic.interfaces.RouletteInterface;

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
        
}
