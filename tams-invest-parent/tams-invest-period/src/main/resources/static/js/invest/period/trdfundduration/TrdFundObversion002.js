(function() {
    var initFlag = true;
    var needInit = true;
    //份额总数
    var sumRegistShare;
    /**
     * 页面初始化
     */
    var calcConditionObjBackUp;
    repoInfoTableOption = {
        rowno : true,
        searchOnInit : true,
        checkbox : false,
        page : false,
        cols : [
        // 组合
        {
            field : 'portfolioId',
            width : "150",
            title : 'IntDetailDuration.portfolioId',
            sortable : false
        }
        // 组合名称
        , {
            field : 'portfolioName',
            width : "150",
            title : 'IntDetailDuration.portfolioName',
            sortable : false
        }
            , {
                field : 'secAcctId',
                visible : false
            }
        //证券托管户
        , {
                field : 'secAcctName',
                width : "150",
                title : 'IntDetailDuration.acctName',
                sortable : false
            }
            , {
                field : 'chIld',
                visible : false
            }
            //通道
            , {
                field : 'chIldName',
                width : "150",
                title : 'invest.channelId',
                sortable : false
            }
        // // 权益登记日
        // , {
        //     field : 'registDate',
        //     width : "200",
        //     title : 'IntDetailDuration.registDate',
        //     type : 'date',
        //     sortable : false
        // }
        // 折算前份额
        , {
            field : 'bconShareAmt',
            width : "150",
            title : 'IntDetailDuration.bconShareAmt',
            type : 'money',digit: 2,
            sortable : false
        }
        // 折算后份额
        , {
            field : 'aconShareAmt',
            width : "200",
            edit : true,
            title : 'IntDetailDuration.aconShareAmt',
            type : 'money', digit: 2,
            sortable : false
        }
        ,{field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['edit'],
                render: function (data, type, row, meta) {
                    //实际交易组合无子组合交易明细
                    if(row.investYesorno =='Y'){
                        return;
                    }
                    return '<i class="btn layui-icon detail" data-ignore="true" title="明细">&#xe60a;</i>';
                }
            }
            ],
        done : function() {
            if (!(typeof formTodoList === "undefined") && formTodoList) {
                calcConditionObjBackUp = tableInitData();
            }
            //统计份额总数
            sumRegistShare = 0;
            var allData = repoInfoTable.getAllRows();
            for (var i = 0; i < allData.length; i++) {
                sumRegistShare = JoyinMath.doAdd(sumRegistShare, allData[i].registShare);
            }

            var tradeAmt = $('#tradeAmt').val();
            if (isNotNull(tradeAmt)&&isNotNull(sumRegistShare)) {
                var assetType = $("#assetType").val();
                if(assetType == FUND_ASSET_TYPE_F01 && $('#bonusMode').val() == BONUS_MODE_02){
                    $("#qty").val(tradeAmt).trigger("blur");
                }
                else{
                    var unitCprice = JoyinMath.doDiv(tradeAmt, sumRegistShare, 12, JoyinMath.ROUND_HALF_UP);
                    $("#unitCprice").val(unitCprice);
                    $("#unitCprice").trigger("blur");
                }
            }

            if (!(typeof needInit === "undefined") && needInit) {
                resetTableData();
                needInit = false;
            }
        },
        data : []
    }
    if (finAssetIntInfoList && finAssetIntInfoList.length > 0) {
    	$.each(finAssetIntInfoList,function(i,v){
    		v.registDate = new Date(v.registDate).format();
    	})
        repoInfoTableOption.data = repoInfoTableOption.data
                .concat(finAssetIntInfoList);
    }
    /**
     * 定义数据表格
     */
    var repoInfoTable = $("#intDetailTable").joyintable(repoInfoTableOption);
    /**
     * 绑定编辑事件
     */
    repoInfoTable.bind('click', '.detail', function(data, index) {
        var param = {
            "portfolioId":data.portfolioId,
            "finprodMarketId":$('#finprodMarketId').val(),
            "dealDate": $('#conversionTime').val()?$('#conversionTime').val():new Date(data.registDate).format(),
            "bizCode":$("#totleTradeId").val(),
            "workflowId":typeof workflowId == "undefined" ? '':workflowId,
            "isAdvance":"N",
            "defaultMap":JSON.stringify({
                "unitCprice":$("#unitCprice").val()
            }),
            "subAconShareAmt":data.aconShareAmt?data.aconShareAmt:0,
            "preMap":JSON.stringify({
                "registShare":data.registShare,
                "tradeAmt":data.tradeAmt,
                "qty":data.qty}),
            cols:JSON.stringify([
                // 折算前份额
                {
                    field : 'bconShareAmt',
                    width : "150",
                    title : 'IntDetailDuration.bconShareAmt',
                    type : 'money',digit: 2,
                    sortable : false
                }
                // 折算后份额
                , {
                    field : 'aconShareAmt',
                    width : "200",
                    edit : false,
                    title : 'IntDetailDuration.aconShareAmt',
                    type : 'money', digit: 2,
                    sortable : false
                }
            ])
        };
        Joyin.dialog('子组合折算明细信息', JoyinUtil.getRealPath('invest/period/common/ChildPortfolioInfo/init'), '80%', '80%',param);
    });
    /**
     * 利息明细初始化
     */
    var dealIntDetail = function() {
        var finprodMarketId = $('#finprodMarketId').val();
        var conversionTime = $('#conversionTime').val();
        if (finprodMarketId && conversionTime) {
            var param = {
                finprodMarketId : finprodMarketId,
                conversionTime : conversionTime
                ,type:'obversion'
            };
            Joyin.ajaxSync(JoyinUtil.getRealPath('/invest/period/trdfundduration/TrdFundObversion002/findAssetIntInfo'),param, function(result) {
                                if (result.data) {
                                    finAssetIntInfoList = result.data;
                                    /* 计算 数量总数 */
                                    sumRegistShare = 0;
                                    for (var i = 0; i < finAssetIntInfoList.length; i++) {
                                        sumRegistShare = JoyinMath.doAdd(sumRegistShare, finAssetIntInfoList[i].registShare);
                                    }
                                }
                            });
        } else {
            finAssetIntInfoList = [];
        }
        resetTableData();
    }
    /**
     * 表格联动
     */
    var resetTableData = function() {
        // 利息明细根据基础数据联动
        var finAssetIntInfos = finAssetIntInfoList;
        if(!finAssetIntInfos){
        	return;
        }
//      分配
        for (var i = 0; i < finAssetIntInfos.length; i++) {
            if($('#conversionType').val()=='01'||$('#conversionType').val()=='02'){//货币基金份额折算或ETF份额折算
                if($('#conversionRat').val()){
                    finAssetIntInfos[i].aconShareAmt = JoyinMath.doMul(finAssetIntInfos[i].bconShareAmt,$('#conversionRat').val(),2);
                }
            }
        }
        repoInfoTable.clear(function() {
            $.each(finAssetIntInfos, function(i, v) {
                repoInfoTable.addRow(v);
            });
        });
    }
    function saveOrSubmit(operateType) {
        // 检查生成划款指令参数是否发生变化
        genCallback(operateType);
    }

    function genCallback(operateType) {
        var isReturn = false;
        if($('#conversionType').val()&&$('#conversionType').val()=='02'){
            if(!$('#conversionRat').val()){
                Joyin.warn("折算类型为ETF的时候，折算比例不能为空！");
                return;
            }
        }
        if(repoInfoTable.getAllRows().length>0){
            for (var i = 0; i < repoInfoTable.getAllRows().length; i++) {
                if(!repoInfoTable.getAllRows()[i].aconShareAmt){
                    isReturn = true;
                    Joyin.warn("折算明细中第"+(i+1)+"行的折算后份额不能为空值");
                    break;
                }
            }
        }
        if(isReturn){
            return;
        }
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm', true);
        object.registShare = sumRegistShare;
        object.operateType = operateType;
        //        新建打开为00
        if (object.type == '00') {
            if (object.assetType == FUND_ASSET_TYPE_F01) {
                //            	现金分红为05（分红除权），红利再投为01
                object.type = object.bonusMode == BONUS_MODE_01?'05':'01';
            }
            else {
                // 非货币除权登记
                object.type = '02';
            }
            // 其他场景已近在001打开画面时处理
        }
        if (FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        if(repoInfoTable.getAllRows().length>0){
            var totalAmount = 0;
            for (var i = 0; i < repoInfoTable.getAllRows().length; i++) {
                totalAmount = JoyinMath.doAdd(totalAmount,
                    JoyinMath.doMinus(repoInfoTable.getAllRows()[i].aconShareAmt,repoInfoTable.getAllRows()[i].bconShareAmt,2),2);
            }
            object.totalAmount = totalAmount;
        }
        Joyin.ajaxJson(basePath + 'invest/period/trdfundduration/TrdFundObversion002/check', object, function(result) {
            Joyin.confirm(result.data,function() {
                // 非货币基金的现金分红，红利再投
                if (object.type == '03'||object.type == '06') {
                    var elementId = 'TTrdFundObversion002_btn_total';
                }
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/period/trdfundduration/TrdFundObversion002/save', object, isNotNull(elementId) ? elementId
                        : "TrdFundObversion002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/period/trdfundduration/TrdFundObversion002/save', object, isNotNull(elementId) ? elementId
                        : "TrdFundObversion002_btn_submit");
                }
            });
        });
    }

    var triValueChange = function(id, data) {
        if (data) {
            $('#' + id).val(data).trigger("change").trigger('blur');
        } else if (!initFlag) {
            $('#' + id).val('').trigger("change");
        } else {
            $('#' + id).trigger("change").trigger('blur');
        }
    };
    // 判空处理
    var isNotNull = function(value) {
        return value && value != "";
    };
    var init = function() {
        // 资产类型
        initFlag = false;
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 1.资产代码下拉框选择后，联动带出 币种、资产类型、资产名称、金融产品代码、发行人
        $('#finprodMarketId').change(function() {
                var finprodMarketId = $('#finprodMarketId').val();
                if (finprodMarketId) {
                    Joyin.ajax(basePath + "invest/period/trdfundduration/TrdFundDuration002/finprodMarketIdLink", {finprodMarketId : finprodMarketId},
                                    function(result) {
                                        if (result.data) {
                                            triValueChange('finprodMarketName',result.data.FINPRODMARKETNAME);
                                            triValueChange('finprodId', result.data.FINPRODID);
                                            triValueChange('issuer', result.data.ISSUER);
                                            triValueChange('ccy', result.data.CCY);
                                            triValueChange('assetType', result.data.ASSETTYPE);
                                        }
                                    });
                } else {
                    triValueChange('finprodMarketName');
                    triValueChange('finprodId')
                    triValueChange('issuer');
                    triValueChange('ccy');
                    triValueChange('assetType');
                }
                dealIntDetail();
            });

        $('#conversionTime').change(function () {
            dealIntDetail()
        });
        $('#conversionRat').change(function () {
            if($(this).val()&&repoInfoTable.getAllRows().length>0){
                for (var i = 0; i < repoInfoTable.getAllRows().length; i++) {
                    var rowData = {};
                    if($('#conversionType').val()=='01'||$('#conversionType').val()=='02'){//货币基金份额折算或ETF份额折算
                        if($(this).val()){
                            rowData.aconShareAmt = JoyinMath.doMul(repoInfoTable.getAllRows()[i].bconShareAmt,$(this).val(),2);
                            repoInfoTable.renderRow(i, rowData);
                        }
                    }
                }
            }
        });
        // 5.基金类型为货币基金时，分行类型 默认为红利再投，不可改
        $('#conversionType').change(function () {
            if($(this).val()=='02'){
                $('#conversionRat').attr("required",true);
                $('#conversionRat').parent().parent().find('.layui-form-label').append('<i class="must">*</i>')
            }else{
                $('#conversionRat').attr("required",false);
                $('#conversionRat').parent().parent().find('.layui-form-label').find('.must').remove();
            }
            $('#conversionRat').val('').trigger('change');
        });
        $('#TrdFundObversion002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdFundObversion002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        setTimeout(function(){init();},0);
    });
}());
