
/**
 * Created by Administrator on 2017/8/15 0015.
 */
// By zhengyan
$(function(){
    //跳转的页码
    var pageCount = 1;
    var getPageParams = function() {
        /* 产品名称*/
        var prodId = $('#prodId').val();
    	if (prodId) {
    		prodId = prodId.join();
    	}
    	 /*产品登记编码*/
        var prodRegistCode=$('#prodRegistCode').val();
        if(prodRegistCode){
            prodRegistCode=prodRegistCode.join();
        }
    	/*报送状态*/
        var submitStatus=$('#submitStatus').val();
        if(submitStatus){
            submitStatus=submitStatus.join();
        }
    	/*数据状态*/
        var status=$('#status').val();
        if(status){
            status=status.join();
        }
        /*反馈状态*/
        var submitFeedbackStatus=$('#submitFeedbackStatus').val();
        if(submitFeedbackStatus){
            submitFeedbackStatus=submitFeedbackStatus.join();
        }
       
        return {
            // 反馈状态
            submitFeedbackStatus : submitFeedbackStatus,
            // 报送状态
            submitStatus : submitStatus,
            // 数据状态
            status : status,
            //报送日开始
            submitDate : $('#submitDate').startDate(),
            //报送日结束
            submitDateEnd : $('#submitDate').endDate(),
            //数据日期开始日
            updateTime : $('#updateTime').startDate(),
            //数据日期截止日
            updateTimeEnd : $('#updateTime').endDate(),
            //报送截止日开始
            submitDeadLineStart : $('#submitDeadLine').startDate(),
            //报送截止日结束
            submitDeadLineEnd : $('#submitDeadLine').endDate(),
            //产品名称
            prodId: prodId,
            //产品登记编码
            prodRegistCode:prodRegistCode,
            //募集起始日开始日
            raiseVdate:$('#raiseVdate').startDate(),
            //募集起始日结束日
            raiseVdateEnd: $('#raiseVdate').endDate(),
            //产品起始日开始
            prdVdate:$('#prdVdate').startDate(),
            //产品起始日结束
            prdVdateEnd: $('#prdVdate').endDate()
        };
    };
    var table = $('#prodPersonIssue').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        showDetail: false,//双击详情对比画面
        done :function(){
            if(indentifyMark){
                table.resize($(document).height() - $('.container').height() + table.getHeight());
                var addTableData = table.getAllRows();
                //标志是否在当前页选中数据
                var isChecked = false;
                if(addTableData){
                    $(addTableData).each(function(i,v){
                        if(indentifyMark===v.indentifyMark && v.submitStatusName==='未报送'){
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
            { title: "登记序号",width:100 , field: 'registSeqno',visible:false},
            { title: "数据状态", field: 'statusStr',width:200, sortable:false,sortField:'status'},
            { field: 'status',visible:false,width:200, sortable:false},
            { title: "数据日期", field: 'updateTime', type: 'datetime',width:200, sortable:false},
            { title: "报送状态", field: 'sendStatusStr',width:100, sortable:false ,sortField:'submitStatus',width:200, sortable:false},
            { title: "报送截止日", field: 'chbSubmitDeadline', type: 'datetime',width:200, sortable:false},
            { title: "反馈状态", field: 'submitFeedbackStatus',width:200, sortable:false,
                render: function (data, type, row, meta) {
                    if(!row.submitFeedbackStatus){
                        return "";
                    }
                    if(row.submitFeedbackStatus=="01"){
                        return "<span class='debt-suc'>发送成功</span>";
                    }
                    if(row.submitFeedbackStatus=="02"){
                        return "<span class='debt-fail'>发送失败</span>";
                    }
                    if(row.submitFeedbackStatus=="03"){
                        return "<span class='debt-pass'>中债通过</span>";
                    }
                    if(row.submitFeedbackStatus=="04"){
                        return "<span class='debt-return'>中债退回</span>";
                    }
                    if(row.submitFeedbackStatus=="05"){
                        return "<span class='debt-return'>文件下载失败</span>";
                    }
                }
            },
            { title: "报送日期", field: 'submitTime', type: 'datetime',width:200, sortable:false},
            { title: "	产品名称	",field:'prodName',width:200, sortable:false},
            { title: "	理财产品代码	",field:'prodId',width:200, sortable:false},
            { title: "	发行机构代码	",field:'issuePartyId',width:200, sortable:false},
            { title: "	产品审批人姓名	",field:'approverName',width:200, sortable:false},
            { title: "	产品审批人身份证号	",field:'approverId',width:200, sortable:false},
            { title: "	产品设计人姓名	",field:'designerName',width:200, sortable:false},
            { title: "	产品设计人身份证号	",field:'designerId',width:200, sortable:false},
            { title: "	投资经理姓名	",field:'managerName',width:200, sortable:false},
            { title: "	投资经理身份证号	",field:'managerId',width:200, sortable:false},
            { title: "	业务联络人姓名	",field:'liaisonName',width:200, sortable:false},
            { title: "	业务联络人座机	",field:'liaisonTel',width:200, sortable:false},
            { title: "	业务联络人手机	",field:'liaisonMobile',width:200, sortable:false},
            { title: "	业务联络人邮箱	",field:'liaisonEmail',width:200, sortable:false},
            
            { title: "	产品收益类型	",field:'profitTypeStr',width:200, sortable:false},
            { title: "	产品期限	",field:'termtypeStr',width:200, sortable:false},
            { title: "	目标客户类型	",field:'saletargetStr',width:200, sortable:false},
            { title: "	资金投向地区	",field:'investAraeStr',width:200, sortable:false},
            { title: "	产品投资国家或地区	",field:'investCountryStr',width:200, sortable:false},
            { title: "	理财业务服务模式	",field:'finanSerModelStr',width:200, sortable:false},
            { title: "	产品运作模式	",field:'operateModeStr',width:200, sortable:false},
            { title: "	会计核算方式	",field:'bookModeStr',width:200, sortable:false},
            { title: "	产品资产配置方式	",field:'cmmodeStr',width:200, sortable:false},
            { title: "	产品管理模式	",field:'pmmodeStr',width:200, sortable:false},
            
            { title: "	实际管理人名称	",field:' ',width:200, sortable:false},
            
            { title: "	产品定价方式	",field:'pricedModeStr',width:200, sortable:false},
            { title: "	投资资产类型	",field:'invAssetTypeStr',width:200, sortable:false},
            { title: "	结构性产品挂钩标的	",field:'conPrdTragetStr',width:200, sortable:false},
            { title: "	是否有预期收益率	",field:'isYieldStr',width:200, sortable:false},
            { title: "	是否有收益率测算依据	",field:'isYieldMeasBasisStr',width:200, sortable:false},
            
            { title: "	产品销售区域	",field:'saleArea',width:200, sortable:false},
            
            { title: "	起点销售金额	",field:'saleAmtStr',width:200, sortable:false},
            { title: "	募集币种	",field:'raiseCcy',width:200, sortable:false},
            { title: "	兑付本金币种	",field:'cashPrinCcy',width:200, sortable:false},
            { title: "	兑付收益币种	",field:'cashProfCcy',width:200, sortable:false},
            
            { title: "	销售手续费率%	",field:'saleFeeRate',width:200, sortable:false},
            { title: "	托管费率%	",field:'truFeeRate',width:200, sortable:false},
            { title: "	境内托管机构名称	",field:'inDepName',width:200, sortable:false},
            { title: "	境内托管机构代码	",field:'inDepCode',width:200, sortable:false},
            { title: "	境外托管机构国别	",field:'outDepCtryStr',width:200, sortable:false},
            { title: "	境外托管机构名称	",field:'outDepName',width:200, sortable:false},
            
            
            { title: "	投资者风险偏好	",field:'riskTypeStr',width:200, sortable:false},
            { title: "	产品风险等级	",field:'riskLevelStr',width:200, sortable:false},
            { title: "	发行机构提前终止权标识	",field:'termRightFlagStr',width:200, sortable:false},
            { title: "	客户赎回权标识	",field:'cusRedeemFlagStr',width:200, sortable:false},
            
            { title: "	产品品牌	",field:'prodBrand',width:200, sortable:false},
            { title: "	产品期次	",field:'prodStage',width:200, sortable:false},
            
            { title: "	合作模式	",field:'contModeStr',width:200, sortable:false},
            
            { title: "	合作机构名称	",field:'cooperationName',width:200, sortable:false},
            
            { title: "	投资本金到账日	",field:'investPrinDateStr',width:200, sortable:false},
            { title: "	投资收益到账日	",field:'investProfDateStr',width:200, sortable:false},
            { title: "	产品增信标识	",field:'creditFlagStr',width:200, sortable:false},
            { title: "	产品增信机构类型	",field:'creditTypeStr',width:200, sortable:false},
            { title: "	产品增信形式	",field:'creditModeStr',width:200, sortable:false},
            
            { title: "	投资资产种类及比例	",field:'invTypeRat',width:200, sortable:false},
            { title: "	募集起始日期	",field:'raisePstrdateStr',width:200, sortable:false},
            { title: "	募集结束日期	",field:'raisePenddateStr',width:200, sortable:false},
            { title: "	产品起始日期	",field:'prdVdateStr',width:200, sortable:false},
            { title: "	产品终止日期	",field:'prdMdateStr',width:200, sortable:false},
            
            { title: "	是否为结构化（分级）产品	",field:'isConstracteStr',width:200, sortable:false},
            { title: "	层级类别	",field:'gradeTypeStr',width:200, sortable:false},
            
            { title: "	预计客户最低年收益率%	",field:'expMinYield',width:200, sortable:false},
            { title: "	预计客户最高年收益率%	",field:'expMaxYield',width:200, sortable:false},
            { title: "	平均预计客户年收益率%	",field:'expAvgYield',width:200, sortable:false},
            { title: "	收益率分档情况说明	",field:'yieldComment',width:200, sortable:false},
            
            { title: "	开放模式	",field:'openModeStr',width:200, sortable:false},
            
            { title: "	规律开放周期	",field:'openCircleStr',width:200, sortable:false},
            
            { title: "	其他规律开放周期 (天)	",field:'openCircleDays',width:200, sortable:false},
            { title: "	无规律开放说明	",field:'ruleComment',width:200, sortable:false},
            { title: "	首次开放周期起始日	",field:'firstOpenDay',width:200, sortable:false},
            
            { title: "	节假日是否开放	",field:'holidayOnFlagStr',width:200, sortable:false},
            { title: "	平均开放次数（年化）	",field:'avgOpenCount',width:200, sortable:false},
            
            { title: "	开放期业务	",field:'openBussStr',width:200, sortable:false},
            { title: "	开放期业务说明	",field:'openBussRemark',width:200, sortable:false},
            { title: "	备注	",field:'remark',width:200, sortable:false},
            { title: "	附件个数	",field:'fileNum',width:200, sortable:false},
      /*    {field:'fileNum', title: '附件个数',
        	render : function(data, type, row, meta) {
                return '<a href="' + JoyinUtil.getRealPath('/framework/download/' + row.fileId) + '">'+data+'</a>';
            }
          },*/
            {
                title: '操作',field: 'operation', type: 'action', width: 150, buttons: [
                 {
                    text: '&#xe67c;',
                    clas: 'upload',
                    title: '上传',
                     muiltiple:"true"
                },
                {   text: '&#xe640;',
                    clas: 'delt',
                    title: '删除'
                   
                },
                {
                	 text: '&#xe601;',
                     clas: 'download',
                     title: '下载'
                },
                {
					text : '&#xe615;',
					clas : 'fileView',
					title : '查看'
				}
                ]
            }
        ]
    });

    // 查询按钮点击事件
    $('#SvrProdOrgIssue001_btn_search').click(function() {
        /*if($('#searchForm').joyinvalid()) {*/
            table.load();
//        }
    });

    // 导出中债Excel按钮点击事件
    $('#SvrProdOrgIssue001_btn_chbexport').click(function() {
        exportMethod("0");
    });

    // 导出查询结果按钮点击事件
    $('#SvrProdOrgIssue001_btn_export').click(function() {
        exportMethod("1");
    });

    //导出方法
    var exportMethod = function (flag){
        var expArr=table.getAllCheckedRows();
        var param={};
        if(!expArr || expArr.length ==0){
            param = getPageParams();
            param.flag = flag;
            if(flag=="0" &&(param.status==null||param.status=='')){
            	param.status="01,02";
            }
            ///param.exportflag = 'export';
            Joyin.exportExcel("export",param);
        }
        else{
        	if(flag=="0")
        		{
        	for(var i=0;i<expArr.length;i++){
        		if(expArr[i].status=="03"){
        			Joyin.error("只能导出新建或修改状态的数据，请重新选择");
        			return;
        			}
        		}
        		}
            var keyList = [];
            $.each(expArr, function(i, row) {
                //keyList.push(row.bankCode + '|' + row.indentifyMark + '|' + row.submitStatus);
                keyList.push(row.registSeqno);
            });
            param.expKeys = keyList.join(',')
            param.flag = flag;
//            param.exportflag = 'exportSelected';
//            Joyin.exportExcel("exportSelect",param);
            ///param.exportflag = 'export';
            Joyin.exportExcel("export",param);
        }

    };


    // 导入按钮点击事件
    $('#SvrProdOrgIssue001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax('importFile', {fileName: fileName});
            },function() {
                table.load();
            });
        });
    });
    //导出删除模板
    $('#SvrProdOrgIssue001_btn_exportDel').click(function(){
        if(!table.isSearched()) {
            return;
        }
        var expArr=table.getAllCheckedRows();
        var param={};
        if(!expArr || expArr.length ==0){
            param = getPageParams();
            if (param.status==null||param.status==''){
            	param.status='03';
            }
            param.flag = "2";
            param.submitStatus='01';
            Joyin.exportExcel("export",param);
        }
        else{
        	
        	 for(var i=0;i<expArr.length;i++){
                 if(expArr[i].status !="03"||expArr[i].submitStatus !="01"){
                     Joyin.error("只能导出删除状态且未报送的数据，请重新选择");
                     return;
                 }
             }
        	 
            var keyList = [];
            $.each(expArr, function(i, row) {
                keyList.push(row.bankCode + '|' + row.indentifyMark + '|' + row.submitStatus);
            });
            param.expKeys = keyList.join(',')
            param.flag = "2";
            Joyin.exportExcel("exportDel",param);
        }
    });
    //数据检查
    $('#SvrProdOrgIssue001_btn_checkData').click(dateCheckMethod);
    function dateCheckMethod(){
        var checkArr=table.getAllCheckedRows();
        if(checkArr.length === 0) {
            var submitStatus = $('#submitStatus').val();
            var status = $('#status').val();
//            if(submitStatus!='01'){
            if (!(submitStatus && submitStatus.length == 1 && submitStatus[0] == '01')) {
                Joyin.error("只能对报送状态为未报送，数据状态为新建和修改的数据进行数据检查！");
                return;
            }
//            else if(status && status.includes('03')){
            else if(status && status.indexOf('03') > -1){
                Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
                return;
            }
            var paramObj = getPageParams()
            if(!status){
                paramObj.status = '01,02'; 
            }
            Joyin.ajax('reportCheck',paramObj, function(result){
                window.checkResult = result;
                Joyin.checkData(dataTitle, checkUrl, getPageParams());
            })
            return ;
        }
        var keyList = [];
        for(var i=0;i<checkArr.length;i++){
        	//modify by wangmin 修改取数顺序 start 2018-07-12
//          keyList.push(checkArr[i].submitStatus + '|' + checkArr[i].bankCode + '|' + checkArr[i].indentifyMark );
        	keyList.push(checkArr[i].bankCode + '|' + checkArr[i].indentifyMark + '|' + checkArr[i].submitStatus );
        	//modify by wangmin 修改取数顺序 end 2018-07-12
            if(checkArr[i].submitStatusName !="未报送"){
                Joyin.error("数据检查只能检查未报送数据，请重新选择！");
                return;
            }
//            else if(checkArr[i].status && checkArr[i].status.includes('03')){
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
    $('#SvrProdOrgIssue001_btn_manual').click(function(){
        var rows = table.getAllCheckedRows();
        if(rows.length === 0) {
          var submitStatus = $("#submitStatus").val();
          var submitFeedbackStatus = $("#submitFeedbackStatus").val();
          if(!((submitStatus && submitStatus.length == 1 &&submitStatus[0] == '01')
              || (submitStatus && submitStatus.indexOf('02')>-1 && submitFeedbackStatus && (submitFeedbackStatus.indexOf('02')>-1 ||  submitFeedbackStatus.indexOf('04')> -1)))){
            Joyin.error("只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理");
            return;
          }
          var paramObj = getSearchParams();
          paramObj.flag = '01';
          Joyin.confirm("请确认是否要手工处理符合查询条件的全部记录？", function() {
            layui.layer.open({type:3});
                 Joyin.ajaxJson('update', paramObj, function() {
                     table.load();
                     layui.layer.closeAll();
                 });
             });
        }else{
          for(var i=0;i<rows.length;i++){
              if (!(rows[i].sendStatus == '01'
                  || (rows[i].sendStatus == '02' && (rows[i].submitFeedbackStatus == '02' || rows[i].submitFeedbackStatus == '04')))) {
                  Joyin.error("只能选择报送状态为未报送的数据，或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理，请重新选择！");
                  return;
              }
          }
        var keyList = [];
        keyList = JSON.stringify(rows);
          Joyin.confirm("请确认是否要手工处理选中记录？", function() {
            layui.layer.open({type:3});
              Joyin.ajaxJson('update', {keyList: JSON.stringify(rows)}, function() {
                  table.load();
                  layui.layer.closeAll();
              });
          });
        }
        
    });
    //liaochunyu add 2017-12-02 start
    /**
     * 取消手工处理 
     */
   $('#SvrProdOrgIssue001_btn_cancelManual').click(function() {
       var manArr=table.getAllCheckedRows();
       if(!manArr || manArr.length ==0){
    	   var submitStatus = $("#submitStatus").val();
       	   if(!(submitStatus && submitStatus.length==1 && submitStatus[0] == '03')){
       		 Joyin.error("只能选择手工处理的数据，请重新选择！");
                return;
       	   }
       	   var paramObj = getSearchParams();
       	   paramObj.flag = '01';
       	   Joyin.confirm("请确认是否要取消手工处理符合查询条件所有记录？", function() {
       		layui.layer.open({type:3});
            Joyin.ajaxJson('cancelMannul', paramObj, function() {
                table.load();
                layui.layer.closeAll();
            	});
       	   });
       }else{
    	   for(var i=0;i<manArr.length;i++){
    		  
               if(manArr[i].sendStatus !="03"){
                   Joyin.error("只能选择手工处理的数据，请重新选择！");
                   return;               
               }
           };
            Joyin.confirm("请确认是否要取消手工处理选中记录？", function() {
            	layui.layer.open({type:3});
                Joyin.ajaxJson('cancelMannul', {keyList: JSON.stringify(manArr)}, function() {
                    table.load();
                    layui.layer.closeAll();
                });
           });
       }
        
   });
    //liaochunyu add 2017-12-02 end
    //报送
    $('#SvrProdOrgIssue001_btn_submit').click(function() {
        var submitArr=table.getAllCheckedRows();
        if(submitArr.length === 0) {
            var paramObj = getPageParams();
            var submitStatus = $('#submitStatus').val();
            var status = $('#status').val();
//            if(submitStatus!='01'){
            if (!(submitStatus && submitStatus.length == 1 && submitStatus[0] == '01')) {
                Joyin.error("只能对报送状态为未报送，数据状态为新建和修改的数据进行报送！");
                return;
            }
//            else if(status && status.includes('03')){
            else if(status && status.indexOf('03') > -1){
                Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
                return;
            }
            if(!status){
                paramObj.status = '01,02'; 
            }
            Joyin.ajax('sendBeforeCheck',paramObj, function(result){
                if(!result || result.length ==0){
                    Joyin.confirm("按照已选择当前查询条件报送,是否确认！",function(){
                    	layui.layer.open({type:3});
                        Joyin.ajaxJson('submitChb', getPageParams(), function() {
                            table.load();
                            layui.layer.closeAll();
                        });
                    });
                }
                else{
                    window.checkResult = result;
                    Joyin.checkData(dataTitle, checkUrl, getPageParams());
                }
            })
            return ;
        }
        var keyList = [];
        for(var i=0;i<submitArr.length;i++){
            keyList.push(submitArr[i].bankCode + '|' + submitArr[i].indentifyMark + '|' + submitArr[i].submitStatus+ '|' + submitArr[i].sumitSeq);
            if(submitArr[i].submitStatus !="01"){
                Joyin.error("选择的报送数据中存在已报送数据，请重新选择！");
                return;
            }
            else if(submitArr[i].status =="03" && submitArr[i].submitStatus =="01"){
                Joyin.error("选择的报送数据中存在删除状态的数据，请重新选择！");
                return;
            }
        };

        Joyin.ajax('sendBeforeCheck',{checkKeys:keyList.join(',')}, function(result){
            if(!result || result.length ==0){
                Joyin.confirm("请确认是否要报送选中记录？", function() {
                	layui.layer.open({type:3});
                    Joyin.ajaxJson('submitChb', {submitKeys:keyList.join(',')}, function() {
                        table.load();
                        layui.layer.closeAll();
                    });
                });
            }
            else{
                window.checkResult = result;
                Joyin.checkData(dataTitle, checkUrl, {checkKeys: keyList.join(',')});
            }
        })
//        Joyin.confirm("请确认是否要报送选中记录？", function() {
//            Joyin.ajaxJson('submitChb', {submitKeys:keyList.join(',')}, function() {
//                table.load();
//            });
//        });
    });
    
    // add by etliu 20180824 start
    // 二次报送
    $('#SvrProdOrgIssue001_btn_submitAgain').click(function() {
        var paramObj = getPageParams();
        
        var submitArr = table.getAllCheckedRows();
        if(submitArr.length === 0) {
            var submitStatus = $('#submitStatus').val();
            var status = $('#status').val();
            var submitFeedbackStatus = $('#submitFeedbackStatus').val();
            if (!(submitStatus && submitStatus.length == 1 && submitStatus[0] == '02')) {
                Joyin.error("只能对报送状态为接口报送的数据进行重新报送！");
                return;
            }
//            if(status && status.includes('03')){
            if(status && status.indexOf('03') > -1){
                Joyin.error("只能对数据状态为新建和修改的数据进行重新报送！");
                return;
            }
            if(submitFeedbackStatus && 
                    (submitFeedbackStatus.indexOf('01') > 0
                            || submitFeedbackStatus.indexOf('03') > 0
                            || submitFeedbackStatus.indexOf('05') > 0)) {
                Joyin.error("只能对反馈状态为发送失败和中债退回的数据进行重新报送！");
                return;
            }
            
            if(!status){
                paramObj.status = '01,02'; 
            }
            if (!submitFeedbackStatus) {
                paramObj.submitFeedbackStatus = '02,04'; 
            }
            Joyin.confirm("请确认是否要按照已选择当前查询条件进行二次报送？",function(){
            	layui.layer.open({type:3});
                Joyin.ajaxJson('submitCbAgain', paramObj, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
            return ;
        }
        
        var keyList = [];
        for (var i = 0; i < submitArr.length; i++) {
        	var submitFeedbackStatus = submitArr[i].submitFeedbackStatus;
        	var submitStatus = submitArr[i].submitStatus;
        	if(submitStatus&&submitStatus.indexOf('01') > -1){
        		 Joyin.error("未报送的数据不能进行二次报送！");
                 return;
        	}else if(!submitFeedbackStatus){
                Joyin.error("反馈状态为空的数据不能进行二次报送！");
                return;
        	}else if(submitFeedbackStatus&& 
                    (submitFeedbackStatus.indexOf('01') > -1
                            || submitFeedbackStatus.indexOf('03') > -1
                            || submitFeedbackStatus.indexOf('05') > -1)){
                    Joyin.error("只能对反馈状态为发送失败和中债退回的数据进行二次报送！");
                    return;
        	}
            keyList.push(submitArr[i].sumitSeq);
        };
        
        paramObj.keyList = keyList.join(',');
        
        Joyin.confirm("请确认是否要二次报送？",function(){
        	layui.layer.open({type:3});
            Joyin.ajaxJson('submitCbAgain', paramObj, function() {
                table.load();
                layui.layer.closeAll();
            });
        });
    });
    // add by etliu 20180824 end
    
    table.bind('click', '.upload', function(data, index) {
        var p='';
		if (data.sendStatus != '01') {
			Joyin.error("只有未报送的记录可以上传！");
			return;
		}
		
		if (!data.fileId || data.fileId == null) {
			uploadTheFile(data,index);
		}else {
			Joyin.confirm("本产品已存在附件，是否覆盖？", 
					function() {
				layui.layer.open({
					type : 3
				});
				uploadTheFile(data,index);
			})
		}
		
		
	});
	
	


//		$('#PerProDeclareReg001_btn_upload').click(function() {
//		Joyin.uploadFile({
//			exts : 'zip',
//			hideTpl : true
//		}, function(fileName, param, fileNo, targetFile, fileId) {
//			Joyin.ajax('uploadFile', {param}, function() {
//				table.reload();
//			});
//		});
//	});
		
		
		

	table.bind('click', '.download', function(data, index) {
		if (!data.fileId || data.fileId == null) {
			Joyin.error("未上传附件，不能下载！");
			return;
		}
		parm = {};
		var url = JoyinUtil.getRealPath('/framework/download/' + data.fileId);
		//		  Joyin.exportExcel('/framework/download/' + data.fileId,parm);
		Joyin.ajax(url, parm, function downloadFileByForm() {
			Joyin.exportExcel('/framework/download/' + data.fileId, parm);
		});

	});
	
	
	
	
	table.bind('click', '.fileView', function(data, index) {
		
		$.post('getFileList', {fileNo:data.fileId}, function(str){
			  layer.open({
			    type: 0,
			    content:decodeURIComponent(str), //注意，如果str是object，那么需要字符拼接。
			    area: ['300px', '300px'],
			    skin: 'layui-layer-lan',
			    offset: 'rb'
			  });
			});
	});

	table.bind('click', '.delt', function(data, index) {
		if (data.sendStatus != '01') {
			Joyin.error("不允许删除已登记数据的附件");
			return;
		}
		if (!data.fileId || data.fileId == null) {
			Joyin.error("未上传附件，不能删除！");
			return;
		} else {
			Joyin.confirm("请确认是否要删除已存在的附件？", function() {
				Joyin.ajax('deltFile', {
					registSeqno : data.registSeqno,
					fileNo:data.fileId
				}, function() {
					table.reload();
				})
				
				Joyin.ajax('/framework/remove/' + data.fileId, {}, function() {
					var url = JoyinUtil.getRealPath('/framework/remove/'
							+ data.fileId);
					var form = $("<form></form>").attr("action", url).attr(
							"method", "get");
					form.append($("<input></input>").attr("type", "hidden"));
					form.appendTo('body').submit().remove();
				}

				)
			});
		}
	  }
   );
    
    var init= function(){
        $('#updateTime').val('');
        $('#submitDate').val('');

        if(isMain=='01'){
            setTimeout(function() {
                $('#submitDeadLine').val(searchDataStart+' - '+searchDataEnd);
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
});