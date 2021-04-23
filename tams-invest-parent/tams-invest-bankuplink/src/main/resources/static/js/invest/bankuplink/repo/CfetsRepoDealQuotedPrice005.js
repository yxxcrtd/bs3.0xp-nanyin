(function(){
	
    /**
     * 获取查询参数
     */
    var genEntrustOrderParam = function() {
        var pageParam = $.serializeToObject('#cfetsRepoDealQuotedPrice005_entrustOrder_form');
        if (pageParam.invManagerId && pageParam.invManagerId != '') {
            pageParam.invManagerId = pageParam.invManagerId.join(",");
        } else {
            pageParam.invManagerId = null;
        }
        if (pageParam.portfolioId && pageParam.portfolioId != '') {
            pageParam.portfolioId = pageParam.portfolioId.join(",");
        } else {
            pageParam.portfolioId = null;
        }
        if (pageParam.entrustId && pageParam.entrustId != '') {
            pageParam.entrustId = pageParam.entrustId.join(",");
        } else {
            pageParam.entrustId = null;
        }
        pageParam.settleSpreed = settleSpreed;
        pageParam.isDzh = isDzh;
        return pageParam;
    };

    /**
     * 定义数据表格
     */
    var entrustOrderTable=$("#CfetsRepoDealQuotedPrice005_entrust_table").joyintable({
        ajax: {
            url: 'queryEntrustOrderList',
            type: 'POST',
            data: function() {
                return genEntrustOrderParam();
            }
        },
        height:240,
        searchOnInit:true,
        loadonce : true,
        page :false,
        multiple: false,
        sortfont: true,
        cols:[
            {field:'entrustStatusStr', width:120, title: 'CfetsRepoDealQuotedPrice005.entrust.table.orderStatus',sortField:"entrustStatus"}
           ,{field:'transactioncom', title: 'CfetsRepoDealQuotedPrice005.entrust.table.finishPercent',render : function(data, type, row, meta){
                   return "<div class='layui-progress layui-progress-big' style='margin-top:1px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.transactioncom+"%'><span class='layui-progress-text'>"+row.transactioncom+"%</span></div></div>";
               }, width:'8%',sortable:false}
           ,{field:'invManagerName', width:120, title: 'CfetsRepoDealQuotedPrice005.entrust.table.invManagerId'}
           ,{field:'portfolioId', width:120, title: 'CfetsRepoDealQuotedPrice005.entrust.table.portfolioId'}
           ,{field:'portfolioName', width:120, title: 'CfetsRepoDealQuotedPrice005.entrust.table.portfolioName'}
           ,{field:'secAcctName', width:120, title: 'CfetsRepoDealQuotedPrice005.entrust.table.secAcctId'}
           ,{field:'psStr', width:120, title: 'CfetsRepoDealQuotedPrice005.entrust.table.entrustPs',sortField:"ps"}
           ,{field:'entrustNum', width:120,type:'number', title: 'CfetsRepoDealQuotedPrice005.entrust.table.entrustQuota'}
           ,{field:'finishAmt', width:120,type:'number', title: 'CfetsRepoDealQuotedPrice005.entrust.table.comQuota'}
           ,{field:'avaNum', width:120,type:'number', title: 'CfetsRepoDealQuotedPrice005.entrust.table.avaQuota'}
           ,{field:'vdate', width:120,type:'date', title: 'CfetsRepoDealQuotedPrice005.entrust.table.entrustStartDate'}
           ,{field:'mdate', width:120,type:'date', title: 'CfetsRepoDealQuotedPrice005.entrust.table.entrustEndDate'}
           ,{field:'entrustId', width:180, title: 'CfetsRepoDealQuotedPrice005.entrust.table.entrustId'}
           ,{field:'ps', width:120,visible:false}
           ,{field:'term', width:120,visible:false}
           ,{field:'settleSpreed', width:120,visible:false}

        ]
    });
    
    /**
	 * 确认点击事件
	 */
    var confirmAction = function(){
    	if(entrustOrderTable!=null && entrustOrderTable!=''){
            var selArr = entrustOrderTable.getAllCheckedRows();
            
            if (!selArr || selArr.length == 0){
            	Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_R"));
                return;
            }
            if(isDzh=="Y"){
                //是否满足如下条件：同一回购期限+同一方向+同一清算速度+同一交易账户（21位码/6位码）
                var msg="";var entrustId = "";
                var termFlag = true;var psFlag = true; var settleSpreedFlag = true; var mysideFlag = true;
                for(var i=0;i<selArr.length-1;i++){
                    if(termFlag&&selArr[i].term!=selArr[i+1].term){termFlag=false;msg += "回购期限&"}
                    if(psFlag&&selArr[i].ps!=selArr[i+1].ps){psFlag=false;msg += "交易方向&"}
                    if(settleSpreedFlag&&selArr[i].settleSpreed!=selArr[i+1].settleSpreed){settleSpreedFlag=false;msg += "清算速度&"}
                    if(mysideFlag&&selArr[i].myside!=selArr[i+1].myside){mysideFlag=false;msg += "交易账户&"}
                }
                if(!(termFlag&&psFlag&&settleSpreedFlag&&mysideFlag)){
                    //“所选指令不满足汇总条件，【回购期限&交易方向&清算速度&交易账户】不同
                    msg = msg.charAt(msg.length-1) == "&"?msg.substr(0,msg.length-1):msg;
                    Joyin.error("所选指令不满足汇总条件["+msg+"]不同");
                    return;
                }else{
                    //进入多组合界面
                    $.each(selArr,function (i,e) {
                        entrustId += e.entrustId+",";
                    })
                    var object = {entrustId:entrustId,operation:"CfetsRepoDealQuotedPrice001.new",isDzh:isDzh};
                    Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
                        if (result.data){
                            //添加组合分配列表
                            // 将选中的委托信息返还给报价页面
                            window.parent.CfetsRepoDealQuotedPrice003.reloadPtlAllocation(selArr);
                            var index = window.parent.layer.getFrameIndex(window.name);
                            window.parent.layer.close(index);
                        }
                    });
                }
            }else{
                if (selArr && selArr.length > 1) {
                    Joyin.error(JoyinUtil.getMessage("COMMON_E_0058"));
                    return;
                }

                var object = {entrustId:selArr[0].entrustId,operation:"CfetsRepoDealQuotedPrice001.new"};
                Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice002/checkEdit', object, function(result) {
                    if (result.data){
                        // 将选中的委托信息返还给报价页面
                        window.parent.CfetsRepoDealQuotedPrice002.saveEntrustSelectData(selArr[0]);
                        var index = window.parent.layer.getFrameIndex(window.name);
                        window.parent.layer.close(index);
                    }
                });
            }
            // 列表中勾选对象1条以上时
        }
    }
    
    
    
    $(function () {
    	/**
    	 * 委托指令放大镜点击事件
    	 */
    	$('#CfetsRepoDealQuotedPrice005_entrustOrder_search').click(function(){
    		entrustOrderTable.table ? entrustOrderTable.reload() : entrustOrderTable.load();
        });
    	/**
    	 * 确认点击事件
    	 */
    	$('#CfetsRepoDealQuotedPrice005_btn_confirm').click(function(){
    		confirmAction();
        });

        /**
         * radio click事件
         */
        /*$('[name="entrustStatus"]').click(function () {
            var value = $(this).val();
            $('#CfetsRepoDealQuotedPrice005_entrustOrder_search').click();
        });*/
    });
}());