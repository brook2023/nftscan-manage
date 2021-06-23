package com.ruoyi.system.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.NftTag;
import com.ruoyi.system.service.INftTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author king
 * @description TODO
 * @date 2021/6/23
 **/
@RestController
@RequestMapping("/tag")
public class NftTagController extends BaseController {


    @Autowired
    private INftTagService nftTagService;

    /**
     * 获取列表
     */
    @PreAuthorize(hasPermi = "system:tag:list")
    @GetMapping("/list")
    public TableDataInfo list(NftTag tag)
    {
        startPage();
        List<NftTag> list = nftTagService.selectTagList(tag);
        return getDataTable(list);
    }

    /**
     * 新增
     */
    @PreAuthorize(hasPermi = "system:tag:add")
    @Log(title = "标签管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody NftTag tag)
    {
        return toAjax(nftTagService.insertTag(tag));
    }

    /**
     * 修改岗位
     */
    @PreAuthorize(hasPermi = "system:tag:edit")
    @Log(title = "标签管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody NftTag tag)
    {
        return toAjax(nftTagService.updateTag(tag));
    }

    /**
     * 删除岗位
     */
    @PreAuthorize(hasPermi = "system:tag:remove")
    @Log(title = "标签管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds)
    {
        return toAjax(nftTagService.deleteTagByIds(tagIds));
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @PreAuthorize(hasPermi = "system:tag:query")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getTag(@PathVariable Long tagId)
    {
        return AjaxResult.success(nftTagService.selectTagById(tagId));
    }
}
