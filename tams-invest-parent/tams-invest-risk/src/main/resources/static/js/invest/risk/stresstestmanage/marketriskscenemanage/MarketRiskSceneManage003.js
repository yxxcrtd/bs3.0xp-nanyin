/**
 * 市场性情景管理
 */
$(function(){
    var factorTable = [];
    var layerIndex = "";
    var resolveDataComplete = false;
    
    function getTableQueryData(){
        layerIndex = layer.load();      //开启loading条
        return {
            sceneId:entity.sceneId,
            entity:JSON.stringify(entity),
            origin:origin
        };
    }
    
    /**
     * 定义表格
     */
    var tableA = $("#dataTable").joyintable({
        ajax:{
            url:'queryfactorList',
            type:'POST',
            data:getTableQueryData()
        },
//        height:180,
        checkbox: false,
        page: false,
        loadonce: true,
        searchOnInit: true,
        cols:[
              {field:'ftrTypeName', title: '因子分类',width:'8%', sortable:false},
              {field:'ftrType2Name', title: '因子二级分类',width:'9%', sortable:false},
              {field:'ftrName', title: '风险因子名称',width:'18%',sortable:false},
              {field:'ftrId', title: '风险因子编号',width:'15%',sortable:false},
              //{field:'gradeCode' , title: '债券评级',width:'9%',sortable:false},
              {field:'startDate', title: '起始时间',width:'9%',sortable:false},
              {field:'endDate' , title: '结束时间',width:'9%', sortable:false},
              {field:'days' , title: '日数',width:'5%',sortable:false},
              {field:'changeValue' , title: '变化',width:'11%',sortable:false,type:'number',digit:8},
              {field:'extraAssetName' , title: '匹配资产类型',width:'16%',sortable:false}     //extraAssetName为resolveData()中为了展示新加的临时数据
              
              ],
        done:function(table, allData){
            //解决表格高度不能自适应的问题
            var layTable = tableA.table.table;
            layTable.fullHeightGap = parseInt(layTable.fullHeightGap) + 45;
            layTable.fullSize();
            
            //不管怎样，直接将返回的数据置为空，防止返回错误数据
            top.returnData = null;
            //数据加载完成后关闭loading条
            if(layerIndex != null){
                layer.close(layerIndex);        //关闭loading条
                layerIndex = null;
            }
            if(resolveDataComplete){        //数据已解析完成
                table.forEach(function(elem, index){
                    if(elem.mixFtr){
                        tableA.$element.next().find(".layui-table-body table.layui-table tr[data-index='"+index+"']").css({
                            'background-color':'#dcdc2c','color':'white'});
                    }
                });
                return;
            }else if(origin === "manage"){      //如果是情景管理页面进入则不需要提示用户因子变更信息
                factorTable = table;
                resolveData();
                return;
            }else{          //提交前预览情景详细时。需要提示用户因子变更
                factorTable = table;
                //遍历数据，根据groupId生成分组信息
                if(factorTable!=null && factorTable.length>0){
                    var groupObj = {};
                    for(var i=0 ; i<factorTable.length; i++){
                        var item = factorTable[i];
                        if(item.groupId != null && item.groupId != ""){
                            //每个组的结构为{mixFtr:混合的因子,arr:[不是混合的变更因子]}
                            if(groupObj[item.groupId] == undefined){
                                groupObj[item.groupId] = {mixFtr:'',arr:[]};
                            }
                            
                            if(item.mixFtr){
                                groupObj[item.groupId].mixFtr = item;
                            }else{
                                groupObj[item.groupId].arr.push(item);
                            }
                        }
                    }
                    
                    var groupKeys = Object.keys(groupObj)
                    if(groupKeys.length > 0){
                        layer.ready(function(){
                            layer.config({closeBtn:false});
                        });
                        var index = 0;
                        //打开提示页面
                        opendialog(groupObj, groupKeys, index, groupKeys.length);
                    }else{
                        resolveData();
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                }
            }
        }
    });
    
    //解析出最终展示的数据
    function resolveData(){
        
        var showData = {};  //相当于map集合
        var showDataList = [];
        factorTable.forEach(function(elem, index){
            if(elem.groupId != null && elem.groupId != ""){
                elem.mixFtr = true;
            }
            
            //将因子相同且最终变化值也相同的因子合并到一条数据中，资产类型为他们的汇总
            var ftrKey = elem.ftrId+"-"+elem.changeValue;
            if(showData[ftrKey] == undefined){
                elem.extraAssetName = elem.assetTypeAndGrade;        //添加额外的扩展属性
                showData[ftrKey] = elem;
                showDataList.push(elem);
            }else{
                var ftrItem = showData[ftrKey];
                if(ftrItem.extraAssetName != null && ftrItem.extraAssetName != "" && ftrItem.extraAssetName != undefined && 
                        elem.assetTypeAndGrade != null && elem.assetTypeAndGrade != "" && elem.assetTypeAndGrade != undefined){
                    ftrItem.extraAssetName += "，"+elem.assetTypeAndGrade;
                }else if(elem.assetTypeAndGrade != null && elem.assetTypeAndGrade != "" && elem.assetTypeAndGrade != undefined){
                    ftrItem.extraAssetName = elem.assetTypeAndGrade;
                }
            }
        })
        top.returnData = null;      //在数据处理完成后需要将返回值设置为null
        resolveDataComplete = true;
        tableA.reload({
            page: false,
            loadonce: true,
            data:showDataList
        });
    }
    
    function opendialog(obj, keys, index, len){
        
        var paramData = {
            array:JSON.stringify(obj[keys[index]])
        };
        //将变更曲线的值放入弹出框返回值中
//        top.returnData = obj[keys[index]].mixFtr;
//        var btns = {"选择混合方式计算":function(layerIndex){
//            top.returnData = obj[keys[index]].mixFtr;
//            layer.close(layerIndex);
//        }};
        
        //打开新页面
        Joyin.dialog('风险因子变更选择',
                basePath + "invest/risk/stresstestmanage/marketriskscenemanage/MarketRiskSceneManage004/init",
                "1000", "350px", paramData, function(rtData){
            //获取用户选择的ftrId.
            var selectedFrtId;
            if(rtData==undefined || rtData==null){
                selectedFrtId = obj[keys[index]].arr[0].ftrId; //默认选择第0位
                console.log("默认添加第0位数据");
            }else{
                selectedFrtId = rtData.ftrId;
            }
            //去除没有被选上的
            factorTable = factorTable.filter(function (elem) {
                return (elem.groupId != keys[index] || (elem.groupId == keys[index] && elem.ftrId == selectedFrtId));
            })
            
            index += 1;
            if(index < len){
                //递归弹出提示框让用户选择
                opendialog(obj, keys, index, len);
            }else{
                resolveData();
            }
        }, {})
    }
    
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
//        var paramObj = getSearchParams();
        Joyin.exportExcel('export', {str:JSON.stringify(factorTable)});
    };
    
    /**
     * 画面加载完成
     */
    $(function() {
        $('#MarketRiskSceneManage003_btn_export').click(function() {
            exportExcel();
        });
        $('#MarketRiskSceneManage003_btn_filter').css("display","none");
        $('#comfirmBtn').click(function() {
            if(origin != "manage"){
                entity.ftrInfoList = factorTable;
            }
            top.returnData = entity;
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
    });
}())