<template>
   <div class="elem-quote" :style="cl">
       <slot></slot>
   </div>
</template>
<style>
.elem-quote{
    width: 100%;
    padding: 15px;
    border-left: 5px solid #009688;
}
</style>
<script>
    export default {
        props: [
            "color"
        ],
        directives: {

        },
        data() {
            return {
                cl:"border-left-color:"+this.color,
            }
        },
        watch: {
            cl(newVal) {
                this.$emit('update', newVal)
            }
        }
    }

</script>
