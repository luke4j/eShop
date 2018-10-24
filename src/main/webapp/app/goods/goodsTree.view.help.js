var goodsTree_view_help = {
    /**商品树*/
    tbl_goodsTree:function(view, $div_Row){
        var me = this;
        var $btn_add_kind = $("<button id='btn_add_kind'>").addClass("btn").addClass(S.btn_add_tag_css).text("添加品类") ;
        $div_Row.append($btn_add_kind) ;
        /**添加商品树*/
        $div_Row.append("<ul id='tb_goodsTree' class='ztree'></ul>");

        /**查询第一级*/
        var rootData  ;
        J.ajax({
            url: 'goodsTree/findNode.act',
            async: false,
            success: function (data, res) {
                rootData = data;
            }
        });
        /**配置并显示树*/
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
                enable: false
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

                    var $btn_del = $("<a>").addClass("ztree_btn_del btn float_right a_btn").text("作废") ;
                    aObj.append($btn_del) ;
                    $btn_del.on("click",function(e){view.ztree_btn_del_click_handler(e,treeNode.id,treeNode) ;}) ;


                    var $btn_edit = $("<a>").addClass('ztree_btn_edit btn float_right a_btn').text("修改") ;
                    aObj.append($btn_edit) ;
                    $btn_edit.on("click",function(e){view.ztree_btn_edit_click_handler(e,treeNode.id,treeNode)}) ;

                    if(treeNode.c_group!='商品'){
                        var $btn_add = $("<a>").addClass("ztree_btn_add btn float_right a_btn").text("新增") ;
                        aObj.append($btn_add) ;
                        $btn_add.on('click',function(e){view.ztree_btn_add_click_handler(e,treeNode.id,treeNode)}) ;
                    }

                    if(treeNode.c_group=='品类'){
                        var $btn_setup= $("<a>").addClass("ztree_btn_setup btn float_right a_btn").text("配置属性") ;
                        aObj.append($btn_setup) ;
                        $btn_setup.on('click',function(e){view.ztree_btn_pzsx_click_handler(e,treeNode.id,treeNode) ;}) ;
                    }
                }
            },
            callback: {
                beforeExpand: me.ztreeBeforeExpand_handler
            }
        };
        $.fn.zTree.init($("#tb_goodsTree"), setting,rootData);
    },
    /**树展开事件*/
    ztreeBeforeExpand_handler:function(treeId, treeNode){
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

    fm_goodsBaseInfo:function(){
        var $jForm = J.createForm("f_goodstree",'form-horizontal') ;
        $jForm.fieldset.append(J.formElement({id:'fid',name:'fid',text:'父结点id',type:'hidden'}))
            .append(J.formElement({id:'c_group',name:'c_group',text:'分组',type:'select',options: J.SelectOptions('商品属性分组')}))
            .append(J.formElement({id:'fname',name:'fname',text:'级别名称'}))
            .append(J.formElement({id:'text',name:'text',text:'名称'}))
            .append(J.formElement({id:'id',name:'id',text:'id',type:'hidden'}))
            .append(J.formElement({id:'ajaxdo',name:'ajaxdo',value:'add',text:'add',type:'hidden'}))
            .append(J.formElement({id:'a1',name:'a1',text:'是否度数',type:'select',options: J.SelectOptions("是否")}))
            .append(J.formElement({id:'a2',name:'a2',text:'是否实物',type:'select',options: J.SelectOptions("是否")}))
            .append(J.formElement({id:'a3',name:'a3',text:'是否效期',type:'select',options: J.SelectOptions("是否")}))
            .append(J.formElement({id:'a4',name:'a4',text:'级别',type:'hidden'}))
            .append(J.formElement({id:'a5',name:'a5',text:'级别',type:'hidden'}))

        return $jForm ;
    },

} ;