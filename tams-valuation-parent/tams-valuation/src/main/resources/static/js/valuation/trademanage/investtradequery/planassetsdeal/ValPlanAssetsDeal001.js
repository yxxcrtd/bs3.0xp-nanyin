$(function () {
    //T01:买入,T02:卖出,T03:行权,T20:转入,T21:转出
    var ps1=['T01','T02','T03','T20','T21'];
    //T04:申购,T05:申购确认,T19:赎回确认,T21:赎回到账  T25应收入款项
    var ps2=['T04','T05','T19','T24','T25'];
    //T11:现金分红,T12:红利再投,T13:除权登记
    var ps3=['T11','T12','T13'];
    if(initps=='T04'){
        $("#div0").show();
        $("#div1").show();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").hide();
        $("#div5").hide();
        $("#div6").hide();
        $("#div7").hide();
        $("#div77").hide();
        $("#div8").hide();
    }else if(initps=='T05'){
        $("#div0").show();
        $("#div1").hide();
        $("#div2").show();
        $("#div3").hide();
        $("#div4").hide();
        $("#div5").hide();
        $("#div6").hide();
        $("#div7").hide();
        $("#div77").hide();
        $("#div8").hide();
    }else if(initps=='T19'){
        $("#div0").show();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").show();
        $("#div4").hide();
        $("#div5").hide();
        $("#div6").hide();
        $("#div7").hide();
        $("#div77").hide();
        $("#div8").hide();
    }else if(initps=='T24'||initps=='T25'){
        $("#div0").show();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").show();
        $("#div5").hide();
        $("#div6").hide();
        $("#div7").hide();
        $("#div77").hide();
        $("#div8").hide();
    }else if(initps=='T11'){
        $("#div0").show();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").hide();
        $("#div5").show();
        $("#div6").hide();
        $("#div7").hide();
        $("#div77").hide();
        $("#div8").hide();
    }else if(initps=='T12'){
        $("#div0").show();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").hide();
        $("#div5").hide();
        $("#div6").show();
        $("#div7").hide();
        $("#div77").hide();
        $("#div8").hide();
    }else if(initps=='T13'){
        $("#div0").show();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").hide();
        $("#div5").hide();
        $("#div6").show();
        $("#div7").hide();
        $("#div77").hide();
        $("#div8").hide();
    }else if(initps=='T08'||initps=='T23'||initps=='T26'){//还本
        $("#div0").hide();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").hide();
        $("#div5").hide();
        $("#div6").hide();
        $("#div7").show();
        $("#div77").hide();
        $("#div8").hide();
    }else if(initps=='T09'||initps=='T22'||initps=='T25'){//收息
        $("#div0").hide();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").hide();
        $("#div5").hide();
        $("#div6").hide();
        $("#div7").hide();
        $("#div77").show();
        $("#div8").hide();
    }else if(ps1.indexOf(initps)>=0){
        $("#div0").hide();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").hide();
        $("#div5").hide();
        $("#div6").hide();
        $("#div7").hide();
        $("#div77").hide();
        $("#div8").show();
    }

});