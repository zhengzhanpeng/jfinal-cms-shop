<template>


    <div class="">
        <div  v-if="type=='preview'">
        <div class="el-flex">
            <div class="el-flex-item">
                <el-upload
                    class="avatar-uploader"
                    action="uploadPath"
                    :show-file-list="false"
                    :on-success="handleUploadSuccess">
                    <i  class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </div>
            <template v-for="(item,index) in attachmentList">
                <div class="el-flex-item" :class="item.checked?'on':''">
                    <el-checkbox v-model="item.checked"></el-checkbox>
                    <img @click="selectImg(index,item.id)" :src="item.thumbnail_temp">
                    <i class="el-icon-close" @click="delAttachment(index,item)"></i>
                </div>
            </template>
        </div>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="1"
                :page-sizes="[10, 20, 30, 40]"
                :page-size="100"
                layout="total, sizes, prev, pager, next, jumper"
                :total="dataPage.totalRow">
            </el-pagination>
        </div>
        <div v-if="type=='upload'">
            <div class="handle-box el-upload-item">
                <i v-if="selectedImg==null||selectedImg==''" class="el-icon-plus" @click="dialogVisible = true"></i>
                <img v-else :src="selectedImg"  @click="dialogVisible = true">
            </div>
            <el-dialog
                title="图片预览"
                :visible.sync="dialogVisible"
                width="85%"
                height="80%"
                top="10vh"
                :append-to-body="true"
                custom-class="dialog-picture"
                :lock-scroll="true">
                <div class="el-flex">
                    <div class="el-flex-item">
                        <el-upload
                            class="avatar-uploader"
                            action="uploadPath"
                            :show-file-list="false"
                            :on-success="handleUploadSuccess">
                            <i  class="el-icon-plus avatar-uploader-icon"></i>
                        </el-upload>
                    </div>
                    <template v-for="(item,index) in attachmentList">
                        <div class="el-flex-item" :class="item.checked?'on':''">
                            <el-checkbox v-model="item.checked"></el-checkbox>
                            <img @click="selectImg(index,item.id)" :src="item.thumbnail_temp">
                            <i class="el-icon-close" @click="delAttachment(index,item)"></i>
                        </div>
                    </template>
                </div>
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="1"
                    :page-sizes="[20, 30, 40]"
                    :page-size="100"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="dataPage.totalRow">
                </el-pagination>
                <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="selectChange">确 定</el-button>
  </span>
            </el-dialog>
        </div>

    </div>
</template>

<script>
    export default {
        props: [
            "type",
            "selectedImg"
        ],
        directives: {

        },
        data() {
            return {
                dialogVisible:false,
                attachmentList:[],
                dataPage:{
                    pageNumber:1,
                    pageSize:20
                },
            }
        },
        methods:{
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
            },
            getAttachmentList(pageNum,pageSize){
                if(pageNum==null)
                    pageNum=this.dataPage.pageNumber
                if(pageSize==null)
                    pageSize=this.dataPage.pageSize
                this.$axios.get(this.HOST+'/file/getAttachmentList',
                    {
                        params:{"pageNum": pageNum,"pageSize":pageSize}
                    }
                ).then((res) => {
                    this.dataPage=res.data.data;
                    this.attachmentList=res.data.data.list;
                })
            },
            delAttachment(index,item){
                this.$axios.get(this.HOST+'/file/delAttachment',
                    {
                        params:{"id":item.id}
                    }
                ).then((res) => {
                    if(res.data.code){
                        this.attachmentList.splice(index,1);
                        this.$message.success('刪除成功');
                    }else{
                        this.$message.success('禁止刪除');
                    }
                })
            },
            selectImg(index,item){
                this.$set(this.attachmentList[index],"checked",!this.attachmentList[index].checked)
            },
            selectChange(){
                this.dialogVisible=false
                let data=[];
                this.attachmentList.forEach(row=>{
                    if(row.checked){
                        data.push(row)
                    }
                })
                this.dialogVisible=false
                const self=this;
                if(data!=null&&data.length>0)
                self.$emit('selectChange', data);
            },
            handleUploadSuccess(res, file){
                let attachment=res.attachment
                attachment.thumbnail_temp=res.url
                this.attachmentList.unshift(attachment)
            },
            handleCurrentChange(pageNum){
                this.$set(this.dataPage,"pageNumber",pageNum)
                this.getAttachmentList(this.dataPage.pageNumber,this.dataPage.pageSize)
            },
            handleSizeChange(pageSize){
                this.$set(this.dataPage,"pageSize",pageSize)
                this.getAttachmentList(this.dataPage.pageNumber,this.dataPage.pageSize)
            }
        },
        watch: {
        },
        mounted() {

        },
        created(){
            this.getAttachmentList()
        }
    }

</script>
<style scoped>
    .el-dialog{
        position: absolute;
        top :50%;
        left: 50%;
        margin:0!important;
        transform:translate(-50%, -50%);
        max-height: calc(100% - 30px);
        max-width:calc(100% - 30px);
        display :flex;
        flex-direction:column;
    }
    .el-dialog>.el-dialog__body{
        overflow :auto;
    }
    .handle-box{
        width: 60px;
        height:60px;
        text-align: center;
        border: 1px dashed  #009688;
        border-radius: 2px;
    }
    .el-upload-item img{
        width: 60px;
        height:60px;
    }
    .handle-box .el-icon-plus{
        margin-top: 15px;
        color: #8c939d;
        cursor: pointer;
        font-size: 30px;
    }
    .el-flex{

        flex-flow: row wrap;
        align-content: flex-start;
    }
    .el-flex-item{
        height: 120px;
        flex:0 0 120px;
        padding: 5px 10px;
        position: relative;
        box-sizing: border-box;
    }
    .el-flex-item img{
        width: 100%;
        height: 100%;
        cursor: pointer;
    }
    .el-flex-item .el-icon-close{
        position: absolute;
        top:8px;
        right: 13px;
        font-size: 20px;
        color: #F56C6C;
        font-weight: 600;
        cursor: pointer;
    }
    .el-flex-item .el-icon-close:hover{
        color: #d13017;
    }
    .el-flex-item.on img, .el-flex-item img:hover{
        border: 2px solid #009688;
    }
    .el-flex-item .el-checkbox{
        position: absolute;
        left: 10px;
        top:5px;
    }
</style>
<style>
    .avatar-uploader,.avatar-uploader .el-upload, .avatar-uploader-icon{
        width: 110px;
        height:110px;
        line-height:110px;
    }

</style>
