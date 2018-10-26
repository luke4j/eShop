var goods_kc_view_help = {

    fm_find :function(view,$div_Row){
        require(['app/goods/goods.help'],function(){
            goods_help.fm_findGoodsForm($div_Row) ;
        }) ;
    },
    tbl_goods:function(view,$div_Row){
        require(['app/goods/goods.help'],function(){
            var $panel = J.$panel({title:'结果列表',content:$("<table id='tbl_findGoodsTable'>")}) ;
            $div_Row.append($panel) ;
            goods_help.tbl_findGoodsTable($("#tbl_findGoodsTable",$panel),{
                url:"findGoods.act",
                columns: [{
                    field: 'kind',
                    title: '菜单名称'
                }, {
                    field: 'MENU_URL',
                    title: '菜单URL'
                }, {
                    field: 'PARENT_ID',
                    title: '父级菜单'
                }, {
                    field: 'MENU_LEVEL',
                    title: '菜单级别'
                }]
            }) ;



        }) ;

    }
}