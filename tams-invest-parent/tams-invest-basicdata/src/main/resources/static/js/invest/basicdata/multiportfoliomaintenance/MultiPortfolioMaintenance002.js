(function() {
    function searchTreeSingle() {
        $('#treeIndPortf').joyinMixTree({
            onTreeLoadSuccess: function () {
                $("#treeIndPortf_tree").closest('div').css("height",$('#treeIndPortf').height());
                if(pageModel == PAGE_MODEL_REFERENCE) {
                    var treeObj = $.fn.zTree.getZTreeObj("treeIndPortf_tree");
                    var nodes = treeObj.getNodes();
                    for(var i=0; i < nodes.length;i++) {
                        var node = nodes[i];
                        DisabledNode(treeObj, node);
                    }

                }
                if(pageModel ==PAGE_MODEL_NEW){
                    $.fn.zTree.getZTreeObj("treeIndPortf_tree").setting.callback.onClick=function(event, treeId, treeNode){
                        event.stopPropagation();
                        if(treeNode==null){
                            $("div.mixtree-div").append('<a class="refreshAicon" ><i class="iconfont icon-found"></i>&nbsp;新建分类</a>')
                        }
                        if(treeNode.level==1){
                            $('#indId').val(treeNode.id);
                            $('#indName').val(treeNode.name);
                            //框架中有800毫秒限制，按钮点击太快会有问题
                            table.load();
                        }
                    };//触发函数

                    //绑定组合刷新事件
                    $("div.mixtree-div").find('a.refreshAicon').click(function(){
                        event.stopPropagation();
                        $("#indType").trigger('change');
                    });
                }
            }
        });
    }
    function DisabledNode(treeObj, node) {
        treeObj.setChkDisabled(node, true);
        if(node.isParent) {
            var children = node.children;
            for(var i=0; i < children.length; i++) {
                DisabledNode(treeObj, children[i]);
            }
        }
    };
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            treeId: $('#indId').val(),
        }
    };
    /**
     * 定义单组合数据表格
     */
    var table=$("#dataIndTable").joyintable({
        ajax: {
            url:  basePath + "invest/basicdata/multiportfoliomaintenance/MultiPortfolioMaintenance001/findPage",
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint004/init",
                "70%", "90%", data);
        },

        cols:[
            {field:'seqNo', width:150 , visible: false}
            ,{field:'portfolioId', width:200, title: '组合代码' ,sortable: true}
            ,{field:'portfolioName', width:200, title: '组合名称',sortable: true}
            ,{field:'acctNo', width:200, title: '资金账户',sortable: true}
            ,{field:'effDate', width:200, title: '生效日',sortable: true,type:'date'}
        ]
    });

    /**
     * 绑定指标名称事件
     */
    table.bind('click', '.indName', function(data, index) {
        data.pageModel = PAGE_MODEL_REFERENCE;
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
            basePath + "invest/riskredis/quota/superviseindicator/RskSupervisionIndicator002/init",
            "70%", "90%",data,function() {
                table.reload();
            });
    })

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint004/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("RskSupervisionPortfPoint004"),
                basePath + "invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint004/init",
                "70%", "90%",data,function() {
                    table.reload();
                });
        });
    });

    /**
     * 绑定设置事件
     */
    table.bind('click', '.set', function(data, index) {
        data.pageModel = PAGE_MODEL_NEW;
        Joyin.dialog(JoyinUtil.getLabel("RskSupervisionIndividuation001"),
            basePath + "invest/riskredis/quota/supervisionindividuation/RskSupervisionIndividuation001/init",
            "90%", "90%",data,function() {
                table.reload();
            });
    })

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var rskSupervisionIndicator002DtoList = [];
        var rskSupervisionIndicator002Dto = {indId:data.indId,portfolioId:data.portfolioId};
        rskSupervisionIndicator002DtoList[0] = rskSupervisionIndicator002Dto;
        data.rskSupervisionIndicator002DtoList = rskSupervisionIndicator002DtoList;
        data.opeType = "delete";
        var object = {selectedItem: [data],treeFlag:"A",indId:$("div.condition-area").find('#indId').val(),portfolioId:data.portfolioId,operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint001/check', object, function(result) {//请求路径，请求参数回调函数
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                // mody by zhuzhen 20191128 组合关联指标删除时校验联合设置 start
                Joyin.ajaxJson(basePath + 'invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint001/checkDelate', object, function(result) {
                    WorkFlow.doDeleteList(basePath + 'invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint001/delete', object,
                        "MultiPortfolioMaintenance001_btn_delete","RskSupervisionPortfPoint004",function() {
                            table.reload();
                        });
                });
                // mody by zhuzhen 20191128 组合关联指标删除时校验联合设置 end
            });
        });
    });
    /**
     * 弹出新建画面
     * @returns
     */
    function openNew() {
        var indId="",indName="";
        var treeObj = $.fn.zTree.getZTreeObj("treeIndPortf_tree");
        var nodes = treeObj.getSelectedNodes();

        $.each(nodes,function(i,v){
            if(v.level==1){
                indId=v.id;
                indName=v.name;
            }
        })
        if(nodes.length==0||indId==""){
            Joyin.error(JoyinUtil.getMessage("RSKRDS_W_0017",JoyinUtil.getLabel("RskSupervisionPortfPoint001.indicator")));
            return;
        }
        Joyin.dialog(JoyinUtil.getLabel("RskSupervisionPortfPointInd002"),
            JoyinUtil.getRealPath("invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPointInd002/init"),
            "70%", "90%",{pageModel : PAGE_MODEL_NEW,indId:indId,indName:indName}
        );
    }

    /**
     * 画面加载完成
     */
    $(function() {
        var jqWin = $(window);
        var winH = jqWin.height();
        console.log('winH=' + winH);

        var jqReport = $('#treeIndPortf');
        var reportTop = jqReport.offset().top;
        var reportH = winH - reportTop-70;
        jqReport.height(reportH);
        jqWin.resize(function() {
            var winH = jqWin.height();
            var reportH = winH - reportTop-70;
            jqReport.height(reportH);
        });
        //初始化，产生树结构
        var indType= $("#indType").val();
        $("#treeIndPortf").attr('url',getAllRelationUrl+'?type='+indType)
        searchTreeSingle();


        // 绑定按钮事件
        $('#MultiPortfolioMaintenance001_btn_search').click(function() {
            table.load();
            $('#MultiPortfolioMaintenance001_btn_delete').show();
        });
        $('#MultiPortfolioMaintenance001_btn_add').click(function() {
            openNew();
        });
        $('#MultiPortfolioMaintenance001_btn_delete').click(function() {
            var selArr = table.getAllCheckedRows();
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            for(var i=0;i<selArr.length;i++){
                var rskSupervisionIndicator002DtoList = [];
                var rskSupervisionIndicator002Dto = {indId:selArr[i].indId,portfolioId:selArr[i].portfolioId};
                rskSupervisionIndicator002DtoList[0] = rskSupervisionIndicator002Dto;
                selArr[i].rskSupervisionIndicator002DtoList = rskSupervisionIndicator002DtoList;
                selArr[i].opeType = "delete"
            }
            var object = {selectedItem: selArr, treeFlag:"A",indId:$("div.condition-area").find('#indId').val(),operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint001/check', object, function(result) {//请求路径，请求参数回调函数
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    // mody by zhuzhen 20191128 组合关联指标删除时校验联合设置 start
                    Joyin.ajaxJson(basePath + 'invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint001/checkDelate', object, function(result) {
                        WorkFlow.doDeleteList(basePath + 'invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint001/delete', object,
                            "MultiPortfolioMaintenance001_btn_delete","RskSupervisionPortfPoint004",function() {
                                table.reload();
                            });
                    });
                    // mody by zhuzhen 20191128 组合关联指标删除时校验联合设置 end
                });
            });
        });
        //指标类型联动事件
        $("#indType").change(function(){
            var indType=$(this).val();
            $("#treeIndPortf").css('height','670px')
            $("#treeIndPortf").attr('url',getAllRelationUrl+'?type='+indType)
            searchTreeSingle();
        })



    });
}());

function serachKey(){
    $('#treeIndPortf').reloadTree({
        treeName: $('#treeName').val()
    });

}