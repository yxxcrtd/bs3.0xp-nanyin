var settingSource = {
		view: {
			dblClickExpand: false  ,
			showIcon: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		check: {  
			enable: true,
			chkboxType: { "Y": "", "N": "" }
        },
		callback: {
			beforeCollapse:zTreeBeforeCollapse,
			onCheck: zTreeOnCheck
		}
	};
var settingTarget = {
		view: {
			dblClickExpand: false  ,
			showIcon: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		check: {  
			enable: true,
			chkStyle: "radio",
			 radioType: "all",
			chkboxType: { "Y": "", "N": "" }
        },
		callback: {
			beforeCollapse:zTreeBeforeCollapsetwo
		}
	};
function zTreeBeforeClick(treeId, treeNode, clickFlag) {
    return !treeNode.isParent;//当是父节点 返回false 不让选取
};

function zTreeBeforeCollapse(treeId, treeNode) 
{
	return true//展开全部数据
};
function zTreeBeforeCollapsetwo(treeId, treeNode) 
{
	return false//展开全部数据
};
//oncheck触发事件
function zTreeOnCheck(event, treeId, treeNode) {
	var childstr =getAllChildrenNodes(treeNode,"");
    var parentstr=getAllParentNodes(treeNode,"");
    disableNodes(childstr+parentstr,treeNode.checked);
};

//禁用选中节点的上下节点
function disableNodes(v,flag){
	var treeObj = $.fn.zTree.getZTreeObj("treeSource");
	if(treeObj!=null){
		var arrId = v.split(",");
		for(var i=0;i<arrId.length;i++){
			if(arrId[i]!=0){
				var node = treeObj.getNodeByParam("id", arrId[i], null);
				if(node!=null){
					treeObj.setChkDisabled(node, flag);
					treeObj.updateNode(node);
				}
			}
		}
	}
}
//获取节点所有子节点
function getAllChildrenNodes(treeNode,result){
	if(treeNode!=null){
		if (treeNode.isParent) {
		      var childrenNodes = treeNode.children;
		      if (childrenNodes) {
		          for (var i = 0; i < childrenNodes.length; i++) {
		              result += ',' + childrenNodes[i].id;
		              result = getAllChildrenNodes(childrenNodes[i], result);
		          }
		      }
		  }
	}
  return result;
}
//获取节点所有父节点
function getAllParentNodes(treeNode,result){
	  if(treeNode!=null){
		  var pNode = treeNode.getParentNode();
	      if (pNode!=null) {
	              result += ',' + pNode.id;
	              result = getAllParentNodes(pNode, result);
	          }
	  }
      return result;
}

//初始化加载---刷新左侧源科目树 flag==true:隐藏  false:显示
function refreshInitTree(v,flag){
    
	var treeObj = $.fn.zTree.getZTreeObj("treeSource");
	if(treeObj!=null){
		var arrId = v.split(",");
		for(var i=0;i<arrId.length;i++){
			if(arrId[i]!=0){
			var node = treeObj.getNodeByParam("id", arrId[i], null);
			if(node!=null){
				treeObj.setChkDisabled(node, false);
				node.nocheck=flag;
				treeObj.updateNode(node);
			}
			}
		}
	}
}

//添加---刷新左侧源科目树
function refreshAddTree(){
	var treeObj = $.fn.zTree.getZTreeObj("treeSource");
	var nodes = treeObj.getCheckedNodes(true);
	for(var i = 0; i < nodes.length; i ++) {
		nodes[i].checked = false;
		nodes[i].nocheck=true;
		refreshInitTree(getAllChildrenNodes(nodes[i],"")+getAllParentNodes(nodes[i],""),true);
		treeObj.updateNode(nodes[i]);
	}
}

//修改---刷新左侧源科目树 flag: true表示新旧预案科目代码没变更 false表示已变更
function refreshEditTree(srcCodeold,flag){
	var treeObj = $.fn.zTree.getZTreeObj("treeSource");
	if(treeObj!=null){
	var nodes = treeObj.getCheckedNodes(true);
	for(var i = 0; i < nodes.length; i ++) {
		nodes[i].checked = false;
		nodes[i].nocheck=true;
		refreshInitTree(getAllChildrenNodes(nodes[i],"")+getAllParentNodes(nodes[i],""),true);
		treeObj.updateNode(nodes[i]);
	}
	if(!flag){
		var nodeOld = treeObj.getNodeByParam("id", srcCodeold, null);
		refreshInitTree(getAllChildrenNodes(nodeOld,"")+getAllParentNodes(nodeOld,""),false);
		nodeOld.nocheck=false;
		treeObj.updateNode(nodeOld);
	}
	}
}

//添加--刷新table
function refreshAddTable(){
	var mapId = $("#mapId").val();var addTbody = $("#addTbody");
	$.get(root + '/api/v1/SubjectCodeMaps/'+mapId+'/details', function(data) {
		IndexStructMap.buildMapDetail(addTbody, data,"Y");
	}, 'json');
}


//删除---刷新左侧源科目树
function refreshDeleteTree(v){
	var treeObj = $.fn.zTree.getZTreeObj("treeSource");
	if(treeObj!=null){
		var arrId = v.split(",");
		for(var i=0;i<arrId.length;i++){
			var node = treeObj.getNodeByParam("id", arrId[i], null);
			if(node!=null){
				refreshInitTree(getAllChildrenNodes(node,"")+getAllParentNodes(node,""),false);
				node.nocheck=false;
				treeObj.updateNode(node);
			}
		}
	}
}