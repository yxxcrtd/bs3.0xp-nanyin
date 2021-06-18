(function () {
    // 风险试算结果
    var riskResultdata;
    var initFlag = false;
    var cluflag;
    var businessId;

    /**
     * 画星星
     */
    var star = function () {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel !== PAGE_MODEL_REFERENCE)) {
            $('.must').each(function () {
                $(this).remove();
            })
            $('*[required]').each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }
    /**
     * 对外公开的接口
     */
    parent.EtrIbBondinfo003 = {
        riskResultdata: riskResultdata,
        initFlag: initFlag,
        cluflag: cluflag,
        getriskdata: getriskdata,
        doRiskcal: doRiskcal,
        getSubmitParam: getSubmitParam,
        doReset: doReset,
        getSearchParams: getSearchParams,
        businessId: businessId
    }

    EtrIbBondinfo003 = {
        riskResultdata: riskResultdata,
        initFlag: initFlag,
        cluflag: cluflag,
        getriskdata: getriskdata,
        doRiskcal: doRiskcal,
        getSubmitParam: getSubmitParam,
        doReset: doReset,
        getSearchParams: getSearchParams,
        businessId: businessId
    }

    function getSearchParams() {
        var object = $.serializeToObject("#searchForm");
        return object;
    }

    /**
     * 风险试算时不需要进行风险试算的校验
     */
    var isNeedRiskcal = function (){
        //质押券入库和出库不需要走风险试算
        ps = $("#ps").val();
        return true;
    }

    /**
     * 获取风险试算参数
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function getSearchParams() {
        var object = $.serializeToObject("#searchForm");
        return object;
    }

    var cols = [
        {
            /* 组合代码 */
            field: 'portfolioId', width: 100, title: 'EtribBondInfo003.portfolioId', edit: true, sortable: false,
            editOption: {
                type: 'select',
                id: 'portfolioId',
                /*childrenIds : 'portfolioId',*/
                multiple: false,
                showField: 'text',
                searchable: true,
                url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
            }
        }
       /* ,{
             组合代码 
            field: 'portfolioId', width: 100, title: 'EtribBondInfo003.portfolioIdChild', edit: true, sortable: false,
            editOption: {
                type: 'select',
                parentIds:'parentPortfolioId',
                id: 'portfolioId',
                multiple: false,
                showField: 'text',
                searchable: true,
                url: JoyinUtil.getRealPath('/business/common/CommonSelect/qryPortfolioIdChildByRequestParam')
            }
        }*/
        , {
        /*劵面总额*/
            field: 'entrustNum',
            width: 50,
            title: 'EtribBondInfo003.entrustAmt',
            edit: true,
            sortable: false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'proAvailableCash',
            width: 40,
            title: 'EtribBondInfo003.proAvailableCash',
            sortable: false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'entrustId',
            visible: false
        }
        , {
            field: 'proAvailableCash1',
            width: 40,
            title: 'EtribBondInfo003.proAvailableCash1',
            sortable: false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'secManageAvailablePosition',
            width: 40,
            title: 'EtribBondInfo003.secManageAvailablePosition',
            sortable: false,
            type: 'number',
            digit: 2,
            visible: ($("#chlFinprodId").val() == null || $("#chlFinprodId").val() == '')?true:false
        }
        , {
            field: 'secManageAvailablePosition1',
            width: 40,
            title: 'EtribBondInfo003.secManageAvailablePosition1',
            sortable: false,
            type: 'number',
            digit: 2,
            visible: ($("#chlFinprodId").val() == null || $("#chlFinprodId").val() == '')?true:false
        }
        , {
            field: 'channelAvailableCash',
            width: 40,
            title: 'EtribBondInfo003.channelAvailableCash', sortable: false, type: 'number', digit: 2,
            visible: ($("#chlFinprodId").val() == null || $("#chlFinprodId").val() == '')?false:true
        }
        , {
            field: 'channelAvailableCash1', width: 100, title: 'EtribBondInfo003.channelAvailableCash1'
            , sortable: false, type: 'number', digit: 2,
            visible: ($("#chlFinprodId").val() == null || $("#chlFinprodId").val() == '')?false:true
        }

        , {
            field: 'assetPositionEntrustAmt',
            width: 40,
            title: 'EtribBondInfo003.assetPositionEntrustAmt',
            sortable: false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'assetAvailableEntrustAmt',
            width: 40,
            title: 'EtribBondInfo003.assetAvailableEntrustAmt',
            sortable: false,
            type: 'number',
            digit: 2
        }
        , {field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['delt']}
    ]
    var table = $("#bussCombDistribtTable").joyintable({
        data:entity_BussCombDistribtList||[],
        cols: cols,
        page: true,
        addbtn: true,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        height: 290,
        addclick: function (row) {

        },
        editCell: function (value, data, field, cell, column) {
            if (field == "entrustNum") {
                refreshTradAmt();
            }
            if (field == "portfolioId") {
                var rowData = {};
                rowData.entrustNum = data.entrustNum;
                table.renderRow(data.LAY_TABLE_INDEX, rowData);
                if (value != null && value != '') {
                    /*var datas = {
                        portfolioId: data.portfolioId,
                        sysDateT1: $('#vdate').val(),
                        calId: $('#calendarId').val(),
                        chlFinprodId: $("#chlFinprodId").val(),
                        finprodId: $("#finprodIdhid").val()
                    };*/
                    
                    var datas = {
                    		portfolioId:data.portfolioId, 
                    		ccy:$('#ccy').val(), 
                    		sysDateT1:$('#vdate').val(), 
                    		calId:$('#calendarId').val(),
                    		chlFinprodId: $("#chlFinprodId").val()};
                    Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtribBondInfo003/getPortfolioAllocationValues', datas, function (result) {
                        if (result) {
                            if ($('#ps').val() == PS_T01) {
                                rowData.proAvailableCash = result.proAvailableCash;
                                rowData.proAvailableCash1 = result.proAvailableCashT1;
                                rowData.assetPositionEntrustAmt = ' ';
                                rowData.assetAvailableEntrustAmt = ' ';
                            } else if ($('#ps').val() == PS_T02 || $('#ps').val() == PS_T03) {
                                rowData.proAvailableCash = ' ';
                                rowData.proAvailableCash1 = ' ';
                                rowData.assetPositionEntrustAmt = result.assetPositionEntrustAmt;
                                rowData.assetAvailableEntrustAmt = result.assetAvailableEntrustAmt;
                            } else {
                                rowData.proAvailableCash = ' ';
                                rowData.proAvailableCash1 = ' ';
                                rowData.assetPositionEntrustAmt = ' ';
                                rowData.assetAvailableEntrustAmt = ' ';
                            }


                            if ($("#chlFinprodId").val() != null && $("#chlFinprodId").val() != '') {
                                table.showColumns([{field: 'secManageAvailablePosition', visible: false}]);
                                table.showColumns([{field: 'secManageAvailablePosition1', visible: false}]);
                                table.showColumns([{field: 'channelAvailableCash', visible: true}]);
                                table.showColumns([{field: 'channelAvailableCash1', visible: true}]);
                                rowData.secManageAvailablePosition = '';
                                rowData.secManageAvailablePosition1 = '';
                                rowData.channelAvailableCash = result.channelAvailable;
                                rowData.channelAvailableCash1 = result.channelAvailableT1;
                            } else {
                                table.showColumns([{field: 'secManageAvailablePosition', visible: true}]);
                                table.showColumns([{field: 'secManageAvailablePosition1', visible: true}]);
                                table.showColumns([{field: 'channelAvailableCash', visible: false}]);
                                table.showColumns([{field: 'channelAvailableCash1', visible: false}]);
                                rowData.secManageAvailablePosition = result.secManageAvailablePosition;
                                rowData.secManageAvailablePosition1 = result.secManageAvailablePositionT1;
                                rowData.channelAvailableCash = '';
                                rowData.channelAvailableCash1 = '';
                            }

                            table.renderRow(data.LAY_TABLE_INDEX, rowData);
                        }
                    });
                }
                if (value == null || value == '') {
                    rowData.proAvailableCash = ' ';
                    rowData.proAvailableCash1 = ' ';
                    rowData.secManageAvailablePosition = ' ';
                    rowData.secManageAvailablePosition1 = ' ';
                    rowData.channelAvailableCash = ' ';
                    rowData.channelAvailableCash1 = ' ';
                    rowData.assetPositionEntrustAmt = ' ';
                    rowData.assetAvailableEntrustAmt = ' ';
                    table.renderRow(data.LAY_TABLE_INDEX, rowData);
                }
            }

        }
    });

    var refreshTradAmt = function () {
        var datas = table.getAllRows();
        var tradeAmt = Number(0)
        for (var i in datas) {
            var entrustNum = 0;
            if (datas[i].entrustNum == '' || datas[i].entrustNum == undefined) {
                entrustNum = 0;
            } else {
                entrustNum = parseFloat(datas[i].entrustNum);
            }
            tradeAmt += parseFloat(entrustNum);
        }
        $("#tradeAmt").val(tradeAmt)
    }
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function (data, index) {
        table.removeRow(data);
        refreshTradAmt();
    });

    var init = function () {
    	table.reload();
    	
        if (pageModel == PAGE_MODEL_REFERENCE || pageModel == PAGE_MODEL_EDIT) {
            $('#EtribBondInfo003_btn_reset').css('display', 'none');
        }
        if (todoList == false) {
            $('#EtribBondInfo003_btn_fileupload').css('display', 'none');
            $('#EtribBondInfo003_btn_riskcal').css('display', 'none');
        } else {
            if ($('#priceModel').val() == PRICEMODEL_03) {
                // 净价置灰不可填
                $('#price').val('').attr('disabled', true);
                $('#price').removeAttr("required");
                // 全价置灰不可填
                $('#fpriceAmt').val('').attr('disabled', true);

                // 到期收益率
                $('#matureYield').val('').attr('disabled', true);

                // 行权收益率
                $('#exerYield').val('').attr('disabled', true);

            } else {
                // 净价必填
                $('#price').attr('disabled', false);
                $('#price').attr("required", "true");
                // 全价 可填
                $('#fpriceAmt').attr('disabled', false);

                $('#matureYield').attr('disabled', false);

                $('#exerYield').attr('disabled', false);
            }
        }
        businessId = $('#businessId').val();
    }

    // 价格模式修改时
    $('#priceModel').change(function () {
        // 市价指令时
        if ($('#priceModel').val() == PRICEMODEL_03) {
            // 净价置灰不可填
            $('#price').val('').attr('disabled', true);
            $('#price').removeAttr("required");
            // 全价置灰不可填
            $('#fpriceAmt').val('').attr('disabled', true);

            // 到期收益率
            $('#matureYield').val('').attr('disabled', true);

            // 行权收益率
            $('#exerYield').val('').attr('disabled', true);

        } else {
            // 净价必填
            $('#price').attr('disabled', false);
            $('#price').attr("required", "true");
            // 全价 可填
            $('#fpriceAmt').attr('disabled', false);

            $('#matureYield').attr('disabled', false);

            $('#exerYield').attr('disabled', false);
        }
    });


    // 交易方向联动
    $('#ps, #chlFinprodId').change(function () {
        var rowsData = table.getAllRows();
        if (rowsData.length > 0) {
            for (var index in rowsData) {
                if (rowsData[index].portfolioId == '' || rowsData[index].portfolioId == undefined) {
                    continue;
                }

                rowsData[index].assetPositionEntrustAmt = '';
                rowsData[index].assetAvailableEntrustAmt = '';
                var datas = {
                    portfolioId: rowsData[index].portfolioId,
                    sysDateT1: $('#vdate').val(),
                    calId: $('#calendarId').val(),
                    chlFinprodId: $("#chlFinprodId").val(),
                    finprodId: $("#finprodIdhid").val()

                };
                Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtribBondInfo003/getPortfolioAllocationValues', datas, function (result) {
                    if (result) {
                        if ($('#ps').val() == PS_T01) {
                            rowsData[index].proAvailableCash = result.proAvailableCash;
                            rowsData[index].proAvailableCash1 = result.proAvailableCashT1;
                            rowsData[index].assetPositionEntrustAmt = ' ';
                            rowsData[index].assetAvailableEntrustAmt = ' ';
                        } else if ($('#ps').val() == PS_T02 || $('#ps').val() == PS_T03 ) {
                            rowsData[index].proAvailableCash = ' ';
                            rowsData[index].proAvailableCash1 = ' ';
                            rowsData[index].assetPositionEntrustAmt = result.assetPositionEntrustAmt;
                            rowsData[index].assetAvailableEntrustAmt = result.assetAvailableEntrustAmt;
                        } else {
                            rowsData[index].proAvailableCash = ' ';
                            rowsData[index].proAvailableCash1 = ' ';
                            rowsData[index].assetPositionEntrustAmt = ' ';
                            rowsData[index].assetAvailableEntrustAmt = ' ';
                        }


                        if ($("#chlFinprodId").val() != null && $("#chlFinprodId").val() != '') {
                            table.showColumns([{field: 'secManageAvailablePosition', visible: false}]);
                            table.showColumns([{field: 'secManageAvailablePosition1', visible: false}]);
                            table.showColumns([{field: 'channelAvailableCash', visible: true}]);
                            table.showColumns([{field: 'channelAvailableCash1', visible: true}]);
                            rowsData[index].secManageAvailablePosition = '';
                            rowsData[index].secManageAvailablePosition1 = '';
                            rowsData[index].channelAvailableCash = result.channelAvailable;
                            rowsData[index].channelAvailableCash1 = result.channelAvailableT1;
                        } else {
                            table.showColumns([{field: 'secManageAvailablePosition', visible: true}]);
                            table.showColumns([{field: 'secManageAvailablePosition1', visible: true}]);
                            table.showColumns([{field: 'channelAvailableCash', visible: false}]);
                            table.showColumns([{field: 'channelAvailableCash1', visible: false}]);
                            rowsData[index].secManageAvailablePosition = result.secManageAvailablePosition;
                            rowsData[index].secManageAvailablePosition1 = result.secManageAvailablePositionT1;
                            rowsData[index].channelAvailableCash = '';
                            rowsData[index].channelAvailableCash1 = '';
                        }
                    }
                }, false);

                table.reload({data: rowsData});
            }

        }
        if ($("#chlFinprodId").val() != null && $("#chlFinprodId").val() != '') {
            $("#secAcctId").val('');
        } else {
            $("#secAcctId").val('999999');
        }
        if ($("#price").val()) {
            calData("price");
        }
        //左下行情信息联动
        var chlFinprodId = $("#chlFinprodId").val();
        var finprodIdhid = $("#finprodIdhid").val();
        var portfolioId = $("#portfolioId").val();
        var ccy = $('#ccy').val();
        var sysDateT1 = $('#vdate').val();
		var calId = $('#calendarId').val();
        parent.EtrIbBondinfoHq.querySecinfo(finprodIdhid, portfolioId, chlFinprodId,ccy,sysDateT1,calId);
    });


    init();

    /**
     * 获取提交的数据
     * @param operateType
     * @returns {{}}
     */
    function getSubmitParam(operateType) {
        var res = {}
        var object = $.serializeToObject('#searchForm');
        if ($('#select2-finprodId-container').attr('title')){
            object.finprodName = $('#select2-finprodId-container').attr('title');
        }
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.tradeMarket = $('#tradeMarket').val();
        object.entrustId = $('#entrustId').val();
        object.businessId = $('#businessId').val();
        object.entrustStatus = $('#entrustStatus').val();
        object.finvdate = $('#finvdate').val();
        object.invManagerId = $('#invManagerId').val();
        object.entrustDate = object.vdate > object.finvdate.split(' ')[0] ?
        object.vdate: object.finvdate.split(' ')[0];

        object.finmdate = $('#finmdate').val();
        object.ccy = $('#ccy').val();
        object.etrInvestPersonInfo = {
            counterId: object.counterId
        };
        // 证券账户ID
        object.secAcctId = $('#secAcctId').val()
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if(object.fileArray && _.isArray(object.fileArray)){
                $.each(object.fileArray, function(index,obj){
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        var objects = new Array();
        var entrustNum = 0;
        var finprodId = null;
        var counterId = null;
        var tradeMarket = $('#tradeMarket').val();
        var ps = null;
        var param = {};
        for(var index in object.bussCombDistribtList){
            var item = object.bussCombDistribtList[index];
            var temp = JSON.parse(JSON.stringify(object));
            temp.portfolioId = item.portfolioId;
            temp.entrustNum = item.entrustNum * 10000;
            finprodId = temp.finprodId;
            counterId = temp.counterId;
            tradeMarket = temp.tradeMarket;
            temp.entrustId = item.entrustId;
            ps = temp.ps;
            temp.invAim = item.invAim;
            temp.secAcctId = item.secManageAcctId;
            temp.proAvailableCash = item.proAvailableCash;
            temp.accAvailableCash = item.secManageAvailablePosition;
            temp.assetAvailablePosition  = item.assetAvailableEntrustAmt;
            temp.elementId = 'EtribBondInfo003_btn_submit';

            objects.push(temp);
        }
        param = JSON.parse(JSON.stringify(object));
        param.entrustNum = object.tradeAmt * 10000;
        param.finprodId = finprodId;
        param.counterId = counterId;
        param.tradeMarket = tradeMarket;
        param.invManagerId = $('#invManagerId').val();
        param.ps = ps;
        param.ccy = $('#ccy').val();
        param.etrInvestBondInfoDtos = objects;
        param.bussCombDistribtList = object.bussCombDistribtList;
        // add by zhangrui1 start
        param.operateType = operateType;
        param.pageModel = pageModel;
        param.riskTrialRetDto = object.riskTrialRetDto;
        param.elementId = 'EtribBondInfo003_btn_submit';

        res.param = param;
        res.objects = objects;
        return res;
    }



    /**
     * 重置
     * @returns
     */
    function doReset() {
        $("#finprodId").val('').trigger('change');
        $("#ps").val('').trigger('change');
        $("#tradeAmt").val('').trigger('change');
        $("#settleSpeed").val('').trigger('change');
        $("#priceModel").val('').trigger('change');
        $("#price").val('').trigger('change');
        $("#fpriceAmt").val('').trigger('change');
        $("#matureYield").val('').trigger('change');
        $("#exerYield").val('').trigger('change');
        $("#counterId").val('').trigger('change');
        $("#chlFinprodId").val('').trigger('change');

        $("#remark").val('').trigger('change');
        var platFormDate = $("#vdate").val();
        $("#mdate").val(platFormDate).trigger('change');
        table.clear();
    }

    /**
     * 委托终止日
     */
    $("#mdate").change(function () {
        checkFinMdate();
        if ($("#price").val()) {
            calData("price");
        }
    });

    /**
     * 清算速度
     */
    $("#settleSpeed").change(function () {
        if ($("#price").val() || $("#exerYield").val() || $("#matureYield").val()) {
            if ($("#price").val()) {
                calData("price");
            }
        }
    });

    /**
     * 净价
     */
    $("#price").change(function () {
        var ps = $("#ps").val();
        var entrustNum = $("#tradeAmt").val();
        if (entrustNum == null) entrustNum = "null";
        if (entrustNum == "") {
            entrustNum = 10000;
        }
        if ($("#finprodId").val() && entrustNum && $(this).val()) {
            calData("price");
        } else if (!$("#finprodId").val() && ps == PS_T01) {
            if ($("#price").val()) {
                $("#exerYield").attr("disabled", true);
                $("#matureYield").attr("disabled", true);
                $("#exerYield").val("");
                $("#matureYield").val("");
            } else {
                $("#exerYield").removeAttr("disabled");
                $("#matureYield").removeAttr("disabled");
            }
        }
    });
    /**
     * 行权收益率
     */
    $("#exerYield").change(function () {
        var ps = $("#ps").val();
        var entrustNum = $("#tradeAmt").val();
        if (entrustNum == "" || entrustNum == undefined) {
            entrustNum = 10000;
        }
        if ($("#finprodId").val() && entrustNum) {
            if ($("#exerYield").val()) {
                calData("exerYield");
            }
        } else if (!$("#finprodId").val() && ps == PS_T01) {
            if ($("#exerYield").val()) {
                $("#price").attr("disabled", true);
                $("#matureYield").attr("disabled", true);
                $("#price").val("");
                $("#matureYield").val("");
            } else {
                $("#price").removeAttr("disabled");
                $("#matureYield").removeAttr("disabled");
            }
        }
    });
    /**
     * 到期收益率
     */
    $("#matureYield").change(function () {
        var ps = $("#ps").val();
        var entrustNum = $("#tradeAmt").val();
        if (entrustNum == "" || entrustNum == undefined) {
            entrustNum = 10000;
        }
        if ($("#finprodId").val() && entrustNum) {
            if ($("#matureYield").val()) {
                calData("matureYield");
            }
        } else if (!$("#finprodId").val() && ps == PS_T01) {
            if ($("#matureYield").val()) {
                $("#price").attr("disabled", true);
                $("#exerYield").attr("disabled", true);
                $("#price").val("");
                $("#exerYield").val("");
            } else {
                $("#price").removeAttr("disabled");
                $("#exerYield").removeAttr("disabled");
            }
        }
    });

    function getriskdata() {
        var bussCombDistribtList = $.serializeToObject('#searchForm').bussCombDistribtList;
        if (bussCombDistribtList.length > 0) {
            var objects = new Array();
            for (var index in bussCombDistribtList) {
                var item = bussCombDistribtList[index];
                var obj = {
                    tradeDirection: $('#ps').val(),
                    timePoint: MY_SUP_TIME_TYPE_02,
                    valDate: sysDate,
                    portfolioCode: item.portfolioId,
                    entrustNum: item.entrustNum,
                    assetType: fintype,
                    isEnabled: '01',
                    assetCode: $('#finprodIdhid').val()
                }
                // add by zhangrui1 交易对手影响风险试算 start
                obj.counterId = $('#counterId').val();
                obj.ps = $('#ps').val();
                obj.portfolioId = item.portfolioId;
                obj.price = $('#price').val();
                obj.ccy = 'CNY';
                obj.finprodId = $('#finprodIdhid').val();

                // add by zhangrui1 交易对手影响风险试算 end
                var object = {
                    tradeDirection: ['ps', 'EtribBondInfo003.ps'],
                    portfolioCode: ['portfolioId', 'EtribBondInfo003.portfolioId'],
                    entrustNum: ['entrustNum', 'EtribBondInfo003.entrustAmt'],
                    data: obj
                }
                // add by zhangrui1 交易对手影响风险试算 start
                object.counterId = ['counterId', 'EtrInvestPersonInfo.counterId'];
                // add by zhangrui1 交易对手影响风险试算 end
                objects.push(object);
            }
            return objects;
        } else {//若组合分配列表为空，则单个check
            var obj = {
                tradeDirection: $('#ps').val(),
                timePoint: MY_SUP_TIME_TYPE_02,
                valDate: sysDate,
                assetType: fintype,
                assetSecType: $('#assetType1').val(),
                isEnabled: '01',
                assetCode: $('#finprodIdhid').val()
            }
            obj.ps = $('#ps').val();
            obj.price = $('#price').val();
            obj.ccy = 'CNY';
            obj.finprodId = $('#finprodIdhid').val();
            // add by zhangrui1 交易对手影响风险试算 start
            obj.counterId = $('#etrInvestPersonInfo_counterId').val();
            // add by zhangrui1 交易对手影响风险试算 end
            var object = {
                tradeDirection: ['ps', 'EtribBondInfo003.ps'],
                portfolioCode: ['portfolioId', 'EtribBondInfo003.portfolioId'],
                entrustNum: ['entrustNum', 'EtribBondInfo003.entrustAmt'],
                assetType: ['assetType1', 'EtribBondInfo003.bondType'],
                data: obj
            }
            // add by zhangrui1 交易对手影响风险试算 start
            object.counterId = ['counterId', 'EtrInvestPersonInfo.counterId'];
            var objects = [];
            objects.push(object)
            // add by zhangrui1 交易对手影响风险试算 end
            return objects;
        }
    }

    /**
     * 文件上传
     */
    var fileUpload = function () {
        var params = getSearchParams();
        var object = {
            businessId: params.businessId
        }

        Joyin.dialog(JoyinUtil.getLabel('EtribBondInfo003.fileUpload'), '/invest/entrust/etribbondinfo/EtrIbBondinfoFile/init', '60%', '75%',object);
    }

    /**
     * 风险试算
     * @param url
     * @param object
     * @param riskdata
     * @returns
     */
    function doRiskcal() {
        var object = getSearchParams();
        var checkParam = {};
        checkParam.data = getriskdata();
        var param = [];

        if (checkParam.data.length > 0) {
            for(var index in checkParam.data){
                var item = checkParam.data[index];
                item.data.assetCode = object.finprodId;
                item.data.finprodId = object.finprodId;
                item.data.counterId = object.counterId;
                item.data.ps = object.ps;
                item.data.ccy = 'CNY';
                item.data.portfolioId = item.data.portfolioCode;
                item.data.price=object.price;
                item.data.entrustNum=item.data.entrustNum;
                param.push(item.data);
            }
        }
        window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/batchRiskTrialRds/0/F01', param, checkParam,riskMathRedisCallback003);
    }

    /**
     * 风险试算的回调函数
     * @param object
     * @param cluflag
     * @returns
     */
    function riskMathRedisCallback003(object,cluflag) {
        riskResultdata = object;
        cluflag = cluflag;
        Joyin.dialog(JoyinUtil.getLabel('EtribBondInfo003Riskcal'), '/invest/entrust/etribbondinfo/EtribBondInfo003Riskcal/init', '60%', '75%',null);
    }

    //到期日小于委托结束日，给出提示
    var checkFinMdate = function () {
        if ($('#finprodId').val()) {
            var finMdate = (new Date($("#finprodId").select2('data')[0].object.finmdate)).format('yyyy-MM-dd');
            if (new Date(finMdate).DateDiff('d', $("#mdate").val()) > 0) {
                Joyin.error(JoyinUtil.getMessage("ETR_X_007"));
                $("#finprodId").val('').trigger('change');
            }
        }
    }


    function saveOrSubmit(operateType) {
        var s;
        if (BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if (!s && s != "") {
                return;
            }
            var checkData = {};
            checkData.data = getriskdata();
            if (window.BaseRiskMathComm.isCalcConditionChanged(checkData)) {
                return;
            }
        }
        var res = EtrIbBondinfo003.getSubmitParam(operateType)

        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', res.param, function(result1) {
            if(res.objects.length>0){
                Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtribBondInfo003/check',res.objects,
                    function(result) {
                        Joyin.confirm(result.data,function() {
                            if (operateType === "save") {
                                WorkFlow.doSaveList(basePath+'invest/entrust/etribbondinfo/EtribBondInfo003/save',res.param,"EtribBondInfo003_btn_save","EtribBondInfo003");
                                EtrIbBondinfo003.doReset();
                            } else {
                                WorkFlow.doSubmitList(basePath+'invest/entrust/etribbondinfo/EtribBondInfo003/save',res.param,"EtribBondInfo003_btn_submit","EtribBondInfo003");
                                EtrIbBondinfo003.doReset();
                            }

                        });
                    });
            }else{
                Joyin.warn("请添加组合分配！");
                return;
            }
        })

    }

    /**
     * 价格模式
     */
    $("#priceModel").change(function () {
        if (pageModel != PAGE_MODEL_REFERENCE) {
            if ($("#priceModel").val() == PRICEMODEL_03) {
                $("#price").val("").attr("disabled", true);
                $("#fpriceAmt").val("").attr("disabled", true);
                $("#matureYield").val("").attr("disabled", true);
                $("#exerYield").val("").attr("disabled", true);
            } else {
                $("#price").val("").attr("disabled", false);
                $("#matureYield").attr("disabled", false);
                $("#exerYield").attr("disabled", false); //行权收益率
            }
            if ($("#price").val()) {
                calData("price");
            }
        }
    });

    //净价、行权收益率、到期收益率联动
    var calData = function (calflag) {
        if (!initFlag) {
            var ps = $("#ps").val();
            if (!ps) {
                return;
            }
            if (!$("#priceModel").val() || !($("#priceModel").val() == "PRM01" || $("#priceModel").val() == "PRM02")) {
                return;
            }
            if (!calflag) {
                return;
            }
            if (!$("#finprodId").val()) {
                return;
            }
            var object = $.serializeToObject("#searchForm");
            object.calflag = calflag;
            object.pageModel = pageModel;
            object.entrustId = $("#entrustId").val();
            object.entrustStatus = $("#entrustStatus").val();
            object.finvdate = $("#finvdate").val();
            object.finmdate = $("#finmdate").val();
            object.finprodId = $("#finprodIdhid").val();
            object.tradeMarketMode = SHARES_TRADE_MARKET_02;
            /* object.etrInvestPersonInfo.invManagerId = $('#etrInvestPersonInfo_invManagerId').val()
            if (object.etrInvestPersonInfo.trader != null && object.etrInvestPersonInfo.trader != '') {
                object.etrInvestPersonInfo.trader = object.etrInvestPersonInfo.trader.join(',')
            } */
            if (object.bussCombDistribtList != null) {
                if (object.bussCombDistribtList.length > 0) {
                    var entrustNum = 0.0;
                    for (var index in object.bussCombDistribtList) {
                        var item = object.bussCombDistribtList[index];
                        if (item.entrustNum == null || item.entrustNum == "") return;
                        entrustNum += parseFloat(item.entrustNum) * 10000;
                    }
                    object.entrustNum = String(entrustNum);
                } else {
                    object.entrustNum = 10000;
                }
            }
            if (typeof FileCommon != "undefined") {
                object.fileArray = FileCommon.getUploadFileInfo();
                if (object.fileArray && _.isArray(object.fileArray)) {
                    $.each(object.fileArray, function (index, obj) {
                        obj.seqNo = obj.fileNo;
                    });
                }
            }
            Joyin.ajaxJson(basePath + "invest/entrust/etribbondinfo/EtribBondInfo003/calData", object, function (result) {
                if (result && result.data) {
                    var data = result.data;
                    if (data.price) {
                        $("#price").val(data.price).trigger("blur");
                        $("#fpriceAmt").val(data.unitFprice).trigger("blur");
                    } else {
                        $("#price").val("");
                        $("#fpriceAmt").val("");
                    }
                    if (pageModel != PAGE_MODEL_REFERENCE) {
                        $("#exerYield").val(data.exerYield).trigger("blur");
                        $("#matureYield").val(data.matureYield).trigger("blur");
                    }
                }
            });
        }
    };


    // 绑定提交按钮
    $('#EtribBondInfo003_btn_submit').click(function () {
        saveOrSubmit('submit');
    });

    // 绑定保存按钮
    $('#EtribBondInfo003_btn_save').click(function () {
        saveOrSubmit('save');
    });

    // 绑定取消按钮
    $('#EtribBondInfo003_btn_reset').click(function () {
        doReset();
    });

    // 绑定风险按钮
    $('#EtribBondInfo003_btn_riskcal').click(function () {
        doRiskcal();
    });

    $('#EtribBondInfo003_btn_fileupload').click(function () {
        fileUpload();
    });

    $('#finprodId').change(function () {
        var ps = $('#ps').val();
        var ccy = $('#ccy').val();
        var portfolioId = $('#portfolioId').val();
        var vdate = $('#vdate').val();
        var finprodId = $('#finprodId').val();
        if (finprodId) {
            var data = $('#finprodId').select2('data')[0].object;
            if (!data.finprodId) {
                //自动加券
                data = AutoCoupon.autoBondTradeCoupon($('#finprodId').val());
            }
            // 不为空
            $('#price').attr("required", false);
            $('#exerYield').attr("required", false);
            $('#matureYield').attr("required", false);
            //var data = $('#finprodId').select2('data')[0].object;
            $('#finprodIdhid').val(data.finprodId).trigger('change');
            $('#finprodName').val(finprodId).trigger('change');
            //if (!initFlag) {
            //    $('#tradeMarket').val(data.tradeMarket).trigger('change');
            //}
            //$('#tradeMarket').attr('disabled', true);
            $('#isRight').val(data.isRight).trigger('change');
            $('#calendarId').val(data.calendarId).trigger('change');
            $('#assetType1').val(data.assetType1).trigger('change');
            $('#assetType10').val(data.assetType10).trigger('change');//新增资产三级分类 王贵奇 2019/7/27
            $('#assetType11').val(data.assetType11).trigger('change');//新增资产四级分类 王贵奇 2019/7/27
            $('#finvdate').val(new Date(data.finvdate)).trigger('change');
            $('#finmdate').val(new Date(data.finmdate)).trigger('change');
            $('#ccy').val(data.ccy).trigger('change').attr('disabled', true);
            if (ps == PS_T01 && ccy && portfolioId && vdate) {
                getPtlCashPositionInfo();
            }
            // 一体化对应etliu start
//                if((ps == T02 ||ps == T03) && portfolioId){
            if ((ps == PS_T02 ) && portfolioId) {
                // 一体化对应etliu end
                getPtlSecPositionInfo();
            }
            Joyin.ajax(basePath + 'invest/entrust/etribbondinfo/EtrIbBondinfo002/getAvailableNum?finprodId=' + data.finprodId, null,
                function (result) {
                    $('#availableNum').val(result);
                });
            checkFinMdate();
            if ($('#price').val()) {
                calData('price');
            }
        } else {
            // 为空
            $('#price').attr("required", true);
            $('#exerYield').attr("required", true);
            $('#matureYield').attr("required", true);
            $('#finprodIdhid').val('').trigger('change');
            $('#finprodName').val('').trigger('change');
            $('#calendarId').val('').trigger('change');
            $('#assetType1').val('').trigger('change');
            $('#assetType10').val('').trigger('change');//新增资产三级分类 王贵奇 2019/7/27
            $('#assetType11').val('').trigger('change');//新增资产四级分类 王贵奇 2019/7/27
            // $('#proAvailableCash1').val('');
            $('#channelAvailableCash').val('');
            $('#availableNum').val('');
            //$('#tradeMarket').val('').trigger('change').attr('disabled', false);
            $('#ccy').val('').trigger('change').attr('disabled', false);
            $('#isRight').val('').trigger('change');

            $('#exerYield').val('').trigger('change').trigger('blur');
            $('#matureYield').val('').trigger('change').trigger('blur');
        }

        // 非含权债时，行权收益率置灰，为空
        if ($('#isRight').val() != 'Y') {
            $('#exerYield').val('').attr('disabled', true);
        }

        //左下行情信息联动
        var chlFinprodId = $("#chlFinprodId").val();
        var finprodIdhid = $("#finprodIdhid").val();
        var ccy = $('#ccy').val();
        var sysDateT1 = $('#vdate').val();
		var calId = $('#calendarId').val();
        if (parent.EtrIbBondinfoHq) {
            parent.EtrIbBondinfoHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId,ccy,sysDateT1,calId);
        }
        // 画星星
        star();
    });


}());