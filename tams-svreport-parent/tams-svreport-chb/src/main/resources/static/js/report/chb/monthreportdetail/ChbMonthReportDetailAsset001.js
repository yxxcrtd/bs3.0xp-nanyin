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
            assetCode: $('#assetCode').val()==null?null:$('#assetCode').val().join(","),
            //结束日（统计日期）
            enddate : $('#enddate').val(),
            dataFlag:'01'
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
            { title: "主键", field: 'posuuid',sortable:false,visible:false},
            { title: "一级分类", width : 120, field: 'reportType1',sortField:'reportType1'},
            { title: "二级分类", width : 120, field: 'reportType2',sortField:'reportType2'},
            { title: "三级分类", width : 120, field: 'reportType3',sortField:'reportType3'},
            { title: "四级分类", width : 120, field: 'reportType4',sortField:'reportType4'},
            { title: "产品代码", width : 120, field: 'prodId',sortField:'prodId'},
            { title: "产品名称", width : 120, field: 'prodName',sortField:'prodName'},
            { title: "是否合规新产品", width : 120, field: 'isFhxgcpStr',sortField:'isFhxgcp'},
            { title: "资产代码/科目号", width : 120, field: 'assetCode',sortField:'assetCode'},
            { title: "资产名称/科目名称", width : 120, field: 'assetName',sortField:'assetName'},
            { title: "资产起息日", width : 200, field: 'vdateStr', sortField:'vdate'},
            { title: "资产到期日", width : 200, field: 'mdateStr', sortField:'mdate'},
            { title: "最近开放计划结束日/行权日", width : 200, field: 'openplanStr',sortField:'openplan'},
            { title: "主体名称", width : 120, field: 'subjectName',sortField:'subjectName'},
            { title: "交易对手名称", width : 120, field: 'rivalParty',sortField:'rivalParty'},
            { title: "利率", width : 120, field: 'rate', type:'money',sortField:'rate'},
            { title: "币种", width : 120, field: 'ccyStr',sortField:'ccy'},
            { title: "行业一级分类", width : 120, field: 'industry1Str',sortField:'industry1'},
            { title: "行业二级分类", width : 120, field: 'industry2Str',sortField:'industry2'},
            { title: "行业三级分类", width : 120, field: 'industry3Str',sortField:'industry3'},
            { title: "行业四级分类", width : 120, field: 'industry4Str',sortField:'industry4'},
            { title: "份额", width : 120, field: 'delayPayAmtStr',sortField:'delayPayAmt'},
            { title: "成本", width : 120, field: 'costAmtStr',sortField:'costAmt'},
            { title: "应计利息", width : 120, field: 'intincexpStr', width : 120,sortField:'intincexp'},
            // { title: "利息调整", width : 120, field: 'intadjexpAddStr', width : 120,sortField:'intadjexpAdd'},
            { title: "公允价值变动", width : 120, field: 'floatIngplStr', width : 120,sortField:'floatIngpl'},
            { title: "减值", width : 120, field: 'lossAmtStr',sortField:'lossAmt'},
            { title: "期末余额", width : 120, field: 'assetvalueAStr',sortField:'assetvalueA'},
            { title: "1.12.9.a投向金融资产投资公司或其附属机构发行的私募股权投资基金", width : 120, field: 'privatefundFlagStr',sortField:'privatefundFlag'},
            { title: "1.13.7.a投向金融资产投资公司发行的私募资产管理产品", width : 120, field: 'privateprdFlagStr',sortField:'privateprdFlag'},
            { title: "信用债使用评级", width : 120, field: 'rating',sortField:'rating'},
            { title: "1.15.b 投向市场化债转股相关产品的部分", width : 120, field: 'bondtostockFlagStr',sortField:'bondtostockFlag'},
            { title: "1.15.c 逾期资产", width : 120, field: 'typeFinal',sortField:'typeFinal'},
            { title: "3.1.1 创业投资基金", width : 120, field: 'typeFinal',sortField:'typeFinal'},
            { title: "3.1.2 政府出资产业投资基金", width : 120, field: 'isGovernFund1Str',sortField:'isGovernFund1'},
            { title: "投管一级分类", width : 120, field: 'orgAssetType1Str',sortField:'orgAssetType1'},
            { title: "投管二级分类", width : 120, field: 'orgAssetType2Str',sortField:'orgAssetType2'},
            { title: "投管三级分类", width : 120, field: 'orgAssetType3Str',sortField:'orgAssetType3'}


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
                keyList.push(row.posuuid);
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
        $('#ChbMonthReportDetailAsset001_btn_search').click(function() {
            var param=getSearchParams();
            if(!param.enddate){
                Joyin.error("请选择统计日期进行查询！");
                return;
            }else{
                table.load();
            }
        });

        $('#ChbMonthReportDetailAsset001_btn_export').click(function() {
            exportExcel(0);
        });
        $('#ChbMonthReportDetailAsset001_btn_exportCheck').click(function() {
            exportExcel(1);
        });


    });
}());