$(function() {

	layui.use('upload', function(){
		var $ = layui.jquery
		,upload = layui.upload
		,uploadedFiles = [];
		var uploadCallback = function(res, file, reupload) {
			// 记录已上传的的文件
			uploadedFiles.push(file);
			$('#test10').show();
			$('#fileUploadName').remove();
			// 如果上传失败
			if(res && res.code > 0){
				return layer.msg('上传失败');
			}
			var parentCallback = function(){};
			if($.isFunction(top.window.uploadCallback)) {
				parentCallback = top.window.uploadCallback;
			}
			if($.isFunction(parent.window.uploadCallback)) {
				parentCallback = parent.window.uploadCallback;
			}
			var fileName = res.fileName;
			if(fileName.indexOf(',') !== -1) {
				fileName = fileName.split(',');
			}
			parentCallback.call(parentCallback, fileName, res);
		};
		
		// 普通图片上传
		var uploadInst = upload.render({
			elem: '#test1',
			url: uploadPath,
			before: function(obj){
				// 预读本地文件示例，不支持ie8
				obj.preview(function(index, file, result){
					$('#demo1').attr('src', result); // 图片链接（base64）
				});
			},
			done: uploadCallback,
		    error: function(){
				// 失败状态，并实现重传
				var demoText = $('#demoText');
				demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
				demoText.find('.demo-reload').on('click', function(){
					uploadInst.upload();
				});
			}
		});
		
		// 选中文件后销毁验证消息
		var destoryMsg = function() {
			if($('#importResultTable').length !== 0) {
				$('#msgArea').empty();
			}
			$table = $('<table class="table table-striped" id="importResultTable" style="display: none; width:100%"></table>');
			$('#msgArea').append($table);
		};
		
		// 多图片上传
		upload.render({
			elem: '#test2',
			url: uploadPath,
			multiple: true,
			before: function(obj){
				// 预读本地文件示例，不支持ie8
				obj.preview(function(index, file, result){
					$('#demo2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
				});
			},
			done: function(res){
				// 如果上传失败
				if(res && res.code > 0){
					return layer.msg('上传失败');
				}
				// 上传成功
				layer.msg('上传成功');
				if($.isFunction(top.window.uploadCallback)) {
					top.window.uploadCallback(top.window.uploadCallback, res);
				}
			}
		});
		// 允许上传的文件后缀
		upload.render({
			elem: '#test4',
			url: uploadPath,
			accept: 'file', //accept: 'video' // 视频, accept: 'audio' // 音频
			exts: exts,
			done: function(res){
				if($.isFunction(top.window.uploadCallback)) {
					top.window.uploadCallback(top.window.uploadCallback, res);
				}
			}
		});
		
		// 选完文件后不自动上传
		upload.render({
			elem: '#test8',
			url: uploadPath,
			accept: 'file',
			exts: exts,
			auto: false,
			//multiple: true,
			bindAction: '#test9',
			before: function() {
				destoryMsg();
			},
			done: uploadCallback
		});
		
		// 拖拽上传
		upload.render({
			elem: '#test10',
			url: uploadPath,
			auto: false,
			accept: 'file',
			exts: exts,
			bindAction: '#dragUploadAction',
			size: 204800, // 限制文件大小，单位 KB
			done: uploadCallback,
			before: function() {
				destoryMsg();
			},
			error: function(){
				layer.msg('上传失败');
			},
			choose: function(obj) {
				var files = obj.pushFile(); // 将每次选择的文件追加到文件队列
				var names = [];
				$.each(files, function(i,file) {
					if(uploadedFiles.indexOf(i) != -1) {
						delete files[i];
					}
					else {
						names.push(file.name);
					}
				});
				// 添加IE8支持
				if(names.length === 0) {
					var _file = $(':file').val();
					names.push(_file);
				}
				var $el = $('<div class="layui-upload-drag" style="width:520px; float: left; padding: 5px;" id="fileUploadName"></div>')
					.mouseover(function() {
						$(this).css('background', '#ccc');
						$close.show();
					}).mouseout(function() {
						$(this).css('background', 'none');
						$close.hide();
					});
				var $name = $('<div></div>').css('margin', '25px').html(names.join(','));
				var $close = $('<div><i class="glyphicon glyphicon-remove"></i></div>').css({
					width: '14px',
					height: '14px',
					position: 'absolute',
					color: '#000',
					display: 'none',
					left: $('#test10').width() - (14/2 + 5),
					top: 5
				}).on('click', function() {
					$el.remove();
					$('#test10').show();
					$(':file', '#test10').val('');
					$.each(files, function(i) {
						delete files[i];
					});
				});
				$el.append($name);
				$el.append($close);
				$('#test10').after($el).hide();
				$('#dragUploadAction').prop('disabled', false);
			}
		});
		
		// 多文件列表示例
		var demoListView = $('#demoList')
		,uploadListIns = upload.render({
			elem: '#testList',
			url: uploadPath,
			accept: 'file',
			multiple: true,
			auto: false,
			bindAction: '#testListAction',
			choose: function(obj){
				var files = obj.pushFile(); // 将每次选择的文件追加到文件队列
				// 读取本地文件
				obj.preview(function(index, file, result){
					var tr = $(['<tr id="upload-'+ index +'">'
					            ,'<td>'+ file.name +'</td>'
					            ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
					            ,'<td>等待上传</td>'
					            ,'<td>'
					            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
					            ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
					            ,'</td>'
					            ,'</tr>'].join(''));
					
					// 单个重传
					tr.find('.demo-reload').on('click', function(){
						obj.upload(index, file);
					});
					
					// 删除
					tr.find('.demo-delete').on('click', function(){
						delete files[index]; // 删除对应的文件
						tr.remove();
					});
					
					demoListView.append(tr);
				});
			},
			done: function(res, index, upload){
				if(res.code == 0){ // 上传成功
					var tr = demoListView.find('tr#upload-'+ index)
					,tds = tr.children();
					tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
					tds.eq(3).html(''); // 清空操作
					delete files[index]; // 删除文件队列已经上传成功的文件
					uploadCallback(res);
					return;
				}
				this.error(index, upload);
			},
			error: function(index, upload){
				var tr = demoListView.find('tr#upload-'+ index)
				,tds = tr.children();
				tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
				tds.eq(3).find('.demo-reload').removeClass('layui-hide'); // 显示重传
			}
		});
	});

	// 模板下载
	$('#tplDowload').click(function() {
		Joyin.exportExcel('downloadTemplate', {
			pageId: parent.pageId,
			exts: exts
		});
	});
	
	$('#dragUploadAction').click(function() {
		$("#loading").show();
		$('#dragUploadAction').prop('disabled',true);
	});
});

function showErrorMessage(result) {
	$('#importResultTable').show().joyintable({
        columns: [
                  { title: "EXCEL行号", data: 'rowNum', width:120, align: 'center', sortable:false},
                  { title: "标识码", data: 'validKey', width:180, align: 'center', sortable:false},
                  { title: "错误信息", data: 'errorInfo', align: 'left', sortable:false}
                  ],
        searchOnInit: true,
        checkbox: false,
        autoresize: false,
		data: result || [],
		height: 350
	});
	$("#loading").hide();
	$('#dragUploadAction').prop('disabled', false);
}