$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId2: $.trim($('#finprodId2').val()),
            foreignexChangeType: $.trim($('#foreignexChangeType').val()),
            valDateBegin: $.trim($('#valDate').startDate()),
            valDateEnd: $.trim($('#valDate').endDate()),
            datasourceType: $.trim($('#datasourceType').val()),
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
            Joyin.dialog(JoyinUtil.getLabel("查看外汇估值信息"),
                basePath + "common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE,
                    finprodId2: data.finprodId2,valmodelId:data.valmodelId,datasourceType: data.datasourceType, valDate: data.valDate, operateType:"view"},function(){
                    table.reload();
                });
        },
        checkbox: true,
        cellMinWidth : 100,
        /*修改列宽 lishuping 20190829 start*/
        cols: [
            {field:'finprodId2', width:160, title: '交易编号' },
            {field:'foreignexChangeType', width:160, title: '外汇类型', visible: false },
            {field:'foreignexChangeTypeFmt',  sortField: 'foreignexChangeType',width:160, title: '外汇类型' },
            {field:'valDate', width:160, type:'date', title: '估值日期' },
            {field:'valuation', width:160, title: '估值' ,type:'number',digit:4},
            {field:'ccy', width:160, title: '币种' , visible: false},
            {field:'ccyFmt', sortField: 'ccy', width:160, title: '币种' },
            {field:'valmodelId', width:160, title: '估值模型' , visible: false},
           /* {field:'valmodelIdFmt', sortField: 'valmodelId', width:160, title: '估值模型' },
            {field:'datasourceType', width:160, title: '估值来源' , visible: false}, 模型法尚未开发 暂时隐藏字段 */
            {field:'datasourceTypeFmt', sortField: 'datasourceType', width:160, title: '数据来源' },
            {field:'inputType', width:160, title: '维护方式' , visible: false},
            {field:'inputTypeFmt', sortField: 'inputType', width:160, title: '维护方式' },
            {field:'operation', title: 'SysCommon.operation', type:'action',
                buttons: ['edit', 'delt']}
        ],
        /*修改列宽 lishuping 20190829 end*/
    });

    /**
     * 事件绑定
     */
    var tableBind = function () {

        /**
         * 绑定事件：外汇估值信息 - 新建
         */
        $("#ComFxValuztionInfo001_btn_add").click(function(){
            Joyin.dialog(JoyinUtil.getLabel("新建外汇估值信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo002/init",
                "85%", "85%", {pageModel : PAGE_MODEL_NEW,  operateType:"new"},function(){
                    table.reload();

                });
        });

        /**
         * 绑定事件：外汇估值信息 - 编辑
         */
        table.bind('click', '.edit', function(data) {
            var object = {};
            object.finprodId = data.finprodId;
            Joyin.confirm("请确认是否要修改？", function(){
                Joyin.dialog(JoyinUtil.getLabel("编辑外汇估值信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
                    basePath + "common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo002/init",
                    "85%", "85%", {pageModel:PAGE_MODEL_EDIT, finprodId2: data.finprodId2, valmodelId:data.valmodelId,datasourceType: data.datasourceType, valDate: data.valDate, operateType:"edit"},function(){
                        table.reload();
                    });
            });
        });

        /**
         * 删除-走流程
         */
        table.bind('click', '.delt', function(data) {
        	var object = {selectedItem: [data], operateType:"delete"};
            Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(
                        basePath + 'common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo001/delete',object,
                        "ValFxValuztionInfo001_btn_delete",
                        "ValFxValuztionInfo002",
                        function() {
                            table.reload();
                        }
                    );
                });
            });
        });

        /** 事件绑定: 删除 */
        $('#ComFxValuztionInfo001_btn_delete').click(function() {
        	if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();// 所有选中行数据
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo001/delete', object,
                        "ValFxValuztionInfo001_btn_delete",
                        "ValFxValuztionInfo002",
                        function() {
                    	table.reload();
                    });
                });
            });
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
        $('#ComFxValuztionInfo001_btn_search').click(function() {
            table.load();
        });

        // 绑定导出事件
        $('#ComFxValuztionInfo001_btn_export').click(function() {
            exportExcel();
        });
        
        /**
         * 导入按钮
         */
        $('#ComFxValuztionInfo001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath +"common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo001/importFile", {
                        fileName : fileName
                    },function(result){
                    	if(!result || !($.isArray(result) && result.length > 0)){
                    		Joyin.ajax(
                                    basePath + 'common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo001/importFileSave', 
                                    {fileName : fileName},function (data1) {
                                    	layui.layer.closeAll()
                                        table.reload();
                              });
                    	}
                    	else if($.isArray(result) && result.length > 0 && result[0].rowNum != "") {
                        	Joyin.ajax(
                                    basePath + 'common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo001/duplicateCheck', 
                                    {fileName : fileName},function (data) {
                                    	if(result.length == data.length){
                                    		Joyin.confirm("导入文件存在"+data.length+"条数据重复，重复数据不导入，是否确认？", function(){
                                    			Joyin.ajax(
                                                        basePath + 'common/basicdata/marketdata/valfxvaluztioninfo/ComFxValuztionInfo001/importFileSave', 
                                                        {fileName : fileName},function (data1) {
                                                        	layui.layer.closeAll()
                                                            table.reload();
                                                        	Joyin.alert("文件导入成功！", 0);
                                                  });
                                            }, {}, function() {
                                            	layui.layer.closeAll()
                                                table.reload();
                                            });
                                    	}
                              });
                        }
                    });
                });
            });
        });

        //新建、修改、删除事件绑定
        tableBind();
    });

});
