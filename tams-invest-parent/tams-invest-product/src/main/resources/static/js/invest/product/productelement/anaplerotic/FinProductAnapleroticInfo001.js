$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = {};
        if ($("#finprodId").val()) {
        	param.portfolioId = $("#finprodId").val().join(",");
        } else {
        	param.portfolioId = null;
        }
        if($("#prodStyle").val()){
        	param.prodStyle = $("#prodStyle").val().join(',');
    	}
        param.effDateStart = $('#effDate').startDate();
        param.effDateEnd = $('#effDate').endDate();
        return param;
    };
    var buttons = [{ text: '&#xe642;',ignore:true, clas: 'edit', title: '编辑' },
                   { text: '&#xe640;',ignore:true, clas: 'delt', title: '删除' }];
    /**
     * 定义数据表格
     */
    var table=$("#finProductAnapleroticPlanTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cellMinWidth : 200,
        cols:[
             {field:'anapleroticId', visible : false} //回补代码
            ,{field:'finprodId', title: 'FinProductAnapleroticInfo.finprodId', width:200, sort : true} //产品代码
            ,{field:'finprodAbbr', title: 'FinProductAnapleroticInfo.finprodAbbr',sort : true}//产品简称
            ,{field:'prodStyle', title: 'BussFinProductAnapleroticInfo.prodStyle',sort : true}//适用款型
            ,{field:'effDateStr', title: 'FinProductAnapleroticInfo.effDate',sort : true,sortField:'effDate'}//生效日
            ,{field:'sFinprodId', visible : false}//若为分档产品时的产品代码
            ,{field:'anapleroticType', visible : false}//回补形式
            ,{field:'anapleroticTypeStr', title: 'FinProductAnapleroticInfo.anapleroticType',sortable: false}//回补形式
            ,{field:'anapleroticRatio', title: 'FinProductAnapleroticInfo.anapleroticRatio', visible : false}//回补比例
            ,{field:'anapleroticRule', visible : false}//回补方式
            ,{field:'anapleroticRuleStr', title: 'FinProductAnapleroticInfo.anapleroticRule',sortable: false}//回补方式
            , {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
        ],
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("FinProductAnapleroticInfo002.update"), JoyinUtil.getRealPath('invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/init'),
                    "98%", "95%", {pageModel:PAGE_MODEL_REFERENCE, anapleroticId : data.anapleroticId,effDateStr:data.effDateStr},function(){table.reload()})
        },
    });
    /**
     * 导出
     */
    var exportExcel = function() {
        Joyin.exportExcel('export', getSearchParams());
    };
  //交易类型 下拉枚举值联动初始化
    var prodStyleSelectInit = function(finprodId){
    	
    	$('#prodStyle').empty();
    	Joyin.ajax(basePath + 'invest/product/messageadjust/finfloatmgtexpquotation/FinFloatMgtExpQuotation001/queryLayerProdStyleList', 
    			{finprodMarketId:finprodId}, function(result) {
      		 $("#prodStyle option").remove();
      		 if(result != null && result.length > 0){
      			 // $("#prodStyle").append("<option></option>");
      			 for(var i in result){
      				 $("#prodStyle").append("<option value='"+ result[i].id +"'>"+result[i].text + "</option>");
      			 }
      		 }
    	});	
    };
  //画面联动：输入产品代码，联动显示产品名称和产品简称
    var finprodIdChange = function() {
        var finprodId = $("#finprodId").val();
        if(finprodId){
        	prodStyleSelectInit(finprodId.join(","));
        }   
    };
    /**
     * 绑定事件： - 删除
     */
    table.bind('click', '.delt', function(data, index) {
    	data.operateType = 'delete';
    	Joyin.ajaxJson(basePath + 'invest/product/productelement/anaplerotic/FinProductAnapleroticInfo001/check', data, function(result) {
	         Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	                WorkFlow.doDeleteList(basePath + 'invest/product/productelement/anaplerotic/FinProductAnapleroticInfo001/deleteByPrimaryKey', {anapleroticId : data.anapleroticId, effDate : data.effDateStr}, "FinProductAnapleroticInfo001_btn_delete", "FinProductAnapleroticInfo002");
	            });
    	});
    });
    /**
	 * 绑定事件： - 修改编辑
	 */
    /**
     * 绑定修改事件
     */
    table.bind('click', '.edit', function(data, index) {
    	  data.operateType = 'edit';
    	  Joyin.ajaxJson(basePath + 'invest/product/productelement/anaplerotic/FinProductAnapleroticInfo001/check', data, function(result) {
	        Joyin.dialog(JoyinUtil.getLabel("FinProductAnapleroticInfo002.update"), JoyinUtil.getRealPath('/invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/init'),
	                "98%", "95%", {pageModel:PAGE_MODEL_EDIT, anapleroticId : data.anapleroticId,effDateStr:data.effDateStr},function(){table.reload()})
	    });
    });
    
    var openNew = function(){
        Joyin.dialog(JoyinUtil.getLabel("FinProductAnapleroticInfo002"),
                JoyinUtil.getRealPath("/invest/product/productelement/anaplerotic/FinProductAnapleroticInfo002/init"),
                "98%", "95%",{pageModel : PAGE_MODEL_NEW},function(){table.reload()}
        );
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	//获取产品款型联动
        $('#finprodId').change(function() {
            finprodIdChange();
        });
        // 绑定按钮事件
        $('#FinProductAnapleroticInfo001_btn_search').click(function() {
            table.load();
        });
        //新建按钮点击
        $('#FinProductAnapleroticInfo001_btn_add').click(function() {
            openNew();
        });
        $('#FinProductAnapleroticInfo001_btn_export').click(function() {
            exportExcel();
        });
    });
}());