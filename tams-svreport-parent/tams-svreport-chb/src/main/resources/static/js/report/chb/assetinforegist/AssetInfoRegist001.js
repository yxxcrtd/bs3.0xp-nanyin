/**
 * Created by weiyuqiang on 2019/1/22.
 */
$(function() {
	//跳转的页码
	var pageCount = 1;
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
			// 资产负债类型
			assetType : $('#assetType').val(),
			// 资产二级类型
			assetTypeTwo : $('#assetTypeTwo').val() == null ? null : $(
					'#assetTypeTwo').val().join(","),
			// 行内资产编码
			assetCode : $('#assetCode').val(),
			// 数据状态
			status : $('#status').val() == null ? null : $('#status').val().join(","),
			// 报送日期
			sendDateStr : $('#submitDate').startDate(),
			sendDateEnd : $('#submitDate').endDate(),
			// 交易流通场所
			tradeMarket : $('#tradeMarket').val() == null ? null : $(
					'#tradeMarket').val().join(","),
			// 币种
			ccy : $('#ccy').val(),
			// 反馈状态
			submitFeedbackStatus : $('#submitFeedbackStatus').val()== null ? null : $(
					'#submitFeedbackStatus').val().join(","),
			// 数据日期
			statusDateStr : $('#statusDate').startDate(),
			statusDateEnd : $('#statusDate').endDate(),
            //报送截止日
            submitDeadLineStart:$('#submitDeadLine').startDate(),
            submitDeadLineEnd:$('#submitDeadLine').endDate(),
            //起息日到期日
            vDateStart:$('#vDate').startDate(),
            vDateEnd:$('#vDate').endDate(),
            mDateStart:$('#mDate').startDate(),
            mDateEnd:$('#mDate').endDate(),
			// 报送状态
			submitStatus : $('#submitStatus').val() == null ? null : $(
					'#submitStatus').val().join(",")
		}
	};
	var table00 = $('#dataTable00').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
				{ title: "起息日",width:100,sortField:'vdate', field: 'vdate', type: 'date'},
				{ title: "到期日",width:100,sortField:'mdate', field: 'mdate', type: 'date'},
				]
	});
	var table01 = $('#dataTable01').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
				{ title: "资金存入银行",width:150,sortField:'bankName', field: 'bankName'},
				{ title: "存款金额",width:100,sortField:'amt', field: 'amt', digit:2, type: 'money'},
				{ title: "起息日",width:100,sortField:'vdate', field: 'vdate', type: 'date'},
				{ title: "到期日",width:100,sortField:'mdate', field: 'mdate', type: 'date'},
				{ title: "年利率(%)",width:100,sortField:'assetYield', field: 'assetYield', type: 'number', digit:5},
				{ title: "计息基础",width:100,sortField:'basis', field: 'basis'},
				{ title: "存款类型",width:100,sortField:'depoType', field: 'depoType'},
//				{ title: "是否为结构性存款",width:150,sortField:'isStruct', field: 'isStruct'},
				{ title: "结构性存款挂钩标的类别",width:150,sortField:'targetType', field: 'targetType'},
				{ title: "结构性存款挂钩标的",width:150,sortField:'target', field: 'target'},
				{ title: "备注",width:100,sortField:'remark', field: 'remark'},
				]
	});
	var table02 = $('#dataTable02').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
	            { title: "起息日",width:100,sortField:'vdate', field: 'vdate', type: 'date'},
	            { title: "到期日",width:100,sortField:'mdate', field: 'mdate', type: 'date'},
	            { title: "对手方",width:100,sortField:'rivalParty', field: 'rivalParty'},
	            { title: "对手方类型",width:150,sortField:'rivalPartyType', field: 'rivalPartyType'},
	            { title: "年利率(%)",width:100,sortField:'assetYield', field: 'assetYield', type: 'number', digit:5},
	            { title: "计息基础",width:100,sortField:'basis', field: 'basis'},
	            { title: "回购标的类别",width:150,sortField:'targetType1', field: 'targetType1'},
	            { title: "回购标的金额",width:150,sortField:'targetAmt', field: 'targetAmt', type: 'money', digit:2},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table03 = $('#dataTable03').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
			    { title: "代码",width:100,sortField:'assetId', field: 'assetId'},
			    { title: "名称",width:100,sortField:'assetName', field: 'assetName'},
			    { title: "具体类别",width:100,sortField:'chbSectype', field: 'chbSectype'},
			    { title: "发行方式",width:100,sortField:'issueMode', field: 'issueMode'},
			    { title: "主体评级",width:100,sortField:'secMainGrade', field: 'secMainGrade'},
//			    { title: "债项评级",width:100,sortField:'', field: 'secCreditGrade'},
			    { title: "发行机构类型（按规模划分）",width:200,sortField:'partyType1', field: 'partyType1'},
			    { title: "发行机构类型（按技术领域划分）",width:200,sortField:'partyType3', field: 'partyType3'},
			    { title: "发行机构类型（按经济类型划分）",width:200,sortField:'partyType2', field: 'partyType2'},
//			    { title: "发行机构所属行业",width:150,sortField:'', field: 'industry'},
			    { title: "发行机构所属行业",width:200,sortField:'industryLevel2', field: 'industryLevel2'},
			    { title: "登记托管机构",width:200,sortField:'registOrg', field: 'registOrg'},
			    { title: "登记托管机构说明",width:200,sortField:'registorgComment', field: 'registorgComment'},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table04 = $('#dataTable04').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "名称",width:100,sortField:'assetName', field: 'assetName'},
//                { title: "存款金额",width:100,sortField:'', field: 'amt', type: 'money' ,digit:2},
                { title: "金额",width:100,sortField:'amt', field: 'amt', type: 'money' ,digit:2},
                { title: "份额面值",width:100,sortField:'prinBaseamt', field: 'prinBaseamt', type: 'money' ,digit:4},
                { title: "收/受益权类型",width:150,sortField:'profitType', field: 'profitType'},
                { title: "是否属于买入返售",width:150,sortField:'isResale', field: 'isResale'},
                { title: "起息日",width:100,sortField:'vdate', field: 'vdate', type: 'date'},
                { title: "到期日",width:100,sortField:'mdate', field: 'mdate', type: 'date'},
                { title: "法定到期日",width:100,sortField:'fmdate', field: 'fmdate', type: 'date'},
                //delete by wm 删除中债不报送字段 start 20201031 
//                { title: "项目期限(天）",width:150,sortField:'termDate', field: 'termDate',digit:0},
                //delete by wm 删除中债不报送字段 end 20201031 
                { title: "是否有预期收益率",width:150,sortField:'rateFlag', field: 'rateFlag'},
                { title: "项目收益率（利率）(%)",width:150,sortField:'assetYield', field: 'assetYield',type: 'number' ,digit:5},
                { title: "计息类型",width:150,sortField:'couponSpecies', field: 'couponSpecies'},
                { title: "规则付息标识",width:150,sortField:'interestRuleFlag', field: 'interestRuleFlag'},
                { title: "付息频率（个月/次）",width:150,sortField:'intpayCycle', field: 'intpayCycle'},
                { title: "利息分布方式",width:150,sortField:'intpayRule', field: 'intpayRule'},
                { title: "还本付息情况说明",width:150,sortField:'repayCmt', field: 'repayCmt'},
				{ title: "计息基础",width:100,sortField:'basis', field: 'basis'},
                { title: "基准利率种类",width:150,sortField:'basisType', field: 'basisType'},
                { title: "是否有浮动因子",width:150,sortField:'floatFactorFlag', field: 'floatFactorFlag'},
                { title: "浮动因子(%)",width:150,sortField:'floatFactor', field: 'floatFactor',type: 'number' ,digit:5},
                { title: "利差（BP）",width:150,sortField:'spreadRate', field: 'spreadRate',type: 'number' ,digit:5},
                { title: "结构档次",width:150,sortField:'structGrade', field: 'structGrade'},
                { title: "还本方式",width:150,sortField:'repaymentType', field: 'repaymentType'},
                { title: "分期还本条款标识",width:150,sortField:'periodRepaymentFlag', field: 'periodRepaymentFlag'},
                { title: "基础资产类型",width:150,sortField:'basicAssetType', field: 'basicAssetType'},
                { title: "超额收益分配比例(%)",width:150,sortField:'excessProfitRate', field: 'excessProfitRate',type: 'number' ,digit:5},
                { title: "融资人",width:150,sortField:'financie', field: 'financie'},
                { title: "融资人内部信用评级",width:150,sortField:'fcrGradeIn', field: 'fcrGradeIn'},
                { title: "外部评级机构名称及对融资人评级结果",width:150,sortField:'fcrGradeOut', field: 'fcrGradeOut'},
			    { title: "融资人类型（按规模划分）",width:200,sortField:'financierType1', field: 'financierType1'},
			    { title: "融资人类型（按技术领域划分）",width:200,sortField:'financierType2', field: 'financierType2'},
			    { title: "融资人类型（按经济类型划分）",width:200,sortField:'financierType3', field: 'financierType3'},
                { title: "融资项目",width:150,sortField:'financeProj', field: 'financeProj'},
//                { title: "融资人所属行业",width:150,sortField:'', field: 'industry1'},
                { title: "融资人所属行业",width:150,sortField:'industryLevel2Type', field: 'industryLevel2Type'},
//                { title: "融资项目所属行业",width:150,sortField:'', field: 'proIndustry'},
                { title: "融资项目所属行业",width:150,sortField:'proIndustryLevel2', field: 'proIndustryLevel2'},
                { title: "项目是否属于重点监控行业和领域",width:150,sortField:'industryFlag', field: 'industryFlag'},
                { title: "重点监控行业和领域类别",width:150,sortField:'monitType', field: 'monitType'},
                { title: "重点监控行业和领域类别说明",width:150,sortField:'monTypeComm', field: 'monTypeComm'},
                { title: "担保方式",width:150,sortField:'guaType', field: 'guaType'},
                { title: "担保情况说明",width:150,sortField:'guaTypeRemark', field: 'guaTypeRemark'},
                
                { title: "抵质押物类型",width:150,sortField:'pledgeType', field: 'pledgeType'},
                { title: "抵质押物价值（元）",width:150,sortField:'pledgeValue', field: 'pledgeValue',type: 'number' ,digit:2},
                { title: "担保性质",width:150,sortField:'natureGuarantee', field: 'natureGuarantee'},
                { title: "担保人与融资人关系",width:150,sortField:'relationFinan', field: 'relationFinan'},
                
                
                { title: "融资人主体评级",width:150,sortField:'financierGrade', field: 'financierGrade'},
                { title: "资产内部评级",width:150,sortField:'inGrade', field: 'inGrade'},
                { title: "资产外部评级",width:150,sortField:'outGrade', field: 'outGrade'},
                
                { title: "含权类型",width:150,sortField:'containType', field: 'containType'},
                { title: "行权方式",width:150,sortField:'exerciseType', field: 'exerciseType'},
                { title: "固定行权日",width:150,sortField:'exerciseDate', field: 'exerciseDate',type: 'date'},
                { title: "首次行权日期",width:150,sortField:'firstExerciseDate', field: 'firstExerciseDate',type: 'date'},
                { title: "行权周期",width:150,sortField:'exercisePeriod', field: 'exercisePeriod',type: 'number'},
                { title: "行权价格",width:150,sortField:'exercisePrice', field: 'exercisePrice',type: 'number' ,digit:4},
                { title: "永续条款类型",width:150,sortField:'perpetualClauseType', field: 'perpetualClauseType'},
                { title: "利息递延条款类型",width:150,sortField:'deferredInterestType', field: 'deferredInterestType'},
                { title: "递延利息是否计息",width:150,sortField:'deferredInterestInt', field: 'deferredInterestInt'},
                { title: "首次重定价日期",width:150,sortField:'firstReprceDate', field: 'firstReprceDate',type: 'date'},
                { title: "重定价周期",width:150,sortField:'reprceCycle', field: 'reprceCycle',type: 'number'},
                { title: "部分赎回标识",width:150,sortField:'partRedemFlag', field: 'partRedemFlag'},
                { title: "部分赎回比例(%)",width:200,sortField:'partRedemRate', field: 'partRedemRate',type: 'number' ,digit:5},
                
                { title: "选择权",width:150,sortField:'chose', field: 'chose'},
                { title: "行权条件说明",width:150,sortField:'conditionCmm', field: 'conditionCmm'},
                
                { title: "增信机构代码",width:150,sortField:'creditOrgCode', field: 'creditOrgCode'},
                { title: "增信机构名称",width:150,sortField:'creditOrgName', field: 'creditOrgName'},
                
                { title: "融资人所属地区",width:150,sortField:'finArea', field: 'finArea'},
                { title: "融资总费率(%)",width:150,sortField:'totalFeeRate', field: 'totalFeeRate',type: 'number' ,digit:5},
                { title: "融资人组织机构（社会信用）代码",width:150,sortField:'finOrgCode', field: 'finOrgCode'},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table05 = $('#dataTable05').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'status', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "是否为收/受益权",width:100,sortField:'isRight', field: 'isRight'},
                { title: "是否属于买入返售",width:150,sortField:'isResale', field: 'isResale'},
                { title: "非标准化债券类资产类型",width:100,sortField:'notstandSecType', field: 'notstandSecType'},
                { title: "非标准化债券类资产数量",width:100,sortField:'notstandSecNum', field: 'notstandSecNum'},
                { title: "合计金额",width:150,sortField:'amt', field: 'amt', type: 'money',digit:2},
                { title: "加权剩余期限（天）",width:150,sortField:'remainTerm', field: 'remainTerm', type: 'number',digit:2},
                { title: "最长剩余期限（天）",width:150,sortField:'maxRemainTerm', field: 'maxRemainTerm'},
                { title: "最短剩余期限（天）",width:150,sortField:'minRemainTerm', field: 'minRemainTerm'},
                { title: "起息日",width:100,sortField:'vdate', field: 'vdate', type: 'date'},
                { title: "到期日",width:100,sortField:'mdate', field: 'mdate', type: 'date'},
                { title: "贴现利率(%)",width:150,sortField:'assetYield', field: 'assetYield', type: 'number',digit:5},
                //modify by wm 行业分类展示列修改  对应bug号 ZZOTZL-352 start
                //{ title: "行业一级分类",width:150,sortField:'industryShares', field: 'industryShares'},
                { title: "行业分类",width:150,sortField:'industryShares2', field: 'industryShares2'},
                //modify by wm 行业分类展示列修改  对应bug号 ZZOTZL-352 end
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table06 = $('#dataTable06').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "股票代码",width:100,sortField:'assetId', field: 'assetId'},
                { title: "股票/企业名称",width:150,sortField:'assetName', field: 'assetName'},
                { title: "股票类型",width:100,sortField:'sharesType', field: 'sharesType'},
                //modify by wm 行业分类展示列修改  对应bug号 ZZOTZL-352 start
                //{ title: "行业一级分类",width:150,sortField:'industryShares', field: 'industryShares'},
                { title: "行业分类",width:150,sortField:'industryShares2', field: 'industryShares2'},
                //modify by wm 行业分类展示列修改  对应bug号 ZZOTZL-352 end
                { title: "投资阶段",width:150,sortField:'investStage', field: 'investStage'},
                
                { title: "股权退出安排",width:150,sortField:'equityQuit', field: 'equityQuit',type: 'date'},
                
                { title: "投资企业类型（按规模划分）",width:200,sortField:'sharesType1', field: 'sharesType1'},
			    { title: "投资企业类型（按技术领域划分）",width:200,sortField:'sharesType3', field: 'sharesType3'},
			    { title: "投资企业类型（按经济类型划分）",width:200,sortField:'sharesType2', field: 'sharesType2'},
                { title: "是否为质押融资",width:150,sortField:'isPledge', field: 'isPledge'},
                
                { title: "是否为债转股",width:150,sortField:'deptEquity', field: 'deptEquity'},
                
                { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table07 = $('#dataTable07').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "名称",width:150,sortField:'assetName', field: 'assetName'},
                { title: "名义本金",width:150,sortField:'nomCapi', field: 'nomCapi', type: 'money'},
				{ title: "标的类别",width:150,sortField:'targetType3', field: 'targetType3'},

				{ title: "持有目的",width:150,sortField:'holdPurpose', field: 'holdPurpose'},
				
				{ title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table08 = $('#dataTable08').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "所属国家或地区",width:150,sortField:'countryArea', field: 'countryArea'},
			    { title: "债券名称",width:100,sortField:'assetName', field: 'assetName'},
			    { title: "债券代码",width:100,sortField:'assetId', field: 'assetId'},
				{ title: "发行机构",width:150 , field: 'issuePartyName',sortField:'issuePartyName'},
//			    { title: "发行机构所属行业",width:150,sortField:'', field: 'industry'},
			    { title: "发行机构所属行业",width:200,sortField:'industryLevel2', field: 'industryLevel2'},
                { title: "起息日",width:100,sortField:'vdate', field: 'vdate', type: 'date'},
                { title: "到期日",width:100,sortField:'mdate', field: 'mdate', type: 'date'},
	            { title: "期限(月）",width:100,sortField:'termMonth', field: 'termMonth'},
				{ title: "发行机构主体信用评级",width:100,sortField:'partyGrade', field: 'partyGrade'},
				{ title: "债券信用评级",width:100,sortField:'secCreditGradeType', field: 'secCreditGradeType'},
                { title: "票面利率(%)",width:150,sortField:'assetYield', field: 'assetYield',type: 'number',digit:5},
                { title: "付息频率（个月/次）",width:150,sortField:'intpayCycle', field: 'intpayCycle'},
                { title: "担保情况说明",width:150,sortField:'guaTypeRemark', field: 'guaTypeRemark'},
                { title: "是否含权等特殊条款情况说明",width:150,sortField:'specialCmt', field: 'specialCmt'},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table09 = $('#dataTable09').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "所属国家或地区",width:150,sortField:'countryArea', field: 'countryArea'},
                { title: "起息日",width:100,sortField:'vdate', field: 'vdate', type: 'date'},
                { title: "到期日",width:100,sortField:'mdate', field: 'mdate', type: 'date'},
	            { title: "对手方",width:100,sortField:'rivalParty', field: 'rivalParty'},
				{ title: "年利率(%)",width:100,sortField:'assetYield', field: 'assetYield',type: 'number',digit:5},
				{ title: "计息基础",width:100,sortField:'basis', field: 'basis'},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table10 = $('#dataTable10').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "所属国家或地区",width:150,sortField:'countryArea', field: 'countryArea'},
			    { title: "股票/基金代码",width:100,sortField:'assetId', field: 'assetId'},
			    { title: "股票/基金名称",width:100,sortField:'assetName', field: 'assetName'},
				{ title: "发行机构",width:150 , field: 'issuePartyName',sortField:'issuePartyName'},
				//modify by wm 行业分类展示列修改  对应bug号 ZZOTZL-352 start
                //{ title: "行业一级分类",width:150,sortField:'industryShares', field: 'industryShares'},
                { title: "行业分类",width:150,sortField:'industryShares2', field: 'industryShares2'},
                //modify by wm 行业分类展示列修改  对应bug号 ZZOTZL-352 end
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table11 = $('#dataTable11').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "所属国家或地区",width:150,sortField:'countryArea', field: 'countryArea'},
                { title: "合约名称",width:100,sortField:'contractName', field: 'contractName'},
                { title: "起息日",width:100,sortField:'vdate', field: 'vdate', type: 'date'},
                { title: "到期日",width:100,sortField:'mdate', field: 'mdate', type: 'date'},
                { title: "票面利率(%)",width:150,sortField:'assetYield', field: 'assetYield',type: 'number',digit:5},
                { title: "付息频率（个月/次）",width:150,sortField:'intpayCycle', field: 'intpayCycle'},
			    { title: "固定收益部分所占比例(%)",width:100,sortField:'incomeRatio', field: 'incomeRatio',type: 'number',digit:5},
			    { title: "衍生金融工具所占比例(%)",width:100,sortField:'derInstrRatio', field: 'derInstrRatio',type: 'number',digit:5},
				{ title: "衍生金融工具具体投资方式",width:150 , field: 'derInstrInvType',sortField:'derInstrInvType'},
                { title: "衍生金融工具挂钩标的资产",width:150,sortField:'derInstrTarget', field: 'derInstrTarget'},
                { title: "利息结算方式",width:150,sortField:'acctMode', field: 'acctMode'},
                { title: "含权情况说明",width:150,sortField:'specialCmt1', field: 'specialCmt1'},
                { title: "结构化票据最高收益率(%)",width:150,sortField:'maxYield', field: 'maxYield',type: 'number',digit:5},
                { title: "结构化票据最低收益率(%)",width:150,sortField:'minYield', field: 'minYield',type: 'number',digit:5},
                { title: "挂钩标的资产基准价格",width:150,sortField:'basicPrice', field: 'basicPrice', type: 'money'},
                { title: "挂钩标的资产登记日价格",width:150,sortField:'regDatePrice', field: 'regDatePrice', type: 'money'},
                { title: "交易费",width:150,sortField:'tradeFee', field: 'tradeFee', type: 'money'},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table12 = $('#dataTable12').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "资管计划名称",width:150,sortField:'assetName', field: 'assetName'},

                { title: "是否由金融资产投资公司发行",width:250,sortField:'financialComissue', field: 'financialComissue'},
                
                { title: "资管计划发起人机构编码",width:100,sortField:'partyCode', field: 'partyCode'},
                { title: "资管计划登记编码",width:100,sortField:'checkCode', field: 'checkCode'},
                { title: "管理人",width:150,sortField:'managerId', field: 'managerId'},
                { title: "托管人",width:150,sortField:'trusteeId', field: 'trusteeId'},
			    { title: "金额",width:100,sortField:'amt', field: 'amt', type: 'money'},
			    { title: "资金实际投向",width:100,sortField:'truActType', field: 'truActType'},
				{ title: "资金运用方式",width:150 , field: 'investType',sortField:'investType'},
				{ title: "资金运用行业",width:150 , field: 'capUseInduLevel2',sortField:'capUseInduLevel2'},
                { title: "资管计划成立日期",width:150,sortField:'planSdate', field: 'planSdate', type: 'date'},
                { title: "资管计划终止日期",width:150,sortField:'planEdate', field: 'planEdate', type: 'date'},
                { title: "资管计划属性",width:150,sortField:'proProperty', field: 'proProperty'},
                { title: "是否有预期收益率",width:150,sortField:'rateFlag', field: 'rateFlag'},
                { title: "预期最高收益率(%)",width:150,sortField:'maxRate', field: 'maxRate',type: 'number',digit:5},
                { title: "预期最低收益率(%)",width:150,sortField:'minRate', field: 'minRate'},
                { title: "购买结构",width:150,sortField:'buyStruct', field: 'buyStruct'},
                { title: "管理方式",width:150,sortField:'manageType', field: 'manageType'},
                /*{ title: "是否掌控计划底层资产详情",width:150,sortField:'isCtrlBottom', field: 'isCtrlBottom'},*/
                { title: "管理费率(%)",width:150,sortField:'mgrFeeRate', field: 'mgrFeeRate',type: 'number',digit:5},
                { title: "托管费率(%)",width:150,sortField:'truFeeRate', field: 'truFeeRate',type: 'number',digit:5},
                { title: "交易相关合计费率(%)",width:150,sortField:'tradeFeeRate', field: 'tradeFeeRate',type: 'number',digit:5},
                { title: "中介服务机构合计费率(%)",width:150,sortField:'agencyFeeRate', field: 'agencyFeeRate',type: 'number',digit:5},
                { title: "其他合计费率(%)",width:150,sortField:'otherFeeRate', field: 'otherFeeRate',type: 'number',digit:5},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table13 = $('#dataTable13').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "所属国家或地区",width:150,sortField:'countryArea', field: 'countryArea'},
			    { title: "名称",width:100,sortField:'assetName', field: 'assetName'},
                { title: "期限(天）",width:150,sortField:'termDate', field: 'termDate',digit:0},
                { title: "资产价值",width:150,sortField:'assetValue', field: 'assetValue', type: 'money'},
                { title: "资产收益率（利率）(%)",width:150,sortField:'assetYield', field: 'assetYield',type: 'number',digit:5},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table18 = $('#dataTable18').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "所属国家或地区",width:150,sortField:'countryArea', field: 'countryArea'},
			    { title: "名称",width:100,sortField:'assetName', field: 'assetName'},
				{ title: "起息日",width:100,sortField:'vdate', field: 'vdate', type: 'date'},
				{ title: "到期日",width:100,sortField:'mdate', field: 'mdate', type: 'date'},
                { title: "资产价值",width:150,sortField:'assetValue', field: 'assetValue', type: 'money'},
                { title: "资产收益率（利率）(%)",width:150,sortField:'assetYield', field: 'assetYield',type: 'number',digit: 5},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table15 = $('#dataTable15').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
			    { title: "基金代码",width:100,sortField:'assetId', field: 'assetId'},
			    { title: "基金名称",width:100,sortField:'assetName', field: 'assetName'},
			    
			    { title: "是否由金融资产投资公司发行",width:200,sortField:'financialComissue', field: 'financialComissue'},
			    //modify by wm 行业分类展示列修改  对应bug号 ZZOTZL-352 start
                //{ title: "行业一级分类",width:150,sortField:'industryShares', field: 'industryShares'},
                { title: "行业分类",width:150,sortField:'industryShares2', field: 'industryShares2'},
                //modify by wm 行业分类展示列修改  对应bug号 ZZOTZL-352 end
			    { title: "登记备案机构",width:200,sortField:'registOrg1', field: 'registOrg1'},
                { title: "是否属于政府投资基金",width:150,sortField:'isGovernFund', field: 'isGovernFund'},
			    { title: "政府投资基金投向",width:100,sortField:'governInvestFund', field: 'governInvestFund'},
                { title: "基金公司名称",width:100,sortField:'fundEnpeName', field: 'fundEnpeName'},
                { title: "基金管理机构名称",width:100,sortField:'mngOrgName', field: 'mngOrgName'},
                { title: "基金托管机构名称",width:150,sortField:'truOrgName', field: 'truOrgName'},
                { title: "投资阶段",width:150,sortField:'investStage', field: 'investStage'},
			    { title: "投资企业类型（按规模划分）",width:200,sortField:'sharesType1', field: 'sharesType1'},
			    { title: "投资企业类型（按技术领域划分）",width:200,sortField:'sharesType3', field: 'sharesType3'},
			    { title: "投资企业类型（按经济类型划分）",width:200,sortField:'sharesType2', field: 'sharesType2'},
                { title: "基金投资资产",width:150,sortField:'fundInvestAsset', field: 'fundInvestAsset'},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table16 = $('#dataTable16').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "委外投资协议名称",width:150,sortField:'assetName', field: 'assetName'},
                { title: "委外投资协议编号",width:150,sortField:'assetId', field: 'assetId'},
                { title: "受托人",width:100,sortField:'trustee', field: 'trustee'},
                { title: "实际管理人",width:100,sortField:'managerId', field: 'managerId'},
                { title: "托管人",width:150,sortField:'trusteeId', field: 'trusteeId'},
			    { title: "委托投资金额",width:100,sortField:'amt', field: 'amt', type: 'money',digit :2},
			    { title: "资金实际投向",width:100,sortField:'truActType', field: 'truActType'},
				{ title: "资金运用方式",width:150 , field: 'investType',sortField:'investType'},
				{ title: "资金运用行业",width:150 , field: 'capUseInduLevel2',sortField:'capUseInduLevel2'},
                { title: "投资运作起始日期",width:150,sortField:'planSdate', field: 'planSdate', type: 'date'},
                { title: "投资运作终止日期",width:150,sortField:'planEdate', field: 'planEdate', type: 'date'},
                { title: "委外投资属性",width:150,sortField:'proProperty', field: 'proProperty1'},
                { title: "是否有预期收益率",width:150,sortField:'rateFlag', field: 'rateFlag'},
                { title: "预期最高收益率(%)",width:150,sortField:'maxRate', field: 'maxRate', type: 'number',digit :5},
                { title: "预期最低收益率(%)",width:150,sortField:'minRate', field: 'minRate', type: 'number',digit :5},
                { title: "管理费率(%)",width:150,sortField:'mgrFeeRate', field: 'mgrFeeRate', type: 'number',digit :5},
                { title: "托管费率(%)",width:150,sortField:'truFeeRate', field: 'truFeeRate', type: 'number',digit :5},
                { title: "交易相关合计费率(%)",width:150,sortField:'tradeFeeRate', field: 'tradeFeeRate', type: 'number',digit :5},
                { title: "中介服务机构合计费率(%)",width:150,sortField:'agencyFeeRate', field: 'agencyFeeRate', type: 'number',digit :5},
                { title: "其他合计费率(%)",width:150,sortField:'otherFeeRate', field: 'otherFeeRate', type: 'number',digit :5},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table17 = $('#dataTable17').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
                { title: "所属国家或地区",width:150,sortField:'countryArea', field: 'countryArea'},
			    { title: "名称",width:100,sortField:'assetName', field: 'assetName'},
                { title: "期限(天）",width:150,sortField:'termDate', field: 'termDate',digit:0},
                { title: "负债规模",width:150,sortField:'debtScale', field: 'debtScale'},
                { title: "利率(%)",width:150,sortField:'assetYield', field: 'assetYield', type: 'number',digit :5},
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	var table14 = $('#dataTable14').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
        showDetail: true,//双击详情对比画面
		checkbox : true,
		contextmenu : true,
		cols : [
				{ field: 'registSeqno',visible:false},
	            { field: 'status',visible:false},
	            { field: 'sendStatus',visible:false},
				{ title: "数据状态",width:100 , field: 'statusName',sortField:'status'},
				{ title: "数据日期",width:100 , field: 'statusDate',sortField:'statusDate', type: 'datetime'},
				{ title: "报送状态",width:100 , field: 'sendStatusName',sortField:'sendStatus'},
				{ title: "报送截止日",width:100 , field: 'chbSubmitDeadline',sortField:'chbSubmitDeadline', type: 'datetime'},
				{ title: "反馈状态",width:100 , field: 'submitFeedbackStatus',sortField:'submitFeedbackStatus'},
				{ title: "报送日期",width:100 , field: 'submitTime', type: 'date',sortField:'submitTime', type: 'datetime'},
				{ title: "行内资产/负债编码",width:150 , field: 'assetCode',sortField:'assetCode'},
				{ title: "发行机构代码",width:150 , field: 'issuePartyId',sortField:'issuePartyId'},
				{ title: "资产/负债类别",width:150 , field: 'assetType2',sortField:'assetType2'},
				{ title: "交易流通场所",width:150 , field: 'tradeMarket',sortField:'tradeMarket'},
				{ title: "币种",width:100 , field: 'ccy',sortField:'ccy'},
			    { title: "名称",width:100,sortField:'assetName', field: 'assetName'},
                { title: "行内资产类别",width:150,sortField:'inAssetType', field: 'inAssetType'},
                { title: "行内资产类别说明",width:150,sortField:'inAssetTypeCmm', field: 'inAssetTypeCmm'},
				{ title: "金额",width:100,sortField:'amt', field: 'amt', type: 'money'},
				{ title: "起息日",width:100,sortField:'vdate', field: 'vdate', type: 'date'},
				{ title: "到期日",width:100,sortField:'mdate', field: 'mdate', type: 'date'},
                { title: "所属国家或地区",width:150,sortField:'countryArea', field: 'countryArea'},
                { title: "是否有预期收益率",width:150,sortField:'rateFlag', field: 'rateFlag'},
                { title: "项目收益率（利率）(%)",width:150,sortField:'assetYield', field: 'assetYield', type: 'number',digit :5},
                { title: "付息频率（个月/次）",width:150,sortField:'intpayCycle', field: 'intpayCycle'},
                { title: "融资人",width:150,sortField:'financie', field: 'financie'},
                { title: "融资人组织机构（社会信用）代码",width:150,sortField:'finOrgCode', field: 'finOrgCode'},
                { title: "外部评级机构名称及对融资人评级结果",width:150,sortField:'fcrGradeOut', field: 'fcrGradeOut'},
			    { title: "融资人类型（按规模划分）",width:200,sortField:'financierType1', field: 'financierType1'},
			    { title: "融资人类型（按技术领域划分）",width:200,sortField:'financierType2', field: 'financierType2'},
			    { title: "融资人类型（按经济类型划分）",width:200,sortField:'financierType3', field: 'financierType3'},
                { title: "融资项目",width:150,sortField:'financeProj', field: 'financeProj'},
//                { title: "融资人所属行业",width:150,sortField:'', field: 'industry1'},
                { title: "融资人所属行业",width:150,sortField:'industryLevel2Type', field: 'industryLevel2Type'},
//                { title: "融资项目所属行业",width:150,sortField:'', field: 'proIndustry'},
                { title: "融资项目所属行业",width:150,sortField:'proIndustryLevel2', field: 'proIndustryLevel2'},
                { title: "项目是否属于重点监控行业和领域",width:150,sortField:'industryFlag', field: 'industryFlag'},
                { title: "重点监控行业和领域类别",width:150,sortField:'monitType', field: 'monitType'},
                { title: "重点监控行业和领域类别说明",width:150,sortField:'monTypeComm', field: 'monTypeComm'},
                { title: "对应资产外部评级",width:150,sortField:'outGrade', field: 'outGrade'},
                
                { title: "担保方式",width:150,sortField:'guaType', field: 'guaType'},
                { title: "担保情况说明",width:150,sortField:'guaTypeRemark', field: 'guaTypeRemark'},
                { title: "抵质押物类型",width:150,sortField:'pledgeType', field: 'pledgeType'},
                { title: "抵质押物价值（元）",width:150,sortField:'pledgeValue', field: 'pledgeValue', type: 'number',digit :2},
                { title: "担保性质",width:150,sortField:'natureGuarantee', field: 'natureGuarantee'},
                { title: "担保人与融资人关系",width:150,sortField:'relationFinan', field: 'relationFinan'},
                { title: "是否为债转股",width:150,sortField:'deptEquity', field: 'deptEquity'},
                
			    { title: "备注",width:150,sortField:'remark', field: 'remark'},
				]
	});
	// 查询按钮点击事件
	$('#SvrAssetInfoRegist001_btn_search').click(function() {
		var assetTypes = $('#assetType').val();
//		if (isEmpty(assetTypes)) {
//			Joyin.error("请选择资产负债类型");
//			return;
//		}
		var assetTypes = $('#assetType').val();
		var table = getTable(assetTypes);
		if (table != null){
			table.load();
		}
	});

	// 导出查询结果按钮点击事件
	$('#AssetInfoRegist001_btn_chbexport').click(function() {
		exportMethod("0");
	});

	//导出方法
	var exportMethod = function(flag) {
		var assetTypes = $('#assetType').val();
		var table = getTable(assetTypes);
		var tableCounts = table.getAllRows();
//		if (!tableCounts || tableCounts.length == 0){
//			Joyin.error("没有数据可以导出，请先查询");
//			return;
//		}
		var expArr = table.getAllCheckedRows();
		var param = {};
		if ((!expArr || expArr.length == 0)&&assetTypes==null) {
			param = getSearchParams();
            param.flag = flag;
            if(flag=="0"){
            	param.status="01";
            	param.submitStatus="01";
            }
            var isHaveData = true;
            Joyin.ajaxSync('searchPage',param, function(result){
                if (!result || result.root.length == 0){
             	   isHaveData = false;
             	   return;
                }
            })
    		Joyin.exportExcel("export", param);
            if (isHaveData){
	            Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function() {
	            	layui.layer.open({type:3});
	                Joyin.ajaxJson('manual', param, function() {
	                    table.load();
	                    layui.layer.closeAll();
	                });
	            });
            }
		} else {
			// 用来标注选择数据中存在修改或者已报送数据，默认为false
			var statuFlag = false;
			if (flag == "0") {
				for (var i = 0; i < expArr.length; i++) {
//					if (expArr[i].status != "01" || expArr[i].sendStatus != "01"){
//						Joyin.error("只能导出新建未报送的数据，请重新选择");
//						return;
//					}
					// 含有删除状态数据
					if (expArr[i].status=="03") {
						Joyin.error("所选数据中包含数据状态为删除的数据，请重新选择!");
	        			return;
	        			// 含有修改状态数据或者已报送数据，将标志改为true
					} else if (expArr[i].status=="02"||expArr[i].sendStatus!="01") {
						statuFlag = true;
					}
				}
			}
			var keyList = [];
			$.each(expArr, function(i, row) {
				keyList.push(row.registSeqno);
			});
			param.expKeys = keyList.join(',');
            param.keyList = JSON.stringify(expArr);
            param.flag = flag;
            param.assetTypes=assetTypes;
            if (statuFlag) {
				Joyin.confirm("导出数据包含修改或已报送数据，请确认是否导出！", function() {
					confirmExport(param, expArr);
				});
			} else {
				confirmExport(param, expArr);
			}
//			Joyin.exportExcel("export", param);
//	        Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function() {
//	        	layui.layer.open({type:3});
//	            Joyin.ajaxJson('manual', {keyList : JSON.stringify(expArr)}, function() {
//	                table.load();
//	                layui.layer.closeAll();
//	            });
//	        });
		}

	};
	
	/**
	 * 确认导出之后的操作
	 */
	var confirmExport = function(param, expArr) {
		var assetTypes = $('#assetType').val();
		var table = getTable(assetTypes);
		Joyin.exportExcel("export", param);
        Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function() {
        	layui.layer.open({type:3});
            Joyin.ajaxJson('manual', {keyList : JSON.stringify(expArr)}, function() {
                table.load();
                layui.layer.closeAll();
            });
        });
	};

    //导出删除模板
    $('#AssetInfoRegist001_btn_exportDel').click(function(){
		var assetTypes = $('#assetType').val();
		var table = getTable(assetTypes);
    	// 如果没有查询则返回提示
        if(!table.isSearched()) {
            return;
        }
        // 根据查询条件查询数据
        var param = getSearchParams();
        // 只导出删除未报送的数据
    	param.status='03';
        param.submitStatus='01';
        var isHaveData = true;
        Joyin.ajaxSync('searchPage',param, function(result){
            if (!result || result.root.length == 0){
//            	Joyin.error("没有数据导出，请重新选择");
         	   	isHaveData = false;
//            	return;
            }
        })
//        if (!isHaveData){
//      	  return; 
//        }
        // 选中的导出数据
        var expArr=table.getAllCheckedRows();
        // 报送状态标志
  	   	var sendFlag = false;
        if(!expArr || expArr.length ==0){
        	// 不勾选不能导出
        	if (!isHaveData){
        		Joyin.error("没有可导出的数据，请重新选择导出条件！");
            	 return; 
              }
        }
        else{
        	// 选中数据判断是否是删除和未报送状态
        	for(var i=0;i<expArr.length;i++){
//                 if(expArr[i].status !="03"||expArr[i].sendStatus !="01"){
//                     Joyin.error("只能导出删除状态且未报送的数据，请重新选择");
//                     return;
//                 }
        		if(expArr[i].status !="03"){
                    Joyin.error("只能导出删除的数据，请重新选择");
                    return;
                }
           		if(expArr[i].sendStatus !="01"){
           			sendFlag = true;
                }
            }
        	// 选中数据
            param.expKeys = JSON.stringify(expArr);
        }
        if (sendFlag) {
        	Joyin.confirm("导出数据中含有已报送的数据，请确认是否导出！", function() {
        		// 打开删除信息页面
                Joyin.dialog(JoyinUtil.getLabel("删除模板导出确认"),
                        basePath + "chb/common/ExportDeleteExcelConfirm001/init",
                        "50%", "50%", {pageModel:PAGE_MODEL_NEW,orgPageId:param.pageId,expKeys:param.expKeys},function(){
                            //防止出现查询转圈圈
                            setTimeout(function(){table.reload();},100);
            	}); 
        	});
        } else {
        	 // 打开删除信息页面
            Joyin.dialog(JoyinUtil.getLabel("删除模板导出确认"),
                    basePath + "chb/common/ExportDeleteExcelConfirm001/init",
                    "50%", "50%", {pageModel:PAGE_MODEL_NEW,orgPageId:param.pageId,expKeys:param.expKeys},function(){
                        //防止出现查询转圈圈
                        setTimeout(function(){table.reload();},100);
        	}); 
        }
       
    });

	/**start   中债一二期直连新加  add  by wangzehui  20201012  */
	/**
	 * 数据检查
	 */
	$("#AssetInfoRegist001_btn_checkData").click(function(){
		var paramObj = getSearchParams();
		paramObj.opertionType = "checkData";
		paramObj.sendStatus = paramObj.submitStatus;
		chbCommonCheck(getTable($('#assetType').val()), paramObj, 'submitBeforeCheck', dataTitle, 'submitChb');
	});
	/**
	 * 报送
	 */
	$('#AssetInfoRegist001_btn_submit').click(function () {
		var paramObj = getSearchParams();
		paramObj.opertionType = "submitChb";
		paramObj.sendStatus = paramObj.submitStatus;
		chbCommonCheck(getTable($('#assetType').val()), paramObj, 'submitBeforeCheck', dataTitle, 'submitChb');
	});
	/**
	 * 重新报送
	 */
	$('#AssetInfoRegist001_btn_reSubmit').click(function () {
		var paramObj = getSearchParams();
		paramObj.opertionType = "reSubmitChb";
		paramObj.sendStatus = paramObj.submitStatus;
		chbCommonCheck(getTable($('#assetType').val()), paramObj, 'submitBeforeCheck', dataTitle, 'submitChb');
	});
	/**end   中债一二期直连新加  add  by wangzehui  20201012  */


	//判断字符是否为空的方法
	function isEmpty(obj) {
		if (typeof obj == "undefined" || obj == null || obj == "") {
			return true;
		} else {
			return false;
		}
	}

	// 判断对应table
	function getTable(assetTypes){
		if (!isEmpty(assetTypes)) {
			/*01现金及银行存款类资产*/
			if ("00" == assetTypes) {
				return table00;
			} if ("01" == assetTypes) {
				return table01;
			} if ("02" == assetTypes) {
				return table02;
			} if ("03" == assetTypes) {
				return table03;
			} if ("04" == assetTypes) {
				return table04;
			} if ("05" == assetTypes) {
				return table05;
			} if ("06" == assetTypes) {
				return table06;
			} if ("07" == assetTypes) {
				return table07;
			} if ("08" == assetTypes) {
				return table08;
			} if ("09" == assetTypes) {
				return table09;
			} if ("10" == assetTypes) {
				return table10;
			} if ("11" == assetTypes) {
				return table11;
			} if ("12" == assetTypes) {
				return table12;
			} if ("13" == assetTypes) {
				return table13;
			} if ("14" == assetTypes) {
				return table14;
			} if ("15" == assetTypes
					|| "19" == assetTypes
					||"L01" == assetTypes) {
				return table15;
			} if ("16" == assetTypes) {
				return table16;
			} if ("17" == assetTypes) {
				return table17;
			} if ("18" == assetTypes) {
				return table18;
			} else {
				Joyin.error("不存在该tab页");
				return null;
			}
		} else {
			// 返回主页面
			return table00;
		}
	}
	var init = function() {
		// 报送日期初始化
//		$('#submitDate').val(searchDataStart + ' - ' + searchDataEnd);
//		// 数据日期初始化
//		$('#statusDate').val(searchDataStart + ' - ' + searchDataEnd);
		 if(isMain=='01'){
             setTimeout(function() {
            	 if($('#submitStatus').val()=='02'||$('#submitStatus').val()=='03'){
            		   $('#submitDate').val(searchDataStart+' - '+searchDataEnd);
            	   }else{
            		   $('#submitDeadLine').val(searchDataStart + ' - ' + searchDataEnd);
            	   }
                 var assetTypes = $('#assetType').val();
         		var table = getTable(assetTypes);
                 table.load();
             }, 500);
         }
		//报送前检查调用数据检查
         if(validKey){
             setTimeout(function() {
                 table.load();
             },500)
         }
		layui.use('element', function() {
			var $ = layui.jquery, element = layui.element;
			//触发事件
			var active = {
	            tabDelete:function(id){
	                element.tabDelete('contentnav',id);
	            },
			};

	        active.tabDelete($(this).attr("id"));
		}); 
	};
	init();
	// 防止死循环,资产负债类别下拉框联动tab页
	var isChangeSelect = true;
	var isChangeTab = true;
	layui.use('element', function() {
		var $ = layui.jquery, element = layui.element;
		element.on('tab(tabList)', function(data){
			var assett = $(".layui-tab-title .layui-this").attr("lay-id");
			// 主页面
			if (assett == "00"){
				assett = "";
				window.event.isclean = 'true';
			}else{
				window.event.isclean = undefined;
			}
			if (isChangeSelect){
				isChangeSelect = true;
				isChangeTab = false;
				$("#assetType").val(assett).trigger('change');
			} else {
				isChangeSelect = true;
				isChangeTab = true;
			}
			var assetTypes = $('#assetType').val();
			var table = getTable(assetTypes);
			table.reload();
		});
		//触发事件
		var active = {
			tabAdd:function(url,id,name){
			    element.tabAdd('contentnav',{
			    title:name,
			    content:'<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;"></iframe>',
			        id:id
			    });
			    rightMenu();
			    iframeWH();
			},
            tabDelete:function(id){
                element.tabDelete('contentnav',id);
            },
			tabChange : function() {
				//切换到指定Tab项
				var assetTypes = $('#assetType').val();
				// 公募基金
//				if (assetTypes == '19'){
//				assetTypes = 15;
//			}
				// 主页面
				if (assetTypes == ''){
					assetTypes = '00';
					$('#assetType').val("");
				}
				element.tabChange('tabList', assetTypes); //切换到指定页面
			},
		};
		$('#assetType').on('change', function() {
			var othis = $(this), type = 'tabChange';
			if (isChangeTab){
				isChangeSelect = false;
				isChangeTab = true;
				active[type] ? active[type].call(this, othis) : '';
			}  else {
				isChangeSelect = true;
				isChangeTab = true;
			}
			var assetTypes = $('#assetType').val();
			var table = getTable(assetTypes);
			table.reload();
		});
	});
	
    //手工处理 
    $('#AssetInfoRegist001_btn_manual').click(function() {
		var assetTypes = $('#assetType').val();
		var table = getTable(assetTypes);
		var tableCounts = table.getAllRows();
//		if (!tableCounts || tableCounts.length == 0){
//			Joyin.error("没有数据可以处理，请先查询");
//			return;
//		}
        var manArr=table.getAllCheckedRows();
        if(!manArr || manArr.length ==0){
        	//当不选择时默认选择符合查询条件的全部数据
        	var submitStatus = $("#submitStatus").val();
        	var submitFeedbackStatus = $("#submitFeedbackStatus").val();
        	if(!((submitStatus && submitStatus.length == 1 &&submitStatus[0] == '01')
        			|| (submitStatus && submitStatus.indexOf('02')>-1 && submitFeedbackStatus && (submitFeedbackStatus.indexOf('02')>-1 ||  submitFeedbackStatus.indexOf('04')> -1)))){
        		Joyin.error("只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理");
        		return;
        	}
        	var paramObj = getSearchParams();
        	paramObj.flag = "01";//判断是否选中记录
        	Joyin.confirm("请确认是否要手工处理全部未报送记录？", function() {
        		//页面添加滚动条
        		layui.layer.open({type:3});
	            Joyin.ajaxJson('manual', paramObj, function() {
	                table.load();
	                layui.layer.closeAll();
	            });
	        });
        }else{
	        for(var i=0;i<manArr.length;i++){
	            if (!(manArr[i].sendStatus == '01' || (manArr[i].sendStatus == '02' && (manArr[i].submitFeedbackStatus == '02' || manArr[i].submitFeedbackStatus == '04'
																					|| manArr[i].submitFeedbackStatus == '发送失败' || manArr[i].submitFeedbackStatus == '中债退回')))) {
	                Joyin.error("只能选择报送状态为未报送的数据，或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理，请重新选择！");
	                return;
	            }
	        };
	        Joyin.confirm("请确认是否要手工处理选中记录？", function() {
	        	layui.layer.open({type:3});
	            Joyin.ajaxJson('manual', {keyList : JSON.stringify(manArr)}, function() {
	                table.load();
	                layui.layer.closeAll();
	            });
	        });
        }
    });
    
    /**
     * 取消手工处理 
     */
   $('#AssetInfoRegist001_btn_cancelManual').click(function() {
		var assetTypes = $('#assetType').val();
	    var table = getTable(assetTypes);
		var tableCounts = table.getAllRows();
//		if (!tableCounts || tableCounts.length == 0){
//			Joyin.error("没有数据可以处理，请先查询");
//			return;
//		}
       var manArr=table.getAllCheckedRows();
       if(!manArr || manArr.length ==0){
    	   var sendStatus = $("#submitStatus").val();
       	   if(!(sendStatus && sendStatus.length==1 && sendStatus[0] == '03')){
       		 Joyin.error("只能选择手工处理的数据，请重新选择！");
                return;
       	   }
       	   var paramObj = getSearchParams();
       	   paramObj.flag = '01';
       	   Joyin.confirm("请确认是否要取消手工处理符合查询条件所有记录？", function() {
       		layui.layer.open({type:3});
            Joyin.ajaxJson('cancelMannul', paramObj, function() {
                table.load();
                layui.layer.closeAll();
            	});
       	   });
       }else{
    	   for(var i=0;i<manArr.length;i++){
               if(manArr[i].sendStatus !="03"){
                   Joyin.error("只能选择手工处理的数据，请重新选择！");
                   return;               
               }
           };
            Joyin.confirm("请确认是否要取消手工处理选中记录？", function() {
            	layui.layer.open({type:3});
                Joyin.ajaxJson('cancelMannul', {keyList: JSON.stringify(manArr)}, function() {
                    table.load();
                    layui.layer.closeAll();
                });
           });
       }
        
   });
   
});