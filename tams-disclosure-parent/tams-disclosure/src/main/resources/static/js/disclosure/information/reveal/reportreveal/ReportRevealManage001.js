$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId: $.trim($('#finprodId').val()), 
            reportName: $.trim($('#reportName').val()), 
            valDateBegin: $('#makeDate').startDate(),
            valDateEnd: $('#makeDate').endDate(),
            revealStatus : $.trim($('#revealStatus').val()),
            workflowId : $('#workflowId').val(),
            // add by guozhongbao 报表披露管理 20181204 start
            confirmDateBegin : $('#confirmDate').startDate(),
            confirmDateEnd : $('#confirmDate').endDate(),
            revealDateBegin : $('#revealDate').startDate(),
            revealDateEnd : $('#revealDate').endDate(),
            // add by guozhongbao 报表披露管理 20181204 end
            repType: $.trim($('#repType').val()),
            revealChanel:$.trim($('#revealChanel').val()),
            confromPage : $.trim($('#confromPage').val()),
            revealEndDateBegin : $('#revealEndDate').startDate(),
            revealEndDateEnd : $('#revealEndDate').endDate()
        }
    };
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: basePath +'disclosure/information/reveal/reportreveal/ReportRevealManage001/findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit :REM_TYPE && REM_TYPE == 'R6S1',
		checkbox: true,
		cols: [ 
		        {field:'id', title: 'id',visible:false},
                {field:'revealStatus', title: '状态',sort: true,visible:false},
                {field:'revealStatusName', title: '披露状态',width:90,sort: true,sortField:"revealStatus"
                    ,render: function (data, type, row, meta) {
                        if(row.revealStatus=='01'){
                            return '<span class="layui-badge-rim layui-bg-green">'+row.revealStatusName+'</span>';
                        }else if(row.revealStatus=='02'){
                            return '<span class="layui-badge-rim layui-bg-red">'+row.revealStatusName+'</span>';
                        }else{
                        	return '';
                        }
                    }
                },
                {field:'prodid', title: '产品代码',width:150, sort: true},
                {field:'prodName', title: '产品名称',width:150, sort: true},
                {field:'reportName', title: '报表名称',width:100,sort: true},
                {field:'startDate', title: '开始日',visible:false,type:"date"},
                {field:'endDate', title: '结束日',visible:false,type:"date"},
                {field:'reportDate', title: '报告期',width:170, sort: true},
                {field:'revealEndDateStr', title: '披露截止日',width:170, sort: true},
                {field:'reportCategory', title: '报表类别', sort: true,visible:false},
                {field:'reportCategoryName', title: '报表类别',width:100, sort: true,sortField:"reportCategory"},
                {field:'repId', title: '报表代码',sort: true,visible:false},
                {field:'sysFilename', title: '文件名称',width:100, sort: true
                    ,render: function (data, type, row, meta) {
                        // /framework/download/ + SYS_UPLOAD_FILE_INFO.SEQ_NO
                    	return '<a href="'+JoyinUtil.getRealPath('/disclosure/information/reveal/reportconfirm/ReportConfirmManage001/downLoad?'+'sysFilepath='+row.sysFilepath+'&prodId='+row.prodid+'&reportDate='+row.reportDate+'&reportName='+row.reportName)+ '">' + data + '</a>';
                       // return '<a href="'+JoyinUtil.getRealPath('/framework/download/' + row.sysFilepath)+ '">' + data + '</a>';
                    }
                },
                {field:'createTimeStr', title: '生成时间',width:100, sort: true},
                {field:'confirmDateStr', title: '确认时间',width:100, sort: true},
                {field:'isConfirmName', title: '确认方式',width:100, sort: true,sortField:"isConfirm"},
                {field:'revealDate', title: 'revealDate',width:100, sort: true,visible:false},
                {field:'revealDateStr', title: '披露时间',width:100, sort: true,sortField:"revealDate"
                	,render: function (data, type, row, meta) {
                    	if(row.revealStatus=='01'){
                            return '<span class="">'+row.revealDateStr+'</span>';
                        }else{
                        	return '';
                        }
                    }
                },
                {field:'revealChannelName', title: '披露渠道',width:100, sort: true,sortField:"revealChannel"},
                {field:'isRevealName', title: '披露方式',width:100, sort: true,sortField:"isReveal"},
                {field:'annex', title: '附件',width:100, sort: true
                	,render: function (data, type, row, meta) {
                          return '<a href="'+JoyinUtil.getRealPath('/framework/download/' + row.annexPath)+ '">'+ data +'</a>'
                   }
                },
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
    
 // add by guozhongbao 报表披露管理 20181204 start
    //下载模板
	var download = function(){
		var object =null;
		var rowArr = table.getAllCheckedRows();// 所有选中行数据
    	var ids = [];
    	//// 不点击查询，直接点击导出所有报表文件;点击查询，未勾选，导出所有报表文件；点击查询，已勾选，导出勾选 报表文件 zhangyf 20190109
    	if(rowArr == null || rowArr.length ==0){
    		layer.msg("请勾选一个数据！");
    	 }
    	else {
    		for(var i = 0 ; i<rowArr.length ; i++){
    			ids[i] = rowArr[i].id;
    		}
    		
    		Joyin.exportExcel('downloadReport', {id:JSON.stringify(ids),data:JSON.stringify(rowArr)});
			
		}
	}
	// add by guozhongbao 报表披露管理 20181204 end
    
    var openBatchGroupDetail = function (finprodId, pageModel) {
        var params = {finprodId: finprodId, pageModel: pageModel};
        layer.alert('还没有做!!!!');
//        if (!finprodId) {
//            delete params.finprodId;
//        }
//        Joyin.dialog(JoyinUtil.getLabel("查看详情"), 'openBatchGroupDetail', '99%', '99%',
//            params,
//            function () {
//                Joyin.ajax('deleteBatchJob', {}, function () {
//                    //table.load();
//                })
//        });
    }
    
    // 确认相关操作
    function checkAllCheckedRows(revealStatus){
        var rows=table.getAllCheckedRows();
        if(rows.length<=0){
            layer.msg("请选择一条记录");
            return;
        }
        var flag=true;
        var msg='';
        var index='';
        var arr=new Array();
        //确认
        if(revealStatus=='1'){
            for(var i = 0, row; row = rows[i]; i++){
            	if(row.revealStatus !='01'){
                    if(i==0){
                        index=i+1;
                    }else{
                        index=index+','+(i+1);
                    }
                }else{
                    layer.alert("只能对未披露的报表进行披露！！！");
                    return;
                }
                var parms={};
                parms.id=row.id;
                parms.revealStatus= '01';
                
                arr.push(parms);
            }
            
            if(index!=''){
                msg='进行报表披露?';
                Joyin.confirm(msg, function(){
                	
            		var object = {}; 
            		object.data = JSON.stringify(rows);
            		object.operateType = 'submit';
                    object.pageModel = pageModel;
                    object.updateTypeFlag = "QR";//当前操作为确认还是下发【确认："QR",下发："XF"】
                    object.pageId = "ReportRevealManage001";
                    //启动流程操作
                    startFunction(object,"ReportRevealManage001_btn_submit");                            
                }, function() {
                    //console.log('取消');
                });
            }
        }
        else if(revealStatus=='0'){
        	//定义变量
        	var notRevealIndexArr = [];//确认状态为未确认的数据
        	var okNoticeStatusIndexArr = [];//下发状态为成功的数据
        	
        	//循环选中的数据，进行数据过滤
            for(var i = 0, row; row = rows[i]; i++){
            	//存在【确认状态为未确认】或者【下发状态为成功】的情况
                if(row.revealStatusCode=='0' || row.noticeStatusCode=='1'){
                	//【确认状态为未确认】的 情况
                	if(row.revealStatusCode=='0'){
                		notRevealIndexArr.push(i+1);
                	}
                	//【下发状态为成功】的情况
                	if(row.noticeStatusCode=='1'){
                		okNoticeStatusIndexArr.push(i+1);
                	}
                	
                }
                //可正常操作的数据
                else{
                    var parms={};
                    parms.finprodId=row.finprodId;
                    parms.finprodAbbr = row.finprodAbbr;
                    parms.valDate=row.valDate;
                    parms.valBalFlag = row.valBalFlag;
                    parms.valBalFlagCode=row.valBalFlagCode;

                    parms.revealStatusCode= revealStatus;
                    parms.revealStatus = '撤销确认';
                    
                    arr.push(parms);
                }
            }
            
            //存在【确认状态为未确认】的 情况
            if(notRevealIndexArr != null && notRevealIndexArr.length > 0){
                msg='第'+notRevealIndexArr.join(',')+'条估值表未确认,不需要取消!!';
                layer.alert(msg);
            }
            //存在【下发状态为成功】的情况
            else if(okNoticeStatusIndexArr != null && okNoticeStatusIndexArr.length > 0){
                msg='存在已经下发的估值数据不能取消确认，请重新选择!!';
                layer.alert(msg);
            }
            //无错误情况，可进行【撤销确认】操作
            else{
                Joyin.confirm('是否撤销确认', function(){
                	
                	var object = {}; 
                	object.data = JSON.stringify(arr);
            		object.operateType = 'submit';
                    object.pageModel = pageModel;
                    object.revealOrNotice = "QR";//当前操作为确认还是下发【确认："QR",下发："XF"】
                    
                    //启动流程操作
                    startFunction(object,"ValNetValueReveal001_btn_delete");
                     
                }, function() {
                   // console.log('取消');
                });
            }
            
        }
        //撤销披露
        else if(revealStatus=='3'){
            for(var i = 0, row; row = rows[i]; i++){
                if(row.revealStatus =='01'){// '01' = 已披露
                    if(i==0){
                        index=i+1;
                    }else{
                        index=index+','+(i+1);
                    }
                }else{
                    layer.alert("只能对已披露的报表进行撤销！");
                    return;
                }
                var parms={};
                parms.id=row.id;
                parms.revealStatus= '01';
                
                arr.push(parms);
            }
            
            if(index!=''){
                msg='是否撤销报表披露?';
                Joyin.confirm(msg, function(){
                    
                    var object = {}; 
                    object.data = JSON.stringify(rows);
                    object.operateType = 'submit';
                    

                    Joyin.ajax(basePath + 'disclosure/information/reveal/reportreveal/ReportRevealManage001/revoke', 
                            object, 
                            function(result) {
                                table.reload();
                            }
                    );
                    /*Joyin.ajaxJson(basePath + 'disclosure/information/reveal/reportreveal/ReportRevealManage001/revoke',
                            object,
                            function(result) {
                                table.reload();
                            }
                    );*/
                }, function() {
                    //console.log('取消');
                });
            }
        }
        //退回
        else if(revealStatus=='4'){

            for(var i = 0, row; row = rows[i]; i++){
                if(undefined == row.revealStatus || row.revealStatus !='01'){// '01' = 已披露
                    if(i==0){
                        index=i+1;
                    }else{
                        index=index+','+(i+1);
                    }
                }else{
                    layer.alert("只能对未披露的报表进行退回！");
                    return;
                }
                var parms={};
                parms.id=row.id;
                
                arr.push(parms);
            }
            
            if(index!=''){
                msg='是否退回?';
                Joyin.confirm(msg, function(){
                    
                    var object = {}; 
                    object.data = JSON.stringify(rows);
                    object.operateType = 'submit';
                    Joyin.ajax(basePath + 'disclosure/information/reveal/reportreveal/ReportRevealManage001/goBack', 
                            object, 
                            function(result) {
                                table.reload();
                            }
                    );
                }, function() {
                    //console.log('取消');
                });
            }
        
        }
    }
    
    //下发相关操作
    function checkUnfrozen(unfrozen){
        var rows=table.getAllCheckedRows();
        if(rows.length<=0){
            layer.alert("请选择一条记录");
            return;
        }
        var flag=true;
        var msg='';
        var arr=new Array();
        for(var i = 0, row; row = rows[i]; i++){
            //撤销下发操作
        	if(unfrozen=='0'){
                if(row.noticeStatusCode!='1'){
                    flag=false;
                    msg='只能对下发状态为“下发成功”的记录进行取消下发操作，请重新选择！';
                    break;
                }
                else{
                    var parms={};
                    parms.finprodId=row.finprodId;
                    parms.finprodAbbr = row.finprodAbbr;
                    parms.valDate=row.valDate;
                    parms.valBalFlag = row.valBalFlag;
                    parms.valBalFlagCode=row.valBalFlagCode;
                    
                    parms.noticeStatusCode=unfrozen;
                    parms.noticeStatus = '撤销下发';
                    
                    arr.push(parms);
                    
                }
            }
        	//下发操作
        	else{
                //【确认状态不为已确认】或者【下发状态为下发成功】情况
        		if(row.revealStatusCode!='1' || row.noticeStatusCode=='1'){
                	flag=false;
                    msg='只能对确认状态为“已确认”并且下发状态为“未下发”或者“下发失败”的记录进行下发操作，请重新选择！';
                    break;
                    
                }else{
                    var parms={};
                    var parms={};
                    parms.finprodId=row.finprodId;
                    parms.finprodAbbr = row.finprodAbbr;
                    parms.valDate=row.valDate;
                    parms.valBalFlag = row.valBalFlag;
                    parms.valBalFlagCode=row.valBalFlagCode;
                    
                    parms.noticeStatusCode=unfrozen;
                    parms.noticeStatus = '下发';
                    
                    
                    parms.finprodType=row.finprodType;
                    parms.capiTal=row.capiTal;
                    parms.assetValue=row.assetValue;
                    parms.netUnitValue=row.netUnitValue;
                    parms.totalNetNalue=row.totalNetNalue; 
                    parms.sevenDayRate =row.sevenDayRate;
                    arr.push(parms);
                }
            }
            
        }
        
        //非正常情况
        if(!flag){
            layer.alert(msg);
            return;
        }
        //正常情况
        else{
        	
       		var object = {}; 
       		object.data = JSON.stringify(arr);
    		object.operateType = 'submit';
            object.pageModel = pageModel;
            object.revealOrNotice = "XF";//当前操作为确认还是下发【确认："QR",下发："XF"】
            
            //撤销下发
            if(unfrozen=='0'){
            	//启动流程操作
                startFunction(object,"ValNetValueReveal001_btn_deleteunfrozen");
            }
            //下发
            else{
            	//启动流程操作
            	startFunction(object,"ValNetValueReveal001_btn_unfrozen");
            }
            
            return;
        }
        
    }
    
    
    /**
     * 启动流程
     */
    var startFunction = function(objectDate,elementId){
        Joyin.ajax(basePath + 'disclosure/information/reveal/reportreveal/ReportRevealManage001/check', 
        		objectDate, 
        		function(result) {
                	WorkFlow.doSubmitList(
                    		basePath + 'disclosure/information/reveal/reportreveal/ReportRevealManage001/updateRevealStatusAndNoticeStatus', 
                    		objectDate, 
                    		elementId,
                    		null,
                    		function (data) {table.reload();}
                    		);
        		}
        );
    }
    
    var openShow=function(){
        var rows=table.getAllCheckedRows();
        Joyin.dialog(JoyinUtil.getLabel("附件上传"),
                JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valnetvaluereveal/ValNetValueReveal003/init"),
                "85%", "90%", {pageModel:PAGE_MODEL_UPLOAD,
            finprodId:rows.finprodId,finprodAbbr : rows.finprodAbbr,valDate:rows.valDate,valBalFlag:rows.valBalFlag},
                function(){
                });
    }
    
	/**
     * 画面加载完成
     */
    $(function() {
    	var searchDate = $('#searchDate').val();
    	var searchDateStart = $('#searchDateStart').val();
    	var searchDateEnd = $('#searchDateEnd').val();
    	if($('#confromPage').val() == '01'){
    		var pageFrom = $("#pageFrom").val();
    		if(searchDateStart && searchDateEnd) {
    			if(pageFrom == "01") {
    				$('#revealEndDate').val(searchDateStart + ' - ' + searchDateEnd);
    			}else if(pageFrom == "02" && searchDate) {
    				$('#revealDate').val(searchDate + ' - ' + searchDate);
    			}
    		}
    		table.load();
    	}
        // 绑定按钮事件
        $('#ReportRevealManage001_btn_search').click(function() {
            table.load();
        });
        $("body").on("click", ".batchName", function () {
            openBatchGroupDetail($(this).data("finprodId"), PAGE_MODEL_REFERENCE);
        })
        $('#ReportRevealManage001_btn_export').click(function() {
            exportExcel();
        });
        
        if($('#workflowId').val()!=''){
        	table.load();
        	$('.condition-area').hide();
        	$('.form-button-area').hide();
    	}
        
//        $('#revealStatus').change(function(){
//        	table.reload();
//	      });
        
        
        //确认按钮
        $('#ReportRevealManage001_btn_submit').click(function() {
            checkAllCheckedRows('1');
        });
        //撤销披露
        $('#ReportRevealManage001_btn_delete').click(function() {
            checkAllCheckedRows('3');
        });
        //退回
        $('#ReportRevealManage001_btn_back').click(function() {
            checkAllCheckedRows('4');
        });
//        $('#ValNetValueReveal001_btn_unfrozen').click(function() {
//            checkUnfrozen('1');
//        });
//        $('#ValNetValueReveal001_btn_deleteunfrozen').click(function() {
//            checkUnfrozen('0');
//        });
        // 绑定上传事件
        $('#ReportRevealManage001_btn_upload').click(function() {
//            redirectUrl2(null);
        });
        // add by guozhongbao 报表披露管理 20181204 start
        // 绑定下载事件
        $('#ReportRevealManage001_btn_download').click(function() {
 		   download();
	    });
        // add by guozhongbao 报表披露管理 20181204 end
    });
	
});
/**
 * 跳转页面2
 */
var redirectUrl2 = function(param){
    if (typeof (Storage) !== "undefined") {
        // 支持 localStorage sessionStorage 对象!
//        sessionStorage.removeItem("ReportRevealManage001_rowid");
        sessionStorage.ReportRevealManage001_rowid=param;// 表REP_REVEAL.主键
    } else {
        // 抱歉! 不支持 web 存储。
    }
    Joyin.dialog(JoyinUtil.getLabel("附件上传下载"),
            JoyinUtil.getRealPath("/disclosure/information/reveal/reportreveal/ReportRevealManage003/init?"+param),
            "80%", "80%", {},
            function(data){
                data;
            });  
}
/**
 * 跳转页面
 */
var redirectUrl = function(param){
    
    Joyin.dialog(JoyinUtil.getLabel("附件上传下载"),
//            JoyinUtil.getRealPath("/disclosure/information/reveal/reportreveal/ReportRevealManage003/init?"+param),
            JoyinUtil.getRealPath("/framework/fileUpload/init?"),// 共通附件上传界面，初始化
            "50%", "50%", {},
            function(data){
                data;
            });  
}