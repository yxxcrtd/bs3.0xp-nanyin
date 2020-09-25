//Asset Types
var FIXED_INCOME=10;		//固定收益
var OPEN_REPO=11;			//不定期回购协议
var MONEY_MARKET=15;		//货币市场
var TERM_REPO=16;			//定期回购协议
var MBS=20;
var TBA=21;					//TBA
var CMO=25;
var ABS=26;	
var PREFERRED_STOCK=30;		//优先股
var COMMON_STOCK=40;		//普通股
var PARTNERSHIPS=45;		//合股
var OPTION=60;				//期权
var FUTURES=70;				//期货
var SWAP=80;				//互换
var CREDIT_SELF=90;
var CASH=100;
var FORWARD_CURRENCY=101;	//远期换汇
var MIXED_HEDGE=102;
var CAPITAL=103;			//股本，对应申赎
var SPOT_CURRENCY=104;		//即期换汇
var ALL=0;

//Trade Types
var PURCHASE = 100;
var SALE = 200;
var COMOFF = 700;
var CASHOFFSET = 711;
var STOCKSPLIT = 150;
var REVSTOCKSPLIT = 251;
var SUB=500;
var RED=600;
var REPO_LEND=131;
var REPO_BORROW=132;
var MATURITY_REPO_LEND=233;
var MATURITY_REPO_BORROW=234;
var FX_OPENING=300;			//Opening Transaction
var FX_CLOSING=400;			//Closing Transaction
var FX_DELIVERY=405;		//Delivery Transaction

//interface categories
var smf=1;
var price=2;
var fxRate=3;
var tran=4;
var corpAction=5;
var fxTrade = 6;
var ta = 7;
var cash = 8;
var manual = 9;

//job categories
var autojob = 501;
var dailyClose = 502;
var daily = 503;
var monthly = 504;
var nav = 505;
var generalClose = 506;

//workflow groups
var MARKET = 0;
var TRADE = 1;
var JOB = 3;

//work flow status
var COMPLETE = 0;
var WORKON = 1;
var NOTSTART = 2;

//info types
var SEC_POSITION = 1001;
var CASH_POSITION = 1002;
var RD = 1003;
var RCPY = 1004;

//Report Types
var REPORT_HTML = 0;
var REPORT_PDF = 1;
var REPORT_EXCEL = 2;

var VALUATION_REPORT = 0;

//Process Types
var NEW_FUND = 0;
var OLD_FUND = 1;

//Voucher Types
var MANUAL = 99;

//DEBIT|CREDIT Types
var DEBIT = 0;
var CREDIT = 1;