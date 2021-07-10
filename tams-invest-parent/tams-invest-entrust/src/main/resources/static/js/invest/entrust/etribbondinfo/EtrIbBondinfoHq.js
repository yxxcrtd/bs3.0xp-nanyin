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
        if (portfolioId != null) {
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
                        document.getElementById('zhkyxj_t').innerHTML = JoyinUtil.formatNumber(result.zhkyxj_t, 2, null, 2) + '元';
                        parent.EtrIbBondinfoHq.zhkyxj_t = result.zhkyxj_t;
                    }
                    if (result.zhkyxj_t1 != null && result.zhkyxj_t1 != '') {
                        document.getElementById('zhkyxj_t1').innerHTML = JoyinUtil.formatNumber(result.zhkyxj_t1, 2, null, 2) + '元';
                    }
                    if (chlFinprodId != null && chlFinprodId != '') {
                        document.getElementById("tghkyxj_t_dev").style.display="none";
                        document.getElementById("tghkyxj_t1_dev").style.display="none";
                        document.getElementById("tdkyxj_t_dev").style.display="";
                        document.getElementById("tdkyxj_t1_dev").style.display="";
                        if (result.tdkyxj_t != null && result.tdkyxj_t != '') {
                            document.getElementById('tdkyxj_t').innerHTML = JoyinUtil.formatNumber(result.tdkyxj_t, 2, null, 2) + '元';
                        }
                        if (result.tdkyxj_t1 != null && result.tdkyxj_t1 != '') {
                            document.getElementById('tdkyxj_t1').innerHTML = JoyinUtil.formatNumber(result.tdkyxj_t1, 2, null, 2) + '元';
                        }
                    } else {
                        document.getElementById("tdkyxj_t_dev").style.display="none";
                        document.getElementById("tdkyxj_t1_dev").style.display="none";
                        document.getElementById("tghkyxj_t_dev").style.display="";
                        document.getElementById("tghkyxj_t1_dev").style.display="";
                        if (result.tghkyxj_t != null && result.tghkyxj_t != '') {
                            document.getElementById('tghkyxj_t').innerHTML = JoyinUtil.formatNumber(result.tghkyxj_t, 2, null, 2) + '元';
                        }
                        if (result.tghkyxj_t1 != null && result.tghkyxj_t1 != '') {
                            document.getElementById('tghkyxj_t1').innerHTML = JoyinUtil.formatNumber(result.tghkyxj_t1, 2, null, 2) + '元';
                        }
                    }
                    if (result.zccc_qmze != null && result.zccc_qmze != '') {
                        document.getElementById('zccc_qmze').innerHTML = JoyinUtil.formatNumber(result.zccc_qmze, 2, null, 2) + '万元';
                    }
                    if (result.zcky_qmze != null && result.zcky_qmze != '') {
                        document.getElementById('zcky_qmze').innerHTML = JoyinUtil.formatNumber(result.zcky_qmze, 2, null, 2) + '万元';
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
                    secId = result.secId;
                    $("#secName").html('');
                    $("#secName").html(result.secName);
                    result.secType=result.secTypeName;
                    result.rateType=result.rateTypeName;
                    result.issuer=result.issuerName;
                    dynview.resultToView(result);
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
    var dynview; 
    $(function() {
        dynview = new DynamicView("EtrIbBondinfoHq", "EtrIbBondinfoHq_dyn");
        dynview.init();
    });
    
}());