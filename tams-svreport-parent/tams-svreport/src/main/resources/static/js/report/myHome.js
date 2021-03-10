var tabs = [];
var first = 0;
var searchInfoAreaUrl = basePath + 'system/reportHome/searchInfoArea';
var searchReportUrl = basePath + 'system/reportHome/searchReportInfo';
var searcheBatchexportTemplatesUrl = basePath + 'system/reportHome/searcheBatchexportTemplates';
var getSystemNowUrl = basePath + "system/reportHome/getSystemNow";
var getHoldInfoSourceInfoUrl = basePath + "system/reportHome/getHoldInfoSourceInfo";
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
        //Joyin.forDialogInit("信息表维护",url,"100%","100%",param,null);
    });
    function destroy() {
        $('#tabNavs').scrollingTabs('destroy');
    }

    function getHeight() {
        //return $('body').height()-42-$("#tabs .scrtabs-tab-container").height()-$(".debt-container").height();
        var height = $(document).height()-$("#tabs .scrtabs-tab-container").outerHeight()-$(".debt-container").height();
        if (window.ActiveXObject || "ActiveXObject" in window){
            height -=8;
        }
        return height;
    }

   /* addTab({
        paneId: 'tabHome',
        title: "首页",
        tabName: "tabHome",
        content: $('#index').html(),
        noNeedClose:true,
        firstAdd:true
    });*/
    /*setTimeout(function() {
        $('#index').remove();
        $("#tabs > .tab-content > .tab-pane.active").height(getHeight());
    }, 200);*/

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
//
//        $(window).on("resize",function(){
//            $("#tabs > .tab-content > .tab-pane.active").height(getHeight());
//        });

    window.homepage = function() {
        return {
            /**
             * 初始化的函数
             */
            init : function(){
                Joyin.ajaxSync(
                        getSystemNowUrl,null,function(result){
                        if(result){
                            $("#searchDate").val(result.data);
                        }
                    }
                );
                /* homepage.changeStyle();*/

                homepage.searchPageInfo();
                
                /*
                if(isChangePassword){
                    homepage.showChangePasswordTip();
                }*/
                
//                实际业务中，维护投资者持有信息通过2种业务模式进行：
//                1.只维护业务明细信息和基期信息，系统自动计算投资者持有信息。
//                --针对这种模式，系统应该隐藏现有功能“投资者持有信息”，同时首页“投资者持有信息”也不予展示。
//                2.用户直接维护投资者持有信息，不维护业务明细信息和基期信息。
//                --针对该模式，系统应该隐藏现有功能“业务明细信息维护”和“基期信息维护”。同时主页业务明细信息也不予展示。
//                3.以上2种模式，系统应通过开关控制，供用户任意选择其中的一种模式。
                Joyin.ajaxSync(getHoldInfoSourceInfoUrl, null,
                        function(result) {
                            if (result) {
                                var holdInfoSourceInfoData = result.data;
                                var HOLD_INFO_SOURCE = holdInfoSourceInfoData.HOLD_INFO_SOURCE;
                                var HOLD_INFO_SOURCE_01 = holdInfoSourceInfoData.HOLD_INFO_SOURCE_01;
                                if (HOLD_INFO_SOURCE == HOLD_INFO_SOURCE_01) {
                                    // 持有信息数据来源_业务明细
                                    $("#chbHoldInfoDiv").hide();
                                } else {
                                    // 持有信息数据来源_持有信息
                                    $("#chbEqudetDiv").hide();
                                }
                            }
                        });
                $('#pageContent').show();
            },
            searchPageInfo : function() {
                homepage.searchInfoArea();
                homepage.searchReportInfo();
                homepage.searcheBatchexportTemplates();
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
                    $('#' + tableId).find('tr:eq(' + rows + ')').find('td:eq('+cells+')').find('a,span').text(text);
                }
            },
            /**
             * 改变信息表中的数据并保留其格式
             */
            changeInfoArea : function(id,text){
                //根据ID获取其obj
                var Obj=document.getElementById(id);
                //替换其html
//                    var temphtml=Obj.innerH	TML;
//                    var len=temphtml.indexOf(">");
//                    var headhtml=temphtml.slice(0,len);
//                    len=temphtml.indexOf("</");
//                    var endhtml=temphtml.slice(len+1);
//                    var reasutHtml=headhtml+text+endhtml;
//                    Obj.innerHTML=reasutHtml;
                if(Obj &&text!=null ){
                    Obj.innerHTML=text;
                }
            },
            /**
             * 查询信息表数据
             */
            searchInfoArea : function() {
                $.ajax({
                    url: searchInfoAreaUrl,
                    dataType: 'json',
                    type: 'GET',
                    cache:false,
                    async: true
                }).done(function(json) {
                    if(json) {
                        for(var item in json){
                            var jValue=json[item];//key所对应的value
                            //信息表维护的Obj
                            //在后台根据控件的ID对数据进行分组，item为控件的ID值，第二个参数为要放入的值
                            homepage.changeInfoArea(item,jValue.countNum);
                        }
                    }
                });
            },

            /**
             * 查询报送信息登记
             */
            searchReportInfo : function() {
                //日期控件的取值
                var data={searchDate :$("#searchDate").startDate()};
                //访问后台查询数据的数量
                $.ajax({
                    url: searchReportUrl,
                    dataType: 'json',
                    data:data,
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
                            //字符的确认替换位置
                            //len=68;
                            //对每个行数据进行赋值
                            homepage.changeTableText(tableId,1,item,jValue["delaynum"]);
                            homepage.changeTableText(tableId,2,item,jValue["delayingnum"]);
                            homepage.changeTableText(tableId,3,item,jValue["sendnum"]);
                            homepage.changeTableText(tableId,4,item,jValue["totalnum"]);
                            //对于将报送数据添加记录中债报送截止日的最小日以及报送截止日的最大日
                            $(document.getElementById(tableId).rows[2].cells[item].firstChild).attr("data-mindate",jValue["mindate"]);
                            $(document.getElementById(tableId).rows[2].cells[item].firstChild).attr("data-maxdate",jValue["maxdate"]);
                        }
                    }
                });
            },
            /**
             * 查询可批量导出删除模板
             */
            searcheBatchexportTemplates : function() {
            	
                //访问后台查询数据的数量
                $.ajax({
                    url: searcheBatchexportTemplatesUrl,
                    dataType: 'json',
                    type: 'GET',
                    cache:false,
                    async: true
                }).done(function(json) {
                	
                    if(json) {
                        //2、遍历Json串获取其属性
                        for(var item in json){
                            var jValue=json[item];//key所对应的value
                            //可批量导出删除模板的Obj
                            //在后台根据控件的ID对数据进行分组，item为控件的ID值，第二个参数为要放入的值
                            homepage.changeInfoArea(item,jValue.countNum);
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

            /*/!**
             * 点击我的工作明细时，打开对应画面
             *
             * @param pageName 画面名
             * @param pageUrl 画面地址
             *!/
            openWorkFlowDetailPage : function(workflowId, taskId,myWork) {
                var pageUrl = workFlowDetailPageUrl;
                Joyin.forDialogInit(workFlowDetailPageName, pageUrl, '100%', '95%', {
                    workflowId : workflowId,
                    taskId : taskId,
                    myWork : myWork,
                    r : Math.random()
                }, function() {
                    if ($('div#todolist').hasClass('active')) {
                        homepage.Home_todolist_btnSearch.load();
                    }
                });
            },
*/
            /*/!**
             * 批量审批按钮点击事件(事前check)
             *!/
            onMutiApprovalAction : function() {
                var selectedRowIndex = homepage.Home_todolist_btnSearch.getAllCheckedRows();
                if($.isArray(selectedRowIndex) && selectedRowIndex.length) {
                    var paramBak = {
                        selectedDatas : selectedRowIndex
                    };
                    var param = JSON.stringify(paramBak);
                    Joyin.ajaxJson("checkMutiApproval", param, function(result) {
                        if (result && result.statuCode == '200') {
                            if (result.data) {
                                var confirmMsg = Joyin.getMessage(COMMON_C_0010, $("#Home_todolist_BTN_mutiApproval")
                                    .text().trim());
                                Joyin.forConfirmInit(confirmMsg, null, function() {
                                    if (result.data.lastUserTask == true) {
                                        paramBak.workflowId = result.data.workflowId;
                                        param = JSON.stringify(paramBak);
                                        homepage.mutiApproval(param);
                                    } else {
                                        WorkFlow.selectUser(result.data.elementId, result.data.pageId,
                                            function(users, roles) {
                                                paramBak.nextUser = users.join();
                                                param = JSON.stringify(paramBak);
                                                homepage.mutiApproval(param);
                                            });
                                    }
                                });
                            }

                        }
                    });
                } else {
                    Joyin.forAlertError(MSG_COMMON_E_0009);
                }
            },*/

           /* /!**
             * 批量审批
             *!/
            mutiApproval : function(param) {
                Joyin.ajaxJson("mutiApproval", param, function(result) {
                    if (result && result.statuCode == '200') {
                        Joyin.forAlertInfo(MSG_COMMON_I_0001, function() {
                            homepage.Home_todolist_btnSearch.load();
                        });
                    }
                });
            },
*/
            /*//待办事项获取查询参数
            getPageParams : function(tabId){
                var operators = $(tabId+'_SELECT_operator').data('result');
                var userIds = '';
                if(operators && operators.length>0){
                    $(operators).each(function(i,v){
                        if(v.id!=null && v.id!=''){
                            userIds = userIds+v.col;
                            if(i < operators.length-1){
                                userIds = userIds + ',';
                            };
                        }
                    });
                    userIds.trim();
                };
                var pageParams = {
                    //开始日期
                    startCrtDate : $(tabId+'_TEXT_createTime').data("start"),
                    //结束日期
                    endCrtDate : $(tabId+'_TEXT_createTime').data("end"),
                    //业务类型
                    businessType : ($(tabId+'_SELECT_businessType').val() || []).join(','),
                    //业务编号
                    tradeId : $(tabId+'_SELECT_tradeId').val(),
                    //经办人
                    operator : userIds,
                    //业务说明
                    title : $(tabId+'_SELECT_title').val()
                };
                return pageParams;
            },
            //首次登陆以及定时三个月提醒用户更换密码
            showChangePasswordTip:function(){
                layer.confirm('请修改登录密码！',{title:'提示',time:3000,btn:['修改密码']},function(){
                    Joyin.forDialogInit(PAGE_TITLE_PWD_CHANGE, PAGE_URL_PWD_CHANGE, "50%", "60%");
                });
                isChangePassword = false;
            }*/
        };
    }();

    $(function() {
        homepage.init(); //todo url确定后，需要放开
        // 定时刷新首页
//        var homePagetimer = setInterval(function() {
//            homepage.searchPageInfo();
//        }, 30000);

        $(".menu-body").on('click', 'a',function() {
            var a=$(this);
            var param={};
            param.url=a.attr("data-type");
            var num=a.attr("data-num");
            var searchDate=$("#searchDate").val();
            var type=a.attr("data-subtype");
            var searchDataStart;
            var searchDataEnd;
            if(type=="1"){
                searchDataStart="1900-01-01";
                var temp=Date.ofDate(searchDate.replace(/-/g,"/"));
                temp.setDate(temp.getDate()-1);
                searchDataEnd=temp.format();
            }
            else if(type=="2"){
                //当前记录的最小的行内截止日
                searchDataStart=a.attr("data-mindate");
                if(searchDataStart==null){
                    searchDataStart=searchDate;
                }
                //当前记录的最大的报送截止日
                searchDataEnd=a.attr("data-maxdate");
                if(searchDataEnd==null){
                    searchDataEnd=searchDate;
                }
            }
            else if(type=="3"){
                var temp=Date.ofDate(searchDate.replace(/-/g,"/"));
//                temp.setDate(temp.getDate());
                temp.setDate(temp.getDate());
                searchDataStart=temp.format();
                searchDataEnd="2099-12-31";
            }
            else{
                searchDataStart="1900-01-01";
                searchDataEnd="2099-12-31";
            }


            var str=a.attr("data-type").split("/");
            var pageId=str[str.length-2];
            var data={};
            data.type=type;
            data.submitStatus="01";
            data.searchDataStart=searchDataStart;
            data.searchDataEnd=searchDataEnd;
            var tableobj=document.getElementById("subInfoTable");
            var newTab = {
                pageId: a.attr("tab-pageid"),
                title: tableobj.rows[0].cells[num].innerText.replace(/[\r\n]/g,"")+"登记",
                url: a.attr("data-type"),
                id:a.attr("lay-id"),
                icon:""
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
            $('div[lay-item-id='+newTab.id+'] iframe', top.window.document.body).attr('src', basePath+newTab.url);
            //Joyin.forDialogInit("报送信息登记",url,"100%","100%",param,null);
        });
//        $("#infoArea").on('click', 'a',function() {
//            var a=$(this);
//            if(a[0].innerText==='0'){
//                return;
//             }
//            //用来放需要传递的参数
//            var data={};
//            data.status="A";
//            var newTab = {
//                pageId: a.attr("tab-pageid"),
//                title: a.attr("tab-title"),
//                url: a.attr('data-type'),
//                id:a.attr("tab-id")
//            };
//            var temp="?";
//            for(var item in data){
//                var jValue=data[item];//key所对应的value
//                //可批量导出删除模板的Obj
//                if(temp.length!=1){
//                    temp+="&";
//                }
//                temp=temp+item+"="+jValue+" ";
//                //把需要传递的参数加在地址后面
//            }
//            newTab.url+=temp;
//            top.tab.tabAdd(newTab);
//            //Joyin.forDialogInit("信息表维护",url,"100%","100%",param,null);
//        });
        $("#canBatchDeleteList").on('click', 'a',function() {
        	var a=$(this);
        	var searchDataStart;
            var searchDataEnd;
            searchDataStart="1900-01-01";
            searchDataEnd="2099-12-31";
            //用来放需要传递的参数
            var data={};
            data.submitStatus="01";
            data.status="03";
            data.searchDataStart=searchDataStart;
            data.searchDataEnd=searchDataEnd;    
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
            $('div[lay-item-id='+newTab.id+'] iframe', top.window.document.body).attr('src', basePath+newTab.url);
        });
        
        $("#searchDate").change(function() {
            homepage.searchReportInfo();
        });
        // 系统注销
        $("#logoutLink").bind("click", homepage.logout);

        /*//待办事项、我的工作加载表格
        function setTable(tabId, url,flag) {
            return $(tabId).joyintable({
                ajax: {
                    url: url,
                    type: 'POST',
                    data: function() {
                        return homepage.getPageParams(tabId);
                    }
                },
                //rowno: true, // 显示行号
                searchOnInit: false, //初始化的时候就请求后台数据
                scrollX:true,
                fixedColumns:   {
                    leftColumns:0,
                    rightColumns: 1,
                    scrollCollapse: true,
                    scrollY:"300px",
                    scrollX: true
                },
                columns: [
                    { title: "日期", data: 'crtDate',width:110},
                    { title: "业务编号", data: 'tradeId',width:200},
                    { title: "业务类型", data: 'businessType',width:120},
                    { title: "操作类型", data: 'actionType',width:100},
                    { title: "业务说明", data: 'title',
                        render: function (data, type, row, meta) {
                            return '<a href="javascript:;" onclick="homepage.openWorkFlowDetailPage(\'' + row.processInstanceId
                                + '\', \'' + row.curTaskId +'\','+ flag+')">'
                                + row.title + '</a>';
                        }},
                    { title: "经办人", data: 'operator' ,width:140},
                    { title: "流程状态", data: 'processState' ,width:120},
                    { data: 'processInstanceId', visible: false },
                    { data: 'workflowId', visible: false },
                    { data: 'curTaskId', visible: false },
                    { data: 'businessTypeValue', visible: false },
                    { data: 'fromUser', visible: false }]
            });
        }*/

       /* //待办事项、我的工作加载多选框
        $('#subTabs a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
            $($(this).attr('href')).find('.form-select').inputSelectMultiple();
        });*/

        /*//表格初始化
        homepage.Home_todolist_btnSearch = setTable('#Home_todolist', 'getToDoListData',false);
        homepage.Home_mywork_btnSearch = setTable('#Home_mywork', 'getMyWorkData',true);*/
        /*//查询按钮点击事件
        $('button[id$="_btnSearch"]').on('click', function() {
            homepage[$(this).attr('id')].load();
        });
        // 批量审批按钮点击事件
        $("#Home_todolist_BTN_mutiApproval").bind('click', homepage.onMutiApprovalAction);*/
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
        /*
        $('#subTabs a[data-toggle="tab"]:not(:hidden)').on('shown.bs.tab', function (e) {
            var href = $(this).attr('href');
            if(href == '#todolist') {
                homepage['Home_todolist_btnSearch'].load();
            }
            if(href == '#myWork') {
                homepage['Home_mywork_btnSearch'].load();
            }
        });*/

        /*//版权信息
        $('#copyright').on("click",function(){
            /!**
             * 跳转至新增页面
             *!/
            Joyin.forDialogInit('', copyrightUrl, '688px', '458px');
        });*/

        //使主页自适应高度
        $(window.parent.document).find("iframe").load(function() {
            $("#carouselHome",parent.window.document).css("height","100%");
            var frameHeight = $(window.parent.document).height()-90;
            $("[pageid='SvreportHome002']",parent.window.document).css("height",frameHeight);
        });
    });
}());