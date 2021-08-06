$(function() {
	
	/*layui.use('laydate', function(){
		  var laydate = layui.laydate;
		  //执行一个laydate实例
		  laydate.render({
		    elem: '#vouchYear',//指定元素
		    type: 'year',
			format: 'yyyy',
		    value: searchDataEnd
		  });
		});*/
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
		return {
        	// 年份
        	vouchYear : $('#vouchYear').val() == null ? null : $('#vouchYear').val(),
        	// 季度
        	vouchQuarter : $('#vouchQuarter').val() == null ? null : $('#vouchQuarter').val()
		}
    };
    
     /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick: function (table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig002/init",
                "95%", "90%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    seqno: data.seqno
                });
        },
        cols: [
	
            {field: 'seqno', visible: false}
            , {field: 'vouchYear', title: 'PdFactorConfig001.vouchYear',sortable:false}
            , {field: 'vouchQuarterStr', title: 'PdFactorConfig001.vouchQuarter',sortable:false}
            , {field: 'optimisticFactor', title: 'PdFactorConfig001.optimisticFactor',sortable:false}
           /* , {field: 'optimisticWeight', title: 'PdFactorConfig001.optimisticWeight',width:150}*/
			, {field: 'basicFactor', title: 'PdFactorConfig001.basicFactor',sortable:false}
            /*, {field: 'basicWeight', title: 'PdFactorConfig001.basicWeight',width:150}*/
			, {field: 'pessimismFactor', title: 'PdFactorConfig001.pessimismFactor',sortable:false}
            /*, {field: 'pessimismWeight', title: 'PdFactorConfig001.pessimismWeight',width:150}*/
            , {field: 'action', width: 200, title: 'SysCommon.operation', type: 'action', buttons: ['edit', 'delt']}
        ]
    });
    
	
	 /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig002/check', data, function (result) {
            Joyin.dialog('前瞻因子维护编辑',
                basePath + "model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig002/init",
                "95%", "90%", {
                    pageModel: PAGE_MODEL_EDIT,
                    seqno: data.seqno
                }, function () {
                    table.reload();
                });
        });
    });
    
	/**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig001/check', object, function(result) {
            if(result.data.indexOf("前瞻因子")>0){
				Joyin.confirm(result.data, function(){
                	Joyin.confirm("重新跑批后影响历史减值结果，是否确认删除？", function(){
						WorkFlow.doDeleteList(basePath + 'model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig001/delete', object, "PdFactorConfig001_btn_delete", "PdFactorConfig002",function(){
                    		table.reload();
                		});
					});
            	});	
			}else{
				Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                	WorkFlow.doDeleteList(basePath + 'model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig001/delete', object, "PdFactorConfig001_btn_delete", "PdFactorConfig002",function(){
                    	table.reload();
                	});
            	});
			}
			
        });
    });

    /**
     * 弹出新建画面
     * @returns
     */
    var showDialog = function(){
        Joyin.dialog(JoyinUtil.getLabel("前瞻因子新建"),
                JoyinUtil.getRealPath("/model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig002/init"),
                "95%", "90%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }

	 /**
     * 弹出权重配置画面
     * @returns
     */
    var showWeightDialog = function(){
        Joyin.dialog(JoyinUtil.getLabel("权重配置"),
                JoyinUtil.getRealPath("/model/impairment/pddefaultmanager/pdfactorconfig/PdFactorWeightConfig003/init"),
                "95%", "90%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
        );
    }
    
   	var exportExcel = function(){       
        Joyin.exportExcel('export',getSearchParams()) 
    };
 	
	function showYear() {
    	var laydate = layui.laydate;
     	laydate.render({
 			elem: '#vouchYear',
 			type: 'year',
 			format: 'yyyy'
 		});
     }

       
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
		$("#vouchYear").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
		});
    	showYear();
    	//查询
        $('#PdFactorConfig001_btn_add').click(function() {
        	showDialog();
        });
		//权重配置
		$('#PdFactorConfig001_btn_weight').click(function() {
        	showWeightDialog();
        });
		//导出
        $('#PdFactorConfig001_btn_export').click(function() {
            exportExcel();
        });
        //初始化查询
        $('#PdFactorConfig001_btn_search').click(function() {
            table.load();
			$('#PdFactorConfig001_btn_delete').show();
        });
		$('#PdFactorConfig001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig001/check', object, function(result) {
                if(result.data.indexOf("前瞻因子")>0){
					Joyin.confirm(result.data, function(){
	                	Joyin.confirm("重新跑批后影响历史减值结果，是否确认删除？", function(){
							WorkFlow.doDeleteList(basePath + 'model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig001/delete', object, "PdFactorConfig001_btn_delete", "PdFactorConfig002",function(){
	                    		table.reload();
	                		});
						});
	            	});	
				}else{
					Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	                	WorkFlow.doDeleteList(basePath + 'model/impairment/pddefaultmanager/pdfactorconfig/PdFactorConfig001/delete', object, "PdFactorConfig001_btn_delete", "PdFactorConfig002",function(){
	                    	table.reload();
	                	});
	            	});
				}
            });
        });
        
    });
});