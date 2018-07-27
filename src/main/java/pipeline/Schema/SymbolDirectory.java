package pipeline.Schema;

public class SymbolDirectory extends Schema {
  public static String tableName = "SYMBOLS";
  public static String schemaSQL =
      "CREATE TABLE IF NOT EXISTS "
          + tableName
          + "(SYMBOL VARCHAR(10)       PRIMARY KEY     NOT NULL, "
          + "NAME           TEXT            NOT NULL)";

  public static String createInsertSQL(String symbol, String name) {
    return String.format(
        "INSERT INTO %s (SYMBOL, NAME) VALUES ('%s', '%s') ON CONFLICT(SYMBOL) DO UPDATE SET NAME = '%s';",
        tableName, symbol, name, name);
  }
}
