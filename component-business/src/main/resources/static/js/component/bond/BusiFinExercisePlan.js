/**
 * 行权计划组件JS
 * @auth zhangnian
 */
(function(){
    var getElementById = function(id) {
        return $('#' + entityName_BusiFinExercisePlan + '_' + id);
    };
    //初始化
        /**
         * 定义数据表格
         */
    var cols = [
            {field:'exerciseStatus',  title: 'BusiFinExercisePlan.exerciseStatus',edit:true,editOption:{type:'select',formatoption:true,result: EXEC_TYPE}}
            ,{field:'vdate',  title: 'BusiFinExercisePlan.vdate',edit:true,type:'date'}
            ,{field:'mdate',  title: 'BusiFinExercisePlan.mdate',edit:true,type:'date'}
            ,{field:'exerciseType',  title: 'BusiFinExercisePlan.exerciseType',editOption:{type:'select', multiple: false,result: EXEC_MODE},edit:true}
            ,{field:'exerciseDate',  title: 'BusiFinExercisePlan.exerciseDate',edit:true,type:'date'}
            // 增加实际行权日 add by renping 20190927 start
            ,{field:'actExerciseDate',  title: 'BusiFinExercisePlan.actExerciseDate',edit:false,type:'date'}
            // 增加实际行权日 add by renping 20190927 start
            ,{field:'exercisePrice',  title: 'BusiFinExercisePlan.exercisePrice',edit:true,type:'number',digit:6}
            ,{field:'exerciseRemark',  title: 'BusiFinExercisePlan.exerciseRemark',edit:true}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//编辑和删除事件
        ]
    var table =$("#busiFinExercisePlanTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/component/bond/BusiFinExercisePlan/initPlan'),
            type: 'POST',
            data: getPreviewParams()
        },
        cols:cols,
        searchOnInit: true,
        contextmenu : false,
        loadonce:true,
        checkbox:false,
        height:290,
        addbtn:true,
        addclick:function(rowData){
            rowData.exerciseStatus = EXEC_TYPE_01;
        },
        beforeEditCell: function(preVal, field, rowData){
            //禁用行权状态可编辑事件
            if(field=='exerciseStatus'){
                return false;
            }
            return true;
            },
        // 增加实际行权日 add by renping 20190927 start
        editCell : function(value, data, field, cell, column){
            if (field === "exerciseDate"){
                var payTdayRule = '';
                if($('#finPayInterestStdInfoDto_payTdayRule')){
                    payTdayRule = $('#finPayInterestStdInfoDto_payTdayRule').val();
                }
                var calendarId = '';
                if($('#calendarId')){
                    calendarId = $('#calendarId').val();
                }
                var rowData = {};
                if(!calendarId || !value){
                    rowData.actExerciseDate = value;
                } else {
                    var obj = {calendarId:calendarId, exerciseDate:value, payTdayRule:payTdayRule};
                    Joyin.ajaxJson(basePath + 'component/bond/BusiFinExercisePlan/getActExerciseDate', obj, function(result) {
                        if(result){
                            rowData.actExerciseDate = result.actExerciseDate;
                        }
                    }, false);
                }
                table.renderRow(data.LAY_TABLE_INDEX, rowData);
            }
        // 增加实际行权日 add by renping 20190927 start
        }
    });
        
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    var getAllRows = function(){
            return table.getAllRows();
    }
    
    /**
     * 获取参数
     * @returns
     */
    function getPreviewParams() {
        var param = {}
        param.finprodId = $("#finprodId").val();
        param.workflowId = $("#workflowId").val();
        param.entityName = entityName_BusiFinExercisePlan;
        param.pageModel = pageModel;
        // 保存待办行权信息不显示bug对应 add by renping 20191012 start
        param.isTodoList = isTodoList;
        // 保存待办行权信息不显示bug对应 add by renping 20191012 end
        return param;
    }
    
    // 公开接口
    window.BusiFinExercisePlan = {
            getAllRows:getAllRows
    };
}());