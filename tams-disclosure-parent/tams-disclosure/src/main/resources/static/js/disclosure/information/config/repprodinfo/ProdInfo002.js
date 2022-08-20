(function () {

    var laydate = layui.laydate;
    var prodIdInit = $('#prodId').val();
    var openFreqGlobal = $('#openFreqGlobal').val();
    var prodTypeGlobal = $('#prodTypeGlobal').val();
    var prodNo = '';
    var flag = false;
    if ('' == prodIdInit || null == prodIdInit) {
        //新建页面
        $('#issuePartyId').val(issuePartyId_New);
        $('#prodId').blur(function () {
            if ($('#prodInsId').val() == '' && this.value != '') {
                layer.msg('请先录入对应的行内标识码，以指定申报信息。')
            }/*else if($('#regCode').val()!=''&&$('#prodId').val()!=prodNo){
                layer.msg('如果想修改申报时候的行内标识码，请同步维护产品登记编码管理信息！')
            }*/
        });

    } else {
        //修改页面
        $("#prodId").attr("disabled", "disabled");
        $("#isGrade").attr("disabled", "disabled");
        $("#prodRe").attr("disabled", "disabled");
        $("#pProdId").attr("disabled", "disabled");
        //$("#profitType").attr("disabled", "disabled");

        var prodType = prodTypeGlobal;
        //若产品类型为“现管类产品”，"发行方式"默认为“开放式”，且不允许修改
        if (prodType != null && prodType == '04') {
            $("#issuingWay").attr("disabled", true);
            $("#openFreq").attr("disabled", true);
            $("#profitType").attr("disabled", true);
            $("#holdday").attr("disabled", true);
        } else {
            $("#issuingWay").attr("disabled", false);
            $("#openFreq").attr("disabled", false);
            $("#profitType").attr("disabled", false);
        }

        showHidePerformDiv(isIndexUpdate);
    }
    var init = 0;
    var initProdInsId = '';
    $('#prodInsId').change(function () {

        var prodInsId = $('#prodInsId').val();
        if (init == 0) {
            initProdInsId = prodInsId;
        }

        if (init > 0) {
            if ('' != prodInsId && null != prodInsId) {
                // 查询产品申报信息
                /*Joyin.ajaxJson(basePath+'disclosure/information/config/repprodinfo/ProdInfo002/selectChbPrdDeclareByProdInsId?prodInsId='+prodInsId,{},function (result){
                    $('#ivstorientation').val(result.ivstorientation).trigger('change');
                    $('#operateMode').val(result.operateMode).trigger('change');
                    $('#prodName').val(result.prodName).trigger('change');
                    $('#ivstorientation_div').show();
                    $('#operateMode_div').show();

                })
                //查询产品登记信息
                Joyin.ajaxJson(basePath+'disclosure/information/config/repprodinfo/ProdInfo002/selectRegCodeByProdId?prodId='+prodInsId,{},function (result){
                    if(result['REG_CODE']!='' && result['REG_CODE']!=null){
                        $('#regCode').val(result['REG_CODE']);
                        $('#prodId').val(result['PRODID']);
                        prodNo = result['PRODID'];
                    }else{
                        prodNo = '';
                        $('#regCode').val('');
                        $('#prodName').val('');
                        $('#prodId').val('');
                        layer.msg('请先维护产品登记编码信息！')
                    }
                })
                if(initProdInsId != prodInsId){
                    // 校验产品是否存在
                    Joyin.ajaxJson(basePath+'disclosure/information/config/repprodinfo/ProdInfo002/selectByKey?prodId='+prodInsId,{},function (result){
                        if(result !=null &&  result[0]!=null){
                            if(result[0]['STATUS']=='D'){
                                Joyin.confirm('存在已删除的产品基本信息，是否覆盖？',function (){
                                    flag = false;
                                },function (){},function () {
                                    flag = true;
                                })
                            }else{
                                flag = true;
                                layer.msg('产品已存在、如需修改，请以修改模式进行信息修改！');
                            }
                        }else{
                            flag = false;
                        }
                    })
                }else{
                    flag = false;
                }*/
            } else {
                prodNo = '';
                $('#regCode').val('');
                $('#prodId').val('');
                $('#prodName').val('');
                $('#ivstorientation').val('');
                $('#ivstorientation_div').hide();
                $('#operateMode_div').hide();

            }
        }
        init += 1;
    });


    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.basicChangeList = mockTradeTable.getAllRows();
        if (pageModel == PAGE_MODEL_EDIT) { //修改 ，判断募集金额是否改变
            var oldRaiseAmt = $("#oldRaiseAmt").val();

            var raiseAmt = String(object.raiseAmt);
            console.log(oldRaiseAmt);
            console.log(raiseAmt);
            if (raiseAmt != oldRaiseAmt) {
                Joyin.confirm("是否同步修改其余子产品的产品募集金额？", function () {
                    object.synchFlag = true;
                    doSaveOrSubmit(object,operateType);
                },null, function () {
                    doSaveOrSubmit(object,operateType);
                });
            }else{
                doSaveOrSubmit(object,operateType);
            }
        } else {//新增操作
            doSaveOrSubmit(object,operateType);
        }
    };

    function doSaveOrSubmit(object,operateType) {
        if (operateType === 'submit'){
            Joyin.ajaxJson(basePath + 'disclosure/information/config/repprodinfo/ProdInfo002/check', object, function (result) {
                //提交工作流
                Joyin.confirm(result.data, function() {
                    WorkFlow.doSubmitList(basePath + 'disclosure/information/config/repprodinfo/ProdInfo002/save', object, "ProdInfo002_btn_submit");
                });
            });
        }else {
            Joyin.confirm('是否保存？', function() {
                    WorkFlow.doSaveList(basePath + 'disclosure/information/config/repprodinfo/ProdInfo002/save', object, "ProdInfo002_btn_save");
            });
        }

    }

    /**
     * 画面加载完成
     */
    var data = $(function () {
        if (pageModel === PAGE_MODEL_EDIT) {
            $('#btnSave_wfl').hide();
            if($('#issuingWay').val()=='02'){
                $('#openFreq').attr("disabled",true);
            }
        }
        // if(pageModel=== PAGE_MODEL_NEW){
        //     $('#isGrade').change(function () {
        //         if($('#isGrade').val()=='02'){
        //             $('#prodName').attr("disabled",true);
        //             $('#setDate').attr("disabled",true);
        //         }
        //     })
        // }

        // 绑定按钮事件
        $('#ProdInfo002_btn_submit').click(function () {
            saveOrSubmit("submit");
        });
        $('#ProdInfo002_btn_save').click(function () {
            saveOrSubmit("save");
        });

        $("#raiseStrdate").change(function () {
            changeRaisetDate();
        });

        $("#raiseEnddate").change(function () {
            changeRaisetDate();
        });

        $("#pSetDate").change(function () {
            changeMDate();
        });
        $("#mdate").change(function () {
            changeMDate();
        });
        $("#setDate").change(function () {
            changeMDate();
        });


        //修改 产品类型
        $("#prodType").change(function () {
            changeProdType();
        });

        function changeProdType(){
            var prodType = $('#prodType').select2('data')[0].id;
            prodTypeGlobal = prodType;
            //若产品类型为“现管类产品”，"发行方式"默认为“开放式”，且不允许修改
            if (prodType != null && prodType == '04') {
                $("#issuingWay").textSelect('setValue', '01');
                $("#issuingWay").attr("disabled", true);
                //当“发行方式”为“开放式”时必填，否则开放频率 置灰不可填,“现管类产品”，此处默认为“日开”，开放频率 且不允许修改
                $("#openFreq").textSelect('setValue', '01');
                $("#openFreq").attr("disabled", true);
                // 若产品类型为“现管类产品”，产品运作方式 默认为“开放式非净值型”，不可修改
                $("#profitType").textSelect('setValue', '03');
                $("#profitType").attr("disabled", true);
            } else {
                $("#issuingWay").attr("disabled", false);
                $("#openFreq").attr("disabled", false);
                $("#profitType").attr("disabled", false);
                changeHoldday();
            }
        }

        // 修改发行方式
        $("#issuingWay").change(function () {
            var issuingWay = $('#issuingWay').select2('data')[0].id;

            //若产品类型为“现管类产品”，"发行方式"默认为“开放式”，且不允许修改
            if (issuingWay != null && issuingWay == '01') {
                //当“发行方式”为“开放式”时开放频率必填，否则置灰不可填
                $("#openFreq").attr("disabled", false);
            } else {
               // $("#openFreq").val();
                $("#openFreq").textSelect('setValue', '99');
                $("#openFreq").attr("disabled", true);
            }
        });

        // 修改开放频率
        $("#openFreq").change(function () {
            var openFreq = $('#openFreq').select2('data')[0].id;
            openFreqGlobal = openFreq;
            changeHoldday();
        });


        // 修改 是否指数型产品
        // 当“是否是指数型产品”为“否”时可填，否则不可填
        $("#isIndex").change(function () {
            var isIndex = $('#isIndex').select2('data')[0].id;
            //当“是否是指数型产品”为“否”时业绩比较基准 可填，否则不可填
            changePerformCompare(isIndex);
            showHidePerformDiv(isIndex);

        });

        // 修改 是否分级产品
        $("#isGrade").change(function () {
            var isGrade = $('#isGrade').select2('data')[0].id;
            generatepProdName(isGrade);
        });

        // 子产品名称修改后的事件
        $("#pProdName").blur(function(){
            var isGrade = $('#isGrade').val();
            generatepProdName(isGrade);
        });

        // 子产品成立时间 修改事件
        $("#pSetDate").change(function(){
            var isGrade = $('#isGrade').val();
            generatepProdName(isGrade);
        });

        // 非分级产品（普通产品），自动填充产品名称和产品成立日期
        function generatepProdName(isGrade){
            //当“是否分级产品”为“否”时
            if(isGrade && isGrade == '02' ){
                // $("#pProdName").attr("disabled", true);
                // $("#pSetDate").attr("disabled", true);
                $('#prodName').attr("disabled",true);
                $('#setDate').attr("disabled",true);
                var pProdName = $('#pProdName').val(); //pSetDate
                var pSetDate = $('#pSetDate').val();
                $("#prodName").val(pProdName);
                laydate.render({
                    elem: '#setDate'
                    ,value: pSetDate //必须遵循format参数设定的格式
                });
            }else{
                //NYLCXP-931 是否分层产品否转是时，取消置灰
                $("#prodName").attr("disabled", false);
                $("#setDate").attr("disabled", false);
            }
        }

        // 修改固定持有天数
        function changeHoldday() {
            //当产品的开放频率为“日开”且“产品类型”为“净值型”时必填，否则不可填
            if (openFreqGlobal && prodTypeGlobal && openFreqGlobal == '01' && prodTypeGlobal == '01') {
                $("#holdday").attr("disabled", false);
            } else {
                $("#holdday").val("");
                $("#holdday").attr("disabled", true);
            }
        }



    });

    // 模拟交易列表
    var mockTradeTable = $("#mockTradeTable").joyintable({
        data: basicChangeList == null ? [] : basicChangeList,
        //data:[],
        searchOnInit: true,
        checkbox: false,
        page: false,
        height: '225px',
        cols: [
            /*{
                field: 'dataType', sortable: false, width: 400, align: 'center', title: '行情代码', edit: true,
                editOption: {
                    type: 'select', formatoption: true, multiple: false, searchable: true, result: QUOTA_CODE
                }
            }*/
            {
                field: 'dataType', edit: true, sortable: false, width:260, title: '行情代码',  align: 'center',
                editOption: {
                    type: 'select', multiple: false, searchable: true, result: QUOTA_CODE
                }
            }
            , {
                field: 'proportion',
                sortable: false,
                title: '系数(%)',
                width: 100,
                align: 'left',
                edit: true,
                type: 'number',
                digit: 2
            }
            , {field: 'operation', sortable: false, title: 'SysCommon.operation', type: 'action', buttons: ['delt']}
        ],
        addbtn: true,
        done: function () {
            this.bind('click', '.delt', function (data, index) {
                var object = {selectedItem: [data], operateType: "delete"};
                mockTradeTable.deleteRow(index);
            });
        }
    });


    // 显示/隐藏 行情代码和系数
    function showHidePerformDiv(isIndex) {
        //  当“是否是指数型产品”为“是”时显示
        if (isIndex && isIndex == '01') {
            $("#performDiv").show();
            $("#performCompare").attr("disabled", true);
        } else {
            $("#performDiv").hide();
        }
    }

    // 修改产品业绩比较基准
    //当“是否是指数型产品”为“否”时可填，否则不可填
    function changePerformCompare(isIndex) {
        if (isIndex == '02') {
            $("#performCompare").attr("disabled", false);
        } else {
            $("#performCompare").val("");
            $("#performCompare").attr("disabled", true);
        }
    }

    // 修改 产品到期日期、产品成立日期、子产品成立日期
    function changeMDate() {
        // 分级产品
        var isGrade = $('#isGrade').val();
        var pSetDate = $('#pSetDate').val();
        var mdate = $('#mdate').val();
        var setDate = $('#setDate').val();

        if (pSetDate && mdate) {
            var days = (Date.ofDate(mdate) - Date.ofDate(pSetDate)) / (24 * 60 * 60 * 1000);
            if (days < 0) {
                Joyin.error(JoyinUtil.getMessage("产品到期日必须大于产品成立日", JoyinUtil.getLabel("ProdInfo002.mdate")));
                $('#mdate').val('');
                return;
            }
        }

        //分级产品
        /*if (pSetDate && setDate && isGrade && isGrade == '01' ){
            var days = (Date.ofDate(pSetDate) - Date.ofDate(setDate))/(24*60*60*1000);
            if(days<0){
                Joyin.error(JoyinUtil.getMessage("分级产品，产品成立日期必须大于子产品成立日期", JoyinUtil.getLabel("ProdInfo002.setDate")));
                $('#setDate').val('');
            }
        }*/

        //if (setDate && mdate && isGrade && isGrade == '01' ){
        var days = (Date.ofDate(mdate) - Date.ofDate(setDate)) / (24 * 60 * 60 * 1000);
        if (days < 0) {
            Joyin.error(JoyinUtil.getMessage("产品到期日必须大于子产品成立日期", JoyinUtil.getLabel("ProdInfo002.mdate")));
            $('#mdate').val('');
        }
        //}
    }

    // 修改 募集结束日/开始日
    function changeRaisetDate() {
        var raiseStrdate = $('#raiseStrdate').val();
        var raiseEnddate = $('#raiseEnddate').val();
        if (raiseStrdate && raiseEnddate) {
            var days = (Date.ofDate(raiseEnddate) - Date.ofDate(raiseStrdate)) / (24 * 60 * 60 * 1000);
            if (days < 0) {
                Joyin.error(JoyinUtil.getMessage("募集结束日期必须大于等于募集起始日期", JoyinUtil.getLabel("ProdInfo002.raiseEnddate")));
                $('#raiseEnddate').val('');
            }
        }
    }

}());
