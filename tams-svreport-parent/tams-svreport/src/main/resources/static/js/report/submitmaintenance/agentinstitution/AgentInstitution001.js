/**
 * 销售结算资金交易信息维护
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){
        var param = $.serializeToObject('#searchForm');
        return param;
    };

    /**
     * 新建
     */
    function showDialog() {
        var data={"operateType":"add"};
        Joyin.dialog(JoyinUtil.getLabel('代理销售机构信息新建'),basePath+'report/submitmaintenance/agentinstitution/AgentInstitution002/init', '98%', '95%',data,function(){table.reload();});
    }
    
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return searchform();
            }
        },
        dblclick:function(table, data) {
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("代理销售机构信息查看"),
                    JoyinUtil.getRealPath("report/submitmaintenance/agentinstitution/AgentInstitution002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[
             {field:'statusStr', title: '数据状态', width:'150',sortField:'status'}
            ,{field:'issuePartyId', title: '发行机构代码', width:'150',sortField:'issuePartyId'}
            ,{field:'agentInstitutionCode', title: '代理销售机构代码', width:'150',sortField:'agentInstitutionCode'}
            ,{field:'agentInstitutionName', title: '代理销售机构名称', width:'150',sortField:'agentInstitutionName'}
            ,{field:'remark', title: '备注', width:'150',sortField:'remark'}
            ,{field:'enclosureNum', title: '附件个数', width:'150',sortField:'enclosureNum'}
            ,{title : '操作附件',field : 'operation',type : 'action',width : 150
               ,buttons : [  {text : '&#xe67c;',clas : 'upload',title : '上传'}
               ,{text : '&#xe640;',clas : 'deltAy',title : '删除'}
               , {text : '&#xe601;',clas : 'download',title : '下载'}
               , {text : '&#xe615;',clas : 'fileView',title : '查看'}
               ,{field: 'sumitSeq',visible:false} ]}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt'] , width:'150'}
        ],
    });
/**
 * 导出表格
 */
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = searchform();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
  
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("代理销售机构信息编辑"),
                JoyinUtil.getRealPath("report/submitmaintenance/agentinstitution/AgentInstitution002/init"),
                "98%", "95%", data,
           function(){table.reload();});
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
                    if(data.status !="E"){
                        return Joyin.error("只能删除有效数据，请重新选择");
                    }
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'report/submitmaintenance/agentinstitution/AgentInstitution001/check', object, function(result) {
          Joyin.ajaxJson(basePath + 'report/submitmaintenance/agentinstitution/AgentInstitution001/deleteCheck', object, function(result1) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'report/submitmaintenance/agentinstitution/AgentInstitution001/delete', object,"AgentInstitution001_btn_delete", "AgentInstitution002", function(result) {
                                        table.reload();
                });
            });
          });
        });
    });
    /**
     * 编辑按钮事件
     */
    $(function () {
        // 绑定重置按钮事件
            $('#AgentInstitution001_btn_reset').click(function(){
                window.location.reload();
            });
        /**
         * 查询按钮
         */
        $('#AgentInstitution001_btn_search').click(function() {
            table.load();
            $('#AgentInstitution001_btn_delete').show();
        });
        /**
         * 导出按钮
         */
        $('#AgentInstitution001_btn_export').click(function() {
            exportExcel();
        });
        /**
         * 新建按钮
         */
        $('#AgentInstitution001_btn_add').click(function() {
            showDialog();
        });

    // 导入按钮点击事件
    $('#AgentInstitution001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
        var options = {};
        options.pageId = "AgentInstitution";
            Joyin.uploadFile(options,function(fileName) {
                Joyin.ajax(basePath + 'report/submitmaintenance/agentinstitution/AgentInstitution001/checkConfirmMsgForImportData', {fileName: fileName}, function() {
                    Joyin.ajax(basePath + 'report/submitmaintenance/agentinstitution/AgentInstitution001/importFile', {fileName: fileName});
                });
            },function() {
                table.reload();
            });
        });
    });

        /**
         * 批量删除按钮
         */
        $('#AgentInstitution001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                  if(selArr[i].status !="E"){
                        return Joyin.error("只能删除有效数据，请重新选择");
                  }
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'report/submitmaintenance/agentinstitution/AgentInstitution001/check', object, function(result) {
              Joyin.ajaxJson(basePath + 'report/submitmaintenance/agentinstitution/AgentInstitution001/deleteCheck', object, function(result1) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'report/submitmaintenance/agentinstitution/AgentInstitution001/delete', object,"AgentInstitution001_btn_delete", "AgentInstitution002", function(result) {
                        table.reload();
                    });
                });
              });
            });
        });

        		/**
        		 * 九大附件批量上传-批量上传（徽商合并）
        		 */
        		$('#SvrPerProDeclareReg001_btn_batchFileUpload').click(function() {
        			var uploadArr = table.getAllCheckedRows();
        			var uploadArrStr=JSON.stringify(uploadArr);
        			var dataArr=[];
        			var dataArrStr;
        			if(!uploadArr || uploadArr.length == 0){
        				Joyin.error("请选择要上传的数据！");
        				return;
        			}else{
        				for(var i = 0; i < uploadArr.length; i++){
        					if(uploadArr[i].sendStatus != '01'){
        						Joyin.error("只有未报送的数据才能进行上传！");
        						return;
        					}
        					var data={
        							fileNo:uploadArr[i].fileNo,
        							prodId:uploadArr[i].prodId,
        							registSeqno:uploadArr[i].registSeqno
        					}
        					dataArr[i]=data;

        				}
        				dataArrStr=JSON.stringify(dataArr);
        				Joyin.confirm("请确认是否要导入数据？", function() {
        					var flag=false;
        					for(var i = 0; i < uploadArr.length; i++){
        						if(uploadArr[i].fileNo && uploadArr[i].fileNo != null){
        							flag=true;
        						}
        					}
        					if(flag){
        						Joyin.confirm('所选产品已存在附件，是否覆盖？',
        								function(){
        							Joyin.dialog(JoyinUtil.getLabel("附件上传-修改"), basePath
        				                    + "report/chb/perprodeclarereg/PerProDeclareReg002/init", "70%",
        				                    "60%", {
        				                        pageModel : PAGE_MODEL_NEW,
        				                        businessId : uploadArr.prodId,
        				                        dataArrStr : dataArrStr
        				                    },function(){
        				                    	table.reload();
        				                    });
        						})
        					}else{
        						Joyin.dialog(JoyinUtil.getLabel("附件上传"), basePath
        			                    + "report/chb/perprodeclarereg/PerProDeclareReg002/init", "70%",
        			                    "60%", {
        			                        pageModel : PAGE_MODEL_NEW,
        			                        businessId : uploadArr.prodId,
        			                        dataArrStr : dataArrStr
        			                    },function(){
        			                    	table.reload();
        			                    });
        					}
        		        });
        			}
        		});
    });
}());