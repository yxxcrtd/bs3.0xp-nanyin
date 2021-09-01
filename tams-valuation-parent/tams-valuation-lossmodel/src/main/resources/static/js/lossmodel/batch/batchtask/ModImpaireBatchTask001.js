$(function () {
    var excuteDate;
    //轮询间隔事件
    var time = 10000;

    var interId = "";

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var result = {};
        result.finprodId = $('#finprodId').val();
        result.importDate = $('#execDate').val();
        return result;
    };

    var docH= $(document).height();


    var tabTable = $('#dataTable').joyintable({
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
        cols:[
            {field: 'executeDateStr', sortField: 'executeDate', width: 150, title: '业务日期'}
            , {field: 'seqNo', visible: false}
            , {field: 'taskId', visible: false}
            , {field: 'startDate', visible: false}
            , {field: 'endDate', visible: false}
            , {
                field: 'taskName', title: '任务名称', width: 150, render: function (data, type, row, meta) {
                    var html = '<a href="javascript:void(0);" class="detailName" data-seqno="' + row.seqNo + '" data-taskId="' + row.taskId + '">' + data + '</a>';
                    return html;
                }
            }
            , {
                field: 'status', width: 90, title: '执行状态', render: function (data, type, row, meta) {
                    if (data == 'UNR') {
                        return '未执行';
                    } else if (data == 'RUN') {
                        return '执行中'
                    } else if (data == 'BRK') {
                        return '中断'
                    } else if (data == 'END' && row.infoSeqNo == 0) {
                        return '正常结束'
                    } else if (data == 'EXP' || row.infoSeqNo != 0) {
                        return '异常结束'
                    }
                    return '未知';
                }
            }
            , {
                field: 'processTime', width: 150, title: '处理时间（S）', render: function (data, type, row, meta) {
                    if (row.status === 'UNR') {
                        return '';
                    }
                    return data*1/1000;
                }
            }
            , {field: 'createTimeString', sortField: 'createTime', width: 200, title: '启动时间', render: function (data, type, row) {
                    if(row.status === 'UNR') {
                        return '';
                    }
                    return data;
                }}

            , {field: 'updateTimeString', sortField: 'updateTime', width: 200, title: '结束时间', render: function (data, type, row) {
                    if(row.status === 'UNR' || row.status === 'RUN') {
                        return '';
                    }
                    return data;
                }}
            , {
                field: 'percent', title: '进度', width: 130, render: function (data, type, row, meta) {
                    var status = row.status;
                    var percent = '0';
                    if (status == 'END' || status == 'EXP') {
                        percent = '100';
                    }
                    var color = 'blue';
                    if (row.infoSeqNo != 0 || status == 'EXP' || status == 'BRK') {
                        color = 'red';
                    }
                    var progress = '<div class="layui-progress layui-progress-big" lay-showpercent="true">' +
                        '<div id="process_' + row.seqNo + '" class="layui-progress-bar layui-bg-' + color + '" lay-percent="' + percent + '%" style="width: ' + percent + '%;">' +
                        '<span class="layui-progress-text">' + percent + '%</span>' +
                        '</div></div>';
                    return progress;
                }
            }
            , {
                field: 'totalNum', title: '资产总数', width: 120, render: function (data, type, row, meta) {
                    var html = '<a href="javascript:void(0);" class="detailName" data-seqno="' + row.seqNo + '" data-taskId="' + row.taskId + '">' + data + '</a>';
                    return html;
                }
            }
            , {
                field: 'successNum', title: '成功', width: 120, render: function (data, type, row, meta) {
                    var html = '<a style="color:blue;" href="javascript:void(0);" class="detailName" data-seqno="' + row.seqNo + '" data-taskId="' + row.taskId + '" data-execStatus="NOR">' + data + '</a>';
                    return html;
                }
            }
            , {
                field: 'errorNum', title: '失败', width: 120, render: function (data, type, row, meta) {
                    var html = '<a style="color:red;" href="javascript:void(0);" class="detailName" data-seqno="' + row.seqNo + '" data-taskId="' + row.taskId + '" data-execStatus="EXP">' + data + '</a>';
                    return html;
                }
            }
            , {
                field: 'warnNum', title: '警告', width: 120, render: function (data, type, row, meta) {
                    var html = '<a style="color:yellow;" href="javascript:void(0);" class="detailName" data-seqno="' + row.seqNo + '" data-taskId="' + row.taskId + '" data-execStatus="WARN">' + data + '</a>';
                    return html;
                }
            }
            ,{field: 'realName', width: 100, title: '操作人'}
        ]
    });

    /**
     * 日志页签
     */
    var mainTable = function(obj) {

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
        var batchList = BATCH_LIST;
        var datas = "";
        if (batchList != null && batchList != '') {
            datas = batchList.join(',')
        }

        // 资产代码

        var finprodId = $('#finprodId').val();
        if (finprodId != null && finprodId != '') {
            finprodId = finprodId.join(',')
        }
        // 执行日期
        var execDate = $('#execDate').val()
        // 执行日期 < 平台日
        if (execDate < SESSION_SYS_DATE) {
            Joyin.confirm('是否确认重跑历史数据？', function (){
                doConfirmExecConfirm(finprodId, datas)
            })
        } else {
            doConfirmExecConfirm(finprodId, datas)
        }
    }

    function doConfirmExecConfirm(finprodId, datas) {
        if (finprodId == null || finprodId == '' || finprodId == undefined) {
            Joyin.confirm('请确认是否要跑所有资产的估值?', function () {
                doExecAjax(datas, finprodId)
            })
        } else {
            doExecAjax(datas, finprodId)
        }
    }

    function doExecAjax(datas, finprodId) {
        Joyin.ajax('execute',
            {
                datas : datas,
                finprodId: finprodId,
                execDate: $('#execDate').val()
            },
            function(data) {
                var result = JSON.parse(data);
                if(result.isCheck==0){
                    //再次执行启动前停止上一次轮询
                    stopInterval();
                    //调用后一秒再查询接口,快速展示界面数据
                    startInterval();
                    layui.layer.alert("批处理已执行，可在批处理监控画面查看执行进度");
                }else if(result.isCheck==1){
                    layui.layer.alert(result.checkMsg);
                }
                else if(result.isCheck==2){
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

        // 绑定查询按钮事件
        $('#ModImpaireBatchTask001_btn_searchBtn').click(function() {
            if (interId != '') {
                stopInterval();
            }
            mainTable($('#log-title'));
        });
        // 绑定执行按钮事件
        $('#ModImpaireBatchTask001_btn_doExecBtn').click(function() {
            doExec();
        });

        /**
         * 详情页面
         */
        $('body').on('click', ".detailName", function () {
            var seqNo = $(this).data("seqno");
            var taskId = $(this).attr("data-taskId");
            var execStatus = $(this).attr("data-execStatus");
            var index = Joyin.dialog("资产明细", "../ModImpaireBatchTask002/init", "100%", "100%", {detailSeqNo: seqNo, taskId: taskId, execStatus:execStatus}, function () {

            }, {
                "关闭": function () {
                    layer.close(index);
                }
            })
        })



        var tree=$("#typeDatatree");
        var docH= $(document).height();
        var tabHeadUlH=100;
        tree.children('div').eq(1).height((docH-tabHeadUlH)+"px");
        tree.children('div').eq(1).css("overflow-x", "hidden");
        startInterval();
    });
});


function serachKey(){
    $('#typeDatatree').reloadTree({
        taskName: $('#taskName').val()
    });
}
