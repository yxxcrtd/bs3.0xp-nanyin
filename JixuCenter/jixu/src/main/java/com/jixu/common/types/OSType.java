package com.jixu.common.types;

public class OSType {
	public final static int WIN = 0;
	public final static int MAC = 1;
	public final static int LINUX = 2;
	public final static int UNIX = 3;
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	public final static int detctOS() {
		if (OS.indexOf("win") >=0){
			return WIN;
		}
		else if (OS.indexOf("mac") >=0){
			return MAC;
		}
		else if (OS.indexOf("nux") >=0){
			return LINUX;
		}
		else {
			return UNIX;
		}
	}
}
