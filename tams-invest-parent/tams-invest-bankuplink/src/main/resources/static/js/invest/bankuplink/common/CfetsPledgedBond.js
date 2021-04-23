(function () {


    //刷新对手方质押券
    var refreshCfetsPldegeBond = function(data){
        if(isShowDsfPledge == 'Y'){
            var html = "";
            if(cfetsRepoDealQuotedPrices&&cfetsRepoDealQuotedPrices.length>0){
                if(data&&data.length>0){
                    cfetsRepoDealQuotedPrices = $.each(cfetsRepoDealQuotedPrices,function (i,e) {
                        e.backgroundDis = false;
                        var pledgePrin = 0;
                        $.each(data,function (j,f) {
                            if(e.bondCode == f.bondCode){
                                pledgePrin = JoyinMath.doAdd(pledgePrin,f.pledgePrin);
                            }
                        });
                        e.ypppledgeNumber = pledgePrin;
                        if(Number(pledgePrin)>=Number(e.pledgeNumber)){
                            //置灰
                            e.backgroundDis = true;
                        }
                    });
                    cfetsRepoDealQuotedPrices = cfetsRepoDealQuotedPrices.sort(function (a,b) {
                        return JoyinMath.doMinus(JoyinMath.doMinus(b.pledgeNumber,b.ypppledgeNumber),JoyinMath.doMinus(a.pledgeNumber,a.ypppledgeNumber));
                    })
                }
                $.each(cfetsRepoDealQuotedPrices,function (i,e) {
                    var ypppledgeNumber = e.ypppledgeNumber?e.ypppledgeNumber:"0";
                    var headDiv = '<div style="float: left;width:98%!important;">';
                    if(e.backgroundDis){
                        headDiv = '<div style="float: left;width:98%!important;background-color: #cccccc">';
                    }
                    html = html.concat(headDiv).concat('<div align="left" style="float: left;width:150px"><a href="#"  onclick="bondCodeClick(\''+e.finprodId+'\')">'+e.bondCode+'</a></div>')
                                                .concat('<div align="left" style="float: left;width:200px"><a><span style="color:red">'+e.bondAbbrName).concat('</span></a></div>')
                                                .concat('<div align="left" style="float: left;width:200px"><a>'+"质押券面额"+'<span style="color:red">'+e.pledgeNumber).concat('万元</span></a></div>')
                                                .concat('<div align="left" style="float: left;width:200px"><a>'+"折算比例"+'<span style="color:red">'+e.pledgeRate).concat('%</span></a></div>')
                                                .concat('<div align="left" style="float: left;width:300px"><a>'+"已匹配质押券面额"+'<span style="color:red">'+ypppledgeNumber).concat('万元</span></a></div>')
                                                .concat("</div>");
                });
                $('#cfetsPledgedBond').html(html);
            }
        }
    }


    /**
     * 对外公开接口
     */
    window.CfetsPledgedBond = {
        refreshCfetsPldegeBond: refreshCfetsPldegeBond
    };
    /**
     * 画面加载完成
     */
    $(function () {
        refreshCfetsPldegeBond();
    });

}());


var bondCodeClick = function(finprodId){
    if(isShowDsfPledge == 'Y' && pageFrom == 'BussRepoSecDialog'){
        $('#bussRepoSec_bondCode').textSelect('setValue',finprodId);
        $('#bussRepoSec_btn_search').click();
    }
}
