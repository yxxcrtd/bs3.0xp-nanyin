$(function () {

    // 绑定按钮事件
    $("#vouchYear").click(function(event){
        event.stopPropagation();// 阻止事件冒泡
    });
    showYear();

    function showYear() {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#vouchYear',
            type: 'year',
            format: 'yyyy'
        });
    }


    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            //add by zyk 20210120
            vouchYear:$("#vouchYear").val(),
            gradeOrgId: $('#gradeOrgId').val(),
            oType: $('#oType').val()
        }
    };
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        /*双击查看*/
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("评级PD维护详情"),
                JoyinUtil.getRealPath("model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/init"),
                "98%", "95%", data,
                function(){table.reload();});
        },
        cols: [
            {field: 'seqNo', visible: false}
            ,{field:'vouchYear',  title: '年份'}
           /* ,{field:'gradeOrgName', title: '评级机构', render: function (data, type, row, meta) {
                    if(row.level == 1) {
                        return '<strong>' + data + '</strong>';
                    }
                    return data;
                }}
            ,{field:'oTypeName',  title: '评级类型'}*/
            ,{field:'gradeOrgName', digit: 4,   title: '评级结果',sortField:'gradeResult'}
            ,{field:'forwardYearPd' ,width:250,type:"number",  title: '前瞻前年度边际PD%'}
            ,{field:'operation', width:200,title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
      });

    var exportExcel = function() {
        Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig001/check', getPageParams(), function(result) {
            Joyin.exportExcel('export', getPageParams());
        });
        
    };


    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询按钮事件
        $('#ModCkGradePdConfig001_btn_search').click(function() {
            table.load();
        });

        //绑定新增按钮事件
        $('#ModCkGradePdConfig001_btn_add').click(function() {
            showDialog();
        });
        //绑定导出按钮事件
        $('#ModCkGradePdConfig001_btn_export').click(function() {
            exportExcel();
        });




        /*$('#booksetId').change(function(){
            gradeChange();
        });*/
    });

    var gradeChange=function(){
        var booksetId=$.trim($('#booksetId').val());
        if(booksetId==''||booksetId==null){
            return;
        }else{
            $.ajax({
                url : 'checkBokkSetDate',
                type : "POST",
                data : {booksetId:booksetId},
                success: function(msg){
                    if(msg=='-1'){
                        $('#booksetId').val('').trigger('change');
                        layer.alert('所选账套的会计日期不一致，请分开执行!!');
                    }else{
                        $('#valDate').val(msg);
                        bookSetDate=msg;
                    }
                },
                failure : function() {
                }
            });
        }
    }




    /**
     * 导出
     */
    var exportExcel = function(){
        Joyin.exportExcel(basePath+"model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig001/export", getPageParams());
    };

    /**
     * 新建
     */
    function showDialog() {
        Joyin.dialog('新建评级PD维护信息', basePath +'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/init', '45%', '75%',function(){table.reload();});
    }

    /**
     * 批量删除
     */
    $('#ModCkGradePdConfig001_btn_del').click(function() {
        delRows();
    });


    //导入按钮点击事件
    $('#ModCkGradePdConfig001_btn_import').click(function() {

            Joyin.uploadFile(function(fileName) {
                Joyin.ajax(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig001/checkGradePdImportData'
                    ,{fileName: fileName}, function(result) {
                      if(result.data != undefined && result.data.indexOf("数据如下") > 0){
                          var messages ="存在重复数据，是否覆盖？"+result.data;
                          Joyin.confirmMulti(messages, ['是', '否'], function () {
                              Joyin.ajax(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig001/importFile', {
                                  fileName: fileName, type: 1
                              });
                          },function () {
                              Joyin.ajax(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig001/importFile', {
                                  fileName: fileName, type: 0
                              });
                          });
                      }else{
                          Joyin.ajax(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig001/importFile', {
                              fileName: fileName, type: 0
                          });
                      }




                        /*var messages ="存在重复数据，是否覆盖？"+result.data;
                        Joyin.confirmMulti(messages, ['是', '否'], function () {
                            console.log('确定');
                        }, function () {
                            console.log('取消');
                        });*/

                });
            },function() {
                table.reload();
            });

    });


    table.bind('click', '.edit', function (data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/check', data, function (result) {
            Joyin.dialog('评级PD维护编辑',
                basePath + "model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/init",
                "45%", "75%", {
                    pageModel: PAGE_MODEL_EDIT,
                    seqNo: data.seqNo
                }, function () {
                    table.reload();
                });
        });
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete",seqNo: data.seqNo};
        Joyin.ajaxJson(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig001/deleteCheck', object, function(result) {
            if(result.data != undefined &&  result.data.indexOf("已被使用")>0){
                    Joyin.confirm("重新跑批后影响历史减值结果，是否确认删除？", function(){
                        WorkFlow.doDeleteList(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/save', object, "ModCkGradePdConfig001_btn_delete", "ModCkGradePdConfig002",function(){
                            table.reload();
                        });
                    });
            }else{
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/save', object, "ModCkGradePdConfig001_btn_delete", "ModCkGradePdConfig002",function(){
                        table.reload();
                    });
                });
            }

        });
    });

    var delRows = function () {
        if(table!=null && table!=''){
            var selArr = table.getAllCheckedRows();
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var object = {selectedItem: selArr, operateType:"delete"};
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            WorkFlow.doDeleteList('/model/impairment/pddefaultmanager/ckgradepdconfig/ModCkGradePdConfig002/save', object, "ModCkGradePdConfig001_btn_delete", "ModCkGradePdConfig002", function(){
                table.reload();//删除事件请求及回调函数
            });
        });

    };


});
