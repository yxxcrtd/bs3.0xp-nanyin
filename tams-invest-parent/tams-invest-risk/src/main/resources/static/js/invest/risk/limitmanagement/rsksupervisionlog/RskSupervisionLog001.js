/**
 * RskSupervisionLog001 指标日志管理
 */
var rsk = function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var portfolioName = $('#portfolioName').val();
       // var indName = $('#indName').val();
        var logLvl = $('#logLvl').val();
        var stDate = $('#supDate').startDate();
        var edDate = $('#supDate').endDate();
        return {
        	portfolioName : portfolioName,
        	logLvl : logLvl,
            stDate : stDate,
            edDate : edDate
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
            ,{field:'portfolioName', title: 'RskSupervisionLog001.portfolioName'}
            ,{field:'indTypeStr',width:150,sortField:"indType", title: 'RskSupervisionLog001.indType'}
            ,{field:'indName',width:420, title: 'RskSupervisionLog001.indName'}
            ,{field:'logTime', width:120,title: 'RskSupervisionLog001.logDate',type:'date'}
            ,{field:'logLvlStr',width:100,sortField:"logLvl", title: 'RskSupervisionLog001.rank',render:function(data, type, row, meta){
                if(row.logLvl == "INFO")
                    return  "<span style='color: green'>"+data+"</span>";
                else if(row.logLvl == "WARNING")
                    return  "<span style='color: orange'>"+data+"</span>";
                else if(row.logLvl == "ERROR")
                    return  "<span style='color: red'>"+data+"</span>";
                }}
            ,{field:'message',sortable:false,title: 'RskSupervisionLog001.message'}
        ]
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
    	var paramObj=getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RskSupervisionLog001_btn_search').click(function() {
            table.load();
        });
        $('#RskSupervisionLog001_btn_export').click(function() {
            exportExcel();
        });
    });
}();
