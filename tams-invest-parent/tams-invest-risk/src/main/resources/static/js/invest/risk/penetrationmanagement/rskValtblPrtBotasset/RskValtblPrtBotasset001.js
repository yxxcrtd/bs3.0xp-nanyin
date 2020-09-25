$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            mappingStat : $('#mappingStat').val().join(','),
            ptlId : $('#ptlId').val(),
            ptlName:$('#ptlId').select2('data')[0].text
        }
    };
    var table=$("#dataTable").joyintable({
         ajax: {
             url: 'findPage',
             type: 'POST',
             data: function() {
                 return getPageParams();
             }
         },
        checkbox: false,
        searchOnInit: false,
        isMulti: true,
        cols: [
            [
                {field:'ptlId', title: 'RskValtblPrtBotasset001.ptlId',visible:false}//组合代码   
                ,{field:'assetSource', title: 'RskValtblPrtBotasset001.assetSource',visible:false}//资产来源 
                ,{field:'subClass', title: 'RskValtblPrtBotasset001.subClass',visible:false,sortField:'SUB_CLASS'}//科目类型
                ,{field:'tradeMarketName', title: 'RskValtblPrtBotasset001.tradeMarket',sortField:'TRADE_MARKET',visible:false}//市场代码
                ,{field:'tradeMarket', title: 'RskValtblPrtBotasset001.tradeMarket',visible:false}//交易场所
                ,{field:'tblMarketId', title: '估值表科目对应的交易场所',visible:false}//估值表科目对应的交易场所
                ,{title: '组合资产科目', colspan: 3}//资管计划资产科目
                ,{title: '系统资产对象', colspan: 3}//系统资产对象
                ,{field:'operation', title: 'SysCommon.operation', type:'action', rowspan: 2,
                    render:function (data, type, row, meta) {
                  	if (row.flg != '01') {
                    		return "";
                    	}else if(row.finprodId==''||typeof row.finprodId == 'undefined'){
                    		if(purviewElements.indexOf('_edit')!=-1){
                                return '<i class="btn layui-icon edit" title="编辑">&#xe642;</i>';
                            }
                    		return "";
                    	}else{
                    		var btn='';
                    		if(purviewElements.indexOf('_edit')!=-1){	
                                btn+= '<i class="btn layui-icon edit" title="编辑">&#xe642;</i>';
                            }
                    		if(purviewElements.indexOf('_batch')!=-1){	
                                btn+= '<i class="btn iconfont icon-browse_fill" title="应用"></i>';
                            }
                                return btn;
                            }
                  		}
                    }
                
            ],
            [
                 {field:'subCode', title: 'RskValtblPrtBotasset001.subCode',sortField:'SUB_CODE'}//科目代码 
                ,{field:'subClassName', title: 'RskValtblPrtBotasset001.subClass',sortField:'SUB_CLASS'}//科目类型
                ,{field:'subName', title: 'RskValtblPrtBotasset001.subName',sortField:'SUB_NAME'}//科目名称
                /*,{field:'assetType', title: '资产类型'}//资产类型
*/              ,{field:'finprodId', title: 'RskValtblPrtBotasset001.finprodId',sortField:'FINPROD_ID'}//资产代码
                ,{field:'finprodAbbr', title: 'RskValtblPrtBotasset001.finprodAbbr',sortField:'FINPROD_ABBR'}//资产简称
                ,{field:'finprodMarketId', title: 'RskValtblPrtBotasset001.finprodMarketId',sortField:'FINPROD_MARKET_ID'}//市场代码 
           ]]
    });
    
    /**
     * 绑定应用事件
     */
    table.bind('click', '.icon-browse_fill', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
            Joyin.dialog(JoyinUtil.getLabel("RskValtblPrtBotasset003.title"),
                    basePath + "invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset003/init",//打开新界面
                    "30%", "45%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
                                    ptlId:data.ptlId,   //组合代码
                                    ptlName : $('#ptlId').select2('data')[0].text,
                                    subCode : data.subCode,
                                    subName : typeof data.subName =='undefined'?'':data.subName,
                                    subClass: typeof data.subClass =='undefined'?'':data.subClass,
                                    finprodId:data.finprodId,//金融产品代码
                                    finprodMarketId:data.finprodMarketId,//市场代码
                                    assetSource:data.assetSource,
                                    tblMarketId:data.tblMarketId//估值表科目对应的交易场所
                                    },function(){
                                        table.reload();//回调函数，表格刷新
                                    });     
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
            Joyin.dialog(JoyinUtil.getLabel("RskValtblPrtBotasset002.title"),
                    basePath + "invest/risk/penetrationmanagement/rskValtblPrtBotasset/RskValtblPrtBotasset002/init",//打开新界面
                    "60%", "55%", {pageModel:PAGE_MODEL_EDIT,//请求的page_model
                                    ptlId:data.ptlId,   //组合代码
                                    ptlName : $('#ptlId').select2('data')[0].text,
                                    subCode : data.subCode,
                                    subName : typeof data.subName =='undefined'?'':data.subName,
                                    subClass: typeof data.subClass =='undefined'?'':data.subClass,
                                    finprodId:data.finprodId,//金融产品代码
                                    finprodMarketId:data.finprodMarketId//市场代码
                                    ,tblMarketId: typeof data.tblMarketId == 'undefined'? '':data.tblMarketId//估值表科目对应的交易场所
                                    },function(){
                                        table.reload();//回调函数，表格刷新
                                    });     
    });
    
    /**
     * 导出选中的表格数据
     */
    var exportExcel = function() {
        var paramObj = getPageParams();//请求参数json格式
        Joyin.exportExcel('export', paramObj);//导出（共通）
    };
    
    /*var mappingStatChange = function(){
        
        if($('#ptlId').val()!=''&&typeof $('#ptlId').val() != 'undefined'){
            $('#RskValtblPrtBotasset001_btn_search').click();
        }
    }*/
    $(function () {
       /*$('#mappingStat').change(mappingStatChange);*/
      //初始化查询
        $('#RskValtblPrtBotasset001_btn_search').click(function() {
            var msg;
            if($('#ptlId').val()==''||typeof $('#ptlId').val() == 'undefined'){
                msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("RskValtblPrtBotasset001.ptlId"));
            }
            if(!$('#mappingStat').val()){
                msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("RskValtblPrtBotasset001.mappingStat"));   
            }
            if(msg){
                Joyin.error(msg);
                return false;
            }else{
                table.load();
            }
        });
        /**
         * 绑定编辑事件
         */
        $(".edit").click(function () {
            Joyin.dialog(JoyinUtil.getLabel('修改编辑估值表底层资产映射'), '../RskValtblPrtBotasset002/init', '60%', '55%');
        })
        
        $('#RskValtblPrtBotasset001_btn_export').click(exportExcel);
    })
});
