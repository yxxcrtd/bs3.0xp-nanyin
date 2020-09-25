$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            jobId: $.trim($('#jobId').val()), 
            execStatus: $.trim($('#execStatus').val()),
            execResult: $.trim($('#execResult').val()),
//            prodId:$.trim($('#prodId').val()),
            valDateBegin: $('#runDate').startDate(),
            valDateEnd: $('#runDate').endDate()
        }
    };
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        minWidth:100,
		checkbox: true,
		cols: [ 
		        {field:'jobId', title: '作业编号',width:180,sort: true},
		        {field:'jobName', title: '作业名称',width:300,sort: true},
		        {field:'execStatus', title: '执行状态',sort: true,visible:false},
		        {field:'execStatusName', title: '执行状态',width:150,sort: true,sortField:"execStatus"},
		        {field:'execDate', title: '首次执行日期',width:150,type:"date",sort: true},
		        {field:'lastexecDate', title: '最新执行日期',width:180,sort: true},
		        {field:'startExecDate', title: '开始执行时间',width:180,type:"datetime",sort: true},
		        {field:'endExecDate', title: '结束执行时间',width:180,type:"datetime",sort: true},
		        {field:'spendTime', title: '耗时（s）',width:120,sortable: false},
		        {field:'execResult', title: '执行结果',width:120,sort: true},
		        {field:'execResultExplain', title: '执行结果说明',width:120,sortable: false},
		        {field:'jobSeq', title: '工作Seq',visible:false},
		        /*{field:'reportName', title: '报表模板',sort: true},
		        {field:'proName', title: '产品名称',sort: true},
		        
		        {field:'jobStatus', title: '当前状态id',visible:false},
		        {field:'jobStatusName', title: '当前状态',sort: true},
		        {field:'endDateStr', title: '上一次运行时间',sort: true},
		        {field:'nextDateStr', title: '下一次运行时间',sort: true},*/
		        
		        
                {field:'operation', title: '操作',fixed : 'right',width:95,
                    render : function(data, type, row, meta){
//                        return '<span class="layui-badge-rim layui-bg-blue">已确认</span>';
//                    	var str = '<a id="jumpTab" href=\'javascript:redirectUrl2("'+1+'","'+row.jobId+'","'+row.execDate+'","'+row.jobSeq+'")\'><i title="查看详细" class="iconfont icon-detail"></i></a>';
//                    	if(row.execStatus != '01'){
//                    		str += '<a id="jumpTab" href=\'javascript:redirectUrl2("'+2+'","'+row.jobId+'","'+row.execDate+'","'+row.jobSeq+'")\'><i title="重跑" class="iconfont icon-rerun"></i></a>';
//                    		str += '<a id="jumpTab" href=\'javascript:redirectUrl2("'+3+'","'+row.jobId+'","'+row.execDate+'","'+row.jobSeq+'")\'><i title="失败重跑" class="iconfont icon-resume"></i></a>';
//                    	}
                    	
                    	var date = new Date(row.execDate).format("yyyy-MM-dd");
                    	var str = '';
                    	if(purviewElements.indexOf('_manual')!=-1){
                    		str +='<a id="jumpTab" style="color: #4893e6;" onclick=\'javascript:redirectUrl2("'+1+'","'+row.jobId+'","'+date+'","'+row.jobSeq+'","'+row.startExecDate+'")\'><i class="iconfont icon-detail manual" title="查看详细"></i></a>';
                    	}
                    	if(row.execStatus != '01'&&row.execStatus != '06'){
                    		if(purviewElements.indexOf('_batch')!=-1){
                    			str += "&nbsp;&nbsp;";
                    			str += '<a id="jumpTab" style="color: green;" href=\'javascript:redirectUrl2("'+2+'","'+row.jobId+'","'+date+'","'+row.jobSeq+'","'+row.startExecDate+'")\'><i class="iconfont icon-rerun batch" title="重跑"></i></a>';
                    		}
                    		//改为edit，download传不上页面
                    		if(row.execResult != '成功' &&purviewElements.indexOf('_edit')!=-1 ){
                    			str += "&nbsp;&nbsp;";
                        		str += '<a id="jumpTab" style="color: #4893e6;" href=\'javascript:redirectUrl2("'+3+'","'+row.jobId+'","'+date+'","'+row.jobSeq+'","'+row.startExecDate+'")\'><i class="iconfont icon-resume edit" title="失败重跑"></i></a>';
                    		}
                    	}                    	
//                    	var str = '<a id="jumpTab" style="color: #4893e6;" onclick=\'javascript:redirectUrl2("'+1+'","'+row.jobId+'","'+date+'","'+row.jobSeq+'","'+row.startExecDate+'")\'><i class="iconfont icon-detail" title="查看详细"></i></a>';
//                    	if(row.execStatus != '01'&&row.execStatus != '06'){
//                    		str += "&nbsp;&nbsp;";
//                    		str += '<a id="jumpTab" style="color: green;" href=\'javascript:redirectUrl2("'+2+'","'+row.jobId+'","'+date+'","'+row.jobSeq+'","'+row.startExecDate+'")\'><i class="iconfont icon-rerun" title="重跑"></i></a>';
//                    		if(row.execResult != '成功'){
//                    			str += "&nbsp;&nbsp;";
//                        		str += '<a id="jumpTab" style="color: #4893e6;" href=\'javascript:redirectUrl2("'+3+'","'+row.jobId+'","'+date+'","'+row.jobSeq+'","'+row.startExecDate+'")\'><i class="iconfont icon-resume" title="失败重跑"></i></a>';
//                    		}
//                    	}
                        return str;
                        //+ '<a id="jumpTab" href=\'javascript:redirectUrl2("'+3+'")\'><span class="layui-badge-rim layui-bg-blue">日志</span></a>'
                    } 
                }
		]
	});

	
	var exportExcel = function() {
		if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    

    /**
     * 跳转页面2
     */
    var redirectUrl2 = function(param, jobId, execDate, jobSeq, startExecDate) {
        /*if("2" == param){
            layer.alert("作业正在取消运行！");
        }else{
            layer.alert("作业正在运行！");
        }*/
    	// add by guozhongbao 报表作业监控 20181205 start
    	if(startExecDate == "undefined") {
    		startExecDate = "";
    	}
    	if("1" == param){
    		Joyin.dialog(JoyinUtil.getLabel("明细"),
                JoyinUtil.getRealPath("/disclosure/information/manage/workmonitor/ReportWorkMonitor002/init"),
                "90%", "90%", {"jobId":jobId,"execDate":execDate,"startExecDate":startExecDate},
                function(data){
                }
            );
    	}else if(("2" == param)){
            Joyin.confirm("请确认重跑全部？", function(){
            	Joyin.ajaxJson('runAllJob', {jobId:jobId,execDate:execDate,jobSeq:jobSeq}, function(result) {
            		 table.reload();
    			   });
            });
    	}
    	else if(("3" == param)){
    		Joyin.confirm("请确认重跑失败？", function(){
            	Joyin.ajaxJson('runErrorJob', {jobId:jobId,execDate:execDate,jobSeq:jobSeq}, function(result) {
    				   table.reload();
    			   });
            });
    	}
    	// add by guozhongbao 报表作业监控 20181205 end
    }

    window.redirectUrl2 = redirectUrl2;
    
	/**
     * 画面加载完成
     */
    $(function() {
    

        // 绑定按钮事件
        $('#ReportWorkMonitor001_btn_search').click(function() {
            table.load();
        });
        $('#ReportWorkMonitor001_btn_export').click(function() {
            exportExcel();
        });
        $('#ReportWorkMonitor001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("新建"),
                    JoyinUtil.getRealPath("/disclosure/information/manage/workmanage/ReportWorkManage002/init"),
                    "90%", "90%", {},
                    function(data){
                    }); 
        });
        $('#ReportWorkMonitor001_btn_delete').click(function() {
            checkAllCheckedRows('0');
        });
    });
    
});