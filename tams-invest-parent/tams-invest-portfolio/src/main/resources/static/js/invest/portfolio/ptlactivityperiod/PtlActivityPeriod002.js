 
(function() {
    var isEditPage = true;
    var flag = false;
    var acctNoList;
    var calcConditionObjBackUp;
    var orderAcctMap = {};
    // 生成指令参数保存
    /**
     * 获取表单值
     * @returns
     */
    function getParameter(){
        var object = $.serializeToObject('#basicInfoForm', true);
        if($("#busiAcctNo").val()) {
            object.busiAcctNo = $("#busiAcctNo").val().join(",");
        }
        object.tradeAmt = $("#tradeAmt").val();
        object.workflowId = workflowId;
        return object;
    }
    
    /**
     * 划款指令表格格式化
     */
    var option = {
        searchOnInit: true,
        loadonce:true,
        page:false,
        data: [],
        checkbox:false,
        cols:[
              {field:'isNeedSend', width:140, title: 'BussTrdTransferOrder.isNeedSend',edit:true,
                  editOption:{type:'select', multiple: false, searchable: false,showFiled:'id', result: YES_NO,initValue:YES_OR_NO_N}, sortable:false}
             , {field:'payTypeText', width:120,title: 'BussTrdTransferOrder.payType',sortable:false}
             , {field:'settleDateText', width:120,title: 'PtlActivityPeriod001.settleDate',sortable:false}
             , {field:'settleAmt', width:120,title: 'BussTrdTransferOrder.settleAmt',sortable:false}
             , {field:'ccy', width:120,title: 'BussTrdTransferOrder.ccy',sortable:false,
                 editOption: {
                     type: 'select', id: 'ccy_select', childrenIds: 'recAcctNo,payAcctNo'
                 }
             }
             , {
                 field: 'payAcctNo', width: 130, title: 'BussTrdTransferOrder.payAcctNo', edit: true,
                 editOption: {
                     type: 'select', multiple: false, searchable: true, parentIds: 'payAcctNo,ccy_select', showField: 'id',
                     url: JoyinUtil.getRealPath("/component/trade/BussTrdTransferOrder/select")
                 }, sortable: false
             }
             , {field: 'payAcctName', width: 150, title: 'BussTrdTransferOrder.payAcctName', sortable:false}
             , {
                 field: 'recAcctNo', width: 130, title: 'BussTrdTransferOrder.recAcctNo', edit: true,
                 editOption: {
                     type: 'select', multiple: false, searchable: true, parentIds: 'recAcctNo,ccy_select', showField: 'id',
                     url: JoyinUtil.getRealPath("/component/trade/BussTrdTransferOrder/select")
                 }, sortable: false
             }
             , {field: 'recAcctName', width: 150, title: 'BussTrdTransferOrder.recAcctName', sortable:false}
             , {field:'recAcctPoint', width:120,title: 'BussTrdTransferOrder.recAcctPoint',sortable:false}
             , {field:'purpose', width:120,title: 'BussTrdTransferOrder.use',sortable:false,edit:true}
             , {field:'remark', width:120,title: 'BussTrdTransferOrder.remark',sortable:false,edit:true}
             , {field:'portfolioId', width:120,title: 'BussTrdTransferOrder.busiId',sortable:false}
             , {field:'portfolioName', width:140,title: 'BussTrdTransferOrder.portfolioName',sortable:false}
             , {field:'orderId', visible: false}
             , {field:'payType',visible: false}
             , {field:'settleDate',visible: false}
             , {field:'settleStatus', visible: false}
             , {field:'isTotalTrade', visible: false}
         ],
        editCell: function (value, row, field, cell, column) {
             if (field == 'payAcctNo') {
                 if (typeof value == "undefined" || value == null || value == "") {
                     row.payAcctName = "";
                 }
                 // 选择付款账户带出贷款账户名称
                 if (typeof value != "undefined" && value) {
                     Joyin.ajax(JoyinUtil.getRealPath('/component/trade/BussTrdTransferOrder/queryStlCapacctInfoByAcctNo'), {
                         ccy: row.ccy,
                         acctNo: value
                     }, function (result) {
                         // 网点
//                         row.recAcctPoint = result.largePayno;
                         //add by wangmin 2019-0-06 begin
                         if (!result) {
                             Joyin.error("该币种下，无此账户。")
                             return;
                         }
                         row.payAcctName = result.acctName;
                         row.payAcctNo = value;
                         //add by wangmin 2019-0-06 end
                         bussTrdTransferOrderTable.renderRow(row.LAY_TABLE_INDEX, row);
                     })
                 } else {
                     bussTrdTransferOrderTable.renderRow(row.LAY_TABLE_INDEX, row);
                 }

             } else if(field == 'recAcctNo'){
                 if (typeof value == "undefined" || value == null || value == "") {
                     row.recAcctName = "";
                 }
                 if (typeof value != "undefined" && value) {
                     // 选择收款账户带出行内/行外、网点
                     Joyin.ajax(JoyinUtil.getRealPath('/component/trade/BussTrdTransferOrder/queryStlCapacctInfoByAcctNo'), {
                         ccy: row.ccy,
                         acctNo: value
                     }, function (result) {
                         var assetSource = JSON.parse(ASSET_SOURCE);
                         $.each(assetSource, function (i, v) {
                             if (v.id == result.inOutBank) {
                                 row.inOutBank = v.text;
                             }
                         });
                         // 网点
                         row.recAcctPoint = result.largePayno;
                         row.recAcctName = result.acctName;
                         row.recAcctNo = value;
                         bussTrdTransferOrderTable.renderRow(row.LAY_TABLE_INDEX, row);
                     })
                 } else {
                     bussTrdTransferOrderTable.renderRow(row.LAY_TABLE_INDEX, row);
                 }
             }
         },
         done:function(table, allData){
             $("[data-field='orderId']").hide();
             calcConditionObjBackUp = tableInitData();
         },
    }
    
    if(orderList) {
        orderList.forEach(function (value) {
            option.data.push(value);
        });
    }
    
    var bussTrdTransferOrderTable = $("#BussTrdTransferOrderTable").joyintable(option);
    
    // 检查生成指令参数是否发生变化
    var isCalcConditionChanged = function() {
        // 参照模式，不做check
        if (pageModel === PAGE_MODEL_REFERENCE) {
            return false;
        }
        if (!$("#bussTrdTransferOrder_div").is(":visible")) {
            return false;
        }
        if (calcConditionObjBackUp == null || calcConditionObjBackUp.length == 0) {
            return false;
        }
        if(stlCommandInput != null){
            var currentCalcConditionObj = stlCommandInput();
            // 影响计划生成的所有项目与计划生成时记录的值比较，值变更时
            for ( var key in currentCalcConditionObj) {
                for ( var key1 in currentCalcConditionObj[key]) {
                    if (typeof currentCalcConditionObj[key][key1] != "object"
                        && currentCalcConditionObj[key][key1] != calcConditionObjBackUp[key][key1]) {
                        var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                        Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                            [labelText, "生成划款指令"]));
                        return true;
                    }
                }
            }
            for ( var key in currentCalcConditionObj) {
                for ( var key1 in currentCalcConditionObj[key].deal) {
                    var currentDealObj = currentCalcConditionObj[key].deal;
                    var backupDealObj = calcConditionObjBackUp[key].deal;
                    if (typeof currentDealObj[key1] != "object"
                        && currentDealObj[key1] != backupDealObj[key1]) {
                        var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                        Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                            [labelText, "生成划款指令"]));
                        return true;
                    }
                }
            }
        }
    };
        
    
    var tableInitData = function () {
        var detailsList = detailsListTable.getAllRows();
        var dtos = [];
        for (var i = 0; i < detailsList.length; i++) {
        	//生成指令校验
            var dealReal = {};
            dealReal.acctNo = detailsList[i].acctNo;
            dealReal.busiAcctNo=detailsList[i].busiAcctNo;
            dealReal.acctName=detailsList[i].acctName;
            dealReal.portfolioId=detailsList[i].portfolioId;
            dealReal.portfolioName=detailsList[i].portfolioName;
            dealReal.theoryTradeAmt=detailsList[i].theoryTradeAmt;
            dealReal.actualTradeAmt=detailsList[i].actualTradeAmt;
            dealReal.ccy=detailsList[i].ccy;
            
            dealReal.tradeDate=$("#settleDate").val();
            dealReal.tradeAmt = dealReal.actualTradeAmt;
            dealReal.ps = PS_T09;
            dealReal.busiType = BUSI_TYPE_PTL03;
            dealReal.finprodType2 = FINPROD_TYPE_F21;
            dtos.push({
                  'isTotalTrade': 'N'
                , 'bankLendingType': null
                , 'portfolioId': dealReal.portfolioId
                , 'tradeAmt' :dealReal.actualTradeAmt
                , 'deal':dealReal
            })
        }
        return dtos;
    }
    
    var stlCommandInput = tableInitData;
    

      
      /**
       * 划款金额默认为列表中所有实际收息金额的总和
       */
      var initTradeAmt = function() {
          if(detailsListTable) {
              var arr = detailsListTable.getAllRows();
              if(arr){
                  var totalAmount = 0;
                  for(var i=0;i<arr.length;i++) {
                	  var actualTradeAmt = arr[i].actualTradeAmt?arr[i].actualTradeAmt.replace(',',''):0;//编辑的列数字因为格式化带，无法参与计算。需要手动替换
                      totalAmount = JoyinMath.doAdd(totalAmount,actualTradeAmt);
                  }
                  
                  $("#tradeAmt").val(totalAmount);
              }
          }
      }
     
    
    var detailsListTable = $("#detailsListTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/findDetailsPage'),
            type: 'POST',
            data: function() {
                return getParameter();
            }
        },
        page: false,
        searchOnInit:true,
        loadonce: true,
        checkbox: false,
        rowno: true,
        cols:[
               {field:'busiAcctNo', title: 'PtlActivityPeriod001.acctNo',sortField:'busiAcctNo'}//账户
              ,{field:'acctName', title: 'PtlActivityPeriod001.acctName1',sortField:'acctName'}//账户名称
              ,{field:'portfolioId', title: 'PtlActivityPeriod001.portfolioId',sortField:'portfolioId'}//组合代码
              ,{field:'portfolioName', title: 'PtlActivityPeriod001.portfolioName',sortField:'portfolioName'}//组合名称
              ,{field:'theoryTradeAmt', title: 'PtlActivityPeriod001.theoryTradeAmt',sortField:'theoryTradeAmt',type:'money'}//理论收息金额
              ,{field:'actualTradeAmt', title: 'PtlActivityPeriod001.actualTradeAmt',sortField:'actualTradeAmt',type:'money',edit:true}//实际收息金额
              ,{field:'ccyStr', title: 'PtlActivityPeriod001.ccy',sortField:'cyy'}//币种
              ,{field:'isParentPortfolio',visible:false}//是否子母组合
              ,{field:'childrenDetailsJson',visible:false}
              ,{field:'acctNo',visible:false}
              ,{field:'ccy',visible:false}
              ,{field:'operation', title: 'SysCommon.operation', type:'action',
                  render:function (data, type, row, meta) {
                      if(row.isParentPortfolio != YES_OR_NO_N){
                          return '<i class="btn iconfont config" title="查看明细">&#xe6ac;</i>';
                      } else {
                          return "";  
                      }
                  }
              }
            ],
            beforeEditCell: function(preVal, field, rowData){
                if (field == 'actualTradeAmt' && rowData.isParentPortfolio == YES_OR_NO_N) {
                    return true;
                } else {
                    return false;
                }
            },
            editCell: function(value, data, field, cell, column){
                if(field == 'actualTradeAmt') {
                    initTradeAmt();
                }
                return true;
            },
            done: function(table, allData){
                //calcConditionObjBackUp = tableInitData();
                initTradeAmt();
            }
    });
    
    
    /**
     * 明细查看
     */
    detailsListTable.bind('click', '.config', function(row, index) {
    	var childrenDetailsJson = row.childrenDetailsJson?row.childrenDetailsJson:JSON.stringify(row.childrenDetailsList)
    	var object = {
                busiAcctNo:row.busiAcctNo,
                acctName:row.acctName,
                acctNo:row.acctNo,
                ccy:row.ccy,
                workflowId:workflowId,
                pageModel:pageModel,
                portfolioId:row.portfolioId,
                childrenDetailsJson:childrenDetailsJson?childrenDetailsJson:"",
                busiNo:$("#busiNo").val()?$("#busiNo").val():"",
                mdate:$("#mdate").val()?$("#mdate").val():""
            }
            Joyin.dialog(JoyinUtil.getLabel("PtlActivityPeriod001.showLayeringInfo"),
                    "/invest/portfolio/ptlactivityperiod/PtlActivityPeriod003/init",
                    "80%", "95%", object, function(data) {
                     if(pageModel == PAGE_MODEL_NEW || pageModel ==PAGE_MODEL_EDIT) {
                         if(!data || data.length<=0) return;
                         // 母组合的实际收息金额=sum(子组合的实际收息金额)
                         var totalAmount = 0;
                         for(var i=0;i<data.length;i++) {
                             if(data[i].busiAcctNo == row.busiAcctNo){
                                 totalAmount = JoyinMath.doAdd(totalAmount,data[i].actualTradeAmt,2);  
                             } else {
                                 return;
                             }  
                         }
                         var jsonData = JSON.stringify(data);
                         if(detailsListTable) {
                             detailsListTable.renderRow(index, {actualTradeAmt: totalAmount,childrenDetailsJson:jsonData});
                             initTradeAmt();  
                         }
                     }
                    });
    });
    /**
     * 保存和提交处理函数
     * @param operateType 操作类型
     * @returns 没有返回值
     */
    function saveOrSubmit(operateType){
        if (isCalcConditionChanged()) {
            return;
        }
        var object = getParameter();
        object.operateType = operateType;
        object.detailsList = detailsListTable.getAllRows();
        object.bussTrdTransferOrderList = bussTrdTransferOrderTable.getAllRows();
        if(!object.bussTrdTransferOrderList || object.bussTrdTransferOrderList.length == 0){
        	Joyin.error("请生成划款指令!")
        	return;
        }
        //进行检查
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/check', object, function(result) {
            //确认窗口
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon."+operateType)), function() {
                //保存操作
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/save', object, "PtlActivityPeriod002_btn_save");
                }
                else {
                    //修改
                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/save', object, "PtlActivityPeriod002_btn_submit");
                }
            });
        }); 
    }
    
    var queryDetailsList = function(){
        if($("#busiAcctNo").val() != null && $("#busiAcctNo") != ''
            && $("#mdate").val() != null && $("#mdate").val() != '') {
            detailsListTable.table?detailsListTable.reload():detailsListTable.load();
        }else{
        	detailsListTable.clear();
        }
    }
       
    
    /**
     * 画面加载完成
     */
    $(function() {
    	
    	if(pageModel == PAGE_MODEL_NEW) { 
    		$('#busiAcctNo').prop('disabled', false);   
    		$('#bankDep').prop('disabled', false);
    		$('#mdate').prop('disabled', false);  
    		
    	} else {
    		$('#busiAcctNo').prop('disabled', true);   
    		$('#bankDep').prop('disabled', true);
    		$('#mdate').prop('disabled', true);  
    	}
       
        $("#busiAcctNo").on('change',function(){
            if(pageModel == PAGE_MODEL_NEW || pageModel ==PAGE_MODEL_EDIT) { 
                queryDetailsList();
            }
        })
        
        $("#mdate").on('change',function() {
            if(pageModel == PAGE_MODEL_NEW || pageModel ==PAGE_MODEL_EDIT){
                $("#settleDate").val($("#mdate").val());
                queryDetailsList();
            }
        })
        // 绑定保存按钮
        $('#PtlActivityPeriod002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        //绑定提交按钮
        $('#PtlActivityPeriod002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        
        $("#bussTrdTransferOrder_btn_generate").on("click", function () {
            if(stlCommandInput != null) {
                var index = layer.open({
                    type: 1,
                    icon : 16,
                    area: ['200px', '100px'],
                    content: '数据加载中',closeBtn: 0
                });
                var data = stlCommandInput();
                // 保存生成指令参数
                calcConditionObjBackUp = data;
                var object = getParameter();
                object.detailsList = detailsListTable.getAllRows();
//                orderAcctMap = {};
                var result = [];
                Joyin.ajaxJson(JoyinUtil.getRealPath('/common/multiOrder/genOrderCheck'), data, function () {
                    Joyin.ajaxJson(basePath + 'invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/generateOrder', object, function (ret) {
                    	
                        if (data.length == ret.data.length) {
                            bussTrdTransferOrderTable.clear(function () {
                            	bussTrdTransferOrderTable.addRow(ret.data)
                                layui.layer.close(index);
                            })
                            
                        }
                    });
                });
            }
        });
        
        $('body').on('change','#counterAcct',function(event,data){
            if(data && data.value[0]){
                Joyin.ajax(basePath+'invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/getCounterAcct',{param:data.value[0]},function(result){
                    if(result){
                        table.renderRow(data.rowIndex, {counterAcctName:result[0].text});
                    }
                });
            }
        });
    });
}());