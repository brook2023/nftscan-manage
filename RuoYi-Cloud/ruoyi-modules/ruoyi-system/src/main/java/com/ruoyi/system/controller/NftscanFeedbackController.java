package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.NftscanFeedback;
import com.ruoyi.system.service.INftscanFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 意见反馈Controller
 * 
 * @author king
 * @date 2021-06-28
 */
@RestController
@RequestMapping("/feedback")
public class NftscanFeedbackController extends BaseController
{
    @Autowired
    private INftscanFeedbackService nftscanFeedbackService;

    /**
     * 查询意见反馈列表
     */
    @PreAuthorize(hasPermi = "system:feedback:list")
    @GetMapping("/list")
    public TableDataInfo list(NftscanFeedback nftscanFeedback)
    {
        startPage();
        List<NftscanFeedback> list = nftscanFeedbackService.selectNftscanFeedbackList(nftscanFeedback);
        return getDataTable(list);
    }

    /**
     * 导出意见反馈列表
     */
    @PreAuthorize(hasPermi = "system:feedback:export")
    @Log(title = "意见反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NftscanFeedback nftscanFeedback) throws IOException
    {
        List<NftscanFeedback> list = nftscanFeedbackService.selectNftscanFeedbackList(nftscanFeedback);
        ExcelUtil<NftscanFeedback> util = new ExcelUtil<NftscanFeedback>(NftscanFeedback.class);
        util.exportExcel(response, list, "feedback");
    }

    /**
     * 获取意见反馈详细信息
     */
    @PreAuthorize(hasPermi = "system:feedback:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(nftscanFeedbackService.selectNftscanFeedbackById(id));
    }

    /**
     * 新增意见反馈
     */
    @PreAuthorize(hasPermi = "system:feedback:add")
    @Log(title = "意见反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NftscanFeedback nftscanFeedback)
    {
        return toAjax(nftscanFeedbackService.insertNftscanFeedback(nftscanFeedback));
    }

    /**
     * 修改意见反馈
     */
    @PreAuthorize(hasPermi = "system:feedback:edit")
    @Log(title = "意见反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NftscanFeedback nftscanFeedback)
    {
        return toAjax(nftscanFeedbackService.updateNftscanFeedback(nftscanFeedback));
    }

    /**
     * 删除意见反馈
     */
    @PreAuthorize(hasPermi = "system:feedback:remove")
    @Log(title = "意见反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nftscanFeedbackService.deleteNftscanFeedbackByIds(ids));
    }
}
