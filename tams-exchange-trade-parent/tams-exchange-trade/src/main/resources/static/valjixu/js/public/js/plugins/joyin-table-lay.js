/* ===========================================================
 * joyin-table-lay.js v2.0.7
 * ===========================================================
 * Copyright 2017 He Yangyang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================== */

!function ($) {
    "use strict";
    var layer = layui.layer;

    /**
     * Joyintable CLASS DEFINITION
     * @constructor
     * @param {string} element jqeury选择器
     * @param {object} options 配置项，支持datatables配置项，并添加扩展
     * @example
     *  var table = new JoyinTable('#myTable', {});
     */
    var JoyinTable = function (element, options) {
        this.init(element, options);
    };
    JoyinTable.prototype = {
        constructor: JoyinTable,
        // 初始化
        init: function(element, options) {
            var self = this;
            this.layTable = layui.table;
            this.$element = $(element);
            if(this.$element.length !== 1) {
                return Joyin.error('表格对象('+element+')不存在!');
            }
            this.$form = null;
            this.options = options;
            this.layOption = {
                // 开启分页
                page: true,
                // 默认每页显示20条
                limit: 20,
                // 显示加载条
                loading: true,
                // 列定义
                cols: [],
                // 数据渲染完的回调
                done: self.drawCallback,
//                // 列边框风格
//                skin: 'row',
                // 开启隔行背景
                even: true,

            };
            this.filter = $(element).attr('id');
            this.layOption['lay-filter'] = this.filter;
            this.layOption['id'] = $(element).attr('id');
            this.layOption.elem = '#' + $(element).attr('id');
            this._getPreData();
            // 树形表格
            this._setTree();
            this._copyOption();
            this._addRowNo();
            this._selectSet();
            this._ajaxSet();
            this._editCol();
            this._createCol();

            // 绑定事件
            $('body').on('click','.btn-filter', function() {
                if(self.table) {
                    var that = self.table.table;
                    if (that === undefined) {
                        that = self.table.config;
                    }
                    that.elem.hide();
                    setTimeout(function(){
                        var _h = self._getHeight();
                        if(_h && /^full-\d+$/.test(_h)){ //full-差距值
                            that.fullHeightGap = _h.split('-')[1];
                        }
                        that.fullSize();
                        // 修正分页条高度
                        var mainHeight = that.layMain.height() - that.layPage.outerHeight();
                        that.layMain.height(mainHeight);
                        // 修正操作列和行号列高度
                        that.layFixLeft.find('.layui-table-body').height(mainHeight);
                        that.layFixRight.find('.layui-table-body').height(mainHeight);
                        that.elem.slideToggle(200, function() {
                            that.scrollPatch();
                        });
                    }, 50);
                }
            });
            // 初始加载数据
            if(options.searchOnInit) {
                // 延迟加载
                setTimeout(function() {
                    self.load();
                }, options.lazytime);
            } else {
                this.loadTableHead();
            }
            this.$element.css({height:0,margin:0});
        }
        ,events: function() {
            var self = this;
            /* 单元格编辑  start */
            if(!self.table.table){
                return;
            }
            self.table.table.layBody.on('click', 'td', function(e) {
                var othis = $(this)
                ,field = othis.data('field')
                ,editClass = 'layui-table-edit'
                ,widgetClass = 'edit-widget'
                ,elemCell = othis.children('.' + editClass)
                ,selfCol = self.options.cols
                ,preVal = elemCell.text() || othis.data('content') || othis.text()
                ,currentCol = self._getColByField(field);

                if(othis.find('.' + editClass)[0]) {
                      return;
                }
                // 判断是否允许修改
                if(currentCol && currentCol.edit) {
                    // 调用beforeEditCell，如果beforeEditCell返回值为false，则不可编辑
                    if(!self.beforeEditCell(preVal, field)) return;
                    
                    e = e || window.event;
                    e.stopPropagation ? e.stopPropagation() : e.cancelBubble = true;
                    var opt = currentCol.editOption, type = opt.type || 'text';
                    if(type === 'text') return;
                    var save = function(input) {
                        input.removeClass(widgetClass);
                        layui.$(input[0]).trigger('change').blur();
                    };
                    //显示编辑框
                  var input = $('<input>').addClass(editClass);
                  input.attr('name', field);
                  input[0].value = preVal;
                  othis.append(input);
                  // 数值类型编辑
                  if(type === 'number') {
                      input.attr('data-fixtype', opt.fixtype || 2);
                      input.attr('data-pattern', opt.pattern);
                      input.attr('data-textrange', opt.textrange || currentCol.digit);
                      input.data('td', othis).textNumber();
                  }
                  // 金额类文本框
                  else if(type === 'money'){
                      if(!opt.unit && currentCol.moneyType){
                          if(currentCol.moneyType === '02'){
                              opt.unit = 10000;
                          }
                          else if(currentCol.moneyType === '03'){
                              opt.unit = 100000000;
                          }
                      }
                      input.attr('data-fixtype', opt.fixtype || 2);
                      input.attr('data-pattern', opt.pattern);
                      input.attr('data-textrange', opt.textrange || currentCol.digit);
                      input.attr('unit', opt.unit);
                      input.data('td', othis).textMoney();
                  }
                  // 日期类型编辑
                  else if(type === 'date'){
                      input.addClass(widgetClass).textDate(false, type.formatter || 'yyyy-MM-dd');
                  }
                  else if(type === 'datetime') {
                      input.data('option', '{type: "datetime", showBottom: true, btns: ["now", "clear", "confirm"]}');
                      input.addClass(widgetClass).textDate(false, type.formatter || 'yyyy-MM-dd HH:mm:ss');
                  }
                  else if(type === 'dateRange') {
                      input.addClass(widgetClass).textDate(true, type.formatter || 'yyyy-MM-dd');
                  }
                  else if(type === 'select') {
                      var showField = opt.showField || 'text';
                      var select = $('<select></select>').css('width', othis.width());
                      var index = othis.parent().eq(0).data('index');
                      var cacheData = self.layTable.cache[(self.layOption.id || self.filter)][index];
                      var parseIds = function(ids) {
                          return ids.split(',').map(function(i) {
                              if($('#' + i).length > 0) {
                                  return i;
                              }
                              return i + '_cell';
                          }).join(',');
                      };

                      input.hide();
                      othis.find('.layui-table-cell').hide();
                      othis.append(select);
                      opt.id && input.attr('id', opt.id);
                      select.attr('data-url', opt.url);
                      select.attr('data-result', opt.result);
                      opt.childrenIds && select.attr('childrenIds', parseIds(opt.childrenIds));
                      opt.parentIds && select.attr('parentIds', parseIds(opt.parentIds));
                      select.attr('multiple', opt.multiple);
                      select.attr('searchable', opt.searchable);
                      // 如果data-result形式加载的下拉框，则根据showField自动设置初始值
                      var _val = input.val();
                      if(!opt.url && opt.result && showField !== 'id') {
                          var dd = $.parseJSON(opt.result);
                          var _values = _val.split(',');
                          var _v = [];
                          if ($.isArray(dd)) {
                              _.each(dd, function(d) {
                                  if(_values.indexOf(d[showField]) != -1) {
                                      _v.push(d['id']);
                                  }
                              });
                              select.attr('initValue', _v.join(','));
                          }
                      }
                      else {
                          select.attr('initValue', _val);
                          select.data('showField', showField);
                      }
                      
                      select.addClass(widgetClass).textSelect();
                      select.on('select2:unselecting', function() {
                          select.data('unselect', 1);
                      });
                      select.on('select2:unselect', function() {
                          select.data('unselect', null);
                      });
                      select.on('select2:close', function(e) {
                          if(select.data('unselect')) return;
                          var d = select.select2('data'), v = [], ids = [];
                          othis.data('sd', d);
                          _.each(d, function(i){
                              v.push(i[showField]);
                              ids.push(i.id);
                          });
                          input[0].value = v.join(',');
                          $(input).trigger('change', {value: ids, rowIndex: index});
                          save(input);
                          othis.find('.layui-table-cell').show();
                          setTimeout(function() {
                              select.hide().select2('destroy').remove();
                          }, 10);
                          if(opt.id) {
                              var $input = $('#' + opt.id + '_cell');
                              $input.length == 0 && ($input = $('<input type="hidden">').attr('name', opt.id).attr('id', opt.id + '_cell'), $input.appendTo('body'));
                              $input.val(ids.join(','));
                          }
                          input.remove();
                          cacheData[field + 'Val'] = ids.join(','); //缓存中添加值
                      });
                      select.select2('open');
                      return select.focus();
                  }
                  // 日期控件，特殊处理
                  if(type && type.indexOf('date') === 0) {
                      $(window).one('click', function() {
                          save(input);
                      });
                  }
                  return input.focus();
                }
            });
            /* 单元格编辑  end */
        }
        ,getHeight: function() {
            return this.$element.next('.layui-table-view').height();
        },
        resize: function(type) {
            if(this.table && this.options.resize) {
                var view = this.$element.next('.layui-table-view');
                var tableBox = view.find('> .layui-table-box');
                var tableMain = tableBox.find('> .layui-table-main > .layui-table');
                var tableHeader = tableBox.find('> .layui-table-header > .layui-table');
                // 修正右侧固定列的位置
                tableBox.find('> .layui-table-fixed-r').css('right', '12px');
                // 表格宽度填满
                tableMain.css('width', '100%');
                // 表头宽度和表格内容宽度相等
//                setTimeout(function() {
//                    tableHeader.css('width', tableMain.width());
//                }, 10);
            }
        },
        getAjaxParam: function(elementId) {
            var _ajax = this.options.ajax;
            var data = {};
            if(_ajax) {
                data = _ajax.data||data;
                if($.isFunction(data)) {
                    data = data.call(this);
                }
            }
            data.pageId = pageId;
            data.pageIdForLog = pageId;
            data.elementIdForLog = elementId;
            var formObj = $.initSerializeToObject();

            // 使用cookie记录画面数据修改
            $.cookie.json = true;
            $.cookie((pageId ? pageId : "") + '_searchData', formObj);
            return data || {};
        },
        // 重新加载
        reload: function(param) {
            if(!this.table) return;
            this.loading();
            var height = this.options.height || this._getHeight();
            var opt = {
                page: {page:1},
                height: height,
                where: this.getAjaxParam('tableReload')
            };
            this._ajaxSet();
            param = param || $.extend({}, {url: this.layOption.url});
            if(this.options.page === false) {
                opt.page = false;
            }
            if(this.options.loadonce && !$.isArray(param.data)) {
                opt.data = this.loadonce();
            }
            else {
               delete this.layOption.data;
            }
            opt = $.extend(opt, param);
            if(opt.page === false && $.isArray(opt.data)) {
                opt.limit = opt.data.length;
            }
            this.treeTier = true;
            this.table.reload(opt);
            this.events();
        },
        // 加载
        load: function() {
            var self = this;
            var height = self.options.height || self._getHeight();
            self.treeTier = true;
            self.loading();
            self.layOption.where = this.getAjaxParam(Joyin.getElementId());
            if(self.table) {
                if(!self.layOption.where.elementIdForLog) {
                    return window.console && console.error('请调用reload方法！load方法仅在点击查询按钮是可以调用！');
                }
                var opt = {
                    page: {page:1},
                    height: height
                };
                self._ajaxSet();
                $.extend(opt, {url: self.layOption.url});
                if(self.options.page === false) {
                    opt.page = false;
                }
                if($.isArray(self.options.data)) {
                    self.layOption.data = self.options.data;
                }
                else if(self.options.loadonce) {
                    opt.data = self.loadonce();
                    self.options.allData = opt.data;
                }
                else {
                    opt.where = self.layOption.where;
                }
                if(opt.page === false && $.isArray(opt.data)) {
                    opt.limit = opt.data.length;
                }
                self.table.reload(opt);
                self.events();
            }
            else {
                if($.isArray(self.options.data)) {
                    self.layOption.data = self.options.data;
                }
                else if(self.options.loadonce) {
                    self.layOption.data = self.loadonce();
                    self.options.allData = self.layOption.data;
                    self.options.elem = self.layOption.elem;
                }
                if(self.layOption.page === false && $.isArray(self.layOption.data)) {
                    self.layOption.limit = self.layOption.data.length;
                }
                self.$element.attr('lay-filter', self.filter);
                self.layOption.cols = self.options.isMulti ? self.layOption.cols : [self.layOption.cols];
                self.layOption.height = height;
                self.table = self.layTable.render(self.layOption);
            }
            self._bindEvent(self.layOption['lay-filter']);
            self.layOption.where.elementIdForLog = "";
            self.layTable.cache[(self.layOption.id || self.filter) + '_config'] = self.options;
            self.events();
        },
        // 加载
        loadTableHead: function() {
            var head = $.extend(true, {}, this);
            head.layOption.ajax = null;
            head.layOption.url = null;
            head.layOption.data = null;
            // 表头初期显示的时候，去掉操作列
            if(!Array.isArray(head.layOption.cols[0])){
                var array = new Array();
                $.each(head.layOption.cols, function(n) {
                    if ($(this).attr("title") != '操作') {
                        array.push(head.layOption.cols[n]);
                    }
                });
                head.layOption.cols =[array];
            }
            head.layOption.height = this._getHeight();
            head.table = head.layTable.render($.extend({}, head.layOption, {done:null}));
            //表头居左
            this.$element.next().find(".layui-table-header .layui-table-cell").removeAttr('align');
        },
        // 获取一行
        getRow: function(rowIndex) {
            var datas = this.getAllRows();
            if(datas.length == 0) {
                return {};
            }
            return datas[rowIndex];
        },
        // 获取所有选中行
        getAllCheckedRows: function() {
            if(!this.table) {
                return [];
            }
            var filter = this.layOption.id || this.filter;
            return _.clone(this.layTable.checkStatus(filter).data);
        },
        // 获取所有行
        getAllRows: function() {
            if(!this.table) {
                return [];
            }
            var filter = this.layOption.id || this.filter;
            return _.clone(this.layTable.cache[filter] || []);
        },
        // 选中行
        selectRow: function(rowData) {
            if(this.table) {
                var filter = this.layOption.id || this.filter;
                var datas = this.layTable.cache[filter];
                var row;
                if($.isArray(datas)) {
                    $.each(datas, function(i, d) {
                        if(!row && compare(rowData, d)) {
                            row = d;
                            return;
                        }
                    });
                }
                if(row) {
                    var index = row[this.layTable.config.indexName];
                    var check = row[this.layTable.config.checkName];
                    // 当前行为被选中
                    if(!check) {
                        this.checkRow(index);
                    }
                }
            }
        },
        // 添加行
        addRow: function(rowData) {
            if(!rowData) return;
            var datas = this.getAllRows();
            if(datas == undefined){
                datas = []
            }
            if(_.isArray(rowData)) {
                datas = _.concat(datas, rowData);
            }
            else {
                datas.push(rowData);
            }
            this.reload({data:datas});
        },
        // 删除行
        removeRow: function(rowData) {
            if(!rowData) return;
            var datas = this.getAllRows();
            if(!datas) return;
            var index;
            $.each(datas, function(k,v){
                if(v && compare(rowData, v)){
                    index = k;
                }
            });
            if(index !== undefined){
                datas.splice(index,1);
                this.reload({data:datas});
            }
        },
        // 删除行
        deleteRow: function(index) {
            if(index > -1) {
                var datas = this.getAllRows();
                if(!datas) return;
                datas.splice(index,1);
                this.reload({data:datas});
            }
        },
        renderRow: function(index, rowData) {
            if(rowData && _.isObject(rowData)) {
                var $tr = this.table.table.layBody.find('tr:eq(' + index + ')');
                var d = this.getRow(index);
                _.each(rowData, function(v, k) {
                    d[k] = v;
                    $tr.find('td[data-field="' + k + '"] > div.layui-table-cell').html(v);
                });
            }
        },
        // 编辑行
        editRow: function(rowId) {

        },
        // 编辑单元格
        editCell: function(value, data, field, cell, column) {
            if($.isFunction(this.options.editCell)) {
                this.options.editCell(value, data, field, cell, column);
            }
        },
        // 编辑单元格之前
        beforeEditCell: function(value, data, field) {
           return true;
        },
        bind: function(eventStr, selector, fun) {
            if($.isFunction(fun)) {
                var self = this;
                var $target = self.$element.parent();
                if($target.length == 0) {
                    $target = $('body');
                }
                $target.on(eventStr, selector, function() {
                    var btn = $(event.srcElement);
                    if(btn.parents('.layui-table-view').prev().attr('lay-filter') === self.filter) {
                        var rowIndex = btn.parents('tr').data('index');
                        var row = self.getRow(rowIndex);
                        // 表格操作列
                        var cls = btn.attr('class').split(' ').reverse()[0];
                        if(cls === 'delt') {
                            Joyin.elementId = pageId + '_btn_delete';
                        }
                        fun.call(btn, row, rowIndex);
                    }
                });
            }
            return this;
        },
        isSearched: function() {
            if(!this.table) {
                Joyin.warn('请先查询数据');
                return false;
            }
            return true;
        },
        checkRow: function(index) {
            var fixedCheckBox = this.$element.next('.layui-table-view').find('.layui-table-fixed').find('tr[data-index="' + index + '"]').find(':checkbox[name="layTableCheckbox"]');
            fixedCheckBox.next().trigger('click');
        },
        uncheck: function() {
            var fixedCheckBox = this.$element.next('.layui-table-view').find('.layui-table-fixed').find(':checkbox[name="layTableCheckbox"]:checked');
            fixedCheckBox.next().trigger('click');
        },
        // 计算表格高度
        _getHeight: function() {
//            this.$element.css('margin-bottom', '0');
//            var tmp = 20;
//            if(!this.$element.parent().prev().is('.btn-group-sm')) {
//                tmp = tmp + 15;
//            }
//            $('.win-height').each(function() {
//                tmp += $(this).outerHeight();
//            });
//            if ($(".navbar-static-top")) {
//                tmp += 40;
//            }
//            return $(document).height() - this.$element.offset().top - tmp;
            var offset = this.$element.offset().top + 30
            return 'full-' + offset;
        },
        _setTree: function() {
            var self = this;
            if(!self.options.tree) return;
            self.layTable = layui.treeGrid;
            // 树形控件强制不允许分页
            // self.options.page = false;
            self.options.checkOnClickRow = false;
            self.options.rowSelectedClass = '';
            self.layOption.lazyload = self.options.lazyload; //懒加载模式
            self.layOption.treeId = self.options.treeId || 'id'; //树形id字段名称
            self.layOption.treeUpId = self.options.treeUpId || 'parentId';//树形父id字段名称
            self.layOption.treeShowName = self.options.treeShowName || 'name';//以树形式显示的字段
            self.layOption.rootId = self.options.rootId || 'root';//树形式的根节点ID
            //存储已存在的tr节点
            self.layTable.trarrays = [];
            // 显示层级
            self.layTable.tier = 1;
            //总显示层级
            self.layTable.tiernum = self.options.tiernum || 4;
            self.layOption.tiernum = self.layTable.tiernum;
        },
        _getPreData : function(){
            var self = this;
            if(self.options.addbtn && !self.options.ajax && !self.options.data){
                self.options.data = [];
            }
            Joyin.ajax(JoyinUtil.getRealPath('/main/getMoneyShowType'),null,function(data){
                self.layOption.moneyType = data;
            });
            Joyin.ajax(JoyinUtil.getRealPath('/main/getDecimalShowType'),null,function(data){
                self.layOption.decimalType = data;
            });
        },
        _createCol: function(ignoreLayId) {
            var self = this, columns = this.options.cols;
            if($.isArray(self.layOption.cols) && $.isArray(columns)) {
                var _hiddenField = [];
                $.each(columns, function(i, col) {
                    // 设置隐藏属性的列，不显示
                    var ___createcell = function(col,arrayCols){
                        if(col.visible === false) {
                            _hiddenField.push(col.field);
                            return;
                        }
                        var _tmpCol = {
                            sort: true,
                            align: col.align || ((col.type || '') == 'number' ? 'right' : ((col.type || '') == 'money' ? 'right' : ((col.type || '') == 'action' ? 'center' : 'left')))
                        };
                        if(col.sortable != undefined && col.sortable === false) {
                            _tmpCol.sort = false;
                        }
                        if(col.fixed) {
                            _tmpCol.fixed = col.fixed;
                            _tmpCol.sort = false;
                        }
                        if(col.unresize) {
                            _tmpCol.unresize = true;
                        }
                        if(col.minWidth) {
                            _tmpCol.minWidth = col.minWidth;
                        }
                        if(col.field) {
                            _tmpCol.field = col.field;
                        }
                        if(col.title) {
                            _tmpCol.title = JoyinUtil.getLabel(col.title);
                        }
                        // 单元格编辑
                        if(col.edit && col.edit === true && pageModel !== PAGE_MODEL_REFERENCE) {
                            // 指定为select是为了取消layui自带的输入框
                            _tmpCol.edit = 'select';
                            if(!col.editOption) {
                                // 如果指定可编辑列，则自动创建editOption
                                col.editOption = {type:col.type};
                                !col.type && (_tmpCol.edit = 'text');
                            }
                        }
                        // date类型，最小宽度为110px
                        if(col.type == 'date') {
                            _tmpCol.width = 110;
                            if(col.width && parseInt(col.width) > 110) {
                                _tmpCol.width = col.width;
                            }
                        }
                        // datetime类型，最小宽度为160px
                        else if(col.type == 'datetime') {
                            _tmpCol.width = 160;
                            if(col.width && parseInt(col.width) > 160) {
                                _tmpCol.width = col.width;
                            }
                        }
                        // email类型，最小宽度为130px
                        else if(col.type == 'email') {
                            _tmpCol.width = 130;
                            if(col.width && parseInt(col.width) > 130) {
                                _tmpCol.width = col.width;
                            }
                        }
                        // action类型，最小宽度为85px
                        else if(col.type == 'action') {
                            _tmpCol.width = 95;
                            _tmpCol.fixed = 'right';
                            _tmpCol.sort = false;
                            if(!_tmpCol.field) {
                                col.field = _tmpCol.field;
                            }
                            if(col.width && parseInt(col.width) > 95) {
                                _tmpCol.width = col.width;
                            }
                        }
                          // money类型，最小宽度为110px
                        else if(col.type == 'money') {
                            _tmpCol.width = 110;
                            if(col.moneyType){
                                _tmpCol.moneyType = col.moneyType;
                            }
                            if(col.width && parseInt(col.width) > 110) {
                                _tmpCol.width = col.width;
                            }
                        }
                          // number类型
                        else if(col.type == 'number') {
                            _tmpCol.width = 110;
                            if(col.decimalType){
                                _tmpCol.decimalType = col.decimalType;
                            }
                            if(col.width && parseInt(col.width) > 110) {
                                _tmpCol.width = col.width;
                            }
                        }
                        else {
                            if(col.width) {
                                _tmpCol.width = col.width;
                            }
                        }
                        if(col.style) {
                            _tmpCol.style = col.style;
                        }

                        if(col.rowspan){
                            _tmpCol.rowspan=col.rowspan;
                        }

                        if(col.colspan){
                            _tmpCol.colspan=col.colspan;
                        }

                        if(col.checkbox){
                            _tmpCol.checkbox=col.checkbox;
                        }
                        if(col.cusVisible){
                            _tmpCol.cusVisible=col.cusVisible;
                        }
                        // 树形表格不允许排序
                        if(self.options.tree) {
                            _tmpCol.sort = false;
                        }

                        if($.isArray(arrayCols)){
                            arrayCols.push(_tmpCol)
                        }else{
                            self.layOption.cols.push(_tmpCol);
                        }
                    }
                    if($.isArray(col)){
                        var tempArray = [];
                        $.each(col, function(i, col1) {
                            ___createcell(col1,tempArray);
                        });
                        self.layOption.cols.push(tempArray)
                    }else{
                        ___createcell(col);
                    }
                });

                if(!ignoreLayId && _hiddenField.length > 0) {
                    self.layOption.id = this.filter+_hiddenField.join(',');
                }
            }
        },
        //获取用户保存的列表样式
        _editCol: function(){
          var self = this
          //col参数，获取用户保存样式
          if(!self.layOption.tableStyle){
                Joyin.ajaxSync(JoyinUtil.getRealPath('main/getTableStyle'),{pageId:pageId, elementId:self.$element.attr('id')},function(data){
                    if(data){
                        self.layOption.tableStyle = JSON.parse(data);
                        //copy到self.layOption.col
                        var cusCols = self.layOption.tableStyle?self.layOption.tableStyle.cols:null;
                        if(cusCols){
                            var selfCol = self.options.cols;
                            $.each(selfCol, function(k,v){
                                for(var i =0; i< cusCols.length; i++){
                                    if(v.field === cusCols[i].field){
                                        if(cusCols[i].moneyType){
                                            v.moneyType = cusCols[i].moneyType;
                                        }
                                        if(cusCols[i].decimalType){
                                            v.decimalType = cusCols[i].decimalType;
                                        }
                                        if(cusCols[i].cusVisible === false){
                                            v.visible = false;
                                            v.cusVisible = false;
                                        }
                                        break;
                                    }
                                }
                            });
                            self.options.cols = selfCol;
                        }
                    }
                });
            };
            if(self.options.tree){
                Joyin.ajaxSync(JoyinUtil.getRealPath('main/getTreeTier'),{pageId:pageId},function(data){
                    if(data){
                        self.layTable.tier=parseInt(data);
                    }
                });
            }
        },

        /**
         * 设置表格是否显示checkbox
         */
        _selectSet: function() {
            if(this.options.checkbox && $.isArray(this.layOption.cols)) {
                var chCol = {type: 'checkbox'};
                if(!this.options.tree) {
                    chCol.fixed = 'left';
                }
                this.layOption.cols.unshift(chCol);
            }
        },
        loadonce: function() {
            var result = [];
            if(this.options.ajax && this.options.loadonce) {
                var _ajax = this.options.ajax;
                var data = this.getAjaxParam(Joyin.getElementId());
                var url = _ajax.url;
                if(url && url.indexOf(basePath) != 0 && url.indexOf('/') == 0) {
                    _ajax.url = JoyinUtil.getRealPath(url);
                }
                $.ajax({
                    url: _ajax.url,
                    dataType: 'json',
                    async: false,
                    type: _ajax.type || 'POST',
                    data: data,
                    success: function(res) {
                        if(!$.isArray(res)) {
                            layer.msg('前台分页必须返回一个list');
                        }
                        else {
                            result = res;
                        }
                    }
                });
                return result;
            }
        },
        /**
         * 设置ajax参数
         */
        _ajaxSet: function() {
            if(this.options.ajax && !this.options.loadonce) {
                var _ajax = this.options.ajax;
                var url = _ajax.url;
                if(url && url.indexOf(basePath) != 0 && url.indexOf('/') == 0) {
                    _ajax.url = JoyinUtil.getRealPath(url);
                }
                this.layOption = $.extend(true, this.layOption, {
                    //数据接口访问地址
                    url: _ajax.url,
                    // 请求方式指定为POST
                    method: _ajax.type || 'POST',
                    // ajax请求参数
                    where: {},
                    // 分页请求参数
                    request: {
                        pageName: 'page', //页码的参数名称，默认：page
                        limitName: 'length' //每页数据量的参数名，默认：limit
                    },
                    // 返回的数据格式的自定义
                    response: {
                        statusName: 'code', //数据状态的字段名称，默认：code
                        statusCode: 0, //成功的状态码，默认：0
                        msgName: 'message', //状态信息的字段名称，默认：msg
                        countName: 'records', //数据总数的字段名称，默认：count
                        dataName: 'root' //数据列表的字段名称，默认：data
                    }
                });
            }
        },
        _copyOption: function() {
            var self = this;
            if(self.options.initSort) {
                self.layOption.initSort = self.options.initSort;
            }
            if(self.options.limits && $.isArray(self.options.limits)) {
                var _step = parseInt(pageStep || 10) / 10;
                var _limits = self.options.limits.map(function(item) {return item * _step;});
                if(_limits.indexOf(20) == -1) {
                    _limits.push(20);
                    _limits.sort(function(a,b) {return a - b;});
                }
                self.layOption.limits = _limits;
            }
            if(self.options.page === false) {
                self.layOption.page = false;
            }
            // 双击查看事件
            self.options.showDetail && (self.options.dblclick = function(t,row) {
                window[pageId] = {
                    columns: self.options.cols,
                    registSeqNo: row.registSeqno,
                    status: row.status,
                    rowData: JSON.stringify(row)
                };
                Joyin.dialog(null, basePath + 'common/detail/init');
            });
            // data参数
            if(self.options.data && $.isArray(self.options.data)) {
                self.layOption.data = self.options.data;
            }
            // loading
            if(self.options.loading === false) {
                self.layOption.loading = false;
            }
        },
        // 添加序号
        _addRowNo: function() {
            if(!this.options.rowno) return;
            if($.isArray(this.layOption.cols)) {
                this.layOption.cols.unshift({type: 'numbers', fixed: 'left'});
            }
        },
        _bindEvent: function(filter) {
            var self = this;
            //监听表格复选框选择
            self.layTable.on('checkbox(' + filter + ')', function (obj) {
                // 获取已选择的行数
                var data = self.layTable.checkStatus(self.layOption.id).data;
                $(".layui-laypage span.layui-hasSelected").remove();
                if(data.length > 0) {
                    self.$element.next('.layui-table-view').find(".layui-laypage").append("<span class='layui-hasSelected'>已选择"+data.length+"条数据</span>")
                }
                var tbody = self.$element.next('.layui-table-view').find('.layui-table-body');
                // 改变选中行背景色
                if(obj.type === 'all') {
                    // 全选checkbox点击
                    tbody.each(function(i, body) {
                        $(body).find('tr').toggleClass(self.options.rowSelectedClass, obj.checked);
                    });
                }
                else {
                    var index = obj.data[self.layTable.config.indexName];
                    tbody.each(function(i, body) {
                        $(body).find('tr[data-index="'+index+'"]').toggleClass(self.options.rowSelectedClass, obj.checked);
                    });
                }
                if(self.options.check && $.isFunction(self.options.check)) {
                    self.options.check.call(this, self, obj.data || {});
                }
            });
            self.layTable.on('selected(' + filter + ')', function (obj) {
                var tbody = self.$element.next('.layui-table-view').find('.layui-table-body');
                tbody.each(function(i, body) {
                    $(body).find('tr').toggleClass(self.options.rowSelectedClass, false);
                    $(body).find('tr[data-index="'+obj.index+'"]').toggleClass(self.options.rowSelectedClass, true);
                });
            });

            //监听单元格编辑
            self.layTable.on('edit(' + filter + ')', function (obj) {
                var value = obj.value //得到修改后的值
                    , data = obj.data //得到所在行所有键值
                    , field = obj.field //得到字段
                    , index = data[self.layTable.config.indexName]
                    , currentCol = self._getColByField(field)
                    , row = self.table.table.layBody.find('tr:eq(' + index + ')')
                    , cell = row.find('td[data-field="' + field + '"]');
                self.editCell(value, data, field, cell, currentCol);
            });

            // 监听分页
            if(self.options.tree) {
                self.layTable.on('page('+ filter +')', function(obj) {
                    self.treeTier = true;
                });
            }

            //监听排序
            self.layTable.on('sort(' + filter + ')', function (obj) {
                window.afterSort = false;
                if(self.options.loadonce) {
                    var filter = self.layOption.id || self.filter;
                    self.drawCallback(self.layTable.cache[filter]);
                    return;
                }
                self.loading();
                var where = self.layOption.where;
                //排序字段
                where.key = obj.field;
                $.each(self.options.cols, function() {
                    var filedId = $(this).attr("field");
                    var sortFiledId = $(this).attr("sortField");
                    if (filedId === obj.field && sortFiledId != undefined) {
                        where.key = sortFiledId;
                        // 是否需要前台排序
                        window.afterSort = true;
                    }
                });

                //排序方式
                where.order = obj.type;
                // 记住滚动条位置
                var header = self.$element.next('.layui-table-view').find('.layui-table-header');
                self.scrollLeft = header.scrollLeft();
                self.table.reload({
                    initSort: obj
                    ,where: where
                });
            });
        },
        _getColByField: function(field) {
            var cols = this.options.cols, currentCol;
            // 获取点击单元格所在的配置
            _.each(cols, function(col) {
                if(col.field && col.field === field) {
                    currentCol = col;
                    return;
                }
            });
            return currentCol;
        },
        _checkOnClick: function() {
            var self = this;
            if(self.options.checkOnClickRow) {
                // 行点击选择
                self.$element.next('.layui-table-view').off('click').on('click', '.layui-table-main tr', function() {
                    var index = $(this).data('index');
                    self.checkRow(index);
                });
            }
        },
        _getRow: function(data, page, rowIndex) {
            var self = this
                , indexName = self.layTable.config.indexName
                , row = {};
            // lazyload
            if(self.options.tree && page == -1) {
                _.each(data, function(d) {
                    var index = d[indexName];
                    if(index == rowIndex) {
                        row = d;
                        return;
                    }
                });
            }
            else {
                row = data[rowIndex] ? data[rowIndex] : data.data ? data.data[rowIndex] :  data.root ? data.root[rowIndex] :{};
            }
            return row;
        },
        /**
         * 自定义列格式化操作
         */
        drawCallback: function(res, page, total) {

            var self = this.elem ? $(this.elem).data('joyintable') : this;
            if(!self) return;
            var header = self.$element.next('.layui-table-view').find('.layui-table-header');
            //表头居左
            header.find(".layui-table-cell").removeAttr('align');
            var cols = self.options.cols;
            var filter = self.layOption.id || self.filter;
            var root = self.options.tree ? layui.treeGrid.cache[filter] : layui.table.cache[filter];
            var rightColumn;
            if(cols && $.isArray(cols)) {
                $.each(cols, function(i, col) {

                    var __drawCallback = function(col){

                        self.$element.next('.layui-table-view').find('td[data-field="' + col.field + '"]').each(function() {
                            var value = $(this).find(' > div').html().trim() || '';
                            var trd = $(this).parent().data();
                            var row = self._getRow(root, page, trd.index);
                            if($.isEmptyObject(row)) return;
                            // 数据状态是删除的时候置灰
                            if (i == 0 && row.status != undefined && row.status === 'D') {
                                $(this).parent().css('background-color','#f2f2f2');
                                $(this).parents('.layui-table-view').find('.layui-table-fixed').find('tr[data-index="' + trd.index + '"]').css('background-color','#f2f2f2').find(':checkbox').prop('disabled',true);
                                $(this).parents('.layui-table-view').find('.layui-table-body').find('tr[data-index="' + trd.index + '"]').css('background-color','#f2f2f2').find(':checkbox').prop('disabled',true);
                            }
                            // 更新Flag是否的时候置灰，不能修改和删除，但是可以点checkbox
                            if (i == 0 && row.updateFlg != undefined && row.updateFlg === '02') {
                                $(this).parent().css('background-color','#f2f2f2');
                                $(this).parents('.layui-table-view').find('.layui-table-fixed').find('tr[data-index="' + trd.index + '"]').css('background-color','#f2f2f2');
                                $(this).parents('.layui-table-view').find('.layui-table-body').find('tr[data-index="' + trd.index + '"]').css('background-color','#f2f2f2');
                            }
                            // 数据类型
                            var type = col.type;
                            if(!col.render && type) {
                                // 如果为日期，则进行格式化
                                if(type === 'date') {
                                    if(value) {
                                        var date = new Date(parseInt(value));
                                        value = date.format('yyyy-MM-dd');
                                    }
                                }
                                else if(type === 'datetime') {
                                    if(value) {
                                        var date = new Date(parseInt(value));
                                        value = date.format('yyyy-MM-dd HH:mm:ss');
                                    }
                                }
                                // 数值格式化
                                else if(type === 'number') {
                                    /**
                                     * 获取配置中的小数位数（digit）
                                     */
                                    var decimal = col.digit <= 0 ? 0 : col.digit || (col.decimalType ? self.layOption.decimalType[parseInt(col.decimalType)-1].itemValue.replace(/([^\u0000-\u00FF])/g,'') : 2);
                                    var num = (value || 0).toString();
                                    if(!isNaN(num)) {
                                        value = JoyinUtil.formatNumber(num, decimal, null, 2);
                                    }
                                }
                                else if(type === 'money'){
                                    var moneyUnit = 1;
                                    var decimal = col.digit <= 0 ? 0 : col.digit || (col.decimalType ? self.layOption.decimalType[parseInt(col.decimalType)-1].itemValue.replace(/([^\u0000-\u00FF])/g,'') : 2);
                                    var num = (value || 0).toString();
                                    if(value && col.moneyType){
                                        if(col.moneyType === '02'){
                                            moneyUnit = 10000;
                                        }
                                        else if(col.moneyType === '03'){
                                            moneyUnit = 100000000;
                                        }
                                    }
                                    if(!isNaN(num)) {
                                        value = JoyinUtil.formatNumber(num, decimal, moneyUnit,2);
                                    }
                                }
                                else if(type === 'email') {
                                    if(value) {
                                        value = '<a href="mailto:' + value + '">' + value + '</a>';
                                    }
                                }
                                // 数据状态是有效的时候显示操作列
                                else if(type === 'action' && (row.status === undefined || (['A','D','P'].indexOf(row.status) == -1))
                                        && (!row.updateFlg || (row.updateFlg != undefined && row.updateFlg === '01'))) {
                                    var defbtns = $.fn.joyintable.defaults.buttons || {};
                                    var buttons = col.buttons;
                                    var privews = getNoPurviewElements(self.options, row);
                                    if(buttons && $.isArray(buttons)) {
                                        var _tmpVal = [];
                                        $.each(buttons, function(i, btn) {
                                            if(typeof btn == 'string' && privews.indexOf(btn) == -1) {
                                                _tmpVal.push('<i class="btn layui-icon ');
                                                _tmpVal.push(defbtns[btn].clas);
                                                _tmpVal.push('" title="'+defbtns[btn].title+'"');
                                                _tmpVal.push('>');
                                                _tmpVal.push(defbtns[btn].text);
                                                _tmpVal.push('</i>');
                                            }
                                            else if(typeof btn == 'object') {
                                                var text = btn.text;
                                                if(text && text.indexOf('&#') == 0) {
                                                    _tmpVal.push('<i class="btn layui-icon ');
                                                    _tmpVal.push(btn.clas);
                                                    if (btn.title) {
                                                        _tmpVal.push('" title="'+btn.title+'"');
                                                    } else {
                                                        _tmpVal.push('"');
                                                    }
                                                    _tmpVal.push('>');
                                                    _tmpVal.push(text);
                                                    _tmpVal.push('</i>');
                                                }
                                                else {
                                                    _tmpVal.push('<i class="btn ');
                                                    _tmpVal.push(btn.clas);
                                                    _tmpVal.push('" title="'+btn.title+'"');
                                                    _tmpVal.push('>');
                                                    _tmpVal.push(text);
                                                    _tmpVal.push('</i>');
                                                }
                                            }
                                        });
                                        value = _tmpVal.join('');
                                    }
                                }
                            }
                            else{
                                var oldRender = col.render;
                                // render列
                                if($.isFunction(oldRender)) {
                                    value = oldRender(value, col.visible, row, self.options);
                                }
                            }
                            $(this).find(' > div').html(value);
                            var text=$(this).text();
                            $(this).attr("title",text);
                        });
                    }
                    if($.isArray(col)){
                        $.each(col,function(i,col1){
                            __drawCallback(col1)
                        })
                    }
                    __drawCallback(col);
                    if(col.type == 'action') {
                        rightColumn = col;
                    }
                });
            }
//            // 表格fixed:right时，滚动条错误BUG修复
//            if(rightColumn) {
//                var tdiv = self.$element.next('.layui-table-view').find('.layui-table-header').find('th[data-field="' + rightColumn.field + '"]').find('div.layui-table-cell');
//                tdiv.each(function(i,div) {
//                    var $d = $(div);
//                    $d.width($d.width() + 4);
//                });
//            }
            self._checkOnClick();
            self.initTools();
            // 设置隔行变色（IE8）
            if(navigator.userAgent.indexOf('MSIE 8') > 0) {
                self.$element.next('.layui-table-view').find('.layui-table-body').find('table').each(function() {
                    $(this).find('tr').each(function(i) {
                        if((i+1) % 2 == 0) {
                            $(this).css('background-color', '#f2f2f2');
                        }
                    });
                });
            }

            // 双击行事件
            $(this.elem).next().find('.layui-table-main').off('dblclick').on('dblclick', 'tr', function(e) {
                if(self.options.dblclick && $.isFunction(self.options.dblclick)) {
                    var index = $(this).data('index');
                    var filter = self.layOption.id || self.filter;
                    var rowData = self._getRow(self.layTable.cache[filter], page, index);
                    self.options.dblclick.call(e.srcElement,self, rowData);
                }
            });
            //添加单元格事件
            $(this.elem).next().find('.tableButtonDiv').on('click','.btn-table-add',function (e) {
                var index = $(this).data('index');
                var filter = self.layOption.id || self.filter;
                var newData ={};
                var templet
                    , othis = $(this)
                    , field = othis.parent().data('field')
                    , index = othis.parents('tr').eq(0).data('index')
                    , data = self.layTable.cache[filter][index]
                    , data1= self.layTable.cache[filter].unshift(newData)
                ;
                var data1 = [];
                data1.length = total +1;
                data1[(page-1)*10] ={};
                for(var i=1;i<self.layTable.cache[filter].length;i++){
                    var dataIndex = (page-1)*10+i;
                    if(dataIndex<=data1.length-1){
                        data1[dataIndex] = self.layTable.cache[filter][i];
                    }
                }
//                self.table.config["data"] = data1;
//                var tempUrl = self.table.config['url'];
//                self.table.config['url'] = "";
//                self.table.config["data"] = data1;
//                self.table.render()
                self.table.reload({url:null, data: data1, where: null});
//                self.table.config['url'] = tempUrl;
                self.events();
            });

            // 单击行事件
            $(this.elem).next().find('.layui-table-main').off('click').on('click', 'tr', function(e) {
                if(self.options.click && $.isFunction(self.options.click)) {
                    var index = $(this).data('index');
                    var filter = self.layOption.id || self.filter;
                    var rowData = self._getRow(self.layTable.cache[filter], page, index);
                    self.options.click.call(e.srcElement,self, rowData);
                    if(!self.options.checkbox){
                        layui.event.call(self, 'table', 'selected('+ self.layOption['lay-filter'] +')', {index:index,data:rowData});
                    }
                }
            });
            // 取消loading
            JoyinUtil.hideLoading();
            if(self.options.progressBar) {
                JoyinProgress.hideCommon();
            }

            // 重置滚动条
            if(self.scrollLeft) {
                header.scrollLeft(self.scrollLeft);
                self.$element.next('.layui-table-view').find('.layui-table-body').scrollLeft(self.scrollLeft);
                delete self.scrollLeft;
            }
//
//            setTimeout(function() {
//                self.resize();
//            }, 100);

            // 执行回调
            if(self.options.done && $.isFunction(self.options.done)) {
                setTimeout(function(){
                    var filter = self.layOption.id || self.filter;
                    var data = self.layTable.cache[filter];
                    self.options.done.call(self, data);
                }, 10);
            }
        }
    };

    // 初始化表格工具，表头右键菜单功能和底部toolbar
    JoyinTable.prototype.initTools = function(){
        var self = this
        ,tableId = self.$element.attr('id')
        ,showElement = self.$element.next()
        ,layMain = showElement.find('.layui-table-main')
        ,headToolBar = $('<div class="rMixMenu laytable-div-edit" style="width:100px"></div>').attr('id', 'headdiv_'+tableId)
        ,shadowDiv = $('<div class="layui-layer-shade laytable-shade-tier" id="layui-layer-shade_'+tableId+'" times="2" ></div>')
        // 显示列表头
        ,_sytleBtnEditClick = function(e) {
            headToolBar.css({"visibility": "hidden"});
            //区别与拖动按钮冲突
            if(dragFlag){
                dragFlag = false;
                return;
            }
            if (!self.layOption.tableStyle) {
                Joyin.ajaxSync(JoyinUtil.getRealPath('main/getTableStyle'), {
                    pageId : pageId,
                    elementId : tableId
                }, function(data) {
                    if (data) {
                        self.layOption.tableStyle = JSON.parse(data);
                    }
                });
            }
            var cols = self.options.cols;
            var cusCols = self.layOption.tableStyle || {};
            var editDiv = '<div id="stylediv_' + tableId + '" class="layui-table-fixed laytable-stylediv laytable-div-tier" >';
            editDiv += '<div class="head-tier">显示列<span class="layui-layer-setwin"  id="styleSpan_'+tableId+'" style="top:10px"><a class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;"></a></span></div>';
            editDiv += '<div class="layui-col-md10 body-tier" style="max-height: 300px; overflow-y:auto;">';
            editDiv += '<div class="laytable-checkbox layui-inline">';
            if (cols && $.isArray(cols)) {
                for (var i = 0; i < cols.length; i++) {
                    if (cols[i].type && (cols[i].type === 'action' || cols[i].type === 'checkbox')) {
                        continue;
                    }
                    if (cols[i].visible === false && typeof (cols[i].cusVisible) == "undefined") {
                        continue;
                    }
                    var f = {};
                    if (cusCols.cols) {
                        for (var j = 0; j < cusCols.cols.length; j++) {
                            if (cusCols.cols[j].field === cols[i].field) {
                                f = cusCols.cols[j];
                                break;
                            }
                        }
                    }
                    editDiv += '<div class="laytable-checkbox-f';
                    if(f.cusVisible !== false) {
                        editDiv += ' laytable-checkbox-selected';
                    }
                    // 树状表格的名称列必须显示
                    if(self.options.tree && f.field == self.options.treeShowName) {
                        editDiv += ' disabled';
                    }
                    editDiv += '" id="' + tableId + "_" + cols[i].field + '"title="'
                        + JoyinUtil.getLabel(cols[i].title) + '">'+JoyinUtil.getLabel(cols[i].title)+'</div>';
                }
                editDiv += '</div>';
                editDiv += '</div>';
            }
            // 保存按钮
            editDiv += '<div class="laytable-button-tier"><a id="headsavebtn_' + tableId
                       + '" class="layui-btn layui-btn-sm btn-save">' + JoyinUtil.getLabel('SysCommon.save') + '</a>';
            // 取消按钮
            editDiv += '<a id="headbackbtn_' + tableId
                       + '" class="layui-btn layui-btn-sm btn-cancel">' + JoyinUtil.getLabel('SysCommon.cancel') + '</a></div>';
            showElement.find(".layui-table-box").find('.laytable-stylediv').remove();
            showElement.find(".layui-table-box").append(shadowDiv).append(editDiv);
            $("#headsavebtn_"+tableId).layButton();
            $("#headbackbtn_"+tableId).layButton();
            $("#headsavebtn_" + tableId).on('click', _headSaveBtnClick);
            $("#headbackbtn_"+ tableId).on('click', function(){
                $('#stylediv_'+tableId).remove();
                shadowDiv.remove();
            });
            $("#styleSpan_"+ tableId).on('click', function(){
                $('#stylediv_'+tableId).remove();
                shadowDiv.remove();
            });
            $(".laytable-checkbox-f:not(.disabled)", showElement).on('click', _checkboxClick);
        }
        // 显示列表头
        ,_sytleBtnHierarchyClick = function(e) {
            headToolBar.css({"visibility": "hidden"});
            //区别与拖动按钮冲突
            if(dragFlag){
                dragFlag = false;
                return;
            }
            var tierNum = self.layTable.tier;
            var cols = [];
            for(var i=1;i<=self.layTable.tiernum;i++){
                var colobj={};
                colobj.field=i;
                colobj.title=i+'级';
                cols.push(colobj);
            }
            var cusCols={cols:[]};
            $.each(cols,function(i,entry){
                var cusC={};
                cusC.field=entry.field;
                if(entry.field!=tierNum.toString()){
                    cusC.cusVisible=false;
                }
                else{
                    cusC.cusVisible=true;
                }
                cusCols.cols.push(cusC);
            })

            var editDiv = '<div id="stylediv_' + tableId
                    + '" class="layui-table-fixed laytable-stylediv laytable-div-tier" >';
            editDiv += '<div class="head-tier">显示层级<span class="layui-layer-setwin" id="styleSpan_'+tableId+'" style="top:10px"><a class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;"></a></span></div>';
            editDiv += '<div class="layui-col-md10 body-tier" >';
            editDiv += '<div class="laytable-checkbox layui-inline">';
            if (cols && $.isArray(cols)) {
                for (var i = 0; i < cols.length; i++) {
                    if (cols[i].type
                            && (cols[i].type === 'action' || cols[i].type === 'checkbox')) {
                        continue;
                    }
                    if (cols[i].visible === false && typeof (cols[i].cusVisible) == "undefined") {
                        continue
                    }
                    var f = {};
                    if (cusCols.cols) {
                        for (var j = 0; j < cusCols.cols.length; j++) {
                            if (cusCols.cols[j].field === cols[i].field) {
                                f = cusCols.cols[j];
                                break;
                            }
                        }
                    }
                    editDiv += '<div class="laytable-checkbox-f' + (f.cusVisible === false ? '' : ' laytable-checkbox-selected') + '" data-width=150 '
                        + '" id="' + cols[i].field + '"title="'
                        + cols[i].title + '">'+cols[i].title+'</div>';
                }
                editDiv += '</div></div>';
            }
            // 保存按钮
            editDiv += '<div class="laytable-button-tier"><a id="headsavebtn_' + tableId
                    + '" class="layui-btn layui-btn-sm btn-save">' + JoyinUtil.getLabel('SysCommon.save') + '</a>';
            // 取消按钮
            editDiv += '<a id="headbackbtn_' + tableId
                    + '" class="layui-btn layui-btn-sm btn-cancel">' + JoyinUtil.getLabel('SysCommon.cancel') + '</a></div>';

            showElement.find(".layui-table-box").find('.laytable-stylediv').remove();
            showElement.find(".layui-table-box").append(shadowDiv).append(editDiv);
            $("#headsavebtn_"+tableId).layButton();
            $("#headbackbtn_"+tableId).layButton();
            $("#headsavebtn_" + tableId).on('click', _headSaveDanymicBtnClick);
            $("#headbackbtn_"+ tableId).on('click', function(){
                $('#stylediv_'+tableId).remove();
                shadowDiv.remove();
            });
            $("#styleSpan_"+ tableId).on('click', function(){
                $('#stylediv_'+tableId).remove();
                shadowDiv.remove();
            });
            $(".laytable-checkbox-f", showElement).on('click', _checkboxHierarchyClick);
        }
        ,_checkboxHierarchyClick = function(e){
               if(!$(e.target).hasClass('laytable-checkbox-selected')){
                   $(e.target).addClass('laytable-checkbox-selected');
                  $(e.target).siblings().removeClass('laytable-checkbox-selected');
             }
        }
        ,_checkboxClick = function(e){
            $(e.target).toggleClass('laytable-checkbox-selected');
        }
        //保存树形列表层级
        ,_headSaveDanymicBtnClick=function(e){
          var allData = self.getAllRows();
          var level = parseInt($('.laytable-div-tier .laytable-checkbox-selected', showElement).attr('id'));
          var param = {
               pageId : pageId,
               elementId : tableId,
               userStyle : level
          };
          Joyin.ajax(JoyinUtil.getRealPath('/main/saveTreeTier'),param,function() {
               layer.msg(JoyinUtil.getMessage('TABLESTYLE_I_0001'));
               self.layTable.tier = level;
               $("#headdiv_" + tableId).show();
               $("#stylediv_" + tableId).hide();
               $("#style_btn_hierarchy_" + tableId).on('click',
                       _sytleBtnHierarchyClick);
               self.layTable.trarrays=[];
               if(!self.table.unspread(level)) {
                   self.table.spread(level);
               }
               shadowDiv.remove();
           });
        }
        ,_headSaveBtnClick = function(e) {
            var data = {};
            var cols = [];
            $("#stylediv_" + tableId).find('.laytable-checkbox-f').each(function(i, v) {
                var col = {};
                col.field = v.id.replace(tableId + "_", '');
                if (!$(v).hasClass('laytable-checkbox-selected')) {
                    col.visible = false;
                    col.cusVisible = false;
                }
                cols.push(col);
            });
            data.cols = cols;
            self.layOption.tableStyle = data;
            var param = {
                pageId : pageId,
                elementId : tableId,
                userStyle : JSON.stringify(self.layOption.tableStyle)
            }
            Joyin.ajax(JoyinUtil.getRealPath('/main/saveTableStyle'),param,function() {
                layer.msg(JoyinUtil.getMessage('TABLESTYLE_I_0001'));
                // tablestyle更新到self.layOption.cols,self.options.cols
                if (self.layOption.tableStyle.cols !== self.layOption.cols) {
                    var cols = self.options.cols;
                    var cusCols = self.layOption.tableStyle.cols;
                    for (var i = 0; i < cols.length; i++) {
                        for (var j = 0; j < cusCols.length; j++) {
                            if (cols[i].field === cusCols[j].field) {
                                cols[i].width = cusCols[j].width;
                                if (typeof (cusCols[j].cusVisible) !== "undefined") {
                                    cols[i].visible = false;
                                    cols[i].cusVisible = false;
                                } else {
                                    delete cols[i].visible;
                                    delete cols[i].cusVisible;
                                }
                                break;
                            }
                        }
                    }
                    self.options.cols = cols;
                }
                self.layOption.cols = [];
                var chCol = {type: 'checkbox'};
                if(!self.options.tree) {
                    chCol.fixed = 'left';
                }
                self.layOption.cols.push(chCol);
                self._createCol();
                self.layOption.cols = [ self.layOption.cols ];
                $("#headdiv_" + tableId).show();
                $("#stylediv_" + tableId).hide();
                $("#style_btn_edit_" + tableId).on('click',
                        _sytleBtnEditClick);
                self.layTable.render(self.layOption);
            });
        };
        if(self.options.contextmenu) {
            // head添加右键菜单
            $('#headdiv_' + tableId).remove();
            var ul = $('<ul></ul>').appendTo(headToolBar);
            var btnSetColumn = $('<li style="padding-top:0px!important" class="laytable-btn-down"></li>');
            btnSetColumn.append('<i class="iconfont icon-edit"></i>&nbsp;设定显示列');
            ul.append(btnSetColumn);
            btnSetColumn.on('click', _sytleBtnEditClick);
            if(self.options.tree){
                var btnSetHierarchy = $('<li style="padding-top:0px!important" class="laytable-btn-down"></li>');
                btnSetHierarchy.append('<i class="iconfont icon-edit"></i>&nbsp;设定树层级');
                ul.append(btnSetHierarchy);
                btnSetHierarchy.on('click', _sytleBtnHierarchyClick);
            };
            $("body").after(headToolBar);
            
            $('.layui-table-header').off('contextmenu').on('contextmenu', showElement, function(event){
                var top= parseFloat(event.clientY) + parseFloat(document.documentElement.scrollTop);
                var left = parseFloat(event.clientX) + parseFloat(document.documentElement.scrollLeft);
                headToolBar.css({top: top , left: left, visibility: "visible"});
                $("body").bind("mousedown", function(){
                    headToolBar.css({"visibility": "hidden"});
                });
            });
            
            if(self.options.tree && self.treeTier) {
                if(!self.table.unspread(self.layTable.tier)) {
                    self.table.spread(self.layTable.tier);
                }
                self.treeTier = false;
            }
        }

        //拖动
        var dragFlag = false;
       /* document.getElementById('headdiv_'+self.$element.attr('id')).onmousedown=function(){
            var oblong=document.getElementById('headdiv_'+self.$element.attr('id'));
            oblong.style.cursor="pointer";
            document.onmousemove=function(){
                var x=event.clientX;
                var y=event.clientY;
                oblong.style.position='fixed';
                oblong.style.left=(x-18)+'px';
                oblong.style.top=(y-16)+'px';
                dragFlag = true;
            };
        };
        document.getElementById('headdiv_'+self.$element.attr('id')).onmouseup=function(){
             document.onmousemove=false;
        };*/

        // 按钮：+
        if(self.options.addbtn){
            var addDiv = $('<div class="layui-inline tableButtonDiv"></div>');
            var addBtn = $('<a class="btn-table-add"><i class="iconfont icon-found"></i></a>');
            addDiv.append(addBtn);
            showElement.find(".layui-table-page").find(".tableButtonDiv").remove();
            // 如果有addbtn，则无论表格有无数据，都需要显示该按钮
            showElement.find(".layui-table-page").removeClass('layui-hide').prepend(addDiv);
            addBtn.click(function(e) {
                if (self.options.addclick && $.isFunction(self.options.addclick)) {
                    self.options.addclick.call(e.target, self);
                }
            });
        }

        //根据表头类型加下拉选项
        if(self.options && $.isArray(self.options.cols)){
            $.each(self.options.cols, function(i,v){
                if(v.type) {
                    var sel;
                    var tmpId = tableId + '_' + v.field;
                    var targetHeadCell = showElement.find(".layui-table-header [data-field='"+v.field+"']").children(".layui-table-cell");
                    if(v.type == "money"){
                        sel = '<div class="selectDiv" id="div_'+tmpId+'" style="display:inline;margin-left:20px"><a data-field="'+v.field+'" class="select-span layui-inline btn-downing"></a></div>';
                    }
                    if(v.type == "number" && (v.digit == undefined || v.digit == null)){
                        sel = '<div class="selectDiv" id="numberdiv_'+tmpId+'" style="display:inline;margin-left:20px"><a data-field="'+v.field+'" class="select-decimal-span layui-inline btn-downing"></a></div>';
                    }
                    if(sel) {
                        targetHeadCell.find('.selectDiv').remove();
                        targetHeadCell.append(sel);
                    }
                }
            });
            showElement.find(".btn-downing").layButton();
            showElement.find(".btn-downing").find("span").remove();
        }

        //表头显示金额单位
        $(".select-span", showElement).off('click').on('click', function(e){
            $(".laytable-moneyTypeDiv").hide();
            $(".laytable-decimalTypeDiv").hide();
            if(!$(e.target).hasClass("select-span") && !$(e.target).hasClass("iconfont")){
                event=event||window.event;
                event.stopPropagation();
                return;
            }
            var field = $(e.target.parentElement).attr('data-field');
            var moneyType = self.layOption.moneyType;
            var sel = '<div class="laytable-moneyTypeDiv laytable-spanDiv" data-field="'+field+'" style="left:'+e.clientX+'px">';
            if(moneyType && $.isArray(moneyType)){
                var fieldIndex;
                for(var m =0; m < self.options.cols.length; m++){
                    if(self.options.cols[m].field === field){
                        fieldIndex = m;
                        break;
                    }
                }
                for(var i=0; i< moneyType.length; i++){
                   if(!self.options.cols[fieldIndex].moneyType && i === 0){
                       sel +='<span class="laytable-moneyTypeSpan laytable-selectedSpan" value="'+moneyType[i].itemCode+'">'+moneyType[i].itemValue+'</span>';
                   }else if (self.options.cols[fieldIndex].moneyType === moneyType[i].itemCode){
                       sel +='<span class="laytable-moneyTypeSpan laytable-selectedSpan" value="'+moneyType[i].itemCode+'">'+moneyType[i].itemValue+'</span>';
                   } else{
                       sel +='<span class="laytable-moneyTypeSpan" value="'+moneyType[i].itemCode+'">'+moneyType[i].itemValue+'</span>';
                   }
                }
            }
            sel += '</div>';
            $(e.target).children('div').remove();
            $(e.target).append(sel);

            $(".laytable-moneyTypeSpan", showElement).off('click').on('click', function(e){
                var tableStyle = self.layOption.tableStyle;
                var field = $(e.target.parentElement).attr('data-field');
                var cols = self.options.cols;
                var moneyType = $(e.target).attr('value');
                if(tableStyle){
                    for(var i = 0; i< tableStyle.cols.length; i++){
                        if(tableStyle.cols[i].field === field){
                            self.layOption.tableStyle.cols[i].moneyType = moneyType;
                            break;
                        }
                    }
                }else{
                    tableStyle = {};
                    var cusCol = [];
                    for (var i = 0; i < cols.length; i++) {
                        if (cols[i].field === field) {
                            var data = cols[i];
                            data.moneyType = moneyType;
                            cusCol.push(cols[i]);
                            tableStyle.cols = cusCol;
                            break;
                        }
                    }
                }

                var param = {
                    pageId : pageId,
                    elementId : tableId,
                    userStyle : JSON.stringify(self.layOption.tableStyle)
                };
                Joyin.ajax(JoyinUtil.getRealPath('/main/saveTableStyle'),param,function() {
                    // tablestyle更新到self.layOption.cols,self.options.cols
                    for (var i = 0; i < cols.length; i++) {
                        if (cols[i].field === field) {
                            cols[i].moneyType = moneyType;
                            break;
                        }
                    }
                    self.options.cols = cols;
                    self.layOption.cols = [];
                    var chCol = {type: 'checkbox'};
                    if(!self.options.tree) {
                        chCol.fixed = 'left';
                    }
                    self.layOption.cols.push(chCol);
                    self._createCol();
                    self.layOption.cols = [ self.layOption.cols ];
                    self.layTable.render(self.layOption);
                });
            });
            event=event||window.event;
            event.stopPropagation();
        });

        //表头显示小数位数
        $(".select-decimal-span", showElement).off('click').on('click', function(e){
            $(".laytable-decimalTypeDiv").hide();
            $(".laytable-moneyTypeDiv").hide();
            if(!$(e.target).hasClass("select-span") && !$(e.target).hasClass("iconfont")){
                event=event||window.event;
                event.stopPropagation();
                return;
            }
            var field = $(e.target.parentElement).attr('data-field');
            var decimalType = self.layOption.decimalType;
            var sel = '<div class="laytable-decimalTypeDiv laytable-spanDiv" data-field="'+field+'" style="left:'+e.clientX+'px">';
            if(decimalType && $.isArray(decimalType)){
                var fieldIndex;
                for(var m =0; m < self.options.cols.length; m++){
                    if(self.options.cols[m].field === field){
                        fieldIndex = m;
                        break;
                    }
                }
                for(var i=0; i< decimalType.length; i++){
                   if(!self.options.cols[fieldIndex].decimalType && i === 0){
                       sel +='<span class="laytable-decimalTypeSpan laytable-selectedSpan" value="'+decimalType[i].itemCode+'">'+decimalType[i].itemValue+'</span>';
                   }else if (self.options.cols[fieldIndex].decimalType === decimalType[i].itemCode){
                       sel +='<span class="laytable-decimalTypeSpan laytable-selectedSpan" value="'+decimalType[i].itemCode+'">'+decimalType[i].itemValue+'</span>';
                   } else{
                       sel +='<span class="laytable-decimalTypeSpan" value="'+decimalType[i].itemCode+'">'+decimalType[i].itemValue+'</span>';
                   }
                }
            }
            sel += '</div>';
            $(e.target).children('div').remove();
            $(e.target).append(sel);

            $(".laytable-decimalTypeSpan", showElement).off('click').on('click', function(e){
                var tableStyle = self.layOption.tableStyle;
                var field = $(e.target.parentElement).attr('data-field');
                var cols = self.options.cols;
                var decimalType = $(e.target).attr('value');
                if(tableStyle){
                    for(var i = 0; i< tableStyle.cols.length; i++){
                        if(tableStyle.cols[i].field === field){
                            self.layOption.tableStyle.cols[i].decimalType = decimalType;
                            break;
                        }
                    }
                }else{
                    tableStyle = {};
                    var cusCol = [];
                    for (var i = 0; i < cols.length; i++) {
                        if (cols[i].field === field) {
                            var data = cols[i];
                            data.decimalType = decimalType;
                            cusCol.push(cols[i]);
                            tableStyle.cols = cusCol;
                            break;
                        }
                    }
                }

                var param = {
                        pageId : pageId,
                        elementId : tableId,
                        userStyle : JSON.stringify(self.layOption.tableStyle)
                    }
                Joyin.ajax(JoyinUtil.getRealPath('/main/saveTableStyle'),param,function() {
                    // tablestyle更新到self.layOption.cols,self.options.cols
                    for (var i = 0; i < cols.length; i++) {
                        if (cols[i].field === field) {
                            cols[i].decimalType = decimalType;
                            break;
                        }
                    }
                    self.options.cols = cols;
                    self.layOption.cols = [];
                    var chCol = {type: 'checkbox'};
                    if(!self.options.tree) {
                        chCol.fixed = 'left';
                    }
                    self.layOption.cols.push(chCol);
                    self._createCol();
                    self.layOption.cols = [ self.layOption.cols ];
                    self.layTable.render(self.layOption);
                });
            });
            event=event||window.event;
            event.stopPropagation();
        });
    };
    //请求loading
    JoyinTable.prototype.loading = function(){
        var that = this
        ,config = that.layOption
        ,elem = $(config.elem);
        if(config.loading/* && config.url*/){
            if(that.$element && that.$element.next().length > 0) {
                elem = that.$element.next();
            }
            JoyinUtil.showLoading(elem);
        }
        if(that.options.progressBar) {
            JoyinProgress.showInvented(false);
        }
      };

      //获取滚动条宽度
      JoyinTable.prototype.getScrollWidth = function(elem){
        var width = 0;
        if(elem){
          width = elem.offsetWidth - elem.clientWidth;
        } else {
          elem = document.createElement('div');
          elem.style.width = '100px';
          elem.style.height = '100px';
          elem.style.overflowY = 'scroll';
          document.body.appendChild(elem);
          width = elem.offsetWidth - elem.clientWidth;
          document.body.removeChild(elem);
        }
        return width;
    };
    // 清空表格数据，只清空前台
    JoyinTable.prototype.clear = function(callback) {
        this.table.reload({ajax:null, url: null, data:[]});
        if(callback && $.isFunction(callback)) {
            callback();
        }
    };

    /*
     * 显示表格指定列，前提条件是该列必须要存在cols定义中
     * cols: Array 列的field属性，以及visible属性
     */
    JoyinTable.prototype.showColumns = function(cols) {
        var oldCols = this.options.cols, _hiddenField = [];
        _.each(oldCols, function(col) {
            _.each(cols, function(c) {
                if(col.field && col.field === c.field) {
                    col.visible = !!c.visible;
                }
            });
        });
        this.layOption.cols = [];
        this._addRowNo();
        this._selectSet();
        this._createCol(true);
        this.layOption.cols = this.options.isMulti ? this.layOption.cols : [this.layOption.cols];
        this.reload({cols: this.layOption.cols});
    };

    /* joyintable 插件定义
    * ======================= */
    $.fn.joyintable = function (option) {
        var $this = $(this),
            data = $this.data('joyintable'),
            options = $.extend(true, {}, $.fn.joyintable.defaults, option);
        if (!data) $this.data('joyintable', (data = new JoyinTable(this, options)));
        return data;
    };

    /**
     * 默认配置
     */
    $.fn.joyintable.defaults = {
        // 是否显示checkbox
        checkbox: true,
        // 是否显示行号
        rowno: false,
        // 是否有权限控制
        permissions: true,
        // 是否显示快捷菜单
        contextmenu: true,
        // 是否前台分页
        loadonce: false,
        // 是否在初始化的时候就加载数据
        searchOnInit: false,
        // 单击选中行
        checkOnClickRow: true,
        // 是否为树形表格
        tree: false,
        // 树型表格总层级
        tiernum: 4,
        //加号按钮是否显示
        addbtn: false,
        // 延迟加载时间
        lazytime: 300,
        // 是否自适应大小
        resize: true,
        // 显示进度条
        progressBar: false,
        limits:[10,20,30,40,50,60,70,80,90],
        // 行选中时的样式
        rowSelectedClass: 'table-row-select',
        // 按钮设置
        buttons: {
            edit: {
                text: '&#xe642;',
                clas: 'edit',
                title: '编辑'
            },
            delt: {
                text: '&#xe640;',
                clas: 'delt',
                title: '删除'
            }
            ,
            add: {
                text: '&#xe654;',
                clas: 'add',
                title: '添加'
            }
        }
    };

    /**
     * 获取没有权限的操作列
     */
    var getNoPurviewElements = function(options, row) {
        if(options.permissions && typeof pageModel !== 'undefined' && pageModel !== PAGE_MODEL_REFERENCE ) {
            /* 操作列权限控制
             * _edit: 编辑权限（行内编辑和非行内编辑都应该用_edit结尾）
             * _delete: 删除权限（行内删除和非行内删除都应该用_delete结尾）
             */
            if(typeof purviewElements == 'undefined') {
                purviewElements = [];
            }
            var purivew = [];
            var editAuth = false;
            var deleteAuth = false;
            // 角色权限
            _.each(purviewElements, function(ele) {
                // 编辑权限
               if(ele.indexOf('_edit') != -1) {
                   editAuth = true;
               }
               // 删除权限
               if(ele.indexOf('_delete') != -1) {
                   deleteAuth = true;
               }
            });
            // 数据权限
            var isContain = false;
            _.each(dataPurview, function(ele) {
                if(row[ele.field]) {
                   if(row[ele.field] == ele.value) {
                       // 编辑权限
                       editAuth = editAuth && ele['modify'];
                       // 删除权限
                       deleteAuth = deleteAuth && ele['delete'];
                       isContain = true;
                   }
               } else {
                   isContain = true;
               }
            });
            if (dataPurview && dataPurview.length > 0 && !isContain) {
                editAuth = false;
                deleteAuth = false;
            }
            
            if(!editAuth) {
                purivew.push('edit');
            }
            if(!deleteAuth) {
                purivew.push('delt');
            }
            return purivew;
        }
        return [];
    };

    /**
     * @param target 被比较的对象
     * @param data 比较对象
     */
    var compare = function(target, data) {
        var flag = true;
        $.each(target, function(key, value) {
            if(data[key] && value && data[key] !== value) {
                flag = false;
                return;
            }
        });
        return flag;
    };
}(window.jQuery);