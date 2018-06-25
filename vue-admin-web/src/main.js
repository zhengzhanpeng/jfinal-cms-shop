import Vue from 'vue';
import App from './App';
import router from './router';
import axios from 'axios';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';    // 默认主题
// import '../static/css/theme-green/index.css';       // 浅绿色主题
import "font-awesome/css/font-awesome.min.css";
import "babel-polyfill";
import qs from 'qs';

// 引入自定义组件。index.js是组件的默认入口
import Blockquote from './components/my-components/blockquote';
Vue.use(Blockquote);
Vue.use(ElementUI, { size: 'middle' });
Vue.use(qs);
Vue.prototype.$axios = axios;

//使用钩子函数对路由进行权限跳转
router.beforeEach((to, from, next) => {
    const user = JSON.parse(localStorage.getItem('user'));
    console.log(user)
    if(to.query.title!=null){
        to.meta.title=to.query.title
    }
    if(!user && to.path !== '/login'){
        next('/login');
    }else if(to.meta.permission){
        // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已'
        user.role.role === 'admininistrator' ? next() : next('/403');
    }else{
        // 简单的判断IE10及以下不进入富文本编辑器，该组件不兼容
        if(navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor'){
            Vue.prototype.$alert('vue-quill-editor组件不兼容IE10及以下浏览器，请使用更高版本的浏览器查看', '浏览器不兼容通知', {
                confirmButtonText: '确定'
            });
        }else{
            next();
        }
    }
})
axios.interceptors.request.use(
    config => {
        // 这里写死一个token，你需要在这里取到你设置好的token的值
        const token = 'this is a token';
        if (token) {
            // 这里将token设置到headers中，header的key是Authorization，这个key值根据你的需要进行修改即可
            config.headers.Authorization = token;
        }
        if(config.method  === 'post'){

            config.data = qs.stringify(config.data);
            config.headers.contentType="application/x-www-form-urlencoded";
        }
        return config
    },
    error => {
        return Promise.reject(error)
    });

Vue.prototype.HOST="/api"
new Vue({
    router,
    render: h => h(App)
}).$mount('#app');
