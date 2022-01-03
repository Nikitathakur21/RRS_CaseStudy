package com.paytm.pg.merchant;

import java.util.TreeMap;

public class PaytmChecksum {

	public static boolean verifySignature(TreeMap<String, String> parameters, String merchantKey,
			String paytmChecksum) {
	
		return false;
	}

	public static String generateSignature(TreeMap<String, String> parameters, String merchantKey) {
		
		return null;
	}

}
