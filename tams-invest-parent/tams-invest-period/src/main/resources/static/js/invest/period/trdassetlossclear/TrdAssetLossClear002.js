/**
 * 资产减值清理新建
 */
$(function() {
	var flag = true;
	
    function saveOrSubmit(operateType) {
    	
    	var tableData = table.getAllRows();
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.finprodId = $('#finprodMarketId').val();
        object.operateType = operateType;
        object.tradeDate = $('#clearDate').val();
        object.selectedItem = tableData;     
        Joyin.ajaxJson(basePath + 'invest/period/trdassetlossclear/TrdAssetLossClear002/check', object, function(result) {
	            Joyin.confirm(result.data, function() {
	                if(operateType === 'save') {
	                    WorkFlow.doSaveList(basePath + 'invest/period/trdassetlossclear/TrdAssetLossClear002/save', object, "TrdAssetLossClear002_btn_save");
	                }
	                else {
	                    WorkFlow.doSubmitList(basePath + 'invest/period/trdassetlossclear/TrdAssetLossClear002/save', object, "TrdAssetLossClear002_btn_submit");
	                }
	            });
	        });
    };
   
    /**
     * 页面加载初始化
     */
	var options = {
			page: true,
			ajax: {
            url: JoyinUtil.getRealPath('invest/period/trdassetlossclear/TrdAssetLossClear002/findNewPage'),
            type: 'POST',
            data: function() {
                return {finprodMarketId: $('#finprodMarketId').val(),
                		pageModel:pageModel,
                        clearDate:$('#clearDate').val()
                	};
              }
			},
		checkbox: false,
        searchOnInit: true,
        cols:[
            {field:'portfolioId', title: 'TrdAssetLossClear002.portfolioId', sortable:false}//组合代码
            ,{field:'portfolioName', title: 'TrdAssetLossClear002.portfolioName', sortable:false}//组合名称
            ,{field:'secAcctId', title: 'TrdAssetLossClear002.secAcctId', sortable:false, visible:false}//证券管理账户/通道代码
            ,{field:'secAcctIdName', title: 'TrdAssetLossClear002.secAcctId', sortable:false}//证券管理账户/通道代码名称
            ,{field:'invAim', title: 'TrdAssetLossClear002.invAim', sortable:false, visible:false}//投资目的
            ,{field:'invAimName', title: 'TrdAssetLossClear002.invAim', sortable:false}//投资目的+文字
            ,{field:'shareAmt', title: 'TrdAssetLossClear002.shareAmt',type:'number', sortable:false}//持仓份额
            ,{field:'prinAmt', title: 'TrdAssetLossClear002.prinAmt',type:'number', sortable:false}//持仓成本
            ,{field:'clearRecAmt', title: 'TrdAssetLossClear002.clearRecAmt',type:'number', sortable:false}//已到期本息
            ,{field:'intAmt', title: 'TrdAssetLossClear002.intAmt',type:'number', sortable:false}//持仓应收利息
            ,{field:'penaltyIntAmt', title: 'TrdAssetLossClear002.penaltyIntAmt',type:'number', sortable:false}//应收逾期罚息
            ,{field:'clearLossAmt', title: 'TrdAssetLossClear002.clearLossAmt',type:'number', sortable:false}//当前账面减值余额
            ,{field:'unitValue', title: 'TrdAssetLossClear002.unitValue', type:'number', sortable:false,visible:false}//单位面值
            ,{field:'tradeMarket', title: 'TrdAssetLossClear002.tradeMarket', sortable:false, visible:false}//交易场所
            ,{field:'ccy', title: 'TrdAssetLossClear002.ccy', sortable:false, visible:false}//币种
        ]
    };
	//产品代码画面联动
	$("#finprodMarketId").change(function(){
		table.table?table.reload():table.load();
	});
    
    /*
     * 绑定表格事件
     */
	var table = $("#dataTable").joyintable(options);  
    
    $(function(){
    	/**
         * 画面加载完成
        */
    	$('#clearDate').attr("disabled",true);
    	
        // 绑定按钮事件
        $('#TrdAssetLossClear002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#TrdAssetLossClear002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    })
}());