package com.jixu.common.util;

import java.util.HashMap;

public class ReturnResult {
	
	public final static int post_buy_success = 1001; // 买入交易成功
	public final static int post_already_failed = 1002; // 该交易已被提交
	public final static int post_buy_failed = 1003; // 买入 失败
	public final static int post_sale_success = 1004; // 卖出成功
	public final static int post_sale_noSum = 1006; // 
	public final static int post_sale_zero = 1007; // 该股票已全部卖出
	public final static int post_zf_unmatch = 1008; // 卖出股数与持仓数量的正负不一致
	public final static int post_rcpy_success = 1021; //rcpy post成功
	public final static int post_offset_success = 1025; //post冲销模板成功
	public final static int post_fee_success = 1031; //post费用成功
	public final static int price_not_exist = 1032; //价格不存在
	public final static int record_not_exist = 1033; //记录不存在
	public final static int contract_already_deal = 1034; //合约已经被处理,不可再次交易
	public final static int smf_already_post = 1035; //证券已经被使用,请修改证券ID
	
	public final static int post_sub_success = 1040; //post申购成功
	public final static int post_red_success = 1041; //post赎回成功
	public final static int unpost_unpost = 1042; // 该股票还没有post
	public final static int unpost_rcpy_cashexist = 1043;	 //现金流还存在
	public final static int unpost_rcpy_exist = 1044;	 //应收应付还存在
	public final static int unpost_rcpy_cash_unmatch = 1045;	 //应收应付和实收实付ID不匹配
	public final static int unpost_rcpy_less_cash = 1046;	 //应收应付:剩余结转金额大于原始金额
	
	public final static int post_maturity_unmatched = 1047;	 //自动到期,找不到原始的买入交易合同单号。提交失败
	
	public final static int ledger_before_close_failed = 1051; 	//错误,在关帐后导入交易
	public final static int trade_after_TranDate = 1052; 		//在该交易日之后,存在其它交易
	public final static int ledgerBeforeClose_failed = 1053; 	//错误,在关帐后导入交易
	
	public final static int import_manual_voucher_success = 1061; //正确,手工分路导入
	public final static int import_manual_voucher_failed = 1062; //错误,手工分路导入失败
	
	public final static int jobType_not_exist = 1070; //作业类型不存在
	
	public final static int post_tran_success = 1099; //导入成功

	public final static int id_not_exist = 2001;	//id不存在
	public final static int default_cash_sec_notexist = 2002;	//证券账户的默认现金账户没有配置
	public final static int default_port_sec_notexist = 2003;	//投资组合的默认证券账户没有配置
	public final static int sec_bankacct_nocashacct = 2004;	//银行账户下没有多余的现金账户关联
	
	public final static int cal_nav_success = 3001;	//净值计算成功
	//1.净资产=资产-负债
	//2.净资产=资本+已实现收益+未实现收益+收入-开支
	public final static int cal_nav_unmatched_falied = 3002;	//净值计算失败,两种算法净值不匹配.
	public final static int cal_nav_unmatchedDate_falied = 3003;	//计算净值，时间不匹配
	public final static int del_nav_GlClose = 3004;	//总账已关帐，不能删除净值
	public final static int cal_nav_no_capital = 3005;	//没有份额值,请先申购
	public final static int nav_alread_exist = 3006;	//净值已经存在,无需重复计算
	
	public final static int close_tran_success = 3021;	//关帐成功
	public final static int close_tran_date_unmatch_failed = 3022;	//关帐失败，关帐日期错误
	
	public final static int close_gl_success = 3031;	//关帐成功
	public final static int close_gl_date_unmatch_failed = 3032;	//关帐失败，关帐日期错误
	public final static int close_gl_no_nav_failed = 3033;	//关帐失败，NAV还没有生成
	public final static int reopen_tran_glclose_failed = 3034;	//总账已关帐
	public final static int reopen_tran_unmatch_failed = 3035;	//开账日期与交易关帐日期不匹配
	public final static int reopen_tran_noCloseHis_failed = 3036;	//关帐历史中,找不到匹配记录
	public final static int reopen_gl_unmatch_failed = 3037;	//总账关帐日期与输入日期不匹配
	public final static int close_tran_gl_unclose_failed = 3038;	//总账尚未关闭
	public final static int closegl_already_failed = 3039;	//总账已关帐,无需重复关帐
	
	public final static int del_seccash_default=3041;	//该现金账户为证券账户默认现金账户。请先指定另外一个现金主账户，再删除该现金账户关联!
	
	public final static int voucher_exist = 3051;	//凭证已存在
	
	public final static int manager_notexist=3071;
	
	public final static int collat_notexist=3081;
	
	public final static int issuer_exist=4001;
	public final static int issuer_name_exist=4002;
	
	public final static int smf_exist=4021;
	
	public final static int smf_exist_tran_post=4022;
	
	public final static int smf_secid_exist=4023;
	
	public final static int acct_notexist_matched=4051;	//该账户不存在或者已被关联
	
	public final static int user_pwd_repeat_nums=4061;	//密码不能重复N次 
	
	public final static int wf_unMatchStatus=5001;
	public final static int wf_processUnCreate=5002;
	public final static int wf_DateUnMatch=5003;
	public final static int wf_NoPortSelect=5004;
	
	public final static int journal_account_unmap=5051;
	
	public final static int error_exception=6001;

	public final static int interface_duplicate_data=6051;
	public final static int interface_fileType_notExist=6052;
	public final static int interface_col_notExist=6053;
	
	public final static int coa_Exist=7000;
	
	public final static int header_not_exist=7021;
	
	public final static int tranID_Exist=7051;
	public final static int assetType_Exist=7052;
	
	public final static int newFund_filedNotComplete=7071;
	public final static int oldFund_filedNotComplete=7072;
	
	public final static int port_sameCode_exist=7081;
	public final static int bkInteAcct_name_exist=7091;
	public final static int bkCashAcct_name_exist=7092;
	public final static int bkAcct_InteAcct_BkNotMatch=7093;
	public final static int portexpense_name_exist=7094;
	public final static int bk_name_exist=7095;
	public final static int broker_name_exist=7096;
	public final static int seat_numbername_exist=7097;
	
	public final static int newfund_coanum_notmatch=8001;
	public final static int newfund_coaname_notmatch=8002;
	public final static int coa_notexist=8003;
	
	public final static int newfund_secid_notmatch=8004;
	public final static int newfund_secname_notmatch=8005;
	public final static int newfund_sec_notexist=8006;
	public final static int oldfund_voucher_exist=8007;
	public final static int oldfund_tran_exist=8008;
	public final static int oldfund_exeDateBeforeInitDate=8009;
	public final static int oldfund_sec_bkacct_notmatch=8010;
	public final static int targetSecid_notexist=8011;
	
	public final static int tran_exist_after_tradeDate=8051;
	
	public final static int verify_yourself=9001;
	public final static int excelformat_error=9002;
	public final static int clientname_exist=9003;
	public final static int anaValPortfolio_failed=9011;
	public final static int anaValDate_failed=9012;
	
	public final static int delete_chinese=10001;
	public final static int secCash_chinese=10002;
	public final static int fx_gainloss_chinese=10003;
	public final static int bankCash_chinese=10004;
	public final static int fx_tran_chinese=10005;
	public final static int closeto_journal_chinese=10006;
	public final static int setInterest_chinese=10007;
	public final static int user_notexist_chinese=10008;
	public final static int user_incorrect_pwd_chinese=10009;
	public final static int yes_chinese=10010;
	public final static int no_chinese=10011;
	public final static int newFundPeriodStart=10012;
	public final static int oldFundRcpyDesc=10013;
	public final static int oldFundCashPostionDesc=10014;
	public final static int mmIncomeSet_chinese=100015;
	public final static int autoJobSet_chinese=100016;
	public final static int incomeSet_chinese=100017;
	public final static int user_alreadyexist_chinese=100018;
	
	public final static int process_entity_exist=100200;
	
	public final static int tran_exist_after_tran=100201;
	public final static int tranfw_exist_after_tranwfw=100202;
	
	public static HashMap<Integer, String> resultHM;
	
	public static HashMap<Integer, String> dicHM;
	
	public static HashMap<Integer, String> getResultHM() {
		if(resultHM==null){
			resultHM=new HashMap<Integer, String>();
			resultHM.put(issuer_exist, "该发行商关联着股票，不能被删除");
			resultHM.put(smf_exist, "该证券已经存在交易，不能被删除");
			resultHM.put(smf_exist_tran_post, "该证券已经存在交易,不能修改证券主ID");
			resultHM.put(smf_secid_exist, "该证券主ID已存在");
			resultHM.put(issuer_name_exist, "该发行商名字已经存在");
			resultHM.put(wf_unMatchStatus, "所选投资组合状态不匹配");
			resultHM.put(wf_processUnCreate, "当日的工作流程还未建立,该用户不具有建立工作流程的权限");
			resultHM.put(wf_DateUnMatch, "选中投资组合的做账时间不在同一个工作日");
			resultHM.put(post_already_failed, "该交易已被提交");
			resultHM.put(post_sale_noSum, "剩余股数小于卖出股数");
			resultHM.put(post_sale_zero, "该证券持仓数量小于卖出数量");
			resultHM.put(post_zf_unmatch, "卖出股数与持仓数量的正负不一致");
			resultHM.put(ledger_before_close_failed, "交易已关帐");
			resultHM.put(ledgerBeforeClose_failed, "交易已关帐,无需重复关帐");
			resultHM.put(close_gl_no_nav_failed, "股关帐失败,NAV还没有生成");
			resultHM.put(reopen_tran_glclose_failed, "总账已关帐");
			resultHM.put(reopen_tran_unmatch_failed, "开账日期与交易关帐日期不匹配");
			resultHM.put(reopen_tran_noCloseHis_failed, "关帐历史中,找不到匹配记录 ");
			resultHM.put(close_tran_date_unmatch_failed, "交易关帐日期不匹配 ");
			resultHM.put(reopen_gl_unmatch_failed, "交易关帐日期不匹配 ");
			resultHM.put(close_gl_date_unmatch_failed, "关帐失败，关帐日期错误 ");
			resultHM.put(close_tran_gl_unclose_failed, "总账尚未关闭");
			resultHM.put(cal_nav_unmatched_falied, "净值计算核对不一致");
			resultHM.put(cal_nav_unmatchedDate_falied, "计算净值，时间不匹配");
			resultHM.put(del_nav_GlClose, "总账已关帐，不能删除净值");
			resultHM.put(journal_account_unmap, "科目号尚未映射");
			resultHM.put(id_not_exist, "编号不存在，请重新选择或输入");
			resultHM.put(unpost_unpost, "该股票还未提交");
			resultHM.put(unpost_rcpy_cashexist, "对应的已收已付现金还存在");
			resultHM.put(unpost_rcpy_exist, "对应的应收应付还存在");
			resultHM.put(unpost_rcpy_cash_unmatch, "应收应付和实收实付ID不匹配");
			resultHM.put(unpost_rcpy_less_cash, "应收应付:剩余结转金额大于原始金额");
			resultHM.put(acct_notexist_matched, "该账户不存在或者已被关联");
			resultHM.put(interface_duplicate_data, "该数据已存在");
			resultHM.put(interface_fileType_notExist, "该接口的文件格式尚不支持");
			resultHM.put(coa_Exist, "科目号已存在");
			resultHM.put(tranID_Exist, "交易代码已存在");
			resultHM.put(assetType_Exist, "证券类型已存在");
			resultHM.put(newFund_filedNotComplete, "新基金内容尚未填写完整,必须完成投资组合、银行和证券帐号信息的填写");
			resultHM.put(port_sameCode_exist, "相同投资组合代码的投资组合已存在");
			resultHM.put(bkInteAcct_name_exist, "相同的利息账户名已存在");
			resultHM.put(bkCashAcct_name_exist, "相同的银行账户名已存在");
			resultHM.put(bk_name_exist, "相同的银行名已存在");
			resultHM.put(broker_name_exist, "相同的券商名已存在");
			resultHM.put(seat_numbername_exist, "该券商下相同席位代码已存在");
			resultHM.put(bkAcct_InteAcct_BkNotMatch, "利息账户和银行账户的银行不匹配");
			resultHM.put(portexpense_name_exist, "相同的投资组合费用已存在");
			resultHM.put(wf_NoPortSelect, "请选择投资组合");
			resultHM.put(newfund_coanum_notmatch, "科目号不一致");
			resultHM.put(newfund_coaname_notmatch, "科目名称不一致");
			resultHM.put(newfund_secid_notmatch, "证券代码不一致");
			resultHM.put(newfund_secname_notmatch, "证券名称不一致");
			resultHM.put(newfund_sec_notexist, "证券不存在");
			resultHM.put(oldfund_voucher_exist, "已有凭证存在,科目余额初始化失败");
			resultHM.put(coa_notexist, "科目不存在");
			resultHM.put(oldfund_tran_exist, "已有交易存在,证券头寸初始化失败");
			resultHM.put(oldfund_exeDateBeforeInitDate, "交易日必须大于等于基金成立日");
			resultHM.put(manager_notexist, "基金经理不存在");
			resultHM.put(oldfund_sec_bkacct_notmatch, "找不到该证券对应的银行账户");
			resultHM.put(oldFund_filedNotComplete, "老基金期初内容尚未填写完整,必须完成科目余额和证券头寸内容");
			resultHM.put(interface_col_notExist, "列不存在");
			resultHM.put(default_cash_sec_notexist,"证券账户的默认现金账户没有配置");
			resultHM.put(tran_exist_after_tradeDate, "已有交易存在于该交易日之后");
			resultHM.put(del_seccash_default, "该现金账户为证券账户默认现金账户。请先指定另外一个现金主账户，再删除该现金账户关联!");
			resultHM.put(post_maturity_unmatched, "匹配不到原始的买入交易合同单号。提交失败");
			resultHM.put(collat_notexist, "缺少抵押物");
			resultHM.put(default_port_sec_notexist, "投资组合的默认证券账户没有配置");
			resultHM.put(sec_bankacct_nocashacct, "银行账户下没有多余的现金账户关联");
			resultHM.put(user_pwd_repeat_nums, "密码不能重复");
			resultHM.put(trade_after_TranDate, "交易日之后存在该股票的其它交易");
			resultHM.put(targetSecid_notexist, "标的证券不存在,请重新配置");
			resultHM.put(price_not_exist, "证券价格不存在");
			resultHM.put(verify_yourself, "没有权限操作该用户");
			resultHM.put(cal_nav_no_capital, "没有份额值,请先申购");
			resultHM.put(excelformat_error, "上传估值表文件格式错误,非Excel文件");
			resultHM.put(clientname_exist, "机构用户名称已经存在");
			resultHM.put(anaValPortfolio_failed, "解析不到该估值表的基金名称");
			resultHM.put(anaValDate_failed, "解析不到该估值表的日期");
			resultHM.put(record_not_exist, "记录不存在");
			resultHM.put(closegl_already_failed, "总账已关帐,无需重复关帐");
			resultHM.put(nav_alread_exist, "净值已存在,无需重复计算");
			resultHM.put(jobType_not_exist, "作业类型不存在");
			resultHM.put(header_not_exist, "消息不存在");
			resultHM.put(process_entity_exist, "所选对象中存在流程中的数据:");
			resultHM.put(tran_exist_after_tran, "该交易之后存在到期或互换交易");
			resultHM.put(contract_already_deal, "合约已经被处理,不可再次交易");
			resultHM.put(smf_already_post, "合约已经被使用,请输入新的合约号");
			resultHM.put(tranfw_exist_after_tranwfw, "该交易之后存在平仓,到期或交割交易");
		}
		return resultHM;
	}
	
	public static HashMap<Integer, String> getDicHM() {
		if(dicHM==null){
			dicHM=new HashMap<Integer, String>();
			dicHM.put(delete_chinese, "删除");
			dicHM.put(secCash_chinese, "证券清算款");
			dicHM.put(fx_gainloss_chinese, "汇兑损益");
			dicHM.put(bankCash_chinese, "银行存款");
			dicHM.put(fx_tran_chinese, "换汇");
			dicHM.put(closeto_journal_chinese, "期末结转");
			dicHM.put(setInterest_chinese, "银行结息");
			dicHM.put(user_notexist_chinese, "用户不存在");
			dicHM.put(user_alreadyexist_chinese, "用户名已存在");
			dicHM.put(user_incorrect_pwd_chinese, "密码错误");
			dicHM.put(yes_chinese, "是");
			dicHM.put(no_chinese, "否");
			dicHM.put(newFundPeriodStart, "基金初始化科目余额");
			dicHM.put(oldFundRcpyDesc, "基金初始化应收应付");
			dicHM.put(oldFundCashPostionDesc, "基金初始化银行现金");
			dicHM.put(mmIncomeSet_chinese, "货币基金结转收益");
			dicHM.put(incomeSet_chinese, "利息结转");
			dicHM.put(autoJobSet_chinese, "自动到期");
		}
		return dicHM;
	}
}
