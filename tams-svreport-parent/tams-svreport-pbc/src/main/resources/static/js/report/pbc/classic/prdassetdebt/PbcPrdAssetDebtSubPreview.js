$(function(){
	
	// 定义数据表格
	var tableDK=$("#dataTableDK").joyintable({
        ajax: {
            url: 'searchPageDX',
            type: 'POST',
            data: {
            	reportuuid:reportuuid,
            }
        },
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        cols:[
				{ title: "资产池代码", field: 'pbcAccountId',width:150,sortable:false},
				{ title: "内部产品代码", field: 'prodId',width:150,sortable:false},
				{ title: "资产代码", field: 'assetCode',width:150,sortable:false},
				{ title: "资产名称",field:'assetName',width:150,sortable:false},
				{ title: "贷款种类", field: 'loansType',width:150,sortable:false},
				{ title: "贷款转让方机构代码", field: 'loanTransferorId',width:150,sortable:false},
				{ title: "贷款转让方机构名称", field: 'loanTransferorName',width:150,sortable:false},
				{ title: "贷款合同原始发放机构代码", field: 'loanContractOrgId',width:150,sortable:false},
				{ title: "贷款合同原始发放机构名称", field: 'loanContractOrgName',width:150,sortable:false},
				{ title: "贷款合同原始发放机构所在地代码", field: 'loanContractOrgCounty',width:250,sortable:false},
				{ title: "借款人类型", field: 'borrowerType',width:150,sortable:false},
				{ title: "地区代码", field: 'borrowerCounty',width:150,sortable:false},
				{ title: "借款人代码", field: 'borrowerId',width:150,sortable:false},
				{ title: "借款人国别", field: 'borrowerNationality',width:150,sortable:false},
				{ title: "行业信息", field: 'industryInfo',width:150,sortable:false},
				{ title: "企业出资人经济成分（一级）", field: 'enterpriseInvestorTypeFirst',width:150,sortable:false},
				{ title: "企业出资人经济成分（二级）", field: 'enterpriseInvestorTypeSec',width:150,sortable:false},
				{ title: "企业出资人经济成分（三级）", field: 'enterpriseInvestorTypeThird',width:150,sortable:false},
				{ title: "企业规模", field: 'enterpriseScale',width:150,sortable:false},
				{ title: "贷款借据编码", field: 'loanBasisId',width:150,sortable:false},
				{ title: "贷款产品类别", field: 'loanPrdType',width:150,sortable:false},
				{ title: "贷款实际投向", field: 'loanActualInvest',width:150,sortable:false},
				{ title: "贷款发放日期", field: 'loanVdateStr',width:150,sortable:false},
				{ title: "贷款到期日期", field: 'loanMdateStr',width:150,sortable:false},
				{ title: "贷款展期到期日期", field: 'loanExtensionDueDateStr',width:150,sortable:false},
				{ title: "利率是否固定", field: 'rateFixed',width:150,sortable:false},
				{ title: "利率水平(%)", field: 'rateLevel',width:150,type:'number',sortable:false},
				{ title: "贷款担保方式", field: 'loanGuaranteeMode',width:150,sortable:false},
				{ title: "贷款质量", field: 'loanQuality',width:150,sortable:false},
				{ title: "贷款状态", field: 'loanState',width:150,sortable:false},
				{ title: "贷款转让折扣率(%)", field: 'loanTransferorRate',width:150,type:'number',sortable:false},
				{ title: "原始合同币种代码", field: 'origCcy',width:150,sortable:false},
				{ title: "原始合同金额", field: 'origContractAmount',width:150,type:'number',sortable:false},
				{ title: "原始合同金额折人民币", field: 'origContractAmountCny',type:'number',width:150,sortable:false},
				{ title: "贷款余额币种代码", field: 'amtCcy',width:150,sortable:false},
				{ title: "贷款余额", field: 'contractAmount',width:150,type:'number',sortable:false},
				{ title: "贷款余额折人民币", field: 'contractAmountCny',width:150,type:'number',sortable:false},
          ]
	});
	
	// 定义数据表格
	var tableXT=$("#dataTableXT").joyintable({
        ajax: {
            url: 'searchPageXT',
            type: 'POST',
            data: {
            	reportuuid:reportuuid,
            }
        },
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        cols:[
				{ title: "资产池代码", field: 'pbcAccountId',width:150,sortable:false},
				{ title: "内部产品代码", field: 'prodId',width:150,sortable:false},
				{ title: "资产代码", field: 'assetCode',width:150,sortable:false},
				{ title: "资产名称",field:'assetName',width:150,sortable:false},
				{ title: "股权种类", field: 'stockType',width:150,sortable:false},
				{ title: "产品发起机构编码", field: 'capitalTrustOrgCode',width:150,sortable:false},
				{ title: "人行资金信托代码", field: 'pobcassetid',width:150,sortable:false},
				{ title: "币种代码", field: 'ccy',width:150,sortable:false},
				{ title: "资金信托产品余额", field: 'capitalTrustAmount',width:150,type:'number',sortable:false},
				{ title: "资金信托产品余额折人民币", field: 'capitalTrustAmountCny',width:250,type:'number',sortable:false},
          ]
	});
	
	// 导出按钮点击事件
    $('#PbcPrdAssetDebtSubPreview_btn_export').click(function() {
    	var param = {reportuuid:reportuuid, previewType:previewType};
    	if(previewType == 'DK'){//贷款明细基本信息预览
    		var rows = tableDK.getAllCheckedRows();
    	}else if(previewType == 'XT'){//资金信托类资产明细预览
    		var rows = tableXT.getAllCheckedRows();
    	}
		if(rows.length > 0) {
			param.keyList = JSON.stringify(rows);
		}
    	Joyin.exportExcel("export", param);
    });
	
	function init(){
		$("#PbcPrdAssetDebtSubPreview_btn_filter").hide();
		setTimeout(function() {
			if(previewType == 'DK'){//贷款明细基本信息预览
				document.title='贷款明细基本信息预览';
				tableDiv0.style.display="block";
        		tableDiv1.style.display="none";
				tableDK.load();
			}else if(previewType == 'XT'){//资金信托类资产明细预览
				document.title='资金信托类资产明细预览';
				tableDiv0.style.display="none";
        		tableDiv1.style.display="block";
				tableXT.load();
			}
            
        }, 500);
	};
	
	init();
})