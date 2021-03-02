(function() {
    
    /**
     * 加载树结构
     */
    $('#tree').joyinTree({
        onTreeLoadSuccess: function () {
            if(pageModel == PAGE_MODEL_REFERENCE) {
                var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
                var nodes = treeObj.getNodes();
                for(var i=0; i < nodes.length;i++) {
                    var node = nodes[i];
                    DisabledNode(treeObj, node);
                }
            }
        }
    });
    
    function expandAll() {
        $.fn.zTree.getZTreeObj('tree_tree').expandAll(!$('#expandAll').is(':checked'));
    };
    
    $('#portfolioId').change(function(){
        $('#tree').reloadTree({portfolioId:$('#portfolioId').val()});
    })
    function DisabledNode(treeObj, node) {
        treeObj.setChkDisabled(node, true);
        if(node.isParent) {
            var children = node.children;
            for(var i=0; i < children.length; i++) {
                DisabledNode(treeObj, children[i]);
            }
        }
    }
    var getElementById = function(id) {
        return $('#' + id);
    };
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    
    function init(){
        if(pageModel == PAGE_MODEL_EDIT){
            $("#portfolioType").attr("disabled",true);
            $('#tree').reloadTree({portfolioId:$('#portfolioId').val()});
        }
        if ($('#wfl_operType').val()){
            if ($('#wfl_operType').val() != 'NEW'){
                $("#portfolioType").attr("disabled",true);
            }else{
                $("#portfolioType").attr("disabled",false);
            }
        }
        if(pageModel!=PAGE_MODEL_NEW){
                $("#portfolioId").attr("disabled",true);
                $("#unitvalueDec").attr("disabled",true);
                $("#unitvalueMode").attr("disabled",true);
                //modify by wm start 框架升级导致js报错问题修复 2019-11-28
                $('#' + userIdList.join(',#')).prop("checked",true);
//                for(var i in userIdList){
//                    $("#"+userIdList[i]).prop("checked",true);
//                }
                //modify by wm end 框架升级导致js报错问题修复 2019-11-28
        }
        if(pageModel == PAGE_MODEL_REFERENCE){
            $(".userId").attr("disabled",true);
            $('#tree').reloadTree({portfolioId:$('#portfolioId').val()});
        }
        if(pageModel==PAGE_MODEL_NEW || pageModel==PAGE_MODEL_EDIT){
            $("#portfolioType").val('01').change();
        }
    }
    function saveOrSubmit(operateType) {
        var fundAccountTables = FundAccountInfo.table;
        var securitiesAccountInfoTables = SecuritiesAccountInfo.table;
        /**
         * 获取列表参数
         */

        var object = $.serializeToObject('#basicInfoForm');
        var treeObj = $.fn.zTree.getZTreeObj("tree_tree");
        var nodes = treeObj.getCheckedNodes();
        var params = new Array();
        for (var i = 0; i < nodes.length; i++) {
            getParam(nodes[i], params);
        }
        if(params.length == 0){
            params = null;
        }
        object.userId = params;
//        if(object.userId){
//            object.userId = object.userId.split(',');
//        }
        if(object.isGuarantee != 'N'){
            object.isGuarantee = 'Y'
        }
        if(object.ptlValParam.isCountingCurInt != 'N'){
            object.ptlValParam.isCountingCurInt = 'Y'
        }
        //文件信息
        if(FileCommon){
            object.fileArray = FileCommon.getUploadFileInfo();
        }
        var fundAccountTable = fundAccountTables.getAllRows();
        var securitiesAccountInfoTable = securitiesAccountInfoTables.getAllRows();
        object.fundAccountInfoDto = fundAccountTable;
        for(var i in object.fundAccountInfoDto){
            if(object.fundAccountInfoDto[i] && object.fundAccountInfoDto[i].acctNo && object.fundAccountInfoDto[i].ccy){
                object.fundAccountInfoDto[i].acctNo = object.fundAccountInfoDto[i].acctNo.concat('_',object.fundAccountInfoDto[i].ccy);
            }
        }
        object.securitiesAccountInfoDto = securitiesAccountInfoTable;
        object.operateType = operateType;
        object.pageModel = pageModel;
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/check', object, function(result) {
                if(operateType === 'save') {
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.save")), function() {
                        WorkFlow.doSaveList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/save', object, "PtlManagementInfo002_btn_save");
                    });
                }
                else {
                    Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.submit")), function() {
                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/save', object, "PtlManagementInfo002_btn_submit");
                    });
                }
        });
    }
    
    /**
     * 递归获取节点和父节点数据
     */
     function getParam(node, params) {
        if (node) {
            if(node.id != null && $.inArray(node.id, params) < 0){
                if(!node.parent){
                    params.push(node.id);
                }
            }
            getParam(node.getParentNode(), params);
        }
    }
    
    function profitType(){
        var selectedValue = $('#profitType').val();
        if (!selectedValue) {
            $(".ccy,.profit,.net").show();
        }else if(selectedValue == profitType01){
            // 当收益类型为“收益型”时，“利率小数位”默认保留2位，“利率处理方式”默认“四舍五入”，“托管活期是否计息”默认为“是”，其他字段字段置灰不可用；
            $(".ccy,.profit,.net").hide();
            $(".net").show();
        }else if(selectedValue == profitType02){
            $(".ccy,.profit,.net").hide();
            $(".profit").show();
        }else if(selectedValue == profitType03){
            $(".ccy,.profit,.net").hide();
            $(".ccy").show();
        }
    }
    
    function portfolioTypeChange(){
        var portfolioType = $('#portfolioType').val();
        if (!portfolioType){ 
        	$(".pro-module-step").attr("style", "position: relative;"); 
        	$(".expandAllWrap_div").show();
        	$(".stepbar").show();
        	$(".stepbar_div").show();
        	$(".prevStep").attr("style", "");
        	$(".step1").attr("style", "display: block;"); 
        	$(".step2").attr("style", "display: none;"); 
        	 $('#' + loginUser).prop("checked",false);
        }else if(portfolioType == profitType01){
        	$(".pro-module-step").attr("style", "position: relative;"); 
        	$(".expandAllWrap_div").show();
        	$(".stepbar").show();
        	$(".stepbar_div").show();
        	$(".prevStep").attr("style", "");
        	$(".step1").attr("style", "display: block;"); 
        	$(".step2").attr("style", "display: none;"); 
        	$('#' + loginUser).prop("checked",false);
        }else if(portfolioType == profitType02){
        	$(".pro-module-step").attr("style", "display: none;"); 
        	$(".step1").attr("style", "display: block;"); 
        	$(".step2").attr("style", "display: block;"); 
        	$(".stepbar").attr("style", "display: none;");  
        	$(".expandAllWrap_div").hide();
        	$(".stepbar_div").show();
        	$(".prevStep").attr("style", "display: none;");
            $('#' + loginUser).prop("checked",true);
        } 
    }
    
    $(function() {
        init();
        $('#expandAll1').on('click','.layui-form-switch',function(){
            expandAll();
        });
       profitType();
       $('#profitType').on('change', function () {
           profitType();
       });
       portfolioTypeChange();
       $('#portfolioType').on('change', function () {
    	   portfolioTypeChange();
       });
        // 按钮事件
        $('#PtlManagementInfo002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#PtlManagementInfo002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });
}());