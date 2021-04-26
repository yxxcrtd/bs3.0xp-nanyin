$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var obj =  $.serializeToObject('#searchForm');
        if ($("#TrdInvestDealQuery001_finprodType2").val()) {
            obj.finprodType2 = $("#TrdInvestDealQuery001_finprodType2").val().join(",");
        }else{
            obj.finprodType2='';
        }
        obj.finprodMarketId = $('#finprodMarketId').val();
        if ($("#caseType").val()) {
            obj.caseType = $("#caseType").val()
                .join(",");
        }else{
            obj.caseType ='';
        }
        obj.registDateStart = $('#registDate').startDate();
        obj.registDateEnd = $('#registDate').endDate();
        obj.exportType = 'N';
        return obj;
    };
    var getSearchParamsTwo = function() {
        var obj =  $.serializeToObject('#searchFormTwo');
        if ($("#TrdInvestDealQuery001_finprodType2_two").val()) {
            obj.finprodType2 = $("#TrdInvestDealQuery001_finprodType2_two").val()
                .join(",");
        }else{
            obj.finprodType2 ='';
        }
        obj.finprodMarketId = $('#finprodMarketIdTwo').val();
        if ($("#caseTypeTwo").val()) {
            obj.caseType = $("#caseTypeTwo").val()
                .join(",");
        }else{
            obj.caseType ='';
        }
        obj.registDateStart = $('#registDateTwo').startDate();
        obj.registDateEnd = $('#registDateTwo').endDate();
        obj.exportType = 'Y';
        return obj;
    };
    /**
     * 定义逾期未结清数据表格
     */

    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'selectPageData',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        /**
         * 定义表头
         */
        sortfont:true,
        cols:[
            {field:'cashTypeStr',sortField:'cashType',width:120,title: '类型'} //类型
            ,{field:'finprodTypeName',sortField:'finprodTypeName', width:100,title:'资产类型'}//证券账户
            ,{
                field: 'finprodMarketId', title: '资产代码', width: '10%',
                render: function (data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>" + row.finprodMarketId + "</ a>";
                }, sortField: "finprodMarketId"
            }
            ,{field:'finprodName',sortField:'finprodName',width:150,title:'资产名称'}//资产名称
            ,{field:'secManageAcctName',sortField:'secManageAcctName', width:100,title:'证券托管户'}//证券账户
            ,{field:'chIldName',sortField:'chIldName', width:100,title:'invest.channelId'}//证券账户
            ,{field:'vdate',sortField:'vdate',width:100,title:'计息起始日',type:'date'}//计息起始日
            ,{field:'mdate',sortField:'mdate',width:100,title:'计息结束日',type:'date'}//交易方向
            ,{field:'endPayDate',sortField:'endPayDate',width:100,title:'最终支付日',type:'date'}//登记日
            ,{field:'theoryAmt',sortField:'theoryAmt',width:120,title:'理论本息总额', type:'number',digit:2}//除权日
            ,{field:'fpriceAmt',sortField:'fpriceAmt',width:120,title:'实收本息总额', type:'number',digit:2}//到账日
            ,{field:'theoryPrin',sortField:'theoryPrin',width:100,title:'未支付本金',type:'number', digit:2}//除权日
            ,{field:'theoryInt',sortField:'theoryInt',width:100,title:'未支付利息',type:'number', digit:2}//到账日
            ,{field:'theoryPenaltyInt',sortField:'theoryPenaltyInt',width:120,title:'未付罚息总额',type:'number', digit:2}//除权日
            ,{field:'penaltyIntamt',sortField:'penaltyIntamt',width:120,title:'实收罚息总额',type:'number', digit:2}//到账日
            ,{field:'isAccrPenaltyIntStr',sortField:'isAccrPenaltyInt',width:120,title:'是否计提罚息'}//资产名称
            ,{field:'penaltyBaseTypeStr',sortField:'penaltyBaseType',width:120,title:'罚息计息本金'}//资产名称
            ,{field:'fxYield',sortField:'fxYield',width:80,title:'罚息利率%', type:'number',digit:4}//资产名称
            ,{field:'fxmdate',sortField:'fxmdate',width:80,title:'罚息截止日',type:'date'}//资产名称
            ,{field:'isRushBackStr',sortField:'isRushBack',width:130,title:'截止后是否冲回'}//资产名称
            ,{field:'bjBranch', visible : false}
            ,{field:'liBranch', visible : false}
            ,{field:'mdateStr', visible : false}
            ,{field:'vdateStr', visible : false}
            ,{field:'secAcctId', visible : false}
            ,{field:'finprodId', visible : false}
            ,{field:'finprodType2', visible : false}
            ,{field:'finprodTypeGz', visible : false}
            ,{field:'finprodTypeTg', visible : false}
            ,{
                field: 'market', width:100, sortField:'market', title: '持仓明细',
                render: function (data, type, row, meta) {
                    return "<a id ='chicmingxi' class='portfolioId-link ' style='cursor: pointer;'>持仓明细</a>";
                }
            }
            ,{
                field: 'market1', width:100, sortField:'market', title: '收息明细',
                render: function (data, type, row, meta) {
                    return "<a id ='shouximingxi' class='portfolioId-link' style='cursor: pointer;'>收息明细</a>";
                }
            }
            // 多级法人改造 add by caiqun 20200615
            //,{field: 'institutionName', width:150, title: '机构名称',sortField:'institutionName'}
            // 多级法人改造 add by caiqun 20200615
        ]
    });
    table.bind('click','.finprodIdLink', function(row) {
    	//债券标的信息查看
    	if(row.finprodType2 == PINAN_ASSET_TYPE_F01){
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath
                + "invest/asset/bond/FinBondTargetAsset002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                    finprodId:row.finprodId,  //金融产品代码
                });
    	//计划类利率型标的信息查看
		}else if(row.finprodType2 == PINAN_ASSET_TYPE_F03){
			label='FinPlanProfitAsset002.detail';
		    pagePath = "invest/asset/plan/planProfit/FinPlanProfitAsset002/init";
			Joyin.dialog(JoyinUtil.getLabel(label),basePath+pagePath,
			    "95%", "90%",{
			    pageModel:PAGE_MODEL_REFERENCE,
			    finprodId:row.finprodId
			});
		}//存款标的信息查看
		else if(row.finprodType2 == PINAN_ASSET_TYPE_F05){
			label='FinDepositTarget002.title';
		    pagePath = "invest/asset/depo/FinDepositTarget002/init";
		    Joyin.dialog(JoyinUtil.getLabel(label),basePath+pagePath,
		    	"95%", "90%",{
		    	pageModel:PAGE_MODEL_REFERENCE,
		    	finprodId:row.finprodId
		     });//项目类资产标的信息查看
		}else if(row.finprodType2 == PINAN_ASSET_TYPE_F06){
			label='FinProjectTargetAsset002.title';
		    pagePath = "invest/asset/project/FinProjectTargetAsset002/init";
			Joyin.dialog(JoyinUtil.getLabel(label),basePath+pagePath,
			    "95%", "90%",{
			    pageModel:PAGE_MODEL_REFERENCE,
			    finprodId:row.finprodId
			});
		}
        else if(row.finprodType2 == PINAN_ASSET_TYPE_F19){
            label='FinProjectNetTargetAsset002.title';
            pagePath = "invest/asset/projectnet/FinProjectNetTargetAsset002/init";
            Joyin.dialog(JoyinUtil.getLabel(label),basePath+pagePath,
                "95%", "90%",{
                    pageModel:PAGE_MODEL_REFERENCE,
                    finprodId:row.finprodId
                });
        }
       
    });

    table.bind('click', '#chicmingxi', function(data, index) {
        var param={};
        if(data.finprodId){
            param.finprodId = data.finprodId;
        }
        if(data.bjBranch){
            param.bjBranch = Number(data.bjBranch);
        }
        if(data.liBranch){
            param.liBranch = Number(data.liBranch);
        }
        if(data.secAcctId){
            param.secAcctId = data.secAcctId;
        }else{
            param.secAcctId = '';
        }
        Joyin.dialog('持仓明细信息', JoyinUtil.getRealPath('invest/period/trdsecoverdueduration/TrdSecOverdueDuration001/initRecePositionDetail'), '90%', '90%',param);
    })
    table.bind('click', '#shouximingxi', function(data, index) {
        var param={};
        if(data.finprodId){
            param.finprodId = data.finprodId;
        }
        param.mdate =data.mdateStr;
        if(data.bjBranch){
            param.bjBranch = Number(data.bjBranch);
        }
        if(data.liBranch){
            param.liBranch = Number(data.liBranch);
        }
        if(data.secAcctId&&data.secAcctId!='999999'){
            param.secAcctId = data.secAcctId;
        }else{
            param.secAcctId = '';
        }
        if(data.theoryPenaltyInt){
        	if(Number(data.theoryPenaltyInt)>0){
        		param.theoryPenaltyInt = Number(data.theoryPenaltyInt);
        	}else{
        		param.theoryPenaltyInt =0 ;
        	}
        }
        param.payType='no';
        Joyin.dialog('收息明细信息', JoyinUtil.getRealPath('invest/period/trdsecoverdueduration/TrdSecOverdueDuration001/initIntertstDetail'), '98%', '95%',param);
    })
    /**
     * 定义多次未结清的数据表格
     * @type {jQuery}
     */
    var tableTwo=$("#dataTableTwo").joyintable({
        ajax: {
            url: 'selectPageData',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParamsTwo();//获取请求参数
            }//ajax请求数据
        },
        /**
         * 定义表头
         */
        sortfont:true,
        cols:[
            {field:'cashTypeStr',sortField:'cashType',width:120,title: '类型'} //类型
            ,{field:'finprodTypeName',sortField:'finprodTypeName', width:100,title:'资产类型'}
            ,{
                field: 'finprodMarketId', title: '资产代码', width: '10%',
                render: function (data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>" + row.finprodMarketId + "</ a>";
                }, sortField: "finprodMarketId"
            }
            ,{field:'finprodName',sortField:'finprodName',width:150,title:'资产名称'}//资产名称
            ,{field:'secManageAcctName',sortField:'secManageAcctName', width:100,title:'证券托管户'}//证券账户
            ,{field:'chIldName',sortField:'chIldName', width:100,title:'通道'}//证券账户
            ,{field:'vdate',sortField:'vdate',width:100,title:'计息起始日',type:'date'}//计息起始日
            ,{field:'mdate',sortField:'mdate',width:100,title:'计息结束日',type:'date'}//交易方向
            ,{field:'endPayDate',sortField:'endPayDate',width:100,title:'最终支付日',type:'date'}//登记日
            ,{field:'theoryAmt',sortField:'theoryAmt',width:120,title:'理论本息总额',type:'number', digit:2}//除权日
            ,{field:'fpriceAmt',sortField:'fpriceAmt',width:120,title:'实收本息总额',type:'number', digit:2}//到账日
            ,{field:'theoryPenaltyInt',sortField:'theoryPenaltyInt',width:120,title:'未付罚息总额',type:'number', digit:2}//除权日
            ,{field:'penaltyIntamt',sortField:'penaltyIntamt',width:120,title:'实收罚息总额',type:'number', digit:2}//到账日
            // 多级法人改造 add by caiqun 20200615
            //,{field: 'institutionName', width:150, title: '机构名称',sortField:'institutionName'}
            // 多级法人改造 add by caiqun 20200615
            ,{field:'bjBranch', visible : false}
            ,{field:'liBranch', visible : false}
            ,{field:'mdateStr', visible : false}
            ,{field:'vdateStr', visible : false}
            ,{field:'secAcctId', visible : false}
            ,{field:'finprodId', visible : false}
            ,{
                field: 'market1', width:100, sortField:'market', title: '收息明细',
                render: function (data, type, row, meta) {
                    return "<a id ='shouximingxi' class='portfolioId-link' style='cursor: pointer;'>收息明细</a>";
                }
            }
        ]
    });
    tableTwo.bind('click','.finprodIdLink', function(row) {
    	//债券标的信息查看
    	if(row.finprodType2 == PINAN_ASSET_TYPE_F01){
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath
                + "invest/asset/bond/FinBondTargetAsset002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                    finprodId:row.finprodId,  //金融产品代码
                });
    	//计划类利率型标的信息查看
		}else if(row.finprodType2 == PINAN_ASSET_TYPE_F03){
			label='FinPlanProfitAsset002.detail';
		    pagePath = "invest/asset/plan/planProfit/FinPlanProfitAsset002/init";
			Joyin.dialog(JoyinUtil.getLabel(label),basePath+pagePath,
			    "95%", "90%",{
			    pageModel:PAGE_MODEL_REFERENCE,
			    finprodId:row.finprodId
			});
		}//存款标的信息查看
		else if(row.finprodType2 == PINAN_ASSET_TYPE_F05){
			label='FinDepositTarget002.title';
		    pagePath = "invest/asset/depo/FinDepositTarget002/init";
		    Joyin.dialog(JoyinUtil.getLabel(label),basePath+pagePath,
		    	"95%", "90%",{
		    	pageModel:PAGE_MODEL_REFERENCE,
		    	finprodId:row.finprodId
		     });//项目类资产标的信息查看
		}else if(row.finprodType2 == PINAN_ASSET_TYPE_F06){
			label='FinProjectTargetAsset002.title';
		    pagePath = "invest/asset/project/FinProjectTargetAsset002/init";
			Joyin.dialog(JoyinUtil.getLabel(label),basePath+pagePath,
			    "95%", "90%",{
			    pageModel:PAGE_MODEL_REFERENCE,
			    finprodId:row.finprodId
			});
		}
        else if(row.finprodType2 == PINAN_ASSET_TYPE_F19){
            label='FinProjectNetTargetAsset002.title';
            pagePath = "invest/asset/projectnet/FinProjectNetTargetAsset002/init";
            Joyin.dialog(JoyinUtil.getLabel(label),basePath+pagePath,
                "95%", "90%",{
                    pageModel:PAGE_MODEL_REFERENCE,
                    finprodId:row.finprodId
                });
        }
    });
    tableTwo.bind('click', '#shouximingxi', function(data, index) {
        var param={};
        if(data.finprodId){
            param.finprodId = data.finprodId;
        }
        param.mdate =data.mdateStr;
        if(data.bjBranch){
            param.bjBranch = Number(data.bjBranch);
        }
        if(data.liBranch){
            param.liBranch = Number(data.liBranch);
        }
        if(data.secAcctId&&data.secAcctId!='999999'){
            param.secAcctId = data.secAcctId;
        }else{
            param.secAcctId = '';
        }
        param.payType='yes';
        Joyin.dialog('收息明细信息', JoyinUtil.getRealPath('invest/period/trdsecoverdueduration/TrdSecOverdueDuration001/initIntertstDetail'), '80%', '80%',param);
    })

    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//请求参数
        paramObj.exportType='N';
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };

    var exportExcelTwo = function() {
        var paramObj = getSearchParamsTwo();//请求参数
        paramObj.exportType='Y';
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };

    /**
     * 画面加载完成
     */
    $(function() {
        //未结清导出
        $('.transfer-form #TrdSecOverdueDuration001_btn_export').on('click',function() {
            exportExcel();
        });
        //已结清导出
        $('.dvp-transfer-form #TrdSecOverdueDuration001_btn_export').on('click',function() {
            exportExcelTwo();
        });
        //未结清查询
        $('.transfer-form #TrdSecOverdueDuration001_btn_search').on('click',function () {
            table.load();
            $('#TrdFundDuration001_btn_revoke').show();
        });
        //已结清查询
        $('.dvp-transfer-form #TrdSecOverdueDuration001_btn_search').on('click',function () {
            tableTwo.load();
            $('#TrdFundObversion001_btn_revoke').show();
        });
        //计息要素维护
        $('.transfer-form #TrdSecOverdueDuration001_btn_intst').on('click',function() {
            var param = {};
           var rows =  table.getAllCheckedRows();
           if(!rows||rows.length==0){
               Joyin.error('未选择未结清列数据！');
               return;
           }
           if(rows&&rows.length>1){
               Joyin.error('请选择一条未结清数据！');
               return;
           }
            param.finprodId = rows[0].finprodId;
           param.finprodName = rows[0].finprodName;
           param.finprodType = rows[0].finprodType2;
           param.vdate =  rows[0].vdateStr;
           param.mdate = rows[0].mdateStr;
           if(rows[0].penaltyBaseType){
               param.penaltyBaseType = rows[0].penaltyBaseType;
           }
           if(rows[0].bjBranch){
             param.bjBranch = Number(rows[0].bjBranch);
            }
            if(rows[0].liBranch){
                param.liBranch = Number(rows[0].liBranch);
            }
            Joyin.dialog('计息信息维护',
                JoyinUtil.getRealPath('invest/period/trdoverduecalcupenalty/TrdOverdueCalcuPenalty002/init'), '85%', '85%',param);

        });
        //还本收息

        $('.transfer-form #TrdSecOverdueDuration001_btn_huanbenshouxi').on('click',function() {
            var param = {};
            var rows =  table.getAllCheckedRows();
            if(!rows||rows.length==0){
                Joyin.error('未选择未结清列数据！');
                return;
            }
            if(rows&&rows.length>1){
                Joyin.error('请选择一条未结清数据！');
                return;
            }
            param.finprodId = rows[0].finprodId;
            param.finprodName = rows[0].finprodName;
            param.finprodType2 = rows[0].finprodType2;
            param.vdate =  rows[0].vdateStr;
            param.mdate = rows[0].mdateStr;
            param.penaltyBaseType = rows[0].penaltyBaseType;
            param.secManageAcctId= rows[0].secAcctId;
            param.secAcctId= rows[0].secAcctId;
            if(rows[0].bjBranch){
              param.bjBranch = Number(rows[0].bjBranch);
             }
             if(rows[0].liBranch){
                 param.liBranch = Number(rows[0].liBranch);
             }
            Joyin.ajaxJson(basePath + 'invest/period/trdsecoverdueduration/TrdSecOverdueDuration001/check', param, function(result) {
            	Joyin.ajaxJson(basePath + 'invest/period/trdsecoverdueduration/TrdSecOverdueDuration001/checkZr', param, function(result) {
            		Joyin.dialog('还本收息新建',
                            JoyinUtil.getRealPath('invest/period/trdsecoverdueduration/TrdSecOverdueDuration002/init'), '95%', '95%',param);
            	},false)
            },false)
        });
        $('.transfer-form #TrdSecOverdueDuration001_btn_zhuanrang').on('click',function() {
            var param = {};
            var rows =  table.getAllCheckedRows();
            if(!rows||rows.length==0){
                Joyin.error('未选择未结清列数据！');
                return;
            }
            if(rows&&rows.length>1){
                Joyin.error('请选择一条未结清数据！');
                return;
            //    ExpiredAssetTransfer
            }
            param.finprodId = rows[0].finprodId;
            param.finprodName = rows[0].finprodName;
            param.finprodType2 = rows[0].finprodType2;
            param.vdate =  rows[0].vdateStr;
            param.mdate = rows[0].mdateStr;
            param.penaltyBaseType = rows[0].penaltyBaseType;
            param.secAcctId = rows[0].secAcctId;
            if(rows[0].bjBranch){
                param.bjBranch = Number(rows[0].bjBranch);
            }
            if(rows[0].liBranch){
                param.liBranch = Number(rows[0].liBranch);
            }
            param.pageModel = PAGE_MODEL_NEW;
            Joyin.ajaxJson(basePath + 'invest/period/trdsecoverdueduration/TrdSecOverdueDuration001/checkZr', param, function(result) {
            	Joyin.ajaxJson(basePath + 'invest/period/trdsecoverdueduration/TrdSecOverdueDuration001/check', param, function(result) {
            		Joyin.dialog("已到期资产转让",basePath+"invest/period/trdsecoverdueduration/TrdSecOverdueDuration003/init",'95%','95%',param);
            	},false);
            },false);
        })
    });
}());