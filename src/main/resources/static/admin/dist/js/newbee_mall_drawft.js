$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/drawft/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'drawftId', index: 'drawftId', width: 50, key: true, hidden: true},
            {label: '用户', name: 'adminUserId', index: 'adminUserId', width: 120},
            {label: '角色', name: 'cardName', index: 'cardName', width: 120},
            {label: '抽数', name: 'drawftCount', index: 'drawftCount', width: 120},
            {label: '添加时间', name: 'createTime', index: 'createTime', width: 120},
            {label: '游戏名', name: 'gameName', index: 'gameName', width: 120}
        ],
        height: 560,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
});