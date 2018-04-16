
layui.use(['element', 'layer'], function () {
    var element = layui.element
        , layer = layui.layer;

    /**
     * 打开弹窗
     * @param url 目标URL
     * @param title 标题
     * @param width 宽度
     * @param height 高度
     */
    function open(url, title, width, height) {
        layer.open({
            title: title
            , area: [width + 'px', height + 'px']
            , type: 2
            , fixed: false //不固定
            , maxmin: true
            , content: basePath + url
        });
    }

    /**
     * 全屏打开弹窗
     * @param url 目标URL
     * @param title 标题
     * @param width 宽度
     * @param height 高度
     */
    function openFull(url, title, width, height) {
        var full = layer.open({
            title: title
            , area: [width + 'px', height + 'px']
            , type: 2
            , fixed: false //不固定
            , maxmin: true
            , content: basePath + url
        });
        layer.full(full);
    }
});