(function() {

    var getPageParams = function() {
        return {
            secType:$('#secType').val()==null?null:$('#secType').val().join(",")
        };
    };

    var table = $('#table_data').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cols: [
            { title: "datauuid",  visible:false, field:'datauuid'},
            { title: "债券类型", field:'secTypeStr', sortField:'secType',width:'10%'},
            { title:'发行方式',field:'issueTypeStr', sortField:'issueType',width:'10%'},
            { title: "评级类型", field:'gradeTypeStr', sortField:'gradeType',width:'10%'},
            { title: "折扣率（%）", field:'discountRate', sortField:'discountRate',width:'10%'},
            {
                title : '操作',
                field : 'operation',
                type : 'action',
                width : 130,
                buttons : [
                    {text: '&#xe642;',clas: 'edit',title: '编辑'}
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
            if(data.secType == null || data.secType == '' || data.secType == undefined){
                Joyin.error("债券类型为空,数据不允许编辑");
                return;
            }else{
                data.pageModel = PAGE_MODEL_EDIT;
                data.type="update";
                Joyin.dialog(JoyinUtil.getLabel("债券折扣率配置编辑"),
                    basePath + "svreport/system/bonddiscountrate/ChbSecDiscountRate002/init",
                    "95%", "95%", data,function(){
                        table.reload();
                    });
            }

        });

    };

    $(function() {

        $('#ChbSecDiscountRate001_btn_search').click(function() {
            table.load();
        });

        tableBind();
    });
}());