package com.stevenhoyosc.cloud.logic.interfaces;

import com.stevenhoyosc.cloud.data.Roulette;
import com.stevenhoyosc.cloud.dto.BetsInputDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface RouletteInterface {
    Roulette newRoulette();
    Boolean openNewRoulette(int idRoulette);
    Map<String,String> openBets(BetsInputDTO params);
    Map<String,ArrayList> closeBets(int idRoulette);
    List<Roulette> allRoulette();
}
