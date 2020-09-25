package com.jixu.common.func;

import java.util.HashMap;

public class GLACCT {

	public final static int INC_CASH = 1; // IncomeCash
	public final static int PRINCIPAL_CASH = 2; // PrincipalCash
	public final static int BOOK_VALUE = 3; // BookValue
	public final static int ACCRUED_INC = 4; // AccruedIncome
	public final static int INC_DUE = 5; // IncomeDue
	public final static int CARRY_VAL_ADJ = 6; // CarryValAdj
	public final static int INVEST_REC_BANK = 7; // InvestmentReceivable-Bank
	public final static int INVEST_REC_PAY_BROKER = 8; //InvestmentRec/Pay-Broker
	public final static int INVEST_REC_PAY_INTERCO = 9; // InvestmentRec/Pay- InterCo.
	public final static int FX_RESERVE_CAP = 10; // FxReserve-Cap
	public final static int PAY_COLLATERAL = 11; // Pay-Columnlateral担保,抵押
	public final static int PREPAID_INC = 12; // PrepaidIncome
	public final static int UNREAL_GAIN = 13; // UnrealizedGain
	public final static int INC_AMORT = 14; // PremiumAmortization
	public final static int DISCOUNT_ACCRUAL = 15; // DiscountAccrual
	public final static int INC_ACCRUAL = 16; // IncomeAccrual
	public final static int FX_GL_INC_UNREAL = 17; // FxG/LIncome-Unreal.
	public final static int INC_REC = 18; // IncomeReceived
	public final static int FX_GL_INC_REAL = 19; // FXG/LIncome-Real
	public final static int CAPITAL_GAIN = 20; // CapitalGain
	public final static int FX_GL_CAPITAL_UNREAL = 21; // FXG/LCapital-Unreal.
	public final static int MISC_INVEST_EXP = 22; // MiscInvestmentExpense
	public final static int REC_MARGIN = 23; // ReceivableMargin
	public final static int FX_RESERVE_INC = 24; // FXReserve-Income
	public final static int FOREIGN_TAXES_PAID = 25; // ForeignTaxesPaid
	public final static int CAPITAL_LOSS = 26; // CapitalLoss
	public final static int FX_GL_CAPITAL_REAL = 27; // FXG/LCapital-Real
	public final static int MISC_INVEST_INC = 28; // MiscInvestmentIncome
	public final static int CONTRIB_SURPLUS = 29; // ContributedSurplus
	public final static int INVEST_PAY_BANK = 30; // InvestmentPayable-Bank
	public final static int INC_PAID = 31; // IncomePaid
	public final static int BOOK_VALUE_SHORT = 32; // BookValue-shortpositions
	public final static int DIVIDEND_REC = 33; // Dividendreceivable
	public final static int INTEREST_RECVBL = 34; // Interestreceivable
	public final static int RECEIV_SECUR_SOLD = 35; // Receivableforsecuritiessold
	public final static int PAY_SECUR_PURCH = 36; // PayableSecuritiesPurchased
	public final static int RECEIV_FUND_SHARES_SOLD = 37; // Receivableforfundsharessold
	public final static int FOREIGN_WH_TAX_RECV = 38; // NonResidentAlienReceivable
	public final static int PAY_FUND_SHARES_REDEEM = 39; // PayableFundSharesRedeemed
	public final static int DIVIDENDS_PAYABLE = 40; // DividendsPayable
	public final static int CAPITAL_STOCK_PAR = 41; // CapitalStock-Par
	public final static int CAPITAL_STOCK_EXCESS = 42; //CapitalStock-excessofpar
	public final static int DIST_PD_CURR_YR_INC = 43; // Distributionspaid-currentyrincome
	public final static int DIST_PD_CURR_YR_GAINS = 44; // Distributionspaid-currentyeargains
	public final static int EQUAL_INC = 45; // Equalizationincomecredit
	public final static int EXP_REIMBURSE = 46; // ExpenseReimbursement
	public final static int RECVBL_EXCESS_EXPENSE = 47; // Receivable-excessexpense
	public final static int INTEREST_INC_DEFERRAL = 48; // InterestIncomeDeferral
	public final static int EQUAL_INC_DEB = 49; // EqualizationIncDebit
	public final static int UNREAL_LOSS = 50; // Unrealizedloss
	public final static int VALUATION_GAIN = 51;		//估值收益		资本 6科目
	public final static int VALUATION_LOSS = 52;		//估值亏损		资本6科目
	public final static int FOREIGN_COMMISSION = 53; // ForeignCommission
	public final static int FOREX_LONG = 54; // FXContracts-Long
	public final static int FOREX_SHORT = 55; // FXContracts-Short
	public final static int FOREX_PREM_DISC = 56; //Discount/Premium-FXContracts
	public final static int FOREX_UNREAL_GL_LONG = 57; // UnrealizedGain/LossFXContracts-Long
	public final static int FOREX_UNREAL_GL_SHORT = 58; // UnrealizedGain/LossFXContracts-Short
	public final static int GOODWILL = 59; // Goodwill
	public final static int INVESTMENT_CLEARING = 60; // InvestmentClearing
	public final static int CAP_GAIN_INTRA_XFER = 61; //CapitalGain-IntraTransfer
	public final static int CAP_LOSS_INTRA_XFER = 62; //CapitalLoss-IntraTransfer
	public final static int FX_CAP_GAIN_INTRA_XFER = 63; // FXCapitalGain-IntraTransfer
	public final static int FX_CAP_LOSS_INTRA_XFER = 64; // FXCapitalLoss-IntraTransfer
	public final static int PROPORT_CARRY_VAL_ADJ = 65; // ProportionedCarryValueAdj
	public final static int PROPORT_FX_RESERVE_CAP = 66; // Proport.FXReserveCapital
	public final static int AMORT_UNREAL_G_L = 67; // AmortizationofUnrealizedGains/Losses
	public final static int PROPORT_AMORT_UNREAL_G_L = 68; // ProportionedAmort.ofUnrealizedG/L
	public final static int AMORT_UNREAL_FX_G_L = 69; // AmortizationofUnrealizedGains/Losses
	public final static int PROPORT_AMORT_UNREAL_FX_G_L = 70; // Proport.Amort.ofUnrealizedFXG/L
	public final static int DEFERRED_CAPITAL_GAIN = 71; // DeferredCapitalGain
	public final static int DEFERRED_CAPITAL_LOSS = 72; // DeferredCapitalLoss
	public final static int DEFERRED_FX_GAIN = 73; // DeferredFXGain
	public final static int DEFERRED_FX_LOSS = 74; // DeferredFXLoss
	public final static int NOT_PRIN_DERIV_PURCHASED = 75; // NotionalPrincipalDerivativePurchased
	public final static int NOT_PRIN_DERIV_SOLD = 76; // NotionalPrincipalDerivativeSold
	public final static int LIAB_DERIV_PURCHASED = 77; // LiabDerivativePurchased
	public final static int LIAB_DERIV_SOLD = 78; // LiabDerivativeSold
	public final static int FEE_PAYABLE = 79; // FeePayable
	public final static int FEE_EXPENSE = 80; // FeeExpense
	public final static int CORP_ACTION_OFFSET = 81; // CorporateActionOffset
	public final static int NOTIONAL_FOREX_LONG = 82; // NotionalPrincipalFXForwardsLong
	public final static int NOTIONAL_FOREX_SHORT = 83; // NotionalPrincipalFXForwardsShort
	public final static int NOTIONAL_FOREX_LONG_OFFSET = 84; // OffsetNotionalFXForwardsLong
	public final static int NOTIONAL_FOREX_SHORT_OFFSET = 85; // OffsetNotionalFXForwardsShort
	public final static int FOREX_PREM_INCOME = 86; //Premium-FXContract(Income)
	public final static int FOREX_DISC_INCOME = 87; //Discount-FXContracts(Income)
	public final static int ARBITRAGE = 88; // Arbitrage
	public final static int LATE_FEE_INCOME = 89;
	public final static int SCHEDULED_FEE_INCOME = 90;
	public final static int CALCULATED_FEE_INCOME = 91;
	public final static int ACCRUED_INT_EXP_PAY_SHORT = 92; // AccruedInt.ExpensePayable-Short
	public final static int AMORTIZATION_SHORT_POSITION = 93; // Amortizationshortposition
	public final static int ACCRETION_SHORT_POSITION = 94; // Accretionshortposition
	public final static int BookValue_Dis=95; //债券的成本折溢价
	public final static int FX_VALUATION_GAIN = 96; // FXValuationGain
	public final static int FX_VALUATION_LOSS = 97; // FXValuationLoss
	public final static int HTM_HEDGE_ADJUSTMENT = 98; // HTMHedgeAdjustment
	public final static int AMORT_DEFERRED_CAP_GAIN = 99; // AmortizationofDeferredCapGain
	public final static int AMORT_DEFERRED_CAP_LOSS = 100; // AmortizationofDeferredCapLoss
	public final static int DEFERRED_OCI_CAP_GAIN = 101; // DeferredOCI-CapitalGain
	public final static int DEFERRED_OCI_CAP_LOSS = 102; // DeferredOCI-CapitalLoss
	public final static int AMORT_DEFERRED_FX_GAIN = 103; // AmortizationofDeferredFXGain
	public final static int AMORT_DEFERRED_FX_LOSS = 104; // AmortizationofDeferredFXLoss
	public final static int DEFERRED_OCI_FX_GAIN = 105; // DeferredOCI-FXGain
	public final static int DEFERRED_OCI_FX_LOSS = 106; // DeferredOCI-FXLoss
	public final static int VARIATION_MARGIN_RP = 107; // VariationMarginAccount	变动保证金账户	资产3科目
	public final static int INVEST_CAPITAL = 108; // Investmentcapital
	public final static int FX_GL_WITHOLDING_TAXES = 109; //FXGLWITHOLDING_TAXES-REAL
	public final static int DEFERRED_CAPITAL_LOSS_ASSET = 110;
	public final static int DEFERRED_LOSS_ASSET_OFFSET = 111;
	public final static int ACCUMULATED_EARNINGS = 112; // AccumulatedEarnings
	public final static int BOOK_VALUE_ADJ_HEDGE = 113;
	public final static int UNREAL_GL_HEDGE_SUSPENSE = 114;
	public final static int DERIVATIVE_PREMIUM = 115;
	public final static int FX_GAIN_INCOME_UNREAL = 116;
	public final static int FX_LOSS_INCOME_UNREAL = 117;
	public final static int FX_GAIN_INCOME_REAL = 118;
	public final static int FX_LOSS_INCOME_REAL = 119;
	public final static int FX_GAIN_CAPITAL_REAL = 120;
	public final static int FX_LOSS_CAPITAL_REAL = 121;
	public final static int FX_GAIN_WITHHOLD_TAX_REAL = 122;
	public final static int FX_LOSS_WITHHOLD_TAX_REAL = 123;
	public final static int FX_GAIN_CAPITAL_UNREAL = 124;
	public final static int FX_LOSS_CAPITAL_UNREAL = 125;
	public final static int FX_DISCOUNT_CONTRACT = 126;
	public final static int FX_PREMIUM_CONTRACT = 127;
	public final static int IMPAIRED_GAIN_REAL = 128;
	public final static int IMPAIRED_LOSS_REAL = 129;
	public final static int FX_IMPAIRED_GAIN_REAL = 130;
	public final static int FX_IMPAIRED_LOSS_REAL = 131;
	public final static int OCI_UNREAL_GAIN = 132;
	public final static int OCI_UNREAL_LOSS = 133;
	public final static int OCI_FX_GAIN_CAPITAL_UNREAL = 134;
	public final static int OCI_FX_LOSS_CAPITAL_UNREAL = 135;
	public final static int OCI_FX_GAIN_INCOME_UNREAL = 136;
	public final static int OCI_FX_LOSS_INCOME_UNREAL = 137;
	public final static int OCI_REAL_GAIN = 138;
	public final static int OCI_REAL_LOSS = 139;
	public final static int OCI_FX_GAIN_CAPITAL_REAL = 140;
	public final static int OCI_FX_LOSS_CAPITAL_REAL = 141;
	public final static int OCI_FX_GAIN_INCOME_REAL = 142;
	public final static int OCI_FX_LOSS_INCOME_REAL = 143;
	public final static int COMMISION_FEE = 144;	///佣金
	public final static int TRAN_TAX = 145;			//税金
	public final static int STAMP_TAX = 146;		//印花税
	public final static int Manager_FEE = 147;	///管理费
	public final static int Custody_FEE = 148;	///托管费
	public final static int Sale_FEE = 149;	///销售费
	public final static int Other_FEE = 150;	///其它费用
	public final static int VALUATION_GAIN_Short = 200;		//估值收益	 期货做空	资本 6科目
	public final static int VALUATION_LOSS_Short = 201;		//估值亏损	 期货做空	资本6科目
	public final static int VARIATION_MARGIN_RP_Short = 202; // VariationMarginAccount	变动保证金账户	资产3科目 期货做空
	public final static int ACCRUED_INT_EXP_SHORT=203;	//AccruedInt.ExpenseShort	
	
	public final static int OUT_BOOK_VALUE=500;	
	public final static int OUT_BOOK_VALUE_SHORT=501;
	public final static int OUT_RECEIV_SECUR_SOLD=502;
	public final static int OUT_PAY_SECUR_PURCH=503;
	
	
	/********** 用于银行入账配置，非总账栏 ************/
	public final static int FX_BANK_CASH_GAIN_UNREAL = 10000;
	public final static int FX_BANK_CASH_LOSS_UNREAL = 10001;
	public final static int BANK_CASH_ACCT = 10002;
	public final static int PRE_TAX = 10004;
	
	/**********用于申购赎回的中国市场配置************/
	public final static int ch_red_fee=10100;				//应付赎回费
	public final static int ch_red_income_fee=10101;		//其它收入(赎回费收入)
	public final static int ch_switchout_income_fee=10102;	//其它收入(转出费收入)
	public final static int share_unreal_equal_sub=10103;		//未实现损益平准金(申购)
	public final static int share_unreal_equal_red=10104;		//未实现损益平准金(赎回)
	public final static int share_unreal_equal_contrib=10105;	//未实现损益平准金(转换转入)
	public final static int share_unreal_equal_withdraw=10106;		//未实现损益平准金(转换转出)
	public final static int share_real_equal_sub=10107;		//已实现损益平准金(申购)
	public final static int share_real_equal_red=10108;		//已实现损益平准金(赎回)
	public final static int share_real_equal_contrib=10109;	//已实现损益平准金(转换转入)
	public final static int share_real_equal_withdraw=10110;		//已实现损益平准金(转换转出)
	public final static int share_unreal_equal_reinvest=10111;		//未实现损益平准金(分红再投资)
	public final static int share_real_equal_reinvest=10112;		//已实现损益平准金(分红再投资)
	public final static int share_RECEIV_FUND_SHARES_switch_in=10113;	//应收申购款-转入
	public final static int share_PAY_FUND_SHARES_switch_out=10114;		//应付赎回款-转出
	public final static int share_Div2Share=10115;		//红利再投份额
	
	public final static int sec_new_sub=10115;		//新股申购,新股申购款
	
	
	public final static String UNKNOWN = "UNKNOWN ACCT";
	
	public final static HashMap<Integer, String> optionHM(){
		HashMap<Integer, String> optionHM=new HashMap<Integer, String>();
		optionHM.put(INC_CASH , INC_CASH+"|现金收入");
		optionHM.put(PRINCIPAL_CASH , PRINCIPAL_CASH+"|本金");
		optionHM.put(BOOK_VALUE , BOOK_VALUE+"|成本");
		optionHM.put(ACCRUED_INC , ACCRUED_INC+"|应收利息");
		optionHM.put(INC_DUE , INC_DUE+"|Income Due");
		optionHM.put(CARRY_VAL_ADJ , CARRY_VAL_ADJ+"|资产估值增值");
		optionHM.put(INVEST_REC_BANK , INVEST_REC_BANK+"|银行投资收入");
		optionHM.put(INVEST_REC_PAY_BROKER , INVEST_REC_PAY_BROKER+"|InvestmentRec/Pay-Broker");
		optionHM.put(INVEST_REC_PAY_INTERCO , INVEST_REC_PAY_INTERCO+"|InvestmentRec/Pay-Interco");
		optionHM.put(FX_RESERVE_CAP , FX_RESERVE_CAP+"|清算款汇兑损益");
		optionHM.put(PAY_COLLATERAL , PAY_COLLATERAL+"|Pay-Columnlateral");
		optionHM.put(PREPAID_INC , PREPAID_INC+"|Prepaid Income");
		optionHM.put(UNREAL_GAIN , UNREAL_GAIN+"|UnrealizedGain");
		optionHM.put(INC_AMORT , INC_AMORT+"|摊余成本折溢价收入");
		optionHM.put(DISCOUNT_ACCRUAL , DISCOUNT_ACCRUAL+"|Discount Accrual");
		optionHM.put(INC_ACCRUAL , INC_ACCRUAL+"|利息收入");
		optionHM.put(FX_GL_INC_UNREAL , FX_GL_INC_UNREAL+"|FxG/LIncome-Unreal");
		optionHM.put(INC_REC , INC_REC+"|收入获得");
		optionHM.put(FX_GL_INC_REAL , FX_GL_INC_REAL+"|FXG/LIncome-Real");
		optionHM.put(FX_GL_CAPITAL_UNREAL , FX_GL_CAPITAL_UNREAL+"|FXG/LCapital-Unreal");
		optionHM.put(MISC_INVEST_EXP , MISC_INVEST_EXP+"|Misc Investment Expense");
		optionHM.put(REC_MARGIN , REC_MARGIN+"|Receivable Margin");
		optionHM.put(FX_RESERVE_INC , FX_RESERVE_INC+"|FXReserve-Income");
		optionHM.put(FOREIGN_TAXES_PAID ,FOREIGN_TAXES_PAID +"|Foreign Taxes Paid");
		optionHM.put(CAPITAL_GAIN , CAPITAL_GAIN+"|资本收益");
		optionHM.put(CAPITAL_LOSS , CAPITAL_LOSS+"|资本亏损");
		optionHM.put(FX_GL_CAPITAL_REAL , FX_GL_CAPITAL_REAL+"|FXG/LCapital-Real");
		optionHM.put(MISC_INVEST_INC , MISC_INVEST_INC+"|Misc Investment Income");
		optionHM.put(CONTRIB_SURPLUS , CONTRIB_SURPLUS+"|Contributed Surplus");
		optionHM.put(INVEST_PAY_BANK , INVEST_PAY_BANK+"|银行投资支出");
		optionHM.put(INC_PAID , INC_PAID+"|收入支出");
		optionHM.put(BOOK_VALUE_SHORT , BOOK_VALUE_SHORT+"|成本(做空)");
		optionHM.put(DIVIDEND_REC , DIVIDEND_REC+"|应收股利");
		optionHM.put(INTEREST_RECVBL , INTEREST_RECVBL+"|应收利息");
		optionHM.put(RECEIV_SECUR_SOLD , RECEIV_SECUR_SOLD+"|应收清算款(卖出)");
		optionHM.put(PAY_SECUR_PURCH , PAY_SECUR_PURCH+"|应付清算款(买入)");
		optionHM.put(RECEIV_FUND_SHARES_SOLD , RECEIV_FUND_SHARES_SOLD+"|应收申购款");
		optionHM.put(FOREIGN_WH_TAX_RECV , FOREIGN_WH_TAX_RECV+"|Non Resident Alien Receivable");
		optionHM.put(PAY_FUND_SHARES_REDEEM , PAY_FUND_SHARES_REDEEM+"|应付赎回款");
		optionHM.put(DIVIDENDS_PAYABLE , DIVIDENDS_PAYABLE+"|应付股利");
		optionHM.put(CAPITAL_STOCK_PAR , CAPITAL_STOCK_PAR+"|实收资本/资本面值");
		optionHM.put(CAPITAL_STOCK_EXCESS , CAPITAL_STOCK_EXCESS+"|Capital Stock-excessofpar");
		optionHM.put(DIST_PD_CURR_YR_INC , DIST_PD_CURR_YR_INC+"|Distribution Spaid-Current Year-Income");
		optionHM.put(DIST_PD_CURR_YR_GAINS , DIST_PD_CURR_YR_GAINS+"|Distribution Spaid-Current Year Gains");
		optionHM.put(EQUAL_INC , EQUAL_INC+"|Equalization Income Credit");
		optionHM.put(EXP_REIMBURSE , EXP_REIMBURSE+"|Expense Reimbursement");
		optionHM.put(RECVBL_EXCESS_EXPENSE , RECVBL_EXCESS_EXPENSE+"|Receivable-excessexpense");
		optionHM.put(INTEREST_INC_DEFERRAL , INTEREST_INC_DEFERRAL+"|InterestIncomeDeferral");
		optionHM.put(EQUAL_INC_DEB , EQUAL_INC_DEB+"|Equalization IncDebit");
		optionHM.put(UNREAL_LOSS , UNREAL_LOSS+"|Unrealizedloss");
		optionHM.put(VALUATION_GAIN , VALUATION_GAIN+"|估增盈利");
		optionHM.put(VALUATION_LOSS , VALUATION_LOSS+"|估增亏损");
		optionHM.put(FOREIGN_COMMISSION , FOREIGN_COMMISSION+"|Foreign Commission");
		optionHM.put(FOREX_LONG , FOREX_LONG+"|FXContracts-Long");
		optionHM.put(FOREX_SHORT , FOREX_SHORT+"|FXContracts-Short");
		optionHM.put(FOREX_PREM_DISC , FOREX_PREM_DISC+"|Discount/Premium-FXContracts");
		optionHM.put(FOREX_UNREAL_GL_LONG , FOREX_UNREAL_GL_LONG+"|UnrealizedGain/LossFXContracts-Long");
		optionHM.put(FOREX_UNREAL_GL_SHORT , FOREX_UNREAL_GL_SHORT+"|UnrealizedGain/LossFXContracts-Short");
		optionHM.put(GOODWILL , GOODWILL+"|Good Will");
		optionHM.put(INVESTMENT_CLEARING ,INVESTMENT_CLEARING +"|Investment Clearing");
		optionHM.put(CAP_GAIN_INTRA_XFER , CAP_GAIN_INTRA_XFER+"|CapitalGain-IntraTransfer");
		optionHM.put(CAP_LOSS_INTRA_XFER , CAP_LOSS_INTRA_XFER+"|CapitalLoss-IntraTransfer");
		optionHM.put(FX_CAP_GAIN_INTRA_XFER , FX_CAP_GAIN_INTRA_XFER+"|FXCapitalGain-IntraTransfer");
		optionHM.put(FX_CAP_LOSS_INTRA_XFER , FX_CAP_LOSS_INTRA_XFER+"|FXCapitalLoss-IntraTransfer");
		optionHM.put(PROPORT_CARRY_VAL_ADJ , PROPORT_CARRY_VAL_ADJ+"|Proportioned Carry Value Adj");
		optionHM.put(PROPORT_FX_RESERVE_CAP ,PROPORT_FX_RESERVE_CAP +"|Proport-FXReserve Capital");
		optionHM.put(AMORT_UNREAL_G_L , AMORT_UNREAL_G_L+"|Amortizationo fUnrealizedGains/Losses");
		optionHM.put(PROPORT_AMORT_UNREAL_G_L , PROPORT_AMORT_UNREAL_G_L+"|Proportioned Amort Of UnrealizedG/L");
		optionHM.put(AMORT_UNREAL_FX_G_L , AMORT_UNREAL_FX_G_L+"|Amortization Of UnrealizedGains/Losses");
		optionHM.put(PROPORT_AMORT_UNREAL_FX_G_L , PROPORT_AMORT_UNREAL_FX_G_L+"|Proport.Amort.ofUnrealizedFXG/L");
		optionHM.put(DEFERRED_CAPITAL_GAIN , DEFERRED_CAPITAL_GAIN+"|Deferred CapitalGain");
		optionHM.put(DEFERRED_CAPITAL_LOSS , DEFERRED_CAPITAL_LOSS+"|Deferred CapitalLoss");
		optionHM.put(DEFERRED_FX_GAIN , DEFERRED_FX_GAIN+"|Deferred FXGain");
		optionHM.put(DEFERRED_FX_LOSS , DEFERRED_FX_LOSS+"|Deferred FXLoss");
		optionHM.put(NOT_PRIN_DERIV_PURCHASED , NOT_PRIN_DERIV_PURCHASED+"|Notional Principa lDerivative Purchased");
		optionHM.put(NOT_PRIN_DERIV_SOLD , NOT_PRIN_DERIV_SOLD+"|Notional Principal Derivative Sold");
		optionHM.put(LIAB_DERIV_PURCHASED ,LIAB_DERIV_PURCHASED +"|Liab Derivative Purchased");
		optionHM.put(LIAB_DERIV_SOLD , LIAB_DERIV_SOLD+"|Liab Derivative Sold");
		optionHM.put(FEE_PAYABLE , FEE_PAYABLE+"|Fee Payable");
		optionHM.put(FEE_EXPENSE ,FEE_EXPENSE +"|费用开支");
		optionHM.put(CORP_ACTION_OFFSET , CORP_ACTION_OFFSET+"|Corporate Action Offset");
		optionHM.put(NOTIONAL_FOREX_LONG , NOTIONAL_FOREX_LONG+"|Notional Principal FXForwards Long");
		optionHM.put(NOTIONAL_FOREX_SHORT , NOTIONAL_FOREX_SHORT+"|Notional Principal FXForwards Short");
		optionHM.put(NOTIONAL_FOREX_LONG_OFFSET , NOTIONAL_FOREX_LONG_OFFSET+"|Offset Notional FXForwards Long");
		optionHM.put(NOTIONAL_FOREX_SHORT_OFFSET , NOTIONAL_FOREX_SHORT_OFFSET+"|Offset Notional FXForwards Short");
		optionHM.put(FOREX_PREM_INCOME , FOREX_PREM_INCOME+"|Premium-FXContract(Income)");
		optionHM.put(FOREX_DISC_INCOME , FOREX_DISC_INCOME+"|Discount-FXContracts(Income)");
		optionHM.put(ARBITRAGE , ARBITRAGE+"|Arbitrage");
		optionHM.put(LATE_FEE_INCOME , LATE_FEE_INCOME+"|Late Fee Income");
		optionHM.put(SCHEDULED_FEE_INCOME , SCHEDULED_FEE_INCOME+"|Scheduled Fee Income");
		optionHM.put(CALCULATED_FEE_INCOME , CALCULATED_FEE_INCOME+"|Calculated Fee Income");
		optionHM.put(ACCRUED_INT_EXP_PAY_SHORT , ACCRUED_INT_EXP_PAY_SHORT+"|应收利息(空)");
		optionHM.put(ACCRUED_INT_EXP_SHORT , ACCRUED_INT_EXP_SHORT+"|利息收入(空)");
		optionHM.put(AMORTIZATION_SHORT_POSITION , AMORTIZATION_SHORT_POSITION+"|Amortization Short Position");
		optionHM.put(ACCRETION_SHORT_POSITION , ACCRETION_SHORT_POSITION+"|Accretion Short Position");
		optionHM.put(BookValue_Dis ,BookValue_Dis +"|摊余成本折溢价");
		optionHM.put(FX_VALUATION_GAIN , FX_VALUATION_GAIN+"|估增-汇兑收益");
		optionHM.put(FX_VALUATION_LOSS , FX_VALUATION_LOSS+"|估增-汇兑损失");
		optionHM.put(HTM_HEDGE_ADJUSTMENT , HTM_HEDGE_ADJUSTMENT+"|HTM Hedge Adjustment");
		optionHM.put(AMORT_DEFERRED_CAP_GAIN , AMORT_DEFERRED_CAP_GAIN+"|摊余成本折溢价利息收入");
		optionHM.put(AMORT_DEFERRED_CAP_LOSS , AMORT_DEFERRED_CAP_LOSS+"|摊余成本折溢价利息损失");
		optionHM.put(DEFERRED_OCI_CAP_GAIN ,DEFERRED_OCI_CAP_GAIN +"|DeferredOCI-CapitalGain");
		optionHM.put(DEFERRED_OCI_CAP_LOSS ,DEFERRED_OCI_CAP_LOSS +"|DeferredOCI-CapitalLoss");
		optionHM.put(AMORT_DEFERRED_FX_GAIN , AMORT_DEFERRED_FX_GAIN+"|Amortization Of Deferred FXGain");
		optionHM.put(AMORT_DEFERRED_FX_LOSS , AMORT_DEFERRED_FX_LOSS+"|Amortization Of Deferred FXLoss");
		optionHM.put(DEFERRED_OCI_FX_GAIN , DEFERRED_OCI_FX_GAIN+"|DeferredOCI-FXGain");
		optionHM.put(DEFERRED_OCI_FX_LOSS , DEFERRED_OCI_FX_LOSS+"|DeferredOCI-FXLoss");
		optionHM.put(VARIATION_MARGIN_RP , VARIATION_MARGIN_RP+"|追加保证金");
		optionHM.put(INVEST_CAPITAL , INVEST_CAPITAL+"|Investment Capital");
		optionHM.put(FX_GL_WITHOLDING_TAXES , FX_GL_WITHOLDING_TAXES+"|FX Gl Withholding Taxes Real");
		optionHM.put(DEFERRED_CAPITAL_LOSS_ASSET , DEFERRED_CAPITAL_LOSS_ASSET+"|Defferred Capital Loss Asset");
		optionHM.put(DEFERRED_LOSS_ASSET_OFFSET , DEFERRED_LOSS_ASSET_OFFSET+"|Defferred Loss Asset Offset");
		optionHM.put(ACCUMULATED_EARNINGS , ACCUMULATED_EARNINGS+"|Accumulated Earnings");
		optionHM.put(BOOK_VALUE_ADJ_HEDGE , BOOK_VALUE_ADJ_HEDGE+"|Book Value Adj Hedge");
		optionHM.put(UNREAL_GL_HEDGE_SUSPENSE , UNREAL_GL_HEDGE_SUSPENSE+"|Unreal Gl Hedge Suspense");
		optionHM.put(DERIVATIVE_PREMIUM , DERIVATIVE_PREMIUM+"|Derivative Permium");
		optionHM.put(FX_GAIN_INCOME_UNREAL , FX_GAIN_INCOME_UNREAL+"|利息收入汇兑未实现损益(收益)");
		optionHM.put(FX_LOSS_INCOME_UNREAL , FX_LOSS_INCOME_UNREAL+"|利息收入汇兑未实现损益(损失)");
		optionHM.put(FX_GAIN_INCOME_REAL , FX_GAIN_INCOME_REAL+"|Fx Gain Income Real");
		optionHM.put(FX_LOSS_INCOME_REAL , FX_LOSS_INCOME_REAL+"|Fx Loss Income Real");
		optionHM.put(FX_GAIN_CAPITAL_REAL , FX_GAIN_CAPITAL_REAL+"|Fx Gain Capital Real");
		optionHM.put(FX_LOSS_CAPITAL_REAL , FX_LOSS_CAPITAL_REAL+"|Fx Loss Capital Real");
		optionHM.put(FX_GAIN_WITHHOLD_TAX_REAL , FX_GAIN_WITHHOLD_TAX_REAL+"|Fx Gain Withhold Tax Real");
		optionHM.put(FX_LOSS_WITHHOLD_TAX_REAL , FX_LOSS_WITHHOLD_TAX_REAL+"|Fx Loss Withhold Tax Real");
		optionHM.put(FX_GAIN_CAPITAL_UNREAL , FX_GAIN_CAPITAL_UNREAL+"|资本汇兑损益-未实现(收益)");
		optionHM.put(FX_LOSS_CAPITAL_UNREAL , FX_LOSS_CAPITAL_UNREAL+"|资本汇兑损益-未实现(损失)");
		optionHM.put(FX_DISCOUNT_CONTRACT , FX_DISCOUNT_CONTRACT+"|Fx Discount Contract");
		optionHM.put(FX_PREMIUM_CONTRACT , FX_PREMIUM_CONTRACT+"|Fx Premium Contract");
		optionHM.put(IMPAIRED_GAIN_REAL , IMPAIRED_GAIN_REAL+"|Impaired Gain Real");
		optionHM.put(IMPAIRED_LOSS_REAL , IMPAIRED_LOSS_REAL+"|Inpaired Loss Real");
		optionHM.put(FX_IMPAIRED_GAIN_REAL , FX_IMPAIRED_GAIN_REAL+"|Fx Impaired Gain Real");
		optionHM.put(FX_IMPAIRED_LOSS_REAL , FX_IMPAIRED_LOSS_REAL+"|Fx Impaired Loss Real");
		/*optionHM.put(OCI_UNREAL_GAIN , OCI_UNREAL_GAIN+"|Oci Unreal Gain");
		optionHM.put(OCI_UNREAL_LOSS , OCI_UNREAL_LOSS+"|Oci Unreal Loss");
		optionHM.put(OCI_FX_GAIN_CAPITAL_UNREAL , OCI_FX_GAIN_CAPITAL_UNREAL+"|Oci Fx Gain Capital Unreal");
		optionHM.put(OCI_FX_LOSS_CAPITAL_UNREAL , OCI_FX_LOSS_CAPITAL_UNREAL+"|Oci Fx Loss Capital Unreal");
		optionHM.put(OCI_FX_GAIN_INCOME_UNREAL , OCI_FX_GAIN_INCOME_UNREAL+"|Oci Fx Gain Income Unreal");
		optionHM.put(OCI_FX_LOSS_INCOME_UNREAL , OCI_FX_LOSS_INCOME_UNREAL+"|Oci Fx Loss Income Unreal");
		optionHM.put(OCI_REAL_GAIN , OCI_REAL_GAIN+"|Oci Real Gain");
		optionHM.put(OCI_REAL_LOSS , OCI_REAL_LOSS+"|Oci Real Loss");
		optionHM.put(OCI_FX_GAIN_CAPITAL_REAL , OCI_FX_GAIN_CAPITAL_REAL+"|Oci Fx Gain Capital Real");
		optionHM.put(OCI_FX_LOSS_CAPITAL_REAL , OCI_FX_LOSS_CAPITAL_REAL+"|Oci Fx Loss Capital Real");
		optionHM.put(OCI_FX_GAIN_INCOME_REAL , OCI_FX_GAIN_INCOME_REAL+"|Oci Fx Gain Income Real");
		optionHM.put(OCI_FX_LOSS_INCOME_REAL , OCI_FX_LOSS_INCOME_REAL+"|Oci Fx Loss Income Real");*/
		optionHM.put(COMMISION_FEE , COMMISION_FEE+"|佣金");
		optionHM.put(FX_BANK_CASH_GAIN_UNREAL , FX_BANK_CASH_GAIN_UNREAL+"|Fx Bank Cash Gain Unreal");
		optionHM.put(FX_BANK_CASH_LOSS_UNREAL , FX_BANK_CASH_LOSS_UNREAL+"|Fx Bank Cash Loss Unreal");
		optionHM.put(BANK_CASH_ACCT , BANK_CASH_ACCT+"|Bank Cash Acct");
		optionHM.put(ch_red_fee, ch_red_fee+"|应付赎回费");
		optionHM.put(ch_red_income_fee, ch_red_income_fee+"|其它收入(赎回费收入)");
		optionHM.put(ch_switchout_income_fee, ch_switchout_income_fee+"|其它收入(转出费收入)");
		optionHM.put(share_unreal_equal_sub, share_unreal_equal_sub+"|未实现损益平准金(申购)");
		optionHM.put(share_unreal_equal_red, share_unreal_equal_red+"|未实现损益平准金(赎回)");
		optionHM.put(share_unreal_equal_contrib , share_unreal_equal_contrib+"|未实现损益平准金(转换转入)");
		optionHM.put(share_unreal_equal_withdraw , share_unreal_equal_withdraw+"|未实现损益平准金(转换转出)");
		optionHM.put(share_real_equal_sub , share_real_equal_sub+"|已实现损益平准金(申购)");
		optionHM.put(share_real_equal_red ,share_real_equal_red +"|已实现损益平准金(赎回)");
		optionHM.put(share_real_equal_contrib , share_real_equal_contrib+"|已实现损益平准金(转换转入)");
		optionHM.put(share_real_equal_withdraw ,share_real_equal_withdraw +"|已实现损益平准金(转换转出)");
		optionHM.put(share_unreal_equal_reinvest , share_unreal_equal_reinvest+"|未实现损益平准金(分红再投资)");
		optionHM.put(share_real_equal_reinvest , share_real_equal_reinvest+"|未实现损益平准金(分红再投资)");
		optionHM.put(PRE_TAX , PRE_TAX+"|预扣税");
		optionHM.put(TRAN_TAX , TRAN_TAX+"|交易费");
		optionHM.put(STAMP_TAX , STAMP_TAX+"|印花税");
		optionHM.put(share_RECEIV_FUND_SHARES_switch_in , share_RECEIV_FUND_SHARES_switch_in+"|应收申购款-转入");
		optionHM.put(share_PAY_FUND_SHARES_switch_out , share_PAY_FUND_SHARES_switch_out+"|应付赎回款-转出");
		optionHM.put(share_Div2Share , share_Div2Share+"|份额红利再投");
		
		optionHM.put(VALUATION_GAIN_Short , VALUATION_GAIN_Short+"|估值收益(卖空)");
		optionHM.put(VALUATION_LOSS_Short , VALUATION_LOSS_Short+"|估值亏损(卖空)");
		optionHM.put(VARIATION_MARGIN_RP_Short , VARIATION_MARGIN_RP_Short+"|追加保证金(卖空)");
		
		optionHM.put(Manager_FEE , Manager_FEE+"|管理费");
		optionHM.put(Custody_FEE , Custody_FEE+"|托管费");
		optionHM.put(Sale_FEE , Sale_FEE+"|销售费");
		optionHM.put(Other_FEE , Other_FEE+"|其它费用");
		
		optionHM.put(OUT_BOOK_VALUE , OUT_BOOK_VALUE+"|成本-表外");
		optionHM.put(OUT_BOOK_VALUE_SHORT , OUT_BOOK_VALUE_SHORT+"|成本-表外(做空)");
		optionHM.put(OUT_RECEIV_SECUR_SOLD , OUT_RECEIV_SECUR_SOLD+"|应收清算款-表外(卖出)");
		optionHM.put(OUT_PAY_SECUR_PURCH , OUT_PAY_SECUR_PURCH+"|应收清算款-买入(买入)");
		
		return optionHM;
	}
}
