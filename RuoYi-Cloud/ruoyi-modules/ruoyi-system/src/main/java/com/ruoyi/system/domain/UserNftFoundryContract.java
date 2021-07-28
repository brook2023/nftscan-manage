package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * userContract对象 user_nft_foundry_contract
 * 
 * @author ruoyi
 * @date 2021-07-22
 */
public class UserNftFoundryContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键自增 */
    private Long id;

    /** 区块链网络 */
    @Excel(name = "区块链网络")
    private String blockchainNetwork;

    /** 铸造协议合约地址 */
    @Excel(name = "铸造协议合约地址")
    private String foundryContractAddress;

    /** 铸造协议类别 */
    @Excel(name = "铸造协议类别")
    private String foundryContractType;

    /** 铸造协议名称 */
    @Excel(name = "铸造协议名称")
    private String foundryContractName;

    /** 官方网站 */
    @Excel(name = "官方网站")
    private String officialWebsite;

    /** 铸造合约特性描述 */
    @Excel(name = "铸造合约特性描述")
    private String foundryContractDescription;

    /** Twitter */
    @Excel(name = "Twitter")
    private String twitter;

    /** Email */
    @Excel(name = "Email")
    private String email;

    /** uri */
    @Excel(name = "uri")
    private String uri;

    /** 元数据 */
    @Excel(name = "元数据")
    private String metadata;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBlockchainNetwork(String blockchainNetwork) 
    {
        this.blockchainNetwork = blockchainNetwork;
    }

    public String getBlockchainNetwork() 
    {
        return blockchainNetwork;
    }
    public void setFoundryContractAddress(String foundryContractAddress) 
    {
        this.foundryContractAddress = foundryContractAddress;
    }

    public String getFoundryContractAddress() 
    {
        return foundryContractAddress;
    }
    public void setFoundryContractType(String foundryContractType) 
    {
        this.foundryContractType = foundryContractType;
    }

    public String getFoundryContractType() 
    {
        return foundryContractType;
    }
    public void setFoundryContractName(String foundryContractName) 
    {
        this.foundryContractName = foundryContractName;
    }

    public String getFoundryContractName() 
    {
        return foundryContractName;
    }
    public void setOfficialWebsite(String officialWebsite) 
    {
        this.officialWebsite = officialWebsite;
    }

    public String getOfficialWebsite() 
    {
        return officialWebsite;
    }
    public void setFoundryContractDescription(String foundryContractDescription) 
    {
        this.foundryContractDescription = foundryContractDescription;
    }

    public String getFoundryContractDescription() 
    {
        return foundryContractDescription;
    }
    public void setTwitter(String twitter) 
    {
        this.twitter = twitter;
    }

    public String getTwitter() 
    {
        return twitter;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setUri(String uri) 
    {
        this.uri = uri;
    }

    public String getUri() 
    {
        return uri;
    }
    public void setMetadata(String metadata) 
    {
        this.metadata = metadata;
    }

    public String getMetadata() 
    {
        return metadata;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("blockchainNetwork", getBlockchainNetwork())
            .append("foundryContractAddress", getFoundryContractAddress())
            .append("foundryContractType", getFoundryContractType())
            .append("foundryContractName", getFoundryContractName())
            .append("officialWebsite", getOfficialWebsite())
            .append("foundryContractDescription", getFoundryContractDescription())
            .append("twitter", getTwitter())
            .append("email", getEmail())
            .append("uri", getUri())
            .append("metadata", getMetadata())
            .toString();
    }
}
