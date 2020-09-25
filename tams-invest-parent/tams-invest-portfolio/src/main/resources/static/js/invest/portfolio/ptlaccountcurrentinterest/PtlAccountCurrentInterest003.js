(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            cashId: cashId,//业务编号
            effDate:effDate,
            rateType:rateType
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
        ajax: {
            url: 'ratePage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        searchOnInit:true,
        /**
         * 定义表格名称
         */
        cols:[
             {field:'indexIdStr', title: 'PtlAccountCurrentInterest001.rateType'}//状态
            ,{field:'effDate', title: 'PtlAccountCurrentInterest001.effDate',type:'date'}//业务编号
            ,{field:'rate', title: 'PtlAccountCurrentInterest001.intRate'}
            ,{field:'coefficient', title: 'PtlAccountCurrentInterest001.coefficient'}//账号名称
            ,{field:'spreadRate', title: 'PtlAccountCurrentInterest001.spreadRate'}//组合代码
            ,{field:'finRate', title: 'PtlAccountCurrentInterest001.finalRate'}//开始日
            
        ]
    });
    
    
    
    
    /**
     * 画面加载完成
     */
    $(function() {
    });
}());