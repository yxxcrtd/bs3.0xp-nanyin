(function () {

    var getSearchParams = function(){
        var data = {
            finprodList :$("#finprodList").val()
        };
        return data;
    }

    /**
     * 定义数据表格
     */
    var buttons = [{ text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }];
    var bondList = {};
    var finprodList = $("#finprodList").val();
    if(finprodList != null && finprodList != '' && finprodList != undefined){
        bondList = JSON.parse(finprodList);
    }
    var bondTable = $("#bondTable").joyintable({
        // ajax请求数据
        /*ajax: {
            // 请求路径
            url: 'selectBondForPage',
            // 请求类型
            type: 'POST',
            data: function () {
                // 获取请求参数
                return getSearchParams();
            }
        },*/
        data:bondList,
        searchOnInit: true,
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:false,
        height : 200,
        cols:[
            {field:'finprodId', width:120, title: 'FxSecrepoQuote002.bondTable.finprodId', sortable: false}
            ,{field:'finprodAbbr', width:120, title: 'FxSecrepoQuote002.bondTable.finprodAbbr', sortable: false,}
            ,{field:'repoAmt', width:120, title: 'FxSecrepoQuote002.bondTable.repoAmt',edit:true,type:'number'}
            ,{field:'dscCostAmt', width:120, title: 'FxSecrepoQuote002.bondTable.dscCostAmt',sortable: false, type:'number'}
            ,{field:'faceTotalAmt', width:120, title: 'FxSecrepoQuote002.bondTable.faceTotalAmt', sortable: false,type:'number'}
            ,{field:'trustTotalAmt', width:120, title: 'FxSecrepoQuote002.bondTable.trustTotalAmt',sortable: false,type:'number'}
            ,{field:'convrate', width:120, title: 'FxSecrepoQuote002.bondTable.convrate',type:'number', sortable: false}
            ,{field:'netprice', width:120, title: 'FxSecrepoQuote002.bondTable.netprice',type:'number', sortable: false}
            ,{field:'cdate', width:120, title: 'FxSecrepoQuote002.bondTable.cdate',type:'date' ,sortable: false}
            ,{field:'trustee', width:120, title: 'FxSecrepoQuote002.bondTable.trustee', sortable: false}
            ,{field:'mdate', width:120, title: 'FxSecrepoQuote002.bondTable.mdate', sortable: false,type:'date'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons}
        ],
        editCell : function(value, data, field, cell, column){

        }
    });
    /**
     * 绑定删除事件
     */
    bondTable.bind('click', '.delt', function(data, index) {
        bondTable.removeRow(data);
    });

    // 添加质押券按钮点击事件
    $('#bond_btn_create').click(function() {
        var bondList = JSON.stringify(bondTable.getAllRows());
        var trustid = $("#trustid").val();
        if(trustid == null || trustid == "" || trustid == undefined){
            JoyinUtil.showMessage("委托编号不能为空！","error");
            return;
        }
        Joyin.dialog(JoyinUtil.getLabel("FxSecrepoQuote003"),
            "/interf/cfetsup/secrepoquote/FxSecrepoQuote003/init",
            "88%", "85%", {pageModel:PAGE_MODEL_NEW,bondList:bondList,"entrustId":trustid}, function(data) {
                if(!data) return;
                var result = data.data;
                var selArr = result.seclist;
                $.each(selArr, function(i,object){
                    var row = {
                        finprodId: object.finprodId,
                        finprodAbbr: object.finprodAbbr,
                        repoAmt: object.repoAmt,
                        dscCostAmt: object.dscCostAmt,
                        faceTotalAmt: object.faceTotalAmt,
                        trustTotalAmt: object.trustTotalAmt,
                        convrate: object.convrate,
                        netprice: object.netprice,
                        cdate : object.cdate,
                        trustee: object.trustee,
                        mdate: object.mdate
                    };
                    //添加一行
                    bondTable.addRow(row);
                });

            });
    });

    /**
     * 查询组合T日可用现金
     */
    function getUsableCash() {
        var tradeDate = $("#dealdate").val();
        if(tradeDate == null || tradeDate == ''){
            return;
        }
        var portfolioId = $("#portfolioId").val();
        if(portfolioId == null || portfolioId == ''){
            return;
        }
        var object = {
            tradeDate : tradeDate,
            portfolioId : portfolioId,
            ccy:CCY,
            calendarId:TRANSACTION_CALENDAR
        }
        Joyin.ajaxJson(basePath + 'component/trade/BussTrdAvailableCashQuery/getAvailableCash', object, function(result) {
            if(result){
                $("#avalibleCash").val(result.T_0);
            }
        });
    }

    function init() {
        $("#replyTypeDiv").hide()
        if(pageModel == '3'){
            //回复报价
            $("#replyTypeDiv").show()
            $(".form-input").attr("disabled",true);
            $("#ratebasic").attr("disabled",false);
        }else if(pageModel == '4'){
            //修改报价
            $(".form-input").attr("disabled",true);
        }else if(pageModel == '5'){
            //交谈报价
            $(".form-input").attr("disabled",true);
            $("#ratebasic").attr("disabled",false);
        }else if(pageModel == '6'){
            //撤销报价
            $(".form-input").attr("disabled",true);
        }
        getUsableCash();
    }

    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        var bondList;
        if (bondTable != null && bondTable != '') {
            bondList = bondTable.getAllRows();
        }
        if(bondList == null && bondList.length <= 0){
            JoyinUtil.showMessage("请选择质押券！","error");
            return;
        }
        if(pageModel == '3'){
            //回复报价
            var replyType = $("#replyType").val();
            if(replyType == null || replyType == ""|| replyType == undefined){
                JoyinUtil.showMessage("请选择回复类型！","error");
                return;
            }
        }
        object.finprodList = bondList;
        object.pageModel = pageModel;
        object.operateType1 = operateType;
        Joyin.ajaxJson(basePath + 'interf/cfetsup/secrepoquote/FxSecrepoQuote002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if (operateType === 'save') {
                    //保存
                    WorkFlow.doSaveList(basePath + 'interf/cfetsup/secrepoquote/FxSecrepoQuote002/save', object, "FxSecrepoQuote002_btn_save", "FxSecrepoQuote002");
                } else if (operateType === 'submit') {
                    //提交
                    WorkFlow.doSubmitList(basePath + 'interf/cfetsup/secrepoquote/FxSecrepoQuote002/save', object, "FxSecrepoQuote002_btn_submit", "FxSecrepoQuote002");
                } else if (operateType === 'revoke') {
                    //撤销
                    WorkFlow.doRevokeList(basePath + 'interf/cfetsup/secrepoquote/FxSecrepoQuote002/doRevoke', object, "FxSecrepoQuote002_btn_revoke","FxSecrepoQuote002");
                }
            });
        });
    }

    $(function () {
        init();
        // 绑定保存按钮事件
        $('#FxSecrepoQuote002_btn_save').click(function() {
            saveOrSubmit('save');
        });

        // 绑定提交按钮事件
        $('#FxSecrepoQuote002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        // 组合代码联动事件
        $('#portfolioId').change(function() {
            var portfolioName = $("#portfolioId").val();
            $("#portfolioName").val(portfolioName);
            $("#portfolioName").textSelect('setValue', portfolioName)
            getUsableCash();
        });

        //放大镜点击选择委托
        $('#magnifier').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("FxSecrepoQuote004"),
                "/interf/cfetsup/secrepoquote/FxSecrepoQuote004/init", "88%", "85%",
                {pageModel:PAGE_MODEL_NEW}, function(result) {
                    var data = result.data;
                    if(data != null && data != undefined){
                        $.each(data, function(id,value){
                            if(id == "finprodList"){
                                //质押券信息
                                bondList = {};
                                bondTable.reload();
                                var rows = bondTable.getAllRows();
                                if(rows != null && rows.length > 0){
                                    $.each(rows,function (i,row) {
                                        bondTable.removeRow(row);
                                    });
                                }
                                $.each(value, function(i,object){
                                    var row = {
                                        finprodId: object.finprodId,
                                        finprodAbbr: object.finprodAbbr,
                                        repoAmt: object.repoAmt,
                                        dscCostAmt: object.dscCostAmt,
                                        faceTotalAmt: object.faceTotalAmt,
                                        trustTotalAmt: object.trustTotalAmt,
                                        convrate: object.convrate,
                                        netprice: object.netprice,
                                        cdate : object.cdate,
                                        trustee: object.trustee,
                                        mdate: object.mdate
                                    };
                                    //添加一行
                                    bondTable.addRow(row);
                                });
                            }else{
                                var element = $("#"+id);
                                if(element.hasClass("input-select")){
                                    //下拉框
                                    element.val(value).trigger('change');
                                    element.textSelect('setValue', value);
                                }else{
                                    element.val(value);
                                }
                            }
                        });
                    }
                });
        });

        //交易日期联动事件
        $('#dealdate').change(function() {
            getUsableCash();
        });
    });
}());
