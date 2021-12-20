$(function() {
  //跳转的页码
    var pageCount = 1;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            //反馈状态
            prodId: $('#prodId').val()==null?null:$('#prodId').val().join(","),

            dataType:$('#dataType').val()==null?null:$('#dataType').val().join(","),
            //结束日（统计日期）
            enddate : $('#enddate').val()
        }
    };
    
    var laydate = layui.laydate;
    laydate.render({
		elem: '#enddate',
		type: 'month',
		format: 'yyyyMM'
			
	});

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
        cols:[
            { title: "主键", field: 'dataTypekey',sortable:false,visible:false},
            { title: "金额类型", width : 130, field: 'dataTypeStr',sortField:'dataType'},
            { title: "产品代码", width : 130, field: 'prodid',sortField:'prodid'},
            { title: "产品名称", width : 130, field: 'prodName',sortField:'prodName'},
            { title: "起息日", width : 200, field: 'vdateStr', sortField:'vdate'},
            { title: "到期日", width : 200, field: 'mdateStr', sortField:'mdate'},
            { title: "募集方式", width : 130, field: 'raiseTypeStr',sortField:'raiseType'},
            { title: "投资性质", width : 130, field: 'ivstorientationStr',sortField:'ivstorientation'},
            { title: "币种", width : 130, field: 'ccy',sortField:'ccy'},
            { title: "管理方式", width : 130, field: 'manageTypeStr',sortField:'manageType'},
            { title: "受托职责", width : 130, field: 'fiduciaryDutyStr',sortField:'fiduciaryDuty'},
            { title: "产品运作模式", width : 130, field: 'operateModeStr',sortField:'operateMode'},
            { title: "期限（天）", width : 130, field: 'termdays',sortField:'termdays'},
            { title: "期限类型", width : 130, field: 'termtypeStr',sortField:'termtype'},
            { title: "是否分级产品", width : 130, field: 'isConstracteStr',sortField:'isConstracte'},
            { title: "是否收益权转让产品", width : 130, field: 'transferTypeStr',sortField:'transferType'},
            { title: "是否货币市场基金", width : 130, field: 'isMarketFundStr',sortField:'isMarketFund'},
            { title: "是否本期发行", width : 130, field: 'isThisTremStr',sortField:'isThisTrem'},
            { title: "是否本期终止", width : 130, field: 'isEndTremStr',sortField:'isEndTrem'},
            { title: "期末是否存续", width : 130, field: 'isEndSubsistStr',sortField:'isEndSubsist'},
            { title: "兑付类型", width : 130, field: 'paystatusStr',sortField:'paystatus'},
            {title: '住户',field:'ordIdentified01', type:'money', sortField:'ordIdentified01'},
            {title: '广义政府',field:'ordIdentified02', type:'money', sortField:'ordIdentified02'},
            {title: '非金融企业',field:'ordIdentified03', type:'money', sortField:'ordIdentified03'},
            {title: '金融机构（实体）',field:'ordIdentified04', type:'money', sortField:'ordIdentified04'},
            {title: '特定目的载体',field:'ordIdentified05', type:'money', sortField:'ordIdentified05'},
            {title: '境外',field:'ordIdentified06', type:'money', sortField:'ordIdentified06'},
            {title: '合计',field:'ordIdentified07', type:'money', sortField:'ordIdentified07'},
            {title: '401000北京',field:'buyPlace01', type:'money', sortField:'buyPlace01'},
            {title: '402000天津',field:'buyPlace02', type:'money', sortField:'buyPlace02'},
            {title: '403000河北',field:'buyPlace03', type:'money', sortField:'buyPlace03'},
            {title: '404000山西',field:'buyPlace04', type:'money', sortField:'buyPlace04'},
            {title: '405000内蒙古',field:'buyPlace05', type:'money', sortField:'buyPlace05'},
            {title: '406001大连',field:'buyPlace06', type:'money', sortField:'buyPlace06'},
            {title: '辽宁其他',field:'buyPlace07', type:'money', sortField:'buyPlace07'},
            {title: '407000吉林',field:'buyPlace08', type:'money', sortField:'buyPlace08'},
            {title: '408000黑龙江',field:'buyPlace09', type:'money', sortField:'buyPlace09'},
            {title: '409000上海',field:'buyPlace10', type:'money', sortField:'buyPlace10'},
            {title: '410000江苏',field:'buyPlace11', type:'money', sortField:'buyPlace11'},
            {title: '411001宁波',field:'buyPlace12', type:'money', sortField:'buyPlace12'},
            {title: '浙江其他',field:'buyPlace13', type:'money', sortField:'buyPlace13'},
            {title: '412000安徽',field:'buyPlace14', type:'money', sortField:'buyPlace14'},
            {title: '413001厦门',field:'buyPlace15', type:'money', sortField:'buyPlace15'},
            {title: '福建其他',field:'buyPlace16', type:'money', sortField:'buyPlace16'},
            {title: '414000江西',field:'buyPlace17', type:'money', sortField:'buyPlace17'},
            {title: '415001青岛',field:'buyPlace18', type:'money', sortField:'buyPlace18'},
            {title: '山东其他',field:'buyPlace19', type:'money', sortField:'buyPlace19'},
            {title: '416000河南',field:'buyPlace20', type:'money', sortField:'buyPlace20'},
            {title: '417000湖北',field:'buyPlace21', type:'money', sortField:'buyPlace21'},
            {title: '418000湖南',field:'buyPlace22', type:'money', sortField:'buyPlace22'},
            {title: '419001深圳',field:'buyPlace23', type:'money', sortField:'buyPlace23'},
            {title: '广东其他',field:'buyPlace24', type:'money', sortField:'buyPlace24'},
            {title: '420000广西',field:'buyPlace25', type:'money', sortField:'buyPlace25'},
            {title: '421000海南',field:'buyPlace26', type:'money', sortField:'buyPlace26'},
            {title: '422000重庆',field:'buyPlace27', type:'money', sortField:'buyPlace27'},
            {title: '423000四川',field:'buyPlace28', type:'money', sortField:'buyPlace28'},
            {title: '424000贵州',field:'buyPlace29', type:'money', sortField:'buyPlace29'},
            {title: '425000云南',field:'buyPlace30', type:'money', sortField:'buyPlace30'},
            {title: '426000西藏',field:'buyPlace31', type:'money', sortField:'buyPlace31'},
            {title: '427000陕西',field:'buyPlace32', type:'money', sortField:'buyPlace32'},
            {title: '428000甘肃',field:'buyPlace33', type:'money', sortField:'buyPlace33'},
            {title: '429000青海',field:'buyPlace34', type:'money', sortField:'buyPlace34'},
            {title: '430000宁夏',field:'buyPlace35', type:'money', sortField:'buyPlace35'},
            {title: '431000新疆',field:'buyPlace36', type:'money', sortField:'buyPlace36'},
            {title: '合计',field:'buyPlaceAll', type:'money', sortField:'buyPlaceAll'}


        ]
    });

    /**
     * 导出
     */
    var exportExcel = function(flag) {
        var expArr=table.getAllCheckedRows();
        var param={};
        if(!expArr || expArr.length ==0){
            param = getSearchParams();
            if(!param.enddate){
                Joyin.error("请选择统计日期进行导出！");
                return;
            }
            Joyin.exportExcel("export",param);
        }else{
            var keyList = [];
            $.each(expArr, function(i, row) {
                keyList.push(row.dataTypekey);
            });
            param.keyList = keyList;
            Joyin.exportExcel('export', param);
        }
        
    };

       
       function init(){

       };
       init();
       
       $(function(){
           // 绑定按钮事件
           $('#PbcMonthReportDetailProd001_btn_search').click(function() {
               table.load();
           });
           $('#PbcMonthReportDetailProd001_btn_export').click(function() {
               exportExcel(0);
           });
           $('#PbcMonthReportDetailProd001_btn_exportCheck').click(function() {
               exportExcel(1);
           });
       });
}());