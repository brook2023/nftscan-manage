package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NftscanFeedbackMapper;
import com.ruoyi.system.domain.NftscanFeedback;
import com.ruoyi.system.service.INftscanFeedbackService;

/**
 * 意见反馈Service业务层处理
 * 
 * @author king
 * @date 2021-06-28
 */
@Service
public class NftscanFeedbackServiceImpl implements INftscanFeedbackService 
{
    @Autowired
    private NftscanFeedbackMapper nftscanFeedbackMapper;

    /**
     * 查询意见反馈
     * 
     * @param id 意见反馈ID
     * @return 意见反馈
     */
    @Override
    public NftscanFeedback selectNftscanFeedbackById(Long id)
    {
        return nftscanFeedbackMapper.selectNftscanFeedbackById(id);
    }

    /**
     * 查询意见反馈列表
     * 
     * @param nftscanFeedback 意见反馈
     * @return 意见反馈
     */
    @Override
    public List<NftscanFeedback> selectNftscanFeedbackList(NftscanFeedback nftscanFeedback)
    {
        return nftscanFeedbackMapper.selectNftscanFeedbackList(nftscanFeedback);
    }

    /**
     * 新增意见反馈
     * 
     * @param nftscanFeedback 意见反馈
     * @return 结果
     */
    @Override
    public int insertNftscanFeedback(NftscanFeedback nftscanFeedback)
    {
        nftscanFeedback.setCreateTime(DateUtils.getNowDate());
        return nftscanFeedbackMapper.insertNftscanFeedback(nftscanFeedback);
    }

    /**
     * 修改意见反馈
     * 
     * @param nftscanFeedback 意见反馈
     * @return 结果
     */
    @Override
    public int updateNftscanFeedback(NftscanFeedback nftscanFeedback)
    {
        return nftscanFeedbackMapper.updateNftscanFeedback(nftscanFeedback);
    }

    /**
     * 批量删除意见反馈
     * 
     * @param ids 需要删除的意见反馈ID
     * @return 结果
     */
    @Override
    public int deleteNftscanFeedbackByIds(Long[] ids)
    {
        return nftscanFeedbackMapper.deleteNftscanFeedbackByIds(ids);
    }

    /**
     * 删除意见反馈信息
     * 
     * @param id 意见反馈ID
     * @return 结果
     */
    @Override
    public int deleteNftscanFeedbackById(Long id)
    {
        return nftscanFeedbackMapper.deleteNftscanFeedbackById(id);
    }
}
