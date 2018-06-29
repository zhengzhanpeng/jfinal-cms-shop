<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="fa fa-bars"></i> 商品管理</el-breadcrumb-item>
                <el-breadcrumb-item>商品类目</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="handle-box">
            <Blockquote>
                <el-button type="primary" icon="el-icon-circle-plus-outline" @click="addCategory">添加类目</el-button>
            </Blockquote>
        </div>
        <div class="container">
            <el-table :data="tableData"  Checkbox border style="width: 100%" fit ref="multipleTable" :default-sort="{prop:'sort_num',order:'descending'}">
                <el-table-column prop="sort_num"  width="60" label="排序" >
                    <template slot-scope="scope">
                        <el-input @change="handleSort(scope.$index,scope.row)" class="input-sort" v-model="scope.row.sort_num"  placeholder=""></el-input>
                    </template>
                </el-table-column>
                <el-table-column prop="id"  label="id">
                </el-table-column>
                <el-table-column prop="name"  label="类目名称">
                </el-table-column>
                <el-table-column prop="count"  label="商品数量">
                </el-table-column>
                <el-table-column prop="level"  label="类目级别">
                    <template slot-scope="scope">
                        {{scope.row.level}}级类目
                    </template>
                </el-table-column>
                <el-table-column label="图标">
                    <template slot-scope="scope">
                        <img class="icon" :src="scope.row.icon_temp">
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="220">
                    <template slot-scope="scope">
                        <a  href="javascript:;" @click="addCategory(scope.$index, scope.row)"> <i class="el-icon-edit-outline"></i>编辑 </a>
                        <a  href="javascript:;" @click="handleDelete(scope.$index, scope.row)"> <i class="el-icon-delete"></i>删除 </a>
                    </template>
                </el-table-column>
            </el-table>
            <el-dialog title="类目编辑" :visible.sync="dialogFieldVisible" width="500px">
                <el-form :model="category" label-width="80px" ref="form">
                    <el-form-item label="类目名" prop="name"  :rules="[
                                      { required: true, message: '请输入类目名', trigger: 'blur' }
                                      ]">
                        <el-input v-model="category.name"></el-input>
                    </el-form-item>

                    <el-form-item label="类目级别" prop="level"  :rules="[
                                      { required: true, message: '请选择类目级别', trigger: 'blur' }
                                      ]">
                        <el-select v-model="category.level" placeholder="请选择">
                            <el-option label="一级类目" value="1"></el-option>
                            <el-option label="二级类目" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item v-if="category.level==2" label="上级类目" prop="level"  :rules="[
                                      { required: true, message: '请选择上级类目', trigger: 'blur' }
                                      ]">
                        <el-select v-model="category.uplevelId" placeholder="请选择">
                            <el-option v-for="item in tableData" v-if="item.level==1"  :label="item.name" :value="item.id+''"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="图标"  prop="icon">
                        <pictureview type="upload" :selectedImg="category.icon_temp" @selectChange="handleImgSuccess"></pictureview>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFieldVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveForm('form')">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import pictureview from '@/components/my-components/pictureview/index';
    export default {
        data: function(){
            return {
                tableData:[],
                category:{},
                dialogFieldVisible:false
            }
        },
        methods: {
            getData() {
                this.$axios.get(this.HOST+'product/getCategory',
                    {

                    }
                ).then((res) => {
                    this.tableData=res.data.data;
                })
            },
            addCategory(index,row){
                if(row!=null){
                    row.level=row.level+"";
                    row.uplevelId=row.uplevelId+"";
                    if(row.uplevelId=="0"){
                        row.uplevelId="";
                    }
                    this.category=row
                }else{
                    this.category={}
                }

                this.dialogFieldVisible=true
            },
            handleImgSuccess(attachments){
                if(attachments.length>0) {
                    this.$set(this.category, "icon_temp", attachments[0].thumbnail_temp)
                    this.$set(this.category, "icon", attachments[0].url)
                }
            },
            saveForm(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.dialogFieldVisible=false
                        this.$axios({
                            method:"post",
                            url: this.HOST+"/product/updateCategory",
                            data: this.category
                        }).then((res)=>{
                            if(res.data.code==0) {

                                if(this.category.id==null){
                                    this.category.id=res.data.data.id;
                                    this.category.sort_num=1;
                                    this.tableData.push(this.category);
                                }
                                this.$message.success('保存成功');
                            }else{
                                this.$message.error(res.data.msg);
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            handleDelete(index, row) {

                this.$axios.get(this.HOST+'/product/delCategory',
                    {
                        params:{id: row.id}
                    }
                ).then((res) => {
                    if(res.data.code==0) {
                        this.$message.success('删除成功');
                        this.tableData.splice(index, 1);
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })

            },
            handleSort(index,row){
                let tableData=this.tableData;
                for(var o in tableData){
                    if(tableData[o].id==row.id){
                        this.$set(this.tableData,o,row)
                    }
                }
                this.$axios({
                    method:"post",
                    url: this.HOST+"/product/sortCategory",
                    data: row
                }).then((res)=>{
                    if(res.data.code==0) {
                        this.$message.success('排序成功');
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })
            },
        },
        created:function(){
            this.getData();
        },
        components:{
            'pictureview':pictureview
        }
    }
</script>

<style scoped>
    .icon{
        width: 50px;
        height:50px;
    }
</style>
