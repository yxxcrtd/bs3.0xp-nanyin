(function () {
    /**
     * 获取查询参数
     */

    var getSearchParams = function () {
        return {
            searchValue: $('#searchValue').val()
        };
    };
    /**
     * 定义数据表格
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: basePath + 'disclosure/information/reveal/reportconfirm/ReportConfirmManage005/findPage',
            type: 'POST',
            data: function () {
                return getSearchParams();
            }
        },
        checkbox: true,
        page:false,
        searchOnInit:true, //初始化立即加载数据
        height:230,
        cols: [
            { field: 'userId', title: 'userId', visible: false },
            { field: 'userName', title: '用户名', width: 90,sort: true},
            { field: 'realName', title: '真实姓名', sort: false}
        ]
    });

    var submit = function(){
        var rowArr = table.getAllCheckedRows();// 所有选中行数据
        if(rowArr == null || rowArr.length ==0){
            layer.msg("请选择1个审批人！");
        }else if(rowArr.length !=1){
            layer.msg("只能选择1个审批人！");
        }
        else {
            top.data=rowArr[0];
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);

        }
    }

    /**
     * 画面加载完成
     */
    $(function () {
        $('#btnSearch').click(function () {
            table.load();
        });

        $('#ReportConfirmManage005_btn_submit').click(function () {
            submit();
        });

        $('#ReportConfirmManage005_btn_cancel').click(function () {
            Joyin.confirm("请确认是否要关闭?", function(){
                top.data=null;
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);

            }, function() {
                console.log('取消');
            });
        });
    });
}());
