/**
 * 关联机构、高管新建
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
		/**
		 * 获取列表参数
		 */
		var relateTypeChecked = $("input[name='relateType']:checked").val();
		var executiveInfos = [];
		var object = {
			corClass : relateTypeChecked,
			orgNo : $('#orgNo').val(),
			source : CONST.INPUT_MANUAL,
			operateType : "new",
			pageModel : pageModel
		};
		if (relateTypeChecked == CONST.COR_CLASS_MANAGE) {// 高管
			if (table != null && table != '') {// 获取所有行，根据主键判断是否添加行，有主键则为原有记录，不处理
				var rowDatas = table.getAllRows();
				for (var i = 0; i < rowDatas.length; i++) {
					if (rowDatas[i].seqno == undefined) {//先清除左右两侧空字符，再进行字符转义，保存成功后可正常显示文本或标签文本
						rowDatas[i].title = $('<span/>').text($.trim(rowDatas[i].title)).html();
						rowDatas[i].executiveName = $('<span/>').text($.trim(rowDatas[i].executiveName)).html();
						executiveInfos.push(rowDatas[i]);
					}
				}
			}
		}
		if (!checkInput(relateTypeChecked, executiveInfos)) {
			return;
		}

		if (relateTypeChecked == CONST.COR_CLASS_MANAGE) {// 高管
			Object.assign(object, {
				selectedItem : executiveInfos,
			});
		} else if (relateTypeChecked == CONST.COR_CLASS_ORG) {// 关联机构
			Object.assign(object, {
			    corOrgid : $("#corOrgid").val(),
			    corOrgtype : $("#corOrgtype").val(),
			})
		}
		object.pageId = "AgencyDetailsInf002";
		Joyin.ajaxJson(basePath + 'invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf002/check', object, function(result) {
			Joyin.confirm(result.data, function() {
				Joyin.ajaxJson(basePath + 'invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf002/save', object, function(result) {
					table.reload();
					Joyin.alert("添加成功！");
					WorkFlow.cancel();//暂时不关闭了
				})
			})
		});
	}

	/**
	 * 验证保存信息
	 * 
	 * @param relateTypeChecked
	 * @param executiveInfos
	 * @returns
	 */
	function checkInput(relateTypeChecked, executiveInfos) {
		if (relateTypeChecked == CONST.COR_CLASS_MANAGE) {// 高管
			if (!executiveInfos || executiveInfos.length == 0) {
				Joyin.error("没有录入任何高管数据");
				return false;
			}
			for (var i = 0; i < executiveInfos.length; i++) {
				var obj = executiveInfos[i];
				if (obj.executiveName == null || obj.executiveName == "" || $.trim(obj.executiveName) == "") {
					Joyin.error("高管人员姓名为空！");
					return false;
				} else if ($.trim(obj.executiveName).getBytesLength() > 50) {
					Joyin.error("姓名过长！");
					return false;
				}
				if (obj.title == null || obj.title == "" || $.trim(obj.title) == "") {
					Joyin.error("高管职称为空！");
					return false;
				} else if ($.trim(obj.title).getBytesLength() > 100) {
					Joyin.error("高管职称过长！");
					return false;
				}
			}

		} else if (relateTypeChecked == CONST.COR_CLASS_ORG) {// 关联机构
			if ($("#corOrgid").val() == null || $("#corOrgid").val() == '') {
				Joyin.error("请选择关联机构名称！");
				return false;
			}
			if ($("#corOrgtype").val() == null || $("#corOrgtype").val() == '') {
				Joyin.error("请选择关联类型！");
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取字符串字节数
	 */
		
	String.prototype.getBytesLength = function() {
		var totalLength = 0;
		var charCode;
		for (var i = 0; i < this.length; i++) {
			charCode = this.charCodeAt(i);
			if (charCode < 0x007f) {
				totalLength++;
			} else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
				totalLength += 2;
			} else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
				totalLength += 3;
			} else {
				totalLength += 4;
			}
		}
		return totalLength;
	}

	/**
	 * 定义数据表格
	 */
	var addbtn = (pageModel == PAGE_MODEL_NEW);
	var options = {
	    ajax : {
	        url : JoyinUtil.getRealPath('invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf002/getSeniorExecutiveInf'),
	        type : 'POST',
	        data : function() {
		        return {
		            orgNo : $('#orgNo').val(),
		            seqNo : $('#seqNo').val(),
		            corClass : CONST.COR_CLASS_MANAGE,
		        };
	        }
	    },
	    id : "searchTable",
	    rowno : true,
	    page : false,
	    addbtn : addbtn,
	    addToLast : true,
	    searchOnInit : true,
	    checkbox : false,
		beforeEditCell : function(preVal, field, rowData) {
			if(rowData.seqno != undefined && rowData.seqno != null && rowData.seqno != "" ){
				return false;
			}else if(field == "source"){
				return false;
			}
			return true;
		},
		 editCell: function(value, data, field, cell, column){
	        	var flag = true;
	        	if(/<\/?.+?\/?>/g.test(value)== true){
	        		Joyin.error("输入文本包含有标签等特殊字符，不支持！");
	        		var tableData = layui.table.cache['dataTableseqno'];
	        		console.log(tableData);
	        		layui.table.reload("dataTableseqno");
	        		flag = false;
	        	}
	        	return flag;
	        },
	    cols : [ {
	        field : 'seqno',
	        title : 'AgencyDetailsInf002.seqno',
	        width : '20%',
	        edit : true,
	        visible : false,
	        align : "center",
	        sortable : false
	    }, {
	        field : 'executiveName',
	        title : 'AgencyDetailsInf002.executiveName',
	        width : '25%',
	        edit : true,
	        align : "center",
	       /* render :function(value,visible,row,options){
	        	return htmlEncode(value);
	        },*/
	        editOption : {
	            type : 'input',
	            id : "executiveName",
	            formatoption : true
	        },
	        sortable : false
	    }, {
	        field : 'title',
	        title : 'AgencyDetailsInf002.title',
	        width : '25%',
	        edit : true,
	        align : "center",
	        editOption : {
	            type : 'input',
	            id : "title",
	            formatoption : true
	        },
	        sortable : false
		    }, {
			field : 'source',
			title : 'AgencyDetailsInf002.source',
			width : '25%',
			edit : true,
			align : "center",
			editOption : {
				type : 'select',
				id : "source",
				initValue : "01",
				formatoption : true,
				result : INPUT_TYPE
			},
	        sortable : false
	    }, {
	        field : 'operation',
	        title : 'SysCommon.operation',
	        width : '25%',
	        type : 'action',
	        buttons : [ addbtn ? 'delt' : '' ],
	        align : "center"
	    } ],

	};
	var table = $("#dataTable").joyintable(options);
	var htmlEncode = function (html){
		          //1.首先动态创建一个容器标签元素，如DIV
		          var temp = document.createElement ("div");
		          //2.然后将要转换的字符串设置为这个元素的innerText(ie支持)或者textContent(火狐，google支持)
		          (temp.textContent != undefined ) ? (temp.textContent = html) : (temp.innerText = html);
		          //3.最后返回这个元素的innerHTML，即得到经过HTML编码转换的字符串了
		          var output = temp.innerHTML;
		         temp = null;
		         return output;
		     };
	var init = function() {
	};
	const CONST = {
	    COR_CLASS_ORG : "0",
	    COR_CLASS_MANAGE : "1",
	    INPUT_MANUAL : "01"
	};
	Object.freeze(CONST);
	var relateTypeOld = CONST.COR_CLASS_ORG;

	

	$(function() {
		init();
		$('#AgencyDetailsInf002_btn_submit').click(function() {
			saveOrSubmit('submit');
		});

		/**
		 * 表格绑定删除事件
		 */
		table.bind('click', '.delt', function(data, index) {
			if (data.seqno != undefined) {
				var object = {
				    seqno : data.seqno,
				    operateType : 'delete'
				};
				Joyin.confirm("是否确认删除此高管人员信息？", function() {
					Joyin.ajaxJson(basePath + 'invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf002/save', object, function(result) {
						table.removeRow(data);// 表格删除行
					});
				}, function() {
				})
			} else {
				table.removeRow(data);// 表格删除行
			}
		});
		
/*		//监听单元格编辑
	    layui.table.on('edit(test)', function (obj) {
	        $('input').keyup(function () {
	            value = value.replace(/[^d.]/g, '');
	        });
	        var value = obj.value, // 得到修改后的值
	            id = obj.data.id, // 得到当前修改行的id
	            field = obj.field; // 得到当前修改的字段名
	        var data = {},
	            key = field;
	        data[key] = value;
	        data['id'] = id;
	        var my = '';
	        var allData = '';
	        //获取表格数据
	        var tableData = table.cache['searchTable'];
	            table.reload('searchTable');
	            return false;
	        });*/

		/**
		 * 新建类型切换事件
		 */
		layui.form.on('radio(relateType)', function(data) {
			if (data.value != relateTypeOld) {
				Joyin.confirm("当前页面可能存在未提交的内容，是否放弃提交，请确认!", function() {
					relateTypeOld = data.value;
					if (relateTypeOld == CONST.COR_CLASS_ORG) {// 选中机构
						$('#customerInfo').css('display', 'block');
						$('#executiveInfo').css('display', 'none');
					} else if (relateTypeOld == CONST.COR_CLASS_MANAGE) {// 选中高管
						$('#customerInfo').css('display', 'none');
						$('#executiveInfo').css('display', 'block');
					}
				}, {},function() {
							$("input[name='relateType'][value='" + relateTypeOld + "']").next().trigger("click");
						})
			}
		});
		
		function clearLabelContent(){
			$('#partyType3').html(null);
            $('#partyType1').html(null);
            $('#companyNature').html(null);
            $('#insitituteCode').html(null);
            $('#registerCapital').html(null);
            $('#province').html(null);
            $('#city').html(null);
            $('#county').html(null);
            $('#industry').html(null);
            $('#industry2').html(null);
            $('#isListedCompany').html(null);
            $('#isGroupCustomer').html(null);
            $('#parentCustomerName').html(null);
		}

		/**
		 * 关联机构下拉框选择改变事件
		 */
		$('#corOrgid').change(
		        function() {
			        var customerId = $('#corOrgid').val();
			        if (customerId != undefined && customerId != null && customerId != ""){
				        Joyin.ajaxJson(basePath + 'invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf002/getMstCustomerInfoAdd?customerId=' + customerId, null,
				                function(result) {
					                if (result != null && result != '' && result.length > 0) {
						                var data = result[0];
						                $('#partyType3').html(data.partyType3 != undefined ? data.partyType3 : null);
						                $('#partyType1').html(data.partyType1 != undefined ? data.partyType1 : null);
						                $('#companyNature').html(data.companyNature != undefined ? data.companyNature : null);
						                $('#insitituteCode').html(data.insitituteCode != undefined ? data.insitituteCode : null);
						                $('#registerCapital').html(data.registerCapital != undefined ? data.registerCapital + "万" : null);
						                $('#province').html(data.province != undefined ? data.province : null);
						                $('#city').html(data.city != undefined ? data.city : null);
						                $('#county').html(data.county != undefined ? data.county : null);
						                $('#industry').html(data.industry != undefined ? data.industry : null);
						                $('#industry2').html(data.industry2 != undefined ? data.industry2 : null);
						                $('#isListedCompany').html(data.isListedCompany != undefined ? data.isListedCompany : null);
						                $('#isGroupCustomer').html(data.isGroupCustomer != undefined ? data.isGroupCustomer : null);
						                $('#parentCustomerName').html(data.parentCustomerName != undefined ? data.parentCustomerName : null);
					                }else{
					                	clearLabelContent();
					                }
				                });
			        }else{
			        	clearLabelContent();
			        }
		        });
		       
	});
}());