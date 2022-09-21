var editFlag=true;
var changeFlag=false;
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

	var previewFlag=false;



	var backParam;
	
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

	    			}
	    		}
				if(getKey==='prodid'){
					obj['prodname']=$('#'+ids[i].key).find("option:selected").text();
				}
				if(getKey==='pprodid'){
					obj['pprodname']=$('#'+ids[i].key).find("option:selected").text();
				}
        	}
    		if(obj.startDate&&obj.endDate){
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
        if(!repId) return;
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
			    var datas = '{}';
			    Joyin.ajaxSync('getPProdIdList1', {repId: repId}, function(res) {
			        datas = JSON.stringify(res);
			    });

				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select select2-hidden-accessible" placeholder="请选择" data-result=\'' + datas
					+'\' parentIds="repId,prodid" childrenIds="elementname" allowClear="true"';
				if(data[i].multiFlag==='Y'){
                        SearchHtml+= ' multiple showAll="true" >' +'</select>';
				}else{
					SearchHtml+= 'onchange=pprodIdChange()>' +'</select>';
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
					SearchHtml+= 'onchange=feeNameChange()>' +'</select>';
				}
			}
			//要素名称下拉框
			else if(data[i].screenType==='04'){
			    var datas = '{}';
			    Joyin.ajaxSync('getElementNameList1', {repId: repId}, function(res) {
			        datas = JSON.stringify(res);
			    });
				SearchHtml+= '<select id="'+data[i].screenKey+'" name="'+data[i].screenKey+'" class="layui-input form-input input-select" placeholder="请选择" data-result=\'' + datas
					+'\' parentIds="repId" allowClear="true" formatoption="false"';
				if(data[i].multiFlag==='Y'){
					SearchHtml+= ' multiple showAll="true">' +'</select>';
				}else{
					SearchHtml+= 'onchange=elementNameChange("'+modelId+'")>' +'</select>';
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
				var $elem = $('#'+ids[i].key+'');
				$elem.textSelect();
				if($elem.attr('showAll') === 'true') {
                    // 自动全选
                    $elem.data('select2').open();
                    $elem.data('select2').$results.find('li.select2-results__option').each(function() {
                        var d = $(this).data('data');
                        if(d.id === '-1_all') {
                            $(this).trigger('mouseup');
                            return;
                        }
                    });
                    $elem.data('select2').close();
				}
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
						repId:treeNode.id,
                		repName:treeNode.name
                	};
				$("#showtable").append("加载中");
				var $container = $("#showtable");
                Joyin.ajax('getRepsearch',
                    parms,
                    function(data) {
						changeFlag=true;
                        if(data){
                             modelId = data.templateId;
                             modelUri = data.templatePath;
                             repId = data.repId;
                             repName = data.reportName;

							Joyin.ajax('previewReport', {uri:modelId,repid:repId}, function(result) {
								$container.html(result);

							});
                             showSearch(data.repScreenList);
							backParam={}
							previewFlag=false;
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
			if(modelId==="CPKFQGG"||modelId==='CPKFQBG'){
				if (params['prodid']===null||params['prodid']===undefined) {
					Joyin.alert('子产品代码必填!',2);
					return;
				}
				if (params['year']===null||params['year']===undefined) {
					Joyin.alert('生成年度必填!',2);
					return;
				}
			}
			if(modelId==="RKCPJZ_GMXGL"||modelId==='RKCPJZ_GMJZXCP'||modelId==="MZJZGG"||modelId==='MZJJBG'){
				if (params['prodid']===null||params['prodid']===undefined) {
					Joyin.alert('子产品代码必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('净值日期必填!',2);
					return;
				}
			}
			if(modelId==="MZJZGG" || modelId==='MZJJBG') {
				var checkflg = false;
				var msg;
				Joyin.ajaxSync('dateCheck', params, function (result) {
					if(result){
						msg = result;
						checkflg = true;
					}
				});
				if (checkflg) {
					Joyin.alert(msg,2);
					return;
				}
			}
			if(modelId==="ZQXCPKFGG_GMCP"||modelId==='ZQXCPKFGG_GMCPDSH'){
				if (params['prodid']===null||params['prodid']===undefined) {
					Joyin.alert('子产品代码必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('本期运作起始日必填！!',2);
					return;
				}
			}
			if(modelId==="KFJZJFHGG_BNYS"||modelId==='KFJZJFHGG_BNYN'||modelId==="JZJFHBG_SMFB"||modelId==='JZJFHBG_SMKF'){
				if (params['prodid']===null||params['prodid']===undefined) {
					Joyin.alert('子产品代码必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('收益分配基准日必填!',2);
					return;
				}
			}
			if(modelId==="KFJJZGG_GM"||modelId==='KFJJZGG_GM'){
				if (params['prodid']===null||params['prodid']===undefined) {
					Joyin.alert('子产品代码必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('本期开放结束日必填!',2);
					return;
				}
			}
			if(modelId==="KFJJZGG_SM"||modelId==='KFJJZGG_SM'){
				if (params['prodid']===null||params['prodid']===undefined) {
					Joyin.alert('子产品代码必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('本期开放结束日必填!',2);
					return;
				}
			}
			if(modelId==="JDBG_ZSX"||modelId==='JDBG_FZSX'){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品代码必填!',2);
					return;
				}
				if (params['year']===null||params['year']===undefined) {
					Joyin.alert('报告年度必填!',2);
					return;
				}
				if (params['quarter']===null||params['quarter']===undefined) {
					Joyin.alert('报告季度必填!',2);
					return;
				}
			}
			if(modelId==="BNDBG_ZSX"||modelId==='BNDBG_FZSX'){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
				if (params['year']===null||params['year']===undefined) {
					Joyin.alert('报告年度必填!',2);
					return;
				}
				if (params['halfyear']===null||params['halfyear']===undefined) {
					Joyin.alert('报告（半）年度必填!',2);
					return;
				}
			}
			if(modelId==="NDBG_ZSX"||modelId==='NDBG_FZSX'){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
				if (params['year']===null||params['year']===undefined) {
					Joyin.alert('报告年度必填!',2);
					return;
				}
			}
			if(modelId==="TZTZFBZCGG"){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('公告日期必填!',2);
					return;
				}
			}
			if(modelId==="DQGG_GM"||modelId==="DQGBG_SM"){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
				if (params['prodid']===null||params['prodid']===undefined) {
					Joyin.alert('子产品代码必填!',2);
					return;
				}
			}
			if(modelId==="ZJCPXSJGGG"){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('公告日期必填!',2);
					return;
				}
			}
			if(modelId==="CPBCLGG_GM"||modelId==="CPBCLBG_SM"){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('产品发布日期必填!',2);
					return;
				}
			}
			if(modelId==="CPBCLGG_GM"||modelId==="CPBCLBG_SM"){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('产品发布日期必填!',2);
					return;
				}
			}
			if(modelId==="DCPDFLMB1_GM"||modelId==="DCPDFLMB1_SM"||modelId==="DCPDFLMB2_GM"||modelId==="DCPDFLMB2_SM"){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('发布日期必填!',2);
					return;
				}
				// if (params['feename']===null||params['feename']===undefined) {
				// 	Joyin.alert('费率名称必填!',2);
				// 	return;
				// }
			}
			if(modelId==="DYSDCPTZMB_GM"||modelId==="DYSDCPTZMB"){
				if (params['elementname']===null||params['elementname']===undefined) {
					Joyin.alert('要素名称必填!',2);
					return;
				}
				// if (params['pprodid']===null||params['pprodid']===undefined) {
				// 	Joyin.alert('产品名称必填!',2);
				// 	return;
				// }
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('发布日期必填!',2);
					return;
				}
			}
			if(modelId==="DFLDCPMB1_GM"||modelId==="DFLDCPMB1_SM"||modelId==="DFLDCPMB2_GM"||modelId==="DFLDCPMB2_SM"){
				if (params['feename']===null||params['feename']===undefined) {
					Joyin.alert('费率名称必填!',2);
					return;
				}
				// if (params['pprodid']===null||params['pprodid']===undefined) {
				// 	Joyin.alert('产品名称必填!',2);
				// 	return;
				// }
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('发布日期必填!',2);
					return;
				}
			}
			if(modelId==="DCPDYSTZMB_GM"||modelId==="DCPDYSTZMB_SM"){
				// if (params['elementname']===null||params['elementname']===undefined) {
				// 	Joyin.alert('要素名称必填!',2);
				// 	return;
				// }
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('发布日期必填!',2);
					return;
				}
			}
			if(modelId==="JESHGG_GM"||modelId==="JESHGBG_SM"){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('发布日期必填!',2);
					return;
				}
			}
			if(modelId==="DLXSJGMDGG"){
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('发布日期必填!',2);
					return;
				}
			}
			if(modelId==="QSBG"){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
			}
			if(modelId==="FHJSQD"){
				if (params['prodid']===null||params['prodid']===undefined) {
					Joyin.alert('子产品代码必填!',2);
					return;
				}
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('收益分配基准日!',2);
					return;
				}
			}
			if(modelId==="FXGG"){
				if (params['pprodid']===null||params['pprodid']===undefined) {
					Joyin.alert('产品名称必填!',2);
					return;
				}
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

			Joyin.ajaxJson(basePath + 'disclosure/information/reveal/detailshow/RepDetailsShow001/dataCheck', params, function(result1) {
				if(result1){
					if(result1.data){
						editFlag=false
					}else{
						editFlag=true
					}
				}

				Joyin.ajax('previewReport', params, function(result) {
					$container.html(result);
					previewFlag=true;
				});
			});
	    }     
	}
	  
    var exportfile = function(type){
		if(!previewFlag){
			Joyin.alert('导出之前请先预览报表！',2);
			return;
		}

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

		if(backParam !=null){
			for ( var key in params) {
				if(params[key]!==backParam[key]){
					Joyin.alert('页面参数发生变化，请重新点击预览',2);
					return;
				}
			}
		}
    	
		if(modelId&&modelUri){
//			params = new Array();
			//params['data'] = JSON.stringify(getSearchParams());
			//报表数据结构
			//alert("单表导出");
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
	            params['uri'] = modelId;
	            //params['portnum'] = 'LEO-001';
	            //alert(params);
//	        Joyin.ajax('downFileAndExport',
//	                		params,
//	            			function(data) {
//	                				//layer.alert(data.message);
//	                        });

//	       	Joyin.exportExcel(basePath + '/disclosure/information/config/reportmodelmanage/ReportModelManage001/mergeExport', params);
	       	Joyin.exportExcel(basePath + '/disclosure/information/reveal/detailshow/RepDetailsShow001/mergeExport', params);
//          Joyin.exportExcel(basePath + 'disclosure/information/config/reportmodelmanage/ReportModelManage001/mergeExport', obj);
        }else if(modelListId){
            //alert("多表导出")
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
		if(!previewFlag){
			Joyin.alert('编辑之前请先预览报表！',2);
			return;
		}

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

		//判断这一次的参数和上一次是否一样，不一样要给出提示
		if(backParam !=null){
			for ( var key in params) {
				if(params[key]!==backParam[key]){
					Joyin.alert('页面参数发生变化，请重新点击预览',2);
					return;
				}
			}
		}
    
    	params['repid'] = repId;
    	params['uri'] = modelId;
		params['flag']="01";
		//编辑之前需要判断改报表是否已确认或者在流程中
		var webRoot=document.location.href;
		webRoot=webRoot.substring(0,webRoot.indexOf('disclosure'));	
		params['webRoot']=webRoot;

		if(document.cookie.includes('webofficetab')) {
			Joyin.alert('已打开模板编辑画面，请先关闭！',2);
		    return;
		}

		Joyin.ajaxJson(basePath + 'disclosure/information/reveal/detailshow/RepDetailsShow001/check',params, function(result) {
            var param = [], paramObj = $.extend({dialog:1},params);
            if (paramObj) {
                $.each(params, function (key, val) {
                    param.push(key + '=' + encodeURIComponent(params[key]));
                });
            }
			changeFlag=false;
            var paraStr = '?' + param.join('&');
		    window.open(basePath+"disclosure/information/reveal/detailshow/RepEdit001/init" + paraStr, "_blank");

//			Joyin.dialog(JoyinUtil.getLabel("编辑报表"),
//				JoyinUtil.getRealPath("/disclosure/information/reveal/detailshow/RepEdit001/init"),
//				"70%","85%",params,function(result){
//					if(top.getData){
//						editFlag=true;
//					}
//				}
//			);
		});


    }

    var submit = function(type){
		if(!previewFlag){
			Joyin.alert('生成之前请先预览报表！',2);
			return;
		}

		if(!editFlag){
			Joyin.alert('该单据报表校验不通过，请检查数据或编辑后生成',2);
			return;
		}

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
		if(backParam !=null){
			for ( var key in params) {
				if(params[key]!==backParam[key]){
					Joyin.alert('页面参数发生变化，请重新点击预览',2);
					return;
				}
			}
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
				params['uri'] = modelId;
				params['flag']="02";
				Joyin.ajaxJson(basePath + 'disclosure/information/reveal/detailshow/RepDetailsShow001/check',params, function(result) {
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
			backParam=getSearchParams();
			previewFlag=false;
			changeFlag=true;
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
        
        $('#htmlExport').click(function(){mei
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

function pprodIdChange(modelId){
	$('#prodid').val("").trigger("change");
	$('#feename').val("").trigger("change");
	$('#elementname').val("").trigger("change");
}

function feeNameChange(){
	$('#pprodid').val("").trigger("change");
}

function elementNameChange(modelId){

	// if(modelId==="DYSDCPTZMB_GM"||modelId==="DYSDCPTZMB"){
	//
	// 	$('#pprodid').val("NYLCSMCP").trigger("change").trigger('blur');
	// 	//$('#pprodid').select2('destroy').textSelect('setValue', 'NYLCSMCP');
	// }
}


function changeEditFlag(){
	if(!changeFlag){
		editFlag=true;
	}
}


