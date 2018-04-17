/**
 * 渲染表格
 * @param id 表格id
 * @param url 请求数据URL
 * @param cols 表头参数
 * @param where 查询条件
 */
function tableRender(id, url, cols,where) {
    layui.use(['element', 'table'], function () {
        var element = layui.element
            , table = layui.table;

        //方法级渲染
        table.render({
            elem: id
            , url: basePath + url
            , method: 'post'
            , request: {
                pageName: 'current' //页码的参数名称，默认：page
                , limitName: 'size' //每页数据量的参数名，默认：limit
            }
            ,where:where
            , cols: [cols]
            , id: 'reload'
            , page: true
            , height: 'full'        //适应性高度
            , limit: 30             //默认分页数
        });
    });
}

/**
 * 表格重载
 * @param where 重载条件
 */
function tableReload(where) {
    layui.use( 'table', function () {
        var  table = layui.table;
        table.reload("reload",{
            page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: where
        })
    });
}

/**
 * 打开弹窗
 * @param url 目标URL
 * @param title 标题
 * @param width 宽度
 * @param height 高度
 */
function open(url, title, width, height) {
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.open({
            title: title
            , area: [width + 'px', height + 'px']
            , type: 2
            , fixed: false //不固定
            , maxmin: true
            , content: basePath + url
        });
    });
}

/**
 * 全屏打开弹窗
 * @param url 目标URL
 * @param title 标题
 */
function openFull(url, title) {
    layui.use('layer', function () {
        var layer = layui.layer;
        var full = layer.open({
            title: title
            , area: [width + 'px', height + 'px']
            , type: 2
            , fixed: false //不固定
            , maxmin: true
            , content: basePath + url
        });
        layer.full(full);
    });
}
