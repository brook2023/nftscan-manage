package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.NftTradeContract;

import java.util.List;

/**
 * tradeContractMapper接口
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
public interface NftTradeContractMapper 
{
    /**
     * 查询tradeContract
     * 
     * @param contract tradeContractID
     * @return tradeContract
     */
    public NftTradeContract selectNftTradeContractById(String contract);

    /**
     * 查询tradeContract列表
     * 
     * @param nftTradeContract tradeContract
     * @return tradeContract集合
     */
    public List<NftTradeContract> selectNftTradeContractList(NftTradeContract nftTradeContract);

    /**
     * 新增tradeContract
     * 
     * @param nftTradeContract tradeContract
     * @return 结果
     */
    public int insertNftTradeContract(NftTradeContract nftTradeContract);

    /**
     * 修改tradeContract
     * 
     * @param nftTradeContract tradeContract
     * @return 结果
     */
    public int updateNftTradeContract(NftTradeContract nftTradeContract);

    /**
     * 删除tradeContract
     * 
     * @param contract tradeContractID
     * @return 结果
     */
    public int deleteNftTradeContractById(String contract);

    /**
     * 批量删除tradeContract
     * 
     * @param contracts 需要删除的数据ID
     * @return 结果
     */
    public int deleteNftTradeContractByIds(String[] contracts);
}
