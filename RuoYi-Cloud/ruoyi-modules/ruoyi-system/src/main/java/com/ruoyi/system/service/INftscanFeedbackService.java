package com.ruoyi.system.service;

import com.ruoyi.system.domain.NftscanFeedback;

import java.util.List;

/**
 * 意见反馈Service接口
 * 
 * @author king
 * @date 2021-06-28
 */
public interface INftscanFeedbackService 
{
    /**
     * 查询意见反馈
     * 
     * @param id 意见反馈ID
     * @return 意见反馈
     */
    public NftscanFeedback selectNftscanFeedbackById(Long id);

    /**
     * 查询意见反馈列表
     * 
     * @param nftscanFeedback 意见反馈
     * @return 意见反馈集合
     */
    public List<NftscanFeedback> selectNftscanFeedbackList(NftscanFeedback nftscanFeedback);

    /**
     * 新增意见反馈
     * 
     * @param nftscanFeedback 意见反馈
     * @return 结果
     */
    public int insertNftscanFeedback(NftscanFeedback nftscanFeedback);

    /**
     * 修改意见反馈
     * 
     * @param nftscanFeedback 意见反馈
     * @return 结果
     */
    public int updateNftscanFeedback(NftscanFeedback nftscanFeedback);

    /**
     * 批量删除意见反馈
     * 
     * @param ids 需要删除的意见反馈ID
     * @return 结果
     */
    public int deleteNftscanFeedbackByIds(Long[] ids);

    /**
     * 删除意见反馈信息
     * 
     * @param id 意见反馈ID
     * @return 结果
     */
    public int deleteNftscanFeedbackById(Long id);
}
