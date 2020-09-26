$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId2: $.trim($('#finprodId2').val()),
            valDateBegin: $.trim($('#valDate').startDate()),
            valDateEnd: $.trim($('#valDate').endDate()),
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看利率互换估值信息"),
                basePath + "valuation/basedata/marketdata/rateswapvaluationinfo/ValRateSwapValuationInfo002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE,
                    finprodId2: data.finprodId2, datasourceType: data.datasourceType, valDate: data.valDate, operateType:"view"},function(){
                    table.reload();
                });
        },
        checkbox: true,
        cellMinWidth : 100,
        cols: [
            {field:'finprodId2', width:200, title: '交易编号' },
            {field:'valDate', width:200, type:'date', title: '估值日期' },
            {field:'valuation', width:200, type:'money', title: '估值' ,
                render : function(data, type, row, meta) {
                    if(row.valuation){
                        return  formatMoney(row.valuation);
                    }
                    else{
                        return formatMoney(0.00);
                    }
                }
            },
            {field:'ccy', width:200, title: '币种' , visible: false},
            {field:'ccyFmt', sortField: 'ccy', width:200, title: '币种' },
            /*{field:'valmodelId', width:200, title: '估值模型' , visible: false},
            {field:'valmodelIdFmt', sortField: 'valmodelId', width:200, title: '估值模型' }, 模型法尚未开发 暂时隐藏字段*/
            {field:'datasourceType', width:200, title: '估值来源' , visible: false},
            {field:'datasourceTypeFmt', sortField: 'datasourceType', width:200, title: '估值来源' },
            {field:'inputType', width:200, title: '维护方式' , visible: false},
            {field:'inputTypeFmt', sortField: 'inputType', width:200, title: '维护方式' },
            {field:'operation', title: 'SysCommon.operation', type:'action',
                buttons: ['edit', 'delt']}
        ],
    });
    
    var formatMoney = function (s, n)
    {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
        var l = s.split('.') [0].split('').reverse(),
            r = s.split('.') [1];
        var t = '';
        for (var i = 0; i < l.length; i++)
        {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
        }
        return t.split('').reverse().join('') + '.' + r;

    }
    
    /**
     * 事件绑定
     */
    var tableBind = function () {

        /**
         * 绑定事件：利率互换估值信息 - 新建
         */
        $("#ValRateSwapValuationInfo001_btn_add").click(function(){
            Joyin.dialog(JoyinUtil.getLabel("新建利率互换估值信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "valuation/basedata/marketdata/rateswapvaluationinfo/ValRateSwapValuationInfo002/init",
                "85%", "85%", {pageModel : PAGE_MODEL_NEW,  operateType:"new"},function(){
                    table.reload();

                });
        });

        /**
         * 绑定事件：利率互换估值信息 - 编辑
         */
        table.bind('click', '.edit', function(data) {
            var object = {};
            object.finprodId = data.finprodId;
            Joyin.dialog(JoyinUtil.getLabel("编辑利率互换估值信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "valuation/basedata/marketdata/rateswapvaluationinfo/ValRateSwapValuationInfo002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_EDIT, finprodId2: data.finprodId2, datasourceType: data.datasourceType, valDate: data.valDate, operateType:"edit"},function(){
                    table.reload();
                });
        });

        /**
         * 删除-走流程
         */
        table.bind('click', '.delt', function(data) {
            var object = {};
            object.finprodId2 = data.finprodId2;
            object.datasourceType = data.datasourceType;
            object.valDate = data.valDate;
            object.operateType = data.operateType;
            Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/rateswapvaluationinfo/ValRateSwapValuationInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(
                        basePath + 'valuation/basedata/marketdata/rateswapvaluationinfo/ValRateSwapValuationInfo001/delete',
                        object,
                        "ValRateSwapValuationInfo001_btn_delete",
                        "ValRateSwapValuationInfo002",
                        function() {
                            table.reload();
                        }
                    );
                });
            });
        });

        /** 事件绑定: 删除 */
        $('#ValRateSwapValuationInfo001_btn_delete').click(function() {
            var object = {};
            var rowArr = table.getAllCheckedRows();// 所有选中行数据
            if(rowArr == null || rowArr.length === 0 || rowArr.length >1){
                Joyin.error("请选择1条记录进行删除。");
            }else{
                object.finprodId2 = rowArr[0].finprodId2;
                object.datasourceType = rowArr[0].datasourceType;
                object.valDate = rowArr[0].valDate;
                object.operateType = rowArr[0].operateType;
                Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/rateswapvaluationinfo/ValRateSwapValuationInfo001/check', object, function(result) {
                    object.operateType = "delete";
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                        WorkFlow.doDeleteList(basePath + 'valuation/basedata/marketdata/rateswapvaluationinfo/ValRateSwapValuationInfo001/delete', object,
                            "ValRateSwapValuationInfo001_btn_delete",
                            "ValRateSwapValuationInfo002",
                            function() {
                            vatTaxTable.reload();
                        });
                    });
                });
            }
        });

    };


    /**
     * 导出
     */
    var exportExcel = function() {
        if (table) {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValRateSwapValuationInfo001_btn_search').click(function() {
            table.load();
        });

        // 绑定导出事件
        $('#ValRateSwapValuationInfo001_btn_export').click(function() {
            exportExcel();
        });

        //新建、修改、删除事件绑定
        tableBind();
    });

});
