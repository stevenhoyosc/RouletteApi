package com.stevenhoyosc.cloud.logic.interfaces;

import com.stevenhoyosc.cloud.data.Roulette;

public interface RouletteInterface {
    Roulette newRoulette();
    Boolean openNewRoulette(int idRoulette);
}
