$(function(){
    //跳转的页码
    var pageCount = 1;
    var getPageParams = function() {
            var param = $.serializeToObject('#searchForm');
        	param.searchSubmitDeadlineStart = $('#submitDeadline').startDate();
        	param.searchSubmitDeadlineEnd = $('#submitDeadline').endDate();
        	param.searchStatusDateStart = $('#statusDate').startDate();
        	param.searchStatusDateEnd = $('#statusDate').endDate();
        	param.searchSubmitTimeStart = $('#submitTime').startDate();
        	param.searchSubmitTimeEnd = $('#submitTime').endDate();
            return param;
        };

    var table = $('#SalesTransactionMaintenanceRegist').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        showDetail: true,//双击详情对比画面
        done :function(){
            if(indentifyMark){
                table.resize($(document).height() - $('.container').height() + table.getHeight());
                var addTableData = table.getAllRows();
                //标志是否在当前页选中数据
                var isChecked = false;
                if(addTableData){
                    $(addTableData).each(function(i,v){
                        if(indentifyMark===v.indentifyMark && v.sendStatusName==='未报送'){
                            table.checkRow(v.LAY_TABLE_INDEX);
                            dateCheckMethod();
                            isChecked = true;
                            return false;
                        }
                    })
                }
                if(!isChecked){
                    pageCount++;
                    table.reload({page:{curr:pageCount}});
                }
            }
        },
        cols: [
             {field: 'dataStatusName', title: "数据状态",width:100,sortField:'dataStatus'}
            ,{field: 'sendStatusName', title: "报送状态", width:100 ,sortField:'sendStatus'}
            ,{field: 'submitTimeStr', title: "报送时间", width:150,sortField:'submitTime'}
            ,{field: 'submitDeadlineStr', title: "报送截止日", width:150,sortField:'submitDeadline'}
            ,{field: 'submitFeedbackStatusStr', title: "反馈状态", width:100,sortField:'submitFeedbackStatus'}
            ,{field:'institutionCode', title: '提供销售结算资金服务机构代码', width:'150',sortField:'institutionCode'}
            ,{field:'prdRegistCode', title: '产品登记编码', width:'150',sortField:'prdRegistCode'}
            ,{field:'accountNo', title: '销售结算资金账号', width:'150',sortField:'accountNo'}
            ,{field:'accountName', title: '销售结算资金账户名称', width:'150',sortField:'accountName'}
            ,{field:'tradeNo', title: '销售结算资金交易流水号', width:'150',sortField:'tradeNo'}
            ,{field:'tradePsName', title: '销售结算资金流动方向', width:'150',sortField:'tradePs'}
            ,{field:'tradeCounterAccNo', title: '销售结算资金交易对手方账号', width:'150',sortField:'tradeCounterAccNo'}
            ,{field:'tradeCounterAccName', title: '销售结算资金交易对手方账户名称', width:'150',sortField:'tradeCounterAccName'}
            ,{field:'tradeCounterAccBankName', title: '销售结算资金交易对手方开户行名称', width:'150',sortField:'tradeCounterAccBankName'}
            ,{field:'tradeCounterAccBankNo', title: '销售结算资金交易对手方账户开户行号', width:'150',sortField:'tradeCounterAccBankNo'}
            ,{field:'tradeCcyName', title: '销售结算资金交易币种', width:'150',sortField:'tradeCcy'}
            ,{field:'tradeAmt', title: '销售结算资金交易金额', width:'150',sortField:'tradeAmt',type:'money',digit: 2}
            ,{field:'tradeVdate', title: '销售结算资金交易日期', width:'150',sortField:'tradeVdate',type:'date'}
            ,{field:'tradeTime', title: '销售结算资金交易时间', width:'150',sortField:'tradeTime'}
            ,{field:'postscript', title: '销售结算资金交易附言', width:'150',sortField:'postscript'}
            ,{field:'remark', title: '备注', width:'150',sortField:'remark'}
        ]
    });

    // 查询按钮点击事件
    $('#SalesTransactionMaintenanceRegist001_btn_search').click(function() {
        table.load();
    });

    // 导出中债Excel按钮点击事件
    $('#SalesTransactionMaintenanceRegist001_btn_chbexport').click(function() {
        exportZZExcel();
    });

    // 导出查询结果按钮点击事件
    $('#SalesTransactionMaintenanceRegist001_btn_export').click(function() {
        exportMethod("1");
    });

    //导出方法
    var exportMethod = function (flag){
    debugger;
        var expArr=table.getAllCheckedRows();
        var param={};
        if(!expArr || expArr.length ==0){
            param = getPageParams();
            param.flag = flag;
            if(flag=="0" &&(param.dataStatus==null||param.dataStatus=='')){
                param.dataStatus="01,02";
            }
            Joyin.exportExcel("export",param);
        }
        else{
            if(flag=="0")
            {
                for(var i=0;i<expArr.length;i++){
                    if(expArr[i].dataStatus=="03"){
                        Joyin.error("只能导出新建或修改状态的数据，请重新选择");
                        return;
                    }
                }
            }
            var keyList = [];
            $.each(expArr, function(i, row) {
                keyList.push(row.institutionCode + '|' + row.tradeNo + '|' + row.sendStatus);
            });
            param.expKeys = keyList.join(',')
            param.flag = flag;
            Joyin.exportExcel("exportSelect",param);
        }

    };

    //数据检查
    $('#SalesTransactionMaintenanceRegist001_btn_checkData').click(dateCheckMethod);
    function dateCheckMethod(){
        var checkArr=table.getAllCheckedRows();
        if(checkArr.length === 0) {
            var sendStatus = $('#sendStatus').val();
            var dataStatus = $('#dataStatus').val();
            if (!(sendStatus && sendStatus.length == 1 && sendStatus[0] == '01')) {
                Joyin.error("只能对报送状态为未报送，数据状态为新建和修改的数据进行数据检查！");
                return;
            }
            else if(dataStatus && dataStatus.indexOf('03') > -1){
                Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
                return;
            }
            var paramObj = getPageParams()
            if(!dataStatus){
                paramObj.dataStatus = '01,02';
            }
            Joyin.ajax('reportCheck',paramObj, function(result){
                window.checkResult = result;
                Joyin.checkData(dataTitle, checkUrl, getPageParams());
            })
            return ;
        }
        var keyList = [];
        for(var i=0;i<checkArr.length;i++){
            keyList.push(checkArr[i].institutionCode + '|' + checkArr[i].tradeNo + '|' + checkArr[i].sendStatus );
            if(checkArr[i].sendStatusName !="未报送"){
                Joyin.error("数据检查只能检查未报送数据，请重新选择！");
                return;
            }
            else if(checkArr[i].status && checkArr[i].status.indexOf('03') > -1){
                Joyin.error("选择的报送数据中存在删除状态的数据，请重新选择！");
                return;
            }
        };
        Joyin.ajax('reportCheck',{checkKeys:keyList.join(',')}, function(result){
            window.checkResult = result;
            Joyin.checkData(dataTitle, checkUrl, {checkKeys: keyList.join(',')});
        })

    }
    // 手工处理 
    $('#SalesTransactionMaintenanceRegist001_btn_manual').click(function() {
        var manArr=table.getAllCheckedRows();
        if(!manArr || manArr.length ==0){
            //当不选择时默认选择符合查询条件的全部数据
            var sendStatus = $("#sendStatus").val();
            var submitFeedbackStatus = $("#submitFeedbackStatus").val();
            if(!((sendStatus && sendStatus.length == 1 &&sendStatus[0] == '01')
                || (sendStatus && sendStatus.indexOf('02')>-1 && submitFeedbackStatus && (submitFeedbackStatus.indexOf('02')>-1 ||  submitFeedbackStatus.indexOf('04')> -1)))){
                Joyin.error("只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理");
                return;
            }
            var paramObj = getPageParams();
            paramObj.flag = "01";//判断是否选中记录
            Joyin.confirm("请确认是否要手工处理全部未报送记录？", function() {
                //页面添加滚动条
                Joyin.ajax('checkConfirmMsgForAllManual', null, function() {
                    Joyin.ajax('update', {
                        flag:'01',
                        key: JSON.stringify(paramObj),
                        selectedBatchGroup: '3073',
                        selectedProductGroup: '',
                        startDate: new Date(),
                        endDate: new Date(),
                        batchGroupType: '1',
                        prodGroupType: '0',
                        detailName: 'SFSGCL'
                    }, function() {
                        table.load();
                        layui.layer.closeAll();
                    });
                });
            });
        }else{
            var keyList = [];
            for(var i=0;i<manArr.length;i++){
                keyList.push(manArr[i].bankCode + '|' + manArr[i].indentifyMark + '|' + manArr[i].sendStatus+ '|' + manArr[i].sumitSeq);
                if (!(manArr[i].sendStatus == '01'
                    || (manArr[i].sendStatus == '02' && (manArr[i].submitFeedbackStatus == '02' || manArr[i].submitFeedbackStatus == '04')))) {
                    Joyin.error("只能选择报送状态为未报送的数据，或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理，请重新选择！");
                    return;
                }
            };
            Joyin.confirm("请确认是否要手工处理选中记录？", function() {
                Joyin.ajax('update', {updateKeys:keyList.join(',')}, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
        }
    });
    // 取消手工处理
    $('#SalesTransactionMaintenanceRegist001_btn_cancelManual').click(function() {
        var manArr=table.getAllCheckedRows();
        if(!manArr || manArr.length ==0){
            var sendStatus = $("#sendStatus").val();
            if(!(sendStatus && sendStatus.length==1 && sendStatus[0] == '03')){
                Joyin.error("只能选择手工处理的数据，请重新选择！");
                return;
            }
            var paramObj = getPageParams();
            paramObj.flag = "01";//判断是否选中记录
            Joyin.confirm("请确认是否取消手工处理全部未报送记录？", function() {
                layui.layer.open({type:3});
                Joyin.ajaxJson('cancelMannul', paramObj, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
        }else{
            var keyList = [];
            for(var i=0;i<manArr.length;i++){
                keyList.push((manArr[i].sumitSeq =='' ? null : manArr[i].sumitSeq) +'|'+ manArr[i].bankCode + '|' + manArr[i].indentifyMark);
                if(manArr[i].sendStatusName !="手工处理"){
                    Joyin.error("只能选择手工处理的数据，请重新选择！");
                    return;
                }
            };
            Joyin.confirm("请确认是否要取消手工处理选中记录？", function() {
                layui.layer.open({type:3});
                Joyin.ajaxJson('cancelMannul', {updateKeys:keyList.join(',')}, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
        }

    });

    var init= function(){
        $('#updateTime').val('');
        $('#submitDate').val('');

        if(isMain=='01'){
            setTimeout(function() {
                if($('#sendStatus').val()=='02'||$('#sendStatus').val()=='03'){
                    $('#submitDate').val(searchDataStart+' - '+searchDataEnd);
                }else{
                    $('#submitDeadLine').val(searchDataStart+' - '+searchDataEnd);
                }
                table.load();
            }, 500);
        }

        //报送前检查调用数据检查
        if(indentifyMark){
            setTimeout(function() {
                table.load();
            },500)
        }
    };
    init();
    
    
    //导出中债excel
    var exportZZExcel = function(flag) {
    	var expArr=table.getAllCheckedRows();
        
        //先判断有没有勾选数据
        if(!expArr || expArr.length ==0){
            	var param = getPageParams();
                param.dataStatus='01,02';
                param.flag = "0";
            exportAndDoManual(param);
        }else{
        	//有勾选数据
        	//先判断勾选的数据有没有包含有删除状态的数据
          	 for(var i=0;i<expArr.length;i++){
                 if(expArr[i].dataStatus =="03"){
                	 //如果包含有删除状态的数据，给出提示并返回
                     Joyin.error(JoyinUtil.getMessage("REPORT_MANUAL_0010"));
                     return;
                 }
             }
          	 //不包含有删除状态的数据
          	 var showTip=false;
          	 var doManualTip=false;
          	for(var i=0;i<expArr.length;i++){
          		if(expArr[i].sendStatus=="01"){
          			doManualTip=true; 
          		}
          	}
          	 for(var i=0;i<expArr.length;i++){
          		 if(expArr[i].dataStatus =="02"||expArr[i].sendStatus!="01"){
                     //若勾选数据中含有数据状态为“修改”或报送状态不为“未报送”的数据时
                	 //赋值showTip为true，给出提示框
                	 showTip=true;
                     break;
                 }
                 
             }
          	 //根据提示状态判断
          	if(showTip){
          		//给出提示框【导出数据包含修改或已报送数据，请确认是否导出！】
                Joyin.confirm(JoyinUtil.getMessage("REPORT_MANUAL_0011"), function() {
                	  //点击“是”导出数据并且手工处理，否则重新选择数据
                	  exportZZ(doManualTip,expArr);
                  });
          	}else{
          		//勾选的数据正常，不需要给出提示，直接导出数据并且手工处理
          		exportZZ(doManualTip,expArr);
          	}
        }
    	
    }
    function exportZZ(flag,expArr){
    	//导出中债excel并且调用手工处理
        $.each(expArr, function(i, v) {
            v.sendStatus=v.sendStatusCode;
            v.status=v.statusCode;
            v.ccy=v.ccyCode;
        });
        var param = {};
        var keyList = [];
        $.each(expArr, function(i, row) {
            keyList.push(row.institutionCode + '|' + row.tradeNo + '|' + row.sendStatus + '|' + row.sumitSeq);
        });
        param.sendStatus='01,02';
        param.flag = "0";
        param.keyList = JSON.stringify(expArr);
        param.expKeys = keyList.join(',')
        Joyin.exportExcel('export', param);
        //检查是否正在批处理导出
//       if(flag){
//	        Joyin.confirm(JoyinUtil.getMessage("REPORT_MANUAL_0012"), function() {
//	              Joyin.ajax('checkConfirmMsgForAllManual', null, function() {
//                     layui.layer.open({type:3});
//                   Joyin.ajax('update', {flag : '02',updateKeys:keyList.join(',')}, function() {
//                       table.load();
//                       layui.layer.closeAll();
//                   });
//                 });
//	          });
//       }
    }
    
    function exportAndDoManual(param){
        Joyin.ajaxJson('checkExport', param, function(result){
            if(result.data){
                Joyin.confirm(JoyinUtil.getMessage("是否将导出数据的报送状态改为“手工处理”？"), function() {
                    // 后台批处理导出
                    Joyin.ajax('batchExportAndDoManual',
                        {key: JSON.stringify(param),
                            selectedBatchGroup: '3074,3073',// 批处理id
                            selectedProductGroup: '',
                            startDate: new Date(),
                            endDate: new Date(),
                            batchGroupType: '1',
                            prodGroupType: '0',
                            detailName: '销售结算资金交易登记-导出中债excel文件'},
                        function(result2) {
                            if(result2.data){
                                Joyin.alert(JoyinUtil.getMessage("REPORT_MANUAL_0009"));
                            }else{
                                Joyin.alert(result2.message);
                            }
                            table.load();
                        }
                    );
                },null,function(){
                    // 后台批处理导出
                    Joyin.ajax('batchExport',
                        {key: JSON.stringify(param),
                            selectedBatchGroup: '3074',// 批处理id
                            selectedProductGroup: '',
                            startDate: new Date(),
                            endDate: new Date(),
                            batchGroupType: '1',
                            prodGroupType: '0',
                            detailName: '销售结算资金交易登记-导出中债excel文件'},
                        function(result2) {
                            if(result2.data){
                                Joyin.alert(JoyinUtil.getMessage("REPORT_MANUAL_0009"));
                            }else{
                                Joyin.alert(result2.message);
                            }
                            table.load();
                        }
                    );
                });
            } else {
                // 无勾选导出，数量小于设置值走勾选逻辑
                Joyin.ajax('searchInfo',
                    param,
                    function (result) {
                            if (result && result.length > 0){
                                exportZZ(true,result);
                            }
                    });
            }
        });
    }
    
    function doManual(message,param){
    	Joyin.confirm(message, function() {
    		Joyin.ajax('checkConfirmMsgForAllManual', null, function() {
    			layui.layer.open({type:3});
	            Joyin.ajax('update', {
	            	flag:'01',
                	key: JSON.stringify(param),
                	selectedBatchGroup: '3073',
    				selectedProductGroup: '',
    				startDate: new Date(),
    				endDate: new Date(),
    				batchGroupType: '1',
    				prodGroupType: '0',
    				detailName: 'SFSGCL'
	            }, function() {
	                table.load();
	                layui.layer.closeAll();
	            });
    		});
            }); 
    }

});