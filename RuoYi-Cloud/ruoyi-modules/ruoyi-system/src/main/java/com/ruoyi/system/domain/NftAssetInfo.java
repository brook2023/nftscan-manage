package com.ruoyi.system.domain;

import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 nft_asset_info
 * 
 * @author ruoyi
 * @date 2021-06-23
 */
public class NftAssetInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 铸造合约 */
    @Excel(name = "铸造合约")
    private String methodContract;

    /** ERC721 token合约 */
    @Excel(name = "ERC721 token合约")
    private String tokenContract;

    /** 简称 */
    @Excel(name = "简称")
    private String symbol;

    /** ERC721资产ID */
    @Excel(name = "ERC721资产ID")
    private String tokenId;

    /** 铸造txhash */
    @Excel(name = "铸造txhash")
    private String txHash;

    /** 铸造区块高度 */
    @Excel(name = "铸造区块高度")
    private Long blockNumber;

    /** 铸造区块hash */
    @Excel(name = "铸造区块hash")
    private String blockHash;

    /** 铸造区块时间 */
    @Excel(name = "铸造区块时间")
    private String txTimestamp;

    /** URI元文件路径 */
    @Excel(name = "URI元文件路径")
    private String uri;

    /** $column.columnComment */
    @Excel(name = "URI元文件路径")
    private String nftFrom;

    /** $column.columnComment */
    @Excel(name = "URI元文件路径")
    private String nftUniqueNum;

    /** $column.columnComment */
    @Excel(name = "URI元文件路径")
    private String name;

    /** $column.columnComment */
    @Excel(name = "URI元文件路径")
    private String description;

    /** $column.columnComment */
    @Excel(name = "URI元文件路径")
    private String image;

    /** $column.columnComment */
    @Excel(name = "URI元文件路径")
    private String externalUrl;

    /** $column.columnComment */
    @Excel(name = "URI元文件路径")
    private String ethResult;

    /** $column.columnComment */
    @Excel(name = "URI元文件路径")
    private String jsonUrl;

    /** $column.columnComment */
    @Excel(name = "URI元文件路径")
    private String imageUrl;

    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMethodContract(String methodContract) 
    {
        this.methodContract = methodContract;
    }

    public String getMethodContract() 
    {
        return methodContract;
    }
    public void setTokenContract(String tokenContract) 
    {
        this.tokenContract = tokenContract;
    }

    public String getTokenContract() 
    {
        return tokenContract;
    }
    public void setSymbol(String symbol) 
    {
        this.symbol = symbol;
    }

    public String getSymbol() 
    {
        return symbol;
    }
    public void setTokenId(String tokenId) 
    {
        this.tokenId = tokenId;
    }

    public String getTokenId() 
    {
        return tokenId;
    }
    public void setTxHash(String txHash) 
    {
        this.txHash = txHash;
    }

    public String getTxHash() 
    {
        return txHash;
    }
    public void setBlockNumber(Long blockNumber) 
    {
        this.blockNumber = blockNumber;
    }

    public Long getBlockNumber() 
    {
        return blockNumber;
    }
    public void setBlockHash(String blockHash) 
    {
        this.blockHash = blockHash;
    }

    public String getBlockHash() 
    {
        return blockHash;
    }
    public void setTxTimestamp(String txTimestamp) 
    {
        this.txTimestamp = txTimestamp;
    }

    public String getTxTimestamp() 
    {
        return txTimestamp;
    }
    public void setUri(String uri) 
    {
        this.uri = uri;
    }

    public String getUri() 
    {
        return uri;
    }
    public void setNftFrom(String nftFrom) 
    {
        this.nftFrom = nftFrom;
    }

    public String getNftFrom() 
    {
        return nftFrom;
    }
    public void setNftUniqueNum(String nftUniqueNum) 
    {
        this.nftUniqueNum = nftUniqueNum;
    }

    public String getNftUniqueNum() 
    {
        return nftUniqueNum;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setImage(String image) 
    {
        this.image = image;
    }

    public String getImage() 
    {
        return image;
    }
    public void setExternalUrl(String externalUrl) 
    {
        this.externalUrl = externalUrl;
    }

    public String getExternalUrl() 
    {
        return externalUrl;
    }
    public void setEthResult(String ethResult) 
    {
        this.ethResult = ethResult;
    }

    public String getEthResult() 
    {
        return ethResult;
    }
    public void setJsonUrl(String jsonUrl) 
    {
        this.jsonUrl = jsonUrl;
    }

    public String getJsonUrl() 
    {
        return jsonUrl;
    }
    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }

}
