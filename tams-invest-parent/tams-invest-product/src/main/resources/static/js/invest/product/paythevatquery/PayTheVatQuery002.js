$(function () {
    /**
     * 纳税截止日
     */
    var payment1="";
    /**
     * 纳税日期
     */
    var payment2="";
    // add by jiangpeng on 20210512 ZGBSSE-363
    /**
     * 纳税结束日 记录上一次点击查询时的纳税结束日,用于提交时判断是否修改了纳税结束日
     */
    var payTaxMDateCheck = "";
    /**
     * 校验是否修改了纳税结束日
     */
    var isPayTaxMDateChange = function (){
        var payTaxMDate = $("#payTaxMDate").val();
        if(payTaxMDate===payTaxMDateCheck){
            return false;
        }
        return true;
    }
    // add by jiangpeng on 20210512 ZGBSSE-363
    $('#taxidenType').val('C12').trigger("change");
    
    // 生成指令参数保存
    var calcConditionObjBackUp = null;
    /**
     * 绑定联动
     */
    var linkBind=function() {
        //默认勾选缴税，展示缴税日期
        $("#drawback").hide();
        $("#drawback2").hide();
        $("#paytax").show();
        $("#paytax2").show();
        $("#taxidenType").change(function(){
            var taxidenType = $("#taxidenType").val();
            if(taxidenType == 'C13'){
                $("#drawback").show();
                $("#drawback2").show();
                $("#paytax").hide();
                $("#paytax2").hide();
            } else {// 隐藏并且置空
                $("#drawback").hide();
                $("#drawback2").hide();
                $("#paytax").show();
                $("#paytax2").show();
            }
        });
        
        $("#taxClass").change(function(){
        	$('#ccy').val("").trigger('change');
            var taxClass = $("#taxClass").val();
            $('#channelId').prop('disabled', taxClass == TAX_CLASS_02 ?false:true); 
            $('#channelId').val("").trigger('change');
            // 增值税品种非通道增值税时，通道代码选项置灰,否则可选            
            if(table.table) {
                table.showColumns([{field:'channelId',visible: taxClass == TAX_CLASS_02 ? true:false},{field:'channelName',visible: taxClass == TAX_CLASS_02 ? true:false}]);  
                }
            portfolioIdBind();
            });
        
        $("#channelId").change(function(){
            var channelId = $("#channelId").val(); 
            if(channelId &&  $('#channelId').select2('data')[0].object) {
                var str = $('#channelId').select2('data')[0].object.split(',');
                var payType = str[1];//划款方式
                if(payType == PAY_MODE_02) {
                    $("#bussTrdTransferOrder_div").hide();
                } else {
                    $("#bussTrdTransferOrder_div").show();
                }
            } else {
                $("#bussTrdTransferOrder_div").show();
            }
            
            if($("#taxClass").val() == TAX_CLASS_02) { 
                portfolioIdBind();
            }
            
        	if(channelId){
        		var obj = {};
        		obj.finprodMarketId = channelId;
        		
    			Joyin.ajax(basePath + 'invest/product/paythevatquery/PayTheVatQuery002/searchCurrency',obj,function (data) {
    				$('#ccy').val(data[0]).trigger("change").prop('disabled', true);
				})
        	}
        });
        
        $("#payTaxMDate").change(function(){
            if($("#taxClass").val() == TAX_CLASS_02) { 
                portfolioIdBind();
            }
        });
        
        $("#portfolioId").change(function(){
        	if($("#taxClass").val()==='01' && $('#portfolioId').val()){
        		$('#ccy').val('CNY').trigger("change").prop('disabled', true);
        	}
        })
    };
    /**
     * 组合代码的联动
     */
    var portfolioIdBind = function(){
        $('#portfolioId').val("").trigger('change');
        var taxClass = $("#taxClass").val();
        if(taxClass == TAX_CLASS_01) {
            $('#portfolioId').prop('disabled', false);  
        } else if(taxClass == TAX_CLASS_02){
            var channelId = $("#channelId").val(); 
            var payTaxMDate = $("#payTaxMDate").val();
            if(channelId && payTaxMDate) {
                $('#portfolioId').prop('disabled', false); 
            } else {
                $('#portfolioId').prop('disabled', true); 
            }
        } else {
            $('#portfolioId').prop('disabled', true);
        }
    }
    /**
     * 获取页面查询参数
     */
    var getSearchParams = function() {        
        return {
        	taxidenType: $.trim($('#taxidenType').val()),
        	payTaxVDate: $.trim($('#payTaxVDate').val()),
        	payTaxMDate: payment1,//缴税截止日
        	refundTaxVDate: $.trim($('#refundTaxVDate').val()),
        	refundTaxMDate: payment1,//退税截止日   
        	payment1:payment1,
        	ccy:$('#ccy').val(),
        	taxClass:$('#taxClass').val()
        }
    };
   var queryObj = function() {
    	var obj = getSearchParams();
    	obj.payment1=payment1;
    	if($("#portfolioId").val()){
    		obj.portfolioId = $("#portfolioId").val().join(",");
    	}
    	if($("#channelId").val()) {
    	    obj.channelId = $("#channelId").val();
    	}
        return obj;
    };
    var loadSubProductTable = function () {
        var isclear = true;//add by jiangpeng on 20210512 ZGBSSE-362
    	// 校验缴税日期与产品账套日期
        Joyin.ajaxJson(basePath +'invest/product/paythevatquery/PayTheVatQuery002/checkBokDate', queryObj(), function(result) {
        			Joyin.ajax(basePath + 'invest/product/paythevatquery/PayTheVatQuery002/search',
        	        		queryObj(),function (rows) {
        	        					 table.table ? table.reload() : table.load(); 
        	        					 if(rows.root && rows.root.length>0) {
        	        					     var isShowWaterTrafficTax = rows.root[0].showWaterTrafficTax;
        	        					     table.showColumns([{field:'waterTrafficTax',visible: isShowWaterTrafficTax}]); 
        	        					 }
                                        isclear=false;//add by jiangpeng on 20210512 ZGBSSE-362
        	        					 
        					        	 table.clear(function () {
        					        		 table.addRow(rows.root);
        					              })
        				            })
             });
        //add by jiangpeng on 20210512 ZGBSSE-362
        if(isclear){
            table.clear();
        }
        //add by jiangpeng on 20210512 ZGBSSE-362
    }
    var tableOption = {
//    	    ajax: {
//                url: 'search',
//                type: 'POST',
//                data: function() {
//                	var obj = getSearchParams();
//                	if($("#portfolioId").val()){
//                		obj.portfolioId = $("#portfolioId").val().join(",");
//                	}
//                    return obj;
//                }
//            },
            checkbox: true,
            page: false,
            cellMinWidth : 100,
    		cols: [
               {field:'taxClassStr',sortable:'taxClass',width:150, title: '增值税品种', sort: true},
               {field:'channelId',width:150, title: '通道代码', sort: true},
               {field:'channelName',width:150, title: '通道名称', sort: true},
               {field:'portfolioId',width:150, title: '组合代码', sort: true},
               {field:'profitType',visible: false},
               {field:'finprodId',visible: false},
               {field:'taxClass',visible: false},
               {field:'prodId',visible: false},
               {field:'payType',visible: false},
               {field:'acctSubjectType',visible: false},
               {field:'finprodAbbr',visible: false},
    		   {field:'portfolioName',width:150, title: '组合简称', sort: true},	   
               {field:'taxStartDate',width:150, title: '缴税开始日', sort: true},
               {field:'dividendIncome',width:150, title: '红利收入税', sort: true, edit: true,type:'number'},
               {field:'interestIncome',width:150, title: '利息收入税', sort: true, edit: true,type:'number'},
               {field:'difbetIncome',width:150, title: '价差收入税', sort: true, edit: true,type:'number'},
               {field:'appreciationTax',width:150, title: '增值税', sort: true,type:'number'},
               {field:'constructionTax',width:150, title: '城建附加税', sort: true, edit: true,type:'number'},
               {field:'educationTax',width:150, title: '教育附加税', sort: true, edit: true,type:'number'},
               {field:'placeeducationTax',width:170, title: '地方教育费附加税', sort: true, edit: true,type:'number'},
               {field:'waterTrafficTax',width:170, title: '水利基金附加税', sort: true, edit: true,type:'number'},
               {field:'additionalTax',width:150, title: '附加税', sort: true,type:'number'},
               {field:'taxTotal',width:150, title: '税费总额', sort: true,type:'number'},
               {field:'remark',visible: false,title: '交易备注'}
            ],
            beforeEditCell: function(preVal, field, rowData){
            	//退税列表可编辑
            	if($("#taxidenType").val()=='C13' && rowData.portfolioName!='合计'){
            		return true;
            	}
            	/**20200107 add taoran**/
            	//税费缴费列表可编辑
            	if($("#taxidenType").val()=='C12' && rowData.portfolioName!='合计'){
            		return true;
            	}
            	/**20200107 add taoran**/
            	return false;
            },
            editCell: function (value, row, field, cell, column) {
            	var arr = table.getAllRows();
            	var i = row.LAY_TABLE_INDEX;
            	//可编辑行相加处理
            	tableRowAdd(field, i, arr);
            	var sumAmt = 0;
            	if(field=='dividendIncome'){
            		sumAmt = tableColAdd(sumAmt, field ,arr);
            		arr[arr.length-1].dividendIncome = JoyinMath.doAdd(0, sumAmt, 2,JoyinMath.ROUND_HALF_UP);
        		}else if(field=='interestIncome'){
        			sumAmt = tableColAdd(sumAmt, field ,arr);
            		arr[arr.length-1].interestIncome = JoyinMath.doAdd(0, sumAmt, 2,JoyinMath.ROUND_HALF_UP);
        		}else if(field=='difbetIncome'){
        			sumAmt = tableColAdd(sumAmt, field ,arr);
            		arr[arr.length-1].difbetIncome = JoyinMath.doAdd(0, sumAmt, 2,JoyinMath.ROUND_HALF_UP);
        		}else if(field=='constructionTax'){
        			sumAmt = tableColAdd(sumAmt, field ,arr);
            		arr[arr.length-1].constructionTax = JoyinMath.doAdd(0, sumAmt, 2,JoyinMath.ROUND_HALF_UP);
        		}else if(field=='educationTax'){
        			sumAmt = tableColAdd(sumAmt, field ,arr);
            		arr[arr.length-1].educationTax = JoyinMath.doAdd(0, sumAmt, 2,JoyinMath.ROUND_HALF_UP);
        		}else if(field=='placeeducationTax'){
        			sumAmt = tableColAdd(sumAmt, field ,arr);
            		arr[arr.length-1].placeeducationTax = JoyinMath.doAdd(0, sumAmt, 2,JoyinMath.ROUND_HALF_UP);
        		}else if(field=='waterTrafficTax'){
                    sumAmt = tableColAdd(sumAmt, field ,arr);
                    arr[arr.length-1].waterTrafficTax = JoyinMath.doAdd(0, sumAmt, 2,JoyinMath.ROUND_HALF_UP);
                }
            	table.renderRow(arr.length-1,arr[arr.length-1]);
            	//横向相加
            	tableRowAdd(field, arr.length-1, arr);
            }
    	}
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable(tableOption);
    if (listpayTheVatQuery002TableDto && listpayTheVatQuery002TableDto.length > 0) {
    	table.load();		
   	 	table.clear(function () {
   		table.addRow(listpayTheVatQuery002TableDto)
         })
    }
    
	tableColAdd = function(sumAmt, field ,arr){
		for(var j=0; j<arr.length-1;j++){
			if(field=='dividendIncome'){
    			sumAmt = new BigNumber(arr[j].dividendIncome).plus(new BigNumber(sumAmt)); 
    		}else if(field=='interestIncome'){
    			sumAmt = new BigNumber(arr[j].interestIncome).plus(new BigNumber(sumAmt)); 
    		}else if(field=='difbetIncome'){
    			sumAmt = new BigNumber(arr[j].difbetIncome).plus(new BigNumber(sumAmt)); 
    		}else if(field=='constructionTax'){
    			sumAmt = new BigNumber(arr[j].constructionTax).plus(new BigNumber(sumAmt)); 
    		}else if(field=='educationTax'){
    			sumAmt = new BigNumber(arr[j].educationTax).plus(new BigNumber(sumAmt)); 
    		}else if(field=='placeeducationTax'){
    			sumAmt = new BigNumber(arr[j].placeeducationTax).plus(new BigNumber(sumAmt)); 
    		}else if(field=='waterTrafficTax'){
    		    sumAmt = new BigNumber(arr[j].waterTrafficTax).plus(new BigNumber(sumAmt)); 
            }
		}
		return sumAmt;
	};
	
	tableRowAdd = function(field, i, arr){
	 	if(field=='dividendIncome' || field=='interestIncome' || field=='difbetIncome'){
    		// 红利收入税
    		if (arr[i].dividendIncome == ""
    			|| arr[i].dividendIncome == null) {
    			arr[i].dividendIncome = 0;
    		}
    		// 利息收入税
    		if (arr[i].interestIncome == ""
    			|| arr[i].interestIncome == null) {
    			arr[i].interestIncome = 0;
    		}
    		// 价差收入税
    		if (arr[i].difbetIncome == ""
    			|| arr[i].difbetIncome == null) {
    			arr[i].difbetIncome = 0;
    		}
    		var appreciationTax = new BigNumber(arr[i].dividendIncome).plus(new BigNumber(arr[i].interestIncome)).plus(new BigNumber(arr[i].difbetIncome));
			// 增值税
    		arr[i].appreciationTax = appreciationTax.toFixed(2,JoyinMath.ROUND_HALF_UP);
    	} else {
    		// 城建附加税
    		if (arr[i].constructionTax == ""
    			|| arr[i].constructionTax == null) {
    			arr[i].constructionTax = 0;
    		}
    		// 教育附加税
    		if (arr[i].educationTax == ""
    			|| arr[i].educationTax == null) {
    			arr[i].educationTax = 0;
    		}
    		
            // 地方教育费附加
            if (arr[i].placeeducationTax == ""
                || arr[i].placeeducationTax == null) {
                arr[i].placeeducationTax = 0;
            }
            
    		// 水利交通费附加税
    		if (arr[i].waterTrafficTax == ""
    			|| arr[i].waterTrafficTax == null) {
    			arr[i].waterTrafficTax = 0;
    		}

    		var additionalTax = new BigNumber(arr[i].constructionTax).plus(new BigNumber(arr[i].educationTax)).plus(new BigNumber(arr[i].placeeducationTax)).plus(new BigNumber(arr[i].waterTrafficTax));
			//附加税
    		arr[i].additionalTax = additionalTax.toFixed(2,JoyinMath.ROUND_HALF_UP);
    	}
		//税费总额
    	arr[i].taxTotal = JoyinMath.doAdd(arr[i].appreciationTax, arr[i].additionalTax, 2,JoyinMath.ROUND_HALF_UP);
    	//渲染行数据
    	table.renderRow(i,arr[i]);
	};

	//提交 zou
	var docommit = function(object){
       Joyin.ajaxJson(basePath + 'invest/product/paythevatquery/PayTheVatQuery002/check', object, function(result) {
           WorkFlow.doSubmitList(basePath + 'invest/product/paythevatquery/PayTheVatQuery002/docommit', 
                   object, "FinproductPayTheVatQuery002_btn_submit",null,function(data){
                   var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
           });
       });
       
   };
   function resetTable() {
       ref = setInterval(function () {
           if ($(".layui-table-view").length > 0) {
               var height = $(window).height();
               $(".layui-table-view").css({height: height / 2 - 92 + 'px'});
               $(".layui-table-view .layui-table-box .layui-table-body").css({height: (height / 2 - 175) + 'px'});
               setted = true;
           }
       }, 300);
       var set = setInterval(function () {
           if (setted) {
               clearInterval(ref);
               clearInterval(set);
           }
       }, 300);
   }
   /**
    * 定义数据表格
    *
    */
   var settleOption = {
       searchOnInit: true,
       checkbox: false,
       cols: [
           {
               field: 'isNeedSend', width: 140, title: 'BussTrdTransferOrder.isNeedSend', edit: true, sortable: false,
               editOption: {type: 'select', multiple: false, searchable: false, showField: 'text', result: YES_NO}
           }
           , {field: 'payTypeText', width: 130, title: 'BussTrdTransferOrder.payType', sortable: false, sortable: false}
           , {field: 'settleDateText', width: 130, title: 'BussTrdTransferOrder.settleDate', sortable: false}
           , {field: 'settleAmt', width: 130, title: 'BussTrdTransferOrder.settleAmt', type: 'money', sortable: false}
           , {field: 'ccyText', width: 130, title: 'BussTrdTransferOrder.ccy', sortable: false}
           , {
               field: 'payAcctNo', width: 130, title: 'BussTrdTransferOrder.payAcctNo', edit: true, sortable: false,
               editOption: {
                   type: 'select', multiple: false, searchable: true, showField: 'id', parentIds: 'orderId_select,acctNoList',
                   url: JoyinUtil.getRealPath("/component/trade/BussTrdTransferOrder/select"), id: 'payAcctNo', postData: function () {
                       return {selectValue: JSON.stringify(payAcctNoList), type: 'pay'};
                   }
               }
           }
           , {field: 'payAcctName', width: 150, title: 'BussTrdTransferOrder.payAcctName', sortable: false}
           , {
               field: 'recAcctNo', width: 130, title: 'BussTrdTransferOrder.recAcctNo', edit: true, sortable: false,
               editOption: {
                   type: 'select', multiple: false, searchable: true, showField: 'id', parentIds: 'orderId_select,acctNoList',
                   url: JoyinUtil.getRealPath("/component/trade/BussTrdTransferOrder/select"), id: 'recAcctNo', postData: function () {
                	   return {selectValue: JSON.stringify(recAcctNoList), type: 'rec'};
                   }
               }
           }
           , {field: 'recAcctName', width: 150, title: 'BussTrdTransferOrder.recAcctName', sortable: false}
           , {field: 'purpose', width: 130, title: 'BussTrdTransferOrder.purpose', sortable: false, edit: true}
           , {field: 'portfolioId', width: 130, title: 'BussTrdTransferOrder.portfolioId', sortable: false}
           , {field: 'portfolioName', width: 150, title: 'BussTrdTransferOrder.portfolioName', sortable: false}
           , {
               field: 'orderId', visible: false, edit: true, width: 0,
               editOption: {
                   type: 'select', id: 'orderId_select', childrenIds: 'recAcctNo,payAcctNo'
               }
           }
           , {field: 'ps', visible: false}
           , {field: 'settleStatus', visible: false}
           , {field: 'isTotalTrade', visible: false}
           , {field: 'finprodType2', visible: false}
       ],
       done: function () {
//           $("[data-field='orderId']").hide();
//           resetTable();
       },
       page: false,
       data: [],
       beforeEditCell: function(preVal, field, rowData){
    	   if(field == 'payAcctNo') {
        	   payAcctNoList = rowData.payAcctNoList;
    	   } else if (field == 'recAcctNo') {
        	   recAcctNoList = rowData.recAcctNoList;
    	   }

    	   return true;
       },
       editCell: function (value, row, field, cell, column) {
           if (field == 'payAcctNo') {
        	   /*$.each(row.payAcctNoList, function (i, v) {
	               if (v.id == value) {
	                   row.payAcctName = v.text;
	               }
       		   });*/
        	   if (typeof value == "undefined" || value == null || value == "") {
                   row.payAcctName = "";
                   orderstable.renderRow(row.LAY_TABLE_INDEX, row);
               } 
               // 选择付款账户带出贷款账户名称
               else {
                   Joyin.ajax(JoyinUtil.getRealPath('/component/trade/BussTrdTransferOrder/queryStlCapacctInfoByAcctNo'), {
                       ccy: row.ccy,
                       acctNo: value
                   }, function (result) {
                       // 网点
                	   //row.recAcctPoint = result.largePayno;
                       //add by wangmin 2019-0-06 begin
                       if (!result) {
                           Joyin.error("该币种下，无此账户。")
                           return;
                       }
                       row.payAcctName = result.acctName;
                       //add by wangmin 2019-0-06 end
                       orderstable.renderRow(row.LAY_TABLE_INDEX, row);
                   })
               }
           // ZGBUGTWO-108 产品增值税缴纳生产指令更改是否发送指令会提示无此账户 modify by lizhi 20200712 start
           } else if (field == 'recAcctNo') {
           // ZGBUGTWO-108 产品增值税缴纳生产指令更改是否发送指令会提示无此账户 modify by lizhi 20200712 end
               /*$.each(row.recAcctNoList, function (i, v) {
                   if (v && v.id == value) {
                       row.recAcctName = v.text;
                   }
               });
               if (typeof value == "undefined" || value == null || value == "") {
                   row.recAcctName = "";
               }*/
        	   if (typeof value == "undefined" || value == null || value == "") {
                   row.recAcctName = "";
                   orderstable.renderRow(row.LAY_TABLE_INDEX, row);
               } else {
            	   // 选择收款账户带出行内/行外、网点
                   Joyin.ajax(JoyinUtil.getRealPath('/component/trade/BussTrdTransferOrder/queryStlCapacctInfoByAcctNo'), {
                       ccy: row.ccy,
                       acctNo: value
                   }, function (result) {
                	   if (!result) {
                           Joyin.error("该币种下，无此账户。")
                           return;
                       }
                       row.recAcctName = result.acctName;
                       orderstable.renderRow(row.LAY_TABLE_INDEX, row);
                   })
               }
           }
       }
   }
   if (bussTrdTransferOrder && bussTrdTransferOrder.length > 0) {
       settleOption.data = settleOption.data.concat(bussTrdTransferOrder);
   }  
   var orderstable = $("#bussTrdTransferOrder_table").joyintable(settleOption);
   /**
    * 初期化处理保存生成指令参数
    */
   var saveCalcCondition = function() {
       // 初期化处理时，表格中是否有数据
       
       if (stlCommandInput() != null) {
           var data = stlCommandInput();
           // 保存生成指令参数
           calcConditionObjBackUp = data;
       }
   };
 //生成指令参数保存
   var orderAcctMap = {};
   var recAcctNoList = null;
   var payAcctNoList = null;
   var stlCommandInput = function () {
   var tradeDate = null;
   var ms = '';
   	//获取纳税截止日的值，并赋给payment
	 	if ($("#taxidenType").val()=='C12'){
	           payment1 = $.trim($('#payTaxMDate').val());
	           tradeDate = $.trim($('#payTaxVDate').val());
	           ms ='缴税';
	           //为空时给提示
	           if(payment1==''||payment1==null){
	        	   payment1 = tradeDate;
	           }
	           if(payment1==''||payment1==null){
	               Joyin.alert(ms+"截止日不可为空",2);
	               return;
	            }
	           if(tradeDate=='' || tradeDate == null){
	        	   Joyin.alert(ms+"日不可为空",2);
	               return; 
	           }
	       }else if($("#taxidenType").val()=='C13'){
	           payment1 = $.trim($('#refundTaxMDate').val());
	           tradeDate = $.trim($('#refundTaxVDate').val());
	           ms ='退税';
	           
	           //为空时给提示
	           if(payment1==''||payment1==null){
	        	   payment1 = tradeDate;
	           }
	           if(payment1==''||payment1==null){
	               Joyin.alert(ms+"截止日不可为空",2);
	               return;
	            }
	           if(tradeDate=='' || tradeDate == null){
	        	   Joyin.alert(ms+"日不可为空",2);
	               return; 
	           }
	       }

   	//获取选中行
   	var data = table.getAllCheckedRows();
//   	if(!data || (data && data.length<1)){
//   		Joyin.alert("请选中数据在进行操作",2);
//        return; 
//   	}
       var dtos = [];
       for(var i = 0 ; i< data.length; i++){
    	   var obj = data[i];
    	   if(obj.portfolioName=='合计'){
    		   continue;
    	   }
    	   dtos.push({
    		   'isTotalTrade': 'Y'
    		   , 'bankLendingType': null
    		   , 'portfolioId': obj.portfolioId
    		   , 'deal': {
    			   portfolioId: obj.portfolioId,
    			   finprodType2: '16',
    			   ps: $("#taxidenType").val(),
    			   tradeDate: tradeDate,
    			   tradeAmt: obj.taxTotal,
    			   ccy: $("#ccy").val()
    		   }
    	   })
       }
       return dtos;
   };

   //检查生成指令参数是否发生变化
   var isCalcConditionChanged = function () {
       // 参照模式，不做check
       if (pageModel === PAGE_MODEL_REFERENCE) {
           return false;
       }
       if (!$("#bussTrdTransferOrder_div").is(":visible")) {
           return false;
       }
       if (calcConditionObjBackUp == null) {
    	   Joyin.alert("指令列表数据为空,请选中数据在点击生成指令按钮!",2);
           return true;
       }
       if (stlCommandInput() != null) {
           var currentCalcConditionObj = stlCommandInput();
           if(currentCalcConditionObj.length!=calcConditionObjBackUp.length){
        	   Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013", ["税费信息","生成划款指令"]));
        	   return true;
           }
           // 影响计划生成的所有项目与计划生成时记录的值比较，值变更时
           for (var key in currentCalcConditionObj) {
               for (var key1 in currentCalcConditionObj[key]) {
                   if (typeof currentCalcConditionObj[key][key1] != "object"
                       && currentCalcConditionObj[key][key1] != calcConditionObjBackUp[key][key1]) {
                       var labelText = JoyinUtil.getLabel("BussTrdTransferOrder." + key1);
                       Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0013",
                           [labelText, "生成划款指令"]));
                       return true;
                   }
               }
           }
           for (var key in currentCalcConditionObj) {
               for (var key1 in currentCalcConditionObj[key].deal) {
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
// 生成划款指令
	$("#bussTrdTransferOrder_btn_generate").on("click", function () {
		if($("#taxidenType").val()== null || $("#taxidenType").val()== ''){
   		 	Joyin.alert("类型不可为空",2);
   		 	return;
		}
		//wangyang1 BS-1617 【产品增值税缴纳】点击生成指令时，没有提示 start
	   	var data = table.getAllCheckedRows();
	   	if(!data || (data && data.length<1)){
	   		Joyin.alert("请选中数据在进行操作",2);
	        return; 
	   	}
	    //wangyang1 BS-1617 【产品增值税缴纳】点击生成指令时，没有提示 end
		buildRemark();
	    if (stlCommandInput() != null) {
	        var data = stlCommandInput(); 
	        if (!data || (data && data.length<1)) {
	            return;
	        }
	        // 保存生成指令参数
	        calcConditionObjBackUp = data;
	        orderAcctMap = {};
	        var result = [];
	        Joyin.ajaxJson(JoyinUtil.getRealPath('/common/multiOrder/genOrderCheck'), data, function () {
	            Joyin.ajaxJson(JoyinUtil.getRealPath('/common/multiOrder/generateOrder'), data, function (ret) {
	                if (data.length == ret.data.length) {
	                    for (var key in ret.data) {
	                        orderAcctMap[ret.data[key].orderId] = {
	                            payAcctNoList: ret.data[key].payAcctNoList,
	                            recAcctNoList: ret.data[key].recAcctNoList
	                        }
	                    }
	                    orderstable.clear(function () {
	                 	   orderstable.addRow(ret.data)
	                    })
	                }
	            }, false);
	        });
	    }
	});
	function buildRemark(){
		if(table.getAllRows() == null || table.getAllRows().length<=0) {
			return;
		}
		
		 var payDate=$('#payTaxMDate').val();//taxTotal
		 var year = payDate.substring(0, 4); 
			var n = payDate.substring(5, 7);
			var quarter = '';
			if(n=='01'||n=='02'||n=='03'){
				quarter = '1';
			}else if(n=='04'||n=='05'||n=='06'){
				quarter = '2';
			}else if(n=='07'||n=='08'||n=='09'){
				quarter = '3';
			}else{
				quarter = '4';
			}
		
		var finAssetIntInfoList = table.getAllCheckedRows();
		var sumtaxTotal = 0;
		if(finAssetIntInfoList.length>0) {
	    	for(var i=0;i<finAssetIntInfoList.length;i++){
	     	   var obj = finAssetIntInfoList[i];
	    	   if(obj.portfolioName=='合计'){
	    		   continue;
	    	   }
	    		sumtaxTotal +=parseFloat(obj.taxTotal);
	    	}	
		}
		sumtaxTotal = sumtaxTotal.toFixed(2);
		//var table=$("#dataTable").joyintable(tableOption);
		/*var sumtaxTotal = 0; 
		var item_table = layui.table.cache.dataTable;//['#dataTable'];
		for (var index in item_table) {
		     //console.log(index);
		     //console.log(item_table[index].taxTotal);
		     sumtaxTotal += item_table[index].taxTotal;
		}*/
		var fy=sumtaxTotal;
		$("#sumLine").val(sumtaxTotal)
		var a =year+"年"+quarter+"季度销项税金结转金额:"+fy;
		$("#remark").val(a);
		
	}
	/**
     * 画面加载完成
     */
	
    $(function() {
        $("#portfolioId").textSelect("setValue",portfolioIdOld);//add by jiangpeng on 20210512 ZGBSSE-365
        // 绑定按钮事件
        $('#FinproductPayTheVatQuery002_btn_search').click(function() { 
        	var message ="";
            //获取纳税截止日的值，并赋给payment
        	if($("#taxidenType").val()== null || $("#taxidenType").val()== ''){
        		 Joyin.alert("类型不可为空",2);
        	} 
        	else if ($("#taxClass").val()== null || $("#taxClass").val()== ''){
        	    Joyin.alert("增值税品种不可为空",2);
        	} 
        	else if ($("#taxClass").val() == TAX_CLASS_02 && ($("#channelId").val() == null || $("#channelId").val() == '')) {
        	    Joyin.alert("通道代码不可为空",2);
        	}
        	else {
            	if ($("#taxidenType").val()=='C12'){
                    payment1 = $.trim($('#payTaxMDate').val());
                    message = "缴税";
                }else if ($("#taxidenType").val()=='C13'){
                    payment1 = $.trim($('#refundTaxMDate').val());
                    message = "退税";
                }
                //为空时给提示
                if(payment1==''||payment1==null){
                      Joyin.alert(message+"截止日不可为空",2);
                 }else{
                	 loadSubProductTable();
                }
        	}
            payTaxMDateCheck = $("#payTaxMDate").val(); // add by jiangpeng on 20210512 ZGBSSE-363
        });
        //绑定联动
        linkBind();
        //绑定删除事件
        
        //绑定提交事件
        $('#FinproductPayTheVatQuery002_btn_submit').click(function(){
            // add by jiangpeng on 20210512 ZGBSSE-363
            //校验是否修改了纳税结束日
            if(isPayTaxMDateChange()) {
                Joyin.error("纳税结束日发生调整，请重新查询!");
                return;
            }
            // add by jiangpeng on 20210512 ZGBSSE-363
        	// 检查生成划款指令参数是否发生变化
            if (isCalcConditionChanged()) {
                return;
            }
            
            if (table != null && table != '') {
            	var datas = table.getAllCheckedRows();
                if(datas==null || datas.length<1){
               		Joyin.alert("请选中数据进行操作!",2);
                    return; 
               	}
                // modify by jiangpeng on 20210512 ZGBSSE-364
                if($("#taxClass").val() == TAX_CLASS_02) {
                    var str = $('#channelId').select2('data')[0].object.split(',');
                    var payType = str[1];//划款方式
                    if(payType == PAY_MODE_02) {
                        buildRemark();
                    }
                }
                // modify by jiangpeng on 20210512 ZGBSSE-364
               var object = {};
               if(datas[datas.length-1].portfolioName=='合计'){
            	   datas.splice(datas.length-1, 1);
               }
               object.listpayTheVatQuery002TableDto = datas;
               object.bussTrdTransferOrder = orderstable.getAllRows();
               object.taxidenType = $.trim($('#taxidenType').val());
               object.payTaxVDate = $.trim($('#payTaxVDate').val());
               object.payTaxMDate = $.trim($('#payTaxMDate').val());
               object.totleTradeId = $.trim($('#totleTradeId').val());
               object.portfolioId = $.trim($('#portfolioId').val());
               object.finprodId = $.trim($('#finprodId').val());
               object.refundTaxVDate = $.trim($('#refundTaxVDate').val());
               object.refundTaxMDate = $.trim($('#refundTaxMDate').val());
               object.remark =  $.trim($('#remark').val());
               object.taxClass = $.trim($('#taxClass').val());
               object.operateType = "submit";
               //获取纳税截止日的值，并赋给payment
           	   if ($("#taxidenType").val() == '' || $("#taxidenType").val() == null){
                   Joyin.alert("类型不可为空",2);
                   return; 
           	   } else if ($("#taxidenType").val()=='C12'){
                   payment1 = $.trim($('#payTaxMDate').val());
                   payment2 = $.trim($('#payTaxVDate').val());
                   //为空时给提示
                   if(payment2 == ''||payment2 == null){
                       Joyin.alert("缴税日期不可为空",2);
                       return;
                   }
               }else if ($("#taxidenType").val()=='C13'){
                   payment1 = $.trim($('#refundTaxMDate').val());
                   payment2 = $.trim($('#refundTaxVDate').val()); 
                   //为空时给提示
                   if(payment2 == ''||payment2 == null){
                       Joyin.alert("退税日期不可为空",2);
                       return;
                   }
               }
               object.payment1 = payment1;
               object.payment2 = payment2;
               object.pageModel = pageModel;
               object.portfolioId = $.trim($('#portfolioId').val());
               if(object.taxClass == TAX_CLASS_02) {
                   if($('#channelId').select2('data')[0].object) {
                       var str = $('#channelId').select2('data')[0].object.split(',');
                       object.payType = str[1]; 
                   }
                  object.channelId = $.trim($('#channelId').val());
               } 
               var ccy =$.trim($('#ccy').val());
			   if(ccy=='' || ccy == null){
				   Joyin.alert("请输入币种",2);
                   return;          	   
			   }
               object.ccy = ccy;
                // 校验缴税日期与产品账套日期
                Joyin.ajaxJson(basePath +'invest/product/paythevatquery/PayTheVatQuery002/checkdate', object, function(result) {
                		if(!result != null){
                             //Joyin.alert("返回值为空 ",2);
                             docommit(object);
                         }
                     });              
            }else{
                Joyin.alert("列表无数据，请确认后重新输入！",2);
                //Joyin.error(JoyinUtil.getMessage("列表无数据，请确认后重新输入！"));
                return;
            }
            
        });
        // ZGBUGTWO-109 产品缴纳增值税退回后重新提交提示需要生产指令 add by lizhi 20200712 start
        if (pageModel != PAGE_MODEL_REFERENCE) {
			calcConditionObjBackUp = stlCommandInput();
        }
        // ZGBUGTWO-109 产品缴纳增值税退回后重新提交提示需要生产指令 add by lizhi 20200712 end
    });
	
});



