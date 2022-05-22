(function() {
    var prodIdInit = $('#prodId').val();
    if(''==prodIdInit||null==prodIdInit){
        //新建页面
        $('#issuePartyId').val(issuePartyId_New);
        $('#prodId').blur(function (){
            if($('#prodId').val()!=$('#prodInsId').val()){
                layer.msg('如果想修改申报时候的行内标识码，请同步维护产品登记编码管理信息！')
            }
        });
    }else{
        //修改页面
        $("#prodId").attr("disabled","disabled");
        $("#prodInsId").attr("disabled","disabled");
        $("#prodName").attr("disabled","disabled");
        $("#issuePartyId").attr("disabled","disabled");
    }

    $('#prodInsId').change(function(){
        var prodInsId = $('#prodInsId').val();
        if(''!=prodInsId && null !=prodInsId){

            Joyin.ajaxJson(basePath+'/chb/businessdatamaintain/chbprdbaseinfo/chbprdbaseinfo002/selectRegCodeByProdId?prodId='+prodInsId,{},function (result){
                if(result['REG_CODE']!='' && result['REG_CODE']!=null){
                    $('#regCode').val(result['REG_CODE']);
                    $('#prodId').val(prodInsId);
                }else{
                    layer.msg('请先维护产品登记编码信息！')
                }
            })
        }

    });

    $('#isConstracte').change(function (){
        var isConstracte = $('#isConstracte').val();
        if(isConstracte=='02'){
            $('#gradProportion').val('').attr('disabled','disabled');

        }else{
            $('#gradProportion').removeAttr('disabled');
        }

    });
    $('#openMode').change(function (){
        var openMode = $('#openMode').val();
        if(openMode=='02'){
            $('#openCircle').val('').trigger('change').attr('disabled','disabled');
        }else{
            $('#openCircle').removeAttr('disabled');
        }

    });
    $('#openCircle').change(function (){
        var openCircle = $('#openCircle').val();
        if(openCircle!='99'){
            $('#openCircleDays').val('').attr('disabled','disabled');
        }else{
            $('#openCircleDays').removeAttr('disabled');
        }
    });
    $('#openBuss').change(function (){
        var openBuss = $('#openBuss').val();
        if(openBuss!='99'){
            $('#openBussRemark').val('').attr('disabled','disabled');
        }else{
            $('#openBussRemark').removeAttr('disabled');
        }
    });
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        if($('#prodInsId').val()==''||$('#prodInsId')==null){
            layer.msg('请先录入对应的行内标识码，以指定申报信息。')
            return;
        }
        Joyin.ajaxJson(basePath + '/chb/businessdatamaintain/chbprdbaseinfo/chbprdbaseinfo002/check', object, function(result) {
           //提交工作流
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                    WorkFlow.doSubmitList(basePath + '/chb/businessdatamaintain/chbprdbaseinfo/chbprdbaseinfo002/save', object, "CchbPrdBaseInfo002_btn_submit");
            });
        });
    };

    /**
     * 画面加载完成
     */
    $(function () {
        if(pageModel === PAGE_MODEL_EDIT){
            $('#btnSave_wfl').hide();
        }
        // 绑定按钮事件
        $('#CchbPrdBaseInfo002_btn_submit').click(function() {
            saveOrSubmit("submit");
        });
        
    });
}());