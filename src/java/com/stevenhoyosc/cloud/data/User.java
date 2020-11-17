package com.stevenhoyosc.cloud.data;

import java.math.BigInteger;

public class User {
    private int idusr;
    private String name;
    private String lastname;
    private BigInteger usrmoney;
    public int getIdusr() {
        return idusr;
    }
    public void setIdusr(int idusr) {
        this.idusr = idusr;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public BigInteger getUsrmoney() {
        return usrmoney;
    }
    public void setUsrmoney(BigInteger usrmoney) {
        this.usrmoney = usrmoney;
    }        
}
