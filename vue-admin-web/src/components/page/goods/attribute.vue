<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="fa fa-bars"></i> 商品管理</el-breadcrumb-item>
                <el-breadcrumb-item>商品规格</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="handle-box">
            <Blockquote>
                <el-button type="primary" icon="el-icon-circle-plus-outline" @click="addAttribute">添加规格</el-button>
            </Blockquote>
        </div>
        <div class="container">
            <el-table :data="tableData"  Checkbox border style="width: 100%" fit ref="multipleTable">
                <el-table-column prop="id"  label="id">
                </el-table-column>
                <el-table-column prop="name"  label="规格名称">
                </el-table-column>
                <el-table-column   label="规格值">
                    <template slot-scope="scope">
                        <el-tag v-for="item in scope.row.attributeValues"  size="small" closable @close="handleClose(scope.$index,item.id)"> {{item.value}} </el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="220">
                    <template slot-scope="scope">
                        <a  href="javascript:;" @click="addAttribute(scope.$index, scope.row)"> <i class="el-icon-edit-outline"></i>编辑 </a>
                        <a  href="javascript:;" @click="addAttributeValue(scope.$index, scope.row)"> <i class="fa fa-plus"></i>添加规格值 </a>
                        <a  href="javascript:;" @click="handleDelete(scope.$index, scope.row)"> <i class="el-icon-delete"></i>删除 </a>
                    </template>
                </el-table-column>
            </el-table>
            <el-dialog title="规格编辑" :visible.sync="dialogFieldVisible" width="500px">
                <el-form :model="attribute" label-width="80px" ref="form">
                    <el-form-item label="规格名" prop="name"  :rules="[
                                      { required: true, message: '请输入规格名', trigger: 'blur' }
                                      ]">
                        <el-input v-model="attribute.name" placeholder="颜色"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFieldVisible = false">取 消</el-button>
                    <el-button type="primary" @click="saveForm('form')">确 定</el-button>
                </div>
            </el-dialog>
            <el-dialog title="添加规格值" :visible.sync="dialogField2Visible" width="500px">
                <el-form :model="attributeValue" label-width="80px" ref="form2">
                    <el-form-item label="规格值" prop="value"  :rules="[
                                      { required: true, message: '规格值', trigger: 'blur' }
                                      ]">
                        <el-input v-model="attributeValue.value" placeholder="黄色"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogField2Visible = false">取 消</el-button>
                    <el-button type="primary" @click="saveForm2('form2')">确 定</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                tableData:[],
                attribute:{},
                attributeValue:{},
                dialogFieldVisible:false,
                dialogField2Visible:false
            }
        },
        methods: {
            getData() {
                this.$axios.get(this.HOST+'product/getAttribute',
                    {

                    }
                ).then((res) => {
                    this.tableData=res.data.data;
                })
            },
            addAttribute(index,row){
                if(row!=null){
                    this.attribute=row
                }else{
                    this.attribute={}
                }
                this.dialogFieldVisible=true
            },
            addAttributeValue(index,row){
                this.attributeValue={}
                this.attributeValue.attributeId=row.id
                this.dialogField2Visible=true
            },
            saveForm(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.dialogFieldVisible=false
                        this.$axios({
                            method:"post",
                            url: this.HOST+"/product/updateAttribute",
                            data: this.attribute
                        }).then((res)=>{
                            if(res.data.code==0) {
                                if(this.attribute.id==null){
                                    this.attribute.id=res.data.data.id;
                                    this.attribute.attributeValues=[];
                                    this.tableData.push(this.attribute)
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
            handleClose(index,tagId) {
                console.log(index)
                let attributeValues=this.tableData[index].attributeValues
                for(var o in attributeValues){
                    if(tagId==attributeValues[o].id){
                        attributeValues.splice(o,1);
                    }
                }
                this.$axios.get(this.HOST+'/product/delAttributeValue',
                    {
                        params:{id: tagId}
                    }
                ).then((res) => {
                    if(res.data.code==0) {
                        this.$message.success('删除成功');
                        this.$set(this.tableData[index],"attributeValues",attributeValues)
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })
            },
            saveForm2(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.dialogField2Visible=false
                        this.$axios({
                            method:"post",
                            url: this.HOST+"/product/addAttributeValue",
                            data: this.attributeValue
                        }).then((res)=>{
                            if(res.data.code==0) {
                                this.$set(this.attributeValue,"id",res.data.data.id)
                                for(var o in this.tableData){
                                    if(this.tableData[o].id==this.attributeValue.attributeId){
                                        this.tableData[o].attributeValues.push(this.attributeValue)
                                    }
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

                this.$axios.get(this.HOST+'/product/delAttribute',
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
        },
        created:function(){
            this.getData();
        },
    }
</script>

<style scoped>
    .icon{
        width: 50px;
        height:50px;
    }
    .el-tag{
        margin: 2px  5px 2px 0;
    }
</style>
