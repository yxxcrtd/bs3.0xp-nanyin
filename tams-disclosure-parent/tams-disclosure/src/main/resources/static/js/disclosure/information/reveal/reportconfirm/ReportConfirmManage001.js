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
            confirmStatus:$.trim($('#confirmStatus').val()),
        	workflowId:$('#workflowId').val(),
        	confirmDateBegin : $('#confirmDate').startDate(),
        	confirmDateEnd: $('#confirmDate').endDate(),
        	confirmType : $.trim($('#confirmType').val()),
        	repType : $.trim($('#repType').val()),
        	confromPage : $.trim($('#confromPage').val()),
        	revealChanel:$.trim($('#revealChanel').val()),
        	revealEndDateBegin : $('#revealEndDate').startDate(),
            revealEndDateEnd : $('#revealEndDate').endDate()
        }
    };
    
    
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
		checkbox: true,
		cols: [ 
		        {field:'id', title: 'id',visible:false},
		        {field:'confirmStatus', title: 'confirmStatus',sort: true,visible:false},
                {field:'confirmStatusName', title: '确认状态',width:90,sort: true,sortField:"confirmStatus"
                    ,render: function (data, type, row, meta) {
                        if(row.confirmStatus=='1'){
                            return '<span class="layui-badge-rim layui-bg-blue">'+row.confirmStatusName+'</span>';
                        }else if(row.confirmStatus=='0'){
                            return '<span class="layui-badge-rim layui-bg-red">'+row.confirmStatusName+'</span>';
                        }else{
                        	return '';
                        }
                    }
                },
                {field:'revealStatus', title: '状态',sort: true,visible:false},
                {field:'prodid', title: '产品代码',width:100, sort: true},
                {field:'prodName', title: '产品名称',width:150, sort: true},
                {field:'reportName', title: '报表名称',width:200, sort: true},
                {field:'repId', title: '报表代码',sort: true,visible:false},
                {field:'reportDate', title: '报告期',width:170, sort: true},
                {field:'revealEndDateStr', title: '披露截止日',width:170, sort: true},
                {field:'reportCategory', title: 'reportCategory',sort: true,visible:false},
                {field:'reportCategoryName', title: '报表类别',width:100, sort: true,sortField:"reportCategory"},
                {field:'sysFilename', title: '系统生成', width:200,sort: true
                    ,render: function (data, type, row, meta) {
                        // /framework/download/ + SYS_UPLOAD_FILE_INFO.SEQ_NO
                    	return '<a href="'+JoyinUtil.getRealPath('/disclosure/information/reveal/reportconfirm/ReportConfirmManage001/downLoad?'+'sysFilepath='+row.sysFilepath+'&prodId='+row.prodid+'&reportDate='+row.reportDate+'&reportName='+row.reportName+'&sysFileName='+row.sysFilename)+ '">' + data + '</a>';
//                    	return '<a href="'+JoyinUtil.getRealPath('/framework/download/' + row.sysFilepath)+ '">' + data + '</a>';
                    }
                },
//                {field:'sysMakeId', title: '系统生成id',sort: true,visible:false},
           //修改
                {field:'filename', title: '手动上传',width:300,sort: true
                	,render: function (data, type, row, meta) {
                		var str = '<div style="width:210px;height:28px; overflow:hidden;float:left">'+'<a href="'+JoyinUtil.getRealPath('/disclosure/information/reveal/reportconfirm/ReportConfirmManage001/downLoad?'+'sysFilepath='+row.filepath+'&prodId='+row.prodid+'&reportDate='+row.reportDate+'&reportName='+row.reportName+'&sysFileName='+row.sysFilename)+ '">' + data + '</a>'+'</div>';
                		//'<a href="'+JoyinUtil.getRealPath('/framework/download/' + row.filepath)+ '">'+ data +'</a>'+'</div>';
                		if(row.filename != null && row.filename != "" && row.confirmStatus == '0'){
                			str += '<div style="width:50px;height:28px;float:right;">'+'<a href=\'javascript:redirectUrl3("'+row.id+'","'+1+'","'+row.seqno+'","'+row.prodid+'")\' style="margin-left: 5px;"><i class="iconfont icon-delete" style="color: #d81e06;" title="删除文件"></i></a>'+'</div>';
                		}
                	    return str;
                   }
                },
//                {field:'perUploadId', title: '人工上传id',sort: true,visible:false},
                {field:'createTimeStr', title: '生成日期',width:100, sort: true},
                {field:'confirmDateStr', title: '确认时间',width:100, sort: true},
                {field:'detail', title: '上传说明',width:100, sort: true},
                {field:'uploadTime', title: '上传时间',width:100, sort: true},
                {field:'isConfirmName', title: '确认方式',width:100, sort: true,sortField:"isConfirm"},
                {field:'revealChannelName', title: '披露渠道',width:100, sort: true,sortField:"revealChannel"},
                {field:'isRevealName', title: '披露方式',width:100, sort: true,sortField:"isReveal"},
//添加          附件  ===========   
                {field:'annexPath', title: '路径',visible:false},
                {field:'annex', title: '附件',width:300, sort: true
                	,render: function (data, type, row, meta) {
                		var str = '<div style="width:210px;height:28px; overflow:hidden;float:left">'+'<a style="width:100%" href="'+JoyinUtil.getRealPath('/disclosure/information/reveal/reportconfirm/ReportConfirmManage001/downLoad?'+'sysFilepath='+row.annexPath+'&prodId='+row.prodid+'&reportDate='+row.reportDate+'&reportName='+row.reportName )+ '">'+ data +'</a>'+'</div>';
                 	   		//str += '<a class="fileclass"  href=\'javascript:deletFile("'+3+'","'+row.id+'")\'><i title="删除文件"  visibility="hidden" class="iconfont icon-cloud-upload1"></i></a>'; 
                		if(row.annex != null && row.annex != "" && row.confirmStatus == '0'){
                			str += '<div style="width:50px;height:28px;float:right;">'+'<a href=\'javascript:redirectUrl3("'+row.id+'","'+2+'","'+row.seqno+'","'+row.prodid+'")\' style="margin-left: 5px;"><i class="iconfont icon-delete" style="color: #d81e06;" title="删除附件"></i></a>'+'</div>';
                		}
                        return str;   
                   }
                },
             
                //ReportConfirmManage001
                {field:'operation', title: '操作',fixed : 'right',width:80,
                	render : function(data, type, row, meta){
                       //console.log(row);
                	   var str = "";
                	   if(row.confirmStatus != '1') {
                		   //update by yantianyuan 二级法人按钮改造 start
                		   if(purviewElements.indexOf('_manual')!=-1){
                			   str = '<a id="jumpTab" style="color: green;" href=\'javascript:redirectUrl2("'+row.id+'","'+1+'","'+row.seqno+'","'+row.prodid+'")\'><i title="上传披露文档" class="iconfont icon-upload manual"></i></a>';
                    		   str += '&nbsp;&nbsp;&nbsp;&nbsp;';
                		   }
                		   if(purviewElements.indexOf('_batch')!=-1){
                			   str += '<a id="jumpTab" href=\'javascript:redirectUrl2("'+row.id+'","'+2+'","'+row.seqno+'","'+row.prodid+'")\'><i title="上传附件" class="iconfont icon-cloud-upload1 batch"></i></a>';
                		   }
//                		   str = '<a id="jumpTab" style="color: green;" href=\'javascript:redirectUrl2("'+row.id+'","'+1+'","'+row.seqno+'","'+row.prodid+'")\'><i title="上传披露文档" class="iconfont icon-upload"></i></a>';
//                		   str += '&nbsp;&nbsp;&nbsp;&nbsp;';
//                		   str += '<a id="jumpTab" href=\'javascript:redirectUrl2("'+row.id+'","'+2+'","'+row.seqno+'","'+row.prodid+'")\'><i title="上传附件" class="iconfont icon-cloud-upload1"></i></a>';
                		 //update by yantianyuan 二级法人按钮改造 end
                           return str;
                	   }
              
                	} 
                   
                }
               
		]
	});

	 /**
     * 删除页面   
     */
    table.bind('click', '.delt', function(data, index) {
        data.filename = "delete";
            Joyin.confirm("确认删除吗？", function(){
            	Joyin.ajaxJson('deleteReport', {repId:data.repId}, function(result) {
 				   table.reload();
 			   });
        });
    });
    	
	//导出
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
    function checkAllCheckedRows(confirmStatus){
        var rows=table.getAllCheckedRows();
        if(rows.length<=0){
        	layer.msg("请至少选中一条数据");
            return;
        }
        for(var i = 0, row; row = rows[i]; i++){//待办事项界面，增加workflowId到数据中 zhangyf
            row.workflowId=$('#workflowId').val();
        }
        var flag=true;
        var msg='';
        var index='';
        var arr=new Array();
        if(confirmStatus=='1'){
            for(var i = 0, row; row = rows[i]; i++){
            	if(row.confirmStatus !='1'){
                    if(i==0){
                        index=i+1;
                    }else{
                        index=index+','+(i+1);
                    }
                }else{
                    layer.alert("只能对未确认的报表进行确认！！！");
                    return;
                }
                var parms={};
                parms.id=row.id;
                parms.confirmStatus= '1';
                
                arr.push(parms);
            }
            if(index!=''){
                msg='进行报表确认?';
                Joyin.confirm(msg, function(){
                	
            		var object = {}; 
            		object.data = JSON.stringify(rows);
            		object.operateType = 'submit';
                    object.pageModel = pageModel;
                    object.updateTypeFlag = "QR";//当前操作为确认还是下发【确认："QR",下发："XF"】
                    object.workflowId=$('#workflowId').val();
                    object.pageId = "ReportConfirmManage001";
                    //启动流程操作
                    startFunction(object,"ReportConfirmManage001_btn_submit");                            
                }, function() {
                    console.log('取消');
                });
            }
        }
        if(confirmStatus=='0'){
        	//定义变量
        	var notConfirmIndexArr = [];//确认状态为未确认的数据
        	var okNoticeStatusIndexArr = [];//下发状态为成功的数据
        	
        	//循环选中的数据，进行数据过滤
            for(var i = 0, row; row = rows[i]; i++){
            	//存在【确认状态为未确认】或者【下发状态为成功】的情况
                if(row.confirmStatusCode=='0' || row.noticeStatusCode=='1'){
                	//【确认状态为未确认】的 情况
                	if(row.confirmStatusCode=='0'){
                		notConfirmIndexArr.push(i+1);
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

                    parms.confirmStatusCode= confirmStatus;
                    parms.confirmStatus = '撤销确认';
                    
                    arr.push(parms);
                }
            }
            
            //存在【确认状态为未确认】的 情况
            if(notConfirmIndexArr != null && notConfirmIndexArr.length > 0){
                msg='第'+notConfirmIndexArr.join(',')+'条估值表未确认,不需要取消!!';
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
                    object.confirmOrNotice = "QR";//当前操作为确认还是下发【确认："QR",下发："XF"】
                    
                    //启动流程操作
                    startFunction(object,"ValNetValueConfirm001_btn_delete");
                     
                }, function() {
                    console.log('取消');
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
        		if(row.confirmStatusCode!='1' || row.noticeStatusCode=='1'){
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
            object.confirmOrNotice = "XF";//当前操作为确认还是下发【确认："QR",下发："XF"】
            
            //撤销下发
            if(unfrozen=='0'){
            	//启动流程操作
                startFunction(object,"ValNetValueConfirm001_btn_deleteunfrozen");
            }
            //下发
            else{
            	//启动流程操作
            	startFunction(object,"ValNetValueConfirm001_btn_unfrozen");
            }
            
            return;
        }
        
    }
    
    
    /**
     * 启动流程
     */
    var startFunction = function(objectDate,elementId){
        Joyin.ajax(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/check', 
        		objectDate, 
        		function(result) {
                	WorkFlow.doSubmitList(
                    		basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/updateConfirmStatusAndNoticeStatus', 
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
                JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valnetvalueconfirm/ValNetValueConfirm003/init"),
                "85%", "90%", {pageModel:PAGE_MODEL_UPLOAD,
            finprodId:rows.finprodId,finprodAbbr : rows.finprodAbbr,valDate:rows.valDate,valBalFlag:rows.valBalFlag},
                function(){            	  
                });
    }
      
    /**
     * 信息确认删除(不走流程)
     */
     var deleteConfirmMessage =function(){
    	var object = {};
     	var rowArr = table.getAllCheckedRows();// 所有选中行数据
     	if(rowArr == null || rowArr.length == 0) {
     		layer.msg("请至少选中一条数据");
     	}
     	else {
     		var keyStr = "";
     		var msg = "";
     		for(var i=0; i<rowArr.length; i++) {
     			keyStr = keyStr + rowArr[i].id + ",";
     			if(rowArr[i].confirmStatus=='1') {
     	     		msg = "不能删除已确认的数据！";
     	     	}
     		}
     		if(msg!="") {
     			layer.msg(msg);
     		}
     		else {
     			var objectWfl = {}; 
		     	var rowArr = table.getAllCheckedRows();
		     	objectWfl.data = JSON.stringify(rowArr);
		     	objectWfl.operateType = 'submit';
		     	objectWfl.pageModel = pageModel;
		     	objectWfl.updateTypeFlag = "QR";//当前操作为确认还是下发【确认："QR",下发："XF"】
			    
     			object.id = keyStr;
     	    	object.operateType = "delete";
     	    	object.data = JSON.stringify(rowArr)//将选中行的数据放入object中 zhangyf 20190130
     	    	Joyin.ajax(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/check', 
     	    			objectWfl, function(result) {
     	    		Joyin.ajaxJson(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/deleteCheck', object, function(result) {
     	    			Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
     	    				Joyin.ajaxJson(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/deleteConfirm', object, function(result) {
     	    					table.reload();
     	    				});
     	    			});
     	    		});
     	    	});
     		}
     	}
     }
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
	 
	 //批量上传披露文档
	 var batchUpload = function(){
		 Joyin.uploadFile({exts:'zip'},function(fileName,file) {
	    		Joyin.ajax(basePath +"disclosure/information/reveal/reportconfirm/ReportConfirmManage003/batchUploadFile?", {
	    			fileName:fileName,fileNo:file[0].fileNo,srcName:file[0].srcName
	    		},function(result){
	    			if(!result || !($.isArray(result) && result.length > 0)) {
	    				layui.layer.closeAll()
	    				table.reload();
	    			}
	    		});
	    	}); 
	 }
	 
	 // 取消确认
	 var confirmCancle = function(){
		var object ={};
     	var rowArr = table.getAllCheckedRows(); // 所有选中行数据
     	var ids = [];
     	var param = [];
     	if(rowArr == null || rowArr.length ==0){
     		layer.msg("请至少选中一条数据");
     	 }
     	else {
     		for(var i = 0 ; i<rowArr.length ; i++){
     			if(rowArr[i].confirmStatus=='0'){
     				Joyin.error("只能对已确认的数据进行取消确认");
     				break;
     			}
     			else if(rowArr[i].revealStatus=='01'){
     				Joyin.error("已披露的数据不能取消确认");
     				break;
     			}
     			else {
     				ids[i] = rowArr[i].id;
     			}
     		}
     		// 表格所选数据中已确认条数不为零，且不存在未确认的条数（需要保证所选的数据都为已确认才能进行取消确认操作）
     		if(ids.length != 0 && ids.length == rowArr.length){
     			Joyin.confirm("是否取消确认？", function(){
//     				Joyin.ajax('confirmCancle',{id:JSON.stringify(ids)},function(){
//     					table.reload();
//     				});
     			    object.id = JSON.stringify(ids);
     			    object.data = JSON.stringify(rowArr)//将选中行的数据放入object中 zhangyf 20190130
     				Joyin.ajaxJson(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/confirmCancle', object, function(result) {
                        table.reload();
                    });
     			});
     		}
     	}
	 }
	 
	 /**
	  * 删除文件
	  */
	 var deletFile = function(type,id){
		 $('.fileclass').css('visibility', 'hidden');
		 $(this).css('visibility', 'visible');
	     if (typeof (Storage) !== "undefined") {
	         // 支持 localStorage sessionStorage 对象!
//	         sessionStorage.removeItem("ReportConfirmManage001_rowid");
	         sessionStorage.ReportConfirmManage001_rowid=param;// 表REP_REVEAL.主键
	         sessionStorage.ReportConfirmManage001_option=option;// 表REP_REVEAL.主键
	     } else {
	         // 抱歉! 不支持 web 存储。
	     }
	     Joyin.dialog(JoyinUtil.getLabel("附件上传下载"),
	             JoyinUtil.getRealPath("/disclosure/information/reveal/reportconfirm/ReportConfirmManage003/init?"+param),
	             "80%", "80%", {},
	             function(data){ 
	            	 table.reload();
	                 data;
	             });  	     
	 } 	 
	 
	 
	 
	 /**
	  * 跳转页面2   -----【附件上传时弹出的界面】
	  */
	 var redirectUrl2 = function(param,option,seqno,prodid){
	     if (typeof (Storage) !== "undefined") {
	         // 支持 localStorage sessionStorage 对象!
//	         sessionStorage.removeItem("ReportConfirmManage001_rowid");
	         sessionStorage.ReportConfirmManage001_rowid=param;// 表REP_REVEAL.主键
	         sessionStorage.ReportConfirmManage001_option=option;// 表REP_REVEAL.主键
	     } else {
	         // 抱歉! 不支持 web 存储。
	     }
	     var workflowId = $("#workflowId").val();
	     var title= "附件上传下载";
	     if(option==1){
	    	 title = "人工上传披露文件";
	    	 	var object = {}; 
		     	var rowArr = table.getAllCheckedRows();
				object.data = '[{"seqno":"' + param + '","prodid":"' + prodid + '","id":"' + param + '"}]';
				object.operateType = 'submit';
			    object.pageModel = pageModel;
			    object.updateTypeFlag = "QR";//当前操作为确认还是下发【确认："QR",下发："XF"】
			    if(workflowId) {
			    	Joyin.uploadFile({exts:'pdf|docx|xlsx|doc|xls'},function(fileName,file) {
			    		Joyin.ajax(basePath +"disclosure/information/reveal/reportconfirm/ReportConfirmManage003/uploadFile?", {
			    			fileName:fileName,id:param,fileNo:file[0].fileNo,option:option,srcName:file[0].srcName
			    		},function(result){
			    			if(!result || !($.isArray(result) && result.length > 0)) {
			    				layui.layer.closeAll()
			    				table.reload();
			    			}
			    		});
			    	});
			    }else {
			    	Joyin.ajax(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/check',
			    			object,function(){
			    		Joyin.uploadFile({exts:'pdf|docx|xlsx|doc|xls'},function(fileName,file) {
			    			Joyin.ajax(basePath +"disclosure/information/reveal/reportconfirm/ReportConfirmManage003/uploadFile?", {
			    				fileName:fileName,id:param,fileNo:file[0].fileNo,option:option,srcName:file[0].srcName
			    			},function(result){
			    				if(!result || !($.isArray(result) && result.length > 0)) {
			    					layui.layer.closeAll()
			    					table.reload();
			    				}
			    			});
			    		});
			    	});
			    }
	     }else {
	    	 Joyin.uploadFile({exts:''},function(fileName,file) {
		    		Joyin.ajax(basePath +"disclosure/information/reveal/reportconfirm/ReportConfirmManage003/uploadFile?", {
		    			fileName:fileName,id:param,fileNo:file[0].fileNo,option:option,srcName:file[0].srcName
		    		},function(result){
		    			if(!result || !($.isArray(result) && result.length > 0)) {
		    				layui.layer.closeAll()
		    				table.reload();
		    			}
		    		});
		    	});
	     }
//	     Joyin.dialog(JoyinUtil.getLabel(title),
//	             JoyinUtil.getRealPath("/disclosure/information/reveal/reportconfirm/ReportConfirmManage003/init?"+param),
//	             "60%", "50%", {},
//	             function(data){ 
//	            	 table.reload();
//	                 data;
//	             });  
	     	
	 } 	 
	 window.redirectUrl2 = redirectUrl2;	
	 
	 
	 /**
	  * 跳转页面3  删除文件
	  */
	 var redirectUrl3 = function(id, option,seqno,prodid){
		 var workflowId = $("#workflowId").val();
		 if(option == "1"){
			 var object = {}; 
			 var rowArr = table.getAllCheckedRows();
			 object.data = '[{"seqno":"' + id + '","prodid":"' + prodid + '","id":"' + id + '"}]';
			 object.operateType = 'submit';
			 object.pageModel = pageModel;
			 object.updateTypeFlag = "QR";//当前操作为确认还是下发【确认："QR",下发："XF"】
			 if(workflowId) {
				 Joyin.ajaxJson(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/deleteFile', {option: option, id:id}, 
						 function(result) {
					 table.reload();
				 });
			 }else {
				 Joyin.ajax(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/check',
						 object,function(){
					 Joyin.ajaxJson(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/deleteFile', {option: option, id:id}, 
							 function(result) {
						 table.reload();
					 });
				 });
			 }
		 }else {
			 Joyin.ajaxJson(basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage001/deleteFile', {option: option, id:id}, 
					 function(result) {
				 table.reload();
			 });
		 }
	 } 	 
	 window.redirectUrl3 = redirectUrl3;
	 
	/**
	 * 
     * 画面加载完成
     */
    $(function() {
    	var searchDateStart = $('#searchDateStart').val();
    	var searchDateEnd = $('#searchDateEnd').val();
    	if($('#confromPage').val() == '01'){
    		if(searchDateEnd && searchDateStart) {
    			$('#revealEndDate').val(searchDateStart + ' - ' + searchDateEnd);
    		}
    		table.load();
    	}
        // 绑定按钮事件
        $('#ReportConfirmManage001_btn_search').click(function() {
            table.load();
        });
        //UAT-342 1：缺少【重置】按钮 leipeng 20180905
//        $('#ValNetValueConfirm001_btn_reset').hide();
        $("body").on("click", ".batchName", function () {
            openBatchGroupDetail($(this).data("finprodId"), PAGE_MODEL_REFERENCE);
        })
        $('#ValNetValueConfirm001_btn_reset').click(function(){
        	$('#revealEndDateHidden').val(null);
        	$('#finprodId').val(null);
        	$('#repType').val(null);
        	$('#reportName').val(null);
        	$('#confirmDate').val(null);
        	$('#confirmType').val(null);
        	$('#revealEndDate').val(null);	
        });
        $('#ReportConfirmManage001_btn_export').click(function() {
            exportExcel();
        });
        
        $(".fileclass").mouseover(function(){
        	$('.fileclass').css('visibility', 'hidden');
   		 	$(this).next().css('visibility', 'visible');
          });
        
        if($('#workflowId').val()!=''){
        	table.load();
        	$('.condition-area').hide();
        	$('.form-button-area').hide();
    	}
        
        //确认按钮
        $('#ReportConfirmManage001_btn_submit').click(function() {
            checkAllCheckedRows('1');
        });
        $('#ReportConfirmManage001_btn_delete').click(function() {
            deleteConfirmMessage();
        });
//        $('#ValNetValueConfirm001_btn_unfrozen').click(function() {
//            checkUnfrozen('1');
//        });
//        $('#ValNetValueConfirm001_btn_deleteunfrozen').click(function() {
//            checkUnfrozen('0');
//        });
        // 绑定上传事件
        $('#ReportConfirmManage001_btn_upload').click(function() {
        	redirectUrl4(table);
        });
        // 绑定下载事件
        $('#ReportConfirmManage001_btn_download').click(function() {
        	
 		   download();
	    });
     // 绑定批量上传披露文档事件
        $('#ReportConfirmManage001_btn_batchUpload').click(function() {
 		   batchUpload();
	    });
        // 绑定取消确认事件
        $('#ReportConfirmManage001_btn_confirmCancle').click(function() {
        	confirmCancle();
	    });
    });
	
});

	/**
 * 跳转页面
 */
var redirectUrl = function(param){  
    Joyin.dialog(JoyinUtil.getLabel("附件上传下载"),
    		//JoyinUtil.getRealPath("/disclosure/information/reveal/reportconfirm/ReportConfirmManage003/init?"+param),
            JoyinUtil.getRealPath("/framework/fileUpload/init?"),// 共通附件上传界面，初始化
            "50%", "50%", {},
            function(data){            	
                data;
            });         
}
/**
 * 跳转页面4[新建]
 */
var redirectUrl4 = function(param){
	
	Joyin.dialog(JoyinUtil.getLabel("报表人工上传"),
          JoyinUtil.getRealPath("/disclosure/information/reveal/reportconfirm/ReportConfirmManage004/init?"),
          "80%", "80%", {},
          function(data){
        	  param.reload();
              data;
          });
}
/**
 * 跳转页面4[新建]
 */
var redirectUrl5 = function(sysFilepath){
	
	Joyin.ajax("/disclosure/information/reveal/reportconfirm/ReportConfirmManage001/downLoad?", 
			{sysFilepath:sysFilepath},function(result){});
}