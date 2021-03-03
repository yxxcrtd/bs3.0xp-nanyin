$(function() {
    //跳转的页码
      var pageCount = 1;
      /**
       * 获取查询参数
       */
      var getSearchParams = function() {
          return {
              //报送截止日
              submitDeadLineStart:$('#submitDeadLine').startDate(),
              submitDeadLineEnd:$('#submitDeadLine').endDate(),
              //报送状态
              sendStatus : $('#sendStatus').val()==null?null:$('#sendStatus').val().join(","),
              //数据状态
              status : $('#status').val()==null?null:$('#status').val().join(","),
              //数据日期
              statusDateStart:$('#updateTime').startDate(),
              statusDateEnd:$('#updateTime').endDate(),
              //报送日期
              submitDateStart:$('#submitDate').startDate(),
              submitDateEnd:$('#submitDate').endDate(),
              //反馈状态
              submitFeedbackStatus: $('#submitFeedbackStatus').val()==null?null:$('#submitFeedbackStatus').val().join(","),
              //登记银行编码
              planAssetIdLike: $('#planAssetIdLike').val(),
              assetCodeLike: $('#assetCodeLike').val(),
              //持仓日期
              posDateStart:$('#posDate').startDate(),
              posDateEnd:$('#posDate').endDate()
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
          done : function(){
              if(validKey){
                  table.resize($(document).height() - $('.container').height() + table.getHeight());
                //标志是否在当前页选中数据
                  var isChecked = false;
                  var addTableData = table.getAllRows();
                  if(addTableData){
                      $(addTableData).each(function(i,v){
                          if(validKey.prdRegistCode===v.prdRegistCode && validKey.indentifyMark===v.indentifyMark
                                  && (Date.ofDate(validKey.holdDate).getTime()) === (Date.ofDate(v.holdDate).getTime()) 
                                  && validKey.ccy===v.ccyCode 
                                  && validKey.registBankCode===v.registBankCode
                                  && v.sendStatusCode==='01'){
                              table.checkRow(v.LAY_TABLE_INDEX);
                              isChecked = true;
                              dateCheckMethod("checkData");
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
          cols:[
              { field: 'sendStatus',visible:false},
              { field: 'status',visible:false},
              { title: "数据状态", field: 'statusStr',width:100,sortField:'status'},
              { title: "数据日期", field: 'statusDate', type: 'datetime',sortField:'statusDate'},
              { title: "报送状态", field: 'sendStatusStr',width:100 ,sortField:'sendStatus'},
              { title: "报送日期", field: 'submitTime',width:100, type: 'datetime',sortField:'submitTime'},
              { title: "报送截止日", field: 'chbSubmitDeadline',width:100, type: 'datetime',sortField:'chbSubmitDeadline'},
              { title: "反馈状态", field: 'submitFeedbackStatus',width:100,sortField:'submitFeedbackStatus'},
              {title: '发行机构代码',field:'issuePartyId', width:150,sortField:'issuePartyId'},
              {title: '对应资管及委外资产行内资产/负债编码', field:'planAssetId', width:300,sortField:''},
              {title: '资管及委外资产当前总数量',field:'totalNum', width:200,type:'number', digit:5,sortField:'totalNum'},
              {title: '资管及委外资产当前总折算人民币金额(元)',field:'issueAmt', width:280,type:'number', digit:2,sortField:'issueAmt'},
              {title: '资管及委外资产未投资头寸(元)',field:'uninvestCash', width:250,type:'number', digit:2,sortField:'uninvestCash'},
              {title: '行内资产/负债编码',field:'assetCode', width:200,sortField:'assetCode'},
              {title: '底层资产持仓数量',field:'posNum', width:250,type:'number', digit:5,sortField:'posNum'},
              {title: '底层资产折算人民币市值(元)',field:'posAmt', width:250,type:'number', digit:2,sortField:'posAmt'},
              {title: '持仓日期',field:'posDate', width:100, type: 'date',sortField:'posDate'},
              {field:'sendStatus', visible:false},
              {field:'sumitSeq', visible:false},
              {field:'status', visible:false},
              {field:'registSeqno', visible:false}
          ]
      });
      
      
      // 导入按钮点击事件
      $('#ChbBotAstPositionRegist001_btn_import').click(function() {
          Joyin.confirm("请确认是否要导入数据？", function() {
              Joyin.uploadFile(function(fileName) {
                  Joyin.ajax('importFile', {fileName: fileName});
              },function() {
                  table.load();
              });
          });
      });
      
      //导出所有查询结果
      $('#ChbBotAstPositionRegist001_btn_export').click(function() {
          exportExcel("1");
      });
      
      //导出中债Excel
      $('#ChbBotAstPositionRegist001_btn_chbexport').click(function() {
          exportExcel("0");
      });
      
    //导出删除模板
      $('#ChbBotAstPositionRegist001_btn_exportDel').click(function(){
          /*if(!table.isSearched()) {
              return;
          }*/
          var expArr=table.getAllCheckedRows();
          var param={};
          if(!expArr || expArr.length ==0){
              param = getSearchParams();
              if (param.status==null||param.status==''){
                  param.status='03';
              }
              param.flag = "2";
              param.sendStatus='01';
              Joyin.exportExcel("export",param);
          }
          else{
             
               for(var i=0;i<expArr.length;i++){
                   if(expArr[i].status !="删除"||expArr[i].sendStatus !="未报送"){
                       Joyin.error("只能导出删除状态且未报送的数据，请重新选择");
                       return;
                   }
               }
               var keyList = [];
               $.each(expArr,function(i,v){
                   v.sendStatus=v.sendStatusCode;
                   v.status=v.statusCode;
                   v.ccy=v.ccyCode;
               })
               param.keyList = JSON.stringify(expArr);
               param.flag='2';
               Joyin.exportExcel('export', param);
          }
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
             
              param.exportflag = 'export';
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
              if(flag=="0")
                  {
              for(var i=0;i<expArr.length;i++){
  //        		if(expArr[i].status!="01"||expArr[i].sendStatus!="01"){
  //        			Joyin.error("只能导出新建未报送状态的数据，请重新选择");
  //        			return;
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
              param.keyList = JSON.stringify(expArr);
              param.expKeys = keyList.join(',')
              param.flag = flag;
              param.exportflag = 'exportSelected';
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
  //                Joyin.ajaxJson('doManual', {keyList : JSON.stringify(expArr)}, function() {
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

    /**start   中债一二期直连新加  add  by wangzehui  20201012  */
    /**
     * 数据检查
     */
    $('#ChbBotAstPositionRegist001_btn_checkData').click(function(){
        var paramObj = getSearchParams();
        paramObj.opertionType = "checkData";
        chbCommonCheck(table, paramObj, 'reportCheck', dataTitle, 'submitChb');
    });
    /**
     * 报送
     */
    $('#ChbBotAstPositionRegist001_btn_submit').click(function () {
        var paramObj = getSearchParams();
        paramObj.opertionType = "submitChb";
        chbCommonCheck(table, paramObj, 'reportCheck', dataTitle, 'submitChb');
    });
    /**
     * 重新报送
     */
    $('#ChbBotAstPositionRegist001_btn_reSubmit').click(function () {
        var paramObj = getSearchParams();
        paramObj.opertionType = "reSubmitChb";
        chbCommonCheck(table, paramObj, 'reportCheck', dataTitle, 'submitChb');
    });
    /**end   中债一二期直连新加  add  by wangzehui  20201012  */
      
      /**
       * 报送
       */
      /*$('#ChbBotAstPositionRegist001_btn_submit').click(function(){
          var rows = table.getAllCheckedRows();
          if(rows.length === 0) {
              var sendStatus = $('#sendStatus').val();
              var status = $('#status').val();
  //            if(sendStatus!='01'){
              if (!(sendStatus && sendStatus.length == 1 && sendStatus[0] == '01')) {
                  Joyin.error("只能报送报送状态为未报送的数据");
                  return;
              }
  //            else if(status!='01'){
  //                Joyin.error("只能报送数据状态为新增的数据");
  //                return;
  //            }
  //            else if(status && status.includes('03')){
              else if(status && status.indexOf('03') > -1){
                  Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
                  return;
              }
              Joyin.ajax('sendBeforeCheck',getSearchParams(), function(result) {
                  if(!result || result.length ==0){
                      Joyin.confirm("按照已选择当前查询条件报送,是否确认！",function(){
                          layui.layer.open({type:3});
                          Joyin.ajaxJson('submitChb', getSearchParams(), function() {
                            table.load();
                            layui.layer.closeAll();
                          });
                      });
                  }
                  else{
                      window.checkResult = result;
                      Joyin.checkData('<h4 class="modal-title" id="myModalLabel">投资者持有信息数据检查 </h4>', 'sendBeforeCheck', getSearchParams());
                  }
              });
              return ;
          }
          for(var i=0;i<rows.length;i++){
              if(rows[i].sendStatus !="01"){
                  Joyin.error("只能报送报送状态为未报送的数据");
                  return;
              }
              else if(rows[i].status && rows[i].status.indexOf('03') > -1){
                  Joyin.error("只能对报送状态为未报送，数据状态为新建和修改的数据进行报送！");
                  return;
              }
          }
          var keyList = [];
          Joyin.ajax('sendBeforeCheck', {keyList: JSON.stringify(rows)}, function(result) {
              if(!result || result.length ==0){
                  Joyin.confirm("请确认是否要报送选中记录？", function() {
                      layui.layer.open({type:3});
                      Joyin.ajaxJson('submitChb', {keyList: JSON.stringify(rows)}, function() {
                          table.load();
                          layui.layer.closeAll();
                      });
                  })
              }
              else{
                  window.checkResult = result;
                  Joyin.checkData('<h4 class="modal-title" id="myModalLabel">投资者持有信息数据检查 </h4>', 'sendBeforeCheck', {keyList: JSON.stringify(rows)});
              }
          });
      });*/
      
      /**
       * 手工处理
       */
      $('#ChbBotAstPositionRegist001_btn_manual').click(function(){
          var rows = table.getAllCheckedRows();
          if(rows.length === 0) {
  //            return Joyin.error("请至少选择一条记录");
              //add by liubin
              var sendStatus = $("#sendStatus").val();
              var submitFeedbackStatus = $("#submitFeedbackStatus").val();
              if(!((sendStatus && sendStatus.length == 1 &&sendStatus[0] == '01')
                      || (sendStatus && sendStatus.indexOf('02')>-1 && submitFeedbackStatus && (submitFeedbackStatus.indexOf('02')>-1 ||  submitFeedbackStatus.indexOf('04')> -1)))){
                  Joyin.error("只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理");
                  return;
              }
              var paramObj = getSearchParams();
              paramObj.flag = '01';
              Joyin.confirm("请确认是否要手工处理符合查询条件的全部记录？", function() {
                  layui.layer.open({type:3});
                   Joyin.ajaxJson('doManual', paramObj, function() {
                       table.load();
                       layui.layer.closeAll();
                   });
               });
          }else{
              for(var i=0;i<rows.length;i++){
                if (!(rows[i].sendStatus == '01'
                    || (rows[i].sendStatus == '02' && (rows[i].submitFeedbackStatus == '02' || rows[i].submitFeedbackStatus == '04')))) {
  //                  Joyin.error("只能手工处理报送状态为未报送的数据");
                    Joyin.error("只能选择报送状态为未报送的数据，或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理，请重新选择！");
                    return;
                }
            }
            
            Joyin.confirm("请确认是否要手工处理选中记录？", function() {
                layui.layer.open({type:3});
                Joyin.ajaxJson('doManual', {keyList: JSON.stringify(rows)}, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
          } 
          
      });
      
          /**
           * 取消手工处理 
           */
         $('#ChbBotAstPositionRegist001_btn_cancelManual').click(function() {
             var manArr=table.getAllCheckedRows();
             if(!manArr || manArr.length ==0){
                 var sendStatus = $("#sendStatus").val();
                    if(!(sendStatus && sendStatus.length==1 && sendStatus[0] == '03')){
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
      // add by wangmin 20180827 start
         // 二次报送
         $('#ChbBotAstPositionRegist001_btn_submitAgain').click(function() {
             var paramObj = getSearchParams();
             
             var submitArr = table.getAllCheckedRows();
             if(submitArr.length === 0) {
                 var submitStatus = $('#sendStatus').val();
                 var status = $('#status').val();
                 var submitFeedbackStatus = $('#submitFeedbackStatus').val();
                 if (!(submitStatus && submitStatus.length == 1 && submitStatus[0] == '02')) {
                     Joyin.error("只能对报送状态为接口报送的数据进行重新报送！");
                     return;
                 }
  //               if(status && status.includes('03')){
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
                 var submitStatus = submitArr[i].sendStatusCode;
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
             };
             $.each(submitArr, function(i, v) {
                 v.sendStatus=v.sendStatusCode;
                 v.status=v.statusCode;
                 v.ccy=v.ccyCode;
             });
             
             Joyin.confirm("请确认是否要二次报送？",function(){
                 layui.layer.open({type:3});
                 Joyin.ajaxJson('submitCbAgain', {keyList: JSON.stringify(submitArr)}, function() {
                     table.load();
                     layui.layer.closeAll();
                 });
             });
         });
         // add by wangmin 20180827 end
         
         function init(){
             if(isMain=='01'){
                 setTimeout(function() {
                     if($('#sendStatus').val()=='02'||$('#sendStatus').val()=='03'){
                           $('#submitDate').val(searchDataStart+' - '+searchDataEnd);
                       }else{
                           $('#submitDeadLine').val(searchDataStart + ' - ' + searchDataEnd);
                       }
                     table.load();
                 }, 500);
             }
           //报送前检查调用数据检查
             if(validKey){
                 setTimeout(function() {
                     table.load();
                 },500)
             }
         };
         init();
         
         $(function(){
             // 绑定按钮事件
             $('#SvrChbBotAstPositionRegist001_btn_search').click(function() {
                 table.load();
             });
         });
         
       //导出删除模板
         $('#SvrChbBotAstPositionRegist001_btn_exportDel').click(function(){
             // 如果没有查询则返回提示
             if(!table.isSearched()) {
                 return;
             }
             // 根据查询条件查询数据
             var param = getSearchParams();
             // 只导出删除未报送的数据
             param.status='03';
             param.sendStatus='01';
             var isHaveData = true;
             Joyin.ajaxSync('findPage',param, function(result){
                 if (!result || result.root.length == 0){
  //            	   Joyin.error("没有数据导出，请重新选择");
                     isHaveData = false;
  //            	   return;
                 }
             })
  //           if (!isHaveData){
  //        	  return; 
  //           }
             // 选中的导出数据
             var expArr=table.getAllCheckedRows();
             // 报送状态标志
              var sendFlag = false;
             if(!expArr || expArr.length ==0){
                 if (!isHaveData){
                     // 不勾选不能导出
                     Joyin.error("没有可导出的数据，请重新选择导出条件！");
                     return; 
                  }
             }
             else{
                 // 选中数据判断是否是删除和未报送状态
                 for(var i=0;i<expArr.length;i++){
  //                    if(expArr[i].status !="03"||expArr[i].sendStatus !="01"){
  //                        Joyin.error("只能导出删除状态且未报送的数据，请重新选择");
  //                        return;
  //                    }
                     if(expArr[i].status !="03"){
                      Joyin.error("只能导出删除的数据，请重新选择");
                      return;
                  }
                     if(expArr[i].sendStatus !="01"){
                         sendFlag = true;
                  }
                 }
                 // 选中数据
                 param.expKeys = JSON.stringify(expArr);
             }
             if (sendFlag) {
                 Joyin.confirm("导出数据中含有已报送的数据，请确认是否导出！", function() {
                  // 打开删除信息页面
                     Joyin.dialog(JoyinUtil.getLabel("SvrExportDeleteExcelConfirm001"),
                             basePath + "chb/common/ExportDeleteExcelConfirm001/init",
                             "50%", "50%", {pageModel:PAGE_MODEL_NEW,orgPageId:param.pageId,expKeys:param.expKeys},function(){
                                 //防止出现查询转圈圈
                                 setTimeout(function(){table.reload();},100);
                             }); 
                 });
             } else {
                 // 打开删除信息页面
                 Joyin.dialog(JoyinUtil.getLabel("SvrExportDeleteExcelConfirm001"),
                         basePath + "chb/common/ExportDeleteExcelConfirm001/init",
                         "50%", "50%", {pageModel:PAGE_MODEL_NEW,orgPageId:param.pageId,expKeys:param.expKeys},function(){
                             //防止出现查询转圈圈
                             setTimeout(function(){table.reload();},100);
                         }); 
             }
            
         });
  }());