var FinMdateCommon = function(){
    return {
            
            getElementById : function(id) {
                return $('#' + pageId + '_' + id);
            },
            /**
             * 调整类型切换
             */
            adjustTypeSelect : function(){
                var adjustType = FinMdateCommon.getElementById('adjustType').val();
                    if(adjustType === ADJUSTTYPE_01){
                        document.getElementById("FinMdateAdjustment002.beforeEnddate").style.display="";
                        document.getElementById("FinMdateAdjustment002.adjustAfterMdate").style.display="none";
                        document.getElementById("FinMdateAdjustment002.RolloverDate").style.display="none";
                    }
                    if(adjustType === ADJUSTTYPE_02){
                        document.getElementById("FinMdateAdjustment002.beforeEnddate").style.display="none";
                        document.getElementById("FinMdateAdjustment002.adjustAfterMdate").style.display="none";
                        document.getElementById("FinMdateAdjustment002.RolloverDate").style.display="";
                    }
                    if(adjustType === ADJUSTTYPE_03){
                        document.getElementById("FinMdateAdjustment002.beforeEnddate").style.display="none";
                        document.getElementById("FinMdateAdjustment002.RolloverDate").style.display="none";
                        document.getElementById("FinMdateAdjustment002.adjustAfterMdate").style.display="";
                    }
                }
            }
}();