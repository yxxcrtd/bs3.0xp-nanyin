

(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){
        var object = $.serializeToObject('#searchForm');

        return object;
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
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("EtrAutoManagementAccount002.refrence"),
                JoyinUtil.getRealPath("invest/entrust/etrautomanagementaccount/EtrAutoManagementAccount002/init"),
                "98%", "95%", data,
                function(){table.reload();});
        },
        cols:[
            {field:'entrustId',visible:false},
            {field:'entrustStatus', visible:false},
            {field:'entrustStatusStr', title:'状态',width:'10%',sortField:"entrustStatus"},
            {field:'portfolioId', title: '自主专户代码', width:'10%',sortField:"portfolioId"}
            ,{field:'finprodMarketId', title: '底层资产代码', width:'10%',sortField:"finprodMarketId"}
            ,{field:'type6Str', title: '资产一级分类', width:'10%',sortField:"entrustStatus"}
            ,{field:'type7Str', title: '资产二级分类', width:'10%',sortField:"entrustStatus"}
            ,{field:'entrustDateStr', title: '指令日期', width:'10%',sortField:"entrustStatus"}
            ,{field:'psStr', title: '交易方向', width:'10%',sortField:"entrustStatus"}
            ,{field:'entrustAmt', title: '指令金额', width:'10%',sortField:"entrustStatus"}
            ,{field:'operation', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}

        ],
    });

    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.valueDate = new Date(data.valueDate).format();
        if(!(data.entrustStatus == "02" && data.entrustDateStr== sysDate)){
            Joyin.error("仅能对当日已生效指令做修改操作");
            return;
        }//update zhagnweiqi 2021-1-9 SNSTZJY-2506 自主管理专户投资查询，对已生效的数据无法修改
        data.pageModel=PAGE_MODEL_EDIT;
        data.operateType='edit'
        //Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrLendSpecInfo002/check', data, function(result) {
        Joyin.dialog(JoyinUtil.getLabel("EtrAutoManagementAccount002.edit"),
            basePath + "invest/entrust/etrautomanagementaccount/EtrAutoManagementAccount002/init",
            "98%", "95%",data,
            function(){table.reload();});
        //});

    });


    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel(basePath+"invest/entrust/etrautomanagementaccount/EtrAutoManagementAccount001/export", searchform());
    };




    /**
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrAutoManagementAccount002'), basePath +'invest/entrust/etrautomanagementaccount/EtrAutoManagementAccount002/init', '98%', '95%');
    }


    $(function () {
        /**
         * 查询按钮
         */
        $('#EtrAutoManagementAccount001_btn_search').click(function() {
            table.load();
        });

        /**
         * 新建
         */
        $('#EtrAutoManagementAccount001_btn_add').click(function(){
            showDialog();
            table.reload()
        });

        /**
         * 导出按钮
         */
        $('#EtrAutoManagementAccount001_btn_export').click(function() {
            exportExcel();
        });


        /**
         * 关闭指令
         */
        $('#EtrAutoManagementAccount001_btn_close').click(function(){
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            for(var i=0; i<selArr.length; i++){
                // if(selArr[i].entrustStatusStr == '待接收'){
                //     Joyin.error('不能对待接收的数据进行关闭操作');
                //     return;
                // }
                if(selArr[i].entrustStatusStr == '已关闭'){
                    Joyin.error('已关闭状态的数据不允许再次关闭');
                    return;
                }
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"),"关闭");
                return;
            }
            for(var index in selArr){
                if(selArr[index].entrustStatus == '08'){
                    Joyin.error("流程中的数据不允许关闭");
                    return ;
                }
            }
            //	画面提示message【ETR_C_0002：请确认是否要关闭指令？】
            var object = {selectedItem: selArr, operateType:"close"};
            Joyin.ajaxJson(basePath + 'invest/entrust/etrautomanagementaccount/EtrAutoManagementAccount001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                    Joyin.ajaxJson(basePath + 'invest/entrust/etrautomanagementaccount/EtrAutoManagementAccount001/close', object, function(){

                        table.reload();
                    });
                });
            });
        })
    })

}());