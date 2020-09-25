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
        var object = $.serializeToObject('#informationForm');

        // 取到表格内所有数据
        object.seqno = $('#seqno').val();
        object.staffid = $('#staffid').val();
        object.cstptraderid = $('#cstptraderid').val();
        object.cstptrader = $('#cstptrader').val();
        object.pageModel = pageModel;
        if("add" == pageModel){
            object.operateType = "save";
            Joyin.ajaxJson(basePath + 'interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/check',
                object, function(result) {
                    Joyin.confirm(result.data, function() {
                        WorkFlow.doSaveList(basePath
                            + 'interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/save',
                            object, "FxTraderManager002_btn_save");
                    });
                });
        }else if("edit" == pageModel){
            object.operateType = "update";
            Joyin.ajaxJson(basePath + 'interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/check',
                object, function(result) {
                    Joyin.confirm(result.data, function() {
                        WorkFlow.doSaveList(basePath
                            + 'interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/update',
                            object, "FxTraderManager002_btn_save");
                    });
                });
        }
        //table.reload();
    };
    var init = function(){
        var object = $.serializeToObject('#informationForm');
        //console.log(pageModel);
        // if(pageModel==PAGE_MODEL_EDIT){
        //
        // }
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
                    staffid : $('#staffid').val(),
                    cstptraderid : $('#cstptraderid').val(),
                    cstptrader : $('#cstptrader').val()
                };
            }
        },
        checkbox : false,
        addbtn : true,
        height:'225px',
        searchOnInit : true,
        cols : [
            {field : 'staffid',title : 'FxTraderManager.staffid',edit : true,sortable : false}
            ,{field : 'cstptraderid',title : 'FxTraderManager.cstpjyyid',edit : true,sortable : false}
            ,{field : 'cstptrader', title : 'FxTraderManager.cstptrader',edit : true,sortable : false }
        ]
    };

    /**
     * 绑定表格事件
     */
    //var table = $("#dataTable").joyintable(options);

    /**
     * 绑定删除事件
     */

    /*table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    *//**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#FxTraderManager002_btn_save').click(function() {
                saveOrSubmit();
        });
    });
}());