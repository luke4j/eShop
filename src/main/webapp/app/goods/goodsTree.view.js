define(function(require, exports, module) {
    require("md5");
    require("S");
    require("J");
    require("backbone");
    require("bootstrap");
    require("ztree") ;
    var View = Backbone.View.extend({
        el: $("body"),
        events: {

        },
        initialize: function () {
            this.render();
        },
        render: function () {
            J.render(function(view,$div_Row){
                /**工作区添加一个tb_goodsTree的table元素*/
                require(['app/goods/goodsTree.view.help'],function(){
                    goodsTree_view_help.tbl_goodsTree(view,$div_Row) ;
                }) ;
            },this) ;
        },
        /**商品树中配置属性连接事件*/
        ztree_btn_pzsx_click_handler:function(jqEvent,id,data,rowNum){
            var view = this ;
            J.changeView(view,'app/goods/goodsAttrSetup.view',data,'app/goods/goodsTree.view') ;
        },
        /**弹出 商品 属性窗口*/
        showAlert:function (data){
        var $jFrom = goodsTree_view_help.fm_goodsBaseInfo() ;
        J.alert({
            title:'添加'+data.name,
            btns:'YN',
            msg:$jFrom.form,
            okFunction:function(e,alt){
                console.dir(e) ;
                console.dir(alt) ;
            }
        }) ;
        var $f_goodstree = $("#f_goodstree") ;
        $f_goodstree[0].reset() ;
        $("#ajaxdo",$f_goodstree).val("add") ;

        $("#c_group",$f_goodstree).addClass("disabled").attr("disabled","disabled") ;
        $("#fname",$f_goodstree).addClass("disabled").attr("disabled","disabled") ;

        $("#a1",$f_goodstree).parent().parent().fadeOut("fast") ;
        $("#a2",$f_goodstree).parent().parent().fadeOut("fast") ;
        $("#a3",$f_goodstree).parent().parent().fadeOut("fast") ;
        $("#fid",$f_goodstree).val(data.id) ;
        return $f_goodstree ;
    }  ,
        /**商品树中新增连接事件*/
        ztree_btn_add_click_handler:function(jqEvent,id,data){
            console.dir(data.c_group) ;

            if(data.c_group=='品类'){
                var $f_goodstree = this.showAlert(data) ;
                $("#c_group",$f_goodstree).val("品牌") ;
                $("#fname",$f_goodstree).val("品类-"+data.text) ;
            }else if(data.c_group=='品牌'){
                var $f_goodstree = this.showAlert(data) ;
                $("#c_group",$f_goodstree).val("型号") ;
                $("#fname",$f_goodstree).val("品类-"+data.getParentNode().text+"   品牌-"+data.text) ;
            }else if(data.c_group=='型号'){
                var $f_goodstree = this.showAlert(data) ;
                $("#c_group",$f_goodstree).val("颜色") ;
                $("#fname",$f_goodstree).val("品类-"+data.getParentNode().getParentNode().text+"   品牌-"+data.getParentNode().text+"   型号-"+data.text) ;
            }else if(data.c_group=='颜色'){
                require(['app/goods/goods.help'],function(){
                    goods_help.alert_fm_addGoods(data) ;
                }) ;
            }else {
                throw new Error("goodsTree.view error 001") ;
            }

            $("#btn_goodsTreeSubmit",$f_goodstree).text(S.btn_add);
        },

        /**商品树中修改事件*/
        ztree_btn_edit_click_handler:function(jqEvent,id,data){

            if(data.c_group=='品类'){
            }else if(data.c_group=='品牌'){
                var $f_goodstree = this.showAlert(data) ;
                $f_goodstree[0].reset() ;
                $("#ajaxdo",$f_goodstree).val("edit") ;
            }else if(data.c_group=='型号'){
                var $f_goodstree = this.showAlert(data) ;
                $f_goodstree[0].reset() ;
                $("#ajaxdo",$f_goodstree).val("edit") ;
            }else if(data.c_group=='颜色'){
                var $f_goodstree = this.showAlert(data) ;
                $f_goodstree[0].reset() ;
                $("#ajaxdo",$f_goodstree).val("edit") ;
            }else{
                require(['app/goods/goods.help'],function(){
                    goods_help.alert_fm_editGoods(data) ;
                }) ;
            }
            if(data.c_group=='品类'){
            }else if(data.c_group=='品牌'){
                $("#fname",$f_goodstree).val("品类-"+data.getParentNode().text) ;
            }else if(data.c_group=='型号'){
                $("#fname",$f_goodstree).val("品类-"+data.getParentNode().getParentNode().text+"   品牌-"+data.text) ;
            }else if(data.c_group=='颜色'){
                $("#fname",$f_goodstree).val("品类-"+data.getParentNode().getParentNode().getParentNode().text+"   品牌-"+data.getParentNode().getParentNode().text+"   型号-"+data.getParentNode().text) ;
            }
            J.setFormValue($("#f_goodstree"),data) ;
            $("#btn_goodsTreeSubmit",$f_goodstree).text(S.btn_edit) ;
        }


    }) ;



    return View ;
}) ;
