(function () {
    function query(ele){
        return $("#EtribBondInfo003_bodydiv " + ele);
    }
    
    // 风险试算结果
    var riskResultdata;
    var initFlag = false;
    var cluflag;
    var businessId;

    // 封装js方法，包含change事件、disabled赋值
    let doChange = {
        // 选中组合事件
        portfolioChange : function (value, data){ 
            // 多组合单行添加 显示同行持仓信息
            portfolioSingleChange(value, data);
            // 左边持仓现金信息联动
            leftHqReload();
        },
        // 刷新修改页面卷面总额
        refreshTradAmt : refreshTradAmt
    }; 
    
    doChange.priceModelChange = function(){
        // 市价指令时
        if (query('#priceModel').val() == PRICEMODEL_03) {
            // 净价置灰不可填
            query('#price').val('').attr('disabled', true);
            query('#price').removeAttr("required");
            // 全价置灰不可填
            query('#fpriceAmt').val('').attr('disabled', true);

            // 到期收益率
            query('#matureYield').val('').attr('disabled', true);

            // 行权收益率
            query('#exerYield').val('').attr('disabled', true);

        } else {
            // 净价必填
            query('#price').attr('disabled', false);
            query('#price').attr("required", "true");
            // 全价 可填
            query('#fpriceAmt').attr('disabled', false);

            query('#matureYield').attr('disabled', false);

            // 行权收益率
            // query('#exerYield').attr('disabled', false);
            doChange.exerYield_setDisabled(false, query("#isRight").val(), query("#priceModel").val())
        }
    }
    doChange.psChange = function (){
        // 交易对手change
        if (query("#ps").val() === PS_T01) {
            if (query("#isRight").val() !== N) {
                doChange.exerYield_setDisabled(false, query("#isRight").val(), query("#priceModel").val())
            }
            query("#matureYield").removeAttr("disabled");

            // 当交易方向为买入卖出时，交易对手下拉框显示为银行间机构信息中的交易账户21位码+交易成员全称
            query("#div_counterId1").css("display","block");
            query("#div_counterId2").css("display","none");
        } else if (query("#ps").val() === PS_T02) {
            if (query("#isRight").val() !== N) {
                doChange.exerYield_setDisabled(false, query("#isRight").val(), query("#priceModel").val())
            }
            query("#matureYield").attr("disabled", true);
            query("#matureYield").val("");

            // 当交易方向为买入卖出时，交易对手下拉框显示为银行间机构信息中的交易账户21位码+交易成员全称
            query("#div_counterId1").css("display","block");
            query("#div_counterId2").css("display","none");
        } else if (query("#ps").val() == PS_T03) {
            doChange.exerYield_setDisabled(true);

            query("#matureYield").attr("disabled", true);
            query("#matureYield").val("");

            // 当交易方向为行权回售时，可选项为市场机构
            query("#div_counterId1").css("display","none");
            query("#div_counterId2").css("display","block");
        }
    }

    doChange.exerYield_setDisabled = function(isDisabled, isRight, priceModel){
        // 行权收益率可用判断通用设置
        let exerYield = query('#exerYield');
        if(isDisabled){
            exerYield.val('').attr('disabled', true).removeAttr("required");
            query('#calculateField_exerYield').prop('checked', false).attr('disabled', true);
        }else{
            if(isRight !== N && priceModel !== PRICEMODEL_03){
                // 含权债或为空 且 价格模式不是市价
                exerYield.val('').attr('disabled', false).trigger("change").trigger("blur");
                query('#calculateField_exerYield').val('').attr('disabled', false);
            }
        }
        return exerYield;
    };
    doChange.finprodIdChange = function(){
        var ps = query('#ps').val();
        var ccy = query('#ccy').val();
        var portfolioId = query('#portfolioId').val();
        var vdate = query('#vdate').val();
        var finprodId = query('#finprodId').val();
        if (finprodId) {
            var data = query('#finprodId').select2('data')[0].object;
            if (!data.finprodId) {
                //自动加券
                data = AutoCoupon.autoBondTradeCoupon(query('#finprodId').val());
            }
            // 不为空
            query('#price').attr("required", false);
            query('#exerYield').attr("required", false);
            query('#matureYield').attr("required", false);
            //var data = $('#finprodId').select2('data')[0].object;
            query('#finprodIdhid').val(data.finprodId).trigger('change');
            query('#finprodName').val(finprodId).trigger('change');
            //if (!initFlag) {
            //    $('#tradeMarket').val(data.tradeMarket).trigger('change');
            //}
            //$('#tradeMarket').attr('disabled', true);
            query('#isRight').val(data.isRight).trigger('change');
            if(data.isRight === N){
                doChange.exerYield_setDisabled(true);
            }
            query('#calendarId').val(data.calendarId).trigger('change');
            query('#assetType1').val(data.assetType1).trigger('change');
            query('#assetType10').val(data.assetType10).trigger('change');//新增资产三级分类 王贵奇 2019/7/27
            query('#assetType11').val(data.assetType11).trigger('change');//新增资产四级分类 王贵奇 2019/7/27
            query('#finvdate').val(new Date(data.finvdate)).trigger('change');
            query('#finmdate').val(new Date(data.finmdate)).trigger('change');
            query('#ccy').val(data.ccy).trigger('change').attr('disabled', true);
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
                    query('#availableNum').val(result);
                });
            checkFinMdate();
            if (query('#price').val()) {
                calData('price');
            }
        } else {
            // 为空
            query('#price').attr("required", true);
            // query('#exerYield').attr("required", true);
            query('#matureYield').attr("required", true);
            query('#finprodIdhid').val('').trigger('change');
            query('#finprodName').val('').trigger('change');
            query('#calendarId').val('').trigger('change');
            query('#assetType1').val('').trigger('change');
            query('#assetType10').val('').trigger('change');//新增资产三级分类 王贵奇 2019/7/27
            query('#assetType11').val('').trigger('change');//新增资产四级分类 王贵奇 2019/7/27
            // query('#proAvailableCash1').val('');
            query('#channelAvailableCash').val('');
            query('#availableNum').val('');
            //query('#tradeMarket').val('').trigger('change').attr('disabled', false);
            query('#ccy').val('').trigger('change').attr('disabled', false);
            query('#isRight').val('').trigger('change');

            query('#exerYield').val('').trigger('change').trigger('blur');
            query('#matureYield').val('').trigger('change').trigger('blur');

            doChange.exerYield_setDisabled(false, query("#isRight").val(), query("#priceModel").val()).attr("required", true);;
        }

        //左下行情信息联动
        var chlFinprodId = query("#chlFinprodId").val();
        var finprodIdhid = query("#finprodIdhid").val();
        var ccy = query('#ccy').val();
        var sysDateT1 = query('#vdate').val();
        var calId = query('#calendarId').val();
        if (parent.EtrIbBondinfoHq) {
            parent.EtrIbBondinfoHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId,ccy,sysDateT1,calId);
        }
        // 画星星
        star();
    }

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
        ps = query("#ps").val();
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
        }, {/*投资目的*/
            field: 'invAim',
            width: 100,
            title: 'EtrIbBondinfo002.invAim',
            edit: true,
            sortable: false,
            editOption:{type:'select', formatoption:true, result: INV_AIM}
        }, {
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
            visible: true
        }
        , {
            field: 'secManageAvailablePosition1',
            width: 40,
            title: 'EtribBondInfo003.secManageAvailablePosition1',
            sortable: false,
            type: 'number',
            digit: 2,
            visible: true
        }
        // , {
        //     field: 'channelAvailableCash',
        //     width: 40,
        //     title: 'EtribBondInfo003.channelAvailableCash', sortable: false, type: 'number', digit: 2,
        //     visible: (query("#chlFinprodId").val() == null || query("#chlFinprodId").val() == '')?false:true
        // }
        // , {
        //     field: 'channelAvailableCash1', width: 100, title: 'EtribBondInfo003.channelAvailableCash1'
        //     , sortable: false, type: 'number', digit: 2,
        //     visible: (query("#chlFinprodId").val() == null || query("#chlFinprodId").val() == '')?false:true
        // }

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

    var table = query("#bussCombDistribtTable").joyintable({
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
                doChange.refreshTradAmt();
            }
            if (field == "portfolioId") {
                doChange.portfolioChange(value, data);
            }
        }
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function (data, index) {
        table.removeRow(data);
        doChange.refreshTradAmt();
    });

    var init = function () {
        table.reload();
        if(calculateField){
            query("#calculateField_" + calculateField).attr("checked", true);
        }

        if (pageModel == PAGE_MODEL_REFERENCE || pageModel == PAGE_MODEL_EDIT) {
            $('#EtribBondInfo003_btn_reset').css('display', 'none');
        }
        if (todoList == false) {
            $('#EtribBondInfo003_btn_fileupload').css('display', 'none');
            $('#EtribBondInfo003_btn_riskcal').css('display', 'none');
        } else {
            if (query('#priceModel').val() == PRICEMODEL_03) {
                // 净价置灰不可填
                query('#price').val('').attr('disabled', true);
                query('#price').removeAttr("required");
                // 全价置灰不可填
                query('#fpriceAmt').val('').attr('disabled', true);

                // 到期收益率
                query('#matureYield').val('').attr('disabled', true);

                // 行权收益率
                query('#exerYield').val('').attr('disabled', true);

            } else {
                // 净价必填
                query('#price').attr('disabled', false);
                query('#price').attr("required", "true");
                // 全价 可填
                query('#fpriceAmt').attr('disabled', false);

                query('#matureYield').attr('disabled', false);

                // query('#exerYield').attr('disabled', false);
                doChange.exerYield_setDisabled(false, query("#isRight").val(), query("#priceModel").val())
            }
        }
        businessId = query('#businessId').val();
    }

    // 价格模式修改时
    query('#priceModel').change(function () {
        doChange.priceModelChange();
    });

    // 交易方向联动
    query('#ps').change(function () {
        doChange.psChange();

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
                    sysDateT1: query('#vdate').val(),
                    calId: query('#calendarId').val(),
                    chlFinprodId: query("#chlFinprodId").val(),
                    finprodId: query("#finprodIdhid").val()

                };
                Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtribBondInfo003/getPortfolioAllocationValues', datas, function (result) {
                    if (result) {
                        if (query('#ps').val() == PS_T01) {
                            rowsData[index].proAvailableCash = result.proAvailableCash;
                            rowsData[index].proAvailableCash1 = result.proAvailableCashT1;
                            rowsData[index].assetPositionEntrustAmt = ' ';
                            rowsData[index].assetAvailableEntrustAmt = ' ';
                        } else if (query('#ps').val() == PS_T02 || query('#ps').val() == PS_T03 ) {
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


                        if (query("#chlFinprodId").val() != null && query("#chlFinprodId").val() != '') {
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
        if (query("#chlFinprodId").val() != null && query("#chlFinprodId").val() != '') {
            query("#secAcctId").val('');
        } else {
            query("#secAcctId").val('999999');
        }
        if (query("#price").val()) {
            calData("price");
        }
        //左下行情信息联动
        var chlFinprodId = query("#chlFinprodId").val();
        var finprodIdhid = query("#finprodIdhid").val();
        var portfolioId = query("#portfolioId").val();
        var ccy = query('#ccy').val();
        var sysDateT1 = query('#vdate').val();
        var calId = query('#calendarId').val();
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
            object.finprodName = query('#select2-finprodId-container').attr('title');
        }
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.tradeMarket = query('#tradeMarket').val();
        object.entrustId = query('#entrustId').val();
        object.businessId = query('#businessId').val();
        object.entrustStatus = query('#entrustStatus').val();
        object.finvdate = query('#finvdate').val();
        object.invManagerId = query('#invManagerId').val();
        object.entrustDate = object.vdate > object.finvdate.split(' ')[0] ?
            object.vdate: object.finvdate.split(' ')[0];
        //提交获取计算方式单选框的值
        object.calculateField = query("input[name=calculateField]:checked").val();

        object.finmdate = query('#finmdate').val();
        object.ccy = query('#ccy').val();
        if (query("#ps").val() === PS_T03) {
            object.counterId=query('#counterId2').val();
        }else{
            object.counterId=query('#counterId1').val();
        }

        object.etrInvestPersonInfo = {
            counterId: object.counterId
        };
        // 证券账户ID
        object.secAcctId = query('#secAcctId').val()
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
        var tradeMarket = query('#tradeMarket').val();
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
        param.invManagerId = query('#invManagerId').val();
        param.ps = ps;
        param.ccy = query('#ccy').val();
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
        query("#finprodId").val('').trigger('change');
        query("#ps").val('').trigger('change');
        query("#tradeAmt").val('').trigger('change');
        query("#settleSpeed").val('').trigger('change');
        query("#priceModel").val('').trigger('change');
        query("#price").val('').trigger('change');
        query("#fpriceAmt").val('').trigger('change');
        query("#matureYield").val('').trigger('change');
        query("#exerYield").val('').trigger('change');
        query("#chlFinprodId").val('').trigger('change');

        query("#remark").val('').trigger('change');
        // var platFormDate = query("#vdate").val();
        // query("#mdate").val(platFormDate).trigger('change');

        query("#calculateField_price").prop('checked', false);
        query("#calculateField_exerYield").prop('checked', false);
        query("#calculateField_matureYield").prop('checked', false);
        layui.form.render(); // reload layui
        query("#vdate").val(sysDate).trigger("change");
        query("#mdate").val(sysDate).trigger("change");
        query("#counterId1").val("").trigger("change");
        query("#counterId2").val("").trigger("change");
        table.clear();
    }

    /**
     * 委托终止日
     */
    query("#mdate").change(function () {
        checkFinMdate();
        if (query("#price").val()) {
            calData("price");
        }
    });

    /**
     * 清算速度
     */
    query("#settleSpeed").change(function () {
        if (query("#price").val() || query("#exerYield").val() || query("#matureYield").val()) {
            if (query("#price").val()) {
                calData("price");
            }
        }
    });

    /**
     * 净价
     */
    query("#price").change(function () {
        var ps = query("#ps").val();
        var entrustNum = query("#tradeAmt").val();
        if (entrustNum == null) entrustNum = "null";
        if (entrustNum == "") {
            entrustNum = 10000;
        }
        if (query("#finprodId").val() && entrustNum && $(this).val()) {
            calData("price");
        } else if (!query("#finprodId").val() && ps == PS_T01) {
            if (query("#price").val()) {
                query("#exerYield").attr("disabled", true);
                query("#matureYield").attr("disabled", true);
                query("#exerYield").val("");
                query("#matureYield").val("");
            } else {
                query("#exerYield").removeAttr("disabled");
                query("#matureYield").removeAttr("disabled");
            }
        }
    });
    /**
     * 行权收益率
     */
    query("#exerYield").change(function () {
        var ps = query("#ps").val();
        var entrustNum = query("#tradeAmt").val();
        if (entrustNum == "" || entrustNum == undefined) {
            entrustNum = 10000;
        }
        if (query("#finprodId").val() && entrustNum) {
            if (query("#exerYield").val()) {
                calData("exerYield");
            }
        } else if (!query("#finprodId").val() && ps == PS_T01) {
            if (query("#exerYield").val()) {
                query("#price").attr("disabled", true);
                query("#matureYield").attr("disabled", true);
                query("#price").val("");
                query("#matureYield").val("");
            } else {
                query("#price").removeAttr("disabled");
                query("#matureYield").removeAttr("disabled");
            }
        }
    });
    /**
     * 到期收益率
     */
    query("#matureYield").change(function () {
        var ps = query("#ps").val();
        var entrustNum = query("#tradeAmt").val();
        if (entrustNum == "" || entrustNum == undefined) {
            entrustNum = 10000;
        }
        if (query("#finprodId").val() && entrustNum) {
            if (query("#matureYield").val()) {
                calData("matureYield");
            }
        } else if (!query("#finprodId").val() && ps == PS_T01) {
            if (query("#matureYield").val()) {
                query("#price").attr("disabled", true);
                query("#exerYield").attr("disabled", true);
                query("#price").val("");
                query("#exerYield").val("");
            } else {
                query("#price").removeAttr("disabled");
                query("#exerYield").removeAttr("disabled");
            }
        }
    });

    function getriskdata() {
        let finprodIdhid = query('#finprodIdhid').val();
        if(finprodIdhid == null || finprodIdhid === ''){
            JoyinUtil.showMessage("请选择资产简称","error");
            return null;
        }
        let ps = query('#ps').val();
        if(ps == null || ps === ''){
            JoyinUtil.showMessage("请选择交易方向","error");
            return null;
        }
        var bussCombDistribtList = $.serializeToObject('#searchForm').bussCombDistribtList;
        if (bussCombDistribtList.length > 0) {
            var objects = new Array();
            for (var index in bussCombDistribtList) {
                var item = bussCombDistribtList[index];
                var obj = {
                    tradeDirection: query('#ps').val(),
                    timePoint: MY_SUP_TIME_TYPE_02,
                    valDate: sysDate,
                    portfolioCode: item.portfolioId,
                    entrustNum: item.entrustNum,
                    assetType: fintype,
                    isEnabled: '01',
                    assetCode: query('#finprodIdhid').val()
                }
                // add by zhangrui1 交易对手影响风险试算 start
                obj.ps = query('#ps').val();
                if (obj.ps === PS_T03) {
                    obj.counterId=query('#counterId2').val();
                }else{
                    obj.counterId=query('#counterId1').val();
                }
                obj.portfolioId = item.portfolioId;
                obj.price = query('#price').val();
                obj.ccy = 'CNY';
                obj.finprodId = query('#finprodIdhid').val();

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
            JoyinUtil.showMessage("请添加组合信息","error");
            return null;
            // var obj = {
            //     tradeDirection: query('#ps').val(),
            //     timePoint: MY_SUP_TIME_TYPE_02,
            //     valDate: sysDate,
            //     assetType: fintype,
            //     assetSecType: query('#assetType1').val(),
            //     isEnabled: '01',
            //     assetCode: query('#finprodIdhid').val()
            // }
            // obj.ps = query('#ps').val();
            // obj.price = query('#price').val();
            // obj.ccy = 'CNY';
            // obj.finprodId = query('#finprodIdhid').val();
            // // add by zhangrui1 交易对手影响风险试算 start
            // obj.counterId = query('#etrInvestPersonInfo_counterId').val();
            // // add by zhangrui1 交易对手影响风险试算 end
            // var object = {
            //     tradeDirection: ['ps', 'EtribBondInfo003.ps'],
            //     portfolioCode: ['portfolioId', 'EtribBondInfo003.portfolioId'],
            //     entrustNum: ['entrustNum', 'EtribBondInfo003.entrustAmt'],
            //     assetType: ['assetType1', 'EtribBondInfo003.bondType'],
            //     data: obj
            // }
            // // add by zhangrui1 交易对手影响风险试算 start
            // object.counterId = ['counterId', 'EtrInvestPersonInfo.counterId'];
            // var objects = [];
            // objects.push(object)
            // // add by zhangrui1 交易对手影响风险试算 end
            // return objects;
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
        if (query('#finprodId').val()) {
            var finMdate = (new Date(query("#finprodId").select2('data')[0].object.finmdate)).format('yyyy-MM-dd');
            if (new Date(finMdate).DateDiff('d', query("#mdate").val()) > 0) {
                Joyin.error(JoyinUtil.getMessage("ETR_X_007"));
                query("#finprodId").val('').trigger('change');
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
    query("#priceModel").change(function () {
        if (pageModel != PAGE_MODEL_REFERENCE) {
            if (query("#priceModel").val() == PRICEMODEL_03) {
                query("#price").val("").attr("disabled", true);
                query("#fpriceAmt").val("").attr("disabled", true);
                query("#matureYield").val("").attr("disabled", true);
                query("#exerYield").val("").attr("disabled", true);
            } else {
                query("#price").val("").attr("disabled", false);
                query("#matureYield").attr("disabled", false);
                query("#exerYield").attr("disabled", false); //行权收益率
            }
            if (query("#price").val()) {
                calData("price");
            }
        }
    });

    //净价、行权收益率、到期收益率联动
    var calData = function (calflag) {
        if (!initFlag) {
            var ps = query("#ps").val();
            if (!ps) {
                return;
            }
            if (!query("#priceModel").val() || !(query("#priceModel").val() == "PRM01" || query("#priceModel").val() == "PRM02")) {
                return;
            }
            if (!calflag) {
                return;
            }
            if (!query("#finprodId").val()) {
                return;
            }
            //只有选中单选按钮的值才进行联动计算  add by yzg 20210622 start
            var calculateField =  query("input[name=calculateField]:checked").val();
            if(calflag != calculateField){
                return;
            }
            //只有选中单选按钮的值才进行联动计算  add by yzg 20210622 end
            var object = $.serializeToObject("#searchForm");
            object.calflag = calflag;
            object.pageModel = pageModel;
            object.entrustId = query("#entrustId").val();
            object.entrustStatus = query("#entrustStatus").val();
            object.finvdate = query("#finvdate").val();
            object.finmdate = query("#finmdate").val();
            object.finprodId = query("#finprodIdhid").val();
            object.tradeMarketMode = SHARES_TRADE_MARKET_02;
            /* object.etrInvestPersonInfo.invManagerId = query('#etrInvestPersonInfo_invManagerId').val()
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
                        query("#price").val(data.price).trigger("blur");
                        query("#fpriceAmt").val(data.unitFprice).trigger("blur");
                    } else {
                        query("#price").val("");
                        query("#fpriceAmt").val("");
                    }
                    if (pageModel != PAGE_MODEL_REFERENCE) {
                        query("#exerYield").val(data.exerYield).trigger("blur");
                        query("#matureYield").val(data.matureYield).trigger("blur");
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

    query('#finprodId').change(function () {
        doChange.finprodIdChange();
    });

//封装方法start-------------------------------------------------------------------------------------------------------------------------------
    
    function portfolioSingleChange(value, data) {
        var rowData = {};
        // rowData.entrustNum = data.entrustNum;
        // table.renderRow(data.LAY_TABLE_INDEX, rowData);
        if (value != null && value !== '') {
            var datas = {
                portfolioId:data.portfolioId,
                ccy:query('#ccy').val(),
                vdate:query('#vdate').val(),
                calId:query('#calendarId').val(),
                finprodId:query("#finprodIdhid").val()
            };
            Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtribBondInfo003/getPortfolioAllocationValues', datas, function (result) {
                if (result) {
                    if (query('#ps').val() == PS_T01) {
                        rowData.proAvailableCash = result.proAvailableCash;
                        rowData.proAvailableCash1 = result.proAvailableCashT1;
                        rowData.assetPositionEntrustAmt = ' ';
                        rowData.assetAvailableEntrustAmt = ' ';
                    } else if (query('#ps').val() == PS_T02 || query('#ps').val() == PS_T03) {
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

                    if(result.secManageAvailablePosition){
                        table.showColumns([{field: 'secManageAvailablePosition', visible: true}]);
                        rowData.secManageAvailablePosition = result.secManageAvailablePosition;
                    }

                    if(result.secManageAvailablePositionT1){
                        table.showColumns([{field: 'secManageAvailablePosition1', visible: true}]);
                        rowData.secManageAvailablePosition1 = result.secManageAvailablePositionT1;
                    }

                    table.renderRow(data.LAY_TABLE_INDEX, rowData);
                }
            });
        }else{
            rowData.proAvailableCash = ' ';
            rowData.proAvailableCash1 = ' ';
            rowData.secManageAvailablePosition = ' ';
            rowData.secManageAvailablePosition1 = ' ';
            rowData.assetPositionEntrustAmt = ' ';
            rowData.assetAvailableEntrustAmt = ' ';
            table.renderRow(data.LAY_TABLE_INDEX, rowData);
        }
    }

    function refreshTradAmt () {
        if(!query("#tradeAmt").val()){ // 卷面总额不为空时执行
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
            query("#tradeAmt").val(JoyinUtil.formatNumber(tradeAmt, 2, null, 2));
        }
    }

    function leftHqReload () { // 左边持仓现金信息联动
        let ccy = query('#ccy').val();
        let vdate = query('#vdate').val();
        let calId = query('#calendarId').val();
        let finprodId = query("finprodIdhid").val;

        /**
         * 查询资产持仓券面总额、资产可用券面总额
         */
        var params_finprodId = {
            ccy:ccy, vdate:vdate, calId:calId, finprodId:finprodId
        };
        Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtribBondInfo003/getPortfolioAllocationValues', params_finprodId,
        function (result) {
            var leftPositionData = {
                proAvailableCash:Number(0),
                proAvailableCashT1:Number(0),
                secManageAvailablePosition:Number(0),
                secManageAvailablePositionT1:Number(0),
                assetPositionEntrustAmt:Number(0),
                assetAvailableEntrustAmt:Number(0)
            };

            if (result) {
                if(result.assetPositionEntrustAmt) leftPositionData.assetPositionEntrustAmt = result.assetPositionEntrustAmt;
                if(result.assetAvailableEntrustAmt) leftPositionData.assetAvailableEntrustAmt = result.assetAvailableEntrustAmt;
            }

            var datas = table.getAllRows();
            for (var i in datas) {
                if (datas[i].portfolioId !== '' && datas[i].portfolioId !== undefined) {
                    var params = {
                        portfolioId:datas[i].portfolioId,
                        ccy:ccy,
                        vdate:vdate,
                        calId:calId,
                        finprodId:finprodId
                    };
                    Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtribBondInfo003/getPortfolioAllocationValues', params,
                        function (result) {
                            if (result) {
                                // 组合T日可用现金
                                if (result.proAvailableCash) leftPositionData.proAvailableCash += Number(result.proAvailableCash);
                                // 组合T+1日可用现金
                                if(result.proAvailableCashT1) leftPositionData.proAvailableCashT1 += Number(result.proAvailableCashT1);
                                // 托管户T日可用现金
                                if(result.secManageAvailablePosition) leftPositionData.secManageAvailablePosition += Number(result.secManageAvailablePosition);
                                // 托管户T+1日可用现金
                                if(result.secManageAvailablePositionT1) leftPositionData.secManageAvailablePositionT1+= Number(result.secManageAvailablePositionT1);
                            }
                            // 左边联动
                            parent.EtrIbBondinfoHq.viewPositionInfo(leftPositionData);
                        });
                }
            }
        });
    }
    
//封装方法end-----------------------------------------------------------------------------------------------------------------------------------
}());
