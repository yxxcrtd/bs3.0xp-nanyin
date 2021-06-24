(function(){
    
    // 确认按钮
    $('#EtrIbBondinfo001_queryDialog_btn_submit').click(function() {
        var obj = $.serializeToObject('#EtrIbBondinfo001_queryDialog_searchForm_zqzy');
        parent.EtrIbBondinfo001.executeQuery(obj);
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    });
    
    // 重置按钮
    $('#EtrIbBondinfo001_queryDialog_btn_reset').click(function() {
        $("#portfolioId_zqzy").empty(); // 重置组合名称
        $('#EtrIbBondinfo001_queryDialog_searchForm_zqzy')[0].reset();
    });
    
}());