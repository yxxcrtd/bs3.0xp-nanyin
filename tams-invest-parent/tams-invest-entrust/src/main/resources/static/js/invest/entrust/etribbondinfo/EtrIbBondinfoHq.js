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
            document.getElementById('secName').innerHTML = '';
            document.getElementById('secType').innerHTML = '';
            document.getElementById('issuer').innerHTML = '';
            document.getElementById('vdate').innerHTML = '';
            document.getElementById('mdate').innerHTML = '';
            document.getElementById('lastqx').innerHTML = '';
            document.getElementById('lastPaydate').innerHTML = '';
            document.getElementById('rateType').innerHTML = '';
            document.getElementById('rate').innerHTML = '';
            document.getElementById('xqFlg').innerHTML = '';
            document.getElementById('xqdate').innerHTML = '';
            document.getElementById('zxGrade').innerHTML = '';
            document.getElementById('ztGrade').innerHTML = '';
            document.getElementById('gzRate').innerHTML = '';
            document.getElementById('priceXq').innerHTML = '';
            document.getElementById('priceTj').innerHTML = '';
            Joyin.ajaxJson(basePath + 'invest/entrust/etribbondinfo/EtrIbBondinfo001/findSecinfo', param, function (result) {
                if (result) {
                    secId = result.secId;
                    if (result.secName != null && result.secName != '') {
                        document.getElementById('secName').innerHTML = result.secName;
                    }
                    if (result.secTypeName != null && result.secTypeName != '') {
                        document.getElementById('secType').innerHTML = result.secTypeName;
                    }
                    if (result.issuerName != null && result.issuerName != '') {
                        document.getElementById('issuer').innerHTML = result.issuerName;
                    }
                    if (result.vdate != null && result.vdate != '') {
                        document.getElementById('vdate').innerHTML = result.vdate;
                    }
                    if (result.mdate != null && result.mdate != '') {
                        document.getElementById('mdate').innerHTML = result.mdate;
                    }
                    if (result.lastqx != null && result.lastqx != '') {
                        if (result.lastqx != 0) {
                            document.getElementById('lastqx').innerHTML = result.lastqx + '年';
                        } else {
                            document.getElementById('lastqx').innerHTML = '--';
                        }
                    }
                    if (result.lastPaydate != null && result.lastPaydate != '') {
                        document.getElementById('lastPaydate').innerHTML = result.lastPaydate;
                    }
                    if (result.rateTypeName != null && result.rateTypeName != '') {
                        document.getElementById('rateType').innerHTML = result.rateTypeName;
                    }
                    if (result.rate != null && result.rate != '') {
                        document.getElementById('rate').innerHTML = JoyinUtil.formatNumber(result.rate, 4, null, 2) + '%';
                    }
                    if (result.xqFlg != null && result.xqFlg != '') {
                        document.getElementById('xqFlg').innerHTML = result.xqFlg;
                    }
                    if (result.xqdate != null && result.xqdate != '') {
                        document.getElementById('xqdate').innerHTML = result.xqdate;
                    }
                    if (result.zxGrade != null && result.zxGrade != '') {
                        document.getElementById('zxGrade').innerHTML = result.zxGrade;
                    }
                    if (result.ztGrade != null && result.ztGrade != '') {
                        document.getElementById('ztGrade').innerHTML = result.ztGrade;
                    }
                    if (result.gzRate != null && result.gzRate != '') {
                        document.getElementById('gzRate').innerHTML = JoyinUtil.formatNumber(result.gzRate, 4, null, 2) + '%';
                    }
                    if (result.priceXq != null && result.priceXq != '') {
                        document.getElementById('priceXq').innerHTML = JoyinUtil.formatNumber(result.priceXq, 4, null, 2) ;
                    }
                    if (result.priceTj != null && result.priceTj != '') {
                        document.getElementById('priceTj').innerHTML = JoyinUtil.formatNumber(result.priceTj, 4, null, 2) ;
                    }
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

        initDynCheckbox("EtrIbBondinfoHq", "MST_BOND_VALUATION_INFO_EtrIbBondinfoHq","EtrIbBondinfoHq_checkbox_background",
            function(data){
            var _this = $("#EtrIbBondinfoHq_view_contain");
            _this.empty();
            $.each(data,function(index, value){
                // alert(index + "-" + value.name + "-" + value.label);
                var item = $('<div style="float: left;width: 50%;height: 22px;line-height: 22px;"><label>'+ value.label +':</label><label id="'+ value.name +'"></label></div>');
                item.appendTo(_this);
            });
        });
        
    });

    /**
     * @param pageid EtrIbBondinfoHq
     * @param dynId MST_BOND_VALUATION_INFO_EtrIbBondinfoHq
     * @param backgroundId EtrIbBondinfoHq_checkbox_background
     * @param callback 执行数据页面加载 [{"name":"secName","label":"债券名称"},{...}...]
     */
    var initDynCheckbox = function (pageid, dynId, backgroundId, callback) {
        var _background = $("#"+backgroundId);
        _background.append('<div style="margin-top:10px;">');
        _background.append('<form id="'+ pageid +'_checkbox_form" style="margin-top:10px;">');
        _background.append('<div id="'+ pageid +'_checkbox_contain"></div>');
        _background.append('<div style="text-align:center"><button id="'+ pageid +'_checkbox_btn_realseach" style="margin-top:5px;margin-bottom:5px;">确认</button><button id="'+ pageid +'_checkbox_btn_seachClose" style="margin-top:5px;margin-bottom:5px;margin-left: 50px;">取消</button></div>');
        
        var isclicked=false; // 多选项有变化才会修改后台数据
        var data=[]; // 展示数据，用来展示页面真实数据，不是多选框
        
        if(true){
            var url = basePath + 'invest/entrust/EtrCommonController/selectByDynId';
            Joyin.ajaxJson(url, {"dynId":dynId}, function (datajson) {

                // 构造多选框
                var pageContain = $("#"+pageid+"_checkbox_contain");
                pageContain.empty(); // 显示多选框之前清空前一次的
                datajson.all=datajson.all.reverse(); // 倒序
                var m;
                $.each(datajson.all,function(index, value){
                    // alert(index + "-" + value.value + "-" + value.label);
                    if(value && value != ''){
                        if((index+1) % 2 == 0 && index > 0){
                            itemfun(pageContain, value, m);
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
                var arr = datajson.checked.split(",");
                $.each(arr,function(index, value){
                    if(value != ''){
                        $("#"+pageid+"_checkbox_contain input:checkbox[value='"+ value +"']").attr("checked",'checked');
                        // 初始化页面展示数据
                        var temp = {}
                        temp.name=value;
                        temp.label=$("#"+pageid+"_checkbox_contain input:checkbox[value='"+ value +"']").attr("title");
                        data[data.length]=temp;
                    }
                });

                // 绑定构造的多选项click事件
                $("#"+pageid+"_checkbox_contain input:checkbox").click(function (){
                    isclicked = true;
                    var _this = $(this);
                    if(_this.attr("checked") == "checked"){
                        _this.removeAttr("checked");
                    }else{
                        _this.attr("checked", "checked");
                    }
                });

                callback(data);
            });
        }

        var itemfun = function(_this, m, n){ // 构造两列表单
            if(!n){
                var div = $('<div style="margin-left: 40px; margin-top:6px;"><span style="display:inline-block; width: 138px; text-align: left; vertical-align: top;"><label><input type="checkbox" name="dynInfo" value="'+ m.value +'" title="'+ m.label +'">'+ m.label +'</label></span></div>');
                div.prependTo(_this);
            }else{
                var div = $('<div style="margin-left: 40px; margin-top:6px;"><span style="display:inline-block; width: 138px; text-align: left; vertical-align: top;"><label><input type="checkbox" name="dynInfo" value="'+ m.value +'" title="'+ m.label +'">'+ m.label +'</label></span><span style="display:inline-block; width: 120px; text-align: left; vertical-align: top;"><label><input type="checkbox" name="dynInfo" value="'+ n.value +'" title="'+ n.label +'">'+ n.label +'</label></span></div>');
                div.prependTo(_this);
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
                var checkedlist=[], data=[];
                var index = 0 ;
                var arr = $("#"+pageid+"_checkbox_contain input:checkbox");
                $.each(arr,function(index, value){
                    var isChecked = $(this).attr("checked");
                    if (isChecked && isChecked == 'checked'){
                        var value = $(this).val();
                        // alert(value + "-" + $(this).attr("checked"));
                        if(value && value != ''){
                            checkedlist[checkedlist.length]=value;
                            var temp = {};
                            temp.name=value;
                            temp.label=$(this).attr("title");
                            data[data.length]=temp;
                        }
                    }
                });
                if (checkedlist.length > 0){
                    var url = basePath + 'invest/entrust/EtrCommonController/updateByParam';
                    var params = {"dynId":dynId, "checked":checkedlist.join(",")};
                    Joyin.ajaxJson(url, params, function (datajson) {
                        $("#"+pageid+"_checkbox_background").hide();
                        callback(data);
                    });
                }
                isclicked=false;
            }
            return false;
        });
    }
}());