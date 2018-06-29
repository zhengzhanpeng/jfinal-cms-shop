<template>
    <div class="">
        <el-form ref="form" :model="content" label-width="80px">
            <template v-for="(item) in fields">
                <template v-if="item.field_en=='thumbnail'">
                    <el-form-item :label="item.field_zh" :prop="item.field_en"  :rules="item.lanmu_option==2?[
                                      { required: true, message: '内容不能为空', trigger: 'blur' }
                                      ]:[]">
                        <el-upload
                            class="avatar-uploader"
                            action="uploadPath"
                            accept="image/*"
                            :show-file-list="false"
                            :on-success="handleThumbnailSuccess">
                            <img v-if="content.thumbnail" :src="content.thumbnail_temp" class="avatar">
                            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </el-form-item>
                </template>
                <template v-else-if="item.field_en=='picture'">
                    <el-form-item :label="item.field_zh" :prop="item.field_en"  :rules="item.lanmu_option==2?[
                                      { required: true, message: '内容不能为空', trigger: 'blur' }
                                      ]:[]">
                        <el-upload
                            action="uploadPath"
                            list-type="picture-card"
                            :on-success="handlePictureSuccess"
                            :on-remove="handlePictureRemove"
                            accept="image/*"
                            :file-list="content.pictures"
                        >
                            <i class="el-icon-plus"></i>
                        </el-upload>
                    </el-form-item>
                </template>
                <template v-else-if="item.field_en=='content'">
                    <el-form-item :label="item.field_zh" :prop="item.field_en"  :rules="item.lanmu_option==2?[
                                      { required: true, message: '内容不能为空', trigger: 'blur' }
                                      ]:[]">
                        <tinymce class="editor" v-model="content.content"  :setting="editorSetting"></tinymce>
                    </el-form-item>
                </template>
                <template v-else-if="item.field_en=='out_link'">
                    <el-form-item :label="item.field_zh" :prop="item.field_en"  :rules="item.lanmu_option==2?[
                                      { required: true, message: '内容不能为空', trigger: 'blur' },
                                       { type: 'url', message: '链接不正确',trigger: 'blur'}
                                      ]:[]">
                        <el-input :value="ObjectGetByKey(item.field_en)" @change="value => { ObjectSetByKey(item.field_en,value)}"></el-input>
                    </el-form-item>
                </template>
                <template v-else-if="item.field_type=='varchar'">
                    <el-form-item :label="item.field_zh" :prop="item.field_en"  :rules="item.lanmu_option==2?[
                                      { required: true, message: '内容不能为空', trigger: 'blur' },
                                      ]:[]">
                        <el-input :value="ObjectGetByKey(item.field_en)" @change="value => { ObjectSetByKey(item.field_en,value)}"></el-input>
                    </el-form-item>
                </template>
            </template>
            <el-form-item>
                <el-button type="primary" @click="saveForm('form')">提交</el-button>
                <el-button>取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import tinymce from '@/components/my-components/tinymce/tinymce';
    export default {
        data() {
            return {
                lanmu:{},
                content:{
                    content:'',
                    pictures:[],
                },
                //tinymce的配置信息 参考官方文档 https://www.tinymce.com/docs/configure/integration-and-setup/
                editorSetting:{
                    height:400
                },
                fields:[]
            }
        },
        methods: {
            init:function () {
                this.lanmu.id=this.$route.query.lanmuId;
                if(this.$route.query.contentId!=null)
                    this.content.id=this.$route.query.contentId;
                this.getData();
                this.getField();
            },
            getData() {
                this.$axios.get(this.HOST+'/getOneContent',
                    {
                        params:{"id": this.content.id,"lanmuId":this.lanmu.id}
                    }
                ).then((res) => {
                    this.lanmu=res.data.data.lanmu;
                    if(res.data.data.content!=null)
                        this.content = res.data.data.content;
                })
            },
            getField(){
                if(this.lanmu.id!=null){
                    this.$axios.get(
                        this.HOST+"/getFieldsByLmIDEditor",
                        {params:{lanmuId:this.lanmu.id}},
                    ).then((res) => {
                        this.fields=res.data.data
                    })
                }
            },
            handleThumbnailSuccess(res, file) {
                this.$set(this.content,"thumbnail_temp",res.url)
                this.$set(this.content,"thumbnail",res.attachment.url)
            },
            handlePictureSuccess(res, file) {
                this.content.pictures.push(res.attachment)
            },
            handlePictureRemove(file, fileList) {
                console.log(file, fileList);
                if(this.content.id!=null) {
                    this.$axios.get(
                        this.HOST + "/delAttachment",
                        {params: {contentId: this.content.id,attachmentId:file.id}},
                    ).then((res) => {
                        if(res.data.code==0) {
                            this.$message.success('删除图片成功');
                        }else{
                            this.$message.error(res.data.msg);
                        }
                    })
                }
            },
            saveForm(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios({
                            method:"post",
                            url: this.HOST+"/updateContent?lanmuId="+this.lanmu.id,
                            data: this.content
                        }).then((res)=>{
                            if(res.data.code==0) {
                                this.content.id=res.data.content.id;
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

            ObjectGetByKey(key){
                let content=this.content;
                return content[key]
            },
            ObjectSetByKey(key,val){
                this.$set(this.content,key,val);
            }

        },
        created:function(){
            this.init();
        },
        watch: {
            '$route' (to, from) {
                this.init();
            }
        },
        computed: {

        },
        components:{
            'tinymce':tinymce
        }
    }

</script>

<style>

</style>

