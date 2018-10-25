define(function(require, exports, module) {
    require("md5");
    require("S");
    require("J");
    require("G");
    require("backbone");
    require("bootstrap");
    require("ztree") ;
    var View = Backbone.View.extend({
        el: $("body"),
        events: {
            /**因为不同品类，商品查询条件不一样，所有有这个事件*/
            "change #select_kind":'select_kind_change_handler'
        },
        initialize: function () {
            this.render();
        },
        render: function () {
            J.render(function(view,$div_Row){
                /**工作区添加一个查询框，下面是查询结果列表*/
                require(['app/kc/goods.kc.view.help'],function(){
                    var $divForm = $("<div>").addClass("container-fluid") ;
                    var $divTable =  $("<div>").addClass("container-fluid") ;
                    $div_Row.append($divForm).append($divTable) ; ;
                    goods_kc_view_help.fm_find(view,$divForm) ;
                    goods_kc_view_help.tbl_goods(view,$divTable) ;
                }) ;
            },this) ;
        },
        // ------------------------------------事件代码区-----------------------------------------
        select_kind_change_handler:function(e){
            var kindId = $(e.currentTarget).val() ;

        }
        // ---------------------------------------------------------------------------------------

    }) ;

    return View ;
}) ;
