$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            tableId: $.trim($('#tableId').val()),
            vatType: $.trim($('#vatType').val())
        }
    };

    /**
     * 会计场景计税配置
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findTaxSchaBokConfig',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit: false,
        cellMinWidth:100 ,
        checkbox:true,
        cols: [
            {field:'configId',  width:200, title: '配置代码'},
            {field:'tableIdCode', width:180, sortField:'tableId', title: '记账场景(会计事件编码)', },
            {field:'subjectNature', width:200, title: '发生额科目性质'},
            {field:'vatType',  width:140, title: '增值税类型', },
            {field:'taxPType',  width:140, title: '确认类型'},
            {field:'incomeSource',  width:140, title: '发生场景'},
            {field:'inOut',  width:140, title: '增项/减项'},
            {field:'calcType',  width:140, title: '计算方式'},
            {field:'cdFlag',  width:140, title: '发生科目借贷方向'},
            {field:'calcScale',  width:140, title: '占比比例(预留)'},
            {field:'calcSnature',  width:140, title: '占比科目性质'},
            {field:'calcBase',  width:140, title: '占比基数'},
            {field:'isCarrt',  width:140, title: '金额计入方式'},
            {field:'intRepayHandle',  width:140, title: '处理条件'},
            {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
                buttons: ['edit', 'delt']
            }
        ],
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("查看会计场景计税配置"),
                basePath + "valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE,  configId: data.configId, operateType: "view"},function(){

                });
        }
    });

    /**
     * 事件绑定：会计场景计税配置
     */
    var taxSchaBokConfigBind = function() {
        /** 事件绑定: 会计场景计税配置-查询 */
        $("#TaxSchBokCfgManage001_btn_search").click(function(event){
            table.load();//重载表格 - 参数设置配置
        });

        /** 事件绑定: 会计场景计税配置-导出 */
        $('#TaxSchBokCfgManage001_btn_export').click(function() {
            var paramObj = {};
            var rowArr = table.getAllCheckedRows();// 所有选中行数据
            if(rowArr == null || rowArr.length == 0){ // 按条件导出
                paramObj = getSearchParams();
            }else{// 按选中导出
                var keyStr = "";
                for(var i=0; i<rowArr.length; i++){
                    keyStr = keyStr + rowArr[i].configId + ",";
                }
                paramObj.configId = keyStr;
            }
            Joyin.exportExcel('exportTaxSchaBokConfig', paramObj);
        });


        /** 事件绑定: 会计场景计税配置-删除 */
        $('#TaxSchBokCfgManage001_btn_delete').click(function() {
            var object = {};
            var rowArr = table.getAllCheckedRows();// 所有选中行数据
            if(rowArr == null || rowArr.length == 0 || rowArr.length >1){
                layer.msg("请选择1条记录进行删除。");
            }else{
                object.configId = rowArr[0].configId;
                object.isDefault = rowArr[0].isDefault;
                object.operateType = "delete";
                Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage001/check', object, function(result) {
                    if (result.data || result.messageType === 'info') {
                        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                            WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage001/deleteTaxSchaBokConfig', object, "TaxSchBokCfgManage001_btn_delete", "TaxSchBokCfgManage002",function() {
                                table.reload();
                            });
                        });
                    } else {
                        WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage001/deleteTaxSchaBokConfig', object, "TaxSchBokCfgManage001_btn_delete", "TaxSchBokCfgManage002",function() {
                            table.reload();
                        });
                    }
                });
            }
        });

        /**
         * 绑定事件：会计场景计税配置- 新建
         */
        $("#TaxSchBokCfgManage001_btn_add").click(function(event){
            Joyin.dialog(JoyinUtil.getLabel("新建会计场景计税配置（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_NEW,  operateType: "add"},function(){
                    table.reload();
                });
        });

        /**
         * 绑定事件：会计场景计税配置 - 编辑
         */
        table.bind('click', '.edit', function(data, index) {
            Joyin.dialog(JoyinUtil.getLabel("编辑会计场景计税配置（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_EDIT, configId: data.configId, operateType: "edit"},function(){
                    table.reload();
                });
        });

        /**
         * 绑定事件：会计场景计税配置 - 删除
         */
        table.bind('click', '.delt', function(data, index) {
            var object = {};
            object.configId = data.configId;
            var isDefault =data.isDefault;
            if(isDefault==='Y'){
                Joyin.alert("默认税务方案中会计场景计税配置内容不可删除");
            }else{
                object.operateType = "delete";
                Joyin.ajaxJson(basePath + 'valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage001/check', object, function(result) {
                    if (result.data || result.messageType === 'info') {
                        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                            WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage001/deleteTaxSchaBokConfig', object, "TaxSchBokCfgManage001_btn_delete", "TaxSchBokCfgManage002",function() {
                                table.reload();
                            });
                        });
                    } else {
                        WorkFlow.doDeleteList(basePath + 'valuation/taxparametermanage/taxschbokcfgmanage/TaxSchBokCfgManage001/deleteTaxSchaBokConfig', object, "TaxSchBokCfgManage001_btn_delete", "TaxSchBokCfgManage002",function() {
                            table.reload();
                        });
                    }
                });
            }
        });
    };
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        taxSchaBokConfigBind();
    });
	
});



