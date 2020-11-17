package com.stevenhoyosc.rouletteservice.rest.service;

import com.stevenhoyosc.cloud.data.Roulette;
import com.stevenhoyosc.cloud.dto.BetsInputDTO;
import com.stevenhoyosc.cloud.logic.RouletteLogic;
import com.stevenhoyosc.cloud.logic.interfaces.RouletteInterface;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    @PUT
    @Path("openRoulette")
    @Produces({MediaType.APPLICATION_JSON})
    public boolean openRoulette(@HeaderParam("idRoulette") int idRoulette){  
        return logic.openNewRoulette(idRoulette);
    }
    @POST
    @Path("openBets")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Map<String,String> openBet(BetsInputDTO params){
        return logic.openBets(params);
    }
    @GET
    @Path("winnRoulette")    
    @Produces({MediaType.APPLICATION_JSON})
    public Map<String,String> openBet(@HeaderParam("idRoulette") int idRoulette){
        return logic.closeBets(idRoulette);
    }
}
