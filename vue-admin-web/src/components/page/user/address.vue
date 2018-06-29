<template>
    <div class="">
        eere
    </div>
</template>

<script>
    export default {
        data() {
            return {
                lanmuId:""
            }
        },
        methods: {
            init:function () {
                this.lanmuId=this.$route.query.lanmuId
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

