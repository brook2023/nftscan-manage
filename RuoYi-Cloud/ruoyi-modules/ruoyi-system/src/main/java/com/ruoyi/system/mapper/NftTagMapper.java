package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.NftTag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NftTagMapper {

    List<NftTag> selectTagList(NftTag tag);

    int insertTag(NftTag tag);

    int updateTag(NftTag tag);

    int deleteTagByIds(Long[] tagIds);

    NftTag selectTagById(Long tagId);

    String getTagsById(String[] arr);
}
