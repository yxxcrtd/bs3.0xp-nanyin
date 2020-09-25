$(function () {
    $('#tree').joyinTree({

        onTreeLoadSuccess: function () {
            if(pageModel == PAGE_MODEL_NEW) {
                var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
                var nodes = treeObj.getNodes();
                for(var i=0; i < nodes.length;i++) {
                    var node = nodes[i];
                    DisabledNode(treeObj, node);
                }

            }
        }
    });
    function DisabledNode(treeObj, node) {
       // treeObj.setChkDisabled(node, true);
        if(node.isParent) {
            var children = node.children;
            for(var i=0; i < children.length; i++) {
                DisabledNode(treeObj, children[i]);
            }
        }
    }

    var resultArrlist = [];//最终选择指标的集合
    var selectArrlist = [];//当前选择指标的集合
    //加载已选指标值
    var parm={};
    parm.type=2;
    Joyin.ajaxSync(
        basePath + "invest/portfolio/ptlpositionanalysis/PtlPositionAnalysis002/getTableCols",parm,
        function(result) {
            if(result!="") {
                var dataTableCols = JSON.parse(result);
                for (var i = 0; i < dataTableCols.length; i++) {
                    $('#targetDiv').append("<div id=" + dataTableCols[i].field + " class=\"fiedldDiv\">" + dataTableCols[i].title + "</div>");
                    resultArrlist.push(dataTableCols[i].field);
                }
            }

        }
    );

    //移除

    $("#btn_remove").click(function () {

        if (selectArrlist != null && selectArrlist.length > 0)
        {

            for (var k = selectArrlist.length - 1; k >= 0; k--) {
                var selectValue=selectArrlist[k];
                if(selectValue=='FINPROD_MARKET_ID'||selectValue=='FINPROD_NAME'||selectValue=='POS_VALUE'||selectValue=='TDY_INTINCEXP')
                {
                    Joyin.error("默认指标不允许删除！");
                }
                else {
                    $("#" + selectArrlist[k] + "").remove();// 移除画面div
                    selectArrlist.splice($.inArray(selectValue, selectArrlist), 1);// 移除数组中指定元素
                    resultArrlist.splice($.inArray(selectValue, resultArrlist), 1);// 移除数组中指定元素
                }
            }
         }
         else
        {
            Joyin.error("请选择要移除的项！");
        }
    });
    //添加
    $("#btn_add").click(function () {

        var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
        var nodes = treeObj.getSelectedNodes();
        if(nodes.length>0)
        {
            for(var i=0; i < nodes.length; i++) {
                if($.inArray(nodes[i].id, resultArrlist) == -1)
                    {
                        $('#targetDiv').append("<div id="+nodes[i].id+" class=\"fiedldDiv\">"+ nodes[i].name+"</div>");
                        resultArrlist.push(nodes[i].id);
                    }
            }
        }

         else if(nodes.length==0)
         {
             Joyin.error("请选择需要添加的指标！");
         }
         else
         {
             Joyin.error("添加的指标已存在,请检查！");
         }
    });

    //选中指标操作
    $(document).on('click',".fiedldDiv",function () {
        var color= $(this).css("background-color");
        if(color.indexOf("255") >= 0) {
            $(this).css("background-color","");
            selectArrlist.splice($.inArray($(this).attr('id'),selectArrlist),1);// 移除数组中指定元素
        }
        else
        {
            $(this).css("background-color", "blue");
            selectArrlist.push($(this).attr('id'));
        }
    });


    //保存
    $("#save").click(function () {

        parm.msg=resultArrlist.join(",");
        Joyin.ajax(
            basePath + "invest/portfolio/ptlpositionanalysis/PtlPositionAnalysis003/savaTableCols",parm,
            function(result) {
                Joyin.alert(result.data);
                setTimeout(function(){
                    //点击保存后关闭窗口
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                },1000);

            }
        );
    });
}());
