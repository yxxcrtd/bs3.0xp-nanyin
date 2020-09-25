package com.jixu.job.service.Impl;

import java.util.Comparator;

import com.jixu.journal.obj.SecCashObj;

public class SortByCusip implements Comparator<SecCashObj> {

	public int compare(SecCashObj c1, SecCashObj c2) {
		// TODO Auto-generated method stub
		if (c1.getCusip().compareTo(c2.getCusip())==1) {
			return 1;
		} else {
			return 0;
		}
	}

}
