/**
 * 持仓信息（右上）
 */
(function(){
    // 定制化配置信息控制页面展示====================================================
    const ZLGL = $(".ZLGL").attr("title");
    const ZYQGL = $(".ZYQGL").attr("title");
    if(ZLGL !== 'true' || ZYQGL !== 'true'){
        if(ZLGL === 'true'){
            $(".ZYQGL").hide();
            $(".ZLGL").show().trigger("click");
        }else if (ZYQGL === 'true'){
            $(".ZYQGL").show().trigger("click");
            $(".ZLGL").hide();
        }
    }
    $(".layui-tab").show();
    // ==========================================================================
	
    function getPositionsSearchParams(){
        var data = $.serializeToObject('#searchForm_cc');
        data.portfolioId = data.portfolioId ? data.portfolioId.join(','):'';
        if(data.entrustDate){
        	data.startDate= $("#entrustDate").startDate();
        	data.endDate= $("#entrustDate").endDate();
        }
        if(data.entrustStatus =='00'){
        	entrustStatusIsAll = YES_OR_NO_Y;
        	data.entrustStatus = "";
        }
        data.isDvp = true;
        return data;
    }
    
	function getZqzyPositionParams() {
		var data = $.serializeToObject('#searchForm_zqzy');
        data.portfolioId = data.portfolioId_zqzy ? data.portfolioId_zqzy.join(','):'';
        data.secAcctId = data.secAcctId ? data.secAcctId.join(','):'';
        data.zyqk = data.zyqk ? data.zyqk.join(','):'';
        data.isDvp = false;
		return data;
	}

    /**
     * 定义数据表格
     */
    var zlglTable = $("#zlglTable").joyintable({ 
        ajax: {
            url: 'findZlglPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getPositionsSearchParams();//获取请求参数
            }//ajax请求数据
        },
        page: true,
        dblclick:function(table, data) {
            if(data.entrustStatus === "05" || data.entrustStatus === "07"){
                parent.EtrIbRepoinfo002.dbclickEtr(data);
            }
        },
        /**
         * 定义表格名称
         */
        checkbox:true,
        searchOnInit:true,
        cols:[
            {field:'entrustId', title: 'EtrIbBondinfo001.entrustId', sortField:'entrustId',width : 100}//指令编号
            ,{field:'entrustStatusName', title: 'EtrIbBondinfo001.entrustStatus', sortField:'entrustStatus',width : 100}//有效状态
            ,{field:'invManagerId', title: 'EtrInvestBondInfo001.invManagerId', sortField:"invManagerId", width : 100}
            ,{field:'parentPortfolioId', title: '组合代码', width: 100, sortField:"parentPortfolioId",visible:false}
            ,{field:'portfolioId', title: 'EtrInvestBondInfo001.portfolioId', width: 100,
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrInvestBondInfo001.portfolioName', width:180,sortField:"portfolioName"}
            ,{field:'repoTerm', title: 'EtrIbRepo001.repoTerm',type:'number', digit:0, width : 150}
            ,{field:'termType', visible:false}
            ,{field:'termTypeName', title: 'EtrIbRepo001.termType', width:100,sortField:"termType"}
            ,{field:'ps', visible:false}
            ,{field:'psName', title: 'EtrIbBondinfo001.ps', sortField:'ps',width : 100}
            ,{field:'entrustAmt', title: 'EtrIbRepo001.entrustAmt',type:'money', width : 150}
            ,{field:'priceModelName', title: 'EtrInvestBondInfo001.priceModel', width:100,sortField:"priceModel"}
            ,{field:'price', title: 'EtrIbRepo001.price',type:'number', digit:4, width : 100}
            ,{field:'settleSpreedName', title: 'EtrInvestBondInfo002.settleSpreed', width : 100,sortField:"settleSpreed"}
            ,{field:'vdateStr', title: 'EtrInvestBondInfo001.vdate', width:100,/*type:'date',*/sortField:"vdate"}
            ,{field:'mdateStr', title: 'EtrInvestBondInfo001.mdate', width:100,/*type:'date',*/sortField:"mdate"}
            ,{field:'finishAmt',type:'money', title: 'EtrIbRepo001.finishAmt', width:100,sortField:"finishAmt"}
            ,{field:'transactioncom', title: 'EtrInvestBondInfo001.transactioncom',render : function(data, type, row, meta){
                    return "<div class='layui-progress layui-progress-big' style='margin-top:15px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.transactioncom+"%'><span class='layui-progress-text'>"+row.transactioncom+"%</span></div></div>";
                }, width:100,sortable:false}
            ,{field:'remark', title: 'EtrInvestBondInfo001.remark', width:180,sortable:false}
        ]
    });

    //数字转千分符
    function thousandBitSeparator(num) {
    return num && num
        .toString()
        .replace(/(\d)(?=(\d{3})+\.)/g, function($0, $1) {
            return $1 + ",";
        });
    }

    /**
     * 组合代码链接
     */
    zlglTable.bind('click','.portfolioIdLink', function(row) {
        Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
            basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
            "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                portfolioId:row.portfolioId});//主键组合代码
    });

    /**
	 * 刷新列表
	 */
    var refreshzlglTable = function(){
        zlglTable.table ? zlglTable.reload() : zlglTable.load();
    }
    /**
     * 刷新债券质押列表
     */
    var refreshZqzyzlglTable = function(){
        zyqglTable.table ? zyqglTable.reload() : zyqglTable.load();
    }

    var getPositionsExportExcel = function () {
        Joyin.exportExcel('export', getPositionsSearchParams());
    };

    var getZqzyPositionExportExcel = function () {
        Joyin.exportExcel('export', getZqzyPositionParams());
    };
    var zyqglTable = null;
    if(repo_pledge_model =='03'){
    	 /**
         * 定义数据表格
         */
        zyqglTable = $("#zyqglTable").joyintable({ 
            ajax: {
                url: 'findZlglPositionPage',//请求路径
                type: 'POST',//请求类型
                data: function() {
                    return getZqzyPositionParams();//获取请求参数
                }//ajax请求数据
            },
            dblclick:function(table, data) {
            },
            /**
             * 定义表格名称
             */
            checkbox: true,
            searchOnInit:true,
            cols:[
                {field:'portfolioId', title: 'EtrInvestBondInfo001.portfolioId', width: 100,
                    render : function(data, type, row, meta) {
                        return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                    },sortField:"portfolioId"
                }
                ,{field:'portfolioName', title: 'EtrInvestBondInfo001.portfolioName', width:180,sortField:"portfolioName"}
                ,{field:'secAcctId', visible:false}
                ,{field:'secAcctStr', title: 'EtrIbRepo001.secAcctId', width:150,sortField:"finprodAbbr"}
                , {field: 'finprodMarketId', title: 'EtrInvestBondInfo001.finprodId',  width:150,sortField: 'finProdId'}
                , {field: 'finProdName', title: 'EtrInvestBondInfo001.finprodAbbr',  width:150,sortField: 'finProdName'}
                , {field: 'posStrdPrinAmt', title: 'EtrIbRepo001.posStrdPrinAmt', type: 'money', width:150}
                , {field: 'pledStrdPrinAmt', title: 'EtrIbRepo001.pledStrdPrinAmt1', type: 'money', width:150}
                , {field: 'gradeResultName', title: 'EtrIbRepo001.gradeResult', width:150, sortField: 'gradeResult'}
                , {field: 'mdateStr', title: 'EtrIbRepo001.mdate', width:150,/*type:'date'*/}
                , {field: 'lastPeriod', title: 'EtrIbRepo001.lastPeriod', width:150}
                , {field: 'registOrgName', title: 'EtrIbRepo001.registOrg', width:150}
                , {field: 'pledgeStockName', title: '质押券库', width:150, visible: function() {
					return repo_pledge_model=='03'?true:false
				}()}
                , {field: 'pledgeStockId', visible: false}
                ],
            //计算总可质押券面总额
            done : function(allData, table){
                var totalPledStrdPrinAmt = '0';
                if (allData && allData.length > 0){
                    $.each(allData, function(id, value){
                    	totalPledStrdPrinAmt = JoyinMath.doAdd(totalPledStrdPrinAmt, value.pledStrdPrinAmt, 2, JoyinMath.ROUND_HALF_UP);
                    });          	
                }
               // $('#totalFinProdBalance').attr("value",thousandBitSeparator(totalFinProdBalance));
                $('#pledStrdPrinAmt').attr("value",thousandBitSeparator(totalPledStrdPrinAmt));
            }
        });
        
        /**
         * 组合代码链接
         */
        zyqglTable.bind('click','.portfolioIdLink', function(row) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:row.portfolioId});//主键组合代码
        });
    }

    /**
	 * 画面加载完成
	 */
    $(function() {
    	
    	// 导入按钮点击事件
        $('#EtrIbRepo001_btn_import').click(function() {
        	window.parent.importEtr();
        });
        /**
         * 关闭委托
         */
        $('#EtrIbRepo001_btn_close').click(function() {
            if (zlglTable != null && zlglTable != '') {
                var selArr = zlglTable.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                for(var index in selArr){
                    if(selArr[index].entrustStatus == '08'){
                        Joyin.error("流程中的数据不允许关闭");
                        return ;
                    }
                }
                var object = {selectedItem: selArr, operateType:"close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/entrustCheckCloseList', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo001/close', object, function(result) {
                        	zlglTable.reload();
                        });
                    });
                });
             }
        });
        /**
         * 撤销
         */
        $('#EtrIbRepo001_btn_revoke').click(function() {
            if (zlglTable != null && zlglTable != '') {
                var selArr = zlglTable.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                for(var index in selArr){
                    if(selArr[index].entrustStatus == '08'){
                        Joyin.error("流程中的数据不允许撤回");
                        return ;
                    }
                }
                var object = {selectedItem: selArr, operateType:"recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrrepo/EtrRepo001/recall', object, function(result) {
                        	zlglTable.reload();
                        });
                    });
                });
             }
        });
        
        $(".cc-transfer-form #EtrIbRepo001_btn_search").click(function () {
//            zlglTable.load();
        	$('.sgePortDiv').find('span.select2-container--default').attr('style', 'width: 210px !important');
            $('#dayOfferSelectorConditionFilter001').hide();
    		if($("#selectorConditionFilter001").css("display")=="none"){
				//clearBasicCondition();
				$("#selectorConditionFilter001").show();
			}
			else{
				//clearBasicCondition();
				$("#selectorConditionFilter001").hide();
			}
        })
        $(".transfer-form #EtrIbRepo001_btn_search").click(function () {
            zyqglTable.load();
        })
        
        /**
    	 * 委托指令查询弹出框查询
    	 */
    	$('#EtrIbRepo001_btn_realseach').click(function() {
//    		fetsBondDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
    		zlglTable.table ? zlglTable.reload() : zlglTable.load();
    		$("#selectorConditionFilter001").hide();
        });
        
        $('#EtrIbRepo001_cc_search').click(function() {
//    		fetsBondDealQuotedPrice001_entrustOrderTable_loading = layui.layer.open({type:3});
    		zlglTable.table ? zlglTable.reload() : zlglTable.load();
    		$("#selectorConditionFilter001").hide();
        });

		/**
    	 * 委托指令查询弹出框重置
    	 */
    	$('#EtrIbRepo001_btn_seachReset').click(function() {
			$("#portfolioId").empty();
			$("#finprodId").empty();
			$("#ps").val('').trigger("change");
			$("#settleSpreed").val('').trigger("change");
			$("#invManagerId").empty();
			$("#entrustId").empty();
			$("#entrustDate").val("");
        });

    	/**
    	 * 委托指令查询弹出框关闭
    	 */
    	$('#EtrIbRepo001_btn_seachClose').click(function() {
			$("#selectorConditionFilter001").hide();
        });
        
        $(".cc-transfer-form #EtrIbRepo001_btn_export").click(getPositionsExportExcel)
        $(".transfer-form #EtrIbRepo001_btn_export").click(getZqzyPositionExportExcel)
        
        /**
         * 绑定自动入库设置按钮事件
         */
        $('#EtrIbRepo001_btn_priority').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("自动入库设置"), basePath
                + "invest/entrust/etribrepo/EtrIbRepoRk/init", "99%", "99%", {
                	pageModel: PAGE_MODEL_NEW
            	}, function () {
            });
        });
        
        // 绑定入库按钮事件
        $('#EtrIbRepo001_btn_in').click(function () {
            var param = {};
            param.portfolioId = $("#portfolioId").val();
            param.tradePlace = $("#tradePlace").val();
            param.secAcctId = $("#secAcctId").val();
            Joyin.dialog(JoyinUtil.getLabel("银行间质押券入库"), '../EtrIbRepoLib/init', '98%', '98%', param);
        });
        // 绑定出库按钮事件
        $('#EtrIbRepo001_btn_out').click(function () {
            //
            var selArr;
            if (zyqglTable != null && zyqglTable != '') {
                selArr = zyqglTable.getAllCheckedRows();
            }
            // 列表中未勾选
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }

            if (selArr.length > 20) {
                Joyin.error("出库券最多不能同时操作20个以上");
                return;
            }
            // 出库操作,是否确认对所选债券进行出库操作？
            Joyin.confirm(JoyinUtil.getMessage('EtrStandardBond001.outSave_001'), function() {
            	var data = {};
        		data.selectedItem = selArr;
                Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtrIbRepo001/outSave', data, function(result) {
                	zyqglTable.reload();
                });
            });
        });
        
        /**
         * 定时刷新
         */
        setInterval(function(){
            refreshzlglTable();
            if(repo_pledge_model =='03'){
            	 refreshZqzyzlglTable();
            }
        },300000);
    });

}());