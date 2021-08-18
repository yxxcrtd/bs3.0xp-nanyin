import hasRole from "./directives/hasRole";
import hasPermi from "./directives/hasPermi";
import dialogDrag from "./directives/dialogDrag";
import jrTableInfiniteScroll from "./directives/jrTableInfiniteScroll";

const install = function(Vue) {
  Vue.directive("hasRole", hasRole);
  Vue.directive("hasPermi", hasPermi);
  Vue.directive("dialogDrag", dialogDrag);
  Vue.directive("infiniteTable", jrTableInfiniteScroll);
};

if (window.Vue) {
  window["hasRole"] = hasRole;
  window["hasPermi"] = hasPermi;
  window["dialogDrag"] = dialogDrag;
  window["infiniteTable"] = jrTableInfiniteScroll;
  Vue.use(install); // eslint-disable-line
}

export default install
