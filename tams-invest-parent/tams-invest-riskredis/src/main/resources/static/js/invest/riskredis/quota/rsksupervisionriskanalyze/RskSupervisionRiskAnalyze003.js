/**
 * RskSupervisionRiskAnalyze003 连续违规天数查询
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
            supDate:supDate,
            foulDays : parseInt(foulDays),
            supFrequency:supFrequency,
            exportFlag : "04"  //连续违规天数明细导出
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
            ,{field:'indName', title: 'RskSupervisionRiskAnalyze001.indName',sortable:false}
            ,{field:'supFrequency',visible :false, title: 'RskSupervisionRiskAnalyze001.supFrequency'}
            ,{field:'supDate', title: 'RskSupervisionRiskAnalyze001.supDate',type:'date',sortable:false}
            ,{field:'warnCond', title: 'RskSupervisionRiskAnalyze001.warnCond',sortable:false,render: RskQuotaCommon.transferWarnFoul}//,render: transferWarnFoul
            ,{field:'approveCond', title: 'RskSupervisionRiskAnalyze001.approveCond',sortable:false,render: RskQuotaCommon.transferWarnFoul}//,render: transferWarnFoul
            ,{field:'foulCond', title: 'RskSupervisionRiskAnalyze001.foulCond',sortable:false,render: RskQuotaCommon.transferWarnFoul}//,render: transferWarnFoul
            ,{field:'supVal', title: 'RskSupervisionRiskAnalyze001.supVal',sortable:false,render: RskQuotaCommon.transferSupVal }//,render: transferWarnFoul
            ,{field:'supStatAbbr',sortField:'supStat',sortable:false,title: 'RskSupervisionRiskAnalyze001.supStat',render:function(d, type, row, meta){
                if(d == normal)
                	 return  "<span style='color: green'>"+d+"</span>";
                else if(d == warning)
                    return  "<span style='color: orange'>"+d+"</span>";
                else if(d == violation)
	                return  '<a  href="javascript:;" onclick="showSupDetail(\''+ row.indId+',\''+row.portfolioId+'\''+',\''+row.supDate
					+ '\')" style="cursor: pointer;"><span style="color: red">'+d+'</span></a>';
                }
            }]
    });
    
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
    	var paramObj=getSearchParams();
        Joyin.exportExcel('exportFu', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        table.load();
        $('div.analy-foul-form>a#RskSupervisionRiskAnalyze003_btn_export').click(function() {
            exportExcel();
        });
    });
}();
