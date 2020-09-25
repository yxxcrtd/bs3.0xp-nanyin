/**
 * RskSupervisionRiskAnalyze004 资产持仓明细
 */
var rsk = function() {
	var globalFoud=null;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	indName : indName,
        	assetName : assetName,
         	portfolioId : portfolioId,
        	supDate : supDate,
        	assetCode : assetCode,
            exportFlag : "05"  //监督明细导出
        }
    };
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
             {field:'indName',visible :false}
            ,{field:'portfolioId', title: 'RskSupervisionRiskAnalyze001.portfolioId'}
            ,{field:'portfolioName', title: 'RskSupervisionRiskAnalyze001.portfolioName'}
            ,{field:'assetCode', title: 'RskSupervisionRiskAnalyze001.assetCode'}
            ,{field:'assetName',title: 'RskSupervisionRiskAnalyze001.assetName'}
            ,{field:'shareAmt', title: 'RskSupervisionRiskAnalyze001.shareAmt',render: function(d,type, row, meta){
                   return RskQuotaCommon.toDecimal(d,2);
            }}
            ,{field:'invManagerId', title: 'RskSupervisionRiskAnalyze001.invManagerId'}
            ]
    });
    
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
    	var paramObj=getSearchParams();
        Joyin.exportExcel('exportNx', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        table.load();
        
        $('div.analy-dtl-form>a#RskSupervisionRiskAnalyze004_btn_export').click(function() {
            exportExcel();
        });
    });
}();
