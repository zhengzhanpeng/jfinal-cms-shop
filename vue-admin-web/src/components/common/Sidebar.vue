<template>
    <div class="sidebar">
        <el-menu class="sidebar-el-menu" :default-active="onRoutes" :collapse="collapse" background-color="#324157"
            text-color="#bfcbd9" active-text-color="#1AA094" unique-opened  router>
            <template v-for="item in items">
                <template v-if="item.subs">
                    <el-submenu :index="'menu_'+item.lanmuId" :key="'menu_'+item.lanmuId">
                        <template slot="title">
                            <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                        </template>
                        <el-menu-item v-for="(subItem,i) in item.subs"  :index="'menu_'+subItem.lanmuId"  :route="{ path: '/'+subItem.index,query:{lanmuId:subItem.lanmuId,title:subItem.title}}">
                            {{ subItem.title }}
                        </el-menu-item>
                    </el-submenu>
                </template>
                <template v-else>
                    <el-menu-item :index="'menu_'+item.lanmuId"  :route="{ path: '/'+item.index,query:{lanmuId:item.lanmuId,title:item.title}}">
                        <i :class="item.icon"></i><span slot="title">{{ item.title }}</span>
                    </el-menu-item>
                </template>
            </template>
        </el-menu>
    </div>
</template>

<script>
    import bus from '../common/bus';
    export default {
        data() {
            return {
                collapse: false,
                items: [
                ]
            }
        },
        methods:{
          getMenu(){
              this.$axios.get('api/admin/getMenu',
                  {
                      params:{page: 1}
                  }
              ).then((res) => {
                  this.items = res.data.data;
              })
          }
        },
        computed:{
            onRoutes(){
                return this.$route.path.replace('/','');
            }
        },
        created(){
            this.getMenu();
            // 通过 Event Bus 进行组件间通信，来折叠侧边栏
            bus.$on('collapse', msg => {
                this.collapse = msg;
            });
            bus.$on('menus', menus => {
                this.items = menus;
        })
        }
    }
</script>

<style scoped>
    .sidebar{
        display: block;
        position: absolute;
        left: 0;
        top: 70px;
        bottom:0;
        overflow-y: scroll;
    }
    .sidebar::-webkit-scrollbar{
        width: 0;
    }
    .sidebar-el-menu:not(.el-menu--collapse){
        width: 250px;
    }
    .sidebar > ul {
        height:100%;
    }
</style>
