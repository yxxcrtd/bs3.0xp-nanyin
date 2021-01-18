$(function() {
    //跳转的页码
      var pageCount = 1;
      /**
       * 获取查询参数
       */
      var getSearchParams = function() {
          return {
              //从业人员类型
        	  employeeType:$('#employeeType').val()==null?null:$('#employeeType').val().join(","),
              //报送截止日
              submitendDateStart:$('#submitDeadLine').startDate(),
              submitendDateEnd:$('#submitDeadLine').endDate(),
              //数据日期
              dataDateStart:$('#dataDate').startDate(),
              dataDateEnd:$('#dataDate').endDate(),
              //报送日期
              submitDateStart:$('#submitDate').startDate(),
              submitDateEnd:$('#submitDate').endDate(),
              //报送状态
              sendStatus : $('#submitStatus').val()==null?null:$('#submitStatus').val().join(","),
              //数据状态
              status : $('#status').val()==null?null:$('#status').val().join(","),
              //反馈状态
              submitFeedbackStatus: $('#submitFeedbackStatus').val()==null?null:$('#submitFeedbackStatus').val().join(",")
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
          showDetail: true,//双击详情对比画面
          cols:[
              { title:'数据状态', field: 'statusStr',width:100, sortField:'STATUS'},
              { title:'数据日期', field: 'statusDate', type:'datetime', sortField:'STATUS_DATE'},
              { title:'报送状态', field: 'sendStatusStr',width:100 , type: 'date', sortField:'SEND_STATUS'},
              { title:'报送日期', field: 'submitTimeStr', type: 'datetime', sortField:'SUBMIT_TIME'},
              { title:'报送截止日', field: 'chbSubmitDeadlineStr', type:'datetime', sortField:'CHB_SUBMIT_DEADLINE'},
              { title:'反馈状态', field: 'submitFeedbackStatusStr',width:100, sortField:'',sortField:'SUBMIT_FEEDBACK_STATUS'},
              {title: '从业人员类型',field:'employeeType', width:100, sortField:'EMPLOYEE_TYPE'},
              {title: '姓名', field:'employeeName', width:100, sortField:'EMPLOYEE_NAME'},
              {title: '性别', field:'employeeSex', width:100, sortField:'EMPLOYEE_SEX'},
              {title: '发行机构代码',field:'issuePartyId', width:100, sortField:'ISSUE_PARTY_ID'},
              {title: '身份证号',field:'identifyNumber',width:100, sortField:'IDENTIFY_NUMBER'},
              {title: '所属总行或分支行',field:'belongsBank',width:100,sortField:'BELONGS_BANK'},
              {title: '所属区域',field:'belongsPlace', width:100, sortField:'BELONGS_PLACE'},
              {title: '具体单位名称',field:'companyName',width:100, sortField:'COMPANY_NAME'},
              {title: '所属部门',field:'belongsDept', width:100, sortField:'BELONGS_DEPT'},
              {title: '职位',field:'position',width:100, sortField:'POSITION'}, 
              {title: '学历',field:'education',width:100, sortField:'EDUCATION'},
              {title: '学位',field:'academicDegree',width:100, sortField:'ACADEMIC_DEGREE'},
              {title: '首次参加工作时间',field:'firsttime2work',width:100, sortField:'FIRSTTIME2WORK'},
              {title: '首次从事理财业务时间', field:'firsttime2finwork',width:100, sortField:'FIRSTTIME2FINWORK'},
              {title: '专业技术职称', field:'professionalTitle', width:100, sortField:'PROFESSIONAL_TITLE'},
              {title: '理财专业证书',field:'finProfessionalCrtf',width:100, sortField:'FIN_PROFESSIONAL_CRTF'},
              {title: '理财登记培训证书编号',field:'finTrainingNo',width:100, sortField:'FIN_TRAINING_NO'},
              {title: '所获奖励',field:'awards',width:100, sortField:'AWARDS'},
              {title: '办公电话',field:'officeTel',width:100, sortField:'OFFICE_TEL'},
              {title: '移动电话 ',field:'mobileTel',width:100, sortField:'MOBILE_TEL'},
              {title: '电子邮箱',field:'mail',width:100, sortField:'MAIL'},
              {title: '登记业务分类', field:'regBusiType', width:100, sortField:'REG_BUSI_TYPE'},
              {title: '登记人员类别',field:'regEmployeeType', width:100, sortField:'REG_EMPLOYEE_TYPE'},
              {title: '备注',field:'remark', width:100, sortField:'REMARK'},
              { field: 'sendStatus',visible:false},
              { field: 'submitFeedbackStatus',visible:false},
              { field: 'registSeqno',visible:false}
          ]
      });
     
     //查询
     $(function(){
         // 绑定按钮事件
         $('#SvrEmployeeInfoReg001_btn_search').click(function() {
             table.load();
         });
     });
     
     //手工处理 
     $('#SvrEmployeeInfoReg001_btn_manual').click(function() {
         // debugger
         var manArr=table.getAllCheckedRows();
         if(!manArr || manArr.length ==0){
             //当不选择时默认选择符合查询条件的全部数据
             var submitStatus = $("#submitStatus").val();
             var submitFeedbackStatus = $("#submitFeedbackStatus").val();
             if(!((submitStatus && submitStatus.length == 1 &&submitStatus[0] == '01')
                     || (submitStatus && submitStatus.indexOf('02')>-1 && submitFeedbackStatus && (submitFeedbackStatus.indexOf('02')>-1 ||  submitFeedbackStatus.indexOf('04')> -1)))){
                 Joyin.error("只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理");
                 return;
             }
             var paramObj = getSearchParams();
             paramObj.flag = "01";//判断是否选中记录
             Joyin.confirm("请确认是否要手工处理全部未报送记录？", function() {
                 //页面添加滚动条
                 layui.layer.open({type:3});
                 Joyin.ajaxJson('doManual', paramObj, function() {
                     table.load();
                     layui.layer.closeAll();
                 });
             });
         }else{
             var keyList = [];
             for(var i=0;i<manArr.length;i++){
                 if (!(manArr[i].sendStatus == '01' || (manArr[i].sendStatus == '02' && (manArr[i].submitFeedbackStatus == '发送失败' || manArr[i].submitFeedbackStatus == '中债退回')))) {
                     Joyin.error("只能选择报送状态为未报送的数据，或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理，请重新选择！");
                     return;
                 }
             };
             keyList = JSON.stringify(manArr);
             Joyin.confirm("请确认是否要手工处理选中记录？", function() {
                 layui.layer.open({type:3});
                 Joyin.ajaxJson('doManual', {keyList : JSON.stringify(manArr)}, function() {
                     table.load();
                     layui.layer.closeAll();
                 });
             });
         }
     });
     
     /**
      * 取消手工处理 
      */
    $('#SvrEmployeeInfoReg001_btn_cancelManual').click(function() {
        var manArr=table.getAllCheckedRows();
        if(!manArr || manArr.length ==0){
            var sendStatus = $("#submitStatus").val();
               if(!(sendStatus && sendStatus.length==1 && sendStatus[0] == '03')){
                 Joyin.error("只能选择手工处理的数据，请重新选择！");
                 return;
               }
               var paramObj = getSearchParams();
               paramObj.flag = '01';
               Joyin.confirm("请确认是否要取消手工处理符合查询条件所有记录？", function() {
                layui.layer.open({type:3});
             Joyin.ajaxJson('cancelMannul', paramObj, function(result) {
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
                 Joyin.ajaxJson('cancelMannul', {keyList: JSON.stringify(manArr)}, function(result) {
                     table.load();
                     layui.layer.closeAll();
                 });
            });
        }
         
    });
    
    //导出中债Excel
    $('#SvrEmployeeInfoReg001_btn_chbexport').click(function() {
        exportExcel("0");
    });
    
    /**
     * 导出
     */
    var exportExcel = function(flag) {
        var expArr=table.getAllCheckedRows();
        var param={};
        if(!expArr || expArr.length ==0){
            param = getSearchParams();
            param.flag = flag;
            if(flag=="0"){
                param.status="01";
                param.sendStatus="01";
            }
            var isHaveData = true;
            Joyin.ajaxSync('findPage',param, function(result){
                if (!result || result.root.length == 0){
                    isHaveData = false;
                    return;
                }
            })
  
            Joyin.exportExcel("export",param);
            
            if (isHaveData){
                Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function() {
                    //页面添加滚动条
                    layui.layer.open({type:3});
                    Joyin.ajaxJson('doManual', param, function() {
                        table.load();
                        layui.layer.closeAll();
                    });
                });
             }
            
        }
        else{
            // 用来标注选择数据中存在修改或者已报送数据，默认为false
            var statuFlag = false;
            if(flag=="0"){
                for(var i=0;i<expArr.length;i++){
//	        		if(expArr[i].status!="01"||expArr[i].sendStatus!="01"){
//	        			Joyin.error("只能导出新建未报送的数据，请重新选择");
//	        			return;
//        			}
                    // 含有删除状态数据
                    if (expArr[i].status=="03") {
                        Joyin.error("所选数据中包含数据状态为删除的数据，请重新选择。");
                        return;
                        // 含有修改状态数据或者已报送数据，将标志改为true
                    } else if (expArr[i].status=="02"||expArr[i].sendStatus!="01") {
                        statuFlag = true;
                    }
                }
            }
            var keyList = [];
//            $.each(expArr,function(i,v){
//                v.sendStatus=v.sendStatusCode;
//                v.status=v.statusCode;
//                v.ccy=v.ccyCode;
//            })
            param.keyList = JSON.stringify(expArr);
            param.expKeys = keyList.join(',');
            param.flag = flag;
            if (statuFlag) {
                Joyin.confirm("导出数据包含修改或已报送数据，请确认是否导出！", function() {
                    confirmExport(param, expArr);
                });
            } else {
                confirmExport(param, expArr);
            }
//            Joyin.exportExcel('export', param);
//            Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function() {
//            	layui.layer.open({type:3});
//                Joyin.ajaxJson('update', {keyList : JSON.stringify(expArr)}, function() {
//                    table.load();
//                    layui.layer.closeAll();
//                });
//            });
        }
    };
    
    
    /**
     * 确认导出之后的操作
     */
    var confirmExport = function(param, expArr) {
        Joyin.exportExcel('export', param);
        Joyin.confirm("导出成功，是否自动标识未报送数据的报送状态为“手工处理”？", function() {
            layui.layer.open({type:3});
            Joyin.ajaxJson('doManual', {keyList : JSON.stringify(expArr)}, function() {
                table.load();
                layui.layer.closeAll();
            });
        });
    };
    
    // 报送
    $('#SvrEmployeeInfoReg001_btn_submitChb').click(function(){
        var paramObj = getSearchParams();
        paramObj.opertionType = "submitChb";
        chbCommonCheck(table, paramObj, 'reportCheck', dataTitle, 'submitChb');
    });
    // 重新报送
    $('#SvrEmployeeInfoReg001_btn_reSubmitChb').click(function(){
        var paramObj = getSearchParams();
        paramObj.opertionType = "reSubmitChb";
        chbCommonCheck(table, paramObj, 'reportCheck', dataTitle, 'submitChb');
    });
    
  }());