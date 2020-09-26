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
            valueSource : $('#valueSource').val(),
            valueDateBegin: $('#valueDate').startDate(),
            valueDateEnd: $('#valueDate').endDate()
        }
    };
/**
 * 表格加载
 */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'ValBondValuationInfo001Search',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        /*dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("债券代码："+data.secId),
                    JoyinUtil.getRealPath("/valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo002/init"),
                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                secId:data.secId,},
                    function(){table.reload();});
        },*/
        dblclick:function(table, data) {
        Joyin.dialog(JoyinUtil.getLabel("债券代码："+data.secId),
                JoyinUtil.getRealPath("/valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo003/init"),
                "90%", 
                "90%", 
                {pageModel:PAGE_MODEL_REFERENCE,secId:data.secId,valueSource:data.valueSource,valueDate:data.valueDate},
                function(){table.reload();});
        },
        cellMinWidth : 100,
        cols : [ 
                {field : 'finprodId', title : '资产代码',width:120, sort : true}, 
                {field : 'finprodAbbr', title : '债券简称',width:200, sort : true}, 
                {field : 'valueSourceName',title : '估值来源',sortField:'valueSource',width:120, sort : true}, 
                {field : 'valueDate',title : '估值日',width:120, sort : true }, 
                {field : 'lastPeriod', align : "right",title : '剩余期限',width:120, sort : true },  
                {field : 'price', align : "right",title : '市价全价',width:120, sort : true}, 
                {field : 'netprice', align : "right", title : '市价净价',width:120, sort : true},
                {field : 'mDuration',align : "right",title : '市价久期',width:120, sort : true}, 
                {field : 'mConvexity',align : "right",title : '市价凸性',width:120, sort : true }, 
                {field : 'bpvalue',align : "right",title : '基点价值',width:120, sort : true }, 
                {field : 'sduration', align : "right",title : '利差久期',width:120, sort : true },  
                {field : 'scnvxty',align : "right",title : '利差凸性',width:120, sort : true}, 
                {field : 'interestDuration', align : "right",title : '利率久期',width:120, sort : true },
                {field : 'interestCnvxty',align : "right", title : '利率凸性',width:120, sort : true}, 
                {field : 'bpyield', align : "right", title : '市价收益率',width:120, sort : true }, 
                {field : 'secId',title : 'secId',visible : false},
                {field : 'valueSource',title : 'valueSource',visible : false},
                {field : 'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
               ]
    });
    
    /**
     * 筛选
     */
    $('#ValBondValuationInfo001_btn_search').click(function() {
        table.load();
    });
    /**
     * 导出
     */
    $('#ValBondValuationInfo001_btn_export').click(function() {
        exportExcel();
    });
    /**
     * 重置
     */
    $('#ValBondValuationInfo001_btn_reset').click(function() {
        location.reload();
    });
    
    /**
     * 新建
     */
    $('#ValBondValuationInfo001_btn_add').click(function() {
        Joyin.dialog(JoyinUtil.getLabel("新建债券估值行情（<i class='iconfont must' style='display:inline'>*</i>必填）"),
            basePath + "valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo003/init",
            "80%", "80%", {pageModel:PAGE_MODEL_NEW},function(){
                //table.reload(); 
            });
    });
    
    /**
     * 编辑
     */
    table.bind('click', '.edit', function(data, index) {
        Joyin.dialog(JoyinUtil.getLabel("修改债券估值行情（<i class='iconfont must' style='display:inline'>*</i>必填）"),
            basePath + "valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo003/init",
            "80%", "80%",  {secId:data.secId,valueSource:data.valueSource,valueDate:data.valueDate,pageModel:PAGE_MODEL_EDIT}, function(){
                table.reload();
                pageModel : PAGE_MODEL_EDIT
        });
    });
    
    /**
     * 删除单个
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {};
        object.listValBondValuationInfo001Dto = [{secId:data.secId,valueSource:data.valueSource,valueDate:data.valueDate}];
        doDeleteData(table,object);
    });
    /**
     * 删除多个
     */
    $('#ValBondValuationInfo001_btn_delete').click(function() {
        deleteData(table);
    });
});

var deleteData = function(table){
    if (table != null && table != '') {
        var selArr = table.getAllCheckedRows();
        console.log(selArr);
        var datas = JSON.stringify(selArr);
    }
    if (!selArr || selArr.length == 0) {
        Joyin.error(JoyinUtil.getMessage("没有选中的数据！"));
        return;
    }
    var object = {};
    object.listValBondValuationInfo001Dto = selArr;
    object.data = datas;
    doDeleteData(table,object);
};

var doDeleteData = function(table,object){
    object.operateType = "delete";
    var message = "是否确认删除？";
    Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo001/deleteCheck', object, function(result) {
        Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.delete")), function(){
            WorkFlow.doDeleteList(
                basePath + 'valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo001/delete', 
                object, 
                "ValBondValuationInfo001_btn_delete", 
                "ValBondValuationInfo001",
                function() {
                    table.reload();
                }        
            );
        });
    });
};
