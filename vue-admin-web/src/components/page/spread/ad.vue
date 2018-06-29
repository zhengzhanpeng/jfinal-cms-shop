<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="fa fa-bars"></i> 推广管理</el-breadcrumb-item>
                <el-breadcrumb-item>广告管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="handle-box">
            <Blockquote>
                <el-button type="primary" icon="el-icon-circle-plus-outline" @click="editAd">新建广告</el-button>
            </Blockquote>
        </div>
        <div class="container">
            <el-table :data="tableData"  Checkbox border style="width: 100%" fit ref="multipleTable" :default-sort="{prop:'sort_num',order:'descending'}">
                <el-table-column prop="sort_num"  width="60" label="排序" >
                    <template slot-scope="scope">
                        <el-input @change="handleSort(scope.$index,scope.row)" class="input-sort" v-model.number="scope.row.sort_num"  placeholder=""></el-input>
                    </template>
                </el-table-column>
                <el-table-column prop="id"  label="id" width="80">
                </el-table-column>
                <el-table-column prop="name"  label="广告标题" width="200">
                </el-table-column>
                <el-table-column label="图片" class-name="table_thumbnail" width="80">
                    <template slot-scope="scope">
                        <img  :src="scope.row.thumbnail_temp">
                    </template>
                </el-table-column>
                <el-table-column label="商品图片" class-name="table_thumbnail" width="80">
                    <template slot-scope="scope" v-if="scope.row.product">
                        <img  :src="scope.row.product.thumbnail_temp">
                    </template>
                </el-table-column>
                <el-table-column label="商品名称" class-name="table_thumbnail" width="300">
                    <template slot-scope="scope" v-if="scope.row.product">
                        {{scope.row.product.name}}
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="220">
                    <template slot-scope="scope">
                        <a  href="javascript:;" @click="editAd(scope.$index, scope.row)"> <i class="el-icon-edit-outline"></i>编辑 </a>
                        <a  href="javascript:;" @click="handleDelete(scope.$index, scope.row)"> <i class="el-icon-delete"></i>删除 </a>
                    </template>
                </el-table-column>
            </el-table>
            <el-dialog title="广告编辑" :visible.sync="dialogVisible" width="500px">
                <el-form :model="ad" label-width="80px" ref="form">
                    <el-form-item label="广告标题" prop="name"  :rules="[
                                      { required: true, message: '请输入广告标题', trigger: 'blur' }
                                      ]">
                        <el-input v-model="ad.name"></el-input>
                    </el-form-item>
                    <el-form-item label="图片"  prop="thumbnail" :rules="[
                                      { required: true, message: '请选择图片', trigger: 'blur' }
                                      ]">
                        <div class="el-flex">
                        <img class="upload-preview-img" v-if="ad.thumbnail" :src="ad.thumbnail_temp">
                        <pictureview type="upload" @selectChange="selectChange"></pictureview>
                        </div>
                    </el-form-item>
                    <el-form-item label="链接商品">
                        <el-select v-model="ad.productId" placeholder="请选择">
                            <el-option
                                v-for="item in productList"
                                :label="item.name"
                                :value="item.id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveForm('form')">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import pictureview from '@/components/my-components/pictureview/index';
    export default {
        data() {
            return {
                lanmu:{},
                tableData:[],
                ad:{},
                productList:[],
                dialogVisible:false
            }
        },
        methods: {
            init:function () {
                this.lanmu.id=this.$route.query.lanmuId
                this.getData()
                this.getProductList()
            },
            getData() {
                this.$axios.get(this.HOST+'product/getAdList',
                    {
                        params:{"lanmuId":this.lanmu.id}
                    }
                ).then((res) => {
                    this.tableData=res.data.data;
                })
            },
            getProductList(){
                this.$axios.get(this.HOST+'/product/getProductList',
                    {
                        params:{"lanmuId":this.lanmu.id}
                    }
                ).then((res) => {
                    this.productList=res.data.data;
                })
            },
            editAd(index,row){
                if(row!=null){
                    row.product=""
                    this.ad=row
                }else{
                    this.ad={}
                }
                this.dialogVisible=true

            },
            selectChange(attachments){
                if(attachments!=null){
                    this.$set(this.ad,"thumbnail",attachments[0].url)
                    this.$set(this.ad,"thumbnail_temp",attachments[0].thumbnail_temp)
                }
            },
            saveForm(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.dialogVisible=false
                        this.$axios({
                            method:"post",
                            url: this.HOST+"/product/saveAd?lanmuId="+this.lanmu.id,
                            data: this.ad
                        }).then((res)=>{
                            if(res.data.code==0) {
                                if(this.ad.id==null){
                                    this.ad.id=res.data.data.id;
                                    this.tableData.push(this.ad)
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
            handleSort(index,row){
                let tableData=this.tableData;
                for(var o in tableData){
                    if(tableData[o].id==row.id){
                        this.$set(this.tableData,o,row)
                    }
                }
                this.$axios({
                    method:"post",
                    url: this.HOST+"/product/saveAd?lanmuId="+this.lanmu.id,
                    data: row
                }).then((res)=>{
                    if(res.data.code==0) {
                        this.$message.success('排序成功');
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })
            },
            handleDelete(index,row){
                this.$axios.get(this.HOST+'/product/delAd',
                    {
                        params:{id: row.id}
                    }
                ).then((res) => {
                    if(res.data.code==0) {
                        let tableData=this.tableData;
                        for(var o in tableData){
                            if(tableData[o].id==row.id){
                                this.tableData.splice(o,1)
                            }
                        }
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })
            }
        },
        created:function(){
            this.init();
        },
        computed: {

        },
        components:{
            'pictureview':pictureview
        }
    }

</script>

<style>

</style>

