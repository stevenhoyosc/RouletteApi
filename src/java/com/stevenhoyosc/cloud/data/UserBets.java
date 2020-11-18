package com.stevenhoyosc.cloud.data;

import java.math.BigDecimal;

/**
 *
 * @author stevenhoyosc
 */
public class UserBets {
    private int idusr;
    private int idrlt;
    private BigDecimal betmoney;
    private int numberbeat;
    private String colorbeat;
    private boolean winner;

    public int getIdusr() {
        return idusr;
    }
    public void setIdusr(int idusr) {
        this.idusr = idusr;
    }
    public int getIdrlt() {
        return idrlt;
    }
    public void setIdrlt(int idrlt) {
        this.idrlt = idrlt;
    }
    public BigDecimal getBetmoney() {
        return betmoney;
    }
    public void setBetmoney(BigDecimal betmoney) {
        this.betmoney = betmoney;
    }
    public int getNumberbeat() {
        return numberbeat;
    }
    public void setNumberbeat(int numberbeat) {
        this.numberbeat = numberbeat;
    }
    public String getColorbeat() {
        return colorbeat;
    }
    public void setColorbeat(String colorbeat) {
        this.colorbeat = colorbeat;
    }
    public boolean isWinner() {
        return winner;
    }
    public void setWinner(boolean winner) {
        this.winner = winner;
    }
}
