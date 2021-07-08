var QueryFunc = function () {
    //放大镜点击事件
    return {
        serachKey: function () {
            //树的重新加载
            loadTree();
        }
    }
}();
var nodeId = '';
//var nodeName = '';
//树的加载
var loadTree = function (callback) {
    $("#mixTree").load(basePath +'invest/entrust/etribrepo/EtrIbRepoRk/getAssetName',
        {pledgeStockName: $('#pledgeStockName').val()}, function (data) {
            nodeId = '';
            if (typeof callback == 'function') {
                callback();
            }
        })
}

	parent.EtrIbRepoRk = {
		loadTree: loadTree
    };
	
	EtrIbRepoRk = {
		loadTree: loadTree
	};

$(function () {
	
	/**
     * 获取提交的数据
     * @param operateType
     * @returns {{}}
     */
    function getSubmitParam(operateType) {
        var res = {};
        var object222 = getSearchParams();
        var object = $.serializeToObject('#submitForm');
        var object111 = $.serializeToObject('#submitForm2');
        var objectss = new Array();
        if (table != null && table != '') {
            var selArr = table.getAllRows();
            for(var index in selArr){
            	var item = selArr[index];
                var temp = JSON.parse(JSON.stringify(object));
                temp.windtypeone = item.windtypeone;
                temp.windtypetwo = item.windtypetwo;
                temp.mGradeResult = item.mGradeResult;
                temp.secGradeResult = item.secGradeResult;
                temp.isSecBond = item.isSecBond;
                temp.isRight = item.isRight;
                temp.isPpb = item.isPpb;
                temp.pledgeStockId = object222.pledgeStockId;
                temp.elementId = 'EtrIbRepoRk_btn_submit';
                objectss.push(temp);
            }
        }
        
        var treeObj=$.fn.zTree.getZTreeObj("typeDatatree_tree"),
		nodes=treeObj.getCheckedNodes(true),
		datas = "";
        var objects = new Array();
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].check_Child_State==-1){
				/*datas+=nodes[i].id + ",";*/
				var temp = JSON.parse(JSON.stringify(object));
	            temp.portfolioId = nodes[i].id;
	            temp.pledgeStockId = object222.pledgeStockId;
	            temp.elementId = 'EtrIbRepoRk_btn_submit';
				objects.push(temp);
			}
		}
        
        object.operateType = operateType;
        object.pageModel = pageModel;
        var param = {};
        param = JSON.parse(JSON.stringify(object));
        param.ptlSecStock = objects;
        param.etrSecStockConfig = objectss;
        param.operateType = operateType;
        param.pledgeStockId = object222.pledgeStockId;
        param.pageModel = pageModel;
        param.elementId = 'EtrIbRepoRk_btn_submit';
        res.param = param;
        res.objects = objects;
        res.objectss = objectss;
        return res;
    }
	
	function saveOrSubmit(operateType) {
		var res = getSubmitParam(operateType);
		Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtrIbRepoRk/checkq', res.param, function(result) {
	        Joyin.confirm(JoyinUtil.getMessage("请确认是否要操作？",JoyinUtil.getLabel("提交")),function() {
	        	WorkFlow.doSubmitList(basePath+'invest/entrust/etribrepo/EtrIbRepoRk/save',res.param,"EtrIbRepoRk_btn_submit","EtrIbRepoRk");
	        	loadTree();
	        	table.clear();
	        });
		});
    };
    
	$('#EtrIbRepoRk_btn_submit').click(function() {
        saveOrSubmit('submit');
    });
    $('#EtrIbRepoRk002_btn_reset').click(function() {
    	reset();
    });
    
    function reset() {
    	loadTree();
        table.clear();
    };
    //获取查询参数
    var getSearchParams = function () {
        return {pledgeStockId: nodeId};
    };
    //树加载
    loadTree();
    //鼠标放置显示操作列效果
    $("body").on('mouseover', ".mixtree-node", null, function () {
        $(".operate_div").hide();
        $(this).find(".operate_div").css({display: 'inline-block'});
    })
    //移开鼠标隐藏操作列效果
    $("body").on('mouseout', ".mixtree-node", null, function () {
        $(".operate_div").hide();
    })
    //点击申报方案事件
    $("body").on('click', ".mixtree-node", null, function () {
        nodeId = $(this).attr('nodeid');
//        nodeName = $(this).prop('innerText');
        table.reload();
        serachKey1();
        //点击选中后显示背景色
        $(".mixtree-node").css({'background-color': ''})
        $(this).css({'background-color': '#D9ECFF'})
    });
    //系列名称删除事件
    $("body").on('click', ".node_delete", null, function () {
        var thisNodeId = $(this).attr('nodeid');
        //获取该删除节点的参数
        var getData=function(){
            return {pledgeStockId:thisNodeId}
        }
        var object = getData();
        object.operateType = "delete";
        //用于判断是否是系列名称删除
        object.flag = true;
        Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtrIbRepoRk002/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/entrust/etribrepo/EtrIbRepoRk/delete', object, 
                        "EtrIbRepoRk_btn_delete", "EtrIbRepoRk002",function() {
                    loadTree();
                    table.clear();
                });
            });
        });
    })
    /**
     * 定义数据表格
     */
    var cols = [
        {field: 'pledgeStockId', title: '质押库代码',visible: false}
        ,{field:'windtypeone',  title: '资产一级分类',edit: true,
        	editOption: {
                type: 'select', id: 'windtypeoneSelect', multiple: false, searchable: true,
                result: CUSTOM_ASSET_TYPE_TWO,childrenIds: 'windtypetwoSelect'
        	},sortable: false
        },
        {field:'windtypetwo',  title: '资产二级分类',edit: true,
        	editOption: {
                type: 'select', id: 'windtypetwoSelect', multiple: false, searchable: true,
                result: CUSTOM_ASSET_TYPE_THREE,parentIds: 'windtypeoneSelect',
        	},sortable: false
        },
        {field:'mGradeResult',  title: '主体评级',edit: true,
        	editOption: {
                type: 'select', id: 'mGradeResultSelect', multiple: false, searchable: true,
                result: MAIN_GRADE_RESULT
        	},sortable: false
        },
        {field:'secGradeResult',  title: '债项评级',edit: true,
        	editOption: {
                type: 'select', id: 'secGradeResultSelect', multiple: false, searchable: true,
                result: MAIN_GRADE_RESULT
        	},sortable: false
        },
        {field:'isSecBond',  title: '是否次级',edit: true,
        	editOption: {
                type: 'select', id: 'isSecBondSelect', multiple: false, searchable: true,
                result: YES_OR_NO
        	},sortable: false
        },
        {field:'isRight',  title: '是否含权',edit: true,
        	editOption: {
                type: 'select', id: 'isRightSelect', multiple: false, searchable: true,
                result: YES_OR_NO
        	},sortable: false
        },
        {field:'isPpb',  title: '是否私募',edit: true,
        	editOption: {
                type: 'select', id: 'isPpbSelect', multiple: false, searchable: true,
                result: YES_OR_NO
        	},sortable: false
        }
        , {field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['delt']}
        ]
    var table = $("#etrSecStockConfig").joyintable({
    	ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        cols: cols,
        page: true,
        addbtn: true,
        addToLast: true,
        searchOnInit: true,
        checkbox: false,
        addclick: function (row) {

        },
        editCell: function (value, data, field, cell, column) {
        	
        }
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function (data, index) {
    	Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){//删除事件的请求
    		table.removeRow(data);
    	});
    });
    //绑定系列名称新建按钮事件
    $('#EtrIbRepoRk_btn_creat').click(function () {
        Joyin.dialog(JoyinUtil.getLabel("质押库新建"),
            basePath + "invest/entrust/etribrepo/EtrIbRepoRk002/init",
            "50%", "75%")
    });
    //“列表信息”上下箭头展示事件 '.icon-downarrow',  '.icon-uparrow'
    $("body").on('click','.mixtree-head', function () {
        if($(this).find('.icon-downarrow').length > 0) {
            $(this).find('.icon-downarrow').removeClass('icon-downarrow').addClass('icon-uparrow');
            $(".tree_node_div").toggle();
            return;
        }
        if($(this).find('.icon-uparrow').length > 0) {
            $(this).find('.icon-uparrow').removeClass('icon-uparrow').addClass('icon-downarrow');
            $(".tree_node_div").toggle();
            return;
        }

    });
    //绑定新建按钮
    $('#EtrIbRepoRk_btn_select').click(function() {
    	top.modelSelectParam =null;
        /*Joyin.dialog(JoyinUtil.getLabel("筛选"),
                basePath+ "invest/entrust/etribrepo/EtrIbRepoRk005/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_NEW});*/
        Joyin.dialog(JoyinUtil.getLabel("筛选"),
                JoyinUtil.getRealPath("invest/entrust/etribrepo/EtrIbRepoRk005/init"),
                "98%", "90%",{isModelSelect:"yes"},function(result){
                	modelSelectParam(top.modelSelectParam);
                }
        );
    });
    
    function modelSelectParam(param){
    	if(param.portfolioId){
    		serachKey11(param);
    	}else{
    		serachKey12(param);
    	}
    };
    
    function serachKey12(param){
        $('#typeDatatree').reloadTree({
        	pledgeStockId: "select",
        	profitType: param.profitType,
        	openType: param.openType,
        	raiseType: param.raiseType,
        	investNature: param.investNature
        });
    }
    
    function serachKey11(param){
        $('#typeDatatree').reloadTree({
        	pledgeStockId: "select",
        	portfolioId: param.portfolioId.join(","),
        	profitType: param.profitType,
        	openType: param.openType,
        	raiseType: param.raiseType,
        	investNature: param.investNature
        });
    }
    
    $('#EtrIbRepoRk005005_btn_searchq').click(function() {
    	var modelSelectParam =null;
        var object = $.serializeToObject('#basicInfoForm');
		  modelSelectParam = object;
		  Joyin.closeTopWindow();
		  serachKey2();
    });

    $('#EtrIbRepoRk_btn_sele').click(function () {
    	/*var data1 = $.serializeToObject('#submitForm2');
        data1.portfolioId = data1.portfolioId ? data1.portfolioId.join(','):'';
    	$("#typeDatatree").load(basePath +'invest/entrust/etribrepo/EtrIbRepoRk/getInterGroup',data1, function (data) {
    		
    	})*/
    	if($("#portfolioId").val()){
    		serachKey();
    	}else{
    		serachKey2();
    	}
    });
    
    function serachKey2(){
        $('#typeDatatree').reloadTree({
        	portfolioId: null,
        	pledgeStockId: "select"
        });
    }
    
    function serachKey(){
        $('#typeDatatree').reloadTree({
        	portfolioId: $("#portfolioId").val().join(","),
        	pledgeStockId: "select"
        });
    }
    
    function serachKey1(){
        $('#typeDatatree').reloadTree({
        	pledgeStockId: getSearchParams().pledgeStockId
        });
    }
    $('#typeDatatree').joyinMixTree({
        onTreeLoadSuccess: function() {
            $.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(true);
            $.fn.zTree.getZTreeObj('typeDatatree_tree').checkAllNodes(true);
        }
    });

    function expandAll() {
        $.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(!$('#expandAll').is(':checked'));
    };

    var tree=$("#typeDatatree");
    var docH= $(document).height();
    var tabHeadUlH=100;
    tree.children('div').eq(1).height((docH-tabHeadUlH)+"px");
    tree.children('div').eq(1).css("overflow-x", "hidden");
}());