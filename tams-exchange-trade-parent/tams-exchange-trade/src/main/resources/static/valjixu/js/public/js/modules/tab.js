;layui.define(["jquery", "element", "nprogress"], function(i) {
    var t = layui.jquery
      , e = layui.element
      , a = t(document)
      , l = t(window)
      , n = function() {
        this.config = {
            elem: void 0,
            mainUrl: "main.html"
        },
        this.v = "1.0.2"
    };
    (n.fn = n.prototype).set = function(i) {
        var e = this;
        return t.extend(!0, e.config, i),
        e
    },
    n.fn.render = function() {
        var i = this
          , t = i.config;
        return void 0 === t.elem ? (layui.hint().error("Tab error:请配置选择卡容器."),
        i) : (c._config = t,
        c.createTabDom(),
        i)
    },
    n.fn.tabAdd = function(i) {
        c.tabAdd(i)
    },
    n.fn.tabDelete = function(e,p) {
        c.tabDelete(e,p)
    };
    var c = {
        _config: {},
        _filter: "kitTab",
        _title: void 0,
        _content: void 0,
        _parentElem: void 0,
        tabDomExists: function() {
            var i = this;
            return a.find("div.kit-tab").length > 0 && (i._title = t(".kit-tab ul.layui-tab-title"),
            i._content = t(".kit-tab div.layui-tab-content"),
            !0)
        },
        createTabDom: function() {
            var i = this
              , e = i._config;
            if (i._parentElem = e.elem,
            !i.tabDomExists()) {
                var a = ['<div class="layui-tab layui-tab-card kit-tab" lay-filter="' + i._filter + '">', '<ul class="layui-tab-title" id="tabHeadUl">', '<li class="layui-this" lay-id="-1"><i class="layui-icon">&#xe68e;</i> 首页</li>', "</ul>", '<div class="kit-tab-tool">','<i class="iconfont icon-triangle-left tab-left-wrap kit-tab-tool-color aaa" data-target="left" style="display: none;background-color: #d9ecff;"></i>', '<i class="iconfont icon-triangle-right tab-right-wrap kit-tab-tool-color aaa" data-target="right" style="display: none;background-color: #d9ecff;"></i>','<i class="iconfont icon-refresh kit-tab-tool-color" data-target="refresh" title="刷新当前选项卡" style="padding-right:4px;display: none;"></i>', '<i class="iconfont icon-close-current kit-tab-tool-color" data-target="closeCurrent" title="关闭当前选项卡" style="padding-right:4px;display: none;"></i>', '<i class="iconfont icon-close-other kit-tab-tool-color" data-target="closeOther" title="关闭其他选项卡" style="padding-right:4px;display: none;"></i>', '<i class="iconfont icon-close-all kit-tab-tool-color" data-target="closeAll" title="关闭所有选项卡" style="padding-right:4px;display: none;"></i>', '<i class="iconfont icon-saved kit-tab-tool-color" data-target="saveWindow" id="saveWindow" name="saveWindow" title="保存窗口视角" style="padding-right:4px;display: none;"></i>', '<i class="iconfont icon-open kit-tab-tool-color" data-target="openWindow" id="openWindow" name="openWindow" title="打开窗口视角" style="padding-right:4px;display: none;"></i>', "</div>", '<div class="layui-tab-content">', '<div class="layui-tab-item layui-show" lay-item-id="-1"><iframe src="' + e.mainUrl + '" frameborder=”no” border=”0″ marginwidth=”0″ marginheight=”0″scrolling=”no” allowtransparency=”yes"></iframe></div>', "</div>", "</div>"];
                t(e.elem).html(a.join("")),
                i._title = t(".kit-tab ul.layui-tab-title"),
                i._content = t(".kit-tab div.layui-tab-content");
                var l = t(".kit-tab-tool");
                l.find("i.iconfont").each(function() {
                    var e = t(this)
                      , a = e.data("target");
                    e.off("click").on("click", function() {
                        var e = i._title.children("li[class=layui-this]").attr("lay-id");
                        var p = i._title.children("li[class=layui-this]").attr("data-pageid");
                        switch (a) {
                        case "refresh":
                            var n = i._content.children("div[lay-item-id=" + e + "]").children("iframe");
                            n.attr("src", n.attr("src"));
                            break;
                        case "closeCurrent":
                                -1 != e && i.tabDelete(e,p);
                                break;
                                case "closeOther":
                                i._title.children("li[lay-id]").each(function() {
                                    var a = t(this).attr("lay-id");
                                var p = t(this).attr("data-pageid");
                                a != e && -1 != a && i.tabDelete(a,p)
                            });
                            break;
                            case "left":
                                i.tabScroll("left");
                                break;
                            case "right":
                                i.tabScroll("right");
                                break;
                        case "closeAll":
                            i._title.children("li[lay-id]").each(function() {
                                var e = t(this).attr("lay-id");
                                var p = t(this).attr("data-pageid");
                                -1 != e && i.tabDelete(e,p)
                            })
                        }
                        l.click()
                    })
                }),
                i.winResize()
            }
        },
        winResize: function() {
            var i = this;
            l.on("resize", function() {
                var e = t(i._parentElem).height();
                t(".kit-tab .layui-tab-content iframe").height(e-10)
            }).resize()
        },
        tabExists: function(i) {
            return this._title.find("li[lay-id=" + i + "]").length > 0
        },
        tabDelete: function(i,p) {
            //log
            if(p){                
                t.ajax({type:"POST", url:"closePage", data:{'pageId':p}});
            }
            e.tabDelete(this._filter, i);
            /*var t1 = this;
            var title = t1._title;
            var lis = title.children("li");
            var titleWidth = title.width();
            var liWidth =0;
            layui.each(lis,function(i,item){
                var width = $(item).width();
                liWidth+=width;

            });
            if(liWidth<titleWidth){
                $(".aaa").css("display","none");
            }*/
            var t1 = this;
            if(t1._title.find('.layui-tab-bar').length < 0) {
                $(".aaa").hide();
            }
        },
        tabChange: function(i) {
            e.tabChange(this._filter, i),this.tabScrollAfterOpt("add");
            this.setCheckedStyle(i);
        },
        getTab: function(i) {
            return this._title.find("li[lay-id=" + i + "]")
        },
        tabAdd: function(i) {
            var t = this
              , a = t._config
              , l = (i = i || {
                id: (new Date).getTime(),
                title: "新标签页",
                icon: "fa-file",
                url: "404.html"
            }).title
              , n = i.icon
              , c = i.url
              , s = i.id
              , p = i.pageId;
            c = c.indexOf('/') === 0 ? c.substring(1) : c;
            if (t.tabExists(s))
                t.tabChange(s);
            else {
                NProgress.start();
                var r = ['<li class="layui-this" data-pageid="' + p + '" lay-id="' + s + '" >'];
                -1 !== n.indexOf("icon-") ? r.push('<i class="iconfont ' + n + '"></i>') : r.push('<i class="layui-icon">' + n + "</i>"),
                r.push("&nbsp;" + l),
                r.push('<i class="layui-icon layui-unselect layui-tab-close">&#x1006;</i>'),
                r.push("</li>");
                var o = '<div class="layui-tab-item layui-show" lay-item-id="' + s + '"><iframe src="' + c + '" frameborder=”no” border=”0″ marginwidth=”0″ marginheight=”0″scrolling=”no” allowtransparency=”yes"></iframe></div>';
                t._title.append(r.join("")),
                t._content.append(o),
                t.getTab(s).find("i.layui-tab-close").off("click").on("click", function() {
                    a.closeBefore ? a.closeBefore(i) && t.tabDelete(s,p) : t.tabDelete(s,p)
                }),
                t.tabChange(s),
                t.winResize(),
                t._content.find("div[lay-item-id=" + s + "]").find("iframe").on("load", function() {
                    NProgress.done()
                }),
                a.onSwitch && e.on("tab(" + t._filter + ")", function(i) {
                    a.onSwitch({
                        index: i.index,
                        elem: i.elem,
                        layId: t._title.children("li").eq(i.index).attr("lay-id")
                    })
                }),t.tabScrollAfterOpt("add")
            }
            if(t._title.find('.layui-tab-bar').length > 0) {
                $(".aaa").show();
            }
            t.setCheckedStyle(s);
        },
        setCheckedStyle: function(tab) {
            var layId = this.getTab(tab).attr('lay-id');
            $('.menu-top-wrapper:visible > li, .menu-tree-container:visible > li').removeClass('checked').each(function(){
            	var $li = $(this), flag = false;
            	$li.find('a').each(function() {
                	var opt = $(this).data('options');
                	if(opt) {
                		opt = new Function("return " + opt)()
                		if(opt.id === layId) {
                			$li.addClass('checked');
                			flag = true;
                			return;
                		}
                	}
                });
            	if(flag) return;
            });
        },
        tabScroll:function(director){
            var title = t(".kit-tab ul.layui-tab-title");
            var lis = title.find(">li");
            var titleWidth = title.outerWidth();
            var liWidth =0;
            var needHidden = 0;
            if(director=="right"){
                layui.each(lis,function(i,item){
                    var width = $(item).outerWidth();
                    if($(item).is(":hidden")){
                        return false;
                    }
                    if(liWidth<titleWidth || (liWidth<titleWidth &&(liWidth+width)>=titleWidth)){
                        liWidth +=width;
                    }else{
                        needHidden = i;
                        return true;
                    }
                })
                if(needHidden>0){
                    liWidth = 0;
                    layui.each(lis,function(i,item){
                        if(i==0){
                            return false;
                        }
                        if(i<needHidden){
                            $(item).hide();
                        }

                    })
                }
            }else{
                var lis = t(title.find(">li:hidden"));
                for(var i=lis.length-1;i>=0;i--){
                    var item = lis[i];
                    var width = $(item).outerWidth();
                    if(liWidth<titleWidth || liWidth<titleWidth && (liWidth+width>=titleWidth)){
                        $(item).show();
                        liWidth+=width;
                    }
                }
            }

        },
        tabScrollAfterOpt:function(i){
            var title = t(".kit-tab ul.layui-tab-title");
            var lis = title.find(">li");
            var titleWidth=title.outerWidth()-title.find("li:eq(0)").outerWidth(),containCur = false
                ,firstShowIndex=0,liWidth=0;
            layui.each(lis,function(i,item){
                var width = $(item).outerWidth();
                if($(item).hasClass("layui-this")){
                    containCur= $(item).hasClass("layui-this");
                }
                if(liWidth<titleWidth || (liWidth<titleWidth &&(liWidth+width)>=titleWidth)){
                    liWidth+=width;
                }else{
                    if(containCur){
                        return true;
                    }else{
                        liWidth = 0
                        firstShowIndex = i;
                    }
                }

            })
            layui.each(lis,function(i,item){
                if(i>0 &&i<firstShowIndex){
                    $(item).hide();
                }else{
                    $(item).show();

                }

            })
        }
    };
    i("tab", new n)
});