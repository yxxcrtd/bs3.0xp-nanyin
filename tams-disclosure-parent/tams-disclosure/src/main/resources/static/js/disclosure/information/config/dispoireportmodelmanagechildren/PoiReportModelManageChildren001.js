$(function(){
    //设置模板的调用参数
    var modelId;
    var modelUri;
    var repId;
    var repName;
    var proName;

    var modelListId;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            // 子表名称
            subName : $('#PoiReportModelManageChildren001_subName').val(),
            // 模板编号
            tabNum : $('#tabNum').val()== null ? null : $('#tabNum').val().join(","),
            // 模板类型
            subType : $('#subType').val()== null ? null : $('#subType').val().join(","),
            // 表格类型
            subFormType : $('#subFormType').val()== null ? null : $('#subFormType').val().join(","),
            // 表格样式
            subTmpType : $('#subTmpType').val()== null ? null : $('#subTmpType').val().join(","),
            // 图表类型
            subChartType : $('#subChartType').val()== null ? null : $('#subChartType').val().join(","),
            // 有效状态
            status : $('#status').val(),
        }
    };

    var table01 = $('#PoiReportModelManageChildren001_poiReportModelManageChildren001').joyintable({
        ajax : {
            url : 'searchPage',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("PoiReportModelManageChildren001.reference"),
                JoyinUtil.getRealPath("disclosure/information/config/dispoireportmodelmanagechildren/PoiReportModelManageChildren002/init"),
                "98%", "95%", data,
                function(){table.reload();});
        },
        cols : [
            { title: "子表编号",width:150 , field: 'id',sortable: false},
            { title: "子表名称",width:150 , field: 'subName',sortable: false},
            { title: "模板名称",width:150 , field: 'tempName',sortable: false},
            { title: "模板类型",width:150 , field: 'subTypeStr',sortable: false},
            { title: "表格类型",width:150 , field: 'subFormTypeStr',sortable: false},
            { title: "表格样式",width:150 , field: 'subTmpTypeStr',sortable: false},
            { title: "图表类型",width:150 , field: 'subChartTypeStr',sortable: false},
            { title: "默认反馈",width:150 , field: 'subDefualt',sortable: false},
            { title: "有效状态",width:150 , field: 'statusStr',sortable: false},
            { title: "图表配置",width:300,  field: 'subChartConf',sortable:false},
            { field: 'status',visible:false},
            { field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });

    /**
     * 编辑按钮事件
     */
    table01.bind('click', '.edit', function(data, index) {
    	data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = "edit";
//        Joyin.ajaxJson(basePath + 'disclosure/information/config/dispoireportmodelmanagechildren/PoiReportModelManageChildren001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("PoiReportModelManageChildren002.edit"),
                basePath + "disclosure/information/config/dispoireportmodelmanagechildren/PoiReportModelManageChildren002/init",
                "98%", "95%", data, function() {
                    table01.reload();
                });
//        });
    });
    /**
     * 行内删除按钮事件
     */
    table01.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        deleteList(object);
    });

    var deleteList = function(param){
    	var table = getTable();
//      Joyin.ajaxJson(basePath + 'disclosure/information/config/dispoireportmodelmanagechildren/PoiReportModelManageChildren001/check', param, function(result) {
          Joyin.confirm(JoyinUtil.getMessage("REPORT_COMMON_C_0003",JoyinUtil.getLabel("SysCommon.delete")), function(){
              Joyin.ajaxJson(basePath + 'disclosure/information/config/dispoireportmodelmanagechildren/PoiReportModelManageChildren001/delete', param, function() {
                  table.load();
                  layui.layer.closeAll();
              });
          });
//      });
  }

    //展示报表信息
    var showDetail = function(params){
        displayMenu();
        //var params = getSearchParams();

        if(modelListId){
            Joyin.alert('报表集合不能查询!',2);
            return;
        }else if(modelId&&modelUri){
            if(!params){
                Joyin.alert('参数不能为空！',2);
                return;
            }
        }else{
            return;
        }
        if(params == "flag") {
            return;
        }

        $("#showtable").append("加载中");
        var $container = $("#showtable");

        var toStr = 'src='+basePath+'image'
        var changeStr= 'src="image'
        if(params&&params.length==0){
            Joyin.ajax(basePath +'disclosure/information/reveal/detailshow/RepDetailsShow001/showDetailHtmlNew', {uri:modelId}, function(result) {
                var data =result.replace(new RegExp(changeStr, "g"), toStr);
                data =data.replace(new RegExp('.png', "g"), '.png&v='+Math.random());
                $container.html(data);
            });
        }else{
            params['uri'] = modelId;
            Joyin.ajax(basePath +'disclosure/information/reveal/detailshow/RepDetailsShow001/showDetailHtmlNew', params, function(result) {
                var data =result.replace(new RegExp(changeStr, "g"), toStr);
                data =data.replace(new RegExp('.png', "g"), '.png&v='+Math.random());
                $container.html(data);
            });
        }
    }

    //判断字符是否为空的方法
    function isEmpty(obj) {
        if (typeof obj == "undefined" || obj == null || obj == "") {
            return true;
        } else {
            return false;
        }
    }

    // 判断对应table
    function getTable(){
        return table01;
    }

    var init = function() {
        layui.use('element', function() {
            var $ = layui.jquery, element = layui.element;
            //触发事件
            var active = {
                tabDelete:function(id){
                    element.tabDelete('contentnav',id);
                },
            };
            active.tabDelete($(this).attr("id"));
        });
    };

    init();
    // 防止死循环,文件类型下拉框联动tab页
    var isChangeSelect = true;
    var isChangeTab = true;
    layui.use('element', function() {
        var $ = layui.jquery, element = layui.element;
        element.on('tab(tabList)', function(data){
            var assett = $(".layui-tab-title .layui-this").attr("lay-id");
            // 主页面
            if (assett == "01"){
                assett = "";
            }
            if (isChangeSelect){
                isChangeSelect = true;
                isChangeTab = false;
                $("#reportType").val(assett).trigger('change');
            } else {
                isChangeSelect = true;
                isChangeTab = true;
            }
            var table = getTable();
            table.reload();
        });
        //触发事件
        var active = {
            tabAdd:function(url,id,name){
                element.tabAdd('contentnav',{
                    title:name,
                    content:'<iframe data-frameid="'+id+'" scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;"></iframe>',
                    id:id
                });
                rightMenu();
                iframeWH();
            },
            tabDelete:function(id){
                element.tabDelete('contentnav',id);
            },
            tabChange : function() {
                //切换到指定Tab项
                var reportTypes = $('#reportType').val();
                // 主页面
                if (reportTypes == ''){
                    reportTypes = '01';
                }
                element.tabChange('tabList', reportTypes); //切换到指定页面
            },
        };
        $('#reportType').on('change', function() {
            var othis = $(this), type = 'tabChange';
            if (isChangeTab){
                isChangeSelect = false;
                isChangeTab = true;
                active[type] ? active[type].call(this, othis) : '';
            }  else {
                isChangeSelect = true;
                isChangeTab = true;
            }
            var table = getTable();
            table.reload();
        });
    });

    // 查询按钮绑定事件
    $('#PoiReportModelManageChildren001_btn_search').click(function() {
        var table = getTable();
        if (table != null){
            table.load();
        }
    });

    //新建按钮绑定事件
    $('#PoiReportModelManageChildren001_btn_add').click(function() {
        var table = getTable();
        Joyin.dialog(JoyinUtil.getLabel("新建"),
            JoyinUtil.getRealPath("/disclosure/information/config/dispoireportmodelmanagechildren/PoiReportModelManageChildren002/init"),
            "98%", "95%", {pageModel:PAGE_MODEL_NEW},
            function(data){
                table.reload();
                table01.reload();
            });
    });

    //删除按钮绑定事件
    $('#PoiReportModelManageChildren001_btn_delete').click(function() {
        var object = {};
        var table = getTable();
        var rowArr = table.getAllCheckedRows();// 所有选中行数据
        if(rowArr == null || rowArr.length == 0){
            Joyin.error(JoyinUtil.getMessage('REPORT_COMMON_E_0017'));
            return;
        }
        for(var i=0;i<rowArr.length;i++){
            if(rowArr[i].status != "E"){
                Joyin.error("请选择有效状态的数据！");
                return;
            }
            if(rowArr[i].status == "D"){
                Joyin.error("删除状态的数据不允许删除！");
                return;
            }
        }
        var object = {selectedItem: rowArr, operateType:"delete"};
        deleteList(object);
    });

    //树单击事件
    var zTreeOnClick = function(event, treeId, treeNode, clickFlag){
        clean();
        //如果是第一级节点.不查询{
        var parms = {
            repId:'CPSMS_001',
            repName:'产品说明书(货币型)'
        };
        Joyin.ajax(basePath +'disclosure/information/reveal/detailshow/RepDetailsShow001/getRepsearch', parms, function(data) {
            if(data){
                modelId = data.templateId;
                modelUri = data.templatePath;
                repId = data.repId;
                repName = data.reportName;
                showDetail(new Array());
                // showSearch(data.repScreenList);
            }else{
                $("#showtable").html('<h2>无报表内容</h2>');
            }

        });
    }

    //初始化界面
    function clean(){
        modelListId = null;
        modelId =null;
        modelUri = null;
        repId = null;
        repName = null;
        proName = null;
        ids = new Array();
        $("#showtable").html('');
    }

    var submit = function(type){
        var table = getTable();
        var rowArr = table.getAllCheckedRows();// 所有选中行数据
        if(rowArr == null || rowArr.length == 0){
            Joyin.error(JoyinUtil.getMessage('REPORT_COMMON_E_0017'));
            return;
        }
        if(rowArr != null && rowArr.length != 1){
            Joyin.error("目前仅支持单产品文件生成！");
            return;
        }
        for(var i=0;i<rowArr.length;i++){
            if(rowArr[i].dataStatus != "E"){
                Joyin.error("请选择有效状态的数据！");
                return;
            }
        }
        displayMenu();
        var params = getSearchParams();
        if((modelId&&modelUri)||modelListId){
            if(!params){
                Joyin.alert('参数不能为空！',2);
                return;
            }
        }else{
            return;
        }
        var uuid = 'pageid' + randomString(10, '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ');
        params.rep_id=uuid;
        //导出会报错所以暂时屏蔽name
        delete params.portname;
        //报表数据结构
        if(modelId&&modelUri){
            var report_info = {
                uri:modelId,
                folderUri:modelUri,
                downloadName:'report',//希望下载的名字：供exportReport使用
                formatType:type,//下载的格式:供exportReport使用, 支持的格式有pdf,html,xls,xlsx,rtf,csv,xml,docx,odt,ods,jrprint
                params:params//运行报表所使用的参数
            }
            //运行并产生报表到页面
            var url = exportReportPath(report_info);

            if(url){
                params['url'] = url;
                params['fileType'] = type;
                params['modelId'] = modelId;
                params['repId'] = repId;
                params['repName'] = repName;
                params['prodName'] = proName;
                $(rowArr).each(function (i, item) {
                    //对集合操作
                    params['portnum'] = item.prodId;
                });
                Joyin.ajax(basePath +'disclosure/information/reveal/detailshow/RepDetailsShow001/querySameCount',params,function(){
                    params['operateType'] = "01";
                    Joyin.ajax(basePath +'disclosure/information/reveal/detailshow/RepDetailsShow001/downFileAndSubmit',
                        params,
                        function(data) {
                        });
                });
            }
        }else if(modelListId){
            if(type=='docx'){
                layer.load();
                params['fileType'] = type;
                params['repId'] = modelListId;
                params['prodName'] = proName;
                params['repName'] = repName;
                $(rowArr).each(function (i, item) {
                    //对集合操作
                    params['portnum'] = item.prodId;
                });

                Joyin.ajax(basePath +'disclosure/information/reveal/detailshow/RepDetailsShow001/downFileListAndSubmit',
                    params,
                    function(data) {
                        layer.closeAll('loading');
                    });
            }else{
                Joyin.alert('目前仅支持word的集合生成！');
                return;
            }
        }
    }

    function displayMenu(){
        $("#toSubmit").css('display','none');
        $("#toExport").css('display','none');
    }

    /*生成随机字符串  add by kongzhifang*/
    function randomString(length, chars) {
        var result = '';
        for (var i = length; i > 0; --i) result += chars[Math.floor(Math.random() * chars.length)];
        return result;
    }

    $('#wordExport').click(function(){
        exportfile("docx");
    });
    $('#pdfExport').click(function(){
        exportfile("pdf");
    });

    $('#wordSubimt').click(function(){
        submit("docx");
    });
    $('#pdfSubimt').click(function(){
        submit("pdf");
    });
    // 绑定查询按钮事件
    $('#showDetail').click(function() {
        showDetail(getSearchParams());
    });

    //导出文件
    $('#PoiReportModelManageChildren001_btn_export').click(function() {
        $('#toSubmit').css('display','none');
        setTimeout(function(){
            displayMenu();
        },5000);
    });
    //生成文件
    $('#PoiReportModelManageChildren001_btn_generate').click(function(){
        $("#toExport").css('display','none');
        setTimeout(function(){
            displayMenu();
        },5000);
    });

    /**
     * 画面加载完成
     */
    $(function(){
        // 绑定查询按钮事件
        $('#showDetail').click(function() {
            showDetail(getSearchParams());
        });
        zTreeOnClick();
    });

});