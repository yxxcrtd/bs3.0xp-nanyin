$(function () {
    if (pageModel == "1") {
        // 编辑模式
        
        $("input").attr("disabled",false);
        $("table").attr("disabled",false);
        $("select").attr("disabled",false);
        $("#finprodId").attr("disabled",true);
        $("#bondid").attr("disabled",true);
        $("#bondtype").attr("disabled",true);
        
        // 如果已存在交易，起息日、交易场所、债券分类和币种不可修改
        if (hasDeals == '1') {
            $("#vdate1").attr("disabled",true);
            $("#tradingplace").attr("disabled",true);
            $("#bondtype").attr("disabled",true);
            $("#ccy").attr("disabled",true);
        }
        var table1Cols=[
                        {field:'applydatestr', title: '行权申请开始日', sort: true, edit:true, editOption:{type: 'date'}},
                        {field:'applydateend', title: '行权申请结束日',sort: true, edit:true, editOption:{type: 'date'}},           
                        {field:'xqmode', title: '行权方式',sort: true, edit:true, editOption:{type: 'select', url: JoyinUtil.getRealPath('/valuation/assets/bondasset/ValBondassetquery002/getExerciseType')}},
                        {field:'exerciseDate', title: '行权日',sort: true, edit:true, editOption:{type: 'date'}},
                        {field:'xqnetprice', title: '行权净价', sort: true, edit:true, editOption:{type: 'number'}},
                        {field:'xqclause', title: '行权条款', sort: true, edit:true},
                        {field:'operation', title : '<i id="ValBondassetquery002_xq_add" class="iconfont icon-found" style="color:green"></i>',align : 'center',type : 'action',buttons : ['delt']}
                    ];
        //债券利率报价信息
        var table2Cols=[
                        {field:'efdate', title: '生效日', width:120, edit:true, editOption:{type: 'date'}},
                        {field:'rate2', title: '利率(%)', edit:true, editOption:{type: 'number'}},
                        {field:'operation', title : '<i id="ValBondassetquery002_bj_add" class="iconfont icon-found" style="color:green"></i>',align : 'center',type : 'action',buttons : ['delt']}
                    ];
        //付息计划列表
        var table3Cols=[
                        {field:'theorydatestr', title: '理论开始日',sort:'true',edit:true, editOption:{type: 'date'}},
                        {field:'theorydateend', title: '理论结束日',sort:'true',edit:true, editOption:{type: 'date'}},
                        {field:'actdatestr', title: '实际计息开始日',sort:'true',edit:true, editOption:{type: 'date'}},
                        {field:'actdateend', title: '实际计息结束日',sort:'true',edit:true, editOption:{type: 'date'}},
                        {field:'plandate', title: '计划支付日',sort:'true',edit:true, editOption:{type: 'date'}},
                        {field:'planintamt',title: '计划支付利息',sort:'true',edit:true,editOption:{type: 'money'},digit:3},
                        {field:'operation', title : '<i id="ValBondassetquery002_fx_add" class="iconfont icon-found" style="color:green"></i>',align : 'center',type : 'action',buttons : ['delt']}
                    ];
        //还本计划列表
        var table4Cols=[
                        {field:'jsdateend', title: '计息结束日', edit:true, editOption:{type: 'date'}},
                        {field:'paydate', title: '支付日', edit:true, editOption:{type: 'date'}},
                        {field:'remoney', title: '还本面额/百元', edit:true, editOption:{type: 'money'}},
                        {field:'operation', title : '<i id="ValBondassetquery002_hb_add" class="iconfont icon-found" style="color:green"></i>',align : 'center',type : 'action',buttons : ['delt']}
                    ];
        
    }
    if (pageModel == "2") {
        // 查看模式
        //行权信息
        
        var table1Cols=[
                        {field:'applydatestr', title: '行权申请开始日', sort: true, edit:false, editOption:{type: 'date'}},
                        {field:'applydateend', title: '行权申请结束日',sort: true, edit:false, editOption:{type: 'date'}},           
                        {field:'xqmode', title: '行权方式',sort: true, edit:false, editOption:{type: 'select', url: JoyinUtil.getRealPath('/valuation/assets/bondasset/ValBondassetquery002/getExerciseType')}},
                        {field:'exerciseDate', title: '行权日',sort: true, edit:false, editOption:{type: 'date'}},
                        {field:'xqnetprice', title: '行权净价', sort: true, edit:false, editOption:{type: 'number'}},
                        {field:'xqclause', title: '行权条款', sort: true, edit:false},
                        {field:'operation', title : '<i id="ValBondassetquery002_xq_add" class="iconfont icon-found" style="color:green"></i>',align : 'center',type : 'action',buttons : ['delt']}
                    ];
        //债券利率报价信息
        var table2Cols=[
                        {field:'efdate', title: '生效日', width:120},
                        {field:'rate2', title: '利率(%)'}
//                        {field:'operation', title : '<i id="ValBondassetquery002_bj_add" class="iconfont icon-found" style="color:green"></i>',align : 'center',type : 'action',buttons : ['delt']}
                    ];
        //付息计划列表
        var table3Cols=[
                        {field:'theorydatestr', title: '理论开始日',sort:'true'},
                        {field:'theorydateend', title: '理论结束日',sort:'true'},
                        {field:'actdatestr', title: '实际计息开始日',sort:'true'},
                        {field:'actdateend', title: '实际计息结束日',sort:'true'},
                        {field:'plandate', title: '计划支付日',sort:'true'},
                        {field:'planintamt',title: '计划支付利息',sort:'true',digit:3}
//                        {field:'operation', title : '<i id="ValBondassetquery002_fx_add" class="iconfont icon-found" style="color:green"></i>',align : 'center',type : 'action',buttons : ['delt']}
                    ];
        //还本计划列表
        var table4Cols=[
                        {field:'jsdateend', title: '计息结束日'},
                        {field:'paydate', title: '支付日'},
                        {field:'remoney', title: '还本面额/百元'},
//                        {field:'operation', title : '<i id="ValBondassetquery002_hb_add" class="iconfont icon-found" style="color:green"></i>',align : 'center',type : 'action',buttons : ['delt']}
                    ];
        
        
        
        
        
        
        
        $("input").attr("disabled",true);
        $("table").attr("disabled",true);
    }
    
    /**
     * 定义数据表格-行权信息
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: basePath + 'valuation/assets/bondasset/ValBondassetquery002/bondxqinfo',
            type: 'POST',
            data: {
                finprodId: $("#finprodId").val(),
                workflowId:workflowId
            }    
        },
        checkbox: false,
        searchOnInit: true,
        height: 150,
        cols:table1Cols,
      loadonce:true,
      page:false
    });
    
    
    /**
     * 定义数据表格-债券利率报价信息
     */
    var table1=$("#dataTable1").joyintable({
        ajax: {
            url: basePath + 'valuation/assets/bondasset/ValBondassetquery002/bondintrate',
            type: 'POST',
            data: {
                finprodId: $("#finprodId").val(),
                workflowId:workflowId
            }    
        },
        checkbox: false,
        searchOnInit: true,
        height: 150,
        cols:table2Cols,
        loadonce:true,
        page:false
    });
    /**
     * 定义数据表格-付息计划列表
     */
    var table2=$("#dataTable2").joyintable({
        ajax: {
            url: basePath + 'valuation/assets/bondasset/ValBondassetquery002/bondintpay',
            type: 'POST',
            data: {
                finprodId: $("#finprodId").val(),
                workflowId:workflowId
            }    
        },
        checkbox: false,
        searchOnInit: true,
        height: 150,
        cols:table3Cols,
      loadonce:true,
      page:false
    });
    /**
     * 定义数据表格-还本计划列表
     */
    var table3=$("#dataTable3").joyintable({
        ajax: {
            url: basePath + 'valuation/assets/bondasset/ValBondassetquery002/bondrepay',
            type: 'POST',
            data: {
                finprodId: $("#finprodId").val(),
                workflowId:workflowId
            }    
        },
        checkbox: false,
        searchOnInit: true,
        height: 150,
        cols:table4Cols,
      loadonce:true,
      page:false
    });
    
    /**
     * 绑定按钮事件
     */
    $('#ValBondassetquery002_btn_submit').click(function() {
        var retId = notNullCheck();
        if (retId != '') {
            document.getElementById(retId).focus();
            return;
        }
        var object = $.serializeToObject('#submitForm');
        object.operateType = "submit";
        object.pageModel = pageModel;
        object.xqList = JSON.stringify(table.getAllRows());
        object.bjList = JSON.stringify(table1.getAllRows());
        object.fxList = JSON.stringify(table2.getAllRows());
        object.hbList = JSON.stringify(table3.getAllRows());
        
        Joyin.ajax(basePath + 'valuation/assets/bondasset/ValBondassetquery002/doUpdateCheck', object, function(result) {
            Joyin.confirm(result.data, function() {
                WorkFlow.doSubmit(
                        basePath + 'valuation/assets/bondasset/ValBondassetquery002/doUpdate', 
                        object, 
                        "ValBondassetquery002_btn_submit"
                );
            });
        });
    });
    
    /**
     * 行权列表点击添加按钮触发事件
     */
    table.bind('click',"#ValBondassetquery002_xq_add",function(){
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        Joyin.ajaxJson('doAdd', object, function(result){
            if(result.errorList.length==0){
                result.data.status = 'N';
                table.addRow(result.data);
                table.events();
            }
        });
    });
    
    /**
     * 债券利率报价信息列表点击添加按钮触发事件
     */
    table1.bind('click',"#ValBondassetquery002_bj_add",function(){
        var couponTypeArr = ['02','03'];
        var couponType = $("#couponType").val();
        if (couponTypeArr.indexOf(couponType) >= 0 && table1.getAllRows().length == 1) {
            return;
        }
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        Joyin.ajaxJson('doAdd', object, function(result){
            if(result.errorList.length==0){
                result.data.status = 'N';
                table1.addRow(result.data);
                table1.events();
            }
        });
    });
    
    /**
     * 付息计划列表点击添加按钮触发事件
     */
    table2.bind('click',"#ValBondassetquery002_fx_add",function(){
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        Joyin.ajaxJson('doAdd', object, function(result){
            if(result.errorList.length==0){
                result.data.status = 'N';
                table2.addRow(result.data);
                table2.events();
            }
        });
    });
    
    /**
     * 还本计划列表点击添加按钮触发事件
     */
    table3.bind('click',"#ValBondassetquery002_hb_add",function(){
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        Joyin.ajaxJson('doAdd', object, function(result){
            if(result.errorList.length==0){
                result.data.status = 'N';
                table3.addRow(result.data);
                table3.events();
            }
        });
    });
    
    
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            if(!data.seqno){
                table.removeRow(data);
                table.events();
            }
        });
        
    });
    
    /**
     * 绑定删除事件
     */
    table1.bind('click', '.delt', function(data, index) {
        var vdate = $('#vdate1').val()
        var vefdateCount = 0;
        for (var i = 0; i < table1.getAllRows().length; i ++) {
            if (table1.getAllRows()[i].efdate == vdate) {
                vefdateCount ++;
            }
        }
        if (data.efdate == vdate && vefdateCount == 1) {
            Joyin.error('起息日当天的报价信息不能删除！');
            return;
        }
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            if(!data.seqno){
                table1.removeRow(data);
                table1.events();
            }
        });
        
    });
    
    /**
     * 绑定删除事件
     */
    table2.bind('click', '.delt', function(data, index) {
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            if(!data.seqno){
                table2.removeRow(data);
                table2.events();
            }
        });
        
    });
    
    /**
     * 绑定删除事件
     */
    table3.bind('click', '.delt', function(data, index) {
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            if(!data.seqno){
                table3.removeRow(data);
                table3.events();
            }
        });
        
    });
    
    String.prototype.getBytesLength = function() {
    	var totalLength = 0;
    	var charCode;
    	for (var i = 0; i < this.length; i++) {
    		charCode = this.charCodeAt(i);
    		if (charCode < 0x007f) {
    			totalLength++;
    		} else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
    			totalLength += 2;
    		} else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
    			totalLength += 3;
    		} else{
    			totalLength += 4;
    		}
    	}
    	return totalLength;
    }  

    var docH= $(document).width();
    $('.layui-colla-content').css('width',docH-50);
    
    var notNullCheck = function() {
    	// 债券简称长度限制
    	var finprodAbbr = $("#finprodAbbr").val();
    	if (finprodAbbr.getBytesLength() > 200) {
    		Joyin.error('债券简称过长！');
            return 'finprodAbbr';
    	}
    	// 债券名称长度限制
    	var finprodName = $("#finprodName").val();
    	if (finprodName.getBytesLength() > 200) {
    		Joyin.error('债券名称过长！');
            return 'finprodName';
    	}
    	// 发行价格必须是数字
    	var prcie = $("#price").val();
    	var priceNum = Number(prcie);
    	if (isNaN(priceNum) || priceNum < 0) {
    		Joyin.error('发行价格必须为大于0的有效数字！');
            return 'price';
    	}
    	// 发行总额必须是数字
    	var sumprice = $("#sumprice").val();
    	var sumpriceNum = Number(sumprice);
    	if (isNaN(sumpriceNum) || sumpriceNum < 0) {
    		Joyin.error('发行总额必须为大于0的有效数字！');
            return 'sumprice';
    	}
    	// 到期赎回价必须是数字
//    	var dqshj = $("#dqshj").val();
//    	var dqshjNum = Number(dqshj);
//    	if (isNaN(dqshjNum) || dqshjNum < 0) {
//    		Joyin.error('到期赎回价必须为大于0的有效数字！');
//            return 'dqshj';
//    	}
    	// 初始利率必须是数字
    	var rate = $("#ratestr").val();
    	var rateNum = Number(rate);
    	if (isNaN(rateNum) || rateNum < 0) {
    		Joyin.error('初始利率必须为大于0的有效数字！');
            return 'ratestr';
    	}
        // 当息票品种选择“零息”、“贴现”、“利随本清”时
        var couponType_array = ['02','03','04'];
        var couponType = $('#couponType').val();
        if (couponType_array.indexOf(couponType) >= 0) {
            // 是否含权债应选否
            if ("N" != $("#isRight").val()) {
                Joyin.error('当息票品种选择“贴现”、“零息”、“利随本清”时，【是否含权债】应选否！');
                return 'isRight';
            }
        }
        // 当债券类型选择“资产支持证券”时，是否含权债显示为“否”
        var bondtype_arr = ['024','025'];
        var bondtype = $('#bondtype').val();
        if (bondtype_arr.indexOf(bondtype) >= 0) {
            if ("N" != $("#isRight").val()) {
                Joyin.error('当债券类型选择“资产支持证券”时，【是否含权债】应选否！');
                return 'isRight';
            }
        }
        // 当债券类型为非“理财直接融资工具”时，交易场所不能选“理财直接融资工具平台”
        if ('032' != bondtype) {
        	if ('19' == $('#tradingplace').val()) {
        		Joyin.error('债券类型不是“理财直接融资工具”，交易场所不能选“理财直接融资工具平台”！');
                return 'tradingplace';
        	}
        }
        // 当债券类型为“理财直接融资工具”时，交易场所必须选“理财直接融资工具平台”
        if ('032' == bondtype) {
        	if ('19' != $('#tradingplace').val()) {
        		Joyin.error('债券类型是“理财直接融资工具”，交易场所必须为“理财直接融资工具平台”！');
                return 'tradingplace';
        	}
        }

        var notNullElements = [
            ['资产代码','finprodId'],['债券代码','bondid'],['债券简称','finprodAbbr'],['债券名称','finprodName'],['发行价格','price'],
            ['币种','ccy'],['债券分类','bondtype'],['交易场所','tradingplace'],['交易日历','tradingtime'],
            ['起息日','vdate'],['到期日','mdate'],['是否含权债','isRight'],['到期赎回价','dqshj'],['息票品种','couponType'],
            ['利率类型','ratetype',],['初始利率(%)','ratestr'],
//            ['营业日准则','yyrzz']
            ['截止日营业日准则','endTdayRule'], ['支付日营业日准则','payTdayRule']
        ];
        for (var i = 0; i < notNullElements.length; i ++) {
            if (notNullElements[i][1] == 'dqshj') {
                // 券分类为非可转债、非可交换债时，无需校验到期赎回价不能为空！
                var bondType_arr = ['028','029'];
                var bondType = $('#bondtype').val();
                if (bondType_arr.indexOf(bondType) < 0) {
                    continue;
                }
            }
            if ($('#' + notNullElements[i][1]).val() == '') {
                Joyin.error(JoyinUtil.getMessage(notNullElements[i][0] + "不能为空！"));
                return notNullElements[i][1];
            }
        }
        
        // 债券起息日不能大于债券到期日
        var vdate = $('#vdate1').val();
        var mdate = $('#mdate1').val();
        if (vdate >= mdate) {
            Joyin.error('债券【起息日】不能大于债券【到期日】！');
            return 'mdate1';
        }
        // 是含权债时
        var isRigth = $('#isRight').val();
        // 行权日期集合
        var exerciseDateArr = new Array();
        if ('Y' == isRigth) {
            // 含权摊销日设置不能为空
            if ($('#exeDate').val() == '') {
                Joyin.error('【是否含权债】为"是"，【含权摊销日设置】不能为空！');
                return 'exeDate';
            }
            // 行权信息不能为空
            if (table.getAllRows() == 0) {
                Joyin.error('【是否含权债】为"是"，行权信息不能为空！');
                return 'dataTable';
            }
            for (var i = 0; i < table.getAllRows().length; i ++) {
                // 必填校验
                if (table.getAllRows()[i].applydatestr == 0) {
                    Joyin.error('第' + (i + 1) + '条行权计划的【行权申请开始日】为空！');
                    return 'dataTable';
                }
                if (table.getAllRows()[i].applydateend == 0) {
                    Joyin.error('第' + (i + 1) + '条行权计划的【行权申请结束日】为空！');
                    return 'dataTable';
                }
                if (table.getAllRows()[i].xqmode == '' || table.getAllRows()[i].xqmode == undefined) {
                    Joyin.error('第' + (i + 1) + '条行权计划的【行权方式】为空！');
                    return 'dataTable';
                }
                if (table.getAllRows()[i].exerciseDate == 0) {
                    Joyin.error('第' + (i + 1) + '条行权计划的【行权日】为空！');
                    return 'dataTable';
                }
                if (table.getAllRows()[i].xqnetprice == 0) {
                    Joyin.error('第' + (i + 1) + '条行权计划的【行权净价】为空！');
                    return 'dataTable';
                }
                // 行权条款字节数不能大于1000
                if (table.getAllRows()[i].xqclause != undefined && table.getAllRows()[i].xqclause.getBytesLength() > 1000) {
                	Joyin.error('第' + (i + 1) + '条行权计划的【行权条款】过长！');
                    return 'dataTable';
                }
                // 行权日期不大于债券到期日
                if (table.getAllRows()[i].exerciseDate > mdate) {
                    Joyin.error('【行权日期】不能大于债券【到期日】！');
                    return 'dataTable';
                }
                // 行权日期>=行权申请结束日>=行权申请开始日
                if (!(table.getAllRows()[i].exerciseDate >= table.getAllRows()[i].applydateend &&
                        table.getAllRows()[i].applydateend >= table.getAllRows()[i].applydatestr)) {
                    Joyin.error('第' + (i + 1) + '条行权计划：【行权申请开始日】应小于等于【行权申请结束日】，【行权申请结束日】应小于等于【行权日期】！');
                    return 'dataTable';
                }
                exerciseDateArr[i] = table.getAllRows()[i].exerciseDate;
            }
            // 行权日期不可重复
            if ((new Set(exerciseDateArr)).size != table.getAllRows().length) {
                Joyin.error('行权信息的【行权日】不可重复！');
                return 'dataTable';
            }
        }
        
        // 息票品种为付息、利随本清时，报价信息列表不能为空
        var couponType_arr = ['01','04'];
        if (couponType_arr.indexOf(couponType) >= 0) {
            if (table1.getAllRows() == 0) {
                Joyin.error('息票品种为“付息”、“利随本清”时，债券利率报价信息不能为空！');
                return 'dataTable1';
            }
        }
        // 债券利率报价信息
        var efdateArr = new Array();
        for (var i = 0; i < table1.getAllRows().length; i ++) {
            // 必填校验
            if (table1.getAllRows()[i].efdate == 0) {
                Joyin.error('第' + (i + 1) + '条债券利率报价信息【生效日】为空！');
                return 'dataTable1';
            }
            if (table1.getAllRows()[i].rate2 == '' || table1.getAllRows()[i].rate2 == undefined) {
                Joyin.error('第' + (i + 1) + '条债券利率报价信息【利率】为空！');
                return 'dataTable1';
            }
            // 生效日不能大于债券到期日
            if (table1.getAllRows()[i].efdate > mdate) {
                Joyin.error('第' + (i + 1) + '条债券利率报价信息【生效日】不能大于债券【到期日】！');
                return 'dataTable1';
            }
            // 生效日为起息日的利率必须等于初始利率
            if (table1.getAllRows()[i].efdate == vdate) {
                if (table1.getAllRows()[i].rate2 != $('#ratestr').val()) {
                    Joyin.error('生效日为起息日的报价必须等于初始利率！');
                    return 'dataTable1';
                }
            }
            efdateArr[i] = table1.getAllRows()[i].efdate;
        }
        if ((new Set(efdateArr)).size != table1.getAllRows().length) {
            Joyin.error('债券利率报价信息的【生效日】不能重复！');
            return 'dataTable1';
        }
        // 付息计划列表不能为空
        if (table2.getAllRows() == 0) {
            Joyin.error('付息计划列表不能为空！');
            return 'dataTable2';
        }
        // 当息票品种选择“零息”、“贴现”、“利随本清”时，只能有一条付息计划
        var couponType_arr_fx = ['02','03','04'];
        if (couponType_arr_fx.indexOf(couponType) >= 0) {
            if (table2.getAllRows().length > 1) {
                Joyin.error('当息票品种选择“贴现”、“零息”、“利随本清”时，只能有一条付息计划！');
                return 'dataTable2';
            }
        }
        // 付息计划列表第一条数据的实际计息开始日要等于债券起息日
        if (table2.getAllRows()[0].actdatestr != vdate) {
            Joyin.error('第一条付息计划的【实际计息开始日】必须等于债券【起息日】！');
            return 'dataTable2';
        }
        // 付息计划
        // 实际计息结束日集合
        var actdateendArr = new Array();
        for (var i = 0; i < table2.getAllRows().length; i ++) {
            // 必填校验
            if (table2.getAllRows()[i].theorydatestr == 0) {
                Joyin.error('第' + (i + 1) + '条付息计划的【理论开始日】为空！');
                return 'dataTable2';
            }
            if (table2.getAllRows()[i].theorydateend == 0) {
                Joyin.error('第' + (i + 1) + '条付息计划的【理论结束日】为空！');
                return 'dataTable2';
            }
            if (table2.getAllRows()[i].actdatestr == 0) {
                Joyin.error('第' + (i + 1) + '条付息计划的【实际计息开始日】为空！');
                return 'dataTable2';
            }
            if (table2.getAllRows()[i].actdateend == 0) {
                Joyin.error('第' + (i + 1) + '条付息计划的【实际计息结束日】为空！');
                return 'dataTable2';
            }
            if (table2.getAllRows()[i].plandate == 0) {
                Joyin.error('第' + (i + 1) + '条付息计划的【计划支付日】为空！');
                return 'dataTable2';
            }
            if (table2.getAllRows()[i].planintamt == '' || table2.getAllRows()[i].planintamt == undefined) {
                Joyin.error('第' + (i + 1) + '条付息计划的【计划支付利息】为空！');
                return 'dataTable2';
            }
            // 实际计息开始日必须小于等于实际计息结束日！
            if (table2.getAllRows()[i].actdatestr > table2.getAllRows()[i].actdateend) {
                Joyin.error('第' + (i + 1) + '条付息计划的【实际计息开始日】必须小于等于【实际计息结束日】！');
                return 'dataTable2';
            }
            // 实际计息开始日 = 上一条付息计划.实际计息结束日
            if (i > 0 && i <= table2.getAllRows().length) {
                if (table2.getAllRows()[i].actdatestr != table2.getAllRows()[i-1].actdateend) {
                    Joyin.error('第' + (i + 1) + '条付息计划的【实际计息开始日】不等于第' + i + '条付息计划的【实际计息结束日】！');
                    return 'dataTable2';
                }
            }
            actdateendArr[i] = table2.getAllRows()[i].actdateend;
        }
        // 最后一条付息计划的实际计息结束日等于到期日！
        if (table2.getAllRows()[table2.getAllRows().length - 1].actdateend != mdate) {
            Joyin.error('最后一条付息计划的【实际计息结束日】要等于债券【到期日】！');
            return 'dataTable2';
        }
        // 还本计划列表不能为空
        if (table3.getAllRows() == 0) {
            Joyin.error('还本计划列表不能为空！');
            return 'dataTable3';
        }
        // 当息票品种选择“零息”、“贴现”、“利随本清”时
        var couponType_arr_hb = ['02','03','04'];
        if (couponType_arr_hb.indexOf(couponType) >= 0) {
            // 只能有一条还本计划
            if (table3.getAllRows().length > 1) {
                Joyin.error('当息票品种选择“贴现”、“零息”、“利随本清”时，只能有一条还本计划！');
                return 'dataTable3';
            }
            // 还本面额为100
            if (Number(table3.getAllRows()[0].remoney) != 100) {
                Joyin.error('当息票品种选择“贴现”、“零息”、“利随本清”时，还本面额应为100！');
                return 'dataTable3';
            }
        }
        // 最后一条还本付息计划的计息截止日要等于债券到期日！
        if (table3.getAllRows()[table3.getAllRows().length - 1].jsdateend != mdate) {
            Joyin.error('最后一条还本计划的【计息结束日】要等于债券【到期日】！');
            return 'dataTable3';
        }
        // 还本计划的所有还本面额之和必须等于100！
        var remoney = 0;
        for (var i = 0; i < table3.getAllRows().length; i ++) {
            // 必填校验
            if (table3.getAllRows()[i].jsdateend == 0) {
                Joyin.error('第' + (i + 1) + '条还本计划的【计息结束日】为空！');
                return 'dataTable3';
            }
            if (table3.getAllRows()[i].paydate == 0) {
                Joyin.error('第' + (i + 1) + '条还本计划的【支付日】为空！');
                return 'dataTable3';
            }
            if (table3.getAllRows()[i].remoney == '') {
                Joyin.error('第' + (i + 1) + '条还本计划的【还本面额/百元】为空！');
                return 'dataTable3';
            }
            remoney = remoney + Number(table3.getAllRows()[i].remoney);
        }
        if (remoney != 100) {
            Joyin.error('还本计划的所有还本面额之和必须等于100！');
            return 'dataTable3'
        }
        // 行权日期必须为其中一个实际计息结束日
        for (var i = 0; i < exerciseDateArr.length; i ++) {
            if (actdateendArr.indexOf(exerciseDateArr[i]) < 0) {
                Joyin.error('第' + (i + 1) + '条行权信息：【行权日期】必须为【实际计息结束日】！');
                return 'dataTable';
            }
        }
        
        return '';
    }
    
    /**
     * 联动绑定 - 筛选条件
     */
    var linkBind = function(){
        // 债券类型联动
        $("#bondtype").change(function(){
            // 当债券类型选择“资产支持证券”时，是否含权债显示为“否”，且不可编辑,当债券类型选择非“资产支持证券”时，是否含权债可编辑
            var bondtype_arr = ['024','025'];
            var bondtype = $('#bondtype').val();
            // 当息票品种选择为贴现、零息债券、利随本清时，是否含权债显示为“否”，且不可编辑；
            var couponType_arr = ['02','03','04'];
            var couponType = $('#couponType').val();
            if (bondtype_arr.indexOf(bondtype) >= 0 || couponType_arr.indexOf(couponType) >= 0) {
                $('#isRight').val('N').trigger('change');
                $('#isRight').attr('disabled',true);
            } else {
                $('#isRight').attr('disabled',false);
            }
            
            // 债券类型选择“理财直接融资工具”时，交易场所为理财直接融资工具平台；
            if ('032' == bondtype) {
                $('#tradingplace').val('19').trigger('change');
            }
        });
        
        // 息票品种联动
        $('#couponType').change(function(){
            // 当债券类型选择“资产支持证券”时，是否含权债显示为“否”，且不可编辑,当债券类型选择非“资产支持证券”时，是否含权债可编辑
            var bondtype_arr = ['024','025'];
            var bondtype = $('#bondtype').val();
            // 当息票品种选择为贴现、零息债券、利随本清时，是否含权债显示为“否”，且不可编辑；
            var couponType_arr = ['02','03','04'];
            var couponType = $('#couponType').val();
            if (bondtype_arr.indexOf(bondtype) >= 0 || couponType_arr.indexOf(couponType) >= 0) {
                $('#isRight').val('N').trigger('change');
                $('#isRight').attr('disabled',true);
            } else {
                $('#isRight').attr('disabled',false);
            }
            
            // 当息票品种选择“零息”、“贴现”、“利随本清”时，付息频率、推算方式、首次付息日不可填
            if (couponType_arr.indexOf(couponType) >= 0) {
                $('#paycycle').val('');
                $('#calcrule').val('');
                $('#fpayDate').val('');
                $('#paycycle').attr('disabled',true);
                $('#fpayDate').attr('disabled',true);
                $("#calcrule").next().addClass('disabled').find('li').off('click');
            } else {
                $('#paycycle').attr('disabled',false);
                $('#fpayDate').attr('disabled',false);
                
                $("#calcrule").next().removeClass('disabled').find('li').off('click').bind('click',function(){
                    if(!$(this).hasClass('laytable-checkbox-selected')){
                        $("#calcrule").next().find('li').removeClass('laytable-checkbox-selected');
                        $(this).addClass('laytable-checkbox-selected');
                    }
                });
            }
            
            // 息票品种选择利随本清时，利率类型为固定利率，不可改
            if ('04' == couponType) {
                $("#ratetype").val("01");
                $("#ratetype").next().addClass('disabled').find('li').off('click');
                $("#ratetype").next().find('li').each(function(index,n){
                    if($(this).data('value') == '01'){
                        $(this).addClass('laytable-checkbox-selected');
                    } else {
                        $(this).removeClass('laytable-checkbox-selected');
                    }
                });
            } else {
                $("#ratetype").next().removeClass('disabled').find('li').off('click').bind('click',function(){
                    if(!$(this).hasClass('laytable-checkbox-selected')){
                        $("#ratetype").next().find('li').removeClass('laytable-checkbox-selected');
                        $(this).addClass('laytable-checkbox-selected');
                        $("#ratetype").val($(this).data('value')).trigger('change');
                    }
                });
            }
            
            // 息票品种选择贴现、零息时，初始利率自动计算，不可修改，其他要素全部不可填
            couponType_arr_3 = ['02','03'];
            if (couponType_arr_3.indexOf(couponType) >= 0) {
                $('#ratestr').val(initRatestr);
                $('#basis').attr('disabled',true);
                $('#ratestr').attr('disabled',true);
                $("#ratetype").next().addClass('disabled').find('li').off('click');
            } else {
                $('#basis').attr('disabled',false);
                $('#ratestr').attr('disabled',false);
                if ('04' != couponType) {
                    $("#ratetype").next().removeClass('disabled').find('li').off('click').bind('click',function(){
                        if(!$(this).hasClass('laytable-checkbox-selected')){
                            $("#ratetype").next().find('li').removeClass('laytable-checkbox-selected');
                            $(this).addClass('laytable-checkbox-selected');
                            $("#ratetype").val($(this).data('value')).trigger('change');
                        }
                    });
                }
            }
            
        });
        
        $('#isRight').change(function(){
         // 是否含权债显示为“否”时，行权计划区域不显示；
            var isRight_arr =['N'];
            var isRight = $('#isRight').val();
            if(isRight_arr.indexOf(isRight) >= 0){
                $("#div1").hide();
            }else {
                $("#div1").show();
            };
        });
        // 初始利率联动利率报价信息生效日为起息日的利率
        $('#ratestr').blur(function(){
            for (var i = 0; i < table1.getAllRows().length; i ++) {
                if (table1.getAllRows()[i].efdate == $('#vdate1').val()) {
                    table1.renderRow(i, {rate2: $('#ratestr').val()});
                }
            }
        });
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
    	if (pageModel == "2"){
    		// 是否含权债显示为“否”时，行权计划区域不显示；
    	    var isrightCode =['N'];
    	    if(isrightCode.indexOf(initisright)>=0){
    	        $("#div1").hide();
    	    }else {
    	        $("#div1").show();
    	    };
    	 
    	    // 息票品种为利随本清、附息时,债券利率报价区域显示
    	    var couponTypeCode =['01', '04'];
    	    if(couponTypeCode.indexOf(initcouponType)>=0){
    	        $("#div2").show();
    	    }else {
    	        $("#div2").hide();
    	    };
    	}
        if (pageModel == "1") {
            // 是否含权债显示为“否”时，行权计划区域不显示；
            var isrightCode =['N'];
            if(isrightCode.indexOf(initisright)>=0){
                $("#div1").hide();
            }else {
                $("#div1").show();
            };
            
           // 息票品种为利随本清、附息时,债券利率报价区域显示
    	    var couponTypeCode =['01', '04'];
    	    if(couponTypeCode.indexOf(initcouponType)>=0){
    	        $("#div2").show();
    	    }else {
    	        $("#div2").hide();
    	    };
            // 当息票品种选择“零息”、“贴现”、“利随本清”时，付息频率、推算方式、首次付息日不可填
            var couponType_arr = ['02','03','04'];
            var couponType = $('#couponType').val();
            if (couponType_arr.indexOf(couponType) >= 0) {
                $('#paycycle').attr('disabled',true);
                $('#fpayDate').attr('disabled',true);
                $("#calcrule").next().addClass('disabled').find('li').off('click');
            } else {
                $('#paycycle').attr('disabled',false);
                $('#fpayDate').attr('disabled',false);
                
                $("#calcrule").next().removeClass('disabled').find('li').off('click').bind('click',function(){
                    if(!$(this).hasClass('laytable-checkbox-selected')){
                        $("#calcrule").next().find('li').removeClass('laytable-checkbox-selected');
                        $(this).addClass('laytable-checkbox-selected');
                    }
                });
            }
            
            // 息票品种选择利随本清时，利率类型为固定利率，不可改
            var couponType = $("#couponType").val();
            if ('04' == couponType) {
                $("#ratetype").val("01");
                $("#ratetype").next().addClass('disabled').find('li').off('click');
                $("#ratetype").next().find('li').each(function(index,n){
                    if($(this).data('value') == '01'){
                        $(this).addClass('laytable-checkbox-selected');
                    } else {
                        $(this).removeClass('laytable-checkbox-selected');
                    }
                });
            } else {
                $("#ratetype").next().removeClass('disabled').find('li').off('click').bind('click',function(){
                    if(!$(this).hasClass('laytable-checkbox-selected')){
                        $("#ratetype").next().find('li').removeClass('laytable-checkbox-selected');
                        $(this).addClass('laytable-checkbox-selected');
                        $("#ratetype").val($(this).data('value')).trigger('change');
                    }
                });
            }
            // 息票品种选择贴现、零息时，初始利率自动计算，不可修改，其他要素全部不可填
            couponType_arr_3 = ['02','03'];
            if (couponType_arr_3.indexOf(couponType) >= 0) {
                $('#ratestr').val(initRatestr);
                $('#basis').attr('disabled',true);
                $('#ratestr').attr('disabled',true);
                $("#ratetype").next().addClass('disabled').find('li').off('click');
            } else {
                $('#basis').attr('disabled',false);
                $('#ratestr').attr('disabled',false);
                if ('04' != couponType) {
                    $("#ratetype").next().removeClass('disabled').find('li').off('click').bind('click',function(){
                        if(!$(this).hasClass('laytable-checkbox-selected')){
                            $("#ratetype").next().find('li').removeClass('laytable-checkbox-selected');
                            $(this).addClass('laytable-checkbox-selected');
                            $("#ratetype").val($(this).data('value')).trigger('change');
                        }
                    });
                }
            }
            linkBind(); // 联动绑定
        }
    });
    
}());