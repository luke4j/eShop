var goods_help = {
    ajax_getGoodsByColor:function(colorId){
        var goodsExtAttr = []  ;
        var ext
        J.ajax({
            url:'goodsTree/find_goods_attr_setup_byColor.act',
            data:{id:colorId},
            async:false,
            success:function(data,res){
                ext =  res.ext ;
                for(var i in data){
                    if(data[i].columnValue){
                        goodsExtAttr.push(data[i]) ;
                    }
                }
            }
        }) ;
        return  {goodsExtAttr:goodsExtAttr,ext:ext} ;
    },
    ajax_getGoodsById:function(colorId){
        var goodsExtAttr = []  ;
        var ext
        J.ajax({
            url:'goodsTree/find_goods_attrsByGoodsId.act',
            data:{id:colorId},
            async:false,
            success:function(data,res){
                ext =  res.ext ;
                for(var i in data){
                    if(data[i].columnValue){
                        goodsExtAttr.push(data[i]) ;
                    }
                }
            }
        }) ;
        return  {goodsExtAttr:goodsExtAttr,ext:ext} ;
    },
    alert_fm_addGoods:function(color){
        var param = this.ajax_getGoodsByColor(color.id) ;
        var $fm_goods = this.fm_goodsInfo(param.goodsExtAttr,param.ext) ;
        var alt = J.alert({
            title:'添加商品信息',
            msg:$fm_goods.form,
            btns:'YN',
            okFunction:function(e,alert){
                var valForm = J.formValues($("#fm_goodsInf")) ;
                var validate = J.validate(valForm,{
                    name:{null_able:false,msg:'商品名不能为空'},
                    kcjb:{null_able:false,msg:'库存级别不能为空'}
                }) ;
                if(validate){
                    J.ajax({
                        url:'goods/addGoods',
                        data:valForm,
                        success:function(data,res){
                            if(res.success){
                                J.alert("保存成功") ;
                                alt.modal('hide') ;
                            }
                        }
                    });
                }
            }
        }) ;
    },
    /**修改商品信息*/
    alert_fm_editGoods:function(goods){
        var param = this.ajax_getGoodsById(goods.id) ;
        var $fm_goods = this.fm_goodsInfo(param.goodsExtAttr,param.ext) ;
        var alt = J.alert({
            title:'修改商品信息',
            msg:$fm_goods.form,
            btns:'YN',
            okFunction:function(e,alert){
                var valForm = J.formValues($("#fm_goodsInf")) ;
                var validate = J.validate(valForm,{
                    name:{null_able:false,msg:'商品名不能为空'},
                    kcjb:{null_able:false,msg:'库存级别不能为空'}
                }) ;
                if(validate){
                    J.ajax({
                        url:'goods/editGoods',
                        data:valForm,
                        success:function(data,res){
                            if(res.success){
                                J.alert("修改成功") ;
                                alt.modal('hide') ;
                            }
                        }
                    });
                }
            }
        }) ;
        J.setFormValue($("#fm_goodsInf"),goods) ;
        J.setFormValue($("#fm_goodsInf"),param.ext.attrs) ;
    },
    /**
     * 表单显示商品详细信息
     * @param goodsExtAttr  商品扩展属性
     * @param ext  商品属性对象 品类，品牌，型号，颜色
     * @returns {{form, fieldset}|{form: (*|jQuery), fieldset: (*|jQuery|HTMLElement)}}
     */
    fm_goodsInfo:function(goodsExtAttr,ext){

        var jFrom = J.createForm('fm_goodsInf') ;
        /**基本属性*/
        jFrom.fieldset
            .append(J.formElementReadOnly({id:'',name:'',text:'品类',value:ext.kind.text}))
            .append(J.formElementReadOnly({id:'',name:'',text:'品牌',value:ext.brand.text}))
            .append(J.formElementReadOnly({id:'',name:'',text:'型号',value:ext.version.text}))
            .append(J.formElementReadOnly({id:'',name:'',text:'颜色',value:ext.color.text}))
            .append(J.formElement({id:'name',name:'name',text:'商品名'}))
            .append(J.formElement({id:'colorId',name:'colorId',text:'颜色ID',type:'hidden',value:ext.color.id}))
            .append(J.formElement({id:'kcjb',name:'kcjb',text:'库存级别',type:'select',options: J.SelectOptions("库存级别")}))
            .append(J.formElement({id:'c_code',name:'c_code',text:'商品编码'})) ;
        jFrom.fieldset.append(J.formElement({id:'id',name:'id',text:'ID',type:'hidden'})) ;
        /**扩展属性*/
        for(var i in goodsExtAttr){
            var el = goodsExtAttr[i] ;//需要生成的元素
            if(el.c_type=='select'&&el.defaults){
                var option = [{val:'',text:''}];
                var def = el.defaults.split(";") ;
                for(var i in def){
                    option.push({val:def[i],text:def[i]}) ;
                }
                jFrom.fieldset.append(J.formElement({id:el.columnName,name:el.columnName,text:el.columnValue,type:'select',options:option})) ;
            }else{
                jFrom.fieldset.append(J.formElement({id:el.columnName,name:el.columnName,text:el.columnValue})) ;
            }
        }
        /**是否度数*/
        if(ext.kind.a1==='true'){
            /**度数商品显示度数配置*/
            var btnSetLens = J.formElement({id:'btn_set_lens',name:'btn_set_lens',text:'配置度数',type:'btn'}) ;
            /**显示配置度数弹出窗*/
            btnSetLens.on('click',
                function(e){
                    requirejs(['app/goods/lens.help'],function(){
                        var valForm = J.formValues($("#fm_goodsInf")) ;
                        if(valForm.id){
                            lens_help.showLensWindow(valForm.id) ;
                        }else{
                            var validate = J.validate(valForm,{
                                name:{null_able:false,msg:'商品名不能为空'},
                                kcjb:{null_able:false,msg:'库存级别不能为空'}
                            }) ;
                            if(validate){
                                J.ajax({
                                    url:'goods/addGoods',
                                    data:valForm,
                                    success:function(d,res){
                                        lens_help.showLensWindow(d.id) ;
                                        $('#id',$("#fm_goodsInf")).val(d.id) ;
                                    }
                                });
                            }
                        }
                    }) ;
                }
            ) ;
            jFrom.fieldset.append(btnSetLens) ;
        }else{
            /**是否添加价格*/
            if(LukeApp.info!=null&&LukeApp.info.listSetupCom!=null){
                /**是否配置了在录入时录入价格*/
                var setups = LukeApp.info.listSetupCom ;
                for(var i in setups){
                    if(setups[i].name==="save_not_lens_add_price"&&setups[i].val==="true"){
                        jFrom.fieldset.append(J.formElement({id:'pin',name:'pin',text:"进货价"})) ;
                        jFrom.fieldset.append(J.formElement({id:'pout',name:'pout',text:"销售价"})) ;
                        break ;
                    }
                }
            }
        }
        return jFrom ;
    }
}