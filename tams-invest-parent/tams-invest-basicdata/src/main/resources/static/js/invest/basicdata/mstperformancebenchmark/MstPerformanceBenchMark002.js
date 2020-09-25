/**
 * 复合基准管理
 */
$(function() {
    /**
     * 保存或提交
     * 
     * @param operateType
     *            操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');

        // 取到表格内所有数据
        var componentInfo = table.getAllRows();
        console.log(table.getAllRows());
        object.indexId = $('#indexId').val();
        object.pageModel = pageModel;
        object.operateType = operateType;
        $.each(componentInfo, function(idx,v) {
            v.componentId = v.componentId;
            v.componentType = v.componentType;
            v.weight = v.weight / 100;
        });
        componentInfo.map(function(v) {
        	if(v.componentId) {
        		var i = v.componentId.indexOf('.');
        		if(i != -1) {
        			v.componentId = v.componentId.substring(i + 1);
        		}
        	}
        });
        
        object.componentInfo = componentInfo;
        
            Joyin.ajaxJson(basePath + 'invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark002/check',
                    object, function(result) {
                        Joyin.confirm(result.data, function() {
                            if (operateType === 'save') {
                                WorkFlow.doSaveList(basePath
                                        + 'invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark002/save',
                                        object, "MstPerformanceBenchMark002_btn_save");
                            } else {
                                WorkFlow.doSubmitList(basePath
                                        + 'invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark002/save',
                                        object, "MstPerformanceBenchMark002_btn_submit");
                            }
                        });
                    });

}
    var init = function(){
        console.log(pageModel);
        if(pageModel==PAGE_MODEL_EDIT){
            $("#indexName").attr("disabled","disabled");
        }
    };
    /**
     * 页面加载初始化
     */
    var options = {
        ajax : {
            url : JoyinUtil.getRealPath('invest/basicdata/mstperformancebenchmark/MstPerformanceBenchMark002/findPage'),
            type : 'POST',
            data : function() {
                return {
                    indexId : $('#indexId').val(),
                    workflowId : $('#workflowId').val()
                };
            }
        },
        loadonce:true,
        page:false,
        checkbox : false,
        addbtn : true,
        height:'225px',
        searchOnInit : true,
        rowno:true,
        cols : [
            {field : 'componentType',title : 'MstPerformanceBenchMark002.componentType',edit : true,
            editOption:{type:'select',id:"componentType",childrenIds:'componentId',formatoption:true,result: MARKET_TYPE},sortable : false}
            ,{field : 'componentId',title : 'MstPerformanceBenchMark002.componentId', edit : true,
            editOption:{type:'select',parentIds:'componentType',id:"componentId",formatoption:true,result:INDEX_ID_SOURCE,searchable:true},sortable : false}
            ,{field : 'weight', title : 'MstPerformanceBenchMark002.weight',edit : true,sortable : false ,type:'number',digit:6}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt'] ,sortable : false}
        ],
        beforeEditCell: function(preVal, field, rowData){
            if (field=='componentId' && (rowData.componentType==''||rowData.componentType==null)) {
                Joyin.error("编辑行情代码前，行情类型不能为空");
                return false;
            }
            return true;
        },
        editCell: function(value, data, field, cell, column){
            if (field=='componentType') {
                var rowData={};rowData.componentId='';
                table.renderRow(data.LAY_TABLE_INDEX, rowData);
            }
            return true;
        }
    };

    /**
     * 绑定表格事件
     */
    var table = $("#dataTable").joyintable(options);
    if (entityInfo!=null && entityInfo.componentInfo!=null){
        $.each(entityInfo.componentInfo, function(idx,v) {
            table.addRow(v);
        })
    }

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#MstPerformanceBenchMark002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstPerformanceBenchMark002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());