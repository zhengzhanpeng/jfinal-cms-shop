<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>基本信息 <i class="fa fa-angle-double-right"></i></el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-row type="flex">
            <el-form ref="form" :model="promotion" label-width="80px">
                <el-form-item label="活动名称" prop="name" :rules="[
      { required: true, message: '请输入活动名称', trigger: 'blur' }]">
                    <el-input v-model="promotion.name"></el-input>
                </el-form-item>
                <el-form-item label="活动时间" prop="datetimerange" :rules="rules.datetimerange">
                    <el-date-picker
                        type="datetimerange"
                        value-format="yyyy-MM-dd  HH:mm:ss"
                        :default-time="['00:00:00', '23:59:59']"
                        v-model="promotion.datetimerange"
                        range-separator="至"
                        start-placeholder="开始日期"
                        end-placeholder="结束日期"
                        align="left" @change="changeDate">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="活动标签" prop="tag" :rules="[
                { required: true, message: '请输入活动标签', trigger: 'blur' }]">
                    <el-input v-model="promotion.tag"></el-input>
                </el-form-item>
                <el-form-item label="促销类型" prop="tag" :rules="[
                { required: true, message: '请选择促销类型', trigger: 'blur' }]">
                    <el-select v-model="promotion.type" placeholder="请选择" @change="saveForm('form')">
                        <el-option label="打折促销" value="0">
                        </el-option>
                        <el-option label="满减优惠" value="1">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="saveForm('form')">保存</el-button>
                </el-form-item>
            </el-form>
        </el-row>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item>选择商品 <i class="fa fa-angle-double-right"></i></el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-tabs value="first">
            <el-tab-pane label="第一步：选择商品" name="first">
                <el-table
                    :data="tableData1"
                    ref="tableData1"
                    @selection-change="handleSelectionChange"
                    style="width: 100%">
                    <el-table-column
                        type="selection"
                        width="55">
                    </el-table-column>
                    <el-table-column prop="id" label="id" width="100">
                    </el-table-column>
                    <el-table-column prop="thumbnail" class-name="thumbnail" label="图片" width="60">
                        <template slot-scope="scope">
                            <img  :src="scope.row.thumbnail_temp">
                        </template>
                    </el-table-column>
                    <el-table-column prop="name" label="商品名称" width="400">
                    </el-table-column>
                    <el-table-column prop="price" label="价格" width="150">
                    </el-table-column>
                    <el-table-column prop="stock" label="库存" width="150">
                    </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane v-if="promotion.type==0" label="第二步：设置折扣" name="second">
                <div class="handle-box">
                    <el-row  align="middle" class="batch_set_input">
                        <el-col  :xs="12" :sm="6" :md="3" >
                            <a href="" href="javascript:;" @click="handBatchDiscount"> 批量打折 </a>
                            <a href="" href="javascript:;" @click="handBatchPrice"> 批量减价 </a>
                        </el-col>
                        <el-col  :xs="12" :sm="12" :md="8" v-if="batchDiscount">
                            <el-input  v-model="discount" placeholder="请输入折扣" size="mini"></el-input>
                            <a href="" href="javascript:;" @click="handOkBatchDiscount"> 确定 </a>
                            <a href="" href="javascript:;" @click="handBatchCancel"> 取消 </a>
                        </el-col>
                        <el-col  :xs="12" :sm="12" :md="8" v-if="batchPrice">
                            减 <el-input v-model="reducePrice"  placeholder="请输入价格" size="mini"></el-input>
                            <a href="" href="javascript:;" @click="handOkBatchPrice"> 确定 </a>
                            <a href="" href="javascript:;" @click="handBatchCancel"> 取消 </a>
                        </el-col>
                    </el-row>
                </div>
                <el-table
                    :data="multipleSelection"
                    @cell-click="selectProductPromotion"
                    style="width: 100%">
                    <el-table-column prop="id" label="id" width="60">
                    </el-table-column>
                    <el-table-column prop="thumbnail" class-name="thumbnail" label="图片" width="100">
                        <template slot-scope="scope">
                            <img  :src="scope.row.thumbnail_temp">
                        </template>
                    </el-table-column>
                    <el-table-column prop="name" label="商品名称" width="400">
                    </el-table-column>
                    <el-table-column prop="price" label="价格" width="100">
                    </el-table-column>
                    <el-table-column prop="discount" label="打折" width="120">
                        <template slot-scope="scope">
                            <el-input v-model.number="scope.row.discount"  @change="discountChange"  :min="0" :max="10" size="mini" placeholder="">
                                <template slot="prepend">打折</template>
                            </el-input>
                        </template>
                    </el-table-column>
                    <el-table-column prop="reduceMoney" label="减价" width="120">
                        <template slot-scope="scope">
                            <el-input v-model.number="scope.row.reduceMoney" @change="reduceMoneyChange"  :min="0" :max="scope.row.price" size="mini" placeholder="">
                                <template slot="prepend">减价</template>
                            </el-input>
                        </template>
                    </el-table-column>
                    <el-table-column prop="lastPrice" label="减价后" width="120">
                        <template slot-scope="scope">
                            减价后{{scope.row.lastPrice}}
                        </template>
                    </el-table-column>
                    <el-table-column fixed="right" label="操作" width="120">
                        <template slot-scope="scope">
                            <el-button type="text" size="small"
                                       @click.native.prevent="delProductPromotion(scope.$index,scope.row)">
                                取消
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-row type="flex" justify="center"><el-button type="primary" size="mini" @click="saveDiscountPromotion">提交</el-button></el-row>
            </el-tab-pane>
            <el-tab-pane v-if="promotion.type==1" label="第二步：设置满减" name="second">
                <div class="handle-box" style="height: 50px">
                    <el-row  align="middle" class="batch_set_input">
                        <el-form ref="form2" :model="promotion" label-width="100px" :inline="true" size="mini">
                            <el-form-item label="消费金额:" prop="lastSpendMoney" :rules="[
                { required: true, message: '请输入消费金额', trigger: 'blur' }]">
                                <el-input min="1" type="number" v-model.number="promotion.lastSpendMoney"></el-input>
                            </el-form-item>
                            <el-form-item prop="reduceMoney" label="减价金额:" :rules="rules.reduceMoney">
                                <el-input min="1" type="number" v-model.number="promotion.reduceMoney"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="text" size="mini" @click="saveForm('form2')">确定</el-button>
                            </el-form-item>
                        </el-form>
                    </el-row>
                </div>
                <el-table
                    :data="multipleSelection"
                    @cell-click="selectProductPromotion"
                    style="width: 100%">
                    <el-table-column prop="id" label="id" width="60">
                    </el-table-column>
                    <el-table-column prop="thumbnail" class-name="thumbnail" label="图片" width="100">
                        <template slot-scope="scope">
                            <img  :src="scope.row.thumbnail_temp">
                        </template>
                    </el-table-column>
                    <el-table-column prop="name" label="商品名称" width="400">
                    </el-table-column>
                    <el-table-column prop="price" label="价格" width="100">
                    </el-table-column>
                    <el-table-column fixed="right" label="操作" width="120">
                        <template slot-scope="scope">
                            <el-button type="text" size="small"
                                       @click.native.prevent="delProductPromotion(scope.$index,scope.row)">
                                取消
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <el-row type="flex" justify="center"><el-button type="primary" size="mini" @click="saveReducePromotion">提交</el-button></el-row>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>

<script>
    export default {
        data() {
            var validatedatetimerange = (rule, date, callback) => {
                if(Date.parse(date[0])<new Date()||Date.parse(date[1])<new Date()){
                    callback(new Error('其实日期不能小于当前时间!'));
                }else{
                    callback();
                }
            };
            var validateReduceMoney = (rule, date, callback) => {
                if(this.promotion.type==1&&this.promotion.reduceMoney>this.promotion.lastSpendMoney){
                    callback(new Error('减价金额必须小于消费金额!'));
                }else{
                    callback();
                }
            };
            return {
                lanmu:{},
                promotion:{},
                tableData1:[],
                tableData2:[],
                goodsList:[],
                multipleSelection:[],
                selectedProductPromotion:{},
                batchDiscount:false,
                batchPrice:false,
                reducePrice:"",
                discount:"",
                rules: {
                    datetimerange: [
                        { required: true, message: '请选择活动时间', trigger: 'blur' },
                        { validator: validatedatetimerange, trigger: 'blur' }
                    ],
                    reduceMoney: [
                        { required: true, message: '请输入减价金额', trigger: 'blur' },
                        { validator: validateReduceMoney, trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            init() {
                this.lanmu.id=this.$route.query.lanmuId;
                if(this.$route.query.id!=null){
                    this.promotion.id=this.$route.query.id
                    this.getData()
                    this.getProductNoPromotionList()
                    this.getProductPromotionList()
                }
            },
            getData(){
                this.$axios.get(this.HOST+'/product/getPromotion',
                    {
                        params:{"lanmuId":this.lanmu.id,"id":this.promotion.id}
                    }
                ).then((res) => {
                    this.promotion=res.data.data;
                    let datetimerange=[]
                    datetimerange.push(this.promotion.startDate)
                    datetimerange.push(this.promotion.endDate)
                    this.promotion.type=this.promotion.type+''
                    this.$set(this.promotion,"datetimerange",datetimerange)
                })
            },
            getProductNoPromotionList(){
                this.$axios.get(this.HOST+'/product/getProductNoPromotionList',
                    {
                        params:{"lanmuId":this.lanmu.id,"promotionId":this.promotion.id}
                    }
                ).then((res) => {
                    this.tableData1=res.data.data;
                })
            },
            getProductPromotionList(){
                this.$axios.get(this.HOST+'/product/getProductPromotionList',
                    {
                        params:{"lanmuId":this.lanmu.id,"promotionId":this.promotion.id}
                    }
                ).then((res) => {
                    this.multipleSelection=res.data.data;
                })
            },
            changeDate(date){
                console.log(date);
                if(Date.parse(date[0])<new Date()||Date.parse(date[1])<new Date()){

                }else{
                    this.promotion.startDate=date[0];
                    this.promotion.endDate=date[1];
                }
            },
            handleSelectionChange(val) {
                this.multipleSelection =this.multipleSelection.concat(val);
            },
            discountChange(val){
                let row=this.selectedProductPromotion
                let multipleSelection=this.multipleSelection
                if(val>0&&val<10){
                    row.reduceMoney=this.toDecimal2(row.price*(10-row.discount)/10)
                    row.lastPrice=this.toDecimal2(row.price-row.reduceMoney)
                    multipleSelection.forEach(_row => {
                        if(row.id==_row.id)
                        {
                            _row=row
                        }
                    })
                    this.$set(this.multipleSelection,0,multipleSelection[0])
                }else{
                    row.discount=""
                    this.$message({
                        message: '折扣范围在0—10',
                        type: 'warning'
                    });
                }

            },
            reduceMoneyChange(val){
                let row=this.selectedProductPromotion
                let multipleSelection=this.multipleSelection
                if(val>0&&val<row.price){
                    row.discount=10-this.toDecimal2(10*row.reduceMoney/row.price)
                    row.lastPrice=this.toDecimal2(row.price-row.reduceMoney)
                    multipleSelection.forEach(_row => {
                        if(row.id==_row.id)
                        {
                            _row=row
                        }
                    })
                    this.$set(this.multipleSelection,0,multipleSelection[0])
                }else{
                    row.reduceMoney=""
                    this.$message({
                        message: '折扣范围在0—'+row.price,
                        type: 'warning'
                    });
                }
            },
            handBatchDiscount(){
                this.batchDiscount=true
                this.batchPrice=false
            },
            handBatchPrice(){
                this.batchDiscount=false
                this.batchPrice=true
            },
            handOkBatchDiscount(){
                let val=this.discount
                if(val>0&&val<10){
                    let multipleSelection=this.multipleSelection
                    multipleSelection.forEach(row => {
                        row.discount=val
                        row.reduceMoney=this.toDecimal2(row.price*(10-val)/10)
                        row.lastPrice=this.toDecimal2(row.price-row.reduceMoney)
                    });
                    this.$set(this.multipleSelection,0,multipleSelection[0])
                }else{
                    this.discount=""
                    this.$message({
                        message: '折扣范围在0—10',
                        type: 'warning'
                    });
                }
            },
            handOkBatchPrice(){
                let val=this.reducePrice
                let multipleSelection=this.multipleSelection
                multipleSelection.forEach(row => {
                    if(val>0&&val<row.price) {
                        row.discount = 10-this.toDecimal2(10 * val / row.price)
                        row.reduceMoney = val
                        row.lastPrice = this.toDecimal2(row.price - row.reduceMoney)
                    }
                });
                this.$set(this.multipleSelection,0,multipleSelection[0])
            },
            handBatchPrice(){
                this.batchDiscount=false
                this.batchPrice=true
            },
            handBatchCancel(){
                this.batchDiscount=false
                this.batchPrice=false
            },
            selectProductPromotion(row, column, cell, event){
                this.selectedProductPromotion=row
            },
            delProductPromotion(index,row){
                let flag=false
                this.tableData1.forEach(_row => {
                    if(row.id==_row.id) {
                        this.$refs.tableData1.toggleRowSelection(_row)
                        flag = true
                    }
                    this.multipleSelection.splice(index,1)
                });
                if(flag==false){
                    this.multipleSelection.splice(index,1)
                    this.tableData1.push(row)
                    this.$axios.get(this.HOST+'/product/delDiscountPromotion',
                        {
                            params:{"lanmuId":this.lanmu.id,"promotionId":this.promotion.id,"productId":row.productId}
                        }
                    ).then((res) => {
                        if(res.data.code==0) {
                            this.$message.success('取消成功');
                        }else{
                            this.$message.error(res.data.msg);
                        }
                    })
                }
            },
            saveDiscountPromotion(){
                this.$axios({
                    method:"post",
                    url: this.HOST+"/product/saveDiscountPromotion",
                    data:{data:JSON.stringify(this.multipleSelection),
                        promotionId:this.promotion.id
                    }
                }).then((res)=>{
                    if(res.data.code==0) {
                        this.$message.success('保存成功');
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })
            },
            saveReducePromotion(){
                this.$axios({
                    method:"post",
                    url: this.HOST+"/product/saveReducePromotion",
                    data:{data:JSON.stringify(this.multipleSelection),
                        promotionId:this.promotion.id
                    }
                }).then((res)=>{
                    if(res.data.code==0) {
                        this.$message.success('保存成功');
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })
            },
            saveForm(formName){
                console.log('submit!!');
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.$axios({
                            method:"post",
                            url: this.HOST+"/product/savePromotion",
                            data: this.promotion
                        }).then((res)=>{
                            if(res.data.code==0) {
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
            toDecimal2(x) {
                var f = parseFloat(x);
                if (isNaN(f)) {
                    return false;
                }
                var f = Math.round(x*100)/100;
                var s = f.toString();
                var rs = s.indexOf('.');
                if (rs < 0) {
                    rs = s.length;
                    s += '.';
                }
                while (s.length <= rs + 2) {
                    s += '0';
                }
                return s;
            }

        },
        created:function(){
            this.init();
        },
        watch:{
            datetimerange:{
                handler(val, oldVal){
                    if(Date.parse(val[0])<new Date()||Date.parse(val[1])<new Date()){
                        this.promotion.startDate=val[0];
                        this.promotion.endDate=val[1];
                    }
                    return true;
                },
                deep:true
            }
        },
        computed: {

        }
    }

</script>

<style>
    .thumbnail img{
        min-height:50px;
        width: 50px;
    }
    .el-input-group__append, .el-input-group__prepend{
        padding: 0 5px;
    }
    .el-input-group--prepend .el-input__inner, .el-input-group__append{
        padding: 0 5px;
    }
    .batch_set_input{

    }
    .batch_set_input .el-input{
        width: 100px;
    }
    .batch_set_input{
        height: 28px;
        margin-left: 10px;
        line-height: 28px;
        font-size: 14px;
    }
</style>

