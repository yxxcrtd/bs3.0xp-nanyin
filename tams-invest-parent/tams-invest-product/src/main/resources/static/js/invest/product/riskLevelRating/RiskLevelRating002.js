/**
 * 存续产品风险等级评定
 */
(function () {
    /**
     * 获取查询参数
     */

    var getPageParams = function () {
        var pageParam = $.serializeToObject('#submitForm');
        pageParam.seqNo=$('#seqNo').val();
        pageParam.selectIem=table.getAllRows();
        console.log(pageParam);
        return pageParam;
    };

    function saveOrSubmit(operateType) {
        /**
         * 获取列表参数
         */
        var object = getPageParams();
        var workflowId;
        if (workflowId != null || workflowId != '') {
            object.workflowId = workflowId;
        }
        var rows=table.getAllRows();
        if(rows && rows!=null && rows!=''){
            for (var i=0;i<rows.length;i++){
                if(!rows[i].ajustRiskLevel ||rows[i].ajustRiskLevel==''||rows[i].ajustRiskLevel==null ){
                    Joyin.error('调整后资产风险等级不能为空');
                    return;
                }
            }
        }
        object.operateType = pageModel;
        Joyin.ajaxJson(basePath + 'invest/product/riskLevelRating/RiskLevelRating002/check', object, function (result) {
            if(pageModel==PAGE_MODEL_NEW){
            Joyin.ajaxJson(basePath + 'invest/product/riskLevelRating/RiskLevelRating002/onlyOne', object, function (result) {
                console.log(result);
                var flag=result[0];
                if (flag=='Y'){
                    Joyin.ajaxJson(basePath + 'invest/product/riskLevelRating/RiskLevelRating002/selectNo', object, function (result) {
                        object.seqNo=result[0];
                    });
                    // /*object.generateTime=formatDate(new Date());
                    // $('#generateTime').val(formatDate(new Date()));*/
                    Joyin.confirm("该评定日已存在该产品的风险等级评定数据，是否覆盖更改",function () {
                        object.operateType=PAGE_MODEL_EDIT;pageModel=PAGE_MODEL_EDIT;
                        confm();
                    }),function () {

                    }
                }
            })}
            var confm=function(){
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/product/riskLevelRating/RiskLevelRating002/save', object, "RiskLevelRating002_btn_save");
                } else if (operateType === 'submit') {
                    WorkFlow.doSubmitList(basePath + 'invest/product/riskLevelRating/RiskLevelRating002/save', object, "RiskLevelRating002_btn_submit");
                }
            });
            };
            confm();
        });
    }

    /**
     * 时间格式转换
     */
    function formatDate(inputTime) {
        var date = new Date(inputTime);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    }
    /**
     * 评定列表
     */
    var table = $("#dataTable").joyintable({
        // addbtn:true,
        height: 'full-' + 220,
        checkbox: false,
        // tree:true,
        // loadonce:true,
        rowno:true,
        cols: [
            {field: 'branch', visible:false}
            , {field: 'finProductCode', width: 100, title: 'RiskLevelRating002.finprodMarketId', type: 'text',sortable:false
                }
            , {field: 'finprodAbbr', width: 100, title: 'RiskLevelRating002.finprodName', type: 'text',edit:true,
                editOption:{type:'select',id:"finprodAbbr",formatoption:true,result:PINAN_ASSET_TYPE},sortable:false}
            , {field: 'pinanAssetType',visible:false}
            , {field: 'customAssetTypeTwo', width: 160, title: 'RiskLevelRating002.customAssetTypeTwo', type: 'text',edit:true,
                editOption:{type:'select',id:"customAssetTypeTwo",formatoption:true,result:CUSTOM_ASSET_TYPE_TWO},sortable:false}
            , {field: 'customAssetTypeThree', width: 160, title: 'RiskLevelRating002.customAssetTypeThree', type: 'text',edit:true,
                editOption:{type:'select',id:"customAssetTypeThree",formatoption:true,result:CUSTOM_ASSET_TYPE_THREE},sortable:false}
            , {field: 'customAssetTypeFour', width: 160, title: 'RiskLevelRating002.customAssetTypeFour', type: 'text',edit:true,
                editOption:{type:'select',id:"customAssetTypeFour",formatoption:true,result:CUSTOM_ASSET_TYPE_FOUR},sortable:false}
            , {field: 'customAssetTypeFive', width: 160, title: 'RiskLevelRating002.customAssetTypeFive', type: 'text',edit:true,
                editOption:{type:'select',id:"customAssetTypeFive",formatoption:true,result:CUSTOM_ASSET_TYPE_FIVE},sortable:false}
            , {field: 'assetRiskType',visible:false}
            , {field: 'assetRiskTypeStr', width: 160, title: 'RiskLevelRating001.assetRiskLevel', type: 'text',edit:true,
                editOption:{type:'select',id:"riskLevel",formatoption:true,result:RISKLEVEL},sortable:false,
                render: function (data, type, row, meta,options) {
                    if (row.pinanAssetType =='RR09') {
                        return '<a class="assetRiskType-link" title="历史数据" style="color:blue;">'+row.assetRiskTypeStr+'</a>';
                    }

                }}
            , {field: 'ajustRiskLevel', width: 160, title: 'RiskLevelRating001.ajustRiskLevel2', type: 'text',edit:true,
                editOption:{type:'select',id:"ajustRiskLevel",formatoption:true,result:RISKLEVEL},sortable:false}
            , {field: 'crn', width: 160, title: '集中度', type: 'number',visible:false }
            , {field: 'assetRiskType', width: 160, title: 'RiskLevelRating001.ajustRiskLevel2', type: 'text',visible:false}

        ],
        beforeEditCell : function(preVal, field, rowData){
            if(field=='ajustRiskLevel'){
                return true;
            }
            return false;
        }
    });
    //绑定资产风险等级
    table.bind('click', '.assetRiskType-link', function (row) {
        Joyin.dialog(JoyinUtil.getLabel("底层资产风险等级评定列表"),
            basePath + "invest/product/riskLevelRating/RiskLevelRating003/init",
            "90%", "90%", {
                finProductCode: $('#finProductCode').val(),
                generateTime:entity.generateTime==null?edate:entity.generateTime
            });
    });
    var finProductCodeChange=function(){
        var text=$('#finProductCode').select2('data')[0].col;
        $('#finprodAbbr').val(text);//产品代码自动带出产品名称
        //获取资产评定列表
        Joyin.ajaxJson(basePath + 'invest/product/riskLevelRating/RiskLevelRating002/findList', getPageParams(), function(result) {
            console.log(result);
            if (result!=null){
                if(pageModel==PAGE_MODEL_NEW){
                $('#riskLevel').textSelect('setValue',result.riskLevel);
                }
                if(!$('#ajustRiskLevel').val()||$('#ajustRiskLevel').val()==null ||$('#ajustRiskLevel').val()==''){
                $('#ajustRiskLevel').textSelect('setValue',result.riskLevel);
                }
                if(result.riskList!=null && result.riskList.length>0 ){
                    table.table ? table.clear() : table.load();
            for(var i = 0; i < result.riskList.length; i++) {
                if(result.riskList[i].ajustRiskLevel==null||result.riskList[i].ajustRiskLevel==''){
                result.riskList[i].ajustRiskLevel=result.riskList[i].assetRiskType;
                }
                table.addRow(result.riskList[i]);
            }
                }
            }
        })
    };
    var init=function(){
        // if (pageModel==PAGE_MODEL_NEW ){
        //     finProductCodeChange();
        // }
        //修改只能修改产品风险等级和调整后风险等级
        if(pageModel==PAGE_MODEL_EDIT){
            $("#finProductCode").prop("disabled", true);
            //$("#riskLevel").prop("disabled", false);BSTOT-559修改仅调整后产品、资产风险等级可以编辑
        }
        if (entity!=null && entity.selectIem!=null && entity.selectIem.length>0){
            table.table ? table.clear() : table.load();
            for(var i = 0; i < entity.selectIem.length; i++) {
                console.log(entity.selectIem[i]);
                table.addRow(entity.selectIem[i]);
            }
        }
    };
    function prodRiskChange(){
        var rows=table.getAllRows();
        var dto={};
        dto.riskList=rows;
        console.log(dto);
        Joyin.ajaxJson(basePath + 'invest/product/riskLevelRating/RiskLevelRating002/prodRisk', dto, function(result) {
        console.log(result[0]);
        $('#riskLevel').textSelect('setValue',result[0]);
        })
    }
    $(function () {
        init();
        //产品代码选择后联动其他表单
        $('#finProductCode').change(function () {
            if (pageModel==PAGE_MODEL_NEW ){
            finProductCodeChange();
            }
        });
        $('#RiskLevelRating001_btn_new').click(function () {
            Joyin.dialog(JoyinUtil.getLabel("RiskLevelRating001.new"),
                basePath + "invest/product/riskLevelRating/RiskLevelRating002/init",
                "90%", "90%", {
                    pageModel: PAGE_MODEL_NEW
                });
        });
        $('#RiskLevelRating002_btn_save').click(function () {
            saveOrSubmit("save");
        });
        $('#RiskLevelRating002_btn_submit').click(function () {
            saveOrSubmit("submit");
        });
        $('#RiskLevelRating001_btn_export').click(function() {
        exportExcel();
    });
        $('#RiskLevelRating002_prod').click(function() {
            prodRiskChange();
        });
    });
}());