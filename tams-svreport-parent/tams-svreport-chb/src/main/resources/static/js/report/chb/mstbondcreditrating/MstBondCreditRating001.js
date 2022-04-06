/**
 * 证券账户管理
 */
$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            oObject:$('#oObject').val(),
            oName:$('#oName').val(),
            status:$('#status').val(),
            shortLongTerm:$('#shortLongTerm').val(),
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
            {field:'oCode', sortable:false, width:100, title: 'MstBondCreditRating001.oCode'}
            ,{field:'oName',sortable:false,width:160,  title: 'MstBondCreditRating001.oName'}
            ,{field:'oObject', sortable:false,width:100,  title: 'MstBondCreditRating001.oObject'}
            ,{field:'gradeOrgId',sortable:false,width:180,title: 'MstBondCreditRating001.gradeOrgId'}
            ,{field:'shortLongTerm', sortable:false,width:100, title: 'MstBondCreditRating001.shortLongTerm'}
            ,{field:'gradeResult',sortable:false,width:100,title: 'MstBondCreditRating001.gradeResult'}
            ,{field:'source', sortable:false, width:100,title: 'MstBondCreditRating001.source'}
            ,{field:'gradeDate', sortable:false, width:100,title: 'MstBondCreditRating001.gradeDate',type:'date'}
            ,{field:'status', sortable:false, width:100, title: 'MstBondCreditRating001.status'}
            ,{field:'describe',sortable:false,   width:150,title: 'MstBondCreditRating001.describe'}
            ,{field:'updateTime',sortable:false,width:100, title: 'MstBondCreditRating001.updateTime',type:'date'}
            ,{field:'updateUser', sortable:false,width:100,title: 'MstBondCreditRating001.updateUser'}
            ,{field:'operation', width:100, title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['edit', 'delt']}
        ]
        
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType='edit'
        data.pageModel=PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'chb/mstbondcreditrating/mstbondcreditrating002/check', data, function(result) {
            Joyin.confirm("请确认是否修改?", function(){
                Joyin.dialog(JoyinUtil.getLabel("MstBondCreditRating002"),
                    basePath + "chb/mstbondcreditrating/mstbondcreditrating002/init",
                    "98%", "95%",data,
                    function(){table.reload();});
            });
        });

    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'chb/mstbondcreditrating/mstbondcreditrating001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'chb/mstbondcreditrating/mstbondcreditrating001/delete', object, "MstBondCreditRating001_btn_delete", "MstBondCreditRating002",function(){
                    table.reload();
                });
            });
        });
    });
    
    var exportExcel = function() {   
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };

    
    var reset = function(conditionArea,table){
        $(conditionArea).find("input").val("");
        $(conditionArea).find("select").val("").trigger("change");// select清空
    }
    /**
     * 画面加载完成
     */
    $(function() {
        //新建
        $('#MstBondCreditRating001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.create")+JoyinUtil.getLabel("MstBondCreditRating002"),
                basePath + "chb/mstbondcreditrating/mstbondcreditrating002/init",
                "95%", "80%", {pageModel:PAGE_MODEL_NEW});
        });
        // 绑定按钮事件
        $('#MstBondCreditRating001_btn_search').click(function() {
            table.load();
            $('#MstBondCreditRating001_btn_delete').show();
        });
        $('#MstBondCreditRating001_btn_export').click(function() {
            exportExcel();
        });
        $('#MstBondCreditRating001_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("没有选中数据"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'chb/mstbondcreditrating/mstbondcreditrating001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'chb/mstbondcreditrating/mstbondcreditrating001/delete', object, "MstBondCreditRating001_btn_delete", "MstBondCreditRating001",function(){
                        table.reload();
                    });
                });
            });
        });
      
        /** 事件绑定: 置空按钮 */
        $("#MstBondCreditRating001_btn_reset").click(function(event){
            reset( '#selectArea',table);
        });
        /**
         * 导入按钮
         */
        $('#MstBondCreditRating001_btn_import').click(function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax(basePath +"chb/mstbondcreditrating/mstbondcreditrating001/importFile", {
                    fileName : fileName
                },function(result){
                    if(!result || !($.isArray(result) && result.length > 0)){
                        Joyin.ajax(
                            basePath + 'chb/mstbondcreditrating/mstbondcreditrating001/importFileSave',
                            {fileName : fileName},function (data1) {
                                layui.layer.closeAll()
                                table.reload();
                            });
                    }
                });
            });
        });
    });
}());