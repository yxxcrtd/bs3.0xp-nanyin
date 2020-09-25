var QueryFunc=function(){
    return {
        serachKey:function(){
        }
    }
}();

(function() {

    var getriskdata = function () {
        var entrustAmt = $('#entrustAmt').val();
        var repFinancier =$('#repFinancier').val();
        var finprodMarketId=$('#finprodMarketId').val();
            var obj = {valDate:sysDate,finprodMarketId:finprodMarketId,entrustAmt:entrustAmt,repFinancier:repFinancier}
            var object = {assetCode: ['finprodMarketId', 'EtrInstruction001.finprodMarketId'],entrustAmt:['entrustAmt','EtrInstruction001.entrustAmt'],repFinancier:['repFinancier','EtrInstruction001.repFinancier'],data:obj}
        return object;
    }

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
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW || pageModel === PAGE_MODEL_REFERENCE)) {
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

    var getElementById = function(id) {
        return $('#' + id);
    };

    //添加风险试算验证
   /* window.componentLoadCallback = function(isDone, pagePath, params) {
        if (isDone) {
            var riskparam = getriskdata();
            if (typeof riskparam != 'undefined') {
                if (pageModel==PAGE_MODEL_NEW){
                    riskparam.data =null;
                }
                window.BaseRiskMathComm.getparam(riskparam);
            }
            }
    }*/

    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        //提交前必须要进行过风险试算
        var s;
       /* if(BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if(!s && s != ''){
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())){
                return;
            }
        }
        var riskMathFlag =$('#riskMathFlag').val();
        if (riskMathFlag != '02'){
            Joyin.error("必须先进行【风险试算】后才允许提交!");
            return;
        }*/

        var object = $.serializeToObject('#submitForm');
        object.bussEtrInvestPersonInfoDto.invManagerId = $('#bussEtrInvestPersonInfoDto_invManagerId').val();
        if(object.bussEtrInvestPersonInfoDto.trader != null && object.bussEtrInvestPersonInfoDto.trader != ''){
            object.bussEtrInvestPersonInfoDto.trader = object.bussEtrInvestPersonInfoDto.trader.join(",");
        }
        if ($('#bussEtrInvestPersonInfoDto_counterId').select2('data') && $('#bussEtrInvestPersonInfoDto_counterId').select2('data')[0]) {
            object.counterType = $('#bussEtrInvestPersonInfoDto_counterId').select2('data')[0].object;;
        }
        if(FileCommon) {
            object.fileArray = FileCommon.getUploadFileInfo();
            if(object.fileArray && _.isArray(object.fileArray)){
                $.each(object.fileArray, function(index,obj){
                    obj.seqNo = obj.fileNo;
                });
            }
        }
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'invest/entrust/EtrCommonController/check', object, function(result1) {
            Joyin.ajaxJson(basePath + 'invest/entrust/etrinstruction/EtrInstruction002/check', object, function(result) {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/entrust/etrinstruction/EtrInstruction002/save', object, "EtrInstruction002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/entrust/etrinstruction/EtrInstruction002/save', object, "EtrInstruction002_btn_submit");
                }
            });
        });
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#EtrInstruction002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#EtrInstruction002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        /**
         * 风险试算
         */
        /*$('#EtrInstruction002_btn_riskcal').click(function(){
            window.BaseRiskMathComm.initBaseRiskMathCommLxzlPage(getriskdata());
        });*/

        if(pageModel == PAGE_MODEL_EDIT){
            //$('#targetId').attr("disabled",true);
        }


    });
}());

