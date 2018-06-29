<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="fa fa-bars"></i> 商品管理</el-breadcrumb-item>
                <el-breadcrumb-item>商品列表</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="handle-box">
            <Blockquote>
                <el-button type="primary" icon="el-icon-circle-plus-outline" @click="addContent">添加商品</el-button>
                <el-button type="primary" icon="el-icon-refresh" @click="getData">刷新</el-button>
            </Blockquote>
        </div>
        <div class="container">
            <el-table :data="tableData"  Checkbox border style="width: 100%" fit ref="multipleTable"
                      :default-sort="{prop:'sort_num',order:'descending'}" @cell-mouse-enter="handleSelectCell">
                <el-table-column prop="sort_num"  width="60" label="排序" >
                    <template slot-scope="scope">
                        <el-input @change="handleSort(scope.$index,scope.row)" class="input-sort" v-model="scope.row.sort_num"  placeholder=""></el-input>
                    </template>
                </el-table-column>
                <el-table-column prop="id"  label="id" width="60">
                </el-table-column>
                <el-table-column   label="缩略图" width="70">
                    <template slot-scope="scope">
                        <img class="thumbnail" :src="scope.row.thumbnail_temp">
                    </template>
                </el-table-column>
                <el-table-column prop="name"  label="商品名称" width="300">
                </el-table-column>
                <el-table-column prop="sales"  label="销量">
                </el-table-column>
                <el-table-column prop="price"  label="销售价">
                    <template slot-scope="scope">
                        {{scope.row.price}}
                    </template>
                </el-table-column>
                <el-table-column  label="市场价">
                    <template slot-scope="scope">
                        {{scope.row.marketPrice}}
                    </template>
                </el-table-column>
                <el-table-column  label="首页推荐">
                    <template slot-scope="scope">
                        <el-switch
                            v-model="scope.row.isRecommend"
                            active-color="#009688"
                            inactive-color="#ff4949"
                            :active-value="1"
                            :inactive-value="0"
                            @change="changeIsRecommend"
                        >
                        </el-switch>
                    </template>
                </el-table-column>
                <el-table-column prop="price"  label="状态">
                    <template slot-scope="scope">
                        {{scope.row.isMarketable?'正常':'下架'}}
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="220">
                    <template slot-scope="scope">
                        <router-link  :to="{ path: '/goodsEdit?id='+scope.row.id}"><i class="el-icon-edit-outline"></i>编辑 </router-link>
                        <a  href="javascript:;" v-if="scope.row.isMarketable" @click="handleXiajia(scope.$index, scope.row)"> <i class="el-icon-delete"></i>下架 </a>
                        <a  href="javascript:;" v-if="scope.row.isMarketable==false" @click="handleShangjia(scope.$index, scope.row)"> <i class="el-icon-delete"></i>上架 </a>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
    export default {
        data: function(){
            return {
                tableData:[],
                productPage:{},
                selectedGoods:{}
            }
        },
        methods: {
            addContent(){
                this.$router.push({path:'/goodsEdit'});
            },
            getData(){
                this.$axios.get(this.HOST+'/product/getProductPage',
                    {
                        params:{"pageNum": 1,"pageSize":10}
                    }
                ).then((res) => {
                    this.tableData=res.data.data.list;
                    this.productPage=res.data.data;
                })
            },
            batchDel(){

            },
            handleDelete(index,row){

            },
            handleSort(index,row){
                this.$set(this.tableData,0,this.tableData[0])
                this.saveGoods(row)
            },
            handleSelectCell(row, column, cell, event){
                this.selectedGoods=row;
            },
            handleXiajia(index,row){
                let goods=row
                goods.isMarketable=false;
                for(let o in  this.tableData){
                    if(this.tableData[o].id==row.id){
                      /*  this.tableData.splice(o,1)*/
                        break;
                    }
                }
                this.saveGoods(goods)
            },
            handleShangjia(index,row){
                let goods=row
                goods.isMarketable=true;
                for(let o in  this.tableData){
                    if(this.tableData[o].id==row.id){
                     /*   this.tableData.splice(o,1)*/
                        break;
                    }
                }
                this.saveGoods(goods)
            },
            saveGoods(goods){
                this.$axios({
                    method:"post",
                    url: this.HOST+"/product/updateProduct",
                    data: goods
                }).then((res)=>{
                    if(res.data.code==0) {
                        this.$message.success('保存成功');
                    }else{
                        this.$message.error(res.data.msg);
                    }
                })
            },
            changeIsRecommend(val){
                let goods=this.selectedGoods
                goods.isRecommend=val
                this.saveGoods(goods)
            }
        },
        created:function(){
            this.getData();
        }
    }
</script>

<style scoped>
    .el-table .cell .thumbnail{
        width: 60px;
    }
</style>
