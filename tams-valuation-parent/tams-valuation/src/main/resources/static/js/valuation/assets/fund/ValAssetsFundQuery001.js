$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	finprodId: $.trim($('#finprodId').val()),
        	fundType: $.trim($('#fundType').val()),     
        	issuer: $.trim($('#issuer').val())
        }
    };
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {// 绑定双击事件，
//        	TODO PAGE_MODEL_REFERENCE存在bug，对于input-checkbox即layui-chcekbox不能disable
//        	joyin-plugins-all要求：_self.prop('disabled') ? _self.next().addClass('disabled') : bindClick(_self);
        	var d_with = "90%";
            var d_high = "90%";
        	if(data.fundTypeCode != '01'){
        		d_with = "90%";
        		d_high = "90%";
        	}
            Joyin.dialog(JoyinUtil.getLabel("查看基金类资产"),
                    basePath + "valuation/assets/fund/ValAssetsFundQuery002/init",
                    d_with, d_high, {pageModel:PAGE_MODEL_REFERENCE,
            	finprodId:data.finprodId,operateType:"view"},function(){
            	    //table.reload();
            	    });
        },
        checkbox: true,
		cellMinWidth : 100,
		cols: [
		   {field:'finprodId', title: '资产代码', sort: true},
		   {field:'finprodAbbr', title: '资产名称', width:180, sort: true},
		   {field:'fundType', title: '基金类型Code', sort: true, visible:false},
		   {field:'finprodMarketId', title: '市场代码',sort: true},
		   {field:'fundTypeName', title: '基金类型', sortField:'fundType',sort: true},
		   {field:'capiIncomeFeatureFmt', title: '本金收益特征', sortField:'capiIncomeFeature',sort: true},
		   {field:'payCycleFmt', title: '分红频率',sortField:'payCycle',sort: true},
		   {field:'calendarName', title: '交易日历',width:160, sortField:'calendarId',sort: true},
           {field:'issuer', title: '管理人', sort: true},
		   {field:'ccyName', title: '币种',sortField:'ccyCode',sort: true},
//		   {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
//               buttons: [
//                   {text: '&#xe642;',clas: 'edit',title: '编辑'},
//                   {text: '&#xe640;',clas: 'delt',title: '删除'}]
//           }
		   {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
		],
	});
	
	/*var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };*/
	
	/**
	 * 事件绑定
	 */
	var tableBind = function () {
	
	/**
     * 绑定事件：基金类资产 - 新建
     */
    $("#ValAssetsFundQuery001_btn_add").click(function(event){
        Joyin.dialog(JoyinUtil.getLabel("新建基金类资产（ "+ "<span style='color:red'> * </span>" + "必填）"),
                   basePath + "valuation/assets/fund/ValAssetsFundQuery002/init",
                   "85%", "85%", {pageModel : PAGE_MODEL_NEW,finprodId: "",operateType:"new"},function(){
                       table.reload();
                       
           });
    });
    
//    /**
//     * 绑定事件：基金类资产 - 编辑
//     */
//    table.bind('click', '.edit', function(data, index) {
//        Joyin.dialog(JoyinUtil.getLabel("编辑基金类资产（ "+ "<span style='color:red'> * </span>" + "必填）"),
//                   basePath + "valuation/assets/fund/ValAssetsFundQuery003/init",
//                   "85%", "85%", {pageModel:PAGE_MODEL_EDIT,finprodId: data.finprodId},function(){
//                       table.reload();
//           });
//    });
    
    
    /**
     * 绑定事件：基金类资产 - 编辑
     */
    table.bind('click', '.edit', function(data, index) {
        var object = {};
        object.finprodId = data.finprodId;
        Joyin.ajaxJson(basePath + 'valuation/assets/fund/ValAssetsFundQuery001/updateCheck', object, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("编辑基金类资产（ "+ "<span style='color:red'> * </span>" + "必填）"),
                  basePath + "valuation/assets/fund/ValAssetsFundQuery002/init",
                  "85%", "85%", {pageModel:PAGE_MODEL_EDIT,finprodId: data.finprodId,operateType:"edit"},function(){
                      table.reload();
               });
           });
     });
    
    
//    /**
//     * 绑定事件：一般资产估值 - 删除
//     */
//    table.bind('click', '.delt', function(data, index) {
//        var object = {};
//        object.finprodId = data.finprodId;
//        object.fundType = data.fundType;
//        var message = "是否删除"+data.finprodAbbr+"["+data.finprodId+"]的产品";
//        Joyin.ajaxJson(basePath + 'valuation/assets/fund/ValAssetsFundQuery001/deleteCheck', object, function(result) {
//               Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.delete")), function(){
//                Joyin.ajaxJson(basePath + 'valuation/assets/fund/ValAssetsFundQuery001/delete', object, function(result) {
//                    table.reload();
//                });
//               });
//           });
//     });
//    
//	}
    
	
	/**
     * 删除-走流程
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {};
        object.finprodId = data.finprodId;
        object.fundType = data.fundType;
        var message = "是否删除"+data.finprodAbbr+"["+data.finprodId+"]的产品";
        Joyin.ajaxJson(basePath + 'valuation/assets/fund/ValAssetsFundQuery001/deleteCheck', object, function(result) {
               Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.delete")), function(){
                   WorkFlow.doDeleteList(
                           basePath + 'valuation/assets/fund/ValAssetsFundQuery001/delete', 
                           object, 
                           "ValAssetsFundQuery001_btn_delete", 
                           "ValAssetsFundQuery002",
                           function() {
                               table.reload();
                           }
                       );
               });
           });
     });
    
    }


	 /**
     * 导出
     */
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValAssetsFundQuery001_btn_search').click(function() {
            table.load();
        });
        
       // 绑定导出事件
        $('#ValAssetsFundQuery001_btn_export').click(function() {
            exportExcel();
        });

        //新建、修改、删除事件绑定
        tableBind();
    });
	
});



