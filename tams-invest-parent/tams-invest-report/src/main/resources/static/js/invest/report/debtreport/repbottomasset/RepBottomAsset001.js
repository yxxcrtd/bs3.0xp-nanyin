(function() {
	
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var repAssetCodeTemp = $('#repAssetCode').val();
        var repAssetCode = null;
        if(repAssetCodeTemp){
            repAssetCode = repAssetCodeTemp.join(",");
        }
        var repBottomCode2Temp = $('#repBottomCode2').val();
        var repBottomCode2 = null;
        if(repBottomCode2Temp){
            repBottomCode2 = repBottomCode2Temp.join(",");
        }
        return {
            repAssetCode: repAssetCode,
            repBottomCode2: repBottomCode2,
            repPositionDateStart: $('#holdingDate').startDate(),
            repPositionDateEnd: $('#holdingDate').endDate(),
            repStatusDateStart: $('#dataDate').startDate(),
            repStatusDateEnd: $('#dataDate').endDate()
        };
    };

    /**
     * 定义数据表格
     */
    
    var table=$("#dataTable").joyintable({
        ajax:{
            url:'findPage',
            type:'POST',
            data:function (){
                return getSearchParams();
            }
        },
        /*
         * 定义表头
         */
        cols:[
            {field:'repStatusDate', title: 'RepBottomAsset.dataDate',type:'date'}
            ,{field:'repIssuePartyId',  title: 'RepBottomAsset.DistributorCode'}
            ,{field:'repAssetCode',title:'RepBottomAsset.theAssetAndTheAsset_liabilityCodesOfTheAssetClass'}
            ,{field:'repSumMoney',title:'RepBottomAsset.capitalManagementAndForeignAssetsAreCurrentlyAtTotalDiscountToTheYuan'}
            ,{field:'repSumAmount', title:'RepBottomAsset.capitalManagementAndExternalAssets'}
            ,{field:'repUninvestCash', title:'RepBottomAsset.capitalManagementAndExternalAssetClass'}
            ,{field:'repBottomCode2', title:'RepBottomAsset.theUnderlyingAsset_liabilityCode'}
            ,{field:'repBottomAssetAmount',title:'RepBottomAsset.lowerAssetPositions'}
            ,{field:'repBottomAssetSmv', title:'RepBottomAsset.theUnderlyingAssetsAreValuedByTheYuan'}
            ,{field:'repPositionDate', title:'RepBottomAsset.holdingDate',type:'date'}
        ]
    });
    
    var getParamObj = function(){
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        var primaryKey = new Array();
        if (selArr || selArr.length > 0) {
            $.each(selArr, function(i, v) {  
                if(v.repFinprodId != null && v.repFinprodId != '' && v.repAssetCode != null && v.repAssetCode != '' &&
                        v.repPositionDate != null && v.repPositionDate != ''){
                    primaryKey[i] = v.repFinprodId + '/' + v.repAssetCode +'/'+ v.repPositionDate;
                }
            });
        }
        return {
            primaryKey:primaryKey
        }
        
    }
    var exportExcel = function() {
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var paramObj = getParamObj();
        Joyin.exportExcel('export', paramObj);
    };
    

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RepBottomAsset001_btn_search').click(function() {
            table.load();
        });
       
        $('#RepBottomAsset001_btn_export').click(function() {
            exportExcel();
        });
    });
}());