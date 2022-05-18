(function() {
    var prodIdInit = $('#prodId').val();
    if(''==prodIdInit||null==prodIdInit){
        //新建页面
        $('#issuePartyId').val(issuePartyId_New);
        $('#prodId').blur(function (){
            var prodId = this.value;
            if(''==prodId||null==prodId){
            }else{
                Joyin.ajaxJson(basePath+'/chb/businessdatamaintain/chbprdbaseinfo/chbprdbaseinfo002/selectRegCodeByProdId?prodId='+prodId,{},function (result){
                    if(result['REG_CODE']!='' && result['REG_CODE']!=null){
                        $('#regCode').val(result['REG_CODE']);
                        $('#regCode').attr("disabled","disabled");
                    }else{
                        $('#regCode').val('');
                        $('#regCode').removeAttr("disabled");
                    }
                })
            }

        });
    }else{
        //修改页面
        $("#prodId").attr("disabled","disabled");
        $('#regCode').attr("disabled","disabled");
    }

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