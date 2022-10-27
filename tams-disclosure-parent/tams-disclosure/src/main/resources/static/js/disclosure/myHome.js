var tabs = [];
var first = 0;
var searchReportUrl = basePath + 'disclosure/searchRevealList';
var getSystemNowUrl = basePath + "system/reportHome/getSystemNow";
(function () {
    'use strict';
    // 去除转授权
    $('#setDelegation', parent.window.document).remove();
    /* 菜单栏logo变小*/
    $(".logo-img",parent.window.document).css("height","33px");
    $("#infoArea").on('click', 'a',function() {
        var a=$(this);
        //用来放需要传递的参数
        var data={};
        data.status="A";
        var newTab = {
            pageId: a.attr("tab-pageid"),
            title: a.attr("tab-title"),
            url: a.data('type'),
            id: a.attr("tab-id"),
            icon: ''
        };
        var temp="?";
        for(var item in data){
            var jValue=data[item];//key所对应的value
            //可批量导出删除模板的Obj
            if(temp.length!=1){
                temp+="&";
            }
            temp=temp+item+"="+jValue+" ";
            //把需要传递的参数加在地址后面
        }
        newTab.url+=temp;
        top.tab.tabAdd(newTab);
        $('div[lay-item-id='+newTab.id+'] iframe', top.window.document.body).attr('src', basePath + newTab.url);
    });
    function destroy() {
        $('#tabNavs').scrollingTabs('destroy');
    }

    function getHeight() {
        var height = $(document).height()-$("#tabs .scrtabs-tab-container").outerHeight()-$(".debt-container").height();
        if (window.ActiveXObject || "ActiveXObject" in window){
            height -=8;
        }
        return height;
    }

    function addTab(tab) {
        var index = -1;
        var tabContent = $('#tabs > .tab-content');
        var tabpanel;
        var newTab = $.extend({}, {
            content: "<iframe width='100%' frameborder='0' height='100%' id='iframe"+tab.tabName+"' src='"+tab.tabUrl+"'></iframe>",
            active: true,
            disabled: false
        }, tab);
        $.each(tabs, function(i, t) {
            t.active = false;
            if(t.paneId === tab.paneId) {
                index = i;
            }
        });
        if(tabContent.length === 0) {
            tabContent = $('<div class="tab-content"></div>');
        }
        tabContent.each(function() {
            $(this).toggleClass('active', false);
            if($(this).attr('id') === newTab.paneId) {
                $(this).toggleClass('active', true);
            }
        });
        if(index === -1) {
            tabs.push(newTab);
            if(tabs.length === 1) {
                activate();
            }
            tabpanel = $('<div role="tabpanel" class="tab-pane active"></div>');
            tabpanel.attr('id', newTab.paneId).html(newTab.content);
            tabpanel.appendTo(tabContent);
            tabContent.append(tabpanel);
        }
        else {
            tabs[index].active = true;
        }
        if($('#tabs > .tab-content').length === 0) {
            $('#tabs').append(tabContent);
        }
        $('#tabNavs').scrollingTabs('refresh', {
            forceActiveTab: true
        });
        if(!tab.firstAdd){
            $("#tabs .scrtabs-tab-container .scrtabs-tabs-fixed-container .nav li a").css("background","#ebebeb");
            $("#tabs .scrtabs-tab-container .scrtabs-tabs-fixed-container .nav li a[href='#"+tab['paneId']+"']").css("background","#fff");
            $('#tabs .tab-home-link').css("background","#ebebeb").find('a').css("background","#ebebeb");
        }

        setTimeout(function() {
            $("#tabs > .tab-content > .tab-pane.active").height(getHeight());
            if(ltIE9) {
                $('#tabs .scrtabs-tabs-movable-container').width('10000px');
            }
        }, 200);
    }

    function removeTab(tabId) {
        var index = -1;
        var active = false;
        $.each(tabs, function(i, t) {
            if(t.paneId === tabId) {
                index = i;
                active = $('#'+t.paneId).hasClass('active');//t.active;
            }
        });
        if(index >= 0) {
            tabs.splice(index, 1);
            if(tabs.length == 0){
                destroy();
                $("#index").show();
                $("#tabs").hide();
            }

            $('#tabs > .tab-content > .tab-pane').each(function() {
                if($(this).attr('id') === tabId) {
                    $(this).remove();
                }
            });

            if(active) {
                if(index > tabs.length - 1) { // 激活下一个标签
                    index = tabs.length - 1;
                }
                if(tabs[index]) {
                    tabs[index].active = true;
                }
            }
            refreshTabs();
        }
    }

    function activate() {
        $('#tabNavs').scrollingTabs({
            tabs: tabs, // required,
            propPaneId: 'paneId', // optional - pass in default value for demo purposes
            propTitle: 'title', // optional - pass in default value for demo purposes
            propActive: 'active', // optional - pass in default value for demo purposes
            propDisabled: 'disabled', // optional - pass in default value for demo purposes
            propContent: 'content', // optional - pass in default value for demo purposes
            scrollToTabEdge: false, // optional - pass in default value for demo purposes
            disableScrollArrowsOnFullyScrolled: true,
            enableSwiping: true,
            //widthMultiplier: 0.7,
            allowClose: true,
            ignoreTabPanes: true,
            tabClickHandler: function (e) {
            }
        });
    }

    function refreshTabs() {
        $('#tabNavs').scrollingTabs('refresh');
        $.each(tabs, function(i, t) {
            if(t.active) {
                $('#' + t.paneId).addClass('active');
                $('#tabs a[data-toggle="tab"][href="#' + t.paneId + '"]').css("background","#fff").parent().addClass('active');
                $("#tabs .tab-home-link").css("background","#ebebeb");
            }
            else {
                $('#' + t.paneId).removeClass('active');
                $('#tabs a[data-toggle="tab"][href="' + '#' + t.paneId + '"]').css("background","#ebebeb").parent().removeClass('active');
            }
        });
    }

    $('#tabs').on('click', 'i.tab-close', function() {
        var tabId = $(this).parent().attr('href');
        if(tabId) {
            removeTab(tabId.substring(1));
        }
        //如果在进行关闭tab操作后仅剩下主页则对首页的数据进行重新加载
        if(tabs.length==1){
            homepage.init();
        }
    });
    $('#tabs').on('click', '.scrtabs-tab-container a[data-toggle="tab"],.tab-home-link a[data-toggle="tab"]', function() {
        var tabId = $(this).attr('href');
        if(tabId && $(tabId).length > 0) {
            $.each(tabs, function(i, t) {
                if('#' + t.paneId === tabId) {
                    t.active = true;
                }
                else {
                    t.active = false;
                }
            });
            //如果在进行关闭tab操作后仅剩下主页则对首页的数据进行重新加载
            if(tabId === '#tabHome'){
                homepage.init();
            }
            refreshTabs();
            // IE8特殊处理
            setTimeout(function() {
                if($('#tabs > .tab-content > .active').length != 1) {
                    $('#tabs > .tab-content > .active').filter(function() {
                        return '#' + $(this).attr('id') != tabId;
                    }).removeClass('active');
                }
            }, 200);
        }
    });
    $(".debt-home-menu li a,#joy-home-index").click(function (e) {
        e.preventDefault();//阻止元素的默认操作
        $("#index").hide();
        $("#tabs").show();
        var a = $(this);
        var path = a.attr("href");
        if(!path || path.indexOf('javascript') != -1) {
            return false;
        }
        addTab({
            paneId: 'tab' + a.attr("tab-name"),
            title: a.attr("tab-title"),
            tabName: a.attr("tab-name"),
            tabUrl: a.attr("href")
        });
    });

    $("#li-index").on("click",function(){
        $("#tabs").hide();
        $("#li-index").show();
    });

    window.homepage = function() {
        return {
            /**
             * 初始化的函数
             */
            init : function(){
                // Joyin.ajaxSync(
                //     getSystemNowUrl,null,function(result){
                //         if(result){
                //             $("#searchDate").val(result.data);
                //         }
                //     }
                // );
                homepage.searchPageInfo();
                $('#pageContent').show();
            },
            searchPageInfo : function() {
                homepage.searchReportInfo();
            },
            /**
             * 更改未实现部分的样式
             */
            changeStyle : function(){
                $(".info-todo");
            },
            /**
             * 打开新画面
             */
            openPage : function(pageUrl) {
                window.location.href = pageUrl;
            },
            /**
             * 系统注销确认
             */
            logout : function() {
                // 系统确认消息框方式未定 TODO
                homepage.logoutSubmit();
            },
            /**
             * 系统注销
             */
            logoutSubmit : function() {
                $("#logoutForm").submit();
            },
            displayTableText : function(rowID){
                var row = document.getElementById(rowID);
                if (row != null) {
                    row.style.display = "";
                }
            },
            /**
             * 改变Table中的某个行或者列的值
             */
            changeTableText : function(tableId,rows,cells,text){
                // //根据Table的id拿到实例
                // var tableObj = document.getElementById(tableId);
                // //拿到当前行的html
                // var test=tableObj.rows[rows].cells[cells].innerHTML;
                // //进行html的拼接防止影响其格式获取其头和尾
                // var len=test.indexOf(">");
                // var startHtml=test.slice(0,len+1);
                // var endHtml=test.slice(test.length,test.length);
                // //拼接字符创
                // var resultHtml=startHtml.concat(text).concat(endHtml);
                // //将其html赋值
                // tableObj.rows[rows].cells[cells].innerHTML=resultHtml;
                if(!isNaN(text) && parseInt(text) >= 0) {
                    /*if(text=="0"){
                        text="0";
                    }*/
                    if($('#' + tableId).find('tr:eq(' + rows + ')').find('td:eq('+cells+')').find('a,span').text() != '--')
                        $('#' + tableId).find('tr:eq(' + rows + ')').find('td:eq('+cells+')').find('a,span').text(text);
                }
            },
            /**
             * 查询报送信息登记
             */
            searchReportInfo : function() {
                //日期控件的取值
                //var data={searchDate :$("#searchDate").startDate()};
                //访问后台查询数据的数量
                $.ajax({
                    url: searchReportUrl,
                    dataType: 'json',
                    data: {},
                    cache:false,
                    type: 'GET',
                    async: true
                }).done(function(json) {
                    if(json) {
                        //2、遍历Json串获取其属性
                        for(var item in json){
                            var jValue=json[item];//key所对应的value
                            //table的ID为了拿到table的对象实体
                            var tableId="subInfoTable";

                            var rownum = document.getElementById(item).rowIndex;
                            homepage.displayTableText(item);
                            //字符的确认替换位置
                            //len=68;
                            //对每个行数据进行赋值
                            homepage.changeTableText(tableId,rownum,1,jValue["expectRevealNum"]);
                            homepage.changeTableText(tableId,rownum,2,jValue["revealNum"]);
                            homepage.changeTableText(tableId,rownum,3,jValue["genFailNum"]);
                            homepage.changeTableText(tableId,rownum,4,jValue["toBeConfirmedNum"]);
                            homepage.changeTableText(tableId,rownum,5,jValue["toBeReviewedNum"]);
                            homepage.changeTableText(tableId,rownum,6,jValue["toBeTgReviewedNum"]);
                            homepage.changeTableText(tableId,rownum,7,jValue["toBeRevealedNum"]);
                        }
                    }
                });
            },
            /**
             * 根据页面信息获取一个tab
             */
            getTab : function(obj){
                //$("#tabs").show();

                $("#index").hide();
                $("#tabs").show();
                var str=obj.url.split("/");
                var pageId=str[str.length-2];
                var newTab = {};
                newTab = {
                    paneId: 'tab' + pageId,
                    title: obj.title,
                    tabName: pageId,
                    tabUrl: obj.url
                };
                var temp="?";
                for(var item in obj.data){
                    var jValue=obj.data[item];//key所对应的value
                    //可批量导出删除模板的Obj
                    if(temp.length!=1){
                        temp+="&";
                    }
                    temp=temp+item+"="+jValue+" ";
                    //把需要传递的参数加在地址后面
                }
                newTab.tabUrl+=temp;
                addTab(newTab);
            },

        };
    }();

    $(function() {
        homepage.init(); //todo url确定后，需要放开

        $("#searchDate").change(function() {
            homepage.searchReportInfo();
        });
        // 系统注销
        $("#logoutLink").bind("click", homepage.logout);

        //绑定首页的快捷事件
        $(".debt-home-fast").on('click', 'a',function() {
            var a=$(this);
            var param={};
            var str=a.attr('tab-url').split("/");
            var pageId=str[str.length-2];
            var newTab = {
                pageId: /*'tab' + pageId*/a.attr("data-pageid"),
                title: a.attr("tab-title"),
                url: a.attr('tab-url'),
                id:a.attr("lay-id"),
                icon:""
            };
            top.tab.tabAdd(newTab);
        });
    });
}());