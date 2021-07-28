package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 热点推荐对象 nft_hot_recommend
 * 
 * @author king
 * @date 2021-06-29
 */
public class NftHotRecommend extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 描述（英文） */
    @Excel(name = "描述", readConverterExp = "英=文")
    private String descriptionEn;

    /** 描述（中文） */
    @Excel(name = "描述", readConverterExp = "中=文")
    private String descriptionCn;

    /** 平台id */
    @Excel(name = "平台id")
    private Long platformId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDescriptionEn(String descriptionEn) 
    {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionEn() 
    {
        return descriptionEn;
    }
    public void setDescriptionCn(String descriptionCn) 
    {
        this.descriptionCn = descriptionCn;
    }

    public String getDescriptionCn() 
    {
        return descriptionCn;
    }
    public void setPlatformId(Long platformId) 
    {
        this.platformId = platformId;
    }

    public Long getPlatformId() 
    {
        return platformId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("descriptionEn", getDescriptionEn())
            .append("descriptionCn", getDescriptionCn())
            .append("platformId", getPlatformId())
            .toString();
    }
}
