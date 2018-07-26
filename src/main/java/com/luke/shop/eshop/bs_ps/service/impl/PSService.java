package com.luke.shop.eshop.bs_ps.service.impl;

import com.luke.shop.eshop.base.BaseService;
import com.luke.shop.eshop.base.service.IBusiness;
import com.luke.shop.eshop.bs_ps.service.IPSService;
import org.springframework.stereotype.Service;

/**
 * Created by luke on 2018/7/26.
 */
@Service
public class PSService extends BaseService implements IPSService,IBusiness{
    @Override
    public <T, V> T createBill(V bill) throws Exception {
        return null;
    }

    @Override
    public <T> T affirmBill(T bill) throws Exception {
        return null;
    }

    @Override
    public <T> T executeBill(T bill) throws Exception {
        return null;
    }
}
