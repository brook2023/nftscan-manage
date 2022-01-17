package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NftTradeContractMapper;
import com.ruoyi.system.domain.NftTradeContract;
import com.ruoyi.system.service.INftTradeContractService;

/**
 * tradeContractService业务层处理
 * 
 * @author ruoyi
 * @date 2021-11-27
 */
@Service
public class NftTradeContractServiceImpl implements INftTradeContractService 
{
    @Autowired
    private NftTradeContractMapper nftTradeContractMapper;

    /**
     * 查询tradeContract
     * 
     * @param contract tradeContractID
     * @return tradeContract
     */
    @Override
    public NftTradeContract selectNftTradeContractById(String contract)
    {
        return nftTradeContractMapper.selectNftTradeContractById(contract);
    }

    /**
     * 查询tradeContract列表
     * 
     * @param nftTradeContract tradeContract
     * @return tradeContract
     */
    @Override
    public List<NftTradeContract> selectNftTradeContractList(NftTradeContract nftTradeContract)
    {
        return nftTradeContractMapper.selectNftTradeContractList(nftTradeContract);
    }

    /**
     * 新增tradeContract
     * 
     * @param nftTradeContract tradeContract
     * @return 结果
     */
    @Override
    public int insertNftTradeContract(NftTradeContract nftTradeContract)
    {
        return nftTradeContractMapper.insertNftTradeContract(nftTradeContract);
    }

    /**
     * 修改tradeContract
     * 
     * @param nftTradeContract tradeContract
     * @return 结果
     */
    @Override
    public int updateNftTradeContract(NftTradeContract nftTradeContract)
    {
        return nftTradeContractMapper.updateNftTradeContract(nftTradeContract);
    }

    /**
     * 批量删除tradeContract
     * 
     * @param contracts 需要删除的tradeContractID
     * @return 结果
     */
    @Override
    public int deleteNftTradeContractByIds(String[] contracts)
    {
        return nftTradeContractMapper.deleteNftTradeContractByIds(contracts);
    }

    /**
     * 删除tradeContract信息
     * 
     * @param contract tradeContractID
     * @return 结果
     */
    @Override
    public int deleteNftTradeContractById(String contract)
    {
        return nftTradeContractMapper.deleteNftTradeContractById(contract);
    }
}
