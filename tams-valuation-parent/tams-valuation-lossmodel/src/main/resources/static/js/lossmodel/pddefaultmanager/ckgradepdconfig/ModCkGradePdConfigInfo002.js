(function() {

    layui.use('laydate', function(){
        var laydate = layui.laydate;
        $(this).removeAttr("lay-key");
        //执行一个laydate实例
        laydate.render({
            elem: '#vouchYear',//指定元素
            type: 'year'
        });
    });

    /**
     * 画面加载完成
     */
    $(function() {
        init();
        // 绑定按钮事件
        $('#ModCkGradePdConfig002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        // 绑定按钮事件
        $('#ModCkGradePdConfig002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
    });

	/**
     * 获取表单参数
     */
    var submitForm = $.serializeToObject('#submitForm');

    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        if (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT) {
            object.seqNo = $('#seqNo').val();

        }
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/check', object, function(result) {
            if(result.data.indexOf("影响历史")>0){
                Joyin.confirm("修改后需手工跑历史批处理！", function () {
                    if (operateType === 'save') {
                        WorkFlow.doSaveList(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/save', object, "ModCkGradePdConfig002_btn_save");
                    } else if(operateType === 'submit'){
                        WorkFlow.doSubmitList(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/save', object, "ModCkGradePdConfig002_btn_submit");
                    }
                });
            }else{
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/save', object, "ModCkGradePdConfig002_btn_save");
                } else if(operateType === 'submit'){
                    WorkFlow.doSubmitList(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/save', object, "ModCkGradePdConfig002_btn_submit");
                }
            }
        }); 	
    };
    
    function　init(){

        //初始化评级结果
        // initGradeResult();
        var operateTypeTh = $('#operateType').val();
        /*alert(pageModel+"================="+operateTypeTh);*/
        //编辑模式资产代码、估值日期、资产简称不可编辑
        //operateTypeTh != undefined
        if (pageModel == PAGE_MODEL_EDIT ) {
            $('#vouchYear').attr("disabled", true);
            $('#gradeResult').attr("disabled", true);
        }
        if(operateTypeTh=="submit"){
            $('#vouchYear').attr("disabled", false);
            $('#gradeResult').attr("disabled", false);
        }
    }

    // function initGradeResult() {
    //     var gr = $('#gradeResult').val();
    //     var itemFwd = [];
    //     var inArr =['AAA+','AAA','AAA-','AA+','AA','AA-','A+','A','A-','BBB+','BBB','BBB-','BB','B','CCC','CC','C','D'];
    //     var TEMP_MAIN_GRADE_RESULT = JSON.parse(MODEL_STANDARD_GRADE_RESULT);
    //     for ( var i in TEMP_MAIN_GRADE_RESULT) {
    //         if (inArr.indexOf(TEMP_MAIN_GRADE_RESULT[i].text) >-1) {
    //             itemFwd.push(TEMP_MAIN_GRADE_RESULT[i].id);
    //         }
    //     }
    //     $('#gradeResult').updateChosenArr({
    //         item : itemFwd,
    //         disabled : false
    //     });
    //     $('#gradeResult').val(gr);
    // };



/*    // 评级机构onchange事件
    $("#gradeOrgId").on('change',function(){
        gradeOrgIdChange();
    });


    // 评级机构事件
    var gradeOrgIdChange = function(){
        var inArr =['AAA+','AAA','AAA-','AA+','AA','AA-','A+','A','A-','BBB+','BBB','BBB-','BB','B','CCC','CC','C','D'];
        var outArr =['AAA+','AAA','AAA-','AA+','AA','AA-','A+','A','A-','A-1','A-2','A-3','BBB+','BBB','BBB-','BB+','BB','BB-','B+','B','B-','CCC+','CCC','CCC-','CC+','CC','CC-','C+','C','C-','D'];
        var TEMP_MAIN_GRADE_RESULT = JSON.parse(MAIN_GRADE_RESULT);
        if ($("#gradeOrgId").val().startsWith("98")) {
            for ( var i in TEMP_MAIN_GRADE_RESULT) {
                if (inArr.indexOf(TEMP_MAIN_GRADE_RESULT[i].text) >-1) {
                    itemFwd.push(TEMP_MAIN_GRADE_RESULT[i].id);
                }
            }
            $('#gradeResult').updateChosenArr({
                item : itemFwd,
                disabled : false
            });

        }else if(!($("#gradeOrgId").val().startsWith("98"))){
            var itemFwd = [];
            for ( var i in TEMP_MAIN_GRADE_RESULT) {
                /!*if (!TEMP_MAIN_GRADE_RESULT.hasOwnProperty(i))
                    continue;*!/
                if (outArr.indexOf(TEMP_MAIN_GRADE_RESULT[i].text) >-1) {
                    itemFwd.push(TEMP_MAIN_GRADE_RESULT[i].id);
                }
            }
            $('#gradeResult').updateChosenArr({
                item : itemFwd,
                disabled : false
            });
        }
    }*/


}());