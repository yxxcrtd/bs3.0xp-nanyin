/**
 * 佣金设置信息新建
 */
(function() {

    /**
     * 显示*号
     */
    var displayStar = function() {
        // 加红*
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

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#informationForm');
        if ($('#isPayWithTrade').next().hasClass('layui-form-onswitch')) {
            object.isPayWithTrade = 'Y';
        } else {
            object.isPayWithTrade = 'N';
        }
        var ps = $('#ps').val();
        var termType = $('#termType').val();
        object.secAcctId=$("#secAcctId").val()?$("#secAcctId").val().join(","):'';
        object.ps=$("#ps").val()?$("#ps").val().join(","):'';
        object.termType=$("#termType").val()?$("#termType").val().join(","):'';
        object.operateType = operateType;
        object.pageModel = pageModel;
        //add by 张年 20190829 start
        var selectedItem = [];
        if (ps) {
            $.each(ps, function(i1, v1) {
                if (!termType) {
                    var mstDto = getDto(object, v1);
                    selectedItem.push(mstDto);
                } else {
                    $.each(termType, function(i2, v2) {
                        var mstDto = getDto(object, v1);
                        mstDto.termType = v2
                        selectedItem.push(mstDto);
                    });
                }
            })
        }
        // 组合pk1数据
        var allData = [];
        var mstDto = new Object();
        mstDto.feeType = object.feeType;
        mstDto.secAcctId = object.secAcctId;
        mstDto.tradeType = object.tradeType;
        mstDto.effDate = object.effDate;
        if ($("#workflowId").val() != null && $("#workflowId").val() != '') {
            mstDto.workflowId = $("#workflowId").val();
        }
        if (ps) {
            mstDto.ps = ps.join(",");
        }
        if(termType){
            mstDto.termType = termType.join(",");
        }
        allData.push(mstDto);
        object.selectedItem = selectedItem;
        // 将所有数据json化存入allData
        object.allData = JSON.stringify(allData);
        object.pageId = "MstCommissionSet002";
        //add by 张年 20190829  end
        Joyin.ajaxJson(basePath + 'invest/basicdata/mstcommissionset/MstCommissionSet002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/basicdata/mstcommissionset/MstCommissionSet002/save', object, "MstCommissionSet002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/basicdata/mstcommissionset/MstCommissionSet002/save', object, "MstCommissionSet002_btn_submit");
                }
            });
        });
    }

    var getDto = function(object, v1) {
        var mstDto = new Object();
        mstDto.chargeBasis = object.chargeBasis;
        mstDto.chargeRate = object.chargeRate;
        mstDto.chargeType = object.chargeType;
        mstDto.chargeValue = object.chargeValue;
        mstDto.detailSeqNo = object.detailSeqNo;
        mstDto.effDate = object.effDate;
        mstDto.feeMaxi = object.feeMaxi;
        mstDto.feeMini = object.feeMini;
        mstDto.feeType = object.feeType;
        mstDto.isMlti = object.isMlti;
        mstDto.operateType = object.operateType;
        mstDto.pageModel = object.pageModel;
        mstDto.secAcctId = object.secAcctId;
        mstDto.batchSeq = object.batchSeq;
        mstDto.tradeType = object.tradeType;
        mstDto.ps = v1;
        if ($("#workflowId").val() != null && $("#workflowId").val() != '') {
            mstDto.workflowId = $("#workflowId").val();
        }
        if ($("#batchSeq").val() != null && $("#batchSeq").val() != '') {
            mstDto.batchSeq = $("#batchSeq").val();
        }
        return mstDto;
    }
    var feeTypeChange = function () {
        if($("#feeType").val() == FEE_TYPE_B02){
            $("#secAcctId").val('').prop('disabled',true).prop("required",false).trigger('change');
            $("#tradeType").val(PINAN_ASSET_TYPE_F11).prop('disabled',true).trigger('change');
            $("#chargeType").val(CHARGE_KIND_1).prop('disabled',true).trigger('change');
            $("#chargeBasis").val(CHARGE_BASIS_02).prop("required",true).trigger('change');
            $('#isPayWithTrade').prop("checked",false).prop("disabled", true).trigger('change');
            $('#isPayWithTrade').next().find("em").text('是');
            $('#isPayWithTrade').next().addClass("layui-form-onswitch");
            $('#isPayWithTrade').next().removeClass('layui-disabled');
            displayStar();
        }else if($("#feeType").val() == FEE_TYPE_B05){
            $("#secAcctId").prop('disabled',false).prop("required",true).trigger('change');
            $("#chargeType").prop('disabled',false).trigger('change');
            $("#tradeType").prop('disabled',false).trigger('change');
            // 是否随交易支付可选
            $('#isPayWithTrade').next().removeClass('layui-disabled');
            $('#isPayWithTrade').prop("disabled", false);
            $('#isPayWithTrade').trigger('change');
            displayStar();
        }else {
            // 是否随交易支付默认为是
            $('#isPayWithTrade').prop("checked",true);
            $('#isPayWithTrade').next().find("em").text('是');
            $('#isPayWithTrade').next().removeClass('layui-disabled');
            $('#isPayWithTrade').prop("disabled", false);
            $('#isPayWithTrade').trigger('change');
        }
    }
    var init = function(){
        var chargeType = $('#chargeType').val();
        if(pageModel==PAGE_MODEL_EDIT){
            // 如果费用类型为印花税
            feeTypeChange();
            var tradeType = $('#tradeType').val();
            //当交易品种为债券和股票时
            // 场内基金改造 modify by renping 20191117 start
            if(tradeType == "F01" || tradeType == "F11" || tradeType == PINAN_ASSET_TYPE_F02){
            // 场内基金改造 modify by renping 20191117 end
                $('#termType').val('').prop('disabled',true).trigger('change');
                $('#termType').prop("required",false).trigger('change');
            }
            else{//当交易品种为回购时
                $('#termType').prop("disabled",false);
                $('#termType').prop("required",true).trigger('change');
            }
            if(chargeType == "01"){
                //最大金额，最小金额，计费基数可以输入
                $('#feeMaxi').prop("disabled",false);
                $('#feeMini').prop("disabled",false);
                $('#chargeBasis').prop("disabled",false).prop("required",true).trigger('change');
                //费率值显示
                $('#feeRateDiv').show();
                //费用值隐藏
                $('#feeValueDiv').hide();
            }
            else{//若计费类型选择为固定金额
                //最大金额，最小金额，计费基数不可以输入
                //费率值隐藏
                $('#feeRateDiv').hide();
                //费用值显示
                $('#feeValueDiv').show();
                $('#feeMaxi').val('').prop("disabled",true);
                $('#feeMini').val('').prop("disabled",true);
                $('#chargeBasis').val('').prop("required",false).prop("disabled",true).trigger('change');

            }

            displayStar();
        }
        if (!$("#feeType").val()){
            // 是否随交易支付默认为是
            $('#isPayWithTrade').prop("checked",true);
            $('#isPayWithTrade').next().find("em").text('是');
            $('#isPayWithTrade').next().removeClass('layui-disabled');
            $('#isPayWithTrade').next().addClass("layui-form-onswitch");
            $('#isPayWithTrade').prop("disabled", false);
            $('#isPayWithTrade').trigger('change');
        }

        if(chargeType == "01"){
            //费率值显示
            $('#feeRateDiv').show();
            //费用值隐藏
            $('#feeValueDiv').hide();
        }
        else{//若计费类型选择为固定金额
            //费率值隐藏
            $('#feeRateDiv').hide();
            //费用值显示
            $('#feeValueDiv').show();

        }
    };
    $(function() {
        init();
        // 按钮事件
        $('#MstCommissionSet002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstCommissionSet002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        $("#chargeType").on('change',function(){
            if($(this).val()=='01'){
                $("#feeRateDiv").show();
                $("#feeValueDiv").hide();
                $("#chargeBasis").val('').prop('disabled',false).prop("required",true).trigger('change');
                $("#feeMaxi").val('').prop('disabled',false).trigger('change');
                $("#feeMini").val('').prop('disabled',false).trigger('change');
            }
            else{
                $("#feeValueDiv").show();
                $("#feeRateDiv").hide();
                $("#chargeBasis").val('').prop('disabled',true).prop("required",false).trigger('change');
                $("#feeMaxi").val('').prop('disabled',true).trigger('change');
                $("#feeMini").val('').prop('disabled',true).trigger('change');
            }
            displayStar();
        });
        // modified by wangguoxian 20190717 start
        $("#tradeType").on('change',function(){
            if($(this).val() == PINAN_ASSET_TYPE_F01 || $(this).val() == PINAN_ASSET_TYPE_F11 || $(this).val() == PINAN_ASSET_TYPE_F02){
                $("#termType").val('').prop('disabled',true).prop("required",false).trigger('change');
            }
            else{
                $("#termType").prop('disabled',false).prop("required",true).trigger('change');
            }
            if($(this).val() == PINAN_ASSET_TYPE_F11){
                $("#chargeBasis").val(CHARGE_BASIS_02).trigger('change');
            }
            displayStar();
        });
        // modified by wangguoxian 20190717 end
        // add by wangguoxian 20190717 start
        $("#feeType").on('change',function(){
            feeTypeChange();
        });
        // add by wangguoxian 20190717 end
    });
}());

