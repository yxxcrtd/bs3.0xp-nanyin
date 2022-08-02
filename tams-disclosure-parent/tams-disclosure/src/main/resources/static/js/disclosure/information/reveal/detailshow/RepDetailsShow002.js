$(function () {

	var docW= $(document).width(); 
	var docH= $(document).height(); 
	var defaultDateStyle = 'yyyy-MM-dd';
	var ids = new Array();

	//设置模板的调用参数
	var modelId;
	var modelUri;

	var repId;
	var repName;
    
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
		   Joyin.ajax('previewReport', {uri:modelId,repid:repId}, function(result) {
			   //var data =result.replace(new RegExp(changeStr, "g"), toStr);
			   //data =data.replace(new RegExp('.png', "g"), '.png&v='+Math.random());
			   $container.html(result);
		   });
       }else{
		   params['uri'] = modelId;
		   params['repid']=repId;
		   console.log(params)
		   alert("b");
		   Joyin.ajax('previewReport', params, function(result) {
			   //var data =result.replace(new RegExp(changeStr, "g"), toStr);
			   //data =data.replace(new RegExp('.png', "g"), '.png&v='+Math.random());
			   $container.html(result);
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
    	//showView(parent.window.getSelectData());
		var parms = {
			repId:repId,
			repName:repName
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
    });
});
