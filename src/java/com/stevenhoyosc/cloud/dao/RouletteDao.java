package com.stevenhoyosc.cloud.dao;

import com.stevenhoyosc.cloud.dao.interfaces.RouletteDaoInterface;
import com.stevenhoyosc.cloud.data.Roulette;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author stevenhoyosc
 */
public class RouletteDao implements RouletteDaoInterface{
    private Connection cx;
    private ResultSet rs;
    private PreparedStatement ps;
    private final DaoFactory daoFactory;

    public RouletteDao() {
        daoFactory = new DaoFactory();
    }
    
    @Override
    public Roulette newRoulette() {
        Roulette result = new Roulette();
        try {
            cx = daoFactory.getConnection();
            String sql = "  INSERT INTO rlt_srv_rlt(status) "
                    + "VALUES (0) "
                    + "SELECT * FROM rlt_srv_rlt WHERE idrlt = SCOPE_IDENTITY()";
            ps = cx.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {                  
                    result.setIdrlt(rs.getInt("idrlt"));
                    result.setStatus(rs.getBoolean("status"));
                }
            }            
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.newRoulette()"+e);
        }
        return result;
    }
    
}
