package com.luke.shop.eshop.base.dao;

import com.luke.shop.eshop.base.IBaseDao;
import com.luke.shop.model.TK_YWLS;
import com.luke.shop.model.TU_User;
import com.luke.shop.model._YW;

import java.util.List;

/**
 * Created by luke on 2018/7/26.
 */
public interface IProxyDao extends IBaseDao{
    void saveAll_proxyDao(List listLs) throws Exception;
    void update_proxyDao(Object obj)throws Exception;

    /**
     * 保存流水与库存
     * @param yw
     * @param user
     * @param yw_tag
     * @return
     */
    String save_ls_kc(_YW yw, TU_User user, String yw_tag,Boolean isLens)throws Exception;
}
