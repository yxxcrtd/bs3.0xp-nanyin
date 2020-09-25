/**
 * FinPlanProfitAsset001 计划类资产（利率型）查询
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
    	 /*   start、zhuyongkang、2019.7.18、获取页面查询参数，剔除12级，加入234级。更改了之前的获取查询参数的方法   */
      		
    	/*var data = $.serializeToObject('#searchForm');
    	return data;*/
    	
        var finprodMarketId = $('#finprodMarketId').val();
        var finprodId = $('#finprodMarketId_hidden').val();   
        var category = $('#category').val();
        var type = $('#type').val();
        /*   start、zhuyongkang、2019.7.19、增加页面查询参数字段   */
        var type9 = $('#type9').val();
        var type10 = $('#type10').val();
        var type11 = $('#type11').val();
        var type12 = $('#type12').val();
        /* end、zhuyongkang、2019.7.19 */ 
        var transMode = $('#transMode :selected').text();
        var ccy = $('#ccy').val();
        var startVDate = $('#vdate').startDate();
        var endVDate = $('#vdate').endDate();
        var startMDate = $('#mdate').startDate();
        var endMDate = $('#mdate').endDate();
        var manager = $('#manager').val();
        var parentPlan = $('#parentPlan').val();
        var trusteeId = $('#trusteeId').val();//托管人
        var issuer = $('#issuer').val()//发行人
        return {
            finprodMarketId : finprodMarketId,
            finprodId : finprodId,
            category : category?category.join(','):'',
            type : type?type.join(','):'',

            type9 : type9?type9.join(','):'',
            type10 : type10?type10.join(','):'',
            type11 : type11?type11.join(','):'',
            type12 : type12?type12.join(','):'',
            transMode : transMode,
            ccy : ccy,
            startVDate : startVDate,
            endVDate : endVDate,
            startMDate : startMDate,
            endMDate : endMDate,
            manager : manager,
            parentPlan:parentPlan,
            trusteeId:trusteeId,
            issuer:issuer
        }
    };
    /**
     * 定义数据表格
     */
    //modify by gaopeng 20191018 数据表格显示优化 start
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
                    basePath + "invest/asset/plan/planProfit/FinPlanProfitAsset002/init",
                    "98%", "98%", {pageModel:PAGE_MODEL_REFERENCE,
                                   finprodId:data.finprodId});
        },
        cols:[
             {field:'finprodId',visible :false}
//             ,{field:'manager',visible :false}
             ,{field:'calendarId',visible :false}
             ,{field:'capiIncomeFeature',visible :false}
//             ,{field:'trusteeId',visible :false}
             ,{field:'issueAmt',visible :false}
             ,{field:'parentPlan',visible :false}
             ,{field:'finprodType2',width:'15%',title: 'FinPlanProfitAsset001.finprodType2',visible:false} //金融产品类型
             ,{field:'finprodMarketId',width:'15%',title: 'FinPlanProfitAsset001.finProdMarketId'}
             ,{field:'finprodName',width:'12%',title: 'FinPlanProfitAsset001.finProdName'}
             /*   start、zhuyongkang、2019.7.18、添加新的查询结果2345级，剔除12级   */
             ,{field:'type9Abbr',width:'12%', title: 'FinPlanProfitAsset001.type9',sortField:'type9'}   //二级分类
             ,{field:'type10Abbr',width:'12%', title: 'FinPlanProfitAsset001.type10',sortField:'type10'} //三级分类
             ,{field:'type11Abbr',width:'12%', title: 'FinPlanProfitAsset001.type11',sortField:'type11',visible:IS_SHOW_TYPE == '01'?true:false} //四级分类
             ,{field:'type12Abbr',width:'12%', title: 'FinPlanProfitAsset001.type12',sortField:'type12',visible:IS_SHOW_TYPE == '01'?true:false} //五级分类
             /*,{field:'categoryAbbr', title: 'FinPlanProfitAsset001.category',sortField:'category'}
             ,{field:'typeAbbr', title: 'FinPlanProfitAsset001.type',sortField:'type'}*/
             /* end、zhuyongkang、2019.7.18 */ 
             ,{field:'transMode', title: 'FinPlanProfitAsset001.transMode',visible:false}
             ,{field:'ccyAbbr', title: 'FinPlanProfitAsset001.currency',sortField:'ccy'}
             ,{field:'vdate', title: 'FinPlanProfitAsset001.vDate',type:'date'}
             ,{field:'mdate', title: 'FinPlanProfitAsset001.mDate',type:'date'}
             ,{field:'manager',title: 'FinPlanProfitAsset001.manager'}
             ,{field:'trusteeId',title: 'FinPlanProfitAsset002.trusteeId'}
             ,{field:'issuer',title: 'FinPlanProfitAsset002.issuer'}
             // 增加推荐方与推荐费率 add by renping 20191014 start
             ,{field:'recommenderName',  title: 'FinPlanProfitAsset002.recommender', width:150,  sortField:'recommender'}
             // 增加推荐方与推荐费率 add by renping 20191014 end
             // 调整操作列宽 by 赵东东 2019.10.29
             ,{field:'operation',width:100,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    //modify by gaopeng 20191018 数据表格显示优化 end
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'invest/asset/plan/planProfit/FinPlanProfitAsset001/check', data, function(result) {
            Joyin.ajax(basePath + 'invest/asset/common/editCheck', data, function(editResult){
            Joyin.dialog(JoyinUtil.getLabel("FinPlanProfitAsset001.edit"),
                    basePath + "invest/asset/plan/planProfit/FinPlanProfitAsset002/init",
                    "98%", "98%", {pageModel:PAGE_MODEL_EDIT,
                                   finprodId: data.finprodId},function() {
                                        table.reload();
                                    });
        });
    });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/asset/plan/planProfit/FinPlanProfitAsset001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/asset/plan/planProfit/FinPlanProfitAsset001/delete', object, 
                        "FinPlanProfitAsset001_btn_delete", "FinPlanProfitAsset002",function() {
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
        // 修改导出错误 zhuyongkang
        paramObj.isShowType=IS_SHOW_TYPE;
        Joyin.exportExcel('export', paramObj);
    };
    /**
     *弹出利率调整
     */
    function showRateAdj(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        if (!selArr || selArr.length >1) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
            return;
        }
        if(selArr[0].parentPlan == 'Y'){
            Joyin.warn("母计划不允许进行利率调整");
            return;
        }
        var finprodId = selArr[0].finprodId;
        var finprodName = selArr[0].finprodName;
        var finprodType2 = selArr[0].finprodType2;
        var finprodMarketId = selArr[0].finprodMarketId;
        var object = selArr[0];
        object.rateType = '01';
        Joyin.ajaxJson(basePath + 'invest/asset/common/FinAssetRateAdj001/isTransInAssetCheck', object, function(result) {
            if(result){
                Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0009'),function(){
                    AssetCommon.finalShowRateAdj(object,function(){
                        table.reload();
                    });
                });
            }else{
                AssetCommon.finalShowRateAdj(object,function(){
                    table.reload();
                });
            }
        });
    }
    
    /**
     * 期次管理
     */
    var showStageManagement = function(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        if (!selArr || selArr.length >1) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
            return;
        }
        var object = selArr[0];
        if(object.parentPlan == 'N'){
            Joyin.warn("该资产非母资管计划");
            return;
        }
        /*新增流程中不可编辑校验 panbin 2020-7-12 */
        Joyin.ajaxJson(basePath + 'invest/asset/common/FinStageManagement/isWorkFlowCheck', object, function(status) {
            if (status) {
                Joyin.error("存在流程中期次计划，不允许编辑");
            }else {
                Joyin.ajaxJson(basePath + 'invest/asset/common/FinAssetRateAdj001/isTransInAssetCheck', object, function(result) {
                    if(result){
                        Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0009'),function(){
                            AssetCommon.showStageManagement(object,function(){
                                table.reload();
                            });
                        });
                    }else{
                        AssetCommon.showStageManagement(object,function(){
                            table.reload();
                        });
                    }
                });
            }

        });

    }
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#FinPlanProfitAsset001_btn_search').click(function() {
            var vdate = $('#vdate').endDate();
            var mdate = $('#mdate').startDate();
            if(vdate != null && vdate != '' && vdate != '1900-01-01' && mdate != null && mdate != '' && mdate != '1900-01-01'){
                var result = parseInt(((Date.parse(new Date(vdate))) - (Date.parse(new Date(mdate)))));
                if (result > 0) {
                	var msg = '成立日期结束日不能大于终止日期开始日。';
                	Joyin.error(msg);
                	table.clear();
                } else {
	        		table.table ? table.reload() : table.load();
		            $('#FinPlanProfitAsset001_btn_delete').show();
                }
        	} else {
        		table.table ? table.reload() : table.load();
	            $('#FinPlanProfitAsset001_btn_delete').show();
        	}
        });
        $('#FinPlanProfitAsset001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinPlanProfitAsset001_btn_create').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FinPlanProfitAsset002"),
                        basePath + "invest/asset/plan/planProfit/FinPlanProfitAsset002/init",
                        "98%", "98%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#FinPlanProfitAsset001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) { 
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/asset/plan/planProfit/FinPlanProfitAsset001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                        WorkFlow.doDeleteList(basePath + 'invest/asset/plan/planProfit/FinPlanProfitAsset001/delete', object, 
                            "FinPlanProfitAsset001_btn_delete","FinPlanProfitAsset002");
                });
            });
        });
        //利率调整
        $("#FinPlanProfitAsset001_btn_rateAdjust").click(function(){
            showRateAdj();
        });
        //期次管理
        $("#FinPlanProfitAsset001_btn_stageManagement").click(function(){
            showStageManagement();
        });
      //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#type11Div').show();
        	$('#type12Div').show();
        }else{
        	$('#type11Div').hide();
        	$('#type12Div').hide();
        }
    });
}());