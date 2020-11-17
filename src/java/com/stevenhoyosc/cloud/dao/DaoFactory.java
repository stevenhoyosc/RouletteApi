package com.stevenhoyosc.cloud.dao;

import com.stevenhoyosc.cloud.util.DatabaseConfig;
import com.stevenhoyosc.cloud.data.Database;
import com.stevenhoyosc.cloud.util.XmlReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author stevenhoyosc
 */
public class DaoFactory {
    private Connection cx;
    private DatabaseConfig dbDao;
    private Database db;
    private XmlReader xml;
    public Connection getConnection() {
        dbDao = getDatabaseDao();
        db = dbDao.createValueObject();
        xml = getXmlReader();
        
        try {
            db = dbDao.load(xml);
            switch (db.getDbtype()) {
                case "sqlserver": 
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    break;
                case "postgresql": 
                    Class.forName("org.postgresql.Driver");
                    break;
                case "oracle": 
                    Class.forName("oracle.jdbc.OracleDriver");
                    break;
                default: 
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    break;
            }
            cx = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
        }
        catch (ClassNotFoundException|NotFoundException|SQLException e) {
            System.out.println("Error: " + e);
            cx = null;
        }    
        
        return cx;
    }
    
    public XmlReader getXmlReader() {
        return new XmlReader();
    }
    
    public DatabaseConfig getDatabaseDao() {
        return new DatabaseConfig();
    }
}
