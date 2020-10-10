/**
 * 还本信息组件JS
 * @auth zhangnian
 */
(function(){
	/**
     * 上一次参数
     */
    var previewParams = {};
    /**
     * 禁止重复reload table
     */
    var doneFlag =false;
    var initFlag = true;
    /**
     * 是否加载table ajax data;
     */
    var loadAjaxData = true;
    /**
     * 编辑付息计划列表后，是否重新计算利息(确认按钮)
     * true：重新计算利息
     * false:重新生成计划
     */ 
    var isAgainCalIntRepay = false;
    var getElementById = function(id,type) {
        if(type=='div'){
            return $('#' + entityName_BussFinRepayment + '_' + id+'_'+type);
        }else{
            return $('#' + entityName_BussFinRepayment + '_' + id);
        }
    };
    
    //获取计息要素画面元素id
    var getFinInterestFactorElementById = function(id){
        return $('#' + entityName_FinInterestFactor + '_' + id);
    }
    
    //获取付息信息画面元素id
    var getFinPayInterestStdInfoElementById = function(id){
        return $('#' + entityName_FinPayInterestStdInfo + '_' + id);
    }
    
    /**
     * 定义数据表格
     */
    var cols = [];
    if (INT_ASSET_TYPE == FINPROD_TYPE_TG_F01) {
        cols = [ {
            field : 'vdate',
            title : 'BussFinRepayment.vdate',
            visible : false,
            edit : true,
            sortable : false,
            type : 'date'
        }, {
            field : 'mdate',
            title : 'BussFinRepayment.mdate',
            width : 200,
            edit : true,
            sortable : false,
            type : 'date'
        }, {
            field : 'payDate',
            title : 'BussFinRepayment.payDate',
            width : 200,
            edit : false,
            sortable : false,
            type : 'date'
        }, {
            field : 'prinAmt',
            title : 'BussFinRepayment.prinAmt',
            width : 200,
            edit : true,
            sortable : false,
            type : 'number',
            digit:4
        }, {
            field : 'intAmt',
            title : 'BussFinRepayment.intAmt',
            width : 200,
            edit : true,
            type : 'number',
            digit:16
        }, {
            field : 'cashAmt',
            title : 'BussFinRepayment.cashAmt',
            width : 200,
            edit : false,
            sortable : false,
            type : 'number'
        }, {
            field : 'operation',
            title : 'SysCommon.operation',
            type : 'action',
            buttons : [ 'delt' ]
        } // 编辑和删除事件
        ]
    } else if (INT_ASSET_TYPE == FINPROD_TYPE_TG_F18||INT_ASSET_TYPE == FINPROD_TYPE_TG_F17) {
        //项目类资产还本计划维护
        if(INT_ASSET_TYPE_REPAY==COMMON_BUSINESS_TYPE_FINPROD10){
            cols = [ {
                field : 'cashId',
                title : '',
                visible : false
            },{
                field : 'mdate',
                title : 'BussFinRepayment.mdate',
                width : 250,
                edit : true,
                sortable : false,
                type : 'date'
            }, {
                field : 'payDate',
                title : 'BussFinRepayment.payDateOther',
                width : 200,
                edit : false,
                sortable : false,
                type : 'date'
            }, {
                field : 'prinAmt',
                title : 'BussFinRepayment.prinAmtOther',
                width : 250,
                edit : true,
                sortable : false,
                type : 'number'
            }, {
                field : 'surplusAmt',
                title : 'BussFinRepayment.surplusAmt',
                width : 250,
                edit : false,
                sortable : false,
                type : 'number'
            }, {
                field : 'isPayed',
                width : 250,
                title : '状态',
                sortable : false,
                render : function(data, type, row, meta) {
                    if (row.isPayed == 'Y')
                        return "已还款";
                    else
                        return '未还款';
                }
            },
                {
                    field : 'repayWithoutInt',
                    title : 'BussFinRepayment.repayWithoutInt',
                    edit : false,
                    visible: false,
                    sortable : false,
                    type : 'number'
                },
                /* {
                     field : 'operation',
                     title : 'SysCommon.operation',
                     type : 'action',
                     buttons : [ 'delt' ]
                 } *///编辑和删除事件
            {field: 'operation', width: 150, title: 'SysCommon.operation', type: 'action', buttons: ['delt'],
                render: function (data, type, row, meta) {
                    if (row.isPayed == 'Y')
                        return "";
                    else
                        return '<i class="btn layui-icon delt" title="删除">&#xe640;</i>';
                }}//删除事件
            ]
        }else{
            cols = [ {
                field : 'vdate',
                title : 'BussFinRepayment.vdate',
                visible : false,
                edit : true,
                sortable : false,
                type : 'date'
            }, {
                field : 'mdate',
                title : 'BussFinRepayment.mdate',
                width : 200,
                edit : true,
                sortable : false,
                type : 'date'
            }, {
                field : 'payDate',
                title : 'BussFinRepayment.payDateOther',
                width : 200,
                edit : false,
                sortable : false,
                type : 'date'
            }, {
                field : 'prinAmt',
                title : 'BussFinRepayment.prinAmtOther',
                width : 200,
                edit : true,
                sortable : false,
                type : 'number'
            }, {
                field : 'intAmt',
                title : 'BussFinRepayment.intAmt',
                width : 200,
                edit : false,
                sortable : false,
                type : 'number'
            }, {
                field : 'cashAmt',
                title : 'BussFinRepayment.cashAmt',
                width : 200,
                edit : false,
                sortable : false,
                type : 'number'
            },
                {
                    field : 'repayWithoutInt',
                    title : 'BussFinRepayment.repayWithoutInt',
                    edit : false,
                    visible: false,
                    sortable : false,
                    type : 'number'
                },
                {
                field : 'operation',
                title : 'SysCommon.operation',
                width : 200,
                type : 'action',
                buttons : [ 'delt' ]
            } //编辑和删除事件
            ]
        }
        
    }
    var table =$("#bussFinRepaymentTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/component/asset/BussFinRepayment/initInfo'),
            type: 'POST',
            dataType: 'json',
            data: function () {
                // 保存本次参数，用于在主画面保存时进行验证
                if (loadAjaxData) {
                    previewParams = getPreviewParams();
                    return previewParams;
                }
                return getPreviewParams();

            }
        },
        cols:cols,
        searchOnInit: true,
        loadonce:true,
        checkbox:false,
        addToLast:true,
        height:290,
        page:false,
        addbtn:true,
        contextmenu : false,
        addclick: function(row) {
          //项目类资产还本计划维护
            var _row = table.getRow(0);
            if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F18&&INT_ASSET_TYPE_REPAY==COMMON_BUSINESS_TYPE_FINPROD10){
                row.cashId = $('#cashId').val();
            }
            row.maxTradeDate = _row ? _row.maxTradeDate : '';
            row.updateFlg = '01';
        },
        done:function(table, allData){
            //项目类资产还本计划维护 计算剩余本金
            if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F18&&INT_ASSET_TYPE_REPAY==COMMON_BUSINESS_TYPE_FINPROD10){
                    calSurplusAmt();
            }
        },
        beforeEditCell: function(preVal, field, rowData){
            //息票品种非附息时不可编辑
            if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F01){
                if(getFinInterestFactorElementById('couponSpecies').val()==COUPON_SPECIES_01
                        ||getFinInterestFactorElementById('couponSpecies').val()==''){
                    return true;
                }else{
                    return false;
                } 
            }
            if(rowData.updateFlg=='02'){
                return false;
            }
            return true;
        },
        editCell : function(value, data, field, cell, column){
            //项目类资产还本计划维护
            if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F18&&INT_ASSET_TYPE_REPAY==COMMON_BUSINESS_TYPE_FINPROD10){
                //还款金额
                if(field === "prinAmt"||field === "mdate"){
                    doneFlag = false;
                    calSurplusAmt();
                }
            }else{
                if(field=='prinAmt'||field=='intAmt'){
                    var rowData = {};
                    rowData.cashAmt = JoyinMath.doAdd(data.prinAmt,data.intAmt,2);
                    //清空支付日，编辑还本金额后是否重新计算利息是根据支付日是否为空判断!
                    rowData.payDate = "";
                    table.renderRow(data.LAY_TABLE_INDEX, rowData);
                }
            }
            if(field=='mdate'){
                
                if(new Date(value).DateDiff('d',data.maxTradeDate)>=0){
                    var rowData = {};
                    Joyin.error("计息结束日不可小于交易日",function(){
                        rowData.mdate  = data.oldMdate;
                        table.renderRow(data.LAY_TABLE_INDEX, rowData);
                    });
        }
            }
        }
    });
        
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    
    var getAllRows = function(){
            return table.getAllRows();
    }
    
    /**
     * 初始化还本计划
     */
    var initGenerationPlan = function(flag,iscallbackPay,callback){
        table.table ? table.reload() : table.load();
        //仅项目类资产 点击生成计划按钮 才会重新调用付息计划 重新计算区间利息
        if(iscallbackPay){
            if(callback&&$.isFunction(callback)){
                callback(flag,true);
            } 
        }
        disableBussFinRepay();
    }
    
    /**
     * 生成还本计划
     * flag(true:确认  flase:生成计划)
     * iscallbackPay(true:重新回调付息计划  flase:不回调)
     * iscallbackRepayByPay(true:重新回调还本计划  false:不回调)
     */
    var generationPlan = function(flag,iscallbackPay,iscallbackRepayByPay){
    	loadAjaxData = true;
        initFlag = false;//非初始化
        isAgainCalIntRepay = flag;
        var list = getAllRows();
        
        if(!list || list.length == 0) {
            initGenerationPlan(flag,iscallbackPay,callIntPlan);
        }else{
            if(iscallbackRepayByPay||flag){
                initGenerationPlan(flag,iscallbackPay,callIntPlan); 
            }else{
                Joyin.confirm(JoyinUtil.getMessage('BUSS_COM_C_0047'),function(){
                    initGenerationPlan(flag,iscallbackPay,callIntPlan);
                });
            }
        }
    }
    
    /**
     * 调用付息计划
     * iscallback(是否是回调函数)
     */
    var callIntPlan = function(flag,iscallback){
        if(FinPayInterestStdInfo){
            FinPayInterestStdInfo.generationPlan(flag,iscallback);
        }
    }
    
    /**
     * 还本计划信息不可编辑
     */
    var disableBussFinRepay = function(){
      //息票品种
        if(getFinInterestFactorElementById("couponSpecies").val() == COUPON_SPECIES_01||getFinInterestFactorElementById("couponSpecies").val()==""){
            //隐藏加号
            getElementById('div').find('.layui-table-page').show();
            //置灰删除按钮
            getElementById('div').find('table').find('.delt').each(function(){
                $(this).prop('disabled',false);
            });
        }else{
          //隐藏加号
            getElementById('div').find('.layui-table-page').hide();
            //置灰删除按钮
            getElementById('div').find('table').find('.delt').each(function(){
                $(this).prop('disabled',true);
            });
        }
    }
    /**
     * 获取参数
     * @returns
     */
    function getPreviewParams() {
        var param = {}
      //付息信息
        var param = {}
            ,endTdayRule  = getFinPayInterestStdInfoElementById('endTdayRule').val()
            , payTdayRule = getFinPayInterestStdInfoElementById('payTdayRule').val()
            , payCycle = getFinPayInterestStdInfoElementById('payCycle').val()
            , remark = getFinPayInterestStdInfoElementById('remark').val()
            , calcRule = getFinPayInterestStdInfoElementById('calcRule').val()
            ,firstPayDate = getFinPayInterestStdInfoElementById('firstPayDate').val();
        param.finprodId = $("#finprodId").val();
        param.workflowId = $("#workflowId").val();
        //资产类型
        param.finprodType2 = INT_ASSET_TYPE;
        //父画面--起息日
        param.vdate = $('#vdate').val();
        //父画面--到期日
        param.mdate = $('#mdate').val();
        //父页面--币种
        param.ccy = $('#ccy').val();
        //父画面--交易日历
        param.calendarId = $("#calendarId").val();
        //付息信息
        param.finPayInterestStdInfoDto ={};
        //资产类型
        param.finPayInterestStdInfoDto.finprodType2 = INT_ASSET_TYPE;
        //计息营业日准则
        param.finPayInterestStdInfoDto.endTdayRule = endTdayRule;
        //支付日营业日准则
        param.finPayInterestStdInfoDto.payTdayRule = payTdayRule;
        //支付频率
        param.finPayInterestStdInfoDto.payCycle = payCycle;
        //推算方式
        param.finPayInterestStdInfoDto.calcRule = calcRule;
        //自定义说明
        param.finPayInterestStdInfoDto.remark = remark;
        //首次付息日
        param.finPayInterestStdInfoDto.firstPayDate = firstPayDate;
        param.finPayInterestStdInfoDto.couponSpecies = getFinInterestFactorElementById('couponSpecies').val();
        //计息要素
        param.finInterestFactorDto ={};
        //资产类型
        param.finInterestFactorDto.finprodType2 = INT_ASSET_TYPE;
        //计息要素--息票品种
        param.finInterestFactorDto.couponSpecies = getFinInterestFactorElementById('couponSpecies').val();
        //计息要素--利率类型
     // 一体化对应etliu start
//        param.finInterestFactorDto.rateType = getFinInterestFactorElementById('rateType').val();
        param.finInterestFactorDto.intType = getFinInterestFactorElementById('rateType').val();
     // 一体化对应etliu end
        //计息要素--计息基础
        param.finInterestFactorDto.basis = getFinInterestFactorElementById('basis').val();
        //计息要素--利息分配方式
        param.finInterestFactorDto.basisStyle = getFinInterestFactorElementById('basisStyle').val();
        //计息要素--初始化利率
        param.finInterestFactorDto.rate = getFinInterestFactorElementById('rate').val();
        //利率上限
        param.finInterestFactorDto.highestRate = getFinInterestFactorElementById('rateMax').val();
        //利率下限
        param.finInterestFactorDto.lowestRate = getFinInterestFactorElementById('rateMin').val();
        //浮动利率--基准编号
        param.finInterestFactorDto.benchmarkId = getFinInterestFactorElementById('indexId').val();
        //系数
        param.finInterestFactorDto.coefficient = getFinInterestFactorElementById('coefficient').val();
        //利差
        param.finInterestFactorDto.spreadRate = getFinInterestFactorElementById('spreadRate').val();
        //基准利率调整规则
        param.finInterestFactorDto.resetType = getFinInterestFactorElementById('resetType').val();
        //延后时间
        param.finInterestFactorDto.observeBefDay = getFinInterestFactorElementById('observeBefDay').val();
        //延后时间单位
        param.finInterestFactorDto.observeBefUnit = getFinInterestFactorElementById('observeBefUnit').val();
        //资产推荐费
        param.finInterestFactorDto.referRate = getFinInterestFactorElementById('referRate').val();
        //净投资收益率
        param.finInterestFactorDto.investRate = getFinInterestFactorElementById('investRate').val();
        //借款利率
        param.finInterestFactorDto.loadRate = getFinInterestFactorElementById('loadRate').val();
        //买断利率
        param.finInterestFactorDto.buyOutRate = $('#buyOutRate').val();
        //计息利率方式
        param.finInterestFactorDto.calRateType = getFinInterestFactorElementById('calRateType').val();
        param.initFlag = initFlag;
        param.isAgainCalInt = isAgainCalIntRepay;
        //获取还本列表信息
        //存款类(主画面存款本金)、项目类资产(主画面融资金额)--issueAmt
        if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F07||INT_ASSET_TYPE==FINPROD_TYPE_TG_F18||INT_ASSET_TYPE==FINPROD_TYPE_TG_F17){
            param.issueAmt = $('#issueAmt').val();
        }else if(INT_ASSET_TYPE == FINPROD_TYPE_TG_F01){
            param.issueAmt = 100;
        }
        //确认按钮，需要带出计划列表重新生成计划
        if(isAgainCalIntRepay){
            if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F01||INT_ASSET_TYPE==FINPROD_TYPE_TG_F18||INT_ASSET_TYPE==FINPROD_TYPE_TG_F17){
                if(FinPayInterestStdInfo){
                    param.invScheduleList = FinPayInterestStdInfo.getAllRows();
                }
                var invRepayList = getAllRows();
                param.invRepayList = invRepayList; 
            }
        }
        //项目类资产通道费率、付息区间
        if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F18&&INT_ASSET_TYPE_REPAY==null){
            if(BussParticipantInfo){
                param.bussParticipantInfoDtoList =  BussParticipantInfo.getAllRows();
            }
        }
        //还款周期
        param.payCycle = getElementById('payCycle').val();
        var isPayIntFlag = getElementById('isPayIntFlag').next().hasClass('layui-form-onswitch');
        if(isPayIntFlag){
        //是还本不带息场景
        param.isPayIntFlag='Y';
        }else{
            param.isPayIntFlag='N';
        }
        param.entityName = entityName_BussFinRepayment;
        param.pageModel = pageModel;
        //付息计划维护标志 INT_ASSET_TYPE_REPAY
        param.adjPlanFlag = INT_ASSET_TYPE_REPAY;
        _.each(param.invRepayList, function(item) {
            item.mdate = (new Date(item.mdate)).format('yyyy-MM-dd');
            item.vdate = (new Date(item.vdate)).format('yyyy-MM-dd');
        });
        _.each(param.invScheduleList, function(item) {
            item.mdate = (new Date(item.mdate)).format('yyyy-MM-dd');
            item.vdate = (new Date(item.vdate)).format('yyyy-MM-dd');
        });
        return param;
    }
    
    /**
     * 生成还本计划前台check
     */
    var generationPlanCheck = function(){
        var msgArray=[];var errorClassArray = [];var messages="";
        var newParam = getPreviewParams(),msg;
        if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F18||INT_ASSET_TYPE==FINPROD_TYPE_TG_F17){
            //付息计划不可为空
            if(FinPayInterestStdInfo){
                var list = FinPayInterestStdInfo.getAllRows();
                if(!list || list.length == 0) {
                    msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("FinPayInterestStdInfo.table.title"));
             //还本信息生成计划提示信息改成只提示付息计划列表数据不能为空  yzg 20190905 start
                    msgArray.push(msg);
                }
            }
            /*if(newParam.vdate==''||typeof newParam.vdate == 'undefined'){
                msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("FinPayInterestStdInfo.asset.vdate"));
                msgArray.push(msg);errorClassArray.push(vdate);
            }
            if(newParam.mdate==''||typeof newParam.mdate == 'undefined'){
                msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("FinPayInterestStdInfo.asset.mdate"));
                msgArray.push(msg);errorClassArray.push(mdate);
            }
            if(newParam.calendarId==''||typeof newParam.calendarId == 'undefined'){
                msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("FinPayInterestStdInfo.asset.calendarId"));
                msgArray.push(msg);errorClassArray.push(calendarId);
            }
            if(newParam.issueAmt==''||typeof newParam.issueAmt == 'undefined'){
                msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("FinPayInterestStdInfo.asset.issueAmt2"));
                msgArray.push(msg);errorClassArray.push(issueAmt);
            }*/
          //还本信息生成计划提示信息改成只提示付息计划列表数据不能为空  yzg 20190905 end
        }
        if(msgArray&&msgArray.length>0){
            msgArray.forEach(function(value){
                messages = messages + '<span>' +value + '</span>' + '<br>';
            });
        }
        if(errorClassArray&&errorClassArray.length>0){
            errorClassArray.forEach(function(value){
                var $el = $(value);
                if ($el.is('.input-select')) {
                    $el = $el.find(' ~ .select2').find('.select2-selection');
                }else if ($(value).is('.input-checkbox')) {
                    $el = $el.next('ul.layui-checkbox-u').find('li.layui-chcekbox');
                }
                $el.addClass('errors');
            });
        }
        if(messages){
            Joyin.error(messages);
            $('.layui-layer-padding').height('80');
            $('.layui-layer-dialog').height('200');
            return false;
        }else{
            return true;
        }
    }
    
    /**
     * 还本计划维护前台check
     * 1.还款日不可重复
     * 2.还款金额汇总需等于资产本金金额
     * 3.最后一条的还本日需为资产到期日
     * 4.计息截止日大于起息日小于等于到期日
     * mdate:资产到期日  issueAmt:资产融资金额
     * vdate:资产起息日
     */
    var repayPlanCheck = function(vdate,mdate,issueAmt){
        sortTalbe();
        var msgArray=[];var errorClassArray = [];var messages="";
        var list = getAllRows();var msg;
        var sumAmt = 0;
        if(!list || list.length == 0) {
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("FinPayInterestStdInfo.table.title"));
        }else{
            $.each(list,function(index,obj){
                if(obj.mdate==''||typeof obj.mdate == 'undefined'){
                        msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("BussFinRepayment.mdate"));
                }else{
                    if(new Date(new Date(obj.mdate).format("yyyy-MM-dd")).DateDiff('d',vdate)>=0){
                        msg = JoyinUtil.getMessage('BUSS_COM_E_0036',[JoyinUtil.getLabel("BussFinRepayment.mdate"),JoyinUtil.getLabel("FinRepayPlan.vdate")]);
                    }
                    if(new Date(new Date(obj.mdate).format("yyyy-MM-dd")).DateDiff('d',mdate)<0){
                        msg = JoyinUtil.getMessage('BUSS_COM_E_0003',[JoyinUtil.getLabel("BussFinRepayment.mdate"),JoyinUtil.getLabel("FinRepayPlan.mdate")]);
                    }
                }
                if(obj.prinAmt==''||typeof obj.prinAmt == 'undefined'){
                    msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("BussFinRepayment.prinAmtOther"));
                }
                if(index<list.length-1){
                    var current = new Date(list[index].mdate).format('yyyy-MM-dd');
                    var next = new Date(list[index+1].mdate).format('yyyy-MM-dd');
                    if(current==next){
                        msg = JoyinUtil.getMessage('FIN_ASSET_E_0009',JoyinUtil.getLabel("BussFinRepayment.mdate"));
                    }
                }
                sumAmt = JoyinMath.doAdd(sumAmt,obj.prinAmt,8);
            });
            if(JoyinMath.doMinus(sumAmt,issueAmt)!=0){
                msg = JoyinUtil.getMessage('BUSS_COM_E_0048',[JoyinUtil.getLabel("BussFinRepayment.cashAmt.sumCheck2"),JoyinUtil.getLabel("FinPayInterestStdInfo.asset.issueAmt2")]);
            }
        }
        if(msg) {
            Joyin.error(msg);
            return false;
        }
        return true;
    }
    
    /**
     * 比较两个数值是否相等
     * @param numStr1
     * @param numStr2
     * @returns
     */
    function isNumEquals (numStr1, numStr2) {
    	var isNumStr1Null = false;
    	var isNumStr2Null = false;
    	if (numStr1 == null || numStr1 == '' || !numStr1) {
    		isNumStr1Null = true;
    	}
    	if (numStr2 == null || numStr2 == '' || !numStr2) {
    		isNumStr2Null = true;
    	}
    	if (isNumStr1Null && isNumStr2Null) {
    		return true;
    	}
    	if ((isNumStr1Null && !isNumStr2Null)
    			|| (!isNumStr1Null && isNumStr2Null)) {
    		return false;
    	}
    	var num1 = parseFloat(numStr1);
    	var num2 = parseFloat(numStr2);
    	var tempResutl = JoyinMath.doMinus(num1, num2, 12);
    	if (tempResutl == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /*
     * 保存验证
     */
    function check() {
        // 付息计划列表为空时
        var list = getAllRows();debugger;
        if(!list || list.length == 0) {
            getElementById('btn_preview').trigger('click');
        }
        else {
            var newParam = getPreviewParams(), msg;
            // 起息日，到期日，交易日历与计划生成时记录的值比较，值变更时
            if(previewParams.vdate != newParam.vdate) {
                msg = JoyinUtil.getMessage('BUSS_COM_E_0037', [JoyinUtil.getLabel("FinPayInterestStdInfo.asset.vdate"),JoyinUtil.getLabel("FinPayInterestStdInfo.title")]);
            }
            else if(previewParams.mdate != newParam.mdate) {
                msg = JoyinUtil.getMessage('BUSS_COM_E_0037', [JoyinUtil.getLabel("FinPayInterestStdInfo.asset.mdate"),JoyinUtil.getLabel("FinPayInterestStdInfo.title")]);
            }
            else if(previewParams.calendarId != newParam.calendarId) {
                msg = JoyinUtil.getMessage('BUSS_COM_E_0037', [JoyinUtil.getLabel("FinPayInterestStdInfo.asset.calendarId"),JoyinUtil.getLabel("FinPayInterestStdInfo.title")]);
            }
            //计息要素所有值  计划生成时记录的值比较，值变更时
            else if(previewParams.finInterestFactorDto.couponSpecies != newParam.finInterestFactorDto.couponSpecies||previewParams.finInterestFactorDto.rateType != newParam.finInterestFactorDto.rateType
                ||previewParams.finInterestFactorDto.basis != newParam.finInterestFactorDto.basis||previewParams.finInterestFactorDto.basisStyle != newParam.finInterestFactorDto.basisStyle
                || !isNumEquals(previewParams.finInterestFactorDto.rate, newParam.finInterestFactorDto.rate) || !isNumEquals(previewParams.finInterestFactorDto.highestRate, newParam.finInterestFactorDto.highestRate)
                || !isNumEquals(previewParams.finInterestFactorDto.lowestRate, newParam.finInterestFactorDto.lowestRate) || previewParams.finInterestFactorDto.benchmarkId != newParam.finInterestFactorDto.benchmarkId
                || previewParams.finInterestFactorDto.coefficient != newParam.finInterestFactorDto.coefficient || !isNumEquals(previewParams.finInterestFactorDto.spreadRate, newParam.finInterestFactorDto.spreadRate)
                ||previewParams.finInterestFactorDto.resetType != newParam.finInterestFactorDto.resetType||previewParams.finInterestFactorDto.observeBefDay != newParam.finInterestFactorDto.observeBefDay
                ||previewParams.finInterestFactorDto.observeBefUnit != newParam.finInterestFactorDto.observeBefUnit|| !isNumEquals(previewParams.finInterestFactorDto.investRate, newParam.finInterestFactorDto.investRate)
                ||!isNumEquals(previewParams.finInterestFactorDto.loadRate, newParam.finInterestFactorDto.loadRate) || !isNumEquals(previewParams.finInterestFactorDto.buyOutRate, newParam.finInterestFactorDto.buyOutRate)
                || !isNumEquals(previewParams.finInterestFactorDto.referRate, newParam.finInterestFactorDto.referRate)
                ||previewParams.finInterestFactorDto.calRateType != newParam.finInterestFactorDto.calRateType) {
                msg = JoyinUtil.getMessage('BUSS_COM_E_0037', [JoyinUtil.getLabel("FinPayInterestStdInfo.InterestFactor"),JoyinUtil.getLabel("FinPayInterestStdInfo.title")]);
            }
            //付息规则，计划生成时记录的值比较，值变更时
            else if(previewParams.payCycle != newParam.payCycle) {
                msg = JoyinUtil.getMessage('BUSS_COM_E_0037', [JoyinUtil.getLabel("BussFinRepayment"),JoyinUtil.getLabel("BussFinRepayment.title")]);
            }
            //存款类
            if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F07){
                //存款本金变化
                if(previewParams.issueAmt != newParam.issueAmt){
                    msg = JoyinUtil.getMessage('BUSS_COM_E_0037', [JoyinUtil.getLabel("FinPayInterestStdInfo.asset.issueAmt"),JoyinUtil.getLabel("FinPayInterestStdInfo.title")]);
                }
                
            }
            //计划类、项目类
            if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F17||INT_ASSET_TYPE==FINPROD_TYPE_TG_F18){
                //存款本金变化
                if(previewParams.issueAmt != newParam.issueAmt){
                    msg = JoyinUtil.getMessage('BUSS_COM_E_0037', [JoyinUtil.getLabel("FinPayInterestStdInfo.asset.issueAmt2"),JoyinUtil.getLabel("FinPayInterestStdInfo.title")]);
                }

                if(previewParams.isPayIntFlag != newParam.isPayIntFlag){
                    msg = JoyinUtil.getMessage('BUSS_COM_E_0037', [JoyinUtil.getLabel("FinPayInterestStdInfo.ispayInt"),JoyinUtil.getLabel("FinPayInterestStdInfo.title")]);
                }
            }
            if(msg) {
                Joyin.error(msg);
                return false;
            }
        }
        return true;
    }
    
    /**
     * 维护还本计划后，是否点击确认
     * 可以通过支付日是否为空判断是否重新计算利息
     */
    var isComfirmCheck = function(){
        var list = getAllRows();var msg;
        if(list){
            $.each(list,function(index,obj){
                if(obj.payDate==''||typeof obj.payDate == 'undefined'){
                    msg = JoyinUtil.getMessage("还本计划已变更,请点击确认,重新计算应计利息!");
                }
            })
        }
        if(msg) {
            Joyin.error(msg);
            return false;
        }
        return true;
    }
    /**
     * 计算剩余金额
     */
    var calSurplusAmt = function(){
        if(doneFlag){
            return;
        }
        sortTalbe();
        var arr = [];
        var issueAmt = $('#issueAmt').val();
        var list = getAllRows();
        //汇总还本金额
        var sumPrinAmt;
        if(list){
            $.each(list,function(index,obj){
                sumPrinAmt = JoyinMath.doAdd(sumPrinAmt,obj.prinAmt,8);
                obj.surplusAmt = JoyinMath.doMinus(issueAmt,sumPrinAmt,8);
                
                // 联动计算还款日（支付日）
                var paramObj = {};
                paramObj.mdate = obj.mdate;
                paramObj.finprodId = $('#finprodId').val();
                
                Joyin.ajaxSync('/component/asset/BussFinRepayment/genPayDate', paramObj, function(result) {
                    if (result) {
                        obj.payDate = result;
                    }
            });
            });
        }
        doneFlag = true;
        table.reload({data:list, url:null});
    }
    /**
     * 重新排序table
     */
    var sortTalbe = function(){
    	//排序不改变本次参数保存
        loadAjaxData = false;
        var list = getAllRows();
        list.sort(function(a,b){
            return -(new Date(a.mdate).DateDiff('d',b.mdate));
        })
        table.reload({data:list, url:null})
    }
    
    
    // 公开接口
    window.BussFinRepayment = {
            getAllRows:getAllRows,
            check:check,
            generationPlan:generationPlan,
            repayPlanCheck:repayPlanCheck,
            disableBussFinRepay:disableBussFinRepay,
            isComfirmCheck:isComfirmCheck
    };
    
    $(function(){
        if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F18||INT_ASSET_TYPE==FINPROD_TYPE_TG_F17){
            if(INT_ASSET_TYPE_REPAY==COMMON_BUSINESS_TYPE_FINPROD10){
                //还本计划维护
                getElementById('title','div').hide();
                getElementById('content','div').hide();
            }else{
                getElementById('content','div').show(); 
                //add by wm 去除项目类资产还本计划确认按钮 start 2019-08-31
                getElementById('btn_confirm').hide(); 
                //add by wm 去除项目类资产还本计划确认按钮 end 2019-08-31
            }
        }else{
            getElementById('content','div').hide();
        }
        //项目类资产和计划类利率型资产不展示是否还本不带息 add by zhouxiuping at 2019.12.12 at begin
        if(INT_ASSET_TYPE==FINPROD_TYPE_TG_F18 || COMMON_BUSINESS_TYPE==COMMON_BUSINESS_TYPE_F03){
            getElementById('isPayInt','div').show();
        }else{
            getElementById('isPayInt','div').hide();
        } //项目类资产和计划类利率型资产不展示是否还本不带息 add by zhouxiuping at 2019.12.12 at end
        //生成计划
        getElementById("btn_generationPlan").click(function(){
        	//还本信息生成计划提示信息改成只提示付息计划列表数据不能为空  yzg 20190905 start
            //Joyin.ajaxJson('/component/asset/BussFinRepayment/check', getPreviewParams(), function(result) {
                if(generationPlanCheck()&&FinPayInterestStdInfo.comfirmCheck()&&FinPayInterestStdInfo.check()){
                      //生成还本计划
                        generationPlan(false,true);
                }
            //});
            //还本信息生成计划提示信息改成只提示付息计划列表数据不能为空  yzg 20190905 end
        })
        //确认按钮
        getElementById("btn_confirm").click(function(){
            if(FinPayInterestStdInfo.comfirmCheck()){
                isAgainCalIntRepay = true;
                Joyin.ajaxJson('/component/asset/BussFinRepayment/check', getPreviewParams(), function(result) {
                    Joyin.confirm(JoyinUtil.getMessage('FIN_ASSET_W_0013'),function(){
                        generationPlan(true,true);
                    });
                });
            }
        })
    });
}());