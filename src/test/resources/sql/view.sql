create or replace view v_goodsLens as
select g.id as gid,p.id as pid,kc.id as kid,s.id as storeid, g.name as gname ,l.sph as sph ,l.cyl as cyl,
kind.text as kind, brand.text as brand,version.text as version,color.text as color,
a.a1,a.a2,a.a3,a.a4,a.a5,a.a6,a.a7,a.a8,a.a9,a.a10,
kc.num_zheng_pin,kc.num_can_pin,kc.num_ci_pin,kc.num_zeng_pin,kc.num_need,
p.pin,p.pout,s.name as storeName
 from tg_goods g
left join tg_goodsattr a on g.id = a.goodsid
left join tg_goodstree color on g.colorid = color.id
left join tg_goodstree version on g.versionId = version.id
left join tg_goodstree brand on g.brandid = brand.id
left join tg_goodstree kind on g.kindid = kind.id
left join tg_lens l on g.id = l.goodsid
left join tk_kc kc on g.id = kc.goodsid and l.sph = kc.sph and l.cyl = kc.cyl
left join tu_store s on s.id = kc.storeid
left join tg_price p on g.id = p.goodsid and l.sph = p.sph and l.cyl = p.cyl
where kind.a1 = 'true';

create or replace view v_goodsNotLens as
select g.id as gid,p.id as pid,kc.id as kid,s.id as storeid, g.name as gname ,'' as sph ,'' as cyl,
kind.text as kind, brand.text as brand,version.text as version,color.text as color,
a.a1,a.a2,a.a3,a.a4,a.a5,a.a6,a.a7,a.a8,a.a9,a.a10,
kc.num_zheng_pin,kc.num_can_pin,kc.num_ci_pin,kc.num_zeng_pin,kc.num_need,
p.pin,p.pout,s.name as storename
from tg_goods g
left join tg_goodsattr a on g.id = a.goodsid
left join tg_goodstree color on g.colorid = color.id
left join tg_goodstree version on g.versionId = version.id
left join tg_goodstree brand on g.brandid = brand.id
left join tg_goodstree kind on g.kindid = kind.id
left join tk_kc kc on g.id = kc.goodsid and kc.sph is null and kc.cyl is null
left join tu_store s on s.id = kc.storeid
left join tg_price p on g.id = p.goodsid and p.sph is null and p.cyl is null and p.storeid is null
where kind.a1 = 'false' ;
