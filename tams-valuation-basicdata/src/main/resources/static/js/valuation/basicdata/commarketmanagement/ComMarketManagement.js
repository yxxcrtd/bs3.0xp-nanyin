/**
 * 证券账户管理
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	oType:$('#oType').val(),
        	oCode:$('#finprodName').val(),
        	baseDataType:$('#ratingHistory').val(),
        	gradeSourceType:$('#gradeSourceType').val(),
        	shortLongTerm:$('#shortLongTerm').val(),
     	   	gradeResult:$('#gradeResult').val(),
     	   	gradeOrgId:$('#gradeOrgId').val(),
     	   	gradeDateStart: $.trim($('#gradeDate').startDate()),
     	   	gradeDateEnd: $.trim($('#gradeDate').endDate()),
        };
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
             {field:'oTypeName', sortable:false,  title: 'ComMarketManagement.oGreadeType'}//对象类型
            ,{field:'oCode', sortable:false,  title: 'ComMarketManagement.secId'}//对象代码
            ,{field:'oCodeName',  title: 'ComMarketManagement.finprodName'}//对象名称
            ,{field:'gradeDate',sortable:false,title: 'ComMarketManagement.gradeDate',type:'date'}//当前评级日期
            ,{field:'gradeResultDis', sortable:false, title: 'ComMarketManagement.gradeResult'}//当前评级结果
            ,{field:'previousgradeDate',sortable:false,title: 'ComMarketManagement.previousgradeDate',type:'date'}//前次评级日期
            ,{field:'previousgradeResult', sortable:false, title: 'ComMarketManagement.previousgradeResultDis'}//前次评级结果
            ,{field:'shortLongTermName', sortable:false, title: 'ComMarketManagement.shortLongTerm'}//评级类型
            ,{field:'noticeDate',sortable:false,   title: 'ComMarketManagement.announcementDate',type:'date'}//公告日期
            ,{field:'gradeExpectationStr',sortable:false, title: 'ComMarketManagement.gradeExpectation'}//评级展望
            ,{field:'directionChangeStr', sortable:false,title: 'ComMarketManagement.directionChange'}//变动方向
            ,{field:'gradeOrgStr', sortable:false,title: 'ComMarketManagement.gradeOrgId'}//当前评级机构
            ,{field:'previousgradeOrgName', visible:false}//前次评级机构(不显示)
            // 移除右侧修改及删除按钮  modified by qufangxu at 2019-10-30
            // ,{field:'operation',  title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['edit', 'delt']}
        ],
        searchOnInit :REM_TYPE && REM_TYPE == 'R7S1',
        checkbox : false
    });
    
    var exportExcel = function() {   
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
   
    var finprodIdChange = function () {
    	if($('#internaLevaluation').val()=='02'){
		       $('#gradeOrgId').prop('disabled', true);
    	}
    	else
        $('#gradeOrgId').removeAttr("disabled");
    }
    
    var reset = function(conditionArea,table){
        $(conditionArea).find("input").val("");
        $(conditionArea).find("select").val("").trigger("change");// select清空
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	
    	$('#internaLevaluation').change(function() {
    		finprodIdChange();
    	});
    	$('#gradeSourceType').change(function() {
    		if($('#gradeSourceType').val() == '02'){
    			$('#gradeOrgId').attr("disabled","disabled");
    		}
    		else{
    			$('#gradeOrgId').removeAttr("disabled");
    		}
    	});
        // 绑定按钮事件
        $('#ComMarketManagement_btn_search').click(function() {
              table.load();
        });
        /**
         * 数据导出
         */
        $('#ComMarketManagement_btn_export').click(function() {
            exportExcel();
        });
      
        /** 事件绑定: 置空按钮 */
        $("#ComMarketManagement_btn_reset").click(function(event){
            reset( '#selectArea',table);
        });

        /**
         * 导入按钮
         * add by yanya on 20210902
         */
        $('#ComMarketManagement001_btn_import').click(function() {
            Joyin.confirm("请确认是否要导入数据？", function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath +"valuation/basicdata/commarketmanagement/ComMarketManagement/importFile", {
                        fileName : fileName
                    },function(result){
                        if(!result || !($.isArray(result) && result.length > 0)){
                            layui.layer.closeAll()
                            table.reload();
                            //Joyin.alert("文件导入成功！", 0);
                        }
                    });
                });
            });
        });
    });
}());