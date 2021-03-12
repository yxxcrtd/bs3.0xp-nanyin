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
              //报送截止日
              submitendDateStart:$('#submitendDate').startDate(),
              submitendDateEnd:$('#submitendDate').endDate(),
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
              { field: 'registSeqno',visible:false},
              { field: 'status' ,visible:false},
              { title:'数据状态', field: 'statusStr',width:100, sortField:'status'},
              { title:'数据日期', field: 'statusDate', type:'datetime', sortField:'statusDate'},
              { title:'报送状态', field: 'sendStatusStr',width:100 , type: 'date', sortField:'sendStatus'},
              { title:'报送日期', field: 'submitTime', type: 'datetime', sortField:'submitTime'},
              { title:'报送截止日', field: 'chbSubmitDeadline', type:'datetime', sortField:'chbSubmitDeadline'},
              { title:'反馈状态', field: 'submitFeedbackStatus',width:100, sortField:'',sortField:'submitFeedbackStatus'},
              {title: '理财产品代码',field:'prodId', width:100, sortField:'prodId'},
              {title: '产品登记编码', field:'prodRegistCode', width:100, sortField:'prodRegistCode'},
              {title: '发行机构代码', field:'issuePartyId', width:100, sortField:'issuePartyId'},
              {title: '初始净值',field:'originalValue', type:'money', digit:5, width:50, sortField:'originalValue'},
              {title: '产品净值',field:'prdValue', type:'money', digit:5, width:50, sortField:'prdValue'},
              {title: '累计净值',field:'totalValue', type:'money', digit:5, width:50, sortField:'totalValue'},
              {title: '净值币种',field:'valueCcy', width:100, sortField:'valueCcy'},
              {title: '折算人民币初始净值',field:'cnyOrlValue', type:'money', digit:5, width:150, sortField:'cnyOrlValue'},
              {title: '折算人民币产品净值',field:'cnyPrdValue', type:'money', digit:5, width:150, sortField:'cnyPrdValue'},
              {title: '折算人民币累计净值',field:'cnyTalValue', type:'money', digit:5, width:150, sortField:'cnyTalValue'}, 
              {title: '实现收益率(%)',field:'reaYield', type:'money', digit:5, width:150, sortField:'newExpYield'},
              {title: '最新预期收益率(%)',field:'newExpYield', type:'money', digit:5, width:150, sortField:'reaYield'},
              {title: '银行实现收益(元)',field:'bankImpInt',type:'money', width:150, sortField:'bankImpInt'},
              {title: '业务开始日', field:'vdate', type:'date',format:'yyyy-MM-dd', width:100, sortField:'vdate'},
              {title: '业务结束日', field:'mdate', type:'date', width:100, sortField:'mdate'},
              {title: '累计申购份额',field:'purchaseQty', type:'money', digit:5, width:150, sortField:'purchaseQty'},
              {title: '累计赎回份额',field:'redeemQty', type:'money', digit:5, width:150, sortField:'redeemQty'},
              {title: '每万份份额分红',field:'shareAmtWan', type:'money', digit:5, width:150, sortField:'shareAmtWan'},
              {title: '每万份现金分红',field:'cashAmtWan', type:'money', digit:5, width:150, sortField:'cashAmtWan'},
              {title: '产品余额（元） ',field:'balanceAmt', type:'money', digit:2, width:150, sortField:'balanceAmt'},
              {title: '产品份额',field:'prdQty', type:'money', digit:5, width:150, sortField:'prdQty'},
              {title: '币种及申购兑付金额', field:'ccyPurchase', width:100, sortField:'ccyPurchase'},
              {title: '备注',field:'remark', width:100, sortField:'remark'},
  //            {field: '主键',field:'registSeqno',visible:false, sortField:''},
              {field: 'sendStatus',visible:false, sortField:'sendStatus'}

              ]
      });


      //导出中债Excel
      $('#ProdDurationReg001_btn_chbexport').click(function() {
          exportExcel("0");
      });
      
  //    //导出删除模板
  //    $('#ProdDurationReg001_btn_exportDel').click(function(){
  //        var expArr=table.getAllCheckedRows();
  //        var param={};
  //        if(!expArr || expArr.length ==0){
  //            param = getSearchParams();
  //            if (param.status==null||param.status==''){
  //            	param.status='03';
  //            }
  //            param.flag = "2";
  //            param.sendStatus='01';
  //            Joyin.exportExcel("export",param);
  //        }
  //        else{
  //       	
  //        	 for(var i=0;i<expArr.length;i++){
  //                 if(expArr[i].status !="删除"||expArr[i].sendStatus !="未报送"){
  //                     Joyin.error("只能导出删除状态且未报送的数据，请重新选择");
  //                     return;
  //                 }
  //        	 }
  //        	 var keyList = [];
  //             param.keyList = JSON.stringify(expArr);
  //             param.flag='2';
  //             param.exportType = "deleteExport";
  //             Joyin.exportExcel('export', param);
  //        }
  //    });
      
      /**
       * 导出
       */
      var exportExcel = function(flag) {
          var expArr=table.getAllCheckedRows();
          var param={};
          if(!expArr || expArr.length ==0){
              param = getSearchParams();
              param.flag = flag;
              if(flag=="0" ){
                  param.status="01";
                  param.sendStatus="01";
              }
              param.exportType = "allExport";
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
              if(flag=="0")
              {
                  for(var i=0;i<expArr.length;i++){
  //        			if(expArr[i].status!="01"||expArr[i].sendStatus!="01"){
  //        				Joyin.error("只能导出新建未报送的数据，请重新选择");
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
              param.keyList = JSON.stringify(expArr);
              param.expKeys = keyList.join(',');
              param.flag = flag;
              param.exportType = "selectExport";
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

    /*add by zhangliang 20201019 start*/
    // 数据检查
    $('#ProdDurationReg001_btn_submit').click(function(){
        var paramObj = getSearchParams();
        paramObj.opertionType = "checkData";
        chbCommonCheck(table, paramObj, 'reportCheck', dataTitle, 'submitChb');
    });
    // 报送
    $('#ProdDurationReg001_btn_submitChb').click(function(){
        var paramObj = getSearchParams();
        paramObj.opertionType = "submitChb";
        chbCommonCheck(table, paramObj, 'reportCheck', dataTitle, 'submitChb');
    });
    // 重新报送
    $('#ProdDurationReg001_btn_reSubmitChb').click(function(){
        var paramObj = getSearchParams();
        paramObj.opertionType = "reSubmitChb";
        chbCommonCheck(table, paramObj, 'reportCheck', dataTitle, 'submitChb');
    });
    /*add by zhangliang 20201019 end*/

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
      
      
      /**
       * 手工处理
       */
      $('#ProdDurationReg001_btn_manual').click(function(){
          var rows = table.getAllCheckedRows();
          if(rows.length === 0) {
              var sendStatus = $("#submitStatus").val();
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
                  if (!(rows[i].sendStatus == '01' || (rows[i].sendStatus == '02' && (rows[i].submitFeedbackStatus == '发送失败' || rows[i].submitFeedbackStatus == '中债退回')))) {
                    Joyin.error("只能选择报送状态为未报送的数据，或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理，请重新选择！");
                    return;
                }
            }
            var keyList = [];
            keyList = JSON.stringify(rows);
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
     $('#ProdDurationReg001_btn_cancelManual').click(function() {
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
     //导出删除模板
     $('#ProdDurationReg001_btn_exportDel').click(function(){
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
  //        	   Joyin.error("没有数据导出，请重新选择");
                 isHaveData = false;
  //        	   return;
             }
         })
  //       if (!isHaveData){
  //    	  return; 
  //       }
         // 选中的导出数据
         var expArr=table.getAllCheckedRows();
         // 报送状态标志
         var sendFlag = false;
         if(!expArr || expArr.length ==0){
             // 不勾选不能导出
             if (!isHaveData){
                 Joyin.error("没有可导出的数据，请重新选择导出条件！");
                    return; 
                 }
         }
         else{
             // 选中数据判断是否是删除和未报送状态
         for(var i=0;i<expArr.length;i++){
  //                if(expArr[i].status !="03"||expArr[i].sendStatus !="01"){
  //                    Joyin.error("只能导出删除状态且未报送的数据，请重新选择");
  //                    return;
  //                }
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
         $('#SvrProdDurationReg001_btn_search').click(function() {
             table.load();
         });
     });
  }());