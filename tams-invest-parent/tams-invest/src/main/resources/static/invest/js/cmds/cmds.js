var websocket = null;
function cmds(wsUrl,onMsgFunction,openFunction,errorFunction) {
    //判断当前浏览器是否支持WebSocket, 主要此处要更换为自己的地址
    if ('WebSocket' in window) {
        websocket = new WebSocket(wsUrl);
    } else {
        alert('Not support websocket')
    }
    if(!onMsgFunction){
        alert('接收消息函数不能为空');
        return;
    }
    //接收到消息的回调方法
    websocket.onmessage = function(event) {
        onMsgFunction(event.data);
    }
    //连接成功建立的回调方法
    if(openFunction){
        websocket.onopen = function(event) {
            openFunction(event);
        }
    }
    //连接发生错误的回调方法
    if(errorFunction){
        websocket.onerror = function() {
            setMessageInnerHTML("error");
        };
    }
    return websocket;
}

function send(websocket,message){
    websocket.send(message);
}

function sendBond(websocket,productID){
    send(websocket,productID+"_CE");
}

function sendRepo(websocket,termType,hqType){
    if("DR" == hqType){
        termType =  termType.replace("R","DR");
    }
    console.log(termType+"_"+hqType)
    send(websocket,termType+"_"+hqType);
}

function close(websocket){
    if(websocket){
        websocket.close();
    }
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = close(websocket);



