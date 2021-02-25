(function () {
    /**
     * 定义数据表格
     */
    var cols = [
        {field:'portfolioId',  title: '组合代码', width:350,sortable:false}
        ,{field:'indName',  title: '指标名称', width:350,sortable:false}
        ,{field:'supDate',  title: '日期', type: "date", width:100,sortable:false}
        ,{field:'warnCond',  title: '预警阈值', width:200,sortable:false,render: transferWarnFoul}
        ,{field:'foulCond',  title: '违规阈值', width:200,sortable:false,render: transferWarnFoul}
        ,{field:'supVal',  title: '当前实际值',width:150,sortable:false,render: transferSupVal}
        ,{field:'supStat',  title: '状态',sortable:false,
            render: function (data, type, row, meta) {
                if(row.supStat == "NORMAL"){
                    return "<span>正常</span>";
                }
                if(row.supStat == "VIOLATION"){
                    return "<span style='color:red'>违规</span>";
                }
                if(row.supStat == "WARNING"){
                    return "<span style='color:orange'>预警</span>";
                }
                return row.supStat;
            }
        },
        {field:'totalConclu', visible: false}
    ];
    var table = $("#baseRiskMathCommTable").joyintable({
        data:riskList == null ? [] : riskList,
        cols : cols,
        searchOnInit:true,
        checkbox : false,
        height : 200,
        page:false,
        done:function () {
            if(riskList && riskList.length>0){
                $("#riskTrialSpan").html("测试不通过！");
            }
        }
    });
    function transferWarnFoul(data, type, row, meta){
        var lnkNo="";
        var wg="";var detail = "";
        if(data==''){
            return wg;
        }
        var detailJson = JSON.parse(data);
        if(detailJson!=null){
            /* 限制型 */
            detail = html2Escape(data);
            if(row.indType.indexOf("LIMIT_")!=-1){
                if(detailJson.threshold_2.lnk==lnkNo){
                    if(detailJson.threshold_1.value!=null&&detailJson.threshold_1.value!=''){
                        wg = detailJson.threshold_1.op+detailJson.threshold_1.value+valueConvertUnit(detailJson.threshold_1.unit);
                    }
                }else{
                    wg = detailJson.threshold_1.op+detailJson.threshold_1.value+valueConvertUnit(detailJson.threshold_1.unit)+detailJson.threshold_2.lnk+detailJson.threshold_2.op+detailJson.threshold_2.value+valueConvertUnit(detailJson.threshold_2.unit);
                }

            }else{
                /* 禁止型 */
                //------update by jjpei 20190812----解析禁投项------
                wg = detailJson.threshold_1.value_cn;
                /*var wgArr=wg.split(",");
                var wgStr='';
                for(var m=0;m<wgArr.length;m++){
                    if(wgArr[m]!=null&&wgArr[m]!='')
                        wgStr+=StringToChinese(wgArr[m],row.indType)+",";
                }*/
                wg=wg.substring(0,wg.length-1);
                //------update by jjpei 20190812----解析禁投项------
            }
        }
        return wg;
    }
    /* 普通字符转换成转意符 */
    var html2Escape = function(sHtml){
        return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
    };
    function transferSupVal(data, type, row, meta){
        var wg=data;
        if(wg==''){
            return wg;
        }
        if(row.indType.indexOf("FORBID_")!=-1){
            /* 禁止型 */
            //------update by jjpei 20190812----解析禁投项------
            var secCell=false;
            if(wg.indexOf("||")!=-1){
                secCell=true;
            }
            var wgArr=wg.split(",");
            var wgStr='';
            for(var m=0;m<wgArr.length;m++){
                if(wgArr[m]!=null&&wgArr[m]!='') {
                    if(secCell) {
                        var wgArrSub = wgArr[m].split("||");
                        if(wgArrSub.length>1)
                            wgStr += wgArrSub[1]+",";
                        else
                            wgStr += wgArrSub[0]+",";
                    }else{
                        wgStr += wgArr[m]+",";
                    }
                }
            }
            wg=wgStr.substring(0,wgStr.length-1);
            //------update by jjpei 20190812----解析禁投项------
        }
        else{
            wg = String(new Number(wg)).replace(/^(.*\..{6}).*$/,"$1");
            wg = Number(wg);
            var detailJson = JSON.parse(row.foulCond);
            var curunit=valueConvertUnit(detailJson.threshold_1.unit);
            if(detailJson.threshold_1.unit=='PERCENT'){
                wg=toDecimal(wg*100)+curunit;
            }
            else if(detailJson.threshold_1.unit=='WRMB'){
                wg=String(new Number(wg/10000)).replace(/^(.*\..{6}).*$/,"$1")+curunit;
            }
            else{
                wg=String(new Number(wg)).replace(/^(.*\..{6}).*$/,"$1")+curunit;
            }
        }
        return wg;
    }
    /**
     * 单位转换
     */
    function valueConvertUnit(value){
        if(value==''){
            return value;
        }
        var retStr;
        var supUnitArr = JSON.parse(SUP_UNIT);
        $.each(supUnitArr,function(i,v){
            if(v.id==value){
                retStr=v.text;
                return false;
            }
        })
        return retStr;
    };
    /**
     * 画面加载完成
     */
    $(function () {
        table.reload({data:riskList});
    });
}());