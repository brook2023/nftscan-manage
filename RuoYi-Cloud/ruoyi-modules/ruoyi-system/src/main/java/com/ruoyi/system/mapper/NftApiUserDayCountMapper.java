package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.NftApiUserDayCount;

/**
 * apiCountMapper接口
 * 
 * @author ruoyi
 * @date 2021-10-20
 */
public interface NftApiUserDayCountMapper 
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
     * 删除apiCount
     * 
     * @param id apiCountID
     * @return 结果
     */
    public int deleteNftApiUserDayCountById(Long id);

    /**
     * 批量删除apiCount
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteNftApiUserDayCountByIds(Long[] ids);
}
