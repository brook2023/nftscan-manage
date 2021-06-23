package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.NftTag;
import com.ruoyi.system.mapper.NftTagMapper;
import com.ruoyi.system.service.INftTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author king
 * @description TODO
 * @date 2021/6/23
 **/
@Service("nftTagService")
public class NftTagServiceImpl implements INftTagService {

    @Autowired
    private NftTagMapper nftTagMapper;

    @Override
    public List<NftTag> selectTagList(NftTag tag) {
        return nftTagMapper.selectTagList(tag);
    }

    @Override
    public int insertTag(NftTag tag) {
        return nftTagMapper.insertTag(tag);
    }

    @Override
    public int updateTag(NftTag tag) {
        return nftTagMapper.updateTag(tag);
    }

    @Override
    public int deleteTagByIds(Long[] tagIds) {
        return nftTagMapper.deleteTagByIds(tagIds);
    }

    @Override
    public NftTag selectTagById(Long tagId) {
        return nftTagMapper.selectTagById(tagId);
    }

    @Override
    public String getTagsById(String[] arr) {
        return nftTagMapper.getTagsById(arr);
    }
}
