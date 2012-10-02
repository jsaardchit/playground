package playground

import javax.servlet.AsyncContext

class TestController {
    static scope = 'singleton'

    private static final String YAHOO_FINANCE_API = "http://download.finance.yahoo.com/d/quotes.csv?s="
    private static final String API_PARAMS = "&f=nsl1op&e=.csv"

    def index() { }

    def async( String ticker ) {
        AsyncContext ctx = startAsync()
        ctx.start {

            ticker = ticker ?: 'GOOG'

            try {
                Double price = new URL(YAHOO_FINANCE_API + ticker + API_PARAMS).text.split(',')[-1] as Double
                render( view: 'sync', model: [ticker:ticker, price:price] )
            }
            finally {
                ctx.dispatch()
            }
        }
    }

    def sync( String ticker ) {
        ticker = ticker ?: 'GOOG'

        Double price = new URL(YAHOO_FINANCE_API + ticker + API_PARAMS).text.split(',')[-1] as Double
        return [ticker:ticker, price: price]
    }
}
