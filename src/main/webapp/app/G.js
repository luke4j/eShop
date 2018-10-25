/**商品属性元素，品类，品牌，型号，颜色，扩展属性，站点*/
var G = G||{} ;
G.EmptyOpts = {val:'',text:'全部 '} ;
G.ajax_select_goodsNode = function(fid){
    var opts = [G.EmptyOpts] ;
    fid = fid||0 ;
    J.ajax({
        async:false,
        data:{id:fid},
        url:'goodsTree/findNode.act',
        success:function(data,res){
            for(var i in data){
                var kind = {val:data[i].id,text:data[i].text} ;
                opts.push(kind) ;
            }
        }
    }) ;
    return opts ;
} ;

G.ajax_select_store = function(){
    var opts = [G.EmptyOpts] ;
    J.ajax({
        async:false,
        url:'store/findAllStore.act',
        success:function(data,res){
            for(var i in data){
                var store = {val:data[i].id,text:data[i].name} ;
                opts.push(store) ;
            }
        }
    }) ;
    return opts ;
} ;
G.ajax_find_goodsAttr = function(kindId){
    var setups = [] ;
    J.ajax({
        async:false,
        data:{id:kindId},
        url:'goodsAttrSetup/findGoodsAttrSetupByKindId.act',
        success:function(data,res){
            if(typeof (data)==='object'&&data.length){
                setups = data ;
            }
        }
    }) ;
    return setups ;
} ;

G.select_store = function(eleId){
    var opts = this.ajax_select_store() ;
    return J.formElement({id:eleId,name:eleId,text:'站点',type:'select',options:opts}) ;
}

G.select_kind = function(eleId){
    var opts = this.ajax_select_goodsNode() ;
    return J.formElement({id:eleId,name:eleId,text:'品类',type:'select',options:opts}) ;
} ;

G.select_brand = function(eleId,kindId){
    if(kindId){
        var opts = this.ajax_select_goodsNode(kindId) ;
        return J.formElement({id:eleId,name:eleId,text:'品牌',type:'select',options:opts}) ;
    }else{
        var opts = [G.EmptyOpts] ;
        return J.formElement({id:eleId,name:eleId,text:'品牌',type:'select',options:opts}) ;
    }
} ;

G.select_version = function(eleId,brandId){
    if(brandId){
        var opts = this.ajax_select_goodsNode(brandId) ;
        return J.formElement({id:eleId,name:eleId,text:'型号',type:'select',options:opts}) ;
    }else{
        var opts = [G.EmptyOpts] ;
        return J.formElement({id:eleId,name:eleId,text:'型号',type:'select',options:opts}) ;
    }
} ;

G.select_color = function(eleId,versionId){
    if(versionId){
        var opts = this.ajax_select_goodsNode(versionId) ;
        return J.formElement({id:eleId,name:eleId,text:'颜色',type:'select',options:opts}) ;
    }else{
        var opts = [G.EmptyOpts] ;
        return J.formElement({id:eleId,name:eleId,text:'颜色',type:'select',options:opts}) ;
    }
} ;
