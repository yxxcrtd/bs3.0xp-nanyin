$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    };

    /**
     * 定义数据表格
     */
    var table=$("#TransCalManage001").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols:[
            {field:'roleId', width:240, title: '交易日历名称'}
            ,{field:'roleName', width:240, title: '包含市场日历类型'}
            ,{field:'rolePosName', width:240, title: '数据来源'}
            ,{field:'remark', width:270, title: '创建时间'}
            ,{field:'status', title: '修改时间'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });

}());