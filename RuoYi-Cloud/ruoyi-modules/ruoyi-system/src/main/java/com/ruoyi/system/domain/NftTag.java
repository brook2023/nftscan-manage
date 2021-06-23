package com.ruoyi.system.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import org.apache.ibatis.type.Alias;

/**
 * @author king
 * @description TODO
 * @date 2021/6/23
 **/
@Alias("tag")
public class NftTag extends BaseEntity {
    private Long id;
    private String tag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
