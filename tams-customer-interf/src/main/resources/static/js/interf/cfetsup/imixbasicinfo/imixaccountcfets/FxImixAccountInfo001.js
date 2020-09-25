/**
 *
 */
(function() {
    /**
     * 获取查询参数
     */
    var searchform =function(){
        return $.serializeToObject('#searchForm');
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return searchform();
            }
        },
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("FxImixAccountInfo001.reference"),
                JoyinUtil.getRealPath("/interf/cfetsup/imixbasicinfo/imixaccountcfets/FxImixAccountInfo002/init"),
                "60%", "50%",{pageModel:PAGE_MODEL_REFERENCE,seqno:data.seqno},function(){table.reload();});
        },
        rowno:true,
        checkbox:false,
        cols:[
            {field:'seqno',visible: false}
            ,{field:'accountid',width:'125',sortField:'accountid', title: 'FxImixAccountInfo001.accountid'}//产品代码
            ,{field:'cstpid',width:'120',sortField:'cstpid', title: 'FxImixAccountInfo001.cstpid'}//六位机构码
            ,{field:'cfetsid',width:'140', sortable:'cfetsid',title: 'FxImixAccountInfo001.cfetsid'}//21位机构代码
            ,{field:'jycs',width:'100', title: 'FxImixAccountInfo001.jycs'}//交易场所
            ,{field:'cfetsname',width:'130',sortField:'cfetsname', title: 'FxImixAccountInfo001.cfetsname'}//机构名称
            ,{field:'effectflag',width:'100',sortField:'effectflag', title: 'FxImixAccountInfo001.effectflag'}//有效状态
            ,{field:'creatorid',width:'100', title: 'FxImixAccountInfo001.creatorid'}//创建人
            ,{field:'createdtime',width:'100', title: 'FxImixAccountInfo001.createdtime',type:'date'}//创建时间
            ,{field:'lastmodifierid',width:'100', title: 'FxImixAccountInfo001.lastmodifierid'}//更新人
            ,{field:'lastmodifiedtime',width:'100', title: 'FxImixAccountInfo001.lastmodifiedtime',type:'date'}//更新时间
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt']}
        ]
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {seqno:data.seqno, operateType:"edit"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            Joyin.ajax(basePath +'interf/cfetsup/imixbasicinfo/imixaccountcfets/FxImixAccountInfo001/delete', object, function(result){
                table.reload();
            });
        });
    });

    /**
     * 编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        var object = {seqno:data.seqno, operateType:"edit",pageModel:PAGE_MODEL_EDIT};
        Joyin.dialog(JoyinUtil.getLabel("FxImixAccountInfo001.edit"), basePath
            + "interf/cfetsup/imixbasicinfo/imixaccountcfets/FxImixAccountInfo002/init", "80%", "80%",
            object, function () {
            table.reload();
        });
    });

    /**
     * 编辑按钮事件
     */
    $(function () {
        table.load();
        /**
         * 查询按钮
         */
        $('#FxImixAccountInfo001_btn_search').click(function() {
            table.load();
        });

        /**
         * 新建按钮
         */
        $('#FxImixAccountInfo001_btn_add').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.create"), basePath
                + "interf/cfetsup/imixbasicinfo/imixaccountcfets/FxImixAccountInfo002/init", "80%", "80%", {
                pageModel: PAGE_MODEL_NEW
            }, function () {
                table.reload();
            });
        });


    });
}());