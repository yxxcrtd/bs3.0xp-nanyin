$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            profitType: $.trim($('#profitType').val()),
            raiseType: $.trim($('#raiseType').val()),     
            issueType: $.trim($('#issueType').val()), 
            investType:$.trim($('#investType').val()),   
            finprodId: $.trim($('#finprodId').val()),   
            vdateStart:$.trim($('#vdate').startDate()), 
            vdateEnd:$.trim($('#vdate').endDate()), 
            mdateStart:$.trim($('#mdate').startDate()),
            mdateEnd:$.trim($('#mdate').endDate())
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
            if(data.profitType=='01'){
                Joyin.dialog(JoyinUtil.getLabel("查看净值型产品信息"),
                        JoyinUtil.getRealPath("/valuation/basedata/productinfoquery/ValProductInfoQuery003/init"),
                        "95%", "95%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId},
                        function(){
                            //table.reload();
                        });
            }else if(data.profitType=='02'){
                Joyin.dialog(JoyinUtil.getLabel("查看收益型产品信息"),
                        JoyinUtil.getRealPath("/valuation/basedata/productinfoquery/ValProductInfoQuery002/init"),
                        "95%", "95%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId},
                        function(){
                            //table.reload();
                        });
            }else{
                Joyin.dialog(JoyinUtil.getLabel("查看货币型产品信息"),
                        JoyinUtil.getRealPath("/valuation/basedata/productinfoquery/ValProductInfoQuery004/init"),
                        "95%", "95%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId},
                        function(){
                            //table.reload();
                        });
            }
        },
		checkbox: true,
		cellMinWidth : 100,
		cols: [
		   {field:'finprodId', title: '产品代码',width:150, sort: true},
		   {field:'finprodAbbr', title: '产品简称',width:180, sort: true},
	       {field:'profitType', title: '产品类型',width:150, sort: true,visible:false},
		   {field:'profitTypeFmt', sortField: "profitType", title: '产品类型',width:150, sort: true},
	       {field:'raiseType', title: '募集方式',width:100, sort: true,visible:false},
		   {field:'raiseTypeFmt', sortField: "raiseType", title: '募集方式',width:100, sort: true ,visible:false},
		   {field:'issueType', title: '发行方式',width:120,sort: true,visible:false},
           {field:'issueTypeFmt', sortField: "issueType", title: '发行方式',width:120,sort: true },
           {field:'investType',  title: '投资性质',width:150,sort: true,visible:false},
           {field:'investTypeFmt', sortField: "investType", title: '投资性质',width:150,sort: true ,visible:false},
           {field:'prdIncomeType', title: '收益标识',sortField:'prdIncomeTypeCode',width:150,sort: true },
           {field:'prdIncomeTypeCode', sortField: "prdIncomeType", title: '收益标识',width:100,sort: true,visible:false},
		   {field:'vdate', title: '起息日',width:110, sort: true},
		   {field:'mdate', title: '到期日',width:110, sort: true},
		   {field:'ccy', title: '募集币种',width:150,sort: true,visible:false},
           {field:'ccyFmt', sortField: "ccy", title: '募集币种',width:150,sort: true},
           {field:'planraiseAmt', type:"money", title: '募集金额',width:150,sort: true},
           {field:'basis', title: '计息基础',width:150, sort: true}
		],
	});
	var exportExcel = function() {
	    var selArr;
        var datas;
        if (table != null && table != '') {
            selArr= table.getAllCheckedRows();
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
        $('#ValProductInfoQuery001_btn_search').click(function() {
            table.load();
        });
        $('#ValProductInfoQuery001_btn_export').click(function() {
            exportExcel();
        });
    });
}());