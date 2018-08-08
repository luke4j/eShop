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
            /**保存信息，与后台交互*/
            "click #btn_goodsTreeSubmit": "btn_goodsTreeSubmit_click_handler",
            /**页面事件，不与后台交互*/
            "click #btn_add_kind":"btn_add_kind_click_handler",
            //"click .ztree_btn_del":function(){console.dir(arguments) ;}
        },
        initialize: function () {
            this.render();
        },
        render: function () {
            J.render(function(view,$div_Row){
                /**工作区添加一个tb_goodsTree的table元素*/
                require(['app/goods/goodsTree.view.help'],function(){
                    goodsTree_view_help.tbl_goodsTree(view,$div_Row) ;
                    goodsTree_view_help.fm_goodsInfo(view,$div_Row) ;
                }) ;
            },this) ;
        },
        // ------------------------------------事件代码区-----------------------------------------
        /**页面中添加品类按钮事件*/
        btn_add_kind_click_handler:function(e){
            alert(1) ;
            var $f_goodstree = $("#f_goodstree") ;
            $f_goodstree[0].reset() ;
            $("#ajaxdo",$f_goodstree).val("add") ;
            $("#c_group",$f_goodstree).val("品类") ;
            $("#a1",$f_goodstree).parent().parent().fadeIn("fast") ;
            $("#a2",$f_goodstree).parent().parent().fadeIn("fast") ;
            $("#a3",$f_goodstree).parent().parent().fadeIn("fast") ;
            $("#fname",$f_goodstree).val('');
        },

        /**
         * 表格中添加事件
         * @param jqEvent
         * @param id
         * @param data
         * @param rowNum
         */
        ztree_btn_add_click_handler:function(jqEvent,id,data){
          this.btn_table_add_click_handler(jqEvent,id,data) ;
        },
        btn_table_add_click_handler:function(jqEvent,id,data,rowNum){

            var $f_goodstree = $("#f_goodstree") ;
            $f_goodstree[0].reset() ;
            $("#ajaxdo",$f_goodstree).val("add") ;

            $("#a1",$f_goodstree).parent().parent().fadeOut("fast") ;
            $("#a2",$f_goodstree).parent().parent().fadeOut("fast") ;
            $("#a3",$f_goodstree).parent().parent().fadeOut("fast") ;
            $("#fid",$f_goodstree).val(data.id) ;
            if(data.c_group=='品类'){
                $("#c_group",$f_goodstree).val("品牌") ;
                $("#fname",$f_goodstree).val("品类-"+data.text) ;
            }else if(data.c_group=='品牌'){
                $("#c_group",$f_goodstree).val("型号") ;
                $("#fname",$f_goodstree).val("品类-"+data.getParentNode().text+"   品牌-"+data.text) ;
            }else if(data.c_group=='型号'){
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
        /**
         * 表格中修改事件
         * @param jqEvent
         * @param id
         * @param data
         * @param rowNum
         */
        ztree_btn_edit_click_handler:function(jqEvent,id,data){
            this.btn_table_edit_click_handler(jqEvent,id,data) ;
        },
        btn_table_edit_click_handler:function(jqEvent,id,data,rowNum){
            var $f_goodstree = $("#f_goodstree") ;
            $f_goodstree[0].reset() ;
            $("#ajaxdo",$f_goodstree).val("edit") ;
            if(data.c_group=='品类'){
                $("#a1",$f_goodstree).parent().parent().fadeIn("fast") ;
                $("#a2",$f_goodstree).parent().parent().fadeIn("fast") ;
                $("#a3",$f_goodstree).parent().parent().fadeIn("fast") ;
            }else if(data.c_group=='品牌'){
                $("#a1",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a2",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a3",$f_goodstree).parent().parent().fadeOut("fast") ;
            }else if(data.c_group=='型号'){
                $("#a1",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a2",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a3",$f_goodstree).parent().parent().fadeOut("fast") ;
            }else if(data.c_group=='颜色'){
                $("#a1",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a2",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a3",$f_goodstree).parent().parent().fadeOut("fast") ;
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
        },
        /**
         * 表格中删除事件
         * @param jqEvent
         * @param id
         * @param data
         * @param rowNum
         */
        ztree_btn_del_click_handler:function(jqEvent,treeId,treeNode){
            this.btn_table_del_click_handler(jqEvent,treeNode.id,treeNode) ;
            return false ;
        },
        btn_table_del_click_handler:function(jqEvent,id,data,rowNum){
            var $f_goodstree = $("#f_goodstree") ;
            $f_goodstree[0].reset() ;
            $("#ajaxdo",$f_goodstree).val("del") ;
            if(data.c_group=='品类'){
                $("#a1",$f_goodstree).parent().parent().fadeIn("fast") ;
                $("#a2",$f_goodstree).parent().parent().fadeIn("fast") ;
                $("#a3",$f_goodstree).parent().parent().fadeIn("fast") ;
            }else if(data.c_group=='品牌'){
                $("#a1",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a2",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a3",$f_goodstree).parent().parent().fadeOut("fast") ;
            }else if(data.c_group=='型号'){
                $("#a1",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a2",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a3",$f_goodstree).parent().parent().fadeOut("fast") ;
            }else if(data.c_group=='颜色'){
                $("#a1",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a2",$f_goodstree).parent().parent().fadeOut("fast") ;
                $("#a3",$f_goodstree).parent().parent().fadeOut("fast") ;
            }else{
                require(['app/goods/goods.help'],function(){
                    goods_help.alert_fm_delGoods(data) ;
                }) ;
            }
            J.setFormValue($f_goodstree,data) ;
            if(data.c_group=='品类'){
            }else if(data.c_group=='品牌'){
                $("#fname",$f_goodstree).val("品类-"+data.getParentNode().text) ;
            }else if(data.c_group=='型号'){
                $("#fname",$f_goodstree).val("品类-"+data.getParentNode().getParentNode().text+"   品牌-"+data.text) ;
            }else if(data.c_group=='颜色'){
                $("#fname",$f_goodstree).val("品类-"+data.getParentNode().getParentNode().getParentNode().text+"   品牌-"+data.getParentNode().getParentNode().text+"   型号-"+data.getParentNode().text) ;
            }
            $("#btn_goodsTreeSubmit",$f_goodstree).text(S.btn_del) ;
            return false ;
        },
        /**
         *表格中配置属性事件
         * @param jqEvent
         * @param id
         * @param data
         * @param rowNum
         */
        ztree_btn_setup_click_handler:function(jqEvent,id,data){
            this.btn_kindSetup_click_handler(jqEvent,data.id,data) ;
        },
        btn_kindSetup_click_handler:function(jqEvent,id,data,rowNum){
            var view = this ;
            J.changeView(view,'app/goods/goodsAttrSetup.view',data,'app/goods/goodsTree.view') ;
        },
        //------------------------------------------------------------------------------------------------------------
        /**表单中提交新增*/
        btn_goodsTreeSubmit_click_handler:function(e){
            var me = this ;
            var param = J.formValues($("#f_goodstree")) ;
            if(param.ajaxdo=='add'){
                me.btn_goodsTreeSubmit_click_handler_add(e,param) ;
            }else if(param.ajaxdo=='edit'){
                me.btn_goodsTreeSubmit_click_handler_edit(e,param) ;
            }else if(param.ajaxdo=='del'){
                me.btn_goodsTreeSubmit_click_handler_del(e,param) ;
            }else{
                J.alert("此表单缺少关键属性ajaxdo属性值 ") ;
            }

            return false ;
        },
        btn_goodsTreeSubmit_click_handler_add:function(e,param){
            if(param.c_group=='品类'){
                param.c_level = 1 ;
                param.fid = param.fid||0 ;
            }
            if(param.c_group=='品牌'){
                param.c_level = 2 ;
            }
            if(param.c_group=='型号'){
                param.c_level = 3 ;
            }
            if(param.c_group=='颜色'){
                param.c_level =4 ;
            }
            var valid = J.validate(param,{
                c_group:{null_able:false,msg:'分组不能为空'},
                text:{null_able:false,msg:'名称不能为空'}
            }) ;
            if(valid){
                J.ajax({
                    url:'goodsTree/addNode',
                    data:param,
                    success:function(res,all){
                        var zTree = $.fn.zTree.getZTreeObj("tb_goodsTree");
                        var node = zTree.getNodeByParam("id",param.fid) ;
                        zTree.expandNode(node,true,false,false,true) ;
                        $("#text",$("#f_goodstree")).val('') ;
                    }
                });
            }
        },
        btn_goodsTreeSubmit_click_handler_edit:function(e,param){
            if(param.c_group=='品类'){
                param.c_level = 1 ;
                param.fid = param.fid||0 ;
            }
            if(param.c_group=='品牌'){
                param.c_level = 2 ;
            }
            if(param.c_group=='型号'){
                param.c_level = 3 ;
            }
            if(param.c_group=='颜色'){
                param.c_level =4 ;
            }
            var valid = J.validate(param,{
                id:{null_able:false,msg:'数据id异常'},
                c_group:{null_able:false,msg:'分组不能为空'},
                text:{null_able:false,msg:'名称不能为空'}
            }) ;
            if(valid){
                J.ajax({
                    url:'goodsTree/editNode',
                    data:param,
                    success:function(res,all){
                        var zTree = $.fn.zTree.getZTreeObj("tb_goodsTree");
                        var node = zTree.getNodeByParam("id",param.fid) ;
                        zTree.expandNode(node,true,false,false,true) ;
                        $("#text",$("#f_goodstree")).val('') ;
                    }
                });
            }
        },
        btn_goodsTreeSubmit_click_handler_del:function(e,param){
           var valid = J.validate(param,{
               id:{null_able:false,msg:'数据id异常'},
                c_group:{null_able:false,msg:'分组不能为空'},
                text:{null_able:false,msg:'名称不能为空'}
            }) ;
            if(valid){
                J.ajax({
                    url:'goodsTree/delNode',
                    data:param,
                    success:function(res,all){
                        $("#tb_goodsTree").bootstrapTable('refresh') ;
                        $("#text",$("#f_goodstree")).val('') ;
                    }
                });
            }
        }
        // -----------------------------------------------------------------------------------------------------------------

    }) ;



    return View ;
}) ;
