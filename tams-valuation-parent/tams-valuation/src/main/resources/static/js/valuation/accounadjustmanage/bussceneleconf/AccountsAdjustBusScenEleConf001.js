/**
 * 跳转页面
 */
var redirectUrl = function(tableId){
	window.location.href = JoyinUtil.getRealPath("/valuation/accounadjustmanage/bussceneleconf/AccountsAdjustBusScenEleConf003/init?tableId="+tableId);
}

$(function() {
  
/**
 * 导出表格
 */
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
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
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            tableName : $('#AccountsAdjustBusScenEleConf001_tableName').val()
        }
    };
    
    
/**
 * 表格加载
 */
    var table = $("#dataTable").joyintable({
        ajax : {
        	url: 'findPage',
            type: 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        cellMinWidth : 100,
        cols : [ 
        {field : 'tableConfig', title : '业务要素配置',align:'center', 
        	render : function(data, type, row, meta){
        		console.log(row);
                   return '<a id="jumpTab" href=\'javascript:redirectUrl("'+row.tableId+'")\'>'+row.tableConfig+'</a>';
            }
        }, 
        {field : 'tableId', title : '业务场景代码',sort : true}, 
        {field : 'tableName',title : '业务场景名称',sort : true}, 
        {field : 'remark',title : '备注', sort : true },
        {field : 'operation', title: '操作',align:'center', fixed:'right',type:'action', buttons: ['edit', 'delt']}]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
    	data.pageModel = PAGE_MODEL_EDIT;
    	Joyin.dialog(JoyinUtil.getLabel("业务场景要素修改"),
                basePath + "valuation/accounadjustmanage/bussceneleconf/AccountsAdjustBusScenEleConf002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_EDIT,
        	                    tableName:data.tableName,
        	                    tableId:data.tableId,
        	                    remark:data.remark});
    });
    
    /**
     * 
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
        	Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/bussceneleconf/AccountsAdjustBusScenEleConf001/delete', data, function(result) {
        		 table.reload();
        	});
        });
    });
    
/**
 * 新建账套
 */
    $('#AccountsAdjustBusScenEleConf001_add').click(
            function() {
                Joyin.dialog(JoyinUtil.getLabel("新建业务要素配置"), basePath
                        + "valuation/accounadjustmanage/bussceneleconf/AccountsAdjustBusScenEleConf002/init", "600px",
                        "465px", {
                            pageModel : PAGE_MODEL_NEW
                        });
            });
    /**
     * 筛选
     */
    $('#AccountsAdjustBusScenEleConf001_btn_search').click(function() {
        table.load();
    });
    /**
     * 导出
     */
    $('#AccountsAdjustBusScenEleConf001_btn_export').click(function() {
        exportExcel();
    });
/**
 * 删除
 */
    var deleteData = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            console.log(selArr);
            var datas = JSON.stringify(selArr);
            console.log(datas);
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("没有选中数据"));
            return;
        }
        Joyin.ajax('delete',{datas : datas},function(data) {
                console.log(data);
                if (data) {            
                    layui.layer.alert("删除成功");
                } else {
                    layui.layer.alert("删除失败");
                }
        });

    };
    /**
     * 删除
     */
    $('#AccountsAdjustBusScenEleConf001_btn_delete').click(function() {
        deleteData();
    });

});