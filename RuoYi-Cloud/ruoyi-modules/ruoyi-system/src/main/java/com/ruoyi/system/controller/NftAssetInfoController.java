package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.NftAssetInfo;
import com.ruoyi.system.domain.NftTag;
import com.ruoyi.system.service.INftAssetInfoService;
import com.ruoyi.system.service.INftTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2021-06-23
 */
@RestController
@RequestMapping("/asset")
public class NftAssetInfoController extends BaseController
{
    @Autowired
    private INftAssetInfoService nftAssetInfoService;

    @Autowired
    private INftTagService nftTagService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize(hasPermi = "system:asset:list")
    @GetMapping("/list")
    public TableDataInfo list(NftAssetInfo nftAssetInfo)
    {
        startPage();
        List<NftAssetInfo> list = nftAssetInfoService.selectNftAssetInfoList(nftAssetInfo);
        return getDataTable(list);
    }

    /**
     * 查询所有标签
     * @return
     */
    @GetMapping("/getAllTags")
    public TableDataInfo getAllTags()
    {
        List<NftTag> list = nftTagService.selectTagList(null);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize(hasPermi = "system:asset:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NftAssetInfo nftAssetInfo) throws IOException
    {
        List<NftAssetInfo> list = nftAssetInfoService.selectNftAssetInfoList(nftAssetInfo);
        ExcelUtil<NftAssetInfo> util = new ExcelUtil<NftAssetInfo>(NftAssetInfo.class);
        util.exportExcel(response, list, "asset");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize(hasPermi = "system:asset:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(nftAssetInfoService.selectNftAssetInfoById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize(hasPermi = "system:asset:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NftAssetInfo nftAssetInfo)
    {
        return toAjax(nftAssetInfoService.insertNftAssetInfo(nftAssetInfo));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize(hasPermi = "system:asset:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NftAssetInfo nftAssetInfo)
    {
        return toAjax(nftAssetInfoService.updateNftAssetInfo(nftAssetInfo));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize(hasPermi = "system:asset:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nftAssetInfoService.deleteNftAssetInfoByIds(ids));
    }
}
