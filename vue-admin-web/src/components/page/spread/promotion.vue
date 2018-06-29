<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="fa fa-users"></i> 推广管理</el-breadcrumb-item>
                <el-breadcrumb-item>{{$route.meta.title}}</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <el-tabs value="first">
            <el-tab-pane label="限时折扣" name="first">
                <Blockquote>
                    <el-button type="primary" icon="el-icon-circle-plus-outline" @click="editPromotion">新建限时折扣</el-button>
                </Blockquote>
                <el-table
                    :data="tableData1"
                    style="width: 100%">
                    <el-table-column prop="id" label="id" width="100">
                    </el-table-column>
                    <el-table-column prop="name" label="活动名称" width="180">
                    </el-table-column>
                    <el-table-column prop="tag" label="活动标签" width="180">
                    </el-table-column>
                    <el-table-column prop="type" label="促销类型" width="180">
                        <template slot-scope="scope">
                            {{scope.row.type==0?"打折促销":"满减优惠"}}
                        </template>
                    </el-table-column>
                    <el-table-column prop="startDate" label="开始时间" width="150">
                    </el-table-column>
                    <el-table-column prop="endDate" label="结束时间" width="150">
                    </el-table-column>
                    <el-table-column fixed="right" label="操作" width="120">
                        <template slot-scope="scope">
                            <el-button type="text" size="small"
                                       @click.native.prevent="editPromotion(scope.$index,scope.row)">
                                编辑
                            </el-button>
                            <el-button type="text" size="small"
                                       @click.native.prevent="delPromotion(scope.$index,scope.row)">
                                删除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="优惠券" name="second">
                <Blockquote>
                    <el-button type="primary" icon="el-icon-circle-plus-outline" @click="editCoupon">
                        新建优惠券
                    </el-button>
                </Blockquote>
                <el-table
                    :data="tableData2"
                    style="width: 100%">
                    <el-table-column prop="id" label="id" width="100">
                    </el-table-column>
                    <el-table-column prop="name" label="优惠券名称" width="180">
                    </el-table-column>
                    <el-table-column prop="money" label="面值" width="180">
                    </el-table-column>
                    <el-table-column prop="leastSpendMoney" label="使用条件" width="180">
                        <template slot-scope="scope">
                            满{{scope.row.leastSpendMoney}}元
                        </template>
                    </el-table-column>
                    <el-table-column prop="period" label="有效期" width="180">
                        <template slot-scope="scope">
                            {{scope.row.period}}天
                        </template>
                    </el-table-column>
                    <el-table-column fixed="right" label="操作" width="120">
                        <template slot-scope="scope">
                            <el-button type="text" size="small"
                                       @click.native.prevent="editCoupon(scope.$index,scope.row)">
                                编辑
                            </el-button>
                            <el-button type="text" size="small"
                                       @click.native.prevent="delCoupon(scope.$index,scope.row)">
                                删除
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table></el-tab-pane>
        </el-tabs>
        <el-dialog
            title="优惠券"
            :visible.sync="dialogCouponVisible"
            width="30%">
            <el-form ref="form" :model="coupon" label-width="120px">
                <el-form-item label="优惠券名称" prop="name" :rules="[
                { required: true, message: '请输入优惠券名称', trigger: 'blur' }]">
                    <el-input v-model="coupon.name"></el-input>
                </el-form-item>
                <el-form-item label="面值(元)" prop="money" :rules="[
                { required: true, message: '请输入面值', trigger: 'blur' },
                 { type: 'number', message: '面值必须为数字值'}]">
                    <el-input  type="number" v-model.number="coupon.money"></el-input>
                </el-form-item>
                <el-form-item label="有效期(天)" prop="period" :rules="[
                { required: true, message: '请输入有效期', trigger: 'blur' },
                 { type: 'number', message: '有效期必须为数字值'}]">
                    <el-input type="number" v-model.number="coupon.period"></el-input>
                </el-form-item>
                <el-form-item label="满多少元可用" prop="leastSpendMoney" :rules="rules.leastSpendMoney">
                    <el-input type="number" v-model="coupon.leastSpendMoney"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                        <el-button @click="dialogCouponVisible = false">取 消</el-button>
                         <el-button type="primary" @click="saveForm('form')">确 定</el-button>
                      </span>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            var leastSpendMoneyRule = (rule, date, callback) => {
                if(this.coupon.leastSpendMoney<=this.coupon.money){
                    callback(new Error('消费金额必须大于面值!'));
                }else{
                    callback();
                }
            };
            return {
                lanmu:{},
                tableData1:[],
                tableData2:[],
                promotionList:[],
                couponList:[],
                coupon:{},
                dialogCouponVisible:false,
                rules: {
                    leastSpendMoney: [
                        { required: true, message: '请输入消费金额', trigger: 'blur' },
                        { validator: leastSpendMoneyRule, trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            init() {
                this.lanmu.id=this.$route.query.lanmuId
                this.getData()
                this.getCoupon()
            },
            getData(){
                this.$axios.get(this.HOST+'/product/getPromotionList',
                    {
                        params:{"lanmuId":this.lanmu.id}
                    }
                ).then((res) => {
                    this.tableData1=res.data.data
                    this.promotionList=res.data.data
                })
            },
            delPromotion(index,row){
                this.$axios.get(this.HOST+'/product/delPromotion',
                    {
                        params:{id: row.id}
                    }
                ).then((res) => {
                    if(res.data.code==0) {
                        this.$message.success('删除成功');
                        this.tableData1.splice(index, 1);
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })
            },
            getCoupon(){
                this.$axios.get(this.HOST+'/product/getCouponList',
                    {
                        params:{"lanmuId":this.lanmu.id}
                    }
                ).then((res) => {
                    this.tableData2=res.data.data
                    this.couponList=res.data.data
                })
            },
            editCoupon(index,row){
                if(row!=null){
                    this.coupon=row
                }
                this.dialogCouponVisible=true
            },
            delCoupon(index,row){
                this.$axios.get(this.HOST+'/product/delCoupon',
                    {
                        params:{id: row.id}
                    }
                ).then((res) => {
                    if(res.data.code==0) {
                        this.$message.success('删除成功');
                        this.tableData2.splice(index, 1);
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
                            url: this.HOST+"/product/saveCoupon",
                            data: this.coupon
                        }).then((res)=>{
                            if(res.data.code==0) {
                                this.dialogCouponVisible=false
                                this.getCoupon()
                                this.$message.success('保存成功')
                            }else{
                                this.$message.error(res.data.msg)
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            editPromotion(index,row){
                if(row==null){
                    this.$router.push({path:'/editPromotion?lanmuId='+this.lanmu.id+'&title=限时折扣'})
                }else{
                    this.$router.push({path:'/editPromotion?lanmuId='+this.lanmu.id+'&title=限时折扣'+'&id='+row.id})

                }
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

</style>

