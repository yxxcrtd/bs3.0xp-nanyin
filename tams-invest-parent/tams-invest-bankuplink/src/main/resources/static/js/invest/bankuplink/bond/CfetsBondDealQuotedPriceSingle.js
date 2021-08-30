
(function(){
    $(function() {
        init();
    });

    var init = function(){
        $('#qqEtr_div').hide();
        $('#dhEtr_div').show();
    };

    var showDiv = function(btnType,selArr){
        if (btnType === "DH"){
            $('#qqEtr_div').hide();
            $('#dhEtr_div').show();
            window.CfetsBondDealQuotedPriceSingle001.setDhDiv(selArr);
        }
        if (btnType === "QQ"){
            $('#qqEtr_div').show();
            $('#dhEtr_div').hide();
            window.CfetsBondDealQuotedPriceSingle002.setQQDiv(selArr);
        }
    }

    var getriskdata_Single001 = function(){
        return window.CfetsBondDealQuotedPriceSingle001.getriskdata();
    }

    var getriskdata_Single002 = function(){
        return window.CfetsBondDealQuotedPriceSingle002.getriskdata();
    }

    var getSaveOrSubmitParams_Single001 = function(operateType){
        return window.CfetsBondDealQuotedPriceSingle001.getSaveOrSubmitParams(operateType);
    }

    var getSaveOrSubmitParams_Single002 = function(operateType){
        return window.CfetsBondDealQuotedPriceSingle002.getSaveOrSubmitParams(operateType);
    }

    var resetSingle001 = function(){
        return window.CfetsBondDealQuotedPriceSingle001.reset();
    }

    var resetSingle002 = function(){
        return window.CfetsBondDealQuotedPriceSingle002.reset();
    }

    var saveRiskResultdata_Single001 = function(riskResultdata) {
        window.CfetsBondDealQuotedPriceSingle001.saveRiskResultdata(riskResultdata);
    }
    var saveRiskResultdata_Single002 = function(riskResultdata) {
        window.CfetsBondDealQuotedPriceSingle002.saveRiskResultdata(riskResultdata);
    }
    var saveCluflag_Single001 = function(cluflag) {
        window.CfetsBondDealQuotedPriceSingle001.saveCluflag(cluflag);
    }
    var saveCluflag_Single002 = function(cluflag) {
        window.CfetsBondDealQuotedPriceSingle002.saveCluflag(cluflag);
    }

    parent.CfetsBondDealQuotedPriceSingle = {
        showDiv: showDiv,
        getriskdata_Single001: getriskdata_Single001,
        getriskdata_Single002: getriskdata_Single002,
        getSaveOrSubmitParams_Single001: getSaveOrSubmitParams_Single001,
        getSaveOrSubmitParams_Single002: getSaveOrSubmitParams_Single002,
        resetSingle001: resetSingle001,
        resetSingle002: resetSingle002,
        saveRiskResultdata_Single001: saveRiskResultdata_Single001,
        saveRiskResultdata_Single002: saveRiskResultdata_Single002,
        saveCluflag_Single001: saveCluflag_Single001,
        saveCluflag_Single002: saveCluflag_Single002
    }

}());