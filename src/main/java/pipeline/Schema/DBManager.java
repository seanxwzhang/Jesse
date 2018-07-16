package pipeline.Schema;

import java.sql.Connection;
import java.sql.DriverManager;

import utility.Config;

public class DBManager {
    private String dbhost = "127.0.0.1";
    private String port = "5432";
    private String dbname = "jesse";
    private String key = "";
    private String user = "postgres";

    public DBManager(Config conf) {
        this.dbhost = conf.getProperty("dbhost", this.dbhost);
        this.port = conf.getProperty("port", this.port);
        this.dbname = conf.getProperty("dbname", this.dbname);
        this.key = conf.getProperty("key", this.key);
        this.user = conf.getProperty("user", this.user);
    }

    public Connection GetConnection() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://" + this.dbhost + ":" + this.port + "/" + this.dbname, this.user, this.key);
            System.out.println("Opened database successfully");
            c.setAutoCommit(false);
            return c;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
        return c;
    }
}
