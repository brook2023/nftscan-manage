package com.ruoyi.system.service;

import com.ruoyi.system.domain.NftTag;

import java.util.List;

public interface INftTagService {

    List<NftTag> selectTagList(NftTag tag);

    int insertTag(NftTag tag);

    int updateTag(NftTag tag);

    int deleteTagByIds(Long[] tagIds);

    NftTag selectTagById(Long tagId);

    String getTagsById(String[] arr);
}
