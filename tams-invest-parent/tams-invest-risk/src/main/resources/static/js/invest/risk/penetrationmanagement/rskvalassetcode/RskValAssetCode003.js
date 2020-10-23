var valTableFileName = '';
RskValAssetCode003 = function(){
        return {
            uploadCallback : function(res){
                valTableFileName = '';
                if(res && res.length > 0){
                    valTableFileName = res[0].fileName;
                 // 为了消除进度条
                    Joyin.ajax(JoyinUtil.getRealPath('invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode003/uploadFile'));
                }
            }
        }
}();

(function() {
    /**
     * 加载源科目树
     */
    var loadTreeSource = function(){
        var param = {};
        param.fileName = valTableFileName;
        param.assetCodeType = $('#assetCodeType').val();
        param.startRow= $('#startRow').val();
        param.assetCodeColIndexLetter = $('#assetCodeColIndexLetter').val();
        //---add by 裴家俊 20190514-------start-----
        param.filterId = $('#filterId').val();
        //---add by 裴家俊 20190514-------end-----
        Joyin.ajaxJson(basePath + gettreeSourceUrl,param,
        function(result){
            $.fn.zTree.init($("#treeSource"),settingSource,result);
            //筛选源科目代码是否已配置
            fiterSrcCode();
        });
     }
    
    /**
     * 加载目标科目树
     */
    var loadTreeTarget = function(){
        Joyin.ajaxJson(basePath + gettreeTargetUrl,{ },
        function(result){
            $.fn.zTree.init($("#treeTarget"),settingTarget,result);
        });
     }
    
    var fiterSrcCode = function(){
        var list = table.getAllRows();
        var childstr ='' ;var parentstr='';
        if(list){
            var treeSourceObj = $.fn.zTree.getZTreeObj("treeSource");
            $.each(list, function (i, item) {
                var src_code = item.srcCode;
                var node = treeSourceObj.getNodeByParam("id", src_code, null);
                childstr+=","+src_code;
                childstr =getAllChildrenNodes(node,childstr);
                parentstr=getAllParentNodes(node,parentstr);
            });
        }
        refreshInitTree(childstr+parentstr,true);
    }
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {id: $('#id').val()};
    };
    /**
     * 定义数据表格
     */

    var table=$("#dataTable").joyintable({
        ajax: {
            url: basePath +'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001/findPageDtl',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        //page:false,
        searchOnInit: true,
        /**
         * 定义表头
         */
        cols:[
            {field: 'id',visible: false}
            ,{field: 'srcCode', title:'RskValAssetCode004.srcCode',sortField:'SRC_CODE'}
            , {field: 'tgtCode', title: 'RskValAssetCode004.tgtCode',sortField:'TGT_CODE'}
            , {field: 'allowSumName', title: 'RskValAssetCode004.allowSum',sortField:'ALLOW_SUM'}
            , {field: 'memo', title: 'RskValAssetCode004.memo'}
        ]
    });
    
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
    
    var reloadTree = function(){
        var msgArray=[];var errorClassArray = [];var messages="";
        if($('#assetCodeType').val()==''||typeof $('#assetCodeType').val() =='undefined'){
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("RskValAssetCode003.assetCodeType"));
            msgArray.push(msg);errorClassArray.push(assetCodeType);
        }
        if($('#startRow').val()==''||typeof $('#startRow').val() =='undefined'){
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("RskValAssetCode003.startRow"));
            msgArray.push(msg);errorClassArray.push(startRow);
        }
        if($('#assetCodeColIndexLetter').val()==''||typeof $('#assetCodeColIndexLetter').val() =='undefined'){
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("RskValAssetCode003.assetCodeColIndex"));
            msgArray.push(msg);errorClassArray.push(assetCodeColIndexLetter);
        }
        if(typeof getFileName()[0] == 'undefined'){
            msg = JoyinUtil.getMessage('COMMON_E_0057',JoyinUtil.getLabel("RskValAssetCode003.excelTemplate"));
            msgArray.push(msg);errorClassArray.push(valtable); 
        }
        if(msgArray&&msgArray.length>0){
            msgArray.forEach(function(value){
                messages = messages + '<span>' +value + '</span>' + '<br>';
            });
        }
        if(errorClassArray&&errorClassArray.length>0){
            errorClassArray.forEach(function(value){
                var $el = $(value);
                if ($el.is('.input-select')) {
                    $el = $el.find(' ~ .select2').find('.select2-selection');
                }else if ($(value).is('.input-checkbox')) {
                    $el = $el.next('ul.layui-checkbox-u').find('li.layui-chcekbox');
                }
                $el.addClass('errors');
            });
        }
        if(messages){
            Joyin.error(messages);
            $('.layui-layer-padding').height('80');
            $('.layui-layer-dialog').height('200');
            return false;
        }
        //加载源科目树
        loadTreeSource();
        //加载目标科目树
        loadTreeTarget();
    }
    
    /**
     * 弹出添加界面
     */
    var showDialog = function(){
        var object = {};
        var treeSourceObj = $.fn.zTree.getZTreeObj("treeSource");
        var treeTargetObj = $.fn.zTree.getZTreeObj("treeTarget");
        var sourceArr = new Array();var targetArr = new Array();
        if(null!=treeTargetObj){
            var targetNodes = treeTargetObj.getCheckedNodes(true);
            for (var i = 0; i < targetNodes.length; i++) {
                targetArr.push(targetNodes[i].id);
            }
        }

        var sourceNodes = [];
        if(null!=treeSourceObj){
            sourceNodes = treeSourceObj.getCheckedNodes(true);
            for (var i = 0; i < sourceNodes.length; i++) {
                sourceArr.push(sourceNodes[i].id);
            }
        }
        object.srcCode = sourceArr.join(",");object.tgtCode = targetArr.join(",");object.id=$('#id').val();
        object.pageModel = PAGE_MODEL_NEW;
        Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode004/check', object, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("RskValAssetCode004.addTitle"),
                    basePath + "invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode004/init",//打开新界面
                    "70%", "70%", {pageModel:PAGE_MODEL_NEW,//请求的page_model
                                    sourceIds:sourceArr,  //源科目代码
                                    targetIds:targetArr,   //目标科目代码
                                    id:$('#id').val()  //资产科目映射id
                                    },function(){
                                        table.reload();//回调函数，表格刷新
//                                        if(isCommit!=1){
//                                            for (var i = 0; i < sourceNodes.length; i++) {
//                                                 sourceNodes[i].checked = false;
//                                            }
//                                        }
                                        if(isCommit == 1){
                                           refreshAddTree();
                                        }
                                        //reloadTree();
                                    });
        });
    }

    var isCommit = 0;

    var isConfirm = function(){

        isCommit =  1;
    }


    /**
     * 画面加载完成
     */
    $(function() {
        $("#RskValAssetCode003_btn_preview").click(function () {
            $(".rsk-table-left").css("display","block");
            $(".rsk-table-right").css("width","60%");
            //加载源科目树、目标树
            reloadTree();
        });

        //添加
        $('#RskValAssetCode003_btn_add').click(function(){
            isCommit = 0;
            showDialog();
        });

        //删除
        $('#RskValAssetCode003_btn_delete').click(function() {
            var deleteArr = new Array()
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            $.each(selArr,function(index,item){
                deleteArr.push(item.srcCode);
            })
            var object = {selectedItem: selArr, operateType:"delete"};
           /* Joyin.ajaxJson(basePath + 'invest/asset/bond/FinBondTargetAsset001/check', object, function(result) {*/
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    Joyin.ajaxJson(basePath + 'invest/risk/penetrationmanagement/rskvalassetcode/RskValAssetCode001/deleteDtl', object,
                        function(result){
                            refreshDeleteTree(deleteArr.join(","));
                            table.reload();//删除事件请求及回调函数
                        });
                });
           /* });*/
        });
    });

     window.RskValAssetAction = {
            isConfirm: isConfirm
     }
}());