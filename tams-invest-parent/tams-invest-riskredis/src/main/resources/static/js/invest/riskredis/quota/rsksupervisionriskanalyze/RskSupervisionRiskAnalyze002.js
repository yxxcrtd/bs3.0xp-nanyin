/**
 * RskSupervisionRiskAnalyze002 指标监督明细查询
 */
var rsk = function() {
	var globalFoud=null;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            indId : indId,
            portfId : portfId,
            foulCond : foulCond,
            supFrequency : supFrequency,
            supDate : supDate,
            exportFlag : "03"  //监督明细导出
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
            {field:'portfolioId',visible :false}
            ,{field:'indId',visible :false}
            ,{field:'supDate',type:'date',visible:false}
            ,{field:'indName', title: 'RskSupervisionRiskAnalyze001.indName'}
            ,{field:'assetCode', title: 'RskSupervisionRiskAnalyze001.assetCode'}
            ,{field:'assetName', title: 'RskSupervisionRiskAnalyze001.assetName'}
            ,{field:'warnCond', title: 'RskSupervisionRiskAnalyze001.warnCond',render: RskQuotaCommon.transferWarnFoul}
            ,{field:'approveCond', title: 'RskSupervisionRiskAnalyze001.approveCond',render: RskQuotaCommon.transferWarnFoul}
            ,{field:'foulCond', title: 'RskSupervisionRiskAnalyze001.foulCond',render: RskQuotaCommon.transferWarnFoul}
            ,{field:'supVal', title: 'RskSupervisionRiskAnalyze001.supVal',render: RskQuotaCommon.transferSupVal}
            ,{field:'supStatAbbr',sortField:"supStat",title: 'RskSupervisionRiskAnalyze001.supStat',render:function(d, type, row, meta){
                if(d == normal)
                	 return  "<span style='color: green'>"+d+"</span>";
                else if(d == warning || d == approve)
                    return  "<span style='color: orange'>"+d+"</span>";
                else if(d == violation)
	                return  "<span style='color: red'>"+d+"</span>";
                }
            }
            , {field: 'operation',title: 'SysCommon.operation', type: 'action',render:function(d, type, row, meta){
	                return  '<a onclick="showSecValuation(\''+ row.portfolioId+'\''+',\''+ row.assetCode+'\''+',\''+row.assetName+'\''+',\''+row.indName
					+ '\''+',\''+new Date(row.supDate).format('yyyy-MM-dd')+'\')"  style="cursor: pointer;cursor: pointer;display: block;width: 70px;background: #2693ff; color: #fff;border-radius: 2px;"><span >持仓明细</span></a>';
               }
            }]
    });
    
    /**
     * 绑定持仓明细事件
     */
    table.bind('click', '.details', function(data, index) {
       
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("RskSupervisionIndicatorParam001.edit"),
                basePath + "invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                    paraCode: data.paraCode},function() {
                    table.reload();
                });
    });
	
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
    	var paramObj=getSearchParams();
        Joyin.exportExcel('exportMx', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        table.load();
        $('div.analy-dtl-form>a#RskSupervisionRiskAnalyze002_btn_export').click(function() {
            exportExcel();
        });
    });
}();

function showSecValuation(portfolioId,assetCode,assetName,indName,supDate){
	var data={};
	data.portfolioId=portfolioId;
	data.assetName=assetName;
	data.assetCode=assetCode;
	data.indName=indName;
	data.supDate=supDate;
	 Joyin.dialog(JoyinUtil.getLabel("RskSupervisionRiskAnalyze004"),
             basePath + "invest/riskredis/quota/rsksupervisionriskanalyze/RskSupervisionRiskAnalyze004/init",
             "95%", "90%", data);
}
