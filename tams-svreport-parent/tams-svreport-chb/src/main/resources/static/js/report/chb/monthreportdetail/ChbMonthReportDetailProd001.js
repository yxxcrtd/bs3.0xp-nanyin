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
            { title: "产品代码", width : 130, field: 'prodid',sortField:'prodid'},
            { title: "产品名称", width : 130, field: 'prodName',sortField:'prodName'},
            { title: "起息日", width : 200, field: 'vdateStr', sortField:'vdate'},
            { title: "到期日", width : 200, field: 'mdateStr', sortField:'mdate'},
            { title: "募集方式", width : 130, field: 'raiseTypeStr',sortField:'raiseType'},
            { title: "投资性质", width : 130, field: 'ivstorientationStr',sortField:'ivstorientation'},
            { title: "募集币种", width : 130, field: 'ccyStr',sortField:'ccy'},
            { title: "产品运作模式", width : 130, field: 'operateModeStr',sortField:'operateMode'},
            { title: "开放类型", width : 130, field: 'opentypeStr',sortField:'opentype'},
            { title: "期限（天）", width : 130, field: 'term',sortField:'term'},
            { title: "期限类型", width : 130, field: 'termtypeStr',sortField:'termtype'},
            { title: "母行划转的产品", width : 130, field: 'isTransferStr',sortField:'isTransfer'},
            { title: "是否保本理财", width : 130, field: 'isBreakevenStr',sortField:'isBreakeven'},
            { title: "养老理财产品", width : 130, field: 'isYllccpStr',sortField:'isYllccp'},
            { title: "是否金融同业专属", width : 130, field: 'excFinSectorStr',sortField:'excFinSector'},
            { title: "是否新产品", width : 130, field: 'isFhxgcpStr',sortField:'isFhxgcp'},
            { title: "本期总募集金额", width : 130, field: 'dirtyamtsumStr',sortField:'dirtyamtsum'},
            { title: "本期净募集金额", width : 130, field: 'cleanamtsumStr',sortField:'cleanamtsum'},
            { title: "是否本期发行", width : 130, field: 'isThisTremStr',sortField:'isThisTrem'},
            { title: "期末是否存续", width : 130, field: 'isEndTremStr',sortField:'isEndTrem'},
            { title: "期末余额", width : 130, field: 'endamtsumOthStr',sortField:'endamtsumOth'},
            { title: "本期兑付金额", width : 130, field: 'payamtsumStr',sortField:'payamtsum'},
            { title: "本期客户端实现收益总额", width : 130, field: 'customeramtStr',sortField:'customeramt'},
            { title: "本期银行端实现收益总额",  width : 130,field: 'bankamtStr',sortField:'bankamt'},
            {title: '自然人（余额）',field:'ordIdentified01Str',  width : 130,sortField:'ordIdentified01'},
            {title: '非金融机构余额',field:'ordIdentified02Str',  width : 130,sortField:'ordIdentified02'},
            {title: '银行类金融机构余额',field:'ordIdentified03Str',  width : 130,sortField:'ordIdentified03'},
            {title: '保险业金融机构余额',field:'ordIdentified04Str',  width : 130,sortField:'ordIdentified04'},
            {title: '信托公司余额',field:'ordIdentified05Str',  width : 130,sortField:'ordIdentified05'},
            {title: '证券公司余额',field:'ordIdentified06Str',  width : 130,sortField:'ordIdentified06'},
            {title: '基金公司余额',field:'ordIdentified07Str',  width : 130,sortField:'ordIdentified07'},
            {title: '其他金融机构余额',field:'ordIdentified08Str',  width : 130,sortField:'ordIdentified08'},
            {title: '金融机构资产管理产品余额',field:'ordIdentified09Str',  width : 130,sortField:'ordIdentified09'}

        ]
    });

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
                keyList.push(row.prodId);
            });
            param.prodId = keyList;
            Joyin.exportExcel('export', param);
        }
        
    };

       
       function init(){

       };
       init();
       
       $(function(){
           // 绑定按钮事件
           $('#ChbMonthReportDetailProd001_btn_search').click(function() {
               var param=getSearchParams();
               if(!param.enddate){
                   Joyin.error("请选择统计年月进行查询！");
                   return;
               }else{
                   table.load();
               }
           });

           $('#ChbMonthReportDetailProd001_btn_export').click(function() {
               exportExcel(0);
           });
           $('#ChbMonthReportDetailProd001_btn_exportCheck').click(function() {
               exportExcel(1);
           });
       });
}());