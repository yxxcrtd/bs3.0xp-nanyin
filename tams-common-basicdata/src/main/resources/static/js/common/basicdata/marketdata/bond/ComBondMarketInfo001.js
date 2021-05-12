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
                {field : 'finprodId',/* fixed : true,*/ title : '债券代码',width:120, sort : true}, //"fixed : true"会导致排序功能失效，故隐藏 add by zhangyf
                {field : 'finprodAbbr', title : '债券简称',width:120, sort : true}, 
//                {field : 'valueSourceFt',title : '估值来源',width:120, sort : true,sortField:'valueSource'},
                {field : 'secId',title : '债券代码（市场代码）',width:120, visible:false},
                {field : 'valueDate',title : '估值日',width:120, sort : true },
//                {field : 'lastPeriod', title : '剩余期限',width:120, sort : true },
                //全价和净价分开 modified by lishuping 20191125 start
                {field : 'cloPrice',type:"money", digit:3, title : '收盘价净价',width:120, sort : true},
                {field : 'avgPrice',type:"money",digit:3, title : '平均价净价',width:120, sort : true },
                {field : 'buyOnePrice',type:"money", digit:3,title : '买一价净价',width:120, sort : true},
                {field : 'sellOnePrice',type:"money", digit:3,title : '卖一价净价',width:120, sort : true },
                {field : 'maxPrice',type:"money",digit:3,title : '最高价净价',width:120, sort : true },
                {field : 'minPrice',type:"money", digit:3,title : '最低价净价',width:120, sort : true },
                {field : 'cloPriceFull',type:"money", digit:3,title : '收盘价全价',width:120, sort : true},
                {field : 'avgPriceFull',type:"money", digit:3,title : '平均价全价',width:120, sort : true },
                {field : 'buyOnePriceFull',type:"money", digit:3,title : '买一价全价',width:120, sort : true},
                {field : 'sellOnePriceFull',type:"money", digit:3,title : '卖一价全价',width:120, sort : true },
                {field : 'maxPriceFull',type:"money",digit:3,title : '最高价全价',width:120, sort : true },
                {field : 'minPriceFull',type:"money", digit:3,title : '最低价全价',width:120, sort : true },
                {field:'operation', title: '操作' , type:'action',buttons: ['edit', 'delt'] , width:100}
                //全价和净价分开 modified by lishuping 20191125 end
//                {fixed : 'right',width : 80, title : '操作', align : 'center',type : 'action', buttons : [ 'edit' ] } 
               ]
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.valueDate = new Date(data.valueDate).format();
        data.pageModel=PAGE_MODEL_EDIT;
        data.operateType='edit';
//        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo002/check', data, function(result) {
            Joyin.confirm("请确认是否要修改？", function(){
                Joyin.dialog(JoyinUtil.getLabel("每日债券行情编辑"),
                    basePath + "common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo002/init",
                    "98%", "95%",data,
                    function(){table.reload();});
            });
//        });

    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {
            finprodId:data.finprodId,
            secId:data.secId,
            valueDate:data.valueDate,
            selectedItem: [data],
            operateType:"delete"
        };
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001/delete', object, "ValBondMarketInfo001_btn_delete", "ValBondMarketInfo002",function(){
                    table.reload();
                });
            });
        });
    });
    
    /**
     * 筛选
     */
    $('#ComBondMarketInfo001_btn_search').click(function() {
        table.load();//
        $('#ValBondMarketInfo001_btn_delete').show();
    });

    /**
     * 删除
     */
    $('#ComBondMarketInfo001_btn_delete').click(function () {
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var object = {selectedItem: selArr, operateType:"delete"};
        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001/delete', object, "ValBondMarketInfo001_btn_delete", "ValBondMarketInfo002",function () {
                    table.reload();
                });
            });
        });
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

    /**
     * 新建
     */
    $('#ComBondMarketInfo001_btn_add').click(function () {
        Joyin.dialog(JoyinUtil.getLabel("每日债券行情新建"), basePath +'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo002/init', '75%', '75%');
    });
    
    
    // 导入按钮点击事件
    $('#ComBondMarketInfo001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax(basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001/importFile', {
                    fileName : fileName
                },function(result){
                	if(!result || !($.isArray(result) && result.length > 0)){
                		Joyin.ajax(
                                basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001/importFileSave', 
                                {fileName : fileName},function (data1) {
                                	layui.layer.closeAll()
                                    table.reload();
                          });
                	}
                	else if($.isArray(result) && result.length > 0 && result[0].rowNum != "") {
                    	Joyin.ajax(
                                basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001/duplicateCheck', 
                                {fileName : fileName},function (data) {
                                	if(result.length == data.length){
                                		Joyin.confirm("导入文件存在"+data.length+"条数据重复，重复数据不导入，是否确认？", function(){
                                			Joyin.ajax(
                                                    basePath + 'common/basicdata/marketdata/bondmarketinfo/ComBondMarketInfo001/importFileSave', 
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
            },function() {
                table.reload();
            });
        });
    });

});