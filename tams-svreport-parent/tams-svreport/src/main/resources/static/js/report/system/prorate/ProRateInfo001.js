(function() {

    var getPageParams = function() {
        var regCode = $('#ProRegistCodeManage001_regCode').val();
        return {
            // 产品代码
            prodId : $('#prod_id').val() == null ? null : $('#prod_id').val()
                .join(","),
            //投资者类型
            investor : $('#ProRegistCodeManage001_investor').val(),
            //产品登记编码
            regCode : regCode,
            isNotNull : $("#ProRegistCodeManage001_isNotNull").prop("checked"),

            orgCode : orgCode,
        };
    };

    var table = $('#ProRateInfo001_prorate').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cols: [
            { title: "数据状态", field:'status',width:'12%'},
            { title: "产品代码", field:'prodId',width:'12%'},
            { title: "启用日期", field:'startDate',width:'15%'},
            { title: "信披费用类型", field:'cashType2',width:'10%'},
            { title: "费率", field:'feeRate',width:'12%'},
            { title: "创建人", field:'createUser',width:'8%'},
            { title: "创建时间", field:'createTime',width:'8%'},
            { title: "更新人", field:'updateUser',width:'10%'},
            { title: "更新时间", field:'updateTime', width:'8%'},
            { title: "数据来源", field:'dataSource', width:'10%'},
            {
                title : '操作',
                field : 'operation',
                type : 'action',
                width : 150,
                buttons : [
                    {text: '&#xe642;',clas: 'edit',title: '编辑'},
                    {text: '&#xe640;',clas: 'delt',title: '删除'}
                    ]
            }

        ]
    });

    /**
     * 事件绑定
     */
    var tableBind = function () {
        /**
         * 绑定事件：修改
         */
        table.bind('click', '.edit', function(data, index) {
            Joyin.dialog(JoyinUtil.getLabel("编辑产品费率信息（ "+ "<span style='color:red'> * </span>" + "必填）"),
                basePath + "valuation/finncproductbuild/FinNCProductBuild002/init",
                "95%", "95%", {pageModel:PAGE_MODEL_EDIT, finprodId: data.finprodId, operateType: "edit"},function(){
                    table.reload();
                });
        });

        /**
         * 绑定事件：删除
         */
        table.bind('click', '.delt', function(data, index) {
            var object = {};
            var listdto=[data];
            object.listFinNCProductBuildDto = listdto;
            object.operateType = "delete";
            Joyin.ajaxJson(basePath + 'valuation/finncproductbuild/FinNCProductBuild001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'valuation/finncproductbuild/FinNCProductBuild001/delete', object, "FinNCProductBuild001_btn_delete", "FinNCProductBuild002",function() {
                        table.reload();
                    });
                });
            });
        });

    };

    $(function() {
        //导出点击事件
        $('#ProRateInfo001_btn_export').click(function() {

        });

        //新增点击事件
        $('#ProRateInfo001_btn_add').click(function() {
            var data={"operateType":"add"};
            Joyin.dialog('产品费率信息新建',basePath+'svreport/system/prorate/ProRateInfo002/init', '98%', '95%',data,function(){table.reload();});
        });

        //修改、删除事件绑定
        tableBind();
    });
}());