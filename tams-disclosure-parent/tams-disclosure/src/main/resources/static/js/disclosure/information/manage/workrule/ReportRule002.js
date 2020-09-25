$(function () {
	
    /**
     * 获取页面参数
     */
    var getParams = function() {
    	var options=$("#repName option:selected");
        return {
        	repId:options.val(),
        	repName :options.text(),
        	repRuleName:$('#repRuleName').val(),
        	rcfType : $('#rcfType').val(),
        	rcfValue : $('#rcfValue').val(),
        	basisDateType : $('#basisDateType').val(),
        	makeType : $('#makeType').val(),
        	makeValue:$('#makeValue').val(),
        	makeDateType : $('#makeDateType').val(),
        	revealType : $('#revealType').val(),
        	revealValue : $('#revealValue').val(),
        	revealDateType : $('#revealDateType').val(),
        	revealTime : $('#revealTime').val(),
        	dataType : $('#dataType').val(),
        	dataRange : $('#dataRange').val(),
        	dataBegin : $('#dataBegin').val(),
        	dataEnd : $('#dataEnd').val(),
        	dataValue : $('#dataValue').val(),
        	isConfirm : $('#isConfirm').val(),
        	fileOutType:$('#fileOutType').val(),
        	isReveal : $('#isReveal').val(),
        	revealChannelW : $('#revealChannelW').val(),
        	revealChannelO : $('#revealChannelO').val(),
        	revealChannelC : $('#revealChannelC').val(),
        	ftpId : $('#ftpId').val(),
			
		
        }
    };
   //新建
   var sava = function(){
	   	// 向后台传参
//	   var param =getParams();
//	   
//	   
	   var options=$("#repName option:selected");
       	
	   var object = $.serializeToObject('#submitForm');
	   object.repId = options.val();
	   object.repName = options.text();
        //新建
	   Joyin.ajax(
				'insert',
				object,
				function() {
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				}
		); 
       
        
        
   }
   function repNameChange(){
	   var repName=$('#repName').select2('data')[0].text;
	   $('#ruleName').val(repName+"_规则");
	   setDetail();
   }
   function basisDateTypeChange(){
	   setDetail();
   }
   function rcfTypeChange(){
	   setDetail();
   }
   function revealDateTypeChange(){
	   setDetail();
   }
   function revealTypeChange(){
	   setDetail();
   }
   function revealTimeChange(){
	   setDetail();
   }
   function dataTypeChange(){
	   setDetail();
   }
   function dataRangeChange(){
	   setDetail();
   }
   function dataBeginChange(){
	   setDetail();
   }
   function dataEndChange(){
	   setDetail();
   }
   function dataValueChange(){
	   setDetail();
   }
   function setDetail(){
	   var repName=($('#repName').val()=="")?"":$('#repName').select2('data')[0].text;
	   var basisDateType=($('#basisDateType').val()=="")?"":$('#basisDateType').select2('data')[0].text;
	   var rcfType=($('#rcfType').val()=="")?"":$('#rcfType').select2('data')[0].text;
	   var revealDateType=($('#revealDateType').val()=="")?"":$('#revealDateType').select2('data')[0].text;
	   var around=($('#revealType').val()=="")?"":$('#revealType').select2('data')[0].text;
	   var revealTime=$('#revealTime').val();
	   var dataType=($('#dataType').val()=="")?"":$('#dataType').select2('data')[0].text;
	   var dataRange=($('#dataRange').val()=="")?"":$('#dataRange').select2('data')[0].text;
	   var dataBegin=($('#dataBegin').val()=="")?"":$('#dataBegin').select2('data')[0].text;
	   var dataEnd=($('#dataEnd').val()=="")?"":$('#dataEnd').select2('data')[0].text;
	   var dataValue=($('#dataValue').val()=="")?"":$('#dataValue').select2('data')[0].text;
	   if(rcfType=="业务发生时"){
		   var around;
		   if(around==""){
			   around
		   }
		   else{
			   around=($('#revealType').select2('data')[0].text=="基准日前")?"前":"后";
		   }
		   $('#detail').val(basisDateType+around+revealDateType+revealTime+"披露"+repName);
	   }
	   if(rcfType!="业务发生时"&&basisDateType=="平台日"){
		   if(dataType=="区间报表"){
			   $('#detail').val(rcfType+"披露"+dataRange+dataBegin+dataEnd+dataValue+"的"+repName);
		   }
		   if(dataType=="时点报表"){
			   $('#detail').val(rcfType+"披露"+dataRange+dataValue+"的"+repName);
		   }
		   if(dataRange=="近N个自然日"||dataRange=="近N个工作日"){
			   var dateType=(dataRange=="近N个自然日")?"个自然日":"个工作日";
			   $('#detail').val(rcfType+"披露"+"近"+dataValue+dateType+"的"+repName);
		   }
	   }
	   if(dataRange=="基准日"){
		   $('#detail').val(rcfType+"披露"+basisDateType+"的"+repName);
	   }
	  
   }
	/**
     * 画面加载完成
     */
    $(function() {
        
      //保存绑定点击事件
        $('#ReportRule002_btn_save').click(function(){
      	sava();
        })
        $('#repName').change(repNameChange);
        $('#basisDateType').change(basisDateTypeChange);
        $('#rcfType').change(rcfTypeChange);
        $('#revealDateType').change(revealDateTypeChange);
        $('#revealType').change(revealTypeChange);
        $('#revealTime').change(revealTimeChange);
        $('#dataType').change(dataTypeChange);
        $('#dataRange').change(dataRangeChange);
        $('#dataBegin').change(dataBeginChange);
        $('#dataEnd').change(dataEndChange);
        $('#dataValue').change(dataValueChange);
    });
    
	 
});

