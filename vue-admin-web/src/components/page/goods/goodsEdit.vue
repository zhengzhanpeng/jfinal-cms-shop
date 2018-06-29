<template>
    <div>
        <el-tabs value="first">
            <el-tab-pane label="基本信息" name="first">
                <el-form ref="form" :model="goods" label-width="80px">
                    <el-form-item label="商品标题"  prop="name" :rules="[
                                      { required: true, message: '商品标题不能为空', trigger: 'blur' }
                                      ]">
                        <el-input v-model="goods.name"></el-input>
                    </el-form-item>
                    <el-form-item label="副标题">
                        <el-input v-model="goods.caption"></el-input>
                    </el-form-item>
                    <el-form-item label="商品编号" prop="sn" :rules="[
                                      { required: true, message: '商品编号', trigger: 'blur' }
                                      ]">
                        <el-input v-model="goods.sn"></el-input>
                    </el-form-item>
                    <el-form-item label="商品类目" prop="productCategoryId" :rules="[
                                      { required: true, message: '请选择商品类目', trigger: 'blur' }
                                      ]">
                        <el-select v-model="goods.productCategoryId" placeholder="请选择商品类目">
                            <el-option   v-for="item in categorys"
                                         :key="item.id"
                                         :label="item.name"
                                         :value="item.id"></el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="展示图" prop="thumbnail" :rules="[
                                      { required: true, message: '请上传图片', trigger: 'blur' }
                                      ]">
                        <div class="el-flex">
                            <pictureview type="upload" :selectedImg="goods.thumbnail_temp" @selectChange="selectChange"></pictureview>
                        </div>
                    </el-form-item>
                    <el-form-item label="市场价">
                        <el-input type="number" v-model="goods.marketPrice"></el-input>
                    </el-form-item>
                    <el-form-item label="销售价" prop="price" :rules="[
                                      { required: true, message: '请输入销售价', trigger: 'blur' }
                                      ]">
                        <el-input type="number" v-model="goods.price"></el-input>
                    </el-form-item>
                    <el-form-item label="库存" prop="stock" :rules="[
                                      { required: true, message: '请输入库存', trigger: 'blur' }
                                      ]">
                        <el-input type="number" v-model="goods.stock"></el-input>
                    </el-form-item>
                    <el-form-item label="是否上架">
                        <el-switch
                            v-model="goods.isMarketable"
                            active-color="#009688"
                            inactive-color="#ff4949">
                        </el-switch>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="saveForm('form')">确定</el-button>
                        <el-button>取消</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="商品参数" name="second">
                <el-form  ref="dynamicValidateForm" label-width="100px" class="demo-dynamic">
                    <template v-for="(item,index) in parameterValue">
                        <el-form-item label="参数组">
                            <el-col :span="11"><el-input type="text" v-model="item.name"   size="small"></el-input></el-col>
                            <el-col :offset="1" :span="10"><el-button size="small" @click="removeParameterGroup(index)">删除</el-button><el-button size="small" @click="addParameter(index)">添加</el-button></el-col>
                        </el-form-item>
                        <el-form-item v-for="(parameterValueEntry,index2) in item.parameterGroup">
                            <el-col :span="5"><el-input type="text" v-model="parameterValueEntry.key"   size="small"></el-input></el-col>
                            <el-col :offset="1" :span="5"><el-input type="text" v-model="parameterValueEntry.value"   size="small"></el-input></el-col>
                            <el-col :offset="1" :span="10"><el-button size="small" @click="removeparameterValueEntry(index,index2)">删除</el-button></el-col>
                        </el-form-item>
                    </template>

                    <el-form-item>
                        <el-button type="primary" @click="saveForm('form')">保存</el-button>
                        <el-button @click="addParameterGroup">新增参数组</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>
            <el-tab-pane label="添加规格" name="third">
                <el-row class="attribute_group">
                    <el-col :span="4" class="attribute_title">选择规格项目</el-col>
                    <el-col :offset="1" :span="19">
                        <template>
                            <el-checkbox-group
                                v-model="checkedAttributesStr"
                                :min="1"
                                :max="3">
                                <el-checkbox v-for="attribute in attributesStr" :label="attribute" :key="attribute">{{attribute}}</el-checkbox>
                            </el-checkbox-group>
                        </template>
                    </el-col>
                </el-row>
                <el-row v-for="item in checkedAttributes" class="attribute_group">
                    <el-col :span="4" class="attribute_title">{{item.name}}</el-col>
                    <el-col :offset="1" :span="19">
                        <template v-for="attributeValue in item.attributeValues">
                            <el-checkbox v-model="attributeValue.checked" @change="changeChecked">{{attributeValue.value}}</el-checkbox>
                        </template>
                    </el-col>
                </el-row>
                <el-row type="flex" align="middle">
                    <el-col :span="4" style="text-align: right">
                        <div class="specifications_title">设置价格，库存</div>
                        <div class="specifications_button">
                            <el-button size="mini" type="success" @click="recovery">恢复</el-button>
                            <el-button size="mini" type="primary" @click="saveSpecification">保存</el-button>
                        </div>
                    </el-col>
                    <el-col :offset="1" :span="19">
                        <el-table
                            :data="specificationsTableData"
                            style="width: 100%" @cell-click="selectSpecification">
                            <el-table-column
                                prop="SKU"
                                label="SKU"
                                width="150">
                            </el-table-column>
                            <el-table-column
                                label="规格"
                                width="250">
                                <template slot-scope="scope">
                                    <el-tag v-for="item in scope.row.attributeValues"  size="small"> {{item.value}} </el-tag>
                                </template>
                            </el-table-column>
                            <el-table-column
                                prop="price"
                                label="价格"
                                width="100">
                                <template slot-scope="scope">
                                    <el-input type="number" min="0" v-model="scope.row.price"   size="small"></el-input>
                                </template>
                            </el-table-column>
                            <el-table-column
                                prop="stock"
                                label="库存"  width="100">
                                <template slot-scope="scope">
                                    <el-input type="number" min="0" v-model="scope.row.stock"   size="small"></el-input>
                                </template>
                            </el-table-column>
                            <el-table-column
                                label="图片"  width="150">
                                <template slot-scope="scope">
                                    <div class="el-flex specificationsImg">
                                        <pictureview type="upload" :selectedImg="scope.row.thumbnail_temp" @selectChange="handlespecificationsImgSuccess"></pictureview>
                                    </div>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-col>
                </el-row>
            </el-tab-pane>
            <el-tab-pane label="商品介绍" name="fourth">
                <tinymce class="editor" v-model="goods.introduction"  :setting="editorSetting"></tinymce>
                <el-button type="primary" @click="saveForm('form')">确定</el-button>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    import tinymce from '@/components/my-components/tinymce/tinymce';
    import pictureview from '@/components/my-components/pictureview/index';
    export default {
        data: function(){
            return {
                goods:{stock:100,isMarketable:true,introduction:""},
                categorys:[],
                editorSetting:{
                    height:400
                },
                parameterValue:[],
                attributes:[],
                attributesStr:[],
                checkedAttributes:[],
                checkedAttributesStr:[],
                checkedAttributesArr:[],
                specifications:[],
                specificationsTableData:[],
                selectedSpecification:{}
            }
        },
        methods: {
            getData(){
                this.$axios.get(this.HOST+'product/getProduct',
                    {
                        params:{"id":this.goods.id}
                    }
                ).then((res) => {
                    this.goods=res.data.data;
                    this.parameterValue=JSON.parse(res.data.data.parameterValue)
                })
            },
            getSpecification(){
                this.$axios.get(this.HOST+'product/getSpecification',
                    {
                        params:{"productId":this.goods.id}
                    }
                ).then((res) => {
                    this.specifications=res.data.data.specifications;
                    this.specificationsTableData=res.data.data.specifications;
                    this.checkedAttributes=res.data.data.checkedAttributes;
                    for(let o of this.checkedAttributes){
                        this.checkedAttributesStr.push(o.name)
                    }
                })
            },
            init:function () {
                if(this.$route.query.id!=null) {
                    this.goods.id = this.$route.query.id;
                    this.getData();
                    this.getSpecification();
                }
            },
            saveForm(formName){
                this.goods.parameterValue =JSON.stringify( this.parameterValue)
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios({
                            method:"post",
                            url: this.HOST+"/product/updateProduct",
                            data: this.goods
                        }).then((res)=>{
                            if(res.data.code==0) {
                                this.goods.id=res.data.data.id;
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
            getCategorys(){
                this.$axios.get(this.HOST+'product/getCategorys',
                    {

                    }
                ).then((res) => {
                    this.categorys=res.data.data
                })
            },
            getAttribute() {
                this.$axios.get(this.HOST+'product/getAttribute',
                    {

                    }
                ).then((res) => {
                    this.attributes=res.data.data;
                    for(let o of this.attributes){
                        this.attributesStr.push(o.name)
                    }
                })
            },
            handleImgSuccess(res, file){
                this.$set(this.goods,"thumbnail_temp",res.url)
                this.$set(this.goods,"thumbnail",res.attachment.url)
            },
            handlespecificationsImgSuccess(attachments){
                if(attachments!=null){
                    this.selectedSpecification.thumbnail_temp=attachments[0].thumbnail_temp;
                    this.selectedSpecification.thumbnail=attachments[0].url;
                    for(let o in this.selectedSpecification){
                        if(this.selectedSpecification[o].SKU== this.selectedSpecification.SKU){
                            this.selectedSpecification[o]=this.selectedSpecification;
                            break;
                        }
                    }
                    this.$set(this.selectedSpecification,0,this.selectedSpecification[0])
                }

            },
            addParameterGroup(){
                let parameterGroup={}
                parameterGroup.name="新属性组";
                parameterGroup.parameterGroup=[];
                this.parameterValue.push(parameterGroup)
            },
            removeParameterGroup(index){
                console.log("index"+index)
                this.parameterValue.splice(index,1)
            },
            addParameter(index){
                console.log("index"+index)
                let parameterValueEntry={"key":"参数名","value":"参数值"};
                for(var o in this.parameterValue){
                    if(o==index){
                        this.parameterValue[o].parameterGroup.push(parameterValueEntry);
                    }
                }
            },
            removeparameterValueEntry(index,index2){
                for(var o in this.parameterValue){
                    if(o==index){
                        this.parameterValue[o].parameterGroup.splice(index2,1);
                    }
                }
            },
            attributeChecked(item){
                for(let o of this.checkedAttributes){
                    if(o.name==item.name){
                        return true;
                    }
                }
                return false;
            },
            changeChecked(){
                this.checkedAttributes.sort(this.compare("id"));
                let checkedAttributes=this.checkedAttributes;

                let checkedAttributesArr=[];
                for(let o in checkedAttributes){
                    let  temp=[];
                    for(let attr of checkedAttributes[o].attributeValues){
                        if(attr.checked){
                            let attribute={};
                            attribute.attributeId=attr.attributeId;
                            attribute.id=attr.id;
                            attribute.value=attr.value;
                            temp.push(attribute);
                        }
                    }
                    if(temp.length>0){
                        checkedAttributesArr.push(temp)
                    }
                }
                this.checkedAttributesArr=checkedAttributesArr;
                if(checkedAttributesArr.length>0)
                    this.specificationsTableData=this.descartes(checkedAttributesArr,checkedAttributesArr.length-1)

            },
            //笛卡儿积组合
            descartes(checkedAttributesArr,index)
            {
                if(index==0){
                    let specifications=[];
                    for(let o of checkedAttributesArr[index]){
                        let attributeValues=[];
                        let specification={};
                        specification.SKU="SKU"+o.id;
                        specification.thumbnail="";
                        specification.stock="";
                        specification.price="";
                        attributeValues.push(o);
                        specification.attributeValues=attributeValues.concat();
                        specifications.push(specification)
                    }
                    return specifications;
                }else if(index>=1){
                    let specificationsTemp=this.descartes(checkedAttributesArr,index-1)
                    let specifications=[];
                    for(let o of  specificationsTemp){
                        for(let b of checkedAttributesArr[index]){
                            let specification={};
                            specification.SKU=o.SKU;
                            specification.attributeValues=o.attributeValues.concat();
                            specification.attributeValues.push(b);
                            specification.SKU=o.SKU+b.id;
                            specification.productId=this.goods.id;
                            specification.thumbnail="";
                            specification.stock="";
                            specification.price="";
                            for(let v of this.specificationsTableData){
                                if(v.SKU==specification.SKU){
                                    specification.stock=v.stock;
                                    specification.price=v.price;
                                    specification.thumbnail=v.thumbnail;
                                    specification.thumbnail_temp=v.thumbnail_temp;
                                    break;
                                }
                            }
                            specifications.push(specification);
                        }
                    }
                    return specifications;
                }else{

                }
            },
            compare(prop) {
                return function (obj1, obj2) {
                    var val1 = obj1[prop];
                    var val2 = obj2[prop];if (val1 < val2) {
                        return -1;
                    } else if (val1 > val2) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            },
            getCheckedAttributes(attributeValues){
                let temp=[];
                for(let o of attributeValues){
                    if(o.checked){
                        temp.push(o);
                    }
                }
                return temp;
            },
            selectSpecification(row, column, cell, event){
                this.selectedSpecification=row;
            },
            saveSpecification(){
                this.$confirm('此操作将会把所有更改写入到数据库, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    console.log(JSON.stringify(this.specificationsTableData))
                    this.$axios({
                        method:"post",
                        url: this.HOST+"/product/saveSpecification",
                        data: {"specifications":JSON.stringify(this.specificationsTableData)}
                    }).then((res)=>{
                        if(res.data.code==0) {
                            this.$message.success('保存成功');
                        }else{
                            this.$message.error(res.data.msg);
                        }
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消保存'
                    });
                });
            },
            selectChange(attachments){
                if(attachments!=null){
                    this.$set(this.goods,"thumbnail",attachments[0].url)
                    this.$set(this.goods,"thumbnail_temp",attachments[0].thumbnail_temp)
                }
            },
            recovery(){
                this.$confirm('此操作将会恢复到数据库数据状态, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.specificationsTableData=this.specifications;
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消恢复'
                    });
                });
            },
            findExtra(valArr,oldValArr){
                valArr.sort();
                oldValArr.sort();
                let i
                for(i=0;i<valArr.length&&i<oldValArr.length;i++){
                    if(valArr[i]!=oldValArr[i]){
                        break;
                    }
                }
                return valArr.length>oldValArr.length?valArr[i]:oldValArr[i]
            }
        },
        watch: {
            '$route' (to, from) {
                this.init();
            },
            parameterValue: {
                handler(val, oldVal){
                    this.$set(this.goods, "parameterValue", JSON.stringify(oldVal));
                },
                deep:true
            },
            checkedAttributesStr:{
                handler(val, oldVal){
                    if(val!=oldVal) {
                        var str=this.findExtra(val,oldVal);
                        if(val.length>oldVal.length){
                            for(var o of this.attributes){
                                if(o.name==str){
                                    this.checkedAttributes.push(o).sort(this.compare("id"));
                                    break;
                                }
                            }
                        }else{
                            for(var o in this.checkedAttributes){
                                if(this.checkedAttributes[o].name==str){
                                    this.checkedAttributes.splice(o,1);
                                    break;
                                }
                            }
                        }
                    }
                    return true;
                },
                deep:true
            }
        },
        computed:{
        },
        created:function(){
            this.init();
            this.getCategorys();
            this.getAttribute();
        },
        components:{
            'tinymce':tinymce,
            'pictureview':pictureview
        }
    }
</script>

<style>
    .attribute_group{
        height: 30px;
    }
    .attribute_group .attribute_title{
        text-align: right;
    }
    .el-tag{
        margin-right: 5px;
    }
    .specificationsImg  .el-upload,.specificationsImg  .el-upload .avatar-uploader-icon{
        width: 80px;
        height:80px;
        line-height:80px;
    }
    .specifications_title{
        height: 30px;
    }
</style>
