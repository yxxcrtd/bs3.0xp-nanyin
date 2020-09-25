--单估值系统，费用在界面的默认支付方式

--佣金不随交易支付(股票)
update JXT_TRAN_FEE_DEF set CASHPERCENT=100 where FEETYPEID=101;
--印花税随交易支付(股票)
update JXT_TRAN_FEE_DEF set CASHPERCENT=0 where FEETYPEID=102;
--上交所交易费用随交易支付(股票)
update JXT_TRAN_FEE_DEF set CASHPERCENT=0 where FEETYPEID=103;
--深交所交易费用随交易支付(股票)
update JXT_TRAN_FEE_DEF set CASHPERCENT=0 where FEETYPEID=104;
--上清所结算手续费支出不随交易支付(利率互换)
update JXT_TRAN_FEE_DEF set CASHPERCENT=100 where FEETYPEID=121;
--交易手续费支出(利率互换)
update JXT_TRAN_FEE_DEF set CASHPERCENT=100 where FEETYPEID=122;
--外汇交易手续费(远期换汇)
update JXT_TRAN_FEE_DEF set CASHPERCENT=100 where FEETYPEID=5000;
--即期交易手续费(即期换汇)
update JXT_TRAN_FEE_DEF set CASHPERCENT=100 where FEETYPEID=5034;
--外汇掉期手续费(外汇掉期)
update JXT_TRAN_FEE_DEF set CASHPERCENT=100 where FEETYPEID=5040;

commit;