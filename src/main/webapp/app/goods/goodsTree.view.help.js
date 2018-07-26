var goodsTree_view_help = {
    /**添加保存功能组件 */
    addForm:function( view,$div_Row){
        var $jForm = J.createForm("f_goodstree",'form-horizontal') ;
        $jForm.fieldset.append(J.formElement({id:'fid',name:'fid',text:'父结点id',type:'hidden'}))
            .append(J.formElement({id:'c_group',name:'c_group',text:'分组',type:'select',options: J.SelectOptions('商品属性分组')}))
            .append(J.formElement({id:'fname',name:'fname',text:'上级名称'}))
            .append(J.formElement({id:'text',name:'text',text:'名称'}))
            .append(J.formElement({id:'id',name:'id',text:'id',type:'hidden'}))
            .append(J.formElement({id:'ajaxdo',name:'ajaxdo',value:'add',text:'id',type:'hidden'}))
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
    },/**添加表格*/
    tbl_goodsTree:function(view, $div_Row){
        var me = view ;
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
                        },
                        "click .btn_kindSetup":function(jqEvent,id,data,rowNum){
                            me.btn_kindSetup_click_handler(jqEvent,id,data,rowNum) ;
                        }
                    },
                    formatter:function(id,data){
                        var btns = ['<button  class=" btn btn_add '+ S.btn_add_tag_css+' " name="btn_add">添加</button>',
                            '<button  class=" btn btn_edit '+ S.btn_edit_tag_css+'" name="btn_edit">修改</button>',
                            '<button class=" btn btn_del '+ S.btn_del_tag_css+'" name="btn_del">删除</button>'] ;
                        if(data.c_group=='品类')
                            btns.push('<button class=" btn a_btn btn_kindSetup" name="btn_kindSetup">配置属性</button>') ;
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

} ;