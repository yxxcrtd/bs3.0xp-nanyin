package com.test.jixu;

import java.sql.Timestamp;
import java.util.Date;

public class Test {

	public void test(){
		Date date=new Date();
		System.out.println((Timestamp)date);
	}
	
	public static void main(String[] args) {
		Test t=new Test();
		t.test();
	}
}
