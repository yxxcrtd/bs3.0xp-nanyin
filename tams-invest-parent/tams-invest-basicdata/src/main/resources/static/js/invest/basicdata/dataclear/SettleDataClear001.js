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
        var portfolioId = $('#portfolioId').val();
        if(_.isArray(portfolioId)) {
            portfolioId = portfolioId.join();
        }
        result.portfolioId = portfolioId;
        result.dealDate = $('#execDate').val();
        result.taskResult = taskResult;
        return result;
    };

    var getTreeParams = function(){
        return {
            fileName: $('#fileName').val()
        }
    }

    $('#typeDatatree').joyinMixTree({
        onTreeLoadSuccess: function() {
            $.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(false);
            $.fn.zTree.getZTreeObj('typeDatatree_tree').checkAllNodes(true);
        }
    });

    function expandAll() {
        $.fn.zTree.getZTreeObj('typeDatatree_tree').expandAll(!$('#expandAll').is(':checked'));
    }

    var docH= $(document).height();
    function createTable(tableId, searchOnInit) {
        $('#'+tableId).joyintable({
            ajax: {
                url: 'findLogPage',
                type: 'POST',
                data : function(){
                    return getSearchParams();
                }
            },
            height:docH-160,
            checkbox: false,
            searchOnInit: searchOnInit,
            cellMinWidth : 100,
            rowno:true,
            cols:[
                {field:'dealDate', sortField: 'DEAL_DATE', title: '业务日期', width:120,type:'date'},
                {field:'portfolioId',sortField: 'PORTFOLIO_ID', title: '组合代码',width:160},
                {field:'secTypeName', title: '任务明细',width:160, sortField:'SEC_TYPE'},
                {field:'taskResult', title: '执行状态',width:105, sortField: 'TASK_RESULT',
                    render: function (data, type, row, meta) {
                        if(data=='NOR') {
                            return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#17c347;color:#fff">'+'成功'+'</span>';
                        } else if(data=='EXP') {
                            return '<span class="layui-badge-rim" style="width:55px;height:20px;background-color:#ff2236;color:#fff">'+'失败'+'</span>';
                        } else if(data=='WARN') {
                            return '<span class="layui-badge-rim layui-bg-orange" style="width:55px;height:20px;">'+'警告'+'</span>';
                        }
                    }},
                {field:'execMsg', title: '数据记录', sortable: false},
                {field:'createTime', sortField:'CREATE_TIME', type:'date', title: '操作日期',width:120},
                {field:'lastTime', title: '耗时(s)',width:100, sortable: false},
                {field:'startTimeFmt',sortField:'START_TIME', title: '开始时间',width:120},
                {field:'endTimeFmt',sortField:'END_TIME',title: '结束时间',width:120}]
        });
    }

    createTable('log-table', true);
    createTable('success-table', false);
    createTable('error-table', false);
    createTable('warning-table', false);

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
        var tableId = id.replace('title','table');
        var tabTable = $('#'+tableId).data('joyintable');
        if(!tabTable.table) {
            tabTable.load();
        }else {
            tabTable.reload();
        }
    };


    //轮询开始
    var startInterval = function(){
        //每5秒进行一次轮序
        interId = setInterval(function () {
            mainTable($('ul.layui-tab-title li.layui-this'));
        },time);
    };

    //轮询结束
    var stopInterval = function(){
        clearInterval(interId);
    }

    /**
     * 执行
     */
    var doExec = function() {
    	var execDate = $('#execDate').val();
    	if(!execDate || execDate == ''){
            Joyin.error('请选择日期！');
            return ;
        }
        var treeObj= $.fn.zTree.getZTreeObj("typeDatatree_tree"),
            nodes = treeObj.getCheckedNodes(true),
            datas = "";
        for(var i=0; i<nodes.length; i++){
            if(nodes[i].check_Child_State==-1){
                datas+=nodes[i].id + ",";
            }
        }
        if(datas==''){
            Joyin.error('请选择导入的数据文件！');
            return ;
        }
        var portfolioId = $('#portfolioId').val();
        if(_.isArray(portfolioId)) {
            portfolioId = portfolioId.join();
        }
        Joyin.ajax('execute',
            {
                datas : datas,
                portfolioId: portfolioId,
                execDate: $('#execDate').val()
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
        $('ul.layui-tab-title li').click(function(){
            mainTable(this);
        });

        // 绑定查询按钮事件
        $('#SettleDataClear001_btn_searchBtn').click(function() {
            if (interId != '') {
                stopInterval();
            }
            var execDate = $('#execDate').val();
            if(!execDate){
                Joyin.error('请选择日期！');
                return ;
            }
            mainTable($('ul.layui-tab-title li.layui-this'));
        });
        // 绑定执行按钮事件
        $('#SettleDataClear001_btn_doExecBtn').click(function() {
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
