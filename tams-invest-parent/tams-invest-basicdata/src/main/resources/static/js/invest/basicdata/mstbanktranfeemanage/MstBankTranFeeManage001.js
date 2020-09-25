/**
 * 查询银行间交易费用设置
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	registOrg: $('#registOrg').val(),
        	feeType:$('#feeType').val(),
        }
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
    	
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("MstBankTranFeeManage002.reference"),
                    JoyinUtil.getRealPath("invest/basicdata/mstbanktranfeemanage/MstBankTranFeeManage002/init"),
                    "98%", "95%",data,function(){table.reload();});
        },
        
        cols:[
             {field:'batchSeq',visible: false}
            ,{field:'feeTypeStr',width:'20%',sortField:'feeType',title: 'MstBankTranFeeManage001.feeType'}//费用类型
            ,{field:'registOrgStr',width:'20%',sortField:'registOrg', title: 'MstBankTranFeeManage001.registOrg'}//登记托管机构
            ,{field:'tradeTypeStr',width:'20%',sortField:'tradeType', title: 'MstBankTranFeeManage001.tradeType'}//交易品种
            ,{field:'psStr',width:'20%', sortable:false,title: 'MstBankTranFeeManage001.ps'}//交易方向           
            ,{field:'chargeTypeStr',width:'20%',sortField:'chargeType', title: 'MstBankTranFeeManage001.chargeType'}//计费类型
            ,{field:'chargeBasisStr',width:'20%',sortField:'chargeBasis', title: 'MstBankTranFeeManage001.chargeBasis'}//计费基础
            ,{field:'feeRate',width:'20%', title: 'MstBankTranFeeManage001.feeRate'}//费用值/费率值
            ,{field:'feeMaxi',width:'20%', title: 'MstBankTranFeeManage001.feeMaxi',type:'money'}//最大金额
            ,{field:'feeMini',width:'20%', title: 'MstBankTranFeeManage001.feeMini',type:'money'}//最小金额
            ,{field:'effDate',width:'20%', title: 'MstBankTranFeeManage001.effDate',type:'date'}//生效日
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        data.chargeValue = 0;
        data.chargeRate = 0;
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstbanktranfeemanage/MstBankTranFeeManage002/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("MstBankTranFeeManage002.edit"),
                    basePath + "invest/basicdata/mstbanktranfeemanage/MstBankTranFeeManage002/init",
                    "95%", "98%", data,function(){table.reload();});
            
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstbanktranfeemanage/MstBankTranFeeManage001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstbanktranfeemanage/MstBankTranFeeManage001/delete', object, "MstBankTranFeeManage001_btn_delete", "MstBankTranFeeManage002");
            });
        });
    });
   
    
    var exportExcel = function(){       
        Joyin.exportExcel('export',getSearchParams()) 
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#MstBankTranFeeManage001_btn_search').click(function() {
            table.load();
            $('#MstBankTranFeeManage001_btn_delete').show();
        });
        $('#MstBankTranFeeManage001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstBankTranFeeManage001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstBankTranFeeManage002"),
                        basePath + "invest/basicdata/mstbanktranfeemanage/MstBankTranFeeManage002/init",
                        "95%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#MstBankTranFeeManage001_btn_delete')
            .click(
                function() {
                    if (table != null && table != '') {
                        var selArr = table.getAllCheckedRows();
                    }
                    if (!selArr || selArr.length == 0) {
                        Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                        return;
                    }
                    var object = {
                        selectedItem : selArr,
                        operateType : "delete"
                    };
                    Joyin
                        .ajaxJson(
                            basePath
                            + 'invest/basicdata/mstbanktranfeemanage/MstBankTranFeeManage001/check',
                            object,
                            function(result) {
                                Joyin
                                    .confirm(
                                        JoyinUtil
                                            .getMessage(
                                                "COMMON_C_0001",
                                                JoyinUtil
                                                    .getLabel("SysCommon.delete")),
                                        function() {
                                            WorkFlow
                                                .doDeleteList(
                                                    basePath
                                                    + 'invest/basicdata/mstbanktranfeemanage/MstBankTranFeeManage001/delete',
                                                    object,
                                                    "MstBankTranFeeManage001_btn_delete",
                                                    "MstBankTranFeeManage002");
                                        });
                            });
                });
    });
}());