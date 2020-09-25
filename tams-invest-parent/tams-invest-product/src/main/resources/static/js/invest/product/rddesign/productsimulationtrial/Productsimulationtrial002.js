var refreshTable;

(function(){
    
    /**
     * 刷新模拟资产信息
     */
    refreshTable = function(Object){
        if(Object) {
            if(Object.rateType == INT_TYPE_02) {
                Object.rateMax = Object.benchmarkMaxStr + '*' +Object.coefficientMax + '+' +Object.spreadRateMax;
                Object.rateMix = Object.benchmarkMinStr + '*' +Object.coefficientMin + '+' +Object.spreadRateMin;
            } 
            convertRow(Object);
            assetTable.addRow(Object);
            
            doSimulationTrial();
        }
    }
    
    /**
     * 转换表格数据
     */
    var convertRow = function(rowData) {
        _.each(JSON.parse(ASSET_TYPE), function(v) {
            if(v.id === rowData.assetType) {
                rowData.assetTypeStr = v.text;
            }
        });
        _.each(JSON.parse(ASSET_RISK_LEVEL), function(v) {
            if(v.id === rowData.assetRiskLevel) {
                rowData.assetRiskLevelStr = v.text;
            }
        });
       
//        _.each(JSON.parse(INDEX_ID_SOURCE), function(v) {
//            if(v.object === rowData.benchmarkMinSource) {
//                rowData.benchmarkMinSource = v.col;
//            }
//            if(v.object === rowData.benchmarkMaxSource) {
//                rowData.benchmarkMaxSource = v.col;
//            }
//        });
    }
    
    /**
     * 模拟试算
     */
    var doSimulationTrial = function (){
        var object = getPageParams();
        Joyin.ajaxJson(basePath + '/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial002/doSimulationTrial', object, function(result) {
            if(result){
                $('#rateMax').val(result.rateMax); 
                $('#rateMix').val(result.rateMix); 
                $('#mockAvailableAmt').val(result.mockAvailableAmt);
            }
        });
    }
    
    var getPageParams = function(){
        var pageParam = $.serializeToObject('#submitForm', true);
        return pageParam;
    }
    
    var getPrdScale = function() {
        if($('#mockDate').val() != null && $('#mockDate').val() != ''
           && $('#prdDesignId').val() != null && $('#prdDesignId').val() != '') {
            var param = {
                    mockDate:$('#mockDate').val(),
                    prdDesignId:$('#prdDesignId').val()
            }
            Joyin.ajaxJson(basePath + '/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial002/getPrdScale', param, function(result) {
                if(result){
                    $('#prdScale').val(result).trigger("change");
                }
            });
        }
    }

    var assetTable = $("#simulationAssetTable").joyintable({
//        ajax : {
//            url : JoyinUtil.getRealPath('/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial002/findTablePage'),
//            type : 'POST',
//            data : function() {
//                return {
//                        pageModel : pageModel,
//                        seqNo : $('#seqNo').val()?$('#seqNo').val():""
//                };
//            }
//        },
        height: 200,
        checkbox:false,
        page:false,
        rowno:false,
        cols:[
             {field:'assetTypeStr', width:200, title: 'ProductSimulationTrial002.assetType',sortable: false}
            ,{field:'assetRiskLevelStr', width:200, title: 'ProductSimulationTrial002.assetRiskLevel',sortable: false}
            ,{field:'mockAmt', width:200, title: 'ProductSimulationTrial002.mockAmt', sortable: false}
            ,{field:'mockShareAmt', width:200, title: 'ProductSimulationTrial002.mockShareAmt',sortable: false}
            ,{field:'rateMax', width:200, title: 'ProductSimulationTrial002.rateMax',sortable: false}
            ,{field:'rateMix', width:200, title: 'ProductSimulationTrial002.rateMix', sortable: false}
            ,{field:'benchmarkMin', visible: false}
            ,{field:'benchmarkMinSource', visible: false}
            //上限基准
            ,{field: 'benchmarkMax', visible: false}
            //上限基准来源
            ,{field: 'benchmarkMaxSource', visible: false}
            ,{field: 'coefficientMin', visible: false}
            ,{field: 'spreadRateMin', visible: false}
            ,{field: 'coefficientMax', visible: false}
            ,{field: 'spreadRateMax', visible: false}
            ,{field: 'assetType', visible: false}
            ,{field: 'assetRiskLevel', visible: false}
            ,{field: 'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//删除事件
        ]
    });
    
    /**
     * 绑定删除事件
     */
    assetTable.bind('click', '.delt', function(data, index) {
        assetTable.removeRow(data);
        doSimulationTrial();
    });
    
    $('#productSimulationTrial_btn_add').click(function() {
        var dialogIndex = Joyin.dialog(JoyinUtil.getLabel("ProductSimulationTrial004.add"),
            "/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial004/init",
            "98%", "95%", null, function(data) {
            
            });
    });
    
    function saveOrSubmit(operateType) {
        var object = getPageParams();
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + '/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + '/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial002/save', object, "ProductSimulationTrial002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + '/invest/product/rddesign/productsimulationtrial/ProductSimulationTrial002/save', object, "ProductSimulationTrial002_btn_submit");
                }
            });
        });
    }
    
    /**
     * 计算模拟可用现金
     */
    var countMockAvailableAmt = function (){
        debugger;
        if(assetTable) {
            var amountTotal = 0;
            var list = assetTable.getAllRows();
            for(var i=0 ; i<list.length;i++) {
                amountTotal = JoyinMath.doAdd(amountTotal, list[i].mockAmt, 2, JoyinMath.ROUND_HALF_UP);
            } 
            var prdScale = $("#prdScale").val();
            if(prdScale && amountTotal) {
                $('#mockAvailableAmt').val(JoyinMath.doMinus(prdScale,amountTotal,2,JoyinMath.ROUND_HALF_UP)); 
            }
        }
    }

    $(function () {
        
        if(assetTable.table){
            assetTable.reload();
        }else{
            assetTable.load();
        }
        
        if(pageModel != PAGE_MODEL_NEW && simulationAssetsList){
            for(var i = 0;i<simulationAssetsList.length;i++) {
                var object = simulationAssetsList[i];
                assetTable.addRow(object);  
            }
        }
        
        $('#mockDate').change(function() {
            getPrdScale();
        });
        
        $('#prdScale').change(function() {
            countMockAvailableAmt();
        });
        
        $('#prdDesignId').change(function() {
            getPrdScale();
            var requirementId = $('#prdDesignId').select2('data')[0].object;
            $('#requirementId').val(requirementId);
        });
        
        // 绑定按钮事件
        $('#ProductSimulationTrial002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ProductSimulationTrial002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });

}());