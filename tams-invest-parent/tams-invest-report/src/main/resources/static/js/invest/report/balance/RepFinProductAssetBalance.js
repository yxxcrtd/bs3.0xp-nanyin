(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var objDiv= $("#RepFinProductAssetBalance_DIV");
        var param = $.serializeToObject('#searchForm');
        if(param.portfolioId){
            param.portfolioId = param.portfolioId.join(",");
        }
        if(param.finprodId){
            param.finprodId = param.finprodId.join(",");
        }
        return param;
    };
    /**
     * 定义数据表格
     */
    
    var table=$("#dataTable").joyintable({
            ajax: {
                url: 'findPage',//请求路径
                type: 'POST',//请求类型
                data: function() {
                    return getSearchParams();//获取请求参数
                }//ajax请求数据
            },
            checkbox:false,
            tree:true,
            treeId: 'childInfo',
            treeUpId: 'parentInfo',
            treeShowName: 'portfolioName',
            lazyload:true,
            searchOnInit: false,
            isMulti: true,
            progressBar: true,
        /**
         * 定义表头
         */
        cols:[[
             {field:'finprodId',visible:false} //金融产品代码
             ,{field:'portfolioId',visible:false} //金融产品代码
            ,{field:'portfolioName',  title: 'RepFinProductAssetBalance.portfolioName',width:200}//资产代码
            ,{field:'finprodType2Name',  title: 'RepFinProductAssetBalance.finprodType2',width:100}//资产简称
            ,{field:'type4Name',  title: 'RepFinProductAssetBalance.type4',width:100}//资产简称
            ,{field:'type5Name',  title: 'RepFinProductAssetBalance.type5',width:100}//资产简称
            ,{field:'finprodMarketId',  title: 'RepFinProductAssetBalance.finprodId',width:100}//资产简称
            ,{field:'finprodName',  title: 'RepFinProductAssetBalance.finprodName',width:100}//资产简称
            ,{field:'firstAmount',  title: 'RepFinProductAssetBalance.firstAmount',type:'number',width:100}//资产简称
            ,{field:'amount',  title: 'RepFinProductAssetBalance.amount',type:'number',width:100}//资产简称
            ,{field:'tradeDate',  title: 'RepFinProductAssetBalance.tradeDate',type:'date',width:100}//资产简称
            ,{field:'vdate',title:'RepFinProductAssetBalance.vdate',type:'date',width:150}//起息日
            ,{field:'mdate',title:'RepFinProductAssetBalance.mdate',type:'date',width:150}//到期日
            ,{field:'basisName',  title: 'RepFinProductAssetBalance.basis',width:100}//资产简称
            ,{field:'rangeYield',  title: 'RepFinProductAssetBalance.rangeYield',type:'number',width:100}//资产简称
            ,{field:'mYield',  title: 'RepFinProductAssetBalance.mYield',type:'number',width:100}//资产简称
            ,{field:'costPrice',  title: 'RepFinProductAssetBalance.costPrice',type:'number',width:100}//资产简称
            ,{field:'closePrice',  title: 'RepFinProductAssetBalance.closePrice',type:'number',width:100}//资产简称
            ,{field:'issuer',  title: 'RepFinProductAssetBalance.issuer',width:100}//资产简称
            ,{field:'ccyName',  title: 'RepFinProductAssetBalance.ccy',width:100}//资产简称
            ,{field:'deptCodeName',  title: 'RepFinProductAssetBalance.deptCode',width:100}//资产简称
        ]]
    });
    
    
    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };
    
    var finprodIdChange = function(){
        var finprodId = $('#finprodId').val();
        if(finprodId==''||finprodId==null){
            $('#showLevel').prop('disabled',false);
        }else{
            $('#showLevel').val("04").trigger('change');
            $('#showLevel').prop('disabled',true);
        }
    }
    /**
     * 画面加载完成
     */
    $(function() {
        $('#finprodId').change(finprodIdChange);
        //导出
        $('#RepFinProductAssetBalance_btn_export').click(exportExcel);
        //初始化查询
        $('#RepFinProductAssetBalance_btn_search').click(function() {
            if($('#queryDate').val()==''){
                Joyin.error("请输入日期。");
                return;
            }
            if($("#showLevel").val()==''){
                Joyin.error("请输入展示层级。");
                return;
            }
            table.load();
        });
    });
}());