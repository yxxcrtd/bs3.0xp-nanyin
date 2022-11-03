//@ sourceURL=uploadFile001.js
(function() {
    if(viewFlag){
        $('#multFileBtn').hide();
        $('#multFileBtn2').hide();
    }
}());
Date.prototype.format = function(format)
{
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
        (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)if(new RegExp("("+ k +")").test(format))
        format = format.replace(RegExp.$1,
            RegExp.$1.length==1 ? o[k] :
                ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}

var processIndex;

var doprocess = function(){
    processIndex = layer.open({
        type: 1
        ,title: false //不显示标题栏
        ,closeBtn: false
        ,area: '300px;'
        ,shade: 0.6
        ,id: 'JOY_process' //设定一个id，防止重复弹出
        ,moveType: 1 //拖拽模式，0或者1
        ,success: function(layero){
            layero.css('background', 'transparent');
            // 开始进度条
        }
    });
}

function viewPdf(fileNo,fileName){
    var index= fileName.lastIndexOf('.');
    var ext = fileName.substr(index+1).toUpperCase();
    debugger;
    //如果是pdf或者word则进行预览
    if(ext=='PDF'){
        Joyin.ajax(basePath + 'disclosure/infoCompliance/uploadFile001/getFilePath', {fileNo:fileNo}, function(result) {
            Joyin.dialog("文件预览",
                basePath+"/js/disclosure/pdf/web/viewer.html?file="+result+"&",
                "90%", "90%", {},function(){});
        });
    } else if(ext=='DOC' || ext=='DOCX'){
        //预览word文件
        Joyin.dialog("文件预览",
            basePath+"/disclosure/common/WordView/init",
            "1120px", "90%", {fileNo:fileNo},function(){});
    }else {
        window.location = basePath+"framework/download/"+fileNo;
    }
}

function downLoad(fileNo){
    window.location = basePath+"framework/download/"+fileNo;
}
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

    var removeFileFromServer = function(fileNo, callback) {
        Joyin.confirm('请确认是否要删除该文件？', function() {
            Joyin.ajax(JoyinUtil.getRealPath('framework/remove/' + fileNo), {}, callback);
        });
    };
    var rownum = 0;

    $.fn.uploadFile = function () {
        var upload = layui.upload;

        $(this).each(function () {
            if($(this).data('uploader')) {
                return;
            }
            $(this).empty();
            var uploadedFiles = {}, initFiles = [], files = {};
            var deleteFile = function(fileNo) {
                rownum --;
                delete uploadedFiles[fileNo];
                delete files[fileNo];
            };

            var options = $(this).data('option');
            var datas = $(this).data('data') || [];
            try {
                options = new Function("return " + options)();
            } catch (e) {
                options = {};
            }

            options = options || {};
            var businessId = $(this).next('#businessId').val() || '';
            var fileType = $('#fileType').val() || '';
            var fileType2 = $('#fileType2').val() || '';
            var url = (options.url || JoyinUtil.getRealPath('disclosure/infoCompliance/uploadFile001/uploadFile')) + '?businessId='+businessId+'&fileType='+fileType+'&fileType2='+fileType2;
            var allowfiletype = options.allowFileType || ''/* || 'doc/docx/xls/xlsx/ppt'*/; // 允许任何类型的文件
            var showdelete = options.showDelete;
            var inline = options.inline;
            var auto = options.autoUpload;
            var muit = options.muiltiple;
            var type = options.type || 'drag'; // 展现类型 drag/list
            var width = options.width;
            var height = options.height;
            var bindAction = options.bindAction;
            var callback = options.callback;
            var disabled = $(this).attr('disabled') === 'disabled';
            var _this = this;
            var elemId = $(this).attr('id');
            var uploadBtn, width, height, fileListView;
            var fileUploader = options.fileUploader;

            if(typeof callback === 'string') {
                callback = eval(callback);
            }

            var elem = $(this)[0];
            if(muit && type == 'list') {
                var $table = $('<table class="layui-table"></table>')
                    ,$thead = $('<thead><tr><th>文件名</th><th>大小</th><th>状态</th>' + (disabled ? '' : '<th>操作</th>') + '</tr></thead>')
                    ,$thr = $('<tr></tr>')
                    ,$tbody = $('<tbody></tbody>')
                    ,$action = $('<button type="button" data-ignore="true" class="mult-file-btn layui-btn layui-btn-normal">选择多文件</button>')
                    ,$bindAction = $('<button type="button" class="mult-file-btn layui-btn">开始上传</button>');
                $table.append($thead.append($thr)).append($tbody);
                if(!disabled) {
                    $(this).append($action);
                    if(typeof auto != undefined && auto === false) {
                        $bindAction.css('margin-left', '10px');
                        $(this).append($bindAction);
                    }
                }
                $(this).append($table);
                fileListView = $tbody;
                elem = $action[0];
                bindAction = bindAction || $bindAction;
                $(this).toggleClass('layui-upload', true);
            }
            else {
                if (inline) {
                    $(this).addClass('layui-btn layui-btn-normal layui-btn-xs');
                    $(this).html('<i class="layui-icon">&#xe67c;</i>选择文件');
                    if (!bindAction) {
                        $(this).after('<span class="layui-btn layui-btn-xs">开始上传</span>');
                        uploadBtn = $(this).next();
                    }
                }
                else {
                    $(this).addClass('layui-upload-drag');
                    $(this).css({'width': width, 'height': height, 'padding-left': '5px', 'padding-right': '5px'});
                    $(this).html('<i class="layui-icon">&#xe67c;</i><p>点击上传，或将文件拖拽到此处</p>');
                    if (!bindAction && typeof auto != undefined && auto === false) {
                        $(this).after('<span class="layui-btn btn-full" style="vertical-align: top">开始上传</span>');
                        uploadBtn = $(this).next();
                    }
                }
            }

            initFiles = datas || [];
            // 设置初始值
            if(initFiles && initFiles.length > 0) {
                if(muit && type == 'list') {
                    for(var i = 0; i < initFiles.length; i++) {
                        var file = initFiles[i];
                        var _disabled = !!disabled;
                        var actionHtml = '';
                        if(fileUploader === 'fileUploader'||fileUploader === 'fileUploader2'||fileUploader === 'fileUploader3' && !_disabled) {
                            /*_disabled = file.createUser != loginUser;*/
                            _disabled = file.createUser != file.updateUser;
                            actionHtml = '<td></td>';
                        }
                        if(viewFlag){
                            _disabled=true;
                        }
                        rownum ++;
                        var tr = $(['<tr id="upload-'+ file.fileNo +'">',
                            ,'<td>'+rownum+'</td>'
                            ,'<td><div class="layui-table-cell"><a style="cursor: pointer" onclick="javascript:viewPdf(\''+file.fileNo+'\',\''+file.fileName+'\')">' + file.fileName + '</a></div></td>'
                            ,'<td style="display: none">'+ (file.size/1014).toFixed(1) +'kb</td>'
                            ,'<td>'+file.uploadUser+'</td>'
                            ,'<td>上传成功</td>'
                            ,'<td>'+file.createTimeText+'</td>'
                            ,(_disabled ? actionHtml : '<td><button data-ignore="true" data-no="' + file.fileNo + '" class="layui-btn layui-btn-mini layui-btn-danger file-delete">删除</button></td>')
                            ,'<td><div class="layui-table-cell"><a style="cursor: pointer" onclick="javascript:viewPdf(\''+file.fileNo+'\',\''+file.fileName+'\')">预览</a>&nbsp;<a style="cursor: pointer" onclick="javascript:downLoad(\''+file.fileNo+'\')">下载</a></div></td>'
                            ,'</tr>'].join(''));
                        tr.find('.file-delete').on('click', function(){
                            var _btn = $(this), fileNo = _btn.data('no');
                            removeFileFromServer(fileNo, function() {
                                var tmpFiles = [];
                                _.each(initFiles, function(f) {
                                    if(f.fileNo !== fileNo) {
                                        tmpFiles.push(f);
                                    }
                                });
                                initFiles = tmpFiles;
                                _btn.parents('tr').remove();
                                $("#"+fileUploader+" tbody tr").each(function (index,item){
                                    $(item).children(":first").html(index+1);
                                });
                                rownum --;
                            });
                            return false;
                        });
                        fileListView.append(tr);
                    }
                }
            }

            if(disabled) {
                if(type !== 'list') {
                    $(this).addClass('layui-disabled').hide();
                    $(this).siblings('span.btn-full').remove();
                }
                else {
                    fileListView.find('button.file-delete').remove();
                }
                return;
            }
            $(this).data('getSelectedFiles', function() {
                var tmpArray = [];
                _.each(uploadedFiles, function(file) {
                    if(_.isArray(file)) {
                        _.each(file, function(f) {
                            tmpArray.push(f);
                        });
                    }
//            		else {
//            			tmpArray.push(file);
//            		}
                });
                _.each(initFiles, function(file) {
                    if(_.isArray(file)) {
                        _.each(file, function(f) {
                            tmpArray.push(f);
                        });
                    }
                    else {
                        tmpArray.push(file);
                    }
                });
                return tmpArray;
            });

            var _upload = upload.render({
                elem: elem
                , accept: 'file'
                , exts: 'doc||docx||pdf||xls||xlsx||zip'
                , multiple: false
                , number: muit ? 0 : 1
                , auto: auto
                , size: 20*1024 // 限制文件大小，单位 KB
                , bindAction: bindAction || uploadBtn
                , url: url
                , method: 'post'
                , choose: function (obj) {
                    //增加遮罩层,上传中不允许再点击上传按钮
                    doprocess();
                    //将每次选择的文件追加到文件队列
                    files = obj.pushFile();
                    var names = [];
                    var _file = $(_this).siblings(':file');
                    if(muit && type == 'list') {
                        // 读取本地文件
                        obj.preview(function(index, file, result){
                            rownum ++;
                            var tr = $(['<tr id="upload-'+ index +'">'
                                ,'<td>'+rownum+'</td>'
                                ,'<td><div class="layui-table-cell">'+ file.name +'</div></td>'
                                ,'<td>'+ $('#realName').val() +'</td>'
                                ,'<td>等待上传</td>'
                                ,'<td class="layui-hide">'+ file.size +'</td>'
                                ,'<td>'+ new Date().format('yyyy-MM-dd hh:mm:ss') +'</td>'
                                ,'<td>'
                                /*,'<button class="layui-btn layui-btn-mini file-reload layui-hide">重传</button>'*/
                                ,'<button class="layui-btn layui-btn-mini layui-btn-danger file-delete">删除</button>'
                                ,'</td>'
                                ,'<td><div class="layui-table-cell">'
                                ,'</div></td>'
                                ,'</tr>'].join(''));

                            // 单个重传
                            tr.find('.file-reload').on('click', function(){
                                obj.upload(index, file);
                            });

                            // 删除
                            tr.find('.file-delete').on('click', function(){
                                var ff = $(tr).data('file');
                                if(ff) {
                                    removeFileFromServer(ff.fileNo, function() {

                                        deleteFile(index); // 删除对应的文件
                                        $(tr).remove();
                                        $("#"+fileUploader+" tbody tr").each(function (index,item){
                                            $(item).children(":first").html(index+1);
                                        });
                                    });
                                }
                                else {
                                    deleteFile(index); // 删除对应的文件
                                    $(tr).remove();
                                }

                                return false;
                            });
                            fileListView.append(tr);
                        });
                        return;
                    }
                    $.each(files, function (i, file) {

                        if (uploadedFiles[i]) {
                            delete files[i];
                        }
                        else {
                            uploadedFiles[i] = file;
                            names.push(file.name);
                        }
                    });
                    // 添加IE8支持
                    if (names.length === 0) {
                        names.push(_file.val());
                    }
                    if (inline) {
                        $(".layui-upload-file").after("<span class='layui-inline layui-upload-choose'>" + names.join(',') + "</span>")
                        if (showdelete) {
                            $(".remove-btn").show();
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
                        style['margin-left'] = '-25px';
                        style['margin-top'] = '-27px';
                        style['left'] = '50%';
                        style['top'] = '50%';
                        $.each(names, function(i, name) {
                            var $el = $('<div class="layui-upload-drag" style="width:' + width + 'px;height:' + height + 'px;padding-left: 5px;padding-right: 5px;"></div>')
                                .mouseover(function () {
                                    $(this).css('background', '#ccc');
                                    if($close) {
                                        $close.show();
                                    }
                                }).mouseout(function () {
                                    $(this).css('background', 'none');
                                    $close.hide();
                                });
                            var $name = $('<div></div>').html(name);
                            var $close = $('<div><i class="layui-icon">&#x1006;</i></div>').css(style).on('click', function () {
                                var ff = $el.data('file');
                                if(ff) {
                                    removeFileFromServer(ff.fileNo, function() {
                                        //deleteFile(ff.fileNo); // 删除对应的文件
                                        var tmpFiles = {};
                                        _.each(uploadedFiles, function(f, k) {
                                            if(f[0] && f[0].fileNo !== ff.fileNo) {
                                                tmpFiles[k] = f;
                                            }
                                            else {
                                                delete files[k];
                                            }
                                        });
                                        uploadedFiles = tmpFiles;
                                        $el.remove();
                                        $(_this).show();
                                    });
                                }
                            });
//                        	var $down = $('<div class="layui-hide file-down"><a href="' + JoyinUtil.getRealPath('/framework/download/' + file.fileNo) + '"><i class="layui-icon">&#xe601;</i></a></div>').css(style);
                            $el.append($close);
//                        	$el.append($down);
                            $el.append($name);
                            if(muit) {
                                $el.addClass('upload-muiltiple');
                            }
                            $(_this).after($el);
                        });
                        if(!muit) {
                            $(_this).hide();
                        }
                    }
                    _file.val('');
                }
                , before: function (obj) {
                    this.data={'pageId':pageId};
                    if(!muit) {
                        layer.load();
                        //调用进度条
                        JoyinProgress.showInDialog('uploadFileProgressBar');
                    }
                }
                , done: function (res, file, upload) {//上传完毕回调
                    layer.closeAll('loading');
                    layer.close(processIndex);
                    uploadedFiles[file] = res;
                    if(muit && type == 'list') {
                        if(res!= null){
                            var tr = fileListView.find('tr#upload-'+ file)
                                ,tds = tr.children();
                            tds.eq(1).find('.layui-table-cell').html('<a style="cursor: pointer" onclick="javascript:viewPdf(\''+res[0].fileNo+'\',\''+res[0].fileName+'\')">' + res[0].fileName + '</a>');
                            tds.eq(7).find('.layui-table-cell').html('<a style="cursor: pointer" onclick="javascript:viewPdf(\''+res[0].fileNo+'\',\''+res[0].fileName+'\')">预览</a>&nbsp;<a style="cursor: pointer" onclick="javascript:downLoad(\''+res[0].fileNo+'\')">下载</a>');
                            tds.eq(3).html('<span style="color: #5FB878;">上传成功</span>');
                            // tds.eq(3).html(''); //清空操作
                            tr.data('file', res[0]);
                            delete files[file]; //删除文件队列已经上传成功的文件
                        }
                        else {
                            this.error(file, upload);
                        }
                    }
                    else {
//                    	$(_this).siblings('.layui-upload-drag').find('.file-down').each(function() {
//                    		$(this).prev().css('margin-left', '-50px');
//                    		$(this).css('margin-left', 0).removeClass('layui-hide');
//                    	});
                        $(_this).siblings('.layui-upload-drag').data('file', res[0]);
                    }
                    if ($.isFunction(callback)) {
                        callback(res);
                    }
                    else {
                        JoyinProgress.clearDefault('uploadFileProgressBar');
                    }
                }
                , error: function (index, upload) {//请求异常回调
                    layer.close(processIndex);
                    layer.closeAll('loading');
                    if(files) delete files[index]; //删除文件队列已经上传成功的文件
                    if(muit && type == 'list') {
                        var tr = fileListView.find('tr#upload-'+ index)
                            ,tds = tr.children();
                        tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
//                        tds.eq(3).find('.file-reload').removeClass('layui-hide'); // 显示重传
                        layer.msg('文件上传异常，请稍后重试！');
                    }
                    else {
                        layer.msg('文件上传异常，请稍后重试！');
                    }
                }
            });
            $(this).data('uploader', _upload);
        });
    };
}(jQuery));