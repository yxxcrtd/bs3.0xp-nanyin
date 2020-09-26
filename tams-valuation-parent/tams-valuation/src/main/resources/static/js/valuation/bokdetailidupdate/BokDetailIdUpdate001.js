$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	booksetId: $.trim($('#booksetId').val()),
        	bokDetailId: $.trim($('#bokDetailId').val()),
        	busiId: $.trim($('#busiId').val()),
        	busiDetailType: $.trim($('#busiDetailType').val()),
        	invAim: $.trim($('#invAim').val()),
        	ccy: $.trim($('#ccy').val()),
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
        
        checkbox: true,
        cellMinWidth : 100,
        cols: [
               {field:'booksetId', width:160, title: '账套' },
               {field:'bokDetailId', width:160, title: '账务明细代码'},
               {field:'busiId', width:160, title: '业务明细代码' },
               {field:'busiDetailType', width:160, title: '业务明细类型' },
               {field:'invAim', width:160, title: '投资目的'},
               {field:'secManageAcctId', width:160, title: '证券管理户代码'},
               {field:'detailSubjectNoTail', width:160, title: '四级科目尾号'},
               {field:'detailSubnameTail', width:160, title: '四级科目尾号名称'},
               {field:'branch', width:160, title: '分支序号'},
               {field:'ccy', width:160, title: '币种' },
           ],
    });
    
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
        $('#BokDetailIdUpdate001_btn_search').click(function() {
            table.load();
        });
        
        // 绑定导出事件
        $('#BokDetailIdUpdate001_btn_export').click(function() {
            exportExcel();
        });
        
        // 绑定更新事件
        $('#BokDetailIdUpdate001_btn_update').click(function() {
            var selArr = table.getAllCheckedRows();// 所有选中行数据
          var datas = JSON.stringify(selArr);
          var message = "更新所有相关表的账务明细代码，是否确认更新操作？";
            if(selArr == null || selArr.length === 0){
            	var paramObj = getSearchParams();
            	
            Joyin.ajax('check', paramObj, function() {
                Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.update")), function(){
                	Joyin.ajax('update',paramObj,function() {
                        table.reload();
                    });
                });
            });
            }else{
            Joyin.ajax('check', {datas : datas}, function() {
                Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.update")), function(){
                	Joyin.ajax('update',{datas : datas},function() {
                        table.reload();
                    });
                });
            });
            }
        });
    });
});
