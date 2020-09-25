package com.jixu.post.service.imp;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LotTranNumUntil {
private static Set<String> lotTranNumSet;
	
	public static Set<String> addKey(String portnum, String cusip, int lotnum, int trannum){
		if(lotTranNumSet==null){
			lotTranNumSet=Collections.synchronizedSet(new HashSet<String>());
		}
		String key=portnum+"_"+cusip+"_"+lotnum+"_"+trannum;
		lotTranNumSet.add(key);
		return lotTranNumSet;
	}
	
	public static void removeKey(String portnum, String cusip, int lotnum, int trannum){
		if(lotTranNumSet!=null){
			String key=portnum+"_"+cusip+"_"+lotnum+"_"+trannum;
			lotTranNumSet.remove(key);
		}
	}
	
	public static boolean containsKey(String portnum, String cusip, int lotnum, int trannum){
		if(lotTranNumSet==null)
			return false;
		else{
			String key=portnum+"_"+cusip+"_"+lotnum+"_"+trannum;
			return lotTranNumSet.contains(key);
		}
	}
}
