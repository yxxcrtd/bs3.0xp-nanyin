(function(){
	
    /**
     * 获取查询参数
     */
    var genParam = function() {
        var pageParam = $.serializeToObject('#CstpPledgeBond_form');
        return pageParam;
    };

    /**
     * 定义数据表格
     */
    var entrustOrderTable=$("#CstpPledgeBond_table").joyintable({
        ajax: {
            url: 'getCstpPledgeBondList',
            type: 'POST',
            data: function() {
                return genParam();
            }
        },
        height:330,
        searchOnInit:true,
        page: false,
        loadonce: true,
        sortfont: true,
        cols:[
            {field:'finprodId', width:150, title: 'BussRepoSec.avaPledgeBondDetailTBL.colmodel.bondCode'}
           ,{field:'bondAbbrName', width:150, title: 'BussRepoSec.pledgeBondTotalTbl.colmodel.bondAbbrName'}
           ,{field:'pledgePrin', width:150, title: '质押券面额(万元)'}
           ,{field:'netPrice', width:150, title: 'BussRepoSec.pledgeBondTotalTbl.colmodel.firstNetPrice'}
           ,{field:'pledgeRate', width:150, title: 'BussRepoSec.pledgeBondTotalTbl.colmodel.pledgeRatio'}
           ,{field:'repRegistOrg', width:150, title: 'BussRepoSec.avaPledgeBondDetailTBL.colmodel.repRegistOrg',visible:false}
           ,{field:'repRegistOrgStr', width:230, title: 'BussRepoSec.avaPledgeBondDetailTBL.colmodel.repRegistOrg'}
        ]
    });

		/**
    	 * 确认点击事件
    	 */
    	$('#CstpBond_btn_confirm').click(function(){
			var index = window.parent.layer.getFrameIndex(window.name);
            window.parent.layer.close(index);
        });
    
}());