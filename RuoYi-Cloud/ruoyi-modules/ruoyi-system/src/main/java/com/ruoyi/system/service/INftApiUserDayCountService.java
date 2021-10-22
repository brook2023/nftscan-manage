package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.NftApiUserDayCount;

/**
 * apiCountService接口
 * 
 * @author ruoyi
 * @date 2021-10-20
 */
public interface INftApiUserDayCountService 
{
    /**
     * 查询apiCount
     * 
     * @param id apiCountID
     * @return apiCount
     */
    public NftApiUserDayCount selectNftApiUserDayCountById(Long id);

    /**
     * 查询apiCount列表
     * 
     * @param nftApiUserDayCount apiCount
     * @return apiCount集合
     */
    public List<NftApiUserDayCount> selectNftApiUserDayCountList(NftApiUserDayCount nftApiUserDayCount);

    /**
     * 新增apiCount
     * 
     * @param nftApiUserDayCount apiCount
     * @return 结果
     */
    public int insertNftApiUserDayCount(NftApiUserDayCount nftApiUserDayCount);

    /**
     * 修改apiCount
     * 
     * @param nftApiUserDayCount apiCount
     * @return 结果
     */
    public int updateNftApiUserDayCount(NftApiUserDayCount nftApiUserDayCount);

    /**
     * 批量删除apiCount
     * 
     * @param ids 需要删除的apiCountID
     * @return 结果
     */
    public int deleteNftApiUserDayCountByIds(Long[] ids);

    /**
     * 删除apiCount信息
     * 
     * @param id apiCountID
     * @return 结果
     */
    public int deleteNftApiUserDayCountById(Long id);
}
