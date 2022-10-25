 $(function(){
	   
	   var dataVal;
	   
	   var getVal = function(){
		   return evel(dataVal);
	   }
	   /**
	     * 左侧 - 获取查询参数
	     */
	    var getSearchKey = function() {
		   var prodId = $('#finprodId').val();
		   if(prodId && prodId != ''){
			   prodId = prodId.join(',');
		   }else {
			   prodId = null;
		   }
	        return {
	        	/*temName:$.trim($('#temName').val()),
	    		temDescription:$.trim($('#temDescription').val()),
	    		temLoad:REPORT_SERVER_PATH*/
				prodId : prodId,
				dataStatus: $.trim($('#dataStatus').val()),
				openStartDateBegin: $('#openStartDate').startDate(),
				openStartDateEnd: $('#openStartDate').endDate(),
				openEndDateBegin: $('#openEndDate').startDate(),
				openEndDateEnd: $('#openEndDate').endDate(),
				operationStartDateBegin: $('#operationStartDate').startDate(),
				operationStartDateEnd: $('#operationStartDate').endDate(),
				autoRedeemDateBegin: $('#autoRedeemDate').startDate(),
				autoRedeemDateEnd: $('#autoRedeemDate').endDate(),
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
				   datesList = datas;
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
    			  datas.push({prodPlanId:rep.prodPlanId,code:rep.label,name:rep.description,temLoad:REPORT_SERVER_PATH,id:i+1,cdate:rep.creationDate,udate:rep.updateDate});
    			 }); 
			   datesList = datas;
    		   if(datas.length>0){
    			   dataTable.reload({data: datesList});
    		   }
        	   }
        	   });
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
/*	 var exportExcel2 = function() {
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

	 };*/
	 var exportExcel2 = function() {
		 var paramObj = getSearchKey();//请求参数json格式
		 Joyin.exportExcel('export', paramObj);//导出（共通）
	 };

	   
	   /**
		 * 定义数据表格
		 */
	   var dataTable=$("#dataTable").joyintable({
	    	data: [],
		    searchOnInit: true,
		    page : false,
	    	cellMinWidth:100 ,
	    	 cols:[{field:'prodPlanId',title:'主键',width:80,sortable:false,visible:false,align:'center'}
	    		/*,{field:'prodPlanId',title:'序 号',width:80,sortable:false,align:'center'}*/
				 ,{field:'prodName',  title: '产品名称',width:250,sortable:false}
				 ,{field:'prodId',  title: '产品主代码',width:150,sortable:false}
				 ,{field:'openStartDateStr',  title: '开放起始日',width:100,sortable:false}
				 ,{field:'openEndDateStr',  title: '开放结束日',width:100,sortable:false}
				 ,{field:'collingoffPeriodStr',  title: '产品冷静期',width:100,sortable:false}
				 ,{field:'operationStartDateStr',  title: '运作起始日',width:100,sortable:false}
				 ,{field:'autoRedeemDateStr',  title: '自动赎回日',width:100,sortable:false}
				 ,{field:'holdDays',  title: '实际持有天数',width:120,sortable:false}
				 ,{field:'dataStatusName',title: '数据状态',width:90,sortable:false}
				 ,{field:'updateDateStr',  title: '更新时间',width:140,sortable:false}
				 ,{field:'updateUser',  title: '更新人',width:100,sortable:false}
				 ,{field:'operation',width:20, title: 'SysCommon.operation', type:'action', buttons: ['edit','delt'],
					 render: function (data, type, row, meta) {
						 if (row.dataStatus == 'D')
							 return '';
						 else
							 return '<i class="btn layui-icon edit edit" title="编辑" data-ignore="false"></i> ' +
								 '<i class="btn layui-icon delt delt" title="删除" data-ignore="false"></i>';
					 }
				 }
		        ]
	    });
	   /**
	     * 编辑页面
	     */
	   dataTable.bind('click', '.edit', function(data, index) {
	   	   debugger;
	    	data.operateType = "edit";
	    	Joyin.dialog(JoyinUtil.getLabel("编辑"),
	                JoyinUtil.getRealPath("/disclosure/information/config/disprodplanmanage/DisProdPlanManage002/init"),
	                "90%", "90%", {pageModel:PAGE_MODEL_EDIT,prodPlanId:data.prodPlanId,dataStatus: data.dataStatus},
	                function(data){
	                	dataTable.reload();
	                	dataTable2.reload();
	                });
	    });
	   /**
	     * 删除页面
	     */
	   dataTable.bind('click', '.delt', function(data, index) {
		   var selArrUseful =[];
		   selArrUseful.push(data);
		   var object = {selectedItem: selArrUseful, operateType: "delete"};//请求参数
		   Joyin.ajaxJson(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/check', object, function (result) {
			   Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
				   WorkFlow.doDeleteList(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/delete', object,
					   "DisProdPlanManage001_btn_delete", "DisProdPlanManage002",function(result) {
						   dataTable.reload();
						   dataTable2.reload();
					   });
			   });
		   });


	    });

	 //删除
	 $('#DisProdPlanManage001_btn_delete').click(function () {
		 if (dataTable2 != null && dataTable2 != '') {
			 var selArr = dataTable2.getAllCheckedRows();
		 }
		 if (!selArr || selArr.length == 0) {
			 Joyin.error(JoyinUtil.getMessage("请至少选择一条记录"));
			 return;
		 }
		 var selArrUseful =[];
		 var selArrDFH = [];
		 selArr.forEach(v => {
			 if(v.dataStatus == 'E'){
				 //selArr.remove();
				 selArrUseful.push(v);
			 }
			 if(v.dataStatus == 'C'){
				 //selArr.remove();
				 selArrDFH.push(v);
			 }
		 })
		 if ((!selArrUseful || selArrUseful.length == 0) && (!selArrDFH || selArrDFH.length == 0)){
			 Joyin.error(JoyinUtil.getMessage("只能删除有效数据，请重新选择!"));
			 return;
		 }
		 if ( selArrDFH.length > 0) {
			 Joyin.error(JoyinUtil.getMessage("数据在流程中，不允许删除"));
			 return;
		 }
		 var object = {selectedItem: selArrUseful, operateType: "delete"};//请求参数
		 Joyin.ajaxJson(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/check', object, function (result) {
		 Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
			 WorkFlow.doDeleteList(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/delete', object,
				 "DisProdPlanManage001_btn_delete", "DisProdPlanManage002",function(result) {
					 dataTable.reload();
					 dataTable2.reload();
				 });
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
		   /**
			* 双击查看
			*/
		   dblclick: function (dataTable2, data) {
			   data.pageModel=PAGE_MODEL_REFERENCE;
			   Joyin.dialog(JoyinUtil.getLabel("产品排期计划查看"),
				   basePath + "disclosure/information/config/disprodplanmanage/DisProdPlanManage002/init",
				   "60%", "90%", data,function(){dataTable2.reload();});
		   },
		   searchOnInit: false,
		   rowno : true,
		   page : true,
		   cols:[{field:'prodPlanId',title:'主键',width:80,sortable:false,visible:false,align:'center'}
			   /*,{field:'prodPlanId',title:'序 号',width:80,sortable:false,align:'center'}*/
			   /*,{field:'prodPlanId',title:'序 号',width:80,sortable:false,align:'center'}*/
			   ,{field:'prodName',  title: '产品名称',width:250}
			   ,{field:'prodId',  title: '产品主代码',width:150,sortField:'prod_id'}
			   ,{field:'openStartDateStr',  title: '开放起始日',width:100,sortField:'openStartDateStr'}
			   ,{field:'openEndDateStr',  title: '开放结束日',width:100,sortField:'openEndDateStr'}
			   ,{field:'collingoffPeriodStr',  title: '产品冷静期',width:100,sortField:'collingoffPeriodStr'}
			   ,{field:'operationStartDateStr',  title: '运作起始日',width:100,sortField:'operationStartDateStr'}
			   ,{field:'autoRedeemDateStr',  title: '自动赎回日',width:100,sortField:'autoRedeemDateStr'}
			   ,{field:'holdDays',  title: '实际持有天数',width:120}
			   ,{field:'dataStatusName',title: '数据状态',width:90,sortField:'dataStatus'}
			   ,{field:'updateDateStr',  title: '更新时间',width:140,sortField:'updateDateStr'}
			   ,{field:'updateUser',  title: '更新人',width:100}
			   ,{field:'operation',width:20, title: 'SysCommon.operation', type:'action', buttons: ['edit','delt'],
				   render: function (data, type, row, meta) {
					   if (row.dataStatus == 'D' || row.dataStatus == 'C')
						   return '';
					   else
						   return '<i class="btn layui-icon edit edit" title="编辑" data-ignore="false"></i> ' +
							   '<i class="btn layui-icon delt delt" title="删除" data-ignore="false"></i>';
				   }
			   }
		   ]
	    });
	   $("#toExport").css('min-width','50px'); 
	   $("#toExport").css('left','100px'); 
	   /**
	     * 编辑页面
	     */
	   dataTable2.bind('click', '.edit', function(data, index) {
	    	data.operateType = "edit";
	    	Joyin.dialog(JoyinUtil.getLabel("产品排期计划编辑"),
	                JoyinUtil.getRealPath("/disclosure/information/config/disprodplanmanage/DisProdPlanManage002/init"),
	                "60%", "90%", {pageModel:PAGE_MODEL_EDIT,prodPlanId:data.prodPlanId,dataStatus: data.dataStatus},
	                function(data){
	                	dataTable.reload();
	                	dataTable2.reload();
	                });
	    });
	   /**
	     * 删除页面
	     */
	   dataTable2.bind('click', '.delt', function(data, index) {
		   if(data.dataStatus == 'D'){
			   Joyin.alert("数据已删除",7,null,null)
			   return;
		   }
		   // Joyin.confirm("确认删除吗？", function(){
			//    var object = {selectedItem: [data], operateType: "delete"};//请求参数
			//    WorkFlow.doDeleteList(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/delete', object,
			// 	   "DisProdPlanManage001_btn_delete", "DisProdPlanManage002",function(result) {
			// 		   dataTable.reload();
			// 		   dataTable2.reload();
			// 	   });
		   // });

		   var selArrUseful =[];
		   selArrUseful.push(data);
		   var object = {selectedItem: selArrUseful, operateType: "delete"};//请求参数
		   Joyin.ajaxJson(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/check', object, function (result) {
			   Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
				   WorkFlow.doDeleteList(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/delete', object,
					   "DisProdPlanManage001_btn_delete", "DisProdPlanManage002",function(result) {
						   dataTable.reload();
						   dataTable2.reload();
					   });
			   });
		   });



	    });
	   // 配置页面[新建]
	    var addNew = function(){    	
	    	Joyin.dialog(JoyinUtil.getLabel('产品排期计划新建'), '/disclosure/information/config/disprodplanmanage/DisProdPlanManage002/init', "60%", "90%",{pageModel:PAGE_MODEL_NEW},
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

	 	function addrule() {
			Joyin.dialog("产品选择",basePath+"disclosure/information/config/disprodplanmanage/DisProdPlanManage003/init","80%","80%",
				{},function () {
					var finprodIds = $('#finprodIds').data('finprodIds');
					if(finprodIds.length ==0){
						return;
					}
					$('#finprodId').textSelect('setValue',finprodIds);
				});
			return;
		};
	   $(function(){
		   // 页面配置调用初始化方法；
		   if(isModelSelect == 'yes'){
			   $('#isModelSelect').css("display","block");
			   $('#oldBtnDiv').css("display","none");
			   //search();
			   dataTable2.load();
		   }

		   $('#DisProdPlanManage001_btn_add').click(addrule);

		   //导出
		   $('#DisProdPlanManage001_btn_export').click(function () {
			   exportExcel2();
		   });

		   /**
		    * 模板选择提交
		    */
		   $('#DisProdPlanManage001_btn_submit').click(function(){
			   modelSelect();
		   });
		   // 页面调用初始化结束；
		   /**
		    * 查询
		    */
    	   $('#DisProdPlanManage001_btn_search').click(function() {
    		   //search();
    		   dataTable2.load();
           }); 

    	   /**
    	    *新建
    	    */
    	   $('#DisProdPlanManage001_btn_design').click(function() {
    		   addNew();
    	   });
		   // 导入按钮
		   // $('#DisProdPlanManage001_btn_import').click(function() {
			//    Joyin.confirm("请确认是否要导入数据？", function () {
			// 	   Joyin.uploadFile(function(fileName) {
			// 		   Joyin.ajax(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/checkImportData', {fileName: fileName}, function(checkResult) {
			// 			   if (checkResult) {
			// 				   setTimeout( function () {
			// 						   if(checkResult.data.errorMsg){
			// 							   Joyin.error(checkResult.data.errorMsg);
			// 							   return;
			// 						   }
			// 						   // confirmMsg3校验-->confirmMsg校验-->提交  ,三层嵌套
			// 						   confirmMsg3ImportExcel(checkResult);
			// 					   }
			// 					   ,1000);
			// 			   }
			// 		   });
			// 	   },function() {
			// 		   table.reload();
			// 	   });
			//    });
		   // });

		   $('#DisProdPlanManage001_btn_import').click(function() {
			   Joyin.confirm(JoyinUtil.getMessage('CHB_ISIMPORT'), function () {
				   Joyin.uploadFile(function(fileName) {
					   Joyin.ajax('checkImportData', {fileName: fileName}, function() {
						   Joyin.ajax('importFile', {fileName: fileName});
						   /*if (checkResult) {
							   setTimeout( function () {
									   if(checkResult.data.errorMsg){
										   Joyin.error(checkResult.data.errorMsg);
										   return;
									   }
									   if (checkResult.data.confirmMsg) {
										   Joyin.confirm(checkResult.data.confirmMsg, function () {
											   if (checkResult.data.confirmMsg2) {
												   Joyin.confirm(checkResult.data.confirmMsg2, function () {
													   var data = {
														   tempList: checkResult.data.correctData
													   }
													   Joyin.ajaxJson(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/importFile', data,
														   function (resultObj) {
															   if (resultObj.statuCode.toString() === '200') {
																   table.reload();
																   return;
															   }
														   });
												   },{}, function () {
													   layui.layer.closeAll()
													   table.reload();
												   });
											   }else{
												   var data = {
													   tempList: checkResult.data.correctData
												   }
												   Joyin.ajaxJson(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/importFile', data,
													   function (resultObj) {
														   if (resultObj.statuCode.toString() === '200') {
															   table.reload();
															   return;
														   }
													   });
											   }

										   },{}, function () {
											   layui.layer.closeAll()
											   table.reload();
										   });
									   }else {
										   if (checkResult.data.confirmMsg2) {
											   Joyin.confirm(checkResult.data.confirmMsg2, function () {
												   var data = {
													   tempList: checkResult.data.correctData
												   }
												   Joyin.ajaxJson(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/importFile', data,
													   function (resultObj) {
														   if (resultObj.statuCode.toString() === '200') {
															   table.reload();
															   return;
														   }
													   });
											   },{}, function () {
												   layui.layer.closeAll()
												   table.reload();
											   });
										   }else{
											   var data = {
												   tempList: checkResult.data.correctData
											   }
											   Joyin.ajaxJson(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/importFile', data,
												   function (resultObj) {
													   if (resultObj.statuCode.toString() === '200') {
														   table.reload();
														   return;
													   }
												   });
										   }
									   }
								   }
								   ,1000);
						   }*/
					   },function() {
						   $('iframe').contents().find("#loading").hide();
					   });
				   },function() {
					   table.reload();
				   });
			   });
		   });



		   // 导入 confirmMsg3 校验
		   function confirmMsg3ImportExcel(checkResult){
			   if (checkResult.data.confirmMsg3) {
				   Joyin.confirm(checkResult.data.confirmMsg3, function () {
					   confirmMsgImportExcel(checkResult);
				   }, {}, function () {
					   layui.layer.closeAll()
					   dataTable.reload();
					   dataTable2.reload();
				   });
			   }else{
				   confirmMsgImportExcel(checkResult);
			   }
		   }
			// 导入 confirmMsg 校验
		   function confirmMsgImportExcel(checkResult){
			   if (checkResult.data.confirmMsg) {
				   Joyin.confirm(checkResult.data.confirmMsg, function () {
					   commitImportExcel(checkResult);
				   },{}, function () {
					   layui.layer.closeAll()
					   table.reload();
				   });
			   }else {
				   commitImportExcel(checkResult);
			   }
		   }

		   // 提交导入方法
		   function commitImportExcel(checkResult){
			   var data = {
				   tempList: checkResult.data.correctData
			   }
			   Joyin.ajaxJson(basePath + 'disclosure/information/config/disprodplanmanage/DisProdPlanManage001/importFile', data,
				   function (resultObj) {
					   if (resultObj.statuCode.toString() === '200') {
						   /** modify by yanya on 20210329 修正导入逻辑**/
						   //Joyin.alert("文件导入成功！");
						   dataTable.reload();
						   dataTable2.reload();
						   return;
					   }
				   });
		   }
	   });
   });
