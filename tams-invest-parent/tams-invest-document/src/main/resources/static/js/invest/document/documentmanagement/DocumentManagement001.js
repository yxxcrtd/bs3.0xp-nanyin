/**
 * 文档管理-查询
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var portfolioIdTemp = $('#portfolioId').val();
        var portfolioId= null;
        if(portfolioIdTemp != null)
        	portfolioId=portfolioIdTemp.join(",");
        return {
            portfolioId:portfolioId,//组合代码
            startDate:$('#createTime').startDate(),//上传日期
            endDate:$('#createTime').endDate(),
            businessType: $('#businessType').val(),//业务类型
            businessId: $('#businessId').val(),//业务编号
            deptInstitutionCode: $('#deptInstitutionCode').val(),//所属部门
            userName:$("#userName").val()//上传人
        }
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable(
            {
            	ajax: {
                    url: 'findPage',
                    type: 'POST',
                    data: function() {
                        return getSearchParams();
                    }
                },
                /* 

                dblclick : function(table, data) {
                    Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"), basePath
                            + "invest/document/stockmarketinfo/StockMarketInfo002/init", "95%",
                            "90%", {
                                pageModel : PAGE_MODEL_REFERENCE,
                                netWorthMarketCode : data.netWorthMarketCode,
                                status : data.status
                            });
                },*/
                
                cols : [ 
                         {field : 'portfolioId',  title : 'DocumentManagement001.portfolioId',sortable:true}
                         ,{field : 'portfolioName', sortField:'portfolioName', title : 'DocumentManagement001.portfolioName',sortable:true}
                       , {field : 'businessTypeStr', sortField:'businessType', title : 'DocumentManagement001.businessType',sortable:true}
                       , {field : 'businessId',  title : 'DocumentManagement001.businessId',sortable:true}
                       , {field : 'deptInstitutionCode',  title : 'DocumentManagement001.deptInstitutionCode',sortable:true}
                       , {field : 'userName',  title : 'DocumentManagement001.userName',sortable:true}
                       , {field: 'operation',title: 'SysCommon.operation', type: 'action',width: 150,
                    	   render:function(data,type,row,meta){
                    		   var buttons = "";
                    		   if(purviewElements.indexOf('_batch')!=-1){
                    			   buttons+='<i class="iconfont icon-upload batch" title="上传"></i>'; 
                    		   }
                    		   if(purviewElements.indexOf('_manual')!=-1){
                    			   buttons+='<i class="btn layui-icon manual" title="附件详情">附件详情</i>'; 
                    		   }
                    		   return buttons;
                    	   }
//                           buttons: [{
//                               text: '',
//                               clas: 'iconfont icon-upload',
//                               title: '上传'
//                           }, {
//                           	text: '附件详情',
//                           	clas: 'details',
//                        	title: '附件详情'
//                        }]
                       }]
            });

    /**
     * 绑定上传事件
     */
    table.bind('click','.batch',function(data, table){
        Joyin.confirm("请确认是否要导入数据？", function() {
        	Joyin.dialog(JoyinUtil.getLabel("DocumentManagement002"), basePath
                    + "invest/document/documentmanagement/DocumentManagement002/init", "70%",
                    "60%", {
                        pageModel : PAGE_MODEL_NEW,
                        businessId : data.businessId
                    });
        });
    });
    
    /**
     * 绑定附件详情事件
     */
    table.bind('click','.manual',function(data, table){
        Joyin.dialog(JoyinUtil.getLabel("DocumentManagement003"), basePath
                + "invest/document/documentmanagement/DocumentManagement003/init", "50%",
                "40%", {
                    pageModel : PAGE_MODEL_EDIT,
                    businessId : data.businessId
                });
    });
    
    
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#DocumentManagement001_btn_search').click(function() {
            table.load();
        });
        $('#DocumentManagement001_btn_export').click(function() {
            exportExcel();
        });
        
        /*$('#DocumentManagement001_btn_delete').click(
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
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil
                            .getLabel("SysCommon.delete")), function() {
                        WorkFlow.doDeleteList(basePath
                                + 'invest/document/documentmanagement/DocumentManagement001/delete',
                                object, "DocumentManagement001_btn_delete", "SysInstitution002");
                    });
                });*/
    });
}());