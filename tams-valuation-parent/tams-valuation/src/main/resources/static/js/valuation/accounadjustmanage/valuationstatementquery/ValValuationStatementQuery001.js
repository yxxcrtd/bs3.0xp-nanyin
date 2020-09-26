$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            booksetId: $('#booksetId').val(),
            booksetName: $('#booksetName').val(),
            fileType: $('#fileType').val(),
            sdate : $('#sdate').val(),
            filePath : $('#filePath').val(),
            fileName : $('#fileName').val()
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
        cellMinWidth : 100,
        cols:[
              {field : 'booksetId', title : '账套代码',sort : true}, 
              {field : 'booksetName', title : '账套名称',sort : true}, 
              {field : 'sdate',title : '日期',sort : true}, 
              {field : 'fileType',title : '文件类型', sort : true },
              {field : 'fileName', title: '文件名称',sort : true },
              {field : 'filePath', title: '文件路径',sort : false }
        ]
    });
    
    var exportExcel = function() {
        var selArr = table.getAllCheckedRows();
        var b = $.isEmptyObject(selArr);
        if(b){        
           layer.alert("请至少选择一个文件导出");
        }else{         
          var datas = JSON.stringify(selArr);
          Joyin.exportExcel('export', {datas:datas});
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValFinprodTradeQuery001_btn_search').click(function() {
            table.load();
        });
        $('#ValFinprodTradeQuery001_btn_export').click(function() {
            exportExcel();
        });
    });
}());