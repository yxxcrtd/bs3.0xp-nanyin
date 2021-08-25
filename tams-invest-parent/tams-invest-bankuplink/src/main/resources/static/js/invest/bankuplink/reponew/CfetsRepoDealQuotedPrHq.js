/**
 * 行情（左上）
 */
(function(){

    /**根据市场代码，查询行情信息，展示出来*/
    var queryJJHQXX = function(termType) {
    	;
    	if (!termType) {
    		return;
    	}
        $.ajax({
            /**临时用这个地址，后面调整！！！！**/
            url: "findQuotations?termType="+termType+"&MarketType="+$(":radio:checked").val(),
            async: false,
            type: "GET",
            dataType:"json",
            success: function(info) {
                var result = info.data;
            }
        });
    }

    /**根据市场代码，查询行情信息，展示出来*/
    var TREM_TYPE = null;
    var querySecinfo = function(finprodId,portfolioId,chlFinprodId, termType) {
    	TREM_TYPE = termType;
    	
        var param = {
            finprodId : finprodId,
            portfolioId : portfolioId,
            chlFinprodId : chlFinprodId
        };
        if (portfolioId != null && portfolioId != '') {
            document.getElementById('zhkyxj_t').innerHTML = '0.00元';
            document.getElementById('zhkyxj_t1').innerHTML = '0.00元';
            document.getElementById('tghkyxj_t').innerHTML = '0.00元';
            document.getElementById('tghkyxj_t1').innerHTML = '0.00元';
            document.getElementById('tdkyxj_t').innerHTML = '0.00元';
            document.getElementById('tdkyxj_t1').innerHTML = '0.00元';
            Joyin.ajaxJson(basePath + 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr001/findPositionInfo', param, function (result) {
                if (result) {
                    if (result.zhkyxj_t != null && result.zhkyxj_t != '') {
                        document.getElementById('zhkyxj_t').innerHTML = JoyinUtil.formatNumber(result.zhkyxj_t, 3, null, 2) + '元';
                        parent.CfetsRepoDealQuotedPrHq.zhkyxj_t = result.zhkyxj_t;
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
                }
            }, false);
        }
        if (finprodId != null && finprodId != '') {
            document.getElementById('hqType').innerHTML = '';
            document.getElementById('tradeType').innerHTML = '';
            document.getElementById('newRate').innerHTML = '';
            document.getElementById('kpRate').innerHTML = '';
            document.getElementById('zgRate').innerHTML = '';
            document.getElementById('zdRate').innerHTML = '';
            document.getElementById('pjRate').innerHTML = '';
            document.getElementById('pjTerm').innerHTML = '';
            document.getElementById('pjlvRate').innerHTML = '';
            Joyin.ajaxJson(basePath + 'invest/bankuplink/reponew/CfetsRepoDealQuotedPr001/findhginfo', param, function (result) {
                if (result) {
                    if (result.hqType != null && result.hqType != '') {
                        document.getElementById('hqType').innerHTML = result.hqType;
                    }
                    if (result.tradeType != null && result.tradeType != '') {
                        document.getElementById('tradeType').innerHTML = result.tradeType;
                    }
                    if (result.pjTerm != null && result.pjTerm != '') {
                        if (result.pjTerm != 0) {
                            document.getElementById('pjTerm').innerHTML = result.pjTerm + '天';
                        } else {
                            document.getElementById('pjTerm').innerHTML = '--';
                        }
                    }
                    if (result.newRate != null && result.newRate != '') {
                        document.getElementById('newRate').innerHTML = JoyinUtil.formatNumber(result.newRate, 4, null, 2) + '%';
                    }
                    if (result.kpRate != null && result.kpRate != '') {
                        document.getElementById('kpRate').innerHTML = JoyinUtil.formatNumber(result.kpRate, 4, null, 2) + '%';
                    }
                    if (result.zgRate != null && result.zgRate != '') {
                        document.getElementById('zgRate').innerHTML = JoyinUtil.formatNumber(result.zgRate, 4, null, 2) + '%';
                    }
                    if (result.zdRate != null && result.zdRate != '') {
                        document.getElementById('zdRate').innerHTML = JoyinUtil.formatNumber(result.zdRate, 4, null, 2) + '%';
                    }
                    if (result.pjRate != null && result.pjRate != '') {
                        document.getElementById('pjRate').innerHTML = JoyinUtil.formatNumber(result.pjRate, 4, null, 2) + '%';
                    }
                    if (result.pjlvRate != null && result.pjlvRate != '') {
                        document.getElementById('pjlvRate').innerHTML = JoyinUtil.formatNumber(result.pjlvRate, 4, null, 2) + '%';
                    }
                }
            }, false);
        }
        
        if (termType) {
        	queryJJHQXX(termType);
        }
    }

    /**
     * 对外公开的接口
     */
    parent.CfetsRepoDealQuotedPrHq = {
        querySecinfo:querySecinfo,
        zhkyxj_t: zhkyxj_t
    };
    CfetsRepoDealQuotedPrHq = {
        querySecinfo:querySecinfo,
        zhkyxj_t: zhkyxj_t
    }
    /**
     * 画面加载完成
     */
    var dynview;
    $(function() {
        dynview = new DynamicView("CfetsRepoDealQuotedPrHq", "CfetsRepoDealQuotedPrHq_dyn");
        dynview.init();
    });

}());