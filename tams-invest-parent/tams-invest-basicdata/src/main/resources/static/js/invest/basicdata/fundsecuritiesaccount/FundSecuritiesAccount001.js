/**
 * 基金账户管理
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            secAcctId: $('#secAcctId').val(),
            manager : $('#manager').val()
        };
    };
    /**
     * 新建
     */
    function showDialog() {
        var data={"operateType":"add"};
        Joyin.dialog(JoyinUtil.getLabel('FundSecuritiesAccount002.add'),
        	basePath+'invest/basicdata/fundsecuritiesaccount/FundSecuritiesAccount002/init', '80%', '80%',
        	data,function(){table.reload();});
    }

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
        /*
         * 双击查看数据
         */
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("FundSecuritiesAccount002.reference"),
                    JoyinUtil.getRealPath("invest/basicdata/fundsecuritiesaccount/FundSecuritiesAccount002/init"),
                    "95%", "80%", data,
                    function(){table.reload();});
        },
       
        cols:[
              // 基金管理人
             {field:'managerName',sortField:'manager', width:'20%' ,title: 'FundSecuritiesAccount001.manager'}
             // 账户账号
             ,{field:'secAcctId', sortField:'secAcctId', width:'20%',title: 'FundSecuritiesAccount001.secAcctId'}
             // 账户名称
             ,{field:'secAcctName',sortField:'secAcctName', width:'20%', title: 'FundSecuritiesAccount001.secAcctName'}
             // 备注
             ,{field:'remark',sortField:'REMARK', width:'30%', title: 'FundSecuritiesAccount001.remark'}
             ,{field:'manager',visible:false}
             ,{field:'operation',width:'25%', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    var exportExcel = function(){
        var paramObj=getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType='edit' ;
      	Joyin.dialog(JoyinUtil.getLabel("FundSecuritiesAccount002.edit"),
      			JoyinUtil.getRealPath("invest/basicdata/fundsecuritiesaccount/FundSecuritiesAccount002/init"),
                    "95%", "80%", data,
                    function(){table.reload();});
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/fundsecuritiesaccount/FundSecuritiesAccount001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/fundsecuritiesaccount/FundSecuritiesAccount001/delete', object, "FundSecuritiesAccount001_btn_delete", "FundSecuritiesAccount002",function(){
                    table.reload();
                });
            });
        });
    });
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FundSecuritiesAccount001_btn_search').click(function() {
            table.load();
            $('#FundSecuritiesAccount001_btn_delete').show();
        });
        $('#FundSecuritiesAccount001_btn_export').click(function() {
            exportExcel();
        });
        $('#FundSecuritiesAccount001_btn_add').click(function() {
            showDialog();
        });
        $('#FundSecuritiesAccount001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/basicdata/fundsecuritiesaccount/FundSecuritiesAccount001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/fundsecuritiesaccount/FundSecuritiesAccount001/delete', object, "FundSecuritiesAccount001_btn_delete", "FundSecuritiesAccount002");
                });
            });
        });
    });
}());