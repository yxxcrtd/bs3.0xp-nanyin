$(function () {

    /**
     * 获取子组合间资金调配查询参数
     */
    var getFundsAllocateSearchParams = function() {
    	var portfolioId = $('#portfolioId').val();
        if(portfolioId && portfolioId != ''){
        	portfolioId = portfolioId.join(',');
        }else {
        	portfolioId = null;
        }

        var subPortfolioId = $('#subPortfolioId').val();
        if(subPortfolioId && subPortfolioId != ''){
        	subPortfolioId = subPortfolioId.join(',');
        }else {
        	subPortfolioId = null;
        }

        return {
        	portfolioId: portfolioId,//组合代码
        	subPortfolioId: subPortfolioId,//子组合代码
        	settleDateBegin: $('#settleDate').startDate(),//调配日期
        	settleDateEnd: $('#settleDate').endDate()
        }
    };

    /**
     * 定义子组合间资金调配数据表格
     */
    var fundsAllocateTable=$("#fundsAllocate-table").joyintable({
        ajax: {
            url: 'findFundsAllocatePage',
            type: 'POST',
            data: function() {
                return getFundsAllocateSearchParams();
            }

        },
        dblclick:function(table, data) {
                Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlsubfundsmange/PtlSubFundsAllocate002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                	totleTradeId:data.totleTradeId,portfolioId:data.portfolioId,parentPortfolioId:data.portfolioId,portfolioName:data.portfolioName});//主键组合代码
        },
        cols: [
           {field:'totleTradeId', title: 'PtlSubFundsAllocate001.totleTradeId',visible:false},//汇总交易编号
           {field:'tradeId', title: 'PtlSubFundsAllocate001.tradeId',visible:false},//交易编号
           {field:'portfolioId', title: 'PtlSubFundsAllocate001.portfolioId',width:160},//组合代码
           {field:'portfolioName', title: 'PtlSubFundsAllocate001.portfolioName',width:180},//组合名称
           {field:'subPortfolioId', title: 'PtlSubFundsAllocate001.subPortfolioId',width:160},//子组合代码
           {field:'subPortfolioName', title: 'PtlSubFundsAllocate001.subPortfolioName',width:180},//子组合名称
           {field:'disPercent',title: 'PtlSubFundsAllocate001.disPercent',width:160},//继承比例
           {field:'remark',title: 'PtlSubFundsAllocate001.remark',width:160},//备注
           {field:'settleDate',title: 'PtlSubFundsAllocate001.settleDate',width:160},//调配日期
           {field:'ccy', title: 'PtlSubFundsAllocate001.ccy',visible:false},//币种
           {field:'tradeAmt', title: 'PtlSubFundsAllocate001.tradeAmt',width:160,type:"money"},//调配金额
           {field:'deployrRemark', title: 'PtlSubFundsAllocate001.deployrRemark',width:160},
           {field:'parentPortfolioId', width:160,visible:false}
        ],
    });

    /**
     * 事件绑定：子组合间资金调配
     */
    var fundsAllocateBind = function() {

        /** 事件绑定: 筛选按钮 */
        $("#PtlSubFundsAllocate001_btn_filter").click(function(event){
            event.stopPropagation();// 阻止事件冒泡
            $("#c_fundsAllocateForm").toggle();
        });

        /** 事件绑定: 子组合间资金调配-查询 */
        $("#PtlSubFundsAllocate001_btn_search").click(function(){
        	fundsAllocateTable.load();

        });

        //绑定导出按钮
        $('#PtlSubFundsAllocate001_btn_export').click(function() {
        	if (fundsAllocateTable != null && fundsAllocateTable != '') {
	            var selArr = fundsAllocateTable.getAllCheckedRows();
	            var datas = JSON.stringify(selArr);
	        }
	        if (!selArr || selArr.length == 0) {
	            var paramObj = getFundsAllocateSearchParams();
	            Joyin.exportExcel('exportPtlSubFundsAllocate', paramObj);
	        } else {
	            Joyin.exportExcel('exportPtlSubFundsAllocate', {
	                datas : datas
	            });
	        }
        });

    }

    /**
     * 重置 Tab
     * conditionArea : 条件区
     * table : 关联的表格
     */
    var reset = function(conditionArea, table){
        $(conditionArea).find("input").val("");
        $(conditionArea).find("select").val("").trigger("change");// select清空
    }
    /**
     * 页面加载完后执行
     */
    $(function() {

    	fundsAllocateBind();//事件绑定 - 子组合间资金调配
    });




    var getIncomeStatisticsSearchParams = function() {
        return {
            prodId:  $.trim($('#prodId').val()),//产品代码
            vdateStart:$('#vdate').startDate(),
            vdateEnd:$('#vdate').endDate(),
            distributionstatus: $('#distributionstatus').val()//分配状态
        }
    };


    var incomeStatisticsTable = $("#incomeStatistics-table").joyintable({

        ajax: {
            url: 'findIncomeStatisticsPage',
            type: 'POST',
            data: function() {
                return getIncomeStatisticsSearchParams();
            }

        },
        /*双击到详情页面*/
        dblclick:function(table, data) {
        	// 子节点无法双击打开
        	if('02' != data.hasChildren){
        		return;
        	}
        	var object = {prodId: data.prodId,
					ccy:data.ccy,
					seqNo:data.seqNo,
					seqNoList:data.seqNoList,
					portfolioId:data.otherPortfolioId,
					tradeAmt:data.tradeAmt === undefined ? "0" : data.tradeAmt,
					totleTradeId:(data.tradeId === undefined ? "": data.tradeId),
					workStatus:(data.workStatus === undefined ? "": data.workStatus),
					workflowId:(data.workflowId === undefined ? "": data.workflowId),
					pageModel:PAGE_MODEL_REFERENCE};// page_model:查看

            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlsubfundsmange/PtlSubFundsMange002/init",
                    "85%", "85%", object);
        },
        tree:true,
        treeId: 'childInfo',
        treeUpId: 'parentInfo',
        treeShowName: 'prodName',
        lazyload:true,
        loadonce: true,
        page: false,
        searchOnInit: false,
        cols: [
               {field:'workStatus', title: '有效状态'},
               {field:'tradeId', title: '交易编号'},
               {field:'settleDate',title: '交易日',sortField:'amtFlag',type:'date'},
               {field:'prodId',title: '产品代码'},
               {field:'prodName', title: '产品名称'},
               {field:'tradeAmt', title: '交易金额',type:"money"},
               {field:'otherPortfolioId', title: '组合代码' ,visible:false},
               {field:'portfolioId', title: '子组合代码'},
               {field:'portfolioName', title: '子组合名称'},
               {field:'allotAmt', title: '分配金额',type:"money"},
               {field:'ccy', title: '币种'},
               {field:'seqNo', title: '流水号' ,visible:false },
               {field:'seqNoList', title: '流水号集合' ,visible:false },
               {field:'workflowId', title: '工作流代码' ,visible:false },
               {field:'hasChildren', title: '是否有子节点',visible:false }
            ],
    });


	$('#PtlSubFundsMange001_btn_search').click(function() {
        //产品代码
        var prodId= $('#prodId').val();
        //产品名称
        var prodName= $('#prodName').val();
        //交易日
        var vdate= $('#vdate').val();
        //分配状态
        incomeStatisticsTable.load();
    });


    /** 事件绑定: 筛选按钮 */
    $("#PtlSubFundsMange001_btn_filter").click(function(event){
        event.stopPropagation();// 阻止事件冒泡
        $("#c_incomeStatisticsForm").toggle();
    });

    /** 事件绑定: 置空按钮 */
    $("#PtlSubFundsMange001_btn_reset").click(function(event){
        reset( '#incomeStatisticsForm', incomeStatisticsTable);
    });

    /** 事件绑定: 资金分配按钮*/
    $('#PtlSubFundsMange001_btn_add').click(function() {
    	if(incomeStatisticsTable!=null && incomeStatisticsTable!=''){
            var selArr = incomeStatisticsTable.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
        }

    	// 有流水号集合的数据，该数据才可以分配
    	var selArrWithSeqNoList = new Array();
        if (!selArr || selArr.length == 0) {//判断是否选择，如果没有则报错COMMON_E_0026
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }else if(selArr.length >= 1){
        	var seqNoList;
        	for(j = 0; j < selArr.length; j++) {
        		seqNoList = selArr[j].seqNoList;
        		if(seqNoList != undefined && seqNoList.length > 0){
        			selArrWithSeqNoList.push(selArr[j]);
        		}
        	}
        	if(selArrWithSeqNoList.length != 1 || "待处理" != selArrWithSeqNoList[0].workStatus){
        		Joyin.error(JoyinUtil.getMessage("PTL_COM_E_0016"));
        		return;
        	}
        }
    	var object = {prodId: selArrWithSeqNoList[0].prodId,
    					ccy:selArrWithSeqNoList[0].ccy,
    					seqNo:selArrWithSeqNoList[0].seqNo,
    					seqNo:selArrWithSeqNoList[0].seqNoList.join(","),
    					portfolioId:selArrWithSeqNoList[0].otherPortfolioId,
    					tradeAmt:selArrWithSeqNoList[0].tradeAmt,
    					pageModel:PAGE_MODEL_NEW};
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlsubfundsmange/PtlSubFundsMange002/checkadd', object, function(result) {//数据验证
        	Joyin.dialog(JoyinUtil.getLabel("PtlSubFundsMange002"),
                    basePath+ "invest/portfolio/ptlsubfundsmange/PtlSubFundsMange002/init",
                        "85%", "85%", object,function(){
                        	incomeStatisticsTable.reload();
                        });
        });
    });



$('#productDimension').change(
        function(){
            if($('#productDimension').val() == '03'){
                $("#productId").attr("disabled",false);
                $("#productId").val("").trigger("change");// select清空

            }else{
                $("#productId").attr("disabled",true);
                $("#productId").val("").trigger("change");// select清空
            }


        }

);

$('#PtlSubFundsAllocate001_btn_add').click(function() {
    Joyin.dialog(JoyinUtil.getLabel("PtlSubFundsAllocate002"),
            basePath+ "invest/portfolio/ptlsubfundsmange/PtlSubFundsAllocate002/init",
                "98%", "90%", {pageModel:PAGE_MODEL_NEW});
});


/**
 * 导出
 */
var exportExcel = function() {
	if (incomeStatisticsTable != null && incomeStatisticsTable != '') {
        var selArr = incomeStatisticsTable.getAllCheckedRows();
        var datas = JSON.stringify(selArr);
    }if (!selArr || selArr.length == 0) {
    	var paramObj =getIncomeStatisticsSearchParams();//获取参数
    	Joyin.exportExcel('export', paramObj);
    } else {
        Joyin.exportExcel('export', {
            datas : datas
        });
    }
};

$('#PtlSubFundsMange001_btn_export').click(function() {
    exportExcel();//导出
});

});