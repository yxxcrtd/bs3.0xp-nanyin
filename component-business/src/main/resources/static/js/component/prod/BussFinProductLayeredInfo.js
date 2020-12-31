(function() {
	// 快速录入标志
	var quickFlag = false;
	
	var getElementById = function(id) {
		return $('#' + layeredInfoEntityName + '_' + id);
	};
	
	var tableCols = null;
	
	/**
	 * 定义数据表格
	*/
	var bussFinProductLayeredInfoTable = getElementById('dataTable')
			.joyintable(
					{
//						ajax : {
//							url : JoyinUtil.getRealPath('component/prod/BussFinProductLayeredInfo/productLayeredInfoQuery'),
//							type : 'POST',
//							data : function() {
//								return {
//									finprodId : layeredInfoFinprodId,
//									workflowId : workflowId,
//									entityName : layeredInfoEntityName,
//									quickFlag : quickFlag
//								};
//							}
//						},						
						data:LayeredDto==null?[]:LayeredDto,
						  searchOnInit: true,
				          loadonce: true,
				          rowno: true,
				          page: false,
				          checkbox:false,
				          addbtn: true,
				          height : 200,
				          cols:[
					             {field:'prodStyle', title: 'bussFinProductLayeredInfoDto.finProductLayeredType',sortable:false, edit:true}
					             ,{field:'saleChannel', title: 'bussFinProductLayeredInfoDto.finProductLayeredSaleChannel', sortable: false,
					            	edit:true,
					            	editOption:{type:'select',formatoption:true,multiple:true,result: SALECHANNELCHB}}
					             ,{field:'saleDepartment', title: 'bussFinProductLayeredInfoDto.finProductLayeredSaleDepartment', edit:true,sortable:false, editOption:{type:'select',formatoption:true,multiple:true,result: SALE_DEPT}}
					             ,{field:'tarCustomerType', title: 'bussFinProductLayeredInfoDto.finProductLayeredRepInvestorType',sortable:false,edit : true,editOption:{type:'select',formatoption:true,multiple:true,result: SALETARGET_DECLARE}}
					             //update by leipeng 20190627 BSTWO-224 【收益型产品管理】新建，分层信息中删除持有上、下线，增加认购起点字段。
					             /*,{field:'amtMin', title: 'bussFinProductLayeredInfoDto.finProductLayeredHoldDown',sortable:false,edit : true,type : 'number'}				         
					             ,{field:'amtMax', title: 'bussFinProductLayeredInfoDto.finProductLayeredHoldUp',sortable:false,edit : true,type : 'number'}*/
					             ,{field:'amtMin', title: 'bussFinProductLayeredInfoDto.finProductLayeredtoffStart',sortable:false,edit : true,type : 'number'}	
					             ,{field:'isSale', title: 'bussFinProductLayeredInfoDto.finProductLayeredIsReplaceSall',sortable:false,edit : true,visible : ($('#' + consignmentFlag).val() === 'Y'),editOption:{type:'select',formatoption:true,result: YES_OR_NO}}
					             ,{field:'yield', title: 'bussFinProductLayeredInfoDto.finProductLayeredield',sortable:false,edit : true,type : 'number', visible : getElementById('pageId').val() === 'FinProductBuild002' }
					             ,{field:'operation', title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
					         ],
				         addbtn:true,
//						beforeEditCell: function(preVal, field, rowData){
//							if(field === 'prodStyle'){
//								//新建分档产品，产品款项输不了值
//								//var td = getElementById('dataTable').next().find('.layui-table-main').find('tr[data-index="'+rowData.LAY_TABLE_INDEX+'"]').find("td[data-field='prodStyle']");
//                                var td = $(event.target).parent();
//								if (td.find('.layui-table-edit')[0]) {return true}
//								else {
//									var input = $('<input>').addClass('layui-table-edit');
//									input.attr('name', 'prodStyle');
//									input[0].value = preVal;
//									td.append(input);
//									input.focus()
//									return false
//								}
//							}
//							return true;
//						},
				         editCell : function(value, data, field, cell, column){
				        	 debugger;
				             //设置产品款型JSON数组
				             if(field === 'prodStyle'){
				                 tableDataChange();
				             }
				             if(field === 'tarCustomerType'){
				            	 tarCustomerTypeChange();
				             }
				         },
				         done : function(table, allData){
				             tableDataChange();
				             //BS-2035 草稿箱，开放分档产品，其中一个分档目标客户类型为同业，选择一个同业机构保存为草稿并打开使用，同业机构错被置灰不可编辑 start
				             tarCustomerTypeChange();
				             //BS-2035 草稿箱，开放分档产品，其中一个分档目标客户类型为同业，选择一个同业机构保存为草稿并打开使用，同业机构错被置灰不可编辑 end
				         }
					});

	/**
	 * 列表数据变更change
	 */			 
	function tableDataChange(){
	    prodStyleArr = [];
	    var prodArr = bussFinProductLayeredInfoTable.getAllRows();
        _.each(prodArr,function (prod){
            prodStyleArr[prodStyleArr.length] = {id: prod.prodStyle, text : prod.prodStyle};
        });
        PROD_STYLE = JSON.stringify(prodStyleArr);
	}
	
	/**
	 * 绑定删除事件
	 */
	bussFinProductLayeredInfoTable.bind('click', '.delt', function(data, index) {
		bussFinProductLayeredInfoTable.removeRow(data);
		tableDataChange();
	});
	
	var layeredInitAction = function(input_finprodId,bussFinProductLayeredInfoDto){
	    
		layeredInfoFinprodId = input_finprodId;
		quickFlag = true;
		bussFinProductLayeredInfoTable.table ? bussFinProductLayeredInfoTable.reload({data:bussFinProductLayeredInfoDto,url:null}) : bussFinProductLayeredInfoTable.load();
		tableDataChange();
	}
	
	var layeredInitActionTmpl = function(bussFinProductLayeredInfoList){
		if(bussFinProductLayeredInfoTable) {
			_.each(bussFinProductLayeredInfoList, function(rowData) {
				bussFinProductLayeredInfoTable.addRow(rowData);
			});
		}
	}
	
	var dealModeChange = function(){
	    //收益类型不为货币型 且分档、分档周期显示
	    if($('#' + profitTypeElement).val() != '03' && ($('#' + dealModeElement).val()=='11' || $('#' + dealModeElement).val()=='12')){
    		//分层   	
    		getElementById('div').show(); 
    	} else {
    	  //不分层          
            getElementById('div').hide();
            //删除table行的所有数据
            if(bussFinProductLayeredInfoTable.table) {
                bussFinProductLayeredInfoTable.clear();
            }
    	}
	}	
	
	function showOrHide(tbl_id,n,stl){
		var tab =document.getElementById("bussFinProductLayeredInfoDto_dataTable");
		var a = bussFinProductLayeredInfoTable;
		
		for(var i=0;i<tab.rows[0].cells.length;i++){
			alert(tab.rows[i].cells[n].innerHTML);
			tab.rows[i].cells[n].style.display = stl;  
		}
		
		
	}
		
	
	var consignmentFlagChange = function(){
		
        if ($('#' + consignmentFlag).val() == 'Y') {
            //显示适用款型
        	bussFinProductLayeredInfoTable.showColumns([{field:'isSale', visible: true}]);
        } else {
        	bussFinProductLayeredInfoTable.showColumns([{field:'isSale', visible: false}]);
        }
//		var cols = bussFinProductLayeredInfoTable.layOption.cols[0];
//		var newCols = [];
//		_.each(cols, function(col) {
//			if(col.field === 'prodStyle' && col.edit){
//				col.edit = 'text';
//			}
//			if(col.field === 'isSale'){
//				if($('#' + consignmentFlag).val() == 'Y') {
//					newCols.push(col);
//				}
//			}
//			else {
//				newCols.push(col);
//			}
//		});
//		bussFinProductLayeredInfoTable.reload({cols: [newCols]});
	
//		if($('#' + consignmentFlag).val() == 'Y'){
//			//$("#bussFinProductLayeredInfoDto_dataTable").next().find("table").find('th:eq(7)').show();
//			
//			//bussFinProductLayeredInfoTable.find('td:eq(7)').show();
//			//showOrHide('isSale',6,'block');
//		}else{   
//			//alert($("#bussFinProductLayeredInfoDto_dataTable").next().find("table").html());
//			//$("#bussFinProductLayeredInfoDto_dataTable").next().find("table").find('th:eq(7)').hide();
//			//showOrHide('isSale',6,'none');
//	  	}
	}
    
    // 公开接口
    window.BussFinProductLayered = {
		table:bussFinProductLayeredInfoTable,
		getAllRows:getAllRows,
		layeredInitAction : layeredInitAction,
		layeredInitActionTmpl : layeredInitActionTmpl
    };

    function getAllRows(){
        if(bussFinProductLayeredInfoTable != ''){
            return bussFinProductLayeredInfoTable.getAllRows();
        }
    };
    
    $(function() {   
        dealModeChange();
    	$('#' + dealModeElement).change(dealModeChange); 
    	$('#' + consignmentFlag).change(consignmentFlagChange);
    });
}());