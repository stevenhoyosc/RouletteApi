package com.stevenhoyosc.cloud.dao;

import com.stevenhoyosc.cloud.dao.interfaces.RouletteDaoInterface;
import com.stevenhoyosc.cloud.data.Roulette;
import com.stevenhoyosc.cloud.data.UserBets;
import com.stevenhoyosc.cloud.dto.BetsInputDTO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    @Override
    public Boolean openRoulette(int idRoulette) {
        boolean result = false;
        try {
            cx = daoFactory.getConnection();
            String sql = "UPDATE rlt_srv_rlt SET status=1 WHERE idrlt=? ";
            ps = cx.prepareStatement(sql);
            ps.setInt(1, idRoulette);            
            result= ps.executeUpdate()>0;            
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.openRoulette()"+e);
        }
        return result;
        
    }    
    @Override
    public Boolean validateOpenRoulette(int idRoulette) {
        boolean result = false;
        try {
            cx = daoFactory.getConnection();
            String sql = "SELECT * FROM rlt_srv_rlt WHERE idrlt=? AND status='true' ";
            ps = cx.prepareStatement(sql);
            ps.setInt(1, idRoulette);            
            rs = ps.executeQuery();
            if (rs!=null) {
                if (rs.next()) {
                    result=true;
                }
            }
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.validateOpenRoulette()"+e);
        }
        return result;

    }
    @Override
    public BigDecimal moneyOfUser(int idUsr) {
        BigDecimal result = BigDecimal.ZERO;
        try {
            cx = daoFactory.getConnection();
            String sql = "SELECT * FROM rlt_srv_usr WHERE idusr = ? ";
            ps = cx.prepareStatement(sql);
            ps.setInt(1, idUsr);            
            rs = ps.executeQuery();
            if (rs!=null) {
                if (rs.next()) {
                    result= rs.getBigDecimal("usrmoney");
                }
            }
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.moneyOfUser()"+e);
        }
        return result;
    }
    @Override
    public BigDecimal acumMoneyOfBet(int idRlt) {
        BigDecimal result = BigDecimal.ZERO;
        try {
            cx = daoFactory.getConnection();
            String sql = "SELECT idrlt, SUM(betmoney) AS acum "
                    + "  FROM rlt_srv_usrbets "
                    + "  WHERE idrlt = ? "
                    + "  GROUP BY idrlt ";
            ps = cx.prepareStatement(sql);
            ps.setInt(1, idRlt);
            rs = ps.executeQuery();
            if (rs != null) {
                if (rs.next()) {
                    result = rs.getBigDecimal("acum");
                }
            }
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.acumMoneyOfBet()" + e);
        }
        return result;
    }
    @Override
    public Boolean updateMoneyUsr(BigDecimal total,int idUsr) {
        boolean result = false;
        try {
            cx = daoFactory.getConnection();
            String sql = "UPDATE rlt_srv_usr SET usrmoney = ? WHERE idusr = ? ";
            ps = cx.prepareStatement(sql);
            ps.setBigDecimal(1, total);
            ps.setInt(2, idUsr);
            result= ps.executeUpdate()>0;            
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.updateMoneyUsr()"+e);
        }
        return result;
    }
    @Override
    public Boolean insertBet(BetsInputDTO param) {
        boolean result = false;
        try {
            cx = daoFactory.getConnection();
            String sql = "INSERT INTO rlt_srv_usrbets(idusr,idrlt,betmoney,numberbeat,colorbeat) VALUES (?,?,?,?,?) ";
            ps = cx.prepareStatement(sql);
            ps.setInt(1, param.getIdUsr());
            ps.setInt(2, param.getIdRoulette());
            ps.setBigDecimal(3, param.getMoneyBet());
            if (param.isBetColor()) {
                ps.setNull(4, java.sql.Types.INTEGER);
            }else{
                ps.setInt(4, param.getNumberBet());                
            }
            ps.setString(5, param.getColorBet());
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.insertBet()" + e);
        }
        return result;
    }
    @Override
    public Boolean updateWinners(int idRlt, String color, int number) {
        boolean result = false;
        try {
            cx = daoFactory.getConnection();
            String sql = "UPDATE rlt_srv_usrbets SET winner = 'true'  "
                    + " WHERE idbets in ( "
                    + "	SELECT idbets FROM rlt_srv_usrbets  "
                    + "	WHERE idrlt=? AND (colorbeat = ? OR numberbeat=?)) ";
            ps = cx.prepareStatement(sql);
            ps.setInt(1, idRlt);
            ps.setString(2, color);
            ps.setInt(3, number);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.updateWinners()" + e);
        }
        return result;
    }
    @Override
    public void updateLooser(int idRlt) {                
        try {
            cx = daoFactory.getConnection();
            String sql = "UPDATE rlt_srv_usrbets SET betmoney = 0  "
                    + "WHERE idrlt = ? AND winner = 0 ";
            ps = cx.prepareStatement(sql);
            ps.setInt(1, idRlt);            
            ps.execute();
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.updateLooser()" + e);
        }        
    }
    @Override
    public void updateWinnerNumber(int idRlt) {
        try {
            cx = daoFactory.getConnection();
            String sql = "UPDATE rlt_srv_usrbets SET betmoney = betmoney*5   "
                    + "WHERE idbets in ( select idbets from rlt_srv_usrbets "
                    + "where idrlt = ? AND numberbeat<>NULL AND winner = 1 ) ";
            ps = cx.prepareStatement(sql);
            ps.setInt(1, idRlt);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.updateWinnerNumber()" + e);
        }
    }
    @Override
    public void updateWinnerColor(int idRlt) {
        try {
            cx = daoFactory.getConnection();
            String sql = "UPDATE rlt_srv_usrbets SET betmoney = betmoney*1.8   "
                    + "WHERE idbets in (select idbets from rlt_srv_usrbets "
                    + "where idrlt = ? AND colorbeat<>'' AND winner = 1) ";
            ps = cx.prepareStatement(sql);
            ps.setInt(1, idRlt);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.updateWinnerColor()" + e);
        }
    }
    @Override
    public ArrayList bets(int idRlt) {
        ArrayList result = new ArrayList();
        try {
            cx = daoFactory.getConnection();
            String sql = "  SELECT * FROM rlt_srv_usrbets "
                    + "WHERE idrlt = ? ";
            ps = cx.prepareStatement(sql);
            ps.setInt(1, idRlt);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {                  
                    UserBets response = new UserBets();
                    response.setIdusr(rs.getInt("idusr"));
                    response.setIdrlt(rs.getInt("idrlt"));
                    response.setBetmoney(rs.getBigDecimal("betmoney"));
                    response.setNumberbeat(rs.getInt("numberbeat"));
                    response.setColorbeat(rs.getString("colorbeat"));
                    response.setWinner(rs.getBoolean("winner"));
                    result.add(response);
                }
            }            
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.bets()"+e);
        }
        return result;        
    }

    @Override
    public List<Roulette> allRoulette() {
        List<Roulette> result = new ArrayList<>();
        try {
            cx = daoFactory.getConnection();
            String sql = "  SELECT * FROM rlt_srv_rlt";
            ps = cx.prepareStatement(sql);            
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {                  
                    Roulette response = new Roulette();
                    response.setIdrlt(rs.getInt("idrlt"));
                    response.setStatus(rs.getBoolean("status"));                    
                    result.add(response);
                }
            }            
        } catch (SQLException e) {
            System.out.println("com.stevenhoyosc.cloud.dao.RouletteDao.allRoulette()"+e);
        }
        return result;
    }
}
