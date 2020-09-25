/**
 * 参与方信息组件JS
 * @auth zhangnian
 */
(function(){
    /**
     * 禁止重复reload table
     */
    var doneFlag =false;
    var getElementById = function(id) {
        return $('#' + entityName_BussParticipantInfo + '_' + id);
    };
    
    /**
     * 获取参数
     * @returns
     */
    function getPreviewParams() {
        var param = {}
        param.finprodId = $("#finprodId").val();
        param.workflowId = $("#workflowId").val();
        param.entityName = entityName_BussParticipantInfo;
        param.pageModel = pageModel;
        param.pFinprodId = pFinprodId;
        return param;
    }

    //初始化
        /**
         * 定义数据表格
         */
    var cols = [
            {field:'counterId', width:100,  title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("BussParticipantInfo.counterId");}(),edit:true,sortable: false,
                editOption:{type:'select',multiple: false, showField:'id',searchable: true,/*childrenIds:'busiAcctNo',id:'counterId',*/
                    url: JoyinUtil.getRealPath('/invest/asset/channel/ChannelAsset001/getSecAcctName'),postData:function () {
                        return {counterType:counterType};
                    }
               }
            }
           /* ,
            {field:'payAcctNo', width:130, title: 'BussTrdTransferOrder.payAcctNo',edit:true,
                editOption:{type:'select', multiple: false, searchable: true, showField:'id',
                    url: JoyinUtil.getRealPath("/business/common/CommonSelect/selectCounterId")}, sortable:false}*/
            ,{field:'counterName', width:100, title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("BussParticipantInfo.counterName");}(), sortable: false}
            ,{field:'managerCustomerName', width:75, title: 'BussParticipantInfo.managerCustomerName', sortable: false}
            ,{field:'chlLevel', width:75, title: function(){return JoyinUtil.getLabel("invest.channelId")+JoyinUtil.getLabel("BussParticipantInfo.chlLevel");}(),visible:false, sortable: false,type:'number', digit:0}
            ,{field:'clientYield', width:100, title: 'BussParticipantInfo.clientYield',type:'number',edit:true, sortable: false, digit:4}
            ,{field:'managerYield', width:100, title: 'BussParticipantInfo.managerYield',edit:true,type:'number', sortable: false, digit:4}
            ,{field:'otherYield', width:100, title: 'BussParticipantInfo.otherYield',edit:true,type:'number', sortable: false, digit:4}
            // 通道增加“增值税税率，附加税率字段” add by renping 20191019 start
            ,{field:'vatTaxRate', width:130, title: 'BussParticipantInfo.vatTaxRate', type:'number', sortable: false, digit:4}
            ,{field:'adtTaxRate', width:130, title: 'BussParticipantInfo.adtTaxRate', type:'number', sortable: false, digit:4}
            // 通道增加“增值税税率，附加税率字段” add by renping 20191019 end
            ,{field:'busiAcctNo', width:100, title: 'BussParticipantInfo.busiAcctNo', sortable: false}
            /*,{field:'busiAcctNo', width:100, title: 'BussParticipantInfo.busiAcctNo', sortable: false,
                edit:true,editOption:{type:'select',parentIds:'counterId',id:"busiAcctNo",url:JoyinUtil.getRealPath('component/project/BussParticipantInfo/selectAcctNoByCounterId')}
            }*/
            ,{field:'clientCustomerName', width:100, title: 'BussParticipantInfo.clientCustomerName', sortable: false}
            ,{field:'contactWay', width:100, title: 'BussParticipantInfo.contactWay', sortable: false}
            ,{field:'contact', width:100, title: 'BussParticipantInfo.contact', sortable: false}
            ,{field:'remark', width:75, title: 'BussParticipantInfo.remark',edit:true, sortable: false}
            ,{field:'finprodId', width:100, title: 'BussParticipantInfo.finprodId',edit:true,visible:false,sortable: false}
            ,{field:'basis', width:100, title: '',edit:true,visible:false,sortable: false}
            ,{field:'payType', width:100, title: '',edit:true,visible:false,sortable: false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//编辑和删除事件
        ]
    var table =$("#bussParticipantInfoTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/component/project/BussParticipantInfo/getParticipantInfo'),
            type: 'POST',
            data: getPreviewParams()
        },
        height: 400,
        cols:cols,
        beforeEditCell:function(data){
        	
        	if(isTransInAsset != null && isTransInAsset != '' && isTransInAsset=== "Y"){
        		return false;
        	}
        	return true;
        },
        editCell : function(value, data, field, cell, column){
        	var list = table.getAllRows();

			if(choseNoSameChannel(list,data,value)){ // 有重复
				// 阻止选择，重新选择
				Joyin.error("重复，通道代码" + value
						+ "已经选择过，请重新选择！");
				table.removeRow(data);
				return; // 阻止往下执行
			}
        	
            if (field === "counterId"){
                var rowData = {};
               /* rowData.busiAcctNo = '';*/
                if (value != null && value != ''){
                    Joyin.ajax(JoyinUtil.getRealPath('component/project/BussParticipantInfo/queryAllinfoByfinprodId'), {counterId : value}, function(result){
                        if (result){
                    		// 重新选择通道托管费率、管理费率为空时不能带出  modify by renping 20191029 start
                    		data.counterName = result.counterName;
                            data.managerCustomerName = result.managerCustomerName;
                            data.clientYield = result.clientYield;
                            data.managerYield = result.managerYield;
                            data.otherYield = result.otherYield  ;
                            // 通道增加“增值税税率，附加税率字段” add by renping 20191019 start
                            data.vatTaxRate = result.vatTaxRate;
                            data.adtTaxRate = result.adtTaxRate;
                            // 通道增加“增值税税率，附加税率字段” add by renping 20191019 end
                            data.busiAcctNo = result.busiAcctNo;
                            data.clientCustomerName = result.clientCustomerName;
                            data.contactWay = result.contactWay;
                            data.contact = result.contact;
                            data.finprodId = result.finprodId;
                            data.payType = result.payType;
                            data.counterId = value;
                            table.renderRow(data.LAY_TABLE_INDEX, data);
            				/*table.removeRow(data);
            				table.addRow(data);*/
                    		// 重新选择通道托管费率、管理费率为空时不能带出 modify by renping 20191029 end
                            //计算投资收益率、资产推荐费
                            AssetCommon.loadRateChange();
                        }
                    });
                }else{
                    table.renderRow(data.LAY_TABLE_INDEX, rowData);
                    //存款类不做计算 add by zhangnian
                    /*if($('#pageId').val() != 'FinDepositTarget002'){*/
                        //计算投资收益率、资产推荐费
                        AssetCommon.loadRateChange();
                    /*}*/
                }
            }
            if(field === "clientYield"||field === "managerYield"||field === "otherYield"){
                /*if($('#pageId').val() != 'FinDepositTarget002'){*/
                    //计算投资收益率、资产推荐费
                    AssetCommon.loadRateChange();
               /* }*/
            }

        },
        searchOnInit: true,
        loadonce: true,
        //rowno: true,
        checkbox:false,
        height:290,
        addbtn:true,
        addclick: function(row) {
                //通道层级递增
                var list = table.getAllRows();
                // 资产只允许选一个通道 add by renping start
                if(list.length > 1){
					// 阻止增加多条通道信息
					Joyin.error("该资产不允许添加多条通道信息!");
					return; // 阻止往下执行
                }
                // 资产只允许选一个通道 add by renping end
                //项目类资产中通道层级全部设置为1
                if ($('#pageId').val() == 'FinProjectTargetAsset002') {
                    row.chlLevel = 1;
                }else {
                    //row.chlLevel = list.length;
                    row.chlLevel = 1;
                }
          },
        // 资产只允许选一个通道 modify by renping start
        done:function(allData){
			// 阻止增加多条通道信息
            if(allData.length > 1){
				table.removeRow(allData[1]);
				return; // 阻止往下执行
            }
        // 资产只允许选一个通道 modify by renping end
            sortTableByChlLevel();
        },
        page:false,
    });
        
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        doneFlag = false;
        table.removeRow(data);
        //if($('#pageId').val() != 'FinDepositTarget002'){
            //计算投资收益率、资产推荐费
            AssetCommon.loadRateChange();
       // }
    });
    
    var getAllRows = function(){
            return table.getAllRows();
    }
    
    /**
     * 选择不同的通道,true有重复，false无重复
     */
    var choseNoSameChannel = function(list,data,value){
    	
    	var flag = false; // 默认无重复
    	
    	if (list) { // 验证不可选择相同的通道
			$.each(list, function(index, obj) {
				if (data.chlLevel != obj.chlLevel) {
					if (value == obj.counterId) {
						flag = true; // 有重复
					}
				}
			});
		}
    	
    	return flag;
    }
    
    /**
     * 通道层级递增排序
     */
    var sortTableByChlLevel = function(){
        if(doneFlag){
            return;
        }
        var list = getAllRows();
        if(list){
            $.each(list,function(index,obj){
                if ($('#pageId').val() == 'FinProjectTargetAsset002') {
                    obj.chlLevel = 1;
                }else {
                    //obj.chlLevel = index+1;
                    obj.chlLevel = 1;
                }
            });
        }
        doneFlag = true;
        table.reload({data:list, url:null});
    }
    // 公开接口
    window.BussParticipantInfo = {
            getAllRows:getAllRows
    };
}());