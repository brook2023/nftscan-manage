package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.NftAssetInfo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2021-06-23
 */
public interface INftAssetInfoService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public NftAssetInfo selectNftAssetInfoById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param nftAssetInfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<NftAssetInfo> selectNftAssetInfoList(NftAssetInfo nftAssetInfo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param nftAssetInfo 【请填写功能名称】
     * @return 结果
     */
    public int insertNftAssetInfo(NftAssetInfo nftAssetInfo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param nftAssetInfo 【请填写功能名称】
     * @return 结果
     */
    public int updateNftAssetInfo(NftAssetInfo nftAssetInfo);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteNftAssetInfoByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteNftAssetInfoById(Long id);
}
