(function(){
    
    var getElementById = function(id) {
        return $('#' + bussFinProductInvestPeriodEntityName + '_' + id);
    };
    
    var getSearchParams = function(){
        var data = {};
         data.workflowId = getElementById('workflowId').val();
         data.finprodId = getElementById('finprodId').val();
         data.entityName = bussFinProductInvestPeriodEntityName;
        return data;
      }
    /**
     * 定义数据表格
     */
    var bussFinProductInvestPeriodTable = getElementById('period').joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/component/prod/BussFinProductInvestPeriod/queryBussFinProductInvestPeriodByFinprodId'),
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
       
        searchOnInit: true,
        loadonce: true,
        checkbox:false,
        page: false,
        height : 200,
        cols:[
            {field:'sn',width:300, title: 'BussFinProductInvestPeriod.sn', edit:false, sortable: false}
              ,{field:'investDays',title: 'BussFinProductInvestPeriod.investDays', sortable: false}
              ,{field:'operation', title: 'SysCommon.operation',type:'action', buttons: ['delt']}
          ],
          done : function(table, allData){
              //设置都不可编辑
               var allrowData =  bussFinProductInvestPeriodTable.options.cols;
               for(var i in allrowData){
                     allrowData[i].edit = false;
               }
               tableDataChange();
               
               var selArr = bussFinProductInvestPeriodTable.getAllRows();
               if (!selArr.length == 0) {
	              	var selectedItem = [];
	                   _.each(selArr,function (sel){
	                   	selectedItem[selectedItem.length] = {investDays: sel.investDays + ''};
	                   });
	               var selected = JSON.stringify(selectedItem);
	       			$('#periods').val(selected);
	       		}
               
          }
    });
    
    // 新增按钮点击事件
    //点击“新增”按钮，在业绩基准信息列表中展示该条信息，并重置上方要素信息	
	getElementById('btn_create').click(function() {
		var investDays = getElementById('investDays').val();
		if (investDays === null || investDays === '' || investDays === 'null') {
			Joyin.error("投资周期不能为空！");
			return;
		}
	    var tableArry = bussFinProductInvestPeriodTable.getAllRows();
		var row = {
				sn: tableArry.length+1,
				investDays: investDays
    		};
		bussFinProductInvestPeriodTable.addRow(row);  
		tableDataChange();
		var selArr = bussFinProductInvestPeriodTable.getAllRows();
        if (!selArr.length == 0) {
       	var selectedItem = [];
            _.each(selArr,function (sel){
            	selectedItem[selectedItem.length] = {investDays: sel.investDays + ''};
            });
        var selected = JSON.stringify(selectedItem);
		$('#periods').val(selected);
		}
		getElementById('investDays').val("");
	});
	
	/**
	 * 绑定删除事件
	 */
	bussFinProductInvestPeriodTable.bind('click', '.delt', function(data, index) {
		bussFinProductInvestPeriodTable.removeRow(data);
		tableDataChange();
	});
	var cleanTable=function(){
		bussFinProductInvestPeriodTable.reload({ajax:null, url: null, data:[]});
		getElementById('investDays').val("");
	}
	var finProductInvestPeriodInitAction = function finProductInvestPeriodInitAction(input_finprodid){
	     getElementById('finprodId').val(input_finprodid).trigger("change");
    	bussFinProductInvestPeriodTable.load();
    }
	/**
	 * 列表数据变更change
	 */			 
	function tableDataChange(){
		var investmentCycleArr = [];
	    var prodInvestmentArr = bussFinProductInvestPeriodTable.getAllRows();
        _.each(prodInvestmentArr,function (prod){
        	investmentCycleArr[investmentCycleArr.length] = {id: prod.investDays, text : prod.investDays};
        });
        INVESTMENT_CLCYE = JSON.stringify(investmentCycleArr);
	}
	window.BussFinProductInvestPeriod = {
			cleanTable : cleanTable,
			finProductInvestPeriodInitAction : finProductInvestPeriodInitAction
		}
}());