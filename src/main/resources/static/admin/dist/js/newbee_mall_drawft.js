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

/**
 * jqGrid重新加载
 */
function reload() {
    initFlatPickr();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

/**
 * 添加数据
 */
function addDraftData() {
    const data = {
        "adminUserId": 1
    };
    $.ajax({
        type: 'POST',//方法类型
        url: "/admin/drawft/add",
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            if (result.resultCode == 200) {
                swal({
                    title: "抽卡成功",
                    type: 'success',
                    showCancelButton: false,
                    confirmButtonColor: '#1baeae',
                    confirmButtonText: '返回列表',
                    confirmButtonClass: 'btn btn-success',
                    buttonsStyling: false
                }).then(function () {
                    window.location.href = "/admin/drawft";
                })
            } else {
                swal(result.message, {
                    icon: "error",
                });
            }
            ;
        },
        error: function () {
            swal("操作失败", {
                icon: "error",
            });
        }
    });
}