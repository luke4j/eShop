package com.luke.shop.eshop.bs_sell.service.impl;

import com.luke.shop.eshop.base.BaseService;
import com.luke.shop.eshop.base.service.IBusiness;
import com.luke.shop.eshop.base.service.impl.BusinessProxy;
import com.luke.shop.eshop.bs_sell.dao.ISellDao;
import com.luke.shop.eshop.bs_sell.service.ISellService;
import com.luke.shop.eshop.bs_sell.vo.VOSellAdd;
import com.luke.shop.tool.Assertion;
import com.luke.shop.tool.vo.VOId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by luke on 2018/7/26.
 */
@Service
public class SellService extends BaseService implements ISellService,IBusiness {

    @Resource
    BusinessProxy proxy ;

    ISellDao sellDao ;


    @Override
    public <T, V> T createBill(V bill) throws Exception {
        this.sellDao.save(bill) ;
        return null;
    }

    @Override
    public <T> T affirmBill(T bill) throws Exception {
        Assertion.Error("没有确认销售单流程");
        return null;
    }

    @Override
    public <T> T executeBill(T bill) throws Exception {

        return null;
    }

    @Override
    public void createSellBill(VOSellAdd sellAdd) throws Exception {
        proxy.getInstance(this).createBill(sellAdd) ;
    }

    @Override
    public void executeSellBill(VOId vo) throws Exception {

    }
}
