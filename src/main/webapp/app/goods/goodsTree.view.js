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
            /**保存信息*/
            "click #btn_goodsTreeSubmit": "btn_goodsTreeSubmit_click_handler",
            'change #c_group':'c_group_change_handler'
        },
        initialize: function () {
            this.render();
            $("#c_group",$("#f_goodstree_add")).val("品类") ;
        },
        render: function () {
            /**工作区添加一个tb_goodsTree的table元素*/
            $("<table id='tb_goodsTree'>").appendTo($("#wm_workspace")) ;
            this.treeTable() ;
            this.addForm() ;
        },
        /**添加保存功能组件 */
        addForm:function(){
            var $jForm = J.createForm("f_goodstree_add",'form-horizontal') ;
            $jForm.fieldset.append(J.formElement({id:'fid',name:'fid',text:'父结点id',type:'hidden'}))
                .append(J.formElement({id:'c_group',name:'c_group',text:'分组',type:'select',options: J.SelectOptions('商品属性分组')}))
                .append(J.formElement({id:'text',name:'text',text:'名称'}))
                .append(J.formElement({id:'a1',name:'a1',text:'是否度数',type:'select',options: J.SelectOptions("是否")}))
                .append(J.formElement({id:'a2',name:'a2',text:'是否实物',type:'select',options: J.SelectOptions("是否")}))
                .append(J.formElement({id:'a3',name:'a3',text:'是否效期',type:'select',options: J.SelectOptions("是否")}))
                .append(J.formElement({id:'a4',name:'a4',text:'级别',type:'hidden'}))
                .append(J.formElement({id:'a5',name:'a5',text:'级别',type:'hidden'}))
                .append(J.formElement({id:'btn_goodsTreeSubmit',name:'btn_goodsTreeSubmit',text: S.btn_add,type:'btn'}))
            ;
            $("#wm_workspace").append($("<div class='div_bian_kuang'>").append($jForm.form)) ;
            $("#c_group","#f_goodstree_add").attr('disabled',"disabled") ;
        },
        /**添加表格*/
        treeTable:function(){
            var bpts = {
                url:'goodsTree/findNode.act',
                height:$(window).height()-450 ,
                //showSelectTitle:false,
                idField: 'id',
                treeShowField: 'text',
                parentIdField: 'pid',
                onLoadSuccess: function(data) {
                    console.log('load');
                    $("#tb_goodsTree").treegrid({
                        treeColumn: 1,
                        onChange: function() {
                            $("#tb_goodsTree").bootstrapTable('resetWidth');
                        }
                    });
                },
                columns: [
                    {
                        field: 'ck',
                        checkbox: true
                    },
                    {
                        field: 'text',
                        title: '名称'
                    },{
                        field: 'id',
                        title: '操作',
                        formatter:function(){
                            return ['修改','删除'].join('') ;
                        }
                    }
                ]
            } ;
            J.bpTable("tb_goodsTree",bpts) ;
        },
        // ------------------------------------事件代码区-----------------------------------------
        c_group_change_handler:function(e){
            var $selectGroup = $(e.currentTarget) ;
            if($selectGroup.val()=='品类'){
                $("#a1",$("#f_goodstree_add")).parent().parent().fadeIn("fast") ;
                $("#a2",$("#f_goodstree_add")).parent().parent().fadeIn("fast") ;
                $("#a3",$("#f_goodstree_add")).parent().parent().fadeIn("fast") ;
            }else{
                $("#a1",$("#f_goodstree_add")).parent().parent().fadeOut("fast") ;
                $("#a1",$("#f_goodstree_add")).val('') ;
                $("#a2",$("#f_goodstree_add")).parent().parent().fadeOut("fast") ;
                $("#a2",$("#f_goodstree_add")).val('') ;
                $("#a3",$("#f_goodstree_add")).parent().parent().fadeOut("fast") ;
                $("#a3",$("#f_goodstree_add")).val('') ;
            }
        },
        btn_goodsTreeSubmit_click_handler:function(e){
            var param = J.formValues($("#f_goodstree_add")) ;
            if(param.c_group=='品类'){
                param.c_level = 1 ;
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

            param.fid = param.fid||0 ;
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
                    }
                });
            }
            return false ;
        }
    }) ;

    return View ;
}) ;
