$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            schemeId: $('#schemeId').val()
        }
    };
	var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
		searchOnInit: false,
		cellMinWidth:100 ,
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "valuation/valuationparametermanage/valuationsolution/ValuationSolutionManage002/init",
                    "95%", "95%", {pageModel:PAGE_MODEL_NEW,
                                    schemeId:data.schemeId});
        },
		cols: [
		    {field:'schemeId', width:120, title: 'ValuationSolutionManage001.schemeId'}
		    ,{field:'schemeName', width:150, title: 'ValuationSolutionManage001.schemeName'}
		    ,{field:'schemeDescription', width:240, title: 'ValuationSolutionManage001.schemeDescription'}
		]
	  });

    /**
     * 画面加载完成
     */
    $(function() {
      // 绑定按钮事件
      $('#ValuationSolutionManage001_btn_search').click(function() {
          table.load();
      });
      $('#ValuationSolutionManage001_btn_reset').click(function() {
          location.reload();
      });
    });
});