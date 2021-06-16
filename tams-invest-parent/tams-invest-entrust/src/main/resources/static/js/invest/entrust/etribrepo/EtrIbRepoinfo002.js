/**
 * 质押式回购投资委托新建
 */
(function () { 
    //试算结果
    var riskResultdata;
    var cluflag;

    var initFlag = true;
    var pageModel = PAGE_MODEL_NEW;
    //默认新增指令 指令操作类型  NEW 是新增指令  EDIT 修改指令
    var operType = ENTRUST_RECORD_NEW;

    var getriskdata = function () {
    	if($('#ps').val() == ps_03){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_zhg,isEnabled:'01'}
        }else if($('#ps').val() == ps_04){
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_nhg,isEnabled:'01'}
        }else{
            var obj = {tradeDirection:$('#ps').val(),timePoint:MY_SUP_TIME_TYPE_02,valDate:sysDate,portfolioCode:$('#portfolioId').val(),tradeAmount:$('#entrustAmt').val(),assetType:fintype_zhg,isEnabled:'01'}
        }
        // add by zhangrui1 交易对手影响风险试算 start
        obj.counterId = $('#etrInvestPersonInfo_counterId').val();
        // add by zhangrui1 交易对手影响风险试算 end
        obj.repoTerm = $('#repoTerm').val();
        var object = {tradeDirection:['ps','EtrRepo002.ps'],portfolioCode:['portfolioId','EtrRepo002.portfolioId'],tradeAmount:['entrustAmt','EtrRepo002.entrustAmt'],assetType:['assetType1','EtrRepo002.assetType1'],data:obj}
        // add by zhangrui1 交易对手影响风险试算 start
        object.counterId = ['counterId', 'EtrInvestPersonInfo.counterId'];
        // add by zhangrui1 交易对手影响风险试算 end
        object.repoTerm = ['repoTerm', 'EtrRepo002.repoTerm'];
        return object;
    };

    /**
     * 获取风险试算参数
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function getSearchParams() {
        var object = $.serializeToObject("#searchForm");
        return object;
    }


    /**
     * 获取表单参数
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function getSaveOrSubmitParams(operateType) {
        //证券账户
        if ($("#chlFinprodId").val() != null && $("#chlFinprodId").val() != '') {
            $("#secManageAcctId").val('');
        } else {
            $("#secManageAcctId").val('999999');
        }
        if (!$("#secManageAcctId").val() && !$("#chlFinprodId").val()) {
            Joyin.warn("证券账户和通道不能同时为空！");
            return false;
        }
        var object = $.serializeToObject("#searchForm");
        if ($("#select2-finprodId-container").attr("title")) {
            object.finprodName = $("#select2-finprodId-container").attr("title");
        }

        //查询可用现金
        object.proAvailableCash = "0";
        var param = {};
        param.chlFinprodId = $("#chlFinprodId").val();
        param.finprodIdhid = $("#finprodIdhid").val();
        param.portfolioId = $("#portfolioId").val();
        Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtrIbRepo001/findPositionInfo', param, function (result) {
            if (result) {
                object.proAvailableCash =  result.zhkyxj_t;
                object.chlIdCash =  result.tdkyxj_t;
            }
        },false);

        object.operateType = operateType;
        object.pageModel = pageModel;
        object.operType = operType;
        object.entrustId = $("#entrustId").val();
        object.entrustStatus = $("#entrustStatus").val();
        object.ccy = $("#ccy").val();
        object.tradeMarket = $("#tradeMarket").val();
        object.finvdate = $("#finvdate").val();
        object.finmdate = $("#finmdate").val();

        object.counterType = $('#counterId').select2('data')[0].object;  //交易对手类型，校验时用到

        return object;
    }
    
    /**
     * 画星星
     */
    var star = function () {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
            var $el = element.prev();
            if (!$el.is("label") && level > 0) {
                $el = $el.find("label");
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== "undefined" && pageModel !== PAGE_MODEL_REFERENCE) {
            $(".must").each(function () {
                $(this).remove();
            });
            $("*[required]").each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find("> .must").length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    };


    /**
     * 初始化
     */
    var init = function () {
        if (todoList == false) {
            $('#EtrIbRepoinfo002_btn_fileupload').css('display', 'none');
            $('#EtrIbRepoinfo002_btn_riskcal').css('display', 'none');
        }
        
        // 加载质押券库
//        if (pageModel === PAGE_MODEL_NEW) {
//	        if ($("#portfolioId").val() != null && $('#tradeMarket').val() != null
//	        		&& $("#portfolioId").val() != "" && $('#tradeMarket').val() != "") {
//	        	Joyin.ajax(basePath + 'invest/trade/bond/TrdBondInvestTrade002/getSecManageAcctId', 
//	                {'portfolioId':$("#portfolioId").val(), 'tradeMarket':$('#tradeMarket').val(), 'finprodMarketId':$('#finprodName').val()}, function (data) {
//	                  if (data.length == 1) {
//	                  	$('#secManageAcctId').textSelect('setValue', data[0].id); 
//	                  } else {
//	                	 $('#secManageAcctId').textSelect('setValue', "");
//	                  }
//	              });
//            }
//        }
        
        businessId = $('#businessId').val()

        $("#ps").change();
        $("#portfolioId").change();
        $("#priceModel").trigger("change");
        
        initFlag = false;
        // 画星星
        star();
        //
        if (typeof shortSellingSupported !== "undefined" && shortSellingSupported == "02") {
            $(".shortSellingSupported").hide();
        }
    };

    /**
     * 风险试算
     */
    function doRiskcal() {
        var object = $.serializeToObject("#searchForm");
        if (object) {
            var riskdata = getriskdata(object);
            var riskInterfUrl = basePath + "riskrds/interf/doInfData/0/RSK002/F07";
            if (riskdata && riskInterfUrl) {
                window.BaseRiskMathComm.initBaseRiskMathRedis(riskInterfUrl, object, riskdata, riskMathRedisCallback);
            }
        }
    } 
    /**
     * 风险试算的回调函数
     */
     function riskMathRedisCallback(object, cluFlag) {
        var data = {};
        EtrIbRepoinfo002.riskResultdata = object;
        EtrIbRepoinfo002.cluflag = cluFlag;  
        Joyin.dialog(JoyinUtil.getLabel("EtrIbRepoinfoRiskcal002"), "/invest/entrust/etribrepo/EtrIbRepoinfoRiskcal002/init", "60%", "75%", null);
    } 

    /**
     * 风险试算时不需要进行风险试算的校验
     */
    var isNeedRiskcal = function (){
        //质押券入库和出库不需要走风险试算
        ps = $("#ps").val();
        return true;
    }

    /**
     * 风险试算时不需要进行风险试算的校验
     */
    var getRiskInterfUrl = function (){
        var riskInterfUrl = basePath + "riskrds/interf/doInfData/0/RSK002/F07";
        return riskInterfUrl;
    }

    /**
     * 上传附件
     */
    var fileUpload = function () {
        var params = $.serializeToObject('#searchForm');
        var object = {
            businessId: params.businessId
        }
        Joyin.dialog(JoyinUtil.getLabel('EtrIbRepoinfo002.upload'), '/invest/entrust/etribrepo/EtrIbRepoinfoFile/init', '60%', '75%',object);
    }

    /**
     * 双击持仓自动填充表单
     */
    var dbclickRefresh002 = function (data) {
        setEntrust002PageItems(data);
        //双击校验后，将风险试算标识重置
        window.parent.dbclickRefreshRisk();
    };

    /**
     * 根据传入的数据，设置画面元素
     */
    var setEntrust002PageItems = function (param) {
        setPageItemsFlag = true;
        if (param) {
            if (param.portfolioId) {
                $("#portfolioId").textSelect('setValue',param.portfolioId);
            }
            if (param.avaShareAmt) {
                $("#entrustAmt").val(param.avaShareAmt).trigger("change");
            }
            if (param.channelId) {
                $("#chlFinprodId").val(param.channelId).trigger("change");//通道
            }
//            $("#ps").textSelect('setValue',"T02");
            $("#priceModel").textSelect('setValue',"PRM02");
            $("#vdate").val(sysDate).trigger("change");
            $("#mdate").val(sysDate).trigger("change");
        }
    };

     /**
     * 双击指令管理
     */
    var dbclickEtr = function (data) {
        setEntrust(data);
        //双击校验后，将风险试算标识重置
        window.parent.dbclickRefreshRisk();
    };

     /**
     * 根据传入的数据，设置画面元素
     */
    var setEntrust = function (param) {
        setPageItemsFlag = true;
        if (param) {
            if (param.entrustId) {
                $("#entrustId").val(param.entrustId).trigger("change");//委托代码
            }
            if (param.entrustStatus) {
                $("#entrustStatus").val(param.entrustStatus).trigger("change");//委托状态
            }
            if (param.portfolioId) {
                $("#portfolioId").textSelect('setValue',param.portfolioId);//组合代码
            }
            /*if (param.ps) {
                $("#ps").textSelect('setValue',param.ps);//交易方向
            }*/
            if (param.ps) {
                $("#ps").val(param.ps).trigger("change");//交易方向
            }
            if (param.entrustAmt) {
                $("#entrustAmt").val(param.entrustAmt).trigger("change");//指令金额
            }
            if (param.price) {
                $("#price").val(param.price);//利率
            }
            if (param.settleSpreed) {
                if(param.settleSpreed === "0"){
                    $("#settleSpreed").textSelect('setValue',"01");
                }else if(param.settleSpreed === "1"){
                    $("#settleSpreed").textSelect('setValue',"02");
                }
            }
            if (param.priceModel) {
                $("#priceModel").val(param.priceModel).trigger("change");//价格模式
            }
            if (param.repoTerm) {
                $("#repoTerm").val(param.repoTerm).trigger("");//回购期限
            }
            if (param.termType) {
                $("#termType").val(param.termType).trigger("change");//期限品种
            }
            if (param.counterId) {
                $("#counterId").val(param.counterId).trigger("change");//交易对手
            }
            if (param.chlFinprodId) {
                $("#chlFinprodId").val(param.chlFinprodId).trigger("change");//通道
            }
            /*if (param.vdate) {
                $("#vdate").val(param.vdate).trigger("change");//成立日
            }
            if (param.mdate) {
                $("#mdate").val(param.mdate).trigger("change");//终止日
            }*/
            if (param.remark) {
                $("#remark").val(param.remark).trigger("change");//备注
            }
            pageModel = PAGE_MODEL_EDIT;
            //operType = ENTRUST_RECORD_MODIFY;
        }
    };

    /**
     * 取消,清空指令数据
     */
    var doReset = function () {
    	
        $("#portfolioId").val("").trigger("change");
        $("#ps").val("").trigger("change");
        $("#entrustAmt").val("").trigger("change");
        $("#settleSpreed").val("").trigger("change");
        $("#priceModel").val("").trigger("change");
        $("#price").val("").trigger("change");
        $("#repoTerm").val("");
        $("#zyqk").val("").trigger("change");
        $("#termType").val("").trigger("change");
        $("#counterId").val("").trigger("change");
        $("#chlFinprodId").val("").trigger("change");
        $("#entrustId").val("").trigger("change");
        $("#entrustStatus").val("").trigger("change");

        pageModel = PAGE_MODEL_NEW;
        operType = ENTRUST_RECORD_NEW;
    };

    /**
     * 保存或提交
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
        var s;
        if (BaseRiskMathComm) {
            s = BaseRiskMathComm.saveOrSubmitCheck(operateType);
            if (!s && s != "") {
                return;
            }
            if (window.BaseRiskMathComm.isCalcConditionChanged(getriskdata())) {
                return;
            }
        }
        var object = getSaveOrSubmitParams(operateType);


        if(!object){
            return;
        }

        Joyin.ajaxJson(basePath + "invest/entrust/EtrCommonController/check", object, function (result1) {
            Joyin.ajaxJson(basePath + "invest/entrust/etribrepo/EtrIbRepoinfo002/check", object, function (result) {
                Joyin.confirm(result.data, function () {
                    if (operateType === "save") {
                        WorkFlow.doSaveList(basePath + "invest/entrust/etribrepo/EtrIbRepoinfo002/save", object, "EtrIbRepoMain_btn_save", "EtrIbRepoinfo002", function () {
                            JoyinUtil.disableButton("EtrIbRepoMain_btn_save", false);
                            doReset();
                        });
                    } else {
                        WorkFlow.doSubmitList(basePath + "invest/entrust/etribrepo/EtrIbRepoinfo002/save", object, "EtrIbRepoMain_btn_submit", "EtrIbRepoinfo002", function () {
                            JoyinUtil.disableButton("EtrIbRepoMain_btn_submit", false);
                            doReset();
                        });
                    }
                });
            });
        });
    }

    /**
     * 对外公开的接口
     */
    parent.EtrIbRepoinfo002 = {
        dbclickRefresh002: dbclickRefresh002,
        dbclickEtr: dbclickEtr,
        doReset: doReset,
        getriskdata: getriskdata,
        getSearchParams: getSearchParams,
        isNeedRiskcal: isNeedRiskcal,
        getRiskInterfUrl: getRiskInterfUrl,
        getSaveOrSubmitParams:  getSaveOrSubmitParams,


        riskResultdata: riskResultdata,
        cluflag: cluflag
    };

    EtrIbRepoinfo002 = {
        riskResultdata: riskResultdata,
        cluflag: cluflag
    };

    /**
     * 绑定事件
     */
    $(function () {

        // 绑定提交按钮
        $('#EtrIbRepoinfo002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

        // 绑定保存按钮
        $('#EtrIbRepoinfo002_btn_save').click(function () {
            saveOrSubmit('save');
        });

        // 绑定取消按钮
        $('#EtrIbRepoinfo002_btn_reset').click(function () {
            doReset();
        });

        // 绑定风险按钮
        $('#EtrIbRepoinfo002_btn_riskcal').click(function () {
            doRiskcal();
        });

        $('#EtrIbRepoinfo002_btn_fileupload').click(function () {
            fileUpload();
        })
        
        /**
         * 初始化
         */
        init();
        
        $('#repoTerm').change(function(){
        	
            var repoTerm = $('#repoTerm').val();
            var re = /^[0-9]*$/;
            if(re.test(repoTerm)){
                var vdateAdd = $('#vdate').val();
                /*if(vdateAdd){
                    var d1 = Date.ofDate(vdateAdd).getTime();
                    var day = parseInt($('#repoTerm').val()*24*60*60*1000);
                    var mdateAdd = Date.ofDate(d1+day);
                    $('#mdate').val(mdateAdd.format("yyyy-MM-dd")).change();
                }*/
            }

            // 当交易场所是银行间时，输入回购期限后，自动带出期限品种，回购期限与期限品种的联动关系
            if ($('#tradeMarket').val() === '01'){
                var repoterm = $('#repoTerm').val();
                if(repoterm < 0){
                    Joyin.error("期限品种不能为负数");
                    return ;
                }
                var re = /^[0-9]*$/;
                if(repoterm && re.test(repoterm)){
                    var vdate = $('#vdate').val();
                    if(vdate){
                        //回购期限联动期限品种
                        var term = getTermType(vdate,repoterm);
                         $('#termType').val(term).trigger('change');
                         $('#termType').textSelect('setValue', term);

                    }
                }else{
                    $('#termType').val("").trigger('change');
                }
            }

            
            //左下行情信息联动
            var chlFinprodId = $("#chlFinprodId").val();
            var finprodIdhid = $("#finprodIdhid").val();
            var portfolioId = $("#portfolioId").val();
            var termType = $("#termType").val();
            if (todoList == false) {
                parent.EtrIbRepoinfoHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId, termType);
            }
        });

        /**
         * 获取期限品种  date(yyyy-MM-dd),repoterm
         */
        function getTermType(date,repoterm) {
        	
            if(repoterm&&repoterm<=21){
                //期限品种小于21天
                if(repoterm==0){
                    Joyin.error("期限品种不能为0");
                    return ;
                }
                if(repoterm==1){
                    return R001;
                }
                if(repoterm>1&&repoterm<=7){
                    return R007;
                }
                if(repoterm>7&&repoterm<=14){
                    return R014;
                }
                if(repoterm>14&&repoterm<=21){
                    return R021;
                }
            }
            var day=0;
            //date为空时  返回0
            if(date&&repoterm){
                //获取日期
                date =DateUtil.parse(date);
                //获取之后月00天 即本月最后一天
                day= new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+1,0).getDate();
                if(repoterm<=day){
                    return R1M;
                }
                day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+2,0).getDate();
                if(repoterm<=day){
                    return R2M;
                }
                day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+3,0).getDate();
                if(repoterm<=day){
                    return R3M;
                }
                day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+4,0).getDate();
                if(repoterm<=day){
                    return R4M;
                }
                day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+5,0).getDate();
                day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+6,0).getDate();
                if(repoterm<=day){
                    return R6M;
                }
                day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+7,0).getDate();
                day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+8,0).getDate();
                day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+9,0).getDate();
                if(repoterm<=day){
                    return 'R9M';
                }
                day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+10,0).getDate();
                day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+11,0).getDate();
                day= day+new Date(date.getFullYear(),new Date(DateUtil.parse(date)).getMonth()+12,0).getDate();
                if(repoterm<=day){
                    return 'R1Y';
                }
            }
            return null;
        }
        
        $('#vdate').change(function(){
            var vdateAdd = $('#vdate').val();
            var repoTerm = $('#repoTerm').val();
            var mdateAdd = $('#mdate').val();
            if(vdateAdd && repoTerm){
                var d1 = Date.ofDate(vdateAdd).getTime();
                var day = parseInt($('#repoTerm').val()*24*60*60*1000);
                var mdateAdd = Date.ofDate(d1+day);
                $('#mdate').val(mdateAdd.format("yyyy-MM-dd")).change();
            }
            if(vdateAdd && mdateAdd){
                if(Date.ofDate(vdateAdd).getTime()>Date.ofDate(mdateAdd).getTime()){
                    Joyin.error("起息日不能大于到期日");
                    $("#vdate").val('');
                    return;
                }
                var datev = Date.ofDate(vdateAdd).getTime();
                var datem = Date.ofDate(mdateAdd).getTime();
                var total = (datem-datev)/1000;
                var day = parseInt(total / (24*60*60));
                $('#repoTerm').val(day);
            }
            
        });
        
/*        $('#mdate').change(function(){
            var vdateAdd = Date.ofDate($('#vdate').val());
            var mdateAdd = Date.ofDate($('#mdate').val());
            if(vdateAdd && mdateAdd){
                if(vdateAdd.getTime()>mdateAdd.getTime()){
                    Joyin.error("到期日不能小于起息日");
                    $("#mdate").val('');
                    return;
                }
                var datev = Date.ofDate(vdateAdd).getTime();
                var datem = Date.ofDate(mdateAdd).getTime();
                var total = (datem-datev)/1000;
                var day = parseInt(total / (24*60*60));
                $('#repoTerm').val(day);
            }
        });*/
        
        $('#termType').change(function(){

            if ($('#tradeMarket').val() !== '01'){
                var termType = $('#termType').val();
                if(termType == GC001){
                    $('#repoTerm').val(1).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == GC002){
                    $('#repoTerm').val(2).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == GC003){
                    $('#repoTerm').val(3).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == GC004){
                    $('#repoTerm').val(4).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == GC007){
                    $('#repoTerm').val(7).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == GC014){
                    $('#repoTerm').val(14).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == GC028){
                    $('#repoTerm').val(28).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == GC091){
                    $('#repoTerm').val(91).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == GC182){
                    $('#repoTerm').val(182).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R001){
                    $('#repoTerm').val(1).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R007){
                    $('#repoTerm').val(7).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R014){
                    $('#repoTerm').val(14).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R021){
                    $('#repoTerm').val(21).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == RC001){
                    $('#repoTerm').val(1).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == RC003){
                    $('#repoTerm').val(3).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == RC007){
                    $('#repoTerm').val(7).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R_001){
                    $('#repoTerm').val(1).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R_002){
                    $('#repoTerm').val(2).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R_003){
                    $('#repoTerm').val(3).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R_004){
                    $('#repoTerm').val(4).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R_007){
                    $('#repoTerm').val(7).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R_014){
                    $('#repoTerm').val(14).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R_028){
                    $('#repoTerm').val(28).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R_091){
                    $('#repoTerm').val(91).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R_182){
                    $('#repoTerm').val(182).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R1M){
                    $('#repoTerm').val(30).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R2M){
                    $('#repoTerm').val(60).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R3M){
                    $('#repoTerm').val(90).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R4M){
                    $('#repoTerm').val(120).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else if(termType == R6M){
                    $('#repoTerm').val(180).attr('disabled',true).change();
                    $('#mdate').attr('disabled',true);
                }else{
                    $('#mdate').attr('disabled',false);
                    $('#repoTerm').attr('disabled',false);
                }
            }

        });

        /**
         * 组合代码
         */
        $("#portfolioId").change(function () {
        	
        	
        	/**
             * 组合下拉框变更
             */
            $("#zyqk").val('').trigger("change");

            var portfolioId = $("#portfolioId").val();

            //左下行情信息联动
            var chlFinprodId = $("#chlFinprodId").val();
            var finprodIdhid = $("#finprodIdhid").val();
            var termType = $("#termType").val();
            if (todoList == false) {
                parent.EtrIbRepoinfoHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId, termType);
            }

        });

        $.fn.updateChosenArr = function updateChosenArr(obj) {
            var $this = $(this),
                arr = obj.dataResult || JSON.parse($(this).attr("data-result") ? $(this).attr("data-result") : "[]"),
                item = obj.item;
            $this.empty();
            $this.append($("<option value>"));
            for (var i in arr) {
                if (!arr.hasOwnProperty(i)) continue;
                if (obj.item && obj.item.length > 0) {
                    if ($.inArray(arr[i].id, item) >= 0) $("<option>").attr("value", arr[i].id).text(arr[i].text).appendTo($this);
                } else $("<option>").attr("value", arr[i].id).text(arr[i].text).appendTo($this);
            }
            if (obj.chosen) $this.val(obj.chosen);
            if (obj.disabled === true || obj.disabled === false) $this.attr("disabled", obj.disabled);
            $this.trigger("chosen:updated").trigger("change");
            if (obj.callback) obj.callback($this);
            return $this;
        };
        
        /**
         * 价格模式
         */
        $('#priceModel').change(function(){
            if($('#priceModel').val() == PRICEMODEL_03){
                $('#price').val('').attr('disabled',true);
                $('#price').removeAttr("required");
            }else{
            	$('#price').attr('disabled',false);
                $('#price').attr("required", "true");
            }
            star();
        });
        
        /**
         * 交易方向
         */
        $("#ps").change(function () {
        	
            if (pageModel != PAGE_MODEL_REFERENCE) {
                if ($("#ps").val() == ps_04) {
                    $("#zyqk").val("").attr("disabled", true);
                    $('#zyqk').removeAttr("required");
                } else {
                    $("#zyqk").attr("disabled", false);
                    $('#zyqk').attr("required", "true");
                }
                star();
            }
        });

        /**
         * 通道
         */
        $("#chlFinprodId").change(function () {

            //证券账户
            if ($("#chlFinprodId").val() != null && $("#chlFinprodId").val() != '') {
                $("#secManageAcctId").val('');
            } else {
                $("#secManageAcctId").val('999999');
            }

            //左下行情信息联动
            var chlFinprodId = $("#chlFinprodId").val();
            var finprodIdhid = $("#finprodIdhid").val();
            var portfolioId = $("#portfolioId").val();
            var termType = $("#termType").val();
            if (todoList == false) {
                parent.EtrIbRepoinfoHq.querySecinfo(finprodIdhid,portfolioId,chlFinprodId, termType);
            }
        });

    });
    // 解决查看画面打开联动仍然触发的bug
    initFlag = false;
})();
