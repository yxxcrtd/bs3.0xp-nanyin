(function($) {
	// 重写layui的排序方法
	layui.sort = function(e, t, n) {
        var o = JSON.parse(JSON.stringify(e || []));
        var flag=false;
        var cache = layui.table.cache;
        _.each(cache, function(obj,key) {
        	if(key.indexOf('_config') != -1 && cache[key].loadonce === true) {
        		if (n) {
            		cache[key].allData.sort(function(a,b){
                		return a[t] > b[t] ? -1 : 1;
                    });
        		} else {
            		cache[key].allData.sort(function(a,b){
                		return a[t] < b[t] ? -1 : 1;
                    });
        		}
        		var pageNumber = $(cache[key].elem).next().find('.layui-laypage-curr').text();
        		var pageDataCount = $(cache[key].elem).next().find('.layui-laypage-limits').find('select').val();
        		var startPos = (parseInt(pageNumber)-1)*parseInt(pageDataCount);
        		var filterData = cache[key].allData.slice(startPos, startPos+o.length);
        		if (!(filterData === undefined || filterData.length === 0)) {
        			o =  filterData;
        		}
        		
        		_.each(cache[key].cols || [], function(temp) {
        			if(temp.field==t){
                		if(temp.type&&(temp.type=="number"||temp.type=="money")){
                			flag=true;
                			return;
                		}
                	}
        		});
        		return;
        	}
        });

        return (window.afterSort && window.afterSort === true) ? e : t ? (o = flag?o.sort(function(a,b){
        		return parseFloat(a[t])-parseFloat(b[t]);
        }):_.sortBy(o, t), n && o.reverse(), o) : o;
	}
	
	// 默认日期格式
	var defaultDateStyle = 'yyyy-MM-dd';
	// 插件样式定义
	var plugins = {
		// 单个日期控件
		dateSelect: '.input-dateSelect'
		// 日期区间控件
		,dateRangeSelect: '.input-dateRangeSelect'
	    // 金额输入框
		,textMoney: '.text-money'
	    // 数值输入框
		,textNumber: '.text-number'
		// 按钮
		,button: '.layui-btn'
		// 上传控件
		,fileUpload: '.upload'
	    // 下拉框（单选，多选，单选联想）
		,select: '.input-select'
	    // 多选联想下拉框
		,lenovoSelect: '.input-lenovoselect'
		//单选按钮
		,inputCheckBox: '.input-checkbox'
		//列表按钮区域
		,formButtonArea: '.form-button-area'
        // 列表查询条件区域
	    ,conditionArea: '.condition-area'
    	 //带图标的输入框
		,inputFuzzyText: '.input-fuzzy-text'
	    //进度条
        ,progressBar:'.input-progressBar'
	};
	
	/**
	 * Date类扩展
	 */
	Date.prototype.format = function(formatStr) {
		return layui.util.toDateString(this, formatStr || defaultDateStyle);
	};
	window.onerror=function(){};
	window.onhelp=function(){return false;}
	/**
	 * 禁用浏览器中除F5以外的快捷键
	 */
	document.onkeydown=function(e) {
		e = window.event || e || e.which;
		if (e.keyCode == 112 || e.keyCode == 113
	            || e.keyCode == 114 || e.keyCode == 115
	            /*|| e.keyCode == 116*/ || e.keyCode == 117
	            || e.keyCode == 118 || e.keyCode == 119
	            || e.keyCode == 120 || e.keyCode == 121
	            || e.keyCode == 122 || e.keyCode == 123) {
	            e.keyCode = 0;
	            return false;
	        }
	};

	/**
	 * 自定义表单验证规则
	 */
	layui.form.verify({
		username : function(value, item) { // value：表单的值、item：表单的DOM对象
			if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
				return '用户名不能有特殊字符';
			}
			if (/(^\_)|(\__)|(\_+$)/.test(value)) {
				return '用户名首尾不能出现下划线\'_\'';
			}
			if (/^\d+\d+\d$/.test(value)) {
				return '用户名不能全为数字';
			}
		},
		pass : [ /^[\S]{6,20}$/, '密码必须6到20位，且不能出现空格' ]
	});
	
	/* *********************************
	 * 重写jquery.val方法
	 * ********************************/
	if(!_oldVal){
	    var _oldVal = $.fn.val;
	}
	$.fn.val = function() {
	    var value = _oldVal.apply(this,arguments);
	    if(arguments.length === 0) {
	        if(this.is(plugins.select) && this.attr('multiple') && this.attr('multiple') !== false && this.attr('multiple') !== 'false') {
	        	var flag = false;
	        	if(_.isString(value) && value.indexOf('_all') != -1) {
	        		value = value.split(',');
	        		flag = true;
	            }
	        	if(_.isArray(value)){
	                var newArr = [];
	                $(value).each(function(i, tt) {
	                   if (tt != '' && tt != '_all') {
	                       newArr.push(tt);
	                   }
	                });
	                value = flag ? newArr.join(','): newArr;
	            }
	            return value;
	        }
	        if ((this.hasClass('text-number') || this.hasClass('text-money')) && _.isString(value)) {
	            return value.replace(/,/g,'');
	        }
	    }
	    return value;
	};
	
	$.ajaxSetup ({
        cache: false //关闭AJAX缓存
    });
	
	/**
	 * jquery ajax扩展
	 */
	var loadArray = [];
	$(document).ajaxSend(function(event, req, setting) {
	    var param = setting.data;
	    if(typeof param === 'string') {
	        if(param.length > 0) {
	            param += '&';
	        }
	        param += 'isTodoList=' + WorkFlow.getTodoList();
	    }
	    else if(typeof param === 'object') {
	        param.isTodoList = WorkFlow.getTodoList();
	    }
	    var e = JoyinUtil.getEvent();
	    if(e && e.currentTarget && $(e.currentTarget).hasClass('layui-btn')) {
	    	loadArray.push(layui.layer.open({type:3}));
	    }
	    setting.data = param;
	});
	
	var loadArrayForLayui = [];
	layui.$(document).ajaxSend(function(event, req, setting) {
	    var e = JoyinUtil.getEvent();
	    if(e && e.currentTarget && $(e.currentTarget).hasClass('layui-btn')) {
	    	loadArrayForLayui.push(layui.layer.open({type:3}));
	    }
	});
	
	$(document).ajaxComplete(function(event, req, res) {
		layui.layer.close(loadArray.pop() || 0);
	});
	layui.$(document).ajaxComplete(function(event, req, res) {
		layui.layer.close(loadArrayForLayui.pop() || 0);
	});
	
	$(document).ajaxError(function(evt, req, settings) {
		if (!req || req.status === 200 || req.status === 0) {
			return false;
		}
		var msg = '错误：';
		var result = {};
		if(req.responseText) {
    	   try {
    		   result = $.parseJSON(req.responseText);
    	   } catch(e){}
        }
		if (req.status === 404) {
			msg = '您访问的地址[' + (result.path || $.trim(req.responseText)) + ']不存在！';
		}
		else {
			if (req.status === 401) {
				return Joyin.confirm('您没有权限访问或者session已经失效，请重新登录。', function() {
					if (self.frameElement != null && self.frameElement.tagName == "IFRAME") {
				        top.location.href = basePath;
				    }
				});
			}
			if(req.status === 403) {
				//这里可能会空在导致失败
				return Joyin.error("由于您权限不足，当前地址无法访问，请联系管理员!");
			}
	        if(req.status === 500) {
	        	if($('iframe')[0]){
	        		//这里可能会空在导致失败
	        		$('iframe')[0].contentWindow.JoyinUtil.hideLoading();
	        	}
	        	if(result && typeof result.message === 'string') {
	        		msg = result.message;
	        	}
//    		   return Joyin.processResult(result);
	        }
	        msg = '系统异常，请联系管理员';
			if (window.console) {
				window.console.error(req.responseJSON ? req.responseJSON.message : msg);
			}
		}
		Joyin.error(msg);
	});
	
	/**
	 * 快捷检索功能
	 * @returns
	 */
	function searchOnPressEnter() {
		$('body').on('keydown', 'form:not([access="none"])', function(e) {
			// 阻止表单默认提交：当form中只有一个输入框时，enter将会导致自动提交
			if(e.keyCode === 13) {
				return false;
			}
		}).on('keyup', function(e) {
			if(e.keyCode === 13 && !$(e.target).is('.layui-table-edit')) {
				var target = $(e.currentTarget).find('form:not([access="none"])');
				if(!target.is('form')) {
					target = $(e.target).parents('form');
				}
				// 延迟200毫秒后执行查询，为控件本身的enter事件提供充足的响应时间
				setTimeout(function() {
					var searchBtn = $('.btn-search', target);
					if(searchBtn.length == 0) {
						searchBtn = $('.btn-search', target.prev());
					}
//					searchBtn.trigger('click');
					if(searchBtn.length > 0) {
						searchBtn[0].click();
					}
				},200);
			}
		});
	}
	
	/**
	 * 编辑和新建模式，添加required红星标注
	 * @returns
	 */
	function addRequiredClass() {
        // 查找label
        var _findPrevLabel = function(element, level) {
            var $el = element.prev();
            if(!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if($el.length === 0) {
                    return _findPrevLabel(element.parent(), level-1);
                }
            }
            return $el;
        };
        
        // 非查看模式
        if(typeof pageModel !== 'undefined' && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            if($('.must').length) return;
            $('*[required]').each(function() {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
	}
	
	/**
	 * 将表单数据序列化为json
	 * flag : 是否序列化不可见属性的值，默认为false（否）
	 * onlyCheckedForGrid: 是否仅序列化表格的选中数据， 默认为false（否）。 该属性仅当画面中有表格时生效
	 */
    $.serializeToObject = function(form, flag, onlyCheckedForGrid) {
        var _createObject = function(object, name, value) {
            var param = {};
            if(name && (typeof value !== 'undefined' && value !== null)) {
                var index = name.indexOf('.');
                if(index != -1) {
                    var supName = name.substring(0, index);
                    var subName = name.substring(index + 1);
                    var subObject = object[supName] || {};
                    param[supName] = $.extend({}, subObject, _createObject(subObject, subName, value));
                }
                else {
                    param[name] = value;
                }
            }
            return param;
        };

        var paramStr = $(form).serialize().replace(/\+/g," ");
        // 将汉字转义
        paramStr = decodeURIComponent(paramStr);
        var result = {};
        if (paramStr) {
            var params = paramStr.split('&');
            $.each(params,function(i, p) {
                var key = p.split('=')[0];
                var val = p.split('=')[1];
                if($('input[name="' + key + '"]').is(plugins.textMoney)) {
                	var memory = $('input[name="' + key + '"]').data("moneymemory");
                	if(memory) {
                		val = memory.replace(/,/g, '');
                	}
                }
                if($('select[name="' + key + '"]').is(plugins.select)) {
                	var multiple = $('select[name="' + key + '"]').attr("multiple");
                	// 如果为复选下拉框
                	if(multiple && multiple !== 'false') {
                		if(val){
                			if (result[key]) {
                				result[key].push(val);
                			} else {
                				result[key] = [val];
                			}
                		}
                	} else {
                        if (result[key]) {
                            result[key] += ',' + val;
                        } else {
                            result[key] = val;
                        }
                    }
                }
                // 非复选下拉框
                else {
                    if (result[key]) {
                        // checkbox 等
                        result[key] += ',' + val;
                    } else {
                        result[key] = val;
                    }
                }
            });
        }

        // 序列化disabled输入框和jqGrid
        var queryStr = ':input:disabled:not(:button):visible, table.layui-table:visible';
        if(flag) {
            queryStr = ':input:disabled:not(:button), table.layui-table';
        }
        $(queryStr).each(function() {
            var $el = $(this);
            var name = $el.attr('name');
            var id = $el.attr('id');
            if(name) {
                var value = '';
                if(this.tagName === 'TABLE') { // table
                	var _table = $(this).data('joyintable');
                    if(onlyCheckedForGrid && onlyCheckedForGrid === true)  {
                        value = _table.getAllCheckedRows();
                    }
                    else {
                    	value = _table.getAllRows();
                    }
                }
                else if($el.is(':checkbox') || $el.is(':radio')) {
                    if($el.is(':checked')) {
                        if(result[name]) {
                            value = result[name] + ',';
                        }
                        value += $el.val();
                    }
                    else {
                        value = undefined;
                    }
                }
                else if($el.is(plugins.textMoney)) {
                    value = $el.data("moneymemory").replace(/,/g, '');
                }
                else {
                    value = $el.val();
                }
                if(typeof value != "undefined") {
                    result = $.extend(result, _createObject(result, name, value));
                }
            }
        });

        // 处理result中原本的 aaa.bbb 属性
        for(var key in result) {
            if(result.hasOwnProperty(key)) {
                if(key.indexOf('.') != -1) {
                    var value = result[key];
                    delete result[key];
                    result = $.extend(result, _createObject(result, key, value));
                }
            }
        }
        // 画面模式
        result.pageModel = pageModel;

        for(var p in result) {
            if(result[p] && typeof result[p] === 'string' && result[p].indexOf(',')===(result[p].length - 1)) {
                result[p] = result[p].substring(0, result[p].length - 1);
            }
        }
        return result;
    };
    
    $.initSerializeToObject = function() {
        var result = $.serializeToObject("form",false,false);
        //取label
        if(!result){
            return null;
        }
        for(var p in result) {
            var label = $("[name='"+ p +"']").parents().has("label").first().children("label").text();
            if ($("[name='"+ p +"']").prop('nodeName') === 'TABLE') {
                label = $("[name='"+ p +"']").attr('title');
            }
            result[p] = {"odata":result[p], "label":label};
        }
        return result ;
    }
	
    /**
     * 禁用画面元素
     * 1. 如果某个元素被指定data-ignore=true，则忽略该元素
     * 2. 如果是来自代码事项的经办节点以外，无视data-ignore属性设置
     */
    window.disablePageElement = function(formId) {
        var _disableFun = function() {
            if(formId && formId.indexOf('#') != 0) {
                formId = '#' + formId;
            }
            // 禁用画面元素
            var $target = $(formId || document.forms[0] || '#business');
            if($target.length === 0) {
                $target = $('body');
            }
            // edit by dubaojun 2017-03-01:禁用画面的元素时，表格的选择功能不需要禁用,表格中按钮带有button-grid的class属性，则不需要禁用
            var elements = $target.find(':input:visible').filter(function() {
                var $le = $(this);
                // ignore 指定为ALL，忽视代办事项
                if($(this).hasClass('select2-search__field')) {
                    $le = $(this).parents('.select2').prev();
                }
                if($le.data('ignore') && $le.data('ignore') === 'all') {
                    return false;
                }
                return (!$le.data('ignore') || $le.data('ignore') === 'false');
            });
            elements.prop('disabled', true);
            elements.parents('.select2').prev().prop('disabled', true);
//            if(navigator.userAgent.indexOf('MSIE 9') > 0) {
//                $(':input.input-select').each(function() {
//                	var select = $(this);
//                	if(!select.is(':disabled')) {
//                		select.prop('disabled', true);
//                		//ie 在控件已被初始化后添加无效
//                		select.select2('destroy');
//                		select.textSelect();
//                	}
//                });
//            }
//            else {
            	$(':input.input-select').prop('disabled', true);
//            }
            // layui-switch
            $target.find(':input[lay-skin="switch"]').each(function() {
                var $le = $(this);
                if(!($le.data('ignore') && ($le.data('ignore') === 'all' || $le.data('ignore') === true))) {
                	$le.prop('disabled', true).next().removeClass('layui-form-onswitch').addClass('layui-checkbox-disbaled layui-disabled');;
                }
            });
            
            $target.find('.layui-btn:not(.btn-cancel):visible').filter(function() {
                var $le = $(this);
                if($le.data('ignore') && $le.data('ignore') === 'all') {
                    return false;
                }
                return (!$le.data('ignore') || $le.data('ignore') === 'false');
            }).addClass('layui-btn-disabled').off();
            // add by dubaojun 2017-03-01:查看模式的表格，如果没有加data-ignore=true属性，直接设置多选框消失
            var elementstable = $target.find('table').filter(function() {
                // ignore 指定为ALL，忽视代办事项
                if($(this).data('ignore') && $(this).data('ignore') === 'all') {
                    return false;
                }
                return (!$(this).data('ignore') || $(this).data('ignore') === 'false');
            });
            elementstable.find(':checkbox').prop('disabled',true);
            elementstable.find('.edit,.delt').prop('disabled',true);
            elementstable.find('.layui-icon').prop('disabled',true);
            $target.find(':input:visible').removeAttr('placeholder');
            $target.find('span.select2-selection__placeholder').remove();
        };
        _disableFun();
        // 每隔0.5秒钟执行一次，一共执行20次后结束，为了确保画面所有元素都处理完
        var count = 0;
        var timer = setInterval(function() {
            _disableFun();
            count++;
            if(count >= 20) {
                clearInterval(timer);
                timer = null;
            }
        }, 500);
    };
    
    
	$(function() {
		/**
		 * 控件
		 */
		/*列表按钮区*/
		$(plugins.formButtonArea).buttonArea();
		/*列表查询条件区域*/
		$(plugins.conditionArea).conditionArea();
		/*单日期控件*/
		$(plugins.dateSelect).textDate(false, defaultDateStyle);
		/*日期区间*/
		$(plugins.dateRangeSelect).textDate(true, defaultDateStyle);
		/*金额文本框*/
		$(plugins.textMoney).textMoney();
		/*数值类文本框*/
		$(plugins.textNumber).textNumber();
		/*按钮*/
		$(plugins.button).layButton();
		/*上传控件*/
		$(plugins.fileUpload).uploadFile();
		/*单选下拉框*/
		$(plugins.select).textSelect();
		/*多选下拉框*/
		//$(".input-selectMultiple").textSelect();
		/*单选联想下拉框*/
		//$(".input-lenovoMultiple").textSelect();
		/*多选联想下拉框*/
		$(plugins.lenovoSelect).associateInput();
		/*单选按钮*/
		$(plugins.inputCheckBox).inputCheckBox();
		/*带图标的输入框*/
		$(plugins.inputFuzzyText).inputFuzzyText();
		/*进度条*/
        $(plugins.progressBar).progressBarInit();
        //按钮组
        $(".btn-select").click(function () {
            $(".btn-select-wrap").toggle();
        });
        // 查看模式
        if(pageModel != null && typeof pageModel !== 'undefined' && (pageModel === PAGE_MODEL_REFERENCE
                || (typeof isReference !== 'undefined' && isReference.toString() === 'true'))) {
            // 禁用画面元素
            disablePageElement('body');
        }
        /*取消按钮点击的事件*/
        $(".btn-cancel").bind('click', function () {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.close")), function(){
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            });
        });

         //表头显示
         $(".laytable-stylediv").bind('click', function(event){
             event=event||window.event;
             event.stopPropagation(); 
         });
         $(document).click(function(e){
         	if(window.__cancelled) return;
            $(".btn-filter").parent().next(".joyin-filter-form").hide();
            $(".laytable-moneyTypeDiv").hide();
            $(".laytable-decimalTypeDiv").hide();
         }); 
		
		/*激活快捷键检索功能*/
		searchOnPressEnter();
		/*添加红星*/
		addRequiredClass();

		if(!Joyin.curfromData) {
			Joyin.curfromData = {};
		}
		Joyin.curfromData[pageId] = $.initSerializeToObject();

        /!* 浏览器禁用右键*!/
        $(document).bind("contextmenu",function(e){
            return false;
        });
        
        
        if(pageId && pageId.indexOf('001') === -1) {
        	$('form.joyin-form-wrap').css('background', 'none');
        }
       if($(".input-dateRangeSelect").length>0 && typeof ($(".input-dateRangeSelect").attr("placeholder"))=='undefined'){
           $(".input-dateRangeSelect").attr('placeholder','请选择日期区间')
	   }
        if($(".input-dateSelect").length>0 && typeof ($(".input-dateSelect").attr("placeholder"))=='undefined'){
            $(".input-dateSelect").attr('placeholder','请选择日期')
        }
        
	});
	
	//
	//单选
    $(".radiolabel li").click(function(){
	  $(this).addClass("curr").siblings().removeClass("curr");
    });
    //多选
    $(".multilabel li").click(function(){
	  $(this).toggleClass("curr");
    });
	//操作进度指示
    $(".joyin-denote .joyin-denotehd").click(function(){   
      $(".joyin-denotebd").animate({width:'toggle'},"slow"); 
    });
}(jQuery));


Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}