var goods_kc_view_help = {

    fm_find :function(view,$div_Row){
        /**品类，站点组*/
        var _fm_kind = J.createForm("_fm_select_kind","form-inline") ;
        _fm_kind.fieldset
            .append(G.select_kind("kindId"))
            .append(G.select_store("storeId"));
        _fm_kind.form.css({'margin-top':'45px'}) ;
        $div_Row.append(_fm_kind.form);

        /**商品查询属性组*/
        var _fm_goods = J.createForm("_fm_goods","form-inline") ;
        _fm_goods.fieldset
            .append(G.select_brand("brandId"))
            .append(G.select_version("versionId"))
            .append(G.select_color("colorId"));
        $div_Row.append(_fm_goods.form);

    },
    tbl_goods:function(view,$div_Row){

        var _tbl_goods = J.createForm("tbl_goods") ;
        $div_Row.append(_tbl_goods.form);
        _tbl_goods.fieldset.append(J.$panel({title:'结果列表'})) ;
    }
}