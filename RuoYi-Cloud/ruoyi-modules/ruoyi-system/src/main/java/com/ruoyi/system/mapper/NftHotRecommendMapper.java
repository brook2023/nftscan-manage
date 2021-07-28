package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.NftHotRecommend;

import java.util.List;

/**
 * 热点推荐Mapper接口
 * 
 * @author king
 * @date 2021-06-29
 */
public interface NftHotRecommendMapper 
{
    /**
     * 查询热点推荐
     * 
     * @param id 热点推荐ID
     * @return 热点推荐
     */
    public NftHotRecommend selectNftHotRecommendById(Long id);

    /**
     * 查询热点推荐列表
     * 
     * @param nftHotRecommend 热点推荐
     * @return 热点推荐集合
     */
    public List<NftHotRecommend> selectNftHotRecommendList(NftHotRecommend nftHotRecommend);

    /**
     * 新增热点推荐
     * 
     * @param nftHotRecommend 热点推荐
     * @return 结果
     */
    public int insertNftHotRecommend(NftHotRecommend nftHotRecommend);

    /**
     * 修改热点推荐
     * 
     * @param nftHotRecommend 热点推荐
     * @return 结果
     */
    public int updateNftHotRecommend(NftHotRecommend nftHotRecommend);

    /**
     * 删除热点推荐
     * 
     * @param id 热点推荐ID
     * @return 结果
     */
    public int deleteNftHotRecommendById(Long id);

    /**
     * 批量删除热点推荐
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteNftHotRecommendByIds(Long[] ids);
}
