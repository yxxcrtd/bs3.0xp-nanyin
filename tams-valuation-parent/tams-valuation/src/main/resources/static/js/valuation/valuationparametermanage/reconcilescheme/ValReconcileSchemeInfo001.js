//上传文件信息
var FileUpload = function() {
        return {
            /**
             * 文件上传后记录文件信息
             */
            onUploaded : function(res) {
                if(res && res.length > 0){
                    Joyin.ajax('uploadFile', {
                        fileName: res[0].fileName,
                        schemeType: $('#schemeType').val(),
                        booksetId : $("#booksetId").val()
                    }, function(data) {
                        var result = data.data;
                        if(result.status=='N'){
                            table_construct.reload({data : result.structConfigList});
                            table_subject.reload({data : result.subjectConfigList});
                            table_assest.reload({data : result.assetConfigList});
                            table_nature.reload({data : result.natureConfigList});
                        }
                    });
                }
            }      
        };
    }();
var table_construct;
var table_subject;
var table_assest;
var table_nature;
$(function () {
	
	//  =========================  设置（左侧列表）start   =======================
	/**
     * 左侧 - 获取查询参数
     */
    var getSearchKey = function() {
        return {
        	searchKey: $('#searchKey').val()
        }
    };
    
    var schemeTable=$("#schemeTable").joyintable({
        ajax: {
            url: 'findSchemeList',
            type: 'POST',
            data: function() {
                return getSearchKey();
            }
        },
		searchOnInit: false,
		cellMinWidth:100 ,
		click:function(table, data){
			//点击时刷新右侧表格
			//	----------------  添加操作图标和绑定事件  ------------------
			var index = data.LAY_TABLE_INDEX;
	      	var tt=$('#schemeTable').next().children(".layui-table-box").find(".layui-table-body").eq(0);
	      	tt.find("tr").each(function(i){
	          	$(this).find('td[data-field="schName"]').each(function(){  // 遍历 tr 的各个 td
	              	var _text=$(this).text();
	              	if(index==i){
	                  	$(this).children("div").nextAll().remove();
	                  	var html = "<div style='float:right'>" +
                                  "  <i class='iconfont icon-delete schName' ></i>";
	                  	$(this).children("div").after(html);
	              	}else{
	              		$(this).children("div").nextAll().remove();
	              	}
	          	});
	      	}); 
	      	// 对应页面回显信息
	      	$("#schId").val(data.schId);
			$("#schName").val(data.schName);
			$("#booksetName").val(data.booksetName+"（"+data.booksetId+"）");
			$("#booksetId").val(data.booksetId);
			$("#schemeTypeFmt").val(data.schemeTypeFmt);
			$("#schemeType").val(data.schemeType);
			$("#isDefault").val(data.isDefault);
			$("#isDefaultFmt").val(data.isDefaultFmt);
			$("#errorNumber").val(data.errorNumber);
			$("#myStartRowNo").val(data.myStartRowNo);
			$("#othersideStartRowNo").val(data.othersideStartRowNo);
			// 表格重新加载信息
			table_construct.reload();
			table_subject.reload();
			table_assest.reload();
			table_nature.reload();
	      	
	      	$(".schName.icon-delete").click(function (event){
	        	event.stopPropagation();// 阻止事件冒泡
	        	var object = {};
	        	object.schId = data.schId;
	        	object.operateType = "delete";
	        	if(!$("#schId").val()){
	                Joyin.error("请先选择方案");
	                return;
	            }
	            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
	                var para =  {
	                        schId : data.schId,
	                        booksetId : data.booksetId
	                    };
	                Joyin.ajax('deleteScheme',para, function(result){
	                	// 方案清空时，全部清空
	                    schemeTable.reload();
	                });
	            });
	        });
		},
        checkbox:false, 
		cols: [
			{field:'schName', title: '方案名称', sortable: false},
			{field:'schId', title: '方案代码', sortable: false,visible:false},
			{field:'booksetId', title: '账套代码', sortable: false,visible:false},
			{field:'booksetSchemeId', title: '账套方案代码', sortable: false,visible:false},
			{field:'schemeTypeFmt', title: '方案类型', sortable: false,visible:false},
			{field:'schemeType', title: '方案类型', sortable: false,visible:false},
			{field:'isDefaultFmt', title: '默认方案', sortable: false,visible:false},
			{field:'isDefault', title: '默认方案', sortable: false,visible:false},
			{field:'errorNumber', title: '单一科目允许误差', sortable: false,visible:false},
			{field:'myStartRowNo', title: '本方数据起始行', sortable: false,visible:false},
			{field:'othersideStartRowNo', title: '外部系统数据起始行', sortable: false,visible:false},
		],
		done:function(){
			$('#schemeTable').next().find("thead").remove();// 删除表头
			var schArr = $('#schemeTable').next().find("td");
			if( schArr != undefined && schArr.length > 0 ){
				schArr[0].click();// 默认选中第一个影子定价方案
			}
		}
    });
	//  =========================  设置（左侧列表）end   =======================
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            schId: $("#schId").val(),
            booksetId : $("#booksetId").val()
        }
    };
    
    /**
     * 定义数据表格——结构映射
     */
    table_construct=$("#table_construct").joyintable({
        ajax: {
            url:  "initStructGrid",
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit: true,
        checkbox: false,
        height: 200,
        cols: [
           {field:'blockTypeFmt', sortField:"blockType", title: '区块类型',sort: true},
           {field:'myColRemark', sortField:"myColId", title: '本系统指标', sort: true},
           {field:'myColId', sortField:"myColId", title: '本系统指标', visible: false},
           {field:'othersideColId', sortField:"othersideColId", title: '外部系统列', sort: true},
        ],
        loadonce:true,
        page:false
    });
    
    /**
     * 定义数据表格——科目映射
     */
    table_subject=$("#table_subject").joyintable({
        ajax: {
            url : "initSubjectGrid",
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit: true,
        checkbox: false,
        height: 200,
        cols: [
               {field:'mySubjectId', title:'本系统科目号'},
               {field:'othersideSubjectId', title:'外部系统科目号'},
               {field:'myIsGenSubsFmt', sortField:"isGenSubs", title: '是否扩展四级科目（本方）'},
               {field:'mySubjectName', title: '本系统科目名称'},
               {field:'othersideSubjectName', title: '外部系统科目名称'},
               {field:'isGenSubsFmt', sortField:"isGenSubs", title: '是否扩展四级科目（外部）'},
               {field:'isGenSubs', title: '是否扩展四级科目（外部）', visible: false},
               {field:'blockTypeFmt', sortField:"blockType", title: '区块类型'},
               {field:'blockType', title: '区块类型', visible: false},
        ],
        loadonce:true,
        page:false
    });
    
    /**
     * 定义数据表格——科目性质映射
     */
    table_nature=$("#table_nature").joyintable({
        ajax: {
            url : "initNatureGrid",
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit: true,
        checkbox: false,
        height: 200,
        cols: [
               {field:'othersideSubjectId', title:'外部系统科目号'},
               {field:'othersideSubjectName', title: '外部系统科目名称'},
               {field:'myNatureName', title: '所属本系统的科目性质描述'},
               {field:'myNatureId', title: '所属本系统的科目性质代码'},
//               {field:'isGenSubsFmt', sortField:"isGenSubs", title: '是否扩展四级科目'},
               {field:'isGenSubs', title: '是否扩展四级科目', visible: false},
               {field:'blockTypeFmt', sortField:"blockType", title: '区块类型'},
               {field:'blockType', title: '区块类型', visible: false},
        ],
        loadonce:true,
        page:false
    });
    
    /**
     * 定义数据表格——资产映射
     */
    table_assest=$("#table_assest").joyintable({
        ajax: {
            url : "initAssetGrid",
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit: true,
        checkbox: false,
        height: 200,
        cols: [
               {field:'myAssetId', title:'本系统资产代码'},
               {field:'othersideAssetId', title:'外部系统资产代码'},
        ],
        loadonce:true,
        page:false
    });
    
    $("#ValReconcileSchemeInfo001_btn_add").click(function(){
        Joyin.dialog(JoyinUtil.getLabel("新建对账方案（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                basePath + "valuation/valuationparametermanage/reconcilescheme/ValReconcileSchemeInfo002/init",
                "75%", "70%", {pageModel:PAGE_MODEL_NEW},function(){
                    document.location.reload();
                });
    });
    
    $("#ValReconcileSchemeInfo001_btn_submit").click(function(){
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.structConfigList = table_construct.getAllRows();
        object.subjectConfigList = table_subject.getAllRows();
        object.natureConfigList = table_nature.getAllRows();
        object.assetConfigList = table_assest.getAllRows();
        Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/reconcilescheme/ValReconcileSchemeInfo001/check', object, function(result) {
            Joyin.ajaxJson(basePath + 'valuation/valuationparametermanage/reconcilescheme/ValReconcileSchemeInfo001/save', object, function(result) {
            	document.location.reload();
            });
        });
    });
    
    // 点击重置按钮
    $("#ValReconcileSchemeInfo001_btn_reset").on('click', function(){
        document.location.reload();
    });
    
    /** 带图标的输入框绑定事件:searchKey */
	$(".iconinput").click(function(){
		schemeTable.reload();//加载左侧名称列表
	});	
    
    /**
     * 页面加载完后执行
     */
    $(function() {
    	schemeTable.load();//加载左侧影子定价方案列表  
    });
    
});
