$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var portfolioId = $('#portfolioId').val();
        if (portfolioId && portfolioId != '' ){
            portfolioId = portfolioId.join(',');
	    }else {
             portfolioId = null;
        } 
        return {
            portfolioId : portfolioId,//组合代码
            // add by caiqun 20200819 东莞 工单YHZGCPB20200814003 start
            startCDate : $('#cdate').startDate(),
            endCDate : $('#cdate').endDate(),
            //cdate : $('#cdate').val(),
            // add by caiqun 20200819 东莞 工单YHZGCPB20200814003 end
            accountType : $('#accountType').val(),
			// add by wangyang1 【吉林银行】 增加 产品经理  2019年9月29日 start
            prodManager:$('#prodManager').val(),//组合代码
			// add by wangyang1 【吉林银行】 增加 产品经理  end  
            index:'0'
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
        ajax: {
            url: 'findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        /*dblclick:function(table, data) {
            data.pageModel = PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/init",
                    "90%", "80%",
                    data);
        },*/
        checkbox:false,
        lazyload:true,
        /**
         * 定义表格名称
         */
        cols:[
             {field:'cdateStr', title: 'PtlFundAcctPosi001.date', width:100,sortable: false}//sortField:'tradeStatus'
            ,{field:'portfolioId', title: 'PtlFundAcctPosi001.portfolioId', width:150,sortField: 'PORTFOLIO_ID'}
            ,{field:'portfolioName', title: 'PtlFundAcctPosi001.portfolioName', width:200}
            ,{field:'acctId', title: 'PtlFundAcctPosi001.busiAcctNo', width:150}
            ,{field:'acctName', title: 'PtlFundAcctPosi001.acctName', width:200}
            ,{field:'amount', title: 'PtlFundAcctPosi001.amount',type:'money',width:140}
            ,{field:'amount1', title: 'PtlFundAcctPosi001.amount1',type:'money',width:140}
            ,{field:'ccyStr', title: 'PtlFundAcctPosi001.ccy',sortField: 'ccy'}
            ,{field:'ccy', visible:false}
            ,{field:'hodingType', visible:false}
            /*,{field:'detail', title: 'PtlFundAcctPosi001.detail',
            	render : function(data, type, row, meta) {
                	return "<a class='PtlFundAcctPosi001 layui-btn layui-btn-sm btn-create' style='color:white'>明细</a>";
        		},
            }*/
            ,{field:'operation' , type:'action', title: 'PtlFundAcctPosi001.detail',
                render:function (data, type, row, meta) {
                var buttons = '';
                if(purviewElements.indexOf('_manual')!=-1){
                       buttons += '<a class="layui-btn layui-btn-sm btn-create  manual" title="明细" style="color:white">明细</a>';
                 }
                return buttons;
                }
            }
            
        ]
    });
    
    /**
     * 组合代码链接
     */
    table.bind('click','.manual', function(row) {
    	Joyin.dialog(JoyinUtil.getLabel("PtlFundAcctPosi002"),
                basePath + "invest/portfolio/ptlfundacctposi/PtlFundAcctPosi002/init",
                "98%", "90%", {pageModel:PAGE_MODEL_NEW,//page_model:查看
                portfolioId:row.portfolioId,
                cdate:row.cdateStr,
                acctId:row.acctId,
                ccy:row.ccy,
                hodingType:row.hodingType});//主键组合代码
    });
    //<a id="PtlActivityPeriod001_btn_add" class="layui-btn layui-btn-sm btn-create" data-th-text="#{SysCommon.create}">新建</a>
    /**
     * 绑定编辑事件
     */
    /*table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("PtlActivityPeriod001.edit"),
                basePath +"invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/init",
                "80%", "80%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
                                portfolioId:data.portfolioId});//主键组合代码
        
    });*/
    
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取参数
        Joyin.exportExcel('export', paramObj);//导出
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询按钮
        $('#PtlFundAcctPosi001_btn_search').click(function() {
            var strError = '';
            /*if(!$('#portfolioId').val()){
                strError = "请输入"+JoyinUtil.getLabel("PtlActivityPeriod001.portfolioId")+"<br>";
            }*/
            if(!$('#cdate').val()){
                strError = strError + "请输入"+JoyinUtil.getLabel("OperationManage001.cdate");
            } else {
                if ($('#cdate').val() > sysDate) {
                    strError = strError + '日期必须小于等于平台日';
                }
            }
            if(strError != '' && strError != null){
                Joyin.error(strError);
                return;
            }
            table.load();//加载表格
        });
        //绑定导出按钮
        $('#PtlFundAcctPosi001_btn_export').click(function() {
            exportExcel();//导出
        });
        //绑定新建按钮
        $('#PtlFundAcctPosi001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PtlFundAcctPosi002"),
                    basePath+ "invest/portfolio/ptlactivityperiod/PtlActivityPeriod002/init",
                        "90%", "80%", {pageModel:PAGE_MODEL_NEW});
        });
    });
}());