/**
 *
 */
(function () {
    /**
     * 初始化  serializeObject
     */
    function initSerializeObject() {
        $.fn.serializeObject = function () {
            let o = {};
            let a = this.serializeArray();
            $.each(a, function () {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
    }
    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 保存
         */
        $('#FxImixAccountInfo002_btn_save').click(function () {
            // 初始化 序列化对象的方法
            initSerializeObject();
            //获取 from表单的数据
            var object = $.serializeToObject('#submitForm', true);
            object.pageModel = pageModel;
            //let jsonData = $("#submitForm").serializeObject();
            var url=null;
            console.log(object.pageModel);
            if(object.pageModel==0){//新建
                 url=basePath+"interf/cfetsup/imixbasicinfo/imixaccountcfets/FxImixAccountInfo002/save";
            }else if(object.pageModel==1){//编辑
                url=basePath+"interf/cfetsup/imixbasicinfo/imixaccountcfets/FxImixAccountInfo002/update";
            }
            $.ajax({
                    type: "POST",
                    url: url,
                    data:JSON.stringify(object),
                    dataType:"json",
                    contentType:"application/json;charset=utf-8",
                    async: false,
                    error: function (request) {//失败
                        //调用父页面方法
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);//关闭弹出的子页面窗口
                    },
                    success: function (data) {//成功
                        // statuCode: 1, message: "该产品已经存在映射关系
                        debugger;
                        console.log(data);
                        if(data.statuCode==1){
                            JoyinUtil.showMessage(data.message,'error');
                        }else {
                            //调用父页面方法
                            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                            parent.layer.close(index);//关闭弹出的子页面窗口
                        }
                    }
                });
        });
        // 机构六位码联动事件
        $('#cstpid').change(function() {
            var cfetsname = $("#cstpid").val();
            if(cfetsname==null|| cfetsname==''){
                return ;
            }
            var cstpid=$("#cstpid").text().replace(/[\r\n]/g,"").replace(/\ +/g,"");
            $("#cfetsname").attr('value', cfetsname);
            var obj={'cstpid':cstpid};
            $.ajax({
                type: "POST",
                url: basePath+"interf/cfetsup/imixbasicinfo/imixaccountcfets/FxImixAccountInfo002/findJgdm",
                data:JSON.stringify(obj),
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                async: false,
                error: function (request) {//失败
                   console.log(request);
                },
                success: function (data) {//成功
                    $("#cfetsid").attr('value', data.jgdm);
                }
            });
        });
    });

}());