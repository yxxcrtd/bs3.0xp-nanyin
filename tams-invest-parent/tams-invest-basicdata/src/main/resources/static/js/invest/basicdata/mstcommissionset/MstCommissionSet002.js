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

        //add by hudongyang 默认为否 start
        var isGrade = $('#isGrade')[0].checked;
        if(isGrade){
            var rskAuthManage;
            if(table!=null && table!=''){
                rskAuthManage = table.getAllRows();
            }
            if (!rskAuthManage || rskAuthManage.length == 0) {
                Joyin.error("请输入表格数据");
                return;
            }
            object.trdFeeConfigDetailList = rskAuthManage;
        }

        if ($('#isPayWithTrade').next().hasClass('layui-form-onswitch')) {
            object.isPayWithTrade = 'Y';
        } else {
            object.isPayWithTrade = 'N';
        }
        if ($('#isMlti').next().hasClass('layui-form-onswitch')) {
            object.isMlti = 'Y';
        } else {
            object.isMlti = 'N';
        }
        if ($('#isGrade').next().hasClass('layui-form-onswitch')) {
            object.isGrade = 'Y';
        } else {
            object.isGrade = 'N';
        }
        object.brokerBaerFee = $("#brokerBaerFee").val()?$("#brokerBaerFee").val().join(","):'';
        //add by hudongyang 默认为否 end
        var ps = $('#ps').val();
        var termType = $('#termType').val();
        //object.customerId=$("#customerId").val()?$("#customerId").val().join(","):'';
        //object.secAcctId=$("#secAcctId").val()?$("#secAcctId").val().join(","):'';
        //object.ps=$("#ps").val()?$("#ps").val().join(","):'';
        //object.termType=$("#termType").val()?$("#termType").val().join(","):'';
        object.operateType = operateType;
        object.pageModel = pageModel;
        //add by 张年 20190829 start
        var selectedItem = [];
        // if (ps) {
        //     $.each(ps, function(i1, v1) {
        //         if (!termType) {
        //             var mstDto = getDto(object, v1);
        //             selectedItem.push(mstDto);
        //         } else {
        //             $.each(termType, function(i2, v2) {
        //                 var mstDto = getDto(object, v1);
        //                 mstDto.termType = v2
        //                 selectedItem.push(mstDto);
        //             });
        //         }
        //     })
        // }
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
        // if (ps) {
        //     mstDto.ps = ps.join(",");
        // }
        // if(termType){
        //     mstDto.termType = termType.join(",");
        // }
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
            //delete by hudongyang 默认为否 start
            // $("#secAcctId").val('').prop('disabled',true).prop("required",false).trigger('change');
            //$("#tradeType").val(PINAN_ASSET_TYPE_F11).prop('disabled',true).trigger('change');
            // $("#chargeType").val(CHARGE_KIND_1).prop('disabled',true).trigger('change');
            // $("#chargeBasis").val(CHARGE_BASIS_02).prop("required",true).trigger('change');
            //delete by hudongyang 默认为否 end
            //delete by hudongyang 默认为否 start
            // $('#isPayWithTrade').prop("checked",false).prop("disabled", true).trigger('change');
            // $('#isPayWithTrade').next().find("em").text('是');
            // $('#isPayWithTrade').next().addClass("layui-form-onswitch");
            // $('#isPayWithTrade').next().removeClass('layui-disabled');
            //delete by hudongyang 默认为否 end
            //add by hudongyang 2020-08-04 start
            //费用不为佣金时可填
            $("#tradePlat").prop('disabled',false).trigger('change');
            //费用为佣金时填写
            $("#portfolioId").val('').prop('disabled',true).trigger('change');
            $("#customerId").val('').prop('disabled',true).trigger('change');
            $("#brokerBaerFee").val('').prop('disabled',true).trigger('change');
            $("#commCalMethod").val('').prop('disabled',true).trigger('change');
            $('#isPayWithTrade').prop("checked",true);
            $('#isPayWithTrade').next().find("em").text('否');
            $('#isPayWithTrade').next().removeClass('layui-disabled');
            $('#isPayWithTrade').next().removeClass("layui-form-onswitch");//控制开关即颜色变化情况
            $('#isPayWithTrade').prop("disabled", true);
            $('#isPayWithTrade').trigger('change');
            //$("#tradePlat").val('').trigger('change');
            //add by hudongyang 2020-08-04 end
            displayStar();
        }else if($("#feeType").val() == FEE_TYPE_B05){
            //delete by hudongyang 默认为否 start
            // $("#secAcctId").prop('disabled',false).prop("required",true).trigger('change');
            // $("#chargeType").prop('disabled',false).trigger('change');
            //$("#tradeType").prop('disabled',false).trigger('change');
            // 是否随交易支付可选
            // $('#isPayWithTrade').next().removeClass('layui-disabled');
            // $('#isPayWithTrade').prop("disabled", false);
            // $('#isPayWithTrade').trigger('change');
            //delete by hudongyang 默认为否 end
            //add by hudongyang 2020-08-04 start
            //费用不为佣金时可填
            $("#tradePlat").val('').prop('disabled',true).trigger('change');

            //费用为佣金时填写
            $("#portfolioId").prop('disabled',false).trigger('change');

            $("#customerId").prop('disabled',false).trigger('change');

            $("#brokerBaerFee").prop('disabled',false).trigger('change');

            $("#commCalMethod").prop('disabled',false).trigger('change');

            $('#isPayWithTrade').prop("disabled", false).trigger('change');
            //add by hudongyang 2020-08-04 end
            displayStar();
        }else {
            // 是否随交易支付默认为是
            //delete by hudongyang 默认为否 start
            // $('#isPayWithTrade').prop("checked",true);
            // $('#isPayWithTrade').next().find("em").text('是');
            // $('#isPayWithTrade').next().removeClass('layui-disabled');
            // $('#isPayWithTrade').prop("disabled", false);
            // $('#isPayWithTrade').trigger('change');
            //delete by hudongyang 默认为否 end
            //add by hudongyang 2020-08-04 start
            //费用不为佣金时可填
            $("#tradePlat").prop('disabled',false).trigger('change');
            //费用为佣金时填写
            $("#portfolioId").val('').prop('disabled',true).trigger('change');
            $("#customerId").val('').prop('disabled',true).trigger('change');
            $("#brokerBaerFee").val('').prop('disabled',true).trigger('change');
            $("#commCalMethod").val('').prop('disabled',true).trigger('change');
            $('#isPayWithTrade').prop("checked",true);
            $('#isPayWithTrade').next().find("em").text('否');
            $('#isPayWithTrade').next().removeClass('layui-disabled');
            $('#isPayWithTrade').next().removeClass("layui-form-onswitch");//控制开关即颜色变化情况
            $('#isPayWithTrade').prop("disabled", true);
            $('#isPayWithTrade').trigger('change');
            //$("#tradePlat").val('').trigger('change');
            //add by hudongyang 2020-08-04 end
        }
    }
    //add by hudongyang 2020-08-04 start
    var isGradeChange = function () {
        var isGrade = $('#isGrade')[0].checked;
        if(isGrade){
            $('#gradeInfo').show();
            $('#chargeRate').val('').prop("disabled",true);//费率值
            $('#chargeValue').val('').prop("disabled",true);//费用值
            $('#feeMaxi').val('').prop("disabled",true);//最大值
            $('#feeMini').val('').prop("disabled",true);//最小值
        }else{
            $('#gradeInfo').hide();
            $('#chargeRate').val('').prop("disabled",false);//费率值
            $('#chargeValue').val('').prop("disabled",false);//费用值
            $('#feeMaxi').val('').prop("disabled",false);//最大值
            $('#feeMini').val('').prop("disabled",false);//最小值
        }
    }

    var tradeTypeChange = function () {
        var tradeType = $('#tradeType').val();
        //是否多券种、期限品种 在交易品种为回购时填写
        if(tradeType == 'F07'){
            // 是否随交易支付默认为是
            $('#isMlti').prop("disabled", false);
            $('#isMlti').trigger('change');

            $('#termType').prop("disabled", false);
            $('#termType').trigger('change');
        }else{
            $('#isMlti').prop("checked",true);
            $('#isMlti').next().find("em").text('否');
            $('#isMlti').next().removeClass('layui-disabled');
            $('#isMlti').next().removeClass("layui-form-onswitch");//控制开关即颜色变化情况
            $('#isMlti').prop("disabled", true);
            $('#isMlti').trigger('change');

            $('#termType').prop("disabled", true);
            $('#termType').trigger('change');
        }
        // 债券以外时，计算基准限定为成交金额
        if(tradeType != null && tradeType != '' && tradeType != 'F01') {
        	$('#chargeBasis').val('02').trigger('change');
        	$('#chargeBasis').prop("disabled", true);
        }
        else {
        	$('#chargeBasis').val('').trigger('change');
        	$('#chargeBasis').prop("disabled", false);
        }
    }
    var chargeTypeChange = function () {
        var chargeType = $('#chargeType').val();
        var isGrade = $('#isGrade')[0].checked;
        if(!isGrade){
            //费率类型选择固定费率，可输入计算的费率
            if(chargeType == '01'){
                $('#chargeRate').val('').prop("disabled",false);//费率值
                $('#chargeValue').val('').prop("disabled",true);//费用值
            }else{
                $('#chargeRate').val('').prop("disabled",true);//费率值
                $('#chargeValue').val('').prop("disabled",false);//费用值
            }
        }
    }
    //add by hudongyang 2020-08-04 end

    var init = function(){
        //delete by hudongyang 2020-08-04 start
        // var chargeType = $('#chargeType').val();
        // if(pageModel==PAGE_MODEL_EDIT){
            // 如果费用类型为印花税
            //feeTypeChange();
            // var tradeType = $('#tradeType').val();
            // //当交易品种为债券和股票时
            // // 场内基金改造 modify by renping 20191117 start
            // if(tradeType == "F01" || tradeType == "F11" || tradeType == PINAN_ASSET_TYPE_F02){
            // // 场内基金改造 modify by renping 20191117 end
            //     $('#termType').val('').prop('disabled',true).trigger('change');
            //     $('#termType').prop("required",false).trigger('change');
            // }
            // else{//当交易品种为回购时
            //     $('#termType').prop("disabled",false);
            //     $('#termType').prop("required",true).trigger('change');
            // }
            // if(chargeType == "01"){
            //     //最大金额，最小金额，计费基数可以输入
            //     $('#feeMaxi').prop("disabled",false);
            //     $('#feeMini').prop("disabled",false);
            //     $('#chargeBasis').prop("disabled",false).prop("required",true).trigger('change');
            //     //费率值显示
            //     $('#feeRateDiv').show();
            //     //费用值隐藏
            //     $('#feeValueDiv').hide();
            // }
            // else{//若计费类型选择为固定金额
            //     //最大金额，最小金额，计费基数不可以输入
            //     //费率值隐藏
            //     $('#feeRateDiv').hide();
            //     //费用值显示
            //     $('#feeValueDiv').show();
            //     $('#feeMaxi').val('').prop("disabled",true);
            //     $('#feeMini').val('').prop("disabled",true);
            //     $('#chargeBasis').val('').prop("required",false).prop("disabled",true).trigger('change');
            //
            // }
        //
        //     displayStar();
        // }
        // if (!$("#feeType").val()){
        //     // 是否随交易支付默认为是
        //     $('#isPayWithTrade').prop("checked",true);
        //     $('#isPayWithTrade').next().find("em").text('是');
        //     $('#isPayWithTrade').next().removeClass('layui-disabled');
        //     $('#isPayWithTrade').next().addClass("layui-form-onswitch");
        //     $('#isPayWithTrade').prop("disabled", false);
        //     $('#isPayWithTrade').trigger('change');
        // }
        //delete by hudongyang 默认为否 end

        //delete by hudongyang 2020-08-04 start
        // if(chargeType == "01"){
        //     //费率值显示
        //     $('#feeRateDiv').show();
        //     //费用值隐藏
        //     $('#feeValueDiv').hide();
        // }
        // else{//若计费类型选择为固定金额
        //     //费率值隐藏
        //     $('#feeRateDiv').hide();
        //     //费用值显示
        //     $('#feeValueDiv').show();
        //
        // }
        //delete by hudongyang 2020-08-04 end

        // isGradeChange();//add by hudongyang
        // feeTypeChange();//add by hudongyang
        // tradeTypeChange();//add by hudongyang
        // chargeTypeChange();//add by hudongyang
        var isGrade = $('#isGrade')[0].checked;
        if(isGrade){
            $('#gradeInfo').show();
            $('#chargeRate').prop("disabled",true);//费率值
            $('#chargeValue').prop("disabled",true);//费用值
            $('#feeMaxi').prop("disabled",true);//最大值
            $('#feeMini').prop("disabled",true);//最小值
        }else{
            $('#gradeInfo').hide();
            $('#chargeRate').prop("disabled",false);//费率值
            $('#chargeValue').prop("disabled",false);//费用值
            $('#feeMaxi').prop("disabled",false);//最大值
            $('#feeMini').prop("disabled",false);//最小值
        }
        //table?table.reload():table.load();//add by hudongyang
    };

    /**
     * add by hudongyang 定义数据表格
     */
    var addbtn = (pageModel == PAGE_MODEL_NEW) || ($("#wfl_operType").val() == 'NEW');
    var options = {
        ajax : {
            //url : JoyinUtil.getRealPath('invest/risk/rskauthoritymanage/RskAuthorityManage002/findPage'),
            url : JoyinUtil.getRealPath('invest/basicdata/mstcommissionset/MstCommissionSet002/findPage'),
            type : 'POST',
            data : function() {
                return {
                    batchSeq : $('#batchSeq').val(),
                    workflowId : $('#wfl_workflowId').val()
                };
            }
        },
        page: false,
        addbtn:true,
        addToLast:true,
        searchOnInit: true,
        checkbox:false,
        cols:[
            {field:'gradeCondition',title:'MstCommissionSet002.gradeCondition',width:150,edit:true,sortable:false
                ,editOption:{type:'select',id:"gradeCondition",formatoption:true,result:GRADE_CONDITION}
            }
            ,{field:'gradeAmt',title:'MstCommissionSet002.gradeAmt',width:150,edit:true,type:"number",digit:'4',sortable:false
            }
            ,{field:'chargeValue',title:'MstCommissionSet002.chargeValue',width:150,edit:true,type:"number",digit:'2',sortable:false
            }
            ,{field:'chargeRate',title:'MstCommissionSet002.chargeRate',width:150,edit:true,type:"number",digit:'8',sortable:false
            }
            ,{field:'feeMaxi',title:'最大值（元）',width:150,edit:true,type:"number",digit:'2',sortable:false
            }
            ,{field:'feeMini',title:'最小值（元）',width:150,edit:true,type:"number",digit:'2',sortable:false
            }
            ,{field:'operation',title:'SysCommon.operation',type:'action',buttons: ['delt']}
        ],
    };
    var table = $("#dataTable").joyintable(options);

    /**
     * add by hudongyang  绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });

    $(function() {
        init();
        // 按钮事件
        $('#MstCommissionSet002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#MstCommissionSet002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        //delete by hudongyang 2020-08-04 start
        // $("#chargeType").on('change',function(){
        //     if($(this).val()=='01'){
        //         $("#feeRateDiv").show();
        //         $("#feeValueDiv").hide();
        //         $("#chargeBasis").val('').prop('disabled',false).prop("required",true).trigger('change');
        //         $("#feeMaxi").val('').prop('disabled',false).trigger('change');
        //         $("#feeMini").val('').prop('disabled',false).trigger('change');
        //     }
        //     else{
        //         $("#feeValueDiv").show();
        //         $("#feeRateDiv").hide();
        //         $("#chargeBasis").val('').prop('disabled',true).prop("required",false).trigger('change');
        //         $("#feeMaxi").val('').prop('disabled',true).trigger('change');
        //         $("#feeMini").val('').prop('disabled',true).trigger('change');
        //     }
        //     displayStar();
        // });
        //delete by hudongyang 2020-08-04 end

        //delete by hudongyang 2020-08-04 start
        // modified by wangguoxian 20190717 start
        // $("#tradeType").on('change',function(){
        //     if($(this).val() == PINAN_ASSET_TYPE_F01 || $(this).val() == PINAN_ASSET_TYPE_F11 || $(this).val() == PINAN_ASSET_TYPE_F02){
        //         $("#termType").val('').prop('disabled',true).prop("required",false).trigger('change');
        //     }
        //     else{
        //         $("#termType").prop('disabled',false).prop("required",true).trigger('change');
        //     }
        //     if($(this).val() == PINAN_ASSET_TYPE_F11){
        //         $("#chargeBasis").val(CHARGE_BASIS_02).trigger('change');
        //     }
        //     displayStar();
        // });
        //delete by hudongyang 2020-08-04 end
        // modified by wangguoxian 20190717 end
        // add by wangguoxian 20190717 start
        $("#feeType").on('change',function(){
            feeTypeChange();
        });
        // add by wangguoxian 20190717 end

        //add by hudongyang 2020-08-03 start
        $("#tradeType").on('change',function(){
            tradeTypeChange();
        });
        $("#chargeType").on('change',function(){
            chargeTypeChange();
        });
        layui.form.on('switch(isGrade)',function(data){
            isGradeChange();
        });
        //add by hudongyang 2020-08-03 end

    });
}());

