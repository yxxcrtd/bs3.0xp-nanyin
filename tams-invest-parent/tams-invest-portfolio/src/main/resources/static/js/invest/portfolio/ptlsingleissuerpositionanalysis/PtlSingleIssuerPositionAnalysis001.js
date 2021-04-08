$(function () {
	/**
     * 获取查询参数
     */
    var getSearchParams = function () {
    	var result = $.serializeToObject('#searchForm');
        if ($("#searchDate").val()) {
            result.searchDateStart = $("#searchDate").startDate();
            result.searchDateEnd = $("#searchDate").endDate();
        }
        var temp=result.sex;
        if($("#publisher").val() == null || $("#publisher").val() == ''){
            result.publisher = $("#publisher").val();
        }
        else{
            result.publisher = $("#publisher").val().join(',');
        }
        return result;
 
    };
    
    var dataTable=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
       checkbox:false,
       tree:true,
       treeId: 'childInfo',
       treeUpId: 'parentInfo',
       treeShowName: 'publisher',
       lazyload:true,
       cols: [{field:'publisher', title: '发行人',minWidth:200, width:200}//资产名称
           ,{field:'finprodAbbr', title: '资产名称',minWidth:200, width:200}//资产名称
           ,{field:'finprodMarketId', title: '资产代码',minWidth:200, width:200}//资产代码
           ,{field:'portFolioName', title: '组合名称',minWidth:200, width:200}//组合名称
           ,{field:'shareAmt',  width:140,title: '持仓数量',type:'money'}//持仓数量
           ,{field:'dscCostAmt', width:140,title: '成本(元)',type:'money'}//成本(元)
           ,{field:'marketValue',width:140, title: '市值(元)',type:'money'}//市值(元)
           ,{field:'marketValueRatio', title: '市值占比%',minWidth:100}//市值比例%
           ,{field:'childInfo', visible: false}
           ,{field:'parentInfo', visible: false}
           ,{field:'belongInstitutionNameNew',minWidth:180, width:180, title: '机构名称',visible:AUTHORITY_TYPE=='02'}//组合名称
       ]
   });
    
    
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取参数
        paramObj.tab = '0';
        Joyin.exportExcel('export', paramObj);//导出
    };
    
    /**
     * 【吉林银行个性化代码】添加导出一级明细数据
     * add by wangyang1
     * 2019年9月23日 
     */
    var detailOneExport = function() { 
        var paramObj = getSearchParams(); 
        paramObj.tab = '1';
        Joyin.exportExcel('export', paramObj);//导出
    };
    
    $(function () {
        //查询按钮
        $("#PtlSingleIssuerPositionAnalysis001_btn_search").click(function () {
            /*$(".ptl-table").css("display","block");
            $(".ptl-table1").css("display","none");*/
            var strError = '';
            if(!$('#searchDate').val()){
                strError = strError + "请输入"+JoyinUtil.getLabel("OperationManage001.cdate");
            }
            if(strError != '' && strError != null){
                Joyin.error(strError);
                return;
            }
        	dataTable.load();
        });
    });
    
  //绑定导出按钮
    $('#PtlSingleIssuerPositionAnalysis001_btn_export').click(function() {
        exportExcel();//导出
    });
    
    /**
     * 添加导出一级明细数据
     * add by wangyang1
     * 2019年9月23日
     */
    $('#PtlCashFlow001_btn_detailOneExport').click(function() {
    	detailOneExport(); 
    });
    
    /*var table1=$("#dataTable1").joyintable({
        searchOnInit: false,
        checkbox: false,
        // tree:true,
        // treeShowName: 'publisher',
        // rootId:"0",
        cols: [{field:'publisher', title: '发行人'}//发行人
            ,{field:'assetCode', title: '资产代码'}//资产代码
            ,{field:'assetName', title: '资产名称'}//资产名称
            ,{field:'combiteName', title: '组合名称'}//组合名称
            ,{field:'positionNumber', title: '持仓数量'}//持仓数量
            ,{field:'cost', title: '成本(元)'}//成本(元)
            ,{field:'marketValue', title: '市值(元)'}//市值(元)
            ,{field:'marketValueRatio', title: '市值比例%'}//市值比例%
        ]
    });
    var table=$("#dataTable").joyintable({
         ajax: {
             url: 'findPage',
             type: 'POST',
             data: function() {
                 return getPageParams();
             }
         },
        tree:true,
         treeId: 'deptCode',
         treeUpId: 'upperDeptCode',
         treeShowName: 'deptName',
        lazyload:true,
        checkbox: false,
        searchOnInit: true,
        checkbox: false,
        tree:true,
        treeShowName: 'publisher',
        rootId:"0",
        isMulti:true,
        data:  [{ parentId: '1',
                id: '100',
                publisher: '',
                assetCode: '019023.SH',
                assetName: '10国债23',
                combiteName: '',
                positionNumber: '200000',
                cost: 19337326.31,
                marketValue: 20772950.00,
                marketValueRatio: '' },
            { parentId: '1',
                id: '101',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日欣稳利1号',
                positionNumber: '100000',
                cost: 9573934.80,
                marketValue: '10273610.00',
                marketValueRatio: '2.10%' },
            { parentId: '1',
                id: '102',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日欣稳利2号',
                positionNumber: '100000',
                cost: 9763391.51,
                marketValue: '10501340.00',
                marketValueRatio: '2.14%' },
            { parentId: '1',
                id: '103',
                publisher: '',
                assetCode: '019037.SH',
                assetName: '10国债37',
                combiteName: '',
                positionNumber: '400000',
                cost: 40016522.47,
                marketValue: 42575070.96,
                marketValueRatio: '' },
            { parentId: '1',
                id: '104',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日欣稳利1号',
                positionNumber: '100000',
                cost: 9984141.51,
                marketValue: '11025600.00',
                marketValueRatio: '2.26%' },
            { parentId: '1',
                id: '105',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日欣稳利2号',
                positionNumber: '100000',
                cost: 10070499.04,
                marketValue: '10952250.00',
                marketValueRatio: '2.24%' },
            { parentId: '1',
                id: '106',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利1号',
                positionNumber: '100000',
                cost: 10041470.96,
                marketValue: '10676810.00',
                marketValueRatio: '2.18%' },
            { parentId: '1',
                id: '107',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利2号',
                positionNumber: '100000',
                cost: 9920410.96,
                marketValue: '9920410.96',
                marketValueRatio: '2.02%' },
            { parentId: '1',
                id: '108',
                publisher: '',
                assetCode: '019112.SH',
                assetName: '11国债12',
                combiteName: '',
                positionNumber: '100000',
                cost: 10136946.71,
                marketValue: 11184510.00,
                marketValueRatio: '' },
            { parentId: '1',
                id: '109',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利2号',
                positionNumber: '100000',
                cost: 10136946.71,
                marketValue: '11184510.00',
                marketValueRatio: '2.28%' },
            { parentId: '1',
                id: '110',
                publisher: '',
                assetCode: '019116.SH',
                assetName: '11国债16',
                combiteName: '',
                positionNumber: '100000',
                cost: 9959240.69,
                marketValue: 11001330.00,
                marketValueRatio: '' },
            { parentId: '1',
                id: '111',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利1号',
                positionNumber: '100000',
                cost: 9959240.69,
                marketValue: '11001330.00',
                marketValueRatio: '2.24%' },
            { parentId: '0',
                id: '1',
                publisher: '中华人民共和国财政部',
                assetCode: '',
                assetName: '',
                combiteName: '',
                positionNumber: '800000',
                cost: 79450036.18,
                marketValue: 85535860.96,
                marketValueRatio: '' },
            { parentId: '2',
                id: '201',
                publisher: '',
                assetCode: '1205001.IB',
                assetName: '12工行01',
                combiteName: '',
                positionNumber: '200000',
                cost: 21593706.23,
                marketValue: 21253450.21,
                marketValueRatio: '' },
            { parentId: '2',
                id: '202',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日欣稳利2号',
                positionNumber: '100000',
                cost: 10786182.60,
                marketValue: '10568778.08',
                marketValueRatio: '2.16%' },
            { parentId: '2',
                id: '203',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利1号',
                positionNumber: '100000',
                cost: 10807523.63,
                marketValue: '10684672.13',
                marketValueRatio: '2.18%' },
            { parentId: '0',
                id: '2',
                publisher: '中国工商银行股份有限公司',
                assetCode: '',
                assetName: '',
                combiteName: '',
                positionNumber: '200000',
                cost: 21593760.23,
                marketValue: 21253450.21,
                marketValueRatio: '' },
            { parentId: '3',
                id: '301',
                publisher: '',
                assetCode: '038001.IB',
                assetName: '03浦发债',
                combiteName: '',
                positionNumber: '60000',
                cost: 66000.00,
                marketValue: 6000600.00,
                marketValueRatio: '' },
            { parentId: '3',
                id: '302',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利2号',
                positionNumber: '60000',
                cost: 66000.00,
                marketValue: 6000600.00,
                marketValueRatio: '1.22%' },
            { parentId: '0',
                id: '3',
                publisher: '上海浦东发展(集团)有限公司',
                assetCode: '',
                assetName: '',
                combiteName: '',
                positionNumber: '60000',
                cost: 66000.00,
                marketValue: 6000600.00,
                marketValueRatio: '' },
            { parentId: '4',
                id: '401',
                publisher: '',
                assetCode: '038006.IB',
                assetName: '03三峡债',
                combiteName: '',
                positionNumber: '500000',
                cost: 53584506.99,
                marketValue: 51573715.06,
                marketValueRatio: '' },
            { parentId: '4',
                id: '402',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日欣稳利1号',
                positionNumber: '100000',
                cost: 10573660.00,
                marketValue: 10497372.60,
                marketValueRatio: '2.14%' },
            { parentId: '4',
                id: '403',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日欣稳利2号',
                positionNumber: '100000',
                cost: 10846852.88,
                marketValue: 10273068.49,
                marketValueRatio: '2.10%' },
            { parentId: '4',
                id: '404',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利1号',
                positionNumber: '100000',
                cost: 10820574.11,
                marketValue: 10260397.26,
                marketValueRatio: '2.10%' },
            { parentId: '4',
                id: '405',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利2号',
                positionNumber: '100000',
                cost: 10679790.00,
                marketValue: 10206397.26,
                marketValueRatio: '2.08%' },
            { parentId: '4',
                id: '405',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利3号',
                positionNumber: '100000',
                cost: 10663630.00,
                marketValue: 10336479.45,
                marketValueRatio: '2.12%' },
            { parentId: '0',
                id: '4',
                publisher: '中国长江三峡集团有限公司',
                assetCode: '',
                assetName: '',
                combiteName: '',
                positionNumber: '500000',
                cost: 53584506.99,
                marketValue: 51573715.06,
                marketValueRatio: '' },
            { parentId: '5',
                id: '501',
                publisher: '',
                assetCode: '068012.IB',
                assetName: '06冀建投债',
                combiteName: '',
                positionNumber: '100000',
                cost: 10215912.47,
                marketValue: 10072342.47,
                marketValueRatio: '' },
            { parentId: '5',
                id: '502',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利3号',
                positionNumber: '100000',
                cost: 10215912.47,
                marketValue: 10072342.47,
                marketValueRatio: '2.06%' },
            { parentId: '0',
                id: '5',
                publisher: '河北建设投资集团有限责任公司',
                assetCode: '',
                assetName: '',
                combiteName: '',
                positionNumber: '100000',
                cost: 10215912.47,
                marketValue: 10072342.47,
                marketValueRatio: '' },
            { parentId: '6',
                id: '601',
                publisher: '',
                assetCode: '068016.IB',
                assetName: '06航天债',
                combiteName: '',
                positionNumber: '200000',
                cost: 21664358.49,
                marketValue: 20536720.00,
                marketValueRatio: '' },
            { parentId: '6',
                id: '602',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日欣稳利1号',
                positionNumber: '100000',
                cost: 10819039.86,
                marketValue: 10275090.00,
                marketValueRatio: '2.10%' },
            { parentId: '6',
                id: '603',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利1号',
                positionNumber: '100000',
                cost: 10845318.63,
                marketValue: 10261630.00,
                marketValueRatio: '2.10%' },
            { parentId: '0',
                id: '6',
                publisher: '中国航天科技集团有限公司',
                assetCode: '',
                assetName: '',
                combiteName: '',
                positionNumber: '200000',
                cost: 21664358.49,
                marketValue: 20536720.00,
                marketValueRatio: '' },
            { parentId: '7',
                id: '701',
                publisher: '',
                assetCode: '038007.IB',
                assetName: '03中铁债',
                combiteName: '',
                positionNumber: '200000',
                cost: 21264492.33,
                marketValue: 20633742.33,
                marketValueRatio: '' },
            { parentId: '7',
                id: '702',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利1号',
                positionNumber: '100000',
                cost: 10632880.41,
                marketValue: 10320312.33,
                marketValueRatio: '2.10%' },
            { parentId: '7',
                id: '703',
                publisher: '',
                assetCode: '',
                assetName: '',
                combiteName: '日日盈稳利3号',
                positionNumber: '100000',
                cost: 10631611.92,
                marketValue: 10313430.00,
                marketValueRatio: '2.10%' },
            { parentId: '0',
                id: '7',
                publisher: '中国铁路总公司',
                assetCode: '',
                assetName: '',
                combiteName: '',
                positionNumber: '200000',
                cost: 21264492.33,
                marketValue: 20633742.33,
                marketValueRatio: '' }
        ],
        cols: [{field:'publisher', title: '发行人'}//发行人
            ,{field:'assetCode', title: '资产代码'}//资产代码
            ,{field:'assetName', title: '资产代码'}//资产名称
            ,{field:'combiteName', title: '组合名称'}//组合名称
            ,{field:'positionNumber', title: '持仓数量'}//持仓数量
            ,{field:'cost', title: '成本(元)'}//成本(元)
            ,{field:'marketValue', title: '市值(元)'}//市值(元)
            ,{field:'marketValueRatio', title: '市值比例%'}//市值比例%
        ]
    });*/
    
    
    
});
