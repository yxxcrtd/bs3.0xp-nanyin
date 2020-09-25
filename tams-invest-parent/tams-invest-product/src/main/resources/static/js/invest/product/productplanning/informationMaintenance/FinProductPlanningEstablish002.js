(function(){
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002/save', object, "FinProductPlanningEstablish002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002/save', object, "FinProductPlanningEstablish002_btn_submit");
                }
            });
        });
    };
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var param = {};
        var planningNo = $('#planningNo').val();
        if(planningNo){
            param.planningNo = planningNo
        }
        if($('#FinProductPlanningEstablish001_bdate').val()) {
            param.baseDate = $('#FinProductPlanningEstablish001_bdate').val();
        }else{
            param.baseDate ="";
        }
        if($('#FinProductPlanningEstablish001_sdate').val()) {
            param.planningStartDate = $('#FinProductPlanningEstablish001_sdate').val();
        }else{
            param.planningStartDate ="";
        }
        if($('#FinProductPlanningEstablish001_edate').val()) {
            param.planningEndDate = $('#FinProductPlanningEstablish001_edate').val();
        }else{
            param.planningEndDate="";
        }
        param.workflowId = workflowId;
        return param;
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: basePath+'invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002/findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
        searchOnInit:true,
        loadonce: true,
        page: false,
        addbtn:true,
        cols:[
            {field:'innovationText', width:150, title: 'FinProductPlanningEstablish001.table.innovation'}
            ,{field:'planningSeriesNo', width:200, title: 'FinProductPlanningEstablish001.table.designId', type: 'input',edit:true}
            ,{field:'planningSeriesName', width:200, title: 'FinProductPlanningEstablish001.table.designSerialName', type: 'input',edit:true}
            ,{field:'initialPlanningValue', width:150, title: 'FinProductPlanningEstablish001.table.initial', type: 'number',digit:6,edit:true}
            ,{field:'planningChangeValue', width:150, title: 'FinProductPlanningEstablish001.table.change', type: 'number',digit:6}
            ,{field:'planningTargetValue', width:150, title: 'FinProductPlanningEstablish001.table.target', type: 'number',digit:6,edit:true}
            ,{field:'planningNo', visible: false}
            ,{field:'productsDue',visible: false}
            ,{field:'operateType', title: 'SysCommon.operation', type:'action',width : 80, buttons: ['delt']}
        ],
        /**
         * table编辑前联动事件
         */
        beforeEditCell: function(preVal, field, rowData){
            // if(field == "planningSeriesNo" && rowData.innovationText=="否" ||
            //     (field == "planningSeriesNo"&& pageModel==PAGE_MODEL_EDIT && rowData.innovationText=="否")){
            //     return false;
            // }
            // else if(field == "planningSeriesNo" && rowData.innovationText=="是"){
            //     return true;
            // }
            // if(field == "planningSeriesName" && rowData.innovationText=="否" ||
            //     (field == "planningSeriesName"&& pageModel==PAGE_MODEL_EDIT) && rowData.innovationText=="否" ){
            //     return false;
            // }
            // else if(field == "planningSeriesName" && rowData.innovationText=="是"){
            //     return true;
            // }
            if(field !="planningSeriesNo" && field !="planningSeriesName"){
                return true;
            }
            if(field =="planningSeriesNo" && rowData.flag=="1"){
                return true;
            }
            if(field =="planningSeriesName" && rowData.flag=="1"){
                return true;
            }
        },
        addclick: function(row) {
            if($('#FinProductPlanningEstablish001_sdate').val()===""){
                Joyin.error("请先输入规划开始日");
                table.reload();
            }
            row.initialPlanningValue = 0;
            row.planningTargetValue=0;
            row.innovationText ="是";
            row.innovation="Y";
            row.flag ="1"
        },

        editCell : function(value, data, field, cell, column) {
            if(field === "planningSeriesName" ){
                var par = {'planningSeriesName':data.planningSeriesName};
                Joyin.ajaxJson(basePath + 'invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002/findCountByName',par,
                    // {planningSeriesNo:data.planningSeriesNo},
                    function(result) {
                        if(result != 0){
                            Joyin.error("库中已经存在该数据！");
                        }
                    });
            }
            if(field === "planningSeriesNo" ){
                var par = {'planningSeriesNo':data.planningSeriesNo};
                Joyin.ajaxJson(basePath + 'invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002/findCountByNo',par,
                    // {planningSeriesNo:data.planningSeriesNo},
                    function(result) {
                        if(result != 0){
                            Joyin.error("库中已经存在该数据！");
                        }
                    });
            }
            if(field === "initialPlanningValue"){
                //设计变动  dgf 20200115  现在不根据开始结束日算变动值
                // var planningStartDate = $('#FinProductPlanningEstablish001_sdate').val();
                // var planningEndDate = $('#FinProductPlanningEstablish001_edate').val();
                if(data.flag==="1"){
                    if(parseFloat(data.planningTargetValue) - parseFloat(value)==0){
                        table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: "0"})
                    }else{
                        table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: parseFloat(data.planningTargetValue) - parseFloat(value)})
                    }
                }
                else if(data.flag != "1" && pageModel==PAGE_MODEL_EDIT){
                    var par = {'planningSeriesNo':data.planningSeriesNo,'planningStartDate':$('#FinProductPlanningEstablish001_sdate').val()};
                    Joyin.ajaxJson(basePath + 'invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002/findProductsDue',par,
                        function(result) {
                        var productsDue = result;
                        if(parseFloat(data.planningTargetValue) - parseFloat(value) + parseFloat(productsDue)==0){
                            table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: "0"})
                        }else{
                            table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: parseFloat(data.planningTargetValue) - parseFloat(value) + parseFloat(productsDue)})
                        }
                        });
                }
                else {
                    var productsDue = data.productsDue;
                    if(parseFloat(data.planningTargetValue) - parseFloat(value) + parseFloat(productsDue)==0){
                        table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: "0"})
                    }else{
                        table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: parseFloat(data.planningTargetValue) - parseFloat(value) + parseFloat(productsDue)})
                    }
                }
                //设计变动  dgf 20200115 现在不根据开始结束日算变动值
                // else if(pageModel==PAGE_MODEL_NEW){
                //     var productsDue = data.productsDufe;
                //     var actDate = DateUtil.parse(data.actDate).Format('yyyy-MM-dd');
                //     var day1 = (Date.ofDate(actDate) - Date.ofDate(planningStartDate)) / (24 * 60 * 60 * 1000);
                //     var day2 = (Date.ofDate(planningEndDate) - Date.ofDate(actDate)) / (24 * 60 * 60 * 1000);
                //     if (day1 > 0 && day2 > 0) {
                //         table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: parseFloat(data.planningTargetValue) - parseFloat(data.initialPlanningValue) + parseFloat(productsDue)})
                //     } else {
                //         table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: parseFloat(data.planningTargetValue) - parseFloat(data.initialPlanningValue)})
                //     }
                // }

            }
            if(field === "planningTargetValue") {
                //设计变动  dgf 20200115  现在不根据开始结束日算变动值
                // var planningStartDate = $('#FinProductPlanningEstablish001_sdate').val();
                // var planningEndDate = $('#FinProductPlanningEstablish001_edate').val();
                if (data.flag === "1") {
                    if(parseFloat(value) - parseFloat(data.initialPlanningValue)==0){
                        table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: "0"})
                    }
                    table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: parseFloat(value) - parseFloat(data.initialPlanningValue)})
                }
                else if(data.flag != "1" && pageModel==PAGE_MODEL_EDIT){
                    var par = {'planningSeriesNo':data.planningSeriesNo,'planningStartDate':$('#FinProductPlanningEstablish001_sdate').val()};
                    Joyin.ajaxJson(basePath + 'invest/product/productplanning/informationMaintenance/FinProductPlanningEstablish002/findProductsDue',par,
                        function(result) {
                            var productsDue = result;
                            if(parseFloat(value) - parseFloat(data.initialPlanningValue) + parseFloat(productsDue)==0){
                                table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: "0"})
                            }else{
                                table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: parseFloat(value) - parseFloat(data.initialPlanningValue) + parseFloat(productsDue)})
                            }
                        });
                }
                else{
                    var productsDue = data.productsDue;
                    if(parseFloat(value) - parseFloat(data.initialPlanningValue) + parseFloat(productsDue)==0){
                        table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: "0"})
                    }else{
                        table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: parseFloat(value) - parseFloat(data.initialPlanningValue) + parseFloat(productsDue)})
                    }
                }
                //设计变动  dgf 20200115  现在不根据开始结束日算变动值
                // else if(pageModel==PAGE_MODEL_NEW){
                //     var actDate = DateUtil.parse(data.actDate).Format('yyyy-MM-dd');
                //     var productsDue = data.productsDue;
                //     var day1 = (Date.ofDate(actDate) - Date.ofDate(planningStartDate)) / (24 * 60 * 60 * 1000);
                //     var day2 = (Date.ofDate(planningEndDate) - Date.ofDate(actDate)) / (24 * 60 * 60 * 1000);
                //     if (day1 > 0 && day2 > 0) {
                //         table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: parseFloat(data.planningTargetValue) - parseFloat(data.initialPlanningValue) + parseFloat(productsDue)})
                //     } else {
                //         table.renderRow(data.LAY_TABLE_INDEX, {planningChangeValue: parseFloat(data.planningTargetValue) - parseFloat(data.initialPlanningValue)})
                //     }
                // }
            }
            return true;
        }
    });

        $('#FinProductPlanningEstablish001_sdate').change(function () {
            var planningStartDate= $("#FinProductPlanningEstablish001_sdate").val();
            if(planningStartDate <  platDate){
                Joyin.error("规划开始日需大于平台日！");
                return;
            }
            //规划结束日值为规划开始日所在年的年末（自然日）
            var planningEndDate= Date.ofDate(planningStartDate).getFullYear()+"-12-31";
            $("#FinProductPlanningEstablish001_edate").val(planningEndDate);
        });
        $('#FinProductPlanningEstablish001_edate').change(function () {
            var planningStartDate = $('#FinProductPlanningEstablish001_sdate').val();
            var planningEndDate = $('#FinProductPlanningEstablish001_edate').val();
            if(planningEndDate < planningStartDate){
                Joyin.error("规划结束日不能小于规划开始日！");
                return;
            }
        });
        $('#FinProductPlanningEstablish001_bdate').change(function () {
            var baseDate = $('#FinProductPlanningEstablish001_bdate').val();
            if(baseDate >=  platDate){
                Joyin.error("规划基准不能大于或者等于规划开始日！");
                return;
            }
        });

    function tableLoadCheck() {
        var vdate = $('#FinProductPlanningEstablish001_sdate').val();
        var edate = $('#FinProductPlanningEstablish001_edate').val();
        var benchDate = $('#FinProductPlanningEstablish001_bdate').val();
        if(vdate != null && vdate.length > 0
            && edate != null && edate.length > 0
            && benchDate != null && benchDate.length > 0 ){
            table.reload();
        }
    }

    $('#FinProductPlanningEstablish001_sdate').change(function () {
        table.clear();
        tableLoadCheck();
    });
    $('#FinProductPlanningEstablish001_edate').change(function () {
        table.clear();
        tableLoadCheck();
    });
    $('#FinProductPlanningEstablish001_bdate').change(function () {
        table.clear();
        tableLoadCheck();
    });

    /*
     * 绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams());
    };

    var init = function(){
        if(pageModel==PAGE_MODEL_EDIT){
            $('#FinProductPlanningEstablish001_sdate').attr('disabled',true);
            $('#FinProductPlanningEstablish001_edate').attr("disabled",true);
            $('#FinProductPlanningEstablish001_bdate').attr('disabled',true);
        }
    };
    /**
     * 查询要素变更后需重新点击查询
     */
    function itemChange(){
        flag = false;
    }
    $(function() {
        init();
        // 绑定按钮事件
        $('#FinProductPlanningEstablish002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#FinProductPlanningEstablish002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());