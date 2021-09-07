$(function() {
	
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
		return {
        	// 所属阶段
        	stageOfAeest : $('#stageOfAeest').val() == null ? null : $('#stageOfAeest').val()
		}
    };
    
     /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        /**
         * 双击查看
         */
        dblclick: function (table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "model/impairment/phaseparameter/ModStagePara002/init",
                "80%", "80%", {
                    pageModel: PAGE_MODEL_REFERENCE,
                    seqno: data.seqno
                });
        },
        cols: [
	
            {field: 'seqno', visible: false}
            , {field: 'stageOfAeestStr', title: 'ModStagePara001.stageOfAeest',sortable:false}
            , {field: 'absoluteOfElementsStr', title: 'ModStagePara001.absoluteOfElements',sortable:false}
            , {field: 'elementsStr', title: 'ModStagePara001.elements',sortable:false}
			, {field: 'elementsValueStr', title: 'ModStagePara001.elementsValue',sortable:false}
            , {field: 'useStatus', title: 'ModStagePara001.status',visible: false}
            , {field: 'useStatusStr', title: 'ModStagePara001.status',sortable:false}
            , {field: 'action', width: 200, title: 'SysCommon.operation', type: 'action', buttons: ['edit']}
        ]
    });
    
	
	 /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'model/impairment/phaseparameter/ModStagePara002/check', data, function (result) {
            Joyin.dialog('减值阶段参数编辑',
                basePath + "model/impairment/phaseparameter/ModStagePara002/init",
                "80%", "80%", {
                    pageModel: PAGE_MODEL_EDIT,
                    seqno: data.seqno
                }, function () {
                    table.reload();
                });
        });
    });
    
   	var exportExcel = function(){       
        Joyin.exportExcel('export',getSearchParams()) 
    };


    //启用
    $('#ModStagePara001_btn_on').click(function() {
        var type='on';
        onOffRows(type);
    });
    //停用
    $('#ModStagePara001_btn_off').click(function() {
        var type='off';
        onOffRows(type);
    });
    var onOffRows = function (type) {
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var data = {selectedItem: selArr, operateType:type};
        Joyin.ajaxJson(basePath + 'model/impairment/phaseparameter/ModStagePara001/setStageParaSwitch', data, function (result) {
            table.reload();
        });


        /*Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            WorkFlow.doDeleteList('/model/impairment/phaseparameter/ModStagePara002/setStageParaSwitch', object, "ModStagePara001_btn_on", "ModStagePara001", function(){
                table.reload();//删除事件请求及回调函数
            });
        });*/

    };
 
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
		//导出
		table.load();
        $('#ModStagePara001_btn_export').click(function() {
            exportExcel();
        });
        //初始化查询
        $('#ModStagePara001_btn_search').click(function() {
            table.load();
			/*$('#PdFactorConfig001_btn_delete').show();*/
        });
    });
});