 $(function(){
	 if(pageModel=='1'){
		 //编辑时，报表编号不可修改
		 $('#tabNum').attr("disabled","true");
	 }
	 
	 	if($('#repId').val()!=''){
	 		$('#ReportModelManage002_btn_downtablefield').show();
	 	}
	 
	   var getSearchParams = function() {
			return {
				tabNum : $.trim($('#tabNum').val())
			}
		};
	   var addParam = $("#addParam")
	   		.joyintable(
   				{
					ajax : {
						url : basePath
								+ 'disclosure/information/config/poireportmodelmanage/PoiReportModelManage002/findParamPage',
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
						field : 'disId',
						title : '查询条件编号',
						width : 250,
						fixed : true
					},{
						field : 'disOrder',
						title : '序号',
						width : 325,
						fixed : true
					},{
						field : 'operation',
						title : 'SysCommon.operation',
						type : 'action',
						width : 370,
						buttons : [ 'delt' ]
					}]
   				});
	   addParam.bind('click', '.delt', function(data, index) {
		   addParam.removeRow(data);
		});
	   
	   function addparam() {
		   var param = new Object;
		   /**
		    * 查询条件
		    */
		   if($('#disId').val()){
			   var  arr=$('#disId').val();
			   param.disId=arr;
		   }
		   else{
			   Joyin.alert("请输入查询条件",7);
			   return;
		   }
		   /**
		    * 序号
		    */
		   if($('#disOrder').val()){
			   param.disOrder=$('#disOrder').val();
		   }
		   else{
			   Joyin.alert("请输入序号",7);
			   return;
		   }
		   var obj = addParam.getAllRows();
		   var index = 0;
		   for ( var i in obj) {
				if (obj[i].disId == param.disId) {
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
			//新建
			if(pageModel=='0'){
				var  files=$('#uploadReport').data('getSelectedFiles')();
                if(files.length==0){
                	//则最终没有上传文件
                	Joyin.alert("请上传文件",7);
                	return;
                }
				var addParam = object.addParam;
				var  arr=object.disId;
				var  id=arr[0];
				object.disId=id;
			}else if(pageModel=='1'){
				//编辑
				var addParam = object.addParam;
				addParam=JSON.stringify(addParam);
				object.liparams=addParam;
			}
			Joyin.ajaxJson("checkSave",object,function(result) {
						if (result) {
							var message = JoyinUtil
									.getMessage("DIS_COMMON_C_0001");
						}
						Joyin.confirm(message,function() {
							//新建
							Joyin.ajax(basePath + 'disclosure/information/config/poireportmodelmanage/PoiReportModelManage002/insert',
									{
										pageModel : pageModel,
										object : JSON.stringify(object),
										addParam : JSON.stringify(addParam),
										liparams:object.liparams
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
		 $('#PoiReportModelManage002_btn_paramAdd').click(function() {
			 addparam();
			});
		 $('#PoiReportModelManage002_btn_save').click(function() {
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
 				    		   fileName1:$.trim($('#fileName').val()),
 				    		   folder:$.trim($('#tempName').val()),
 				    		   reportWarehouse:$.trim($('#reportWarehouse').val()),
 				    		   uploadFileName:res[0].srcName
 				    	   }, function(result) {
			    				   //upFileName = res[0].srcName;
 				    	   });
 		       }
 		}
 };
 

