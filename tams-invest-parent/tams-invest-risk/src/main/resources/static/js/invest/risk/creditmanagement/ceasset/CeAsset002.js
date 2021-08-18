(function() {
	var initFlag = true;
	function onSaveOrSubmitAction(operateType) {
        var object = $.serializeToObject('#ceAssetForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.creditMstId='0002';
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        // 资产授信新建增加字段  add by yufei 20200630 start
        if($('#firstLendingInterval').val()){
            object.creditVdate = $('#firstLendingInterval').startDate();// 首次开贷区间开始日
            object.creditMdate = $('#firstLendingInterval').endDate();// 首次开贷区间结束日
        }
        // 资产授信新建增加字段  add by yufei 20200630 end
    	 Joyin.ajaxJson(basePath + 'invest/risk/creditmanagement/ceasset/CeAsset002/check', object, function(result) {        	
     		Joyin.confirm(result.data, function() {
		        if(operateType === 'save') {
		            WorkFlow.doSaveList(basePath + 'invest/risk/creditmanagement/ceasset/CeAsset002/save', object, "CeAsset002_btn_save");
		        }
		        else {
		            WorkFlow.doSubmitList(basePath + 'invest/risk/creditmanagement/ceasset/CeAsset002/save', object, "CeAsset002_btn_submit");
		        }
     		});
    	 });   
    };	
    // 资产授信新建增加字段 add by yufei 20200630 start
    /**
     * 授信对象监听
     */
    var onCreditObjChange = function (){
        // 获取授信对象
        var data = $('#creditObj').select2('data')[0];
        // 授信总份额（亿份）展示控制
        if(data && data.id == creditObj_02){
            showCreditShareAmt();
        }
        else {
            showCreditAmt();
        }
    }
    /**
     * 资产变更监听
     */
    var onCustomerIdChange = function (){
        var data = $('#customerId').select2('data')[0].object;
        // 授信总份额（亿份）展示控制
        if(data && (data.finprodType2 == finprodType2_F06 ||
            (data.finprodType2 == finprodType2_F17  && (data.profitType == profitType_01 || data.profitType == profitType_03)))){
            $("#creditObj_div").show();
            if(!initFlag){
            	$("#creditObj").val("02").trigger("change");
            }
        }
        else {
            $("#creditObj_div").hide();
            if(!initFlag){
            	$("#creditObj").val("01").trigger("change");
            }
        }
        if(data&&(data.finprodType2 == 'F01'||data.finprodType2 == 'F32')){
            $('#creditPrice').attr("disabled",false);
        }else{
            $('#creditPrice').val("").attr("disabled",true);
        }
        // 页面展示控制
        initElements();
    }
    /**
     * 初始化展示总份额还是总额度
     */
    var initAmt = function () {
        var data = $('#customerId').select2('data')[0].object;
        var creditObj = $('#creditObj').select2('data')[0];
        // 授信总份额（亿份）展示控制
        if(creditObj ){
            if(creditObj && creditObj.id == creditObj_02) {
                showCreditShareAmt();
            }
            else {
                showCreditAmt();
            }
        }
        else {
            if(data && data.finprodType2 == finprodType2_F17 
                    && (data.profitType == profitType_01 || data.profitType == profitType_03)){
                showCreditShareAmt();
            }
            else {
                showCreditAmt();
            }
        }
    }
    /**
     * 隐藏总份额，显示总额度
     */
    var showCreditAmt = function () {
        $("#creditShareAmt").val("");
        $("#creditShareAmtDiv").toggle(false);
        $("#creditAmtDiv").toggle(true);
    }
    /**
     * 隐藏总额度，显示总份额
     */
    var showCreditShareAmt = function () {
        $("#creditShareAmtDiv").toggle(true);
        $("#creditAmt").val("");
        $("#creditAmtDiv").toggle(false);
    }
    /**
     * 审批开始日、审批结束日展示控制
     */
    var initApproveDateDiv = function (){
        var data = $('#customerId').select2('data')[0].object;
        // 审批开始日、审批结束日展示控制
        if(data && data.finprodType2==finprodType2_F01){
            $("#approveVdateDiv").toggle(true);
            $("#approveMdateDiv").toggle(true);
         }
         else {
             $("#apprVdate").val("");
             $("#approveVdateDiv").toggle(false);
             $("#apprMdate").val("");
             $("#approveMdateDiv").toggle(false);
         }
    }
    /**
     * 首次开贷区间展示控制
     */
    var initFirstLendingIntervalDiv = function (){
        var data = $('#customerId').select2('data')[0].object;
        if(data && (data.finprodType2 == finprodType2_F18
            || (data.finprodType2 == finprodType2_F37 && data.type9 == type9_87))){
            $("#firstLendingIntervalDiv").toggle(true);
        }else {
             $("#firstLendingInterval").val("");
             $("#firstLendingIntervalDiv").toggle(false);
         }
    }
    /**
     * 页面原始初始化
     */
    var initElements = function (){
        initFirstLendingIntervalDiv();
        initApproveDateDiv();
        initAmt();
    }
    // 资产授信新建增加字段  add by yufei 20200630 end
	//modify by gaopeng 20191012 start 编辑时资产名称不可编辑
    //新建或编辑初始化
    function init(){
        $("#creditObj_div").hide();
    	if(pageModel!=PAGE_MODEL_NEW){
            $("input[name='pflsh']").attr('disabled',true);
            $("#customerId").attr('disabled',true);
            initElements();
        }
        onCustomerIdChange();
        initFlag = false;
    }
    //modify by gaopeng 20191012 end 编辑时资产名称不可编辑
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
        // 保存按钮执行事件
        $('#CeAsset002_btn_save').click(function(){
    		onSaveOrSubmitAction('save');
        });
        // 提交按钮执行事件
        $('#CeAsset002_btn_submit').click(function(){
    		onSaveOrSubmitAction('submit');
        });

        // 资产授信新建增加字段 add by yufei 20200630 start
        // 资产变更监听
        $('#customerId').change(function(){
            onCustomerIdChange();
        });
        // 授信对象监听
        $('#creditObj').change(function(){
            onCreditObjChange();
        });
        // 资产授信新建增加字段  add by yufei 20200630 end
    });
}());