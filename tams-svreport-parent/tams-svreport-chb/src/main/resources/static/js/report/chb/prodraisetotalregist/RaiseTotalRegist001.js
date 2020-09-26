$(function() {
    //跳转的页码
      var pageCount = 1;
      /**
       * 获取查询参数
       */
      var getSearchParams = function() {
          return {
              //产品名称
              prodName:$('#prodname').val()==null?null:$('#prodname').val().join(","),
              //产品登记编码
              regCode:$('#regCode').val()==null?null:$('#regCode').val().join(","),
              //投资者类别
              holderType:$('#holderType').val()==null?null:$('#holderType').val().join(","),
              //是否有其他机构代销
              isConsign:$('#isConsign').val(),
              //报送截止日
              submitendDateStart:$('#submitendDate').startDate(),
              submitendDateEnd:$('#submitendDate').endDate(),
              //数据日期
              dataDateStart:$('#dataDate').startDate(),
              dataDateEnd:$('#dataDate').endDate(),
              //报送日期
              submitDateStart:$('#submitDate').startDate(),
              submitDateEnd:$('#submitDate').endDate(),
              vdateStart:$('#prdVdate').startDate(),
              vdateEnd:$('#prdVdate').endDate(),
              //报送状态
              sendStatus : $('#submitStatus').val()==null?null:$('#submitStatus').val().join(","),
              //数据状态
              status: $('#status').val()==null?null:$('#status').val().join(","),
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
              { field: 'registSeqno',width:0,sortField:'',visible:false},
              { field: 'sendStatus',visible:false},
              { field: 'status',visible:false},
              { title: "数据状态", field: 'statusStr',width:100,sortField:'status'},
              { title: "数据日期", field: 'statusDate', type: 'datetime',sortField:'statusDate'},
              { title: "报送状态", field: 'sendStatusStr',width:100 ,sortField:'sendStatus'},
              { title: "报送日期", field: 'submitTime', type: 'datetime',sortField:'submitTime'},
              { title: "报送截止日", field: 'chbSubmitDeadline', type: 'datetime',sortField:'chbSubmitDeadline'},
              { title: "反馈状态", field: 'submitFeedbackStatus',width:100 ,sortField:'submitFeedbackStatus'},
              { title: "产品代码", field: 'prodId',width:100 ,sortField:'prodId'},
              {title: "发行机构代码",field:'issuePartyId', width:150,sortField:'issuePartyId'},
              {title: '产品登记编码', field:'prodRegCode', width:150,sortField:'regCode'},
              {title: '个人投资者总数', field:'singleTotal', width:150,sortField:'singleTotal'},
              {title: '法人投资者总数', field:'legalTotal', width:150,sortField:'legalTotal'},
              {title: '非法人投资者总数', field:'unlegalTotal', width:150,sortField:'unlegalTotal'},
              {title: '认购币种', field:'subscribeCcyAmt', width:100,sortField:'subscribeCcyAmt'},
              {title: '销售区域及募集金额', field:'saleAreaAmt', width:160,sortField:'saleAreaAmt'},
              {title: '实际募集金额（元）',field:'totalAmt', width:180,type:'money', digit:2,sortField:'totalAmt'},
              {title: '募集总份额',field:'raiseQty', width:150, type:'money', digit:5,sortField:'raiseQty'},
              {title: '是否有其他机构代销',field:'isConsign', width:200,sortField:'isConsign'},
              {title: '代销总金额',field:'consignAmt', width:200,type:'money', digit:2,sortField:'consignAmt'},
              {title: '备注',field:'remark', width:200,sortField:'remark'}
          ]
      });
      
      
      //导出所有查询结果
      $('#SvrRaiseTotalRegist001_btn_export').click(function() {
          exportExcel("1");
      });
      
      //导出中债Excel
      $('#SvrRaiseTotalRegist001_btn_chbexport').click(function() {
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
                      Joyin.ajaxJson('manual', param, function() {
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
  //                Joyin.ajaxJson('manual', {keyList : JSON.stringify(expArr)}, function() {
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
              Joyin.ajaxJson('manual', {keyList : JSON.stringify(expArr)}, function() {
                  table.load();
                  layui.layer.closeAll();
              });
          });
      };
      
      //手工处理 
      $('#SvrRaiseTotalRegist001_btn_manual').click(function() {
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
                  Joyin.ajaxJson('manual', paramObj, function() {
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
                  Joyin.ajaxJson('manual', {keyList : JSON.stringify(manArr)}, function() {
                      table.load();
                      layui.layer.closeAll();
                  });
              });
          }
      });
      
      /**
       * 取消手工处理 
       */
     $('#SvrRaiseTotalRegist001_btn_cancelManual').click(function() {
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
     
   //数据检查
     $('#SvrRaiseTotalRegist001_btn_checkData').click(dateCheckMethod);
     function dateCheckMethod(){
         var checkArr=table.getAllCheckedRows();
         if(checkArr.length === 0) {
             var sendStatus = $('#submitStatus').val();
             var status = $('#status').val();
             if (!(sendStatus && sendStatus.length == 1 && sendStatus[0] == '01')) {
                 Joyin.error("只能对报送状态为未报送，数据状态为新建和修改的数据进行数据检查！");
                 return;
             }
             else if(status && status.indexOf('03') > -1){
                 Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
                 return;
             }
             var paramObj = getSearchParams()
             if(!status){
                 paramObj.status = '01,02'; 
             }
             Joyin.ajax('reportCheck',paramObj, function(result){
                 window.checkResult = result;
                 Joyin.checkData(dataTitle, checkUrl, getSearchParams());
             })
             return ;
         }
         var keyList = [];
         for(var i=0;i<checkArr.length;i++){
             keyList.push(checkArr[i].registSeqno);
             if(checkArr[i].sendStatus !="01"){
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
         
         function init(){
             if(isMain=='01'){
                 setTimeout(function() {
                     if($('#submitStatus').val()=='02'||$('#submitStatus').val()=='03'){
                         $('#submitDate').val(searchDataStart+' - '+searchDataEnd);
                     }else{
                         $('#submitendDate').val(searchDataStart+' - '+searchDataEnd);
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
             $('#SvrRaiseTotalRegist001_btn_search').click(function() {
                 table.load();
             });
         });
  }());