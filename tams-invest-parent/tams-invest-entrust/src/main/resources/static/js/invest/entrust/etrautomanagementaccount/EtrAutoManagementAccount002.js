(function(){

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var object = $.serializeToObject('#searchForm');
        if(pageModel != PAGE_MODEL_NEW){
            object.portfolioIdChange = $('#portfolioIdChange').val();
            object.modelStatus = '0';
            if(entrustId)object.entrustId=entrustId;//update zhangweiqi 2021-1-11 列表展示重复问题
            if($("#entrustDate").val())object.entrustDate=$("#entrustDate").val();//update zhangweiqi 2021-1-12 SNSTZJY-2549 自主专户投资指令，AAsfpjccs组合持仓在新建和修改页面显示不一致
            object.workflowId=workflowId;
        }


        return object;
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax : {
            url : basePath + 'invest/entrust/etrautomanagementaccount/EtrAutoManagementAccount002/findNewPage',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        // data: pageModel === PAGE_MODEL_NEW ? [] : PAGELIST,
        cols:[
            {field:'portfolioId', width:200, title: '组合代码',sortable: false}
            ,{field:'portfolioName', width:200, title: '组合名称',sortable: false}
            ,{field:'subjectNum', width:200, title: '持仓份额',sortable: false}
            ,{field:'portfolioAmt', width:200, title: '组合分配金额',edit: true, sortable: false}
            // ,{field:'operation', title: '操作' , type:'action',buttons: ['delt'] , width:'10%'}
        ]
    });


    var getriskdata = function () {
        var selectedItem=table.getAllRows();
        var selectedItemForm = $.serializeToObject('#searchForm');
        var finprodType2 = $('#finprodType2').val();
        var objects = new Array();
        if (selectedItem.length > 0) {
            
            for ( var index in selectedItem) {
                var item = selectedItem[index];
                var trdProductDeal = {};
                trdProductDeal.tradeAmt = selectedItemForm.entrustAmt;
                trdProductDeal.unitCprice  = item.portfolioAmt;
                var obj = {
                    tradeDirection : selectedItemForm.ps,
                    timePoint : MY_SUP_TIME_TYPE_02,
                    valDate : sysDate,
                    portfolioCode : item.portfolioId,
                    tradeAmount : item.portfolioAmt,
                    assetType : finprodType2,
                    assetCode :selectedItemForm.finprodId,
                    isEnabled : '01',
                    fullDataFlag : '1',
                    trdProductDeal : trdProductDeal,
                    counterId : null
                }
//                    // add by zhangrui1 交易对手影响风险试算 start
//                    obj.counterId = $('#bussEtrInvestPersonInfoDto_counterId').val();
//                    // add by zhangrui1 交易对手影响风险试算 end
                var object = {
                    tradeDirection : [ 'ps', 'EtrAutoManagementAccount002.ps' ],
                    portfolioCode : [ 'portfolioId', 'EtrAutoManagementAccount002.portfolioId' ],
                    tradeAmount : [ 'entrustAmt','EtrAutoManagementAccount002.portfolioAmt' ],
  //                  assetType : ['finprodType2','EtrAutoManagementAccount002.finprodType2'],
                    assetCode : [ 'finprodMarketId','EtrAutoManagementAccount002.finprodId'],
                    data : obj
                }
//                // add by zhangrui1 交易对手影响风险试算 start
//                object.counterId = ['counterId', 'BussEtrInvestPersonInfoDto.counterId'];
//                // add by zhangrui1 交易对手影响风险试算 end
                objects.push(object);
            }
            return objects;
        }
        return objects;
    }

    // add by zhangrui1 start
    //添加风险试算验证
    window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            var riskparam = getriskdata();
            if (pageModel==PAGE_MODEL_NEW){
                for(var index in riskparam){
                    var item = checkParam.data[index];
                    item.data =null;
                }
            }
            window.BaseRiskMathComm.getparam(riskparam);
        }
    }
    // add by zhangrui1 end

    function onSaveOrSubmitAction(operateType) {
        // add by zhangrui1 end
    	if(!$("#portfolioId").val()){
    		Joyin.error(JoyinUtil.getMessage("自主专户代码不能为空!"));
    		return ;
    	}
    	if(!$("#finprodMarketId").val()){
    		Joyin.error(JoyinUtil.getMessage("底层资产代码不能为空!"));
    		return ;
    	}
    	if(table.getAllRows().length==0){
    		Joyin.error(JoyinUtil.getMessage("自主专户没有组合持有!"));
    		return ;
    	}
        if(!$("#entrustAmt").val()){
            Joyin.error(JoyinUtil.getMessage("指令金额不可为空!"))
        }
        var objectRisk=table.getAllRows();
        var objectRiskForm = $.serializeToObject('#searchForm');
        var checkParamRisk = {};
        checkParamRisk.data = getriskdata();
        var assetType = $('#assetType').val();
        var paramRisk = [];
        if (checkParamRisk.data.length > 0) {
            for(var index in checkParamRisk.data){
                var item = checkParamRisk.data[index];
                item.data.ps = objectRiskForm.ps;
                item.data.valDate = sysDate;
                item.data.portfolioId = objectRisk[index].portfolioId;
                item.data.finprodId = objectRiskForm.finprodId;
                item.data.assetCode = objectRiskForm.finprodId;
                item.data.entrustAmt = objectRisk[index].portfolioAmt;
                item.data.entrustNum = objectRisk[index].portfolioAmt;
                item.data.counterId = null;
                item.data.eventType='0';
                item.data.assetType= assetType;
                paramRisk.push(item.data);
            }
        }
        window.BaseRiskMathComm.initBaseRiskMathRedisEtr(basePath + 'riskrds/interf/batchRiskTrialRds/0/'+assetType, paramRisk, checkParamRisk);
        if($('#riskTrialSpan').html()==''||$('#riskTrialSpan').html() == ("&nbsp;&nbsp;"+JoyinUtil.getLabel('BaseRiskMathComm.noclusion'))){
            return;
        }



        var object = $.serializeToObject('#searchForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        if (pageModel == PAGE_MODEL_NEW) {
            object.operateType = 'submit';
        } else if (pageModel == PAGE_MODEL_EDIT) {
            object.operateType = 'update';
        }
        object.finprodId = $('#finprodId').val();
        object.finprodType2 = $('#finprodType2').val();
        object.type6 = $('#type6').val();
        object.type7 = $('#type7').val();
        object.portfolioIdChange = $('#portfolioIdChange').val();

        var rows=table.getAllRows();
        var selectedItem = []
        var entrustAmt = $('#entrustAmt').val();
        var sumportfolioAmt = 0;
        for(var i = 0; i< rows.length; i++){
            sumportfolioAmt += Number(rows[i].portfolioAmt);
            var data = {};
            $.extend(data,object);
            $.extend(data,rows[i]);
            data.entrustAmt=data.portfolioAmt;//update zhangweiqi 2021-01-07 修改金额不对问题
            data.entrustNum=data.entrustAmt;//update zhangweiqi 2021-01-07 修改金额不对问题
            selectedItem.push(data);
        }
        if(!(Number(entrustAmt) == Number(sumportfolioAmt))){
            Joyin.error(JoyinUtil.getMessage("组合分配金额加总与指令金额不同，无法提交"));
            return;
        }

        object.selectedItem = selectedItem;
        if(entrustId)object.entrustId=entrustId;//update zhangweiqi 2021-1-11 列表展示重复问题
        Joyin.ajaxJson(basePath + 'invest/entrust/etrautomanagementaccount/EtrAutoManagementAccount002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                WorkFlow.doSubmitList(basePath + 'invest/entrust/etrautomanagementaccount/EtrAutoManagementAccount002/save', object, "EtrAutoManagementAccount002_btn_submit");
            });
        });
    };


    //画面联动，资产代码带出资产名称
    $("#finprodMarketId").change(function(){
        if($("#finprodMarketId").val()){
            var object = $('#finprodMarketId').select2('data')[0].object;
            var finprodId = $('#finprodMarketId').select2('data')[0].col;

            $('#finprodId').val(finprodId);
            $('#finprodType2').val(object.finprodType2);
            $('#type6').val(object.type6);
            $('#type7').val(object.type7);
            $('#assetType').val(object.type8);
            Joyin.ajaxJson(basePath + 'invest/entrust/etrautomanagementaccount/EtrAutoManagementAccount002/finprodMarketIdChange', object, function(result) {
                if(result){
                    $('#type6Str').val(result.type6);
                    $('#type7Str').val(result.type7);
                }

                if(object.finprodType2 == 'F01' || object.finprodType2 == 'F10' || object.finprodType2 == 'F18'){
                    $('#ps').textSelect('setValue', 'T01');
                    $('#ps').prop('disabled',true);
                }else if(object.finprodType2 == 'F06'|| object.finprodType2 == 'F37'){
                    $('#ps').textSelect('setValue', 'T04');
                    $('#ps').prop('disabled',true);
                }else {
                    $('#ps').prop('disabled',false);
                    $('#ps').textSelect("setValue", "");
                }
            });
        }else {
            $('#type6').val('');
            $('#type7').val('');
            $('#assetType').val('');
            $('#type6Str').val('');
            $('#type7Str').val('');
            $('#ps').prop('disabled',false);
            // $('#ps').textSelect('setValue', "");
        }
    });

    $("#portfolioId").change(function(){
        var portfolioId = $('#portfolioId').val();
        var finprodId = $('#portfolioId').select2('data')[0].col;
        $('#portfolioIdChange').val(finprodId);
        table.table == null ? table.load():table.reload();
        if($("#entrustAmt").val()&& pageModel == PAGE_MODEL_NEW){
        	setTimeout( function(){$("#entrustAmt").trigger("change");},500 );
        	//SNSTZJY-2529 自主专户投资指令新建，填写信息后，切换了自主专户代码，组合分配金额没有自动算过来，还需要再次填写指令金额才可以自动计算
        }
        
    });

    $("#entrustAmt").change(function(){
        var entrustAmt = $('#entrustAmt').val();
        if(entrustAmt){
            var rows=table.getAllRows();
            var sumSubjectNum = 0;
            for(var i = 0; i< rows.length; i++){
                sumSubjectNum += Number(rows[i].subjectNum);
            }

            var portfolioAmtT = 0;
            var portfolioAmtTotal=0;
            for(var i = 0; i< rows.length; i++){
            	if(i<rows.length-1){
                    portfolioAmtT = (Number(rows[i].subjectNum) / Number(sumSubjectNum) * Number(entrustAmt)).toFixed(2);
                    portfolioAmtTotal=(Number(portfolioAmtTotal)+Number(portfolioAmtT)).toFixed(2);
            	}else{
            		portfolioAmtT= (Number(entrustAmt)-Number(portfolioAmtTotal)).toFixed(2);
            	}
                rows[i].portfolioAmt = portfolioAmtT;
                table.renderRow(rows[i].LAY_TABLE_INDEX,rows[i])
            }
            debugger;
        }
    });

    function init(){
        if(pageModel != PAGE_MODEL_NEW){
            $("#portfolioId").attr("disabled",true);
            $("#finprodMarketId").attr("disabled",true);
            $("#ps").attr("disabled",true);
            $("#portfolioId").attr("disabled",true);

            $('#type6Str').val(type6Str);
            $('#type7Str').val(type7Str);
            $('#portfolioIdChange').val(portfolioIdChange);

            if($('#portfolioId').val() && portfolioIdChange){
                table.table == null ? table.load():table.reload();
            }

        }
    }


    $(function() {
        init();
        // 提交按钮执行事件
        $('#EtrAutoManagementAccount002_btn_submit').click(function(){
            onSaveOrSubmitAction('submit');
        });
        $("#entrustAmt").change(function(){
            var entrustAmt = $('#entrustAmt').val();

        });

    });

}());