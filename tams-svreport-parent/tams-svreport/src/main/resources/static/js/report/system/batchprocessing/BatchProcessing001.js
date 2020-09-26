$(function() {
    //是否需要初始化
    var isNeedInit = true;
    //定时任务的id
    var taskId;
    //时间戳
    var fileId;
    var getPageParams = function() {
        return{
            batchDealProcess : $('#BatchDealProcess').val(),
            batchDealDateStart : $('#BatchDealDate').startDate(),
            batchDealDateEnd : $('#BatchDealDate').endDate(),
            fileId : fileId,
            isNeedInit: isNeedInit
        };
    }
    var table = $('#batchProcessing001').joyintable({
            ajax : {
                url : dealBatchProcessUrl,
                type : 'POST',
                data : function(d) {
                    return getPageParams();
                }
            },
            checkbox : false,
            page: false, // 不分页
            rowno : false, // 显示行号
            searchOnInit : false, // 初始化的时候就请求后台数据
            //回调函数
            done : function(){
                //隐藏表头
                $("thead").hide();
                //隐藏分页
                $(".layui-table-page").hide();
                var rows=table.getAllRows();
                for(var i=0; i<rows.length; i++){
                    if(rows[i].taskStatus == 'UNR'||rows[i].taskStatus =='RUN'){
                        return;
                    }
                }
                //批处理处理完成， 初始化画面进入就绪状态
                reSetPage();
            },
            even :false,
            cols : [
                       {field : 'batchId' ,visible : false},
                       {title : "任务名称", field : 'processName',width : 200},
                       {title : "处理日期", field : 'dealDate',type : 'date',width : 120},
                       {field:'taskStatus',visible : false},
                       {title : "处理状态",
                           width : 100,
                           render : function(data, type, row, meta) {
                               if(row.taskStatus == 'END'){
                                   return "<span class='debt-execution'>执行完成</span>";
                               }
                               else if (row.taskStatus == 'RUN'){
                                   return "<span class='debt-inProgress'>正在执行</span>";
                               }
                               else if(row.taskStatus == 'UNR'){
                                   return "<span class='debt-inProgress'>未执行</span>";
                               }
                           }
                       }
           ]
    });
    //批处理执行
    $("#BatchProcessing001_btn_running").click(function() {
        Joyin.ajaxSync(checkUrl, getPageParams(), function(result) {
            if (result) {
                //文件Id
                fileId=result.data;
                Joyin.confirm("是否执行批处理任务", function() {
                    executeBatch();
                });
            }
        });
    });
    //执行批处理
    var executeBatch=function(){
        //禁用画面元素
        //立刻刷新画面
        unableBox(true);
        $(".debt-record-emprty").css("display","none");
        $("#recordWrap").css("display","block");
        reflushList();
        reflushLog();
        isNeedInit=false;
        //启动定时任务
        taskId = self.setInterval(function(){
            reflushList();
            reflushLog();
        },3000);
    }
    //禁用画面元素
    var unableBox= function(flag){
        //需要初始化，没有被禁止
        if (flag){
            $("#BatchProcessing001_btn_running").prop("disabled",true);
            $("#BatchDealProcess").prop("disabled",true);
            $('#BatchDealDate').prop("disabled",true);
            $('#BatchProcessing001_btn_recover').prop("disabled",true);
        }
        else {
            $("#BatchProcessing001_btn_running").prop("disabled",false);
            $("#BatchDealProcess").prop("disabled", false);
            $('#BatchDealDate').prop("disabled", false);
            $('#BatchProcessing001_btn_recover').prop("disabled",false);
        }
    }
    //刷新列表
    var reflushList = function(){
        table.load();
    }
    //刷新日志
    var reflushLog=function(){
        var param={};
        param.fileId=fileId;
        Joyin.ajax("reflushLog",param,function(result){
            if(result){
                $(".text-area")[0].innerHTML=result.data;
             // 光标移动到最后一行
                var d = document.getElementById("recordWrap").scrollHeight;
                document.getElementById("recordWrap").scrollTop = d;
            }
        })
    }
    //批处理执行完成处理画面为就绪状态
    var reSetPage=function(){
        //结束定时任务
        self.clearInterval(taskId);
        //关闭画面元素的禁用
        unableBox(false);
        isNeedInit=true;
    }
});