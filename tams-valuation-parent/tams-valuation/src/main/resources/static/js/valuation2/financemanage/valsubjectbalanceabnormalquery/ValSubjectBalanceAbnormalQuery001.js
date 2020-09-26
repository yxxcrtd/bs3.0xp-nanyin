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
            bookDate: $('#bookDate').val(), 
            isRed: $('#isRed').next().hasClass('layui-form-onswitch') ? 'true' : 'false' 
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
        checkbox: true,
        cellMinWidth : 100,
        cols : [ {field : 'balanceDate', title : '日期',width:120, sort : true}, 
        {field : 'booksetId', title : '账套代码',width:200, sort : true}, 
        {field : 'booksetName',title : '账套名称',width:200,sort : true}, 
        {field : 'subjectNo',title : '异常科目代码', width:200,sort : true }, 
        {field : 'subjectName', title : '异常科目名称',width:240,sort : true },  
        {field : 'balFlagFmt',title : '余额方向', sortField:'balFlag',width:120,sort : true}, 
        {field : 'oAmt', title : '科目余额',type:"money",width:120,sort : true }
        ]
    });
    
    /**
     * 筛选
     */
    $('#ValSubjectBalanceAbnormalQuery001_btn_search').click(function() {
        var bookDate = $('#bookDate').val();
        if(bookDate==''){
            Joyin.error(JoyinUtil.getMessage("日期不能为空"));  
            return;
        }else{
            table.load();
        }
    });
    
    /**
     * 导出
     */
    $('#ValSubjectBalanceAbnormalQuery001_btn_export').click(function() {
        exportExcel();
    });

});