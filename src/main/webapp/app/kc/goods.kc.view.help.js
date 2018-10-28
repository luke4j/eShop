var goods_kc_view_help = {

    fm_find :function(view,$div_Row){
        require(['app/goods/goods.help'],function(){
            goods_help.fm_findGoodsForm($div_Row,function($btnFind,jsonForm){
                J.debugPrint(jsonForm) ;
                J.debugPrint($btnFind) ;
            }) ;
        }) ;
    },
    tbl_goods:function(view,$div_Row){
        require(['app/goods/goods.help'],function(){
            var $panel = J.$panel({title:'结果列表',content:$("<table id='tbl_findGoodsTable'>")}) ;
            $div_Row.append($panel) ;
        }) ;
    }
}