/**
 * 开放计划共通JS
 * @auth zhengyan
 */
(function(){

	var getParams = function(){
		var data = JSON.parse($('#finProductFeePlanInfo_feeInfo').val());
		if (data.provisionFirstDay){
			data.provisionFirstDay = new Date(data.provisionFirstDay);
		}
		return data;
	}
	
	/**
     * 定义数据表格
     */
    var bussFinProductFeePlanTable = $('#feePlanTable').joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('component/prod/BussFinProductFeeInfo/generatePayFeePlan'),
            type: 'POST',
            data: getParams()
        },
        searchOnInit: true,
        height: 200,
        page: false,
        checkbox: false,
        loadonce: true,
        rowno: true,
        cols:[
             {field:'vdate', title: 'BussFinProductFeeInfo.calcFeeStartDate',sortable:false, type:'date', width: 150}
			,{field:'mdate', title: 'BussFinProductFeeInfo.calcFeeEndDate',sortable:false, type:'date', width: 150}
			,{field:'payDate', title: 'BussFinProductFeeInfo.payFeeDate',sortable:false, type:'date', width: 150}
        ]
    });
    
    
    $(function() {
    	
    });
}());