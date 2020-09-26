$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    };

    /**
     * 定义数据表格
     */
    var table=$("#ValChannelManage001").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols:[
            {field:'sourcecode', width:100, title: '通道代码', sort: true},
            {field:'sourcename', width:260, title: '通道名称'},
            {field:'sourcetype', width:100, title: '通道类型', sort: true},
            {field:'sourcecharge', width:300, title: '通道费用'},
            {field:'startdate', width:100, title: '成立日期', sort: true},
            {field:'enddate', width:100, title: '终止日期'},
            {field:'manager', width:100, title: '管理人', sort: true},
            {field:'trustee', width:100, title: '托管人'},
            {field:'datatype', width:100, title: '数据来源', sort: true},
            {field:'buildtime', width:100, title: '创建时间', sort: true},
            {field:'changetime', width:100, title: '修改时间'},
            {field:'operation', title: '操作', type:'action', buttons: ['edit', 'delt']}
        ]
    });

}());