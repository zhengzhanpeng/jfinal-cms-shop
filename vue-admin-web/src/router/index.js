import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    //mode: 'history',
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: { title: '自述文件' },
            children:[
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: { title: '系统首页' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/lanmu',
                    component: resolve => require(['../components/page/lanmu.vue'], resolve),
                    meta: { title: '栏目管理' ,keepAlive:true},
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/lanmuEdit',
                    component: resolve => require(['../components/page/lanmuEdit.vue'], resolve),
                    meta: { title: '栏目编辑' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/content',
                    component: resolve => require(['../components/page/content/content.vue'], resolve),
                    meta: { title: '内容管理' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/contentEdit',
                    component: resolve => require(['../components/page/content/contentEdit.vue'], resolve),
                    meta: { title: '编辑内容' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/user',
                    component: resolve => require(['../components/page/user/user.vue'], resolve),
                    meta: { title: '用户管理' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/goodslist',
                    component: resolve => require(['../components/page/goods/goodslist.vue'], resolve),
                    meta: { title: '商品列表' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/goodsEdit',
                    component: resolve => require(['../components/page/goods/goodsEdit.vue'], resolve),
                    meta: { title: '添加商品' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/category',
                    component: resolve => require(['../components/page/goods/category.vue'], resolve),
                    meta: { title: '商品类目' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/attribute',
                    component: resolve => require(['../components/page/goods/attribute.vue'], resolve),
                    meta: { title: '商品规格' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/adminUser',
                    component: resolve => require(['../components/page/user/adminUser.vue'], resolve),
                    meta: { title: '管理员' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/user',
                    component: resolve => require(['../components/page/user/user.vue'], resolve),
                    meta: { title: '会员管理' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/address',
                    component: resolve => require(['../components/page/user/address.vue'], resolve),
                    meta: { title: '会员管理' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/promotion',
                    component: resolve => require(['../components/page/spread/promotion.vue'], resolve),
                    meta: { title: '促销活动' },
                    props: (route) => ({ query: route.query })
                },{
                    path: '/editPromotion',
                    component: resolve => require(['../components/page/spread/promotionEdit.vue'], resolve),
                    meta: { title: '限时折扣' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/ad',
                    component: resolve => require(['../components/page/spread/ad.vue'], resolve),
                    meta: { title: '广告' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/attachment',
                    component: resolve => require(['../components/page/Attachment.vue'], resolve),
                    meta: { title: '限时折扣' },
                    props: (route) => ({ query: route.query })
                },
                {
                    path: '/table',
                    component: resolve => require(['../components/page/BaseTable.vue'], resolve),
                    meta: { title: '基础表格' }
                },
                {
                    path: '/tabs',
                    component: resolve => require(['../components/page/Tabs.vue'], resolve),
                    meta: { title: 'tab选项卡' }
                },
                {
                    path: '/form',
                    component: resolve => require(['../components/page/BaseForm.vue'], resolve),
                    meta: { title: '基本表单' }
                },
                {
                    // 富文本编辑器组件
                    path: '/editor',
                    component: resolve => require(['../components/page/VueEditor.vue'], resolve),
                    meta: { title: '富文本编辑器' }
                },
                {
                    // markdown组件
                    path: '/markdown',
                    component: resolve => require(['../components/page/Markdown.vue'], resolve),
                    meta: { title: 'markdown编辑器' }
                },
                {
                    // 图片上传组件
                    path: '/upload',
                    component: resolve => require(['../components/page/Upload.vue'], resolve),
                    meta: { title: '文件上传' }
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    component: resolve => require(['../components/page/BaseCharts.vue'], resolve),
                    meta: { title: 'schart图表' }
                },
                {
                    // 拖拽列表组件
                    path: '/drag',
                    component: resolve => require(['../components/page/DragList.vue'], resolve),
                    meta: { title: '拖拽列表' }
                },
                {
                    // 权限页面
                    path: '/permission',
                    component: resolve => require(['../components/page/Permission.vue'], resolve),
                    meta: { title: '权限测试', permission: true }
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '/404',
            component: resolve => require(['../components/page/404.vue'], resolve)
        },
        {
            path: '/403',
            component: resolve => require(['../components/page/403.vue'], resolve)
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})
