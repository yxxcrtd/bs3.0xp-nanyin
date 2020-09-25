/**
 * 投后信息组件JS
 * @auth zhangnian
 */
(function(){
    //获取本画面元素id
    var getElementById = function(id,type) {
        if(type=="div"){
            return $('#' + entityName_BussPostInvestInfo + '_' + id+'_'+'div');
        }else{
            return $('#' + entityName_BussPostInvestInfo + '_' + id);
        }
    };

    var isBuyBackChange = function(){
        var isBuyBack = getElementById('isBuyBack')[0].checked;
        if(isBuyBack){
            getElementById('buyBackRate').prop('disabled',false).trigger('change');
        }else{
            getElementById('buyBackRate').val("");
            getElementById('buyBackRate').prop('disabled',true).trigger('change');
        }

    }
    
    $(function(){
        //是否代持change
        layui.form.on('switch(isBuyBack)',function(){
            isBuyBackChange();
        });
        isBuyBackChange();
    });
}());