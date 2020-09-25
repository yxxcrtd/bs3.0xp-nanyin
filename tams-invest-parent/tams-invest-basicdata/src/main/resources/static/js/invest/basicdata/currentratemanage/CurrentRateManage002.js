(function() {
    
    /**
     * 获取表单参数
     */
        var informationform = $.serializeToObject('#informationForm1');
   
        /**
         * 保存或提交
         * @param operateType 操作类型（save，submit）
         * @returns
         */
        function saveOrSubmit(operateType) {
            var object = $.serializeToObject('#informationForm1');
            object.rateType=$('#rateType').val();
            object.rate=$('#rate').val();
            object.coefficient=$('#coefficient').val();
            object.spread=$('#spread').val();
            object.currentRate=$('#currentRate').val();
            object.operateType = operateType;
            Joyin.ajax(basePath + 'invest/basicdata/currentratemanage/CurrentRateManage002/check', object, function(result) {
                Joyin.confirm(result.data, function() {
                    if(operateType === 'save') {
                        WorkFlow.doSave(basePath + 'invest/basicdata/currentratemanage/CurrentRateManage002/save', object, "FundAccountManagement002_btn_save");
                    }
                    else {
                        WorkFlow.doSubmit(basePath + 'invest/basicdata/currentratemanage/CurrentRateManage002/save', object, "FundAccountManagement002_btn_submit");
                    }
                });
            });
        }
        
        //根据利率类型设置显隐
        function checkChange(){
            if($('#rateType').val()=='固定利率'){
                $('#rate').required=true;
                $('#rateDiv').style.display='show';
                $('#currentRateDiv').style.display='none';
            }else if($('#rateType').val()=='浮动利率'){
                $('#currentRate').required=true;
                $('#currentRateDiv').style.display='show';
                $('#rateDiv').style.display='none';
            }
        }
        
        $(function() {
            // 按钮事件
            $('#CurrentRateManage002_btn_save').click(function() {
                saveOrSubmit('save');
            });
            $('#CurrentRateManage002_btn_submit').click(function() {
                saveOrSubmit('submit');
            });
            $('#rateType').change(function(){
                checkChange();
            });
        });    
}());