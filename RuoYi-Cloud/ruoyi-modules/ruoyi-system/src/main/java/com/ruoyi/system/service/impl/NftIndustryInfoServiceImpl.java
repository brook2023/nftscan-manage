package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.exception.CustomException;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.system.domain.NftIndustryInfo;
import com.ruoyi.system.mapper.NftIndustryInfoMapper;
import com.ruoyi.system.service.INftIndustryInfoService;
import com.ruoyi.system.service.INftTagService;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author king
 * @description TODO
 * @date 2021/6/4
 **/
@Service
public class NftIndustryInfoServiceImpl implements INftIndustryInfoService {

    @Autowired
    private NftIndustryInfoMapper nftIndustryInfoMapper;

    @Autowired
    private ISysDictDataService sysDictDataService;

    @Autowired
    private INftTagService nftTagService;

    @Override
    public List<NftIndustryInfo> selectInfoList(NftIndustryInfo info) {

        List<NftIndustryInfo> list =  nftIndustryInfoMapper.selectInfoList(info);

        // 显示标签信息
        list.forEach(nftIndustryInfo -> {
            if (StringUtils.isNotEmpty(nftIndustryInfo.getTags())) {
                String[] arr = nftIndustryInfo.getTags().split(",");
                String tag = nftTagService.getTagsById(arr);
                nftIndustryInfo.setTags(tag);
            }
        });
        return list;
    }

    @Override
    public int insertInfo(NftIndustryInfo info) {
        // logo名称
        String logo = sysDictDataService.selectDictLabel("media-logo", info.getContentSource());
        // 保存logo地址
        info.setLogo(logo);
        return nftIndustryInfoMapper.insertInfo(info);
    }

    @Override
    public int updateInfo(NftIndustryInfo info) {
        return nftIndustryInfoMapper.updateInfo(info);
    }

    @Override
    public int deleteInfoByIds(Long[] infoIds) {
        return nftIndustryInfoMapper.deleteInfoByIds(infoIds);
    }

    @Override
    public NftIndustryInfo selectInfoById(Long infoId) {
        return nftIndustryInfoMapper.selectInfoById(infoId);
    }

    @Override
    public String importInfo(List<NftIndustryInfo> infoList, String operName) {
        if (StringUtils.isNull(infoList) || infoList.size() == 0)
        {
            throw new CustomException("导入资讯数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (NftIndustryInfo info : infoList)
        {
            try
            {
                // 字典数据转换
                // 资讯类型
                String type = sysDictDataService.selectDictValue("nft_info_type",info.getType());
                info.setType(type);
                // 内容来源
                String contentSource = sysDictDataService.selectDictValue("sys_content_source",info.getContentSource());
                info.setContentSource(contentSource);
                this.insertInfo(info);
                successNum++;
                successMsg.append("<br/>" + successNum + "、标题 " + info.getTitle() + " 导入成功");
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、标题 " + info.getTitle() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
        }
        return successMsg.toString();
    }
}
