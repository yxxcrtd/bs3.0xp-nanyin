(function () {

    var levno = function () {
      var status = $('#FinProductFeaAna002_feasibilityStatus').val();
      if(status === analyse_status){
          $('#FinProductFeaAna002_feasibilityType').prop("disabled",false);
      }else{
          $('#FinProductFeaAna002_feasibilityType').prop("disabled",true);
      }
    };

    var FinProductFeaAnaInit = function(){
        levno();
        $('#FinProductFeaAna002_feasibilityStatus').change(function () {
            levno();
        });
    };

    function saveAction(){
        saveOrSubmitAction('save');
    }

    function submitAction(){
        saveOrSubmitAction('submit');
    }

    function saveOrSubmitAction(operateType){
        var object = $.serializeToObject('#FinProductFeaAna002_Form', false);
        object.operateType = operateType;
        if(FileCommon) {
            object.uploadFileList = FileCommon.getUploadFileInfo();
        }
        Joyin.ajaxJson(basePath + "invest/product/rddesign/feasibilityanalysis/FinProductFeaAna002/check",object,function (result) {
            Joyin.confirm(result.data,function () {
                if(operateType === 'save'){
                    WorkFlow.doSaveList(basePath + "invest/product/rddesign/feasibilityanalysis/FinProductFeaAna002/save",object,"FinProductFeePay002_btn_save");
                }else {
                    WorkFlow.doSubmitList(basePath + "invest/product/rddesign/feasibilityanalysis/FinProductFeaAna002/save",object,"FinProductFeaAna002_btn_submit");
                }
            })
        });
    }

    $(function () {
        FinProductFeaAnaInit();
        //保存
        $('#FinProductFeePay002_btn_save').click(saveAction);
        //提交
        $('#FinProductFeaAna002_btn_submit').click(submitAction);
    });
}());