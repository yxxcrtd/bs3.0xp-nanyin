/**
 * 个人产品发行登记
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	var str = $('#repFinprodName').select2('data');
    	var repFinprodNameTemp = new Array();
    	if(str && str.length > 0){
    		$.each(str, function(i, v) {
    			if(v.object){
    				repFinprodNameTemp[i] = v.object
    			}
    		})
    	}
        var repFinprodName= null;
        if(repFinprodNameTemp != null)
        	repFinprodName=repFinprodNameTemp.join(",");
        return {
        	repFinprodName: repFinprodName,
        	repFinprodRegId:$('#repFinprodRegId').val(),
        	recruitmentSDate:$('#recruitmentDate').startDate(),
        	recruitmentEDate:$('#recruitmentDate').endDate(),
        	dateSDate:$('#dataDate').startDate(),
        	dateEDate:$('#dataDate').endDate(),
            repSvdate:$('#repPrdVdate').startDate(),
            repEvdate:$('#repPrdVdate').endDate()
        }
    };

    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable(
            {
                
                  ajax: { url: 'findPage', type: 'POST', data: function() {
                  return getSearchParams(); } },
                 
                  
                
                cols : [ 
                        {field : 'repFinprodId', width : 120, visible: false}
                       ,{field : 'dataDate', width : 120, title : 'RepFinProductPerIssRegist001.dataDate',type:'date'}//数据日期
                       , {field : 'repFinprodRegId', width : 150, title : 'RepFinProductPerIssRegist001.repFinprodRegId'}//产品登记代码
                       , {field : 'repFinprodInsId', width : 150, title : 'RepFinProductPerIssRegist001.repBankProdCode'}//理财产品代码
                       , {field : 'repFinprodName', width : 150, title : 'RepFinProductPerIssRegist001.repFinprodName'}//产品名称
                       , {field : 'issuerCode', width : 150, title : 'RepFinProductPerIssRegist001.issuerCode'}//发行机构代码
                       , {field : 'repRaiseVdate', width : 150, title : 'RepFinProductPerIssRegist001.repRaiseVdate',type:'date'}//募集起始日期
                       , {field : 'repRaiseEnddate', width : 150, title : 'RepFinProductPerIssRegist001.repRaiseEnddate',type:'date'}//募集结束日期
                       , {field : 'repPrdVdate', width : 150, title : 'RepFinProductPerIssRegist001.repPrdVdate',type:'date'}//产品起始日期
                       , {field : 'repPrdMdate', width : 150, title : 'RepFinProductPerIssRegist001.repPrdMdate',type:'date'}//产品结束日期
                       , {field : 'repIsConstracteStr',width : 150, sortField:"repIsConstracte", title : 'RepFinProductPerIssRegist001.repIsConstracte'}//是否为结构化(分级)产品
                       , {field : 'repGradeType', width : 150, title : 'RepFinProductPerIssRegist001.repGradeType'}
                       , {field : 'repExpMinYield', width : 200, title : 'RepFinProductPerIssRegist001.repExpMinYield'}
                       , {field : 'repExpMaxYield', width : 200, title : 'RepFinProductPerIssRegist001.repExpMaxYield'}
                       , {field : 'repExpAvgYield', width : 200, title : 'RepFinProductPerIssRegist001.repExpAvgYield'}
                       , {field : 'repYieldComment', width : 200, title : 'RepFinProductPerIssRegist001.repYieldComment'}
                       , {field : 'repOpenModeStr', width : 150, sortField:"repOpenMode",title : 'RepFinProductPerIssRegist001.repOpenMode'}//开放模式
                       , {field : 'repOpenCircleStr', width : 200, sortField:"repOpenCircle",title : 'RepFinProductPerIssRegist001.repOpenCircle'}
                       , {field : 'repOpenCircleDays', width : 200, title : 'RepFinProductPerIssRegist001.repOpenCircleDays'}
                       , {field : 'repRuleComment', width : 200, title : 'RepFinProductPerIssRegist001.repRuleComment'}
                       , {field : 'repFirstOpenDayStr',width : 200, sortField:"repFirstOpenDay", title : 'RepFinProductPerIssRegist001.repFirstOpenDay',type:'date'}
                       , {field : 'repHolidayOnFlagStr', width : 150,sortField:"repHolidayOnFlag", title : 'RepFinProductPerIssRegist001.repHolidayOnFlag'}
                       , {field : 'repAvgOpenCount', width : 150, title : 'RepFinProductPerIssRegist001.repAvgOpenCount'}
                       , {field : 'repOpenBussStr',width : 150, sortField:"repOpenBuss", title : 'RepFinProductPerIssRegist001.repOpenBuss'}
                       , {field : 'repOpenBussRemark', width : 200, title : 'RepFinProductPerIssRegist001.repOpenBussRemark'}
                       
                ]
            });
    var getParamObj = function(){
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
    	var repFinprodId = new Array();
    	if (selArr || selArr.length > 0) {
    		$.each(selArr, function(i, v) {  
	    	    if(v.repFinprodId != null || v.repFinprodId != ''){
	    	    	repFinprodId[i] = v.repFinprodId;
	    	    }
    		});
    	}
    	return {
    		repFinprodId:repFinprodId
    	}
    	
    }
    var exportExcel = function() {
    	if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
	    var paramObj = getParamObj();
	    Joyin.exportExcel('export', paramObj);
    };
    var init = function(){
    	//隐藏筛选按钮
    	$('#RepFinProductPerIssRegist001_btn_filter').attr("style","display:none;");
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
        // 绑定按钮事件
        $('#RepFinProductPerIssRegist001_btn_search').click(function() {
            table.load();
        });
        $('#RepFinProductPerIssRegist001_btn_export').click(function() {
            exportExcel();
        });
        
    });
}());