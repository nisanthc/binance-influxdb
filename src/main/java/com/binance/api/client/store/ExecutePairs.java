package com.binance.api.client.store;

import java.util.Arrays;
import java.util.List;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.general.ExchangeInfo;
import com.binance.api.client.store.DepthCacheOrderBook;


public class ExecutePairs {
	
	public static void main(String[] args) {
		
		BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance();
		BinanceApiRestClient client = factory.newRestClient();
		
		// To fetch all BTC pairs
		ExchangeInfo exchangeInfo = client.getExchangeInfo();
		exchangeInfo.getSymbols().forEach((item) -> {
	    	
	    	if(item.getQuoteAsset().equals("BTC")) {
				DepthCacheOrderBook dco = new DepthCacheOrderBook(item.getSymbol());
				dco.start();
	    	}
   	    });
	    /*
		List<String> pairs = Arrays.asList("ETCBTC", "BNBBTC", "LINKBTC", "ERDBTC", "XRPBTC", "PPTBTC", "CVCBTC");
		for (String pair : pairs) {
		DepthCacheOrderBook dco = new DepthCacheOrderBook(pair);
			dco.start();
		}
		*/	
	}
}
