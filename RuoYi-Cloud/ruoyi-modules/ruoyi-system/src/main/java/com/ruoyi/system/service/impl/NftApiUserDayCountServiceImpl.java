package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NftApiUserDayCountMapper;
import com.ruoyi.system.domain.NftApiUserDayCount;
import com.ruoyi.system.service.INftApiUserDayCountService;

/**
 * apiCountService业务层处理
 * 
 * @author ruoyi
 * @date 2021-10-20
 */
@Service
public class NftApiUserDayCountServiceImpl implements INftApiUserDayCountService 
{
    @Autowired
    private NftApiUserDayCountMapper nftApiUserDayCountMapper;

    /**
     * 查询apiCount
     * 
     * @param id apiCountID
     * @return apiCount
     */
    @Override
    public NftApiUserDayCount selectNftApiUserDayCountById(Long id)
    {
        return nftApiUserDayCountMapper.selectNftApiUserDayCountById(id);
    }

    /**
     * 查询apiCount列表
     * 
     * @param nftApiUserDayCount apiCount
     * @return apiCount
     */
    @Override
    public List<NftApiUserDayCount> selectNftApiUserDayCountList(NftApiUserDayCount nftApiUserDayCount)
    {
        return nftApiUserDayCountMapper.selectNftApiUserDayCountList(nftApiUserDayCount);
    }

    /**
     * 新增apiCount
     * 
     * @param nftApiUserDayCount apiCount
     * @return 结果
     */
    @Override
    public int insertNftApiUserDayCount(NftApiUserDayCount nftApiUserDayCount)
    {
        return nftApiUserDayCountMapper.insertNftApiUserDayCount(nftApiUserDayCount);
    }

    /**
     * 修改apiCount
     * 
     * @param nftApiUserDayCount apiCount
     * @return 结果
     */
    @Override
    public int updateNftApiUserDayCount(NftApiUserDayCount nftApiUserDayCount)
    {
        return nftApiUserDayCountMapper.updateNftApiUserDayCount(nftApiUserDayCount);
    }

    /**
     * 批量删除apiCount
     * 
     * @param ids 需要删除的apiCountID
     * @return 结果
     */
    @Override
    public int deleteNftApiUserDayCountByIds(Long[] ids)
    {
        return nftApiUserDayCountMapper.deleteNftApiUserDayCountByIds(ids);
    }

    /**
     * 删除apiCount信息
     * 
     * @param id apiCountID
     * @return 结果
     */
    @Override
    public int deleteNftApiUserDayCountById(Long id)
    {
        return nftApiUserDayCountMapper.deleteNftApiUserDayCountById(id);
    }
}
