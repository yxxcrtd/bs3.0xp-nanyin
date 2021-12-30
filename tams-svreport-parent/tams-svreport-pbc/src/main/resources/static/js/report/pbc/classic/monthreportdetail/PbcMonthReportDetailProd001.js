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
            { title: "币种", width : 130, field: 'ccyStr',sortField:'ccy'},
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
            {title: '住户',field:'ordIdentified01Str',width : 130, sortField:'ordIdentified01'},
            {title: '广义政府',field:'ordIdentified02Str',width : 130, sortField:'ordIdentified02'},
            {title: '非金融企业',field:'ordIdentified03Str',width : 130, sortField:'ordIdentified03'},
            {title: '金融机构（实体）',field:'ordIdentified04Str',width : 130, sortField:'ordIdentified04'},
            {title: '特定目的载体',field:'ordIdentified05Str',width : 130, sortField:'ordIdentified05'},
            {title: '境外',field:'ordIdentified06Str',width : 130, sortField:'ordIdentified06'},
            {title: '合计',field:'ordIdentifiedAllStr',width : 130, sortField:'ordIdentifiedAll'},
            {title: '401000北京',field:'buyPlace01Str',width : 130, sortField:'buyPlace01'},
            {title: '402000天津',field:'buyPlace02Str',width : 130, sortField:'buyPlace02'},
            {title: '403000河北',field:'buyPlace03Str',width : 130, sortField:'buyPlace03'},
            {title: '404000山西',field:'buyPlace04Str',width : 130, sortField:'buyPlace04'},
            {title: '405000内蒙古',field:'buyPlace05Str',width : 130, sortField:'buyPlace05'},
            {title: '406001大连',field:'buyPlace06Str',width : 130, sortField:'buyPlace06'},
            {title: '辽宁其他',field:'buyPlace07Str',width : 130, sortField:'buyPlace07'},
            {title: '407000吉林',field:'buyPlace08Str',width : 130, sortField:'buyPlace08'},
            {title: '408000黑龙江',field:'buyPlace09Str',width : 130, sortField:'buyPlace09'},
            {title: '409000上海',field:'buyPlace10Str',width : 130, sortField:'buyPlace10'},
            {title: '410000江苏',field:'buyPlace11Str',width : 130, sortField:'buyPlace11'},
            {title: '411001宁波',field:'buyPlace12Str',width : 130, sortField:'buyPlace12'},
            {title: '浙江其他',field:'buyPlace13Str',width : 130, sortField:'buyPlace13'},
            {title: '412000安徽',field:'buyPlace14Str',width : 130, sortField:'buyPlace14'},
            {title: '413001厦门',field:'buyPlace15Str',width : 130, sortField:'buyPlace15'},
            {title: '福建其他',field:'buyPlace16Str',width : 130, sortField:'buyPlace16'},
            {title: '414000江西',field:'buyPlace17Str',width : 130, sortField:'buyPlace17'},
            {title: '415001青岛',field:'buyPlace18Str',width : 130, sortField:'buyPlace18'},
            {title: '山东其他',field:'buyPlace19Str',width : 130, sortField:'buyPlace19'},
            {title: '416000河南',field:'buyPlace20Str',width : 130, sortField:'buyPlace20'},
            {title: '417000湖北',field:'buyPlace21Str',width : 130, sortField:'buyPlace21'},
            {title: '418000湖南',field:'buyPlace22Str',width : 130, sortField:'buyPlace22'},
            {title: '419001深圳',field:'buyPlace23Str',width : 130, sortField:'buyPlace23'},
            {title: '广东其他',field:'buyPlace24Str',width : 130, sortField:'buyPlace24'},
            {title: '420000广西',field:'buyPlace25Str',width : 130, sortField:'buyPlace25'},
            {title: '421000海南',field:'buyPlace26Str',width : 130, sortField:'buyPlace26'},
            {title: '422000重庆',field:'buyPlace27Str',width : 130, sortField:'buyPlace27'},
            {title: '423000四川',field:'buyPlace28Str',width : 130, sortField:'buyPlace28'},
            {title: '424000贵州',field:'buyPlace29Str',width : 130, sortField:'buyPlace29'},
            {title: '425000云南',field:'buyPlace30Str',width : 130, sortField:'buyPlace30'},
            {title: '426000西藏',field:'buyPlace31Str',width : 130, sortField:'buyPlace31'},
            {title: '427000陕西',field:'buyPlace32Str',width : 130, sortField:'buyPlace32'},
            {title: '428000甘肃',field:'buyPlace33Str',width : 130, sortField:'buyPlace33'},
            {title: '429000青海',field:'buyPlace34Str',width : 130, sortField:'buyPlace34'},
            {title: '430000宁夏',field:'buyPlace35Str',width : 130, sortField:'buyPlace35'},
            {title: '431000新疆',field:'buyPlace36Str',width : 130, sortField:'buyPlace36'},
            {title: '合计',field:'buyPlaceAllStr',width : 130, sortField:'buyPlaceAll'}


        ]
    });

    var numFormat = function(num) {
        var c = (num.toString().indexOf ('.') !== -1) ? num.toLocaleString() : num.toString().replace(/(\d)(?=(?:\d{3})+$)/g, '$1,');
        return c;
    }

    /**
     * 导出
     */
    var exportExcel = function(flag) {
        var expArr=table.getAllCheckedRows();
        var param=getSearchParams();
        if(!param.enddate){
            Joyin.error("请选择统计日期进行导出！");
            return;
        }
        if(flag == '0'){
            Joyin.exportExcel("export",param);
        }else{
            if((!expArr || expArr.length ==0)){
                Joyin.error("请选择导出数据！");
                return;
            }
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
               var param=getSearchParams();
               if(!param.enddate){
                   Joyin.error("请选择统计日期进行查询！");
                   return;
               }else{
                   table.load();
               }
           });
           $('#PbcMonthReportDetailProd001_btn_export').click(function() {
               exportExcel(0);
           });
           $('#PbcMonthReportDetailProd001_btn_exportCheck').click(function() {
               exportExcel(1);
           });
       });
}());