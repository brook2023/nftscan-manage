package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NftHotRecommendMapper;
import com.ruoyi.system.domain.NftHotRecommend;
import com.ruoyi.system.service.INftHotRecommendService;

/**
 * 热点推荐Service业务层处理
 * 
 * @author king
 * @date 2021-06-29
 */
@Service
public class NftHotRecommendServiceImpl implements INftHotRecommendService 
{
    @Autowired
    private NftHotRecommendMapper nftHotRecommendMapper;

    /**
     * 查询热点推荐
     * 
     * @param id 热点推荐ID
     * @return 热点推荐
     */
    @Override
    public NftHotRecommend selectNftHotRecommendById(Long id)
    {
        return nftHotRecommendMapper.selectNftHotRecommendById(id);
    }

    /**
     * 查询热点推荐列表
     * 
     * @param nftHotRecommend 热点推荐
     * @return 热点推荐
     */
    @Override
    public List<NftHotRecommend> selectNftHotRecommendList(NftHotRecommend nftHotRecommend)
    {
        return nftHotRecommendMapper.selectNftHotRecommendList(nftHotRecommend);
    }

    /**
     * 新增热点推荐
     * 
     * @param nftHotRecommend 热点推荐
     * @return 结果
     */
    @Override
    public int insertNftHotRecommend(NftHotRecommend nftHotRecommend)
    {
        return nftHotRecommendMapper.insertNftHotRecommend(nftHotRecommend);
    }

    /**
     * 修改热点推荐
     * 
     * @param nftHotRecommend 热点推荐
     * @return 结果
     */
    @Override
    public int updateNftHotRecommend(NftHotRecommend nftHotRecommend)
    {
        return nftHotRecommendMapper.updateNftHotRecommend(nftHotRecommend);
    }

    /**
     * 批量删除热点推荐
     * 
     * @param ids 需要删除的热点推荐ID
     * @return 结果
     */
    @Override
    public int deleteNftHotRecommendByIds(Long[] ids)
    {
        return nftHotRecommendMapper.deleteNftHotRecommendByIds(ids);
    }

    /**
     * 删除热点推荐信息
     * 
     * @param id 热点推荐ID
     * @return 结果
     */
    @Override
    public int deleteNftHotRecommendById(Long id)
    {
        return nftHotRecommendMapper.deleteNftHotRecommendById(id);
    }
}
