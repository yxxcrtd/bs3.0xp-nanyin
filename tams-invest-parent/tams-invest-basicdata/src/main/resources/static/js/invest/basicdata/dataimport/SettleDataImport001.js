$(function () {
    var excuteDate;
    //轮询间隔事件
    var time = 20000;

    var taskResult = "";

    var interId = "";

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var result = {};
        result.portfolioId = $('#portfolioId').val();
        result.importDate = $('#execDate').val();
        result.taskResult = taskResult;
        return result;
    };

    var getTreeParams = function(){
        return {
            fileName: $('#fileName').val()
        }
    }

    $('#typeDatatree').joyinMixTree({
        setting: {
        	callback: {
        		onCheck: function(event, treeId, treeNode) {
        			if (treeNode.checked) {
        				// 获取选中节点的根目录的父ID
        				var rootId = treeNode.id.substr(treeNode.id.length-8);
        				var nodes = $.fn.zTree.getZTreeObj("typeDatatree_tree").getCheckedNodes(true);
        				for(var i = 0; i < nodes.length; i++){
        		            if (!nodes[i].pId && nodes[i].id != rootId) {
        		            	$.fn.zTree.getZTreeObj("typeDatatree_tree").checkNode(nodes[i], false, true);
        		            }
        		        }
        				// 将日期设置成选中节点的根目录的父ID的日期
        				$('#execDate').val(rootId.substring(0,4) + '-' + rootId.substring(4,6) + '-' + rootId.substring(6));
        			}
        		}
        	}
        },
    	onTreeLoadSuccess: function() {
            //$.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(false);
        	var treeObj = $.fn.zTree.getZTreeObj("typeDatatree_tree");
        	var nodes = treeObj.getCheckedNodes(true);
        	if (nodes.length > 0) {
        		treeObj.expandNode(nodes[0], true, true, true);
        	}
        	$("#typeDatatree_rMenu").remove();
        }
    });

//    function expandAll() {
//        $.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(!$('#expandAll').is(':checked'));
//    };


    /**
     * 日志页签
     */
    var mainTable = function(obj) {

        var id = $(obj).attr("id");
        if(!id){
            id='log-title';
        }

        if(id=='success-title'){
            taskResult = 'NOR';
        }else if(id=='error-title'){
            taskResult = 'EXP';
        }else if(id=='warning-title'){
            taskResult = 'WARN';
        } else {
            taskResult = '';
        }
        //根据title获取table
        var table = id.replace('title','table');

        var docH= $(document).height();


        var tabTable = $('#'+table).joyintable({
            ajax: {
                url: 'findLogPage',
                type: 'POST',
                data : function(){
                    return getSearchParams();
                }
            },
            height:docH-160,
            checkbox: false,
            cellMinWidth : 100,
            rowno:true,
            cols:[ {field:'importDateFmt', sortable:false, title: '业务日期', width:120},
                {field:'portfolioId', sortField: 'PORTFOLIO_ID', title: '组合代码',width:160},
                {field:'dataFileName', sortField: 'DATA_FILE_NAME', title: '数据文件',width:160},
                {field:'taskResult', sortField: 'TASK_RESULT', title: '执行状态',width:105,
                    render: function (data, type, row, meta) {
                        if(data=='NOR') {
                            return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#17c347;color:#fff">'+'成功'+'</span>';
                        } else if(data=='EXP') {
                            return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#ff2236;color:#fff">'+'失败'+'</span>';
                        } else if(data=='WARN') {
                            return '<span class="layui-badge-rim layui-bg-orange" style="width:55px;height:20px;">'+'警告'+'</span>';
                        }
                    }},
                {field:'execMsg',sortable:false, title: '数据记录',width:160},
                {field:'createTimeFmt',sortField:"CREATE_TIME", title: '操作日期',width:120},
                {field:'lastTime',sortable:false, title: '耗时(s)',width:100},
                {field:'startTimeFmt',sortable:false, title: '开始时间',width:120},
                {field:'endTimeFmt',sortable:false,title: '结束时间',width:120}]
        });
        //tabTable.load();
        if(!tabTable.table) {
            tabTable.load();
        }else {
            tabTable.reload();
        }
    };


    //轮询开始
    var startInterval = function(){
        //每5秒进行一次轮序
        interId = setInterval(mainTable,time);
    };

    //轮询结束
    var stopInterval = function(){
        clearInterval(interId);
    }

    /**
     * 执行
     */
    var doExec = function() {
        var treeObj= $.fn.zTree.getZTreeObj("typeDatatree_tree"),
            nodes = treeObj.getCheckedNodes(true),
            datas = "";
        for(var i=0; i<nodes.length; i++){
            if(nodes[i].check_Child_State==-1){
                datas+=nodes[i].id + ",";
            }
        }
        if(datas==''){
            layer.alert('请选择导入的数据文件！');
            return ;
        }
        Joyin.ajax('execute',
            {
                datas : datas,
                portfolioId: $('#portfolioId').val()
            },
            function(data) {
                var result = JSON.parse(data);
                if(result.isCheck==1){
                    //再次执行启动前停止上一次轮询
                    stopInterval();
                    //调用后一秒再查询接口,快速展示界面数据
                    startInterval();
                }else if(result.isCheck==0){
                    layui.layer.alert(result.checkMsg);
                }else{
                    layui.layer.alert("发送启动请求失败");
                }
            }
        );
    }

    /**
     * 画面加载完成
     */
    $(function() {
        //日志页签点击事件
        $('#log-title').click(function(){
            mainTable(this);
        });

        $('#success-title').click(function(){
            mainTable(this);
        });
        $('#error-title').click(function(){
            mainTable(this);
        });
        $('#warning-title').click(function(){
            mainTable(this);
        });

        // 绑定查询按钮事件
        $('#SettleDataImport001_btn_searchBtn').click(function() {
             if (interId != '') {
                 stopInterval();
             }
            mainTable($('#log-title'));
        });
        // 绑定执行按钮事件
        $('#SettleDataImport001_btn_doExecBtn').click(function() {
            doExec();
        });



        var tree=$("#typeDatatree");
        var docH= $(document).height();
        var tabHeadUlH=100;
        tree.children('div').eq(1).height((docH-tabHeadUlH)+"px");
        tree.children('div').eq(1).css("overflow-x", "hidden");
        //startInterval();
    });
});


function serachKey(){
    $('#typeDatatree').reloadTree({
        fileName: $('#fileName').val()
    });
}
