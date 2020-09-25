(function () {
    /**
     * 按钮控件，只是为了给按钮添加icon
     * btn-search 查询
     * btn-export 导出
     * btn-import 导入
     * btn-reset 重置
     * btn-create 新建
     * btn-delete 删除
     * btn-frozen 冻结
     * btn-unfrozen 解冻
     * btn-save 保存
     * btn-submit 提交
     * btn-cancel 取消
     * btn-preview 预览
     * btn-approval 批量审批
     * btn-pwdreset 密码重置
     * btn-detail 管理明细
     * @date 2018-02-01
     * @auth heyangyang
     */
    $.fn.layButton = function () {
        $(this).click(function() {
            Joyin.elementId = $(this).attr('id');
        }).each(function () {
            var self = $(this);
            var html = self.html();
            var span = $('<span></span>').html('&nbsp;&nbsp;' + html);
            var icon = $('<i class="iconfont"></i>');
            if ($('i', self).length > 0) return;
            if (self.hasClass('btn-search')) {
            	if(IS_SHOW_BTN_ICON != "02"){
            		icon.addClass('icon-search');
            	}
            }
            else if (self.hasClass('btn-export')) {
                icon.addClass('icon-export');
            }
            else if(self.hasClass('btn-import')){
                icon.addClass('icon-import');
            }
            else if (self.hasClass('btn-reset')) {
            	if(IS_SHOW_BTN_ICON != "02"){
            		icon.addClass('icon-reset');
            	}
            }
            else if (self.hasClass('btn-create')) {
                icon.addClass('icon-found');
            }
            else if (self.hasClass('btn-config')) {
                icon.addClass('icon-configure');
            }
            else if (self.hasClass('btn-delete')) {
                icon.addClass('icon-delete');
            }
            else if (self.hasClass('btn-frozen')) {
                icon.addClass('icon-lock');
            }
            else if (self.hasClass('btn-unfrozen')) {
                icon.addClass('icon-unlock');
            }
            else if (self.hasClass('btn-save')) {
                icon.addClass('icon-save');
            }
            else if (self.hasClass('btn-submit')) {
                icon.addClass('icon-submit');
            }
            else if (self.hasClass('btn-cancel')) {
                icon.addClass('icon-delete');
            }
            else if (self.hasClass('btn-preview')) {
                icon.addClass('icon-preview');
            }
            else if (self.hasClass('btn-approval')) {
                icon.addClass('icon-approval');
            }
            else if (self.hasClass('btn-pwdreset')) {
                icon.addClass('icon-password-reset');
            }
            else if(self.hasClass('btn-filter')) {
                icon.addClass('icon-btn-filter');
            }
            else if(self.hasClass('btn-top')){
                icon.addClass('icon-top-arrow');
            }
            else if(self.hasClass('btn-down')){
                icon.addClass('icon-down-arrow');
            }
            else if(self.hasClass('btn-downing')){
                icon.addClass('icon-turn-downing');
            }
            else if(self.hasClass('btn-detail')){
                icon.addClass('icon-detail');
            }
            else {
                var i = self.data('icon');
                if (i) {
                    icon = $('<i></i>').addClass(i);
                }
            }
            self.empty().append(icon).append(span);
        });
    };
    
    /**
     * 列表按钮区域
     */
    $.fn.buttonArea = function() {
        $(this).each(function () {
            var self = $(this)
                , filter = self.attr('filter')
                , exp = self.attr('export')
                , position = self.attr('position');
            // 角色权限
            var searchAuth = false, exportAuth = false;
            _.each(purviewElements, function(ele) {
                // 查询权限
               if(ele.indexOf('_search') != -1) {
                   searchAuth = true;
               }
                // 导出权限
               if(ele.indexOf('_export') != -1) {
                   exportAuth = true;
               }
            });
            
            // 添加筛选按钮
            if(filter !== 'false' && searchAuth) {
                var filterBtn = $('<a class="layui-btn layui-btn-sm btn-filter"></a>');
                filterBtn.attr('id', pageId + '_btn_filter')
                    .text(JoyinUtil.getLabel('SysCommon.filter')).prependTo(self);
                // 筛选按钮添加事件
                filterBtn.click(function() {
                    var $form = $(this).parent().next('.joyin-form-wrap');
                    $form.toggleClass('joyin-filter-form');
                    $form.prev().css('float', 'none');
                    if(!$form.hasClass('joyin-filter-form') && $form.is(':hidden')) {
                        $form.removeAttr('style');
                    }
                }).mouseenter(function() {
                    $(this).parent().next('.joyin-form-wrap').show();
                });
                filterBtn.parent().next(".joyin-form-wrap").click(function(event){
                    event=event||window.event;
                    event.stopPropagation();  
                });
            }
            //筛选区域展示控制
            if(FILTER_AREA_SHOW && FILTER_AREA_SHOW == '01'){
                var $form = $(".joyin-form-wrap");
                $form.toggleClass('joyin-filter-form', false);
                $form.prev().css('float', 'none');
            }
            if(exp !== 'false' && exportAuth) {
                // 添加导出按钮
                var exportBtn = $('<a class="layui-btn layui-btn-sm btn-export btn-default"></a>');
                exportBtn.attr('id', pageId + '_btn_export')
                    .text(JoyinUtil.getLabel('SysCommon.export')).appendTo(self);
            }
            if(position === 'right') {
                // 将按钮右对齐
                self.css('text-align', 'right');
            }
        });
    };
    
    /**
     * 列表查询条件区域
     */
    $.fn.conditionArea = function() {
        $(this).on('click', function(e) {
            if(!$(e.target).is('.input-dateRangeSelect') && !$(e.target).is('.input-dateSelect')) {
                $('.layui-laydate').remove();
            }
        }).each(function () {
            var self = $(this)
                , btns = $('<div class="form-search-btns"></div>')
                , line = self.attr('line') || 2
                , items = self.find('.layui-form-item')
                , searchBtn
                , resetBtn;
            // 行数
            if(!isNaN(line)) {
                line = parseInt(line);
            }

            // 角色权限
            var searchAuth = false;
            _.each(purviewElements, function(ele) {
                // 查询权限
               if(ele.indexOf('_search') != -1) {
                   searchAuth = true;
               }
            });
            // 添加查询按钮
            if(items.length >= 1 && searchAuth) {
                searchBtn = $('<a class="layui-btn layui-btn-sm btn-search"></a>');
                searchBtn.attr('id', pageId + '_btn_search')
                    .text(JoyinUtil.getLabel('SysCommon.search'))
                    .appendTo(btns);
                searchBtn
                    .wrap('<div class="layui-form-item"></div>')
                    .wrap('<div class="layui-inline"></div>')
                    .click(function() {
                        searchBtn.parents('.joyin-filter-form').hide();
                    });
            }
            // 添加重置按钮
            if(items.length >= Math.min(line, 2) && searchAuth) {
                resetBtn = $('<a class="layui-btn layui-btn-sm btn-reset btn-default"></a>');
                resetBtn.attr('id', pageId + '_btn_reset')
                    .text(JoyinUtil.getLabel('SysCommon.reset'))
                    .appendTo(btns);
                resetBtn
                    .wrap('<div class="layui-form-item"></div>')
                    .wrap('<div class="layui-inline"></div>')
                    .click(function() {
                    location.reload();
                });
            }
            // 添加更多按钮
            if(items.length > line) {
                var other = self.find('.layui-form-item:gt(' + (line-1) + ')');
                var moreBtn = $('<i class="iconfont icon-downarrow"></i>');
                if(searchAuth) {
                    if(line >= 2) {
                        resetBtn.after(moreBtn);
                    }
                    else {
                        searchBtn.after(moreBtn);
                        resetBtn.hide();
                    }
                }
                else {
                    btns.append(moreBtn);
                }
                moreBtn
                    .wrap('<a style="margin-left:5px"></a>')
                    .click(function() {
                    other.toggle();
                    line == 1 && resetBtn.toggle();
                    moreBtn.toggleClass('icon-downarrow').toggleClass('icon-uparrow');
                });
                other.hide();
            }
            // 添加按钮区域
            self.prepend(btns);
        });
    };
}());

(function ($) {
    function fnAdjustAjaxParam(keyword, options) {
//      var otherParam = keyword + "&";
        var object = {};
        var result = {};
        if (options.parentIds) {
            var parentIdArr = options.parentIds.split(",");
            $(parentIdArr).each(function (i, parentId) {
                var $val;
                if ($.isArray($("#" + parentId).val())) {
                    $val = $("#" + parentId).val().join(',');
                } else {
                    $val = $("#" + parentId).val();
                }
                object[$("#" + parentId).attr("name")] = $val;
                object["param"] = keyword;
                result["data"] = object;
            });
        } else {
            object["param"] = keyword;
            result["data"] = object;
        }
        return result;
    };
    $.fn.associateInput = function () {
        $(this).each(function () {
            if (typeof $(this).attr("disabled") != 'undefined') {
                return;
            }
            $(this).after('<div class="input-inline-btn">'
                + '<button type="button" class="btn btn-default dropdown-toggle" data-th-data-toggle="dropdown">'
                + '<span class="caret"></span></button><ul class="dropdown-menu dropdown-menu-right" role="menu">'
                + '</ul></div>');
            var effectiveFields = $(this).data("effectivefields");
            if (effectiveFields) {
                effectiveFields = effectiveFields.split(",");
            } else {
                effectiveFields = ['code', 'shortName']
            }
            var effectiveFieldsAlias = $(this).data("effectivefieldsalias");
            var showBtn = false;
            var delayUntilKeyup = true;
            var idField = $(this).data("idfield");
            var keyField = $(this).data("keyfield");
            var linkId = $(this).data("linkedids");
            var suggestCode = $(this).data("code");
            var stationCode = $(this).data("rolestationcode");
            var type = $(this).data("type");
            var url = $(this).data("url");
            var parentIds = $(this).data("parentids");
            var callBack = $(this).data("callback");
            $(this).bsSuggest({
                url: url,
                parentIds: parentIds,
                effectiveFieldsAlias: effectiveFieldsAlias,
                effectiveFields: effectiveFields,
                fnAdjustAjaxParam: fnAdjustAjaxParam,
                ignorecase: true,
                showHeader: true,
                showBtn: showBtn,
                delayUntilKeyup: delayUntilKeyup,
                getDataMethod: "url",
                allowNoKeyword: false,
                idField: idField,
                keyField: keyField,
                processData: function (json) {// url 获取数据时，对数据的处理，作为 getData 的回调函数
                    var i, len, data = {value: []};
                    if (!json || json.length == 0) {
                        return false;
                    }
                    len = json.length;
                    for (i = 0; i < len; i++) {
                        data.value.push({
                            "code": json[i].code,
                            "name": json[i].name,
                            "shortName": json[i].shortName,
                            "hidCode": json[i].hidCode,
                            "linkValue": json[i].linkValue,
                            "object": json[i].object
                        });
                    }
                    return data;
                }
            }).on('onSetSelectValue', function (e, keyword, data) {
                window.__cancelled = true;
                setTimeout(function() {
                    window.__cancelled = false;
                }, 100);
                var $hidden = $("#" + $(this).attr("id") + "_hidden");
                if ($hidden.length === 0) {
                    $hidden = $('<input type="hidden">').attr('id', $(this).attr("id") + "_hidden");
                    $hidden.insertAfter($(this));
                }
                $(this).data('selected', data);
                $hidden.val(data.hidCode);
                if (linkId && $("#" + linkId)) {
                    $("#" + linkId).val(data.linkValue);
                }
                $(this).trigger('change');
                if (callBack) {
                    var func = eval(callBack);
                    new func(data);
                }
            });
            // add by 何阳阳  20161209 start
            $(this).change(function () {
                if (!$(this).val()) {
                    $(this).data('selected', {});
                    var elementId = '#' + $(this).attr("id") + '_hidden';
                    if ($(elementId).length > 0) {
                        $(elementId).val('');
                    }
                    if (linkId && $('#' + linkId)) {
                        $('#' + linkId).val('');
                    }
                }
            });
            // add by 何阳阳  20161209 end
        });
    };
})(jQuery);

/*按钮组下拉选项*/

(function ($) {
   $.fn.btnSelect=function (text,array,done) {
       var html=$(this).html(text);
       var childs=$(this).next(".layui-nav-child");
       $(childs).html("");
       for(var i=0;i<array.length;i++){
           var str = "<dd><a href='javascript:;'>"+array[i]+"</a></dd>";
           $(childs).append(str);
       }
       $(".layui-nav-child").on("click","dd a",function(){
           if($.isFunction(done)){
               return done($(this))
           }else {
               return;
           }
       });
   };
})(jQuery);

/**
 * 弹出框和AJAX
 */
!function (global, $) {
    var JoyinTemp = function () {
        var elementId = '';
        var isTodoList = false;
        return {
            signTodoList: function () {
                isTodoList = true;
            },
            /**
             * 初始化dialog对话框
             *
             * @param title
             *            对话框title
             * @param url
             *            dialog URL
             * @param width
             *            dialog宽度
             * @param height
             *            dialog高度
             * @param paramObj
             *            新页面初始化参数
             * @param callback
             *            画面关闭时的回调函数
             * @param btns 按钮 {name1:function1, name2:function2}
             */
            dialog: function (title, url, width, height, paramObj,
                              callback, btns) {
                var param = [];
                if (paramObj) {
                    $.each(paramObj, function (key, val) {
                        param.push(key + '=' + encodeURIComponent(paramObj[key]));
                    });
                }
                var paraStr = param.join('&');
                if (url && paraStr && url.indexOf('?') == -1) {
                    paraStr = '?' + paraStr;
                }
                var $width = $(window).width();
                var realWidth;
                if (!width) {
                    realWidth = 0.5 * $width + 'px';
                } else if ((width + '').indexOf('%') != -1) {
                    realWidth = (parseFloat(width) / 100) * $width + 'px';
                } else {
                    realWidth = parseFloat(width) + 'px';
                }
                if(url && url.indexOf(basePath) != 0 && url.indexOf('/') == 0) {
                    url = JoyinUtil.getRealPath(url);
                }
                var option = {
                    type: 2,
                    title: title,
                    shade: 0.8,
                    zIndex:layer.zIndex,
                    resize: false,
                    area: [realWidth, height || '90%'],
                    content: url + paraStr,
                    end: function () {
                        if ($.isFunction(callback)) {
                            callback.call(callback, top.returnData);
                            top.returnData = null;
                        }
                    }
                };
                if (btns) {
                    option.btn = [];
                    option.btnAlign = 'c';
                    for (var k in btns) {
                        option.btn.push(k);
                        if (option.btn.length == 1) {
                            option.yes = btns[k];
                        }
                        else {
                            option['btn' + option.btn.length] = btns[k];
                        }
                    }
                }
                return layer.open(option);
            },
            /**
             * 初始化confirm弹出窗
             *
             * @param message
             *            弹出窗title
             * @param yesMethod
             *            确定按钮回调函数
             * @param noMethod
             *            取消按钮回调函数
             */
            confirm: function (message, yesMethod, noMethod) {
                var index = layer.confirm(message, {
                    id: 'JOY_confirm',
                    icon: 3,
                    btn: ['确认', '取消']
                }, function () {
                    layer.close(index);
                    if ($.isFunction(yesMethod)) {
                        yesMethod();
                    }
                }, function () {
                    layer.close(index);
                    if ($.isFunction(noMethod)) {
                        noMethod();
                    }
                });
            },
            confirmMulti: function (message, btnArr, method1, method2, method3) {
                var index = layer.confirm(message, {
                    icon: 3,
                    btn: btnArr || ['确认', '取消']
                }, function () {
                    layer.close(index);
                    if ($.isFunction(method1)) {
                        method1();
                    }
                }, function () {
                    layer.close(index);
                    if ($.isFunction(method2)) {
                        method2();
                    }
                }, function () {
                    layer.close(index);
                    if ($.isFunction(method3)) {
                        method3();
                    }
                });
            },
            /**
             * 初始化promp弹出窗
             *
             * @param message
             *            弹出窗title
             * @param formType
             *            【0，1，2】输入框类型
             * @param callback
             *            回调函数
             * @returns
             */
            prompt: function (message, formType, callback, initValue, maxlength) {
                var index = layer.prompt({
                    title: message,
                    value: initValue,
                    formType: formType,
                    maxlength: maxlength
                }, function (result) {
                    layer.close(index);
                    if ($.isFunction(callback)) {
                        callback(result);
                    }
                });
            },

            /**
             * 初始化alert弹出窗
             *
             * @param message
             *            弹出信息
             * @param closeMethod
             *            关闭回调函数
             * @param iconType
             *            图标类型 1: 2:叉 3：问好
             * @returns
             */
            alert: function (message, iconType, callback, result) {
            	setTimeout(function() {
            		var index = layer.alert(message, {
            			icon: iconType || 1,
            			closeBtn: 0
            		}, function () {
            			layer.close(index);
            			if (callback && typeof callback === "function") {
            				callback(result);
            			}
            		});
            	}, 300);
            },

            /**
             * alertError消息框
             *
             * @param message
             *            错误消息
             * @param callback
             *            回调函数
             * @returns
             */
            error: function (message, callback) {
                var index = layer.alert(message, {
                    icon: 2,
                    closeBtn: 0,
                    area: ["400px", "160px"],
                    maxHeight: 230
                    // 按钮
                }, function () {
                    layer.close(index);
                    if (callback && typeof callback === "function") {
                        callback();
                    }
                });
            },

            /**
             * alertWarning消息框
             *
             * @param message
             * @param closeMethod
             * @returns
             */
            warn: function (message, closeMethod) {
                this.alert(message, 7, closeMethod);
            }
            , showSelectUserDialog: function (multiselect, from, nodeId, moduleId, type, initSelected, callback) {
                var paramStr = 'multiselect=false';
                var path = JoyinUtil.getRealPath('workflow/userSelect/init');
                var callbackFunc = function () {};
                if (arguments.length > 0 && arguments.length < 8) {
                    if ($.isFunction(multiselect)) {
                        callbackFunc = multiselect;
                    } else if ($.isFunction(from)) {
                        callbackFunc = from;
                    } else if ($.isFunction(nodeId)) {
                        callbackFunc = nodeId;
                    } else if ($.isFunction(moduleId)) {
                        callbackFunc = moduleId;
                    } else if ($.isFunction(type)) {
                        callbackFunc = type;
                    } else if ($.isFunction(initSelected)) {
                        callbackFunc = initSelected;
                    } else if ($.isFunction(callback)) {
                        callbackFunc = callback;
                    }
                    var param = {};
                    if (typeof multiselect === 'string' || typeof multiselect === 'boolean') {
                        param['multiselect'] = multiselect;
                    }
                    if (typeof from === 'string') {
                        param['from'] = from;
                    }
                    if (typeof nodeId === 'string') {
                        param['nodeId'] = nodeId;
                    }
                    if (typeof moduleId === 'string') {
                        param['moduleId'] = moduleId;
                    }
                    if (typeof type === 'string') {
                        param['type'] = type;
                    }
                    // 处理人选择画面显示的处理人
                    if(window.showUserIds) {
                        param.showUserIds = window.showUserIds;
                    }
                }
                var height = '400px';
                var btns = {};
                var dialogIndex = -1;
                btns[JoyinUtil.getLabel('userselect.btn.submit')] = function () {
                    var executeDialogResult = JoyinUtil.executeDialogMethod(dialogIndex, 'setUser');
                    if (executeDialogResult != -1) {
                        layer.close(dialogIndex);
                    }
                };
                btns[JoyinUtil.getLabel('userselect.btn.cancel')] = function () {
                    layer.close(dialogIndex);
                };
                if (typeof initSelected === 'object') {
                    window.params = $.extend({}, param, initSelected);
                }
                else {
                    window.params = param;
                }
                if (from === 'workflow') {
                    dialogIndex = this.dialog(JoyinUtil.getLabel('userselect.title.user'), path, '600px', height, param, function (params) {
                        if (params && _.isArray(params)) {
                            callbackFunc.call(callbackFunc, params[0], params[1], params[2], params[3]);
                        }
                    }, btns);
                } else {
                    dialogIndex = this.dialog(JoyinUtil.getLabel('userselect.title'), path, '600px', height, param, function (params) {
                        if (params && _.isArray(params)) {
                            callbackFunc.call(callbackFunc, params[0], params[1], params[2], params[3]);
                        }
                    }, btns);
                }
            },
            /**
             * 普通ajax请求，必须是Post请求
             */
            ajax: function (url, param, callback, errorCallback) {
                var elementIdForLog = Joyin.getElementId(url);
                if (!param) {
                    param = {};
                }
                param.elementIdForLog = elementIdForLog || Joyin.elementId;
                param.pageIdForLog = pageId;
                param.isTodoList = isTodoList;
                // “保存”、“提交”操作时，记录画面修改值
                if(param.elementIdForLog && (param.elementIdForLog.indexOf('_submit') != -1 || param.elementIdForLog.indexOf('_save') != -1)) {
                    if (param.elementIdForLog.indexOf('_submit') != -1 && param.businessInfo) {
                        JoyinUtil.setModifyFormData(JSON.parse(param.businessInfo));
                    } else {
                        JoyinUtil.setModifyFormData(param);
                    }
                }
                if(url && url.indexOf(basePath) != 0 && url.indexOf('/') == 0) {
                    url = JoyinUtil.getRealPath(url);
                }
                $.ajax({
                    type: "POST", url: url, data: param, success: function (result) {
                        Joyin.processResult(result, callback, errorCallback);
                    }
                });
            },
            /**
             * 同步ajax请求，必须是Post请求
             */
            ajaxSync: function (url, param, callback) {
                var elementId = Joyin.getElementId(url);
                if (!param) {
                    param = {};
                }
                param.logElementId = elementId;
                param.elementIdForLog = elementId;
                param.pageId = pageId;
                param.isTodoList = isTodoList;
                // “保存”、“提交”操作时，记录画面修改值
                if(param.elementIdForLog && (param.elementIdForLog.indexOf('_submit') != -1 || param.elementIdForLog.indexOf('_save') != -1)) {
                    if (param.elementIdForLog.indexOf('_submit') != -1 && param.businessInfo) {
                        JoyinUtil.setModifyFormData(JSON.parse(param.businessInfo));
                    } else {
                        JoyinUtil.setModifyFormData(param);
                    }
                }
                if(url && url.indexOf(basePath) != 0 && url.indexOf('/') == 0) {
                    url = JoyinUtil.getRealPath(url);
                }
                $.ajax({
                    type: "POST", url: url, data: param, async: false, success: function (result) {
                        Joyin.processResult(result, callback);
                    }
                });
            },
            /**
             * 参数是json字符串的ajax请求，必须是Post请求
             */
            ajaxJson: function (url, param, callback) {
                var elementIdForLog = Joyin.getElementId(url);
                if (url && url.indexOf("pageIdForLog") == -1) {
                    if (url.indexOf("?") != -1) {
                        if (pageId) {
                            url += "&pageIdForLog=" + pageId + "&elementIdForLog=" + elementIdForLog;
                        }

                    } else {
                        if (pageId) {
                            url += "?pageIdForLog=" + pageId + "&elementIdForLog=" + elementIdForLog;
                        }
                    }
                }
                // 是否为待办事项标识
                if (param) {
                    url += (url.indexOf("?") != -1 ? '&' : '?') + 'isTodoList=' + isTodoList;
                }
                param = JSON.stringify(param);
                if(url && url.indexOf(basePath) != 0 && url.indexOf('/') == 0) {
                    url = JoyinUtil.getRealPath(url);
                }
                // “保存”、“提交”操作时，记录画面修改值
                if(elementIdForLog && (elementIdForLog.indexOf('_submit') != -1 || elementIdForLog.indexOf('_save') != -1)) {
                    var data = JSON.parse(param);
                    if (elementIdForLog.indexOf('_submit') != -1 && data.businessInfo) {
                        JoyinUtil.setModifyFormData(JSON.parse(data.businessInfo));
                    } else {
                        JoyinUtil.setModifyFormData(data);
                    }
                }
                $.ajax({
                    type: "POST",
                    url: url,
                    dataType: "json",
                    contentType: "application/json",
                    data: param,
                    success: function (result) {
                        Joyin.processResult(result, callback);
                    }
                });
            },
            processResult: function (result, callback, errorCallback) {
                var showMessage = function (message, messageType) {
                    // 如果是错误消息，则不执行回调函数
                    if (result && result.statuCode != 200) {
                        return JoyinUtil.showMessage(message, messageType);
                    }
                    if(messageType === 'confirm' && typeof errorCallback != 'undefined' && $.isFunction(errorCallback)) {
                        result = errorCallback;
                    }
                    JoyinUtil.showMessage(message, messageType, callback, result);
                };
                // message 排序，根据画面中元素的先后次序排序
                var sortMessage = function (messageList) {
                    if (_.isArray(messageList)) {
                        var fields = [];
                        $(document.forms[0] || 'body').find(':input:visible:not(:button), table:visible').each(function (i, item) {
                            var name = $(item).attr('name');
                            if (name) fields.push(name);
                        });
                        messageList.sort(function (a, b) {
                            var sort = fields.indexOf(a.field) - fields.indexOf(b.field);
                            if (sort === 0) {
                                sort = (a.order || 0) - (b.order || 0);
                                if (sort === 0) {
                                    if (a.rowId && b.rowId) {
                                        sort = parseInt(a.rowId) - parseInt(b.rowId);
                                    }
                                }
                            }
                            return sort;
                        });
                    }
                };
                // 清空所有错误
                $('.errors').removeClass('errors');
                // string类型的错误消息
                if (result && typeof result.message === 'string') {
                    return showMessage(result.message, result.messageType);
                }
                // 后台valid错误消息
                if (result && result.errorList && result.errorList.length) {
                    var messages = '';
                    if (!result.sortable) {
                        // 如果未开启后台排序，则进行前台排序
                        sortMessage(result.errorList);
                    }
                    $.each(result.errorList, function (i, msg) {
                        var field = msg.field;
                        var message = msg.message;
                        var rowId = msg.rowId;
                        if (field) {
                            var fieldId = '';
                            if (field.indexOf('\.') != -1) {
                                fieldId = field.substring(field.indexOf('\.') + 1);
                            } else {
                                fieldId = field;
                            }
                            var $el = $('#' + fieldId);
                            if ($el.length === 0) {
                                $el = $(':input[name="' + field + '"]');
                            }
                            if ($el.length === 0) {
                                $el = $('*[name="' + field + '"]');
                            }
                            if ($el.is('.input-select')) {
                                $el = $el.next('.select2').find('.select2-selection');
                            }
                            if ($el.is('table')) { // grid验证错误处理
                                $el = $($el[0]);
                                var title = $el.attr('title') || $el.attr('name');
                                var rows = $el.next().find('.layui-table-main').find('tr');
                                $el = $el.next().find('.layui-table-main').find('.layui-table');
                                if (rowId) {
                                    var row = $el.find('tr[data-index="' + rowId + '"]');
                                    var column = msg.column;
                                    if (column !== 'rowId') {
                                        row.find('td[data-field="' + column + '"]').addClass('errors');
                                    }
                                }
                                var rowIndex = 0;
                                rows.each(function (i, tr) {
                                    if ($(tr).attr('data-index') === rowId) {
                                        rowIndex = i + 1;
                                    }
                                });
                                message = message.replace('tableName', title).replace('rowIndex', rowIndex);
                            }
                            $el.addClass('errors');
                        }
                        if (messages) {
                            messages = messages + '<br>';
                        }
                        messages = messages + '<span>' + (i + 1) + '.&nbsp;' + message + '</span>';
                    });
                    showMessage(messages, result.messageType);
                    return;
                }
                // 导入文件验证消息处理
                //添加一级判断在ie9中对于undefine的变量进行indexof会导致出错
                if($('iframe').length > 0 && $('iframe').attr('src') && $('iframe').attr('src').indexOf('framework/fileUpload/init') != -1) {
                    if($.isArray(result)) {
                        this.showCheckResult(result);
                    }
                    else {
                        //防止在进行导入前check时进行了两次的弹框
                        if(result.data != 'noInfoShowOut'){
                            $('iframe').length > 0 && $('iframe')[0].contentWindow.JoyinUtil.hideLoading();
                            this.alert(JoyinUtil.getMessage('FW_I_0001'));
                        }

                    }
                }
                if ($.isFunction(callback)) {
                    callback(result);
                }
            }
            ,showCheckResult: function(result) {
              if(result.length === 0) {
                  $('iframe').length > 0 && $('iframe')[0].contentWindow.JoyinUtil.hideLoading();
                  return this.alert(JoyinUtil.getMessage('FW_I_0002'));
              }
              $('iframe').each(function(i, frame) {
                  // 文件上传控件
                  if($(frame).attr('src').indexOf('framework/fileUpload/init') != -1) {
                      if($.isFunction(frame.contentWindow.showErrorMessage)) {
                          frame.contentWindow.showErrorMessage.call(frame.contentWindow, result);
                      }
                  }
              });
            }
            /**
             * 模拟表单提交，导出excel
             * @param url 提交的请求url
             * @param paramObj 提交请求的参数对象
             * @param callback 导出成功后的回调函数
             * @param 回调函数延迟执行时间（默认为1000毫秒），设置为0时，回调函数不生效
             */
            , exportExcel: function (url, paramObj, callback, millisecond) {
                if(url && url.indexOf(basePath) != 0 && url.indexOf('/') == 0) {
                    url = JoyinUtil.getRealPath(url);
                }
                var form = $("<form action='" + url + "' method='POST'></form>");
                $(document.body).append(form);
                if (pageId) {
                    paramObj.pageId = pageId;
                }
                var event = JoyinUtil.getEvent();
                if ((elementId || elementId === '') && event && event.currentTarget && $(event.currentTarget).attr('id') && $(event.currentTarget).attr('id') != elementId) {
                    elementId = $(event.currentTarget).attr('id');
                }
                if (elementId) {
                    paramObj.elementId = elementId;
                }
                for (var key in paramObj) {
                    if (paramObj[key]) {
                        form.append("<input name='" + key + "' value='" + paramObj[key] + "'/>");
                    }
                }
                form.submit();
                form.remove();    
                // 模板下载时不出进度条
                if(url !== 'downloadTemplate') {
                    // 显示导出文件进度条
                    JoyinProgress.showInDialog('exportProcessBar');
                }
                if ($.isFunction(callback)) {
                    setTimeout(callback, millisecond || 1000);
                }
            }
            /**
             * 上传文件
             * options参数说明
             *    type: 默认drag
             *      singleImage 单个图片上传，
             *      mutiImage 多图片上传，
             *      allowFile 允许上传指定后缀名的文件，
             *      noAutoUpload 选择文件后不自动上传文件
             *      drag 允许拖拽文件上传
             *      list 批量上传文件，同时显示文件列表
             *    size: 文件大小限制，单位是KB
             *    size: 允许上传的文件数。 当type指定为允许批量上传的类型时，该参数有效
             *    auto: 选择文件后是否自动上传，默认为false。当type为noAutoUpload或list时，该参数不生效
             *    hideTpl: 是否隐藏模板下载按钮（如果显示，则下载当前pageId对应的模板文件）
             *    width: 文件上传画面的宽度
             *    height: 文件上传画面的高度
             * uploadCallback 上传成功后的回调函数，接收参数为上传后的文件名（多个文件名会是一个数组） 
             * callback 子画面关闭后调用
             */
            , uploadFile: function(options, uploadCallback, callback) {
                var uploadType = 'drag';
                var opt = {type:uploadType, hideTpl:false, auto: false, height: '530px', width: '750px', pageId: pageId};
                if($.isFunction(options)) {
                    if($.isFunction(uploadCallback)){
                        callback = uploadCallback;
                    }
                    uploadCallback = options;
                }
                if(!$.isFunction(options) && _.isObject(options)){
                    opt = $.extend({}, opt, options);
                }
                window.uploadCallback = uploadCallback || function(){};
                this.dialog(JoyinUtil.getLabel('FwFileUpload.title'), JoyinUtil.getRealPath('framework/fileUpload/init'), opt.width, opt.height, opt);
            }
            /**
             * 关闭顶层窗口
             */
            , closeTopWindow: function () {
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            },
            /**
             * 获取elementId
             */
            getElementId: function (url) {
                // 如果是导入控件
                if(JoyinUtil.hasUploadFrame() && url.indexOf('check') == -1){
                    return Joyin.elementId;
                }
                // 非导入控件
                var event = JoyinUtil.getEvent();
                var elementId = "";
                if (!event) {
                    return elementId;
                }
                if (event.currentTarget) {
                    elementId = $(event.currentTarget).attr('id');
                }
                if (event.srcElement && !elementId) {
                    elementId = $(event.srcElement).attr('id');
                    // 消息框
                    if(!elementId && ($(event.srcElement).parent().is('.layui-layer-btn') || $(event.srcElement).parent().is('.layui-btn'))) {
                        elementId = Joyin.elementId;
                    }
                }
                if (elementId) {
                    Joyin.elementId = elementId;
                }
                return elementId;
            },
            /**
             * 打印共通
             * @param options 提交请求的参数对象
             * options 参数说明(templateHtml:模板html,data:待装入模板的数据)
             */
             printTemplate: function (options) {
                var laytpl = layui.laytpl;
                var getTpl = options.templateHtml;
                laytpl(getTpl).render(options.data, function(html){
                    WebBrowser;
                    $('#_tempPrint').html(html);
                      $('#_tempPrint').printArea({
                        popClose : true,
                        mode : "popup",
                        extraCss :false
                    });
                      $('#_tempPrint').empty();
                });
            }
        }
    };
    global.Joyin = JoyinTemp();
}(window, window.jQuery);

/**
 * Created by zhengyan on 2018/1/10 0010.
 */
//1.type:控件选择类型,year年选择器,month年月选择器,date日期选择器,time时间选择器,datetime    日期时间选择器
//2.range - 开启左右面板范围选择,默认值：false
//3.calendar - 是否显示公历节日默认值：false
//4.showBottom - 是否显示底部栏默认值：true
//5.btns - 工具按钮默认值：['clear', 'now', 'confirm']
//6.format - 自定义格式默认值：yyyy-MM-dd
(function ($) {
    //按钮开关控制是否显示按钮
    var btnInit = null;
    var isShowBottom = false;
    if(DATE_SELECT_BTN_CTR&&DATE_SELECT_BTN_CTR=="01"){
        btnInit = ['clear', 'confirm'];
        isShowBottom = true;
    }
    var defaultOptions = {
        lang: 'cn'
        , type: 'date'
        , calendar: false
        , autoChoose: true
        , showBottom: isShowBottom
        , btns:btnInit
        ,change: function(value, date, endDate){
            if(!isShowBottom && (endDate != null && endDate != undefined && !$(event.target).is('.laydate-icon'))){
                if($($(this)[0].elem).hasClass('edit-widget')) return;
                $($(this)[0].elem).val(value).trigger('change');
                $('.layui-laydate').remove();
            }
        }
        //, btns: ['clear', 'confirm']
    };
    $.fn.textDate = function (range, format) {
        var laydate = layui.laydate;
        //this是调用这个函数的对象，已经不是通过 class input-dateSelect得到的jquery对象代表的dom
        $(this).each(function () {
            var initValue = $(this).val(),
                self = $(this),
                options = $(this).data('option');
            try {
                options = new Function("return " + options)();
            } catch (e) {
            }
            if(!options) options = {};
            if(!options.done || !$.isFunction(options.done)) {
                options.done = function(value, date, endDate) {
                    if(self.hasClass('edit-widget')) {
                        self.removeClass('edit-widget');
                        self[0].value = value;
                        layui.$(self[0]).trigger('change').blur();
                    }
                    self.trigger('change');
                };
            }
            var _date = laydate.render($.extend({
                elem: this
                , range: range
                , format: format
                , value: initValue
            }, defaultOptions, options));
            $(this).data('laydate', _date);
            if(typeof options.readonly === 'undefined' || options.readonly === true) {
                $(this).prop('readonly', true).on('keyup', function(e) {
                    // 退格键或删除键
                    if(e.keyCode === 8 || e.keyCode === 46) {
                        self.val('');
                    }
                });
            }
        });
    };
    $.fn.startDate = function () {
        var v = $(this).val(), _date = $(this).data('laydate'), range = _date.config.range;
        var values = v.split(' ' + range + ' ');
        return $.trim(values[0]);
    };
    $.fn.endDate = function () {
        var v = $(this).val(), _date = $(this).data('laydate'), range = _date.config.range;
        var values = v.split(' ' + range + ' ');
        return $.trim(values[1]);
    };
}(jQuery));

/**
 * Created by zhengyan on 2018/1/4 0004.
 */
(function ($) {
    /*数值类文本框控件*/
    /*1. data-pattern:格式化表达式
     2. data-fixtype：对于小数的处理(1：取整；2：四舍五入；3：向上取整；4:向下取整)
     3. unit：单位，数值类型（格式化后的数据将会除以该单位） 默认值 1
     4. data-textrange:具体保留几位小数*/
    $.fn.textMoney = function () {
        $(this).each(function () {
            $(this).on('focus', function () {
                var value = $(this).val();
                $(this).val(value.replace(/,/g, ""));
            });
            $(this).on('blur', function () {
                var moneyType = $(this).attr("data-fixtype") || 2;
                var moneyPattern = $(this).attr("data-pattern");
                var textRange = $(this).attr("data-textrange") || 2;
                var moneyValue = $(this).val();
                var precision = 0;//确定格式化后保留几位小数
                var moneyUnit = $(this).attr("unit");
                //只有输入的不是数字，小数点，负号全部为空
                if (isNaN(moneyValue) || !moneyValue) {//11.a 11.
                    moneyValue = moneyValue.replace(/[^\d\.\-]+/g, "");
                    if (moneyValue == "") {
                        $(this).val("");
                        return;
                    }
                }
                if (moneyPattern) {
                    //对格式化进行处理
                    var dotIndex = moneyPattern.lastIndexOf(".");//获取moneyPattern中小数点的位置
                    if (dotIndex != -1) {
                        precision = moneyPattern.length - dotIndex - 1;
                    }
                }
                precision = textRange ? textRange : precision;
                var resultValue = JoyinUtil.formatNumber(moneyValue, precision, moneyUnit, moneyType);
                $(this).val(resultValue);
                
                // 来自表格单元格编辑
                if($(this).hasClass('layui-table-edit')) {
                	$(this).data('td').find('div.layui-table-cell').text(resultValue);
                }
            }).on('input', function() {
            	var moneyValue = $(this).val();
            	if(moneyValue != '') {
            		//只有输入的不是数字，小数点，负号全部为空
            		if (isNaN(moneyValue)) {
            			moneyValue = moneyValue.replace(/[^\d\.\-]+/g, "");
        				$(this).val(moneyValue);
            		}
            	}
            });
            if($(this).val()) {
            	$(this).trigger('blur');
            }
        })
    }
}(jQuery));

/**
 * Created by zhengyan on 2018/1/8 0008.
 */
(function ($) {
    /*数值类文本框控件
    1. data-pattern:格式化表达式
    2. data-fixtype：对于小数的处理(1：取整；2：四舍五入；3：向上取整；4:向下取整)
    3. data-textrange:具体保留几位小数*/
    $.fn.textNumber = function () {
        $(this).each(function () {
            $(this).on('focus', function () {
                var value = $(this).val();
                $(this).val(value.replace(/,/g, ""));
            });
            $(this).on('blur', function () {
                var numberFixtype = $(this).attr("data-fixtype") || 2;
                var numberPattern = $(this).attr("data-pattern");
                var numberVal = $(this).val();
                var textRange = $(this).attr("data-textrange") || 2;
                var precision = 0;//确定格式化后保留几位小数
                var thousandSeparator = "";//定义一个分隔符变量
                if (isNaN(numberVal) || !numberVal) {
                    numberVal = numberVal.replace(/[^\d\.\-]+/g, "");
                    if (numberVal == "") {
                        $(this).val("");
                        return;
                    }
                }
                //对data-pattern进行处理
                if (numberPattern) {
                    //data-pattern存在时，要获得小数点的位数，要知道整数部分有几位，还有小数部分有几位
                    var patternDot = numberVal.lastIndexOf(".");//获取小数点的位置,索引是从0开始
                    if (patternDot != -1) {
                        precision = numberVal.length - patternDot - 1;
                    }
                }
                precision = textRange ? textRange : precision;
                var resultValue = JoyinUtil.formatNumber(numberVal, precision, 1, numberFixtype);
                $(this).val(resultValue);

                // 来自表格单元格编辑
                if($(this).hasClass('layui-table-edit')) {
                	$(this).data('td').find('div.layui-table-cell').text(formatterInteger + decimalPart);
                }
            }).on('input', function() {
            	var moneyValue = $(this).val();
            	if(moneyValue != '') {
            		//只有输入的不是数字，小数点，负号全部为空
            		if (isNaN(moneyValue)) {
            			moneyValue = moneyValue.replace(/[^\d\.\-]+/g, "");
        				$(this).val(moneyValue);
            		}
            	}
            });
            if($(this).val()) {
            	$(this).trigger('blur');
            }
        })
    }
}(jQuery));

/**
 * Created by zhengyan on 2018/1/18 0018.
 */
/*
  1. data-url：ajax请求后台url
  2. initValue：默认值（可选）
  3. childrenIds,parentIds用于联想框的id
  4. multiple下拉框的多选
  5. placeholder模糊查询输入框中的提示信息（可选）
  6. data-result初始化model中获取，el表达式
* */
$(function ($) {
    var ajaxResult = [], OPTION_ALL_ID = '_all';
    var displayHasSelectedTitle = function (element) {
        var next = element.next();
        var parentWidth = next.width();
        var options = next.find(".select2-selection__choice");
        var clear = next.find(".select2-selection__clear");
        var clearWidth = 10;
        if (clear && clear.length > 0) {
            clearWidth = clear.width();
        }
        var optionWidth = 0;
        $.each(options, function (i, item) {
            optionWidth += $(item)[0].scrollWidth;
        });
        var selectedLength = 0;
        var title = "";
        if (parentWidth - clearWidth * 2 - optionWidth - (options.length + 1) * 10 <= 0) {
            var lis = next.find(".select2-selection__rendered").find("li");
            var selectLis = lis.filter(function (i, item) {
                return !!$(item).attr("title")
            }).each(function (i, item) {
            	var data = $(item).data('data');
            	if(data.id === OPTION_ALL_ID) {
            		data.selected = false;
            	}
            	else if ($(item).attr("title")) {
            		data.selected = true;
                    selectedLength++;
                    title += $(item).attr("title") + ","
                }
            });
            selectLis.first().before("<li class='select2-selection__choice' title='" + title.substring(0, title.length - 1)
                + "'>已选择" + selectedLength + "个项目</li>");
            selectLis.remove();
        }
    };
    var oldMatcher = function (element) {
        var newMatcher = function (term, text, child) {
            var inputVal = term;
            if (typeof text != 'undefined' && text && text.indexOf(inputVal) >= 0) {
                return true;
            }
            var nowObj;
            var $child = element.children('[value="' + child.id + '"]');
            $child.each(function () {
                nowObj = $(this);
            });
            if (nowObj) {
                var mka = nowObj.attr("mka") || $child.html();
                var mkb = nowObj.attr("mkb") || $child.toPinyin();
                var mkc = nowObj.attr("mkc") || '';
                if (mka.toLowerCase().indexOf(inputVal.toLowerCase()) >= 0
                    || mkb.toLowerCase().indexOf(inputVal.toLowerCase()) >= 0
                    || mkc.toLowerCase().indexOf(inputVal.toLowerCase()) >= 0) {
                    return true;
                }
            }
            return false;
        }

        function wrappedMatcher(params, data) {
            var match = $.extend(true, {}, data);
            if (params.term == null || $.trim(params.term) === '') {
                return match;
            }
            if (data.children) {
                for (var c = data.children.length - 1; c >= 0; c--) {
                    var child = data.children[c];
                    var doesMatch = newMatcher(params.term, child.text, child)
                        || newMatcher(params.term, child.id, child);
                    if (!doesMatch) {
                        match.children.splice(c, 1);
                    }
                }
                if (match.children.length > 0) {
                    return match;
                }

            }
            if (newMatcher(params.term, data.text, data)
                || newMatcher(params.term, data.id, data)) {
                return match;
            }
            return null;
        }

        return wrappedMatcher;
    }
    
    /**
     * 复选下拉框选择所有出发事件
     * @param 当前控件id
     */
    var setAllChecked = function($e, $this) {
        if (($e.params.data && $e.params.data.id === OPTION_ALL_ID)
        		|| ($e.params.args && $e.params.args.data && $e.params.args.data.id === OPTION_ALL_ID)) {
            if (!$($e.delegateTarget).data("result")) {
                $($e.delegateTarget).data("result", ajaxResult);
            }
            var resultOptArr = $($e.delegateTarget).data("result");
            var allOptIds = [];
            $(resultOptArr).each(function(i, opt){
                if (opt.id !== OPTION_ALL_ID) {
                    allOptIds.push(opt.id);
                }
            });
            $this.val(allOptIds).trigger("change");
        }
    };
    
    var inputSelect = function ($this) {
        var dataUrl = $this.attr("data-url");
        var placeholder = $this.attr("placeholder");
        var results = $this.attr("data-result") || {};
        var initValue = $this.attr("initValue");
        var allowClear = $this.attr("allowClear");
        var childrenIds = $this.attr("childrenIds");
        var parentIds = $this.attr("parentIds");
        var multiple = $this.attr("multiple");
        var showSearch = $this.attr('searchable');
        var formatOption = $this.attr("formatoption") === "false" ? false : true;
        var showAll = $this.attr("showAll") === "true" ? true : false;
        var options = {
            placeholder: placeholder || '',
            language: "zh-CN",
            allowClear: !allowClear || allowClear == "true",
            closeOnSelect: !multiple,
            multiple: multiple,
            selectOnClose: false,
            matcher: oldMatcher($this)
        };
        if(showSearch == 'false') {
            options.minimumResultsForSearch = -1;
        }
        if (formatOption) {
            options.templateResult = function(repo) {
                if (repo.id && repo.id !== OPTION_ALL_ID) {
                    return repo.id + ":" + repo.text;
                } else {
                    return repo.text;
                }
            };
        }
        // 解决 下拉框无法被清空的BUG
        if($this.is('select') && !multiple) {
            $this.prepend('<option></option>');
        }
        if (initValue && dataUrl) {
            var dataObj = {initValue: initValue};
            if (parentIds) {
                var parentArray = parentIds.split(",");
                $.each(parentArray, function (i, item) {
                    var parent = $("#" + item);
                    var parentValue = parent.val();
                    if ($.isArray(parentValue)) {
                        parentValue = parentValue.join(",");
                    }
                    dataObj[parent.attr("name")] = parentValue;
                })
            }
            $.ajax({
                url: dataUrl,
                type: 'post',
                async: false,
                data: dataObj
            }).done(function (data) {
                data = data.root||data;
                if (data && $.isArray(data)) {
                    results.results = data;
                }
            })
        }
        if (dataUrl) {
            options.ajax = {
                url: dataUrl,
                dataType: 'json',
                delay: 250,
                data: function (params) {
                    var query = {
                        search: params.term,
                        page: params.page || 1
                    };
                    if (parentIds) {
                        var parentArray = parentIds.split(",");
                        $.each(parentArray, function (i, item) {
                            var parent = $("#" + item);
                            var parentValue = parent.val();
                            if ($.isArray(parentValue)) {
                                parentValue = parentValue.join(",");
                            }
                            query[parent.attr("name")] = parentValue;
                        });
                    }
                    return query;
                },
                processResults: function (data, params) {
                	data = data.root || data;
                	ajaxResult = data;
                    params.page = params.page || 1;
//                	// 添加全选功能
//                    if(showAll && options.multiple) {
//                    	data.length > 0 && data.unshift({'id': OPTION_ALL_ID, 'text': '全部'});
//                    }
                    return {
                        results : data,
                        pagination: {
                            more: (params.page * 20) <= (data.records || data.length)
                        }
                    };
                }
            }
        }
        if (results && JSON.stringify(results) != "{}") {
            if ($.isArray(results.results)) {
                options.data = results.results;
            }
            else {
                results = $.parseJSON(results);
                if ($.isArray(results)) {
                    options.data = results;
                }
            }
        }
        // 查看模式
        if(typeof pageModel !== 'undefined' && pageModel === PAGE_MODEL_REFERENCE
                && (!$this.data('ignore') || $this.data('ignore') == 'false')) {
            $this.attr('disabled', 'disabled');
        }
        
        // 添加全选功能
        if(showAll && options.multiple) {
        	options.data && options.data.unshift({'id': OPTION_ALL_ID, 'text': '全部'});
        }

        var select2Object = $this.select2(options);
        if (initValue) {
            var showField = $this.data('showField'), values = [], _valueArray = initValue.split(',');
            if($this.data('url') && $this.hasClass('edit-widget') && showField) {
                if (options.data && $.isArray(options.data)) {
                    _.each(options.data, function(d) {
                        if(_valueArray.indexOf(d[showField]) != -1) {
                          values.push(d['id']);
                        }
                    });
                    select2Object.val(values);
                }
            }
            else {
                select2Object.val(initValue.split(","));
            }
        }
        select2Object.on("change", function () {
        	var event = JoyinUtil.getEvent();
        	if($(event.srcElement).is('.select2-selection__clear')) {
        		return;
        	}
        	if (childrenIds) {//p5
                var childrenArray = childrenIds.split(",");
                $.each(childrenArray, function (i, item) {
                    inputSelect($("#" + item))
                    $("#" + item).val('').trigger('change');
                })
            }
            setTimeout(function () {
                displayHasSelectedTitle($this)
            }, 10);
        }).trigger('change');
        //nihuai@2018/3/30
        //解决由于搜索区域悬浮后，下拉列表显示不完整问题
        select2Object.on('select2:open', function(e){
            $('.select2-container').css('z-index','3');
            $('body').css('overflow-x', 'hidden');
        }).on('select2:close', function(e){
            $('body').css('overflow-x', 'auto');
        });
        // 全选事件
        if(showAll) {
        	select2Object.on("select2:selecting", function(e) {
        		setAllChecked(e, select2Object);
        	});
        }
        $this.children().each(function (i, child) {
            var htmword = $(child).html();
            var pyword = $(child).toPinyin();
            var supperword = "";
            pyword.replace(/[A-Z]/g, function (word) {
                supperword += word
            });
            $(child).attr("mka", (htmword).toLowerCase());
            $(child).attr("mkb", (pyword).toLowerCase());
            $(child).attr("mkc", (supperword).toLowerCase());
        });
    };
    $.fn.textSelect = function () {
        $(this).each(function () {
            inputSelect($(this));
        });
    };
}(jQuery));

/**
 * Created by zhengyan on 2018/1/29 0029.
 */
/*
1. data-url：文件上传的路径。
2. id：必填，上传文件控件ID
3. data-allowfiletype：允许上传的文件的类型，竖线分隔。（可选，默认值 doc|docx|xls|xlsx|ppt）
4. data-inline：是否行内上传模式
5. data-width,data-height：指定拖拽区域大小
6. data-bind-action,可选，不提供自动生成确认上传按钮
7. data-showdelete 显示删除按钮(默认false）
8. data-callback  上传文件后的回调函数
9. name 指定name属性后，序列化方法将可以获取上传后文件的ID
*/
$(function ($) {
    $.fn.uploadFile = function () {
        var upload = layui.upload, uploadedFiles = [];
        $(this).each(function () {
            var url = JoyinUtil.getRealPath('framework/fileUpload/uploadFile')/*$(this).attr("data-url")*/;
            var allowfiletype = $(this).attr("data-allowfiletype");
            var showdelete = $(this).attr("data-showdelete");
            var inline = $(this).attr("data-inline");
            var muiltiple = $(this).attr("data-muiltiple");
            var width = $(this).attr("data-width");
            var height = $(this).attr("data-height");
            var bindAction = $(this).attr("data-bind-action");
            var callback = eval($(this).attr("data-callback"));
            var name = $(this).attr("name");
            var _this = this;
            var uploadBtn, width, height;
            var muit = muiltiple && muiltiple == 'true';
            if (inline) {
                $(this).addClass('layui-btn layui-btn-normal layui-btn-xs');
                $(this).html('<i class="layui-icon">&#xe67c;</i>选择文件');
                if (!bindAction) {
                    $(this).after('<span class="layui-btn layui-btn-xs">上传文件</span>');
                    uploadBtn = $(this).next();
                }
            }
            else {
                $(this).addClass('layui-upload-drag');
                $(this).css({'width': width, 'height': height, 'padding-left': '5px', 'padding-right': '5px'});
                $(this).html('<i class="layui-icon">&#xe67c;</i><p>点击上传，或将文件拖拽到此处</p>');
                if (!bindAction) {
                    $(this).after('<span class="layui-btn" style="vertical-align: top">上传文件</span>');
                    uploadBtn = $(this).next();
                }
            }
            if(muit) {
                $(this).parent().addClass('upload-muiltiple');
            }
            var options = upload.render({
                elem: $(this)[0]
                , accept: 'file'
                , exts: allowfiletype
                , multiple: muit
                , number: muit ? 0 : 1
                , auto: false
                , bindAction: bindAction || uploadBtn
                , url: url
                , method: 'post'
                , choose: function (obj) {
                    //将每次选择的文件追加到文件队列
                    var files = obj.pushFile();
                    var names = [];
                    $.each(files, function (i, file) {
                        if (uploadedFiles.indexOf(i) != -1) {
                            delete files[i];
                        }
                        else {
                            names.push(file.name);
                        }
                    });
                    // 添加IE8支持
                    if (names.length === 0) {
                        var _file = $(':file').val();
                        names.push(_file);
                    }
                    if (inline) {
                        $(".layui-upload-file").after("<span class='layui-inline layui-upload-choose'>" + names.join(',') + "</span>")
                        if (showdelete == "true") {
                            $(".remove-btn").css("display", "block");
                            $(".remove-btn").click(function () {
                                $(".layui-upload-choose").remove();
                            })
                        }
                    } else {
                        var style = {
                            width: '14px',
                            height: '14px',
                            position: 'absolute',
                            color: '#000',
                            display: 'none'
                        };
                        if(muit) {
                            style['margin-top'] = '-16px';
                            style['right'] = '5px';
                            width = $('#template p').width();
                        }
                        else {
                            style['margin-left'] = '-25px';
                            style['margin-top'] = '-27px';
                            style['left'] = '50%';
                            style['top'] = '50%';
                            height = $('#template').height();
                        }
                        $(_this).find('~ div.layui-upload-drag').remove();
                        $.each(names, function(i, name) {
                            var $el = $('<div class="layui-upload-drag" style="width:' + width + 'px;height:' + height + 'px;padding-left: 5px;padding-right: 5px;"></div>')
                            .mouseover(function () {
                                $(this).css('background', '#ccc');
                                $close.show();
                            }).mouseout(function () {
                                $(this).css('background', 'none');
                                $close.hide();
                            });
                            var $name = $('<div></div>').html(name);
                            var $close = $('<div><i class="layui-icon">&#x1006;</i></div>').css(style).on('click', function () {
                                $el.remove();
                                $(_this).show();
                                $(':file', this).val('');
                                $.each(files, function (i) {
                                    delete files[i];
                                });
                            });
                            $el.append($name);
                            $el.append($close);
                            $(_this).after($el);
                        });
                        if(!muit) {
                            $(_this).hide();
                        }
                    }
                }
                , before: function (obj) {
                    this.data={'pageId':pageId};
                    layer.load();
                    //调用进度条
                    JoyinProgress.showInDialog('uploadFileProgressBar');
                }
                , done: function (res, file) {//上传完毕回调
                    layer.closeAll('loading');
                    uploadedFiles.push(file);
                    if ($.isFunction(callback)) {
                        callback(res);
                    }
                }
                , error: function () {//请求异常回调
                    layer.closeAll('loading');
                    layer.msg('网络异常，请稍后重试！');
                }
            });
        })
    };
}(jQuery));


/**
 * Created by zhengyan on 2018/2/2 0002.
 */
//data-nameArray  用来传的数组
$(function ($) {
    $.fn.download = function () {
        $(this).each(function () {
            var nameArray = $(this).data("nameArray");
            for (var i = 0; i < nameArray.length; i++) {
                $(this).append("<a href='" + nameArray[i].url + "'><i class='layui-icon'>&#xe601;" + nameArray[i].name + "</i></a>");
            }
        })
    }
}(jQuery));

/**
 * Created by nihuai on 2018/3/30.
 * 少量下拉菜单单选时改成checkbox样式
 */
$(function ($) {
    var inputCheckBox = function(_self, result, initValue){
        _self.hide();
        var divStr = '<ul class="layui-checkbox-u">';
        for(var i =0; i< result.length; i++){
            divStr += '<li class="layui-chcekbox';
            if(result[i].id === initValue){
                divStr += ' laytable-checkbox-selected';
            }
            divStr += '" data-value="'+result[i].id+'">'+result[i].text+'</li>';
        }
        divStr += '</ul>';
        _self.parent().append(divStr);
        if(initValue){
            _self.val(initValue);
        }
        _self.prop('disabled') ? _self.next().addClass('disabled') : bindClick(_self);
        _self.data('inputCheckBoxed', true);
    };
    
    var bindClick = function(_self) {
        _self.next().removeClass('disabled').find('li').off('click').bind('click',function(){
            if(!$(this).hasClass('laytable-checkbox-selected')){
                _self.next().find('li').removeClass('laytable-checkbox-selected');
                $(this).addClass('laytable-checkbox-selected');
                _self.val($(this).data('value')).trigger('change');
            }
        });
    };
    
    var offClick = function(_self) {
        _self.next().addClass('disabled').find('li').off('click');
    };
    
    $.fn.inputCheckBox = function (param) {
        $(this).each(function () {
            var $elem = $(this);
            if(!$elem.data('inputCheckBoxed')) {
                var result = $elem.data("result");
                var initValue = $elem.attr("initValue");
                if(result && $.isArray(result)){
                    inputCheckBox($elem, result, initValue);
                }
            }
            if(_.isObject('param')) {
                var disabled = !!param['disabled'];
                $elem.prop('disabled', disabled);
                if(!disabled) {
                    bindClick($elem);
                }
                else if(disabled) {
                    offClick($elem);
                }
            }
        });
    }
}(jQuery));

/**
 * Created by peijiajun on 2018/7/6.
 * 带图标的输入框
 */
$(function ($) {
    $.fn.inputFuzzyText = function (param) {
        $(this).each(function () {
            var self = $(this);
            var icon = $('<a class="iconinput"><i class="iconfont icon-search"></i></a>');
                var callback = eval(self.attr("data-eventfunc"));
            self.wrap('<div class="input-btn-append"></div>')
            self.attr('autocomplete','off').parent().append(icon);
            icon.click(function(){
                 if($.isFunction(callback)){
                     callback.call(icon, self.val());
                 }
            });
            self.bind('keydown',function(event){
                if(event.keyCode == "13") {
                 if($.isFunction(callback)){
                     callback.call(icon, self.val());
                 }
                }
            }); 
        });
    }
}(jQuery));

/**
 * Created by peijiajun on 2018/7/4.
 */
(function ($) {
    var treeLevel=1;
    $.fn.joyinMixTree = function (options) {
        var leftAtagClick=function(self,treeId,iconQ){
            iconQ.parent().click(function() {
                iconQ.toggleClass('icon-downarrow').toggleClass('icon-uparrow');
                $.fn.zTree.getZTreeObj(treeId).expandAll(iconQ.hasClass('icon-downarrow'));
            });
        };
        var showztreemenuNum=function(b,childnodes,lev,ztreeobj,expFlag) {
            if(b){
                var rootnodes = ztreeobj.getNodes();
                showztreemenuNum(false,rootnodes,lev,ztreeobj,expFlag);//递归
            }else{
                var len=-1;
                if(childnodes!=null&&(len=childnodes.length)!=null&&len>0){
                    if(!expFlag){
                        if(treeLevel<childnodes[0].level){
                            treeLevel=childnodes[0].level;
                        }
                    }
                    else{
                        if(lev<=childnodes[0].level){
                            return;
                        }
                    }
                    for (var i = 0; i < len; i++) {
                        if(expFlag){
                            ztreeobj.expandNode(childnodes[i], true, false, false, true);
                        }
                        var child=childnodes[i].children;
                        showztreemenuNum(false,child,lev,ztreeobj,expFlag);//递归
                    }
                }
            }
        };
        var rightAtagClick=function(self,objDiv,treeId){
            self.find('div').find('a:last').hover(function(event){
                var ztree=$.fn.zTree.getZTreeObj(treeId);
                showztreemenuNum(true,ztree,100,ztree,false);
                var _levVal = [];
                for(var i=1;i<=treeLevel;i++){
                    _levVal.push("<li>"+i+"</li>")
                }
                objDiv.find('ul:first').empty().append(_levVal.join(''));
                
                var top=$(this).offset().top+22;
                var left=$(this).offset().left - $(this).width() / 2;
                objDiv.css({
                    top: top,
                    left: left,
                    width:"35px",
                    visibility:"visible",
                    background:"#fff",
                    border:"1px solid #ccc",
                    'text-align':'center'
                });
            },function(){
                objDiv.css({"visibility": "hidden"});
            });
            
            objDiv.hover(function(){
                $(this).css({"visibility": "visible"});
            },function(){
                $(this).css({"visibility": "hidden"});
            });
            
            objDiv.find('ul:first').on('click','li',function(){
                var ztree=$.fn.zTree.getZTreeObj(treeId);
                var level=$(this).html();
                ztree.expandAll(false);
                showztreemenuNum(true,ztree,level,ztree,true);
            })
        };
        
        var getFixElements = function(options) {
            if(options.clas=='add'||options.clas=='addsub'){
                return 'icon-found';
            }
            if(options.clas=='edit'){
                return 'icon-edit';
            }
            if(options.clas=='del'){
                return 'icon-delete';
            }
            return '';
        };
        /**
         * 添加全选按钮
         */
        var addCheckAllBtn = function(rootDiv, id) {
            var filter = id + '_all';
            var checkBox = $('<input type="checkbox" lay-skin="primary" lay-filter="' + filter + '">');
            rootDiv.prepend(checkBox);
            layui.form.render();
            layui.form.on('checkbox(' + filter + ')', function(obj) {
            	var treeObject = $.fn.zTree.getZTreeObj(id + '_tree');
                if(obj.othis.hasClass('layui-form-checked')) {
                    treeObject && treeObject.checkAllNodes(true);
                }
                else {
                    treeObject && treeObject.checkAllNodes(false);
                }
            });
            checkBox.next('.layui-form-checkbox').css({
                'margin-top': -5,
                'margin-left': 5
            });
        };
        
        /*
         * 创建树控件的HTML结构
         * add by 何阳阳 2018-08-04
         */
        var createTreeDom = function(treeElement) {
            var self = $(treeElement);
            //添加头部div
            var rootTitle=$(treeElement).data("roottitle");
            var atag = '<a class="mixtree-head"></a>';
            var span = $('<span></span>').html('&nbsp;' + rootTitle);
            var iconQ = $('<i class="iconfont icon-downarrow"></i>');
            var iconH = $('<i class="iconfont icon-showorder fsize18"></i>');
            var rootDiv = $("<div class='mixtree-div'></div>");
            self.empty().append(rootDiv.append(iconQ));
            iconQ.wrap(atag).after(span);
            iconH.appendTo(rootDiv).wrap($(atag).css({float:"right","padding-right":"5px"}));

            var id = self.attr("id");
            var checkAll = self.data('checkall');
            var rLevelId = id + "_rLevel";
            var levelHtml = "<div id='"+rLevelId+"' class='rMixMenu treeMenu'><ul></ul></div>"; 
            $("body").after(levelHtml);
            leftAtagClick(self, id+"_tree" ,iconQ);
            rightAtagClick(self,$("#" + rLevelId),id+"_tree");

            // 添加内容部分
            var contentDiv = $('<div></div>');
            contentDiv.css({
                height: self.height()
                ,width: '100%'
                ,overflow: 'auto'
            });
            self.height('auto').append(contentDiv);
            
            if(typeof checkAll === 'undefined' || checkAll === 'true' || checkAll === true) {
                addCheckAllBtn(rootDiv, id);
            }
            return contentDiv;
        };
        
        $(this).each(function () {
            var contentDiv = createTreeDom(this);
            var id = $(this).attr("id");
            var canEdit = $(this).data("canedit");
            var checkable = $(this).data("checkable");
            var showIcon = $(this).data("icon");
            var url = $(this).attr("url");
            var html = "<ul id='" + id + "_tree' class='ztree'></ul>";
            var treeData = $(this).data("result");
            var rMenuId = id + "_rMenu";
            var optpanelR = $.extend({}, $.fn.joyinMixTree.defaults, {});
            if(options && options.col) {
               optpanelR.col = options.col;
            }
            contentDiv.append(html);
            if (canEdit == '1') {
                //构造右键面板
                var panelR=optpanelR.col;
                if(panelR && $.isArray(panelR)) {
                     var _rightVal = [];
                     _rightVal.push("<div  id= '" + rMenuId+ "' class='rMixMenu'><ul>");
                     $.each(panelR, function(i, arrEntry) {
                         var clasValue=getFixElements(arrEntry);
                         if(clasValue==''){
                             return true;
                         }
                         _rightVal.push("<li class='"+arrEntry.clas+"'><i class='iconfont "+clasValue+"'></i>&nbsp"+arrEntry.text+"</li>");
                     });
                     _rightVal.push("</ul></div>");
                     $("body").after(_rightVal.join(''));
                }
            }
            var rMenuDom = $("#" + rMenuId);
            var setting = {
                view: {
                    dblClickExpand: false,
                    showIcon:  typeof showIcon === 'undefined' || showIcon === 'true' || showIcon === true
                },
                check: {
                    enable: typeof checkable === 'undefined' || checkable === 'true' || checkable === true
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "pId",
                        rootPid: ""
                    }
                },
                callback: {
                    onAsyncSuccess: function () {
                        JoyinUtil.hideLoading(this);
                        // 取消全选状态
                        $(':checkbox[lay-filter="' + id + '_all"]').next('.layui-form-checked').trigger('click');
                        if (typeof options.onTreeLoadSuccess != 'undefined' && options.onTreeLoadSuccess && $.isFunction(options.onTreeLoadSuccess)) {
                            options.onTreeLoadSuccess.call(options.onTreeLoadSuccess, arguments);
                        }
                    },
                    onRightClick: function (event, treeId,
                                            treeNode) {
                        if (treeNode && !treeNode.noR) {
                            //暂时取消右键功能
                            tree.zTree.selectNode(treeNode);
                            tree.showRMenu("node", event.clientX, event.clientY, treeNode);
                        }
                    }
                }
            }
            var tree = {
                setting: options && options.setting ? $.extend(true, setting, options.setting) : setting,
                zTree: "",
                addCount: 0,
                rMenuId: rMenuId,
                rMenuDom: rMenuDom,
                showRMenu: function (type, x, y, treeNode) {
                    tree.rMenuDom.show();
                    if (type == "root") {
                        tree.rMenuDom.find(".del").hide();
                    } else {
                        tree.rMenuDom.find(".del").show();
                    }
                    var top = parseFloat(y) + parseFloat(document.documentElement.scrollTop)
                    var left = parseFloat(x) + parseFloat(document.documentElement.scrollLeft)
                    tree.rMenuDom.css({"top": top + "px", "left": left + "px", "visibility": "visible"});

                    $("body").one("mousedown", tree.onBodyMouseDown);
                },reloadTree:function(){
                     if (url) {
                         setting.async = {
                             type: "post",
                             dataType: "json",
                             url: url,
                             enable: true,
//                             contentType: "application/json",
                             autoParam: ["id"]
                         }
                         tree.zTree = $.fn.zTree.init($("#" + id + "_tree"), setting);
                     } else {
                         tree.zTree = $.fn.zTree.init($("#" + id + "_tree"), setting, treeData);
                     }
                },hideRMenu: function () {
                    tree.rMenuDom.hide();
                }, onBodyMouseDown: function (event) {
                    if (!(event.target.id == tree.rMenuId
                        || $(event.target).parents("#" + tree.rMenuId).length > 0)) {
                        tree.rMenuDom.css({"visibility": "hidden"});
                    }
                }, addTreeNode: function (e) {
                    tree.hideRMenu();
                    var zTree = tree.zTree;
                    var parenNode = zTree.getSelectedNodes()[0].getParentNode();
                    var clas = $(e.currentTarget).attr('class');
                    var optCol=optpanelR.col;
                    if(optCol && $.isArray(optCol)) {
                        var curEntry = null;
                        $.each(optCol,function(i,entry){
                            if(entry.clas == clas){
                                curEntry=entry;
                                return false;
                            }
                        })
                        if(curEntry==null){
                            return;
                        }
                        if(typeof curEntry.isdefine != 'undefined' && curEntry.isdefine && curEntry.isdefine==true){
                            if (typeof options.onTreeAddFunction != 'undefined' && options.onTreeAddFunction && $.isFunction(options.onTreeAddFunction)) {
                                options.onTreeAddFunction.call(options.onTreeAddFunction, function(){
                                    tree.reloadTree();
                                });
                            }
                        }
                        else{
                            Joyin.prompt(curEntry.text,0,function(resValue){
                            var nodeName = resValue;
                               if (!nodeName) {
                                    return;
                                }
                                tree.ajaxAddTreeNode(parenNode, nodeName);
                            });
                        }
                    }

                }, addSubTreeNode: function (e) {
                    tree.hideRMenu();
                    var zTree = tree.zTree;
                    var parenNode = zTree.getSelectedNodes()[0];
                    var clas = $(e.currentTarget).attr('class');
                    var optCol=optpanelR.col;
                    if(optCol && $.isArray(optCol)) {
                        var curEntry=null;
                        $.each(optCol,function(i,entry){
                            if(entry.clas==clas){
                                curEntry=entry;
                                return false;
                            }
                        })
                        if(curEntry==null){
                            return;
                        }
                        if(typeof curEntry.isdefine != 'undefined' && curEntry.isdefine && curEntry.isdefine==true){
                            if (typeof options.onTreeAddSubFunction != 'undefined' && options.onTreeAddSubFunction && $.isFunction(options.onTreeAddSubFunction)) {
                                options.onTreeAddSubFunction.call(options.onTreeAddSubFunction, parenNode.id,parenNode.name,function(){
                                    tree.reloadTree();
                                });
                            }
                        }
                        else{
                            Joyin.prompt(curEntry.text,0,function(resValue){
                                var nodeName = resValue;
                                   if (!nodeName) {
                                        return;
                                    }
                                    tree.ajaxAddTreeNode(parenNode, nodeName);
                                });
                        }
                    }

                }, ajaxAddTreeNode: function (parenNode, nodeName) {
                    var newNode = {name: nodeName};
                    var addNode = function() {
                        var zTree = tree.zTree;
                        if (parenNode) {
                            newNode.checked = parenNode.checked;
                            zTree.addNodes(parenNode, newNode);
                        } else {
                            zTree.addNodes(null, newNode);
                        }
                    };
                    if(options["addUrl"]) {
                        $.ajax({
                            url: options["addUrl"],
                            data: {parenId: parenNode ? parenNode.id : "", name: nodeName},
                            success: function (data) {
                                if (data && data.success) {
                                    addNode();
                                }
                            }
                        })
                    }
                    else {
                        addNode();
                    }

                }, expandTreeNode: function () {
                    tree.hideRMenu();
                    if (tree.zTree.getSelectedNodes()[0]) {
                        tree.zTree.expandNode(tree.zTree.getSelectedNodes()[0])
                    }
                }, removeTreeNode: function () {
                    tree.hideRMenu();
                    var zTree = tree.zTree;
                    var nodes = zTree.getSelectedNodes();
                    var delParentWithChild = false;
                    if (nodes && nodes.length > 0) {
                        if (nodes[0].children && nodes[0].children.length > 0) {
                            var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
                            Joyin.confirm(msg, function() {
                                tree.ajaxDelNode(nodes[0], true);
                            });
                        }
                        else {
                            tree.ajaxDelNode(nodes[0], delParentWithChild);
                        }
                    }
                }, editTreeNode: function () {
                    tree.hideRMenu();
                    var zTree = tree.zTree;
                    var updateNode = zTree.getSelectedNodes()[0];
                    if (!updateNode) {
                        return;
                    }
                    var optCol=optpanelR.col;
                    if(optCol && $.isArray(optCol)) {
                        var curEntry=null;
                        $.each(optCol,function(i,entry){
                            if(entry.clas=='edit'){
                                curEntry=entry;
                                return false;
                            }
                        })
                        if(curEntry==null){
                            return;
                        }
                        if(typeof curEntry.isdefine != 'undefined' && curEntry.isdefine && curEntry.isdefine==true){
                            if (typeof options.onTreeEditFunction != 'undefined' && options.onTreeEditFunction && $.isFunction(options.onTreeEditFunction)) {
                                options.onTreeEditFunction.call(zTree, updateNode, function(){
                                    tree.reloadTree();
                                });
                            }
                            
                        }
                        else{
                            Joyin.prompt(curEntry.text,0,function(resValue){
                                var nodeName = resValue;
                                   if (!nodeName) {
                                        return;
                                    }
                                    tree.ajaxEditTreeNode(updateNode, nodeName);
                                });
                        }
                    }
                },
                ajaxEditTreeNode: function (updateNode, nodeName) {
                    if(options["editUrl"]) {
                        $.ajax({
                            type: "post",
                            url: options["editUrl"],
                            data: {id: updateNode.id, name: nodeName},
                            success: function (data) {
                                updateNode.name = nodeName;
                                tree.zTree.updateNode(updateNode);
                            }
                        });
                    }
                    else {
                        updateNode.name = nodeName;
                        tree.zTree.updateNode(updateNode);
                    }
                }, ajaxDelNode: function (node, delParentWithChild) {
                    if(options["delUrl"]) {
                        $.ajax({
                            type: "post",
                            url: options["delUrl"],
                            data: {id: node.id},
                            success: function (data) {
                                if (data && data.success) {
                                    tree.zTree.removeNode(node);
                                }
                            }
                        });
                    }
                    else {
						var optCol=optpanelR.col;
						if(optCol && $.isArray(optCol)) {
							var curEntry=null;
							$.each(optCol,function(i,entry){
								if(entry.clas=='edit'){
									curEntry=entry;
									return false;
								}
                        })
                        if(curEntry==null){
                            return;
                        }
                        tree.zTree.removeNode(node);
                        if(typeof curEntry.isdefine != 'undefined' && curEntry.isdefine && curEntry.isdefine==true){
                            if (typeof options.onTreeDelFunction != 'undefined' && options.onTreeDelFunction && $.isFunction(options.onTreeDelFunction)) {
                                options.onTreeDelFunction.call(tree.zTree, node);
                            }
                            
                        }
						}
                    }
                },
                bindMenuEvent: function () {
                    var rMenu = tree.rMenuDom;
                    rMenu.find(".add").on("click", tree.addTreeNode)
                    rMenu.find(".addsub").on("click", tree.addSubTreeNode)
                    rMenu.find(".expand").on("click", tree.expandTreeNode)
                    rMenu.find(".del").on("click", tree.removeTreeNode)
                    rMenu.find(".edit").on("click", tree.editTreeNode)
                }
            }
            setting.callback["onRename"] = tree.onRename;
            tree.bindMenuEvent();
            JoyinUtil.showLoading(this);
            if (url) {
                setting.async = {
                    type: "post",
                    dataType: "json",
                    url: url,
                    enable: true,
//                    contentType: "application/json",
                    autoParam: ["id"]
                }
                tree.zTree = $.fn.zTree.init($("#" + id + "_tree"), setting);
            } else {
                tree.zTree = $.fn.zTree.init($("#" + id + "_tree"), setting, treeData);
            }
        })


    };
    $.fn.joyinMixTree.defaults={
            col:[
                {text:'增加节点',clas:'add'},
                {text:'编辑节点',clas:'edit'},
                {text:'删除节点',clas:'del'},
                {text:'增加子节点',clas:'addsub'}
            ]
    };
}(jQuery));

/**
 * Created by zhengyan on 2018/2/5.
 */
(function ($) {
    $.fn.joyinTree = function (options) {
        $(this).each(function () {
            var id = $(this).attr("id");
            var canEdit = $(this).data("canedit");
            var checkable = $(this).data("checkable");
            var showIcon = $(this).data("icon");
            var url = $(this).attr("url");
            var html = "<ul id='" + id + "_tree' class='ztree' style=' overflow:auto;'></ul>"
            $(this).append(html);
            var treeData = $(this).data("result");
            var rMenuId = id + "_rMenu";
            if (canEdit == '1') {
                var menuHtml = "<div id= '" + rMenuId
                + "' class='rMenu'><ul>" + "<li  class='add'>增加节点</li>"
                + "<li  class='edit'>编辑节点</li>"
                + "<li  class='del'>删除节点</li>"
                + "<li class='expand'>展开节点</li></ul>" + "</div>";
                $("body").after(menuHtml);
            }
            var rMenuDom = $("#" + rMenuId);
            var setting = {
                view: {
                    dblClickExpand: false,
                    showIcon:  typeof showIcon === 'undefined' || showIcon === 'true' || showIcon === true
                },
                check: {
                    enable: typeof checkable === 'undefined' || checkable === 'true' || checkable === true
                },
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "id",
                        pIdKey: "pId",
                        rootPid: ""
                    }
                },
                callback: {
                    onAsyncSuccess: function () {
                        JoyinUtil.hideLoading(this);
                        if (typeof options.onTreeLoadSuccess != 'undefined' && options.onTreeLoadSuccess && $.isFunction(options.onTreeLoadSuccess)) {
                            options.onTreeLoadSuccess.call(options.onTreeLoadSuccess, arguments);
                        }
                    },
                    onRightClick: function (event, treeId,
                                            treeNode) {
                        if (treeNode && !treeNode.noR) {
                            //暂时取消右键功能
                            tree.zTree.selectNode(treeNode);
                            tree.showRMenu("node", event.clientX, event.clientY, treeNode);
                        }
                    }
                }
            }
            var tree = {
                setting: setting,
                zTree: "",
                addCount: 0,
                rMenuId: rMenuId,
                rMenuDom: rMenuDom,
                showRMenu: function (type, x, y, treeNode) {
                    tree.rMenuDom.show();
                    if (type == "root") {
                        tree.rMenuDom.find(".del").hide();
                    } else {
                        tree.rMenuDom.find(".del").show();
                    }
                    var top = parseFloat(y) + parseFloat(document.documentElement.scrollTop)
                    var left = parseFloat(x) + parseFloat(document.documentElement.scrollLeft)
                    tree.rMenuDom.css({"top": top + "px", "left": left + "px", "visibility": "visible"});

                    $("body").one("mousedown", tree.onBodyMouseDown);
                }, hideRMenu: function () {
                    tree.rMenuDom.hide();
                }, onBodyMouseDown: function (event) {
                    if (!(event.target.id == tree.rMenuId
                        || $(event.target).parents("#" + tree.rMenuId).length > 0)) {
                        tree.rMenuDom.css({"visibility": "hidden"});
                    }
                }, addTreeNode: function () {
                    tree.hideRMenu();
                    var zTree = tree.zTree;
                    var parenNode = zTree.getSelectedNodes()[0];
                    var nodeName = prompt("please enter node name!");
                    if (!nodeName) {
                        return;
                    }
                    tree.ajaxAddTreeNode(parenNode, nodeName);

                }, ajaxAddTreeNode: function (parenNode, nodeName) {
                    var newNode = {name: nodeName};
                    $.ajax({
                        type: "post",
                        url: options["addUrl"],
                        data: {parenId: parenNode ? parenNode.id : "", name: nodeName},
                        success: function (data) {
                            if (data && data.success) {
                                var zTree = tree.zTree;
                                if (parenNode) {
                                    newNode.checked = zTree.getSelectedNodes()[0].checked;
                                    zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
                                } else {
                                    zTree.addNodes(null, newNode);
                                }
                                alert("添加成功！");
                            } else {
                                alert("添加失败！");
                                return;
                            }

                        }
                    })

                }, expandTreeNode: function () {
                    tree.hideRMenu();
                    if (tree.zTree.getSelectedNodes()[0]) {
                        tree.zTree.expandNode(tree.zTree.getSelectedNodes()[0])
                    }
                }, removeTreeNode: function () {
                    tree.hideRMenu();
                    var zTree = tree.zTree;
                    var nodes = zTree.getSelectedNodes();
                    var delParentWithChild = false;
                    if (nodes && nodes.length > 0) {
                        if (nodes[0].children && nodes[0].children.length > 0) {
                            var msg = "要删除的节点是父节点，如果删除将连同子节点一起删掉。\n\n请确认！";
                            if (confirm(msg) == false) {
                                return;
                            }
                            delParentWithChild = true;
                        }
                        tree.ajaxDelNode(nodes[0], delParentWithChild);
                    }
                }, editTreeNode: function () {
                    tree.hideRMenu();
                    var zTree = tree.zTree;
                    var updateNode = zTree.getSelectedNodes()[0];
                    if (!updateNode) {
                        return;
                    }
                    var nodeName = prompt("please enter node name!");
                    if (!nodeName) {
                        return;
                    }
                    tree.ajaxEditTreeNode(updateNode, nodeName);
                },
                ajaxEditTreeNode: function (updateNode, nodeName) {
                    $.ajax({
                        type: "post",
                        url: options["editUrl"],
                        data: {id: updateNode.id, name: nodeName},
                        success: function (data) {
                            if (data && data.success) {
                                updateNode.name = nodeName;
                                tree.zTree.updateNode(updateNode);
                                alert("编辑成功！");
                            } else {
                                alert("编辑失败！");
                            }
                        }
                    })
                }, ajaxDelNode: function (node, delParentWithChild) {
                    $.ajax({
                        type: "post",
                        url: options["delUrl"],
                        data: {id: node.id},
                        success: function (data) {
                            if (data && data.success) {
                                tree.zTree.removeNode(node);
                                alert("删除成功！");
                            } else {
                                alert("删除失败！");
                            }
                        }
                    })
                },
                bindMenuEvent: function () {
                    var rMenu = tree.rMenuDom;
                    rMenu.find(".add").on("click", tree.addTreeNode)
                    rMenu.find(".expand").on("click", tree.expandTreeNode)
                    rMenu.find(".del").on("click", tree.removeTreeNode)
                    rMenu.find(".edit").on("click", tree.editTreeNode)
                }
            }
            setting.callback["onRename"] = tree.onRename;
            tree.bindMenuEvent();
            JoyinUtil.showLoading(this);
            if (url) {
                setting.async = {
                    type: "post",
                    dataType: "json",
                    url: url,
                    enable: true,
//                    contentType: "application/json",
                    autoParam: ["id"]
                }
                tree.zTree = $.fn.zTree.init($("#" + id + "_tree"), setting);
            } else {
                tree.zTree = $.fn.zTree.init($("#" + id + "_tree"), setting, treeData);
            }
        })
    };
    $.fn.reloadTree = function(params) {
        $(this).each(function () {
            var treeId = $(this).attr('id') + "_tree";
            var treeObject = $.fn.zTree.getZTreeObj(treeId);
            params = params || {};
            if(treeObject) {
                $.fn.zTree.init($("#" + treeId), $.extend(true,{},treeObject.setting, {async:{otherParam: params}}));
            }
        });
    };
    $.expandTree = function (treeId, button) {
        var btn = $(button);
        var flag = true;
        if (btn.attr("expandOrCollapse")) {
            var html = btn.html();
            if (html == "折叠") {
                flag = false;
                btn.html("展开");
            } else {
                btn.html("折叠");
                flag = true;
            }
        }
        $.fn.zTree.getZTreeObj(treeId + "_tree").expandAll(flag);
    };
}(jQuery));

/**
 * Created by zhangnian on 2018/7/26 
 */
//data-process  进度条--调用方法
//type 1:进度条控件(假) 2:进度条控件(真) 3:进度条弹框(真)
$(function ($) {
    
    /**
     * 显示进度条控件，该进度条为共通进度条，显示在表格最底部
     * @callback 进度条完成后的回调函数
     */
    function showProgressBar(callback) {
        var commonProgerssBar = $('#commonProgerssBar');
        layui.element.progress('progressBar', '0%');
        commonProgerssBar.show().find('div.layui-progress-bar').html('0%');
        startProgressBar(commonProgerssBar, 'progressBar', callback);
    }

    /**
     * 显示进度条控件，该进度条为共通进度条，显示在表格最底部
     * @callback 进度条完成后的回调函数
     */
    function forceFinishProgressBar() {
        var commonProgerssBar = $('#commonProgerssBar');
        var precent = parseInt(commonProgerssBar.find('div.layui-progress-bar').html());
        var intval = setInterval(function() {
            precent += 10;
            precent = Math.max(precent, 100);
            if(precent > 100) {
                commonProgerssBar.hide();
                clearInterval(intval);
                intval = null;
            }
            layui.element.progress('progressBar', precent + '%');
            commonProgerssBar.find('div.layui-progress-bar').html(precent + '%');
        }, 100);
    }
    
    /**
     * 进度条开始
     * @callback 进度完成后的回调
     */
    function startProgressBar($progerssBar, barId, callback) {
        if($progerssBar && $progerssBar.length > 0) {
            // 显示导出文件进度条
            var processing = setInterval(function() {
                $.ajax({
                    url: JoyinUtil.getRealPath('common/' + pageId + '/percent/' + barId),
                    type: 'get',
                    success: function(percent) {
                        if(!isNaN(percent) && percent < 0.99 && percent >= 0) {
                            var percentText = parseInt(percent * 100) + '%';
                            layui.element.progress(barId, percentText);
                            $progerssBar.find('.layui-progress-bar').text(percentText);
                        }
                        else {
                            finish(0, callback);
                        }
                    },
                    error: function() {
                        finish(1, callback);
                    }
                });
            }, 800);
            // 进度条完成
            var finish = function(status, callback) {
                layui.element.progress(barId, '100%');
                $progerssBar.find('.layui-progress-bar').text('100%');
                setTimeout(function() {
                    clearInterval(processing);
                    processing = null;
                    if(callback && $.isFunction(callback)) {
                        callback.call($progerssBar, status, $progerssBar);
                    }
                }, 1000);
            };
        }
    }

    //进度条--调用方法
    var progressBarMethod = function (barId, methodName, autoclose) {
        methodName = methodName || '0';
        // 展示虚拟进度条
        if(methodName == '1'){
            var commonProgerssBar = $('#commonProgerssBar');
            layui.element.progress(barId, '0%');
            commonProgerssBar.show().find('div.layui-progress-bar').html('0%');
            var n = 0;
            var processing = setInterval(function(){
                n = n + Math.random()*10|0;  
                if(n > 100){
                  n = 100;
                }
                layui.element.progress(barId, n+'%');
                commonProgerssBar.find('div.layui-progress-bar').html(n+'%');
                if(n == 100 && autoclose){
                    clearInterval(processing);
                    processing = null;
                    commonProgerssBar.hide();
                }
              }, 100);
        }
        // 展示共通进度条
        else if(methodName == '2') {
            showProgressBar();
        }
        else{
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
                        layer.close(processIndex);
                    });
                }
             });
        }
    }
    //进度条--控件
    $.fn.progressBarInit = function(){
        $(this).each(function () {
            //构造进度条div
            var key = $(this).attr("data-key");
            var width = $(this).attr("data-width") || '100%';
            var start = $(this).attr("data-start");
            var hide = $(this).attr("data-hide");
            var objProgressBar = $('<div class="layui-progress-bar" lay-percent="0%">0%</div>');
            $(this).attr('lay-filter', key).width(width).addClass('layui-progress layui-progress-big').append(objProgressBar);
            if(start && start != 'false') {
                startProgressBar($(this), key);
            }
            if(hide && hide == 'true') {
                $(this).hide();
            }
        });
    };

    window.JoyinProgress = {
        // 虚拟进度条
        showInvented : function(autoclose) {
            if(typeof autoclose == 'undefined') {
                autoclose = true;
            }
            progressBarMethod('progressBar', '1', autoclose);
        }
        // 公共进度条
        , showCommon: function(autoclose) {
            if(typeof autoclose == 'undefined') {
                autoclose = true;
            }
            progressBarMethod('progressBar', '2', autoclose);
        }
        // 公共进度条完成
        , hideCommon: function() {
            forceFinishProgressBar();
        }
        // 弹框进度条
        , showInDialog : function(barId) {
            barId = barId || 'dialogProgressBar';
            progressBarMethod(barId, '0', true);
        }
    };
}(jQuery));

/* ****************************************************
 * workflow 功能
 * ****************************************************/
var isTodoList = false;
/**
 * 工作流处理
 */
(function ($, global) {
    var users = [];
    var roles = [];
    var elementId = '';
    var pageUrl = '';
    var isLastTask = false;

    // 获取新建或修改时业务画面的操作类型（新建，修改）
    function _getSaveOperType() {
        if (pageModel !== 'undefined' && pageModel === PAGE_MODEL_EDIT) {
            return 'modify';
        }
        return 'new';
    }

    // 撤销
    function doRevoke(url, formElement, elementId, pid, callback) {
        users = [];
        roles = [];
        _submit(url, 'revoke', formElement, elementId, pid, '0', callback);
    }

    // 撤销 提交
    function doRevokeSubmit(url, formElement, elementId, pid, callback) {
        if (!isLastTask) {
            selectUser(elementId, pid, formElement, function () {
                _submit(url, 'revoke', formElement, elementId, pid, '1', callback);
            });
        } else {
            _submit(url, 'revoke', formElement, elementId, pid, '1', callback);
        }
    }

    // 新建-修改
    function doSave(url, formElement, elementId, pid, callback) {
        users = [];
        roles = [];
        _submit(url, _getSaveOperType(), formElement, elementId, pid, '0', callback);
    }

    // 新建-修改 批量保存
    function doSaveList(url, formElement, elementId, pid, callback) {
        users = [];
        roles = [];
        _submitJson(url, _getSaveOperType(), formElement, elementId, pid, '0', callback);
    }

    // 选择用户
    function selectUser(elementId, pid, param, callback) {
        users = [];
        roles = [];
        elementId = elementId;
        if (!elementId) {
            return Joyin.warn('未指定画面元素ID');
        }
        if($.isFunction(param)) {
            callback = param;
            param = '';
        }
        var data = {
            processInstanceId: $('#processInstanceId').val() || '',
            taskId: $('#wfl_taskId').val() || '',
            pageId: (pid || pageId),
            elementId: elementId
        };
        if(typeof param === 'string') {
            data.businessInfo = param;
        } else {
            data.businessInfo = JSON.stringify($.extend({}, param));
        }
        Joyin.ajax(JoyinUtil.getRealPath('workflow/nextTask'), data, function (result) {
            var data = result.data || {};
            if (!data.nodeId && !isLastTask) {
                if($.isFunction(callback)) {
                    callback.call(callback);
                }
                return;
            }
            if (!data.nodeId || !data.moduleId || !data.type) {
                Joyin.alert(JoyinUtil.getMessage('WFL_W00003', data.moduleId, data.nodeId));
                return;
            }
            Joyin.showSelectUserDialog(true, 'workflow', data.nodeId, data.moduleId, /*data.type*/'user', function () {
                if (arguments && arguments.length >= 2) {
                    var type = arguments[0];
                    var selectedObject = arguments[1];
                    if (type === 'user') {
                        $.each(selectedObject, function (i, user) {
                            users.push(user.userName);
                        });
                    } else if (type === 'role') {
                        $.each(selectedObject, function (i, role) {
                            roles.push(role.roleId);
                        });
                    }

                    if ($.isFunction(callback)) {
                        callback.call(callback, users, roles);
                    }
                }
            });
        });
    }

    // 新建-修改 提交
    function doSubmit(url, formElement, elementId, pid, callback) {
        if (!isLastTask) {
            selectUser(elementId, pid, formElement, function () {
                _submit(url, _getSaveOperType(), formElement, elementId, pid, '1', callback);
            });
        } else {
            _submit(url, _getSaveOperType(), formElement, elementId, pid, '1', callback);
        }
    }

    // 新建-修改 批量提交
    function doSubmitList(url, formElement, elementId, pid, callback) {
        if (!isLastTask) {
            selectUser(elementId, pid, formElement, function () {
                _submitJson(url, _getSaveOperType(), formElement, elementId, pid, '1', callback);
            });
        } else {
            _submitJson(url, _getSaveOperType(), formElement, elementId, pid, '1', callback);
        }
    }

    // 删除 提交
    function doDelete(url, formElement, elementId, pid, callback) {
        if (!isLastTask) {
            selectUser(elementId, pid, formElement, function () {
                _submit(url, 'delete', formElement, elementId, pid, '1', callback);
            });
        } else {
            _submit(url, 'delete', formElement, elementId, pid, '1', callback);
        }
    }

    // 删除 提交
    function doDeleteList(url, formElement, elementId, pid, callback) {
        if (!isLastTask) {
            selectUser(elementId, pid, formElement, function () {
                _submitJson(url, 'delete', formElement, elementId, pid, '1', callback);
            });
        } else {
            _submitJson(url, 'delete', formElement, elementId, pid, '1', callback);
        }
    }

    function _submit(url, operType, formElement, elementId, pid, submit, callback) {
        var param = _.isObject(formElement) ? formElement : $.serializeToObject($(formElement));
        param.operType = operType; // 操作类型
        param.users = users.join(',');
        param.roles = roles.join(',');
        param.pageId = pid || pageId;
        param.elementId = elementId;
        param.submit = submit || '0';
        param.isTodoList = isTodoList;
        if (isTodoList) {
            // 代办事项保存
            if (submit !== '1') {
                JoyinWorkFlow.doSave(param, callback);
            }
            // 代办事项提交
            else {
                JoyinWorkFlow.doSubmit(param, callback);
            }
        } else {
            Joyin.ajax(url, param, function () {
                closeSelf(callback);
            });
        }
    }

    function _submitJson(url, type, formElement, elementId, pid, submit, callback) {
        var param = _.isObject(formElement) ? formElement : $.serializeToObject($(formElement));
        var workflowParameter = [];
        workflowParameter.push(type);// 操作类型
        workflowParameter.push(users.join(',')); // 下一步骤处理人
        workflowParameter.push(roles.join(',')); // 下一步骤处理角色
        workflowParameter.push(submit || '0'); // 是否为提交操作
        workflowParameter.push(pid || pageId); // 画面ID
        workflowParameter.push(elementId); // 画面元素ID
        workflowParameter.push(isTodoList); // 是否为来自代办事项
        param.workflowParameter = workflowParameter.join('|');
        param.operType = type; // 操作类型
        param.users = users.join(',');
        param.roles = roles.join(',');
        param.pageId = pid || pageId;
        param.elementId = elementId;
        param.submit = submit || '0';
        param.isTodoList = isTodoList;
        if (url) {
            if (url.indexOf("?") != -1) {
                if (url.indexOf("operType") == -1) {
                    url += "&operType=" + type;
                }
            } else {
                url += "?operType=" + type;
            }

            if (elementId) {
                url += "&elementIdForLog=" + elementId;
            }

            if (pageId) {
                url += "&pageIdForLog=" + pageId;
            }
        }

        if (isTodoList) {
            // 代办事项保存
            if (submit !== '1') {
                JoyinWorkFlow.doSave(param, callback);
            }
            // 代办事项提交
            else {
                JoyinWorkFlow.doSubmit(param, callback);
            }
        } else {
            Joyin.ajaxJson(url, param, function () {
                closeSelf(callback);
            });
        }
    }

    // 取消
    function closeSelf(callback) {
        if (window.name) {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        }
        if ($.isFunction(callback)) {
            callback.call(callback);
        }
    }

    function signTodoList(pageUrl) {
        isTodoList = true;
        pageUrl = pageUrl;
        Joyin.signTodoList();
    }

    function getTodoList() {
        return isTodoList;
    }

    function signLastTask() {
        isLastTask = true;
    }

    function setShowUserIds(uids) {
        if (typeof uids !== 'undefined' && uids) {
            if (_.isArray(uids)) {
                global.showUserIds = uids.join(',');
            }
            else {
                global.showUserIds = uids;
            }
        }
    }

    global.WorkFlow = {
        doSave: doSave,
        doSaveList: doSaveList,
        doRevoke: doRevoke,
        doRevokeSubmit: doRevokeSubmit,
        doDelete: doDelete,
        doDeleteList: doDeleteList,
        doSubmit: doSubmit,
        doSubmitList: doSubmitList,
        selectUser: selectUser,
        cancel: closeSelf,
        getTodoList: getTodoList,
        signTodoList: signTodoList,
        signLastTask: signLastTask,
        setShowUserIds: setShowUserIds
    };
}(jQuery, this));