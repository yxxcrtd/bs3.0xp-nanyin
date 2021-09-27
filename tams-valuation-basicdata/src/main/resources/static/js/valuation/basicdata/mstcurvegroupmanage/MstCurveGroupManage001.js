$(function () {

    /**
     * 获取表单参数
     */

    var getSearchParams = function () {
        return {
            curveGroupCode: $('#curveGroupCode').val(),
            curveGroupName:$('#curveGroupName').val(),
            valueSource:$('#valueSource').val()
        };
    };

    var table = $("#dataTable").joyintable({

        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },


        /*
         * 定义表头
         */
        sortfont: true,//高亮排序
        cols: [
            //曲线代码
            {field: 'curveGroupCode',  title: '曲线代码'}
            //曲线名称
            , {field: 'curveGroupName',  title: '曲线名称'}
            //曲线来源 字典：VALUATION_SOURCE
            ,{field: 'valueSource',  title: 'MstCurveDefManage001.valueSource', visible: false}
            , {field: 'valueSourceName',  title: 'MstCurveDefManage001.valueSource'}
            ,{field:'operation', width:120,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });

    var updateEffStatus = function (effStatus) {
        //获得表中选中的对象
        var object = table.getAllCheckedRows();
        if (object.length == 0) {
            Joyin.alert("您还未选中数据");
            return;
        }


        var commitObj = {
            selectedItem: object
        }


    }



    /**
     * 新建
     */
    function showDialog() {
        Joyin.dialog('新建收益率曲线族', basePath +'valuation/basicdata/mstcurvegroupmanage/MstCurveGroupManage002/init', '85%', '75%',
            {pageModel:PAGE_MODEL_NEW},function(){
                table.reload();
            });
    }

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete",curveGroupCode: data.curveGroupCode};
        Joyin.ajaxJson(basePath + 'valuation/basicdata/mstcurvegroupmanage/MstCurveGroupManage001/deleteCheck', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'valuation/basicdata/mstcurvegroupmanage/MstCurveGroupManage002/save', object, "MstCurveGroupManage001_btn_delete", "MstCurveGroupManage002",function(){
                    table.reload();
                });
            });
        });
    });

    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'valuation/basicdata/mstcurvegroupmanage/MstCurveGroupManage002/check', data, function (result) {
            Joyin.dialog('收益率曲线编辑',
                basePath + "valuation/basicdata/mstcurvegroupmanage/MstCurveGroupManage002/init",
                "85%", "75%", {
                    pageModel: PAGE_MODEL_EDIT,
                    curveGroupCode: data.curveGroupCode
                }, function () {
                    table.reload();
                });
        });
    });


    $(function () {

        // 绑定按钮事件
        $('#MstCurveGroupManage001_btn_search').click(function () {
            table.load();
        });

        // 绑定重置按钮事件
        $('#MstCurveGroupManage001_btn_reset').click(function(){
            window.location.reload();
        });

        //绑定新增按钮事件
        $('#MstCurveGroupManage001_btn_add').click(function() {
            showDialog();
        });



    });
}());

