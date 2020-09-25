 $(function(){
	 
	 	if($('#repId').val()!=''){
	 		$('#ReportModelManage002_btn_downtablefield').show();
	 	}
	 
	   var getSearchParams = function() {
			return {
				repId : $.trim($('#repId').val())
			}
		};
	   var addParam = $("#addParam")
	   		.joyintable(
   				{
					ajax : {
						url : basePath
								+ 'disclosure/information/config/reportmodelmanage/ReportModelManage002/findParamPage',
						type : 'POST',
						data : function() {
							return getSearchParams();
						}
					},
					checkbox : false,
					searchOnInit : true,
					loadonce : true,
					page : false,
					cols : [{
						field : 'paramName',
						title : '参数名称',
						width : 250,
						fixed : true
					},{
						field : 'name',
						title : '中文描述',
						width : 325,
						fixed : true
					},{
						field : 'paramType',
						title : '参数类型',
						width : 170,
						fixed : true
					},{
						field : 'operation',
						title : 'SysCommon.operation',
						type : 'action',
						width : 150,
						buttons : [ 'delt' ]
					}]
   				});
	   addParam.bind('click', '.delt', function(data, index) {
		   addParam.removeRow(data);
		});
	   
	   function addparam() {
		   var param = new Object;
//		   if($('#paramNum1').val()){
//			   param.paramNum=$('#paramNum1').val();
//		   }
//		   else{
//			   Joyin.alert("请输入参数编号",7);
//			   return;
//		   }
		   
		   param.paramNum=$('#paramNum1').val();
		   
		   
		   if($('#paramName1').val()){
			   param.paramName=$('#paramName1').val();
		   }
		   else{
			   Joyin.alert("请输入参数名称",7);
			   return;
		   }
		   if($('#name1').val()){
			   param.name=$('#name1').val();
		   }
		   else{
			   Joyin.alert("请输入参数描述",7);
			   return;
		   }
		   if($('#paramType1').val()){
			   param.paramType=$('#paramType1').val();
		   }
		   else{
			   Joyin.alert("请输入参数类型",7);
			   return;
		   }
		   var obj = addParam.getAllRows();
		   var index = 0;
		   for ( var i in obj) {
				if (obj[i].paramName == param.paramName) {
					index++;
				}
			}
			if (index == 0) {
				addParam.addRow(param);
			}
			else{
				Joyin.alert("参数已存在，请重新设置！",7);
				return;
			}
	    }
	    //新建
	    var sava = function() {
			var object = $.serializeToObject('#submitForm', true);
			object.pageModel = pageModel;
			var addParam = object.addParam;
			Joyin.ajaxJson("checkSave",object,function(result) {
						if (result) {
							var message = JoyinUtil
									.getMessage("DIS_COMMON_C_0001");
						}
						Joyin.confirm(message,function() {
							//新建
							Joyin.ajax(basePath + 'disclosure/information/config/reportmodelmanage/ReportModelManage002/insert',
									{
										pageModel : pageModel,
										object : JSON.stringify(object),
										addParam : JSON.stringify(addParam)
									},
									function(result) {
										var index = parent.layer.getFrameIndex(window.name);
										parent.layer.close(index);
									});
						
							
						});
					});
	    }
	    var downloadReport = function(){
	    	Joyin.exportExcel('downloadReport', {folder:$.trim($('#folder').val())});   
		  }
	    
/*		$("#uploadReport").css('width','200px');
		$("#uploadReport").css('height','30px');*/
		$(function(){
		 //规则增加事件
		 $('#ReportModelManage002_btn_paramAdd').click(function() {
			 addparam();
			});
		 $('#ReportModelManage002_btn_save').click(function() {
				sava();
			});
		 $('#downloadReport').click(function() {
			 downloadReport();
			});
	    });
   });
 
 var FileCommon = {
 		onUploaded : function(res) {
 		       if(res && res.length > 0){
 		    	  $('#fileName').val(res[0].srcName);
		    	   $('#filePath').val(res[0].fileNo);
 		    	   Joyin.ajax('uploadFile', {
 				    		   fileNo : res[0].fileNo,
 				    		   fileName : res[0].fileName,
 				    		   size :res[0].size,
 				    		   folder:$.trim($('#folder').val()),
 				    		   reportWarehouse:$.trim($('#reportWarehouse').val()),
 				    		   uploadFileName:res[0].srcName
 				    	   }, function(result) {
 				    			   upFileName = res[0].srcName;
 				    	   });
 		       }
 		}
 };
