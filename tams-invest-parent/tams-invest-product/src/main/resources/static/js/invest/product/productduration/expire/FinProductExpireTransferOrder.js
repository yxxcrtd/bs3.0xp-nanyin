/**
 * 划款指令共通组件
 */
var FinProductExpireOrder = function(){
	var entityName = ENTITY_NAME;
	var getElementById = function(id) {
		return $('#' + entityName + '_' + id);
	};
	var table;
	
    /**
     * 定义数据表格
     *
     */
    var option = {
        searchOnInit:true,
        checkbox:false,
        cols:[
            {field:'isNeedSend', width:140, title: 'BussTrdTransferOrder.isNeedSend',edit:true,
                editOption:{type:'select', multiple: false, searchable: false, result: YES_NO}, sortable:false}
            ,{field:'payTypeText', width:130, title: 'BussTrdTransferOrder.payType', sortable:false}
            ,{field:'settleDateText', width:130, title: 'BussTrdTransferOrder.settleDate', sortable:false}
            ,{field:'settleAmt', width:130, title: 'BussTrdTransferOrder.settleAmt', type: 'money', sortable:false}
            ,{field:'ccy', width:130, title: 'BussTrdTransferOrder.ccy', sortable:false}
            ,{field:'payAcctNo', width:130, title: 'BussTrdTransferOrder.payAcctNo',edit:true,
                editOption:{type:'select', multiple: false, searchable: true, parentIds: entityName + '_payAcctNo', showField:'id',
                    url: JoyinUtil.getRealPath("/component/trade/BussTrdTransferOrder/select")}, sortable:false}
            ,{field:'payAcctName', width:150, title: 'BussTrdTransferOrder.payAcctName', sortable:false}
            ,{field:'recAcctNo', width:130, title: 'BussTrdTransferOrder.recAcctNo',edit:true,
                editOption:{type:'select', multiple: false, searchable: true, parentIds: entityName + '_recAcctNo', showField:'id',
                    url: JoyinUtil.getRealPath("/component/trade/BussTrdTransferOrder/select")}, sortable:false}
            ,{field:'recAcctName', width:150, title: 'BussTrdTransferOrder.recAcctName', sortable:false}
            ,{field:'recAcctPoint', width:150, title: 'BussTrdTransferOrder.recAcctPoint', sortable:false}
            ,{field:'purpose', width:130, title: 'BussTrdTransferOrder.purpose', sortable:false, edit:true}
            ,{field:'remark', width:130, title: 'BussTrdTransferOrder.remark', sortable:false, edit:true}
            ,{field:'portfolioId', width:130, title: 'BussTrdTransferOrder.portfolioId', sortable:false}
            ,{field:'portfolioName', width:150, title: 'BussTrdTransferOrder.portfolioName', sortable:false}
            ,{field:'inOutBank', width:130, title: 'BussTrdTransferOrder.inOutBank', sortable:false}
            ,{field:'orderId', visible:false}
            ,{field:'payType', visible:false}
            ,{field:'ps', visible:false}
            ,{field:'settleStatus', visible:false}
            ,{field:'isTotalTrade', visible:false}
            ,{field:'settleDate', visible:false}
        ],
        page:false,
        data:[],
        editCell: function (value, row, field, cell, column) {
            if (column.field == 'payAcctNo') {
                $.each(row.payAcctNoList, function (i, v) {
                    if (v.id == value) {
                        row.payAcctName = v.text;
                    }
                });
                if (typeof value == "undefined" || value == null || value == "") {
                    row.payAcctName = "";
                }
            } else if (column.field == 'recAcctNo') {
                $.each(row.recAcctNoList, function (i, v) {
                    if (v.id == value) {
                        row.recAcctName = v.text;
                    }
                });
                if (typeof value == "undefined" || value == null || value == "") {
                    row.recAcctName = "";
                }
                
                // 选择收款账户带出行内/行外、网点
                Joyin.ajax(JoyinUtil.getRealPath('/component/trade/BussTrdTransferOrder/queryStlCapacctInfoByAcctNo'), {ccy : row.ccy, acctNo : value}, function(result){
                	var assetSource = JSON.parse(ASSET_SOURCE);
                	$.each(assetSource, function (i, v) {
                        if (v.id == result.inOutBank) {
                            row.inOutBank = v.text;
                        }
                    });
                	
                	var yesNo = JSON.parse(YES_NO);
                    $.each(yesNo, function (i, v) {
                        if (v.id == row.isNeedSend) {
                            row.isNeedSend = v.text;
                        }
                    });
                    
                    // 网点
                    row.recAcctPoint = result.largePayno;
                    
                    table.renderRow(row.LAY_TABLE_INDEX, row);
                })
            }
            
            var yesNo = JSON.parse(YES_NO);
            $.each(yesNo, function (i, v) {
                if (v.id == row.isNeedSend) {
                    row.isNeedSend = v.text;
                }
            });
            
            table.renderRow(row.LAY_TABLE_INDEX, row);
        }
    }

    if (bussTrdTransferOrderDtos && bussTrdTransferOrderDtos.length > 0) {
        option.data = option.data.concat(bussTrdTransferOrderDtos);
    }
    table = getElementById("table").joyintable(option);
    
    return {
	    	tableReload : function(data){
	    		table.clear(function () {
                    table.addRow(data)
                })
	    	}
	}
}();