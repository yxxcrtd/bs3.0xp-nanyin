$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    };

    /**
     * 定义数据表格
     */
    var table=$("#ValStructureInfoManage001").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols:[
            {field:'subject', width:100, title: '标的'},
            {field:'subjectdefine', width:150, title: '标的定义'},
            {field:'subjectdefineappoint', width:100, title: '标的定义及约定'},
            {field:'hooksclassify', width:100, title: '挂钩标的分类'},
            {field:'trancal', width:100, title: '交易日历'},
            {field:'regulerrules', width:100, title: '调整规则'},
            {field:'remark', width:100, title: '备注'},
            {field:'datatype', width:100, title: '数据来源'},
            {field:'buildtime', width:100, title: '创建时间'},
            {field:'changetime', width:100, title: '修改时间'},
            {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });

}());