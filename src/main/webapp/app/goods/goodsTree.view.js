define(function(require, exports, module) {
    require("md5");
    require("S");
    require("J");
    require("backbone");
    require("bootstrap");
    require("bootstrap-table-treegrid") ;
    var View = Backbone.View.extend({
        el: $("body"),
        events: {
            /**保存信息，与后台交互*/
            "click #btn_goodsTreeSubmit": "btn_goodsTreeSubmit_click_handler",
            /**页面事件，不与后台交互*/
            "click #btn_add_kind":"btn_add_kind_click_handler"
        },
        initialize: function () {
            this.render();
        },
        render: function () {
            J.render(function(view,$div_Row){
                /**工作区添加一个tb_goodsTree的table元素*/
                $("<table id='tb_goodsTree'>").appendTo( $div_Row) ;
                view.treeTable($div_Row) ;
                view.addForm($div_Row) ;

            },this) ;
        },
        /**添加保存功能组件 */
        addForm:function( $div_Row){
            var $jForm = J.createForm("f_goodstree",'form-horizontal') ;
            $jForm.fieldset.append(J.formElement({id:'fid',name:'fid',text:'父结点id',type:'hidden'}))
                .append(J.formElement({id:'c_group',name:'c_group',text:'分组',type:'select',options: J.SelectOptions('商品属性分组')}))
                .append(J.formElement({id:'fname',name:'fname',text:'上级名称'}))
                .append(J.formElement({id:'text',name:'text',text:'名称'}))
                .append(J.formElement({id:'id',name:'id',text:'id',type:'hidden'}))
                .append(J.formElement({id:'ajaxdo',name:'ajaxdo',text:'id',type:'hidden'}))
                .append(J.formElement({id:'a1',name:'a1',text:'是否度数',type:'select',options: J.SelectOptions("是否")}))
                .append(J.formElement({id:'a2',name:'a2',text:'是否实物',type:'select',options: J.SelectOptions("是否")}))
                .append(J.formElement({id:'a3',name:'a3',text:'是否效期',type:'select',options: J.SelectOptions("是否")}))
                .append(J.formElement({id:'a4',name:'a4',text:'级别',type:'hidden'}))
                .append(J.formElement({id:'a5',name:'a5',text:'级别',type:'hidden'}))
                .append(J.formElement({id:'btn_goodsTreeSubmit',name:'btn_goodsTreeSubmit',text: S.btn_add,type:'btn',cls: S.btn_r5_tag_css}))
            ;
            $div_Row.append($("<div class='div_bian_kuang'>").append($jForm.form)) ;
            $("#c_group","#f_goodstree").attr('disabled',"disabled") ;
            $("#fname","#f_goodstree").attr('disabled',"disabled") ;
            var $f_goodstree = $("#f_goodstree") ;
            $("#c_group",$f_goodstree).val("品类") ;
        },
        /**添加表格*/
        treeTable:function( $div_Row){
            var me = this ;
            var bpts = {
                url:'goodsTree/findNode.act',
                height:$(window).height()-450 ,
                pagination:false,
                showSelectTitle:true,
                treeEnable:true,
                idField: 'id',        //id不解释
                treeShowField: 'text',//感觉没用的属性，但他妈的有用，不过写什么都行(限下面的列 field 值 )
                parentIdField: 'fid', //数据中的父id
                onLoadSuccess: function(data) {
                    var optTreeGrid = {
                        treeColumn: 1,
                        onCollapse:function(e,nodeId){
                            var data = $("#tb_goodsTree").bootstrapTable("getData") ;
                            var tmp = [] ;
                            for(var i in data){
                                if(data[i].fid!=nodeId){
                                    tmp.push(data[i])
                                }
                            }
                            if(tmp.length==data.length) return false ;
                            $("#tb_goodsTree").bootstrapTable('load', tmp)
                            $("#tb_goodsTree").treegrid(optTreeGrid) ;
                            return false ;
                        },
                        onExpand:function(e,nodeId){
                            var isLoadGoodsTree = false ;
                            var data = $("#tb_goodsTree").bootstrapTable("getData") ;
                            var param = J.ArrayToMap(data);
                            J.ajax({
                                url:'goodsTree/findNode.act',
                                async:false,
                                data:{id:nodeId},
                                success:function(res){
                                    if(res.length!=0){
                                        isLoadGoodsTree = true ;
                                    }else{
                                        return false ;
                                    }
                                    var tmp = J.ArrayToMap(res) ;
                                    param = $.extend(param,tmp) ;
                                    $("#tb_goodsTree").bootstrapTable('load',  J.MapToArray(param)) ;
                                }
                            }) ;
                            if(isLoadGoodsTree){
                                $("#tb_goodsTree").treegrid(optTreeGrid) ;
                            }
                            return false ;
                        },
                        onChange: function() {
                            $("#tb_goodsTree").bootstrapTable('resetWidth');
                        }
                    };
                    $("#tb_goodsTree").treegrid(optTreeGrid);
                },
                columns: [
                    {
                        field: 'c_group',
                        title: '分组',
                        width:'33%'
                     },{
                        field: 'text',
                        title: '名称',
                        width:'33%'
                    },{
                        field: 'id',
                        title: '操作',
                        width:'33%',
                        events:{
                            "click .btn_add":function(jqEvent,id,data,rowNum){
                                me.btn_table_add_click_handler(jqEvent,id,data,rowNum) ;
                            },
                            "click .btn_edit":function(jqEvent,id,data,rowNum){
                                me.btn_table_edit_click_handler(jqEvent,id,data,rowNum) ;
                            },
                            "click .btn_del":function(jqEvent,id,data,rowNum){
                                me.btn_table_del_click_handler(jqEvent,id,data,rowNum) ;
                            }
                        },
                        formatter:function(id,data){
                            var btns = ['<button  class=" btn btn_add '+ S.btn_add_tag_css+' " name="btn_add">添加</button>',
                                '<button  class=" btn btn_edit '+ S.btn_edit_tag_css+'" name="btn_edit">修改</button>',
                                '<button class=" btn btn_del '+ S.btn_del_tag_css+'" name="btn_del">删除</button>'] ;
                            if(data.c_group=='品类')
                                btns.push('<button class=" btn a_btn" name="btn_kindSetup">配置属性</button>') ;
                            return btns.join("") ;
                        }
                    }
                ]
            } ;
            var $toolbar = $("<div id='treeToolbar'>") ;
            $toolbar.append("<button class='btn "+S.btn_add_tag_css+"' id='btn_add_kind'  >添加品类</button>") ;
            $toolbar.appendTo( $div_Row) ;
            bpts.toolbar = $toolbar ;
            J.bpTable("tb_goodsTree",bpts) ;
        },
        // ------------------------------------事件代码区-----------------------------------------
        /**页面中添加品类按钮事件*/
        btn_add_kind_click_handler:function(e){
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
                $("#fname",$f_goodstree).val("品类-"+data._parent.text+"   品牌-"+data.text) ;
            }else if(data.c_group=='型号'){
                $("#c_group",$f_goodstree).val("颜色") ;
                $("#fname",$f_goodstree).val("品类-"+data._parent._parent.text+"   品牌-"+data._parent.text+"   型号-"+data.text) ;
            }else if(data.c_group=='颜色'){
                J.alert("goods")
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
                J.alert("goods")
                throw new Error("goodsTree.view error 001") ;
            }
            if(data.c_group=='品类'){
            }else if(data.c_group=='品牌'){
                $("#fname",$f_goodstree).val("品类-"+data._parent.text) ;
            }else if(data.c_group=='型号'){
                $("#fname",$f_goodstree).val("品类-"+data._parent._parent.text+"   品牌-"+data.text) ;
            }else if(data.c_group=='颜色'){
                $("#fname",$f_goodstree).val("品类-"+data._parent._parent._parent.text+"   品牌-"+data._parent._parent.text+"   型号-"+data._parent.text) ;
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
                J.alert("goods")
                throw new Error("goodsTree.view error 001") ;
            }
            J.setFormValue($f_goodstree,data) ;
            $("#btn_goodsTreeSubmit",$f_goodstree).text(S.btn_del) ;
        },
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
                        $("#tb_goodsTree").bootstrapTable('refresh') ;
                        $("#text",$("#f_goodstree")).val('') ;
                    }
                });
            }
        },
        btn_goodsTreeSubmit_click_handler_edit:function(e,param){
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
                        $("#tb_goodsTree").bootstrapTable('refresh') ;
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
    }) ;

    return View ;
}) ;
