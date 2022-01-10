;layui.define(["jquery", "laytpl", "element"], function(e) {
    var i = layui.jquery
      , n = (i(window),
    i(document))
      , t = layui.laytpl;
    e("onelevel", {
        v: "1.0.1",
        config: {
            elem: void 0,
            data: void 0,
            wrapWidth: 140,
            type: 'click', // hover, click
            remote: {
                url: void 0,
                type: "GET"
            },
            onClicked: void 0
        },
        set: function(e) {
            var n = this;
            return i.extend(!0, n.config, e),
            n
        },
        hasElem: function() {
            var e = this.config;
            return void 0 !== e.elem || 0 !== n.find("ul[kit-one-level]").length || !i(e.elem) || (console.log("One-Level error:请配置One-Level容器."),
            !1)
        },
        getElem: function() {
            var e = this.config;
            return void 0 !== e.elem && i(e.elem).length > 0 ? i(e.elem) : n.find("ul[kit-one-level]")
        },
        hideOverflow: function() {
        	if (!this.hasElem())
                return this;
            var c = this.getElem();
            var overflowElement = c.find('>li').filter(function(){return $(this).offset().top > 0;});
            overflowElement.addClass('top-nav-hide').hide();
            if(overflowElement.length > 0) {
            	//c.next('.top-nav-toggle-btn').show().css('left', c.offset().left + c.width() + 25);
            	c.next('.left-nav-toggle-btn').show().css('left', c.offset().left + c.width() + 70)
            	.next('.right-nav-toggle-btn').show().css('left', c.offset().left + c.width() + 67);
            }
        },
        bind: function() {
            var n = this.config
                , $elem = this.getElem()
                , w = n.wrapWidth;
            
            var updateWidth = function(target) {
        		var wrap = $(target).parents('.menu-child-wrap'), width = 0, _tmpWidth = wrap.width();
        		wrap.find('.menu-child-container.layui-show').each(function() {
        			width += $(this).width();
        		});
        		if(_tmpWidth < width || $(target).find('.right-icon-wrap').length == 0) {
        			wrap.width(width);
        		}
            };
            
            var hideSubMenu = function(container, menuId) {
            	var next = container.next(), children;
            	if(next.length == 1) {
            		next.find('li.layui-show').removeClass('layui-show').addClass('layui-hide');
            		if(menuId) {
            			children = next.find('li[data-id^="'+ menuId +'"]');
            			children.removeClass('layui-hide').addClass('layui-show');
            			if(next.hasClass('layui-hide')) {
            				next.css('minHeight', container.height());
            				next.removeClass('layui-hide').addClass('layui-show');
            			}
            		}
            		else {
            			if(next.hasClass('layui-show')) {
            				next.removeClass('layui-show').addClass('layui-hide');
                            next.find("ul li").removeClass("li-hover");
            			}
            		}
            		if(!children || children.length == 0) {
        				next.removeClass('layui-show').addClass('layui-hide');
                        next.find("ul li").removeClass("li-hover");
        			}
            		hideSubMenu(next);
            	}
            };
            
            if("function" == typeof n.onClicked){
            	$elem.find("li.nav-item").off("click").on("click", function() {
                    var e = i(this).children("a"), a = e.data("options");
                    if (void 0 !== a && a !==null) {
                    	try {
                    		e = new Function("return " + a)()
                    	} catch (i) {
                    		layui.hint().error("onelevel 组件a[data-options]配置项存在语法错误：" + a)
                    	}
                        n.onClicked(e);
                    }
                    $(".menu-child-wrap").removeClass('layui-show');
                });
            }

            $elem.find(".menu-child-container").find("li.nav-item").mouseenter(function() {
            	var container = $(this).parents('.menu-child-container');
        		var menuId = $(this).data('id');
            	hideSubMenu(container, menuId);
            	updateWidth(this);
            	if(document.all) {
            		event.cancelBubble=true;
            	} else {
            		event.stopPropagation();
            	}
            });
            $elem.find(".menu-child-container ul li").hover(function() {
            	$(this).siblings().removeClass("li-hover")
                $(this).addClass("li-hover");
            });

            $elem.find('.menu-child-wrap').mouseleave(function() {
            	var wrap = $(this);
            	var container = $(this).find('.menu-child-container:gt(0)');
            	container.find('li.layui-show').removeClass('layui-show').addClass('layui-hide');
            	container.removeClass('layui-show').addClass('layui-hide');
            	//wrap.width(w);
            	wrap.width('auto');
            });
            $elem.find(">li").hover(function(){
            	$elem.find(".menu-child-container ul li").removeClass("li-hover")
			});
            /*$elem.next('.top-nav-toggle-btn').click(function() {
            	var hide = $(this).hasClass('icon-down');
            	if(hide) {
            		$elem.find('>li.top-nav-hide').show();
            		$(this).removeClass('icon-down').addClass('icon-turn-top');
            	}
            	else {
            		$elem.find('>li.top-nav-hide').hide();
            		$(this).removeClass('icon-turn-top').addClass('icon-down');
            	}
            });*/
        },
        render: function() {
            var e = this
              , n = e.config
              , l = n.remote
              , a = ["{{# layui.each(d,function(index, item){ }}", '<li class="layui-nav-item">', '<a href="javascript:;" data-options="{title:\'{{item.title}}\', icon:\'{{item.icon}}\', id:\'{{item.id}}\', url:\'{{item.url}}\',pageId:\'{{item.pageId}}\'}">', '{{# if (item.icon.indexOf("fa-") !== -1) { }}', '<i class="fa {{item.icon}}" aria-hidden="true"></i>', "{{# } else { }}", '<i class="layui-icon">{{item.icon}}</i>', "{{# } }}", "<span> {{item.title}}</span>", "</a>", "{{# var hasChildren = item.children!==undefined && item.children.length>0; }}", "{{# if(hasChildren){ }}", '<div class="layui-nav-child menu-child-wrap">', '<div class="menu-child-container">', '<ul class="menu-wrapper">', "{{# var children = item.children; }}", "{{# layui.each(children,function(childIndex, child){ }}",'<li data-type="1">',"<a href=\"javascript:;\" kit-target data-options=\"{url:'{{child.url}}',icon:'{{child.icon}}',title:'{{child.title}}',id:'{{child.id}}', pageId: '{{child.pageId}}'}\">", '{{# if (child.icon.indexOf("fa-") !== -1) { }}', '<i class="fa {{child.icon}}" aria-hidden="true"></i>', "{{# } else { }}", '<i class="layui-icon">{{child.icon}}</i>', "{{# } }}", "<span> {{child.title}}</span>", '<i class="iconfont icon-rightarrow right-icon-wrap"></i>', "</a>", "</li>", "{{# }); }}","</ul>", "</div>","</div>","{{# } }}","</li>", "{{# }); }}"]
              , o = []
              , level = 0
              , r = layer.load(2);
            if (!e.hasElem())
                return e;
            var c = e.getElem();
            if (void 0 !== n.data && n.data.length > 0)
                o = n.data;
            else {
                l.jsonp;
                var d = {
                    url: l.url,
                    type: l.type,
                    cache: false, //关闭AJAX缓存
                    error: function(e, i, n) {
                        layui.hint().error("One-Level error:AJAX请求出错." + n)
                    },
                    success: function(e) {
                        o = e;
                        r && layer.close(r);
                    }
                };
                i.extend(!0, d, l.jsonp ? {
                    dataType: "jsonp",
                    jsonp: "callback",
                    jsonpCallback: "jsonpCallback"
                } : {
                    dataType: "json"
                }),
                i.support.cors = !0,
                i.ajax(d)
            }

            /* click start */
            var g = function(d) {
            	var html = [];
            	var menuiconAndNameOneLine = "";
            	var menuiconAndNameOneLine_lineHeight = "";
                //菜单名称和图标同行展示
                if(MENUNAME_ICON_ONELINE == "02"){
                	menuiconAndNameOneLine='style="padding-top:5px;text-align:center;display:block;" ';
                	menuiconAndNameOneLine_lineHeight = ' style="line-height: 20px;" ';
                }
            	layui.each(d,function(index, item){
            		level = 0;
        			html.push('<li class="layui-nav-item nav-item menu_menu_distance_top"'+menuiconAndNameOneLine_lineHeight+'>');
        			var hasChildren = item.children!==undefined && item.children.length>0;
            		if(hasChildren){
            			html.push('<a href="javascript:;">');
            		}
            		else {
            			html.push('<a href="javascript:;" kit-target data-options="{url:\'' + item.url + '\',icon:\'' + item.icon + '\',title:\'' + item.title + '\',id:\'' + item.id + '\', pageId:\'' + item.pageId + '\'}">');
            		}
            		if(item.icon) {
            			if (item.icon.indexOf("icon-") !== -1) {
            				html.push('<i class="iconfont ' + item.icon + '"'+menuiconAndNameOneLine+'></i>');
            			}
            			else if (item.icon.indexOf("fa-") !== -1) {
            				html.push('<i class="fa ' + item.icon + '"'+menuiconAndNameOneLine+' aria-hidden="true"></i>');
            			}
            			else {
            				html.push('<i class="layui-icon" '+menuiconAndNameOneLine+'>' + item.icon + '</i>');
            			}
            		}
            		html.push('<span> ' + item.title + '</span>');
            		html.push('</a>');

            		if(hasChildren){
            			var children = item.children, width = n.wrapWidth;
            			var childrenHtml = gg(children, false);
                    	html.push('<div class="layui-nav-child menu-child-wrap">');
            			html.push(childrenHtml);
            			html.push('</div>');
            		}
            		html.push('</li>');
            	});
            	return html.join('');
            };
            
            var gg = function(children, hide) {
            	var html = [], subChildren = [], navClass = hide ? 'layui-hide' : 'layui-show';
            	html.push('<div class="menu-child-container ' + navClass + '">');
        		html.push('<ul class="menu-wrapper">');
    			layui.each(children,function(childIndex, child){
    				var hasChildren = !!child.children;
					html.push('<li class="nav-item ' + navClass + '" data-id="' + child.id + '">');
    				if(hasChildren) {
    					subChildren = subChildren.concat(child.children);
    					html.push('<a href="javascript:;" style="position: relative;">');
    				}
    				else {
    					html.push('<a href="javascript:;" data-options="{url:\'' + child.url + '\',icon:\'' + child.icon + '\',title:\'' + child.title + '\',id:\'' + child.id + '\', pageId: \'' + child.pageId + '\'}">');
    				}
    				if (child.icon) {
        				if (child.icon.indexOf("icon-") !== -1) {
        					html.push('<i class="iconfont ' + child.icon + '"></i>');
        				}
        				else if (child.icon.indexOf("fa-") !== -1) {
        					html.push('<i class="fa ' + child.icon + '" aria-hidden="true"></i>');
        				}
        				else {
        					html.push('<i class="layui-icon">' + child.icon + '</i>');
        				}
    				}
    				html.push('<span> ' + child.title + '</span>');
    				if(hasChildren) {
    					html.push('<i class="iconfont icon-rightarrow right-icon-wrap" style="position: absolute; right: 5px;"></i>');
    				}
    				html.push('</a>');
    				html.push('</li>');
    			});
    			html.push('</ul>');
            	html.push('</div>');
            	level = level + 1;
    			if(subChildren.length > 0) {
    				html.push(gg(subChildren, true));
    			}
    			return html.join('');
            };
            /* click end */

            /* hover start */
            var g2 = function(t) {
            	var menuiconAndNameOneLine = "";
            	var menuiconAndNameOneLine_lineHeight = "";
                //菜单名称和图标同行展示
                if(MENUNAME_ICON_ONELINE == "02"){
                	menuiconAndNameOneLine='style="padding-top:5px;text-align:center;display:block;" ';
                	menuiconAndNameOneLine_lineHeight = ' style="line-height: 20px;" ';
                }
                var html = [];
                layui.each(t,function(index, item){
                    var level = 0;
                    html.push('<li class="layui-nav-item nav-item left-nav-item top-nav-item menu_menu_distance_top"'+menuiconAndNameOneLine_lineHeight+'>');
                    var hasChildren = item.children!==undefined && item.children.length>0;
                    if(hasChildren){
                        html.push('<a href="javascript:;">');
                    }
                    else {
                        html.push('<a href="javascript:;" kit-target data-options="{url:\'' + item.url + '\',icon:\'' + item.icon + '\',title:\'' + item.title + '\',id:\'' + item.id + '\', pageId:\'' + item.pageId + '\'}">');
                    }
                    if(item.icon) {
                        if (item.icon.indexOf("icon-") !== -1) {
                            html.push('<i class="iconfont ' + item.icon + '"'+menuiconAndNameOneLine+'></i>');
                        }
                        else if (item.icon.indexOf("fa-") !== -1) {
                            html.push('<i class="fa ' + item.icon + '" '+menuiconAndNameOneLine+'aria-hidden="true"></i>');
                        }
                        else {
                            html.push('<i class="layui-icon"'+menuiconAndNameOneLine+'>' + item.icon + '</i>');
                        }
                    }
                    html.push('<span> ' + item.title + '</span>');
                    html.push('</a>');
                    if(hasChildren){
                        var children = item.children, width = n.wrapWidth;
                        html.push('<div class="layui-nav-child menu-child-wrap1" style="background-color: #fff!important;min-width:100px;border: 1px solid #dddddd;">');
                        html.push('<ul class="menu-wrapper">');
                        var isCreateSecond = false;
                        var isAllSecond = true;
                        layui.each(children,function (index,item) {
                            var hasThirdChildren = item.children&&item.children.length>0;
                            if(hasThirdChildren){
                                isAllSecond = false;
                                return false;
                            }
                        });
                        var childrenLength = children?children.length:0;
                        layui.each(children,function (index,item) {
                            if(isAllSecond){
                                html.push('<li class="nav-item" style="vertical-align:top;">');
                                html.push('<a href="javascript:;" style="color: #333!important;" kit-target data-options="{url:\'' + item.url + '\',icon:\'' + item.icon + '\',title:\'' + item.title + '\',id:\'' + item.id + '\', pageId:\'' + item.pageId + '\'}">');
                                html.push(item.title);
                                html.push('</a>');
                                html.push('</li>');
                            }
							else {
								var hasThirdChildren = item.children&&item.children.length>0;
								if(hasThirdChildren){
									if(isCreateSecond){
										html.push('</ul>');
										html.push('</div>');
										html.push('</li>');
									}
									html.push('<li class="nav-item bbbb" style="vertical-align:top;display: inline-block;border-right: 1px solid #dddddd;">');
									html.push('<a class="top-item-linking" href="javascript:;" style="color: #999!important;">');
									html.push(item.title);
									html.push('</a>');
									var thirdChildren = item.children;
									html.push('<div>');
									html.push('<ul>');
									layui.each(thirdChildren,function (index,item) {
										html.push('<li class="nav-item ddd">');
										html.push('<a href="javascript:;" style="color: #333!important;" kit-target data-options="{url:\'' + item.url + '\',icon:\'' + item.icon + '\',title:\'' + item.title + '\',id:\'' + item.id + '\', pageId:\'' + item.pageId + '\'}">');
										html.push(item.title);
										html.push('</a>');
										html.push('</li>');
									});
									html.push('</ul>');
									html.push('</div>');
									html.push('</li>');
									isCreateSecond = false;
									//todo 处理之前是无三级菜单，当前为有三级菜单的情况
								}else if(!hasThirdChildren && isCreateSecond){
									//todo 处理之前无三级菜单，当前也无三级菜单
									html.push('<li class="nav-item aaa">');
									html.push('<a href="javascript:;" style="color: #333!important;" kit-target data-options="{url:\'' + item.url + '\',icon:\'' + item.icon + '\',title:\'' + item.title + '\',id:\'' + item.id + '\', pageId:\'' + item.pageId + '\'}">');
									html.push(item.title);
									html.push('</a>');
									html.push('</li>');
									if(index == childrenLength-1){
										html.push('</ul>');
										html.push('</div>');
										html.push('</li>');
									}
									//todo 如果是最后一个元素，需要结束对无三级菜单的处理
								}else if(!hasThirdChildren && !isCreateSecond){
									html.push('<li class="nav-item cccc" style="vertical-align:top;display: inline-block;border-right: 1px solid #dddddd;">');
									html.push('<div>');
									html.push('<ul>');
									html.push('<li class="nav-item">');
									html.push('<a href="javascript:;" style="color: #333!important;" kit-target data-options="{url:\'' + item.url + '\',icon:\'' + item.icon + '\',title:\'' + item.title + '\',id:\'' + item.id + '\', pageId:\'' + item.pageId + '\'}">');
									html.push(item.title);
									html.push('</a>');
									html.push('</li>');

									if(index == childrenLength-1){
										html.push('</ul>');
										html.push('</div>');
										html.push('</li>');
									}
									isCreateSecond = true;
									//todo 再一次处理无三级菜单的二级菜单
								}
							}

                        });
                        html.push('</ul>');
                        html.push('</div>');
                    }
                    html.push('</li>');
                });
                return html.join('');
            };

            /* hover end */
            
            var j = function(t) {
            	if(n.type == 'hover') {
            		return g2(t);
            	}
        		return g(t);
            };
            var setTop = function(f){

                if(n.type != 'hover') {
                    return;
                }
                var width = $(".layui-side-scroll").width();
                var lis = f.children("li");
                $(lis).hover(function(){
                    var windowWidth = window.innerWidth;
                    var left = $(this).offset().left;
                    var divChildren = $(this).children("div");
                    var width = divChildren.width();
                    if(width+left>windowWidth){
                        divChildren.css("left",windowWidth-width-left);
                    }
                    var secondChildren = divChildren.children("ul").children("li");
					if(secondChildren.find('ul').length > 0) {
						secondChildren.height(divChildren.height());
					}
                })
            }

            var u = setInterval(function() {
                o.length > 0 && (clearInterval(u),function(t) {
                	t = j(t),
                    c.html(t),
                        setTop(c),
                    layui.element.init(),
                    e.bind(),
                    e.hideOverflow(),
                    r && layer.close(r)
                }(o))
                /*t(a.join("")).render(o, function(e) {
                    c.html(e),
                    layui.element.init(),
                    "function" == typeof n.onClicked && c.children("li.layui-nav-item").off("click").on("click", function() {
                        var e = i(this).children("a"), a = e.data("options");
                        if (void 0 !== a) {
                        	try {
                        		e = new Function("return " + a)()
                        	} catch (i) {
                        		layui.hint().error("onelevel 组件a[data-options]配置项存在语法错误：" + a)
                        	}
                        } else {
                        	e = {
                    			icon: e.data("icon"),
                    			id: e.data("id"),
                    			title: e.data("title"),
                    			url: e.data("url")
                        	};
                        }
                        n.onClicked(e)
                    }),
                    r && layer.close(r)
                })*/
            }, 50);
            return e
        }
    })
});