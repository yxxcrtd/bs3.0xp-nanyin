(function() {
	/**
     * 获取查询参数
     */
    var getSearchParamsByOrg = function() {
        var customerId = $('.ce-org-form #customerId').val();
        if (customerId) {
            customerId = customerId.join(",");
        }
        return {
        	pflsh: $('.ce-org-form #pflsh').val(),
        	vdate: $('.ce-org-form #vdate').val(),//生效日开始
        	sheetFlag:'ORG',
            customerId: customerId
        }
    };
    var getSearchParamsByAsset = function() {
        var customerId = $('.ce-asset-form #customerId').val();
        if (customerId) {
            customerId = customerId.join(",");
        }
        return {
        	pflsh: $('.ce-asset-form #pflsh').val(),
        	vdate: $('.ce-asset-form #vdate').startDate(),//生效日开始
        	sheetFlag:'ASSET',
            customerId: customerId
        }
    };
    var getSearchParamsByAssetGrp = function() {
        var customerId = $('.ce-assetgroup-form #customerId').val();
        if (customerId) {
            customerId = customerId.join(",");
        }
        return {
        	vdate: $('.ce-assetgroup-form #vdate').startDate(),//生效日开始
        	sheetFlag:'ASSETGROUP',
            customerId: customerId,
            pflsh: $('.ce-assetgroup-form #pflsh').val()
        }
    };
    /**
     * 定义数据表格
     */
    var orgTable=$("#ceOrgTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParamsByOrg();
            }
        },
        checkbox: false,
        isMulti: true,

        tree:true,
        treeId: 'childInfo',
        treeUpId: 'parentInfo',
        treeShowName: 'pflsh',
        searchOnInit: false,
        lazyload:true,
        cols:[
				[
				 {field:'seqno', width:150 ,visible: false}
				,{field:'customerId', width:190 , visible: false,title: 'CeMonitorReport001.institutionId'}
				,{field:'pflsh', width:120 , title: 'CeMonitorReport001.pflsh',rowspan: 2}
				,{field:'customerName', width:210 , title: 'CeMonitorReport001.institutionName',rowspan: 2}
				,{field:'creditQuoteTypeValue', width:180 , title: 'CeMonitorReport001.creditQuoteType',rowspan: 2}
				,{field:'creditAmt',type :"number", sortable:false,width:140 , title: 'CeMonitorReport001.creditTotalAmtOrg',rowspan: 2,fixType:"2",pattern:"2", digit:4}
                ,{field:'creditAmtNl',type :"number", sortable:false,width:200 , title: 'CeMonitorReport001.creditAmtNl',rowspan: 2,fixType:"2",pattern:"2", digit:4}
				,{title: 'CeMonitorReport001.useAmtStatusOrg',colspan: 4}//系统资产对象
				,{field:'remainAmt', width:160 ,title: 'CeMonitorReport001.remainAmtOrg',type: 'number',rowspan: 2,fixType:"2",pattern:"2", digit:4}
				,{title: 'CeMonitorReport001.useAmtStatusNl',colspan: 4}//非法人系统资产对象
				,{field:'remainAmtNl',type :"number", width:200 ,title: 'CeMonitorReport001.remainAmtNl',type: 'number',rowspan: 2,fixType:"2",pattern:"2", digit:4}
				,{field:'vdate', width:100 ,title: 'CeMonitorReport001.vdate',type:'date',rowspan: 2}           
				,{field:'mdate',  title: 'CeMonitorReport001.mdate',type:'date',rowspan: 2}
	            ,{field:'childInfo', width:150, visible: false}
	            ,{field:'parentInfo', width:150, visible: false}
				],
              [
				 {field:'usedAmt',type :"number", width:180, title: 'CeMonitorReport001.usedAmtAcct',type: 'number',fixType:"2",pattern:"2", digit:4}
				,{field:'usedEnstustAmt',type :"number", width:180, title: 'CeMonitorReport001.usedAmtEnstustAcct',type: 'number',fixType:"2",pattern:"2", digit:4}
				,{field:'usedZizhuAmt',type :"number", width:180, title: 'CeMonitorReport001.usedZizhuAmt',type: 'number',fixType:"2",pattern:"2", digit:4}
				,{field:'usedAmtTotal',type :"number", width:180,  title: 'CeMonitorReport001.usedAmtTotal',type: 'number',fixType:"2",pattern:"2", digit:4}
                ,{field:'usedAmtNl',type :"number", width:200, title: 'CeMonitorReport001.usedAmtAcctNl',type: 'number',fixType:"2",pattern:"2", digit:4}
                ,{field:'usedEnstustAmtNl',type :"number", width:200, title: 'CeMonitorReport001.usedAmtEnstustAcctNl',type: 'number',fixType:"2",pattern:"2", digit:4}
                ,{field:'usedZizhuAmtNl',type :"number", width:180, title: 'CeMonitorReport001.usedZizhuAmtNl',type: 'number',fixType:"2",pattern:"2", digit:4}
                ,{field:'usedAmtTotalNl',type :"number", width:200,  title: 'CeMonitorReport001.usedAmtTotalNl',type: 'number',fixType:"2",pattern:"2", digit:4}
			  ]
           
        ]
    });    
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParamsByOrg();
        paramObj.parentId = 'export';
        Joyin.exportExcel('export', paramObj);
    };
    
    /**
     * 定义数据表格
     */
    var assetTable=$("#ceAssetTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParamsByAsset();
            }
        },
        checkbox: false,
        searchOnInit: false,
        isMulti: true,
        
        cols:[
				[
				 {field:'seqno', width:150 ,visible: false}
				,{field:'customerId', width:190 , visible: false,title: 'CeMonitorReport001.institutionId'}
				,{field:'pflsh', width:120 , title: 'CeMonitorReport001.pflsh',rowspan: 2}
				,{field:'customerName', width:210 , title: 'CeMonitorReport001.assetName',rowspan: 2}
				,{field:'creditAmt', sortable:false,width:140 , title: 'CeMonitorReport001.creditTotalAmt',rowspan: 2,fixType:"2",pattern:"2", digit:4}
				,{title: 'CeMonitorReport001.useAmtStatus', colspan: 4}//系统资产对象
				,{field:'remainAmt', width:140 ,sortable:false,title: 'CeMonitorReport001.remainAmt',type: 'number',rowspan: 2,digit:4}
                ,{field:'creditPrice', width:100 ,  title: 'CeAsset002.creditPrice',type:'number',digit:'4',rowspan: 2}
				,{field:'vdate', width:80 ,sortable:false,title: 'CeMonitorReport001.vdate',type:'date',rowspan: 2}           
				,{field:'mdate', width:80, sortable:false,title: 'CeMonitorReport001.mdate',type:'date',rowspan: 2}
				],
              [
				 {field:'usedAmt', width:150, sortable:false,title: 'CeMonitorReport001.usedAmtAcct',type: 'number',fixType:"2",pattern:"2", digit:4}
				,{field:'usedEnstustAmt', width:150,sortable:false, title: 'CeMonitorReport001.usedAmtEnstustAcct',type: 'number',fixType:"2",pattern:"2", digit:4}
				,{field:'usedZizhuAmt', width:150,sortable:false, title: 'CeMonitorReport001.usedZizhuAmt',type: 'number',fixType:"2",pattern:"2", digit:4}
				,{field:'usedAmtTotal', width:150, sortable:false,  title: 'CeMonitorReport001.usedAmtTotal',type: 'number',fixType:"2",pattern:"2", digit:4}
			  ]
           
        ]
    });    
    
    /**
     * 绑定导出事件
     */
    var exportAssetExcel = function() {
        var paramObj = getSearchParamsByAsset();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 定义数据表格
     */
    var assetGroupTable=$("#ceAssetGroupTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParamsByAssetGrp();
            }
        },
        checkbox: false,
        searchOnInit: false,
        isMulti: true,
        cols:[
				[
				 {field:'seqno', width:150 ,visible: false}
				,{field:'customerId', width:190 , visible: false,title: 'CeMonitorReport001.institutionId'}
				,{field:'pflsh', width:120 , title: 'CeMonitorReport001.pflsh',rowspan: 2}
				,{field:'customerName', width:210 , title: 'CeMonitorReport001.assetGroupName',rowspan: 2}
				,{field:'creditAmt', sortable:false,width:140 , title: 'CeMonitorReport001.creditTotalAmt',rowspan: 2,fixType:"2",pattern:"2", digit:4}
				,{title:'CeMonitorReport001.useAmtStatus', colspan: 4}//系统资产对象
				,{field:'remainAmt', sortable:false,width:160 ,title: 'CeMonitorReport001.remainAmt',type: 'number',rowspan: 2,digit:4}
				,{field:'vdate', width:100 ,sortable:false,title: 'CeMonitorReport001.vdate',type:'date',rowspan: 2}           
				,{field:'mdate', sortable:false, title: 'CeMonitorReport001.mdate',type:'date',rowspan: 2}
				],
              [
				 {field:'usedAmt', sortable:false,width:160 ,title: 'CeMonitorReport001.usedAmtAcct',type: 'number',fixType:"2",pattern:"2", digit:4}
				,{field:'usedEnstustAmt', sortable:false,width:160 ,title: 'CeMonitorReport001.usedAmtEnstustAcct',type: 'number',fixType:"2",pattern:"2", digit:4}
				,{field:'usedZizhuAmt', sortable:false,width:160 ,title: 'CeMonitorReport001.usedZizhuAmt',type: 'number',fixType:"2",pattern:"2", digit:4}
				,{field:'usedAmtTotal', sortable:false,width:160,  title: 'CeMonitorReport001.usedAmtTotal',type: 'number',fixType:"2",pattern:"2", digit:4}
			  ]
           
        ]
    });    
    
    /**
     * 绑定导出事件
     */
    var exportGroupExcel = function() {
        var paramObj = getSearchParamsByAssetGrp();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
        $('.ce-org-form #CeMonitorReport001_btn_search').click(function() {
            orgTable.load();
        });
     // 绑定按钮事件
        $('.ce-asset-form #CeMonitorReport001_btn_search').click(function() {
            assetTable.load();
        });
     // 绑定按钮事件
        $('.ce-assetgroup-form #CeMonitorReport001_btn_search').click(function() {
            assetGroupTable.load();
        });
        $('.ce-org-form #CeMonitorReport001_btn_export').click(function() {
            exportExcel();
        });
        $('.ce-asset-form #CeMonitorReport001_btn_export').click(function() {
            exportAssetExcel();
        });
        $('.ce-assetgroup-form #CeMonitorReport001_btn_export').click(function() {
            exportGroupExcel();
        });
    });
}());