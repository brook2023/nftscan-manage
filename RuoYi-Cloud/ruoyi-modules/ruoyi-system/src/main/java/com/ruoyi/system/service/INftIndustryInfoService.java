package com.ruoyi.system.service;

import com.ruoyi.system.domain.NftIndustryInfo;

import java.util.List;

public interface INftIndustryInfoService {
    List<NftIndustryInfo> selectInfoList(NftIndustryInfo info);

    int insertInfo(NftIndustryInfo info);

    int updateInfo(NftIndustryInfo info);

    int deleteInfoByIds(Long[] infoIds);

    NftIndustryInfo selectInfoById(Long infoId);
}
