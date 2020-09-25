$(function () {

	var tree=$("#typeDatatree");
	var docW= $(document).width(); 
	var docH= $(document).height(); 
	var tabHeadUlH=100;
	var defaultDateStyle = 'yyyy-MM-dd';
	var ids = new Array();

	//设置模板的调用参数
	var modelId;
	var modelUri;
	var repId;
	var repName;
	var proName;
	
	var modelListId;
	var modelListName;
	
	
	
	
	var listSearch = [{screenType:'03',screenKey:'portnum',screenName:'产品名称'},
	                  {screenType:'01',screenKey:'startDate',screenName:'开始日期'},
	                  {screenType:'01',screenKey:'endDate',screenName:'结束日期'},
	                  {screenType:'06',screenKey:'prodSeriesNum',screenName:'产品系列'}]
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var obj = {};
    	obj['repName'] = $('#repName').val();
    	if(ids.length>0){
    		for(var i in ids){
	    		var getKey = ids[i].key;
	    		var getVal = $('#'+ids[i].key).val();
	    		if(getKey !='indexId'){
	    			if(getVal){
	    				obj[getKey] = getVal;
	    				if(getKey == 'portnum'){
	    					for (var int = 0; int < prodArray.length; int++) {
	    						if(prodArray[int].id == getVal){
	    							obj['portname'] = prodArray[int].text;
	    							proName = prodArray[int].text;
	    							break;
	    						}
	    					}
	    				}
	    			}else{
	    				return null;
	    			}
	    		}
        	}
    		if(obj.startDate){
    			if(obj.startDate>obj.endDate){
    				Joyin.alert('开始日不能大于结束日！',2);
    				return "flag";
    			}
    		}
    	}
    	return obj;
    };

    
  //展示报表查询条件
    var showSearch = function(data){
    	
    	//拼接查询条件
    	var SearchHtml = '<input type="hidden" name="repId" id="repId" value="'+repId+'" />'
    	                 +'<input type="hidden" name="repName" id="repName" value="'+repName+'" />'
    	                 +'<div class="layui-form-item">';
    	for (var i = 0; i < data.length; i++) {
    		if(data[i].screenKey != 'indexId') {
    			SearchHtml +='<div class="layui-inline layui-col-md4 layui-col-xs4 layui-col-sm4 layui-col-lg4">'
    				+'<label class="layui-form-label form-lable" style="width: 100px">'+data[i].screenName+'</label>'
    				+'<div class="layui-input-inline input-inline" data-th-formatoption="false" style="width: 60%!important;">';
    		}
    		//如果类型是日期的
    		if(data[i].screenType=='01'){
    			SearchHtml+= '<input type="text" id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" placeholder="请选择日期" class="layui-input form-input input-dateSelect" />';
    		//文本框
    		}else if(data[i].screenType=='02'){
    			SearchHtml+= '<input type="text" id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" placeholder="请输入名称" class="layui-input form-input"/>';
    		//下拉框
    		}else if(data[i].screenType=='03'){
    			if(data[i].screenKey != 'indexId') {
    				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getPrdInfoSelect"'
    				+'parentIds="repId" allowClear="true">'
    				+'</select>';
    			}
    		}
    		//下拉框，客户名称 zhangyf 20190426
    		else if(data[i].screenType=='04'){
    			if(data[i].screenKey != 'indexId') {
    				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getCusInfoSelect"'
    				+'parentIds="repId" allowClear="true">'
    				+'</select>';
    			}
    		}
    		//多选产品下拉框 leipeng 20200401
    		else if(data[i].screenType=='05'){
    			if(data[i].screenKey != 'indexId') {
    				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getPrdInfoSelect"'
    				+'parentIds="repId" allowClear="true" multiple="true">'
    				+'</select>';
    			}
    		}
    		else if(data[i].screenType=='06'){
    			if(data[i].screenKey != 'indexId') {
    				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getProdSeriesList"'
    				+'parentIds="repId" allowClear="true">'
    				+'</select>';
    			}
    		}
			ids.push({key:data[i].screenKey,type:data[i].screenType});
    		SearchHtml +='</div></div>';
    		
		}
    	SearchHtml += '</div>';
    	$('#showSearch').html(SearchHtml);
    	
    	
    	for(var i in ids){
    		//日期类型(单选)
    		if(ids[i].type=='01'){
        		$('#'+ids[i].key+'').textDate(false, defaultDateStyle);
                if(ids[i].key == 'startDate'){
                	$('#'+ids[i].key+'').val(cureDate);
                }else{
                	$('#'+ids[i].key+'').val(cureDate);
                }
        	//文本框
    		}else if(ids[i].type=='02'){
        		
    		//下拉框
    		}else if(ids[i].type=='03' || ids[i].type=='04' ||ids[i].type=='05' || ids[i].type=='06'){
        		$('#'+ids[i].key+'').textSelect();
    		}
    	}
    }
    
    var getOpt = function(type){
    	
    	var option = "";
    	for(var i = 0 ; i<prodArray.length;i++){
    		option +='<option value ="'+prodArray[i].id+'">'+prodArray[i].text+'</option>';
    	}
    	return option;
    }
    
  //树单击事件
    var zTreeOnClick = function(event, treeId, treeNode, clickFlag){
    	
    	clean();
    	//如果是第一级节点.不查询
        if(treeNode.level>0){
        	if(treeNode.level==1&&treeNode.object=='C'){
        		modelListId = treeNode.id;
        		listSearch = [{screenType:'03',screenKey:'portnum',screenName:'产品名称'},
	                  {screenType:'01',screenKey:'startDate',screenName:'开始日期'},
	                  {screenType:'01',screenKey:'endDate',screenName:'结束日期'}]
				showSearch(listSearch);
				$("#showtable").html('<h2>报表集合无法查询,目前仅提供word的生成及导出</h2>');
        	}else{
        		var parms = {
                		repId:treeNode.id,
                		repName:treeNode.name
                	};
                	Joyin.ajax('getRepsearch', 
                			parms,
                			function(data) {			
                				if(data){
                					 modelId = data.templateId;
                					 modelUri = data.templatePath;
                					 repId = data.repId;
                					 repName = data.reportName;
                					 showDetail(new Array());
                					 showSearch(data.repScreenList);
                				}else{
                					$("#showtable").html('<h2>无报表内容</h2>');
                				}
                				
                            });
        	}
        }
    }
    
    //初始化界面
    function clean(){
    	modelListId = null;
    	modelId =null;
		modelUri = null;
		repId = null;
		repName = null;
		proName = null;
    	ids = new Array();
		$("#showtable").html('');
		showSearch(new Array());
    }
    
    //树内容设置
    $('#typeDatatree').joyinMixTree({
    	setting:{
    	    callback: {
    	    	onClick: zTreeOnClick
    	    }
    	},
    	onTreeLoadSuccess: function() {
    		$.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(true);
    		$.fn.zTree.getZTreeObj('typeDatatree_tree').checkAllNodes(true);
    	}
    });
    
	$("#toSubmit").css('min-width','50px'); 
	$("#toExport").css('min-width','50px'); 

	$("#toSubmit").css('right','65px'); 
	$("#toExport").css('left','140px'); 


    //展示报表信息
    var showDetail = function(params){
        displayMenu();
		//var params = getSearchParams();

    	if(modelListId){
    		Joyin.alert('报表集合不能查询!',2);
			return;
    	}else if(modelId&&modelUri){
    		if(!params){
				Joyin.alert('参数不能为空！',2);
				return;
    		}
    	}else{
    		return;
    	}
    	if(params == "flag") {
    		return;
    	}
    	
    	if(params.portnum instanceof Array){
    		Joyin.alert('多选产品编码不能预览界面！',2);
			return;
    	} 
    	//导出会报错所以暂时屏蔽name
    	//delete params.portname; 
    	
        //报表数据结构
//        var report_info = {
//            uri:modelId,
//            folderUri:modelUri,
//            params:params,//运行报表所使用的参数
//            formatType:'html',//下载的格式:供exportReport使用, 支持的格式有pdf,html,xls,xlsx,rtf,csv,xml,docx,odt,ods,jrprint
//            container:'showtable',//生成报表后展示到界面ID 
//        }
//       params.uri = modelId;
//       params.folderUri = modelUri;
//       params.formatType = 'html';
       $("#showtable").append("加载中");
//       var report_info = {
//                    uri:modelId,
//                    folderUri:modelUri,
//                    downloadName:'report',//希望下载的名字：供exportReport使用
//                    formatType:type,//下载的格式:供exportReport使用, 支持的格式有pdf,html,xls,xlsx,rtf,csv,xml,docx,odt,ods,jrprint
//                    params:params//运行报表所使用的参数
//                }
//        		   //运行并产生报表到页面
//               var url = genReportHtmlUrl(report_info);
//               var url1 = exportReportPath(report_info);
 	   var $container = $("#showtable");
 	   	//将返回的HTML报表插入到界面中    	  
       /*
       if(params&&params.length==0){
    	   Joyin.ajax('showDetailHtml', {uri:modelId}, function(result) {
//   	   		var $container = $("#showtable");
   	   	//将返回的HTML报表插入到界面中
   	   		var report = result.data;
           	var startIndex = report.indexOf("/jasperserver");
           	var endIndex = report.indexOf(".png");
           	var subStr = report.substring(startIndex, endIndex);
           	var repStr = basePath+"/image/disclosure/zxlogo";
           	var newReport = report.replace(subStr,repStr);
               $container.html(newReport);
           });
       }else{
    	   params['uri'] = modelId;
    	   Joyin.ajax('showDetailHtml', params, function(result) {
//      	   		var $container = $("#showtable");
      	   	//将返回的HTML报表插入到界面中
      	   		var report = result.data;
              	var startIndex = report.indexOf("/jasperserver");
              	var endIndex = report.indexOf(".png");
              	var subStr = report.substring(startIndex, endIndex);
              	var repStr = basePath+"/image/disclosure/zxlogo";
              	var newReport = report.replace(subStr,repStr);
                  $container.html(newReport);
              });
       }     
		   //运行并产生报表到页面
      // genReport(report_info);
   }  */
 	   
 	   
	var toStr = 'src='+basePath+'image'
	var changeStr= 'src="image'
    if(params&&params.length==0){
    	Joyin.ajax('showDetailHtmlNew', {uri:modelId}, function(result) {
			var data =result.replace(new RegExp(changeStr, "g"), toStr);
			data =data.replace(new RegExp('.png', "g"), '.png&v='+Math.random());
			$container.html(data);
        });
    }else{
    	params['uri'] = modelId;
    	Joyin.ajax('showDetailHtmlNew', params, function(result) {
			var data =result.replace(new RegExp(changeStr, "g"), toStr);
			data =data.replace(new RegExp('.png', "g"), '.png&v='+Math.random());
			$container.html(data);
        });
    }     
    
		   //运行并产生报表到页面
   // genReport(report_info);
}  
    var exportfile = function(type){
        displayMenu();

		var params = getSearchParams();
		
		if((modelId&&modelUri)){
    		if(null!=params){
    			if(!params){
    				Joyin.alert('参数不能为空！',2);
        			return;
        		}
    		}else{
    			params = new Array();
    		}
    	}
		
		if(params){
			//导出会报错所以暂时屏蔽name
			delete params.portname; 
		}
    	
		if(modelId&&modelUri){
			params = new Array();
			params['data'] = JSON.stringify(getSearchParams());
			//报表数据结构
	        var report_info = {
	            uri:modelId,
	            folderUri:modelUri,
	            downloadName:'report',//希望下载的名字：供exportReport使用
	            formatType:type,//下载的格式:供exportReport使用, 支持的格式有pdf,html,xls,xlsx,rtf,csv,xml,docx,odt,ods,jrprint
	            params:params//运行报表所使用的参数
	        }
	        var obj = {};
	        params.name = modelId;
	        params.fileType = type;
	        params.code = modelId;
	        params.repName = repName;
	        params.url = exportReportPath(report_info);
//	        obj.name = modelId;
//	        obj.fileType = type;
//	        obj.code = modelId;
//	        obj.repName = repName;
//	        obj.url = exportReportPath(report_info);
//            $.each(params, function(k, v){
//            	obj[k] = v;
//            });
	        //运行并产生报表到页面
//	        if(type=='html'){
//	        	genReportHtml(report_info);
//	        }else{
//		        exportReport(report_info);
//	        }
	       	Joyin.exportExcel(basePath + 'disclosure/information/config/reportmodelmanage/ReportModelManage001/mergeExport', params);
//          Joyin.exportExcel(basePath + 'disclosure/information/config/reportmodelmanage/ReportModelManage001/mergeExport', obj);
        }else if(modelListId){
        	params = getSearchParams();
        	if("flag" == params){
        		return;
        	}
        	if(modelListId){
        		if(!params){
    				Joyin.alert('参数不能为空！',2);
        			return;
        		}
        	}else{
        		return;
        	}
        	
        	if(type=='docx'){
        		params['fileType'] = type;
                params['repId'] = modelListId;
                params['prodName'] = proName;
                params['repName'] = repName;

                Joyin.exportExcel('mergeExport', params);
        	}else{
        		Joyin.alert('目前仅支持word的集合导出！');
    			return;
        	}
        }
    	
    	
        
    }
    
    
    /*生成随机字符串  add by kongzhifang*/
    function randomString(length, chars) {
        var result = '';
        for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
        return result;
    }
    

    
    
    var submit = function(type){
        displayMenu();
		var params = getSearchParams();
		
		params['portnum'] = JSON.stringify(params['portnum']);
		
    	if((modelId&&modelUri)||modelListId){
    		if(!params){
				Joyin.alert('参数不能为空！',2);
    			return;
    		}
    	}else{
    		return;
    	}
    	var uuid = 'pageid' + randomString(10, '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
		params.rep_id=uuid;
    	//导出会报错所以暂时屏蔽name
    	delete params.portname; 
    	//报表数据结构
        if(modelId&&modelUri){
        	var report_info = {
                    uri:modelId,
                    folderUri:modelUri,
                    downloadName:'report',//希望下载的名字：供exportReport使用
                    formatType:type,//下载的格式:供exportReport使用, 支持的格式有pdf,html,xls,xlsx,rtf,csv,xml,docx,odt,ods,jrprint
                    params:params//运行报表所使用的参数
                }
        		   //运行并产生报表到页面
//                var url = exportReportPath(report_info);
                
//	        	params['url'] = url;
	            params['fileType'] = type;
	            params['modelId'] = modelId;
	            params['repId'] = repId;
	            params['repName'] = repName;
	            params['prodName'] = proName;
	            Joyin.ajax('querySameCount',params,function(){
	            	params['operateType'] = "01";
	            	Joyin.ajax('downFileAndSubmit', 
	                		params,
	            			function(data) {
	                				//layer.alert(data.message);
	                        });
	            });
        }else if(modelListId){
        	if(type=='docx'){
        		layer.load();
        		params['fileType'] = type;
                params['repId'] = modelListId;
                params['prodName'] = proName;
                params['repName'] = repName;

                Joyin.ajax('downFileListAndSubmit', 
                		params,
            			function(data) {
                				layer.closeAll('loading');
                				//layer.alert(data.message);
                        });
        	}else{
        		Joyin.alert('目前仅支持word的集合生成！');
    			return;
        	}
        }
    }
    
    function displayMenu(){
    	$("#toSubmit").css('display','none'); 
    	$("#toExport").css('display','none');
    }
 
	
    /**
     * 画面加载完成
     */
    $(function() {
    	
    	// 绑定查询按钮事件
        $('#RepDetailsShow001_showDetail').click(function() {
        	showDetail(getSearchParams());
        });

        // 绑定查询按钮事件
        $('#excelExport').click(function() {
        	
        	exportfile("xlsx");
        });
        
        $('#wordExport').click(function(){
        	exportfile("docx");
        });
        
        $('#pdfExport').click(function(){
        	exportfile("pdf");
        });
        
        $('#htmlExport').click(function(){
        	exportfile("html");
        });
        
        
        
        $('#excelSubimt').click(function(){
        	submit("xlsx");
        });
        
        $('#wordSubimt').click(function(){
        	submit("docx");
        });
        $('#pdfSubimt').click(function(){
        	submit("pdf");
        });
        
        $('#RepDetailsShow001_subimt').click(function(){
        	$("#toExport").css('display','none'); 
//        	$("#toSubmit").css('display','block'); 
        	
        	setTimeout(function(){
    			displayMenu();
    		},5000);
        });
        $('#RepDetailsShow001_export').click(function(){
        	$("#toSubmit").css('display','none'); 
//        	$("#toExport").css('display','block'); 
        	
        	setTimeout(function(){
        		displayMenu();
    		},5000);
        });

        
        
        //失去焦点事件
//        $('#toSubmit').blur(function(){
//        	$("#toSubmit").css('display','none'); 
//        	$("#toExport").css('display','none'); 
//        });
//        
//        $('#doExport').blur(function(){
//        	$("#toSubmit").css('display','none'); 
//        	$("#toExport").css('display','none'); 
//        });
    	
    	$('#showtable').width((docW-350)+"px");
    	$('#showtable').height((docH-tabHeadUlH)+"px");

    	tree.children('div').eq(1).height((docH-tabHeadUlH)+"px");  
//    	tree.children('div').eq(1).css("overflow-x", "hidden");
    	
    });
});


function serachKey(){
	$('#typeDatatree').reloadTree({
		reportCode: $('#reportCode').val()
 	});
}
