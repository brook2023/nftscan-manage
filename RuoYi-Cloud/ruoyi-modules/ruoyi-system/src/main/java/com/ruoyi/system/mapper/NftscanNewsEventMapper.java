package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.NftscanNewsEvent;

import java.util.List;

/**
 * newsMapper接口
 * 
 * @author ruoyi
 * @date 2021-11-15
 */
public interface NftscanNewsEventMapper 
{
    /**
     * 查询news
     * 
     * @param id newsID
     * @return news
     */
    public NftscanNewsEvent selectNftscanNewsEventById(Integer id);

    /**
     * 查询news列表
     * 
     * @param nftscanNewsEvent news
     * @return news集合
     */
    public List<NftscanNewsEvent> selectNftscanNewsEventList(NftscanNewsEvent nftscanNewsEvent);

    /**
     * 新增news
     * 
     * @param nftscanNewsEvent news
     * @return 结果
     */
    public int insertNftscanNewsEvent(NftscanNewsEvent nftscanNewsEvent);

    /**
     * 修改news
     * 
     * @param nftscanNewsEvent news
     * @return 结果
     */
    public int updateNftscanNewsEvent(NftscanNewsEvent nftscanNewsEvent);

    /**
     * 删除news
     * 
     * @param id newsID
     * @return 结果
     */
    public int deleteNftscanNewsEventById(Integer id);

    /**
     * 批量删除news
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteNftscanNewsEventByIds(Integer[] ids);
}
