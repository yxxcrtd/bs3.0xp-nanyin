/**
 * 资产减值阶段管理
 */


$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var finprodId = $('#finprodId').val();
        var rangeDate = $('#rangeDate').val();
        var startDate = rangeDate.substring(0,10);
        var endDate = rangeDate.substring(13);
        var stage = $('#stage').val();
        return {
            finprodId: finprodId,
            stage: stage,
            startDate: startDate,
            endDate: endDate
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
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                "/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage002/init",
                "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                    stage:data.stage,effDateStr:data.effDateStr,finprodId:data.finprodId,seqNo:data.seqNo});
        },

        cols:[
            {field:'finprodId', width:400 , title: '资产id',visible:false}
            ,{field:'finprodValue', width:400 , title: '资产名称'}
            ,{field:'stage', width:400 ,title: '阶段',visible:false}
            ,{field:'stageStr', width:400 ,title: '阶段',sortField:"stage"}
            ,{field:'effDate', width:400 , title: '生效日期',type:'date'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson('/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("资产减值阶段管理编辑"),
                 "/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage002/init",
                "90%", "90%",data,function() {
                    table.reload();
                });

        });

    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson('/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.ajaxJson('/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage001/deleteFinProLosSta',object,function () {
                    table.reload();
                });
            });
        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var selArr;
        var datas;
        if (table != null && table != '') {
            selArr = table.getAllCheckedRows();
            datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
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
        $('#FinProductLossStage001_btn_search').click(function() {
            table.load();
            $('#FinProductLossStage001_btn_delete').show();
        });
        $('#FinProductLossStage001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinProductLossStage001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FinProductLossStage002"),
                "/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage002/init",
                "90%", "85%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#FinProductLossStage001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson('/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    Joyin.ajaxJson('/valuation/trdassetlossmanage/finproductlossstage/FinProductLossStage001/deleteFinProLosSta',object,function () {
                        table.reload();
                    });
                });
            });
        });

    });
}());