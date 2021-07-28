package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.NftPlatform;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface NftPlatformMapper {
    List<NftPlatform> selectPlatformList(NftPlatform platform);

    int insertPlatform(NftPlatform platform);

    int updatePlatform(NftPlatform platform);

    int deletePlatformByIds(Long[] platformIds);

    NftPlatform selectPlatformById(Long platformId);

    int updatePlatformIsHot(NftPlatform platform);

    int updatePlatformByName(NftPlatform platform);
}
