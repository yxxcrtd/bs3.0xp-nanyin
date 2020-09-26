$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	acctNo: $('#acctNo').val(),
        	acctName : $('#acctName').val(),
        	bankDep : $('#bankDep').val()
        };
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
		cellMinWidth : 100,
        cols:[
            {field:'acctNo', width:170, title: '账号'}
            ,{field:'acctName', width:150, title: '户名'}
            ,{field:'bankDep', width:100, title: '开户行'}
            ,{field:'inOutBank', width:100, title: '行内行外'}
            ,{field:'corrNo', width:100, title: '联行行号'}
            ,{field:'largePayno', width:100, title: '大额支付系统号', visible:false}
            ,{field:'inOut',  title: '境内境外'}
            ,{field:'cyy',  title: '币种'}
            ,{field:'isOverdraw',  title: '是否允许透支'}
            ,{field:'isDvp', title: '是否DVP清算', sortable:false}
            ,{field:'status', visible:false}
            ,{field:'operation', title: 'SysCommon.operation',align:'center', fixed:'right',type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("SysDepartment002.edit"),
                basePath + "valuation/squaremanagement/mstcapacctinfo/ValMstCapAcctInfo002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_EDIT,
        	                    acctNo:data.acctNo,
        	                    dealType:'edit'});
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
        	Joyin.ajaxJson(basePath + 'valuation/squaremanagement/mstcapacctinfo/ValMstCapAcctInfo001/delete', data, function(result) {
        		 table.reload();
        	});
        });
    });

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValMstCapAcctInfo001_BTN_search').click(function() {
            table.load();
            $('#ValMstCapAcctInfo001_BTN_delete').show();
        });
        $('#ValMstCapAcctInfo001_BTN_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("清算账户新建"),
                        basePath + "valuation/squaremanagement/mstcapacctinfo/ValMstCapAcctInfo002/init",
                        "95%", "80%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#ValMstCapAcctInfo001_BTN_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("VAL_COMMON_E_0002"));
                return;
            }
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            	 $.each(selArr,function(i,value){
                 	var data = value;
                	Joyin.ajaxJson(basePath + 'valuation/squaremanagement/mstcapacctinfo/ValMstCapAcctInfo001/delete', data, function(result) {
                  		 
                  	});
                 });
            	 table.reload();
            });
        });
    });
}());