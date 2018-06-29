<template>
    <div class="">
        <el-row :gutter="20">
            <el-col :span="4">
                <el-input placeholder="请输入昵称" clearable v-model="nickname" size="small">
                </el-input>
            </el-col>
            <el-col :span="4">
                <el-input placeholder="请输入手机号" clearable v-model="telephone" size="small">
                </el-input>
            </el-col>
            <el-col :span="4">
                <el-button type="primary" icon="el-icon-search" size="small" @click="filterCustom">查找</el-button>
            </el-col>
        </el-row>
        <el-table
            :data="tableData"
            style="width: 100%">
            <el-table-column prop="id" label="Id" sortable width="100">
            </el-table-column>
            <el-table-column prop="nickname" label="昵称" width="100">
            </el-table-column>
            <el-table-column prop="avatar" label="头像" width="100">
                <template slot-scope="scope">
                    <img class="avatar" :src="scope.row.avatar">
                </template>
            </el-table-column>
            <el-table-column prop="telephone" label="手机号码"></el-table-column>
            <el-table-column prop="gender" label="性别"
                             :filters="[{text: '男', value: '男'}, {text: '女', value: '女'}]"
                             :filter-method="filterHandler"></el-table-column>
            <el-table-column prop="birthday" label="生日"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column prop="created" label="注册时间" width="120"></el-table-column>
            <el-table-column prop="logged" label="最后登录时间" width="120"></el-table-column>
            <el-table-column prop="status" label="状态" width="150"
                             :filters="[{text: '正常', value: 0}, {text: '拉黑', value: 1}]"
                             :filter-method="filterHandler">
                <template slot-scope="scope">
                    {{scope.row.status==0?"正常":"拉黑"}}
                </template>
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="120">
                <template slot-scope="scope">
                    <el-button v-if="scope.row.status==0" type="text" size="small"
                               @click.native.prevent="deleteUser(scope.$index,scope.row)">
                        拉黑
                    </el-button>
                    <el-button v-if="scope.row.status==1" type="text" size="small"
                               @click.native.prevent="recoveryUser(scope.$index,scope.row)">
                        恢复
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                lanmuId:"",
                userPage:{},
                nickname:"",
                telephone:"",
                tableData:[]
            }
        },
        methods: {
            init:function () {
                this.lanmuId=this.$route.query.lanmuId;
                this.getUser();
            },
            getUser(){
                this.$axios.get(this.HOST+'/user/getNormalUserList',
                    {
                        params:{"pageNum": 1,"pageSize":10}
                    }
                ).then((res) => {
                    this.tableData=res.data.data.list
                this.userPage=res.data.data
            })
            },
            filterHandler:function (value, row, column) {
                const property = column['property'];
                return row[property] === value;
            },
            filterCustom(){
                if(this.nickname==""&&this.telephone==""){
                    this.tableData=this.userPage.list;
                }else{
                    this.tableData=[];
                    for(let o of this.userPage.list){
                        console.log(this.username=="")
                        if(this.nickname==""&&this.telephone!=""&&this.telephone==o.telephone){
                            console.log(1)
                            this.tableData.push(o)
                        }else if(this.nickname!=""&&this.telephone==""&&this.nickname==o.nickname){
                            this.tableData.push(o)
                        }else if(this.telephone==o.telephone&&this.nickname==o.nickname){
                            this.tableData.push(o)
                        }
                    }
                }
            },
            deleteUser(index,row){
                row.status=1
            },
            recoveryUser(){
                row.status=0
            }
        },
        created:function(){
            this.init();
        },
        computed: {

        }
    }

</script>

<style>
    .avatar{
        width: 40px;
        height:40px;
    }
</style>

