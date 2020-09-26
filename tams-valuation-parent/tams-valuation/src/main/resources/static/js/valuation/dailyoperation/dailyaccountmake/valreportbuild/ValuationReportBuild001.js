$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	finprodId: $('#').val(),
        	profitType: $('#').val(),     
        	issuer: $('#').val(),
        	issuer: $('#').val()
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
//        dblclick:function(table, data) {// 绑定双击事件，
////        	TODO PAGE_MODEL_REFERENCE存在bug，对于input-checkbox即layui-chcekbox不能disable
////        	joyin-plugins-all要求：_self.prop('disabled') ? _self.next().addClass('disabled') : bindClick(_self);
//            Joyin.dialog(JoyinUtil.getLabel("查看基金类资产"),
//                    basePath + "valuation/assets/fund/ValAssetsFundQuery002/init",
//                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE,
//            	finprodId:data.finprodId},function(){table.reload();});
//        },
		cellMinWidth : 100,
		cols: [
		   {field:'', title: '估值日期', sort: true},
		   {field:'', title: '产品代码', sort: true},
		   {field:'', title: '产品简称', sort: true},
           {field:'', title: '产品类型', sort: true},
		   {field:'', title: '生成状态',sort: true},
		   {field:'', title: '错误类型', sort: true},
		   {field:'operation', title: '错误明细', type:'action', text: '查看'}
		],
	});
	/**
     * 绑定查看事件
     */
    table.bind('click', '.edit', function(data, index) {
        var datas = JSON.stringify(data);
        Joyin.dialog(JoyinUtil.getLabel("日期切换"),
                basePath + "valuation/accounadjustmanage/booksetdateswitch/ValBooksetDateSwitch002/init",
                "90%", "75%", {pageModel:PAGE_MODEL_NEW,datas : datas}
                ,function(){
                    table.reload();
                }
        );
    });
	

	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValuationReportBuild001_btn_search').click(function() {
            table.load();
        });

    });
	
});



