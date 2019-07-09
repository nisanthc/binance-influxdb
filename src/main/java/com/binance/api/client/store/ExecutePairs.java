package com.binance.api.client.store;

import java.util.Arrays;
import java.util.List;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.store.DepthCacheOrderBook;


public class ExecutePairs {
	
	public static void main(String[] args) {
				
		if (args.length == 1 && Arrays.asList("BTC", "BNB").contains(args[0])) {
			
		    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
			BinanceApiRestClient client = factory.newRestClient();
			
			// To fetch all BTC pairs
			ExchangeInfo exchangeInfo = client.getExchangeInfo();
			exchangeInfo.getSymbols().forEach((item) -> {
		    	if(item.getQuoteAsset().equals(args[0])) {
					DepthCacheOrderBook dco = new DepthCacheOrderBook(args[0], item.getSymbol());
					dco.start();
		    	}
	   	    });
//			String pair = "BTC";
//			List<String> symbols = Arrays.asList("BNBBTC","PPTBTC","BNBBTC", "LINKBTC", "ERDBTC", "XRPBTC", "PPTBTC", "CVCBTC");
//			for (String symbol : symbols) {
//			DepthCacheOrderBook dco = new DepthCacheOrderBook(pair, symbol);
//				dco.start();
//			 }
		}
		else {
			System.out.println("Please provide command line argument either BTC or BNB.");
		}			
	}
}
