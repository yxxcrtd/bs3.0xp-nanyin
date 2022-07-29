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
	
	var currReportFile;
	
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
						if(ids[i].multiFlag==='Y'){
							obj[getKey]=getVal.join(",");
						}else{
							obj[getKey] = getVal;
						}

	    			}else{
	    				return null;
	    			}
	    		}
        	}
    		if(obj.startDate&&obj.endDate){
    			if(obj.startDate>obj.endDate){
    				Joyin.alert('开始日不能大于结束日！',2);
    				return "flag";
    			}
    		}
    	}
		console.log(obj);
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
			//产品名称下拉框
			if(data[i].screenType==='01'){
				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getPProdIdList"'
					+'parentIds="repId,prodid" allowClear="true"';
				if(data[i].multiFlag==='Y'){
					SearchHtml+= 'multiple="true">' +'</select>';
				}else{
					SearchHtml+= '>' +'</select>';
				}
			}
			//子产品代码下拉框
			else if(data[i].screenType==='02'){
				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getProdIdList"'
					+'parentIds="repId,pprodid" allowClear="true"';
				if(data[i].multiFlag==='Y'){
					SearchHtml+= 'multiple="true">' +'</select>';
				}else{
					SearchHtml+= '>' +'</select>';
				}
			}
			//费率名称下拉框
			else if(data[i].screenType==='03'){
				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getFeeNameList"'
					+'parentIds="repId,pprodid" allowClear="true" formatoption="false"';
				if(data[i].multiFlag==='Y'){
					SearchHtml+= 'multiple="true">' +'</select>';
				}else{
					SearchHtml+= '>' +'</select>';
				}
			}
			//要素名称下拉框
			else if(data[i].screenType==='04'){
				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getElementNameList"'
					+'parentIds="repId,pprodid" allowClear="true" formatoption="false"';
				if(data[i].multiFlag==='Y'){
					SearchHtml+= 'multiple="true">' +'</select>';
				}else{
					SearchHtml+= '>' +'</select>';
				}
			}
			//季度选择框
			else if(data[i].screenType==='05'){
				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getQuarter"'
					+'parentIds="repId" allowClear="true">' +'</select>';
			}
			//半年度选择框
			else if(data[i].screenType==='06'){
				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择" data-url="getHalfYear"'
					+'parentIds="repId" allowClear="true">' +'</select>';
			}
			//年度选择框
			else if(data[i].screenType==='07'){
				SearchHtml+= '<input type="text" id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" placeholder="请选择日期" class="layui-input form-input input-dateSelect" />';
			}
			//开始日期
			else if(data[i].screenType==='08'){
				SearchHtml+= '<input type="text" id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" placeholder="请选择日期" class="layui-input form-input input-dateSelect" />';
			}
			//结束日期
			else if(data[i].screenType==='09'){
				SearchHtml+= '<input type="text" id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" placeholder="请选择日期" class="layui-input form-input input-dateSelect" />';
			}
			ids.push({key:data[i].screenKey,type:data[i].screenType,multiFlag:data[i].multiFlag});
			SearchHtml +='</div></div>';

		}
		SearchHtml += '</div>';
		$('#showSearch').html(SearchHtml);

		var laydate = layui.laydate;
		laydate.render({
			elem: '#year',
			type: 'year',
			format: 'yyyy'
		});

		for(var i in ids){
			//日期类型(单选)
			if(ids[i].type==='08'||ids[i].type==='09'){
				$('#'+ids[i].key+'').textDate(false, defaultDateStyle);
				if(ids[i].key === 'startDate'){
					$('#'+ids[i].key+'').val(cureDate);
				}else{
					$('#'+ids[i].key+'').val(cureDate);
				}
				//文本框
			} else {
				$('#'+ids[i].key+'').textSelect();
			}
		}
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
						repid:treeNode.id,
                		repname:treeNode.name
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
    	
       $("#showtable").append("加载中");
 	   var $container = $("#showtable");
 	    	   
		var toStr = 'src='+basePath+'image'
		var changeStr= 'src="image'
	    if(params&&params.length==0){
	    	Joyin.ajax('previewReport', {uri:modelId,repid:repId}, function(result) {
				//var data =result.replace(new RegExp(changeStr, "g"), toStr);
				//data =data.replace(new RegExp('.png', "g"), '.png&v='+Math.random());
				$container.html(result);
	        });
	    }else{
	    	params['uri'] = modelId;
	    	params['repid']=repId;
			console.log(params)
	    	Joyin.ajax('previewReport', params, function(result) {
				//var data =result.replace(new RegExp(changeStr, "g"), toStr);
				//data =data.replace(new RegExp('.png', "g"), '.png&v='+Math.random());
				$container.html(result);
	        });
	    }     
	}
	  
    var exportfile = function(type){
        displayMenu();

		var params = getSearchParams();
		
		if((modelId&&modelUri)||modelListId){
            if(!params){
                Joyin.alert('参数不能为空！',2);
                return;
            }
        }else{
    	    return;
    	}
		
		if(params){
			//导出会报错所以暂时屏蔽name
			delete params.portname; 
		}
    	
		if(modelId&&modelUri){
//			params = new Array();
			//params['data'] = JSON.stringify(getSearchParams());
			//报表数据结构
			alert("单表导出");
	        var report_info = {
	            uri:modelId,
	            folderUri:modelUri,
	            downloadName:'report',//希望下载的名字：供exportReport使用
	            formatType:type,//下载的格式:供exportReport使用, 支持的格式有pdf,html,xls,xlsx,rtf,csv,xml,docx,odt,ods,jrprint
	            params:params//运行报表所使用的参数
	        }

	        var obj = {};
	            params['fileType'] = type;
	            params['modelId'] = modelId;
	            params['repid'] = repId;
	            params['repname'] = repName;
	            params['prodname'] = proName;
	            //params['portnum'] = 'LEO-001';
	            alert(params);
//	        Joyin.ajax('downFileAndExport',
//	                		params,
//	            			function(data) {
//	                				//layer.alert(data.message);
//	                        });

//	       	Joyin.exportExcel(basePath + '/disclosure/information/config/reportmodelmanage/ReportModelManage001/mergeExport', params);
	       	Joyin.exportExcel(basePath + '/disclosure/information/reveal/detailshow/RepDetailsShow001/mergeExport', params);
//          Joyin.exportExcel(basePath + 'disclosure/information/config/reportmodelmanage/ReportModelManage001/mergeExport', obj);
        }else if(modelListId){
            alert("多表导出")
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
        		params['filetype'] = type;
                params['repid'] = modelListId;
                params['prodname'] = proName;
                params['repname'] = repName;

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
    
    //编辑报表
    var editReport = function(params){
        displayMenu();
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
 	    	   
		var toStr = 'src='+basePath+'image'
		var changeStr= 'src="image'
    
    	params['repid'] = repId;
        Joyin.dialog(JoyinUtil.getLabel("编辑报表"),
            JoyinUtil.getRealPath("/disclosure/information/reveal/detailshow/RepEdit001/init"),
            "60%","85%",params,function(){ }
        );
    }

    var submit = function(type){
        displayMenu();
		var params = getSearchParams();
		
		//params['portnum'] = JSON.stringify(params['portnum']);

		if(modelId&&modelUri){
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
	            params['filetype'] = type;
	            params['modelid'] = modelId;
	            params['repid'] = repId;
	            params['repname'] = repName;
	            params['prodname'] = proName;
	            Joyin.ajax('querySameCount',params,function(){
	            	params['operateType'] = "01";
	            	Joyin.ajax('genReport',
	                		params,
	            			function(data) {
	                				//layer.alert(data.message);
	                        });
	            });
        }else if(modelListId){
        	if(type=='docx'){
        		layer.load();
        		params['filetype'] = type;
                params['repid'] = modelListId;
                params['prodname'] = proName;
                params['repname'] = repName;

                Joyin.ajax('submitWord',
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

        $('#btnEditReport').click(function() {
            editReport(getSearchParams());
        });

        // 绑定查询按钮事件
        $('#excelExport').click(function() {
        	
        	exportfile("xlsx");
        });
        
        $('#btnExportWord').click(function(){
        	exportfile("docx");
        });
        
        $('#btnExportPdf').click(function(){
        	exportfile("pdf");
        });
        
        $('#htmlExport').click(function(){
        	exportfile("html");
        });
        
        
        
        $('#excelSubimt').click(function(){
        	submit("xlsx");
        });
        
        $('#btnGenReport').click(function(){
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
