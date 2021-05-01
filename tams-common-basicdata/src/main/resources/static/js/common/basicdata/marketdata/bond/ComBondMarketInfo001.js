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
            finprodId : $('#finprodId').val(),
            finprodAbbr : $('#finprodAbbr').val(),
//            valueSource : $('#valueSource').val(),
            valueDateBegin: $('#valueDate').startDate(),
            valueDateEnd: $('#valueDate').endDate()
        }
    };
/**
 * 表格加载
 */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'findPage',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        
        cellMinWidth : 100,
//        loadonce : true,
        cols : [ 
                {field : 'finprodId',/* fixed : true,*/ title : '资产代码',width:120, sort : true}, //"fixed : true"会导致排序功能失效，故隐藏 add by zhangyf
                {field : 'finprodAbbr', title : '债券简称',width:120, sort : true}, 
//                {field : 'valueSourceFt',title : '估值来源',width:120, sort : true,sortField:'valueSource'}, 
                {field : 'valueDate',title : '估值日',width:120, sort : true }, 
             /*   {field : 'lastPeriod', title : '剩余期限',width:120, sort : true },*/
                //全价和净价分开 modified by lishuping 20191125 start
                {field : 'cloPrice',type:"money", title : '收盘价净价',width:120, sort : true},
                {field : 'avgPrice',type:"money", title : '平均价净价',width:120, sort : true },
                {field : 'buyOnePrice',type:"money", title : '买一价净价',width:120, sort : true},
                {field : 'sellOnePrice',type:"money", title : '卖一价净价',width:120, sort : true },
                {field : 'maxPrice',type:"money",title : '最高价净价',width:120, sort : true },
                {field : 'minPrice',type:"money", title : '最低价净价',width:120, sort : true },
                {field : 'cloPriceFull',type:"money", title : '收盘价全价',width:120, sort : true},
                {field : 'avgPriceFull',type:"money", title : '平均价全价',width:120, sort : true },
                {field : 'buyOnePriceFull',type:"money", title : '买一价全价',width:120, sort : true},
                {field : 'sellOnePriceFull',type:"money", title : '卖一价全价',width:120, sort : true },
                {field : 'maxPriceFull',type:"money",title : '最高价全价',width:120, sort : true },
                {field : 'minPriceFull',type:"money", title : '最低价全价',width:120, sort : true }
                //全价和净价分开 modified by lishuping 20191125 end
//                {fixed : 'right',width : 80, title : '操作', align : 'center',type : 'action', buttons : [ 'edit' ] } 
               ]
    });
    
    /**
     * 筛选
     */
    $('#ComBondMarketInfo001_btn_search').click(function() {
        table.load();//
    });
    /**
     * 导出
     */
    $('#ComBondMarketInfo001_btn_export').click(function() {
        exportExcel();
    });

    /**
     * 重置
     */ 
    $('#ComBondMarketInfo001_btn_reset').click(function() {
        //location.reload();
        $('#finprodId').val('');     
        $('#finprodAbbr').val('');
        $('#valueSource option:selected').remove();
        $('#valueDate').val('');
    });
    
    
    // 导入按钮点击事件
    $('#ComBondMarketInfo001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax(basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                    Joyin.ajax(basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001/importFile', {fileName: fileName});
                });
            },function() {
                table.reload();
            });
        });
    });

});