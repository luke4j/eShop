var goodsTree_view_help = {


    /**添加保存功能组件 */
    fm_goodsInfo:function( view,$div_Row){
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
    },
    ztreeBeforeExpand:function(treeId, treeNode){
        if(treeNode.c_group=='商品'){
            J.alert("商品没有下一级") ;
            return false ;
        }
        if(!treeNode.isAjaxing){
            var zTree = $.fn.zTree.getZTreeObj(treeId);
            zTree.reAsyncChildNodes(treeNode,"refresh") ;
            return true ;
        }else{
            return false ;
        }

    },
    tbl_goodsTree:function(view, $div_Row) {
        var me = this;
        var $btn_add_kind = $("<button id='btn_add_kind'>").addClass("btn").addClass(S.btn_add_tag_css).text("添加品类") ;
        $div_Row.append($btn_add_kind) ;
        $div_Row.append("<ul id='tb_goodsTree' class='ztree'></ul>");
        var rootData  ;
        J.ajax({
            url: 'goodsTree/findNode.act',
            async: false,
            success: function (data, res) {
                rootData = data;
            }
        });
        var setting = {
            async: {
                enable: true,
                type: 'POST',
                dataType:'json',
                contentType: 'application/json', //很重要
                traditional: true,
                url:'goodsTree/findNode.act',
                otherParam:function(treeId,treeNode){
                    if(treeNode!=null){
                        return {id:treeNode.id} ;
                    }else{
                        return {id:0} ;
                    }
                },
                dataFilter:function(treeId, parentNode, responseData){
                    return responseData.data ;
                }
            },
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: false
                }
            },
            view: {
                expandSpeed: "",
                addDiyDom: function(treeId, treeNode){
                    var zTree = $.fn.zTree.getZTreeObj(treeId);
                    var aObj = $("#" + treeNode.tId + "_a");
                    aObj.addClass("treeNode_50") ;
                    if(treeNode.count)
                        aObj.append("<span class='badge'>"+treeNode.count+"</span>") ;

                    var $btn_del = $("<a>").addClass(" ztree_btn_del btn float_right a_btn").addClass(S.btn_add_tag_css).text("删除") ;
                    aObj.append($btn_del) ;
                    $btn_del.on("click",function(e){view.ztree_btn_del_click_handler(e,treeId,treeNode) ;})

                    aObj.append("<a class='float_right a_btn'>删除</a>") ;
                    aObj.append("<a class='float_right a_btn'>修改</a>") ;
                    if(treeNode.c_group!='商品'){
                        var $btn_add = $("<a class='float_right a_btn'>添加</a>") ;
                        aObj.append($btn_add) ;
                        $btn_add.on('click',function(){}) ;
                    }

                    if(treeNode.c_group=='品类'){
                        var $btn_setup= $("<a class='float_right a_btn'>配置属性</a>") ;
                        aObj.append($btn_setup) ;
                        $btn_setup.on('click',function(e){view.ztree_btn_setup_click_handler(e,treeId,treeNode) ;}) ;
                    }
                }
            },
            callback: {
                beforeExpand: me.ztreeBeforeExpand,
                //onAsyncSuccess: me.onAsyncSuccess,
                //onAsyncError: onAsyncError
            }
        };
        $.fn.zTree.init($("#tb_goodsTree"), setting,rootData);

    }
} ;