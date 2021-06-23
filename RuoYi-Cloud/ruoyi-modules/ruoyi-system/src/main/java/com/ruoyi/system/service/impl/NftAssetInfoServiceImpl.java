package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.domain.NftAssetInfo;
import com.ruoyi.system.mapper.NftAssetInfoMapper;
import com.ruoyi.system.service.INftAssetInfoService;
import com.ruoyi.system.service.INftTagService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-23
 */
@Service
public class NftAssetInfoServiceImpl implements INftAssetInfoService 
{
    @Autowired
    private NftAssetInfoMapper nftAssetInfoMapper;

    @Autowired
    private INftTagService nftTagService;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public NftAssetInfo selectNftAssetInfoById(Long id)
    {
        return nftAssetInfoMapper.selectNftAssetInfoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param nftAssetInfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NftAssetInfo> selectNftAssetInfoList(NftAssetInfo nftAssetInfo)
    {
        List<NftAssetInfo> list =  nftAssetInfoMapper.selectNftAssetInfoList(nftAssetInfo);
        // 显示标签信息
        list.forEach(nftAsset -> {
            if (StringUtils.isNotEmpty(nftAsset.getTag())) {
                String[] arr = nftAsset.getTag().split(",");
                String tag = nftTagService.getTagsById(arr);
                nftAsset.setTag(tag);
            }
        });
        return list;
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param nftAssetInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNftAssetInfo(NftAssetInfo nftAssetInfo)
    {
        return nftAssetInfoMapper.insertNftAssetInfo(nftAssetInfo);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param nftAssetInfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNftAssetInfo(NftAssetInfo nftAssetInfo)
    {
/*        String[] arr = nftAssetInfo.getTag().split(",");
        String tag = nftTagService.getTagsById(arr);
        // 更新ES缓存数据
        // 根据认证的铸造合约地址更新es中的认证状态
        UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest("nft_record");
*//*        Map<String, Object> paramsMap = new HashMap<>(16);
        paramsMap.put("auth_flag", true);
        paramsMap.put("nft_platform", foundry_contract_name);*//*
        ScriptType type = ScriptType.INLINE;
        String lang = "painless";
        updateByQueryRequest.setConflicts("proceed");
        updateByQueryRequest.setScript(new Script(type, lang, "ctx._source.tag = " + tag, Collections.emptyMap()));
        updateByQueryRequest.setQuery(new TermQueryBuilder("id", nftAssetInfo.getId()));
        updateByQueryRequest.setAbortOnVersionConflict(false);
        updateByQueryRequest.setRefresh(true);

        try {
            restHighLevelClient.updateByQuery(updateByQueryRequest, RequestOptions.DEFAULT);
            // 更新的数量
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return nftAssetInfoMapper.updateNftAssetInfo(nftAssetInfo);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteNftAssetInfoByIds(Long[] ids)
    {
        return nftAssetInfoMapper.deleteNftAssetInfoByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteNftAssetInfoById(Long id)
    {
        return nftAssetInfoMapper.deleteNftAssetInfoById(id);
    }
}
