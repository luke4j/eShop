create index idx_goods_kindId_brandid_versionId_colorid on tg_goods (kindId,brandid,versionId,colorid) ;
create unique index idx_lens_goodsId_sph_cyl on tg_lens(goodsid,sph,cyl) ;
create unique index idx_kc_goodsId_sph_cyl_storeId on tk_kc(goodsid,sph,cyl,storeid) ;
create unique index idx_price_goodsId_sph_cyl_storeId on tg_price(goodsid,sph,cyl,storeid) ;
create index idx_store_name on tu_store (name ) ;
create unique index idx_goodsattr_goodsId_a1_10 on tg_goodsattr(goodsid,a1,a2,a3,a4,a5,a6,a7,a8,a9,a10) ;
