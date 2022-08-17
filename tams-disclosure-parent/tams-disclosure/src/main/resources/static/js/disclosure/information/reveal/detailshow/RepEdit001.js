$(function () {

	var tree=$("#typeDatatree");
	var docW= $(document).width(); 
	var docH= $(document).height(); 
	var tabHeadUlH=100;
	var defaultDateStyle = 'yyyy-MM-dd';
	var ids = new Array();

	//设置模板的调用参数
	var modelId;
	var modelUri;
	var repId;
	var repName;
	var proName;
	
	var modelListId;
	var modelListName;

	var currFilename;
	//var webRoot = "http://192.168.60.186:8081/tamsyy";
	//var webRoot = "http://localhost:8081/tamsyy";
	//alert(docH);
	document.getElementById("OfficeDiv").style.height=docH-60+"px";
	
    //预览报表信息，用插件编辑器打开word文档，本项目是用html打开，没有用到这个方法
    var showDetail = function(params){
        Joyin.ajax('previewReport', {uri:modelId}, function(result) {
            if ( result ) {
                //layer.alert(result);
                WebOfficeObj.ServerUrl = webRoot; //服务器地址
                WebOfficeObj.ShowMenu = 0;
                WebOfficeObj.ShowToolBar = 0;
                SetGraySkin();			//设置控件皮肤
                // alert(result.reportName);
                currFilename = result.reportName;
                WebOfficeObj.FileName = result.reportName;
                WebOfficeObj.FileType = ".docx";
                WebOfficeObj.SaveServlet = "/disclosure/information/reveal/detailshow/RepEdit001/doWebOffice";
                WebOfficeObj.WebOpen(true); // 文件在服务器上的相对路径 FileName
            } else {
                $("#showtable").html('<h2>无报表内容</h2>');
            }
        });
    }

    //保存文件
    var saveReport = function(params){
        WebOfficeObj.ServerUrl = webRoot;
        //WebOfficeObj.FileName = "产品说明书.docx";
        WebOfficeObj.FileType = ".docx";
        WebOfficeObj.SaveServlet = "/disclosure/information/reveal/detailshow/RepEdit001/doWebOffice";
        //alert(WebOfficeObj.FileName);
        if (WebOfficeObj.WebSave()){    //交互OfficeServer的OPTION="SAVEFILE"
     //       WebOfficeObj.WebClose();
            WebOfficeObj.Alert("保存成功!");
            top.getData="true"
        }else{
            WebOfficeObj.Alert("保存失败");
        }
    }

        //关闭窗口
    var closeReport = function(params){
            WebOfficeObj.WebClose();
            parent.layer.closeAll();
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
        $('#btnSaveReport').click(function() {
            saveReport("");
        });
        $('#btnCloseReport').click(function() {
            closeReport("");
        });
    });

});
