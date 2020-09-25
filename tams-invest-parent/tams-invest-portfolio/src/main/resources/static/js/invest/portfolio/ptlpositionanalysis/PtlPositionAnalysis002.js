$(function () {
    var dataTable;
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var result = $.serializeToObject('#submitForm');
        if ($("#searchDate").val()) {
            result.searchDateStart = $("#searchDate").startDate();
            result.searchDateEnd = $("#searchDate").endDate();
        }
        if($("#portfolioId").val() == null || $("#portfolioId").val() == ''){
            result.portfolioId = $("#portfolioId").val();
        }
        else{
            result.portfolioId = $("#portfolioId").val().join(',');
        }
        if($("#profitType").val() == null || $("#profitType").val() == ''){
            result.profitType = $("#profitType").val();
        }
        else{
            result.profitType = $("#profitType").val().join(',');
        }
	    if($("#prodManager").val() == null || $("#prodManager").val() == ''){
            result.prodManager = null;
        }
        else{
            result.prodManager = $("#prodManager").val().join(',');
        }

        if($("#finprodMarketId").val() == null || $("#finprodMarketId").val() == ''){
            result.finprodMarketId = null;
        }
        else{
            result.finprodMarketId = $("#finprodMarketId").val().join(',');
        }
        if($("#deepLevel").val() == null || $("#deepLevel").val() == ''){
            result.deepLevel = null;
            Joyin.error("请选择下钻层级！");
            return false;
        }
        else{
            result.deepLevel = $("#deepLevel").val();
        }
        if($("#displayType").val() == null || $("#displayType").val() == ''){
            result.displayType = null;
            Joyin.error("请选择展示方式！");
            return false;
        }
        else{
            result.displayType = $("#displayType").val();
        }

        return result;
    };
    var cols=new Array();
    getTableCols();
    dataTable =$("#dataTable").joyintable({
         ajax: {
             url: 'findPage',
             type: 'POST',
             data: function() {
                return getPageParams();
             }
         },
        checkbox:false,
        tree:true,
        treeId: 'childInfo',
        treeUpId: 'parentInfo',
        treeShowName: 'typeName',
        lazyload:true,
        searchOnInit: false,
        cols: cols
        ,done: function() {
    }
    });

    //展示模式切换时候重新加载表格列
    $("#displayType").bind("change", function () {

        var displayValue= $("#displayType").val();
        $("#dataTableDiv") .empty();
        $("#dataTableDiv").append("<table id=\"dataTable\"></table>");
        if(displayValue=='01')
        {
           //重新加载表格
            getTableCols();
             dataTable=$("#dataTable").joyintable({
                ajax: {
                    url: 'findPage',
                    type: 'POST',
                    data: function() {
                        return getPageParams();
                    }
                },
                checkbox:false,
                tree:true,
                treeId: 'childInfo',
                treeUpId: 'parentInfo',
                treeShowName: 'portfolioId',
                lazyload:true,
                searchOnInit: false,
                cols: cols
                ,done: function() {
                }
            });
        }
        else
        {

            getTableCols();
             dataTable=$("#dataTable").joyintable({
                ajax: {
                    url: 'findPage',
                    type: 'POST',
                    data: function() {
                        return getPageParams();
                    }
                },
                checkbox:false,
                tree:true,
                treeId: 'childInfo',
                treeUpId: 'parentInfo',
                treeShowName: 'typeName',
                lazyload:true,
                searchOnInit: false,
                cols: cols
                ,done: function() {
                }
            });
        }
        //modified by liuwei for 深农商:修改不输入下钻层级时同时有查询日期的校验 20200817 start
        var paramObj = getPageParams();
        if(paramObj!=false){
            dataTable.load();
        }
        //modified by liuwei for 深农商:修改不输入下钻层级时同时有查询日期的校验 20200817 end
    });


    /**
     *加载动态表头
     */
    function getTableCols()
    {
        var parm={};
        parm.displayType=$("#displayType").val();
        parm.type=1;
        Joyin.ajaxSync(
            basePath + "invest/portfolio/ptlpositionanalysis/PtlPositionAnalysis002/getTableCols",parm,
            function(result) {
                if(result!="") {
                    var dataTableCols = JSON.parse(result);

                    if (dataTableCols.length > 0) {
                        for (var i = 0; i < dataTableCols.length; i++) {
                            cols[i] = {
                                field: dataTableCols[i].fieldMsg,
                                title: dataTableCols[i].title,
                                sortable: dataTableCols[i].sortable,
                                type: dataTableCols[i].type,
                                width: '20%'
                            };
                        }
                    }
                }

            }
        );
    }
    /**
     * 导出
     */
    var exportExcel = function() {
        //modified by liuwei for 深农商:修改不输入下钻层级时同时有查询日期的校验 20200817 start
        if(!$("#searchDate").val()){
            Joyin.error("请选择查询日期！");
            return;
        }
        var paramObj = getPageParams();//获取参数
        if(paramObj!=false){
            Joyin.exportExcel('export', paramObj);//导出
        }
        //modified by liuwei for 深农商:修改不输入下钻层级时同时有查询日期的校验 20200817 end
    };
    $(function () {
        $("#PtlPositionAnalysis002_btn_search").click(function () {
            //modified by liuwei for 深农商:修改不输入下钻层级时同时有查询日期的校验 20200817 start
            if(!$("#searchDate").val()){
                Joyin.error("请选择查询日期！");
                return;
            }
            var paramObj = getPageParams();
            if(paramObj!=false){
                dataTable.load();
            }
            //modified by liuwei for 深农商:修改不输入下钻层级时同时有查询日期的校验 20200817 end
        });
         //绑定导出按钮
        $('#PtlPositionAnalysis002_btn_export').click(function() {
          //  Joyin.alert("功能暂未实现！");
            exportExcel();//导出
        });
        //指标设置
        $('#PtlPositionAnalysis002_btn_target').click(function() {
            Joyin.dialog("指标设置",
                JoyinUtil.getRealPath("invest/portfolio/ptlpositionanalysis/PtlPositionAnalysis003/init"),
                "80%", "80%", {
                    pageModel: PAGE_MODEL_NEW
                },function () {
                  //设置指标成功后重新刷新列
                    $("#displayType").trigger("change");
                });
        });
    })
});
