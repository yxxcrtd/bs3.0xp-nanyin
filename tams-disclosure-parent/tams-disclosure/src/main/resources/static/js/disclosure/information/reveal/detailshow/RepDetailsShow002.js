$(function () {

	var docW= $(document).width(); 
	var docH= $(document).height(); 
	var defaultDateStyle = 'yyyy-MM-dd';
	var ids = new Array();

	//设置模板的调用参数
	var modelId;
	var modelUri;

    
  //展示报表查询条件
    var showSearch = function(data){
    	//拼接查询条件
    	var SearchHtml = '<div class="layui-form-item">';
    	for (var i = 0; i < data.length; i++) {
    		
			if(data[i].screenStatus=='Y'){
				SearchHtml +='<div class="layui-inline layui-col-md4">'
					+'<label class="layui-form-label form-lable">'+data[i].screenName+'</label>'
						+'<div class="layui-input-inline input-inline">'
				//如果类型是日期的
				if(data[i].screenType=='01'){
					SearchHtml+= '<input type="text" id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" placeholder="请选择日期" class="layui-input form-input input-dateSelect"   />';
				//文本框
				}else if(data[i].screenType=='02'){
					SearchHtml+= '<input type="text" id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" placeholder="请输入名称" class="layui-input form-input"   />';
				//下拉框
				}else if(data[i].screenType=='03'){
					
					SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择"'
			            	+'allowClear="true">'
			            		+'<option value =""></option>'
			            		+getOpt(data[i].screenValue)
			            	+'</select>';
			
				}
				ids.push({key:data[i].screenKey,type:data[i].screenType});
				SearchHtml +='</div></div>';
			}
		}
    	SearchHtml += '</div>';
    	$('#showSearch').html(SearchHtml);
    	
    	
    	for(var i in ids){
    		//日期类型(单选)
    		if(ids[i].type=='01'){
        		$('#'+ids[i].key+'').textDate(false, defaultDateStyle);
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
    	option +='<option value ="1">产品001</option>'
		    	+'<option value ="2">产品002</option>'
		    	+'<option value ="3">产品003</option>'
		    	+'<option value ="4">产品004</option>'
		    	+'<option value ="5">产品005</option>'
		    	+'<option value ="6">产品006</option>'
		    	+'<option value ="7">产品007</option>'
		    	+'<option value ="8">产品008</option>'
		    	+'<option value ="9">产品009</option>'
		    	+'<option value ="10">产品010</option>'
		    	+'<option value ="11">产品011</option>'
		    	+'<option value ="12">产品012</option>';
    	
    	return option;
    }
    
    
    //展示报表信息
    var showDetail = function(params){
        //报表数据结构
//        var report_info = {
//            uri:modelId,
//            folderUri:modelUri,
//            params:params,//运行报表所使用的参数
//            container:'showtable',//生成报表后展示到界面ID 
//        }
       $("#showtable").append("加载中")
		   //运行并产生报表到页面
       //genReport(report_info); var $container = $("#showtable");
 	   	//将返回的HTML报表插入到界面中    	  
       var $container = $("#showtable");
       if(params&&params.length==0){
    	Joyin.ajax(basePath + 'disclosure/information/reveal/detailshow/RepDetailsShow001/showDetailHtmlNew', {uri:modelId}, function(result) {
//   	   		var $container = $("#showtable");
   	   	//将返回的HTML报表插入到界面中
   	   		/*
    		var report = result.data;
   	   		var report = result;
           	var startIndex = report.indexOf("/jasperserver");
           	var endIndex = report.indexOf(".png");
           	var subStr = report.substring(startIndex, endIndex);
           	var repStr = basePath+"/image/disclosure/zxlogo";
           	var newReport = report.replace(subStr,repStr);
               $container.html(newReport);
             */
    		var toStr = 'src='+basePath+'image'
    		var changeStr= 'src="image'
			var data =result.replace(new RegExp(changeStr, "g"), toStr);
			$container.html(data);	
           });
       }else{
    	   params['uri'] = modelId;
    	   Joyin.ajax(basePath + 'disclosure/information/reveal/detailshow/RepDetailsShow001/showDetailHtml', params, function(result) {
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
   }  
    
    var showView = function(data){
    	 modelId = data.templateId;
		 modelUri = data.templatePath;
		 showDetail(new Array());
		 showSearch(data.repScreenParam);
    }

    /**
     * 画面加载完成
     */
    $(function() {
    	showView(parent.window.getSelectData());
    });
});
