(function() {
    var lastResult;
    var currentPage = 0;
    
    function searchChange(_this) {
        $("#homeContainer").empty();
        $("#positonDiv").empty();
        $("#dataListDiv").empty();
        var v = $(_this).val();
        if(v){
            Joyin.ajax("/invest/entrust/home/Home/getCheckedConfigList", {id: v},
                function(result){
                    let v = JSON.parse(result.data);
                    if(!v){
                        return;
                    }

                    let data = {};
                    data.pageList = v.all;
                    data.layoutStyle = v.checked;
                    data.pathUrl = v.pathUrl;

                    if(!data.pageList){
                        JoyinUtil.showMessage($(_this).val() + "动态配置数据有误!", "error");
                    }
                    lastResult = data;
                    homeViewSetting(data);
                });
        }
    }
    
    // 获取配置数据与拖拽数据
    var getAndInitView = function(callback){
        if(lastResult){
            callback(lastResult);
        }
    };
    
    /**
     * 根据配置数据加载编辑信息以及数据来源
     * @param result
     */
    var homeViewSetting = function(result){
        var layoutList;
        var pageList;
        if(result && result.layoutStyle){
            // layoutStyle: "{\"settingSaveIds\":[\"2\",\"1\"],\"pageList\":[\"SysUser001\",\"PbcPrdAssetDebtSubPreview\",\"SvreportHome002\"]}"
            
            // 层级关系
            // \"settingSaveIds\":[\"2\",\"1\"]
            layoutList = result.layoutStyle.settingSaveIds;
            
            // 主页面展示所拖
            // \"pageList\":[\"SysUser001\",\"PbcPrdAssetDebtSubPreview\",\"SvreportHome002\"]
            pageList = result.layoutStyle.pageList;
        }
        else{
            result.layoutStyle = [
                {
                    settingSaveIds:[],
                    pageList:[]
                }
            ]
        }
        
        // 所有list
        var allPageList = result.pageList;
        $("#myHomeSetting").show();
        
        if (layoutList) { // 渲染主页面层级框架
            $.each(layoutList,function(i, layoutType){
                var layout = $($('#layout' + layoutType).data('layout'));
                layout = newLayout(layout);
                $("#positonDiv").append(layout);
            });
        }
        
        // 主页面框架渲染具体拖拽
        $("#positonDiv").children('.layui-row').children('.dataDiv').each(function(i,v){
            if(pageList && pageList.length > i && pageList[i] != null){
                for(var j = 0; j < allPageList.length; j++){
                    if(allPageList[j] != null && _.indexOf(pageList[i].split(','),allPageList[j].id) >= 0){
                        var page = newLayoutPage(allPageList[j]);
                        page.css('min-height',page.data('height'));
                        page.addClass("cloned");
                        $(v).append(page);
                    }
                }
            }
        });
        
        // 数据右侧列表
        $.each(allPageList, function(i, v) {
            $("#dataListDiv").append(newLayoutPage(v));
        });
        
    };
    
    // 主页面拖拽页面
    var newLayoutPage = function(page){
        var height = page.minHeight;
        if(height == null){
            height = '200px';
        }
        divStr = "<div id='" + page.id + "' class='dataDiv' draggable='true'>" +
            "<i class='layui-icon'>&#x1006;</i>" +
            "<p>" + page.name + "</p></div>";
        var pageObj = $(divStr);
        pageObj.unbind('drag');
        pageObj.bind('drag', drag);
        pageObj.data('type','page');
        pageObj.data('height',height);
        pageObj.children().data('type','page');
        pageObj.children('i').on('click', function(){
            $("#"+pageObj[0].id).filter(function(){return $(this).hasClass("cloned")}).remove();
        });
        return pageObj;
    };

    var newLayout = function(layout){
        layout[0].id = 'layout_' + maxLayoutId++;
        layout.data('type','frame');
        layout.children().data('type','frame');
        layout.unbind('drag').bind('drag', drag);
        layout.children('i').unbind('click').bind('click', function(){
            layout.children('.dataDiv').find('i').trigger('click');
            layout.remove();
        });
        return layout;
    };

    var registFunctions = function(){
        $("#layoutListDiv").children().children('div').unbind('drag').bind('drag', drag);
        $("#positonDiv").unbind('drop').bind('drop', dropLayout).unbind('dragover').bind('dragover', allowDrop);
    };

    // 获取已拖拽数据保存使用
    var serializeLayout = function(){
        layoutList=[];
        pageList=[];
        $("#positonDiv").children('.layui-row').each(function(i,v){
            layoutList.push($(v).data('layouttype'));
        });
        $("#positonDiv").children('.layui-row').children('.dataDiv').each(function(i,v){
            var page = $(v).children('.dataDiv');
            if(page.length > 0){
                pageList.push(_.toArray(page.map(function(i,v){return v.id;})).join(','));
                return;
            }
            pageList.push(null);
        });
        return {
            layoutList: layoutList,
            pageList: pageList
        };
    };

    // 注册拖动方法
    var allowDrop = function(ev) {
        ev.preventDefault();
    };
    var divTransferData;
    var maxLayoutId = 0;
    var drag = function(ev) {
        divTransferData = ev.target.id;
    };
    var swapPosition = function(a, b){
        var $temobj1 = $("<div></div>");
        var $temobj2 = $("<div></div>");
        $temobj1.insertBefore(a);
        $temobj2.insertBefore(b);
        a.insertAfter($temobj2);
        b.insertAfter($temobj1);
        $temobj1.remove();
        $temobj2.remove();
    };
    var dropLayout = function(ev) {
        ev.preventDefault();
        ev.stopPropagation();
        var target = $(ev.target);
        var data = divTransferData;
        var layout = $($('#' + data).data('layout'));
        var isNew = false;
        if(layout.length == 0){
            layout = $('#' + data).filter(function(){return $(this).hasClass("cloned")});
            if(layout.length == 0){
                layout = $('#' + data);
            }
        }
        else{
            layout = newLayout(layout);
            isNew = true;
        }
        if(layout.data('type') == 'frame'){
            if(target.data('type') == 'page'){
                while(!target.hasClass('layui-row')) target = target.parent();
            }
            if(target.data('type') == 'frame'){
                while(!target.hasClass('layui-row')) target = target.parent();
                if(isNew){
                    target.after(layout);
                }
                else{
                    while(!layout.hasClass('layui-row')) layout = layout.parent();
                    swapPosition(target,layout);
                }
            }
            else{
                if (target.hasClass('dataDiv')) {
                    var targetParent = ev.target.parentElement;
                    while(!$(targetParent).hasClass('layui-row')) targetParent = ev.target.parentElement;
                    $(targetParent).replaceWith(layout);
                } else {
                    target.append(layout);
                }
            }
        }
        else{
            while(!layout.hasClass('dataDiv')) layout = layout.parent();
            var layoutInList;
            if(!layout.hasClass("cloned")){
                layoutInList = layout;
                layout = layout.clone(true);
                layout.css('min-height',layout.data('height'));
                layout.addClass("cloned");
            }
            if(target.data('type') == 'frame' && !target.hasClass('layui-row')){
                if (target.hasClass('dataDiv') >= 0) {
                    target.append(layout);
                    if(layoutInList){
                        $("#dataListDiv").append(layoutInList);
                    }
                }
            }
            else if(target.data('type') == 'page'){
                while(!target.hasClass('dataDiv')) target = target.parent();
                target.parent().append(layout);
                if(layoutInList){
                    $("#dataListDiv").append(layoutInList);
                }
            }
        }
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        getAndInitView(homeViewSetting); 

        registFunctions();

        // 保存按钮事件
        $("#settingSave").click(function(){
            var config = serializeLayout();
            var data = {};
            data.settingSaveIds = config.layoutList;
            data.pageList = config.pageList;
            var dynId = $("#search").val();
            
            if(!dynId){
                JoyinUtil.showMessage("请选择数据源!", "info");
                return;
            }
            if(config){
                let url = basePath + 'invest/entrust/EtrCommonController/updateByParam';
                let param = {"dynId":dynId, "checked":data}
                Joyin.ajaxJson(url, param, function (datajson) {
                    if(datajson === 1){
                        JoyinUtil.showMessage("保存成功!")
                    }else{
                        JoyinUtil.showMessage("保存失败!")
                    }
                    
                });
            }
            
        });
        
        // 管理按钮事件
        $("#settingManage").click(function(){
            // 打开主页管理页面
            Joyin.dialog(JoyinUtil.getLabel("settingManager"), "/tams/main/settingManager/init", "50%", "70%", function(result){
                window.location.reload();
            });
        });
        
        // 分享按钮事件
        $("#settingShare").click(function(){
            // 打开主页管理页面
            Joyin.dialog(JoyinUtil.getLabel("settingShare"), "/tams/main/settingShare/init", "90%", "90%", function(result){});
        });
        
        // 预览按钮事件
        $("#settingPreview").click(function(){
            Joyin.dialog( "预览", $('#search').select2('data')[0].object + "/init", "100%", "100%", function(result){});
        });
        //返回按钮事件
        $("#settingReturn").click(function () {
            window.location.reload();
        });
        // 编辑按钮事件
        $("#settingEdit").click(function(){
            getAndInitView(homeViewSetting);
        });
        // 新建
        $("#settingCreate").click(function(){
            currentPage = lastResult.layoutStyle.length;
            lastResult.layoutStyle.push({
                settingSaveIds:[],
                pageList:[]
            });
            getAndInitView(homeViewSetting);
        });

        // 选择联想框
        $("#search").change(function () {
            // 重新加载数据来源
            searchChange(this);
        });

    });

}());