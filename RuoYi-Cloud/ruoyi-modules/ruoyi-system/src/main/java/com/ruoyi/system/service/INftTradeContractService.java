package com.ruoyi.system.service;

import com.ruoyi.system.domain.NftTradeContract;

import java.util.List;

/**
 * tradeContractService接口
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
public interface INftTradeContractService 
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
     * 批量删除tradeContract
     * 
     * @param contracts 需要删除的tradeContractID
     * @return 结果
     */
    public int deleteNftTradeContractByIds(String[] contracts);

    /**
     * 删除tradeContract信息
     * 
     * @param contract tradeContractID
     * @return 结果
     */
    public int deleteNftTradeContractById(String contract);
}
