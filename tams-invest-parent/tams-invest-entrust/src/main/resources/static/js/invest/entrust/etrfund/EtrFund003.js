/**
 * 基金多对多指令
 */
(function() {
    var getriskdata = function () {
        var selectedItem = $.serializeToObject('#searchForm').selectedItem;
        if (selectedItem.length > 0) {
            var objects = new Array();
            for ( var index in selectedItem) {
                var item = selectedItem[index];
                var trdProductDeal = {};
                trdProductDeal.tradeAmt = item.entrustAmt;
                trdProductDeal.unitCprice  = item.price;
                trdProductDeal.shareAmt  = item.entrustNum;
                if (item.ps == T04) {
                    var obj = {
                        tradeDirection : item.ps,
                        timePoint : MY_SUP_TIME_TYPE_02,
                        valDate : sysDate,
                        portfolioCode : item.portfolioId,
                        tradeAmount : item.entrustAmt,
                        assetType : fintype,
                        isEnabled : '01',
                        assetCode : item.finprodId,
                        fullDataFlag : '1',
                        trdProductDeal : trdProductDeal
                    }
                    var object = {assetCode: ['finprodMarketId', 'EtrFund001.finprodId'],tradeDirection:['ps','EtrFund001.ps'],portfolioCode:['portfolioId','EtrFund001.portfolioId'],tradeAmount:['entrustAmt','EtrFund001.entrustAmt'],assetType:['assetType1','EtrFund001.finprodType2'],data:obj}
                } else if (item.ps == T06) {
                    var obj = {
                        tradeDirection : item.ps,
                        timePoint : MY_SUP_TIME_TYPE_02,
                        valDate : sysDate,
                        portfolioCode : item.portfolioId,
                        tradeAmount : item.entrustNum,
                        assetType : fintype,
                        isEnabled : '01',
                        assetCode : item.finprodId,
                        amtType:'1',
                        fullDataFlag : '1',
                        trdProductDeal : trdProductDeal
                    }
                    var object = {assetCode: ['finprodMarketId', 'EtrFund001.finprodId'],tradeDirection:['ps','EtrFund001.ps'],portfolioCode:['portfolioId','EtrFund001.portfolioId'],tradeAmount:['entrustNum','EtrFund001.entrustNum'],assetType:['assetType1','EtrFund001.finprodType2'],data:obj}
                } else if(item.ps == T01){
                    var obj = {
                        tradeDirection : item.ps,
                        timePoint : MY_SUP_TIME_TYPE_02,
                        valDate : sysDate,
                        portfolioCode : item.portfolioId,
                        tradeAmount : item.entrustAmt,
                        assetType : fintype,
                        isEnabled : '01',
                        assetCode : item.finprodId,
                        fullDataFlag : '1',
                        trdProductDeal : trdProductDeal
                    }
                    var object = {assetCode: ['finprodMarketId', 'EtrFund001.finprodId'],tradeDirection:['ps','EtrFund001.ps'],portfolioCode:['portfolioId','EtrFund001.portfolioId'],tradeAmount:['entrustAmt','EtrFund001.entrustAmt'],assetType:['assetType1','EtrFund001.finprodType2'],data:obj}
                }else if(item.ps == T02){
                    var obj = {
                            tradeDirection : item.ps,
                            timePoint : MY_SUP_TIME_TYPE_02,
                            valDate : sysDate,
                            portfolioCode : item.portfolioId,
                            tradeAmount : item.entrustNum,
                            assetType : fintype,
                            isEnabled : '01',
                            assetCode : item.finprodId,
                            amtType:'1',
                            fullDataFlag : '1',
                            trdProductDeal : trdProductDeal
                        }
                        var object = {assetCode: ['finprodMarketId', 'EtrFund001.finprodId'],tradeDirection:['ps','EtrFund001.ps'],portfolioCode:['portfolioId','EtrFund001.portfolioId'],tradeAmount:['entrustNum','EtrFund001.entrustNum'],assetType:['assetType1','EtrFund001.finprodType2'],data:obj}
                }
                objects.push(object);
            }
            return objects;
        }
    }
  //初始化委托列表
    /**
     * 定义数据表格
     */
    var cols = [
    {
        field : 'indexId',
        title : 'EtrFund003.indexId',
        width : 200,
        sortField : 'indexId',
        sortable: false
    }// 序号
    , {
        field : 'portfolioId',
        title : 'EtrFund001.portfolioId',
        width : 200,
        sortField : 'portfolioId',
        sortable: false
    }// 组合代码
    , {
        field : 'vdate',
        title : 'EtrFund001.entrustVDate',
        width : 200,
        sortField : 'vdate',
        type : 'date',
        sortable: false
    }// 委托开始日
    , {
        field : 'mdate',
        title : 'EtrFund001.entrustMDate',
        width : 200,
        sortField : 'mdate',
        type : 'date',
        sortable: false
    }// 委托终止日
    , {
        field : 'ps',
        visible : false
    }, {
        field : 'psStr',
        title : 'EtrFund001.ps',
        width : 200,
        sortField : 'ps',
        sortable: false
    }// 交易方向
    , {
        field : 'price',
        title : 'EtrFund002.price',
        width : 200,
        sortField : 'price',
        type : 'money',
        sortable: false
    }// 交易价格
    , {
        field : 'finprodId',
        title : 'EtrFund001.finprodId',
        width : 200,
        sortField : 'finprodId',
        visible : false,
        sortable: false
    }// 资产代码
    , {
        field : 'finprodMarketId',
        title : 'EtrFund001.finprodId',
        width : 200,
        sortField : 'finprodMarketId',
        sortable: false
    }// 资产代码
    , {
        field : 'finprodName',
        title : 'EtrFund001.finprodName',
        width : 200,
        sortField : 'finprodName',
        sortable: false
    }// 资产名称
    , {
        field : 'assetType1Name',
        title : 'EtrInvestBondInfo001.type9',
        width : 120,
        sortField : 'assetType1',
        sortable: false
    }// 资产二级分类
    , {
        field : 'assetType1',
        title : 'EtrInvestBondInfo001.type9',
        width : 120,
        sortField : 'assetType1',
        visible : false,
        sortable: false
    }// 资产二级分类
    , {
        field : 'assetType10Name',
        title : 'EtrInvestBondInfo001.type10',
        width : 120,
        sortField : 'assetType10',
        sortable: false
    }// 资产三级分类
    , {
        field : 'assetType10',
        title : 'EtrInvestBondInfo001.type10',
        width : 120,
        sortField : 'assetType10',
        visible : false,
        sortable: false
    }// 资产三级分类
    , {
        field : 'assetType11Name',
        title : 'EtrInvestBondInfo001.type11',
        width : 120,
        sortField : 'assetType11',
        sortable: false,
        visible:IS_SHOW_TYPE == '01'?true:false
    }// 资产四级分类
    , {
        field : 'assetType11',
        title : 'EtrInvestBondInfo001.type11',
        width : 120,
        sortField : 'assetType11',
        visible : false,
        sortable: false
    }// 资产四级分类
    , {
        field : 'counterId',
        title : 'etrInvestPersonInfo.counterId',
        width : 120,
        sortField : 'counterId',
        visible : false,
        sortable: false
    }// 交易对手
    , {
        field : 'counterIdName',
        title : 'etrInvestPersonInfo.counterId',
        width : 120,
        sortable: false
    }// 交易对手名称
    , {
        field : 'tradeMarket',
        visible : false,
        sortable: false
    }, {
        field : 'tradeMarketStr',
        title : 'EtrFund002.tradePlat',
        width : 200,
        sortField : 'tradeMarket',
        sortable: false
    }// 交易场所
    , {
        field : 'secAcctId',
        title : 'EtrStock002.secAcctId',
        width : 200,
        sortField : 'secAcctId',
        visible : false,
        sortable: false
    }// 证券账户
    , {
        field : 'secAcctIdName',
        title : 'EtrStock002.secAcctId',
        width : 200,
        sortField : 'secAcctId',
        sortable: false
    }// 证券账户名称
    , {
        field : 'ccy',
        visible : false,
        sortable: false
    }, {
        field : 'ccyStr',
        title : 'EtrFund001.ccy',
        width : 200,
        sortField : 'ccy',
        sortable: false
    }// 币种
    , {
        field : 'priceModel',
        visible : false,
        sortable: false
    }, {
        field : 'entrustAmt',
        title : 'EtrFund001.entrustAmt',
        width : 200,
        sortField : 'entrustAmt',
        sortable: false
    }// 委托金额
    , {
        field : 'trmFundAbbr',
        title : 'EtrFund002.trmFundAbbr',
        width : 200,
        sortField : 'trmFundAbbr',
        sortable: false
    }// 交易所基金简称
    , {
        field : 'entrustNum',
        title : 'EtrStock001.entrustNum',
        width : 200,
        sortField : 'entrustNum',
        type : 'number',
        sortable: false
    }// 委托数量
    , {
        field : 'proAvailableCash',
        title : 'EtrStock003.proAvailableCash',
        width : 200,
        sortField : 'proAvailableCash',
        type : 'money',
        sortable: false
    }// 指令可用现金
    , {
        field : 'tradeMarketCash',
        title : 'EtrStock001.tradeCash',
        width : 200,
        sortField : 'tradeMarketCash',
        type : 'money',
        sortable: false
    }// 交易所资金账户可用现金
    , {
        field : 'assetAvailablePosition',
        title : 'EtrStock003.assetAvailablePosition',
        width : 200,
        sortField : 'assetAvailablePosition',
        type : 'number',
        sortable: false
    }// 指令可用持仓
    , {
        field : 'trmFundType',
        title : 'EtrFund002.trmFundType',
        width : 200,
        sortfield : 'trmFundType',
        sortable: false
    }// 交易所基金类型
    , {
        field : 'chlId',
        title : 'invest.channelId',
        width : 200,
        sortfield : 'chlId',
        visible : false,
        sortable: false  
    }// 通道
    , {
        field : 'chlIdName',
        title : 'invest.channelId',
        width : 200,
        sortfield : 'chlIdName',
        sortable: false
    }// 通道名称
    , {
        field : 'chlIdCash',
        title : function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("EtrFund003.chlIdCash");}(),
        width : 200,
        type  : 'money',
        sortfield : 'chlIdCash',
        sortable: false
    }// 通道可用现金
    , {
        field : 'remark',
        title : 'EtrStock003.remark',
        width : 200,
        sortfield : 'remark',
        sortable: false
    }// 备注
    , {
        field : 'changeToFinprodMarketId',
        title : 'EtrFund003.changeToFinprodMarketId',
        width : 200,
        sortfield : 'changeToFinprodMarketId',
        sortable: false
    }// 转入资产代码
    , {
        field : 'rFinprodId',
        visible : false,
        sortable: false  
    }// 转入金融产品代码
    , {
        field : 'changeToFinprodName',
        title : 'EtrFund003.changeToFinprodName',
        width : 200,
        sortfield : 'changeToFinprodName',
        sortable: false
    }// 转入资产名称
    , {
        field : 'fileArray',
        title : 'EtrStock003.fileArray',
        width : 150,
        visible : false,
        sortable: false
    }, {
        field : 'businessId',
        width : 150,
        visible : false,
        sortable: false
    }, {
        field : 'fileArrayName',
        title : 'EtrStock003.fileArray',
        type : 'text',
        width : 150,
        sortable: false
    }, {
        field : 'operation1',
        title : 'SysCommon.operation',
        sortable: false,
        render : function(data, type, row, meta) {
            return '<a class="layui-btn layui-btn-sm uploadFile" >上传附件</a> ';
        },
        width : 150
    }
    ]
    var table = $("#dataTable").joyintable(
            {
                data : etrFundDtoList == null || etrFundDtoList == '' ? null : JSON
                        .parse(etrFundDtoList),
                cols : cols,
                loadonce : false,
                page: false,
                filter: false,
                checkbox : false,
                height : 290,
                addbtn : true,

            });
    //绑定上传事件
    table.bind('click', '.uploadFile', function(data) {
        data.operateType = "edit";
        data.pageModel = PAGE_MODEL_EDIT;
        var object = {};
        object.rowData = JSON.stringify(data);
        object.businessId = data.businessId;
        Joyin.dialog("上传附件", JoyinUtil.getRealPath("component/entrust/EtrListUploadFile/init"),
                "88%", "85%", object, function(result) {
                    var fileArrayName = '';
                    if (result && result.fileArray && _.isArray(result.fileArray)
                            && result.fileArray.length > 0) {
                        for ( var index in result.fileArray) {
                            fileArrayName += result.fileArray[index].srcName + '，';
                        }
                        result.fileArrayName = fileArrayName.substr(0, fileArrayName.length - 1);
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
            Joyin.alert("委托列表至少有一条数据！", 2);
            return false;
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#searchForm', true);
        object.operateType = operateType;
        object.pageModel = pageModel;
        var objects = object.selectedItem;
        for (var index in objects) {
            var temp = objects[index];
            
            // 指令授信 start
            temp.finprodType2 = 'F06';
            temp.inputValue1 = 'F02';
            temp.tradeDate = temp.vdate;
            temp.counterId = temp.counterId;
            temp.tradeAmt = temp.entrustNum;
            temp.prinAmt = temp.entrustNum;
            if(temp.entrustAmt) {
            	temp.tradeAmt =temp.entrustAmt;
            	temp.prinAmt =temp.entrustAmt;
            }
            // 指令授信 end
            
            temp.operateType = operateType;
            temp.pageModel = pageModel;
            if (operateType === 'submit') {
                temp.elementId = 'EtrFund002_btn_submit';
            } 
        }
        var allDto = {};
        allDto.importFlow = true;
        allDto.selectedItem = objects;

        Joyin.ajaxJson(basePath + 'invest/entrust/etrfund/EtrFund003/check', allDto,
            function (result) {
                Joyin.confirm(result.data, function () {
                    if (operateType === 'submit') {
                        WorkFlow.doSubmitList(basePath + 'invest/entrust/etrfund/EtrFund003/save', allDto, "EtrFund002_btn_submit","EtrFund002");
                    } 
                });
            });
    }
    $(function() {
        if (etrFundDtoList) {
            $.each(JSON.parse(etrFundDtoList), function(id, value) {

            });

            table.reload({
                data : JSON.parse(etrFundDtoList)
            });
        }
        /**
         * 提交
         */
        $('#EtrFund003_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        $('#EtrFund003_btn_riskcal').click(function () {
            //window.BaseRiskMathComm.initBaseRiskMathCommPage(getriskdata());
            $("#riskMathFlag").val("02").trigger('change');
        });
    })

    var getriskdatas = function () {
        var mun=null;
        // var object = $.serializeToObject('#searchForm');
        // var bussCombDistribtList=null;
        // if(object!=null&&object!=""&&object.bussCombDistribtList!=null&&object.bussCombDistribtList!=""){
        //     bussCombDistribtList=object.bussCombDistribtList;
        // }

        //校验数据  校验表格
        var tableData = table.getAllRows();
        if (tableData.length == 0) {
            Joyin.alert("委托列表至少有一条数据！", 2);
            return false;
        }

        var list=[];
        for(var i in tableData){
            var item = tableData[i];
            var tradeAmount = null;
            if(item.ps == 'T06'){
                tradeAmount = item.entrustNum;
            }else{
                tradeAmount = item.entrustAmt;
            }

            var obj = {
                tradeDirection: item.ps,
                timePoint: MY_SUP_TIME_TYPE_02,
                valDate: sysDate,
                portfolioCode: item.portfolioId,
                tradeAmount: tradeAmount,
                assetType: fintype,
                assetSecType: item.assetType1,//$('#assetType1').val(),
                isEnabled: '01',
                assetCode: item.finprodId //$('#finprodIdhid').val()
            }
            list[i]=obj;
        }
        var object = {
            tradeDirection: ['ps', 'EtrInvestBondInfo002.ps'],
            portfolioCode: ['portfolioId', 'EtrInvestBondInfo002.portfolioId'],
            tradeAmount: ['entrustNum', 'EtrInvestBondInfo002.entrustAmt'],
            assetType: ['assetType1', 'EtrInvestBondInfo002.bondType'],
            data: list
        }
        return object;
    };

}());