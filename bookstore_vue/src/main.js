import Vue from 'vue'
import App from './App.vue'

import ViewUI from 'view-design'
import 'view-design/dist/styles/iview.css'
import VueTableDynamic from 'vue-table-dynamic'

Vue.use(ViewUI);
Vue.use(VueTableDynamic);

Vue.config.productionTip = false;
Vue.config.productionTip = false;

new Vue({
    render: h => h(App),
}).$mount('#app');
