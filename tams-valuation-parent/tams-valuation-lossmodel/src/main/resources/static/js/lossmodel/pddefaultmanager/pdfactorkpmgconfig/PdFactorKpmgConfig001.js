$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
		return {
        	// 年份
        	vouchYear : $('#vouchYear').val() == null ? null : $('#vouchYear').val(),
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
                basePath + "model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002/init",
                "95%", "90%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    seqno: data.seqno
                });
        },
        cols: [
            {field: 'seqno', visible: false}
            , {field: 'vouchYear', title: '年份',sortable:false}
            , {field: 'combinedFactor', title: '综合系数',sortable:false}
            , {field: 'action', width: 200, title: 'SysCommon.operation', type: 'action', buttons: ['edit', 'delt']}
        ]
    });
    
	
	 /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002/check', data, function (result) {
            Joyin.dialog('前瞻因子维护编辑',
                basePath + "model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002/init",
                "45%", "75%", {
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
        Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig001/check', object, function(result) {
            if(result.data.indexOf("前瞻因子")>0){
				Joyin.confirm(result.data, function(){
                	Joyin.confirm("重新跑批后影响历史减值结果，是否确认删除？", function(){
						WorkFlow.doDeleteList(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig001/delete', object, "PdFactorConfig001_btn_delete", "PdFactorConfig002",function(){
                    		table.reload();
                		});
					});
            	});	
			}else{
				Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                	WorkFlow.doDeleteList(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig001/delete', object, "PdFactorConfig001_btn_delete", "PdFactorConfig002",function(){
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
                JoyinUtil.getRealPath("/model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig002/init"),
                "45%", "75%",{pageModel : PAGE_MODEL_NEW},function(){table.reload();}
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
        $('#PdFactorKpmgConfig001_btn_add').click(function() {
        	showDialog();
        });

		//导出
        $('#PdFactorKpmgConfig001_btn_export').click(function() {
            exportExcel();
        });
        //初始化查询
        $('#PdFactorKpmgConfig001_btn_search').click(function() {
            table.load();
			$('#PdFactorKpmgConfig001_btn_delete').show();
        });
		$('#PdFactorKpmgConfig001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig001/check', object, function(result) {
                if(result.data.indexOf("前瞻因子")>0){
					Joyin.confirm(result.data, function(){
	                	Joyin.confirm("重新跑批后影响历史减值结果，是否确认删除？", function(){
							WorkFlow.doDeleteList(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig001/delete', object, "PdFactorKpmgConfig001_btn_delete", "PdFactorKpmgConfig002",function(){
	                    		table.reload();
                        });
						});
	            	});	
				}else{
					Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	                	WorkFlow.doDeleteList(basePath + 'model/impairment/pddefaultmanager/pdfactorkpmgconfig/PdFactorKpmgConfig001/delete', object, "PdFactorKpmgConfig001_btn_delete", "PdFactorKpmgConfig002",function(){
	                    	table.reload();
	                	});
	            	});
				}
            });
        });
        
    });
});