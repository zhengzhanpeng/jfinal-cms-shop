<template>
    <textarea :id="id" :value="value"></textarea>
</template>

<script>
    // Import TinyMCE
    import tinymce from 'tinymce/tinymce';
    import 'tinymce/themes/modern/theme';
    import 'tinymce/plugins/paste';
    import 'tinymce/plugins/image'
    import 'tinymce/plugins/link'
    import 'tinymce/plugins/code'
    import 'tinymce/plugins/table'
    import 'tinymce/plugins/lists'
    import 'tinymce/plugins/contextmenu'
    import 'tinymce/plugins/wordcount'
    import 'tinymce/plugins/colorpicker'
    import 'tinymce/plugins/textcolor'
    import 'tinymce/plugins/advlist'
    import 'tinymce/plugins/autolink'
    import 'tinymce/plugins/charmap'
    import 'tinymce/plugins/preview'
    import 'tinymce/plugins/hr'
    import 'tinymce/plugins/anchor'
    import 'tinymce/plugins/imagetools'
    import 'tinymce/plugins/visualblocks'
    import 'tinymce/plugins/visualchars'
    import 'tinymce/plugins/fullpage'
    import 'tinymce/plugins/media'
    import 'tinymce/plugins/save'
    import 'tinymce/plugins/directionality'
    import 'tinymce/plugins/emoticons'
    import 'tinymce/plugins/textpattern'
    import 'tinymce/plugins/textpattern'




    const INIT = 0;
    const CHANGED = 2;
    var EDITOR = null;
    export default {
        props: {
            value: {
                type: String,
                required: true
            },
            setting: {}
        },
        watch: {
            value: function (val) {
                console.log('init ' + val)
                if (this.status == INIT || tinymce.activeEditor.getContent() != val){
                    tinymce.activeEditor.setContent(val);
                }
                this.status = CHANGED
            }
        },
        data: function () {
            return {
                status: INIT,
                id: 'editor-'+new Date().getMilliseconds(),
            }
        },
        methods: {

        },
        mounted: function () {
            const _this = this;
            const setting =
            {
                selector:'#'+_this.id,
                language:"zh_CN",
                init_instance_callback:function(editor) {
                    EDITOR = editor;
                    console.log("Editor: " + editor.id + " is now initialized.");
                    editor.on('input change undo redo', () => {
                        var content = editor.getContent()
                        _this.$emit('input', content);
                    });
                },
                plugins:[' advlist autolink lists link image charmap  preview hr anchor  imagetools',
                    ' visualblocks visualchars code fullpage',
                    ' media  save table contextmenu directionality',
                    'emoticons paste textcolor colorpicker textpattern imagetools  wordcount ']
            };

            setting.language_url="/static/tinymce/tinymce_languages/langs/zh_CN.js",
                setting.skin_url="/static/tinymce/skins/lightgray",
                setting.toolbar1='bold italic underline strikethrough | fontsizeselect | forecolor backcolor | alignleft aligncenter alignright alignjustify | bullist numlist | outdent indent blockquote | undo redo | link unlink image code | removeformat preview',
                setting.imagetools_toolbar='paste rotateleft rotateright | flipv fliph | editimage imageoptions',
                setting.images_upload_credentials=true,
                setting.images_upload_handler=function (blobInfo, success, failure) {
                    if (blobInfo.blob().size > self.maxSize) {
                        failure('文件体积过大')
                    }else{
                        uploadPic()
                    }
                    function uploadPic () {
                        let formData = new FormData()
                        // 服务端接收文件的参数名，文件数据，文件名
                        formData.append('file', blobInfo.blob(), blobInfo.filename())
                        _this.$axios({
                            method: 'POST',
                            // 这里是你的上传地址
                            url: "api/admin/file/upload",
                            data: formData,
                        })
                            .then((res) => {
                                // 这里返回的是你图片的地址
                                success(res.data.url)
                            })
                            .catch(() => {
                                failure('上传失败')
                            })
                    }
                }
            Object.assign(setting,_this.setting)
            tinymce.init(setting);
        },
        beforeDestroy: function () {
            tinymce.get(this.id).destroy();
        }
    }

</script>

