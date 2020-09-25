$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            fundCode: $('#portfolioName').val(),
            fundManager: $('#createUser').val(),
            valuationDay : $('#createDate').val(),
            valuationSource:$('#status').val(),
        };
    };

    /**
     * 定义数据表格
     */
    
    var table=$("#dataTable").joyintable({
        data:[
              {
            	  "status":"C",
                  "groupName":"01234",
                  "createUser":"system",
                  "createTime":"2018-11-01",
                  "remark":"fadfe"
              },
              {
            	  "status":"C",
                  "groupName":"0222",
                  "createUser":"system",
                  "createTime":"2018-10-22",
                  "remark":"dfae"
              },
              {
            	  "status":"E",
                  "groupName":"3333",
                  "createUser":"system",
                  "createTime":"2018-11-01",
                  "remark":"dfee"
              }
              ],
              /**
               * 定义表格双击事件
               */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("PortfolioManageInfo002.title"),
                    basePath + "invest/basicdata/portfoliomanageInfo/PortfolioManageInfo002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                    deptCode:data.deptCode,
                                    status:data.status});
        },
        /*
         * 定义表头
         */
        cols:[
            {field:'status',width:'20%', title: 'PortfolioManageInfo001.status'}
            ,{field:'groupName',width:'20%',  title: 'PortfolioManageInfo001.groupName'}
            ,{field:'createUser',width:'20%',title:'PortfolioManageInfo001.createUser'}
            ,{field:'createTime',width:'20%',title:'PortfolioManageInfo001.createTime'}
            ,{field:'remark',width:'20%', title:'PortfolioManageInfo001.remark'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
            Joyin.dialog(JoyinUtil.getLabel("PortfolioManageInfo002.title"),
                    basePath + "invest/basicdata/portfoliomanageInfo/PortfolioManageInfo002/init",
                    "80%", "80%", {pageModel:PAGE_MODEL_EDIT,
                                    fundCode:data.fundCode,
                                    fundType:data.fundType,
                                    valuationDay:data.valuationDay,
                                    thousandsOfIncome:data.thousandsOfIncome,
                                    fundManager:data.fundManager
                                    
                                    });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/portfoliomanageInfo/PortfolioManageInfo002/delete', object, "PortfolioManageInfo001_btn_delete", "PortfolioManageInfo002");
        });
    });

    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#PortfolioManageInfo001_btn_search').click(function() {
            table.load();
            $('#PortfolioManageInfo001_btn_delete').show();
        });
       
        $('#PortfolioManageInfo001_btn_export').click(function() {
            exportExcel();
        });
        $('#PortfolioManageInfo001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PortfolioManageInfo002.title"),
                        basePath + "invest/basicdata/portfoliomanageInfo/PortfolioManageInfo002/init",
                        "90%", "90%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#PortfolioManageInfo001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/basicdata/portfoliomanageInfo/PortfolioManageInfo002/delete', object, "PortfolioManageInfo001_btn_delete", "PortfolioManageInfo002");
            });
        });
    });
}());