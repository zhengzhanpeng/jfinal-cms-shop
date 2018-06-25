/**
 * Created by gongzhen on 2018/6/3.
 */
import BlockQuote from './Blockquote.vue'
// 这里是重点
const Blockquote = {
    install: function(Vue){
        Vue.component('Blockquote',BlockQuote)
    }
}

// 导出组件
export default Blockquote
