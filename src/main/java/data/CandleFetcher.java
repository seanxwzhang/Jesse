package data;


import com.ib.client.*;

import java.util.HashSet;
import java.util.Set;

/*
 * Class for fetching candle data from IB
 * Implements the EWrapper interface
 * Due to the asynchronous nature of IB query, the CandleFetcher maintains a JMS queue
 * Essentially CandleFetcher is a IB client as well as a JSM producer
 */
public class CandleFetcher implements EWrapper {
    private EJavaSignal m_signal = new EJavaSignal();
    private EClientSocket m_client = new EClientSocket(this, m_signal);
    private EReader m_reader;
    private HashSet<Integer> reqIds;

    /**
     * Initiate fetch IB request
     * @param symbol stock symbol
     * @param duration http://interactivebrokers.github.io/tws-api/historical_bars.html#hd_duration
     * @param barSize http://interactivebrokers.github.io/tws-api/historical_bars.html#hd_barsize
     * @return reqId: an id used for tracking data received from callback function
     */
    public int fetchStockDataImpl(String symbol, String duration, String barSize) {

    }


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
