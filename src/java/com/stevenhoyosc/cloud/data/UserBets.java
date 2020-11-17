package com.stevenhoyosc.cloud.data;

import java.math.BigInteger;

/**
 *
 * @author stevenhoyosc
 */
public class UserBets {
    int idusr;
    int name;
    int lastname;
    BigInteger usrmoney;
    public int getIdusr() {
        return idusr;
    }
    public void setIdusr(int idusr) {
        this.idusr = idusr;
    }
    public int getName() {
        return name;
    }
    public void setName(int name) {
        this.name = name;
    }
    public int getLastname() {
        return lastname;
    }
    public void setLastname(int lastname) {
        this.lastname = lastname;
    }
    public BigInteger getUsrmoney() {
        return usrmoney;
    }
    public void setUsrmoney(BigInteger usrmoney) {
        this.usrmoney = usrmoney;
    }
}
