package pipeline.Schema;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import utility.Config;

public class SymbolDirectory implements Schema {
  public static String tableName = "SYMBOLS";
  public static String schemaSQL =
      "CREATE TABLE IF NOT EXISTS "
          + tableName
          + " (SYMBOL VARCHAR(10)       PRIMARY KEY     NOT NULL, "
          + "NAME           TEXT            NOT NULL)";

  public static void createTable(Connection conn) {
    Statement stmt = null;
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
  }

  public static String createInsertSQL(String symbol, String name) {
    return String.format(
        "INSERT INTO %s (SYMBOL, NAME) VALUES ('%s', '%s') ON CONFLICT(SYMBOL) DO UPDATE SET NAME = '%s';",
        tableName, symbol, name, name);
  }
}
