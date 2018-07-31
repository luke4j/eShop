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
    },
    tbl_goodsTree:function(view, $div_Row){
        var me = this ;
        $div_Row.append("<div style='max-height: 300px;height: 300px;' id='div_goodsTree'>") ;
        var treeNodes  ;
        J.ajax({
            url:'goodsTree/findNode.act',
            async:false,
            success:function(data,res){
                treeNodes = data ;
                console.dir(arguments) ;
            }
        }) ;

        $("#div_goodsTree").treeview({
            expandIcon: "glyphicon glyphicon-stop",
            collapseIcon: "glyphicon glyphicon-unchecked",
            showTags: true,
            highlightSelected: true,
            //data: treeNodes
            data:me.testData()
        }) ;
    },
    testData:function(){
        return [
            {
                text: "Node 1",
                icon: "glyphicon glyphicon-stop",
                selectedIcon: "glyphicon glyphicon-stop",
                color: "#000000",
                backColor: "#FFFFFF",
                href: "#node-1",
                selectable: true,
                tags: ['available'],
            }
        ]
    }



} ;