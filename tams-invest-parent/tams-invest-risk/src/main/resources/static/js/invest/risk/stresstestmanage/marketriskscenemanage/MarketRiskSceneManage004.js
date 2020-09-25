/**
 * 市场性情景管理
 */
$(function(){
    var facData = JSON.parse(entity);
    
    /**
     * 定义表格
     */
    var tableA = $("#dataTable").joyintable({
        checkbox: false,
        rowno:true,
        page: false,
        loadonce: true,
        searchOnInit: true,
        data:facData.arr,
        height:158,
        cols:[
              {field:'assetTypeAndGrade' , title: '资产类型',sortable:false},
              {field:'startDate', title: '匹配开始时间',sortable:false},
              {field:'endDate' , title: '匹配结束时间', sortable:false},
              {field:'ftrId', title: '风险因子编号',sortable:false},
              {field:'ftrName', title: '风险因子名称',sortable:false}
              ],
        done:function(dataList, allData){
//            $($('input[lay-filter="layTableAllChoose"]').next(".layui-form-checkbox")).css('display','none');
//            tableA.layTable.on('checkbox('+tableA.filter+')', function(obj){
//                top.returnData = obj.data;
//                var index = parent.layer.getFrameIndex(window.name);
//                parent.layer.close(index);
//            });
            
            dataList.forEach(function(elem, index){
                var divStr = "<div><input type='radio' name='netOutf' value='"+index+"' title='选择直接以风险因子"+elem.ftrName
                    +"的变化值进行计算'/></div>";
                $("#form").append(divStr);
            })
            var divStr = "<div><input type='radio' name='netOutf' value='"+dataList.length+"' title='选择直接以风险因子"+dataList[0].ftrName
                    +"与风险因子"+dataList[dataList.length-1].ftrName+"的变化值进行计算'/></div>";
            $("#form").append(divStr);
            layui.form.render('radio');
            
            layui.form.on('radio', function(data){
                var valueIndex = data.value;
                if(parseInt(valueIndex) == dataList.length){
                    top.returnData = facData.mixFtr;
                }else{
                    top.returnData = dataList[valueIndex];
                }
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            });
        }
    });
    
}())