/**
 * FinPlanAssetC001 计划类资产（货币型）查询、删除和导出
 */
$(function() {
    /**
     * 获取查询参数
     *
     */
    /*新添五级分类查询条件(type9、tyep10、type11、type12) lishuping 2019/7/18 start*/
   var getSearchParams = function() {
        var finprodMarketId = $('#finprodMarketId').val();
        var finprodId = $('#finprodMarketId_hidden').val();
        var category = $('#category').val();
        var type = $('#type').val();
        var type9 = $('#type9').val();
        var type10 = $('#type10').val();
        var type11 = $('#type11').val();
        var type12 = $('#type12').val();
        var manager = $('#manager').val();
        // add by  2019/9/29 张普裕 查询条件新增发行人 ，托断人   start
        var trusteeId=$('#trusteeId').val();
        var issuer=$('#issuer').val();
        // add by  2019/9/29 张普裕 查询条件新增发行人 ，托断人   end
        var startVDate = $('#vdate').startDate();
        var endVDate = $('#vdate').endDate();
        var startMDate = $('#mdate').startDate();
        var endMDate = $('#mdate').endDate();
        return {
            finprodMarketId : finprodMarketId,
            finprodId : finprodId,
            category : category ? category.join(','):'',
            type : type ? type.join(','):'',
            type9 : type9 ? type9.join(','):'',
            type10: type10 ? type10.join(','):'',
            type11: type11 ? type11.join(','):'',
            type12: type12 ? type12.join(','):'',
            manager : manager,
            // add by  2019/9/29 张普裕 查询条件新增发行人 ，托管人   start
            trusteeId : trusteeId,
            issuer : issuer,
            // add by  2019/9/29 张普裕 查询条件新增发行人 ，托管人   end
            startVDate : startVDate,
            endVDate : endVDate,
            startMDate : startMDate,
            endMDate : endMDate
        }
    };
    /*新添五级分类查询条件(type9、tyep10、type11、type12) lishuping 2019/7/18 end*/
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
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/asset/plan/plancur/FinPlanAssetC002/init",
                    "99%", "99%", {pageModel:PAGE_MODEL_REFERENCE,
                                   finprodId:data.finprodId});
        },
        cols:[
             {field:'finprodId',visible :false}
//            ,{field:'issuer',visible :false}
            ,{field:'calendarId',visible :false}
//            ,{field:'capiIncomeFeature',visible :false}
//            ,{field:'trusteeId',visible :false}
            ,{field:'issueAmt',visible :false}
            ,{field:'investmentAttributes',visible :false}
            ,{field:'capiIncomeFeature',visible :false}
            ,{field:'finprodMarketId',width:180,title: 'FinPlanAssetC001.finProdMarketId'}
            ,{field:'finprodType2',visible :false}
            ,{field:'finprodName',width:150,title: 'FinPlanAssetC001.finProdName'}
             /*隐藏两级分类 lishuping 2019/7/23 start*/
           /* ,{field:'categoryAbbr', sortField:"category",width:150, title: 'FinPlanAssetC001.category'}
            ,{field:'typeAbbr', sortField:"type",width:150, title: 'FinPlanAssetC001.type'}*/
            /*隐藏两级分类 lishuping 2019/7/23  end*/
             /*新添五级分类 lishuping 2019/7/18  start*/
            ,{field:'type9Abbr', sortField:"type9",width:150, title: 'FinPlanAssetC002.type9'}
            ,{field:'type10Abbr', sortField:"type10",width:150, title: 'FinPlanAssetC002.type10'}
            ,{field:'type11Abbr', sortField:"type11",width:150, title: 'FinPlanAssetC002.type11',visible:IS_SHOW_TYPE == '01'?true:false}
            ,{field:'type12Abbr', sortField:"type12",width:150, title: 'FinPlanAssetC002.type12',visible:IS_SHOW_TYPE == '01'?true:false}
            /*新添五级分类 lishuping 2019/7/18  end*/
            ,{field:'ccyAbbr', sortField:"ccy",width:150, title: 'FinPlanAssetC001.currency'}
            ,{field:'vdate',width:150, title: 'FinPlanAssetC001.vDate',type:'date'}
            ,{field:'mdate',width:150, title: 'FinPlanAssetC001.mDate',type:'date'}
            ,{field:'manager', sortField:"manager",title: 'FinPlanAssetC002.manager'}
            // add by  2019/9/29 张普裕 查询条件新增发行人 ，托管人   start
            ,{field:'trusteeId', sortField:"trusteeId",width:150,title: 'FinPlanAssetC002.trusteeId'}
            ,{field:'issuer', sortField:"issuer",width:150,title: 'FinPlanAssetC002.issuer'}
            // add by  2019/9/29 张普裕 查询条件新增发行人 ，托管人   end
            // 增加推荐方与推荐费率 add by renping 20191014 start
            ,{field:'recommenderName',  title: 'FinPlanAssetC002.recommender', width:150, sortField:'recommender'}
            // 增加推荐方与推荐费率 add by renping 20191014 start
            ,{field:'operation',title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/asset/plan/plancur/FinPlanAssetC001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("FinPlanAssetC002.edit"),
                    basePath + "invest/asset/plan/plancur/FinPlanAssetC002/init",
                    "99%", "99%", data,function() {
                                        table.reload();
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/asset/plan/plancur/FinPlanAssetC001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/asset/plan/plancur/FinPlanAssetC001/delete', object, 
                        "FinPlanAssetC001_btn_delete", "FinPlanAssetC002",function() {
                    table.reload();
                });
            });
        });
    });
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinPlanAssetC001_btn_search').click(function() {
            table.load();
        });
        $('#FinPlanAssetC001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinPlanAssetC001_btn_create').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FinPlanAssetC002"),
                        basePath + "invest/asset/plan/plancur/FinPlanAssetC002/init",
                        "99%", "99%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#FinPlanAssetC001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) { 
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/asset/plan/plancur/FinPlanAssetC001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                        WorkFlow.doDeleteList(basePath + 'invest/asset/plan/plancur/FinPlanAssetC001/delete', object, 
                            "FinPlanAssetC001_btn_delete","FinPlanAssetC002");
                });
            });
        });
    });
  //是否展示资产四五级分类
    if(IS_SHOW_TYPE == '01'){
    	$('#type11Div').show();
    	$('#type12Div').show();
    }else{
    	$('#type11Div').hide();
    	$('#type12Div').hide();
    }
}());