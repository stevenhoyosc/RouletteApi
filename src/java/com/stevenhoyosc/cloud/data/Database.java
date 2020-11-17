package com.stevenhoyosc.cloud.data;
/**
 *
 * @author stevenhoyosc
 */
public class Database {
    private String host;
    private int port;
    private String dbname;
    private String dbtype;
    private String user;
    private String password;
    private String url;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getDbtype() {
        return dbtype;
    }

    public void setDbtype(String dbtype) {
        this.dbtype = dbtype;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        url = "jdbc:sqlserver://" + host + ":" + port + ";database=" + dbname + ";user=" + user + ";password=" + password + ";";
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
