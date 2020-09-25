/**
 * 资金划拨管理
 */
(function() {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        var pageParam =  {};
        return pageParam;
    };

    /**
     * 日志
     */
    var logTable = $("#logTable").joyintable({
        ajax: {
            url: 'selectForPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        rowno:true,
        checkbox:false,
        cols : [
            {field : 'fileName', title : 'ServerManage001.fileName', width : '70%'},
            {field : 'fileSize', title : 'ServerManage001.fileSize', width : '30%'},
            {field:'operation', title: 'SysCommon.operation', type:'action',
                render:function (value,visible,row,options) {
                   return '<a href="javascript:void(0)" class="download"><i class="layui-icon layui-icon-download-circle"></i></a>';
                }
            }
        ]
    });

    /**
     * 绑定下载事件
     */
    logTable.bind('click', '.download', function(data, index) {
        alert(0);
    });

    /**
     * 刷新 登录 登出
     * @param rid
     * @param operateType
     */
    function refreshOrlogin(rid,operateType){
        var params = {
            pageModel: operateType
        };
        if(operateType == 'refresh'){
            Joyin.ajaxJson(basePath + 'interf/cfetsup/imixbasicinfo/servermanage/ServerManage001/refresh', params,
                function(result) {//请求路径，请求参数回调函数
                    if(result){
                        var data = result.data;
                        $("#status" + rid).val(data.status);
                        $("#beatTime" + rid).val(data.beatTime);
                    }
            });
        }else if(operateType == 'login'){
            Joyin.ajaxJson(basePath + 'interf/cfetsup/imixbasicinfo/servermanage/ServerManage001/login', params,
                function(result) {//请求路径，请求参数回调函数

            });
        }else if(operateType == 'loginOut'){
            Joyin.ajaxJson(basePath + 'interf/cfetsup/imixbasicinfo/servermanage/ServerManage001/loginOut', params,
                function(result) {//请求路径，请求参数回调函数

            });
        }
    }

    $(function () {

        //选项卡切换事件
        $('.layui-tab-title li').click(function() {
            var rid = $(this).attr("rid");
            $(".form-button-area").hide()
            if(rid == '2'){
                $(".form-button-area").show()
            }
        });

        // 绑定按钮事件
        $('#ServerManage001_btn_search').click(function() {
            var rid = $(".layui-this").attr("rid");
            if(rid == '2'){
                logTable.load();
            }
        });

        //刷新按钮事件
        $('.btn-refresh').click(function() {
            var rid = $(this).attr("rid");
            refreshOrlogin(rid,"refresh");
        });
        //登录按钮事件
        $('.btn-login').click(function() {
            var rid = $(this).attr("rid");
            refreshOrlogin(rid,"login");
        });
        //登出按钮事件
        $('.btn-login-out').click(function() {
            var rid = $(this).attr("rid");
            refreshOrlogin(rid,"loginOut");
        });
    });
}());