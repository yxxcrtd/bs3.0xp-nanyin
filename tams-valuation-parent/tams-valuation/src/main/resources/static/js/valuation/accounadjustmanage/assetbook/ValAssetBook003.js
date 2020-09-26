$(function() {
    var getCommitParams = function() {
        return {
            booksetId : $('#ValAssetBook003_id').val(),
            booksetName : $('#ValAssetBook003_name').val(),
            booksetType : $('#ValAssetBook003_type').val(),
            acctSubjectId : $('#ValAssetBook003_ACCT_SUBJECT_ID').val(),
            acctSubjectType : $('#ValAssetBook003_ACCT_SUBJECT_TYPE').val(),
            standardCcy : $('#ValAssetBook003_STANDARD_CCY').val(),
            booksetIdTmpl : $('#ValAssetBook003_BOOKSET_ID_TMPL').val(),
            acctStandard : $('#ValAssetBook003_ACCT_STANDARD').val(),
            startDate : $('#ValAssetBook003_DATE_startDate').val(),
            endDate : $('#ValAssetBook003_DATE_endDate').val()
        }
    };

    $("#ValAssetBook003_id").focus(function() {
        $("#ValAssetBook003_id").blur(function() {
            if ($("#ValAssetBook003_id").val() == 0) {
                layui.layer.alert("账套代码不能为空");
            } else {
                $.ajax({
                    type : "POST",
                    url : 'checkBooksetId',
                    data : {
                        id : $("#ValAssetBook003_id").val()
                    },
                    success : function(data) {
                        if (data) {
                            var layer = layui.layer;
                            layer.alert("账套代码已存在");
                        }
                    }
                });
            }
        });
    });

    function CompareDate(d1, d2) {
        return ((new Date(d1.replace(/-/g, "\/"))) > (new Date(d2.replace(/-/g, "\/"))));
    }

    $("#ValAssetBook003_btn_submit").click(function() {
        if ($("#ValAssetBook003_DATE_startDate").val() == 0) {
            layui.layer.alert("账套开始日不能为空");
        } else if ($("#ValAssetBook003_DATE_endDate").val() == 0) {
            layui.layer.alert("账套结束日不能为空");
        } else {
            var start = $("#ValAssetBook003_DATE_startDate").val();
            var end = $("#ValAssetBook003_DATE_endDate").val();
            if (!(CompareDate(end, start))) {
                layui.layer.alert("账套结束日不能小于等于账套开始日");
            } else {
                var data = JSON.stringify(getCommitParams());
                console.log(data);
                Joyin.ajax('update001',
                    {
                        data : data
                    },
                    function(data) {
                        if (data) {
                            console.log(data);
                            var layer = layui.layer;
                            layer.alert("更新成功");
                        } else {
                            var layer = layui.layer;
                            layer.alert("更新失败");
                        }
                });
            }
        }
    })
});