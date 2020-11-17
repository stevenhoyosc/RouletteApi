package com.stevenhoyosc.cloud.dto;

import java.math.BigDecimal;

public class BetsInputDTO {
    private int idUsr;
    private int idRoulette;
    private BigDecimal moneyBet;
    private int numberBet;
    private String colorBet;
    private boolean betColor;
    public int getNumberBet() {
        return numberBet;
    }
    public void setNumberBet(int numberBet) {
        this.numberBet = numberBet;
    }
    public String getColorBet() {
        return colorBet;
    }
    public void setColorBet(String colorBet) {
        this.colorBet = colorBet;
    }
    public BigDecimal getMoneyBet() {
        return moneyBet;
    }
    public void setMoneyBet(BigDecimal moneyBet) {
        this.moneyBet = moneyBet;
    }
    public int getIdUsr() {
        return idUsr;
    }
    public void setIdUsr(int idUsr) {
        this.idUsr = idUsr;
    }
    public int getIdRoulette() {
        return idRoulette;
    }
    public void setIdRoulette(int idRoulette) {
        this.idRoulette = idRoulette;
    }  
    public boolean isBetColor() {
        return betColor;
    }
    public void setBetColor(boolean betColor) {
        this.betColor = betColor;
    }    
}
