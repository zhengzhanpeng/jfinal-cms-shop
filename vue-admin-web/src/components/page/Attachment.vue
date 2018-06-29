<template>
    <div class="">
        <pictureview type="preview" @selectChange="selectChange"></pictureview>
    </div>
</template>

<script>
    import pictureview from '@/components/my-components/pictureview/index';
    export default {
        data() {
            return {
                lanmuId:""
            }
        },
        methods: {
            init:function () {
                this.lanmuId=this.$route.query.lanmuId
            },
            selectChange(data){
                console.log(data)
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

