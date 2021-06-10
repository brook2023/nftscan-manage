package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.NftIndustryInfo;

import java.util.List;

public interface NftIndustryInfoMapper {
    List<NftIndustryInfo> selectInfoList(NftIndustryInfo info);

    int insertInfo(NftIndustryInfo info);

    int updateInfo(NftIndustryInfo info);

    int deleteInfoByIds(Long[] infoIds);

    NftIndustryInfo selectInfoById(Long infoId);
}
