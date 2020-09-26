$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodType: $.trim($('#finprodType').val()),//金融工具类别
            finprodId: $.trim($('#finprodId').val()),//金融工具代码
            finprodName: $.trim($('#finprodName').val()),//金融工具名称
            vDateBegin:$('#vDate').startDate(),//起息日
            vDateEnd:$('#vDate').endDate(),   
            mDateBegin:$('#mDate').startDate(),//到期日
            mDateEnd:$('#mDate').endDate()
        }
    };
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
		checkbox: true,
		//cellMinWidth : 100,
		cols: [
		   {field:'finprodId', title: '金融工具代码',width:150, sort: true},
		   {field:'finprodTypeFmt', sortField:'finprodType', title: '金融工具类型',width:150, sort: true},
		   {field:'finprodType', title: '金融工具类型',width:150, sort: true,visible:false},
		   {field:'finprodAbbr', title: '金融工具简称',width:200, sort: true},
		   {field:'finprodName', title: '金融工具全称',width:200, sort: true},
		   {field:'profitTypeFmt', sortField:'profitType',title: '收益类型',width:100,sort: true},
		   {field:'profitType', title: '收益类型',width:100,sort: true,visible:false},
		   {field:'couponSpeciesFmt', sortField:'couponSpecies',title: '息票品种',width:100, sort: true},
		   {field:'couponSpecies', title: '息票品种',width:100, sort: true,visible:false},
	       {field:'chlFinprodId', title: '通道代码',width:100, sort: true},
           {field:'finprodMarketId', title: '市场代码',width:150, sort: true},
           {field:'issuePrice', type:"money", title: '发行价',width:100, sort: true},
           {field:'issueAmt', type:"money", title: '发行规模',width:150, sort: true},
           {field:'ccyFmt', sortField:'ccy',title: '募集币种',width:150,sort: true},
           {field:'ccy', title: '募集币种',width:150,sort: true,visible:false},
           {field:'blnAreaFmt', sortField:'blnArea',title: '境内外',width:100, sort: true},
           {field:'blnArea', title: '境内外',width:100, sort: true,visible:false},
//           {field:'blnAreaCode', title: '境内外',width:100, sort: true,visible:false},
           {field:'tradeMarketFmt', sortField:'tradeMarket',title: '交易场所', width:150, sort: true},
           {field:'tradeMarket', title: '交易场所', visible: false,width:150, sort: true},
//           {field:'tradeMarketCode', title: '交易场所',width:100, sort: true,visible:false},
           {field:'issueType', title: '发行方式',width:120,sort: true,visible:false},
           {field:'issueTypeFmt', sortField:'issueType', title: '发行方式',width:150, sort: true},
           {field:'issuer', title: '发行人',width:150,sort: true},
           {field:'idateStr', title: '发行日',width:110, sort: true},
		   {field:'vdateStr', title: '起息日',width:110, sort: true},
		   {field:'mdateStr', title: '到期日',width:110, sort: true},
           {field:'actmdateStr', title: '实际到期日',width:150, sort: true},
           {field:'isChlFt', title: '是否通道产品',width:150, sort: true,sortField:'isChl'},
           {field:'isSusFt', title: '是否永续',width:110, sort: true,sortField:'isSus'},
           {field:'isRightFmt', sortField:'isRight',title: '是否含权',width:110, sort: true},
           {field:'isRight', title: '是否含权',width:110, sort: true, visible: false},
           {field:'pFinprodId', title: '母金融产品代码',width:150, sort: true},
           {field:'oFinprodId', title: '原金融产品代码',width:150, sort: true},
           {field:'oFinprodTypeFmt', title: '原金融产品类型', sortField:'oFinprodType',width:150, sort: true},
           {field:'oFinprodType', visible: false,title: '原金融产品类型',width:150, sort: true}
	
		],
	});
	
	var exportExcel = function() {
	    var rows=table.getAllCheckedRows();
	    var flag=true;
        if(rows.length<=0){
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        }else {
            var datas = JSON.stringify(rows);
            Joyin.exportExcel('export', {datas:datas});
        }
    };
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValProductAssetsQuery001_btn_search').click(function() {
            table.load();
        });
        $('#ValProductAssetsQuery001_btn_export').click(function() {
            exportExcel();
        });
    });
	
});