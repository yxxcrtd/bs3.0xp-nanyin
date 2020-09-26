$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
    	return $.serializeToObject('#searchForm');
    };
    
    	// 导出数据
        var exportExcel = function() {
            Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valcurrentdepositinterest/ValCurrentDepositInterest001/check',getPageParams(), function(result) {
                        if (result.data) {
                            Joyin.exportExcel('export', getPageParams());
                        }
                    });
        };
        
        /**
         * 清空日期 input
         */
        var clearDateInput = function() {
            //$("#year").val("");
        }
        /**
         * 筛选条件
         */
        var linkBind = function(){
            var laydate = layui.laydate;
            laydate.render({
                elem: '#year',
                type: 'year',
                format: 'yyyy'
            });
            $("#year").show();
            clearDateInput();
        
            $("#year").click(function(event){
                event.stopPropagation();// 阻止事件冒泡
            });
           
        }
        
      /**
       * 画面加载完成
       */
      $(function() {
          // 筛选条件
          linkBind();
          //绑定导出按钮事件
          $('#ValCurrentDepositInterest001_btn_export').click(function() {
              exportExcel();
          });
      });
});
