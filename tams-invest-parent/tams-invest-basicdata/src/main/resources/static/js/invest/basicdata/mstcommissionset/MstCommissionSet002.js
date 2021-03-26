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
    var getparam = function () {
        var data = $.serializeToObject('#informationForm');
        data.isGrade = $('#isGrade').val();
        data.batchSeq = $("#batchSeq").val();
        data.workflowId = $("#workflowId").val();
        return data;
    }

    var table=$("#dataTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/invest/basicdata/mstcommissionset/MstCommissionSet002/findTable'),
            type: 'POST',
            data: function() {
                return getparam();
            }
        },
        cols:[{field:'seqNo',visible:false},
             {field:'gradeAmount', title: '分档起点金额（万元）', edit:true,sortable:false,width : 170,type:'money'},
             {field:'isCludeStartamt',title:'是否包含起点金额', edit:true, editOption:{type:'select', result: YES_OR_NO},sortable:false,width : 170},
        	 {field:'chargeValue', title: '费用值', edit:true,sortable:false,type : 'money',width : 170},
        	 {field:'chargeRate',title:'费率（‰）', edit:true,sortable:false,type : 'number',width : 170},
        	 {field:'feeMaxi', title: '最大值', edit:true,sortable:false,type : 'money',width : 170},
        	 {field:'feeMini', title: '最小值', edit:true,sortable:false,type : 'money',width : 170},
        	 {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt'],width : 131}
        ],
        searchOnInit:true,
        addbtn:true,
        checkbox:false,
        rowno:true,
        page:false,
        addclick: function(row) {
            var list = table.getAllRows();
            if(list.length == 1){
                row.gradeAmount='0';
                row.isCludeStartamt='Y';
            }
        },
        beforeEditCell:function(preVal, field, rowData){
            if($('#chargeType').val()=='01'){
                if(field=='chargeValue')return false;
            }else if($('#chargeType').val()=='02'){
                if(field=='chargeRate')return false;
                if(field=='feeMaxi')return false;
                if(field=='feeMini')return false;
            }
            if(rowData.LAY_TABLE_INDEX==0){
                if(field=='gradeAmount'||field=='isCludeStartamt')return false;
            }
            return true;
        }
    });
    table.bind('click', '.delt', function(data, index) {
        if(index!=0){
            table.removeRow(data);
        }
    });

    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
         if($("#windtypeone").val()==null){
            if($("#windtypetwo").val()!=null){
                Joyin.error(JoyinUtil.getMessage("不能单独输入资产分类二，请先输入资产分类一。"));
                return;
            }
         }
        var object = $.serializeToObject('#informationForm');
        object.trdFeeConfigDetailList = table.getAllRows();
        object.windtypeone=$("#windtypeone").val()?$("#windtypeone").val().join():'';
        object.windtypetwo=$("#windtypetwo").val()?$("#windtypetwo").val().join():'';
        if ($('#isPayWithTrade').next().hasClass('layui-form-onswitch')) {
            object.isPayWithTrade = 'Y';
        } else {
            object.isPayWithTrade = 'N';
        }
        var ps = $('#ps').val();
        var termType = $('#termType').val();
        var secAcctId = $('#secAcctId').val();
        var windtypeone = $('#windtypeone').val();
        var windtypetwo = $('#windtypetwo').val();
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
                    if(!secAcctId){
                        var mstDto = getDto(object, v1);
                        selectedItem.push(mstDto);
                    }else{
                        $.each(secAcctId, function(i2, v2) {
                            var mstDto = getDto(object, v1);
                            mstDto.secAcctId = v2;
                            selectedItem.push(mstDto);
                        })
                    }
                } else {
                    $.each(termType, function(i2, v2) {
                        if(!secAcctId){
                            var mstDto = getDto(object, v1);
                            mstDto.termType = v2
                            selectedItem.push(mstDto);
                        }else{
                            $.each(secAcctId, function(i3, v3) {
                                var mstDto = getDto(object, v1);
                                mstDto.termType = v2
                                mstDto.secAcctId = v3;
                                selectedItem.push(mstDto);
                            })
                        }
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
        mstDto.termType = object.termType;
        mstDto.effDate = object.effDate;
        mstDto.windtypeone = object.windtypeone;
        mstDto.windtypetwo = object.windtypetwo;
        mstDto.tradeMarket = object.tradeMarket;
        mstDto.tradeMarketMode = object.tradeMarketMode;
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
        mstDto.termType = object.termType;
        mstDto.tradeType = object.tradeType;
        mstDto.tradeMarket = object.tradeMarket;
        mstDto.tradeMarketMode = object.tradeMarketMode;
        mstDto.ps = v1;
        mstDto.windtypeone = object.windtypeone;
        mstDto.windtypetwo = object.windtypetwo;
        if ($("#workflowId").val() != null && $("#workflowId").val() != '') {
            mstDto.workflowId = $("#workflowId").val();
        }
        if ($("#batchSeq").val() != null && $("#batchSeq").val() != '') {
            mstDto.batchSeq = $("#batchSeq").val();
        }
        return mstDto;
    }

    var feeTypeChange = function () {
        var feeType = $("#feeType").val();
        if(feeType == FEE_TYPE_B01 || feeType == FEE_TYPE_B07 || feeType == FEE_TYPE_B02){
            $("#secAcctId").val('').prop('disabled',true).prop("required",false).trigger('change');
        }else{
            $("#secAcctId").prop('disabled',false).prop("required",true).trigger('change');
        }
        if(feeType == FEE_TYPE_B01||feeType == FEE_TYPE_B07){
            $("#chargeType").prop('disabled',false).trigger('change');
        }else if(feeType == FEE_TYPE_B02){
            $("#chargeType").val(CHARGE_KIND_1).prop('disabled',true).trigger('change');
            $("#chargeBasis").val(CHARGE_BASIS_02).prop("required",true).trigger('change');
        }else if(feeType == FEE_TYPE_B05){
            $("#chargeType").prop('disabled',false).trigger('change');
        }
        if(feeType == FEE_TYPE_B01||feeType == FEE_TYPE_B07){
            $("#tradeType").val('F01').prop('disabled',true).trigger('change');
            // $('#tradeMarketMode').val('02').prop('disabled',true).trigger('change');
        }else if(feeType == FEE_TYPE_B02){
            // $('#tradeMarketMode').val('').prop('disabled',false).trigger('change');
            $("#tradeType").val('F11').prop('disabled',true).trigger('change');
        }else{
            // $('#tradeMarketMode').val('').prop('disabled',false).trigger('change');
            $("#tradeType").prop('disabled',false).trigger('change');
        }
        displayStar();
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
            table.reload({data:[],url:null});
        });
        // modified by wangguoxian 20190717 start
        $("#tradeType").change(function(){
            if($(this).val() == PINAN_ASSET_TYPE_F01 || $(this).val() == PINAN_ASSET_TYPE_F11 || $(this).val() == PINAN_ASSET_TYPE_F02){
                $("#termType").val('').prop('disabled',true).prop("required",false).trigger('change');
            }else{
                $("#termType").prop('disabled',false).prop("required",true).trigger('change');
            }
            if($(this).val() == PINAN_ASSET_TYPE_F11){
                $("#chargeBasis").val(CHARGE_BASIS_02).trigger('change');
            }
            if($(this).val() == PINAN_ASSET_TYPE_F01){
                $("#tradeMarketMode_div").show().trigger('change');
                $("#windtypeone").prop('disabled',false).trigger('change');
                $("#windtypetwo").prop('disabled',false).trigger('change');
            }else{
                $("#tradeMarketMode_div").hide().trigger('change');
                $("#tradeMarketMode").val('').trigger('change');
                $("#windtypeone").val('').prop('disabled',true).trigger('change');
                $("#windtypetwo").val('').prop('disabled',true).trigger('change');
            }
            if($(this).val() == PINAN_ASSET_TYPE_F11 && $('#feeType').val() == FEE_TYPE_B05 || $('#feeType').val() == FEE_TYPE_B02){
                $('#isPayWithTrade').prop("checked",true);
                $('#isPayWithTrade').next().find("em").text('是');
                $('#isPayWithTrade').next().removeClass('layui-disabled');
                $('#isPayWithTrade').next().addClass("layui-form-onswitch");
                $('#isPayWithTrade').prop("disabled", true).trigger('change');
                $('#isGrade').val('N').prop('disabled',true).trigger('change');
            }else{
                $('#isPayWithTrade').prop('disabled',false).trigger('change');
                $('#isGrade').prop('disabled',false).trigger('change');
            }
            displayStar();
        });
        // modified by wangguoxian 20190717 end
        // add by wangguoxian 20190717 start
        $("#feeType").on('change',function(){
            feeTypeChange();
        });
        // add by wangguoxian 20190717 end
        $("#isGrade").change(function(){
            if($("#isGrade").val()=='Y'){
                $('#grade_div2').show().trigger('change');
                $('#grade_div1').hide().trigger('change');
                $('#chargeValue').val('').trigger('change');
                $('#chargeRate').val('').trigger('change');
                $('#feeMaxi').val('').trigger('change');
                $('#feeMini').val('').trigger('change');
            }else{
                table.reload({data:[],url:null});
                $('#grade_div2').hide().trigger('change');
                $('#grade_div1').show().trigger('change');
            }
        });
        $("#isGrade").trigger('change');
        $("#tradeType").trigger('change');
        if(($("#workflowId").val()!=null&&$("#workflowId").val()!='')||pageModel!=PAGE_MODEL_NEW){
            $("#windtypetwo").val(windtypetwo).trigger("change");
            $("#chargeBasis").val(chargeBasis).trigger("change");
            $("#feeMaxi").val(feeMaxi).trigger("change");
            $("#feeMini").val(feeMini).trigger("change");
            $("#tradeMarketMode").val(tradeMarketMode).trigger("change");
        }
        if($('#chargeType').val() == "02"){
            $('#chargeBasis').val('').prop("disabled",true).trigger('change');
        }
    });
}());

