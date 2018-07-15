import com.ib.client.*;

import javax.swing.*;
import java.util.*;


public class Main  {
    public static void main (String args[]) {
        PlayClient client = new PlayClient();
        System.out.println("Getting data");
        client.getDailyDataLastYear();
        System.out.println("end");
    }

}


class PlayClient implements EWrapper {

    private EJavaSignal m_signal = new EJavaSignal();
    private EClientSocket m_client = new EClientSocket(this, m_signal);
    private EReader m_reader;

    private static String pad( int val) {
        return val < 10 ? "0" + val : "" + val;
    }

    private void processMessages() {
        while (m_client.isConnected()) {
            m_signal.waitForSignal();
            try {
                m_reader.processMsgs();
            } catch (Exception e) {
                error(e);
            }
        }
    }

    public void getDailyDataLastYear() {
        // connect to TWS
        m_client.eConnect("127.0.0.1", 7496, 0);
        if (m_client.isConnected()) {
            System.out.println("Connected to Tws server version " +
                    m_client.serverVersion() + " at " +
                    m_client.TwsConnectionTime());
        }

        m_reader = new EReader(m_client, m_signal);
        m_reader.start();
        new Thread() {
            public void run() {
                processMessages();

                int i = 0;
                System.out.println(i);
            }
        }.start();

        // define contract
        Contract m_contract = new Contract();

        // set contract fields
        m_contract.conid(0);
        m_contract.symbol("TSLA");
        m_contract.secType("STK");
        m_contract.strike( 0.0);
        m_contract.exchange("SMART");
        m_contract.primaryExch("ISLAND");
        m_contract.currency("USD");
        m_contract.includeExpired(false);

        // set backfillEndTime
        String m_backfillEndTime;
        JTextField m_BackfillEndTime = new JTextField(22);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateTime = "" +
                gc.get(Calendar.YEAR) +
                pad(gc.get(Calendar.MONTH) + 1) +
                pad(gc.get(Calendar.DAY_OF_MONTH)) + " " +
                pad(gc.get(Calendar.HOUR_OF_DAY)) + ":" +
                pad(gc.get(Calendar.MINUTE)) + ":" +
                pad(gc.get(Calendar.SECOND)) + " " +
                gc.getTimeZone().getDisplayName( false, TimeZone.SHORT);

        m_BackfillEndTime.setText(dateTime);
        m_backfillEndTime = m_BackfillEndTime.getText();

        // set backfillDuration
        String m_backfillDuration = "1 Y";

        // set barSizeSetting
        String m_barSizeSetting = "1 day";

        // set whatToShow
        String m_whatToShow = "TRADES";

        // set useRTH
        int m_useRTH = 1;

        // set formatDate
        int m_formatDate = 1;

        ArrayList<TagValue> m_options = new ArrayList<TagValue>();
        m_client.reqHistoricalData( 1001, m_contract,
                m_backfillEndTime, m_backfillDuration,
                m_barSizeSetting, m_whatToShow,
                m_useRTH, m_formatDate, m_options );
    };

    @Override public void nextValidId(int orderId) {

    }

    @Override public void error(Exception e) {
    }

    @Override public void error(int id, int errorCode, String errorMsg) {
    }

    @Override public void connectionClosed() {
    }

    @Override public void error(String str) {
    }

    @Override public void tickPrice(int tickerId, int field, double price, int canAutoExecute) {
    }

    @Override public void tickSize(int tickerId, int field, int size) {
    }

    @Override public void tickOptionComputation(int tickerId, int field, double impliedVol, double delta, double optPrice, double pvDividend, double gamma, double vega, double theta, double undPrice) {
    }

    @Override public void tickGeneric(int tickerId, int tickType, double value) {
    }

    @Override public void tickString(int tickerId, int tickType, String value) {
    }

    @Override public void tickEFP(int tickerId, int tickType, double basisPoints, String formattedBasisPoints, double impliedFuture, int holdDays, String futureLastTradeDate, double dividendImpact,
                                  double dividendsToLastTradeDate) {
    }

    @Override public void orderStatus(int orderId, String status, double filled, double remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId, String whyHeld) {
    }

    @Override public void openOrder(int orderId, Contract contract, Order order, OrderState orderState) {
    }

    @Override public void openOrderEnd() {
    }

    @Override public void updateAccountValue(String key, String value, String currency, String accountName) {
    }

    @Override public void updatePortfolio(Contract contract, double position, double marketPrice, double marketValue, double averageCost, double unrealizedPNL, double realizedPNL, String accountName) {
    }

    @Override public void updateAccountTime(String timeStamp) {
    }

    @Override public void accountDownloadEnd(String accountName) {
    }

    @Override public void contractDetails(int reqId, ContractDetails contractDetails) {
    }

    @Override public void bondContractDetails(int reqId, ContractDetails contractDetails) {
    }

    @Override public void contractDetailsEnd(int reqId) {
    }

    @Override public void execDetails(int reqId, Contract contract, Execution execution) {
    }

    @Override public void execDetailsEnd(int reqId) {
    }

    @Override public void updateMktDepth(int tickerId, int position, int operation, int side, double price, int size) {
    }

    @Override public void updateMktDepthL2(int tickerId, int position, String marketMaker, int operation, int side, double price, int size) {
    }

    @Override public void updateNewsBulletin(int msgId, int msgType, String message, String origExchange) {
    }

    @Override public void managedAccounts(String accountsList) {
    }

    @Override public void receiveFA(int faDataType, String xml) {
    }

    @Override public void historicalData(int reqId, String date, double open, double high, double low,
                                         double close, int volume, int count, double WAP, boolean hasGaps) {
        String msg = EWrapperMsgGenerator.historicalData(reqId, date, open, high, low, close, volume, count, WAP, hasGaps);
        System.out.println(msg);
    }

    @Override public void scannerParameters(String xml) {
    }

    @Override public void scannerData(int reqId, int rank, ContractDetails contractDetails, String distance, String benchmark, String projection, String legsStr) {
    }

    @Override public void scannerDataEnd(int reqId) {
    }

    @Override public void realtimeBar(int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count) {
    }

    @Override public void currentTime(long time) {
    }

    @Override public void fundamentalData(int reqId, String data) {
    }

    @Override public void deltaNeutralValidation(int reqId, DeltaNeutralContract underComp) {
    }

    @Override public void tickSnapshotEnd(int reqId) {
    }

    @Override public void marketDataType(int reqId, int marketDataType) {
    }

    @Override public void commissionReport(CommissionReport commissionReport) {
    }

    @Override public void position(String account, Contract contract, double pos, double avgCost) {
    }

    @Override public void positionEnd() {
    }

    @Override public void accountSummary(int reqId, String account, String tag, String value, String currency) {
    }

    @Override public void accountSummaryEnd(int reqId) {
    }

    @Override public void verifyMessageAPI( String apiData) {
    }

    @Override public void verifyCompleted( boolean isSuccessful, String errorText){
    }

    @Override public void verifyAndAuthMessageAPI( String apiData, String xyzChallenge) {
    }

    @Override public void verifyAndAuthCompleted( boolean isSuccessful, String errorText){
    }

    @Override public void displayGroupList( int reqId, String groups){
    }

    @Override public void displayGroupUpdated( int reqId, String contractInfo){
    }

    @Override public void positionMulti( int reqId, String account, String modelCode, Contract contract, double pos, double avgCost) {
    }

    @Override public void positionMultiEnd( int reqId) {
    }

    @Override public void accountUpdateMulti( int reqId, String account, String modelCode, String key, String value, String currency) {
    }

    @Override public void accountUpdateMultiEnd( int reqId) {
    }

    public void connectAck() {
        if (m_client.isAsyncEConnect())
            m_client.startAPI();
    }

    @Override
    public void securityDefinitionOptionalParameter(int reqId, String exchange, int underlyingConId, String tradingClass,
                                                    String multiplier, Set<String> expirations, Set<Double> strikes) {
    }

    @Override
    public void securityDefinitionOptionalParameterEnd(int reqId) {
        System.out.println("done");
    }

    @Override
    public void softDollarTiers(int reqId, SoftDollarTier[] tiers) {
    }
}

