/**
 * 机构负面信息
 */
$(function(){
    /**
     * 获取查询参数
     */
    var getSearchParams = function(){
        var objTypes = null;
        if($('#objTypes').val() != null){
            objTypes = $('#objTypes').val().join(',');
        }
        var dateRangeStr = $("#dateRange").val();
        var startDate = "";
        var endDate = "";
        if(dateRangeStr != ""){
            var dates = dateRangeStr.split(' - ');
            startDate = dates[0];
            endDate = dates[1];
        }
        return {
            objTypes: objTypes,             //对象类型
            orgId: $('#orgId').val(),                       //机构id
            startDate: startDate,                //查询日期
            endDate: endDate,
        }
    };
    
    /**
     * 定义表格
     */
    var table = $("#dataTable").joyintable({
        ajax:{
            url:'findPage',
            type:'POST',
            data:function(){
                return getSearchParams();
            }
        },
        rowno: true,
        searchOnInit: REM_TYPE && (REM_TYPE == 'R7S3'),
        checkbox:false,
        cols:[
              {field:'objTypeName', title: '对象类型',width:300, sortField:'objType'},
              {field:'orgName', width: 200,title: '机构名称'},
              {field:'title', width: 200,title: '负面信息', render:function(value,visible,row,options){
                  return "<div class='newsTitle'>"+value+"</div>";
              }},
              {field:'pubDate' , title: '日期', type:'date', width:200,},
              {field:'source' , title: '来源', width:150,},
              {field:'operation', width: 200,title: 'SysCommon.operation', style:"text-align: left;",render:function(value,visible,row,options){
            	  if(purviewElements.indexOf('_manual')!=-1){
            		  return "<div class='detail manual'>机构信息查询</div>";
            	  }
            	  else{
            		  return "";
            	  }
              }}
//              {field:'operation', title: 'SysCommon.operation', width:200, style:"text-align: left;",
//                    buttons: [{text: '机构信息查询',clas: 'detail',title: '机构信息查询'}]
//              }
        ]}
    );
    
    /**
     * 绑定负面信息链接
     */
    table.bind('click', '.newsTitle', function(data, index) {
        Joyin.dialog("负面信息详情",
                basePath + "invest/risk/customerrelationtree/agencynegativeinf/AgencyNegativeInf002/init",
                "1200", "90%",data);
    });

    //打开机构详细页面
    table.bind('click', '.manual', function(data, index) {
        var pageDate = {
            icon: "",
            id: "RISK_MANAGEMENT_001_011_002",
            pageId: "AgencyDetailsInf001",
            title: "机构详细信息",
            url: "/invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf001/init",
        };
        //判断标签页是否已存在
        var tabTitle = $(".kit-tab ul.layui-tab-title", window.parent.document);
        if( tabTitle.find("li[lay-id=" + pageDate.id + "]").length > 0){
            parent.tab.tabAdd(pageDate);        //打开或切换到目标标签页
            //获取目标标签页的iframe
            var iframe = $("div[lay-item-id=RISK_MANAGEMENT_001_011_002]", window.parent.document).children("iframe")[0];
            iframe.contentWindow.changeTab(data.orgId, data.orgName);
        }else{
            parent.tab.tabAdd(pageDate);        //打开或切换到目标标签页
            //获取目标标签页的iframe
            var iframe = $("div[lay-item-id=RISK_MANAGEMENT_001_011_002]", window.parent.document).children("iframe")[0];
            iframe.onload = function(){
                iframe.contentWindow.changeTab(data.orgId, data.orgName);
            };
        }

//        var object = {agencyId: data.orgId};//请求参数
//        Joyin.dialog('机构详细信息',
//                basePath + "invest/risk/customerrelationtree/agencydetailsinf/AgencyDetailsInf001/init",
//                "1200", "90%", object);
    });
    /**
     * 画面加载完成
     */
    $(function() {

        $("#dateRange").val(queryDate+' - '+queryDate).trigger("change");
        $('#AgencyNegativeInf001_btn_export').hide();
        $('#AgencyNegativeInf001_btn_search').click(function() {
            table.load();
        });
    });
}())