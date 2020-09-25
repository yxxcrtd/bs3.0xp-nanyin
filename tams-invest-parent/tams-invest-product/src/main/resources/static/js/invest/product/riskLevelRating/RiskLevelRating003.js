/**
 * 存续产品风险等级评定底层资产风险等级评定
 */
(function () {
    /**
     * 获取查询参数
     */

    var getPageParams = function () {
        var pageParam = {};
        pageParam.finProductCode = finProductCode;
        pageParam.generateTime = generateTime;
        pageParam.flag = 'Y';
        return pageParam;
    };

    /**
     * 组合信息查询
     */
    var table = $("#dataTable").joyintable({
        ajax: {
            url: 'findList',
            type: 'POST',
            data: function () {
                return getPageParams();
            },
        },
        // addbtn:true,
        height: 'full-' + 220,
        checkbox: false,
        // tree:true,
        // loadonce:true,
        searchOnInit:true,
        page:false,
        loadonce:true,
        cols: [
            {field: 'finProductCode', width: 100, title: 'RiskLevelRating002.finprodMarketId', type: 'text',sortable:false
            }
            , {field: 'finprodAbbr', width: 100, title: 'RiskLevelRating002.finprodName', type: 'text',
                editOption:{type:'select',id:"finprodAbbr",formatoption:true,result:PINAN_ASSET_TYPE},sortable:false}
            , {field: 'customAssetTypeTwo', width: 160, title: 'RiskLevelRating002.customAssetTypeTwo', type: 'text',edit:true,
                editOption:{type:'select',id:"customAssetTypeTwo",formatoption:true,result:CUSTOM_ASSET_TYPE_TWO},sortable:false}
            , {field: 'customAssetTypeThree', width: 160, title: 'RiskLevelRating002.customAssetTypeThree', type: 'text',edit:true,
                editOption:{type:'select',id:"customAssetTypeThree",formatoption:true,result:CUSTOM_ASSET_TYPE_THREE},sortable:false}
            , {field: 'customAssetTypeFour', width: 160, title: 'RiskLevelRating002.customAssetTypeFour', type: 'text',edit:true,
                editOption:{type:'select',id:"customAssetTypeFour",formatoption:true,result:CUSTOM_ASSET_TYPE_FOUR},sortable:false}
            , {field: 'customAssetTypeFive', width: 160, title: 'RiskLevelRating002.customAssetTypeFive', type: 'text',edit:true,
                editOption:{type:'select',id:"customAssetTypeFive",formatoption:true,result:CUSTOM_ASSET_TYPE_FIVE},sortable:false}
            ,{field: 'assetRiskType', width: 160, title: 'RiskLevelRating001.assetRiskLevel', type: 'text',edit:true,
                editOption:{type:'select',id:"riskLevel",formatoption:true,result:RISKLEVEL},sortable:false}
            ],
        beforeEditCell : function(preVal, field, rowData){
            return false;
        }
    });


}());