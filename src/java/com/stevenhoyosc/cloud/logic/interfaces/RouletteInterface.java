package com.stevenhoyosc.cloud.logic.interfaces;

import com.stevenhoyosc.cloud.data.Roulette;
import com.stevenhoyosc.cloud.dto.BetsInputDTO;
import java.util.Map;

public interface RouletteInterface {
    Roulette newRoulette();
    Boolean openNewRoulette(int idRoulette);
    Map<String,String> openBets(BetsInputDTO params);
}
