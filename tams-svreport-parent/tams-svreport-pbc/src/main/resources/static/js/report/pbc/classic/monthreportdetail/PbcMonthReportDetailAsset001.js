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
            flag:'01'
        }
    };

    var laydate = layui.laydate;
    laydate.render({
        elem: '#enddate',
        type: 'month',
        format: 'yyyy-MM'

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
            { title: "五级分类", width : 120, field: 'reportType5',sortField:'reportType5'},
            { title: "六级分类", width : 120, field: 'reportType6',sortField:'reportType6'},

            { title: "产品代码", width : 120, field: 'prodid',sortField:'prodid'},
            { title: "产品名称", width : 120, field: 'prodName',sortField:'prodName'},
            { title: "资产代码/科目号", width : 120, field: 'assetCode',sortField:'assetCode'},
            { title: "资产名称/科目名称", width : 120, field: 'assetName',sortField:'assetName'},
            { title: "资产起息日", width : 200, field: 'vdateStr', sortField:'vdate'},
            { title: "资产到期日", width : 200, field: 'mdateStr', sortField:'mdate'},
            { title: "最近开放计划结束日/行权日", width : 200, field: 'openplanStr',sortField:'openplan'},
            { title: "主体名称", width : 120, field: 'subjectName',sortField:'subjectName'},
            { title: "交易对手名称", width : 120, field: 'rivalParty',sortField:'rivalParty'},
            { title: "利率", width : 120, field: 'rate', type:'money',sortField:'rate'},
            { title: "币种", width : 120, field: 'ccy',sortField:'ccy'},
            { title: "行业分类", width : 120, field: 'industry1Str',sortField:'industry1'},
            { title: "企业规模分类", width : 120, field: 'industry1Str',sortField:'industry1'},
            { title: "除回购和拆借外贷款所属地区", width : 120, field: 'industry1Str',sortField:'industry1'},
            { title: "成本", width : 120, field: 'costAmt', type:'money',sortField:'costAmt'},
            { title: "应计利息", width : 120, field: 'floatIngpl', type:'money',sortField:'floatIngpl'},
            { title: "利息调整", width : 120, field: 'intincexp', type:'money',sortField:'intincexp'},
            { title: "公允价值变动", width : 120, field: 'intadjexpAdd', type:'money',sortField:'intadjexpAdd'},
            { title: "减值", width : 120, field: 'lossAmt', type:'money',sortField:'lossAmt'},
            { title: "期末余额", width : 120, field: 'assetvalueA', type:'money',sortField:'assetvalueA'},
            { title: "投管一级分类", width : 120, field: 'orgAssetType1Str',sortField:'orgAssetType1'},
            { title: "投管二级分类", width : 120, field: 'orgAssetType2Str',sortField:'orgAssetType2'},
            { title: "投管三级分类", width : 120, field: 'orgAssetType3Str',sortField:'orgAssetType3'},
            { title: "资产负债剩余期限", width : 120, field: 'orgAssetType3Str',sortField:'orgAssetType3'},

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
                keyList.push(row.posuuid);
            });
            param.keyList = keyList;
            Joyin.exportExcel('export', param);
        }

    };


    function init(){
        // if(isMain=='01'){
        //     setTimeout(function() {
        //         if($('#sendStatus').val()=='02'||$('#sendStatus').val()=='03'){
        //             $('#submitDate').val(searchDataStart+' - '+searchDataEnd);
        //         }else{
        //             $('#submitDeadLine').val(searchDataStart + ' - ' + searchDataEnd);
        //         }
        //         table.load();
        //     }, 500);
        // }
        // //报送前检查调用数据检查
        // if(validKey){
        //     setTimeout(function() {
        //         table.load();
        //     },500)
        // }
    };
    init();

    $(function(){
        // 绑定按钮事件
        $('#PbcMonthReportDetailAsset001_btn_search').click(function() {
            table.load();
        });

        $('#PbcMonthReportDetailAsset001_btn_export').click(function() {
            exportExcel(0);
        });
        $('#PbcMonthReportDetailAsset001_btn_exportCheck').click(function() {
            exportExcel(1);
        });


    });
}());