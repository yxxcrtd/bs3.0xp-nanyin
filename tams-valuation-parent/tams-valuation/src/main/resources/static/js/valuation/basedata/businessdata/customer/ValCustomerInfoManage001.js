$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    };

    /**
     * 定义数据表格
     */
    var table=$("#ValCustomerInfoManage001").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols:[
            {field:'customercode', title: '客户代码'},
            {field:'customername', title: '客户名称'},
            {field:'customertype', title: '客户类型'},
            {field:'customernature', title: '客户性质'},
            {field:'capaccountdetails', title: '资金账户明细'},
            {field:'datatype', title: '数据来源'},
            {field:'buildtime', title: '创建时间'},
            {field:'changetime', title: '修改时间'}
        ]
    });

}());