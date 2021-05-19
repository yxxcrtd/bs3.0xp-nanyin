(function() {
    function init(){
        layui.laydate.render({
            elem: '#firsttime2work',
            type: 'month',
            format: 'yyyyMM'
        });
        $("#firsttime2work").click(function (event) {
            event.stopPropagation();// 阻止事件冒泡
        });
        layui.laydate.render({
            elem: '#firsttime2finwork',
            type: 'month',
            format: 'yyyyMM'
        });
        $("#firsttime2finwork").click(function (event) {
            event.stopPropagation();// 阻止事件冒泡
        });
        $(".layui-fixbar").hide();

    };

    /**
     * 画星星
     */
    var star = function() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function() {
                $(this).remove();
            })
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }

    $("#belongsBank").change(function () {
        if ($("#belongsBank").val() == '02'
            || $("#belongsBank").val() == '03'
            || $("#belongsBank").val() == '04') {
            $('#companyName').attr("required", true);
            star();
        } else {
            $('#companyName').removeAttr("required");
            star();
        }
    })

    $(function() {
        init();
        //保存点击事件
        $('#ChbEmployeeInfo002_btn_save').click(function() {
            var object = $.serializeToObject('#submitForm',true);
            object.identifyNumber = $("#identifyNumber").val();
            object.oldIdentifyNumber = $("#oldIdentifyNumber").val();
            object.oldEmployeeType = $("#oldEmployeeType").val();
            object.employeeType = $("#employeeType").val();
            object.employeeName = $("#employeeName").val();
            object.employeeSex = $("#employeeSex").val();
            object.issuePartyId = $("#issuePartyId").val();
            object.belongsBank = $("#belongsBank").val();
            object.belongsPlace = $("#belongsPlace").val();
            object.companyName = $("#companyName").val();
            object.belongsDept = $("#belongsDept").val();
            object.position = $("#position").val();
            object.education = $("#education").val();
            object.academicDegree = $("#academicDegree").val();
            object.firsttime2work = $("#firsttime2work").val();
            object.firsttime2finwork = $("#firsttime2finwork").val();
            object.professionalTitle = $("#professionalTitle").val();
            //object.finProfessionalCrtf = $("#finProfessionalCrtf").val();
            object.finProfessionalCrtf=$('#finProfessionalCrtf').val()==null?null:$('#finProfessionalCrtf').val().join();
            object.finTrainingNo = $("#finTrainingNo").val();
            object.awards = $("#awards").val();
            object.officeTel = $("#officeTel").val();
            object.mobileTel = $("#mobileTel").val();
            object.mail = $("#mail").val();
            //object.regBusiType = $("#regBusiType").val();
            //object.regEmployeeType = $("#regEmployeeType").val();
            object.regBusiType=$('#regBusiType').val()==null?null:$('#regBusiType').val().join();
            object.regEmployeeType=$('#regEmployeeType').val()==null?null:$('#regEmployeeType').val().join();
            object.remark = $("#remark").val();
            object.type =$("#type").val();

            var checkFlag = true;
            if(object.identifyNumber==''){
                Joyin.error("身份证号码不能为空");
                checkFlag = false;
            }
            if(object.employeeType==''){
                Joyin.error("从业人员类型不能为空");
                checkFlag = false;
            }
            if ($("#belongsBank").val() == '02'
                || $("#belongsBank").val() == '03'
                || $("#belongsBank").val() == '04') {
                if ($("#companyName").val() == '') {
                    Joyin.error("具体单位名称不能为空");
                    checkFlag = false;
                }
            }
            if(checkFlag){
                Joyin.ajaxJson(basePath + 'svreport/system/chbemployee/ChbEmployeeInfo002/check', object, function(result) {
                    if(result){
                        Joyin.ajaxJson(basePath + 'svreport/system/chbemployee/ChbEmployeeInfo002/save',object,function(result){
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }
                });
            }
        });

    });
}());