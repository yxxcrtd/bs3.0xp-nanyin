$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    };

    /**
     * 定义数据表格
     */
    var table=$("#ValNaturePerManage001").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols:[
            {field:'name', width:100, title: '姓名', sort: true},
            {field:'sex', width:100, title: '性别'},
            {field:'cardid', width:150, title: '身份证号码', sort: true},
            {field:'officephone', width:150, title: '办公电话'},
            {field:'mobilephone', width:150, title: '移动电话', sort: true},
            {field:'workunit', width:100, title: '工作单位'},
            {field:'position', width:100, title: '职位', sort: true},
            {field:'propertystatus', width:150, title: '财产状况'},
            {field:'address', width:120, title: '地址', sort: true},
            {field:'email', width:150, title: '电子邮箱'},
            {field:'datatype', width:100, title: '数据来源', sort: true},
            {field:'buildtime', width:100, title: '创建时间', sort: true},
            {field:'changetime', width:100, title: '修改时间'},
            {field:'operation', title: '操作', type:'action', buttons: ['edit', 'delt']}
        ]
    });

}());