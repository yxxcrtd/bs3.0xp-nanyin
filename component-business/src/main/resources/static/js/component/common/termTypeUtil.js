/**
 * 质押回购期限品种
 */
;(function ($) {
    $.fn.tradeRepTypeChange = function tradeRepTypeChange(obj) {
        //上交所
        if (obj.tradeMarket == obj.tradeP02) {
            if (obj.repoType == obj.repoTypeH02){
                //竞价式
                $('#' +  obj.termType).updateChosenArr({item: ['GC001','GC002','GC003','GC004','GC007','GC014','GC028','GC091','GC182'],
                    disabled: false});
            } else if(obj.repoType == obj.repoTypeH01){
                //协议式
                $('#' +  obj.termType).updateChosenArr({item: ['R001','R007','R014','R021','R1M' ,'R1Y' ,'R2M' ,'R3M' ,'R4M' ,'R6M' ,'R9M' ],
                    disabled: false});
            }else {
                $('#' +  obj.termType).updateChosenArr({item: [''], disabled: false});
            }
           
        }else if (obj.tradeMarket == obj.tradeP03 && obj.repoType == obj.repoTypeH02 ) {
            $('#' +  obj.termType).updateChosenArr({item: ['R-001','R-002','R-003','R-004','R-007','R-014','R-028','R-091','R-182','RC001','RC003','RC007'],
                disabled: false});
            //modify by yujiaping 2020-11-25 BS-1144银行间:回购类型为质押式回购
        }else if (obj.tradeMarket == obj.tradeP01 && obj.repoType == obj.repoTypeH04) {
            $('#' +  obj.termType).updateChosenArr({item: ['R001','R007','R014','R021','R1M' ,'R1Y' ,'R2M' ,'R3M' ,'R4M' ,'R6M' ,'R9M' ],
                disabled: false});
        }else {
            $('#' +  obj.termType).updateChosenArr({item: [''], disabled: false});
        }
        if(obj.termTypeValue){
            $('#' +  obj.termType).val(obj.termTypeValue).trigger('change');
        }
    }
})(jQuery)
