$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function () {
        var result = $.serializeToObject('#searchForm');
        if ($("#portfolioId1").val()) {
            result.portfolioId = $("#portfolioId1").val().join(",");
        } else {
            result.portfolioId = null;
        }
        if ($("#finprodId1").val()) {
            result.finprodId = $("#finprodId1").val();
        } else {
            result.finprodId = null;
        }
        if ($("#shareId1").val()) {
            result.shareId = $("#shareId1").val();
        } else {
            result.shareId = null;
        }
        //modify by gaopeng  20200110 start
        if ($("#entrustStatus").val()) {
            result.entrustStatus = $("#entrustStatus").val();
        } else {
            result.entrustStatus = null;
        }
        //modify by gaopeng  20200110 end
        if ($("#entrustDate").val()) {
            result.entrustDateStart = $("#entrustDate").startDate();
            result.entrustDateEnd = $("#entrustDate").endDate();
        }
        result.isPosi = false;
        return result;
    };
    function getPositionFormParams() {
        var result = $.serializeToObject('#othersearchForm');
        if ($("#portfolioId").val()) {
            result.portfolioId = $("#portfolioId").val().join(",");
        } else {
            result.portfolioId = null;
        }
        if ($("#finprodId").val()) {
            result.finprodId = $("#finprodId").val();
        } else {
            result.finprodId = null;
        }
        if ($("#shareId").val()) {
            result.shareId = $("#shareId").val();
        } else {
            result.shareId = null;
        }
        result.isPosi = true;
        return result;
    }
    
    /**
     * 定义数据表格 委托
     */

    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getPageParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            data.operateType = "dblclick";
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("EtrBondToStock002.refrence"),
                    JoyinUtil.getRealPath("/invest/entrust/etrbondtostock/EtrBondToStock002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        /**
         * 定义表头
         */
        cols:[
        	{field:'entrustStatus',visible:false}
            ,{field:'busEntrustStatus', title: 'EtrInvestBondInfo001.entrustStatus', width:'10%',sortField:"entrustStatus"}
            ,{field:'portfolioId', title: 'EtrInvestBondInfo001.portfolioId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrInvestBondInfo001.portfolioName', width:'10%',sortField:"portfolioName"}
            ,{field:'finprodMarketId', title: 'EtrBondToStock001.finprodId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+row.finprodMarketId+"</ a>";
                },sortField:"finprodMarketId"
            }
            ,{field:'entrustId',visible:false}
            ,{field:'finprodName', title: 'EtrBondToStock001.finprodName', width:'10%',sortField:"finprodName"}
            ,{field:'finprodId', visible:false}
            ,{field:'shareFinprodId', visible:false}
            ,{field:'shareId', title: 'EtrBondToStock001.shareId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link shareIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"shareId"
            }
            ,{field:'shareName', title: 'EtrBondToStock001.shareName', width:'10%',sortField:"shareName"}
            ,{field:'entrustAmt', title: 'EtrBondToStock001.entrustAmt', width:'10%',sortField:"entrustAmt"}
            ,{field:'conPrice', title:'EtrBondToStock001.conPrice',width:'10%',sortable:"conPrice"}
            ,{field:'prec', title:'EtrBondToStock001.prec',width:'10%',sortable:"prec"}
            ,{field:'preb', title:'EtrBondToStock001.preb',width:'10%',sortable:"preb"}
            ,{field:'invAimName', title:'EtrInvestBondInfo002.invAim',width:'10%',sortable:"invAim"}
            ,{field:'tradeMarket', visible:false}
            ,{field:'busTradeMarket', title: 'EtrInvestBondInfo001.tradeMarket', width:'10%',sortField:"tradeMarket"}
            ,{field:'ccy',visible:false}
            ,{field:'ccy', title: 'EtrInvestBondInfo001.ccy', width:'10%',sortField:"ccy"}
            ,{field:'vdate', title: 'EtrInvestBondInfo001.vdate', width:'10%',type:'date',sortField:"vdate"}
            ,{field:'mdate', title: 'EtrInvestBondInfo001.mdate', width:'10%',type:'date',sortField:"mdate"}
            ,{field:'invManagerId', title: 'EtrInvestBondInfo001.invManagerId', width:'10%',sortField:"invManagerId"}
            ,{field:'remark', title: 'EtrInvestBondInfo001.remark', width:'10%',sortable:false}
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit']}
        ]
    });
    
    /**
     * 定义数据表格 持仓
     */

    var othertable=$("#otherDataTable").joyintable({
        ajax: {
            url: 'findOtherPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getPositionFormParams();//获取请求参数
            }//ajax请求数据
        },
        /**
         * 定义表头
         */
        cols:[
            {field:'portfolioId', title: 'EtrInvestBondInfo001.portfolioId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
                },sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrInvestBondInfo001.portfolioName', width:'10%',sortField:"portfolioName"}
            ,{field:'finprodMarketId', title: 'EtrBondToStock001.finprodId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+row.finprodMarketId+"</ a>";
                },sortField:"finprodMarketId"
            }
            ,{field:'finprodName', title: 'EtrBondToStock001.finprodName', width:'10%',sortField:"finprodName"}
            ,{field:'holdetrPriceTotal', title: 'EtrBondToStock001.holdetrPriceTotal', width:'10%',sortField:"holdetrPriceTotal"}
            ,{field:'conetrPriceTotal', title: 'EtrBondToStock001.conetrPriceTotal', width:'10%',sortField:"conetrPriceTotal"}
            ,{field:'prec', title:'EtrBondToStock001.prec',width:'10%',type:'number', fixtype:2,sortable:"prec"}
            ,{field:'preb', title:'EtrBondToStock001.preb',width:'10%',type:'number', fixtype:2,sortable:"preb"}
            ,{field:'bondPrice', title:'EtrBondToStock001.bondPrice',width:'10%',sortable:"bondPrice"}
            ,{field:'pbondvalue', title:'EtrBondToStock001.pbondvalue',width:'10%',sortable:"pbondvalue"}
            ,{field:'shareFinprodId', visible:false}
            ,{field:'shareId', title: 'EtrBondToStock001.shareId', width:'10%',
                render : function(data, type, row, meta) {
                    return "<a  class='pro-link shareIdLink' style='cursor: pointer;'>"+data+"</ a>";
                },sortField:"shareId"
            }
            ,{field:'shareName', title: 'EtrBondToStock001.shareName', width:'10%',sortField:"shareName"}
            ,{field:'sharePrice', title:'EtrBondToStock001.sharePrice',width:'10%',sortable:"sharePrice"}
            ,{field:'conPrice', title:'EtrBondToStock002.conPrice',width:'10%',sortable:"conPrice"}
            ,{field:'conProportion', title:'EtrBondToStock001.conProportion',type:'number', fixtype:2,width:'10%',sortable:"conProportion"}
            ,{field:'conValue', title:'EtrBondToStock001.conValue',type:'number', fixtype:2,width:'10%',sortable:"conValue"}
            ,{field:'resalePrice', title:'EtrBondToStock001.resalePrice',width:'10%',sortable:"resalePrice"}
            ,{field:'redeemPrice', title:'EtrBondToStock001.redeemPrice',width:'10%',sortable:"redeemPrice"}
            ,{field:'finvdate', title: 'EtrBondToStock002.finvdate', width:'10%',type:'date',sortField:"finvdate"}
            ,{field:'finmdate', title: 'EtrBondToStock002.finmdate', width:'10%',type:'date',sortField:"finmdate"}
            ,{field:'conVdate', title: 'EtrBondToStock001.conVdate', width:'10%',type:'date',sortField:"conVdate"}
            ,{field:'invAimName', title:'EtrInvestBondInfo002.invAim',width:'10%',sortable:"invAim"}
        ]
    });
    
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrBondToStock002'), basePath +'invest/entrust/etrbondtostock/EtrBondToStock002/init', '98%', '95%',function(){
        	othertable.reload();});
    }
   /**
     * 导出事件
     */
    var entrustExportExcel = function () {
        Joyin.exportExcel('export', getPageParams());
    };

    var positionExportExcel = function () {
        Joyin.exportExcel('export', getPositionFormParams());
    };
    //自动刷新时间

    /**
     * 画面加载完成
     */
    $(function () {
    	 $(".entrust-form #EtrBondToStock001_btn_search").click(function () {
    		 table.load();
         })
         var interval = null;
         $(".position-form #EtrBondToStock001_btn_search").click(function () {
    	    //定时自动刷新 
        	if(interval != null){
        	    clearInterval(interval);
        	}
            if(BTSRMS=='02'){
           	 var changeTime = $("#refreshTime").val();
            	function myrefresh()
                {
                	othertable.reload();
            		Joyin.ajax(JoyinUtil.getRealPath('invest/entrust/etrbondtostock/EtrBondToStock001/findOtherPage'), getPositionFormParams(),
            			function(result) {
            			$('#otherDataTable').html(result);
            		})
                }
                interval = setInterval(function(){myrefresh()},changeTime*1000);
            }
           	   othertable.load();
            
         })
        $(".entrust-form #EtrBondToStock001_btn_export").click(entrustExportExcel)
        $(".position-form #EtrBondToStock001_btn_export").click(positionExportExcel)
        //新建事件
         $("#EtrBondToStock001_btn_add").click(function () {
        	 showDialog();
          });
        //债转股点击事件
        $("#EtrBondToStock001_btn_bondtostocke").click(function () {
        	if (othertable != null && othertable != '') {
                var selArr = othertable.getAllCheckedRows();
             }
        	if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }
        	if (selArr.length > 1) {
                Joyin.error(JoyinUtil.getMessage("BUSS_COM_E_0046"));
                return;
             }
            // 平台日期小于转股起始日报错
            if (platDate < Date.ofDate(selArr[0].conVdate).format('yyyy-MM-dd')) {
                Joyin.error(JoyinUtil.getMessage("ETR_BTS_001"));
                return;
            }
            // 可转换券面总额验证
            if (selArr[0].conetrPriceTotal==''||selArr[0].conetrPriceTotal==null||selArr[0].conetrPriceTotal==0) {
                Joyin.error(JoyinUtil.getMessage("ETR_BTS_002"));
                return;
            }else {
            	  var param = {
                          pageModel: PAGE_MODEL_NEW,
                          portfolioId: selArr[0].portfolioId,
                          finprodId: selArr[0].finprodId,
                          shareId: selArr[0].shareId,
                          finprodName: selArr[0].finprodName,
                          shareName: selArr[0].shareName,
                          portfolioName: selArr[0].portfolioName,
                          holdetrPriceTotal: selArr[0].holdetrPriceTotal,
                          conetrPriceTotal: selArr[0].conetrPriceTotal,
                          prec: selArr[0].prec,
                          preb: selArr[0].preb,
                          bondPrice: selArr[0].bondPrice,
                          pbondvalue: selArr[0].pbondvalue,
                          conPrice: selArr[0].conPrice,
                          resalePrice: selArr[0].resalePrice,
                          redeemPrice: selArr[0].redeemPrice,
                          sharePrice: selArr[0].sharePrice,
                          conProportion: selArr[0].conProportion,
                          invAim: selArr[0].invAim,
                          vdate: selArr[0].vdate,
                          mdate: selArr[0].mdate,
                          conValue: selArr[0].conValue
                      };
            	  param.dtoStr=JSON.stringify(selArr[0]);
            	  Joyin.dialog(JoyinUtil.getLabel('EtrBondToStock002'), basePath +'invest/entrust/etrbondtostock/EtrBondToStock002/init', '98%', '95%',param,function(){
                  	othertable.reload();});
             }
        
        });
        
        //撤回事件
        $('#EtrBondToStock001_btn_revoke').click(function(){
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                var object = {selectedItem: selArr, operateType:"recall"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrbondtostock/EtrBondToStock001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrbondtostock/EtrBondToStock001/recall', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        
        });
        //关闭事件
        $('#EtrBondToStock001_btn_close').click(function(){
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
             }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
             }else {
                var object = {selectedItem: selArr, operateType:"close"};
                Joyin.ajaxJson(basePath + 'invest/entrust/etrbondtostock/EtrBondToStock001/check', object, function(result) {
                    Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                        Joyin.ajaxJson(basePath + 'invest/entrust/etrbondtostock/EtrBondToStock001/close', object, function(result) {
                            table.reload();
                        });
                    });
                });
             }
        
        });
        
        /**
         * 委托组合代码链接
         */
        table.bind('click','.portfolioIdLink', function(row) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:row.portfolioId});//主键组合代码
        });
        
        /**
         * 委托转债代码链接
         */
        table.bind('click','.finprodIdLink', function(row) {
            //在新建界面进行显示单行的详细信息
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                     basePath
                    + "invest/asset/bond/FinBondTargetAsset002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                    finprodId:row.finprodId  //金融产品代码 
                                    });
        });
        
        /**
         * 委托正股代码链接
         */
        table.bind('click','.shareIdLink', function(row) {
            //在新建界面进行显示单行的详细信息
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                     basePath
                    + "invest/asset/stock/FinStockInfo002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
            						finprodId:row.shareFinprodId  //金融产品代码 
                                    });
        });
        
        /**
         * 持仓组合代码链接
         */
        othertable.bind('click','.portfolioIdLink', function(row) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                    "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                    portfolioId:row.portfolioId});//主键组合代码
        });
        
        /**
         * 持仓转债代码链接
         */
        othertable.bind('click','.finprodIdLink', function(row) {
            //在新建界面进行显示单行的详细信息
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                     basePath
                    + "invest/asset/bond/FinBondTargetAsset002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
                                    finprodId:row.finprodId  //金融产品代码 
                                    });
        });
        
        /**
         * 持仓正股代码链接
         */
        othertable.bind('click','.shareIdLink', function(row) {
            //在新建界面进行显示单行的详细信息
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                     basePath
                    + "invest/asset/stock/FinStockInfo002/init",
                    "95%", "80%", {pageModel:PAGE_MODEL_REFERENCE,
            						finprodId:row.shareFinprodId  //金融产品代码 
                                    });
        });
        
        /**
         * 绑定编辑事件
         */
        table.bind('click', '.edit', function(data, index) {
            data.operateType = "edit";
            data.pageModel=PAGE_MODEL_EDIT;
             Joyin.ajaxJson(basePath + 'invest/entrust/etrbondtostock/EtrBondToStock001/check', data, function(result) {
                Joyin.dialog(JoyinUtil.getLabel("EtrBondToStock002.edit"),
                        JoyinUtil.getRealPath("/invest/entrust/etrbondtostock/EtrBondToStock002/init"),
                        "98%", "95%", data,
                   function(){table.reload();});
             });
        });
        $("#refreshTime").change(function(){
        	//自动刷新时间判定
        	if($("#refreshTime").val()>180){
        		$("#refreshTime").val(180);
        	}if($("#refreshTime").val()<0){
        		$("#refreshTime").val(0);
        	}
        })
         
    });
}());