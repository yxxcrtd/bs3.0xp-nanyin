$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var localAccount = $('#localAccount').val();
        return {
            
            queryDateBegin: $('#queryDate').startDate(),
            queryDateEnd: $('#queryDate').endDate(),
            localAccount: $.trim($('#localAccount').val())
        }
    };
    /**
     * 表格加载
     */
        var productRedemptionTable = $("#dataTable").joyintable({
            ajax : {
                url : 'findMaintainProductRedemptionData',
                type : 'POST',
                data : function() {
                    return getSearchParams();
                }
            },
            cellMinWidth : 100,
            checkbox: false,
            page:false,
            cols : [ 
                    {field : 'targetName', title : 'FlowForecastConfig.targetName1',width:340, sortable:false}, 
                    {field : 'targetTimeoneSG', title : 'FlowForecastConfig.targetName2',width:340,type:'number', sortable:false,digit:2}, 
                    {field : 'targetTimeoneSH', title : 'FlowForecastConfig.targetName3',width:340,type:'number', sortable:false,digit:2},  
                    {field : 'targetTimeone', title : 'FlowForecastConfig.targetName4',width:340,type:'number', sortable:false,digit:2}, 
                   ]
        });
        /**
         * 查询
         */
        $('#MaintainProductRedemptionData001_btn_search').click(function() {
            var queryDateBegin = $('#queryDate').startDate();
            var queryDateEnd = $('#queryDate').endDate();
            var qDate = $('#queryDate').val();
            var localAccount = $.trim($('#localAccount').val())
            if(qDate==''){
                Joyin.error(JoyinUtil.getMessage("查询日期区间不能为空!"));
                return;
            }
            else{
                
                Joyin.ajax('/invest/product/forecast/MaintainProductRedemptionData001/check',null,function(data) {
                        
                        var plateFormDate = new Date(data);
                        var EndDate = new Date(queryDateEnd);
                        if(EndDate.getTime()>plateFormDate.getTime()){
                            Joyin.error(JoyinUtil.getMessage("区间结束日必须小于平台日!"));
                            return;
                        }else{
                            if(!productRedemptionTable.table) {
                                productRedemptionTable.load();
                                }else {
                                    productRedemptionTable.reload();
                                }
                        }
                });
            }    
         });
});