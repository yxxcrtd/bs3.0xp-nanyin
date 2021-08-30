(function(){
    $(function() {
        init();
    });

    var init = function(){
        $('#qqEtr_div').hide();
        $('#dhEtr_div').show();
    };

    var showDiv = function(btnType,entrustIdList){
        if (btnType === "DH"){
            $('#qqEtr_div').hide();
            $('#dhEtr_div').show();
            window.CfetsBondDealQuotedPriceMuti001.setDhDiv(entrustIdList);
        }
        if (btnType === "QQ"){
            $('#qqEtr_div').show();
            $('#dhEtr_div').hide();
            window.CfetsBondDealQuotedPriceMuti002.setQQDiv(entrustIdList);
        }
    }

    var getriskdata_Muti001 = function(){
        return window.CfetsBondDealQuotedPriceMuti001.getriskdata();
    }
    
    var getriskdata_Muti002 = function(){
        return window.CfetsBondDealQuotedPriceMuti002.getriskdata();
    }

    var getSaveOrSubmitParams_Muti001 = function(operateType){
        return window.CfetsBondDealQuotedPriceMuti001.getSaveOrSubmitParams(operateType);
    }

    var getSaveOrSubmitParams_Muti002 = function(operateType){
        return window.CfetsBondDealQuotedPriceMuti002.getSaveOrSubmitParams(operateType);
    }

    var resetMuti001 = function(){
        return window.CfetsBondDealQuotedPriceMuti001.reset();
    }

    var resetMuti002 = function(){
        return window.CfetsBondDealQuotedPriceMuti002.reset();
    }

    var saveRiskResultdata_Muti001 = function(riskResultdata) {
        window.CfetsBondDealQuotedPriceMuti001.saveRiskResultdata(riskResultdata);
    }
    var saveRiskResultdata_Muti002 = function(riskResultdata) {
        window.CfetsBondDealQuotedPriceMuti002.saveRiskResultdata(riskResultdata);
    }
    var saveCluflag_Muti001 = function(cluflag) {
        window.CfetsBondDealQuotedPriceMuti001.saveCluflag(cluflag);
    }
    var saveCluflag_Muti002 = function(cluflag) {
        window.CfetsBondDealQuotedPriceMuti002.saveCluflag(cluflag);
    }

    parent.CfetsBondDealQuotedPriceMuti = {
        showDiv: showDiv,
        getriskdata_Muti001: getriskdata_Muti001,
        getriskdata_Muti002: getriskdata_Muti002,
        getSaveOrSubmitParams_Muti001: getSaveOrSubmitParams_Muti001,
        getSaveOrSubmitParams_Muti002: getSaveOrSubmitParams_Muti002,
        resetMuti001: resetMuti001,
        resetMuti002: resetMuti002,
        saveRiskResultdata_Muti001: saveRiskResultdata_Muti001,
        saveRiskResultdata_Muti002: saveRiskResultdata_Muti002,
        saveCluflag_Muti001: saveCluflag_Muti001,
        saveCluflag_Muti002: saveCluflag_Muti002
    }

}());