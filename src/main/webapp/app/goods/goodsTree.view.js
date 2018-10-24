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
            "click #btn_add_kind":"btn_add_kind_handler"
            /**因传递参数问题，ztree中的添加修改作废事件都在定义时添加*/
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
        showAlert:function (title,data,okCallBack){
            var $jFrom = goodsTree_view_help.fm_goodsBaseInfo() ;
            J.alert({
                title:title+data.name,
                btns:'YN',
                msg:$jFrom.form,
                okFunction:function(e,alt){
                    if(okCallBack&& (typeof(okCallBack)==='function') ){
                        var $f = $("form",alt) ;
                        okCallBack($f,alt) ;
                    }
                }
            }) ;
            var $f_goodstree = $("#f_goodstree") ;
            $f_goodstree[0].reset() ;
            $("#ajaxdo",$f_goodstree).val("add") ;

            $("#c_group",$f_goodstree).addClass("disabled").attr("disabled","disabled") ;
            $("#fname",$f_goodstree).addClass("disabled").attr("disabled","disabled") ;

            $("#a1,#a2,#a3",$f_goodstree).parent().parent().fadeOut("fast") ;
            $("#fid",$f_goodstree).val(data.id) ;
            return $f_goodstree ;
        }  ,
        /**添加品类弹出窗事件*/
        btn_add_kind_handler:function(){
            var $jFrom = goodsTree_view_help.fm_goodsBaseInfo() ;
            J.alert({
                title:'添加品类',
                btns:'YN',
                msg:$jFrom.form,
                okFunction:function(e,alt){
                    var fv_add = J.formValues($jFrom.form) ;
                    fv_add.fid = 0 ;
                    J.ajax({
                        url:'goodsTree/addNode.act',
                        ajaxOk:true,
                        data:fv_add,
                        success:function(res,data){
                            var treeObj = $.fn.zTree.getZTreeObj("tb_goodsTree");
                            treeObj.reAsyncChildNodes(null, "refresh");
                        }
                    }) ;
                }
            }) ;
            $("#c_group",$jFrom.form).attr('disabled',"disabled").val("品类")  ;
            $("#fname",$jFrom.form).attr('disabled',"disabled") ;
        },
        /**商品树中新增连接事件*/
        ztree_btn_add_click_handler:function(jqEvent,id,data){
            function showAlertCallBack($f,alt){
                var f_addVal = J.formValues($f) ;
                J.ajax({
                    url:'goodsTree/addNode.act',
                    ajaxOk:true,
                    data:f_addVal
                }) ;
            } ;
            if(data.c_group=='品类'){
                var $f_goodstree = this.showAlert("添加",data,showAlertCallBack) ;
                $("#c_group",$f_goodstree).val("品牌") ;
                $("#fname",$f_goodstree).val("品类-"+data.text) ;
            }else if(data.c_group=='品牌'){
                var $f_goodstree = this.showAlert("添加",data,showAlertCallBack) ;
                $("#c_group",$f_goodstree).val("型号") ;
                $("#fname",$f_goodstree).val("品类-"+data.getParentNode().text+"   品牌-"+data.text) ;
            }else if(data.c_group=='型号'){
                var $f_goodstree = this.showAlert("添加",data,showAlertCallBack) ;
                $("#c_group",$f_goodstree).val("颜色") ;
                $("#fname",$f_goodstree).val("品类-"+data.getParentNode().getParentNode().text+"   品牌-"+data.getParentNode().text+"   型号-"+data.text) ;
            }else if(data.c_group=='颜色'){
                require(['app/goods/goods.help'],function(){
                    goods_help.alert_fm_addGoods(data) ;
                }) ;
            }else {
                throw new Error("goodsTree.view error 001") ;
            }
        },

        /**商品树中修改事件*/
        ztree_btn_edit_click_handler:function(jqEvent,id,data){
            J.debugPrint(data) ;
            function showAlertCallBack($f,alt){
                var f_addVal = J.formValues($f) ;
                J.ajax({
                    url:'goodsTree/editNode',
                    ajaxOk:true,
                    data:f_addVal,
                    success:function(res){
                        J.alertOk() ;
                        var treeObj = $.fn.zTree.getZTreeObj("tb_goodsTree");
                        treeObj.reAsyncChildNodes(null, "refresh");
                    }
                }) ;
            } ;

            if(data.c_group=='品类'){
                var $f_goodstree = this.showAlert("修改",data,showAlertCallBack) ;
                $f_goodstree[0].reset() ;
                $("#ajaxdo",$f_goodstree).val("edit") ;
            }else if(data.c_group=='品牌'){
                var $f_goodstree = this.showAlert("修改",data,showAlertCallBack) ;
                $f_goodstree[0].reset() ;
                $("#ajaxdo",$f_goodstree).val("edit") ;
            }else if(data.c_group=='型号'){
                var $f_goodstree = this.showAlert("修改",data,showAlertCallBack) ;
                $f_goodstree[0].reset() ;
                $("#ajaxdo",$f_goodstree).val("edit") ;
            }else if(data.c_group=='颜色'){
                var $f_goodstree = this.showAlert("修改",data,showAlertCallBack) ;
                $f_goodstree[0].reset() ;
                $("#ajaxdo",$f_goodstree).val("edit") ;
            }else{
                require(['app/goods/goods.help'],function(){
                    goods_help.alert_fm_editGoods(data,function(){
                        var treeObj = $.fn.zTree.getZTreeObj("tb_goodsTree");
                        treeObj.reAsyncChildNodes(null, "refresh");
                    }) ;
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
        },

        /**商品作废事件*/
        ztree_btn_del_click_handler:function(jqEvent,id,data){
            J.debugPrint(data) ;
            function showAlertCallBack($f,alt){
                var f_addVal = J.formValues($f) ;
                var v_id = {id:f_addVal.id} ;
                J.ajax({
                    url:'goodsTree/delNode',
                    ajaxOk:true,
                    data:v_id,
                    success:function(res){
                        J.alertOk() ;
                        var treeObj = $.fn.zTree.getZTreeObj("tb_goodsTree");
                        treeObj.reAsyncChildNodes(null, "refresh");
                    }
                }) ;
            } ;

            if(data.c_group=='品类'){
                var $f_goodstree = this.showAlert("作废",data,showAlertCallBack) ;
                $f_goodstree[0].reset() ;
                $("#ajaxdo",$f_goodstree).val("edit") ;
            }else if(data.c_group=='品牌'){
                var $f_goodstree = this.showAlert("作废",data,showAlertCallBack) ;
                $f_goodstree[0].reset() ;
                $("#ajaxdo",$f_goodstree).val("edit") ;
            }else if(data.c_group=='型号'){
                var $f_goodstree = this.showAlert("作废",data,showAlertCallBack) ;
                $f_goodstree[0].reset() ;
                $("#ajaxdo",$f_goodstree).val("edit") ;
            }else if(data.c_group=='颜色'){
                var $f_goodstree = this.showAlert("作废",data,showAlertCallBack) ;
                $f_goodstree[0].reset() ;
                $("#ajaxdo",$f_goodstree).val("edit") ;
            }else{
                require(['app/goods/goods.help'],function(){
                    goods_help.alert_fm_editGoods(data,function(){
                        var treeObj = $.fn.zTree.getZTreeObj("tb_goodsTree");
                        treeObj.reAsyncChildNodes(null, "refresh");
                    }) ;
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
        }
    }) ;



    return View ;
}) ;
