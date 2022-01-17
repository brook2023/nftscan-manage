package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.UserSubmittedContract;
import com.ruoyi.system.service.IUserSubmittedContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * NFT项目信息提交Controller
 * 
 * @author ruoyi
 * @date 2022-01-13
 */
@RestController
@RequestMapping("/UserSubmittedContract")
public class UserSubmittedContractController extends BaseController
{
    @Autowired
    private IUserSubmittedContractService userSubmittedContractService;

    /**
     * 查询NFT项目信息提交列表
     */
    @PreAuthorize(hasPermi = "system:UserSubmittedContract:list")
    @GetMapping("/list")
    public TableDataInfo list(UserSubmittedContract userSubmittedContract)
    {
        startPage();
        List<UserSubmittedContract> list = userSubmittedContractService.selectUserSubmittedContractList(userSubmittedContract);
        return getDataTable(list);
    }

    /**
     * 导出NFT项目信息提交列表
     */
    @PreAuthorize(hasPermi = "system:UserSubmittedContract:export")
    @Log(title = "NFT项目信息提交", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserSubmittedContract userSubmittedContract) throws IOException
    {
        List<UserSubmittedContract> list = userSubmittedContractService.selectUserSubmittedContractList(userSubmittedContract);
        ExcelUtil<UserSubmittedContract> util = new ExcelUtil<UserSubmittedContract>(UserSubmittedContract.class);
        util.exportExcel(response, list, "UserSubmittedContract");
    }

    /**
     * 获取NFT项目信息提交详细信息
     */
    @PreAuthorize(hasPermi = "system:UserSubmittedContract:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(userSubmittedContractService.selectUserSubmittedContractById(id));
    }

    /**
     * 新增NFT项目信息提交
     */
    @PreAuthorize(hasPermi = "system:UserSubmittedContract:add")
    @Log(title = "NFT项目信息提交", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserSubmittedContract userSubmittedContract)
    {
        return toAjax(userSubmittedContractService.insertUserSubmittedContract(userSubmittedContract));
    }

    /**
     * 修改NFT项目信息提交
     */
    @PreAuthorize(hasPermi = "system:UserSubmittedContract:edit")
    @Log(title = "NFT项目信息提交", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserSubmittedContract userSubmittedContract)
    {
        return toAjax(userSubmittedContractService.updateUserSubmittedContract(userSubmittedContract));
    }

    /**
     * 删除NFT项目信息提交
     */
    @PreAuthorize(hasPermi = "system:UserSubmittedContract:remove")
    @Log(title = "NFT项目信息提交", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(userSubmittedContractService.deleteUserSubmittedContractByIds(ids));
    }
}
