$(function() {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            ptlId:$('#ptlId').val(),
            valTableFileName:$("#valTableFileName").val(),
            valDate:$('#valDate').val(),
            structMapId:$('#structMapId').val(),
            sumsubMapId:$('#sumsubMapId').val(),
            assetSubMapId:$('#assetSubMapId').val(),
            subjectCodeCol:$('#subjectCodeCol').val(),
            startRow:$('#startRow').val(),
            trimChars:$('#trimChars').val(),
            filterId:$('#filterId').val()
            };
        };
    /**
     * 定义表头
     */
    var cols = [
                {field:'subCode',  title: 'RskValTableImport001.subCode',width:200,sortable: false}
               ,{field:'subName',  title: 'RskValTableImport001.subName',width:200,sortable: false}
               ,{field:'securityQuantity',  title: 'RskValTableImport001.securityQuantity',width:150,type:'number',sortable: false}
               ,{field:'unitCost',  title: 'RskValTableImport001.unitCost',type:'number',width:150,sortable: false}
               ,{field:'securityCost',  title: 'RskValTableImport001.securityCost',width:150,type:'number',sortable: false}
               ,{field:'ctnwr',  title: 'RskValTableImport001.ctnwr',width:150,type:'number',digit:6,sortable: false}
               ,{field:'mcp',  title: 'RskValTableImport001.mcp',width:150,type:'number',sortable: false}
               ,{field:'smv',  title: 'RskValTableImport001.smv',width:150,type:'number',sortable: false}
               ,{field:'mcr',  title: 'RskValTableImport001.mcr',width:150,type:'number',digit:6,sortable: false}
               ,{field:'valueAdded',  title: 'RskValTableImport001.valueAdded',width:150,sortable: false}
               ,{field:'suspensionInfo',  title: 'RskValTableImport001.suspensionInfo',width:150,sortable: false}
               ,{field:'equityInfo',  title: 'RskValTableImport001.equityInfo',width:150,sortable: false}
               ,{field:'valType',  title: 'RskValTableImport001.valType', visible : false}
            ];
    /**
     * 定义数据表格
     */
    var table=$("#valTable").joyintable({
            ajax: {
                url: JoyinUtil.getRealPath('invest/risk/valtblimport/RskValTableImport002/initValTableInfo'),
                type: 'POST',//请求类型
                dataType: 'json',
                data: function() {
                    return getSearchParams();//获取请求参数
                }//ajax请求数据
            },
        cols:cols,
        searchOnInit: true,
        loadonce:true,
        checkbox:false,
        done: function(table, allData){
            $('#RskValTableImport002_btn').show();
        }
    });
    
    
    function saveOrSubmit(operateType) {
        if(table!=null && table!=''){
            var selArr = $("#valTable").data('joyintable').table.table.config.data;
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0057"),JoyinUtil.getLabel("RskValTableImport002"));
            return;
        }
        var object = {selectedItem: selArr,ptlId:$('#ptlId').val(),valDate:$('#valDate').val(),operateType:operateType};
        Joyin.ajaxJson(basePath + 'invest/risk/valtblimport/RskValTableImport002/check',object, function(result) {
            if(result.valCount>0){
                var msg = "估值表数据已存在,是否覆盖!";
                Joyin.confirm(msg,function(){
                    finalSubmit(object);
                });
            }else{
                finalSubmit(object);
            }
        })
    };
    
    /**
     * 最终提交
     */
    var finalSubmit = function(object){
        var processIndex = showProcess($('#ptlId').val());
        Joyin.ajaxJson(basePath + 'invest/risk/valtblimport/RskValTableImport002/save',
                object,function(result){
                    setTimeout(function() {
                    layer.close(processIndex);
                    //点击保存后关闭窗口
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }, 1000);
                });
    }

    var  showProcess = function(barId){
        // 弹框进度条
        var processIndex = layer.open({
            type: 1
            ,title: false //不显示标题栏
            ,closeBtn: false
            ,area: '300px;'
            ,shade: 0.6
            ,id: 'JOY_process' //设定一个id，防止重复弹出
            ,moveType: 1 //拖拽模式，0或者1
            ,content: '<div class="layui-progress layui-progress-big" lay-showpercent="true" lay-filter="' + barId + '"><div class="layui-progress-bar" lay-percent="0%">0%</div></div>'
            ,success: function(layero){
                layero.css('background', 'transparent');
                // 开始进度条
                startProgressBar(layero, barId, function() {
                    //layer.close(processIndex);
                });
            }
        });
        return processIndex;
    }

    /**
     * 进度条开始
     * @callback 进度完成后的回调
     */
    function startProgressBar($progerssBar, barId, callback) {
        if($progerssBar && $progerssBar.length > 0) {
            var n = 0, timer = setInterval(function(){
                n = n + Math.random()*10|0;
                if(n>100){
                    n = 100;
                    clearInterval(timer);
                }
                layui.element.progress(barId, n+'%');
                $progerssBar.find('.layui-progress-bar').text(n+'%');
            }, 300+Math.random()*1000);
        }
    }

    $(function(){
        $('#RskValTableImport002_btn_save').click(function() {
            saveOrSubmit('save');
        });
    });
});
