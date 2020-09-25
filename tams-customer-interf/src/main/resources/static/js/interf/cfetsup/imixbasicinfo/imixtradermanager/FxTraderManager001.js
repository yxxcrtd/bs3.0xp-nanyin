(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        var pageParam = {
/*            // notesid
            notesid: $('#notesid').val()*/
            // 用户名
            staffcode: $('#staffcode').val()
            // 人员姓名
            , staffname: $('#staffname').val()
            // 外汇交易中心交易员名称
            , cstptrader: $('#cstptrader').val()
        };
        return pageParam;
    };
    /**
     * 定义数据表格
     */

    var table = $("#dataTable").joyintable({
        // ajax请求数据
        ajax: {
            // 请求路径
            url: 'selectForPage',
            // 请求类型
            type: 'POST',
            data: function () {
                // 获取请求参数
                return getSearchParams();
            }
        },
        rowno:true,
        checkbox: false,
        dblclick: function (table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("FxTraderManager.reference"),
                JoyinUtil.getRealPath("interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/init"),
                "99%", "99%", data
                , function(){table.reload();}
            );
        },
        /**
         * 定义表头
         */
        cols: [
            {field: 'seqno', width:100, sortField:'seqno', title: 'FxTraderManager.notesid',visible: false}
            , {field: 'staffcode', width:300, sortField:'staffcode', title: 'FxTraderManager.user'}
            , {field: 'staffname', width:300, sortField:'staffname', title: 'FxTraderManager.username'}
            ,{field:'cstptrader',sortField:'cstptrader', width:300, title: 'FxTraderManager.cstptrader'}
            , {field: 'cstptraderid', width:300, sortField:'cstptraderid', title: 'FxTraderManager.cstpjyyid'}
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit', 'delt']}
            ]
    });




    var doOperate = function () {
        var selArr;
        if (table != null && table != '') {
            selArr = table.getAllCheckedRows();
        }
        // 列表中未勾选
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }

        Joyin.exportExcel('exportMailModel', {tradeId: tradeId.join(",")});
    };
    /**
     * 新建
     */
    function add() {
        var data={"operateType":"add"};
        data.pageModel="add";
        Joyin.dialog(JoyinUtil.getLabel('FxTraderManager.new'),basePath+'interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/init', '98%', '95%',data,function(){table.reload()});
    }
    /**
     * 修改
     */
    function modify(table,data) {
        /*data.operateType = "edit";
        data.pageModel=PAGE_MODEL_EDIT;*/
        Joyin.dialog(JoyinUtil.getLabel('FxTraderManager.modify'),basePath+'interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/init', '98%', '95%',{pageModel:PAGE_MODEL_EDIT,
            seqno:data.seqno},function(){table.reload()});
    }
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.operateType = "edit";
        data.pageModel="edit";
        //Joyin.ajaxJson(basePath + 'interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("FxTraderManager.edit"),
                basePath + "interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/init",
                "95%", "90%", {pageModel:"edit",
                    seqno:data.seqno},function(){table.reload();});
       // });
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {seqno:data.seqno, operateType:"edit"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            Joyin.ajax(basePath +'interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/delete',{seqno:data.seqno}, function(result){
                table.reload();
            });
        });
    });
    /**
     * 画面加载完成
     */
    $(function () {
        // 初始化查询
        $('#FxTraderManager001_btn_search').click(function () {
            table.load();
        });
        /**
         * 新建按钮
         */
        $('#FxTraderManager_btn_create').click(function() {
            add();
        });
        /**
         * 修改按钮
         */
        $('#FxTraderManager_btn_modify').click(function() {
            modify();
        });
        /**
         * 批量删除按钮
         */
        $('#FxTraderManager_btn_delete').click(function() {
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    Joyin.ajaxJson(basePath + 'interf/cfetsup/imixbasicinfo/imixtradermanager/FxTraderManager002/delete', object, function(result) {
                        table.reload();
                    });
                });
            });
        });
    });
}());