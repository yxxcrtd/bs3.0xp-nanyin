(function(){
    /**
     * 获取查询参数
     */
    var searchform = $.serializeToObject('#searchForm');

    //新建
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('CurrentRateManage002'), '../CurrentRateManage002/init', '98%', '95%');
    }
    
 
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
       /* ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },*/
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("CurrentRateManage002"),
                    JoyinUtil.getRealPath("invest/basicdata/currentratemanage/CurrentRateManage002/init"),
                    "98%", "95%", data,
                    function(){table.load();});
        },
        data:[ {'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
                ,{'accountId':'10086ee','status':'有效','accountName':'谢欣廷的账户','rateType':'活期','rate':'0.15','currency':'人民币','effectDate':'1111-11-1','pid':'4321'}
              ],
        
        
        cols:[
             
            {field:'accountId', title: 'CurrentRateManage001.accountId'}
            ,{field:'status', title: 'CurrentRateManage001.status'}
            ,{field:'accountName', title: 'CurrentRateManage001.accountName'}
            ,{field:'rateType', title: 'CurrentRateManage002.rateType'}
            ,{field:'rate', title: 'CurrentRateManage002.rate'}
            ,{field:'currency', title: 'CurrentRateManage002.currency'}
            ,{field:'effectDate', title: 'CurrentRateManage002.effectDate'}
            ,{field:'pid', visible: false}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt'] , width:150}
        ],
        loadonce:true
    });
  
    var exportExcel = function(){       
        Joyin.exportExcel('export', searchform);
    };
  
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("CurrentRateManage002"),
                JoyinUtil.getRealPath("invest/basicdata/currentratemanage/CurrentRateManage002/init"),
                "98%", "95%", data,
           function(){table.load();});
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        data.operateType = "delete";
        Joyin.ajaxJson(basePath + 'invest/basicdata/currentratemanage/CurrentRateManage001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/currentratemanage/CurrentRateManage001/delete', data, "CurrentRateManage001_btn_delete", "CurrentRateManage001");
            });
        });
    });
    
    $(function () {
        // 绑定按钮事件
        $('#CurrentRateManage001_btn_search').click(function() {
            table.load();
            $('#CurrentRateManage001_btn_delete').show();
        });
        
        $('#CurrentRateManage001_btn_export').click(function() {
            exportExcel();
        });
        $('#CurrentRateManage001_btn_add').click(function() {
            showDialog();
        });
        $('#CurrentRateManage001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/basicdata/currentratemanage/CurrentRateManage001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/currentratemanage/CurrentRateManage001/deleteList', object, "CurrentRateManage001_btn_delete", "CurrentRateManage001");
                });
            });
        });
    });
}());