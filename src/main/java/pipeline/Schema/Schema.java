package pipeline.Schema;

import java.sql.Connection;
import java.sql.Statement;

public class Schema {
    public static String tableName;
    public static String schemaSQL;
    public static void createTable(Connection conn) {
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(schemaSQL);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table " + tableName + " created successfully");
    };
}
