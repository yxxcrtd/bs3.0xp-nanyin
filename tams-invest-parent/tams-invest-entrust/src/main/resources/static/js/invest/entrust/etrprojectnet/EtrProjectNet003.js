/**
 * 权益类资产对多委托新建
 */
(function(){
    var getriskdata = function () {
        var selectedItem = $.serializeToObject('#searchForm').selectedItem;
        if (selectedItem.length > 0) {
            var list = [];
            var object = {
                tradeDirection: ['ps', 'EtrPlanAssetN002.ps'],
                portfolioCode: ['portfolioId', 'EtrPlanAssetN002.portfolioId'],
                tradeAmount: ['entrustAmt', 'EtrPlanAssetN002.entrustAmt'],
                assetType: ['assetType1', 'EtrPlanAssetN002.assetType1'],
                data: list
            };
            for (var index in selectedItem) {
                var item = selectedItem[index];
                var trdProductDeal = {};
                trdProductDeal.shareAmt = item.entrustNum;
                trdProductDeal.tradeAmt = item.entrustAmt;
                var obj = {
                    tradeDirection: item.ps,
                    timePoint: MY_SUP_TIME_TYPE_02,
                    valDate: sysDate,
                    portfolioCode: item.portfolioId,
                    tradeAmount: item.entrustAmt,
                    assetType: fintype,
                    isEnabled: '01',
                    assetCode: item.finprodId,
                    fullDataFlag: '1',
                    trdProductDeal: trdProductDeal
                };
                if (item.entrustNum) {
                    obj.tradeAmount = item.entrustNum;
                    obj.amtType = '1';
                } else {
                    obj.tradeAmount = item.entrustAmt;
                }
                if ($('#assetType2').val() && $('#assetType2').val() != '') {
                    obj.assetSecType = $('#assetType2').val();
                }
                obj.counterId = item.counterId;
                list.push(obj);
            }
            // add by zhangrui1 交易对手影响风险试算 start
            object.counterId = ['counterId', 'EtrPlanAssetN002.counterId'];
            // add by zhangrui1 交易对手影响风险试算 end
            return object;
        }
        /*if (selectedItem.length > 0) {
            var objects = new Array();
            for ( var index in selectedItem) {
                var item = selectedItem[index];
                var trdProductDeal = {};
                trdProductDeal.shareAmt  = item.entrustNum;
                trdProductDeal.shareAmt  = item.entrustAmt;
                var entrustAmt = item.entrustAmt;
                var entrustNum = item.entrustNum;
                if (entrustAmt) {
                    var obj = {
                        tradeDirection:item.ps,
                        timePoint: MY_SUP_TIME_TYPE_02,
                        valDate: sysDate,
                        portfolioCode:item.portfolioId,
                        tradeAmount: item.entrustAmt,
                        assetType: fintype,
                        isEnabled: '01',
                        assetCode:item.finprodId,
                        fullDataFlag : '1',
                        trdProductDeal : trdProductDeal
                    }
                    if ($('#assetType2').val() && $('#assetType2').val() != '') {
                        obj.assetSecType = $('#assetType2').val();
                    }
                    var object = {
                        tradeDirection: ['ps', 'EtrPlanAssetN002.ps'],
                        portfolioCode: ['portfolioId', 'EtrPlanAssetN002.portfolioId'],
                        tradeAmount: ['entrustAmt', 'EtrPlanAssetN002.entrustAmt'],
                        assetType: ['assetType1', 'EtrPlanAssetN002.assetType1'],
                        data: obj
                    }
                } else if (entrustNum) {
                    var obj = {
                        tradeDirection:item.ps,
                        timePoint: MY_SUP_TIME_TYPE_02,
                        valDate: sysDate,
                        portfolioCode: item.portfolioId,
                        tradeAmount: item.entrustNum,
                        assetType: fintype,
                        isEnabled: '01',
                        assetCode: item.finprodId,
                        amtType: '1',
                        fullDataFlag : '1',
                        trdProductDeal : trdProductDeal
                    }
                    if ($('#assetType2').val() && $('#assetType2').val() != '') {
                        obj.assetSecType = $('#assetType2').val();
                    }
                    var object = {
                        tradeDirection: ['ps', 'EtrPlanAssetN002.ps'],
                        portfolioCode: ['portfolioId', 'EtrPlanAssetN002.portfolioId'],
                        tradeAmount: ['entrustNum', 'EtrPlanAssetN002.entrustNum'],
                        assetType: ['assetType1', 'EtrPlanAssetN002.assetType1'],
                        data: obj
                    }
                } else {
                    var obj = {
                        tradeDirection:item.ps,
                        timePoint: MY_SUP_TIME_TYPE_02,
                        valDate: sysDate,
                        portfolioCode: item.portfolioId,
                        tradeAmount: item.entrustAmt,
                        assetType: fintype,
                        isEnabled: '01',
                        assetCode: item.finprodId,
                        fullDataFlag : '1',
                        trdProductDeal : trdProductDeal
                    }
                    if ($('#assetType2').val() && $('#assetType2').val() != '') {
                        obj.assetSecType = $('#assetType2').val();
                    }
                    var object = {
                        tradeDirection: ['ps', 'EtrPlanAssetN002.ps'],
                        portfolioCode: ['portfolioId', 'EtrPlanAssetN002.portfolioId'],
                        tradeAmount: ['entrustAmt', 'EtrPlanAssetN002.entrustAmt'],
                        assetType: ['assetType1', 'EtrPlanAssetN002.assetType1'],
                        data: obj
                    }
                }

            }
            return object;
        }*/
    }
    //初始化委托列表
    /**
     * 定义数据表格
     */
    var cols = [
        {field: 'indexId', title: 'EtrProjectNet003.indexId', sortable : false, edit: false, width: 100}
        ,{field : 'vdate', title : 'EtrPlanAssetN002.vdate', sortable : false, width : 100, edit : false,type : 'date' }
        ,{field : 'mdate', title : 'EtrPlanAssetN002.mdate', sortable : false, width : 100, edit : false, type : 'date'}
        ,{field:'portfolioId', width:100,  title: 'EtrPlanAssetN002.portfolioId',edit:false,sortable: false, required:true,
            editOption:{type:'select', id:'portfolioId', multiple: false, showField:'text',searchable: true,
                url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')}
        }
        ,{field:'finprodId', width:100,  title: 'EtrPlanAssetN002.finprodId',edit:false,sortable: false, required:true,
            editOption:{type:'select',id:'finprodId', multiple: false, showField:'text',searchable: true,
                url: JoyinUtil.getRealPath('/invest/entrust/etrprojectnet/EtrPlanAssetN002/getfinprodIdForAdd')}
            , visible: false}
        , {field: 'finprodMarketId', title: 'EtrPlanAssetN002.finprodId', sortable : false, width: 150}
        /*,{field:'assetType1',visible:false}*/
        ,{field:'busAssetType1', title: 'EtrProject001.type9', sortable : false, width:'10%',edit: false}
        //,{field:'busAssetType10', title: 'EtrProjectNet001.assetType2', sortable : false, width:'10%',edit: false,visible:false}
        ,{field:'assetType10',visible:false}
        ,{field:'busAssetType10', title: 'EtrProject001.type10', sortable : false, width:'10%',edit: false}
        ,{field:'assetType11',visible:false}
        ,{field:'busAssetType11', title: 'EtrProject001.type11', sortable : false, width:'10%',edit: false,visible:IS_SHOW_TYPE == '01'?true:false},{field:'ccy',visible:false}
       /* ,{field:'purSpeed', title: 'FinPlanNetAsset002.purSpeed', sortable : false, width:'10%',edit: false}
        ,{field:'redSpeed', title: 'FinPlanNetAsset002.redSpeed', sortable : false, width:'10%',edit: false}*/
        ,{field:'busCcy', title: 'EtrPlanAssetN001.ccy', sortable : false, width:'10%',sortField:"ccy"}
        ,{field:'ps', width:100, title: 'EtrPlanAssetN002.ps', sortable: false, required:true,
            edit:false,editOption:{type:'select',id:"ps",formatoption:true,result:PS}  , visible: false}
        , {field: 'psName', title: 'EtrPlanAssetN002.ps', sortable : false,width: 150}
        ,{field:'entrustAmt', width:100, title: 'EtrPlanAssetN002.entrustAmt', edit:false, sortable: false, required:true, type:'number', digit:2}
        ,{field:'entrustNum', width:100, title: 'EtrPlanAssetN002.entrustNum', edit:false, sortable: false, required:true, type:'number', digit:2}
        , {field: 'counterId', title: 'EtrProjectNet003.counterId', width: 120, edit: false,visible: false}
        , {field: 'counterIdName', title: 'EtrProjectNet003.counterId',sortable : false, width: 120,edit: false}
        ,{field:'assetAvailablePosition', width:100, title: 'EtrProjectNet003.assetAvailablePosition', edit:false, sortable: false,  type:'number', digit:2}
        ,{field:'proAvailableCash', width:100, title: 'EtrProjectNet003.proAvailableCash', edit:false, sortable: false,  type:'number', digit:2}
        , {field: 'remark', title: 'EtrProjectNet003.remark', width: 150}
        , {field: 'fileArray', title: 'EtrProjectNet003.fileArray', width: 150, visible: false}
        , {field: 'businessId', width: 150, visible: false}
        , {field: 'fileArrayName', title: 'EtrProjectNet003.fileArray', type: 'text', width: 150}
        , {
            field: 'operation1', title: 'SysCommon.operation', render: function (data, type, row, meta) {
                return '<a class="pro-link uploadFile" >上传附件</a> ';
            }, width: 150
        }
    ];

    var table =$("#dataTable").joyintable({
        data:etrPlanAssetNDtoList==null || etrPlanAssetNDtoList=='' ? null:JSON.parse(etrPlanAssetNDtoList),
        cols:cols,
        addbtn:true,
        addToLast:true,
        searchOnInit: true,
        page: false,
        checkbox: false,
        filter: false,
    });

    //绑定上传事件
    table.bind('click', '.uploadFile', function (data) {
        data.operateType = "edit";
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {};
        object.rowData = JSON.stringify(data);
        object.businessId = data.businessId;
        Joyin.dialog("上传附件",
            JoyinUtil.getRealPath("component/entrust/EtrListUploadFile/init"),
            "88%", "85%", object,
            function(result){
                var fileArrayName = '';
                if (result && result.fileArray && _.isArray(result.fileArray) && result.fileArray.length>0) {
                    for (var index in result.fileArray) {
                        fileArrayName += result.fileArray[index].srcName +'，';
                    }
                    result.fileArrayName = fileArrayName.substr(0,fileArrayName.length-1);
                }
                table.renderRow(data.LAY_TABLE_INDEX, result);
            });
    });
    /**
     * 保存或提交
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        //校验数据  校验表格
        var tableData = table.getAllRows();
        if (tableData.length == 0) {
            Joyin.alert("指令列表至少有一条数据！", 2);
            return false;
        }
        var s;
        if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm',true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        var objects = new Array();
        for(var index in object.selectedItem){
            var item = object.selectedItem[index];
            var temp = JSON.parse(JSON.stringify(object));
            temp.vdate = item.vdate;
            temp.mdate = item.mdate;
            temp.portfolioId = item.portfolioId;
            temp.finprodId = item.finprodId;
            temp.finprodMarketId = item.finprodMarketId;
            temp.assetType1 = item.assetType1;
            temp.assetType10 = item.assetType10;
            temp.assetType11 = item.assetType11;
            temp.ccy = item.ccy;
            temp.ps = item.ps;
            temp.entrustAmt= item.entrustAmt;
            temp.entrustNum  = item.entrustNum;
            temp.counterId  = item.counterId;
            temp.assetAvailablePosition  = item.assetAvailablePosition;
            temp.proAvailableCash  = item.proAvailableCash;
            temp.fileArray = item.fileArray;
            temp.counterId = item.counterId;
            temp.remark = item.remark;
            var etrInvestPersonInfo = {};
            etrInvestPersonInfo.invManagerId =item.invManagerId;
            etrInvestPersonInfo.room=item.room;
            etrInvestPersonInfo.counterId = item.counterId;
            temp.etrInvestPersonInfo = etrInvestPersonInfo;
            if (operateType === 'submit') {

                temp.elementId = 'EtrProjectNet002_btn_submit';
            }
            objects.push(temp);
        }

        var allDto = {};
        allDto.importFlow = true;
        //批量风险预占需要
        allDto.operateType = operateType;
        allDto.selectedItem = objects;
        allDto.riskTrialRetDto = object.riskTrialRetDto;
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', allDto, function(result1) {
            Joyin.ajaxJson(basePath + 'invest/entrust/etrprojectnet/EtrProjectNet003/check',allDto,
                function(result) {
                    Joyin.confirm(result.data,function() {
                        if (operateType === 'submit') {
                            WorkFlow.doSubmitList(basePath+'invest/entrust/etrprojectnet/EtrProjectNet003/save',allDto,"EtrProjectNet002_btn_submit","EtrProjectNet002");
                        }
                    });
                });
        })

    }
    /**
     * 绑定事件
     */
    $(function() {
        /**
         * 提交
         */
        $('#EtrProjectNet003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrProjectNet003_btn_riskcal').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            // //add by zhangnian  start
            // var list = $.serializeToObject('#searchForm', true).selectedItem;
            // var objectList = [];
            // for(var i in list){
            //     var temp = {};
            //     temp.portfolioId = list[i].portfolioId;
            //     temp.ps = list[i].ps;
            //     temp.ccy = list[i].ccy;
            //     temp.finprodId = list[i].finprodId;
            //     temp.entrustAmt = list[i].entrustAmt;
            //     temp.entrustNum = list[i].entrustNum;
            //     temp.groupCode = 'F19_0_'+i;//格式类型+委托/交易+序号
            //     temp.counterId = list[i].counterId;
            //     objectList.push(temp);
            // }
            // window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/batchRiskTrialRds/0/F19',
            //     objectList, getriskdata());
            //add by zhangnian end
            $("#riskMathFlag").val("02")
        });

    });

}());