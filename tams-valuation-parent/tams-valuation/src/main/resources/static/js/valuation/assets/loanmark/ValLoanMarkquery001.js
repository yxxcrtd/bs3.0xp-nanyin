$(function () {    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	assets:$.trim($('#assets').val()),
        	directionBorrowing:$.trim($('#directionBorrowing').val()),
        	vDateBegin: $('#vDate').startDate(),
            vDateEnd: $('#vDate').endDate(),
            mDateBegin: $('#mDate').startDate(),
            mDateEnd: $('#mDate').endDate()
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
            var selChe = table.getAllCheckedRows();
            if(selChe.length > 1){
                alert("请选择一条记录进行操作！");
                return;
            }
              Joyin.dialog(JoyinUtil.getLabel("查看拆借类资产"),           		  
                      JoyinUtil.getRealPath("/valuation/assets/loanmark/ValLoanMarkquery002/init"),
                      "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId,finprodType:data.finprodType},
                      function(){
                          //table.reload();
                          });
          },
        cols: [
            {field:'finprodId',width:260,title: '资产代码', sort: true,rowspan: 2,fixed:'left'},
            {field:'type2',width:100,title: '核算分类', sort: true,visible:false},
            {field:'type2Code',width:200,title: '核算分类',sortField:'type2', sort: true},
            {field:'finprodType',width:100,title: '拆借方向',sort: true,visible:false},
            {field:'finprodTypeCode',width:100,title: '拆借方向',sortField:'finprodType', sort: true},
            {field:'prin',width:150,title: '拆借金额',sort: true,type:'money'},
            {field:'vdate', width:100,title: '起息日',sort: true},
            {field:'mdate',width:100, title: '到期日', sort: true,},
            {field:'term',width:100,title: '拆借期限', sort: true},
            {field:'termType',width:100,title: '期限品种', sort: true,visible:false},
            {field:'termTypeCode',width:100,title: '期限品种',sortField:'termType', sort: true},
            {field:'basis', width:100,title: '计息基础',sort: true},
            {field:'intRate',width:150,title: '拆借利率（%）', sort: true,align:'right',type:'money'},
            {field:'mIntAmt', width:150,title: '应计利息', sort: true,type:'money'},
            {field:'mTradeAmt',width:150,title: '到期本息和', sort: true,type:'money'}
        ] 
    });
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
        $('#ValLoanMarkquery001_btn_search').click(function() {
            table.load();
        });
        $('#ValLoanMarkquery001_btn_export').click(function() {
            exportExcel();
        });
    });
}());