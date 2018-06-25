<template>
    <div class="">
        <el-tabs v-model="activeName">
            <el-tab-pane label="基本信息" name="first">
                <el-form  :model="lanmu" label-width="80px" ref="lanmu" >
                    <el-form-item label="栏目名称" prop="name"
                                  :rules="[
                                      { required: true, message: '请输入栏目名称', trigger: 'blur' }
                                      ]">
                        <el-input v-model="lanmu.name" ></el-input>
                    </el-form-item>
                    <el-form-item label="栏目类型" prop="lanmu_type" :rules="[
                                      { required: true, message: '请选择栏目类型', trigger: 'blur' }
                                      ]">
                        <el-select v-model="lanmu.lanmu_type"  placeholder="请选择栏目类型" @change="handleSelectlanmuType">
                            <el-option v-if="lanmu.up_levelId==0" value="0" label="栏目组"></el-option>
                            <el-option value="1" label="子栏目"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="内容类型" prop="is_single" :rules="[
                                      { required: true, message: '请输入内容名称', trigger: 'blur' }
                                      ]">
                        <el-select v-model="lanmu.is_single" placeholder="请选择内容类型">
                            <el-option v-if="lanmu.lanmu_type==1" label="专题内容" value="0"></el-option>
                            <el-option v-if="lanmu.lanmu_type==1" label="单网页" value="1"></el-option>
                            <el-option label="空白" value="2"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="栏目简介">
                        <el-input v-model="lanmu.description"></el-input>
                    </el-form-item>
                    <el-form-item label="路由" prop="route"
                                  :rules="[
                                      { required: true, message: '请输入路由', trigger: 'blur' }
                                      ]">
                        <el-input v-model="lanmu.route" ></el-input>
                    </el-form-item>
                    <el-form-item label="图标">
                        <el-input v-model="lanmu.font_icon"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="saveLanmu('lanmu')">提交</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="字段设置" name="second">
           <!--     <div class="handle-box">
                    <Blockquote>
                        <el-button type="primary" icon="el-icon-circle-plus-outline">添加字段</el-button>
                        <el-button type="primary" icon="el-icon-d-caret">排序</el-button>
                    </Blockquote>
                </div>-->
                <div class="container">
                    <el-table :data="fields" border style="width: 100%" ref="multipleTable" :default-sort="{prop:'sort_num'}">
                        <el-table-column prop="sort_num" width="80" label="排序" sortable>
                            <template slot-scope="scope">
                                <el-input @change="handleSort(scope.$index,scope.row)" class="input-sort" v-model="scope.row.sort_num"  placeholder=""></el-input>
                            </template>
                        </el-table-column>
                        <el-table-column prop="id" label="字段Id"  width="70">
                        </el-table-column>
                        <el-table-column prop="field_zh"  label="中文名" width="150">
                        </el-table-column>
                        <el-table-column prop="field_en" label="英文名" width="150">
                        </el-table-column>
                        <el-table-column prop="field_type" label="类型">
                        </el-table-column>
                        <el-table-column prop="filed_length" label="长度">
                        </el-table-column>
                        <el-table-column prop="lanmu_option" label="状态">
                            <template slot-scope="scope">
                                {{scope.row.lanmu_option==0?"禁用":(scope.row.lanmu_option==1?"选填":"必填")}}
                            </template>
                        </el-table-column>
                        <el-table-column prop="field_show" label="是否显示">
                            <template slot-scope="scope">
                                {{scope.row.field_show==1?"显示":"不显示"}}
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="250">
                            <template slot-scope="scope">
                                <a href="javascript:;" @click="handleEdit(scope.$index, scope.row)"> <i class="el-icon-edit-outline"></i> 编辑</a>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </el-tab-pane>
        </el-tabs>
        <el-dialog title="字段设置" :visible.sync="dialogFieldVisible" width="500px">
            <el-form :model="field" label-width="80px" ref="field">
                <el-form-item label="字段名" prop="field_zh"  :rules="[
                                      { required: true, message: '请输入字段名称', trigger: 'blur' }
                                      ]">
                    <el-input v-model="field.field_zh"></el-input>
                </el-form-item>
                <el-form-item label="英文名"  prop="field_en"  :rules="[
                                      { required: true, message: '请输入字段英文名称', trigger: 'blur' }
                                      ]">
                    <el-input v-model="field.field_en" readonly></el-input>
                </el-form-item>
                <el-form-item label="字段选项">
                    <el-radio-group v-model="field.lanmu_option">
                        <el-radio :label="0">禁用</el-radio>
                        <el-radio :label="1">选题</el-radio>
                        <el-radio :label="2">必填</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="是否显示">
                    <el-radio-group v-model="field.field_show">
                        <el-radio :label="0">不显示</el-radio>
                        <el-radio :label="1">显示</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFieldVisible = false">取 消</el-button>
                <el-button type="primary" @click="saveField('field')">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                activeName:"first",
                lanmu:{},
                fields:[],
                field:{},
                dialogFieldVisible:false
            }
        },
        methods: {
            init:function () {
                let lanmu=this.lanmu;
                lanmu.id=this.$route.query.lanmuId;
                lanmu.up_levelId=this.$route.query.up_levelId;
                if(this.lanmu.id!=null){
                    this.$axios.get(
                        this.HOST+"/admin/getLanmu",
                        {params:{lanmuId:lanmu.id}},
                    ).then((res) => {
                        lanmu =res.data.data;
                        lanmu.is_single=lanmu.is_single+'';
                        lanmu.lanmu_type=lanmu.lanmu_type+'';
                        this.lanmu=lanmu;
                        this.getField();
                    })
                }else{
                    this.$set(this.lanmu,'up_levelId',lanmu.up_levelId);
                    this.$set(this.lanmu,'is_single',"2");
                }
            },
            saveLanmu(formName) {
                this.$refs.lanmu.validate((valid) => {
                    if (valid) {
                        this.$axios({
                            method:"post",
                            url: this.HOST+"/admin/saveLanmu",
                            headers:{
                                'Content-type': 'application/x-www-form-urlencoded'
                            },
                            data: this.lanmu
                        }).then((res)=>{
                            if(res.data.code==0) {
                                this.lanmu.id=res.data.data.id
                                this.$message.success('保存成功');
                                this.getField();
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
            handleSelectlanmuType(value){
                if(value==0){
                    this.$set(this.lanmu,'is_single','2')
                }
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
            handleEdit(index, row) {
                this.field=row
                this.dialogFieldVisible=true
            },
            saveField(){
                this.$refs.field.validate((valid) => {
                    if (valid) {
                        this.dialogFieldVisible=false;
                        this.$axios({
                            method:"post",
                            url: this.HOST+"/admin/updateField",
                            data: this.field
                        }).then((res)=>{
                            if(res.data.code==0) {
                                this.$message.success('保存成功');
                            }else{
                                this.$message.error(res.data.msg);
                            }
                        })
                    }else{
                        console.log('error submit!!');
                        return false;
                    }
                })
            },
            handleSort(index,row){
                this.$axios({
                    method:"post",
                    url: this.HOST+"/admin/updateField",
                    data: row
                }).then((res)=>{
                    if(res.data.code==0) {
                        this.$message.success('保存成功');
                        this.$set(this.fields,index,row)
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })
            }
        },
        mounted:function(){
            this.init();
        },
        computed: {

        }
    }

</script>

<style>

</style>

