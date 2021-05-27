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
        if (typeof pageModel !== 'undefined') {
            if (pageModel === PAGE_MODEL_EDIT) {
                $('#identifyNumber').attr("disabled","disabled");
            } else if (pageModel === PAGE_MODEL_NEW) {
                $('#identifyNumber').removeAttr("disabled");
            }
        }
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

    $("#employeeType").change(function () {
        if ($("#employeeType").val() != '05') {
            $('#regBusiType').val('').trigger('change');;
            $('#regEmployeeType').val('').trigger('change');;
            $('#regBusiType').attr("disabled","disabled");
            $('#regEmployeeType').attr("disabled","disabled");
        } else {
            $('#regBusiType').removeAttr("disabled");
            $('#regEmployeeType').removeAttr("disabled");
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

            // var checkFlag = true;
            // if(object.identifyNumber==''){
            //     Joyin.error("身份证号码不能为空");
            //     checkFlag = false;
            // }else if(object.identifyNumber.length>18){
            //     Joyin.error("身份证号不能超过18");
            //     checkFlag = false;
            // }
            // if(object.employeeType==''){
            //     Joyin.error("从业人员类型不能为空");
            //     checkFlag = false;
            // }
            // if(object.employeeName==''){
            //     Joyin.error("姓名不能为空");
            //     checkFlag = false;
            // }else
            //     if(object.employeeName.length>200){
            //     Joyin.error("姓名不能超过200");
            //     checkFlag = false;
            // }
            // if(object.employeeSex==''){
            //     Joyin.error("性别不可以为空");
            //     checkFlag = false;
            // }
            // if(object.issuePartyId==''){
            //     Joyin.error("发行机构代码不能为空");
            //     checkFlag = false;
            // }else if(object.issuePartyId.length>6){
            //     Joyin.error("发行机构代码不能超过6");
            //     checkFlag = false;
            // }
            // if(object.belongsBank==''){
            //     Joyin.error("所属总行或分支行不可以为空");
            //     checkFlag = false;
            // }
            // if(object.belongsPlace==''){
            //     Joyin.error("所属区域不可以为空");
            //     checkFlag = false;
            // }else if(object.belongsPlace=='900000'){
            //     if(object.remark == ''){
            //         Joyin.error("备注不可为空");
            //         checkFlag = false;
            //     }
            // }
            // if(object.belongsDept==''){
            //     Joyin.error("所属部门不能为空");
            //     checkFlag = false;
            // }else if(object.belongsDept.length>40){
            //     Joyin.error("所属部门不能超过40");
            //     checkFlag = false;
            // }
            // if(object.position==''){
            //     Joyin.error("职位不能为空");
            //     checkFlag = false;
            // }else if(object.position.length>40){
            //     Joyin.error("职位不能超过40");
            //     checkFlag = false;
            // }
            // if(object.education==''){
            //     Joyin.error("学历不可以为空");
            //     checkFlag = false;
            // }
            // if(object.academicDegree==''){
            //     Joyin.error("学位不可以为空");
            //     checkFlag = false;
            // }
            // if(object.firsttime2work==''){
            //     Joyin.error("首次参加工作时间不可以为空");
            //     checkFlag = false;
            // }else if(object.firsttime2work.length>6){
            //     Joyin.error("首次参加工作时间必须为日期格式（yyyyMM）");
            //     checkFlag = false;
            // }
            // if(object.firsttime2finwork==''){
            //     Joyin.error("首次从事理财业务时间不可以为空");
            //     checkFlag = false;
            // }else if(object.firsttime2finwork.length>6){
            //     Joyin.error("首次从事理财业务时间必须为日期格式（yyyyMM）");
            //     checkFlag = false;
            // }
            // if(object.professionalTitle==''){
            //     Joyin.error("专业技术职称不可以为空");
            //     checkFlag = false;
            // }else if(object.professionalTitle=='99'){
            //     if(object.remark == ''){
            //         Joyin.error("备注不可为空");
            //         checkFlag = false;
            //     }
            // }
            // if(object.finProfessionalCrtf==null){
            //     Joyin.error("理财专业证书不可以为空");
            //     checkFlag = false;
            // }else if(object.finProfessionalCrtf.indexOf('99') !=-1){
            //     if(object.remark == ''){
            //         Joyin.error("备注不可为空");
            //         checkFlag = false;
            //     }
            // }
            // if(object.finTrainingNo!='' && object.finTrainingNo.length!=14){
            //     Joyin.error("理财登记培训证书编号应为14位");
            //     checkFlag = false;
            // }
            // if(object.awards==''){
            //     Joyin.error("所获奖励不能为空");
            //     checkFlag = false;
            // }else if(object.awards.length>400){
            //     Joyin.error("所获奖励不能超过400");
            //     checkFlag = false;
            // }
            // if(object.officeTel==''){
            //     Joyin.error("办公电话不能为空");
            //     checkFlag = false;
            // }else if(object.officeTel.length>30){
            //     Joyin.error("办公电话不能超过30");
            //     checkFlag = false;
            // }else{
            //     if(! /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(object.officeTel)){
            //         Joyin.error("办公电话格式不正确");
            //         checkFlag = false;
            //     }
            // }
            // if(object.mobileTel==''){
            //     Joyin.error("移动电话不能为空");
            //     checkFlag = false;
            // }else if(object.mobileTel.length>11){
            //     Joyin.error("移动电话不能超过11");
            //     checkFlag = false;
            // }else {
            //     if(! /^\d{11}$/.test(object.mobileTel)){
            //         Joyin.error("移动电话格式不正确");
            //         checkFlag = false;
            //     }
            // }
            // if(object.mail==''){
            //     Joyin.error("电子邮箱不能为空");
            //     checkFlag = false;
            // }else if(object.mail.length>50){
            //     Joyin.error("电子邮箱不能超过50");
            //     checkFlag = false;
            // }
            // if (object.employeeType == '05') {
            //    if(object.regBusiType ==null ){
            //        Joyin.error("登记业务分类不能为空");
            //        checkFlag = false;
            //    }
            //    if(object.regEmployeeType==null){
            //        Joyin.error("登记人员类别不能为空");
            //        checkFlag = false;
            //    }
            // }
            //
            // if ($("#belongsBank").val() == '02'
            //     || $("#belongsBank").val() == '03'
            //     || $("#belongsBank").val() == '04') {
            //     if ($("#companyName").val() == '') {
            //         Joyin.error("具体单位名称不能为空");
            //         checkFlag = false;
            //     }
            // }
            // if(checkFlag){
            //     Joyin.ajaxJson(basePath + 'svreport/system/chbemployee/ChbEmployeeInfo002/check', object, function(result) {
            //         if(result){
            //             Joyin.ajaxJson(basePath + 'svreport/system/chbemployee/ChbEmployeeInfo002/save',object,function(result){
            //                 var index = parent.layer.getFrameIndex(window.name);
            //                 parent.layer.close(index);
            //             });
            //         }
            //     });
            // }
            Joyin.ajaxJson(basePath + 'svreport/system/chbemployee/ChbEmployeeInfo002/check', object, function(result) {
                if(result){
                    Joyin.ajaxJson(basePath + 'svreport/system/chbemployee/ChbEmployeeInfo002/save',object,function(result){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                }
            });
        });

    });
}());