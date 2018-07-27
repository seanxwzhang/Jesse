package pipeline;

import lombok.Getter;
import lombok.Setter;

public class Asset {
    /**
     * Symbol of the asset
     * Can only be constructed at initialization
     */
    @Getter private String symbol;

    /**
     * Exchange that this asset is traded in
     */
    @Getter @Setter private String exchange;

    /**
     * Currency
     */
    @Getter private String currency;

    public Asset(String _symbol, String _exchange, String _currency) {
        this.symbol = _symbol;
        this.exchange = _exchange;
        this.currency = _currency;
    }
}
