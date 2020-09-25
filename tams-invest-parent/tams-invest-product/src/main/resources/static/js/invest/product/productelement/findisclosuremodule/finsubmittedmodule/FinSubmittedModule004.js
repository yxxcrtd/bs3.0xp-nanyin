/**
 * FinSubmittedModule004 申报附件模板附件明细
 */
$(function() {
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return {businessId : seqno};
            }
        },
        checkbox:false,
        searchOnInit:true,
        cols:[
              {field:'businessId',visible:false}
             ,{field:'fileName',
                render : function(data, type, row, meta) {
                    return '<a href="' + JoyinUtil.getRealPath('/framework/download/' + row.seqno) + '">'+data+'</a>';
                }
              }
             ,{field:'operation',type:'action', buttons: ['delt']}
         ],
    });
    /**
     * 绑定表格删除事件
     */
    table.bind('click','.delt', function(row) {
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            Joyin.ajaxJson(basePath + 'invest/product/productelement/findisclosuremodule/finsubmittedmodule/FinSubmittedModule004/delete', 
                {businessId: row.businessId}, function(result){
                    table.reload();
                    });
        });
    });
}());