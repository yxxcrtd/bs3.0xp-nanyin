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
	var proName;
	
	var modelListId;
	var modelListName;
	
	var listSearch = [{screenType:'03',screenKey:'portnum',screenName:'产品名称'},
	                  {screenType:'01',screenKey:'startDate',screenName:'开始日期'},
	                  {screenType:'01',screenKey:'endDate',screenName:'结束日期'}]
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var obj = {};
    	if(ids.length>0){
    		for(var i in ids){
	    		var getKey = ids[i].key;
	    		var getVal = $('#'+ids[i].key).val();
	    		
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
    		if(obj.startDate){
    			if(obj.startDate>obj.endDate){
    				Joyin.alert('开始日不能大于结束日！');
    				return null;
    			}
    		}
    	}
    	return obj;
    };

    
  //展示报表查询条件
    var showSearch = function(data){
    	//拼接查询条件
    	var SearchHtml = '<div class="layui-form-item">';
    	for (var i = 0; i < data.length; i++) {
			SearchHtml +='<div class="layui-inline layui-col-md4">'
							+'<label class="layui-form-label form-lable">'+data[i].screenName+'</label>'
								+'<div class="layui-input-inline input-inline">'
    		//如果类型是日期的
    		if(data[i].screenType=='01'){
    			SearchHtml+= '<input type="text" id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" placeholder="请选择日期" class="layui-input form-input input-dateSelect" />';
    		//文本框
    		}else if(data[i].screenType=='02'){
    			SearchHtml+= '<input type="text" id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" placeholder="请输入名称" class="layui-input form-input"   />';
    		//下拉框
    		}else if(data[i].screenType=='03'){
    			
    			SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择"'
//                    +'data-th-data-url="@{/system/user/SysUser001/getUserName}"'
                    	+'allowClear="true">'
                    		+'<option value =""></option>'
	                    	+getOpt(data[i].screenValue)
                    	+'</select>';
                
    			  		
    			
    		}
			ids.push({key:data[i].screenKey,type:data[i].screenType});
    		SearchHtml +='</div></div>';
    		
		}
    	SearchHtml += '</div>';
    	$('#showSearch').html(SearchHtml);
    	
    	
    	for(var i in ids){
    		//日期类型(单选)
    		if(ids[i].type=='01'){
    			//获取当前系统时间
    			var now = new Date();
    			var yy = now.getFullYear();      //年
    			var mm = now.getMonth() + 1;     //月
    			var dd = now.getDate();          //日
    			var dataString  = yy+'-'+mm+'-'+dd;
    			
        		$('#'+ids[i].key+'').textDate(false, defaultDateStyle);
                if(ids[i].key == 'startDate'){
                	$('#'+ids[i].key+'').val(dataString);
                }else{
                	$('#'+ids[i].key+'').val(dataString);
                }
        	//文本框
    		}else if(ids[i].type=='02'){
        		
    		//下拉框
    		}else if(ids[i].type=='03'){
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
    	
    	//清除筛选框
    	clean();
    	
    	// 根据所选节点，获取对应产品列表。当前根据节点id进行判断，取决于表[REP_TREE]中定的id
    	// 货币型产品节点
    	if(treeNode.id == "001_001"){
    		prodArray = prodArray_hbx;
    	}
    	// 净值型产品节点
    	else if(treeNode.id == "001_002"){
    		prodArray = prodArray_jzx;
    	}
    	// 其他默认为空
    	else{
    		prodArray = new Object();
    	}
    	
    	//如果是第一级节点.不查询
        if(treeNode.level>0){
        	if(treeNode.level==1&&treeNode.object=='C'){
        		modelListId = treeNode.id;
				showSearch(listSearch);
				$("#showtable").html('<h2>报表集合无法查询,目前仅提供word的生成及导出</h2>');
        	}else{
        		var parms = {
                		repId:treeNode.id
                	};
                	Joyin.ajax('getRepsearch', 
                			parms,
                			function(data) {
                				
                				
                				if(data){
                					 modelId = data.templateId;
                					 modelUri = data.templatePath;
                					 repId = data.repId;
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
		var params = getSearchParams();

    	if(modelListId){
    		Joyin.alert('报表集合不能查询!');
			return;
    	}else if(modelId&&modelUri){
    		if(!params){
				Joyin.alert('参数不能为空！');
				return;
    		}
    	}else{
    		return;
    	}
    	
    	//导出会报错所以暂时屏蔽name
    	//delete params.portname; 
    	
        //报表数据结构
/*        var report_info = {
            uri:modelId,
            folderUri:modelUri,
            params:params,//运行报表所使用的参数
            container:'showtable',//生成报表后展示到界面ID 
        }
*/
       $("#showtable").append("加载中")
 	   var $container = $("#showtable");
 	   	//将返回的HTML报表插入到界面中    	  
       if(params&&params.length==0){
    	   Joyin.ajax('showDetailHtml', {uri:modelId}, function(result) {
    		//将返回的HTML报表插入到界面中
   	   		var report = result.data;
//           	var startIndex = report.indexOf("/jasperserver");
//           	var endIndex = report.indexOf(".png");
//           	var subStr = report.substring(startIndex, endIndex);
//           	var repStr = basePath+"/image/report/zxlogo";
//           	var newReport = report.replace(subStr,repStr);
               $container.html(report);
           });
       }else{
    	   params['uri'] = modelId;
    	   params.fileType='html';
    	   Joyin.ajax('showDetailHtml', params, function(result) {
      	   		//将返回的HTML报表插入到界面中
      	   		var report = result.data;
//              	var startIndex = report.indexOf("/jasperserver");
//              	var endIndex = report.indexOf(".png");
//              	var subStr = report.substring(startIndex, endIndex);
//              	var repStr = basePath+"/image/report/zxlogo";
//              	var newReport = report.replace(subStr,repStr);
                  $container.html(report);
              });
       } 
		   //运行并产生报表到页面
//       genReport(report_info);
   }  
    
    var exportfile = function(type){
        displayMenu();

		var params = getSearchParams();

		if((modelId&&modelUri)){
    		if(null!=params){
    			if(!params){
    				Joyin.alert('参数不能为空！');
        			return;
        		}
    		}else{
    			params = new Array();
    		}
    	}

		
		if(modelId&&modelUri){
			//报表数据结构
	        var report_info = {
	            uri:modelId,
	            folderUri:modelUri,
	            downloadName:'report',//希望下载的名字：供exportReport使用
	            formatType:type,//下载的格式:供exportReport使用, 支持的格式有pdf,html,xls,xlsx,rtf,csv,xml,docx,odt,ods,jrprint
	            params:params//运行报表所使用的参数
	        }
/*	        var obj = {};
       		obj.name = modelId;
        	obj.fileType = type;
        	obj.code = 'report';
        	obj.url = exportReportPath(report_info);
*/
        	
	        params.name = modelId;
	        params.fileType = type;
	        params.code = 'report';
	        //运行并产生报表到页面
//	        if(type=='html'){
//	        	genReportHtml(report_info);
//	        }else{
//		        exportReport(report_info);
//	        }
        	Joyin.exportExcel(basePath + 'disclosure/information/reveal/detailshow/ValRepDetailsShow001/mergeExport', params);  
        }else if(modelListId){
        	
        	if(modelListId){
        		if(!params){
    				Joyin.alert('参数不能为空！');
        			return;
        		}
        	}else{
        		return;
        	}
        	
        	if(type=='docx'){
        		params['fileType'] = type;
                params['repId'] = modelListId;
                params['prodName'] = proName;

                Joyin.exportExcel('mergeExport', params);
        	}else{
        		Joyin.alert('目前仅支持word的集合导出！');
    			return;
        	}
        }
    	
    	
        
    }
   
  //获取需要导出文件的路径
    function exportReportPath(report_info){
        var username = report_info.username || REPORT_USERNAME;
        var pwd = report_info.password || REPORT_PASSWORD;

        var url = report_info.url || REPORT_SERVER_URL;

        var paramStr = '?j_username=' + username + '&' + 'j_password=' + pwd;
        $.each(report_info.params, function(k, v){
            paramStr = paramStr + '&' + k + '=' + v;
        });
        
        var path = url + '/rest_v2/reports' + REPORT_SERVER_PATH + '/' + report_info.uri + '.' + report_info.formatType 
        					+ paramStr +"/"+report_info.downloadName + "." + report_info.formatType;

        return path
    }
    
    var submit = function(type){
        displayMenu();
		var params = getSearchParams();

    	if((modelId&&modelUri)||modelListId){
    		if(!params){
				Joyin.alert('参数不能为空！');
    			return;
    		}
    	}else{
    		return;
    	}
    	
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
                var url = exportReportPath(report_info);
                
                if(url){
                	params['url'] = url;
                    params['fileType'] = type;
                    params['modelId'] = modelId;
                    params['repId'] = repId;
                    params['prodName'] = proName;
                    Joyin.ajax('querySameCount',params,function(){
                    	params['operateType'] = "01";
                    	Joyin.ajax('downFileAndSubmit', 
                        		params,
                    			function(data) {
                        				//layer.alert(data.message);
                                });
                    });
                }
        }else if(modelListId){
        	if(type=='docx'){
        		layer.load();
        		params['fileType'] = type;
                params['repId'] = modelListId;
                params['prodName'] = proName;

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
        $('#showDetailBtn').click(function() {
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
        
        $('#doSubimt').click(function(){
        	$("#toExport").css('display','none'); 
//        	$("#toSubmit").css('display','block'); 
        	
        	setTimeout(function(){
    			displayMenu();
    		},5000);
        });
        $('#doExport').click(function(){
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
    	tree.children('div').eq(1).css("overflow-x", "hidden");
    	
    });
});


function serachKey(){
	$('#typeDatatree').reloadTree({
		reportCode: $('#reportCode').val()
 	});
}
