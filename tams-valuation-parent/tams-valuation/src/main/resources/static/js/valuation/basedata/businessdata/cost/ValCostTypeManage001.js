$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    };

    /**
     * 定义数据表格
     */
    var table=$("#ValCostTypeManage001").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols:[
            {field:'validstate', width:100, title: '有效状态'},
            {field:'costname', width:150, title: '费用名称'},
            {field:'associaterange', width:100, title: '关联范围'},
            {field:'chargetype', width:100, title: '收费类型'},
            {field:'calbasis', width:100, title: '计算基础'},
            {field:'bilebase', width:100, title: '计费基数'},
            {field:'accounttype', width:100, title: '记账类型'},
            {field:'costtype', width:100, title: '费用类型'},
            {field:'costsectype', width:150, title: '费用二级类型'},
            {field:'remark', width:100, title: '备注'},
            {field:'datatype', width:100, title: '数据来源'},
            {field:'buildtime', width:100, title: '创建时间'},
            {field:'changetime', width:100, title: '修改时间'},
            {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });

}());