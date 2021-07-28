package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.NftHotRecommend;
import com.ruoyi.system.service.INftHotRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 热点推荐Controller
 * 
 * @author king
 * @date 2021-06-29
 */
@RestController
@RequestMapping("/hot")
public class NftHotRecommendController extends BaseController
{
    @Autowired
    private INftHotRecommendService nftHotRecommendService;

    /**
     * 查询热点推荐列表
     */
    @PreAuthorize(hasPermi = "system:hot:list")
    @GetMapping("/list")
    public TableDataInfo list(NftHotRecommend nftHotRecommend)
    {
        startPage();
        List<NftHotRecommend> list = nftHotRecommendService.selectNftHotRecommendList(nftHotRecommend);
        return getDataTable(list);
    }

    /**
     * 导出热点推荐列表
     */
    @PreAuthorize(hasPermi = "system:hot:export")
    @Log(title = "热点推荐", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NftHotRecommend nftHotRecommend) throws IOException
    {
        List<NftHotRecommend> list = nftHotRecommendService.selectNftHotRecommendList(nftHotRecommend);
        ExcelUtil<NftHotRecommend> util = new ExcelUtil<NftHotRecommend>(NftHotRecommend.class);
        util.exportExcel(response, list, "hot");
    }

    /**
     * 获取热点推荐详细信息
     */
    @PreAuthorize(hasPermi = "system:hot:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(nftHotRecommendService.selectNftHotRecommendById(id));
    }

    /**
     * 新增热点推荐
     */
    @PreAuthorize(hasPermi = "system:hot:add")
    @Log(title = "热点推荐", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NftHotRecommend nftHotRecommend)
    {
        return toAjax(nftHotRecommendService.insertNftHotRecommend(nftHotRecommend));
    }

    /**
     * 修改热点推荐
     */
    @PreAuthorize(hasPermi = "system:hot:edit")
    @Log(title = "热点推荐", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NftHotRecommend nftHotRecommend)
    {
        return toAjax(nftHotRecommendService.updateNftHotRecommend(nftHotRecommend));
    }

    /**
     * 删除热点推荐
     */
    @PreAuthorize(hasPermi = "system:hot:remove")
    @Log(title = "热点推荐", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nftHotRecommendService.deleteNftHotRecommendByIds(ids));
    }
}
