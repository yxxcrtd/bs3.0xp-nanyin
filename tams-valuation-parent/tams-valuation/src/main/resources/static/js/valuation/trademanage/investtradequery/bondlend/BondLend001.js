$(function () {
    //T01:买入
    var ps1=['T01'];
    //T07:到期
    var ps2=['T07'];
    if(initps=='T01'){//买入
        $("#div0").show();
        $("#div01").show();
        $("#div02").hide();
        $("#div1").hide();
    }else if(initps=='T07'){//到期
        $("#div0").show();
        $("#div01").hide();
        $("#div02").show();
        $("#div1").show();
    }


});