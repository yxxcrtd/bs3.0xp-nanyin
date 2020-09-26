//@ sourceUrl=TrdDepositInvestTrade002.js
(function(){
    var fileArray =[];
    var entrustId = '';
    var entity = '';
    var isOpics = '';
    var isHoliday = '';
    var getriskdata = function(){
        var obj = {timePoint:MY_SUP_TIME_TYPE_02,valDate:$('#tradeDate').val(),portfolioCode:$('#portfolioId').val(),tradeAmount:$('#tradeAmtAndFee').val()==0?null:$('#tradeAmtAndFee').val(),assetType:FINPROD_TYPE_F07,isEnabled:'01',assetCode:$('#finprodMarketId').select2("data")[0].object,tradeDirection:$('#ps').val()}
            obj.assetSecType =MY_SUP_TIME_TYPE_02;
        var object = {assetCode:['finprodMarketId','TrdDepositInvestTrade001.finprodId'],valDate:['tradeDate','TrdDepositInvestTrade002.tradeDate'],portfolioCode:['portfolioId','TrdDepositInvestTrade001.portfolioId'],tradeAmount:['tradeAmtAndFee','TrdFundInvestTrade001.tradeAmt'],assetType:['finprodType','TrdDepositInvestTrade001.finprodType'],tradeDirection:['ps','TrdDepositInvestTrade001.ps'],data:obj}
        return object;
    }

    window.componentLoadCallback = function(isDone, pagePath, params){
        if(isDone){
            var riskparam = getriskdata();
            if (pageModel==PAGE_MODEL_NEW){
                riskparam.data =null;
            }
            window.BaseRiskMathComm.getparam(riskparam);
            if(window.BussTrdTransferOrder && window.BussTrdTransferOrder['bussTrdTransferOrder']) {
                window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                    getEntity();
                    return {
                        'isTotalTrade':YES_OR_NO_N
                        ,'bankLendingType':null
                        ,'portfolioId':$("#portfolioId").val()
                        ,'deal':entity
                        //存款类交易新建交易对手非空校验 yjm start 20190724
                        ,'repPageId': pageId
                        //存款类交易新建交易对手非空校验 yjm end 20190724
                    };
                });
            }
            if(entrustId != ''){
                if(etrEntrust){
                    window.TrdInvestPersonInfo002['trdInvestPersonInfo'].setEntrust(function () {
                        return etrEntrust;
                    });
                }
            }
          //交易对手改变时  影响通道费金额，内部交易对手随交易支付，外部交易对手根据收取方式
            $('#trdInvestPersonInfo002Dto_counterId').on("change",function(){
            	checkChannelFee();
//            	changeTradeAmtAndFee();
            });
        }
    }
    
    function getEntity(){
        var object = $.serializeToObject('#basicInfoForm', true);
        if(object.loadRate != null){
            object.loadRate = object.loadRate.valueOf()/100;
        }
        var data = $("#chlId").find("option:selected").data("collecttype");
        if (data ) {
            object.collectType = data;
        }
        if($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != ''){
            object.finprodId= $("#finprodMarketId").select2("data")[0].object;
        }else if(finprodId != null){
            object.finprodId = finprodId;
        }
        Joyin.ajaxSync(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getEntity', object,function(result){
            result.createTime = null;
            result.updateTime = null;
            result.tradeId = null;
            result.cpriceAmt = null;
            result.shareAmt = null;
            result.prinAmt = null;
            result.intAmt = null;
            result.fpriceAmt = null;
            // add by renping 20191109 start
            result.remark = null;
            // add by renping 20191109 end
            entity = result;
        });
    }
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	// 划款指令隐藏时，自动生成划款指令信息
    	generateOrders(function () {
	        var s;
	        if(BaseRiskMathComm) {
	            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
	            if(!s && s != ''){
	                return;
	            }
	            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
	                return;
	            }
	        }
	        // 检查生成划款指令参数是否发生变化
	         if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
	             return;
	         }
	        /**
	         * 获取列表参数
	         */
	        var object = $.serializeToObject('#basicInfoForm',true);
	        if ($('#select2-finprodMarketId-container').attr('title')){
	            object.finprodName = $('#select2-finprodMarketId-container').attr('title');
	        }
	        object.operateType = operateType;
	        if($("#finprodMarketId").val() != null && $("#finprodMarketId").val() != ''){
	            object.finprodId = $("#finprodMarketId").select2("data")[0].object;
	        }else if(finprodId != null){
	            object.finprodId = finprodId;
	        }
	        object.pageModel = pageModel;
	        if(FileCommon) {
	            object.fileArray = FileCommon.getUploadFileInfo()
	        }
	//        if(object.bussTrdTransferOrder && object.bussTrdTransferOrder[0] ){
	//            if(object.bussTrdTransferOrder[0].settleAmt != ''+(parseFloat($().val())+parseFloat($().val()))){
	//                Joyin.error("本金金额发生改变，请重新生成指令");
	//                return ;
	//            }else if(object.bussTrdTransferOrder[0].portfolioId != object.portfolioId){
	//                Joyin.error("组合发生改变，请重新生成指令");
	//                return ;
	//            }
	//        }
	//        if(object.stlPayOrdersList != null){
	//            object.stlPayOrdersList[0].myAcctNo = object.stlPayOrdersList[0].payAcctNo;
	//            object.stlPayOrdersList[0].myAcctName = object.stlPayOrdersList[0].payAcctName;
	//            if(object.stlPayOrdersList[0].orderId == null){
	//                object.stlPayOrdersList = null;
	//            }
	//        }
	        var data = $("#chlId").find("option:selected").data("collecttype");
	        if (data ) {
	            object.collectType = data;
	        }
	        data = $("#chlId").find("option:selected").data("paytype");
	        if (data ) {
	            object.payType = data;
	        }
	        if(isOpics == 'Y'){
	//            object.bussTrdTransferOrder = null;
	        }else{
	            object.interfSendOpicsList = null;
	        }
	
	        //如果资产类型是活期存放，到期时为交易日
	        if ('D03' == $("#finprodType").val()){
	            object.mdate = object.tradeDate;
	        }
	        if ($('#trdInvestPersonInfo002Dto_counterId').select2('data') && $('#trdInvestPersonInfo002Dto_counterId').select2('data')[0]) {
	            object.counterType = $('#trdInvestPersonInfo002Dto_counterId').select2('data')[0].object;
	            object.counterId=$('#trdInvestPersonInfo002Dto_counterId').select2('data')[0].id;
	        }
	        object.counterId
	        object.finprodType2 = FINPROD_TYPE_F07;
	        object.tradeAmt=object.tradeAmtAndFee;
	        Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/check', object, function(result1) {
	            Joyin.ajaxJson(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/check', object, function(result) {
	                var message = '';
	                if(object.ps === buy && object.prinAmt && object.accAvailableCashT && Number(object.prinAmt) > (object.chlId && object.payType == '02' ? Number(object.channelAvailableCash) : Number(object.accAvailableCashT))){
	                    if (object.chlId && object.payType == '02') {
	                        message = JoyinUtil.getMessage("TRD_C_0004",JoyinUtil.getMessage("invest.channelId"));
	                    }
	                    else {
	                        message = JoyinUtil.getMessage("TRD_C_0002");
	                    }
	                }else{
	                    message = JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save"));
	                    if (operateType === 'submit') {
	                        message = JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit"));
	                    }
	                    if(isHoliday != '' && isHoliday == 'Y'){
	                        message = JoyinUtil.getMessage("BUSS_COM_E_0039",JoyinUtil.getLabel("TrdDepositInvestTrade002.dealdate"));
	                    }
	                }
	                message = s+message;
	                if (message.length > 9 && result.data) {
	                    message += result.data;
	                }else if (result.data && result.data == JoyinUtil.getMessage("TRD_C_0005")) {
	                    message = result.data;
	                } 
	              //授信占用额度
//	                Joyin.ajaxJson(basePath + 'invest/trade/TrdCommonController/delLinesDeal',result1.data,function(result2) {});
	                object.riskCreditListCommonDto = result1.data;
	                if(operateType === 'save') { 
	                	Joyin.confirm(message, function() {
	                        WorkFlow.doSaveList(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/save', object, "TrdDepositInvestTrade002_btn_save");
	                    });
	                }else{
	                    Joyin.confirm(message, function() {
	                        WorkFlow.doSubmitList(basePath + 'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/save', object, "TrdDepositInvestTrade002_btn_save");
	                    });
	                }
	            });
	        });
    	});   
	  }
    /**
     * 定义数据表格
     */
	/**
    var table=$("#opicsTable").joyintable({
        data:interfSendOpicsList == null ? [] : interfSendOpicsList,
        checkbox:false,
        searchOnInit:true,
        cols:[     
            {field:'sendflag', title: 'TrdDepositInvestTrade002.sendflag',width:150, edit:true, editOption:{type:'select', multiple: true, result: YES_OR_NO}}
            ,{field:'dealtype', title: 'TrdDepositInvestTrade002.dealtype', width:100, edit:true, editOption:{type:'select', multiple: true, url: JoyinUtil.getRealPath('/invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getSelectOptions?type=DEALTYPE')}}//交易类型
            ,{field:'trad', title: 'TrdDepositInvestTrade002.trad',width:100}//交易员
            ,{field:'port', title: 'TrdDepositInvestTrade002.port',width:100}//投资组合
            ,{field:'cost', title: 'TrdDepositInvestTrade002.cost', width:200, edit:true, editOption:{type:'select', multiple: true, url: JoyinUtil.getRealPath('/invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getSelectOptions?type=COST')}}//成本中心
            ,{field:'branch', title: 'TrdDepositInvestTrade002.branch', width:100, edit:true, editOption:{type:'select', multiple: true, url: JoyinUtil.getRealPath('/invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getSelectOptions?type=BRANCH')}}//分支机构
            ,{field:'brok',  title: 'TrdDepositInvestTrade002.brok', width:100, edit:true, editOption:{type:'select', multiple: true, url: JoyinUtil.getRealPath('/invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getSelectOptions?type=BROK')}}//是否修复
            ,{field:'verind', title: 'TrdDepositInvestTrade002.verind', width:140,  edit:true, editOption:{type:'select', multiple: true, url: JoyinUtil.getRealPath('/invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getSelectOptions?type=VERIND')}}//是否自动复核 
            ,{field:'cno', title: 'TrdDepositInvestTrade002.cno',width:120}//交易对手
            ,{field:'intrate', title: 'TrdDepositInvestTrade002.intrate',width:120}//平盘收益率
            ,{field:'vdate', title: 'TrdDepositInvestTrade002.vdate',type:'date',width:120}//起息日 
            ,{field:'mdate', title: 'TrdDepositInvestTrade002.mdate',type:'date',width:120}//到期日 
            ,{field:'ccy', title: 'TrdDepositInvestTrade002.ccy', width:100, edit:true, editOption:{type:'select', multiple: true, result: CCY}}//币种
            ,{field:'amt', title: 'TrdDepositInvestTrade002.amt',width:100}//金额
            ,{field:'basis', title: 'TrdDepositInvestTrade002.basis', edit:true, width:100, editOption:{type:'select', multiple: true, url: JoyinUtil.getRealPath('/invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getSelectOptions?type=BASIS')}}//计息规则
            ,{field:'spread', title: 'TrdDepositInvestTrade002.spread',width:100}//点差
            ,{field:'dealdate', title: 'TrdDepositInvestTrade002.dealdate',type:'date',width:120}//交易日
            ,{field:'dealtext', title: 'TrdDepositInvestTrade002.dealtext',edit:true, width:100}//交易备注 
            ,{field:'remark', title: 'TrdDepositInvestTrade002.remark',width:100}//备注 
        ]
    });
	*/
    /**
     * 获取查询信息
     */
    var getParameters = function (){
        if($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != ''){
            finprodId = $("#finprodMarketId").select2("data")[0].object;
        }
        return{
            finprodId:finprodId,
            vdate:$('#vdate').val(),
            mdate:$('#mdate').val(),
            portfolioId:$('#portfolioId').val(),
            ps:$('#ps').val(),
            chlId:$('#chlId').val(),
            tradeDate:$('#tradeDate').val(),
            ccy:$('#ccy').val()
        };
    };
    /**
     * 资产代码变动联动事件
     */
    var finChange = function(){
        var finprodIdJson = null;
        var finprodId=null;
        if($("#finprodMarketId").val() != null && $("#finprodMarketId").val() != ''){
            finprodIdJson = {finprodId:$("#finprodMarketId").select2("data")[0].object};
            finprodId=$("#finprodMarketId").select2("data")[0].object;
        }
        if(finprodIdJson != null && finprodIdJson.finprodId != null && finprodIdJson.finprodId != ''){
            Joyin.ajax(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getFinInfo",finprodIdJson,function(result){
                if(result != null && result != ''){
                    var vdate = Date.ofDate(result.vdate);
                    var mdate = Date.ofDate(result.mdate);
                    $("#finprodName").val($("#finprodMarketId").select2("data")[0].object);
                    $('#vdate').val(vdate.format('yyyy-MM-dd'));
                    $('#mdate').val(mdate.format('yyyy-MM-dd'));
                    $('#finprodType').val(result.finprodType).trigger('change');
                    // 新增五级分类 王贵奇 2019/8/1 开始
                    $('#type9').val(result.type9).trigger('change');
                    $('#type10').val(result.type10).trigger('change');
                    $('#type11').val(result.type11).trigger('change');
                    // 新增五级分类 王贵奇 2019/8/1 结束
                    $('#termDays').val(result.termDays);
                    $('#loadRate').val(result.loadRate);
                    $('#ccy').val(result.ccy).trigger('change'); 
                    if (pageModel==PAGE_MODEL_NEW) {
                    	$("#trdInvestPersonInfo002Dto_counterId").textSelect('setValue',result.issuer);
					}
                    // 【bug对应】存款类资产交易新建时，先选择一个有资产提供行的资产，再改为没有资产提供行的资产，资产提供行并没有变成空
//                    $('#assetProvider').textSelect('setValue',result.assetProvider);
                    if (result.assetProvider) {
                        $('#assetProvider').textSelect('setValue', result.assetProvider);
                    } else {
                        $('#assetProvider').textSelect('setValue', '');
                        $('#assetProvider').val('').trigger('change');
                    }
                    
//                    $('#assetProvider').val(result.assetProvider).trigger('change');
                    $('#basis').val(result.basis).trigger('change');
                    available();

                    //活期存放时联动交易对手默认为存款机构不可修改 add by gejiaao 20191211 start
                    if('D03' == result.finprodType){
                        $("#trdInvestPersonInfo002Dto_counterId").textSelect('setValue',result.issuer);
                        $("#trdInvestPersonInfo002Dto_counterId").attr("disabled",true);

                        if($('#ps').val() && $('#ps').val() != buy){
                            $("#isClean_div").show();
                            $("#intAmt").attr('disabled',false).trigger("change");
                        }

                        $("#mdate_div").hide();
                        $("#termDays_div").hide();
                    }else {
                        $("#trdInvestPersonInfo002Dto_counterId").attr("disabled",false);
                        $("#isClean_div").hide();
                        $("#intAmt").attr('disabled',true).trigger("change");
                        $("#mdate_div").show();
                        $("#termDays_div").show();
                    }
                    //活期存放时联动交易对手默认为存款机构不可修改 add by gejiaao 20191211 end

                }else{
                    $('#finprodName').val('');
                    $('#vdate').val('');
                    $('#mdate').val('');
                    $('#loadRate').val('');
                    $('#finprodType').val('').trigger('change');
                    $('#termDays').val('');
//                    $('#ccy').val('').trigger('change');
                    $('#assetProvider').textSelect('setValue','');
                    $('#assetProvider').val('').trigger('change');
                    $('#basis').val('').trigger('change');
                }
                intAmtCalcu();
            });
        }else{
            $('#finprodName').val('');
            $('#vdate').val('');
            $('#mdate').val('');
            $('#loadRate').val('');
            $('#finprodType').val('').trigger('change');
            $('#termDays').val('');
//            $('#ccy').val('').trigger('change');
            $('#assetProvider').val('').trigger('change');
            $('#assetProvider').val('').trigger('change');
            $('#basis').val('').trigger('change');

        }
        ChannelAssetCommon.setChannelSelectObject($("#chlId"), finprodId, $("#entrustId").val(), function () {

        });
        changeTradeAmtAndFee();
    }
    /*
     * 资产代码联动带出
     */
    $('#finprodMarketId').change(function(){
//        var finprodIdJson = {finprodId:$("#finprodMarketId").select2("data")[0].object};
//        if(finprodIdJson.finprodId != null && finprodIdJson.finprodId != ''){
//            Joyin.ajax(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getFinInfo",finprodIdJson,function(result){
//                if(result != null && result != ''){
//                    var vdate = new Date(result.vdate);
//                    var mdate = new Date(result.mdate);
//                    var tradeDate = new Date(result.tradeDate);
//                    $('#finprodName').val('').val(result.finprodName);
//                    $('#vdate').val(vdate.format('yyyy-MM-dd'));
//                    $('#mdate').val(mdate.format('yyyy-MM-dd'));
//                    $('#tradeDate').val(tradeDate.format('yyyy-MM-dd'));
//                    $('#finprodType').val(result.finprodType).trigger('change');
//                    $('#termDays').val(result.termDays);
//                    $('#loadRate').val(result.loadRate);
//                    $('#ccy').val(result.ccy).trigger('change');
//                    $('#assetProvider').textSelect('setValue',result.assetProvider);
////                    $('#assetProvider').val(result.assetProvider).trigger('change');
//                    $('#basis').val(result.basis).trigger('change');
//                    available();
//                }else{
//                    $('#finprodName').val('');
//                    $('#vdate').val('');
//                    $('#mdate').val('');
//                    $('#tradeDate').val('');
//                    $('#loadRate').val('');
//                    $('#finprodType').val('').trigger('change');
//                    $('#termDays').val('');
////                    $('#ccy').val('').trigger('change');
//                    $('#assetProvider').textSelect('setValue','');
//                    $('#assetProvider').val('').trigger('change');
//                    $('#basis').val('').trigger('change');
//                }
//                rateCalculate();
//            });
//        }
        finChange();
    });
    /**
     * 利息总额计算
     */
    $("#calculate").click(function(){
        object = {
                vdate:$('#vdate').val(),
                mdate:$('#mdate').val(),
                loadRate:$('#loadRate').val().valueOf() / 100,
                prinAmt:$('#prinAmt').val(),
                basis:$('#basis').val()
        }
        if(object.vdate != null && object.mdate != null && object.loadRate !=null && object.prinAmt != null &&
                object.vdate != '' && object.mdate != '' && object.loadRate != '' && object.prinAmt != ''){
            Joyin.ajaxJson(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getIntAmt",object,function(result){
                $('#intAmt').val(result);
            });
        }
    });
    
    /**
     * 界面初始化
     * @returns
     */
    function init(){
        if(pageModel==PAGE_MODEL_NEW){
            Joyin.ajax(basePath+'invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getInitDate',{},function(result){
                $('#tradeDate').val(result);
            });
        }        
        if($('#entrustId').val() != null && $('#entrustId').val() != ''){
            $('#portfolioId').attr('disabled',true);
            $('#finprodMarketId').attr('disabled',true);
            $('#prinAmt').attr('disabled',true);
            $('#ps').attr('disabled',true).trigger("change");
        }
        $(".hidden").attr('disabled',true);
            if($('#accAvailableCashT').val() != null && $('#accAvailableCashT').val() != ''){
                available();
            }
            if($('#portfolioId').val() != null){
//                Joyin.ajax(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getIsGuarantee",{portfolioId:$('#portfolioId').val()},function(result){
//                    if(result != null && result !== ''){
//                        if(result == YES){
//                            $("#OpicsTable").css('display','');
//                            $("#BussTrdTransferOrder").css('display','none');
//                        }else{
//                            $("#OpicsTable").css('display','none');
//                            $("#BussTrdTransferOrder").css('display','');
//                        }
//                    }
//                });
                portChange();
            }
//            if(finprodId != null && finprodId != ''){
//                Joyin.ajax(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getFinInfo",{finprodId:finprodId},function(result){
//                    if(result != null && result != ''){
//                        var vdate = new Date(result.vdate);
//                        var mdate = new Date(result.mdate);
//                        var tradeDate = new Date(result.tradeDate);
//                        $('#finprodName').val('').val(result.finprodName);
//                        $('#vdate').val(vdate.format('yyyy-MM-dd'));
//                        $('#mdate').val(mdate.format('yyyy-MM-dd'));
//                        $('#tradeDate').val(tradeDate.format('yyyy-MM-dd'));
//                        $('#finprodType').val(result.finprodType).trigger('change');
//                        $('#termDays').val(result.termDays);
//                        $('#loadRate').val(result.loadRate);
//                        $('#ccy').val(result.ccy).trigger('change');
//                        if($('#assetProvider').initValue != null){
//                            $('#assetProvider').textSelect('setValue',$('#assetProvider').initValue);
//                        }else{
//                            $('#assetProvider').textSelect('setValue',result.assetProvider);
//                        }
//                        $('#assetProvider').val(result.assetProvider).trigger('change');
//                        $('#assetProvider').val(result.assetProvider);
//                        $('#basis').val(result.basis).trigger('change');
//                        available();
//                    }else{
//                        $('#finprodName').val('').val('');
//                        $('#vdate').val('');
//                        $('#mdate').val('');
//                        $('#tradeDate').val('');
//                        $('#loadRate').val('');
//                        $('#finprodType').val('').trigger('change');
//                        $('#termDays').val('');
//                        $('#ccy').val('').trigger('change');
////                        $('#assetProvider').val('').trigger('change');
//                        $('#assetProvider').textSelect('setValue','');
//                        $('#basis').val('').trigger('change');
//                    }
//                    rateCalculate();
//                });
//            }
            finChange();
//            var ps = $('#ps').val();
//            if(ps !== buy){
//                $('#Buy').css('display','none');
//                $('#Sale').css('display','');
//            }else{
//                $('#Buy').css('display','');
//                $('#Sale').css('display','none');
//            }
            psChange();
            if($('#entrustId').val() != null && $('#entrustId').val() != ''){
                $('#prinAmt').attr('disabled',true);
            }

    }
    /*
     * 交易方向修改联动显示持仓，和非持仓
     */
    $('#ps').change(function(){
//        var ps = $('#ps').val();
//        if(ps !== buy){
//            $('#Buy').css('display','none');
//            $('#Sale').css('display','');
//        }else{
//            $('#Buy').css('display','');
//            $('#Sale').css('display','none');
//        }
        psChange();
        available();

    });

    var isCleanChange = function(){
    	if (pageModel != PAGE_MODEL_REFERENCE) {
    		if ($("#ps").val() != buy){
                if ($('#isClean')[0].checked) {
                	if($('#portfolioId').val()&&$('#finprodMarketId').val()&&$('#tradeDate').val()){
                        var param = {
                            portfolioId:$('#portfolioId').val(),
                            finprodMarketId:$('#finprodMarketId').val(),
                            cDate:$('#tradeDate').val()
                        };
                        Joyin.ajaxJson(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getTdyIntincexp",param,function(result){
                            if (Number(result) > 0){
                                $('#intAmt').val(Number(result));
                                changeTradeAmtAndFee();
                            }else {
                                $('#intAmt').val('');
                            }
                        });
                        //终止协议勾选时，本金金额不可编辑，等于资产可用持仓
                        $("#prinAmt").val($("#bottomAssetAmount").val());
                        $("#prinAmt").attr('disabled',true);
                        //终止协议勾选时，获取应记利息总额
                    
                	}
                }else {
                    $("#prinAmt").attr('disabled',false);
                }
            }
    	}
    };
    var psChange = function(){
        var ps = $('#ps').val();
        if(ps !== buy){
            $('#Buy').css('display','none');
            // <!-- 通道交易 新增 强成西 2019/03/26 start -->
            $('.channel_cash').css('display','none');
            // <!-- 通道交易 新增 强成西 2019/03/26 end -->
            $('#Sale').css('display','');
            //如果是卖出并且资产类型是 活期存放
            if ($('#finprodType').val() == 'D03'){
                $("#isClean_div").show();
                $("#intAmt").attr('disabled',false).trigger("change");
            }else {
                $("#isClean_div").hide();
                $("#intAmt").attr('disabled',true).trigger("change");
            }
        }else{
            $('#Buy').css('display','');
            // <!-- 通道交易 新增 强成西 2019/03/26 start -->
            $('.channel_cash').css('display','');
            // <!-- 通道交易 新增 强成西 2019/03/26 end -->
            $('#Sale').css('display','none');
            $("#isClean_div").hide();
            $("#intAmt").attr('disabled',true).trigger("change");
            $("#prinAmt").attr('disabled',false).trigger("change");
        }

        //初始化协议终止隐藏
        if(!ps){
            $("#isClean_div").hide();
        }

        //买入 卖出 且选择通道时 显示通道费
        if((ps==PS_T01||ps==PS_T02)&&$("#chlId").val()){

        	$("#channelFee_div").show();
        }else{
        	$("#channelFee_div").hide()
        }
        checkChannelFee();
    }
    
    /**
     * 标的挂通道 内转交易 通道费置灰
     * 外部卖出显示可填  其他置灰
     */
    var checkChannelFee = function(){
        var ps = $('#ps').val();
        if(IS_CAL_LOADRATE&&IS_CAL_LOADRATE=='01'){
        	if(ps==PS_T01&&$('#trdInvestPersonInfo002Dto_counterId').val()&&$("#trdInvestPersonInfo002Dto_counterId").select2('data')[0].object!='N'){
            	$("#channelFee").val("").attr('disabled',true);
            }else{
            	$("#channelFee").attr('disabled',false);
            }
        }else{
        	if(ps==PS_T02&&!$('#trdInvestPersonInfo002Dto_counterId').val()){
            	$("#channelFee").attr('disabled',false);
            }
            else if(ps==PS_T02&&$('#trdInvestPersonInfo002Dto_counterId').val()&&$("#trdInvestPersonInfo002Dto_counterId").select2('data')[0].object!='N'){
            	$("#channelFee").attr('disabled',false);
            }
            else{
            	$("#channelFee").val("").attr('disabled',true);
            }
        }
        changeTradeAmtAndFee();
        
    }
    
    
    var portChange = function(){
        Joyin.ajax(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getIsGuarantee",{portfolioId:$('#portfolioId').val()},function(result){
            if(result != null && result !== ''){
                if(result !== YES){
                    //$("#OpicsTable").css('display','none');
//                    $("#BussTrdTransferOrder").css('display','');
                    isOpics = 'N';
                }else{
                    //$("#OpicsTable").css('display','');
//                    $("#BussTrdTransferOrder").css('display','none');
                    isOpics = 'Y';
                }
            }
        });
    }
    
    $('#portfolioId').change(function(){
        available();
//        Joyin.ajax(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getIsGuarantee",{portfolioId:$('#portfolioId').val()},function(result){
//            if(result != null && result !== ''){
//                if(result !== YES){
//                    $("#OpicsTable").css('display','none');
//                    $("#BussTrdTransferOrder").css('display','');
//                    isOpics = 'N';
//                }else{
//                    $("#OpicsTable").css('display','');
//                    $("#BussTrdTransferOrder").css('display','none');
//                    isOpics = 'Y';
//                }
//            }
//        });
        portChange();
        isCleanChange();
    });
    
    /**
     * 指令生成
     */
    $('#TrdDepositInvestTrade002_generate').click (function(){
        $('#bussTrdTransferOrder_btn_generate').trigger('click');
        var object = $.serializeToObject('#basicInfoForm');
        if(object.loadRate != null){
            object.loadRate = object.loadRate.valueOf()/100;
        }
        if($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != ''){
            object.finprodId= $("#finprodMarketId").select2("data")[0].object;
        }else if(finprodId != null){
            object.finprodId = finprodId;
        }
		/**
        Joyin.ajaxJson(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getOpics",object,function(result){
            var rows = table.getAllRows();
            for(var i in rows){
                table.removeRow(rows[i]);
            }
            table.addRow(result);
        });
		*/
    });

    // <!-- 通道交易 新增 强成西 2019/03/26 start -->
    $("#channelFee").on("change", function () {
        changeTradeAmtAndFee();
    });

     $("#prinAmt").on("change", function () {
         changeTradeAmtAndFee();
     });

     $("#intAmt").on("change", function () {
         changeTradeAmtAndFee();
     });

    $("#chlId").on("change", function () {
        available();
      //卖出 且选择通道时 显示通道费
        if($("#ps").val()&&($("#ps").val()==PS_T02||$("#ps").val()==PS_T01)&&$("#chlId").val()){
        	$("#channelFee_div").show();
        }else{
        	$("#channelFee_div").hide()
        }
        changeTradeAmtAndFee();
    });

    var changeTradeAmtAndFee = function() {
//        if ($("#ps").val() == PS_T01) {
//            $("#tradeAmtAndFee").val(JoyinMath.doAdd(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2), $("#channelFee").val(), 2)).trigger('blur');
//        }
//        else {
//            $("#tradeAmtAndFee").val(JoyinMath.doMinus(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2), $("#channelFee").val(), 2)).trigger('blur');
//        }
    	var data = $("#chlId").find("option:selected").data("collecttype");
    	if($("#chlId").val()&&data){
//    		if($("#chlId").select2("data")[0].object.collectType==collectType_01){
    		if(($("#trdInvestPersonInfo002Dto_counterId").val()&&$("#trdInvestPersonInfo002Dto_counterId").select2('data')[0].object=='N')
           		 ||($("#trdInvestPersonInfo002Dto_counterId").val()&&$("#trdInvestPersonInfo002Dto_counterId").select2('data')[0].object!='N'
        			 &&$("#ps").val() == PS_T02&&data==collectType_01)){
//    			2019.12.26 通道费改造  标的上挂通道，不参与计算(存款根据开关标识
    			if(IS_CAL_LOADRATE&&IS_CAL_LOADRATE=='01'){
    				$("#tradeAmtAndFee").val(JoyinMath.doMinus(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2), $("#channelFee").val(), 2)).trigger('blur');
    			}else{
    				$("#tradeAmtAndFee").val(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2)).trigger('blur');
    			}
//    			$("#tradeAmtAndFee").val(JoyinMath.doMinus(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2), $("#channelFee").val(), 2)).trigger('blur');
    		}else if(!$("#trdInvestPersonInfo002Dto_counterId").val()&&data==collectType_01){
//    			2019.12.26 通道费改造  标的上挂通道，不参与计算(存款根据开关标识
    			if(IS_CAL_LOADRATE&&IS_CAL_LOADRATE=='01'){
    				$("#tradeAmtAndFee").val(JoyinMath.doMinus(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2), $("#channelFee").val(), 2)).trigger('blur');
    			}else{
    				$("#tradeAmtAndFee").val(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2)).trigger('blur');
    			}
//    			$("#tradeAmtAndFee").val(JoyinMath.doMinus(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2), $("#channelFee").val(), 2)).trigger('blur');
    		}
    		else{
    			$("#tradeAmtAndFee").val(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2)).trigger('blur');
    		}
    	}else{
    		$("#tradeAmtAndFee").val(JoyinMath.doAdd($("#prinAmt").val(), $("#intAmt").val(), 2)).trigger('blur');
    	}
    }
    // <!-- 通道交易 新增 强成西 2019/03/26 end -->


    /**
     * 利息计算
     */
    $('.rateCal').change(function (){
        intAmtCalcu();
    });
    /**
     * 利息计算函数,已改用自动关联带出了
     */
    var rateCalculate = function(){
        let object = {
                vdate:$('#vdate').val(),
                mdate:$('#mdate').val(),
                loadRate:$('#loadRate').val().valueOf() / 100,
                prinAmt:$('#prinAmt').val(),
                basis:$('#basis').val(),
                finprodType:$('#finprodType').val()
        }
        if(object.vdate != null && object.mdate != null && object.loadRate !=null && object.prinAmt != null &&
                object.vdate != '' && object.mdate != '' && object.loadRate != '' && object.prinAmt != '' && object.finprodType != 'D03'){
            Joyin.ajaxJson(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getIntAmt",object,function(result){
                $('#intAmt').val(result);
            });
        }else{
            $('#intAmt').val('');
        }
        changeTradeAmtAndFee();
    }

    /**
     * 利息总额计算,增加分段进行计算
     */
    var intAmtCalcu = function(){
        if (pageModel != PAGE_MODEL_REFERENCE) {
            if($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != ''){
                finprodId = $("#finprodMarketId").select2("data")[0].object;
            }
            object = {
                //                portfolioId:$('#portfolioId').val(),
                finprodId:finprodId,
                settleDate:$('#tradeDate').val(),
                //                investRate:$('#loadRate').val().valueOf() / 100,
                prinAmt:$('#prinAmt').val(),
                finprodType:$('#finprodType').val()
            }
            if(object.finprodId != null && object.finprodId != null &&
                object.settleDate != '' && object.settleDate != '' && object.prinAmt != null && object.prinAmt != ''){
                if(object.finprodType != 'D03'){
                    Joyin.ajaxJson(basePath+ "invest/trade/trdplanrateinvesttrade/TrdPlanRateInvestTrade002/getIntAmt",object,function(result){
                        $('#intAmt').val(JoyinMath.doAdd(result.data,0,2)).trigger('blur');
                        // $('#tradeAmt').val(parseFloat(result.data)+parseFloat($('#prinAmt').val().valueOf())).trigger('blur');
                        changeTradeAmtAndFee();
                    });
                }else {
                    //如果是活期存放则取估值的数据
                	// modify by wj 20200506  TISHKBS-300
                	//存款类资产活期存放类型二次买入的时候应计利息总额会带出之前计提的利息  期望：应计利息总额应该是0，交易金额=本金金额
                    if($('#ps').val() && $('#ps').val() == buy){
                    	 $('#intAmt').val(0).trigger('blur');
                         changeTradeAmtAndFee();
                    }else{
                    	
                    	var param = {
                                portfolioId:$('#portfolioId').val(),
                                finprodMarketId:$('#finprodMarketId').val(),
                                cDate:$('#tradeDate').val()
                            };
                            if(param.finprodMarketId != null && param.finprodMarketId != null &&
                                param.portfolioId != '' && param.portfolioId != '' && param.cDate != null && param.cDate != ''
                                && $("#prinAmt").val() && Number($("#prinAmt").val())>0){
                                Joyin.ajaxJson(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/getTdyIntincexp", param,function(result){
                                    $('#intAmt').val(Number(result)).trigger('blur');
                                    changeTradeAmtAndFee();
                                    // $('#tradeAmt').val(parseFloat(result.data)+parseFloat($('#prinAmt').val().valueOf())).trigger('blur');
                                });
                            }
                    	
                    }
                    
                }
            }else {
                $('#intAmt').val('').trigger('blur');
                // $('#tradeAmt').val('').trigger('blur');
                changeTradeAmtAndFee();
            }
        }

    }
    /**
     * 获取持仓、现金余额、可用现金等
     * @param object json对象
     * @returns 获取持仓、现金余额、可用现金等
     */
    function available(){
        var object = getParameters(); 
        if(object.portfolioId != null && object.portfolioId != '' && object.ps != null && object.ps != '' && object.ccy != null && object.ccy != '' && object.tradeDate != null){
            Joyin.ajax(basePath+ "invest/trade/trddepositinvesttrade/TrdDepositInvestTrade002/available",object,function(result){
                if(object.ps == buy){
                    //如果资产代码为空，后台返回空，赋值报错
                    if (result.length > 0) {
                        $('#accAvailableCashT').val(result[0].name).trigger('blur');
                        $('#accAvailableCashT1').val(result[1].name).trigger('blur');
                        $('#accAvailableCash').val(result[2].name).trigger('blur');
                        if($('#chlId').val()){
                            $('#channelAvailableCash').val(result[3].name).trigger('blur');
                        }else{
                            $('#channelAvailableCash').val('').trigger('blur');
                        }
                        if(result[3].name){
                            isHoliday = result[3].name;
                        }
                    }
                    
                }else{
                    if (result.length > 0){
                        $('#bottomAssetAmount').val(result[0].name).trigger('blur');
                        if(result[1].name){
                            isHoliday = result[1].name;
                        }
                        //如果是卖出且终止协议勾选，本金金额等于资产可用持仓
                        if ($('#isClean')[0].checked) {
                            $("#prinAmt").val($("#bottomAssetAmount").val());
                        }
                    } 
                }
            });
        }
    }
    //add by lizhi 2020-2-26 划款指令是否自动生成  start
    var generateOrders = function (callback) {
    	// 如果还款指令设置为隐藏，则自动生成划款指令
    	if(GEN_PAY_ORDERS_AUTO =='01'){
    		$('#bussTrdTransferOrder_btn_generate').click();
    	}
    	// 回调方法
	    if (callback && typeof callback === 'function') {
	        callback();
	    }
    }
    //add by lizhi 2020-2-26 划款指令是否自动生成  end
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 是否显示划款指令
        if(GEN_PAY_ORDERS_AUTO=='01'){
        	$('#BussTrdTransferOrder').hide() ;
        }else{
        	$('#BussTrdTransferOrder').show() ;
        }
        $('#TrdDepositInvestTrade002_btn_save').click(function() {
            saveOrSubmit('save');
        });
       
        //导出
        $('#TrdDepositInvestTrade002_btn_submit').click(function() {
            saveOrSubmit("submit");
        });
        
        $('#TrdDepositInvestTrade002_btn_001').click(function(){
            if($('#finprodMarketId').val() != null && $('#finprodMarketId').val() != ''){
                finprodId = $("#finprodMarketId").select2("data")[0].object;
            }
            if(finprodId == null || finprodId == ''){
                return;
            }
            Joyin.dialog(JoyinUtil.getLabel("FinDepositTarget002"),
                    basePath + "invest/asset/depo/FinDepositTarget002/init",
                    "95%", "90%",{pageModel:PAGE_MODEL_REFERENCE,
                    finprodId: finprodId});
        })
       $('#TrdDepositInvestTrade002_btn_riskMath').click(function(){
           //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
           //-----------add by jjpei 2020-01-07 风险新版本 start----------
           var object = $.serializeToObject('#basicInfoForm', true);
           if($("#finprodMarketId").val() != null && $("#finprodMarketId").val() != ''){
               object.finprodId = $("#finprodMarketId").select2("data")[0].object;
           }else if(finprodId != null){
               object.finprodId = finprodId;
           }
           window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/doInfData/1/RSK002/F05',object,getriskdata());
           //-----------add by jjpei 2020-01-07 风险新版本 end----------
           window.BaseRiskMathComm.initBaseRiskMathCommPageCus(getriskdata());

       })
     //是否展示资产四五级分类
       if(IS_SHOW_TYPE == '01'){
       	$('#selector5').show();
       	}else{
       	$('#selector5').hide();
       	}

        //终止协议绑定change事件
        layui.form.on('switch(isClean)',function(){
            isCleanChange();
        });
    });
}());
