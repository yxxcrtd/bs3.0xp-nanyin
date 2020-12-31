/**
 * 项目类委托新建
 */
(function() {
    var getriskdata = function () {
        var dataTableList = $.serializeToObject('#searchForm').dataTableList;
        if (dataTableList.length > 0) {
            var objects = new Array();
            for ( var index in dataTableList) {
                var item = dataTableList[index];
                var trdProductDeal = {};
                trdProductDeal.shareAmt  = item.entrustNum;
                trdProductDeal.shareAmt  = item.entrustAmt;
                var obj = {
                    tradeDirection: item.ps,
                    timePoint: MY_SUP_TIME_TYPE_02,
                    valDate: sysDate,
                    portfolioCode:item.portfolioId,
                    tradeAmount:item.entrustAmt,
                    assetType: fintype,
                    isEnabled: '01',
                    assetCode: item.finprodId,
                    fullDataFlag : '1',
                    trdProductDeal : trdProductDeal
                }
                if ($('#assetType1').val() && $('#assetType1').val() != '') {
                    obj.assetSecType = $('#assetType1').val();
                }
                var object = {
                    tradeDirection: ['ps', 'EtrProject002.ps'],
                    portfolioCode: ['portfolioId', 'EtrProject002.portfolioId'],
                    tradeAmount: ['entrustAmt', 'EtrProject002.entrustAmt'],
                    assetType: ['assetType1', 'EtrProject002.assetType1'],
                    data: obj
                }
            }
                return object;
            }
    }

    var cols = [
         {field: 'indexId', title: 'EtrPlanAssetN003.indexId',sortable : false, edit: false, width: 100}
        ,{field : 'vdate', title : 'EtrProject002.vdate', sortable : false, width : 100, edit : false, type : 'date'}
        ,{field : 'mdate', title : 'EtrProject002.mdate', sortable : false, width : 100, edit : false, type : 'date'}
        ,{field:'portfolioId', width:100,  title: 'EtrProject002.portfolioId',edit:false,sortable: false, required:true,
            editOption:{type:'select', id:'portfolioId', multiple: false, showField:'text',searchable: true,
                url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
            }
        }
        ,{field:'finprodId', width:100,  title: 'EtrProject002.finprodId',edit:false,sortable: false, required:true,
            editOption:{type:'select',id:'finprodId', multiple: false, showField:'text',searchable: true,
                url: JoyinUtil.getRealPath('/invest/entrust/etrproject/EtrProject002/getfinprodIdForAdd')
            },visible:false
        }
        , {field: 'finprodMarketId', title: 'EtrProject002.finprodId', sortable : false,width: 150}
        // ,{field:'finprodName', title: 'EtrPlanAssetN002.finprodName', edit:true, sortable: false, required:true, type:'text'}
        ,{field:'type9', width:100,  title: 'EtrProject002.type9',edit:false,sortable: false, required:true,visible:false,
            editOption:{type:'select',id:'type9',formatoption:true, result:CUSTOM_ASSET_TYPE_TWO}
        }
        ,{field:'type9Name', title: 'EtrProject001.type9', sortable : false,width:'10%'}
        ,{field:'type10Name', title: 'EtrProject001.type10', sortable : false,width:'10%'}
        ,{field:'type11Name', title: 'EtrProject001.type11', sortable : false,width:'10%',visible:IS_SHOW_TYPE == '01'?true:false}
        ,{field:'type10', width:100,  title: 'EtrProject002.type10',edit:false,sortable: false, required:true,visible:false,
            editOption:{type:'select',id:'type10',formatoption:true, result:CUSTOM_ASSET_TYPE_THREE}
        }
        ,{field:'type11', width:100,  title: 'EtrProject002.type11',edit:false,sortable: false, required:true,visible:false,
            editOption:{type:'select',id:'type10',formatoption:true, result:CUSTOM_ASSET_TYPE_FOUR}
        }
        ,{field:'ccy',visible:false}
        ,{field:'busCcy', title: 'EtrProject001.ccy',sortable : false, width:'10%',sortField:"ccy"}
        ,{field:'ps', width:100, title: 'EtrProject002.ps', sortable: false, required:true,
            edit:false,editOption:{type:'select',id:"ps",formatoption:true,result:PS} ,visible:false}
        , {field: 'psName', title: 'EtrProject002.ps',sortable : false, width: 150}
        ,{field:'entrustAmt', width:100, title: 'EtrProject002.entrustAmt', edit:false, sortable: false, required:true, type:'number', digit:2}
       ,{field:'chlId', width:100, title: 'invest.channelId', edit:false, sortable: false, required:true,visible:false}
        ,{field:'chlIdName', width:100, title: 'invest.channelId', edit:false, sortable: false, required:true}
        ,{field: 'counterId', title: 'EtrProject003.counterId', width: 120, edit: false,visible: false}
        ,{field: 'counterIdName', title: 'EtrProject003.counterId', width: 120,edit: false}
        ,{field:'prinAmt', width:100, title: 'EtrProject003.prinAmt', edit:false, sortable: false, required:true, type:'number', digit:2}
        ,{field:'settleSpeed', width:100,  title: 'EtrProject003.settleSpeed',edit:false,sortable: false, required:true,
            editOption:{type:'select',id:'settleSpeed',formatoption:true, result:ACCOUNT_SPEED}
        }
        ,{field:'investRate', width:100, title: 'EtrProject003.investRate', edit:false, sortable: false, required:true, type:'number', digit:2}
        ,{field:'assetAvailablePosition', width:100, title: 'EtrProject002.assetAvailablePosition', edit:false, sortable: false,  type:'number', digit:2}
        ,{field:'proAvailableCash', width:100, title: 'EtrProject002.proAvailableCash', edit:false, sortable: false,  type:'number', digit:2}
        , {field: 'remark', title: 'EtrProject003.remark', width: 150}
        , {field: 'fileArray', title: 'EtrPlanAssetN003.fileArray', width: 150, visible: false}
        , {field: 'businessId', width: 150, visible: false}
        , {field: 'fileArrayName', title: 'EtrPlanAssetN003.fileArray', type: 'text', width: 150}
        , {
            field: 'operation1', title: 'SysCommon.operation', render: function (data, type, row, meta) {
                return '<a class="pro-link uploadFile" >上传附件</a> ';
            }, width: 150
        }
    ];

    var table =$("#dataTable").joyintable({
        data:etrProjectDtoList==null || etrProjectDtoList=='' ? null:JSON.parse(etrProjectDtoList),
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
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm',true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        var objects = new Array();
        for(var index in object.dataTableList){
            var item = object.dataTableList[index];
            var temp = JSON.parse(JSON.stringify(object));
            
            // 指令授信 start
            temp.finprodType2 = 'F18';
            temp.inputValue1 = 'F06';
            temp.counterId = item.counterId;
            temp.tradeAmt = item.entrustAmt;
            temp.prinAmt = item.entrustAmt;
            temp.tradeDate = item.vdate;
            // 指令授信 end
            
            temp.vdate = item.vdate;
            temp.mdate = item.mdate;
            temp.portfolioId = item.portfolioId;
            temp.finprodId = item.finprodId;
            temp.finprodMarketId = item.finprodMarketId;
            temp.type9 = item.type9;
            temp.type10 = item.type10;
            temp.type11 = item.type11;
            temp.ccy = item.ccy;
            temp.ps = item.ps;
            temp.entrustAmt= item.entrustAmt;
            temp.entrustNum  = item.entrustNum;
            temp.counterId  = item.counterId;
            temp.prinAmt  = item.prinAmt;
            temp.settleSpeed  = item.settleSpeed;
            temp.investRate  = item.investRate;
            temp.chlId =item.chlId;
            temp.assetAvailablePosition  = item.assetAvailablePosition;
            temp.proAvailableCash  = item.proAvailableCash;
            temp.fileArray = item.fileArray;
            temp.counterId = item.counterId;
            temp.invManagerId=item.invManagerId;
            var etrInvestPersonInfo = {};
            etrInvestPersonInfo.invManagerId=item.invManagerId;
            etrInvestPersonInfo.counterId = item.counterId;
            etrInvestPersonInfo.room=item.room;
            temp.etrInvestPersonInfo = etrInvestPersonInfo;
            if (operateType === 'submit') {
                //     temp.elementId = 'EtrPlanAssetN003_btn_save';
                // }else {
                temp.elementId = 'EtrProject002_btn_submit';
            }
            if(object.riskTrialRetDto[index])
                temp.riskTrialRetDto = [object.riskTrialRetDto[index]];
            objects.push(temp);
        }
        Joyin.ajaxJson(basePath + 'invest/entrust/etrproject/EtrProject003/check',objects,
          function(result) {
              Joyin.confirm(result.data,function() {
                   if (operateType === 'save') {
                       WorkFlow.doSaveList(basePath+'invest/entrust/etrproject/EtrProject003/save',objects,"EtrProject002_btn_save");
                   } else {
                       WorkFlow.doSubmitList(basePath+'invest/entrust/etrproject/EtrProject003/save',objects,"EtrProject002_btn_submit","EtrProject002");
                   }
               });
           });
    }

    //添加风险试算验证
    // window.componentLoadCallback = function(isDone, pagePath, params) {
    //     if (isDone) {
    //         var riskparam = getriskdata();
    //         if (pageModel==PAGE_MODEL_NEW){
    //             riskparam.data =null;
    //         }
    //         window.BaseRiskMathComm.getparam(riskparam);
    //     }
    // }
    /**
     * 绑定事件
     */
    $(function() {

        var init = function(){
            $('#ps').change();
            if ($('#finprodId').val()){
            $('#finprodId').change();
        }
        }

        /**
         * 提交
         */
        $('#EtrProject003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrProject003_btn_riskcal').click(function(){
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            $("#riskMathFlag").val("02");
        });

        if(IS_SHOW_TYPE == '01'){
        	$('#assetType11Div').show();
        }else{
        	$('#assetType11Div').hide();
        }
        init();
    });
}());
