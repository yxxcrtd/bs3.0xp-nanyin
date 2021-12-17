var tab;
layui.define(['element', 'nprogress', 'form', 'table', 'loader', 'tab', 'navbar', 'onelevel'], function (exports) {
    var $ = layui.jquery,
        element = layui.element,
        layer = layui.layer,
        _win = $(window),
        _doc = $(document),
        _body = $('.kit-body'),
        form = layui.form,
        table = layui.table,
        loader = layui.loader,
        navbar = layui.navbar,
        _componentPath = 'components/';
    tab = layui.tab
    var app = {
        config: {
            position: 'all',
            type: 'iframe'
        },
        set: function (options) {
            var that = this;
            $.extend(true, that.config, options);
            return that;
        },
        init: function () {
            var that = this,
                _config = that.config;
            if (_config.type === 'page') {
                $('a[kit-loader]').on('click', function () {
                    var url = $(this).data('url'),
                        name = $(this).data('name'),
                        id = $(this).data('id');
                    loader.load({
                        url: url,
                        name: name,
                        id: id === undefined ? new Date().getTime() : id,
                        onSuccess: success
                    });

                    function success(data) {
                        switch (data.name) {
                            case 'table':
                                loader.getScript(_componentPath + 'table/table.js', function () {
                                    var tableIns = table.render(moduleTable.config);
                                    moduleTable.extend({
                                        currTable: tableIns,
                                        table: table,
                                        layer: layer,
                                        form: form,
                                        jquery: $
                                    });
                                });
                                break;
                            case 'form':
                                form.render();
                                break;
                            default:
                                break;
                        }
                    };
                });
            }
            if (_config.type === 'iframe') {
                tab.set({
                    elem: '#container',
                    mainUrl: 'main/init.html',
                    onSwitch: function (data) { //选项卡切换时触发
                        //console.log(data.layId); //lay-id值
                        //console.log(data.index); //得到当前Tab的所在下标
                        //console.log(data.elem); //得到当前的Tab大容器
                        var layId = data.layId;
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
                    closeBefore: function (data) { //关闭选项卡之前触发
                        // console.log(data);
                        // console.log(data.icon); //显示的图标
                        // console.log(data.id); //lay-id menuId
                        // console.log(data.title); //显示的标题
                        // console.log(data.url); //跳转的地址
//                        $.ajax({type:"POST", url:"closePage", data:{'pageId':data.pageId}});
                        return true; //返回true则关闭
                    }
                }).render();
                //设置样式
                var head = document.head || document.getElementsByTagName('head')[0];
        		var style = document.createElement('style');
        		style.type ="text/css";
        		var innerHtml = '';

        		//添加样式，菜单距离
        		if(MENU_MENU_DISTANCE){
            		var menu_menu_distance_top =  '.menu_menu_distance_top{';
            		var menu_menu_distance_left =  '.menu_menu_distance_left{';
        			var distanceSetter = MENU_MENU_DISTANCE + 'px;'
        			menu_menu_distance_top = menu_menu_distance_top +'margin-left : ' + distanceSetter;
        			menu_menu_distance_left = menu_menu_distance_left + 'margin-bottom : '+ distanceSetter;
        			menu_menu_distance_top +='}';
            		menu_menu_distance_left += '}';
            		innerHtml = innerHtml+menu_menu_distance_top + '\n' + menu_menu_distance_left;
        		}
        		//添加样式，菜单字体
        		if(MENU_FONT_SIZE){
        			var menu_menu_distance_top =  '.menu_menu_distance_top a span{';
            		var menu_menu_distance_left =  '.menu-tree-container .menu_menu_distance_left a span{';
        			var fontSizeSetter ='font-size : ' + MENU_FONT_SIZE + 'px !important;';
        			menu_menu_distance_top = menu_menu_distance_top + fontSizeSetter;
        			menu_menu_distance_left = menu_menu_distance_left + fontSizeSetter;
        			menu_menu_distance_top +='}';
            		menu_menu_distance_left += '}';
            		innerHtml = innerHtml+menu_menu_distance_top + '\n' + menu_menu_distance_left;
        		}
        	    style.innerHTML = innerHtml;
        	    head.appendChild(style);
                //银行名称
                if(SYSNAME_PRE_ICON == "01"){
                	$(".bank-logo").css('display','block');
                	$('.application-log').css('left','80px');
                	$('.menu-top-wrapper').css('left','270px');
                	$('.menu-top-wrapper').css('width','745px');
                }
				else{
                	$(".bank-logo").css('display','none');
                	$('.application-log').css('left','0px');
                	$('.menu-top-wrapper').css('left','190px');
                	$('.menu-top-wrapper').css('width','825px');
				}
                if (_config.position === 'left' || _config.position === 'all') {
                    //左侧菜单
                    navbar.set({
                        remote: {
                            url: _config.navurl
                        },
                        type: menuType,
                    }).render(function (data) {
                        tab.tabAdd(data);
                    });
                }
                if (_config.position === 'top' || _config.position === 'all') {
                    //处理顶部一级菜单
                    var onelevel = layui.onelevel;
                    if (onelevel.hasElem()) {
                        onelevel.set({
                            type: menuType,
                            remote: {
                                url: _config.navurl //远程地址
                            },
                            onClicked: function (data) {
                                tab.tabAdd(data);
                                //触发top-nav-toggle-btn的click事件
                                $('body').find('.top-nav-toggle-btn').click();
                            }
                        }).render();
                    }
                }
            }
            return that;
        }
    };

    //输出test接口
    exports('app', app);
});