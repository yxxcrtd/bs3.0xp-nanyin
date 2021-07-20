$(function () {
    
    $('#EtrIbRepoRk005005_btn_searchq').click(function() {
        qq();
    });
    
    function qq() {
    	  var modelSelectParam =null;
          var object = $.serializeToObject('#basicInfoForm');
          top.modelSelectParam = object;
  		  Joyin.closeTopWindow();
  		  return modelSelectParam;
    };
}());