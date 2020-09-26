$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	repAsset: $.trim($('#finprodId').val()),  
        	finprodType: $.trim($('#finprodType').val()),         	      	
        	vdateBegin: $('#vDate').startDate(),
            vdateEnd: $('#vDate').endDate(),
            mdateBegin: $('#mDate').startDate(),
            mdateEnd: $('#mDate').endDate()
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
		/*cellMinWidth : 90,*/
		dblclick:function(table, data) {//绑定双击事件
            Joyin.dialog(JoyinUtil.getLabel("查看逾期罚息类资产"),
                    JoyinUtil.getRealPath("/valuation/assets/penaltyinterest/ValPenaltyInterestQuery002/init"),
                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
            	finprodId:data.finprodId,finprodType:data.finprodType,branchNo:data.branchNo},
                    function(){            	    
            	    });
        },
		cols: [
           {field:'finprodId', title: '金融产品代码',width : 160, sort: true,fixed:false},
		   {field:'finprodName', title: '金融产品名称', width : 160, sort: true,fixed:false},
		   {field:'finprodTypeFmt', title: '金融产品类型',width : 150, sortField:'finprodType',sort: true,fixed:false},
		   {field:'branchTypeFmt', title: '分支类型',width : 150, sortField:'branchType', sort: true,fixed:false},
		   {field:'branchNo', title: '分支序号',width : 150, sort: true},
           {field:'vdate1', title: '罚息开始日', width : 110,sort: true},
		   {field:'mdate1', title: '罚息截止日', width : 110,sort: true},
		   {field:'basisFmt', title: '计息基础',width : 110, sortField:'basis',sort: true},
		   {field:'yield',width : 110, title: '罚息利率',type: 'money', sort: true},
		   {field:'ifPenaltyFmt', title: '是否罚息计提', width : 110, sortField:'ifPenalty', sort: true},
		   {field:'ifRushBackFmt', title: '截止日是否冲回', width : 110, sortField:'ifRushBack', sort: true}
		],
	});
	
	/**
     * 导出
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
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValPenaltyInterestQuery001_btn_search').click(function() {
            table.load();
        });
        $('#ValPenaltyInterestQuery001_btn_export').click(function() {
           exportExcel();
       });
    });
	
});