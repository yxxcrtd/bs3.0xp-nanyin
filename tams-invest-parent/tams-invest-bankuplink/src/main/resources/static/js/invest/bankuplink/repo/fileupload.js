$(function() {
	var uploadPath = JoyinUtil.getRealPath('framework/fileUpload/uploadFile');

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
				return layer.msg(JoyinUtil.getMessage('FW_E_0010'));
			}
			var parentCallback = function(){};
			if($.isFunction(top.window.uploadCallback)) {
				parentCallback = top.window.uploadCallback;
			}
			if($.isFunction(parent.window.uploadCallback)) {
				parentCallback = parent.window.uploadCallback;
			}
			parentCallback.call(parentCallback, res[0].fileName, res);
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
					return layer.msg('CFETS_UPLOAD_FAILURE');
				}
				// 上传成功
				layer.msg('CFETS_UPLOAD_SUCCESS');
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
			number: 1,
			accept: 'file',
			exts: exts,
			bindAction: '#dragUploadAction',
			size: maxFileSize, // 限制文件大小，单位 KB
			done: uploadCallback,
			before: function() {
				destoryMsg();
			},
			error: function(){
				layer.msg(JoyinUtil.getMessage('FW_E_0010'));
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
				var $el = $('<div class="layui-upload-drag" style="float: left; padding: 5px;" id="fileUploadName"></div>')
					.mouseover(function() {
						$(this).css('background', '#ccc');
						$close.show();
					}).mouseout(function() {
						$(this).css('background', 'none');
						$close.hide();
					});
				var $name = $('<div></div>').css('margin', '25px').html(names.join(','));
				var $close = $('<div><i class="layui-icon">&#x1006;</i></div>').css({
					width: '14px',
					height: '14px',
					position: 'absolute',
					'margin-left': '-25px',
					'margin-top': '-27px',
					color: '#000',
					display: 'none',
					left: '50%',
					top: '50%'
				}).on('click', function() {
					$el.remove();
					$('#test10').show();
					$(':file').val('');
					$.each(files, function(i) {
						delete files[i];
					});
				});
				$el.append($name);
				$el.append($close);
				$('#test10').after($el).hide();
				$('#dragUploadAction').removeClass('layui-btn-disabled').prop('disabled', false);
				$(':file').val('');
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
			pageId: currentPageId || parent.pageId,
			exts: exts
		});
	});
	
	$('#dragUploadAction').click(function() {
		JoyinUtil.showLoading('body', JoyinUtil.getLabel('FwFileUpload.processing'));
		$('#dragUploadAction').prop('disabled',true).addClass('layui-btn-disabled');
	});

	// 批量删除
	/*$('#CfetsBondDealQuotedPrice001_import_btn_delete').click(function() {
		if (previewTable) {
			var selArr = previewTable.getAllCheckedRows();
			if (selArr && $.isArray(selArr) && selArr.length > 0) {
				$.each(selArr, function(i, row){
					previewTable.removeRow(row);
				});
				// 重新验证，删除了行，done事件稍后还会执行
				window.parent.CfetsBondDealQuotedPrice001.validatePreviewResult(previewTable.getAllRows());
			}
		}
	});*/

	// 提交
	$('#CfetsRepoDealQuotedPrice001_import_btn_submit').click(function() {
        if (previewTable) {
            var dataRows = previewTable.getAllRows();
            if(dataRows.length==0){
            	Joyin.error("导入预览表格数据不能为空!");
            	return;
            }
            //处理交易对手
            $.each(dataRows,function(i,val){ //两个参数，第一个参数表示遍历的数组的下标，第二个参数表示下标对应的值
            	 if(val.counterId){
            		 var orgIdList = [];
            		 orgIdList.push(val.counterId)
            		 val.counterIdList = orgIdList
            	 }
            })
        	//window.parent.CfetsBondDealQuotedPrice001.quoteImport(dataRows);
        	Joyin.ajaxJson(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice001/importFileCheck', dataRows, 
        			function(result) {//AjaxResult
        				Joyin.confirm(result.data, 
        						function () {
        					var allDto = {};
        					allDto.repoQuoteImportPreviewDtoList =dataRows;
        					allDto.importFlow = true;
        				    	WorkFlow.doSubmitList(basePath + 'invest/bankuplink/repo/CfetsRepoDealQuotedPrice001/save', allDto, "CfetsRepoDealQuotedPrice002_btn_submit","CfetsRepoDealQuotedPrice002",
        				    		function() {
		                                var index = window.parent.layer.getFrameIndex(window.name);
		                                window.parent.layer.close(index);
		                            })
        						})
                	});
        }
	});
});

var previewTable;
var loadCount = 0;



function showErrorMessage(result) {
	previewTable = $('#importResultTable').show().joyintable({
        cols:[
               {field:'rowNumber', width:55, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.rowNumber', align: 'center', sortable:false}
              ,{field:'validationErrorMsg', width:180, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.validationErrorMsg', sortable:false}
              ,{field:'validationWarnMsg', width:180, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.validationWarnMsg', sortable:false}
              ,{field:'sendStatusStr', width:80, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.sendStatus', sortable:false, align: 'center', render : function(data, type, row, meta) {
 	               if (data == sendStatus_fail){
 	            	   return '<a style="color:red;font-weight:600;">' + data + '</a>';
 	               }
 	               else if (data == sendStatus_unsend){
 	            	   return '<a style="color:black;font-weight:600;">' + data + '</a>';
 	               }
 	               else {
 	            	   return '<a style="color:green;font-weight:600;">' + data + '</a>';
 	               }
 	       		}
              }
              ,{field:'entrustId', width:165, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.entrustId', sortable:false}
              ,{field:'invManagerId', width:120, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.invManagerId', sortable:false}
              ,{field:'invManagerName',visible: false, width:120, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.invManagerId', sortable:false}
              ,{field:'portfolioId', width:120, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.portfolioId', sortable:false}
              ,{field:'portfolioName', width:160, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.portfolioName', sortable:false}
              ,{field:'quoteType', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.quoteType', sortable:false,render : function(data, type, row, meta) {
 	               if (data == '01'){
 	            	   return '对话报价';
 	               }else {
 	            	   return data;
 	               }
 	       		}
              }

			  ,{field:'psStr', width:90, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.ps', sortable:false}
			  ,{field:'mySideAbbr', width:180, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.mySide', sortable:false}
              ,{field:'mySideTraderName', width:100, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.mySideTrader', sortable:false}
              ,{field:'counterName', width:180, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.counterId', sortable:false}
              ,{field:'counterTraderName', width:110, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.counterTrader', sortable:false}

			  ,{field:'repoMethod', width:110, title: '回购方式*', sortable:false}
			  ,{field:'term', width:90, title: '回购期限(天)*', sortable:false,render : function(data, type, row, meta) {
 	               if (data == 0){
 	            	   return '';
 	               }else {
 	            	   return data;
 	               }
 	       		}
              }
			  ,{field:'price', width:90, title: '回购利率(%)*', align: 'right', sortable:false,digit:4,type:'number',fixtype:2}
			  ,{field:'dealAmt', width:85, title: '交易金额(元)', sortable:false}
	
			  ,{field:'bondCode', width:85, title: '质押债券代码', sortable:false}
			  ,{field:'bondAbbrName', width:85, title: '质押债券简称', sortable:false}
			  ,{field:'pledgePrin', width:85, title: '质押券面总额(万)', sortable:false}
			  ,{field:'pledgeRate', width:85, title: '折算比例(%)', sortable:false}
	
			  ,{field:'settleSpreedStr', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.settleSpreed', sortable:false}
              ,{field:'settleTypeStr', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.settleType', sortable:false}
			  ,{field:'vDeliveryTypeStr', width:85, title: '首次结算方式*', sortable:false}
			  ,{field:'mDeliveryTypeStr', width:85, title: '到期结算方式*', sortable:false}



              /*,{field:'finprodMarketId', width:110, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.finprodId', sortable:false}
              ,{field:'finprodAbbr', width:140, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.finprodAbbr', sortable:false}
              ,{field:'psStr', width:90, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.ps', sortable:false}
              
              ,{field:'entrustAmt', width:90, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.entrustAmt', align: 'right', sortable:false,digit:2,type:'number',fixtype:2}
              ,{field:'price', width:90, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.price', align: 'right', sortable:false,digit:4,type:'number',fixtype:2}
              ,{field:'matureYield', width:80, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.matureYield', align: 'right', sortable:false,digit:4,type:'number',fixtype:2}
              ,{field:'exerYield', width:75, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.exerYield', align: 'right', sortable:false,digit:4,type:'number',fixtype:2}
              ,{field:'settleSpreedStr', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.settleSpreed', sortable:false}
              ,{field:'settleMethodStr', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.settleMethod', sortable:false}
              ,{field:'settleTypeStr', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.settleType', sortable:false}*/
              
              ,{field:'calculateField', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.calculateField', sortable:false, visible: false}
              ,{field:'unitInt', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.unitInt', sortable:false, visible: false}
              ,{field:'unitFprice', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.unitFprice', sortable:false, visible: false}
              ,{field:'cpriceAmt', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.cpriceAmt', sortable:false, visible: false}
              ,{field:'intAmt', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.intAmt', sortable:false, visible: false}
              ,{field:'fpriceAmt', width:85, title: 'CfetsBondDealQuotedPrice001.fileUpload.table.fpriceAmt', sortable:false, visible: false}
              ,{
                  field : 'quoteType1',
                  title : 'CfetsBondDealQuotedPrice001.fileUpload.table.operation',
                  fixed: 'right',
                  width:100,
                  sortable: false,
                  render : function(data, type, row, meta) {
                	  return "<a class='CfetsBondDealQuotedPrice001_import_delete layui-btn'>删除</a>";
                  }
              }
          ],
        searchOnInit: true,
        resize: true,
        page: false,
		data: result || [],
		height: 375,
        done:function(rows) {
        	loadCount += 1;
        	// 格式化inValidFieldNames字段
        	$.each(rows, function(i, previewDto){
        		if (previewDto.inValidFieldNames && previewDto.inValidFieldNames.length > 0) {
        			$.each(previewDto.inValidFieldNames, function(j, fieldName) {
            			var className = ".layui-table-cell.laytable-cell-" + loadCount + "-" + fieldName;
            			$($("div" + className)[i+1]).addClass("errorBorder");
        			});
        		}
        	});

        	let previewTable = this;
			/**
			 * 删除
			 */
			/*this.bind('click','.CfetsBondDealQuotedPrice001_import_delete', function(row) {
				previewTable.removeRow(row);
				// 重新验证，删除了行，done事件稍后还会执行
				window.parent.CfetsBondDealQuotedPrice001.validatePreviewResult(previewTable.getAllRows());
			});*/

			if (window.parent.CfetsRepoDealQuotedPrice001.hasError) {
			    $('#CfetsRepoDealQuotedPrice001_import_btn_submit').toggle(false);
			}
			else {
			    $('#CfetsRepoDealQuotedPrice001_import_btn_submit').toggle(true);
			}
        },
	});
	previewTable.bind('click','.CfetsBondDealQuotedPrice001_import_delete', function(row) {
		previewTable.removeRow(row);
		// 重新验证，删除了行，done事件稍后还会执行
		validatePreviewResult(previewTable.getAllRows());
	});
}

// 验证并设置当前的预览数据中有无错误消息
    var validatePreviewResult = function (previewDtoList) {
    	// 有错误信息则不能提交；预警信息可以继续提交
    	window.parent.CfetsRepoDealQuotedPrice001.hasError = false;

    	$.each(previewDtoList, function(i, previewDto){
    		if (previewDto.validationErrorMsg && previewDto.validationErrorMsg.length > 0) {
    			window.parent.CfetsRepoDealQuotedPrice001.hasError = true;
    			return false; //break;
    		}
    	});
    	
    	return window.parent.CfetsRepoDealQuotedPrice001.hasError;
    };


//JoyinUtil.hideLoading();
$('#dragUploadAction').prop('disabled', false).removeClass('layui-btn-disabled');

// 订阅现卷报价导入时的发送状态
SocketClient.subscribe('/bondQuoteImport/sendResult', function(result) {
    var message = $.parseJSON(result.responseMessage);
    console.log(message);
    var rowNumber = message.rowNumber;
    var sendStatusStr = message.sendResult ? sendStatus_sent : sendStatus_fail;

    if (previewTable) {
    	previewTable.renderRow(rowNumber - 1, {sendStatusStr: sendStatusStr});
    }
},'user');