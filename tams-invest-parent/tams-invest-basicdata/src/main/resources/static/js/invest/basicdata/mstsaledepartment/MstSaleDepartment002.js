/**
 * 营业部信息新建
 */
$(function() {
	/**
	 * 保存或提交
	 * 
	 * @param operateType
	 *            操作类型（save，submit）
	 * @returns
	 */
	function saveOrSubmit(operateType) {
		var object = $.serializeToObject('#mstSaleDepartmentForm');
		if(!checkInput(object)){
			return;
		}
		object.operateType = operateType;
		object.pageModel = pageModel;
		if (pageModel == PAGE_MODEL_NEW) {
			object.operateType = 'new';
		} else if (pageModel == PAGE_MODEL_EDIT) {
			object.operateType = 'update';
		}
		Joyin.ajaxJson(basePath + 'invest/basicdata/mstsaledepartment/MstSaleDepartment002/check',object,
						function(result) {
							Joyin.confirm(result.data,function() {if (operateType === 'save') {
													Joyin.ajaxJson(basePath + 'invest/basicdata/mstsaledepartment/MstSaleDepartment002/save',object,
																	function(result) {
																		WorkFlow.cancel();// 暂时不关闭了
																	});
												}
											});
						});

	}
	
	//获取深交所和上交所全部席位号
//	var itemAllSH = arrayAll(SHSeatNo);
//	var itemAllSZ = arrayAll(SZSeatNo);
//	var itemAllZJ = arrayAll(ZJSeatNo);
	//update by huanghao 上期所席位号暂时注释掉 20201203 start
	//var itemAllSQ = arrayAll(SQSeatNo);
	//update by huanghao 上期所席位号暂时注释掉 20201203 end
//	/**
//	 * 返回数值
//	 * @param itemAll
//	 * @returns
//	 */
//	function arrayAll(itemAll){
//		let optArr = [];
//		let jsonArr = $.parseJSON(itemAll);
//		$.each(jsonArr,function(i,val){
//			optArr.push(val.id);
//		})
//		return optArr;
//	}
	/**
     * 画星星
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }
	/**
	 * 接口类型下拉框改变事件
	 */
	$('#intertype').change(function(){
		if($('#intertype').val() == '5'){
			$("#handletype").removeAttr("required");
			$("#isnetreturn").removeAttr("required");
		}else{
			$("#handletype").attr("required","required");
			$("#isnetreturn").attr("required","required");
		}
		star();
	})
	/**
	 * 券商下拉框选择改变事件
	 */
	$('#brokerid').change(
			
					function() {
						var brokerid = $('#brokerid').val();
						if (brokerid != undefined && brokerid != null && brokerid != "") {
							
						//控制显示哪些交易场所
						Joyin.ajaxJson(basePath + 'invest/basicdata/mstsaledepartment/MstSaleDepartment002/getTradeMarketBybrokerId?customerId=' + brokerid,
								null,
								function(result) {
									if (result != null && result != '' ) {
										if(result.isBroker == "N"){
											$('#shseatno_warp').hide();
											$('#szseatno_warp').hide();
											$('#zjseatno_warp').show();
											$('#zjseatno').val(ZJSeatNo).trigger('change');
											//update by huanghao 上期所席位号暂时注释掉 20201203 start
//											$('#sqseatno_warp').show();
											//update by huanghao 上期所席位号暂时注释掉 20201203 end
										}else{
											$('#shseatno_warp').show();
											$('#szseatno_warp').show();
											$('#zjseatno_warp').hide();
											//update by huanghao 上期所席位号暂时注释掉 20201203 start
//											$('#sqseatno_warp').hide();
											//update by huanghao 上期所席位号暂时注释掉 20201203 end
											$('#shseatno').val(SHSeatNo).trigger('change');
											$('#szseatno').val(SZSeatNo).trigger('change');
										}
									}
								});
//							Joyin.ajaxJson(basePath + 'invest/basicdata/mstsaledepartment/MstSaleDepartment002/getSeatInfoByCustomer?brokerid=' + brokerid + "&tradeMarket=03",
//											null,
//											function(result) {
//												if (result != null && result != '' && result.length > 0) {
//													var itemSH = [];
//													   for ( var m=0; m<result.length; m++) {
//																itemSH.push(result[m].id);
//														}
//													    $('#shseatno').updateChosenArr({
//													                item: itemSH,
//													                disabled: false
//													    });
//														$('#shseatno').val(result[0].id).trigger('change');
//													}else{
//														$('#shseatno').updateChosenArr({
//												                item: itemAllSH,
//												                disabled: false
//												        });
//													}
//											});
//							Joyin.ajaxJson(basePath + 'invest/basicdata/mstsaledepartment/MstSaleDepartment002/getSeatInfoByCustomer?brokerid=' + brokerid + "&tradeMarket=04",
//											null,
//											function(result) {
//												if (result != null && result != '' && result.length > 0) {
//														var itemSZ = [];
//															for (var n=0; n<result.length; n++) {
//																itemSZ.push(result[n].id);
//														    }
//														$('#szseatno').updateChosenArr({
//													                item: itemSZ,
//													                disabled: false
//													    });
//														$('#szseatno').val(result[0].id).trigger('change');
//													}else{
//														$('#szseatno').updateChosenArr({
//												                item: itemAllSZ,
//												                disabled: false
//												        });
//													}
//											});
//							//中金所席位号 12
//							Joyin.ajaxJson(basePath + 'invest/basicdata/mstsaledepartment/MstSaleDepartment002/getSeatInfoByCustomer?brokerid=' + brokerid + "&tradeMarket=12",
//									null,
//									function(result) {
//										if (result != null && result != '' && result.length > 0) {
//											var itemZJ = [];
//											   for ( var m=0; m<result.length; m++) {
//												   itemZJ.push(result[m].id);
//												}
//											    $('#zjseatno').updateChosenArr({
//											                item: itemZJ,
//											                disabled: false
//											    });
//												$('#zjseatno').val(result[0].id).trigger('change');
//											}else{
//												$('#zjseatno').updateChosenArr({
//										                item: itemAllZJ,
//										                disabled: false
//										        });
//											}
//									});
//							//update by huanghao 上期所席位号暂时注释掉 20201203 start
//							//上期所席位号 13
////							Joyin.ajaxJson(basePath + 'invest/basicdata/mstsaledepartment/MstSaleDepartment002/getSeatInfoByCustomer?brokerid=' + brokerid + "&tradeMarket=13",
////									null,
////									function(result) {
////										if (result != null && result != '' && result.length > 0) {
////											var itemSQ = [];
////											   for ( var m=0; m<result.length; m++) {
////														itemSQ.push(result[m].id);
////												}
////											    $('#sqseatno').updateChosenArr({
////											                item: itemSQ,
////											                disabled: false
////											    });
////												$('#sqseatno').val(result[0].id).trigger('change');
////											}else{
////												$('#sqseatno').updateChosenArr({
////										                item: itemAllSQ,
////										                disabled: false
////										        });
////											}
////									});
//							//update by huanghao 上期所席位号暂时注释掉 20201203 end
//							
//						}else{
//							$('#shseatno').updateChosenArr({
//				                item: itemAllSH,
//				                disabled: false
//				            });
//							 $('#szseatno').updateChosenArr({
//					                item: itemAllSZ,
//					                disabled: false
//					        });
//							 $('#zjseatno').updateChosenArr({
//					                item: itemAllZJ,
//					                disabled: false
//					        });
////							 $('#sqseatno').updateChosenArr({
////					                item: itemAllSQ,
////					                disabled: false
////					        });
						}
					});

	/**
	 * 输入有效性验证 主要为IP、端口
	 */
	var checkInput = function(obj) {
		if (obj.portnum != null && obj.portnum != "") {
			if (!isValidPort(obj.portnum)) {
				Joyin.error("端口不合法,有效值 1-65535!");
				return false;
			}
		}
		if (obj.portnum2 != null && obj.portnum2 != "") {
			if (!isValidPort(obj.portnum2)) {
				Joyin.error("备用端口不合法,有效值 1-65535!");
				return false;
			}
		}
		if (obj.ipaddr != null && obj.ipaddr != "") {
			if (!isValidIP(obj.ipaddr)) {
				Joyin.error("IP地址不合法,请重新输入!");
				return false;
			}
		}
		if (obj.ipaddr2 != null && obj.ipaddr2 != "") {
			if (!isValidIP(obj.ipaddr2)) {
				Joyin.error("备用IP地址不合法,请重新输入!");
				return false;
			}
		}
		if (!((obj.shseatno != null && obj.shseatno != "")
				||(obj.szseatno != null && obj.szseatno != "")
				||(obj.zjseatno != null && obj.zjseatno != "")
				//update by huanghao 上期所席位号暂时注释掉 20201203 start
//				||(obj.sqseatno != null && obj.sqseatno != "")
				//update by huanghao 上期所席位号暂时注释掉 20201203 end
				)
				) {
				Joyin.error("请输入席位号!");
				return false;
			}
		return true;
	}

	/**
	 * 端口验证
	 * @param port
	 * @returns
	 */
	function isValidPort(port) {
		if (!(/^[1-9]\d*$/.test(port) && 1 <= 1 * port && 1 * port <= 65535)) {
			return false;
		}
		return true;
	}

	/**
	 * IP验证
	 * @param ip
	 * @returns
	 */
	function isValidIP(ip) {
		var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/
		return reg.test(ip);
	}

	/**
	 * 页面初始化
	 */
	var init = function() {
		console.log(pageModel);
		if (pageModel == PAGE_MODEL_EDIT) {
			$("#saledepid").attr("disabled", "disabled");
		}
	};

	/**
	 * 画面加载完成
	 */
	$(function() {
		init();
		// 绑定按钮事件
		$('#MstSaleDepartment002_btn_save').click(function() {
			saveOrSubmit('save');
		});
	});
}());