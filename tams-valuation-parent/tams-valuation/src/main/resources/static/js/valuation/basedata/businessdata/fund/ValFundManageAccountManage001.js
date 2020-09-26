$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    };

    /**
     * 定义数据表格
     */
    var table=$("#ValFundManageAccountManage001").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols:[
            {field:'fundmanager', title: '基金管理人', sort: true},
            {field:'accountcode', title: '账户代码'},
            {field:'accountname', title: '账户名称', sort: true},
            {field:'remark', title: '备注'},
            {field:'datatype', title: '数据来源', sort: true},
            {field:'buildtime', title: '创建时间', sort: true},
            {field:'changetime', title: '修改时间'},
            {field:'operation', title: '操作', type:'action', buttons: ['edit', 'delt']}
        ]
    });

}());