$(function () {

    /**
     * 修改/新建
     */
    var saveOrSubmit = function(operateType) {
        var object = $.serializeToObject('#searchForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.dataSource = '02';
        if(object.preProspectivePd){
            var preProspectivePd = object.preProspectivePd/100;
            preProspectivePd = preProspectivePd.toFixed(6);
            object.preProspectivePd = preProspectivePd;
        }

        if(object.prospectivePd){
            var prospectivePd = object.prospectivePd/100;
            prospectivePd = prospectivePd.toFixed(6);
            object.prospectivePd = prospectivePd;
        }

        if(object.lgd){
            var lgd = object.lgd/100;
            lgd = lgd.toFixed(6);
            object.lgd = lgd;
        }

        Joyin.ajaxJson(basePath + 'model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement002/check', object, function(result) {
            if (result.data || result.messageType === 'info') {
                Joyin.confirm(result.data, function() {
                    doComplete(operateType, object);
                });
            } else {
                doComplete(operateType, object);
            }


        });
    };

    var doComplete = function(operateType, object){
        if(operateType === 'save'){
            WorkFlow.doSaveList(basePath + 'model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement002/save',
                object, "AssetImpairmentKpmgManagement002_btn_save");
        }else{// submit
            WorkFlow.doSubmitList(basePath + 'model/impairment/assetimpairmentkpmgmanagement/AssetImpairmentKpmgManagement002/save',
                object, "AssetImpairmentKpmgManagement002_btn_submit");
        }
    };

    /**
     * 根据操作类型：显示按钮等
     */
    var linkCalculation = function () {
        var preProspectivePd = parseFloat($('#preProspectivePd').val());
        var prospectivePd = parseFloat($('#prospectivePd').val());
        var lgd = parseFloat($('#lgd').val());
        var ead = parseFloat($('#ead').val());
        preProspectivePd = preProspectivePd.toFixed(4);
        prospectivePd = prospectivePd.toFixed(4);
        lgd = lgd.toFixed(4);
        ead = ead.toFixed(4);
        if(preProspectivePd != 'NaN' && prospectivePd != 'NaN' && lgd != 'NaN' && ead != 'NaN'){
            var preProspectiveEcl = preProspectivePd*lgd*ead/100/100;
            var prospectiveEcl = prospectivePd*lgd*ead/100/100;
            $('#preProspectiveEcl').val(preProspectiveEcl.toFixed(2)).trigger('change');
            $('#prospectiveEcl').val(prospectiveEcl.toFixed(2)).trigger('change');
        }else{
            $('#preProspectiveEcl').val('').trigger('change');
            $('#prospectiveEcl').val('').trigger('change');
        }
    };

    /**
     * 计算
     */
    var initShow = function () {
        if (pageModel !== PAGE_MODEL_NEW){
            $("#prodName1").attr("disabled", "disabled");
            $("#assetName1").attr("disabled", "disabled");
            $("#decDate").attr("disabled", "disabled");
        }
        var actionStatus = $('#actionStatus').val();
        if(actionStatus == 'A'){
            $("#prodName1").attr("disabled", false);
            $("#assetName1").attr("disabled", false);
            $("#decDate").attr("disabled", false);
        }
    };

    var star = function () {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function () {
                $(this).remove();
            })
            $('*[required]').each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }

    $(function() {
        // 绑定按钮事件
        $('#AssetImpairmentKpmgManagement002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#AssetImpairmentKpmgManagement002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });

        $('#assetName1').change(function (data) {
            var assetId = $('#assetName1').val();
            var assetName = $.trim($('#assetName1').text());
            if (assetId) {
                $('#assetId').val(assetId).trigger('change');
                var objects = $('#assetName1').select2('data')[0].object;
                /*Joyin.ajaxJson(basePath + 'model/impairment/assetimpairmentmanagement/AssetImpairmentManagement002/getAssetType?params=' + objects,null,
                    function(result) {
                        if(result.assetType){
                            //获取资产类型
                            $('#assetType').val(result.assetType).trigger('change');
                        }else{
                            layer.alert('该资产类型未映射！');
                            $('#assetType').val('').trigger('change');
                        }
                    });*/
                /*var arr = objects.split('/');*/
                $('#assetType').val(objects).trigger('change');
            } else {
                $('#assetId').val('').trigger('change');
            }
            if(assetName){
                $('#assetName').val(assetName).trigger('change');
            }else{
                $('#assetName').val('').trigger('change');
            }
            if(pageModel == PAGE_MODEL_NEW){
                $('#ps').val('').trigger('change');
                $('#tradeDate').val('').trigger('change');
               /* $('#portfolioId').val('').trigger('change');*/
            }
            // 画星星
            star();
        });

        $('#prodName1').change(function (data) {
            var prodId = $('#prodName1').val();
            var prodName = $.trim($('#prodName1').text());
            if (prodId) {
                $('#prodId').val(prodId).trigger('change');
                $('#prodName').val(prodName).trigger('change');
            } else {
                $('#prodId').val('').trigger('change');
                $('#prodName').val('').trigger('change');
            }
            if(pageModel == PAGE_MODEL_NEW){
                $('#ps').val('').trigger('change');
                $('#tradeDate').val('').trigger('change');
                /*$('#portfolioId').val('').trigger('change');*/
            }
            // 画星星
            star();
        });



        $('#decDate').change(function (data) {
            var decDate = $('#decDate').val();
            var sysDate = $('#sysDate').val();
            if (decDate) {
                if(new Date(decDate) < new Date(sysDate)){
                    layer.alert('减值日不能小于平台日！');
                    $('#decDate').val('');
                }
            }
        });

        $("#preProspectivePd").change(function(){
            var preProspectivePd = $('#preProspectivePd').val();
            if(preProspectivePd<0 || preProspectivePd>100){
                layer.alert('[0,100]的数！');
                $('#preProspectivePd').val('');
                return ;
            }
            linkCalculation();
        });

        $("#prospectivePd").change(function(){
            var prospectivePd = $('#prospectivePd').val();
            if(prospectivePd<0 || prospectivePd>100){
                layer.alert('[0,100]的数！');
                $('#prospectivePd').val('');
                return ;
            }
            linkCalculation();
        });

        $("#lgd").change(function(){
            var lgd = $('#lgd').val();
            if(lgd<0 || lgd>100){
                layer.alert('[0,100]的数！');
                $('#lgd').val('');
                return ;
            }
            linkCalculation();
        });

        $("#ead").change(function(){
            var ead = $('#ead').val();
            if(ead<=0){
                layer.alert('大于0的数！');
                $('#ead').val('');
                return ;
            }
            linkCalculation();
        });


        initShow();// 根据操作类型：显示按钮等

    });
});