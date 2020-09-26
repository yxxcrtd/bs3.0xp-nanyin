$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            profitType: $.trim($('#profitType').val()),
            operationType: $.trim($('#operationType').val()), 
            finprodId: $.trim($('#finprodId').val()),
            finprodType: $.trim($('#finprodType').val()),
            vdateStart:$.trim($('#vdate').startDate()), 
            vdateEnd:$.trim($('#vdate').endDate()), 
            mdateStart:$.trim($('#mdate').startDate()),
            mdateEnd:$.trim($('#mdate').endDate())
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
        dblclick:function(table, data) {
            if(data.profitType=='01'){
                Joyin.dialog(JoyinUtil.getLabel("查看净值型产品信息"),
                        JoyinUtil.getRealPath("/valuation/finncproductbuild/FinNCProductBuild002/init"),
                        "95%", "95%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId},
                        function(){
                        });
            }else{
                Joyin.dialog(JoyinUtil.getLabel("查看货币型产品信息"),
                        JoyinUtil.getRealPath("/valuation/finncproductbuild/FinNCProductBuild002/init"),
                        "95%", "95%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:data.finprodId},
                        function(){
                        });
            }
        },
		checkbox: true,
		cellMinWidth : 100,
		cols: [
		   {field:'finprodId', title: '产品代码',width:150, sort: true},
		   {field:'finprodAbbr', title: '产品简称',width:180, sort: true},
	       {field:'profitType', title: '产品类型',width:120, sort: true,visible:false},
		   {field:'profitTypeFmt', sortField: "profitType", title: '产品类型',width:120, sort: true},
		   {field:'finprodType', title: '金融产品类型',width:150, sort: true,visible:false},
		   {field:'finprodTypeFmt', sortField: "finprodType", title: '金融产品类型',width:150, sort: true},
		   {field:'operationType', title: '发行方式',width:120,sort: true,visible:false},
           {field:'operationTypeFmt', sortField: "operationType", title: '发行方式',width:120,sort: true },
           {field:'profitFlag', title: '收益标识',width:150,sort: true,visible:false },
           {field:'profitFlagFmt', sortField: "profitFlag", title: '收益标识',width:150,sort: true},
		   {field:'vdate', title: '起息日',width:110, sort: true},
		   {field:'mdate', title: '到期日',width:110, sort: true},
		   {field:'ccy', title: '募集币种',width:130,sort: true,visible:false},
           {field:'ccyFmt', sortField: "ccy", title: '募集币种',width:130,sort: true},
           {field:'planraiseAmt', type:"money", title: '募集金额',width:150,sort: true},
           {field:'basis', title: '计息基础',width:100, sort: true},
		   {field:'createOrgName', title: '创建机构',width:110, sort: true},
		   {field:'valOrgName', title: '估值机构',width:110, sort: true},
           {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
  	        	buttons: [
  	                {text: '&#xe642;',clas: 'edit',title: '编辑'},
  	                {clas: 'iconfont icon-copy',title: '复制'},
  	                {text: '&#xe640;',clas: 'delt',title: '删除'}]
  	        }
		],
	});
	
	var exportExcel = function() {
	    var selArr;
        var datas;
        if (table != null && table != '') {
            selArr= table.getAllCheckedRows();
            datas = JSON.stringify(selArr);
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
     * 事件绑定
     */
    var tableBind = function () {
		/**
		 * 绑定事件：新建
		 */
		$("#FinNCProductBuild001_btn_add").click(function(){
			Joyin.dialog(JoyinUtil.getLabel("新建净值型产品信息"),
                    JoyinUtil.getRealPath("/valuation/finncproductbuild/FinNCProductBuild002/init"),
                    "95%", "95%", {pageModel:PAGE_MODEL_NEW, operateType: "add"},
                    function(){
                        table.reload();
                    });
		});
    	/**
    	 * 绑定事件：修改
    	 */
		table.bind('click', '.edit', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("编辑产品信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/finncproductbuild/FinNCProductBuild002/init",
		               "95%", "95%", {pageModel:PAGE_MODEL_EDIT, finprodId: data.finprodId, operateType: "edit"},function(){
		            	   table.reload();
		       });
        });
    	/**
    	 * 绑定事件：复制
    	 */
		table.bind('click', '.icon-copy', function(data, index) {
		   	Joyin.dialog(JoyinUtil.getLabel("复制产品信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
		               basePath + "valuation/finncproductbuild/FinNCProductBuild002/init",
		               "95%", "95%", {pageModel:PAGE_MODEL_NEW, finprodId: data.finprodId, operateType: "copy"},function(){
		            	   table.reload();
		       });
        });
    	/**
    	 * 绑定事件：删除
    	 */
		table.bind('click', '.delt', function(data, index) {
			var object = {};
			var listdto=[data];
            object.listFinNCProductBuildDto = listdto;
	    	object.operateType = "delete";
            Joyin.ajaxJson(basePath + 'valuation/finncproductbuild/FinNCProductBuild001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                	WorkFlow.doDeleteList(basePath + 'valuation/finncproductbuild/FinNCProductBuild001/delete', object, "FinNCProductBuild001_btn_delete", "FinNCProductBuild002",function() {
 	        		   table.reload();
                    });
                });
            });
        });
    	/**
    	 * 绑定事件：批量删除
    	 */
        $('#FinNCProductBuild001_btn_delete').click(function(){
            if(table != null && table != ''){
                var selArr = table.getAllCheckedRows();
                var datas = JSON.stringify(selArr);
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error("请选择记录进行删除。");
                return;
            }
            var object = {};
            object.listFinNCProductBuildDto = selArr;
            object.data = datas;
            object.operateType = "delete";
            Joyin.ajaxJson(basePath + 'valuation/finncproductbuild/FinNCProductBuild001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/finncproductbuild/FinNCProductBuild001/delete', object, "FinNCProductBuild001_btn_delete", "FinNCProductBuild002",function() {
    	        		   table.reload();
                    });
                });
            });
        });
    };

	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询事件
        $('#FinNCProductBuild001_btn_search').click(function() {
            table.load();
        });
        // 绑定查询事件
        $('#FinNCProductBuild001_btn_export').click(function() {
            exportExcel();
        });
        //新建、修改、删除事件绑定
        tableBind();
    });
}());