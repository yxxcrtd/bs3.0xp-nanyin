$(function () {
	var busiType1 = ['02','20'];// 过滤业务类型 - 债券交易,交易所债券交易
	var busiType2 = ['04', '05'];// 债券质押式回购交易 和 债券买断式回购交易
	var busiType3 = ['01'];
	var busiType4 = ['06','07'];//基金类交易
	var busiType5 = ['09','03'];//计划类资产、、存借款
	var busiType6 = ['15'];//活期存款收息交易
	var busiType7 = ['19'];//交易账户收划款交易
	var busiType9 = ['18'];//现金类交易
	var busiType10 = ['14'];//债券借贷交易

	if(busiType1.indexOf(s_busiType) >= 0){// 查看 债券交易,交易所债券交易
		$("#div0").show();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").hide();
	}else if(busiType2.indexOf(s_busiType) >= 0){// 债券质押式回购交易 和 债券买断式回购交易  
		$("#div0").hide();
        $("#div1").show();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").hide();
	}else if(busiType4.indexOf(s_busiType) >= 0){// 基金类交易  
        $("#div0").hide();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").show();
        $("#div4").hide();
    }else if(busiType6.indexOf(s_busiType) >= 0){// 活期存款收息交易
        $("#div0").hide();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").show();
        $("#div4").hide();
    }else if(busiType7.indexOf(s_busiType) >= 0){// 交易账户收划款交易
        $("#div0").hide();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").show();
        $("#div4").hide();
    }if(busiType3.indexOf(s_busiType) >= 0){// 拆借交易  
		$("#div0").hide();
        $("#div1").hide();
        $("#div2").show();
        $("#div3").hide();
        $("#div4").hide();
	}if(busiType5.indexOf(s_busiType) >= 0){//计划类资产、、存借款
        $("#div0").hide();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").show();
    }if(busiType9.indexOf(s_busiType) >= 0){//现金类交易
        $("#div0").hide();
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").show();
    }if(busiType10.indexOf(s_busiType) >= 0){// 查看债券借贷交易
        $("#div0").hide();
        $("#div1").show();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").hide();
    }
	
	
	
    

});