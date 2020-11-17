package com.stevenhoyosc.rouletteservice.rest.service;

import com.stevenhoyosc.cloud.data.Roulette;
import com.stevenhoyosc.cloud.logic.RouletteLogic;
import com.stevenhoyosc.cloud.logic.interfaces.RouletteInterface;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("roulette")
public class RouletteFacadeRest {
    private RouletteInterface logic;
    public RouletteFacadeRest() {
        this.logic= new RouletteLogic();
    }
    @POST
    @Path("newRoulette")
    @Produces({MediaType.APPLICATION_JSON})
    public Roulette createNewRoulette(){       
        return logic.newRoulette();
    }
}
