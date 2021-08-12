/*
对话框拖拽指令
*/
export default {
  inserted(el, binding, vnode, oldVnode) {
    //初始非全屏标识
    // let isFullScreen = false;
    //当前宽高
    // let nowWidth = 0;
    // let nowHight = 0;
    //当前顶部高度1
    let nowMarginTop = 0;
    const dialogHeaderEl = el.querySelector(".el-dialog__header");
    // let hasSetBodyHight = false;
    //弹窗
    const dragDom = el.querySelector(".el-dialog");
    //给弹窗加上overflow auto；不然缩小时框内的标签可能超出dialog；
    // dragDom.style.overflow = "auto";
    //清除选择头部文字效果
    dialogHeaderEl.onselectstart = new Function("return false");
    //头部加上可拖动cursor
    dialogHeaderEl.style.cursor = "move";

    // 获取对话框原有属性 ie dom元素.currentStyle /火狐谷歌 window.getComputedStyle(dom元素, null);
    const sty = dragDom.currentStyle || window.getComputedStyle(dragDom, null);

    //头部插入最大化最小化元素图标
    // let maxMin = document.createElement("button");
    // maxMin.className += " el-dialog__headerbtn el-dialog__minmax";
    // maxMin.style.right = "40px";
    // maxMin.style.color = "#909399";
    // maxMin.title = "最大化";
    // maxMin.innerHTML =
    //   '<i class="jricfont jric-maximize" onMouseOver="this.style.color=\'#409EFF\'" onMouseOut="this.style.color=\'inherit\'"></i>';
    // dialogHeaderEl.insertBefore(maxMin, dialogHeaderEl.childNodes[1]);

    let moveDown = e => {
      const disX = e.clientX - dialogHeaderEl.offsetLeft;
      const disY = e.clientY - dialogHeaderEl.offsetTop;

      // 获取到的值带px 正则匹配替换
      let styL, styT;

      styL = +sty.left.replace(/\px/g, "");
      styT = +sty.top.replace(/\px/g, "");

      document.onmousemove = function(e) {
        let l = e.clientX - disX;
        let t = e.clientY - disY;
        dragDom.style.left = `${l + styL}px`;
        dragDom.style.top = `${t + styT}px`;
      };

      document.onmouseup = function(e) {
        document.onmousemove = null;
        document.onmouseup = null;
      };
    };
    dialogHeaderEl.onmousedown = moveDown;
    // let bodyHeight = "auto";
    // let dialogFooter = "auto";
    // function setMaxMin() {
    //   if (isFullScreen == false) {
    //     let i = maxMin.querySelector(".jric-maximize");
    //     i.classList.remove("jric-maximize");
    //     i.classList.add("jric-minimize");
    //     maxMin.title = "还原";
    //     bodyHeight =
    //       dragDom.querySelector(".el-dialog__body").offsetHeight + "px";
    //     dialogFooter = dragDom.querySelector(".el-dialog__footer");
    //     nowHight = dragDom.clientHeight;
    //     nowWidth = dragDom.clientWidth;
    //     nowMarginTop = dragDom.style.marginTop;
    //     dragDom.style.left = 0;
    //     dragDom.style.top = 0;
    //     // if (dialogFooter) {
    //     //   dragDom.style.height = "85VH";
    //     // } else {
    //     //   dragDom.style.height = "100VH";
    //     // }
    //     // dragDom.style.height = "85VH";
    //     dragDom.style.width = "100VW";
    //     dragDom.style.marginTop = 0;
    //     isFullScreen = true;
    //     dialogHeaderEl.style.cursor = "initial";
    //     dialogHeaderEl.onmousedown = null;
    //     if (!hasSetBodyHight) {
    //       dragDom.querySelector(".el-dialog__body").style.height =
    //         "calc(100% - " + dialogHeaderEl.offsetHeight + "px)";
    //       hasSetBodyHight = true;
    //     }
    //   } else {
    //     let i = maxMin.querySelector(".jric-minimize");
    //     i.classList.remove("jric-minimize");
    //     i.classList.add("jric-maximize");
    //     // maxMin.innerHTML = '<i class="jric-maximize"></i>';
    //     maxMin.innerHTML = '<i class="jricfont jric-maximize"></i>';
    //     maxMin.title = "最大化";
    //     dragDom.style.height = "auto";
    //     dragDom.style.width = nowWidth + "px";
    //     dragDom.style.marginTop = nowMarginTop;
    //     isFullScreen = false;
    //     dialogHeaderEl.style.cursor = "move";
    //     dialogHeaderEl.onmousedown = moveDown;
    //     dragDom.querySelector(".el-dialog__body").style.height = bodyHeight;
    //     hasSetBodyHight = false;
    //   }
    // }
    // //点击放大缩小效果
    // maxMin.onclick = setMaxMin;
  }
};
