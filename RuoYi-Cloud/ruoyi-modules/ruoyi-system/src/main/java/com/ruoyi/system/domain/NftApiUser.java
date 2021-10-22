package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * api平台用户对象 nft_api_user
 * 
 * @author ruoyi
 * @date 2021-10-20
 */
public class NftApiUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 用户登录邮箱 */
    @Excel(name = "用户登录邮箱")
    private String email;

    /** 密码 */
    private String password;

    /** api_ID */
    @Excel(name = "api_ID")
    private String apiKey;

    /** API_SECRET */
    @Excel(name = "API_SECRET")
    private String apiSecret;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setApiKey(String apiKey) 
    {
        this.apiKey = apiKey;
    }

    public String getApiKey() 
    {
        return apiKey;
    }
    public void setApiSecret(String apiSecret) 
    {
        this.apiSecret = apiSecret;
    }

    public String getApiSecret() 
    {
        return apiSecret;
    }
    public void setLastLoginTime(Date lastLoginTime) 
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime() 
    {
        return lastLoginTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("email", getEmail())
            .append("password", getPassword())
            .append("apiKey", getApiKey())
            .append("apiSecret", getApiSecret())
            .append("createTime", getCreateTime())
            .append("lastLoginTime", getLastLoginTime())
            .toString();
    }
}
