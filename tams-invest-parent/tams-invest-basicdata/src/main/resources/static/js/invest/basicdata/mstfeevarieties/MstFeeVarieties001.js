/**
 * 查询费用品种信息
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	feeName: $('#feeName').val(),
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
            Joyin.dialog(JoyinUtil.getLabel("MstFeeVarieties002.reference"),
                    JoyinUtil.getRealPath("invest/basicdata/mstfeevarieties/MstFeeVarieties002/init"),
                    "98%", "95%",data,function(){table.reload();});
        },
        
        cols:[
             {field:'feeId',width:'20%', title: 'MstFeeVarieties002.feeId',visible: false}
            ,{field:'feeName',width:'20%', title: 'MstFeeVarieties002.feeName'}
            ,{field:'applyTypeStr',width:'20%', sortField:'applyType', title: 'MstFeeVarieties002.applyType'}
            ,{field:'feeTypeStr',width:'20%',sortField:'feeType',title: 'MstFeeVarieties002.feeType'}
            ,{field:'orgTypeStr',width:'20%',sortField:'orgType', title: 'MstFeeVarieties002.orgType'}
            ,{field:'orgType2Str',width:'20%',sortField:'orgType2', title: 'MstFeeVarieties002.orgType2'}
            ,{field:'chargeTypeStr',width:'20%', sortField:'chargeType',title: 'MstFeeVarieties002.chargeType'}           
            ,{field:'basicBillStr',width:'20%',sortField:'basicBill', title: 'MstFeeVarieties002.basicBill'}
            ,{field:'basisStr',width:'20%',sortField:'basis', title: 'MstFeeVarieties002.basis'}
            ,{field:'remark',width:'20%', title: 'MstFeeVarieties002.remark'}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit'
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstfeevarieties/MstFeeVarieties002/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("MstFeeVarieties002.edit"),
                    basePath + "invest/basicdata/mstfeevarieties/MstFeeVarieties002/init",
                    "95%", "90%", data,function(){table.reload();});
            
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstfeevarieties/MstFeeVarieties001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/basicdata/mstfeevarieties/MstFeeVarieties001/delete', object, "MstFeeVarieties001_btn_delete", "MstFeeVarieties002");
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
        $('#MstFeeVarieties001_btn_search').click(function() {
            table.load();
        });
        $('#MstFeeVarieties001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstFeeVarieties001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("MstFeeVarieties002"),
                        basePath + "invest/basicdata/mstfeevarieties/MstFeeVarieties002/init",
                        "85%", "80%", {pageModel:PAGE_MODEL_NEW});
        });
    });
}());