/**
 * 行情（左上）
 */
(function(){


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
//            document.getElementById('zccc_qmze').innerHTML = '0.00万元';
//            document.getElementById('zcky_qmze').innerHTML = '0.00万元';
            Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtrIbRepo001/findPositionInfo', param, function (result) {
                if (result) {
                    if (result.zhkyxj_t != null && result.zhkyxj_t != '') {
                        document.getElementById('zhkyxj_t').innerHTML = JoyinUtil.formatNumber(result.zhkyxj_t, 3, null, 2) + '元';
                        parent.EtrIbRepoinfoHq.zhkyxj_t = result.zhkyxj_t;
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
//                    if (result.zccc_qmze != null && result.zccc_qmze != '') {
//                        document.getElementById('zccc_qmze').innerHTML = JoyinUtil.formatNumber(result.zccc_qmze, 3, null, 2) + '万元';
//                    }
//                    if (result.zcky_qmze != null && result.zcky_qmze != '') {
//                        document.getElementById('zcky_qmze').innerHTML = JoyinUtil.formatNumber(result.zcky_qmze, 3, null, 2) + '万元';
//                    }
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
            Joyin.ajaxJson(basePath + 'invest/entrust/etribrepo/EtrIbRepo001/findhginfo', param, function (result) {
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
            // add by caiqun 20211220 调用接口平台外汇cmds行情 start
            //queryJJHQXX(termType);
            document.getElementById('tradeType').innerHTML = termType;
            var hqTypeArr = document.getElementsByName('hqType');
            var hqType = $("[name='hqType']").attr("title");
            sendRepo(websocket,termType,hqType);
            // add by caiqun 20211220 调用接口平台外汇cmds行情 end
        }
    }

    var websocket = cmds(cmdsWebSocketUrl,
        function (data) {
            if(data != null && data != "" && "没有数据" != data){
                var objs = eval('('+data+')');
                var noMDEntries = objs.noMDTypes.noRelatedSym.noMDEntries;
                for(var j = 0;j<noMDEntries.length;j++) {
                    if (noMDEntries[j].mDEntryType === 'e') { // 最新利率
                        document.getElementById('newRate').innerHTML = JoyinUtil.formatNumber(noMDEntries[j].mDEntryPx, 4, null, 2) + '%';
                    } else if (noMDEntries[j].mDEntryType === '4') { // 开盘利率
                        document.getElementById('kpRate').innerHTML = JoyinUtil.formatNumber(noMDEntries[j].mDEntryPx, 4, null, 2) + '%';
                    } else if (noMDEntries[j].mDEntryType === '7') { // 最高利率
                        document.getElementById('zgRate').innerHTML = JoyinUtil.formatNumber(noMDEntries[j].mDEntryPx, 4, null, 2) + '%';
                    } else if (noMDEntries[j].mDEntryType === '8') { // 最低利率
                        document.getElementById('zdRate').innerHTML = JoyinUtil.formatNumber(noMDEntries[j].mDEntryPx, 4, null, 2) + '%';
                    } else if (noMDEntries[j].mDEntryType === 'g') { // 加权平均利率
                        document.getElementById('pjRate').innerHTML = JoyinUtil.formatNumber(noMDEntries[j].mDEntryPx, 4, null, 2) + '%';
                    } else if (noMDEntries[j].mDEntryType === '6') { // 平均回购期限
                        if (noMDEntries[j].mDEntryPx != 0) {
                            document.getElementById('pjTerm').innerHTML = noMDEntries[j].mDEntryPx + '天';
                        } else {
                            document.getElementById('pjTerm').innerHTML = '--';
                        }
                    } else if (noMDEntries[j].mDEntryType === '3') { // 加权平均利率(利率债)
                        document.getElementById('pjlvRate').innerHTML = JoyinUtil.formatNumber(noMDEntries[j].mDEntryPx, 4, null, 2) + '%';
                    }
                }
            }else{
                document.getElementById('newRate').innerHTML ='';// 最新利率
                document.getElementById('kpRate').innerHTML ='';// 开盘利率
                document.getElementById('zgRate').innerHTML ='';// 最高利率
                document.getElementById('zdRate').innerHTML ='';// 最低利率
                document.getElementById('pjRate').innerHTML ='';// 加权平均利率
                document.getElementById('pjTerm').innerHTML = '--';// 平均回购期限
                document.getElementById('pjlvRate').innerHTML ='';// 加权平均利率(利率债)
            }
        },
        function (event) {
            console.log("建立连接:" + event);
        },
        function (event) {
            console.log("连接发生错误:" + event);
        }
    );

    $("[name='hqType']").click(function () {
        var hqType = $(this).attr("title");
        sendRepo(websocket,TREM_TYPE,hqType);
    });

    /**
     * 对外公开的接口
     */
    parent.EtrIbRepoinfoHq = {
        querySecinfo:querySecinfo,
        zhkyxj_t: zhkyxj_t
    }
    /**
     * 画面加载完成
     */
    $(function() {
    });

}());