/**
 * FinPlanNetAsset001 计划类资产（净值型）查询、删除和导出
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var finprodMarketId = $('#finprodMarketId').val();
        var finprodId = $('#finprodMarketId_hidden').val();
        var category = $('#category').val();
        var type = $('#type').val();
        //新增行内五级分类zhangpuyu 2019.7.22 start
        var type9 = $('#type9').val();
        var type10 = $('#type10').val();
        var type11 = $('#type11').val();
        var type12 = $('#type12').val();
        //新增行内五级分类 zhangpuyu2019.7.22 end
        var transMode = $('#transMode :selected').text();
        var ccy = $('#ccy').val();
        var manager = $('#manager').val();
        // add by 张普裕  2019/9/27  增加托管人、实际管理人、发行人字段 资产推荐方 start
        var trusteeId=$('#trusteeId').val();
        var actualManager=$('#actualManager').val();
        var issuer=$('#issuer').val();
        // add by 张普裕  2019/9/27  增加托管人、实际管理人、发行人字段 资产推荐方 end
        var startVDate = $('#vdate').startDate();
        var endVDate = $('#vdate').endDate();
        var startMDate = $('#mdate').startDate();
        var endMDate = $('#mdate').endDate();
        var purSpeed = $('#purSpeed').val();
        var redSpeed = $('#redSpeed').val();
        return {
            finprodMarketId : finprodMarketId,
            finprodId : finprodId,
            category : category ? category.join(','):'',
            type : type ? type.join(','):'',
            //新增行类五级分类 zhangpuyu2019.7.22 start
            type9 : type9 ? type9.join(','):'',
            type10 : type10 ? type10.join(','):'',
            type11 : type11 ? type11.join(','):'',
            type12 : type12 ? type12.join(','):'',
            //新增行类五级分类 zhangpuyu2019.7.22 end
            transMode : transMode,
            ccy : ccy,
            manager : manager,
            // add by 张普裕  2019/9/27  增加托管人、实际管理人、发行人字段 资产推荐方 start
            trusteeId : trusteeId,
            actualManager : actualManager,
            issuer : issuer ,          
            // add by 张普裕  2019/9/27  增加托管人、实际管理人、发行人字段 资产推荐方 end
            startVDate : startVDate,
            endVDate : endVDate,
            startMDate : startMDate,
            endMDate : endMDate,
            purSpeed:purSpeed,
            redSpeed:redSpeed
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
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/asset/plan/planNet/FinPlanNetAsset002/init",
                    "99%", "99%", {pageModel:PAGE_MODEL_REFERENCE,
                                   finprodId:data.finprodId});
        },
        cols:[
             {field:'finprodId',visible :false}
//            ,{field:'manager',visible :false}
            ,{field:'calendarId',visible :false}
            ,{field:'capiIncomeFeature',visible :false}
//            ,{field:'trusteeId',visible :false}
            ,{field:'issueAmt',visible :false}
            ,{field:'finprodMarketId',title: 'FinPlanNetAsset001.finProdMarketId',width:180}
            ,{field:'finprodType2',visible :false}
            ,{field:'finprodName',title: 'FinPlanNetAsset001.finProdName',width:150}
            ,{field:'categoryAbbr', title: 'FinPlanNetAsset001.category',sortField:'category',visible :false}
            ,{field:'typeAbbr', title: 'FinPlanNetAsset001.type',sortField:'type',visible :false}
            //新增行类五级分类 zhangpuyu2019.7.22 start
            ,{field:'type9Abbr', title: 'FinPlanNetAsset001.type9',sortField:'type9',width:150}
            ,{field:'type10Abbr', title: 'FinPlanNetAsset001.type10',sortField:'type10',width:150}
            ,{field:'type11Abbr', title: 'FinPlanNetAsset001.type11',sortField:'type11',width:150,visible:IS_SHOW_TYPE == '01'?true:false}
            ,{field:'type12Abbr', title: 'FinPlanNetAsset001.type12',sortField:'type12',width:150,visible:IS_SHOW_TYPE == '01'?true:false}
            //新增行类五级分类 zhangpuyu2019.7.22 start
            /* ,{field:'transMode', title: 'FinPlanNetAsset001.transMode'}*/
            //update by  zhangnian 20190916 start
            ,{field:'purSpeed', title: 'FinPlanNetAsset002.purSpeed',width:150}
            ,{field:'redSpeed', title: 'FinPlanNetAsset002.redSpeed',width:150}
            //update by  zhangnian 20190916 end
            ,{field:'ccyAbbr', title: 'FinPlanNetAsset001.currency',sortField:'ccy',width:150}
            ,{field:'vdate', title: 'FinPlanNetAsset001.vDate',type:'date',width:150}
            ,{field:'mdate', title: 'FinPlanNetAsset001.mDate',type:'date',width:150}
            ,{field:'manager',title: 'FinPlanNetAsset002.manager',width:150}
            // add by 张普裕  2019/9/27  增加托管人、实际管理人、发行人字段 资产推荐方 start
            ,{field:'trusteeId',title: 'FinPlanNetAsset002.trusteeId',width:150}
            ,{field:'actualManager',title: 'FinPlanNetAsset002.actualManager',width:150}
            ,{field:'issuer',title: 'FinPlanNetAsset002.issuer',width:150} 
            // add by 张普裕  2019/9/27  增加托管人、实际管理人、发行人字段 资产推荐方 end
            // 增加推荐方与推荐费率 add by renping 20191014 start
            ,{field:'recommenderName',  title: 'FinPlanNetAsset002.recommender', width:150, sortField:'recommender'}
            // 增加推荐方与推荐费率 add by renping 20191014 end
            ,{field:'trialNo',visible :false}
            ,{field:'contractNo',visible :false}
            ,{field:'operation',width:100,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        // 编辑时走FinPlanNetAsset001的check by 赵东东2019.10.15  start
        Joyin.ajaxJson(basePath + 'invest/asset/plan/planNet/FinPlanNetAsset001/check', data, function(result) {
        // 编辑时走FinPlanNetAsset001的check by 赵东东2019.10.15  start
            Joyin.ajax(basePath + 'invest/asset/common/editCheck', data, function(editResult){
            Joyin.dialog(JoyinUtil.getLabel("FinPlanNetAsset001.edit"),
                    basePath + "invest/asset/plan/planNet/FinPlanNetAsset002/init",
                    "99%", "99%", {pageModel:PAGE_MODEL_EDIT,
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
        Joyin.ajaxJson(basePath + 'invest/asset/plan/planNet/FinPlanNetAsset001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/asset/plan/planNet/FinPlanNetAsset001/delete', object, 
                        "FinPlanNetAsset001_btn_delete", "FinPlanNetAsset002",function() {
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
        $('#FinPlanNetAsset001_btn_search').click(function() {
            table.load();
        });
        $('#FinPlanNetAsset001_btn_export').click(function() {
            exportExcel();
        });
        $('#FinPlanNetAsset001_btn_create').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("FinPlanNetAsset002"),
                        basePath + "invest/asset/plan/planNet/FinPlanNetAsset002/init",
                        "99%", "99%", {pageModel:PAGE_MODEL_NEW});
        });
        $('#FinPlanNetAsset001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) { 
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/asset/plan/planNet/FinPlanNetAsset001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                        WorkFlow.doDeleteList(basePath + 'invest/asset/plan/planNet/FinPlanNetAsset001/delete', object, 
                            "FinPlanNetAsset001_btn_delete","FinPlanNetAsset002");
                });
            });
        });
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#type11Div').show();
        }else{
        	$('#type11Div').hide();
        }
    });
}());