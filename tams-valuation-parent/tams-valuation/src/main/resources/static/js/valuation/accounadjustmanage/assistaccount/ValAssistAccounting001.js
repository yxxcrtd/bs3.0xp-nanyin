$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function () {
        return {
            booksetId: $.trim($('#booksetId').val()),
            subjectInternalCode: $.trim($('#subjectInternalCode').val()),
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
        cellMinWidth: 100,
        cols: [
            {field: 'booksetId', title: '账套代码', width: 180, sort: true}
            , {field: 'booksetName', title: '账套名称', width: 150, sort: true}
            , {field: 'subjectInternalCode', title: '内部科目编码', width: 150, sort: true}
            , {field: 'subjectInternalName', title: '内部科目名称', width: 150, sort: true}
            , {field: 'subjectTypeName', title: '核算属性', visible: false}
            , {field: 'subjectLevel', title: '科目层级', visible: false}
            , {field: 'accFlagName', title: '是否核算项', visible: false}
            , {field: 'genDetsubRule', title: '底层科目', visible: false}
            , {field: 'hands', title: '辅助核算项LIST', visible: false}
            , {field: 'status', title: '数据状态',visible: false}
            , {
                field: 'handstr', title: '辅助核算项', width: 1200,
                render: function (data, type, row, meta) {
                    var str = "";
                    for (var i = 0; i < row.hands.length; i++) {
                        //console.log(row.hands[i])
                        //str=str+'<input type="checkbox" name="handList"  value="'+row.hands[i].handCode+'" lay-skin="primary"  title="'+row.hands[i].handName+'"  checked  disabled > '+"\n" ;
                        if ('Y' == row.hands[i].status) {
                            str = str + '<input type="checkbox"  name="handList"  value="' + row.hands[i].handCode + '"  lay-skin="primary" checked  disabled > ' + row.hands[i].handName + "\n";
                        } else {
                            str = str + '<input type="checkbox"  name="handList"  value="' + row.hands[i].handCode + '"  lay-skin="primary"   disabled > ' + row.hands[i].handName + "\n";
                        }

                    }
                    return str;
                }

            }
            , {
                field: 'operation', title: 'SysCommon.operation', type: 'action', buttons: [
                    {text: '&#xe642;', clas: 'edit', title: '编辑'}]

            }
        ],

        done: function () {
            // $("table tr").each(function () {
            //                              var s = $(this).children().eq(1).text();
            //                              console.log($(this).children());
            //                             if (s == "SUBJECT_INTERNAL_001")
            //                                      $(this).css("background","#EBEBEB");
            //
            //                         })
            layui.form.render();

        }
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function (data, index) {
        var datas = JSON.stringify(data);
        Joyin.dialog(JoyinUtil.getLabel("辅助核算配置"),
            basePath + "/valuation/accounadjustmanage/assistaccount/ValAssistAccounting002/init",
            "60%", "75%", {pageModel: PAGE_MODEL_NEW, datas: datas}
            , function () {
                table.reload();
            }
        );
    });


    function formatoption() {
        document.getElementById("booksetId").formatoption = "false";
    }

    /**
     * 画面加载完成
     */
    $(function () {


        // 绑定按钮事件
        $('#ValAssistAccounting001_btn_search').click(function () {
            table.load();

        });
    });
}());