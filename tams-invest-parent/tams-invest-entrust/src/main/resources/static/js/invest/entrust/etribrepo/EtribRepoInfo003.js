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
    parent.EtrIbRepoinfo003 = {
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

    EtrIbRepoinfo003 = {
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
            field: 'portfolioId', width: 100, title: 'EtribRepoInfo003.portfolioId', edit: true, sortable: false,
            editOption: {
                type: 'select',
                id: 'portfolioId',
//                childrenIds : 'portfolioId',
                multiple: false,
                showField: 'text',
                searchable: true,
                url: JoyinUtil.getRealPath('/business/common/CommonSelect/selectPortfolioId?act=true')
            }
        },
        /*{
             组合代码 
            field: 'portfolioId', width: 100, title: '子组合代码', edit: true, sortable: false,
            editOption: {
                type: 'select',
                id: 'portfolioId',
                multiple: false,
                showField: 'text',
                searchable: true,
                parentIds:'parentPortfolioId',
                childrenIds : 'zyqk',
                url: JoyinUtil.getRealPath('/business/common/CommonSelect/qryPortfolioIdChildByRequestParam')
            }
        }
        ,*/
        {
            /* 质押券库代码 */
            field: 'zyqk', width: 100, title: 'EtrIbRepo001.zyqk',
            /* 光大质押券暂时隐藏 */
            visible: false,
            edit: true, 
            sortable: false,
            editOption: {
                type: 'select',
                id: 'zyqk',
                multiple: false,
                showField: 'text',
                parentIds : 'portfolioId',
                searchable: true,
                url: JoyinUtil.getRealPath('/invest/entrust/etribrepo/EtrIbRepoinfo002/getPledgeStockIdByPortfolioId')
            }
        } 
        , {
        /*指令金额*/
            field: 'entrustAmt',
            width: 50,
            title: 'EtrRepo002.entrustAmt',
            edit: true,
            sortable: false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'proAvailableCash',
            width: 40,
            title: 'EtribRepoInfo003.proAvailableCash',
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
            title: 'EtribRepoInfo003.proAvailableCash1',
            sortable: false,
            type: 'number',
            digit: 2
        }
        , {
            field: 'secManageAvailablePosition',
            width: 40,
            title: '托管户T日可用现金',
            sortable: false,
            type: 'number',
            digit: 2,
            visible: ($("#chlFinprodId").val() == null || $("#chlFinprodId").val() == '')?true:false
        }
        , {
            field: 'secManageAvailablePosition1',
            width: 40,
            title: '托管户T+1日可用现金',
            sortable: false,
            type: 'number',
            digit: 2,
            visible: ($("#chlFinprodId").val() == null || $("#chlFinprodId").val() == '')?true:false
        }
        , {
            field: 'channelAvailableCash',
            width: 40,
            title: 'EtribRepoInfo003.channelAvailableCash', sortable: false, type: 'number', digit: 2,
            visible: ($("#chlFinprodId").val() == null || $("#chlFinprodId").val() == '')?false:true
        }
        , {
            field: 'channelAvailableCash1', width: 100, title: 'EtribRepoInfo003.channelAvailableCash1'
            , sortable: false, type: 'number', digit: 2,
            visible: ($("#chlFinprodId").val() == null || $("#chlFinprodId").val() == '')?false:true
        }
     // 添加质押券
        ,{field: 'addPledgeInfo',width:100,title: 'BussRepoSecPtl.addPledgeInfo',visible:false,sortable: false,
            render:function (data, type, row, meta) {
            return '<a class="btn_add_plegin" style="padding-left: 0px;display:block;width: 40px;height: 20px;line-height: 20px;text-align: center;background-color: rgb(38, 147, 255);border-radius: 2px;color: #fff;margin: 3px 15px;">添加</a>'
            }
        }
        , {field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['delt']}
    ]
    var table = $("#bussCombDistribtTable").joyintable({
        data:entity_BussCombDistribtList||[],
        cols: cols,
        page: false,
        addbtn: true,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        height: 190,
        addclick: function (row) {

        },
        
        editCell: function (value, data, field, cell, column) {
            if (field == "entrustAmt") {
                refreshTradAmt();
            }
            if (field == "portfolioId") {
                var rowData = {};
                rowData.entrustAmt = data.entrustAmt;
                table.renderRow(data.LAY_TABLE_INDEX, rowData);
                if (value != null && value != '') {
                    var datas = {
                        portfolioId: data.portfolioId,
                        sysDateT1: $('#vdate').val(),
                        calId: $('#calendarId').val(),
                        chlFinprodId: $("#chlFinprodId").val(),
                        ps: $("#ps").val(),
                        finprodId: $("#finprodIdhid").val()
                    };
                    Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtribRepoInfo003/getPortfolioAllocationValues', datas, function (result) {
                        if (result) {
                            if ($('#ps').val() == '03' || $('#ps').val() == '04') {
                                rowData.proAvailableCash = result.proAvailableCash;
                                rowData.proAvailableCash1 = result.proAvailableCashT1;
                            }
                            if(repo_pledge_model=='03'){
                            	if ($('#ps').val() == '04') {
                                	table.showColumns([{field: 'zyqk', visible: false}]);
                                    rowData.zyqk = '';
                                }else{
                                	table.showColumns([{field: 'zyqk', visible: true}]);
                                }
                            }else if (repo_pledge_model=='02'){
                            	if ($('#ps').val() == '04') {
                                	table.showColumns([{field: 'addPledgeInfo', visible: false}]);
                                    rowData.zyqk = '';
                                }else{
                                	table.showColumns([{field: 'addPledgeInfo', visible: true}]);
                                }
                            }
                            /*else if ($('#ps').val() == PS_T02 || $('#ps').val() == PS_T03) {
                                rowData.proAvailableCash = ' ';
                                rowData.proAvailableCash1 = ' ';
                            } else {
                                rowData.proAvailableCash = ' ';
                                rowData.proAvailableCash1 = ' ';
                            }*/


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
                    table.renderRow(data.LAY_TABLE_INDEX, rowData);
                }
            }

        }
    });

    var refreshTradAmt = function () {
        var datas = table.getAllRows();
        var tradeAmt = Number(0)
        for (var i in datas) {
            var entrustAmt = 0;
            if (datas[i].entrustAmt == '' || datas[i].entrustAmt == undefined) {
            	entrustAmt = 0;
            } else {
            	entrustAmt = parseFloat(datas[i].entrustAmt);
            }
            tradeAmt += parseFloat(entrustAmt);
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
        if (pageModel == PAGE_MODEL_REFERENCE || pageModel == PAGE_MODEL_EDIT) {
            $('#EtribRepoInfo003_btn_reset').css('display', 'none');
        }
        if (todoList == false) {
            $('#EtribRepoInfo003_btn_fileupload').css('display', 'none');
            $('#EtribRepoInfo003_btn_riskcal').css('display', 'none');
        } else {
            if ($('#priceModel').val() == PRICEMODEL_03) {
                // 净价置灰不可填
                $('#price').val('').attr('disabled', true);
                $('#price').removeAttr("required");

            } else {
                // 净价必填
                $('#price').attr('disabled', false);
                $('#price').attr("required", "true");
            }
        }
        businessId = $('#businessId').val();
    }
    $('#repoTerm').change(function(){
        var repoTerm = $('#repoTerm').val();
        var re = /^[0-9]*$/;
        if(re.test(repoTerm)){
            var vdateAdd = $('#vdate').val();
            /*if(vdateAdd){
                var d1 = Date.ofDate(vdateAdd).getTime();
                var day = parseInt($('#repoTerm').val()*24*60*60*1000);
                var mdateAdd = Date.ofDate(d1+day);
                $('#mdate').val(mdateAdd.format("yyyy-MM-dd")).change();
            }*/
        }

        // 当交易场所是银行间时，输入回购期限后，自动带出期限品种，回购期限与期限品种的联动关系
       // if ($('#tradeMarket').val() === '01'){
            var repoterm = $('#repoTerm').val();
            if(repoterm < 0){
                Joyin.error("期限品种不能为负数");
                return ;
            }
            var re = /^[0-9]*$/;
            if(repoterm && re.test(repoterm)){
                var vdate = $('#vdate').val();
                if(vdate){
                    //回购期限联动期限品种
                    var term = getTermType(vdate,repoterm);
                     $('#termType').val(term).trigger('change');
                     $('#termType').textSelect('setValue', term);

                }
            }else{
                $('#termType').val("").trigger('change');
            }
      //  }


    });

    var pledgeBondTotalTblDetailed ;
    if(repo_pledge_model=='02'){
    	
    	
    	table.bind('click', 'a.btn_add_plegin', function(data,index) {
    	        var data = {
    	                portfolioId: data.portfolioId,
    	                portfolioName: data.portfolioName,
    	                ps: $("#ps").val(),
    	               tradeMarket: $("#tradeMarket").val(),
    	                secAcctId: data.secAcctId,
    	                repoType: data.repoType,
    	                chlId: $("#chlId").val()
    	            }
    	            var object = {
    	                portfolioId: ['portfolioId', 'TrdRepoTrade002.portfolioId'],
    	                portfolioName: ['portfolioName', 'TrdRepoTrade002.portfolioName'],
    	                ps: ['ps', 'TrdRepoTrade002.ps'],
    	                secAcctId: ['secAcctId', 'TrdRepoTrade002.secAcctId'],
    	                repoType: ['repoType', 'TrdRepoTrade002.repoType'],
    	                chlId: ['chlId', 'TrdRepoTrade002.chlId'],
    	                data: data
    	            }
    	        param = object;
    	        Joyin.ajaxJson(basePath + 'component/trade/BussRepoSec/check',
    	                param, function () {
    	                    if (entity_pledgeBondList == null) {
    	                    	entity_pledgeBondList = {};
    	                    }
    	                    var pledgeBondList = pledgeBondTotalTblDetailed.getAllRows();
    	                    entity_pledgeBondList.pledgeBondList =[];
    	                    $.each(pledgeBondList,function (index,data) {
    	                        if(data.avaPledgePrin){
    	                            data.avaPledgePrin = data.avaPledgePrin;
    	                        }else if(data.avaPledgePrin){
    	                            data.avaPledgePrin = data.avaPledgePrin;
    	                        }
    	                        if(param.data.portfolioId ==data.portfolioId){
    	                        	entity_pledgeBondList.pledgeBondList.push(data);
    	                        }
    	                    });
    	                    Joyin.dialog(JoyinUtil.getLabel('BussRepoSecPtl.addPledgeInfo'),basePath+
    	                        '/component/trade/BussRepoSecDialog/init', '98%', '98%',
    	                        {
    	                            portfolioId: param.data.portfolioId,
    	                            portfolioName: param.data.portfolioName,
    	                            ps: param.data.ps,
    	                            secAcctId: param.data.secAcctId,
    	                            tradeMarket :param.data.tradeMarket,
    	                            chlId: param.data.chlId,
    	                            entity: JSON.stringify(entity_pledgeBondList),
    	                            entityName: 'bussRepoSec',
    	                            pageFromTz: pageId,
//    	                            statusValid: statusValid,
//    	                            bizCode: bizCode,
//    	                            pageModel: pageModel,
//    	                            operationType: operationType,
//    	                            workflowId: workflowId
    	                        });
    	                },false);
    	        
    	    });
    	
    	var buttons = [ { text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }];
        if (pageId != 'EtribRepoInfo003'){
            buttons = ['delt'];
        }
        
    	/**
         * 质押券汇总信息（Total）
         */
        pledgeBondTotalTblDetailed = $("#pledgeBondTotalListDetailed").joyintable({
            page: false,
            data:  entity_PledgeBondTotalListDetailed||[],
            height: 200,
            /**
             * 定义表头
             */
            cols: [
                // 委托代码
               /* {field: 'entrustId',title: 'BussRepoSecPtl.entrustId',type: 'text',align: 'left',sortable: false,edit: false},*/
                // 债券代码
                {field: 'bondCode',title: 'BussRepoSecPtl.secId',type: 'text',align: 'left',sortable: false,edit: false}
                // 债券简称
                ,{field: 'bondAbbrName',title: '债券简称',type: 'text',align: 'left',sortable: true,edit: false}
                // 债券类型
                /*,{field: 'secTypeStr',title: 'BussRepoSecPtl.secType',type: 'text',align: 'left',edit: false,sortable: false}*/
                // 组合代码
                ,{field: 'portfolioId',title: 'BussRepoSecPtl.portfolioId',type: 'text',sortable: false,edit: false}
                // 组合名称
                ,{field: 'portfolioName',title: 'BussRepoSecPtl.portfolioName',type: 'text',edit: false,sortable: false}
                // 质押本方券券面总额
//                , {field: 'pledgeBfPrin',title: '质押本方券券面总额',type: 'number',edit: true,sortable: false}
                // 质押融入券券面总额
//                , {field: 'pledgeRrPrin',title: '质押融入券券面总额',type: 'number',edit: true,sortable: false}
                // 可质押券面总额
                , {field: 'avaPledgePrin',title: '可质押券面总额',type: 'number',edit: false,sortable: false}
                //质押券汇总券面总额
                , {field: 'pledgePrin',title: '质押券券面总额',type: 'number',edit: true,sortable: false}
             // 首次净价
                , {
                    field: 'firstNetPrice',
                    width: 100,
                    title: '首次净价',
                    sortable: false
                }
                //折算比例%
                ,{field: 'pledgeRatio',title: '折算比例%',type: 'number',edit: false,sortable: false}
             
                // 登记托管机构
                ,{field: 'repRegistOrgStr',title: 'BussRepoSecPtl.repRegistOrg',type: 'text',align: 'left',edit:false,sortable:false}
                //可质押本方券券面总额
//                ,{field: 'avaPledgeBfPrin',title: '可质押本方券券面总额',type: 'number',edit: false,sortable: false}
                //可质押融入券券面总额
//                ,{field: 'avaPledgeRrPrin',title: '可质押融入券券面总额',type: 'number',edit: false,sortable: false}
               //操作
               ,{field:'operation',title: '删除',type:'action',buttons: buttons}
            ],
            done:function(){
                this.bind('click', '.delt', function(data, index) {
                    var pbtd2=pledgeBondTotalTblDetailed.getAllRows();
                    for(var i in pbtd2){
                        if(pbtd2[i].portfolioId==data.portfolioId){
                            var totalPrinAmt2=Number(pbtd2[i].totalPrinAmt)-Number(data.pledgePrin);
                            if(totalPrinAmt2==0){
                                var totalPrinAmt2="0";
                                pledgeBondTotalTblDetailed.renderRow(i, {totalPrinAmt:totalPrinAmt2});
                            }else{
                                pledgeBondTotalTblDetailed.renderRow(i, {totalPrinAmt:totalPrinAmt2});
                            }
                        }
                    }
                    pledgeBondTotalTblDetailed.deleteRow(index);
                    createAuto();
                });
           },
            beforeEditCell: function (preVal, field, rowData) {
                if (field === "pledgePrin") {
                    tempPledgePrin = preVal.replace(/\,/g, '');
                }
                return true;
            },
            checkbox:false
        });
        
        /**
         * 保存按钮按下，添加子画面传回的质押券信息
         */
        var addPledgeBond = function (pledgeRows) {
            // 列表中未勾选
            if (!pledgeRows || pledgeRows.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            } else {
            	pledgeBondTotalTblDetailed.clear(function () {
                    var totalPrinAmt = Number(0);
                    var zzdPrinAmt = Number(0);
                    var sqsPrinAmt = Number(0);
                    //券面总额合计  不计算折算率
                    var totalPrinAmtSum = Number(0);
                    for (var i in pledgeRows) {
                        var obj = pledgeRows[i];
                        totalPrinAmt += obj.pledgePrin * obj.pledgeRatio / 100;
                        totalPrinAmtSum += obj.pledgePrin / 10000; //以万元为单位
                        if(obj.repRegistOrg=='01'){
                            zzdPrinAmt+=obj.pledgePrin * obj.pledgeRatio / 100;
                        }
                        if(obj.repRegistOrg=='02'){
                            sqsPrinAmt+=obj.pledgePrin * obj.pledgeRatio / 100;
                        }
                        pledgeBondTotalTblDetailed.addRow(obj);
                    }
                    //多组合  可质押券面额和质押券面额 以万元为单位
//                    if(isDzh == 'Y'){
                        totalPrinAmtSum = totalPrinAmtSum*10000;
                        totalPrinAmt = totalPrinAmt*10000;
                        zzdPrinAmt = zzdPrinAmt*10000;
                        sqsPrinAmt = sqsPrinAmt*10000;
//                    }
//                    getElementById("totalPrinAmt").val(totalPrinAmt).trigger('change').trigger('blur');
//                    getElementById("zzdTotalPrinAmt").val(zzdPrinAmt).trigger('change').trigger('blur');
//                    getElementById("sqsTotalPrinAmt").val(sqsPrinAmt).trigger('change').trigger('blur');
                    $("#zzdPrinAmt").val(zzdPrinAmt).trigger('change').trigger('blur');
                    $("#sqsPrinAmt").val(sqsPrinAmt).trigger('change').trigger('blur');
                    //更新CFETS质押式回购报价要素信息
//                    refreshParentFace({totalPrinAmt:totalPrinAmtSum,shareCtAmt:zzdPrinAmt+sqsPrinAmt});
                });
            }
        };
        
        window.sss="sss";
        pledgeBondTotalTblDetailed.load();
        if(!window.BussRepoSec){
        	window.BussRepoSec={};
        }
        window.BussRepoSec['bussRepoSec'] = {
//                setInputParam: setInputParam,
//                clearHideRepo: clearHideRepo,
//                setStlCommandInput:setStlCommandInput,
//                showRepo: showRepo,
//                showCash: showCash,
                addPledgeBond: addPledgeBond,
//                refreshCashAndBalance: refreshCashAndBalance,
//                "pledgeBondTotalTblDetailed":pledgeBondTotalTblDetailed,
//                "pledgeBondTotalTblDetailed":pledgeBondTotalTblDetailed,
//                reloadPledgeBond: reloadPledgeBond,
//                extractedJiSuan:extractedJiSuan,
//                extractedCpriceAmt:extractedCpriceAmt,
//                entrustSurAmtAll:entrustSurAmtAll,
//                extractedTime:extractedTime
            };
    }

    /**
     * 获取期限品种  date(yyyy-MM-dd),repoterm
     */
    function getTermType(date,repoterm) {
        if(repoterm&&repoterm<=21){
            //期限品种小于21天
            if(repoterm==0){
                Joyin.error("期限品种不能为0");
                return ;
            }
            if(repoterm==1){
                return R001;
            }
            if(repoterm>1&&repoterm<=7){
                return R007;
            }
            if(repoterm>7&&repoterm<=14){
                return R014;
            }
            if(repoterm>14&&repoterm<=21){
                return R021;
            }
        }
        var day=0;
        //date为空时  返回0
        if(date&&repoterm){
            //获取日期
            date =DateUtil.parse(date);
            //获取之后月00天 即本月最后一天
            day= new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+1,0).getDate();
            if(repoterm<=day){
                return R1M;
            }
            day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+2,0).getDate();
            if(repoterm<=day){
                return R2M;
            }
            day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+3,0).getDate();
            if(repoterm<=day){
                return R3M;
            }
            day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+4,0).getDate();
            if(repoterm<=day){
                return R4M;
            }
            day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+5,0).getDate();
            day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+6,0).getDate();
            if(repoterm<=day){
                return R6M;
            }
            day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+7,0).getDate();
            day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+8,0).getDate();
            day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+9,0).getDate();
            if(repoterm<=day){
                return 'R9M';
            }
            day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+10,0).getDate();
            day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+11,0).getDate();
            day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+12,0).getDate();
            if(repoterm<=day){
                return 'R1Y';
            }
        }
        return null;
    }
    
    $('#vdate').change(function(){
        var vdateAdd = $('#vdate').val();
        var repoTerm = $('#repoTerm').val();
        var mdateAdd = $('#mdate').val();
        if(vdateAdd && repoTerm){
            var d1 = Date.ofDate(vdateAdd).getTime();
            var day = parseInt($('#repoTerm').val()*24*60*60*1000);
            var mdateAdd = Date.ofDate(d1+day);
            $('#mdate').val(mdateAdd.format("yyyy-MM-dd")).change();
        }
        if(vdateAdd && mdateAdd){
            if(Date.ofDate(vdateAdd).getTime()>Date.ofDate(mdateAdd).getTime()){
                Joyin.error("起息日不能大于到期日");
                $("#vdate").val('');
                return;
            }
            var datev = Date.ofDate(vdateAdd).getTime();
            var datem = Date.ofDate(mdateAdd).getTime();
            var total = (datem-datev)/1000;
            var day = parseInt(total / (24*60*60));
            $('#repoTerm').val(day);
        }
        
    });
    
    /*$('#mdate').change(function(){
        var vdateAdd = Date.ofDate($('#vdate').val());
        var mdateAdd = Date.ofDate($('#mdate').val());
        if(vdateAdd && mdateAdd){
            if(vdateAdd.getTime()>mdateAdd.getTime()){
                Joyin.error("到期日不能小于起息日");
                $("#mdate").val('');
                return;
            }
            var datev = Date.ofDate(vdateAdd).getTime();
            var datem = Date.ofDate(mdateAdd).getTime();
            var total = (datem-datev)/1000;
            var day = parseInt(total / (24*60*60));
            $('#repoTerm').val(day);
        }
    });*/
    
    /*$('#termType').change(function(){

        if ($('#tradeMarket').val() !== '01'){
            var termType = $('#termType').val();
            if(termType == GC001){
                $('#repoTerm').val(1).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == GC002){
                $('#repoTerm').val(2).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == GC003){
                $('#repoTerm').val(3).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == GC004){
                $('#repoTerm').val(4).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == GC007){
                $('#repoTerm').val(7).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == GC014){
                $('#repoTerm').val(14).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == GC028){
                $('#repoTerm').val(28).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == GC091){
                $('#repoTerm').val(91).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == GC182){
                $('#repoTerm').val(182).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R001){
                $('#repoTerm').val(1).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R007){
                $('#repoTerm').val(7).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R014){
                $('#repoTerm').val(14).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R021){
                $('#repoTerm').val(21).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == RC001){
                $('#repoTerm').val(1).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == RC003){
                $('#repoTerm').val(3).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == RC007){
                $('#repoTerm').val(7).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R_001){
                $('#repoTerm').val(1).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R_002){
                $('#repoTerm').val(2).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R_003){
                $('#repoTerm').val(3).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R_004){
                $('#repoTerm').val(4).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R_007){
                $('#repoTerm').val(7).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R_014){
                $('#repoTerm').val(14).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R_028){
                $('#repoTerm').val(28).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R_091){
                $('#repoTerm').val(91).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R_182){
                $('#repoTerm').val(182).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R1M){
                $('#repoTerm').val(30).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R2M){
                $('#repoTerm').val(60).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R3M){
                $('#repoTerm').val(90).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R4M){
                $('#repoTerm').val(120).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else if(termType == R6M){
                $('#repoTerm').val(180).attr('disabled',true).change();
                $('#mdate').attr('disabled',true);
            }else{
                $('#mdate').attr('disabled',false);
                $('#repoTerm').attr('disabled',false);
            }
        }

    });*/
    /**
     * 价格模式
     */
    $('#priceModel').change(function(){
        if($('#priceModel').val() == PRICEMODEL_03){
            $('#price').val('').attr('disabled',true);
            $('#price').removeAttr("required");
        }else{
        	$('#price').attr('disabled',false);
            $('#price').attr("required", "true");
        }
        star();
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
        object.businessId = $('#businessId').val();
        object.pageModel = pageModel;
        object.entrustId = $('#entrustId').val();
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
        var entrustAmt = 0;
        var finprodId = null;
        var counterId = null;
        var tradeMarket = null;
        var ps = null;
        var param = {};
        for(var index in object.bussCombDistribtList){
            var item = object.bussCombDistribtList[index];
            var temp = JSON.parse(JSON.stringify(object));
            temp.portfolioId = item.portfolioId;
            temp.zyqk = item.zyqk;
            temp.entrustAmt = item.entrustAmt;
            counterId = temp.counterId;
            tradeMarket = temp.tradeMarket;
            temp.entrustId = item.entrustId;
            ps = temp.ps;
            temp.invAim = item.invAim;
            temp.secAcctId = item.secManageAcctId;
            temp.proAvailableCash = item.proAvailableCash;
            temp.accAvailableCash = item.secManageAvailablePosition;
            temp.assetAvailablePosition  = item.assetAvailableEntrustAmt;
            temp.elementId = 'EtribRepoInfo003_btn_submit';

            objects.push(temp);
        }
        param = JSON.parse(JSON.stringify(object));
        param.entrustAmt = object.tradeAmt;
        param.counterId = counterId;
        param.tradeMarket = tradeMarket;
        param.invManagerId = $('#invManagerId').val();
        param.ps = ps;
        param.ccy = $('#ccy').val();
        param.etrInvestRepoInfoDtos = objects;
        param.bussCombDistribtList = object.bussCombDistribtList;
        // add by zhangrui1 start
        param.operateType = operateType;
        param.pageModel = pageModel;
        param.riskTrialRetDto = object.riskTrialRetDto;
        param.elementId = 'EtribRepoInfo003_btn_submit';

        res.param = param;
        res.objects = objects;
        return res;
    }



    /**
     * 重置
     * @returns
     */
    function doReset() {
        $("#ps").val('').trigger('change');
        $("#tradeAmt").val('').trigger('change');
        $("#settleSpeed").val('').trigger('change');
        $("#priceModel").val('').trigger('change');
        $("#price").val('').trigger('change');
        $("#counterId").val('').trigger('change');
        $("#chlFinprodId").val('').trigger('change');
        $("#repoTerm").val("");
        $("#zyqk").val("").trigger("change");
        $("#termType").val("").trigger("change");
        $("#remark").val('').trigger('change');
        var platFormDate = $("#vdate").val();
        $("#mdate").val(platFormDate).trigger('change');
        $("#settleSpreed").val('').trigger('change');
        if(repo_pledge_model =='03'){
        	$("#zyqk").val("").trigger("change");
        }else if (repo_pledge_model =='02'){
        	pledgeBondTotalTblDetailed.clear();
        }
        table.clear();
    }

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
                    entrustAmt: item.entrustAmt,
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
                // add by zhangrui1 交易对手影响风险试算 end
                var object = {
                    tradeDirection: ['ps', 'EtribRepoInfo003.ps'],
                    portfolioCode: ['portfolioId', 'EtribRepoInfo003.portfolioId'],
                    entrustAmt: ['entrustAmt', 'EtribRepoInfo003.entrustAmt'],
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
            // add by zhangrui1 交易对手影响风险试算 start
            obj.counterId = $('#etrInvestPersonInfo_counterId').val();
            // add by zhangrui1 交易对手影响风险试算 end
            var object = {
                tradeDirection: ['ps', 'EtribRepoInfo003.ps'],
                portfolioCode: ['portfolioId', 'EtribRepoInfo003.portfolioId'],
                entrustAmt: ['entrustAmt', 'EtribRepoInfo003.entrustAmt'],
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

        Joyin.dialog(JoyinUtil.getLabel('EtribRepoInfo003.fileUpload'), '/invest/entrust/etribrepo/EtrIbRepoinfoFile/init', '60%', '75%',object);
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
                item.data.counterId = object.counterId;
                item.data.ps = object.ps;
                item.data.ccy = 'CNY';
                item.data.portfolioId = item.data.portfolioCode;
                item.data.price=object.price;
                item.data.entrustAmt=item.data.entrustAmt;
                param.push(item.data);
            }
        }
        window.BaseRiskMathComm.initBaseRiskMathRedis(basePath + 'riskrds/interf/batchRiskTrialRds/0/F07', param, checkParam,riskMathRedisCallback003);
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
        Joyin.dialog(JoyinUtil.getLabel('EtribRepoInfo003Riskcal'), '/invest/entrust/etribrepo/EtribRepoInfo003Riskcal/init', '60%', '75%',null);
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
        var res = EtrIbRepoinfo003.getSubmitParam(operateType)

        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', res.param, function(result1) {
            if(res.objects.length>0){
                Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtribRepoInfo003/check',res.objects,
                    function(result) {
                        Joyin.confirm(result.data,function() {
                            if (operateType === "save") {
                                WorkFlow.doSaveList(basePath+'invest/entrust/etribrepo/EtribRepoInfo003/save',res.param,"EtribRepoInfo003_btn_save","EtribRepoInfo003");
                                EtrIbRepoinfo003.doReset();
                            } else {
                                WorkFlow.doSubmitList(basePath+'invest/entrust/etribrepo/EtribRepoInfo003/save',res.param,"EtribRepoInfo003_btn_submit","EtribRepoInfo003");
                                EtrIbRepoinfo003.doReset();
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
            } else {
                $("#price").val("").attr("disabled", false);
            }
        }
    });
    
    /**
     * 价格模式
     */
    $("#ps").change(function () {
        if (pageModel != PAGE_MODEL_REFERENCE) {
        	if (repo_pledge_model =='02'){
        		if ($("#ps").val() == ps_04) {
        			pledgeBondTotalTblDetailed.clear();
        			$("#pledgeBondTotalTblDetailed_div").hide()
        		}else{
        			$("#pledgeBondTotalTblDetailed_div").show()
        		}
        	}
        }
    });

    // 绑定提交按钮
    $('#EtribRepoInfo003_btn_submit').click(function () {
        saveOrSubmit('submit');
    });

    // 绑定保存按钮
    $('#EtribRepoInfo003_btn_save').click(function () {
        saveOrSubmit('save');
    });

    // 绑定取消按钮
    $('#EtribRepoInfo003_btn_reset').click(function () {
        doReset();
    });

    // 绑定风险按钮
    $('#EtribRepoInfo003_btn_riskcal').click(function () {
        doRiskcal();
    });

    $('#EtribRepoInfo003_btn_fileupload').click(function () {
        fileUpload();
    });
}());