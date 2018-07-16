package pipeline;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import pipeline.Schema.DBManager;
import pipeline.Schema.SymbolDirectory;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.sql.Connection;
import java.sql.Statement;

// class for fetching listed equity symbols and update it to database
public class SymbolFetcher {
  private DBManager _manager;

  private enum NasdaqHeader {
    Symbol,
    SecurityName,
    MarketCategory,
    TestIssue,
    FinancialStatus,
    RoundLotSize,
    ETF,
    NextShares
  }

  private enum OthersHeader {
    Symbol,
    SecurityName,
    Exchange,
    CQSSymbol,
    ETF,
    RoundLotSize,
    TestIssue,
    NASDAQSymbol
  }

  private static String nasdaqListUrl =
      "ftp://ftp.nasdaqtrader.com/SymbolDirectory/nasdaqlisted.txt";
  private static String othersUrl = "ftp://ftp.nasdaqtrader.com/SymbolDirectory/otherlisted.txt";

  public SymbolFetcher(DBManager manager) {
    _manager = manager;
  }

  public void fetchSync() {
    fetchSync(nasdaqListUrl, NasdaqHeader.class);
    fetchSync(othersUrl, OthersHeader.class);
    System.out.println("Fetched all equity symbols");
  }

  public void fetchAsync() {}

  private void fetchSync(String url, Class<? extends Enum<?>> headerClass) {
    try {
      CSVParser parser =
          CSVParser.parse(
              new URL(url),
              Charset.defaultCharset(),
              CSVFormat.EXCEL
                  .withDelimiter('|')
                  .withHeader(headerClass)
                  .withSkipHeaderRecord(true));
      Connection conn = _manager.GetConnection();
      Statement stmt = conn.createStatement();
      for (CSVRecord csvRecord : parser) {
        if (!csvRecord.get(0).contains(" ") && csvRecord.get(0).length() < 10) {
          System.out.println("inserting " + csvRecord.get(0));
          stmt.executeUpdate(
              SymbolDirectory.createInsertSQL(
                  csvRecord.get(0).replace('\'', '\"'), csvRecord.get(1).replace('\'', '\"')));
        }
      }
      stmt.close();
      conn.commit();
      conn.close();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
