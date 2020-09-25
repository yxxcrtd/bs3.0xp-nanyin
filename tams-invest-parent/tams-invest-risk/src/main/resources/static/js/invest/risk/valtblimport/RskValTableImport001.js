var valTableFileName = '';
RskValTableImport001 = function(){
        return {
            uploadCallback : function(res){
                valTableFileName = '';
                if(res && res.length > 0){
                    valTableFileName = res[0].fileName;
                 // 为了消除进度条
                    Joyin.ajax(JoyinUtil.getRealPath('invest/risk/valtblimport/RskValTableImport001/uploadFile'));
                }
            }
        }
}();
(function() {
    /**
     * 获取查询参数
     */
    var param = {};
    var getSearchParams = function() {
        return param;
    };
    /**
     * 定义数据表格(指标结构映射)
     */
    var rskvalstructureTable=$("#rskvalstructureTable").joyintable({
        ajax: {
            url: basePath+'invest/risk/penetrationmanagement/rskvalstructure/RskValStructure001/findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        checkbox:false,
        cols:[
             {
                field : 'srcColIndex',visible: false,
                title : 'RskValStructure001.srcColIndex'
            }
            ,{
                field : 'srcColIndexLetter',
                title : 'RskValStructure001.srcColIndex'
            }, {
                field : 'srcColDataTypeStr',
                sortField : "srcColDataType",
                title : 'RskValStructure001.srcColDataType'
            }, {
                field : 'tgtIndexStr',
                sortField : "tgtIndex",
                
                title : 'RskValStructure001.tgtIndex'
            }, {
                field : 'memo',
                title : 'RskValStructure001.memo'
            }
        ]
    });
    
    /**
     * 定义数据表格(资产科目映射)
     */
    var rskValAssetCodeTable=$("#rskValAssetCodeTable").joyintable({
        ajax: {
            url: basePath+'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001/findPageDtl',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        checkbox:false,
        cols:[
             {
            field : 'id',
            visible : false
        }, {
            field : 'srcCode',
            title : 'RskValAssetCode004.srcCode'
        }, {
            field : 'tgtCode',
            title : 'RskValAssetCode004.tgtCode'
        }, {
            field : 'allowSumName',
            title : 'RskValAssetCode004.allowSum'
        }, {
            field : 'memo',
            title : 'RskValAssetCode004.memo'
        }
        ]
    });
    
    /**
     * 定义数据表格(汇总科目映射)
     */
    var rskvalsumnameTable=$("#rskvalsumnameTable").joyintable({
        ajax: {
            url: basePath+'invest/risk/penetrationmanagement/rskvalsumname/RskValSumname001/findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        checkbox:false,
        /**
         * 定义表头
         */
        cols:[
             {
            field : 'sumName',
            title : 'RskValSumname001.sumName'
        }, {
            field : 'sumCodeStr',
            sortField : "sumCode",
            title : 'RskValSumname001.sumCode'
        }, {
            field : 'nextIndexTypeStr',
            sortField : "nextIndexType",
            title : 'RskValSumname001.nextIndexType'
        }
        ]
    });
    
    /**
     * 组合名称change事件
     */
    var reloadTable = function(){
        Joyin.ajaxJson(basePath + 'invest/risk/valtblimport/RskValTableImport001/selectRskValRelateImportById',{ptlId:$('#ptlId').val()}, function(result) {
            param.structMapId = result.structMapId;param.sumsubMapId = result.sumsubMapId;param.assetSubMapId = result.assetSubMapId;
            $('#structMapId').val(result.structMapId);$('#sumsubMapId').val(result.sumsubMapId);$('#assetSubMapId').val(result.assetSubMapId);
            $('#subjectCodeCol').val(result.subjectCodeCol);$('#startRow').val(result.startRow);
            $('#trimChars').val(result.trimChars);
            //--add by 裴家俊20190514---start----
            $('#filterId').val(result.filterId);
            //--add by 裴家俊20190514---end----
            $('div.rsk-dl-wrap').show();
            $('#ddstartRow').text(result.startRow);$('#ddassetCodeColIndex').text(result.subjectCodeColLetter);$('#ddtrimChars').text(result.trimChars);
            //--add by 裴家俊20190514---start----
            $('#ddfilterId').text(result.filterName==null||result.filterName==''?'':result.filterName);
            //--add by 裴家俊20190514---end----
            rskvalstructureTable.table ? rskvalstructureTable.reload() : rskvalstructureTable.load();
            rskValAssetCodeTable.table ? rskValAssetCodeTable.reload() : rskValAssetCodeTable.load();
            rskvalsumnameTable.table ? rskvalsumnameTable.reload() : rskvalsumnameTable.load();
        })
    }
    
    /**
     * 前台check
     */
    var check = function(){
        var rskvalstructureList = rskvalstructureTable.getAllRows();
        var rskValAssetCodeList = rskValAssetCodeTable.getAllRows();
        var rskvalsumnameList = rskvalsumnameTable.getAllRows();
        var msg;var msgArray=[];var messages="";
        if(!rskvalstructureList || rskvalstructureList.length == 0) {
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("RskValStructure001.title"));
            msgArray.push(msg);
        }
        if(!rskValAssetCodeList || rskValAssetCodeList.length == 0) {
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("RskValAssetCode002.title2"));
            msgArray.push(msg);
        }
        if(!rskvalsumnameList || rskvalsumnameList.length == 0) {
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("RskValSumname001.title"));
            msgArray.push(msg);
        }
        if(msgArray&&msgArray.length>0){
            msgArray.forEach(function(value){
                messages = messages + '<span>' +value + '</span>' + '<br>';
            });
        }
        if(messages){
            Joyin.error(messages);
            $('.layui-layer-padding').height('80');
            $('.layui-layer-dialog').height('200');
            return false;
        }else{
            return true;
        }
        /*if(msg) {
            Joyin.error(msg);
            return false;
        }
        return true;*/
    }
    
    /**
     * 获取文件路径名
     */
    var getFileName = function(){
        var fun = $('#valtable').data('getSelectedFiles');
        if(typeof fun === 'function') {
            return fun();
        }
        return [];
    }
    /**
     * 弹出入库画面
     * @returns
     */
    function showDialog() {
        var object = $.serializeToObject('#submitForm');
        object.operateType = "sava";
        object.pageModel = pageModel;
        var fileInfo = getFileName();
        if(typeof fileInfo[0] != 'undefined'){
            object.valTableFileName = fileInfo[0].fileName;
        }
        Joyin.ajaxJson(basePath + 'invest/risk/valtblimport/RskValTableImport001/check',object, function(result) {
            if(check()){
                Joyin.dialog(JoyinUtil.getLabel('估值表预览'), '../RskValTableImport002/init', '95%', '95%'
                        ,object);
            }
        });
        
    }

    /**
     * 画面加载完成
     */
    $(function() {
        $('#ptlId').change(reloadTable);
        // 绑定入库按钮事件
        $('#RskValTableImport001_btn_see').click(showDialog);
    });
}());