 $(function(){
	   
	   var dataVal;
	   
	   var getVal = function(){
		   return evel(dataVal);
	   }
	   /**
	     * 左侧 - 获取查询参数
	     */
	    var getSearchKey = function() {
	        return {
	        	temName:$.trim($('#temName').val()),
	    		temDescription:$.trim($('#temDescription').val()),
	    		temLoad:REPORT_SERVER_PATH
	        }
	    };
	   
	   //初始化导出表单
	   var initTable2 = function(){
			   
			   dataVal=null;
			     //目录数据结构
	           var folder_info = {
	               folderUri:REPORT_SERVER_PATH,
	           }
	           Joyin.ajaxSync('searchReports', folder_info,function(result) {
	        	   if(result && result.statuCode == 200){
	        	   var reports = result.data;
	        	   var datas = new Array();
	    		   var datesList = new Array();
	    		   //循环返回的报表，以便取得每个报表的具体信息
	    		   var temName =$('#temName').val();
	    		   var temDescription =$('#temDescription').val();	
	    		   
	    	       $.each(reports, function(i, rep){
	    	    		   var s=rep.updateDate.indexOf("T");
	    		    	   var udate=(rep.updateDate).substring(0,s);
	    		    	   
	    		    	   var p=rep.creationDate.indexOf("T");
	    		    	   var cdate=(rep.creationDate).substring(0,p);
	    			  datas.push({code:rep.label,name:rep.description,temLoad:REPORT_SERVER_PATH,id:i+1,cdate:cdate,udate:udate});
	    			 }); 
	    		  if(temDescription===""&&temName!==""){
	    			    //英文名称模糊查询	    	
	    		    	$.each(datas,function(i){
	    		    		if(datas[i].code.indexOf(temName)>=0){					  
	    						   datesList.push(datas[i]);
	    			    	}
	    		    	})	    	
	    		   }else if(temName==="" && temDescription!==""){
	    			   //中文名称模糊查询
	    				$.each(datas,function(i){
	    		    		if(datas[i].name.indexOf(temDescription)>=0){					  
	    						   datesList.push(datas[i]);
	    			    	}
	    		    	})
	    		  }else if(temName!=="" && temDescription!==""){
	    			   //中文和英文名称模糊查询
	    				$.each(datas,function(i){
	    		    		if(datas[i].name.indexOf(temDescription)>=0&&datas[i].code.indexOf(temName)>=0){					  
	    						   datesList.push(datas[i]);
	    			    	}
	    		    	})
	    		  }		   
	    		   else{
	    			   datesList = datas;
	    		   }
	    			   
	    		   if(datas.length>0){
	    			   dataTable2.reload({data: datesList});
	    			   dataTable2.getAllRows();
	    		   }
	        	   }
	        	   });
	   }
	   /**
	    * 查询
	    */
	   var search = function(){
		   
		   dataVal=null;
		     //目录数据结构
           var folder_info = {
               folderUri:REPORT_SERVER_PATH,
           }
           Joyin.ajax('searchReports', folder_info,function(result) {
        	   if (result && result.statuCode == 200) {
        	   var reports = result.data;
        	   var datas = new Array();
    		   var datesList = new Array();
    		   //循环返回的报表，以便取得每个报表的具体信息
    		   var temName =$('#temName').val();
    		   var temDescription =$('#temDescription').val();	
    		   
    	       $.each(reports, function(i, rep){
//    	    		   var s=rep.updateDate.indexOf("T");
//    		    	   var udate=(rep.updateDate).substring(0,s);
//    		    	   
//    		    	   var p=rep.creationDate.indexOf("T");
//    		    	   var cdate=(rep.creationDate).substring(0,p);
    			  datas.push({keyId:rep.keyId,code:rep.label,name:rep.description,temLoad:REPORT_SERVER_PATH,id:i+1,cdate:rep.creationDate,udate:rep.updateDate});
    			 }); 
    		  if(temDescription===""&&temName!==""){
    			    //英文名称模糊查询	    	
    		    	$.each(datas,function(i){
    		    		if(datas[i].code&&datas[i].code.indexOf(temName)>=0){					  
    						   datesList.push(datas[i]);
    			    	}
    		    	})	    	
    		   }else if(temName==="" && temDescription!==""){
    			   //中文名称模糊查询
    				$.each(datas,function(i){
    		    		if(datas[i].name&&datas[i].name.indexOf(temDescription)>=0){					  
    						   datesList.push(datas[i]);
    			    	}
    		    	})
    		  }else if(temName!=="" && temDescription!==""){
    			   //中文和英文名称模糊查询
    				$.each(datas,function(i){
    		    		if(datas[i].name&&datas[i].name.indexOf(temDescription)>=0&&datas[i].code&&datas[i].code.indexOf(temName)>=0){					  
    						   datesList.push(datas[i]);
    			    	}
    		    	})
    		  }		   
    		   else{
    			   datesList = datas;
    		   }
    			   
    		   if(datas.length>0){
    			   dataTable.reload({data: datesList});
    		   }
        	   }
        	   });
		   //查询某个目录下，所有的报表
//           var reports = searchReports(folder_info);		    
//		   var datas = new Array();
//		   var datesList = new Array();
//		   //循环返回的报表，以便取得每个报表的具体信息
//		   var temName =$('#temName').val();
//		   var temDescription =$('#temDescription').val();	
//	       $.each(reports, function(i, rep){
//	    		   var s=rep.updateDate.indexOf("T");
//		    	   var udate=(rep.updateDate).substring(0,s);
//		    	   
//		    	   var p=rep.creationDate.indexOf("T");
//		    	   var cdate=(rep.creationDate).substring(0,p);
//			  datas.push({code:rep.label,name:rep.description,temLoad:REPORT_SERVER_PATH,id:i+1,cdate:cdate,udate:udate});
//			 }); 
//		  if(temDescription===""&&temName!==""){
//			    //英文名称模糊查询	    	
//		    	$.each(datas,function(i){
//		    		if(datas[i].code.indexOf(temName)>=0){					  
//						   datesList.push(datas[i]);
//			    	}
//		    	})	    	
//		   }else if(temName==="" && temDescription!==""){
//			   //中文名称模糊查询
//				$.each(datas,function(i){
//		    		if(datas[i].name.indexOf(temDescription)>=0){					  
//						   datesList.push(datas[i]);
//			    	}
//		    	})
//		  }else if(temName!=="" && temDescription!==""){
//			   //中文和英文名称模糊查询
//				$.each(datas,function(i){
//		    		if(datas[i].name.indexOf(temDescription)>=0&&datas[i].code.indexOf(temName)>=0){					  
//						   datesList.push(datas[i]);
//			    	}
//		    	})
//		  }		   
//		   else{
//			   datesList = datas;
//		   }
//			   
//		   if(datas.length>0){
//			   dataTable.reload({data: datesList});
//		   }
	   }
	   
	   //下载模板
	   var download = function(type){
		var object =null;
       	var rowArr = dataTable2.getAllCheckedRows();// 所有选中行数据
       	if(rowArr == null || rowArr.length ==0){
       		layer.msg("请勾选一个数据！");
       	}else if(rowArr.length == 1){
       		
       		object = rowArr[0].code;
       		var report_info = {
                 uri:object,//运行报表的URI
                 folderUri:REPORT_SERVER_PATH,//运行报表所在目录
                 downloadName:'report',//希望下载的名字：供exportReport使用
                 formatType:type,//下载的格式:供exportReport使用, 支持的格式有pdf,html,xls,xlsx,rtf,csv,xml,docx,odt,ods,jrprint
                 params:{'portnum':1, 'endDate':'2018-03-01', 'startDate':'2018-03-01'},//运行报表所使用的参数
             }
       		var obj = {};
       		obj.name = rowArr[0].name;
       		obj['data'] = JSON.stringify({'portnum':'A', 'endDate':'2018-03-01', 'startDate':'2018-03-01'});
       		obj.repName = rowArr[0].name;
        	obj.fileType = type;
        	obj.code = rowArr[0].code;
        	obj.url = exportReportPath(report_info);
        	Joyin.exportExcel('mergeExport', obj);
  			   //导出报表
            //exportReport(report_info);
         }else if(rowArr.length > 1){
        	 
        	 var datas = new Array();
        	 
        	 for (var int = 0; int < rowArr.length; int++) {
        		 var obj = {};
            		var report_info = {
                      uri:rowArr[int].code,//运行报表的URI
                      folderUri:REPORT_SERVER_PATH,//运行报表所在目录
                      downloadName:'report',//希望下载的名字：供exportReport使用
                      formatType:type,//下载的格式:供exportReport使用, 支持的格式有pdf,html,xls,xlsx,rtf,csv,xml,docx,odt,ods,jrprint
                      params:{'portnum':1, 'endDate':'2018-03-01', 'startDate':'2018-03-01'},//运行报表所使用的参数
                  }
        		obj.name = rowArr[int].description;
            	obj['data'] = JSON.stringify({'portnum':'A', 'endDate':'2018-03-01', 'startDate':'2018-03-01'});
            	obj.fileType = type;
        		obj.url = exportReportPath(report_info);
        		obj.code = rowArr[int].code;
        		datas.push(obj);
			}
        	 
        	 Joyin.exportExcel('exportZip', {datas:JSON.stringify(datas)});
//        	 Joyin.exportExcel('mergeExport', {datas:JSON.stringify(datas)});
        	 
         }	  
       	$("#toExport").css('display','none');
     }
	   
	   
	   /**
	     * 导出
	     */
	    var exportExcel = function() {
	        if (dataTable.getAllRows().length>0) {
	            var selArr = dataTable.getAllCheckedRows();
	            var datas = JSON.stringify(selArr);
	            if (!selArr || selArr.length == 0) {	     
		        	var paramObj = dataTable.getAllRows();
		        	var datasAll = JSON.stringify(paramObj);
		        	 Joyin.exportExcel('export', {
		        		 datasAll : datasAll
			            });
		        } else {
		            Joyin.exportExcel('export', {
		                datas : datas
		            });
		        }
	        }
	        else{
	        	initTable2();
	        	var paramObj = dataTable2.getAllRows();
	        	var datasAll = JSON.stringify(paramObj);
	        	 Joyin.exportExcel('export', {
	        		 datasAll : datasAll
		            });
	        }
	        
	    };
	    /**
	     * 导出
	     */
	    var exportExcel2 = function() {
		var selArr = dataTable2.getAllCheckedRows();
		var datas = JSON.stringify(selArr);
		if (!selArr || selArr.length == 0) {
			Joyin.exportExcel('export', {
				datasAll : null,
				temName:$.trim($('#temName').val()),
	    		temDescription:$.trim($('#temDescription').val()),
	    		temLoad:REPORT_SERVER_PATH
			});
		} else {
			Joyin.exportExcel('export', {
				datas : datas
			});
		}

	};
	   
	   /**
		 * 定义数据表格
		 */
	   var dataTable=$("#dataTable").joyintable({
	    	data: [],
		    searchOnInit: true,
		    page : false,
	    	cellMinWidth:100 ,
	    	 cols:[{field:'keyId',title:'主键',width:80,sortable:false,visible:false,align:'center'}
	    		,{field:'id',title:'序 号',width:80,sortable:false,align:'center'}
	            ,{field:'code',title: '模板英文名',width:250,sortable:false}
		        ,{field:'name',  title: '模板中文名',width:350,sortable:false}
		        ,{field:'temLoad',  title: '模板路径',width:150,sortable:false}
		        ,{field:'cdate',  title: '创建时间',width:150,sortable:false}
		        ,{field:'udate',  title: '更新时间',width:150,sortable:false}
		        ,{field:'operation',width:20, title: 'SysCommon.operation', type:'action', buttons: ['edit','delt']}
		        ]
	    });
	   /**
	     * 编辑页面
	     */
	   dataTable.bind('click', '.edit', function(data, index) {
	    	data.operateType = "edit";
	    	Joyin.dialog(JoyinUtil.getLabel("编辑"),
	                JoyinUtil.getRealPath("/disclosure/information/config/reportmodelmanage/ReportModelManage002/init"),
	                "90%", "90%", {pageModel:PAGE_MODEL_EDIT,keyId:data.keyId},
	                function(data){
	                	dataTable.reload();
	                	dataTable2.reload();
	                });
	    });
	   /**
	     * 删除页面
	     */
	   dataTable.bind('click', '.delt', function(data, index) {
	        data.operateType = "delete";
	            Joyin.confirm("确认删除该模板吗？", function(){
	            	Joyin.ajaxJson('deleteModle', {keyId:data.keyId}, function(result) {
	            		dataTable.reload();
	            		dataTable2.reload();
	 			   });
	        });
	    });
	   /**
		 * 定义数据表格
		 */
	   var dataTable2=$("#dataTable2").joyintable({
		   ajax: {
	            url: 'findPage',
	            type: 'POST',
	            data: function() {
	                return getSearchKey();
	            }
	        }, 
		    searchOnInit: false,
		    rowno : true,
		    page : true,
	    	cols:[{field:'keyId',title:'主键',width:80,sortable:false,visible:false,align:'center'}
	            ,{field:'code',title: '模板英文名',width:250,sortable:true}
		        ,{field:'name',  title: '模板中文名',width:350,sortable:true}
		        ,{field:'temLoad',  title: '模板路径',width:150,sortable:true}
		        ,{field:'cdate',  title: '创建时间',width:200,sortable:true}
		        ,{field:'udate',  title: '更新时间',width:200,sortable:true}
		        ,{field:'operation',width:20, title: 'SysCommon.operation', type:'action', buttons: ['edit','delt']}
		        ]
	    });
	   $("#toExport").css('min-width','50px'); 
	   $("#toExport").css('left','100px'); 
	   /**
	     * 编辑页面
	     */
	   dataTable2.bind('click', '.edit', function(data, index) {
	    	data.operateType = "edit";
	    	Joyin.dialog(JoyinUtil.getLabel("编辑"),
	                JoyinUtil.getRealPath("/disclosure/information/config/reportmodelmanage/ReportModelManage002/init"),
	                "90%", "90%", {pageModel:PAGE_MODEL_EDIT,keyId:data.keyId},
	                function(data){
	                	dataTable.reload();
	                	dataTable2.reload();
	                });
	    });
	   /**
	     * 删除页面
	     */
	   dataTable2.bind('click', '.delt', function(data, index) {
	        data.operateType = "delete";
	            Joyin.confirm("确认删除该模板吗？", function(){
	            	Joyin.ajaxJson('deleteModle', {keyId:data.keyId}, function(result) {
	            		dataTable.reload();
	            		dataTable2.reload();
	 			   });
	        });
	    });
	   // 配置页面[新建]
	    var addNew = function(){    	
	    	Joyin.dialog(JoyinUtil.getLabel('报表新建'), '/disclosure/information/config/reportmodelmanage/ReportModelManage002/init', "85%", "90%",null,
	    			function(result) {
	    			dataTable.reload();
	    			dataTable2.reload();
	    	}
	        );
	    	
	    };
	   /**
	    * 选择模板确认方法
	    * @returns
	    */
	   function modelSelect(){
		  //var modelSelectParam = dataTable.getAllCheckedRows();
		  var modelSelectParam = dataTable2.getAllCheckedRows();
		  if(modelSelectParam.length != 1){
			  Joyin.alert("请选择一个模板",7,null,null)
			  return;
		  }
		  Joyin.confirm("确认选择该模板", function(){
			  top.modelSelectParam = modelSelectParam;
			  Joyin.closeTopWindow();
			  return modelSelectParam;
         });
	   };
	   $(function(){
		   // 页面配置调用初始化方法；
		   if(isModelSelect == 'yes'){
			   $('#isModelSelect').css("display","block");
			   $('#oldBtnDiv').css("display","none");
			   //search();
			   dataTable2.load();
		   }
		   /**
		    * 模板选择提交
		    */
		   $('#ReportModelManage001_btn_submit').click(function(){
			   modelSelect();
		   });
		   // 页面调用初始化结束；
		   /**
		    * 查询
		    */
    	   $('#ReportModelManage001_btn_search').click(function() {
    		   //search();
    		   dataTable2.load();
           }); 
    	     /**
    	      * 下载
    	      */
    	   $('#ReportModelManage001_btn_download').click(function() {
           			// $("#toExport").css('display','block'); 
           });  
    	   /**
    	    *新建
    	    */
    	   $('#ReportModelManage001_btn_design').click(function() {
    		   addNew();
    	   });
    	   $('#excelExport').click(function() {
        	
    		   download("xlsx");
	        });
	        
	        $('#wordExport').click(function(){
	        	download("docx");
	        });
	        
	        $('#pdfExport').click(function(){
	        	download("pdf");
	        });
    	   
    	    /**
    	     * 导出
    	     */
    	    $('#ReportModelManage001_btn_export').click(function() {
    	    	
    	    	exportExcel2();
    	    });
	   });
   });
