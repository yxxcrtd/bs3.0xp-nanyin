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
     *初始化界面
     */
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
	
	/**
	 *树单击事件
	 */
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

				var $container = $("#showtable");
                Joyin.ajax('getRepsearch',
                    parms,
                    function(data) {
                        if(data){
                             modelId = data.templateId;
                             modelUri = data.templatePath;
                             repId = data.repId;
                             repName = data.reportName;
                             showSearch(data.repScreenList);
                             parms['uri'] = modelId;
               				 $('#tree').reloadTree(parms);
							backParam={}
                        }else{
                            $("#showtable").html('<h2>无报表内容</h2>');
                        }

                    });
                    
		       	//通过repId获取是子产品还是母产品

//				Joyin.ajax('queryProdList',
//					parms,
//					function(data) {
//	                if(data){
//	                	$('#tree').reloadTree(data)      
//                    }else{
//                        $("#showtable").html('<h2>产品树无内容</h2>');
//                    }
//
//                });
        	}
 
        }
        
    }

    /**
     * 报表树内容设置
     */
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
    
    /**
     *展示报表查询条件
     */
	var showSearch = function(data){
        if(!repId) return;
		//拼接查询条件
		var SearchHtml = '<input type="hidden" name="repId" id="repId" value="'+repId+'" />'
			+'<input type="hidden" name="repName" id="repName" value="'+repName+'" />'
			+'<div class="layui-form-item">';
		for (var i = 0; i < data.length; i++) {
			if(data[i].screenKey != 'indexId') {
				SearchHtml +='<div class="layui-inline layui-col-md4 layui-col-xs4 layui-col-sm4 layui-col-lg4">'
					+'<label class="layui-form-label form-lable">'+data[i].screenName+'</label>'
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
				if(getKey==='feename'){
					obj['prodFeeName']=$('#'+ids[i].key).find("option:selected").text();
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
    
    //展示报表信息---预览
    var checkParams = function(params){

        displayMenu();
        if ( !repId ) {
        	Joyin.alert('请选择报表!',2);
        	return;
        }
        
    	if(modelListId){
    		Joyin.alert('报表集合不能查询!',2);
			return;
    	}else if(modelId&&modelUri){
			if(modelId==="CPKFQGG"||modelId==='CPKFQBG'){
				if (params['year']===null||params['year']===undefined) {
					Joyin.alert('生成年度必填!',2);
					return;
				}
			}
			if(modelId==="RKCPJZ_GMXGL"||modelId==='RKCPJZ_GMJZXCP'||modelId==="MZJZGG"||modelId==='MZJJBG'){
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('净值日期必填!',2);
					return;
				}
			}
			if(modelId==="MZJZGG" || modelId==='MZJJBG') {
//				var checkflg = false;
//				var msg;
//				Joyin.ajaxSync('dateCheck', params, function (result) {
//					if(result){
//						msg = result;
//						checkflg = true;
//					}
//				});
//				if (checkflg) {
//					Joyin.alert(msg,2);
//					return;
//				}
				return;
			}
			if(modelId==="ZQXCPKFGG_GMCP"||modelId==='ZQXCPKFGG_GMCPDSH'){
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('本期运作起始日必填！!',2);
					return;
				}
			}
			if(modelId==="KFJZJFHGG_BNYS"||modelId==='KFJZJFHGG_BNYN'||modelId==="JZJFHBG_SMFB"||modelId==='JZJFHBG_SMKF'){
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('收益分配基准日必填!',2);
					return;
				}
			}
			if(modelId==="KFJJZGG_GM"||modelId==='KFJJZGG_GM'){
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('本期开放结束日必填!',2);
					return;
				}
			}
			if(modelId==="KFJJZGG_SM"||modelId==='KFJJZGG_SM'){
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('本期开放结束日必填!',2);
					return;
				}
			}
			if(modelId==="JDBG_ZSX"||modelId==='JDBG_FZSX'){
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
				if (params['year']===null||params['year']===undefined) {
					Joyin.alert('报告年度必填!',2);
					return;
				}
			}
			if(modelId==="TZTZFBZCGG"){
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('公告日期必填!',2);
					return;
				}
			}
			if(modelId==="DQGG_GM"||modelId==="DQGBG_SM"){
			}
			if(modelId==="ZJCPXSJGGG"){
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('公告日期必填!',2);
					return;
				}
			}
			if(modelId==="CPBCLGG_GM"||modelId==="CPBCLBG_SM"){
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('产品发布日期必填!',2);
					return;
				}
			}
			if(modelId==="CPBCLGG_GM"||modelId==="CPBCLBG_SM"){
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('产品发布日期必填!',2);
					return;
				}
			}
			if(modelId==="DCPDFLMB1_GM"||modelId==="DCPDFLMB1_SM"||modelId==="DCPDFLMB2_GM"||modelId==="DCPDFLMB2_SM"){
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
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('发布日期必填!',2);
					return;
				}
			}
			if(modelId==="JESHGG_GM"||modelId==="JESHGBG_SM"){
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
			}
			if(modelId==="FHJSQD"){
				if (params['enddate']===null||params['enddate']===undefined) {
					Joyin.alert('收益分配基准日!',2);
					return;
				}
			}
			if(modelId==="FXGG"){
			}
    	}else{
    		return;
    	}
 
    	return "success";
	}
	  
	var showProegress = function() {
		layer.msg("报表生成中，请稍候... ...",{
			icon: 16,
			shade: 0.2,
			time: false
		});
		return true;
	}
	
	/**
	 * 批量生成报表
	 */
   var submit = function(type){
       //showProegress();  //等待窗口
   		JoyinProgress.showInDialog('batchGenProgressBar');  //进度条
       displayMenu();
		var params = getSearchParams();

		if ( checkParams(params)!=="success")
			return;
		
		var listProd = getProds();
    	if ( listProd && listProd.length>0) {
    		params['listProd'] = JSON.stringify(listProd);
    	} else {
    		return;
    	}
    	
    	var uuid = 'pageid' + randomString(10, '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
		params.rep_id=uuid;
    	//导出会报错所以暂时屏蔽name
    	delete params.portname;
    	
    	//报表数据结构
    	var report_info = {
                uri:modelId,
                folderUri:modelUri,
                downloadName:'report',//希望下载的名字：供exportReport使用
                formatType:type,//下载的格式:供exportReport使用, 支持的格式有pdf,html,xls,xlsx,rtf,csv,xml,docx,odt,ods,jrprint
                params:params//运行报表所使用的参数
            }

            //运行并产生报表到页面
            params['fileType'] = type;
            params['modelId'] = modelId;
            params['repId'] = repId;
            params['repName'] = repName;
			params['uri'] = modelId;
			params['flag']="02";
			Joyin.ajaxJson(basePath + 'disclosure/information/reveal/genreport/BatchGenReport001/check',params, function(result) {
            	params['operateType'] = "01";
            	Joyin.ajax('batchExec',
            		params,
        			function(ret) {
            			//layer.alert(data.message);
            			$("#showtable").html(ret.data);
                    });
            });
    }
    
    function displayMenu(){
    	//$("#btnGenReport").css('display','none'); 
    }	
    
    /*生成随机字符串  add by kongzhifang*/
    function randomString(length, chars) {
        var result = '';
        for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
        return result;
    }
 
	$('#tree').joyinTree({
			onTreeLoadSuccess : function() {
				if (pageModel == PAGE_MODEL_REFERENCE) {
					var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
					var nodes = treeObj.getNodes();
					for (var i = 0; i < nodes.length; i++) {
						var node = nodes[i];
						DisabledNode(treeObj, node);
					}
				}
			}
		});

	function expandAll() {
		$.fn.zTree.getZTreeObj('tree_tree').expandAll(!$('#expandAll').is(':checked'));
	};

	function DisabledNode(treeObj, node) {
		treeObj.setChkDisabled(node, true);
		if(node.isParent) {
			var children = node.children;
			for(var i=0; i < children.length; i++) {
				DisabledNode(treeObj, children[i]);
			}
		}
	}

	/**
	 * 递归获取节点和父节点数据
	 */
	function getParam(node, params) {
		if (node) {
			if(node.id != null && node.pId !=null && node.pId.length>4 && $.inArray(node.id, params) < 0){
				if(node.id.startsWith("02") || node.id.startsWith("03")){
					return false;
				}
				params.push(node);
			}
			getParam(node.getParentNode(), params);
		}
	}

	function getProds() {
		var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
		var nodes = treeObj.getCheckedNodes();
		var params = new Array();
		for (var i = 0; i < nodes.length; i++) {
			if ( nodes[i].level>=3 && (nodes[i].isParent==null || nodes[i].isParent==false) ) //取叶子节点
			params.push(nodes[i]);
			//var flag = getParam(nodes[i], params);
		}
		if(params.length == 0){
			Joyin.alert('请选择产品！',2);
			return;
		}
		return params;
	}
	
	/**
     * 可能是无效代码
     */
	$("#toSubmit").css('min-width','50px'); 
	$("#toExport").css('min-width','50px'); 

	$("#toSubmit").css('right','65px'); 
	$("#toExport").css('left','140px'); 
	
    /**
     * 画面加载完成
     */
    $(function() {
    	
    	// 绑定按钮事件
        $('#btnGenReport').click(function(){
        	submit("docx");
        });
          
    	$('#showtable').width((docW-350)+"px");
    	$('#showtable').height((docH-tabHeadUlH)+"px");

    	tree.children('div').eq(1).height((docH-tabHeadUlH)+"px");  

		$('#expandAll').next().on('click',function(){
			expandAll();
		});
    	
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


