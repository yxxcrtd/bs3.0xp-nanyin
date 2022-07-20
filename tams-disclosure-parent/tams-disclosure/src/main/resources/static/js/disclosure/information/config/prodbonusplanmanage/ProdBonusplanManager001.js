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
				bonusMode: $.trim($('#bonusMode').val()),
				settDateBegin: $('#settDate').startDate(),
				settDateEnd: $('#settDate').endDate(),
				paymentDateBegin: $('#paymentDate').startDate(),
				paymentDateEnd: $('#paymentDate').endDate()
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
    			  datas.push({bonusPlanId:rep.bonusPlanId,code:rep.label,name:rep.description,temLoad:REPORT_SERVER_PATH,id:i+1,cdate:rep.creationDate,udate:rep.updateDate});
    			 }); 
			   datesList = datas;
    		   if(datas.length>0){
    			   dataTable.reload({data: datesList});
    		   }
        	   }
        	   });
	   }
	   


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
	    	 cols:[{field:'bonusPlanId',title:'主键',width:80,sortable:false,visible:false,align:'center'}
	    		/*,{field:'bonusPlanId',title:'序 号',width:80,sortable:false,align:'center'}*/
				 ,{field:'prodId',  title: '子产品主代码',width:150,sortable:false}
				 ,{field:'prodName',  title: '产品名称',width:250,sortable:false}
				 ,{field:'settDateStr',  title: '收益分配基准日',width:150,sortable:false}
				 ,{field:'paymentDateStr',  title: '现金红利发放日',width:150,sortable:false}
				 ,{field:'achievement',  title: '业绩报酬',width:100,sortable:false}
				 ,{field:'excessAchievementProp',  title: '超额业绩报酬收取比例（%）',width:200,sortable:false}
				 ,{field:'achievementBasis',  title: '业绩比较基准',width:120,sortable:false}
				 ,{field:'achievementBasisProp',  title: '业绩比较基准比例（%）',width:200,sortable:false}
				 ,{field:'bonusModeName',title: '分红方式',width:90,sortable:false}
				 ,{field:'dataStatusName',title: '数据状态',width:90,sortable:false}
				 ,{field:'updateDateStr',  title: '更新时间',width:200,sortable:false}
				 ,{field:'updateUser',  title: '更新人',width:100,sortable:false}
				 ,{field:'operation',width:20, title: 'SysCommon.operation', type:'action', buttons: ['edit','delt'],
					 render: function (data, type, row, meta) {
						 if (row.dataStatus == '02')
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
	    	data.operateType = "edit";
	    	Joyin.dialog(JoyinUtil.getLabel("编辑"),
	                JoyinUtil.getRealPath("/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002/init"),
	                "90%", "90%", {pageModel:PAGE_MODEL_EDIT,bonusPlanId:data.bonusPlanId},
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
	            	Joyin.ajaxJson('deleteModle', {bonusPlanId:data.bonusPlanId}, function(result) {
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
		   /**
			* 双击查看
			*/
		   dblclick: function (dataTable2, data) {
			   data.pageModel=PAGE_MODEL_REFERENCE;
			   Joyin.dialog(JoyinUtil.getLabel("查看"),
				   basePath + "disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002/init",
				   "40%", "70%", data,function(){dataTable2.reload();});
		   },
		   searchOnInit: false,
		   rowno : true,
		   page : true,
		   cols:[{field:'bonusPlanId',title:'主键',width:80,sortable:false,visible:false,align:'center'}
			   /*,{field:'bonusPlanId',title:'序 号',width:80,sortable:false,align:'center'}*/
			   /*,{field:'bonusPlanId',title:'序 号',width:80,sortable:false,align:'center'}*/
			   ,{field:'prodId',  title: '子产品主代码',width:150,sortable:false}
			   ,{field:'prodName',  title: '产品名称',width:250,sortable:false}
			   ,{field:'settDateStr',  title: '收益分配基准日',width:150,sortable:false}
			   ,{field:'paymentDateStr',  title: '现金红利发放日',width:150,sortable:false}
			   ,{field:'achievement',  title: '业绩报酬',width:100,sortable:false}
			   ,{field:'excessAchievementProp',  title: '超额业绩报酬收取比例（%）',width:200,sortable:false}
			   ,{field:'achievementBasis',  title: '业绩比较基准',width:120,sortable:false}
			   ,{field:'achievementBasisProp',  title: '业绩比较基准比例（%）',width:200,sortable:false}
			   ,{field:'bonusModeName',title: '分红方式',width:90,sortable:false}
			   ,{field:'dataStatusName',title: '数据状态',width:90,sortable:false}
			   ,{field:'updateDateStr',  title: '更新时间',width:200,sortable:false}
			   ,{field:'updateUser',  title: '更新人',width:100,sortable:false}
			   ,{field:'operation',width:20, title: 'SysCommon.operation', type:'action', buttons: ['edit','delt'],
				   render: function (data, type, row, meta) {
					   if (row.dataStatus == '02')
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
	    	Joyin.dialog(JoyinUtil.getLabel("编辑"),
	                JoyinUtil.getRealPath("/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002/init"),
	                "40%", "70%", {pageModel:PAGE_MODEL_EDIT,bonusPlanId:data.bonusPlanId},
	                function(data){
	                	dataTable.reload();
	                	dataTable2.reload();
	                });
	    });


	   /**
	     * 删除页面
	     */
	   dataTable2.bind('click', '.delt', function(data, index) {
		   if(data.dataStatus == '02'){
			   Joyin.alert("数据已删除",7,null,null)
			   return;
		   }
			Joyin.confirm("确认删除吗？", function(){
				var object = {selectedItem: [data], operateType: "delete"};//请求参数
				var deleteUrl = basePath + 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager001/delete';
				WorkFlow.doDeleteList(deleteUrl, object, "ProdBonusplanManager001_btn_delete", "ProdBonusplanManager002",function(){
					dataTable.reload();
					dataTable2.reload();
				});
	        });
	    });

	 //删除
	 $('#ProdBonusplanManager001_btn_delete').click(function () {
		 if (dataTable2 != null && dataTable2 != '') {
			 var selArr = dataTable2.getAllCheckedRows();
		 }
		 if (!selArr || selArr.length == 0) {
			 Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
			 return;
		 }
		 var selArrUseful =[];
		 selArr.forEach(v => {
			 if(v.dataStatus == '01'){
				 //selArr.remove();
				 selArrUseful.push(v);
			 }
		 })
		 if (!selArrUseful || selArrUseful.length == 0) {
			 Joyin.error(JoyinUtil.getMessage("请选择一条有效的数据"));
			 return;
		 }
		 var object = {selectedItem: selArrUseful, operateType: "delete"};//请求参数
		 Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
			 WorkFlow.doDeleteList(basePath + 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager001/delete', object,
				 "ProdBonusplanManager001_btn_delete", "ProdBonusplanManager002",function(result) {
				 dataTable.reload();
				 dataTable2.reload();
			 })
		 });
	 });

	   // 配置页面[新建]
	    var addNew = function(){    	
	    	Joyin.dialog(JoyinUtil.getLabel('新增产品分红到期计划'), '/disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager002/init', "40%", "70%",null,
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
		   //导出
		   $('#ProdBonusplanManager001_btn_export').click(function () {
			   exportExcel2();
		   });

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
		   $('#ProdBonusplanManager001_btn_submit').click(function(){
			   modelSelect();
		   });
		   // 页面调用初始化结束；
		   /**
		    * 查询
		    */
    	   $('#ProdBonusplanManager001_btn_search').click(function() {
    		   //search();
    		   dataTable2.load();
           }); 

    	   /**
    	    *新建
    	    */
    	   $('#ProdBonusplanManager001_btn_design').click(function() {
    		   addNew();
    	   });

		   /**
			* 导入按钮
			*/
		   $('#ProdBonusplanManager001_btn_import').click(function() {
			   Joyin.confirm("请确认是否要导入数据？", function () {
				   Joyin.uploadFile({pageId:'ProdBonusplanManager001'},function(fileName) {
					   Joyin.ajax(basePath + 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager001/checkImportData', {fileName: fileName}, function(checkResult) {
						   if (checkResult) {
							   setTimeout( function () {
									   if(checkResult.data.errorMsg){
										   Joyin.error(checkResult.data.errorMsg);
										   return;
									   }
									   if (checkResult.data.confirmMsg) {
										   Joyin.confirm(checkResult.data.confirmMsg, function () {
											   var data = {
												   tempList: checkResult.data.correctData
											   }
											   Joyin.ajaxJson(basePath + 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager001/importFile', data,
												   function (resultObj) {
													   if (resultObj.statuCode.toString() === '200') {
														   /** modify by yanya on 20210329 修正导入逻辑**/
														   //Joyin.alert("文件导入成功！");
														   dataTable.reload();
														   dataTable2.reload();
														   return;
													   }
												   });
										   },{}, function () {
											   layui.layer.closeAll()
											   dataTable.reload();
											   dataTable2.reload();
										   });
									   }else {
										   var data = {
											   tempList: checkResult.data.correctData
										   }
										   Joyin.ajaxJson(basePath + 'disclosure/information/config/prodbonusplanmanage/ProdBonusplanManager001/importFile', data,
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
								   }
								   ,1000);
						   }
					   });
				   },function() {
					   table.reload();
				   });
			   });
		   });
    	   

	   });
   });
