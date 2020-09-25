package com.jixu.common.types;

import java.util.HashMap;

public class InterestInitCode {
	public final static int Coupons_30_360 = 65;					//'A' 65 30/360 Coupons 
	public final static int Coupons_30_365=80;					//'P' 80 Act/360 at Maturity(CD's)
	public final static int Coupons_30_Act=87;					//'P' 80 Act/360 at Maturity(CD's) 
	public final static int Coupons_Act_Act = 66;			//'B' 66 Act/Act Coupons (Treasury)
	public final static int Coupons_Act_360 = 68;				//'D' 68 Act/360 Coupons (CD's) 
	public final static int Coupons_Act_365 = 69;				//'E' 69 Act/365 Coupons (CD's)
	public final static int Coupons_Act_365_CY = 90;
	
	//public final static int Coupons_European_30_360 = 70;			//'F' 70 30/360 Coupons (European) 
	 
	//public final static int Coupons_Fixed_Act_365 = 67;				//'C' 67 Act/365 Coupons (Fixed) 
	//public final static int Coupons_UK_Gilts_Act_Act = 71;			//'J' 74 Act/Act Canadian MBS 
	//public final static int Coupons_Japan_Act_360 = 85;				//'U' 85 Act/360 (Japan) 
	//public final static int Coupons_Japan_Act_365 = 72;				//'H' 72 Act/365 (Japan) 
	//public final static int Coupons_Uneven_Cpns_Act_Act = 73;		//'I' 73 Act/Act (Uneven Cpns) 
	//public final static int Coupons_French_OATs_Act_Act = 75;		//'K' 75 Act/Act (French OATs) 
	//public final static int Coupons_Italian_Act_Act = 76;			//'L' 76 Act/Act (Italian) 
	//public final static int Coupons_Non_Treasury_Act_Act = 79;		//'O' 79 Act/Act (Non Treasury) 
	//public final static int Coupons_Asia_Pacific_Act_365 = 88;		//'X' 88 Act/365 (Asia Pacific) 
	//public final static int Coupons_Asia_Pacific_Act_Act = 120;		//'x' 120 Act/Act (Asia Pacific) 
	//public final static int Discount_Notes_Act_360=77;				//'M' 77 Act/360 Discount Notes 
	//public final static int Discount_Notes_Act_365=81;				//'Q' 81 Act/365 Discount Notes 
	//public final static int Discount_Basis=78;						//'N' 78 Discount Basis 
	//public final static int Maturity_Muni_30_360=82;				//'R' 82 30/360 at Maturity (Muni) 
	//public final static int Maturity_Act_Act=83;					//'S' 83 Act/Act at Maturity 
	//public final static int Zero_Coupon_Bond=84;					//'T' 84 Zero Coupon Bond 
	//public final static int Maturity_Act_365=86;					//'V' 86 Act/365 at Maturity 
	//public final static int Normal=80;								//'Z' 90 Normal Normal 
	//public final static int Money_Market_Mutual_Fund=87;			//'W' 87 Money Market Mutual Fund MM Mutual Fund 
	
	public final static HashMap<Integer, String> intInitCodeHM=new HashMap<Integer, String>();
	static{
		intInitCodeHM.put(Coupons_30_360, "30/360");
		intInitCodeHM.put(Coupons_30_365, "30/365");
		intInitCodeHM.put(Coupons_30_Act, "30/Act");
		intInitCodeHM.put(Coupons_Act_Act, "Act/Act");
		intInitCodeHM.put(Coupons_Act_360, "Act/360");
		intInitCodeHM.put(Coupons_Act_365, "Act/365");
		intInitCodeHM.put(Coupons_Act_365_CY, "Act/365(2/28)");
	}
}
