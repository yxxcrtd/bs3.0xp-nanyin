(function() {

    var settingSource1 = {
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
            onCheck: zTreeOnCheck1
        }
    };

    //oncheck触发事件
    function zTreeOnCheck1(event, treeId, treeNode) {
        var childstr =getAllChildrenNodes(treeNode,"");
        var parentstr=getAllParentNodes(treeNode,"");
        disableNodes(childstr+parentstr,treeNode.checked);
        loadSubClass();
    };

    var buttons = [{ text: '取消映射',ignore:true, clas: 'delt', title: '取消映射' }];
    /**
     * 加载源科目树
     */
    var loadTreeSource = function(){
        var param = {};
        param.ptlName = $("#ptlName").val();
        param.ptlId = $("#ptlId").val();
        Joyin.ajaxJson(basePath + gettreeSourceUrl,param,
        function(result){
            $.fn.zTree.init($("#treeSource"),settingSource1,result);
            //筛选源科目代码是否已配置
            // fiterSrcCode();
        });
     }
    var loadSubClass = function() {
        var treeSourceObj = $.fn.zTree.getZTreeObj("treeSource");
        var subClassArr = "";
        if(null!=treeSourceObj){
            var sourceNodes = treeSourceObj.getCheckedNodes(true);
            for (var i = 0; i < sourceNodes.length; i++) {
                if(sourceNodes[i].pId && sourceNodes[i].pId!=''){
                    if(subClassArr.indexOf("sourceNodes[i]")<0){
                        subClassArr +=sourceNodes[i].pId+",";
                    }
                }
            }
            if(subClassArr!=""){
                subClassArr = subClassArr.substr(0,subClassArr.length-1);
            }
        }
        $("#subClass").val(subClassArr).trigger('blur');
    };

     var fiterSrcCode = function(){
        var list = table.getAllRows();
        var childstr ='' ;var parentstr='';
        if(list){
            var treeSourceObj = $.fn.zTree.getZTreeObj("treeSource");
            $.each(list, function (i, item) {
                var src_code = item.subCode;
                var node = treeSourceObj.getNodeByParam("id", src_code, null);
                childstr+=","+src_code;
                childstr =getAllChildrenNodes(node,childstr);
                parentstr=getAllParentNodes(node,parentstr);
            });
        }
        refreshInitTree(childstr+parentstr,true);
    }
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {ptlId : $("#ptlId").val()};
    };
    /**
     * 定义数据表格
     */

    var table=$("#dataTable").joyintable({
        ajax: {
            url: basePath +'invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset004/selectMapSubjectInfo',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        //page:false,
        searchOnInit: true,
        /**
         * 定义表头
         */
        cols:[
            {field: 'ptlId',visible: false}
            ,{field: 'subCode', title:'RskValtblPrtBotasset001.subCode',width:100}
            ,{field: 'subName', title:'RskValtblPrtBotasset001.subName',width:100}
            ,{field: 'subClass', title: 'RskValtblPrtBotasset001.subClass',width:100,visible: false}
            ,{field: 'subClassName', title: 'RskValtblPrtBotasset001.subClass',width:100}
            ,{field: 'finprodId', title: 'RskValtblPrtBotasset001.finprodId',width:100}
            ,{field: 'finprodAbbr', title: 'RskValtblPrtBotasset001.finprodAbbr',width:100}
            ,{field: 'finprodMarketId', title: 'RskValtblPrtBotasset001.finprodMarketId',width:100}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: buttons}
        ]
    });

    /**
     * 绑定取消映射事件
     */
    table.bind('click', '.delt', function(data, index) {
        Joyin.confirm("请确认是否要取消映射?",function() {
            Joyin.ajaxJson(basePath + '/invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset004/cancelApplyMapForSubject',data,function () {
                table.reload();
                loadTreeSource();
                $("#finprodId").val('').trigger('change');
            });
        });
    });
    /**
     * 映射&应用
     */
    var mapAndApply = function(){
        var finprodId = $('#finprodId').val();
        if(!finprodId || finprodId==''){
            Joyin.alert("请选择资产代码",2);
            return;
        }
        var object = {};
        var treeSourceObj = $.fn.zTree.getZTreeObj("treeSource");
        var sourceArr = new Array();
        if(null!=treeSourceObj){
            var sourceNodes = treeSourceObj.getCheckedNodes(true);
            for (var i = 0; i < sourceNodes.length; i++) {
                if(!sourceNodes[i].pId ||sourceNodes[i].pId==''){
                    Joyin.alert("请选择子节点进行映射！",2);
                    return;
                }
                sourceArr.push(sourceNodes[i].id);
            }
        }
        if(!sourceArr || sourceArr.length!=1){
            Joyin.alert("请选择一条资产科目进行映射！",2);
            return;
        }
        object.subCode = sourceArr.join(",");
        object.ptlId=$('#ptlId').val();
        object.finprodId=$('#finprodId').val();
        Joyin.confirm("请确认是否要操作?",function() {
            Joyin.ajaxJson(basePath + '/invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset004/applyMapForSubject',object,function () {
                table.reload();
                loadTreeSource();
                $("#finprodId").val('').trigger('change');
            });
        });
    }
    /**
     * 画面加载完成
     */
    $(function() {
        //加载树形资产科目
        loadTreeSource();
        
        //映射&应用
        $('#RskValtblPrtBotasset004_btn_add').click(function(){
            mapAndApply();
        });
    });
}());