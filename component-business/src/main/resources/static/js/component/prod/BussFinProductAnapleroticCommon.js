/**
 * 回补信息共通JS
 */
(function(){

    // 快速录入标志
    var quickFlag = false;

    //增加ignore 属性
    var buttons = [{ text: '&#xe642;',ignore:true, clas: 'edit', title: '编辑' },
        { text: '&#xe614;',ignore:true, clas: 'preview', title: '预览' },
        { text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }
    ];

    var isAnapleroticElement = function () {
        var selectedValueAnaplerotic = $('#'+getElementById('isAnapleroticElement').val()).val();
        if (selectedValueAnaplerotic) {
            // 是
            if (selectedValueAnaplerotic === 'Y') {
                getElementById('div').show();
            }
            else{
                getElementById('div').hide();
                if(bussFinProductAnapleroticInfoTable.table){
                    bussFinProductAnapleroticInfoTable.deleteRow(0);
                }
            }
        } else {
            getElementById('div').hide();
            if(bussFinProductAnapleroticInfoTable.table){
                bussFinProductAnapleroticInfoTable.deleteRow(0);
            }
        }
    }

    /**
     * 获取画面元素
     */
    function getElementById(id) {
        return $('#' + anapleroticCommonName + '_' + id);
    }

    var getSearchParams = function(){
        var data = {};
        data.quickFlag = quickFlag;
        if ($('#FinProductBuild002_quickFlag') && $('#FinProductBuild002_quickFlag').val() === 'N') {
            data.ptlId = $('#' + getElementById('ptlIdElement').val()).val();
            date.finprodId = $('#' + getElementById('ptlIdElement').val()).val();
        } else {
            data.workflowId = workflowId;
            data.finprodId = finAnapleroticFinprodId;
            data.entityName = anapleroticCommonName;
        }

        return data;
    }

    /**
     * 定义数据表格
     */
    var bussFinProductAnapleroticInfoTable = getElementById('anapleroticInfo').joyintable({
//    	ajax: {
//    		url: 'getAnapleroticInfo',
//    		type: 'POST',
//    		data: function() {
//                return getSearchParams();
//            }
//    	},
        searchOnInit: true,
        loadonce: false,
        rowno: true,
        page: false,
        checkbox:false,
//        addbtn: true,
        height : 200,
        data:finProductAnapleroticInfo==null?[]:finProductAnapleroticInfo,
        cols:[
            {field:'anapleroticType', visible : false}//回补形式
            ,{field:'anapleroticTypeStr', title: 'BussFinProductAnapleroticInfo.anapleroticType',sortable: false}//回补形式
            ,{field:'prodStyle', title: 'BussFinProductAnapleroticInfo.prodStyle',sortable: false, visible : $('#' + dealModeElement).val() == anapleroticDealMode11}//适用款型
            ,{field:'anapleroticRule', visible : false}//回补方式
            ,{field:'anapleroticRuleStr', title: 'BussFinProductAnapleroticInfo.anapleroticRule',sortable: false}//回补方式
//            ,{field:'anapleroticRatio', title: 'BussFinProductAnapleroticInfo.anapleroticRatio',sortable: false, visible : false}//回补比例
            ,{field:'anapleroticBasic', title: 'BussFinProductAnapleroticInfo.anapleroticBasic',sortable: false}//计费基础
//            ,{field:'anapleroticFrequency', visible : false}//回补规则
//            ,{field:'anapleroticFrequencyStr', title: 'BussFinProductAnapleroticInfo.anapleroticFrequency',sortable: false, visible : false}//回补规则
//            ,{field:'cycleTdayRule', visible : false}//周期营业日准则
//            ,{field:'cycleTdayRuleStr', title: 'BussFinProductAnapleroticInfo.cycleTdayRule',sortable: false, visible : false}//周期营业日准则
            ,{field:'firstAnapleroticDate', visible : false}//首次回补日
            ,{field:'anapleroticEndDate', visible : false}//回补结束日
            ,{field:'firstAnapleroticCycleBegDate', visible : false}//首次回补周期开始日
            ,{field:'firstAnapleroticDateStr', title: 'BussFinProductAnapleroticInfo.firstAnapleroticDate', visible : true, type:'date'}//首次回补日
            ,{field:'anapleroticEndDateStr', title: 'FinProductAnapleroticInfo.anapleroticEndDate', visible : true, type:'date'}//回补结束日
            ,{field:'firstAnapleroticCycleBegDateStr', title: 'FinProductAnapleroticInfo.firstAnapleroticCycleEndDate', visible : true , type:'date'}//首次回补周期开始日
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons,width:130}
            ,{field:'finProductAnapleroticInfoDto', visible : false}//model
            ,{field:'rateType', visible : false}
            ,{field:'rate', visible : false}
            ,{field:'indexId', visible : false}
            ,{field:'indexSource', visible : false}
            ,{field:'coefficient', visible : false}
            ,{field:'spreadRate', visible : false}
            ,{field:'resetType', visible : false}
        ],
        beforeEditCell : function(preVal, field, rowData){
            return true;
        },
        editCell : function(value, data, field, cell, column){
        },
        done : function(table, allData){

        }
    });


    /**
     * 绑定删除事件
     */
    bussFinProductAnapleroticInfoTable.bind('click', '.delt', function(data, index) {
        bussFinProductAnapleroticInfoTable.removeRow(data);
    });
    /**
     * 绑定修改事件
     */
    //传参时增加 发行方式 operationType : $('#' + getElementById('operationTypeElement').val()).val() add by wangcheng 20191101
    bussFinProductAnapleroticInfoTable.bind('click', '.edit', function(data, index) {
        debugger;
        Joyin.dialog(JoyinUtil.getLabel("FinProductAnapleroticInfo002.update"), JoyinUtil.getRealPath('/invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/init'),
            "98%", "95%", {pageModel:PAGE_MODEL_EDIT,applyType : 'prod',calendarId : $('#' + getElementById('dealCalIdElement').val()).val(), mdate : $('#' + getElementById('mdateElement').val()).val(), vdate : $('#' + getElementById('vdateElement').val()).val(), operationType : $('#' + getElementById('operationTypeElement').val()).val() , finProductAnapleroticInfoDto : data.finProductAnapleroticInfoDto,dealMode:$('#' + dealModeElement).val(),PROD_STYLE:PROD_STYLE,quickInputAnapleroticFlag:quickFlag},function(result) {
                if(!result) return;
                data.anapleroticId = result.anapleroticId;
                data.finprodMarketId = result.finprodMarketId;
                data.anapleroticType = result.anapleroticType;
                data.anapleroticTypeStr = result.anapleroticTypeStr;
                data.anapleroticRule = result.anapleroticRule;
                data.anapleroticRuleStr = result.anapleroticRuleStr;
//                    data.anapleroticRatio = result.anapleroticRatio;
                data.anapleroticBasic = result.anapleroticBasic;
//                    data.anapleroticFrequency = result.anapleroticFrequency;
//                    data.anapleroticFrequencyStr = result.anapleroticFrequencyStr;
//                    data.cycleTdayRule = result.cycleTdayRule;
//                    data.cycleTdayRuleStr = result.cycleTdayRuleStr;

                data.rateType = result.rateType;
                data.rate = result.rate;
                data.indexId = result.indexId;
                data.indexSource = result.indexSource;
                data.coefficient = result.coefficient;
                data.spreadRate = result.spreadRate;
                data.resetType = result.resetType;

                data.prodStyle = result.prodStyle;
                data.firstAnapleroticDate = result.firstAnapleroticDate;
                data.anapleroticEndDate = result.anapleroticEndDate;
                data.firstAnapleroticCycleBegDate = result.firstAnapleroticCycleBegDate;
                data.firstAnapleroticDateStr = result.firstAnapleroticDateStr;
                data.anapleroticEndDateStr = result.anapleroticEndDateStr;
                data.firstAnapleroticCycleBegDateStr = result.firstAnapleroticCycleBegDateStr;
                
                data.finProductAnapleroticInfoDto = JSON.stringify(result);
                bussFinProductAnapleroticInfoTable.renderRow(data.LAY_TABLE_INDEX, data);
                mdate2 =  $('#' + mdate).val();
            });
    });
    /**
     * 绑定查看事件
     */
    bussFinProductAnapleroticInfoTable.bind('click', '.preview', function(data, index) {
        Joyin.dialog(JoyinUtil.getLabel("FinProductAnapleroticInfo002"), JoyinUtil.getRealPath('/invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/init'),
            "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,applyType : 'prod', finProductAnapleroticInfoDto : data.finProductAnapleroticInfoDto})
    });
    //产品处理模式影响列表控制
    var parentAnapleroticDealModeChange = function (){
        var anapleroticDealMode = $('#' + dealModeElement).val();
        if(!bussFinProductAnapleroticInfoTable.table){
            bussFinProductAnapleroticInfoTable.load();
        }
        else{
            bussFinProductAnapleroticInfoTable.clear();
        }
        if (anapleroticDealMode){
            if (anapleroticDealMode == anapleroticDealMode11){
                bussFinProductAnapleroticInfoTable.showColumns([{field:'prodStyle',visible: true}]);
            }
            else {
                bussFinProductAnapleroticInfoTable.showColumns([{field:'prodStyle',visible: false}]);
            }
        }
        else{
            bussFinProductAnapleroticInfoTable.showColumns([{field:'prodStyle',visible: false}]);
        }
    }
    //不生成回补计划，无需校验
    var chkAnapleroticPlan=function(){
//		if(mdate2&&mdate2!=  $('#' + mdate).val()){
//			Joyin.error(JoyinUtil.getMessage("到期日已经改变,请重新生成回补计划"));
//		    return false;
//		}
        return true;
    }
    // 公开接口
    window.BussFinProductAnapleroticCommon = {
        table :  bussFinProductAnapleroticInfoTable,
        anapleroticInfo : anapleroticInfo,
        chkAnapleroticPlan : chkAnapleroticPlan
    };
    function anapleroticInfo(input_finprodId,bussFinProductAnapleroticInfo){
        finAnapleroticFinprodId = input_finprodId;
        quickFlag = true;
        if(bussFinProductAnapleroticInfo && bussFinProductAnapleroticInfo.length>0){
            for(var i=0;i< bussFinProductAnapleroticInfo.length;i++){
                bussFinProductAnapleroticInfo[i].finProductAnapleroticInfoDto = bussFinProductAnapleroticInfo[i].finProductAnapleroticInfoDtoStr
            }
        }
        bussFinProductAnapleroticInfoTable.reload({data:bussFinProductAnapleroticInfo,url:null}) ;
    }

    $(function() {
        $('#' + dealModeElement).change(parentAnapleroticDealModeChange);
        // 新建按钮点击事件
        getElementById('btn_create').click(function() {
            var mdate = $('#' + getElementById('mdateElement').val()).val();
            var vdate = $('#' + getElementById('vdateElement').val()).val();
            var operationType = $('#' + getElementById('operationTypeElement').val()).val();
            var calendarId = $('#' + getElementById('dealCalIdElement').val()).val();

            var dealMode = $('#' + getElementById('dealModeElement').val()).val();
            var openMode = $('#' + getElementById('openModeElement').val()).val();

            if(null==mdate || "" == mdate){
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0057", JoyinUtil.getLabel("FinProductBuild002.mdate")));
                return;
            }
            if(null==vdate || "" == vdate){
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0057", JoyinUtil.getLabel("FinProductBuild002.vdate")));
                return;
            }
            if(null==calendarId || "" == calendarId){
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0057", JoyinUtil.getLabel("FinProductBuild002.calendarId")));
                return;
            }
            var anapleroticInfoList = bussFinProductAnapleroticInfoTable.getAllRows();
            if($('#' + dealModeElement).val() != anapleroticDealMode11 && anapleroticInfoList && anapleroticInfoList.length>0){
                Joyin.error(JoyinUtil.getMessage("REPORT_COMMON_E_0028", "回补信息"));
                return;
            }
            var anapleroticList = '';
            for(var m=0;m<anapleroticInfoList.length;m++){
                anapleroticList = anapleroticInfoList[m].prodStyle+',';
            }
            var dialogIndex = Joyin.dialog(JoyinUtil.getLabel("FinProductAnapleroticInfo002"),
                "/invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/init",
                "98%", "95%", {pageModel:PAGE_MODEL_NEW, applyType: 'prod', workflowId : workflowId, calendarId: calendarId ,vdate : vdate ,mdate : mdate,operationType:operationType,dealMode:$('#' + dealModeElement).val(),PROD_STYLE:PROD_STYLE,anapleroticList:anapleroticList}, function(data) {
                	if(!data) return;
                    // 新建回补 返回列
                    var row = {
                        anapleroticId : data.anapleroticId,
                        finprodMarketId : data.finprodMarketId,
                        anapleroticType : data.anapleroticType,
                        anapleroticTypeStr : data.anapleroticTypeStr,
                        anapleroticRule : data.anapleroticRule,
                        anapleroticRuleStr : data.anapleroticRuleStr,
//                               anapleroticRatio : data.anapleroticRatio,
                        anapleroticBasic : data.anapleroticBasic,
//                               anapleroticFrequency : data.anapleroticFrequkency,
//                               anapleroticFrequencyStr : data.anapleroticFrequencyStr,
//                               cycleTdayRule : data.cycleTdayRule,
//                               cycleTdayRuleStr : data.cycleTdayRuleStr,

                        prodStyle : data.prodStyle,

                        firstAnapleroticDate : data.firstAnapleroticDate,
                        anapleroticEndDate : data.anapleroticEndDate,
                        firstAnapleroticCycleBegDate : data.firstAnapleroticCycleBegDate,
                        firstAnapleroticDateStr : data.firstAnapleroticDateStr,
                        anapleroticEndDateStr : data.anapleroticEndDateStr,
                        firstAnapleroticCycleBegDateStr : data.firstAnapleroticCycleBegDateStr,
                        
                        
                        finProductAnapleroticInfoDto : JSON.stringify(data)
                    };
                    bussFinProductAnapleroticInfoTable.addRow(row);
                });
        });
        isAnapleroticElement();
        // 父画面是否回补联动事件
        $('#'+getElementById('isAnapleroticElement').val()).change(isAnapleroticElement);
    });
}());