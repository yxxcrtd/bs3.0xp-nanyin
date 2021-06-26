/**
 * 行情（左上）
 */
(function(){
    
    /**根据市场代码，查询行情信息，展示出来*/
    var queryJJHQXX = function(seccode) {
        $.ajax({
            /**临时用这个地址，后面调整！！！！**/
            url: "findQuotations?seccode="+seccode,
            async: true,
            type: "POST",
            dataType:"json",
            success: function(info) {
                var result = info.data;
                if (result != null && result != '') {
                    if (result.latestCleanPrice != null && result.latestCleanPrice != '') {
                        document.getElementById('newPrice').innerHTML = JoyinUtil.formatNumber(result.latestCleanPrice, 4, null, 2);
                    } else {
                        document.getElementById('newPrice').innerHTML = '';
                    }
                    if (result.latestYield != null && result.latestYield != '') {
                        document.getElementById('newRate').innerHTML = JoyinUtil.formatNumber(result.latestYield, 4, null, 2) + '%';
                    } else {
                        document.getElementById('newRate').innerHTML = '';
                    }
                }
                else {
                    document.getElementById('newPrice').innerHTML = '';
                    document.getElementById('newRate').innerHTML = '';
                }
            }
        });
    }

    /**根据市场代码，查询行情信息，展示出来*/
    var querySecinfo = function(finprodId,portfolioId,chlFinprodId,ccy,sysDateT1,calId) {
        var param = {
            finprodId : finprodId,
            portfolioId : portfolioId,
            chlFinprodId : chlFinprodId,
            ccy:ccy, 
    		sysDateT1:sysDateT1,
    		calId:calId
        };
        if (portfolioId != null && portfolioId != '') {
            document.getElementById('zhkyxj_t').innerHTML = '0.00元';
            document.getElementById('zhkyxj_t1').innerHTML = '0.00元';
            document.getElementById('tghkyxj_t').innerHTML = '0.00元';
            document.getElementById('tghkyxj_t1').innerHTML = '0.00元';
            document.getElementById('tdkyxj_t').innerHTML = '0.00元';
            document.getElementById('tdkyxj_t1').innerHTML = '0.00元';
            document.getElementById('zccc_qmze').innerHTML = '0.00万元';
            document.getElementById('zcky_qmze').innerHTML = '0.00万元';
            Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtrIbBondinfo001/findPositionInfo', param, function (result) {
                if (result) {
                    if (result.zhkyxj_t != null && result.zhkyxj_t != '') {
                        document.getElementById('zhkyxj_t').innerHTML = JoyinUtil.formatNumber(result.zhkyxj_t, 3, null, 2) + '元';
                        parent.EtrIbBondinfoHq.zhkyxj_t = result.zhkyxj_t;
                    }
                    if (result.zhkyxj_t1 != null && result.zhkyxj_t1 != '') {
                        document.getElementById('zhkyxj_t1').innerHTML = JoyinUtil.formatNumber(result.zhkyxj_t1, 3, null, 2) + '元';
                    }
                    if (chlFinprodId != null && chlFinprodId != '') {
                        document.getElementById("tghkyxj_t_dev").style.display="none";
                        document.getElementById("tghkyxj_t1_dev").style.display="none";
                        document.getElementById("tdkyxj_t_dev").style.display="";
                        document.getElementById("tdkyxj_t1_dev").style.display="";
                        if (result.tdkyxj_t != null && result.tdkyxj_t != '') {
                            document.getElementById('tdkyxj_t').innerHTML = JoyinUtil.formatNumber(result.tdkyxj_t, 3, null, 2) + '元';
                        }
                        if (result.tdkyxj_t1 != null && result.tdkyxj_t1 != '') {
                            document.getElementById('tdkyxj_t1').innerHTML = JoyinUtil.formatNumber(result.tdkyxj_t1, 3, null, 2) + '元';
                        }
                    } else {
                        document.getElementById("tdkyxj_t_dev").style.display="none";
                        document.getElementById("tdkyxj_t1_dev").style.display="none";
                        document.getElementById("tghkyxj_t_dev").style.display="";
                        document.getElementById("tghkyxj_t1_dev").style.display="";
                        if (result.tghkyxj_t != null && result.tghkyxj_t != '') {
                            document.getElementById('tghkyxj_t').innerHTML = JoyinUtil.formatNumber(result.tghkyxj_t, 3, null, 2) + '元';
                        }
                        if (result.tghkyxj_t1 != null && result.tghkyxj_t1 != '') {
                            document.getElementById('tghkyxj_t1').innerHTML = JoyinUtil.formatNumber(result.tghkyxj_t1, 3, null, 2) + '元';
                        }
                    }
                    if (result.zccc_qmze != null && result.zccc_qmze != '') {
                        document.getElementById('zccc_qmze').innerHTML = JoyinUtil.formatNumber(result.zccc_qmze, 3, null, 2) + '万元';
                    }
                    if (result.zcky_qmze != null && result.zcky_qmze != '') {
                        document.getElementById('zcky_qmze').innerHTML = JoyinUtil.formatNumber(result.zcky_qmze, 3, null, 2) + '万元';
                    }
                }
            }, false);
        }
        if (finprodId != null && finprodId != '') {
            var secId = '';
            
            // document.getElementById('secName').innerHTML = '';
            // document.getElementById('secType').innerHTML = '';
            // document.getElementById('issuer').innerHTML = '';
            // document.getElementById('vdate').innerHTML = '';
            // document.getElementById('mdate').innerHTML = '';
            // document.getElementById('lastqx').innerHTML = '';
            // document.getElementById('lastPaydate').innerHTML = '';
            // document.getElementById('rateType').innerHTML = '';
            // document.getElementById('rate').innerHTML = '';
            // document.getElementById('xqFlg').innerHTML = '';
            // document.getElementById('xqdate').innerHTML = '';
            // document.getElementById('zxGrade').innerHTML = '';
            // document.getElementById('ztGrade').innerHTML = '';
            // document.getElementById('gzRate').innerHTML = '';
            // document.getElementById('priceXq').innerHTML = '';
            // document.getElementById('priceTj').innerHTML = '';
            Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtrIbBondinfo001/findSecinfo', param, function (result) {
                if (result) {
                    DynCheckboxView.messageInfo = result; // 全局资产信息保存
                    secId = result.secId;
                    resultToView(DynCheckboxView.checkedList, DynCheckboxView.messageInfo);
                    //
                    // if (result.secName != null && result.secName != '') {
                    //     document.getElementById('secName').innerHTML = result.secName;
                    // }
                    // if (result.secTypeName != null && result.secTypeName != '') {
                    //     document.getElementById('secType').innerHTML = result.secTypeName;
                    // }
                    // if (result.issuerName != null && result.issuerName != '') {
                    //     document.getElementById('issuer').innerHTML = result.issuerName;
                    // }
                    // if (result.vdate != null && result.vdate != '') {
                    //     document.getElementById('vdate').innerHTML = result.vdate;
                    // }
                    // if (result.mdate != null && result.mdate != '') {
                    //     document.getElementById('mdate').innerHTML = result.mdate;
                    // }
                    // if (result.lastqx != null && result.lastqx != '') {
                    //     if (result.lastqx != 0) {
                    //         document.getElementById('lastqx').innerHTML = result.lastqx + '年';
                    //     } else {
                    //         document.getElementById('lastqx').innerHTML = '--';
                    //     }
                    // }
                    // if (result.lastPaydate != null && result.lastPaydate != '') {
                    //     document.getElementById('lastPaydate').innerHTML = result.lastPaydate;
                    // }
                    // if (result.rateTypeName != null && result.rateTypeName != '') {
                    //     document.getElementById('rateType').innerHTML = result.rateTypeName;
                    // }
                    // if (result.rate != null && result.rate != '') {
                    //     document.getElementById('rate').innerHTML = JoyinUtil.formatNumber(result.rate, 4, null, 2) + '%';
                    // }
                    // if (result.xqFlg != null && result.xqFlg != '') {
                    //     document.getElementById('xqFlg').innerHTML = result.xqFlg;
                    // }
                    // if (result.xqdate != null && result.xqdate != '') {
                    //     document.getElementById('xqdate').innerHTML = result.xqdate;
                    // }
                    // if (result.zxGrade != null && result.zxGrade != '') {
                    //     document.getElementById('zxGrade').innerHTML = result.zxGrade;
                    // }
                    // if (result.ztGrade != null && result.ztGrade != '') {
                    //     document.getElementById('ztGrade').innerHTML = result.ztGrade;
                    // }
                    // if (result.gzRate != null && result.gzRate != '') {
                    //     document.getElementById('gzRate').innerHTML = JoyinUtil.formatNumber(result.gzRate, 4, null, 2) + '%';
                    // }
                    // if (result.priceXq != null && result.priceXq != '') {
                    //     document.getElementById('priceXq').innerHTML = JoyinUtil.formatNumber(result.priceXq, 4, null, 2) ;
                    // }
                    // if (result.priceTj != null && result.priceTj != '') {
                    //     document.getElementById('priceTj').innerHTML = JoyinUtil.formatNumber(result.priceTj, 4, null, 2) ;
                    // }
                }
            }, false);

            queryJJHQXX(secId);
        }
    }

    /**
     * 对外公开的接口
     */
    parent.EtrIbBondinfoHq = {
        querySecinfo:querySecinfo,
        zhkyxj_t: zhkyxj_t
    }
    
    /**
     * 画面加载完成
     */
    $(function() {

        DynCheckboxView.init("EtrIbBondinfoHq", "EtrIbBondinfoHq_dyn",function(checkedList, messageInfo){
            resultToView(checkedList, messageInfo); // 展示资产信息
        });
        
    });

    /*
     * 展示checkbox界面并根据选中项进行回调展示数据
     * dynId表示数据库表DYN_CONFIG_INFO中DYN_ID配置的值
     * 设置显示列按钮id以pageId开头    例：<a id="EtrIbBondinfoHq_checkbox_start">
     * 多选框展示页面id以pageId开头   例：<div id="EtrIbBondinfoHq_checkbox_background">
     * 行情展示页面id以pageId开头    例：<div id="EtrIbBondinfoHq_view_contain">
     */
    var DynCheckboxView = {
        checkedList: [],  // 选中项
        messageInfo: {},  // 行情信息
        init: function (pageid, dynId, callback) {
            let _background = $("#"+ pageid +"_checkbox_background");
            _background.append('<div style="margin-top:10px;"><form id="'+ pageid +'_checkbox_form" style="margin-top:10px;"><div id="'+ pageid +'_checkbox_contain"></div><div style="text-align:center"><button id="'+ pageid +'_checkbox_btn_realseach" style="margin-top:5px;margin-bottom:5px;">确认</button><button id="'+ pageid +'_checkbox_btn_seachClose" style="margin-top:5px;margin-bottom:5px;margin-left: 50px;">取消</button></div></form></div>');

            let isclicked=false; // 多选项有变化才会修改后台数据

            if(true){
                let url = basePath + 'invest/entrust/EtrCommonController/selectByDynId';
                Joyin.ajaxJson(url, {"dynId":dynId}, function (datajson) {
                    // 构造多选框
                    let pageContain = $("#"+pageid+"_checkbox_contain");
                    pageContain.empty(); // 显示多选框之前清空前一次的
                    let m; // 存储奇数序号
                    $.each(datajson.all,function(index, value){
                        // alert(index + "-" + value.value + "-" + value.label);
                        if(value && value != ''){
                            if((index+1) % 2 == 0 && index > 0){
                                itemfun(pageContain, m, value);
                                m = null;
                            }else{
                                m = value;
                            }
                        }
                    });
                    if(m != null){
                        itemfun(pageContain, m);
                    }

                    // 对构造的多选项赋值
                    let chs = datajson.checked;
                    if(chs){
                        let arr = chs.split(",");
                        DynCheckboxView.checkedList = [];
                        $.each(arr,function(index, value){
                            if(value != ''){
                                $("#"+pageid+"_checkbox_contain input:checkbox[value='"+ value +"']").attr("checked",'checked');
                                // 初始化页面展示数据
                                var temp = {}
                                temp.name=value;
                                temp.label=$("#"+pageid+"_checkbox_contain input:checkbox[value='"+ value +"']").attr("title");
                                DynCheckboxView.checkedList[DynCheckboxView.checkedList.length]=temp;
                            }
                        });
                    }

                    // 绑定构造的多选项click事件
                    $("#"+pageid+"_checkbox_contain input:checkbox").click(function (){
                        isclicked = true;
                        let thisCheckbox = $(this);
                        if(thisCheckbox.attr("checked") == "checked"){
                            thisCheckbox.removeAttr("checked");
                        }else{
                            thisCheckbox.attr("checked", "checked");
                        }
                    });

                    DynCheckboxView.defaulView(pageid + "_view_contain", DynCheckboxView.checkedList); // 展示空行情
                    callback(DynCheckboxView.checkedList, DynCheckboxView.messageInfo);
                });
            }

            var itemfun = function(_this, m, n){ // 构造两列表单
                if(!n){
                    let div = $('<div style="margin-left: 40px; margin-top:6px;"><span style="display:inline-block; width: 138px; text-align: left; vertical-align: top;"><label><input type="checkbox" name="dynInfo" value="'+ m.value +'" title="'+ m.label +'">'+ m.label +'</label></span></div>');
                    // div.prependTo(_this);
                    _this.append(div);
                }else{
                    let div = $('<div style="margin-left: 40px; margin-top:6px;"><span style="display:inline-block; width: 138px; text-align: left; vertical-align: top;"><label><input type="checkbox" name="dynInfo" value="'+ m.value +'" title="'+ m.label +'">'+ m.label +'</label></span><span style="display:inline-block; width: 120px; text-align: left; vertical-align: top;"><label><input type="checkbox" name="dynInfo" value="'+ n.value +'" title="'+ n.label +'">'+ n.label +'</label></span></div>');
                    // div.prependTo(_this);
                    _this.append(div);
                }
            }

            // 设置显示列
            $("#"+pageid+"_checkbox_start").click(function() {
                if($("#"+pageid+"_checkbox_background").css("display")=="none"){
                    $("#"+pageid+"_checkbox_background").show();
                }
                else{
                    $("#"+pageid+"_checkbox_background").hide();
                }
            });

            // 取消
            $("#"+pageid+"_checkbox_btn_seachClose").click(function() {
                $("#"+pageid+"_checkbox_background").hide();
                return false;
            });

            // 确认
            $("#"+pageid+"_checkbox_btn_realseach").click(function() {
                if(isclicked){
                    let checkedlist=[];
                    let index = 0 ;
                    let arr = $("#"+pageid+"_checkbox_contain input:checkbox");
                    DynCheckboxView.checkedList = [];
                    $.each(arr,function(index, value){
                        let isChecked = $(this).attr("checked");
                        if (isChecked && isChecked == 'checked'){
                            let value = $(this).val();
                            // alert(value + "-" + $(this).attr("checked"));
                            if(value && value != ''){
                                checkedlist[checkedlist.length]=value;
                                let temp = {};
                                temp.name=value;
                                temp.label=$(this).attr("title");
                                DynCheckboxView.checkedList[DynCheckboxView.checkedList.length]=temp;
                            }
                        }
                    });
                    if (checkedlist.length > 0){
                        let url = basePath + 'invest/entrust/EtrCommonController/updateByParam';
                        let params = {"dynId":dynId, "checked":checkedlist.join(",")};
                        Joyin.ajaxJson(url, params, function (datajson) {
                            $("#"+pageid+"_checkbox_background").hide();
                            DynCheckboxView.defaulView(pageid + "_view_contain", DynCheckboxView.checkedList); // 展示空行情
                            callback(DynCheckboxView.checkedList, DynCheckboxView.messageInfo);
                        });
                    }
                    isclicked=false;
                }
                return false;
            });
        },
        /**
         * 空值展示行情
         * @param id 需要展示的#标识
         * @param checkedList 选中项
         */
        defaulView : function (id, checkedList) {
            var _this = $("#" + id);
            _this.empty();
            $.each(checkedList, function(index, value){
                // alert(index + "-" + value.name + "-" + value.label);
                var item = $('<div style="float: left;width: 50%;height: 22px;line-height: 22px;"><label>'+ value.label +':</label><label id="EtrIbBondinfoHq_'+ value.name +'"></label></div>');
                item.appendTo(_this);
            });
        }
    } 
    /*DynCheckboxView--end*/

    /**
     * 根据持仓单击联动到展示资产信息
     * @param result 资产数据
     * @param data checkbox选中数据
     */
    var resultToView = function(data, result){
        if(result && data){
            $("#secName").html('');
            $("#secName").html(result.secName);
            $.each(data,function(index, value){
                let name = value.name;
                let _labelName = $("#EtrIbBondinfoHq_" + name);
                if(_labelName){
                    _labelName.html('');
                    let resultValue = result[name];
                    // alert(index + "-" + value.name + "-" + value.label + "===" + str);
                    if(resultValue && resultValue != ''){
                        if(name == "lastqx"){
                            _labelName.html(resultValue + "年");
                        }else if(name == "rate" || name == "gzRate" || name == "priceXq" || name == "priceTj"){
                            _labelName.html(JoyinUtil.formatNumber(result.rate, 4, null, 2) + '%');
                        }else{
                            _labelName.html(resultValue);
                        }
                    }
                }
            });
        }
    }
}());