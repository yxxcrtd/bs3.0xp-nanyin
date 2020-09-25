(function () {
    /**
     * 页面初始化
     */
	var acctNoList;
    var calcConditionObjBackUp;
    var isInItDone= true;
//    var childPortfolioInfo = {};
    repoInfoTableOption = {
        rowno: true,
        searchOnInit: true,
        checkbox: false,
        page: false,
        cols: [
            //组合
            {field: 'portfolioId', width: "200", title: 'IntDetailDuration.portfolioId', sortable:false}
//            组合名称
            ,{field: 'portfolioName', width: "200", title: 'IntDetailDuration.portfolioName', sortable:false}
          //投资目的
            , {field: 'invAimStr', width: "90", title: '投资目的', sortable:false}
          //投资目的
            , {field: 'invAim', width: "90", title: '投资目的', sortable:false, visible : false}
            //未支付本金
            , {field: 'prinAmtTheory', width: "200", title: '未支付本金',type: 'money', sortable:false}
//          未支付利息
            , {field: 'theoryInt', width: "200", title: '未支付利息',type: 'money',sortable:false}
            //理论罚息总额
            , {field: 'theoryPenalty', width: "200", title: '理论罚息总额', type: 'money', sortable:false}
            //实收本金
            , {field: 'prinAmt', width: "120", title: '实收本金',type: 'money', sortable:false}
            //实收利息
            , {field: 'intAmt', width: "120", title: '实收利息', type: 'money',sortable:false,edit:true}
            //实收罚息
            , {field: 'penaltyIntamt', width: "200", title: '实收罚息', type: 'money', sortable:false,edit:true}
            //实收本息和
            , {field: 'tradeAmt', width: "200", title: '实收本息总额', type: 'money', sortable:false}
            //通道费
            , {field: 'channelFee',  width: "200",title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("TrdDepoDuration002.channelFee");}(), type: 'money', sortable:false, visible : CHANNEL,edit:true}
          //收息未支付利息
            , {field: 'chargeUnpayInterest',  width: "200",title: '收息未支付利息', type: 'money', sortable:false, visible : false}
          //还本未支付利息
            , {field: 'repaymentUnpayInterest',  width: "200",title: '还本未支付利息', type: 'money', sortable:false, visible : false}
          //理论利息罚息
            , {field: 'interestPenalty',  width: "200",title: '理论利息罚息', type: 'money', sortable:false, visible : false}
          //理论本金罚息
            , {field: 'capitalPenalty',  width: "200",title: '理论本金罚息', type: 'money', sortable:false, visible : false}
          //理论还本未付利息罚息
            , {field: 'repaymentUnpayHoldPenalty',  width: "200",title: '理论还本未付利息罚息', type: 'money', sortable:false, visible : false}
          //还本未付息总持仓
            , {field: 'repaymentUnpayHoldPosition',  width: "200",title: '还本未付息总持仓', type: 'money', sortable:false, visible : false}
            , {field: 'payDate',  width: "200",title: '计划支付日', type: 'date', sortable:false, visible : false}
            
            , {field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['edit'],
                render: function (data, type, row, meta) {
                    if(row.investYesorno =='Y'){
                        return;
                    }
                    return '<i class="btn layui-icon detail" data-ignore="true" title="明细">&#xe60a;</i>';
                }
            }
        ],
        /*beforeEditCell:function(){
        	return false;
        },*/
        editCell:function(value, data, field, cell, column){
        	var int =0;
        	var flag = false;//是否联动计算通道费信息
        	if(CHANNEL){//通道存在
        		if($("#finprodType2").val()==FINPROD_TYPE_TG_F01){//债券
            		if(COLLECT_TYPE==COLLECT_TYPE_01){//回款前扣除
            			flag=true;
            		}
            	}else if($("#finprodType2").val()==FINPROD_TYPE_TG_F07){//存款
            		if(COLLECT_TYPE==COLLECT_TYPE_01&&IS_CAL_LOADRATE&&IS_CAL_LOADRATE=='01'){//回款前扣除
            			flag=true;
            		}
            	}
        		//项目与计划类 标的挂通道  不影响交易金额
        	}
        	if(field=='penaltyIntamt'){
        		//此处取出值为string 可能存在逗号，手动去除加减
        		if(flag){
        			int=JoyinMath.doMinus(JoyinMath.doAdd(Number(value.replace(",","")),data.intAmt),data.channelFee)
        		}else{
        			int=JoyinMath.doAdd(Number(value.replace(",","")),data.intAmt)
        		}
        		
        	}else if(field=='intAmt'){
        		//此处取出值为string 可能存在逗号，手动去除加减
        		if(flag){
        			int =JoyinMath.doMinus(JoyinMath.doAdd(Number(value.replace(",","")),data.penaltyIntamt),data.channelFee)
        		}else{
        			int =JoyinMath.doAdd(Number(value.replace(",","")),data.penaltyIntamt)
        		}
        	}else if(field=='channelFee'){
        		//此处取出值为string 可能存在逗号，手动去除加减
        		if(flag){
        			int =JoyinMath.doMinus(JoyinMath.doAdd(data.intAmt,data.penaltyIntamt),Number(value.replace(",","")))
        		}else{
        			int =JoyinMath.doAdd(data.intAmt,data.penaltyIntamt)
        		}
        	}
    		data.tradeAmt=JoyinMath.doAdd(data.prinAmt,int)
    		repoInfoTable.renderRow(data.LAY_TABLE_INDEX,{"tradeAmt": data.tradeAmt})
    		//删除对应子组合信息
    		var portfolioIdInvAim = data.portfolioId+data.invAim;
    		delete childPortfolioInfo[portfolioIdInvAim];
        },
        done : function(){
        	if (formTodoList&&isInItDone) {
        		 window.BussTrdTransferOrder['bussTrdTransferOrder'].saveCalcCondition();
            }
        	isInItDone=false;
        },
        data: []
    }

    if (finAssetIntInfoList && finAssetIntInfoList.length > 0) {
        repoInfoTableOption.data = repoInfoTableOption.data.concat(finAssetIntInfoList);
        var theoryPrin =0;
        var theoryInt =0;
        var theoryPenaltyInt =0;
        for(var i=0;i<finAssetIntInfoList.length;i++){
        	theoryPrin += finAssetIntInfoList[i].prinAmtTheory;
        	theoryInt += finAssetIntInfoList[i].theoryInt
        	theoryPenaltyInt += finAssetIntInfoList[i].theoryPenalty
        }
        //初始化默认
        if(!$("#theoryPrin").val()){
        	$("#theoryPrin").val(theoryPrin);//未支付本金
        }
        if(!$("#theoryInt").val()){
        	$("#theoryInt").val(theoryInt);//未支付利息
        }
        if(!$("#theoryPenaltyInt").val()){
        	$("#theoryPenaltyInt").val(theoryPenaltyInt);//理论罚息总额
        }
        if(!$("#prinAmt").val()){
        	$("#prinAmt").val(theoryPrin);//实收本金
        }
        if(!$("#intAmt").val()){
        	$("#intAmt").val(theoryInt);//实收利息
        }
        if(!$("#penaltyIntamt").val()){
        	$("#penaltyIntamt").val(theoryPenaltyInt);//实收罚息
        }
        if(!$("#tradeAmt").val()){
        	$("#tradeAmt").val(JoyinMath.doAdd(theoryPrin,JoyinMath.doAdd(theoryInt,theoryPenaltyInt)));
        }
        //本金分支序号不存在时，实付本金不可填
        if(!$("#bjBranch").val()){
        	$("#prinAmt").attr('disabled', true);;//实收本金
        }
      //利息分支序号不存在时，实付利息不可填
        if(!$("#liBranch").val()){
        	$("#intAmt").attr('disabled', true);;//实收本金
        }
        //通道交易时，通道费显示
        if(CHANNEL){
        	$('#channelFee_div').show();
        }
    }
    /**
     * 定义数据表格
     */
    var repoInfoTable = $("#intDetailTable").joyintable(repoInfoTableOption);

    /**
     * 绑定编辑事件
     */
    repoInfoTable.bind('click', '.detail', function(data, index) {
    	var bjBranch ="";
    	if($("#bjBranch").val()){
    		bjBranch =$("#bjBranch").val();
    	}
    	var liBranch ="";
    	if($("#liBranch").val()){
    		liBranch =$("#liBranch").val();
    	}
    	var portfolioIdInvAim = data.portfolioId+data.invAim;
    	var childPortfolioInfoPortfolioIdInvAim = "" ;
    	if(childPortfolioInfo[portfolioIdInvAim]){
    		childPortfolioInfoPortfolioIdInvAim=JSON.stringify(childPortfolioInfo[portfolioIdInvAim]);
    	}
    	//实际交易组合无子组合交易明细
        var param = {
        	"secOver":true,//是否逾期交易
        	"portfolioId":data.portfolioId,
            "finprodId":$('#finprodId').val(),
            "bjBranch":bjBranch,
            "liBranch":liBranch,
            "secManageAcctId":$("#secAcctId").val(),
            "invAim":data.invAim,
            "CHANNEL":CHANNEL,
            "COLLECT_TYPE":COLLECT_TYPE,
            "finprodType2":finprodType2,
            "preMap":JSON.stringify({
                "prinAmt":data.prinAmt,
                "intAmt":data.intAmt,
                "penaltyIntamt":data.penaltyIntamt,
                "tradeAmt":data.tradeAmt,
                "channelFee":data.channelFee
                
            }),
            "childPortfolioInfo":childPortfolioInfoPortfolioIdInvAim,
            cols:JSON.stringify([
            	//投资目的
                {field: 'invAimStr', width: "90", title: '投资目的', sortable:false}
              //投资目的
                ,{field: 'invAim', width: "90", title: '投资目的', sortable:false, visible : false}
                //未支付本金
                , {field: 'prinAmtTheory', width: "200", title: '未支付本金',type: 'money', sortable:false}
//              未支付利息
                , {field: 'theoryInt', width: "200", title: '未支付利息',type: 'money',sortable:false}
                //理论罚息总额
                , {field: 'theoryPenalty', width: "200", title: '理论罚息总额', type: 'money', sortable:false}
                //实收本金
                , {field: 'prinAmt', width: "120", title: '实收本金',type: 'money', sortable:false}
                //实收利息
                , {field: 'intAmt', width: "120", title: '实收利息',type: 'money', sortable:false,edit:true}
                //实收罚息
                , {field: 'penaltyIntamt', width: "200", title: '实收罚息', type: 'money', sortable:false,edit:true}
                //实收本息和
                , {field: 'tradeAmt', width: "200", title: '实收本息总额', type: 'money', sortable:false}
                //通道费
                , {field: 'channelFee',  width: "200",title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("TrdDepoDuration002.channelFee");}(), type: 'money', sortable:false, visible : CHANNEL,edit:true}
              //收息未支付利息
                , {field: 'chargeUnpayInterest',  width: "200",title: '收息未支付利息', type: 'money', sortable:false, visible : false}
              //还本未支付利息
                , {field: 'repaymentUnpayInterest',  width: "200",title: '还本未支付利息', type: 'money', sortable:false, visible : false}
              //理论利息罚息
                , {field: 'interestPenalty',  width: "200",title: '理论利息罚息', type: 'money', sortable:false, visible : false}
              //理论本金罚息
                , {field: 'capitalPenalty',  width: "200",title: '理论本金罚息', type: 'money', sortable:false, visible : false}
              //理论还本未付利息罚息
                , {field: 'repaymentUnpayHoldPenalty',  width: "200",title: '理论还本未付利息罚息', type: 'money', sortable:false, visible : false}
              //还本未付息总持仓
                , {field: 'repaymentUnpayHoldPosition',  width: "200",title: '还本未付息总持仓', type: 'money', sortable:false, visible : false}
                , {field: 'payDate',  width: "200",title: '计划支付日', type: 'date', sortable:false, visible : false}
            ])
        };
        param.pageModel = pageModel;
        Joyin.dialog(JoyinUtil.getLabel('TrdDurationCommon.ChildPortfolioInfo'), JoyinUtil.getRealPath('invest/period/common/ChildPortfolioInfo/init'), '80%', '80%', param,function(){
        	childPortfolioInfo[portfolioIdInvAim]=top.childPortfolioInfo;
        });
    });
    
    /**
     * 实收罚息计算
     */
    var penaltyIntamtCal = function(){
    	/*if(Number($("#penaltyIntamt").val())>0){
    		if(Number($("#penaltyIntamt").val())>Number($("#channelFee").val())){
    			$("#penaltyIntamt").val(JoyinMath.doMinus($("#penaltyIntamt").val(),$("#channelFee").val()))
    		}else{
    			//实收罚息扣减为零， 扣减实收利息
    			$("#penaltyIntamt").val(0);
    			$("#intAmt").val(JoyinMath.doMinus($("#intAmt").val(),JoyinMath.doMinus($("#channelFee").val(),$("#penaltyIntamt").val())));
    		}
    	}else{
    		$("#intAmt").val(JoyinMath.doMinus($("#intAmt").val(),$("#channelFee").val()))
    	}*/
    	var flag = false;//是否联动计算通道费信息
    	if(CHANNEL){//通道存在
    		if($("#finprodType2").val()==FINPROD_TYPE_TG_F01){//债券
        		if(COLLECT_TYPE==COLLECT_TYPE_01){//回款前扣除
        			flag=true;
        		}
        	}else if($("#finprodType2").val()==FINPROD_TYPE_TG_F07){//存款
        		if(COLLECT_TYPE==COLLECT_TYPE_01&&IS_CAL_LOADRATE&&IS_CAL_LOADRATE=='01'){//回款前扣除
        			flag=true;
        		}
        	}
    		//项目与计划类 标的挂通道  不影响交易金额
    	}
    	if(flag){
    		$("#penaltyIntamt").val(JoyinMath.doAdd(JoyinMath.doMinus($("#tradeAmt").val(),JoyinMath.doAdd($("#intAmt").val(),$("#prinAmt").val())),$("#channelFee").val()));
    	}else{
    		$("#penaltyIntamt").val(JoyinMath.doMinus($("#tradeAmt").val(),JoyinMath.doAdd($("#intAmt").val(),$("#prinAmt").val())));
    	}
    	resetTableData();
    }
    
    /**
     * 实收本息和
     */
    var fpriceAmtCal = function(){
    	var flag = false;//是否联动计算通道费信息
    	if(CHANNEL){//通道存在
    		if($("#finprodType2").val()==FINPROD_TYPE_TG_F01){//债券
        		if(COLLECT_TYPE==COLLECT_TYPE_01){//回款前扣除
        			flag=true;
        		}
        	}else if($("#finprodType2").val()==FINPROD_TYPE_TG_F07){//存款
        		if(COLLECT_TYPE==COLLECT_TYPE_01&&IS_CAL_LOADRATE&&IS_CAL_LOADRATE=='01'){//回款前扣除
        			flag=true;
        		}
        	}
    		//项目与计划类 标的挂通道  不影响交易金额
    	}
    	if(flag){
    		$("#tradeAmt").val(JoyinMath.doMinus(JoyinMath.doAdd($("#penaltyIntamt").val(),JoyinMath.doAdd($("#intAmt").val(),$("#prinAmt").val())),$("#channelFee").val()));
    	}else{
    		$("#tradeAmt").val(JoyinMath.doAdd($("#penaltyIntamt").val(),JoyinMath.doAdd($("#intAmt").val(),$("#prinAmt").val())));
    	}
    	penaltyIntamtCal();
    }
    

    
    /**
     * 利用占比计算所有的金额
     */
    var resetTableData = function () {
    	//清空所有子组合信息
    	childPortfolioInfo ={};
        // 按占比计算还本金额、理论本息、实收本息
        var finAssetIntInfos = finAssetIntInfoList;
        //实收本金：未支付本金，实收利息：未支付利息，实收罚息：理论未支付罚息，实收本息和：未支付本金+未支付利息+理论未支付罚息
        var itemMap ={"prinAmt":"prinAmtTheory","intAmt":"theoryInt","penaltyIntamt":"theoryPenalty","tradeAmt":"tradeAmt","channelFee":"tradeAmt"}
//      实收本金、实收利息、实收罚息、实收本息和
        var itemArr = ['prinAmt','intAmt','penaltyIntamt','tradeAmt','channelFee'];
        $.each(itemArr,function(index,val){
//			初始化
        	var itemMapval = itemMap[val];
        	//----   组合按持仓从小到大排序--start---
            finAssetIntInfos.sort(function (a,b) {
                return JoyinMath.doMinus(a[itemMapval], b[itemMapval]);
            })
            //---- 组合按持仓从小到大排序--end---
        	var sumAmount = 0;
	        for (var i = 0; i < finAssetIntInfos.length; i++) {
//				初始化
	        	var finAssetIntInfosi = finAssetIntInfos[i];
	        	if(val=="tradeAmt"||val=="channelFee"){
	        		sumAmount =JoyinMath.doAdd( sumAmount,JoyinMath.doAdd(finAssetIntInfosi.prinAmtTheory, JoyinMath.doAdd(finAssetIntInfosi.theoryInt, finAssetIntInfosi.theoryPenalty)))||0;
		        }
		        //实收本金特殊处理
	        	else if(val=="prinAmt"){
		        	sumAmount =JoyinMath.doAdd( sumAmount,finAssetIntInfosi.prinAmtTheory)||0;
		        }
		        //实收罚息特殊处理
	        	else  if(val=="penaltyIntamt"){
		        	sumAmount =JoyinMath.doAdd( sumAmount,finAssetIntInfosi.theoryPenalty)||0;
		        }else {
		        	sumAmount = JoyinMath.doAdd(sumAmount, finAssetIntInfosi[itemMapval]);
		        }
	        }
//	      	所有的钱
	        var itemObj = $('#'+val).val()||0;
	        /*//实收本息和特殊处理
	        if(val=="tradeAmt"){
	        	itemObj =JoyinMath.doAdd($("#prinAmtTheory").val(), JoyinMath.doAdd($("#theoryInt").val(), $("#theoryPenalty").val()))||0;
	        }
	        //实收本金特殊处理
	        if(val=="prinAmt"){
	        	itemObj =$("#theoryPrin").val()||0;
	        }
	        //实收罚息特殊处理
	        if(val=="penaltyIntamt"){
	        	itemObj =$("#theoryPenaltyInt").val()||0;
	        }*/
//	      	已分配的钱
	        var sumObj = 0;
//			sumAmount不为零时 计算占比
	        if(sumAmount!=0){
//		      	分配
		        for (var i = 0; i < finAssetIntInfos.length; i++) {
//					初始化
		        	var finAssetIntInfosi = finAssetIntInfos[i];
//		          	最后一个
		        	if (i == finAssetIntInfos.length - 1) {
//		        		将剩余的钱放入对应值
		        		finAssetIntInfosi[val] = JoyinMath.doMinus(itemObj, sumObj);
		        	}
//		        	非最后一个
		        	else {
//		        		计算占比
		        		var prop = JoyinMath.doDiv(finAssetIntInfosi[itemMapval], sumAmount, 12, JoyinMath.ROUND_HALF_UP);
		        		if(val=="tradeAmt"||val=="channelFee"){
		        			//实收本息总额使用 未支付本金 未支付利息 理论罚息总额 之和计算
		        			prop = JoyinMath.doDiv(JoyinMath.doAdd(finAssetIntInfosi.prinAmtTheory, JoyinMath.doAdd(finAssetIntInfosi.theoryInt, finAssetIntInfosi.theoryPenalty)), sumAmount, 12, JoyinMath.ROUND_HALF_UP);
		        		}
		        		
//		        		根据占比处理所有的钱
//		        		计算当前需要的钱
	        			var thisNum = JoyinMath.doMul(itemObj, prop, 2, JoyinMath.ROUND_HALF_UP);
//		        		处理已分配的钱
	        			sumObj = JoyinMath.doAdd(sumObj, thisNum);
//		        		把钱放入对应数据
	        			finAssetIntInfosi[val] = thisNum;
		        	}
		        }
	        }else{
//		      	分配
		        for (var i = 0; i < finAssetIntInfos.length; i++) {
//					初始化
		        	var finAssetIntInfosi = finAssetIntInfos[i];
//		        	将剩余的钱放入对应值
		        	finAssetIntInfosi[val] = 0;
	        	}
	        }
            
        })
        
        repoInfoTable.clear(function () {
            $.each(finAssetIntInfos, function (i, v) {
                repoInfoTable.addRow(v);
            })
        });
    }
    
    
    /**
     * 页面初始化
     */
    var init = function () {
         $("#prinAmt").on("change",function(){
         	fpriceAmtCal()
         })
         
         $("#intAmt").on("change",function(){
         	fpriceAmtCal()
         })
         
         $("#channelFee").on("change",function(){
        	 fpriceAmtCal();
         })
         
         $("#tradeAmt").on("change",function(){
         	penaltyIntamtCal();
         })
    }
    function saveOrSubmit(operateType) {
        
        // 检查生成划款指令参数是否发生变化
        if (window.BussTrdTransferOrder['bussTrdTransferOrder'].isCalcConditionChanged()) {
            return;
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm');
        object.operateType = operateType;
	    if(childPortfolioInfo){
	    	object.childPortfolioInfo = new Array();
	        for (var key in childPortfolioInfo) {  
	        	$.map(childPortfolioInfo[key],function(i,v){
	        		object.childPortfolioInfo.push(i);
	        	})
	        }
	    }
	  //文件信息
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        Joyin.ajaxJson(basePath + 'invest/period/trdsecoverdueduration/TrdSecOverdueDuration002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/period/trdsecoverdueduration/TrdSecOverdueDuration002/save', object, "TrdSecOverdueDuration002_btn_save", "TrdSecOverdueDuration002", function () {
                        if (parent.TrdBondDuration001) {
                            parent.TrdBondDuration001.reloadTable();
                        }
                    });
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/period/trdsecoverdueduration/TrdSecOverdueDuration002/save', object, "TrdSecOverdueDuration002_btn_submit", "TrdSecOverdueDuration002", function () {
                        if (parent.TrdBondDuration001) {
                            parent.TrdBondDuration001.reloadTable();
                        }
                    });
                }
            });
        });
    }

    window.componentLoadCallback = function (isDone, pagePath, params) {
        if (isDone) {
            window.BussTrdTransferOrder['bussTrdTransferOrder'].setStlCommandInput(function () {
                var list;
            	list = new Array();
            	var finAssetIntInfos = repoInfoTable.getAllRows();
            	for(var i = 0; i < finAssetIntInfos.length; i++)
        		{	
                    var dealReal = JSON.parse(deal);
                    dealReal.tradeMarket=$("#tradeMarket").val();
                    dealReal.settleDate=$("#tradeDate").val();
                    dealReal.tradeAmt=finAssetIntInfos[i].tradeAmt;
                    dealReal.portfolioId=finAssetIntInfos[i].portfolioId;
                    dealReal.invAim=finAssetIntInfos[i].invAim;
            		list.push(dealReal);
        		}
                return {
                    'isTotalTrade':'Y'
                    ,'bankLendingType':null
                    ,'portfolioId':'dzh'
                    ,'jiaoyiType':'dzh'
                    ,'deal':list
                };
            }, true);
        }
    }
    /**
     * 画面加载完成
     */
    $(function () {
        init();
        $('#TrdSecOverdueDuration002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdSecOverdueDuration002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
    });
}());

