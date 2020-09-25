$(function() {
	
	   var serverUrl = $('#serverUrl').val();
	   var serverPath = $('#serverPath').val();
	   var userName = $('#userName').val();
	   var passWord = $('#passWord').val();
	window.location.href=serverUrl+"?pass="+passWord;
  })