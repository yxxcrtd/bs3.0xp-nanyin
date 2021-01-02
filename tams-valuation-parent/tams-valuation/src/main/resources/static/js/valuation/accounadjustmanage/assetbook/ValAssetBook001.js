$(function() {
  
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            name : $.trim($('#ValAssetBook001_name').val()),
            type : $('#ValAssetBook001_type').val()
        }
    };
/**
 * 表格加载
 */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'ValAssetBook001search',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
        cols : [ 
                {field : 'booksetName', fixed : true, title : '账套名称',sort : true, width: 200},
                { field : 'booksetType', title : '账套类别',sort : true, width: 200},
                {field : 'acctSubjectId',title : '核算主体代码',sort : true, width: 200},
                {field : 'standardCcy',title : '本位币',sortField:'standardCcyCode', sort : true, width: 200},
                {field : 'acctSubjectType', title : '核算主体类别',sort : true, width: 200},
                {field : 'booksetIdTmpl',title : '模板账套代码', sort : true, width: 200},
                {field : 'acctStandard', title : '会计准则',sort : true, width: 200},
                {field : 'startDate', title : '账套开始日', sort : true, width: 200},
                {field : 'endDate',title : '账套结束日', sort : true, width: 200},
                {field : 'booksetId',title : 'booksetId',visible : false, width: 200},
                {field : 'booksetTypeCode',title : 'booksetTypeCode',visible : false, width: 200},
                {field:'operation', title: '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp操 作', width:120, type:'action', fixed: 'right',
    	        	buttons: [
    	                {text: '&#xe642;',clas: 'edit',title: '编辑'},
//    	                {clas: 'iconfont icon-copy',title: '复制'},
//    	                {text: '&#xe640;',clas: 'delt',title: '删除'}
    	                ]
    	        }
               ],
        dblclick: function(table, data){
        	Joyin.dialog(JoyinUtil.getLabel("账套明细"), basePath + "valuation/accounadjustmanage/assetbook/ValAssetBook002/init",
                    "90%", "90%", {booksetId:data.booksetId, pageModel:PAGE_MODEL_REFERENCE,status:"02"}, function(){
                        //table.reload();
                    }
              );
        }
    });
    
    /**
     * 账套事件绑定
     */
    var tableBind = function () {
    	/**
         * 绑定编辑事件
         */
        table.bind('click', '.edit', function(data, index) {
            Joyin.dialog(JoyinUtil.getLabel("修改账套"), basePath
                    + "valuation/accounadjustmanage/assetbook/ValAssetBook002/init",
                    "90%",
                    "90%",
                    {booksetId:data.booksetId, pageModel : PAGE_MODEL_EDIT},    
                    function(){
                        table.reload();
                        pageModel : PAGE_MODEL_EDIT
                    }
              );
        });
        
        /**
         * 新建账套
         */
        $('#ValAssetBook001_btn_add').click(
	        function() {
	            Joyin.dialog(JoyinUtil.getLabel("新建账套"), basePath
	                    + "valuation/accounadjustmanage/assetbook/ValAssetBook002/init", "90%",
	                    "90%", null,
	                    function(){
	                        table.reload();
	                        pageModel : PAGE_MODEL_NEW
	                    },
	                    null
	            ); 
        });
        
        /**
         * 筛选
         */
        $('#ValAssetBook001_btn_search').click(function() {
            table.load();
        });
        /**
         * 导出
         */
        $('#ValAssetBook001_btn_export').click(function() {
            exportExcel();
        });
        
        /**
         * 删除
         */
        $('#ValAssetBook001_btn_delete').click(function() {
        	if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("没有选中数据"));
                return;
            }
            var object = selArr[0];
        	if ("01" == object.booksetTypeCode) {
        		// 账套模板
        		deleteData();
        	}
        	if ("02" == object.booksetTypeCode) {
        		// 理财账套
                Joyin.error(JoyinUtil.getMessage("抱歉，理财账套暂时不支持删除"));
                return;
        	}
//        	if ("02" == object.booksetTypeCode) {
//        		// 理财账套
//        		deleteDataWithFlow();
//        	}
        });

        // 2.0.1 个性化 : BS2.0BSTWO-26 建议勾选新的账套类别，实时刷新清空显示一览列表。
        $("#ValAssetBook001_type").next("ul").click(function () {
            if (!table.table){
                table.load();
            } else {
                table.reload();
            }
        });
    };
    
    

	/**
	 * 删除
	 */
    var deleteData = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            console.log(selArr);
            var datas = JSON.stringify(selArr);
        }
        var object = selArr[0];
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            Joyin.ajax('delete', {datas : datas}, function(data) {  
                if (data) {      
                    table.reload();
                    layui.layer.alert("删除成功");
                } else {
                    layui.layer.alert("删除失败");
                }
            }
            );
        });
        

    };
    
    /**
	 * 理财账套删除-带流程
	 */
    var deleteDataWithFlow = function() {
    	var selArr = table.getAllCheckedRows();
        // 启动删除流程
        var object = selArr[0];
        object.operateType = "delete";
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/assetbook/ValAssetBook001/doDeleteCheck', object, function(result) {
        	if (result.data) {
        		Joyin.confirm(result.data, function(){
	                WorkFlow.doDeleteList(
	                         basePath + 'valuation/accounadjustmanage/assetbook/ValAssetBook001/deleteWithFlow', 
	                         object, 
	                         "ValAssetBook001_btn_delete", 
	                         "ValAssetBook005",
	                         function() {
	                             table.reload();
	                         }
	                 );
        		});
        	}
        });
    };

    /**
     * 导出表格
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
     * 账套参数修改
     */
    var modification = function(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
            //判断是否选中元素
            if( selArr != null &&  selArr.length > 0 ){
                if (selArr.length != 1){
                    Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
                }
                var data = selArr[0];
//                if(data.booksetTypeCode&&data.booksetTypeCode!='02'){
//                    Joyin.error(JoyinUtil.getMessage("只能修改“账套类别”为“理财账套”的参数！"));
//                    return;
//                }
                Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/assetbook/ValAssetBook006/check', {booksetId:data.booksetId,booksetTypeCode:data.booksetTypeCode}, function(result1) {
//                    if(data.booksetTypeCode=='02'&&data.startDate && data.startDate <= sysDate){
//                        Joyin.warn(JoyinUtil.getMessage("该产品已经起息，不支持修改，只能查看！"));
//                        data.modifyFlag=false;
//                    }
                    Joyin.dialog(JoyinUtil.getLabel("账套参数修改"),
                        JoyinUtil.getRealPath("valuation/accounadjustmanage/assetbook/ValAssetBook006/init"),
                        "99%", "95%",{modifyFlag : data.modifyFlag, booksetId : data.booksetId},function(){table.reload();}
                    );
                });
            }else{
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
        }else{
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
    }
    /**
     * 页面加载完后执行
     */
    $(function(){
    	tableBind();// 账套事件绑定

    	$('#ValAssetBook001_btn_modification').click(function() {
            //账套参数修改
            modification();
        });
    });

});