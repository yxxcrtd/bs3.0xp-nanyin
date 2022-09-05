(function() {
	/**
     * 获取表单参数
     */
    var submitForm = $.serializeToObject('#submitForm');

    //画面联动：输入产品名称，带出产品主代码
    var prodNameChange = function() {
        if (!$('#prodName').select2('data') || $('#prodName').select2('data').length == 0 || !$('#prodName').select2('data')[0]) {
            return;
        }
        var finprodId = $('#prodName').select2('data')[0].id;
        $('#prodId').val(finprodId);
    };
	
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'disclosure/information/config/prodpartner/ProdPartner002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'disclosure/information/config/prodpartner/ProdPartner002/save', object, "ProdPartner002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'disclosure/information/config/prodpartner/ProdPartner002/save', object, "ProdPartner002_btn_submit");
                }
            });
        });
    }
    
    function　init(){
        if(pageModel != PAGE_MODEL_NEW){
            $("#prodName").attr("disabled",true);
        }
        // //修改时，如果生效日小于等于平台日，生效日不能修改
        // if($('#effDate').val() != '' && $('#effDate').val() != null){
        //     if($('#effDate').val()<=platDate){
        //         $("#effDate").attr("disabled",true);
        //     }
        // }
        //
        // //修改时，如果生效截止日小于等于平台日，生效日截止不能修改
        // if($('#effEndDate').val() != '' && $('#effEndDate').val() != null){
        //     if($('#effEndDate').val()<=platDate){
        //         $("#effEndDate").attr("disabled",true);
        //     }
        // }
    }
    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#ProdPartner002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ProdPartner002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        //产品名称联动
        $('#prodName').change(function() {
            prodNameChange();
        });

    });
}());