(function () {
    $(function () {
        var returnTxtHidden = $("#returnTxtHidden").val();
        if(returnTxtHidden == ""){
            $("#returnTxt").html("无日志信息");
        }else{
            $("#returnTxt").html($("#returnTxtHidden").text());
        }
    })
}());