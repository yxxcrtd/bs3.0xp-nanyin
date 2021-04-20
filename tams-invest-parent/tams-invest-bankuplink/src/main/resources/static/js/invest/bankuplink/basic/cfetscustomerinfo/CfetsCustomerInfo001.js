(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
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
            {field:'partyCustomerId',title: 'CfetsCustomerInfo001.partyId', width:'10%', sortField:"PARTY_CUSTOMER_ID"},//交易成员ID
            {field:'partyId', title: 'CfetsCustomerInfo001.partyCustomerId', width:'15%', sortField:"PARTY_ID"},//交易账户21位码
            {field:'partyName', title: 'CfetsCustomerInfo001.partyName', width:'15%', sortField:"PARTY_NAME"},//交易成员全称
            {field:'mpartyAbbr', title: 'CfetsCustomerInfo001.mpartyAbbr', width:'10%', sortField:"MPARTY_ABBR"},//主机构
            {field:'partyAbbr', title: 'CfetsCustomerInfo001.partyAbbr', width:'10%', sortField:"PARTY_ABBR"},//中文简称
            {field:'partyNameEn', title: 'CfetsCustomerInfo001.partyNameEn', width:'10%', sortField:"PARTY_NAME_EN"},//英文全称
            {field:'partyAbbrEn', title: 'CfetsCustomerInfo001.partyAbbrEn', width:'10%', sortField:"PARTY_ABBR_EN"},//英文简称
            {field:'createTimeStr', title: 'CfetsCustomerInfo001.createTime', width:'11%', sortField:"CREATE_TIME"},//创建时间
            {field:'updateTimeStr', title: 'CfetsCustomerInfo001.updateTime', width:'11%', sortField:"UPDATE_TIME"}//更新时间
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
        $('#CfetsCustomerInfo001_btn_search').click(function() {
            table.load();//加载表格
        });
        //绑定到处按钮
        $('#CfetsCustomerInfo001_btn_export').click(function() {
            exportExcel();//导出
        });
    });
}());