(function() {
	console.log(QUOTA_CODE);
	var prodIdInit = $('#prodId').val();

	var prodNo = '';
	var flag=false;
	if(''==prodIdInit||null==prodIdInit){
		//新建页面
		$('#issuePartyId').val(issuePartyId_New);
		$('#prodId').blur(function (){
			if($('#prodInsId').val()=='' && this.value!=''){
				layer.msg('请先录入对应的行内标识码，以指定申报信息。')
			}/*else if($('#regCode').val()!=''&&$('#prodId').val()!=prodNo){
                layer.msg('如果想修改申报时候的行内标识码，请同步维护产品登记编码管理信息！')
            }*/
		});

	}else{
		//修改页面
		$("#prodId").attr("disabled","disabled");
		$("#isGrade").attr("disabled","disabled");
		$("#prodRe").attr("disabled","disabled");
		$("#pProdId").attr("disabled","disabled");
		$("#profitType").attr("disabled","disabled");


	}
	var init = 0;
	var initProdInsId = '';
	$('#prodInsId').change(function(){

		var prodInsId = $('#prodInsId').val();
		if(init == 0){
			initProdInsId = prodInsId;
		}

		if(init>0){
			if(''!=prodInsId && null !=prodInsId){
				// 查询产品申报信息
				/*Joyin.ajaxJson(basePath+'disclosure/information/config/repprodinfo/ProdInfo002/selectChbPrdDeclareByProdInsId?prodInsId='+prodInsId,{},function (result){
					$('#ivstorientation').val(result.ivstorientation).trigger('change');
					$('#operateMode').val(result.operateMode).trigger('change');
					$('#prodName').val(result.prodName).trigger('change');
					$('#ivstorientation_div').show();
					$('#operateMode_div').show();

				})
				//查询产品登记信息
				Joyin.ajaxJson(basePath+'disclosure/information/config/repprodinfo/ProdInfo002/selectRegCodeByProdId?prodId='+prodInsId,{},function (result){
					if(result['REG_CODE']!='' && result['REG_CODE']!=null){
						$('#regCode').val(result['REG_CODE']);
						$('#prodId').val(result['PRODID']);
						prodNo = result['PRODID'];
					}else{
						prodNo = '';
						$('#regCode').val('');
						$('#prodName').val('');
						$('#prodId').val('');
						layer.msg('请先维护产品登记编码信息！')
					}
				})
				if(initProdInsId != prodInsId){
					// 校验产品是否存在
					Joyin.ajaxJson(basePath+'disclosure/information/config/repprodinfo/ProdInfo002/selectByKey?prodId='+prodInsId,{},function (result){
						if(result !=null &&  result[0]!=null){
							if(result[0]['STATUS']=='D'){
								Joyin.confirm('存在已删除的产品基本信息，是否覆盖？',function (){
									flag = false;
								},function (){},function () {
									flag = true;
								})
							}else{
								flag = true;
								layer.msg('产品已存在、如需修改，请以修改模式进行信息修改！');
							}
						}else{
							flag = false;
						}
					})
				}else{
					flag = false;
				}*/
			}else{
				prodNo = '';
				$('#regCode').val('');
				$('#prodId').val('');
				$('#prodName').val('');
				$('#ivstorientation').val('');
				$('#ivstorientation_div').hide();
				$('#operateMode_div').hide();

			}
		}
		init+=1;
	});
	$("#raiseStrdate").change(function (){
		changeRaisetDate();
	});

	$("#raiseEnddate").change(function (){
		changeRaisetDate();
	});

	$("#pSetDate").change(function (){
		changeMDate();
	});
	$("#mdate").change(function (){
		changeMDate();
	});
	$("#setDate").change(function (){
		changeMDate();
	});

	$("#prodType").change(function (){
		var prodType = $('#prodType').select2('data')[0].id;
		//若产品类型为“现管类产品”，"发行方式"默认为“开放式”，且不允许修改
		if(prodType !=null && prodType == 'M'){
			$("#issuingWay").attr("disabled",true);
		}else{
			$("#issuingWay").attr("disabled",false);
		}
	});


	// 修改 产品到期日期、产品成立日期、子产品成立日期
	function changeMDate(){
		var pSetDate = $('#pSetDate').val();
		var mdate = $('#mdate').val();
		var setDate = $('#setDate').val();

		if (pSetDate && mdate){
			var days = (Date.ofDate(mdate) - Date.ofDate(pSetDate))/(24*60*60*1000);
			if(days<0){
				Joyin.error(JoyinUtil.getMessage("产品到期日期必须大于产品成立日期", JoyinUtil.getLabel("ProdInfo002.mdate")));
				$('#mdate').val('');
				return;
			}
		}

		if (setDate && mdate){
			var days = (Date.ofDate(mdate) - Date.ofDate(setDate))/(24*60*60*1000);
			if(days<0){
				Joyin.error(JoyinUtil.getMessage("产品到期日期必须大于子产品成立日期", JoyinUtil.getLabel("ProdInfo002.mdate")));
				$('#mdate').val('');
			}
		}
	}

	// 修改 募集结束日/开始日
	function changeRaisetDate(){
		var raiseStrdate = $('#raiseStrdate').val();
		var raiseEnddate = $('#raiseEnddate').val();
		if (raiseStrdate && raiseEnddate){
			var days = (Date.ofDate(raiseEnddate) - Date.ofDate(raiseStrdate))/(24*60*60*1000);
			if(days<0){
				Joyin.error(JoyinUtil.getMessage("募集结束日必须大于等于募集开始日", JoyinUtil.getLabel("ProdInfo002.raiseEnddate")));
				$('#raiseEnddate').val('');
			}
		}
	}




	function saveOrSubmit(operateType) {
		var object = $.serializeToObject('#submitForm');
		object.operateType = operateType;
		object.pageModel = pageModel;
		object.basicChangeList = mockTradeTable.getAllRows();

		Joyin.ajaxJson(basePath + 'disclosure/information/config/repprodinfo/ProdInfo002/check', object, function(result) {
			//提交工作流
			Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
				WorkFlow.doSubmitList(basePath + 'disclosure/information/config/repprodinfo/ProdInfo002/save', object, "ProdInfo002_btn_submit");
			});
		});
	};

	/**
	 * 画面加载完成
	 */
	$(function () {
		if(pageModel === PAGE_MODEL_EDIT){
			$('#btnSave_wfl').hide();
		}
		// 绑定按钮事件
		$('#ProdInfo002_btn_submit').click(function() {
			saveOrSubmit("submit");
		});

	});

	// 模拟交易列表
	var mockTradeTable = $("#mockTradeTable").joyintable({
		data: basicChangeList == null ? [] : basicChangeList,
		//data:[],
		searchOnInit: true,
		checkbox: false,
		page: false,
		height: '225px',
		cols: [
			/*{
				field: 'mockAssettype', edit: true, sortable: false, width: 120, title: '资产类型',
				editOption: {
					type: 'select',formatoption: true, multiple: false, searchable: true, result: MOCK_ASSET_TYPE
				}
			}*/
			{
				field: 'quotaCode', sortable: false, width: 400,  align: 'center',  title: '行情代码',edit: true,
				editOption: {
					type: 'select',formatoption: true, multiple: false, searchable: true, result: QUOTA_CODE
				}}
			, {
				field: 'quotaCode1',
				edit: true,
				width:150,
				sortable: false,
				title: '币种',
				editOption: {type: 'select', multiple: false, searchable: true, result: QUOTA_CODE}
			}
			, { field: 'ratio', sortable: false, title: '系数', width: 300, align: 'center',  edit: true, type: 'number', digit: 2 }
			, { field: 'operation', sortable: false, title: 'SysCommon.operation', type: 'action', buttons: ['delt'] }
		],
		addbtn: true,
		done: function () {
			this.bind('click', '.delt', function (data, index) {
				var object = { selectedItem: [data], operateType: "delete" };
				mockTradeTable.deleteRow(index);
			});
		}
	});

}());
