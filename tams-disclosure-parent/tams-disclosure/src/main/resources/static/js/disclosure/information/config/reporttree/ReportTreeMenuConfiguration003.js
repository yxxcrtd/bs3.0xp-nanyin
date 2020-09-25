$(function() {  
    var  getAddSonNode = function(){
        var parms = {
                PId:PId,
                treeId:$('#treeId').val(),
                treeName:$('#treeName').val()
                
            };
        Joyin.ajax('getAddSonNode',
                parms,function(result){
                    //点击保存后关闭窗口
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                });
    }   
    $(function() {
        $('#ReportTreeMenuConfiguration003_btn_save').click(function() {
            getAddSonNode();
        });
        
    });
});
        
        
        
                    
                
                
           