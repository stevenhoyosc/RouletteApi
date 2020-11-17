package com.stevenhoyosc.cloud.util;
import com.stevenhoyosc.cloud.data.Database;
import com.stevenhoyosc.cloud.util.XmlReader;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author stevenhoyosc
 */
public class DatabaseConfig {
    public Database createValueObject() {
          return new Database();
    }
    
    public Database load(XmlReader xml) throws NotFoundException {
        Database valueObject = new Database();
        if (xml.initFile()) {
            valueObject.setDbname(xml.parseFile("dbname"));
            valueObject.setHost(xml.parseFile("host"));
            valueObject.setPassword(xml.parseFile("password"));
            valueObject.setPort(Integer.parseInt(xml.parseFile("port")));
            valueObject.setUser(xml.parseFile("user"));
            valueObject.setDbtype(xml.parseFile("dbtype"));
        } else {
            throw new NotFoundException("[Debug] XML File not found.");
        }
        return valueObject;
    }

}
