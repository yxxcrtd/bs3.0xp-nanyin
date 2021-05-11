(function() {

    var getPageParams = function() {
        return {
            prodId:$('#prodId').val()==null?null:$('#prodId').val().join(","),
            tradeCode:$('#tradeCode').val()==null?null:$('#tradeCode').val(),
            assetCode:$('#assetCode').val()==null?null:$('#assetCode').val().join(","),
            status:$('#status').val()==null?null:$('#status').val().join(","),
            dealdateStart : $('#dealdate').val()==null?null:$('#dealdate').startDate(),
            dealdateEnd : $('#dealdate').val()==null?null:$('#dealdate').endDate(),
        };
    };

    var table = $('#InvestmentDealInfo001_prorate').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cols: [
            { title: "数据状态", field:'statusName',sortField:'status',width:'8%'},
            { title: "status",  visible:false, field:'status'},
            { title: "行内交易编码", field:'tradeCode',width:'8%',sortField:'tradeCode'},
            { title: "产品代码", field:'prodId',width:'8%',sortField:'prodId'},
            { title: "行内资产/负债编码", field:'assetCode',width:'8%',sortField:'assetCode'},
            { title: "币种", field:'ccyStr',width:'8%',sortField:'ccy'},
            { title: "发生金额", field:'amt',width:'8%',sortField:'amt'},
            { title: "数量", field:'num',width:'8%',sortField:'num'},
            { title: "资产计量方式", field:'assetMethodStr',width:'8%',sortField:'assetMethod'},
            { title: "资金流动类型", field:'curAssetTypeStr',width:'8%',sortField:'curAssetType'},
            { title: "资金流动类型说明", field:'curAssetdetType',width:'8%',sortField:'curAssetdetType'},
            { title: "交易日", field:'dealdate',width:'8%',sortField:'dealdate',type:'date'},
            { title: "交易对手", field:'tradeCounter',width:'8%',sortField:'tradeCounter'},
            { title: "交易对手方类型", field:'rivalPartyTypeStr',width:'8%',sortField:'rivalPartyType'},
            { title: "单位成交价格（全价）", field:'unitPriceFull',width:'8%',sortField:'unitPriceFull'},
            { title: "单位成交价格（净价）", field:'unitPriceNet',width:'8%',sortField:'unitPriceNet'},
            { title: "到期收益率", field:'endRate',width:'12%',sortField:'endRate'},
            { title: "成交编号/合同号", field:'contractCode',width:'12%',sortField:'contractCode'},
            { title: "备注", field:'remark',width:'12%',sortField:'remark'},
            { title: "创建人", field:'createUser',width:'8%',sortField:'createUser'},
            { title: "创建时间", field:'createTime',type:'date',width:'12%',sortField:'createTime'},
            { title: "更新人", field:'updateUser',width:'8%',sortField:'updateUser'},
            { title: "更新时间", field:'updateTime',type:'date', width:'8%',sortField:'updateTime'},
            { title: "数据来源", field:'dataSourceName', width:'10%',sortField:'dataSource'},
            { title: "dataSource",  visible:false, field:'dataSource'},
            { title: "uuid",  visible:false, field:'uuid'},
            {
                title : '操作',
                field : 'operation',
                type : 'action',
                width : 130,
                buttons : [
                    {text: '&#xe642;',clas: 'edit',title: '编辑'},
                    {text: '&#xe640;',clas: 'delt',title: '删除'}
                    ]
            }

        ]
    });

    /**
     * 事件绑定
     */
    var tableBind = function () {
        /**
         * 绑定事件：修改
         */
        table.bind('click', '.edit', function(data, index) {
            if(data.tradeCode == null || data.tradeCode == '' || data.tradeCode == undefined){
                Joyin.error("行内交易编码为空,数据不允许编辑");
                return;
            }else{
                data.pageModel = PAGE_MODEL_EDIT;
                data.type="update";
                Joyin.dialog(JoyinUtil.getLabel("编辑投资交易信息"),
                    basePath + "svreport/system/investmentdeal/InvestmentDealInfo002/init",
                    "95%", "95%", data,function(){
                        table.reload();
                    });
            }

        });

        /**
         * 绑定事件：删除
         */
        table.bind('click', '.delt', function(data, index) {
            var listdto=[data];
            var error='';
            var s='D';
            for(var i=0; i<listdto.length; i++){
                    if(listdto[i].status.indexOf("D")!= -1){
                    error = "选择的数据中包含数据状态为删除的数据,请重新选择"+"<br>";
                }
            }
            if(error != '' && error != null){
                Joyin.error(error);
                return;
            }
            var object = {selectedItem: listdto, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'svreport/system/investmentdeal/InvestmentDealInfo001/deleteData', object, function(result) {
                if(result.message=='删除成功'){
                    table.load();
                }else{
                    alert(result.message);
                }
            });
        });

    };

    $(function() {
        // 导入按钮点击事件
        $('#InvestmentDealInfo001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath + 'svreport/system/investmentdeal/InvestmentDealInfo001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                        Joyin.ajax(basePath + 'svreport/system/investmentdeal/InvestmentDealInfo001/importFile', {fileName: fileName});
                    });
                },function() {
                    table.reload();
                });
            });
        });

        // 导出按钮点击事件
        $('#InvestmentDealInfo001_btn_export').click(function() {
            var param ={};
            var rows = table.getAllCheckedRows();
            if(rows.length > 0) {
                var keyList = [];
                $.each(rows, function(i, row) {
                    keyList.push(row.counterId);
                });
                param.keys = keyList.join();
            }
            else{
                //有勾选优先勾选数据
                param = getPageParams();
            }
            Joyin.exportExcel("export",param);
        });
        //删除点击事件
        $('#InvestmentDealInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var errorMessage='';
            for(var i=0; i<selArr.length; i++){
                if(selArr[i].status.indexOf("D")!= -1){
                    errorMessage = "选择的数据中包含数据状态为删除的数据,请重新选择"+"<br>";
                }
            }
            if(errorMessage != '' && errorMessage != null){
                Joyin.error(errorMessage);
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'svreport/system/investmentdeal/InvestmentDealInfo001/deleteData', object, function(result) {
                if(result.message=='删除成功'){
                    table.load();
                }else{
                    alert(result.message);
                }
            });
        });
        $('#InvestmentDealInfo001_btn_search').click(function() {
            table.load();
        });
        //新增点击事件
        $('#InvestmentDealInfo001_btn_add').click(function() {
            var data={"operateType":"add"};
            Joyin.dialog('投资交易信息新建',basePath+'svreport/system/investmentdeal/InvestmentDealInfo002/init', '98%', '95%',data,function(){table.reload();});
        });

        //修改、删除事件绑定
        tableBind();
    });
}());