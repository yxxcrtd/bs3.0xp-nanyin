(function() {
	/**
	 * 获取表单参数
	 */
	$('#tree').joyinTree({
		onTreeLoadSuccess: function () {
			if(pageModel == PAGE_MODEL_REFERENCE) {
				var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
				var nodes = treeObj.getNodes();
				for(var i=0; i < nodes.length;i++) {
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
				params.push(node.id);
			}
			getParam(node.getParentNode(), params);
		}
	}

	function saveOrSubmit() {
		var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
		var nodes = treeObj.getCheckedNodes();
		var params = new Array();
		for (var i = 0; i < nodes.length; i++) {
			var flag = getParam(nodes[i], params);
			// if(!flag){
			// 	Joyin.error("产品中包含“产品状态”为“已到期”或“已销户”的产品,不能提交");
			// 	return;
			// }
		}
		if(params.length == 0){
			Joyin.error("请选择产品");
			return;
		}
		parent.$('#finprodIds').data('finprodIds',params);
		var index = parent.layer.getFrameIndex(window.name);
		parent.layer.close(index);
		/*Joyin.ajaxJson(basePath + 'disclosure/information/config/saleagency/SaleAgency002/check', object, function(result) {
			Joyin.confirm(result.data, function() {
				if(operateType === 'save') {
					WorkFlow.doSaveList(basePath + 'disclosure/information/config/saleagency/SaleAgency002/save', object, "SaleAgency002_btn_save");
				}
				else {
					WorkFlow.doSubmitList(basePath + 'disclosure/information/config/saleagency/SaleAgency002/save', object, "SaleAgency002_btn_submit");
				}
			});
		});*/
	}

	/**
	 * 画面加载完成
	 */
	$(function() {
		$('#RepProdIndex003_btn_submit').click(function() {
			saveOrSubmit();
		});

		$('#expandAll').next().on('click',function(){
			expandAll();
		});

	});
}());