(function(){
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var prodId=$('#FinProductScheduling001_input_prodId').val();
        var vdateStart=$('#FinProductScheduling001_date_vdate').startDate();
        var vdateEnd = $('#FinProductScheduling001_date_vdate').endDate();
            return {
            	prodId : prodId,
            	vdateStart : vdateStart,
            	vdateEnd : vdateEnd
        }
    };
	/**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
    	ajax: {
            url: 'selectForPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
    	
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("FinProductScheduling002.reference"),
                    JoyinUtil.getRealPath("invest/product/productelement/scheduling/FinProductScheduling002/init"),
                    "98%", "95%",data,function(){table.reload();}
            );
        },
        
        cols:[
             {field:'finprodMarketId', title: 'FinProductScheduling001.finprodMarketId'}
            ,{field:'finprodName', title: 'FinProductScheduling001.finprodName'}
            ,{field:'finprodAbbr', title: 'FinProductScheduling001.finprodAbbr'}
            ,{field:'prodRegistCode', title: 'FinProductScheduling001.prodRegistCode'}
            ,{field:'vdate', title: 'FinProductScheduling001.vdate', type:'date'}
            ,{field:'mdate', title: 'FinProductScheduling001.mdate', type:'date'}
            ,{field:'raiseStrdate', title: 'FinProductScheduling001.raiseStartDate', type:'date'}
            ,{field:'raiseEnddate', title: 'FinProductScheduling001.raiseEndDate', type:'date'}
            ,{field:'issueMode', title: 'FinProductScheduling001.issueMethod'}
            ,{field:'remark', title: 'FinProductScheduling001.remark'}
            ,{field:'finprodId', visible:false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/product/productelement/scheduling/FinProductScheduling001/check', data, function(result) {
        	Joyin.dialog(JoyinUtil.getLabel("FinProductScheduling002.edit"),
                    basePath + "invest/product/productelement/scheduling/FinProductScheduling002/init",
                    "98%", "95%", data,function(){table.reload();});
            
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	var object = {selectedItem: [data], operateType:"delete", finprodId : data.finprodId, finprodMarketId : data.finprodMarketId, finprodName : data.finprodName, finprodAbbr : data.finprodAbbr, vdate : data.vdate};
    	data.operateType = 'delete';
        Joyin.ajaxJson(basePath + 'invest/product/productelement/scheduling/FinProductScheduling001/check', data, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/product/productelement/scheduling/FinProductScheduling001/delete', object, "FinProductScheduling001_btn_delete", "FinProductScheduling002");
            });
        });
    });
    
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };
    
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("FinProductScheduling002.new"),
                JoyinUtil.getRealPath("invest/product/productelement/scheduling/FinProductScheduling002/init"),
                "98%", "90%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
	
	$(function() {
		// 绑定按钮事件
        $('#FinProductScheduling001_btn_search').click(function() { 
            table.load();
        });
        $('#FinProductScheduling001_btn_export').click(function() {
        	exportExcel();
        });
        $('#FinProductScheduling001_btn_add').click(function() {
        	openNew();
        });
	});
}());