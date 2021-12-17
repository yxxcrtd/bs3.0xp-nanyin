;layui.define(["layer", "laytpl", "element"], function(i) {
    var e = layui.jquery
      , a = layui.layer
      , t = (e(window),
    e(document))
      , n = layui.laytpl
      , l = layui.element;
    i("navbar", {
        v: "1.0.2",
        config: {
            data: void 0,
            type: "click",
            remote: {
                url: void 0,
                type: "GET",
                jsonp: !1
            },
            cached: !1,
            elem: void 0,
            filter: "kitNavbar"
        },
        set: function(i) {
            var a = this;
            return a.config.data = void 0,
            e.extend(!0, a.config, i),
            a
        },
        hasElem: function() {
            var i = this.config;
            return void 0 !== i.elem || 0 !== t.find("ul[kit-navbar]").length || !e(i.elem) || (layui.hint().error("Navbar error:请配置Navbar容器."),
            !1)
        },
        getElem: function() {
            var i = this.config;
            return void 0 !== i.elem && e(i.elem).length > 0 ? e(i.elem) : t.find("ul[kit-navbar]")
        },
        bind: function(i) {
            var n = this;
            return n.hasElem() ? (n.getElem().find("a[kit-target]").each(function() {
                var t = e(this)
                  , n = void 0;
//                t.hover(function() {
//                    n = a.tips(e(this).children("span").text(), this)
//                }, function() {
//                    n && a.close(n)
//                }),
                t.off("click").on("click", function() {
                    var e, a = t.data("options");
                    if (void 0 !== a)
                        try {
                            e = new Function("return " + a)()
                        } catch (i) {
                            layui.hint().error("Navbar 组件a[data-options]配置项存在语法错误：" + a)
                        }
                    else
                        e = {
                            icon: t.data("icon"),
                            id: t.data("id"),
                            title: t.data("title"),
                            url: t.data("url")
                        };
                    "function" == typeof i && i(e);
                })
            }),
            e(".kit-side-fold").off("click").on("click", function() {
                var i = t.find("div.kit-side");
               /* $(".top-icon").toggle();
                $(".skin-icon").toggle();*/
                i.hasClass("kit-sided") ? (i.removeClass("kit-sided"),
                i.find("li.layui-nav-item").removeClass("kit-side-folded"),
                i.find("dd").removeClass("kit-side-folded"),
                t.find("div.layui-body").removeClass("kit-body-folded"),
                t.find("div.layui-footer").removeClass("kit-footer-folded")) : (i.addClass("kit-sided"),
                i.find("li.layui-nav-item").addClass("kit-side-folded"),
                i.find("dd").addClass("kit-side-folded"),
                t.find("div.layui-body").addClass("kit-body-folded"),
                t.find("div.layui-footer").addClass("kit-footer-folded"))
            }),
            n) : n
        },
        render: function(i) {
            var t = this
              , d = t.config
              , o = d.remote
              , r = ["{{# layui.each(d,function(index, item){ }}", "{{# if(item.spread){ }}", '<li class="layui-nav-item layui-nav-itemed">', "{{# }else{ }}", '<li class="layui-nav-item">', "{{# } }}", "{{# var hasChildren = item.children!==undefined && item.children.length>0; }}", "{{# if(hasChildren){ }}", '<a href="javascript:;">', '{{# if (item.icon.indexOf("fa-") !== -1) { }}', '<i class="fa {{item.icon}}" aria-hidden="true"></i>', "{{# } else { }}", '<i class="layui-icon">{{item.icon}}</i>', "{{# } }}", "<span> {{item.title}}</span>", "</a>", "{{# var children = item.children; }}", '<dl class="layui-nav-child">', "{{# layui.each(children,function(childIndex, child){ }}", "<dd>", "<a href=\"javascript:;\" kit-target data-options=\"{url:'{{child.url}}',icon:'{{child.icon}}',title:'{{child.title}}',id:'{{child.id}}', pageId: '{{child.pageId}}'}\">", '{{# if (child.icon.indexOf("fa-") !== -1) { }}', '<i class="fa {{child.icon}}" aria-hidden="true"></i>', "{{# } else { }}", '<i class="layui-icon">{{child.icon}}</i>', "{{# } }}", "<span> {{child.title}}</span>", "</a>", "</dd>", "{{# }); }}", "</dl>", "{{# }else{ }}", "<a href=\"javascript:;\" kit-target data-options=\"{url:'{{item.url}}',icon:'{{item.icon}}',title:'{{item.title}}',id:'{{item.id}}', pageId: '{{item.pageId}}'}\">", '{{# if (item.icon.indexOf("fa-") !== -1) { }}', '<i class="fa {{item.icon}}" aria-hidden="true"></i>', "{{# } else { }}", '<i class="layui-icon">{{item.icon}}</i>', "{{# } }}", "<span> {{item.title}}</span>", "</a>", "{{# } }}", "</li>", "{{# }); }}"]
              , s = []
              , c = a.load(2);
            if (!t.hasElem())
                return t;
            var f = t.getElem();
            if (void 0 !== d.data && d.data.length > 0)
                s = d.data;
            else {
                o.jsonp;
                var u = {
                    url: o.url,
                    type: o.type,
                    cache: false, //关闭AJAX缓存
                    error: function(i, e, a) {
                        layui.hint().error("Navbar error:AJAX请求出错." + a)
                    },
                    success: function(i) {
                        s = i;
                        if(s.length == 0) {
                        	c && a.close(c);
                        }
                    }
                };
                e.extend(!0, u, o.jsonp ? {
                    dataType: "jsonp",
                    jsonp: "callback",
                    jsonpCallback: "jsonpCallback"
                } : {
                    dataType: "json"
                }),
                e.support.cors = !0,
                e.ajax(u)
            }
            /* click start */
            var g = function(d) {
            	var html = [];
            	layui.each(d,function(index, item){
            		if(item.spread){
            			html.push('<li class="layui-nav-item layui-nav-itemed menu_menu_distance_left">');
            		}
            		else {
            			html.push('<li class="layui-nav-item menu_menu_distance_left">');
            		}
            		var hasChildren = item.children!==undefined && item.children.length>0;
            		if(hasChildren){
            			html.push('<a href="javascript:;">');
            			if(item.icon) {
        					if (item.icon.indexOf("icon-") !== -1) {
        						html.push('<i class="iconfont ' + item.icon + '"></i>');
        					}
        					else if (item.icon.indexOf("fa-") !== -1) {
            					html.push('<i class="fa ' + item.icon + '" aria-hidden="true"></i>');
            				}
            				else {
            					html.push('<i class="layui-icon">' + item.icon + '</i>');
            				}
            			}
            			html.push('<span> ' + item.title + '</span>');
            			html.push('</a>');
            			var children = item.children;
            			html.push(gg(children));
            		}
            		else {
            			html.push('<a href="javascript:;" kit-target data-options="{url:\'' + item.url + '\',icon:\'' + item.icon + '\',title:\'' + item.title + '\',id:\'' + item.id + '\', pageId:\'' + item.pageId + '\'}">');
            			if(item.icon) {
	            			if (item.icon.indexOf("icon-") !== -1) {
	            				html.push('<i class="iconfont ' + item.icon + '" aria-hidden="true"></i>');
	            			}
	            			else if (item.icon.indexOf("fa-") !== -1) {
	            				html.push('<i class="fa ' + item.icon + '" aria-hidden="true"></i>');
	            			}
	            			else {
	            				html.push('<i class="layui-icon">' + item.icon + '</i>');
	            			}
            			}
            			html.push('<span> ' + item.title + '</span>');
            			html.push('</a>');
            		}
            		html.push('</li>');
            	});
            	return html.join('');
            };
            
            var gg = function(children, space) {
            	var html = [], space = space || ''/*'&nbsp;&nbsp;'*/;
            	html.push('<dl class="layui-nav-child">');
    			layui.each(children,function(childIndex, child){
    				var hasChildren = !!child.children;
    				if(hasChildren) {
    					if(child.spread){
    						html.push('<dt class="layui-nav-item layui-nav-itemed">');
    					}
    					else {
    						html.push('<dt class="layui-nav-item">');
    					}
    					html.push('<a href="javascript:;">');
    				}
    				else {
    					html.push('<dd>');
    					html.push('<a href="javascript:;" kit-target data-options="{url:\'' + child.url + '\',icon:\'' + child.icon + '\',title:\'' + child.title + '\',id:\'' + child.id + '\', pageId: \'' + child.pageId + '\'}">');
    				}
    				html.push(space/* + '&nbsp;&nbsp;'*/);
    				if(child.icon) {
    					if (child.icon.indexOf("icon-") !== -1) {
    						html.push('<i class="iconfont ' + child.icon + '" aria-hidden="true"></i>');
    					}
    					else if (child.icon.indexOf("fa-") !== -1) {
    						html.push('<i class="fa ' + child.icon + '" aria-hidden="true"></i>');
    					}
    					else {
    						html.push('<i class="layui-icon">' + child.icon + '</i>');
    					}
    				}
    				html.push('<span> ' + child.title + '</span>');
    				html.push('</a>');
    				if(hasChildren) {
    					var ss = gg(child.children, space + '&nbsp;&nbsp;');
    					html.push(ss);
    					html.push('</dt>');
    				}
    				else {
    					html.push('</dd>');
    				}
    			});
    			html.push('</dl>');
    			return html.join('');
            };
            /* click end */
            /* hover start */
            var g2 = function(t) {
                var html = [];
                layui.each(t,function(index, item){
                    var level = 0;
                    html.push('<li class="layui-nav-item nav-item left-nav-item" style="position: relative;">');
                    var hasChildren = item.children!==undefined && item.children.length>0;
                    if(hasChildren){
                        html.push('<a href="javascript:;">');
                    }
                    else {
                        html.push('<a href="javascript:;" kit-target data-options="{url:\'' + item.url + '\',icon:\'' + item.icon + '\',title:\'' + item.title + '\',id:\'' + item.id + '\', pageId:\'' + item.pageId + '\'}">');
                    }
                    if(item.icon) {
                        if (item.icon.indexOf("icon-") !== -1) {
                            html.push('<i class="iconfont ' + item.icon + '"></i>');
                        }
                        else if (item.icon.indexOf("fa-") !== -1) {
                            html.push('<i class="fa ' + item.icon + '" aria-hidden="true"></i>');
                        }
                        else {
                            html.push('<i class="layui-icon">' + item.icon + '</i>');
                        }
                    }
                    html.push('<span> ' + item.title + '</span>');
                    html.push('</a>');
                    if(hasChildren){
                        var children = item.children, width = n.wrapWidth;
                        html.push('<div class="layui-nav-child menu-child-wrap1" style="position: fixed;left:100px;background-color: #fff!important;min-width:100px;max-width:620px;border: 1px solid #dddddd;">');
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
                                html.push('<li class="nav-item">');
                                html.push('<a href="javascript:;" style="color: #333!important;" kit-target data-options="{url:\'' + item.url + '\',icon:\'' + item.icon + '\',title:\'' + item.title + '\',id:\'' + item.id + '\', pageId:\'' + item.pageId + '\'}">');
                                html.push(item.title);
                                html.push('</a>');
                                html.push('</li>');
                                return true;
                            }
                            var hasThirdChildren = item.children&&item.children.length>0;
                            if(hasThirdChildren){
                                if(isCreateSecond){
                                    html.push('</ul>');
                                    html.push('</div>');
                                    html.push('</li>');
                                }
                                html.push('<li class="nav-item bbbb" style="overflow:hidden;vertical-align:top;display: block;border-bottom: 1px solid #ddd;">');
                                html.push('<a href="javascript:;" style="color: #999!important;">');
                                html.push(item.title);
                                html.push('</a>');
                                var thirdChildren = item.children;
                                html.push('<div>');
                                html.push('<ul>');
                                layui.each(thirdChildren,function (index,item) {
                                    html.push('<li class="nav-item" style="float: left;">');
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
                                html.push('<li class="nav-item aaa" style="float: left;">');
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
                                html.push('<li class="nav-item cccc" style="overflow:hidden;vertical-align:top;display: block;border-bottom: 1px solid #ddd;">');
                                html.push('<div>');
                                html.push('<ul>');
                                html.push('<li class="nav-item" style="float: left;">');
                                html.push('<a class="left-item-linking" href="javascript:;" style="color: #333!important;" kit-target data-options="{url:\'' + item.url + '\',icon:\'' + item.icon + '\',title:\'' + item.title + '\',id:\'' + item.id + '\', pageId:\'' + item.pageId + '\'}">');
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
                if(d.type == 'hover') {
                    return g2(t);
                }
                return g(t);
            };
            var setTop = function(f){

                if(d.type != 'hover') {
                    return;
                }
                var width = $(".layui-side-scroll").width();
                var lis =  f.children("li");
                $(lis).hover(function(){
                    $(lis).removeClass("layui-nav-itemed")
                    $(lis).children("div").css("display","none");
                    $(this).addClass("layui-nav-itemed")
                    $(this).children("div").css("display","block");
                    var liHeight = $(this).height();
                    var top = $(this).offset().top;
                    var height = top+liHeight-50;
                    var div1 = $(this).children("div");

                    var divHeight = $(div1).css("minHeight",liHeight);
                    var divHeight = $(div1).height();
                    var top1 = (divHeight>height?top:height-divHeight+50)
                    $(div1).css("top",top1)
                    $(div1).css("left",width);
                },function(){
                   
                })
                $(lis).children("div").hover(function(){

                },function(){
                     $(this).css("display","none");
                     $(this).parent("li").removeClass("layui-nav-itemed")
                })
                layui.each(lis,function(i,item){
                    /*var liHeight = $(item).height();
                    var top = $(item).offset().top;
                    var height = top-50+liHeight;
                    var div1 = $(item).children("div");
                    var divHeight = $(div1).height();
                    var top1 = (divHeight>height?top:height-divHeight)
                    $(div1).css("top",top1+"px!important")*/
                })
            };

            var h = setInterval(function() {
                s.length > 0 && (clearInterval(h), function(e){
                	e = j(e),
                	f.html(e),
                        setTop(f),
                    l.init(),
                    t.bind(function(e) {
                        "function" == typeof i && i(e)
                    }),
                    c && a.close(c)
                }(s)
                /*n(r.join("")).render(s, function(e) {
                    f.html(e),
                    l.init(),
                    t.bind(function(e) {
                        "function" == typeof i && i(e)
                    }),
                    c && a.close(c)
                })*/)
            }, 50);
            return t
        }
    })
});