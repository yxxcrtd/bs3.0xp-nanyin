(function() {
    var editFlag = true;
    var getSearchParams=function(){
//        return $.serializeToObject($("#submitForm"));
        var dto = {};
        dto.finprodName = $("#finprodName").text();
        dto.finprodId = $("#finprodId").val();
        dto.gradeOrgId = $("#gradeOrgId").val();
        dto.oType = $("#oType").val();
        return dto;
    };

    // 评级机构类型onchange事件
    $("#grade_party_type").on('change',function(){
        if($("#gradeOrgType").val() == '02'){
            $("#grade_org_id").show();
        }else{
            $("#grade_org_id").hide();
            $("#gradeOrgId").val("");
        }
    });

    $("#finprodName").on('change',function(){
        if($('#finprodName').val()&&$('#finprodName').val()!=""){
            $("#finprodId").val($('#finprodName').select2('data')[0].id);
        }else {
            $("#finprodId").val("").trigger('change');
        }
    });
    //页面全部加载完毕后
    $(document).ready(function(){
        if($("#gradeOrgType").val() == '02'){
            $("#grade_org_id").show();
        }else{
            $("#grade_org_id").hide();
            $("#gradeOrgId").val("");
        }

        if(pageModel!=PAGE_MODEL_NEW){
            $("#finprodName").attr('disabled',true);
            editFlag=false;
        }
    });

    // zhujiabin add 20181018 end
    $('#Specialassets002_btn_save').click(function() {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.workflowId = workflowId;
        object.finprodName = $("#finprodName").val();
        object.finprodId = $("#finprodId").val();
        object.gradeOrgId = $("#gradeOrgId").val();
        object.oType = $("#oType").val();
        object.seqno=SEQNO;
        object.operateType = 'save';
        Joyin.ajaxJson("/model/impairment/assetratingallocation/specialassets/Specialassets002/check",object,function(result){
                Joyin.confirm(result.data, function () {
                        WorkFlow.doSaveList('/model/impairment/assetratingallocation/specialassets/Specialassets002/save', object, "Specialassets002_btn_save");
                });
        })
    });
    $('#Specialassets002_btn_submit').click(function() {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.workflowId = workflowId;
        object.finprodName = $("#finprodName").val();
        object.finprodId = $("#finprodId").val();
        object.gradeOrgId = $("#gradeOrgId").val();
        object.oType = $("#oType").val();
        object.seqno=SEQNO;
        object.operateType = 'submit';
        Joyin.ajaxJson("/model/impairment/assetratingallocation/specialassets/Specialassets002/check",object,function(result){
                Joyin.confirm(result.data, function () {
                    WorkFlow.doSubmitList('/model/impairment/assetratingallocation/specialassets/Specialassets002/save', object, "Specialassets002_btn_submit");
                });
        })
    });
}());