/**
 * 证券账户管理
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            secAcctId: $('#secAcctId').val(),
            registOrg : $('#registOrg').val(),
            portfolioId:$('#portfolioId').val()
        };
//        return $.serializeToObject('#searchForm');
    };
    /**
     * 新建
     */
    function showDialog() {
        var data={"operateType":"add"};
        Joyin.dialog(JoyinUtil.getLabel('MstSecuritiesAccount002.add'),basePath+'invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount002/init', '80%', '80%',data,function(){table.reload();});
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
            //data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("MstSecuritiesAccount002.reference"),
                    JoyinUtil.getRealPath("invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount002/init"),
                    "95%", "80%", data,
                    function(){table.reload();});
        },
       
        cols:[
            {field:'secAcctId', sortField:'SEC_ACCT_ID', width:'15%',title: 'MstSecuritiesAccount001.secAcctId'}
            ,{field:'secAcctName',sortField:'SEC_ACCT_NAME', width:'15%', title: 'MstSecuritiesAccount001.secAcctName'}
            ,{field:'registOrgStr',sortField:'REGIST_ORG', width:'15%', sortField:"REGIST_ORG" ,title: 'MstSecuritiesAccount001.registOrg'}
            ,{field:'registOrg',width:'15%',visible:false}
            ,{field:'recAcctId',width:'15%',visible:false}
            //,{field:'busiAcctNo2',sortField:'busiAcctNo2', width:'15%', title: 'MstSecuritiesAccount001.recAcctId'}
            ,{field:'trdAcctId',width:'15%',visible:false}
            ,{field:'busiAcctNo1',sortField:'busiAcctNo1', width:'15%', title: 'MstSecuritiesAccount001.trdAcctId'}
            ,{field:'acctName',width:'15%',sortField:'acctName', title: 'MstSecuritiesAccount001.trdAcctName'}
           ,{field:'tradePlaceStr',width:'15%',sortField:'tradePlace', title: 'MstSecuritiesAccount001.tradePlace'}
            ,{field:'secAcctSourceStr',width:'15%',sortField:'secAcctSource', title: 'MstSecuritiesAccount001.secAcctSource'}
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
        data.operateType='edit'
            //加上这一条在点击修改按钮时就开始check
            //Joyin.ajaxJson(basePath + 'invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("MstSecuritiesAccount002.edit"),
                    JoyinUtil.getRealPath("invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount002/init"),
                    "95%", "80%", data,
                    function(){table.reload();});
            //});
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount001/delete', object, "MstSecuritiesAccount001_btn_delete", "MstSecuritiesAccount002",function(){
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
        $('#MstSecuritiesAccount001_btn_search').click(function() {
            table.load();
            $('#MstSecuritiesAccount001_btn_delete').show();
        });
        $('#MstSecuritiesAccount001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstSecuritiesAccount001_btn_add').click(function() {
            showDialog();
        });
        $('#MstSecuritiesAccount001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount001/delete', object, "MstSecuritiesAccount001_btn_delete", "MstSecuritiesAccount002");
                });
            });
        });
    });
}());