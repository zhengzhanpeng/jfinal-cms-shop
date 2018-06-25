<template>
    <div class="">
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="fa fa-bars"></i> 内容管理</el-breadcrumb-item>
                <el-breadcrumb-item>{{$route.meta.title}}</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <template v-if="lanmu.is_single==1">
            <div class="handle-box">
                <Blockquote>
                    <router-link  :to="{ path: '/contentEdit?contentId='+content.id+'&lanmuId='+lanmu.id}"><i class="el-icon-edit-outline"></i>编辑 </router-link>
                </Blockquote>
            </div>
            <div class="container">
                <template v-for="item in fields">
                    <div class="el-flex" v-if="item.field_show==1&&item.field_en=='thumbnail'">
                        <div class="el-flex-item" style="flex:0 0 100px">
                            {{item.field_zh}}
                        </div>
                        <div class="el-flex-item thumbnail">
                            <img  :src="ObjectGetByKey(item.field_en)">
                        </div>
                    </div>
                    <div class="el-flex" v-else-if="item.field_show==1&&item.field_en=='picture'">
                        <div class="el-flex-item" style="flex:0 0 100px">
                            {{item.field_zh}}
                        </div>
                        <div class="el-flex-item pictures">
                            <img v-for="picture in content.pictures"  :src="picture.url">
                        </div>
                    </div>
                    <div class="el-flex" v-else-if="item.field_show==1&&item.field_en=='content'">
                        <div class="el-flex-item" style="flex:0 0 100px">
                            {{item.field_zh}}
                        </div>
                        <div class="el-flex-item"  v-html="ObjectGetByKey(item.field_en)">
                        </div>
                    </div>
                    <div class="el-flex" v-else-if="item.field_show==1">
                        <div class="el-flex-item" style="flex:0 0 100px">
                            {{item.field_zh}}
                        </div>
                        <div class="el-flex-item">
                            {{ObjectGetByKey(item.field_en)}}
                        </div>
                    </div>
                </template>
            </div>
        </template>
        <template v-else-if="lanmu.is_single==0">
            <div class="handle-box">
                <Blockquote>
                    <el-button type="primary" icon="el-icon-circle-plus-outline" @click="addContent">添加内容</el-button>
                    <el-button type="primary"  @click="batchDel" icon="el-icon-d-caret">批量删除</el-button>
                    <el-button type="primary" icon="el-icon-refresh" @click="getData">刷新</el-button>
                </Blockquote>
            </div>
            <div class="container">
                <el-table :data="contentList" row-key="" Checkbox border style="width: 100%" fit ref="multipleTable" @selection-change="handleSelectionChange"  :default-sort="{prop:'sort_num'}">
                    <el-table-column
                        type="selection"
                        width="55">
                    </el-table-column>
                    <el-table-column prop="sort_num"  width="60" label="排序" >
                        <template slot-scope="scope">
                            <el-input @change="handleSort(scope.$index,scope.row)" class="input-sort" v-model="scope.row.sort_num"  placeholder=""></el-input>
                        </template>
                    </el-table-column>
                    <template v-for="item in fields">
                        <template v-if="item.field_show==1&&item.field_en=='thumbnail'">
                            <el-table-column :prop="item.field_en"  :label="item.field_zh" width="100">
                                <template slot-scope="scope">
                                    <a :href="scope.row.thumbnail" target="_blank"><img class="thumbnail" :src="scope.row.thumbnail"></a>
                                </template>
                            </el-table-column>
                        </template>
                        <template v-else-if="item.field_show==1&&item.field_en=='out_link'">
                            <el-table-column :prop="item.field_en"  :label="item.field_zh" width="150" fit>
                                <template slot-scope="scope">
                                    <a :href="scope.row.out_link" target="_blank">{{scope.row.out_link}}</a>
                                </template>
                            </el-table-column>
                        </template>
                        <template v-else-if="item.field_show==1">
                            <el-table-column :prop="item.field_en"  :label="item.field_zh">

                            </el-table-column>
                        </template>
                    </template>
                    <el-table-column label="操作" width="220">
                        <template slot-scope="scope">
                            <router-link  :to="{ path: '/contentEdit?contentId='+scope.row.id+'&lanmuId='+lanmu.id}"><i class="el-icon-edit-outline"></i>编辑 </router-link>
                            <a  href="javascript:;" @click="handleDelete(scope.$index, scope.row)"> <i class="el-icon-delete"></i>删除 </a>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
        </template>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                lanmu:{},
                content:{id:""},
                contentList:[],
                fields:[],
                multipleSelection:[],
                selectedId:[]
            }
        },
        methods: {
            init:function () {
                this.$set(this.lanmu,"id",this.$route.query.lanmuId);
                this.getField();
                this.getData();
            },
            addContent(){
                this.$router.push({path:'/contentEdit?lanmuId='+this.lanmu.id+'&title=添加内容'});
            },
            getData() {
                this.$axios.get('api/admin/getContent',
                    {
                        params:{"page": 1,"lanmuId":this.lanmu.id}
                    }
                ).then((res) => {
                    this.lanmu=res.data.data.lanmu;
                    if(this.lanmu.is_single==0){
                        this.contentList = res.data.data.contents;
                    }else if(res.data.data.content!=null){
                        this.content = res.data.data.content;
                    }
                })
            },
            getField(){
                if(this.lanmu.id!=null){
                    this.$axios.get(
                        this.HOST+"/admin/getField",
                        {params:{lanmuId:this.lanmu.id}},
                    ).then((res) => {
                        this.fields=res.data.data
                    })
                }
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            handleEdit(index, row) {
                this.$router.push('/contentEdit?lanmuId='+row.id);
            },
            handleDelete(index, row) {
                this.selectedId=[];
                this.selectedId.push(row.id);
                this.delContent(index);
            },
            batchDel:function () {
                this.selectedId=[];
                for(var o of this.multipleSelection){
                    this.selectedId.push(o.id);
                }
                this.delContent();
            },
            delContent(){
                this.$axios.get('api/admin/delContent',
                    {
                        params:{id:this.selectedId}
                    }
                ).then((res) => {
                    if(res.data.code==0) {
                        this.$message.success('删除成功');
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })
            },
            handleSort(index,row){
                let contentList=this.contentList;
                for(var o in contentList){
                    if(contentList[o].id==row.id){
                        this.$set(this.contentList,o,row)
                    }
                }
                this.$axios({
                    method:"post",
                    url: this.HOST+"/admin/sortContent",
                    data: row
                }).then((res)=>{
                    if(res.data.code==0) {
                        this.$message.success('保存成功');
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })
            },
            ObjectGetByKey(key){
                let content=this.content;
                return content!=null?content[key]:""
            },
        },
        created:function(){
            this.init();
        },
        watch: {
            '$route' (to, from) {
                this.init();
            }
        },
        activated:function () {

        },
        computed: {

        }
    }

</script>

<style>
    .el-flex{
        min-height: 30px;
        padding: 10px 0;
    }
    .thumbnail img{
        height: 100px;
    }
    .pictures img{
        height: 100px;
        margin-right: 20px;
        margin-bottom: 20px;
    }

</style>

