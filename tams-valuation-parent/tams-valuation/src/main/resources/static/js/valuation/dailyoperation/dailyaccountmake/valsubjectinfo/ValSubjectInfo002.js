(function () {
    $(function () {
        //$("#returnTxtHidden").text()
        //var returnTxtHidden = $("#returnTxtHidden").val();
        var returnTxtHidden = $("#returnTxtHidden").text();
        if(returnTxtHidden == ""){
            $("#returnTxt").html("无日志信息");
        }else{
            $("#returnTxt").html($("#returnTxtHidden").text());
        }
    })
}());