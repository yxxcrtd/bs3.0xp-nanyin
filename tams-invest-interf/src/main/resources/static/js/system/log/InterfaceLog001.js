(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {

        return {
            interfId: $.trim($('#interfId').val()),
            interfSts: $.trim($('#interfSts').val()),
            beginTime: $('#createTime').startDate(),
            endTime: $('#createTime').endDate(),

        }
    };

    
    /**
     * 定义数据表格
     */
    
    var table=$("#dataTable").joyintable({
            ajax: {
                url: 'findPage',//请求路径
                type: 'POST',//请求类型
                data: function() {
                    return getSearchParams();//获取请求参数
                }//ajax请求数据
            },

        /**
         * 定义表头
         */
        cols:[
             {field:'interfId',title: '接口代码',width:150} //接口代码
            ,{field:'interfName',title: '接口名称',width:150}//接口名称
            ,{field:'interfType',title: '接口类型',width:150}//接口类型
            ,{field:'interfSts',title:'状态',width:100}//状态
            ,{field:'interfTermtrc',title:'接口流水号',width:250}//接口流水号
            ,{field:'msgBody',title:'报文',width:300}//报文
            ,{field:'sendFlg',title:'是否手工发送',width:150}//是否手工发送
            ,{field:'sendCount',title:'发送次数',width:150}//发送次数
            ,{field:'createTime',title:'发送日期',width:200,type:'datetime'}//发送日期
            ,{ field:'operation', title: 'SysCommon.operation', type: 'action', width: 20, buttons: [{
                text: '&#xe614;',
                clas: 'config',
                title: '再次发送'
            }]}//再次发送
        ],
        dblclick:function(table, data) {
             Joyin.alert(data.msgBody);
        },
        checkbox: false,
        sortfont: false,
        rowno:true,
        page:true
    });
    table.bind('click', '.config', function (data, index) {

            Joyin.confirm("确认再次发送此条数据吗？", function () {
                Joyin.ajax('restartSend', {interfTermtrc: data.interfTermtrc,msgBody:data.msgBody}, function (data) {
                    Joyin.alert("发送成功！");
                    setTimeout(function () {
                        table.reload();
                    }, 1000)
                });
            });

    });
    /**
     * 画面加载完成
     */
    $(function() {

        //初始化查询
        $('#InterfaceLog001_btn_search').click(function() {
            table.load();
        });

    });
}());
