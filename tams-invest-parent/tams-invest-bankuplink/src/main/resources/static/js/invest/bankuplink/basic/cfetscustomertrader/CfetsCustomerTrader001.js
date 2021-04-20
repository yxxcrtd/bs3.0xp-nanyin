(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            traderId: $('#traderId').val(),//交易员ID
            traderName: $('#traderName').val(),//交易员姓名
            partyName: $('#partyName').val(),//交易成员
            partyId:$('#partyId').val()//交易成员ID
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
         * 定义表格名称
         */
        checkbox: false,
        rowno: true,
        cols:[
            {field:'partyCustomerId', title: 'CfetsCustomerTrader001.partyId', width:'10%', sortField:"party_Id"}//交易成员ID
            ,{field:'partyName', sortField:"party_Name", title: 'CfetsCustomerTrader001.partyName', width:'10%'}//交易成员
            ,{field:'traderId', sortField:"trader_Id", title: 'CfetsCustomerTrader001.traderId', width:'10%'}//交易员ID
            ,{field:'traderName', sortField:"trader_Name", title: 'CfetsCustomerTrader001.traderName', width:'10%'}//交易员姓名
            ,{field:'telNo', sortField:"tel_No", title: 'CfetsCustomerTrader001.telNo', width:'10%'}//联系电话
            ,{field:'faxNo', sortField:"fax_No", title: 'CfetsCustomerTrader001.faxNo', width:'10%'}//传真
            ,{field:'email', title: 'CfetsCustomerTrader001.email', width:'15%'}//Email
            ,{field:'msn', title: 'CfetsCustomerTrader001.msn', width:'10%'}//MSN
            ,{field:'address', title: 'CfetsCustomerTrader001.address', width:'20%'}//通讯地址
            ,{field:'postCode',sortField:"post_Code", title: 'CfetsCustomerTrader001.postCode', width:'10%'}//邮政编号
            ,{field:'createTimeStr',sortField:"create_Time", title: 'CfetsCustomerTrader001.createTime',width:'12%'}//创建时间
            ,{field:'updateTimeStr',sortField:"update_Time", title: 'CfetsCustomerTrader001.updateTime',width:'12%'}//更新时间
        ]
    });
    
    
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取参数
        Joyin.exportExcel('export', paramObj);//导出
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询按钮
        $('#CfetsCustomerTrader001_btn_search').click(function() {
            table.load();//加载表格
        });
        //绑定到处按钮
        $('#CfetsCustomerTrader001_btn_export').click(function() {
            exportExcel();//导出
        });
    });
}());