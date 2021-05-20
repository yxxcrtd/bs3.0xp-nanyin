(function(){
    /**
     * 是否快速查询
     * @type {string}
     */
    var isQuickFlag = true;
    var settleStatus = '';
    var settleSeqNo = '';
    /**
     * 获取查询参数
     */
    var searchform =function(){
        if(isQuickFlag){
            return {"settleStatus":settleStatus};
        }else{
            var data = $.serializeToObject('#searchForm');
            if ($("#portfolioId").val()) {
                data.portfolioId = $("#portfolioId").val().join(",");
            } else {
                data.portfolioId = null;
            }
            data.tradeDateStart = $('#tradeDate').startDate();//开始日
            data.tradeDateEnd = $('#tradeDate').endDate();//结束日
            data.settleDateStart = $('#settleDate').startDate();//开始日
            data.settleDateEnd = $('#settleDate').endDate();//结束日
            return data;
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        height:'300',
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return searchform();
            }
        },
        click:function(table, data) {
            data.operateType = "click";
            settleSeqNo = data.settleSeqNo;
            dataTableDetail.table == null ? dataTableDetail.load() : dataTableDetail.reload();
        },
        rowno:true,
        cols:[
            {field:'settleSeqNo',title: '待交割序号', width:'150', visible:false}
            ,{field:'settleStatus', visible:false}
            ,{field:'settleStatusStr', title: '交收状态', width:'150',sortField:'settleStatus'}
            ,{field:'entrustNo', title: '指令序号', width:'150'}
            ,{field:'orderId', title: '指令序号', width:'150', visible:false}
            ,{field:'tradeId', title: '原业务编号', width:'150'}
            ,{field:'tradeDate', title: '交易日期', width:'150', type:'date'}
            ,{field:'settleDate', title: '交收日期', width:'150', type:'date'}
            ,{field:'portfolioId', title: '组合代码', width:'150'}
            ,{field:'portfolioName', title: '组合名称', width:'150'}
            ,{field:'acctNo', title: '资金账户', width:'150'}
            ,{field:'secAcctId', title: '证券账户', width:'150'}
            ,{field:'tradeMarket', visible:false}
            ,{field:'tradeMarketStr', title: '交易市场', width:'150',sortField:'tradeMarket'}
            ,{field:'tradePlat', visible:false}
            ,{field:'tradePlatStr', title: '交易平台', width:'150',sortField:'tradePlat'}
            ,{field:'finprodId',visible:false}
            ,{field:'finprodMarketId', title: 'EtrTransactionDetail001.finprodId', width:'150'}
            ,{field:'finprodName', title: 'EtrTransactionDetail001.finprodName', width:'150'}
            ,{field:'settleNum',title: '交割数量', width:'150', type:'money'}
            ,{field:'settleAmt', title: '交割金额', width:'150', type:'money'}
            ,{field:'busiType', title: '业务类别', width:'150', visible:false}
            ,{field:'busiTypeStr', title: '业务类别', width:'150',sortField:'busiType'}
            ,{field:'counterId', title: '交易对手', width:'150', visible:false}
            ,{field:'counterName', title: '交易对手', width:'150'}
        ],
        done: function(res){
            $('#dataTable').next('.layui-table-view').find('.layui-table-header').eq(1).find('th[data-field="1"]').find('span').html("序号");
            if(dataTableDetail&&dataTableDetail.table){
                dataTableDetail.table?dataTableDetail.clear():'';
            }
        }
    });
    /**
     * 定义成交明细数据表格
     */
    var dataTableDetail = $("#dataTableDetail").joyintable({
        ajax: {
            url: 'queryForFlowPage',
            type: 'POST',
            data: function() {
                return {settleSeqNo:settleSeqNo};
            }
        },
        loadonce:true,
        checkbox:false,
        rowno:true,
        cols:[
            {field:'seqNo', title: '交收流水序号', width:'150', visible:false}
            ,{field:'settleDateStr', title: '交收日期', width:'150', type:'date'}
            ,{field:'settleTimeStr', title: '交收时间', width:'200'}
            ,{field:'finprodMarketId', title:'资产代码', width:'150'}
            ,{field:'finprodName', title:'资产名称', width:'150'}
            ,{field:'settleNum',title:'交收数量', width:'150', type:'money'}
            ,{field:'settleAmt',title:'交收金额', width:'150', type:'money'}
            ,{field:'operType', title:'操作类型', width:'200', visible:false}
            ,{field:'operTypeStr', title:'操作类型', width:'200'}
            ,{field:'operator',title:'操作员', width:'150'}
        ],
        done: function(res){
            $('#dataTableDetail').next('.layui-table-view').find('.layui-table-header').eq(1).find('th[data-field="0"]').find('span').html("序号");

        }
    });
    /**
     * 导出
     */
    var exportExcel = function(){
        //勾选导出
        var paramObj = {};
        Joyin.exportExcel(basePath + "invest/trade/manualsettle/ManualSettle001/export", paramObj);
    };

    var myrefresh = function(){
        table.table ? table.reload() : table.load();
    }


    /**
     * 操作
     */
    var settle = function(item){
        //存在异常数据
        var exceptionArr = [];
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        for(var i=0;i<selArr.length;i++){
            if((item=="01"||item=="03"||item=="04")&&(selArr[i].settleStatus==DELIVERY_STATUS_02||selArr[i].settleStatus==DELIVERY_STATUS_04)){
                exceptionArr.push(selArr[i]);
            }
            else if(item=="02"&&selArr[i].settleStatus!=DELIVERY_STATUS_02){
                exceptionArr.push(selArr[i]);
            }
        }
        for(var i=0;i<selArr.length;i++){
            selArr[i].pageModel = item;
        }
        var object = {selectedItem: selArr, pageModel:item};
        var confirmMessage;
        if(exceptionArr.length>0) {
            if (item == "01") {
                confirmMessage = JoyinUtil.getMessage("MANUAL_SETTLE_001");
            }
            if (item == "02") {
                confirmMessage = JoyinUtil.getMessage("MANUAL_SETTLE_002");
            }
            if (item == "03") {
                confirmMessage = JoyinUtil.getMessage("MANUAL_SETTLE_003");
            }
            if (item == "04") {
                confirmMessage = JoyinUtil.getMessage("MANUAL_SETTLE_004");
            }
            //是:不操作  否:继续操作
            Joyin.confirm(confirmMessage, function () {
                    cancelCheck(exceptionArr);
            });
        }else{
            confirmMessage = JoyinUtil.getMessage("COMMON_C_0001","提交");
            //正常提交
            Joyin.confirm(confirmMessage,function(){
                finalSubmit(object);
            });
        }
    }

    /**
     * 最终提交
     * @param object
     */
    var finalSubmit = function(object){
        WorkFlow.doDeleteList(basePath + 'invest/trade/manualsettle/ManualSettle001/settle', object, "ManualSettle001_btn_settle", "ManualSettle001", function(){
            table.reload();//回调函数
        });
    }

    /**
     * 取消选中
     */
    var cancelCheck = function(exceptionArr){
        var tr = $('#dataTable').next('.layui-table-view').find('.layui-table-main').find('tr.table-row-select');
        for(var i=0;i<tr.length;i++){
            var index = tr.eq(i).data('index');
            var td = tr.eq(i).find('td[data-field="settleSeqNo"]').find('div').html();
            for(var j=0;j<exceptionArr.length;j++){
                if(td==exceptionArr[j].settleSeqNo){
                    var fixedCheckBox = $('#dataTable').next('.layui-table-view').find('.layui-table-fixed').find('tr[data-index="' + index + '"]').find(':checkbox[name="layTableCheckbox"]');
                    fixedCheckBox.next().trigger('click');
                    break;
                }
            }
        }
        //table.uncheck();
    }
    /**
     * 编辑按钮事件
     */
    $(function () {
        if(pageModel == PAGE_MODEL_NEW && isQuickFlag ){
            table.load();
        }


        /**
         * 筛选-隐藏显示查询条件
         */
        $('#ManualSettle001_btn_filter').click(function(){
            if($(".condition-area").css("display")=="none"){
                $(".condition-area").show();
            }else{
                $(".condition-area").hide();
            }
        });

        /**
         * 查询按钮
         */
        $('#ManualSettle001_btn_search').click(function() {
            isQuickFlag = false;
            table.load();
        });
        /**
         * 导出按钮
         */
        $('#ManualSettle001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 交收
         */
        $('#ManualSettle001_btn_settle').click(function () {
            settle("01");
        })
        /**
         * 回溯
         */
        $('#ManualSettle001_btn_back').click(function () {
            settle("02");
        })
        /**
         * 交收延期
         */
        $('#ManualSettle001_btn_settledelay').click(function () {
            settle("03");
        })
        /**
         * 交收失败
         */
        $('#ManualSettle001_btn_settlefail').click(function () {
            settle("04");
        })
        $('#settleStatus_div').find('input[name="settleStatus"]').click(function () {
            settleStatus = $(this).val();
            isQuickFlag = true;
            table.table == null ? table.load() : table.reload();
        })

        /**
         * 刷新列表
         */


        layui.form.on('radio(settleStatus)',function(data){
            myrefresh();
        })
    });
}());