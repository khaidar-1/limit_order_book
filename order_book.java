import java.util.HashMap;
import java.math.BigDecimal;

public class order_book
{
    private HashMap<Double, orderlist> priceMap_bids = new HashMap<Double, orderlist>();
    private HashMap<Double, orderlist> priceMap_asks = new HashMap<Double, orderlist>();
    private double tick_size;
    private int time;
    private int quote_id;
    

    public order_book(double tick)
    {
        this.tick_size=tick;

    }
    
    // Normalize Price according to tick sieze
    public double ticked_price(double price)
    {
    	int unit = (int)Math.log10(1 / this.tick_size);
	BigDecimal price_bd = new BigDecimal(price);
	BigDecimal result = price_bd.setScale(unit, BigDecimal.ROUND_HALF_UP);
	return result.doubleValue();
    }
    
    public void process_order(order incoming_quote){
        //verify if limit or market order
        boolean is_limit=incoming_quote.is_limit();
        // update LoB time
        this.time=incoming_quote.get_timestamp();
	if (is_limit) {
            double ticked_price = ticked_price(incoming_quote.get_price());
            incoming_quote.set_price(ticked_price);
            process_limit_order(incoming_quote);
            } else {
            process_market_order(incoming_quote);
        }
    }
        
    public void process_market_order(order incoming_quote){
        
    }
    
    public void process_limit_order(order incoming_quote){
        
    }
        
        
   
    
}
