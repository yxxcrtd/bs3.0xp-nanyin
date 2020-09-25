var BankZiGuan = 112;
var ShareBankZGMaturity = 135;
var ShareBankZGPayment = 138;			
var ShareBankZGPayFee = 138;

function genTradeInfo(name, lot_id, info){
	var brief = lot_id.split("|"); 
	
	 if (name == "red"){
		 info["assetType"] = BankZiGuan;
		 info["tradeType"] = ShareBankZGMaturity;
		 info["tp"] = "ShareBankZGMaturity";
	 }
	 
	 else if (name == "payment"){
		 info["assetType"] = BankZiGuan;
		 info["tradeType"] = ShareBankZGPayment;
		 info["tp"] = "ShareBankZGPayment";
	 }
	 
	 else if (name == "performance"){
		 info["assetType"] = BankZiGuan;
		 info["tradeType"] = ShareBankZGPayFee;
		 info["tp"] = "ShareBankZGPayFee";
	 }
	 return info;
 }