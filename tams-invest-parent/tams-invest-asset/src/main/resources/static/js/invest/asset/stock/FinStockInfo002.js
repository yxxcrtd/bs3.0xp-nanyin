var QueryFunc = function () {
    return {
        serachKey: function () {
        }
    }
}();

(function () {
    /**
     * 画星星
     */
    var star = function () {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        var gp2=['02']
        if (initgp == 'MST23') {
            $("#finprodMarketId").attr("disabled",true).trigger('change');
            $("#tradeMarket").attr("disabled",true).trigger('change');
        }
        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function () {
                $(this).remove();
            })
            $('*[required]').each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }

    /**
     * 中债报送信息DIV控制显示
     */
    function typeSelect() {
        var type = $('#type').val();
        if (type == publicOffPlan) {
            $(".bussRepAssetMngProdInfo_div").show();
            $(".bussRepOutInvestAgrInfo_div").hide();
        } else {
            $(".bussRepAssetMngProdInfo_div").hide();
            $(".bussRepOutInvestAgrInfo_div").show();
        }
    };

    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');    
        object.finprodAbbr = $("#finprodName").val();
        var assetProviderTemp = $('#assetProvider').val();
        var assetProvider = null;
        if (assetProviderTemp != null)
            assetProvider = assetProviderTemp.join(",");
        var shOrZhList = $("#shOrZh").val();
        if(shOrZhList!=null)
            shOrZh = shOrZhList.join(",");
        object.shOrZh = shOrZh;
        object.assetProvider = assetProvider;
        object.pageModel = pageModel;
        object.operateType = operateType;
        // 为type9，type10，type11，type12赋值 by 赵东东 start
        object.type9 = "04";
        object.type10 = "47";
        object.type11 = "095";
        object.type12 = "097";
        // 为type9，type10，type11，type12赋值 by 赵东东 end
        //文件信息
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if(object.fileArray && _.isArray(object.fileArray)){
                $.each(object.fileArray, function(index,obj){
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        object.fileCommonList = FileCommon.getUploadFileInfo();
        //alert(4);
        Joyin.ajaxJson(basePath + 'invest/asset/stock/FinStockInfo002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/asset/stock/FinStockInfo002/save', object, "FinStockInfo002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/asset/stock/FinStockInfo002/save', object, "FinStockInfo002_btn_submit");
                }
            });
        });
    };

    var getInterfStockInfo = function(){
        isAutoCoupon = true;
    	var finprodMarketId =  $('#finprodMarketId').val();
        //拆分市场代码       
        var arr  = finprodMarketId.split('.');
        if (arr.length == 2){
            finprodMarketId = arr[0];
            $('#finprodMarketId').val(finprodMarketId);
            $('#tradeMarketId').val('.' + arr[1]);
        } 
        if (finprodMarketId) {
    		Joyin.ajax(basePath + 'invest/asset/stock/FinStockInfo002/getInterfStockDto?finprodMarketId=' 
   				 + finprodMarketId + '&tradeMarketId=' + $('#tradeMarketId').val(),'' , function(result){
    			if (result.data[0]) {
    				Joyin.error(result.data[0].message);
				} else {
                    //组件赋值 先清后赋
                    $.each(Joyin.curfromData['FinStockInfo002'],function(id, value){
                        resetForm(id, value.odata, '');
                    });
                    $.each(result.data, function(id, value) {
                        setData(id, value, '');
                    });
                    $('#repAssetType').val(result.data.repAssetType).trigger('change');
				}
    			  layui.form.render();
   		 });
		} else {
			Joyin.error(JoyinUtil.getMessage('BUSS_COM_E_0038',JoyinUtil.getLabel('FinStockInfo002.finprodMarketId')));
		}
    }
    
    var resetForm = function(id,value,pageId){
    	if (id == 'finInterestFactorDto') {
			$.each(value, function(id,value){
				resetForm(id,value,'finInterestFactorDto');
			});
		}else if(id == 'finPayInterestStdInfoDto'){
			$.each(value, function(id,value){
				resetForm(id,value,'finPayInterestStdInfoDto');
			});
		}else{
			var element;
	    	if(pageId != '' && pageId != undefined){
				id = pageId + '_' + id;
			}
	    	if($('#'+id).size() > 0){
	    		element = $('#'+id);
	    	}else{
	    		element = $('input[name=' + id + ']');
	    	}
	    	
	    	//下拉框特殊处理
	    	if (element.hasClass('input-select')) {
	    		element.val(value).trigger('change');
			}else if(element.hasClass('input-checkbox')){
				//单选按钮特殊处理
	    		element.val(value).trigger('change');
	    	}else if ($('#' + id).attr('lay-skin') === 'switch') {
	    		//switch 特殊处理
				$('#' + id).prop('checked', false);
				// 是否,有无
				if (value === 'Y' || value === '01') {
					$('#' + id).prop('checked', true);
				}
	    	}else{
	    		element.val(value);
	    	}
		}
    	
    }
    
    var setData = function(id, value ,pageId) {
		if (id == 'finInterestFactorDto' ) {
			$.each(value, function(id, value) {
                if (id == 'intType'){
                    id = 'rateType';
                }
                // 债券新建新增快捷录入功能 add by renping 20191010 start
                else if (id == 'highestRate'){
                	id = 'rateMax';
                }
                else if (id == 'lowestRate'){
                	id = 'rateMin';
                }
                else if (id == 'benchmarkId'){
                	id = 'indexId';
                }
                // 债券新建新增快捷录入功能 add by renping 20191010 end
				setData(id, value , 'finInterestFactorDto');
			});
		} else if (id == 'finPayInterestStdInfoDto') {
			// 付息
			$.each(value, function(id, value) {		
			    if (id == 'firstPayDate' && $('#finPayInterestStdInfoDto_calcRule').val()=='01') {
			        value = null;
                }
                    setData(id, value , 'finPayInterestStdInfoDto');					
			});
			// 表格
			$('#generationPlanTable').data('joyintable').reload({
				data : value.finCashPlanList,
				url : null
			});
		} else if (id == 'bussFinRepaymentDto') {
			// 还本
			// 表格
			$('#bussFinRepaymentTable').data('joyintable').reload({
				data : value.finCashPlanList,
				url : null
			});
		}else if (id == 'repAssetBond'){
		    //中债报送 bussFinBondRepInfoDto_repAssetType  bussFinBondRepInfoDto_repPartyType2
            $.each(value, function(id, value) {
                setData(id, value , 'bussFinBondRepInfoDto');
            });
		    
        }else if (id == 'repAssetEquity'){
            //中债报送
            $.each(value, function(id, value) {
                setData(id, value , 'bussRepEquityInfoDto');
            });
           // 中债报送 by 赵东东 2019.11.12 start
        }else if(id == 'repAssetOther'){
        	$.each(value, function(id, value) {
                setData(id, value , 'bussFinBondRepInfoDto');
            });
            $.each(value, function(id, value) {
                setData(id, value , 'bussRepEquityInfoDto');
            });
        }else if(id == 'repAssetDept'){
            $.each(value, function(id, value) {
                setData(id, value , 'bussFinBondRepInfoDto');
            });
            $.each(value, function(id, value) {
                setData(id, value , 'bussRepEquityInfoDto');
            });
            // 中债报送 by 赵东东 2019.11.12 end
        }else if (id == 'repAssetEquity'){
            //中债报送
            $.each(value, function(id, value) {
                setData(id, value , 'bussRepEquityInfoDto');
            });

        }else if (id == 'isRight') {
            if (value === 'Y' || value === '01') {
                $('#isRight').prop('checked', true);
            }
		    //是否含权债
            isRightChange();
			 
		}else if (id == 'busiFinExercisePlanDto'){
           // 行权 表格
            $('#busiFinExercisePlanTable').data('joyintable').reload({
                data : value.finExercisePlanList,
                url : null
            });
        }
        else if(id == 'mstBondCreditRatingList'){
		    //评级  表格
            $('#busiFinBondRatingInfoTable').data('joyintable').reload({
                data : value,
                url : null
            });
        // 债券新建新增快捷录入功能 add by renping 20191010 start
        }
        else if(id == 'fileArray'){
        	// 附件
    		$("#fileUploader").data('uploader','');
    		$("#fileUploader").attr('data-data',value);
    		$("#fileUploader").data('data',value);
    		$("#fileUploader").uploadFile()
        // 债券新建新增快捷录入功能 add by renping 20191010 end
		    
        }else {
			// 基本信息
			var element;
	    	if(pageId != '' && pageId != undefined){
				id = pageId + '_' + id;
			}
	    	if($('#'+id).size() > 0){
	    		element = $('#'+id);
	    	}else{
	    		element = $('input[name=' + id + ']');
	    	}
	    	
	    	//下拉框特殊处理
	    	if (element.hasClass('input-select')) {
                if (id == 'tradeMarket') {
                    AssetCommon.setTradeMarketId(value);
                    element.val(value).trigger('change');
                // 债券新建新增快捷录入功能 modify by renping 20191010 start
                    // 修改快录中债报送丢失交易流通场所 by 赵东东 2019.11.22 start
                }else if(id == 'bussFinBondRepInfoDto_repTradeMarket'){
                    //修改债券快录资产代码后缀名出错 by 赵东东 start
                	//AssetCommon.setTradeMarketId(value);
                  //修改债券快录资产代码后缀名出错 by 赵东东 end
                	element.val(value).trigger('change');
                	// 修改快录中债报送丢失交易流通场所 by 赵东东 2019.11.22 end
                }else if (id == 'issuer' || id == 'calendarId' || id == 'sponsor' || id == 'oFinprodId' || id == 'postOrg'||id=='conFinprodMarketId') {
                // 债券新建新增快捷录入功能 modify by renping 20191010 end
                    //data-url 赋值要特殊处理
                    element.textSelect('setValue', value);
                } else {
                    element.val(value).trigger('change');
                }
            }else if (element.hasClass('input-dateSelect')) {
	    		//日期框特殊处理
	    		if (value){
                    element.val(new Date(value).format('yyyy-MM-dd'));
                } 
			}else if(element.hasClass('input-checkbox')){
				//单选按钮特殊处理
	    		element.val(value).trigger('change');
	    	}else if ($('#' + id).attr('lay-skin') === 'switch') {
	    		//switch 特殊处理
				$('#' + id).prop('checked', false);
				$('#' + id).next().removeClass('layui-form-onswitch').trigger('change');
				$('#' + id).next().find("em").text('否');
				// 是否,有无
				if (value === 'Y' || value === '01') {
					$('#' + id).prop('checked', true);
					$('#' + id).next().addClass('layui-form-onswitch').trigger('change');
					$('#' + id).next().find("em").text('是');
				}
                // 债券新建新增快捷录入功能 add by renping 20191010 start
				if(id == 'isCityBond'){
					isCityBondChange();
				}
                // 债券新建新增快捷录入功能 add by renping 20191010 end
	    	}else{
	    		element.val(value);
	    	}
		}
    }
    
    //控件加载完成后
    window.componentLoadCallback = function (isDone, pagePath, params) {
    }
    var init = function () {
        //资产代码拆分
        var finprodMarketId =  $('#finprodMarketId').val();
        if (finprodMarketId){
            $('#finprodMarketId').val(finprodMarketId.split('.')[0]);
        }
        //如果授信模式=='02'，则显示批复号和实际授信主体
        if (CE_MODEL == '02') {
            $('#actualCreditProvidDiv').show();
            $('#pflshDiv').show();
        } else {
            $('#actualCreditProvidDiv').hide();
            $('#pflshDiv').hide();
        }
        var hapCreditAmt = $("#hapCreditAmt").val();
        if (hapCreditAmt && hapCreditAmt > 0) {
            $("#pflsh").attr("disabled", true).trigger('change');
        }
        // if (initgp == '03') {
        //     AssetCommon.setTradeMarketId($('#tradeMarket').val());//在js的初始化添加，给tradeMarketId赋值
        // }
        // if (initps == '01') {
        //     AssetCommon.setTradeMarketId($('#tradeMarket').val());//在js的初始化添加，给tradeMarketId赋值
        // }
        star();
        if(isTransInAsset){
        	$("#ccy").attr("disabled",true);
        }
        if($('#tradeMarket').val()=='12'){
            $('#shOrZhDiv').show();
        }

    }
    var getCalendarId = function(){
            Joyin.ajax(basePath + 'invest/asset/stock/FinStockInfo002/getCalendarId?tradeMarket=' + $('#tradeMarket').val(),'' , function(result){
               // $('#calendarId').val(result.data[0].code);
                $('#calendarId').textSelect("setValue",result.data[0].code);
            });
    };

    /**
     * 画面加载完成
     *
     */
    $(function () {
        init();
        // 绑定按钮事件
        $('#FinStockInfo002_btn_save').click(function () {
            saveOrSubmit('save');
        });
        $('#FinStockInfo002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
//        $('#magnifier').click(function() {
//            getInterfFundDto();
//        });
        $('#tradeMarket').change(function(){
            AssetCommon.setTradeMarketId($('#tradeMarket').val());
            getCalendarId();
            if($('#tradeMarket').val()=='12'){
                $('#shOrZhDiv').show();
                $('#isMarginFinprod').val('N').trigger('change').attr('disabled',true);
                $('#ccy').val('HKD').trigger('change');
            }else {
                $('#shOrZh').val('').trigger('change');
                $('#shOrZhDiv').hide();
                $('#isMarginFinprod').attr('disabled',false);
                $('#ccy').val('CNY').trigger('change');
            }
        });
        $('#magnifier').click(getInterfStockInfo);
        //typeSelect();
        if (pageModel != PAGE_MODEL_NEW) {
            $('#type').attr("disabled", true);
            $('.btn-save').hide();
        }
        $("#pflsh").change(function () {
            var str = $('#pflsh').select2('data')[0].object.split(',');
            if (str != null && str.length == 3) {
                $("#seqno").val(str[0]);
                $("#creditMstId").val(str[1]);
                $("#creditFlag").val(str[2]);
            }
            if (str[2] == 'Y') {
                $("#actualCreditProvid").attr("required", true).trigger('change');
            }
            if (str[2] != 'Y') {
                $("#actualCreditProvid").attr("required", false).trigger('change');
            }
            star();
        });
        $("#shOrZh").change(function () {
           if($("#shOrZh").val()=='01'){
               $('#calendarId').textSelect("setValue",'13');
           } else if($("#shOrZh").val()=='02'){
               $('#calendarId').textSelect("setValue",'14');
           }else if($("#shOrZh").val()){
                   Joyin.ajax(basePath + 'invest/asset/stock/FinStockInfo002/getShOrZhCalendarId','' , function(result){
                       $('#calendarId').textSelect("setValue",result.data[0].code);
               });
           }else{
               $("#calendarId").empty();
           }
        });
    });
}());

