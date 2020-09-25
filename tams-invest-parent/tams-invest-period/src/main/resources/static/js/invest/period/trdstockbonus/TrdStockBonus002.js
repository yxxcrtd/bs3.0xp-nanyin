(function () {
	var initFlag = true;
	var needInit = false;
	var sumRegistShare;//权益登记日股本总和
	var acctNoList;
	/**
     * 分红明细表格
     */
	var calcConditionObjBackUp,
	 repoInfoTableOption = {
	        rowno: true,
	        searchOnInit: true,
	        checkbox: false,
	        page: false,
	        cols: [
	            //组合代码
	            {field: 'portfolioId', width: "300", title: 'IntDetailDuration.portfolioId', sortable:false}
	            //证券托管户
	            ,{field: 'secAcctId', width: "300", title: 'TrdStockBonus001.secManageAcctId', sortable:false}
	            //组合名称
	            ,{field: 'portfolioName', width: "300", title: 'IntDetailDuration.portfolioName', sortable:false}
	            //权益登记日股本
	            , {field: 'regDateAmt', width: "200", title: '权益登记日股本', type: 'money', sortable:false}
	            //分红金额
	            , {field: 'fpriceAmt', width: "200", title: '分红金额',type: 'money',sortable:false,edit:true}
	            //分红股本
	            , {field: 'shareAmt', width: "200", digit:'0', title: '分红股本', type: 'money', sortable:false,edit:true}
                ,
                {field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: ['edit'],
                    render: function (data, type, row, meta) {
                        //实际交易组合无子组合交易明细
                        if(row.investYesorno =='Y'){
                            return;
                        }
                        return '<i class="btn layui-icon detail" data-ignore="true" title="明细">&#xe60a;</i>';
                    }
                }

	        ],
	        beforeEditCell:function(value, field, data){
//	        	return false;
	        	 if(field=='shareAmt'&&value){
                     return true;
	        	 }
	        	 if(field=='fpriceAmt'&&value){
                     return true;
	        	 }
	        	 return false;
	        },
	        done : function(){
	            // if (formTodoList) {
	            //     calcConditionObjBackUp = tableInitData();
	            // }
	            if(!(typeof needInit === "undefined") && needInit){
	            	resetTableData();
	            	needInit =false;
	            }
	        },
	        data: []
	    }
	if (dividInfoList && dividInfoList.length > 0) {
        repoInfoTableOption.data = repoInfoTableOption.data.concat(dividInfoList);
    }
	
	/**
     * 定义数据表格
     */
    var repoInfoTable = $("#intDetailTable").joyintable(repoInfoTableOption);
    var tableInitData = function () {
        var finAssetIntInfos = repoInfoTable.getAllRows();
        var dtos = [];
        for (var i = 0; i < finAssetIntInfos.length; i++) {
            var dealReal = JSON.parse(deal);
            dealReal.tradeMarket=$("#tradeMarket").val();
            dealReal.settleDate=$("#tradeDate").val();
            dealReal.tradeAmt=finAssetIntInfos[i].tradeAmt;
            dtos.push({
                'isTotalTrade': YES_OR_NO_Y
                , 'bankLendingType': null
                , 'portfolioId': finAssetIntInfos[i].portfolioId
                , 'deal':dealReal
            })
        }
        return dtos;
    }
    
    /**
     * 分红明细初始化
     */
    var dealIntDetail = function() {
        var finprodMarketId = $('#finprodMarketId').val();
        var regDate = $('#regDate').val();
        /*var chlId = $("#chlId").val()?$("#chlId").val():$('#secManageAcctId').val();*/
//        var chlId = $('#secManageAcctId').val();
        if (finprodMarketId && regDate ) {
            var param = {
                finprodMarketId : finprodMarketId,
                regDate : regDate
//                , chlId: chlId
            };
            Joyin
                    .ajaxSync(
                            JoyinUtil
                                    .getRealPath('/invest/period/trdstockbonus/TrdStockBonus002/findividInfo'),
                            param, function(result) {
                                if (result.data) {
                                	dividInfoList = result.data;
                                    /* 计算股本总数 */
                                    sumRegistShare = 0;
                                    for (var i = 0; i < dividInfoList.length; i++) {
                                        sumRegistShare = JoyinMath.doAdd(sumRegistShare, dividInfoList[i].regDateAmt);
                                    }
                                    triValueChange('regDateAmt',sumRegistShare);
                                }
                            });
        } else {
        	dividInfoList = [];
        }
        resetTableData();
    }
    
//    /**
//     * 利用占比计算所有的金额
//     */
//    var resetTableData = function () {
//        // 按占比计算明细分红金额、分红股本
//        var finAssetIntInfos = dividInfoList;
//        //var sumAmount = 0;
//        if(!finAssetIntInfos){
//        	return;
//        }
////      分红金额、分红股本
//        var itemArr = ['fpriceAmt','shareAmt'];
////      所有的钱
//        var itemObj = {};
////      已分配的钱
//        var sumObj = {};
////      初始化所有的钱和已分配的钱
//        $.each(itemArr,function(index,val){
//        	itemObj[val] = $('#'+val).val()||0;
//        	sumObj[val] = 0;
//        })
////      分配
//        for (var i = 0; i < finAssetIntInfos.length; i++) {
////          最后一个
//        	if (i == finAssetIntInfos.length - 1) {
////        		将剩余的钱放入对应值
//        		$.each(itemArr,function(index,val){
//        			finAssetIntInfos[i][val] = JoyinMath.doMinus(itemObj[val], sumObj[val]);
//                })
//        	}
////        	非最后一个
//        	else {
////        		计算占比
//        		var prop = JoyinMath.doDiv(finAssetIntInfos[i].regDateAmt, sumRegistShare, 12, JoyinMath.ROUND_HALF_UP);
////        		根据占比处理所有的钱
//        		$.each(itemArr,function(index,val){
////        			计算当前需要的钱
//        			var thisNum = JoyinMath.doMul(itemObj[val], prop, 2, JoyinMath.ROUND_HALF_UP);
////        			处理已分配的钱
//        			sumObj[val] = JoyinMath.doAdd(sumObj[val], thisNum);
////        			把钱放入对应数据
//        			finAssetIntInfos[i][val] = thisNum;
//                })
//        	}
//        }
//        repoInfoTable.reload({ data:finAssetIntInfos});
//
//    }

    /**
	   * 利用占比计算所有的金额
	   */
  var resetTableData = function () {
	  var finAssetIntInfos = dividInfoList;
	  if(!finAssetIntInfos){
			 return;
	  }
  	  for (var i = 0; i < finAssetIntInfos.length; i++) {
	 		//计算当前行
  		  if($("#unitCprice").val()){
  			  var thisNum = JoyinMath.doMul(finAssetIntInfos[i].regDateAmt, $("#unitCprice").val(), 2, JoyinMath.ROUND_HALF_UP);
  			  finAssetIntInfos[i].shareAmt = thisNum;
  		  }
  		  else{
              finAssetIntInfos[i].shareAmt = "";
          }
  		  if($("#unitInt").val()){
  			  var thisFpriceAmt = JoyinMath.doMul(finAssetIntInfos[i].regDateAmt, $("#unitInt").val(), 2, JoyinMath.ROUND_HALF_UP);
  			  finAssetIntInfos[i].fpriceAmt = thisFpriceAmt;
  		  }
  		  else{
              finAssetIntInfos[i].fpriceAmt = "";
          }

  	  }
  	  repoInfoTable.reload({ data:finAssetIntInfos});
  }
    //按每股送转、每股计算明细分红金额、分红股本
    // var refereshTableData = function (id,amount){
    //
    // 	var finAssetIntInfos = dividInfoList;
    // 	if(!finAssetIntInfos){
    //     	return;
    //     }
    //
    // 	for (var i = 0; i < finAssetIntInfos.length; i++) {
    // 		//计算当前行
	// 		var thisNum = JoyinMath.doMul(finAssetIntInfos[i].regDateAmt, amount, 2, JoyinMath.ROUND_HALF_UP);
	// 		finAssetIntInfos[i][val] = thisNum;
    // 	}
    // 	//刷新表格
    // 	repoInfoTable.clear(function () {
    //         $.each(finAssetIntInfos, function (i, v) {
    //             repoInfoTable.addRow(v);
    //         })
    //     });
    // }

    /**
     * 绑定编辑事件
     */
    repoInfoTable.bind('click', '.detail', function(data, index) {
        var param = {
            "portfolioId":data.portfolioId,
            "finprodMarketId":$('#finprodMarketId').val(),
            "dealDate": new Date($("#regDate").val()).format(),
            "isAdvance":"N",
            "bizCode":$("#busiCode").val(),
            "workflowId":typeof workflowId == "undefined" ? '':$("#workflowId").val(),
            // 修改字段删除
            "chlId" : $("#chlId").val()?$("#chlId").val():$('#secManageAcctId').val()||"",
            "preMap":JSON.stringify({
                "regDateAmt":data.regDateAmt,
                "fpriceAmt":data.fpriceAmt,
                "shareAmt":data.shareAmt,
            }),
            cols:JSON.stringify([
                //权益登记日股本
                {field: 'regDateAmt', width: "200", title: '权益登记日股本', type: 'money', sortable:false}
                //分红金额
                , {field: 'fpriceAmt', width: "200", title: '分红金额',type: 'money',sortable:false}
                //分红股本
                , {field: 'shareAmt', width: "200", title: '分红股本', type: 'money', sortable:false}
            ])
        };
        Joyin.dialog(JoyinUtil.getLabel('TrdDurationCommon.ChildPortfolioInfo'), JoyinUtil.getRealPath('invest/period/common/ChildPortfolioInfo/init'), '80%', '80%',param);
    });
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm', true);
        object.operateType = operateType;
        Joyin
                .ajaxJson(
                        basePath
                                + 'invest/period/trdstockbonus/TrdStockBonus002/check',
                        object,
                        function(result) {
                            Joyin.confirm(
                                            result.data,
                                            function() {
                                                if (operateType === 'save') {
                                                    WorkFlow
                                                            .doSaveList(
                                                                    basePath
                                                                            + 'invest/period/trdstockbonus/TrdStockBonus002/save',
                                                                    object,
                                                                    "TrdStockBonus002_btn_save","TrdStockBonus002");
                                                } else {
                                                    WorkFlow
                                                            .doSubmitList(
                                                                    basePath
                                                                            + 'invest/period/trdstockbonus/TrdStockBonus002/save',
                                                                    object,
                                                                    "TrdStockBonus002_btn_submit","TrdStockBonus002");
                                                }
                                            });
                        });
    }
    
    var triValueChange = function(id, data) {
        if (data) {
            $('#' + id).val(data).trigger("change").trigger('blur');
        } else if (!initFlag) {
            $('#' + id).val('').trigger("change").trigger('blur');
        } else {
            $('#' + id).trigger("change").trigger('blur');
        }
    };
    // 判空处理
    var isNotNull = function(value) {
        return value && value != "";
    };
    
    
    var init = function() {
        triValueChange("ps");
        triValueChange("chlId");
        triValueChange("secManageAcctId");
        //分红类型为送转时 红股上市日=派息日
        // var ps = $('#ps').val();
        // if(ps==STOCK_DEVISION_TYPE02){
        //     $('#redStockDate').val($('#settleDate').val())
        // }
        initFlag = false;

    };
    /**
     * 画面加载完成
     */
    $(function () {

        $('#TrdStockBonus002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#TrdStockBonus002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
        
     // 1.资产代码
        $('#finprodMarketId')
                .change(
                        function() {
                            var finprodMarketId = $('#finprodMarketId').val();

                            if (finprodMarketId || finprodMarketId != '') {
                                triValueChange('finprodAbbr',$("#finprodMarketId").select2('data')[0].text);
                                triValueChange('finprodId',$("#finprodMarketId").select2('data')[0].object);
                            	dealIntDetail();
                            }else{
                                triValueChange('finprodAbbr');
                                triValueChange('finprodId');
                            }
                            
                        });
        //通道联动
        $('#chlId').change(function() {
        	var chlId = $('#chlId').val();
        	if (isNotNull(chlId)) {
        		$('#secManageAcctId').attr('disabled', true);
        		dealIntDetail();
        	}else{
        		$('#secManageAcctId').attr('disabled', false);
        	}
        });
        //证券托管户
        $('#secManageAcctId').change(function(){
        	var secManageAcctId = $('#secManageAcctId').val();
        	if (isNotNull(secManageAcctId)) {
        		$('#chlId').attr('disabled', true);
        		dealIntDetail();
        	}else{
        		$('#chlId').attr('disabled', false);
        	}
        });
        //分红类型联动
        $('#ps').change(function(){
        	var ps = $('#ps').val();

            triValueChange('unitCprice');
            triValueChange('unitInt');
            // triValueChange('regDateAmt');
        	//现金分红
        	if(ps==STOCK_DEVISION_TYPE01){
        	    //分红股本
                triValueChange('shareAmt');
        		$('.ps02').attr('disabled', true);
        		$('.ps01').attr('disabled', false);
        	}
        	//送转
        	else if(ps==STOCK_DEVISION_TYPE02){
        	    //分红金额
                triValueChange('fpriceAmt');
        		$('.ps01').attr('disabled', true);
        		$('.ps02').attr('disabled', false);
        	}else{
        		$('.ps01').attr('disabled', false);
        		$('.ps02').attr('disabled', false);
        	}

        });
        // 权益登记日联动
        $('#regDate').change(function() {
            var registDate = $('#regDate').val();
            if (isNotNull(registDate)) {
                dealIntDetail();
            }
        });
        //每股送转联动
        $('#unitCprice').change(function() {
        	var unitCprice = $('#unitCprice').val();
        	//权益登记日股本
        	var regDateAmt = $('#regDateAmt').val();
        	if(isNotNull(unitCprice) && isNotNull(regDateAmt)){
            	//4舍5入取整
            	$('#shareAmt').val(JoyinMath.doMul(regDateAmt, unitCprice, 0, JoyinMath.ROUND_HALF_UP));
            	resetTableData();
        		//refereshTableData("shareAmt",unitCprice);
        	}
        	
        });
        //每股派息联动
        $('#unitInt').change(function() {
        	var unitInt = $('#unitInt').val();
        	//权益登记日股本
        	var regDateAmt = $('#regDateAmt').val();
        	if(isNotNull(unitInt) && isNotNull(regDateAmt)){
            	$('#fpriceAmt').val(JoyinMath.doMul(regDateAmt, unitInt, 2, JoyinMath.ROUND_HALF_UP));
            	resetTableData();
            	//refereshTableData("fpriceAmt",unitInt);
        	}
        });

        //输入分红股本联动
        $('#shareAmt').change(function () {
            var shareAmt = $('#shareAmt').val();
            if(isNotNull(shareAmt)){
                resetTableData();
            }
        });
        //分红金额联动
        $('#fpriceAmt').change(function () {
            var fpriceAmt = $('#fpriceAmt').val();
            if(isNotNull(fpriceAmt)){
                resetTableData();
            }
        });

        //权益登记日
        $('#regDateAmt').change(function () {
            //每股送转
            var unitCprice = $('#unitCprice').val();
            //每股派息
            var unitInt = $('#unitInt').val();
            //权益登记日股本
            var regDateAmt = $('#regDateAmt').val();
            if(isNotNull(unitCprice) && isNotNull(regDateAmt)){
                //4舍5入取整
                $('#shareAmt').val(JoyinMath.doMul(regDateAmt, unitCprice, 0, JoyinMath.ROUND_HALF_UP));
            }
            if(isNotNull(unitInt) && isNotNull(regDateAmt)){
                $('#fpriceAmt').val(JoyinMath.doMul(regDateAmt, unitInt, 2, JoyinMath.ROUND_HALF_UP));
            }
        });
        //修改除权除息日联动
        $('#tradeDate').change(function() {
            var tradeDate = $('#tradeDate').val();
            $('#settleDate').val(tradeDate);
            var perDate = new Date(tradeDate);
            perDate.setDate(perDate.getDate()-1);
            triValueChange("regDate",perDate.format('yyyy-MM-dd'));
            var nextDate = new Date(tradeDate);
            nextDate.setDate(nextDate.getDate()+1);
            $('#redStockDate').val(nextDate.format('yyyy-MM-dd'));

        });
        init();
    });
}());