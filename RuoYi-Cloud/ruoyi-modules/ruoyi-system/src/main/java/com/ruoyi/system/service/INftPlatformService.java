package com.ruoyi.system.service;

import com.ruoyi.system.domain.NftPlatform;

import java.util.List;

public interface INftPlatformService {
    List<NftPlatform> selectPlatformList(NftPlatform platform);

    int insertPlatform(NftPlatform platform);

    int updatePlatform(NftPlatform platform);

    int deletePlatformByIds(Long[] platformIds);

    NftPlatform selectPlatformById(Long platformId);

    int updatePlatformIsHot(NftPlatform platform);

    int updatePlatformByName(NftPlatform platform);
}
