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
            booksetId: $.trim($('#booksetId').val()),
            comTableId: $.trim($('#comTableId').val()),     
            busiId: $('#busiId').val(),     
            bookDateBegin: $('#bookDate').startDate(),
            bookDateEnd: $('#bookDate').endDate(),
            subjectNo: $.trim($('#subjectNo').val()),
            ccy: $('#ccy').val(),
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
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看凭证编号："+data.busVouchnum),
                    JoyinUtil.getRealPath("/valuation/accounadjustmanage/voucherquery/ValVoucherQuery002/init"),
                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                booksetId:data.booksetId,tableId:data.comTableId,busVouchnum:data.busVouchnum},
                    function(){});
        },
        checkbox: true,
        cellMinWidth : 100,
        cols : [ {field : 'bookDate', title : '记账日期',width:120, sort : true}, 
        { field : 'vouchNum', title : '记账流水号',width:200, sort : true}, 
        { field : 'bankSerialNumber', title : '银行记账流水号',width:200, sortable : false},
        {field : 'booksetId',title : '账套代码',width:200,sort : true}, 
        {field : 'booksetName',title : '账套名称', width:180,sort : true }, 
        {field : 'busiId', title : '资产/负债代码',width:180,sort : true },  
        {field : 'finprodAbbr',title : '资产/负债名称', width:200,sort : true}, 
        {field : 'subjectNo', title : '科目号',width:120,sort : true },
        {field : 'subjectName', title : '科目名称', width:350,sort : true}, 
        {field : 'cdFlagFmt', sortField:'cdFlag',  title : '借贷', width:100,sort : true }, 
        {field : 'ccyFmt', sortField:'ccy', title : '原币币种', width:160,sort : true},
        {field : 'bccyFmt', sortField:'bccy', title : '记账币种', width:160,sort : true},
        {field : 'happenAmt', type:"money",sortField:'happenAmt', title : '原币金额', width:120,sort : true},
        {field : 'bHappenAmt', type:"money",sortField:'bHappenAmt', title : '本币金额', width:120,sort : true},
        {field : 'happenNumber', type:"number",sortField:'happenNumber', title : '数量',width:140, sort : true},
    /*    {field : 'balFlagFmt', sortField:'balFlag', title : '余额方向', width:100,sort : true }, 
        {field : 'balance', type:"money",sortField:'balance', title : '余额金额', width:120,sort : true},*/
        {field : 'tradeId',title : '业务编号', width:160,sort : true},
        {field : 'comTableIdFmt', sortField:'comTableId', title : '业务类别',width:260,sort : true }
        ]
    });
    /**
     * 绑定编辑事件
     */
//    table.bind('click', '.edit', function(data, index) {
//        console.log(data);
//        var datas = JSON.stringify(data);
//        Joyin.dialog(JoyinUtil.getLabel("修改账套"), basePath
//                + "valuation/accounadjustmanage/assetbook/ValAssetBook003/init",
//                "600px",
//                "430px",
//                {datas:datas},    
//                function(){
//                    table.reload();
//                    pageModel : PAGE_MODEL_NEW
//                    }
//                {
//                    pageModel : PAGE_MODEL_NEW
//                }
//              );
//    });
    
/**
 * 新建账套
 */
//    $('#ValAssetBook001_btn_add').click(
//            function() {
//                Joyin.dialog(JoyinUtil.getLabel("新建账套"), basePath
//                        + "valuation/accounadjustmanage/assetbook/ValAssetBook002/init", "600px",
//                        "465px", null,
//                        function(){
//                    table.reload();
//                    pageModel : PAGE_MODEL_NEW
//                    },
//                        {
//                            pageModel : PAGE_MODEL_NEW
//                        }
//                        null
//                ); 
//            });
    /**
     * 筛选
     */
    $('#ValSubjectWaterQuery001_btn_search').click(function() {
        table.load();
    });
    
    /**
     * 重置
     */
    $('#ValSubjectWaterQuery001_btn_clear').click(function() {
        //location.reload();
        $('#booksetId option:selected').remove();
        $('#comTableId option:selected').remove();    
        $('#busiId').val('');     
        $('#bookDate').val('');
        $('#subjectNo option:selected').remove();
        $('#ccy option:selected').remove();
    });
    
    
    /**
     * 导出
     */
    $('#ValSubjectWaterQuery001_btn_export').click(function() {
        exportExcel();
    });
/**
 * 删除
 */
//    var deleteData = function() {
//        if (table != null && table != '') {
//            var selArr = table.getAllCheckedRows();
//            console.log(selArr);
//            var datas = JSON.stringify(selArr);
//        }
//        if (!selArr || selArr.length == 0) {
//            Joyin.error(JoyinUtil.getMessage("没有选中数据"));
//            return;
//        }
//        $.ajax({
//            type : "POST",
//            url : 'delete',
//            data : {datas : datas},
//            success : function(data) {  
//                if (data) {      
//                    table.reload();
//                    layui.layer.alert("删除成功");
//                } else {
//                    layui.layer.alert("删除失败");
//                }
//            }
//        });
//
//    };
    /**
     * 删除
     */
//    $('#ValAssetBook001_btn_delete').click(function() {
//        deleteData();
//    });
    /**
     * 复制
     */
//    $('#ValAssetBook001_btn_copy').click(
//            function() {
//                Joyin.dialog(JoyinUtil.getLabel("新建账套"), basePath
//                        + "valuation/accounadjustmanage/assetbook/ValAssetBook003/init", 
//                        "600px",
//                        "420px",{
//                            pageModel : PAGE_MODEL_NEW
//                        });
//            });
    $('#booksetType').change(function(){
    	booksetIdChange();
    });
    
    var booksetIdChange = function () {
        var booksetType = $('#booksetType').val();
        var object = {booksetTypes:booksetType};
        Joyin.ajaxJson(basePath + '/valuation/accounadjustmanage/voucherquery/ValVoucherQuery001/findBooksetIds', object, function(result) {
        	$("#booksetId").empty();
        	$.each(result, function(index, item) {                   
        		$('#booksetId').append(new Option(item.text,item.id));
            });
		}); 
    };

});