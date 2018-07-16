package com.luke.shop.model;

import javax.persistence.Entity;

/**
 * Created by luke on 2018/5/11.
 * 加工单
 */
@Entity
public class TJG_JGD extends Model {

//    @Id
//    @TableGenerator(name = "tk_tjg_jgd_seq",       //sequence name
//            initialValue=Static.initialValue,
//            table = "seq_table",              //sequence table
//            pkColumnName = "seq_name",        //在表中对对应的sequence name 列
//            pkColumnValue = "tk_tjg_jgd_seq",      //在表中对对应的sequence name 值
//            valueColumnName = "num",          //值
//            allocationSize = Static.allocationSize)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tk_tjg_jgd_seq")
//    private Long id;

    private Long sellOrderId ;
    private Long streamId ;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(Long sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public Long getStreamId() {
        return streamId;
    }

    public void setStreamId(Long streamId) {
        this.streamId = streamId;
    }
}
