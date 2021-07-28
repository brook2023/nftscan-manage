package com.ruoyi.system.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.PreAuthorize;
import com.ruoyi.system.domain.UserNftFoundryContract;
import com.ruoyi.system.service.IUserNftFoundryContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * userContractController
 * 
 * @author ruoyi
 * @date 2021-07-22
 */
@RestController
@RequestMapping("/contract")
public class UserNftFoundryContractController extends BaseController
{
    @Autowired
    private IUserNftFoundryContractService userNftFoundryContractService;

    /**
     * 查询userContract列表
     */
    @PreAuthorize(hasPermi = "system:contract:list")
    @GetMapping("/list")
    public TableDataInfo list(UserNftFoundryContract userNftFoundryContract)
    {
        startPage();
        List<UserNftFoundryContract> list = userNftFoundryContractService.selectUserNftFoundryContractList(userNftFoundryContract);
        return getDataTable(list);
    }

    /**
     * 导出userContract列表
     */
    @PreAuthorize(hasPermi = "system:contract:export")
    @Log(title = "userContract", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserNftFoundryContract userNftFoundryContract) throws IOException
    {
        List<UserNftFoundryContract> list = userNftFoundryContractService.selectUserNftFoundryContractList(userNftFoundryContract);
        ExcelUtil<UserNftFoundryContract> util = new ExcelUtil<UserNftFoundryContract>(UserNftFoundryContract.class);
        util.exportExcel(response, list, "contract");
    }

    /**
     * 获取userContract详细信息
     */
    @PreAuthorize(hasPermi = "system:contract:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(userNftFoundryContractService.selectUserNftFoundryContractById(id));
    }

    /**
     * 新增userContract
     */
    @PreAuthorize(hasPermi = "system:contract:add")
    @Log(title = "userContract", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserNftFoundryContract userNftFoundryContract)
    {
        return toAjax(userNftFoundryContractService.insertUserNftFoundryContract(userNftFoundryContract));
    }

    /**
     * 修改userContract
     */
    @PreAuthorize(hasPermi = "system:contract:edit")
    @Log(title = "userContract", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserNftFoundryContract userNftFoundryContract)
    {
        return toAjax(userNftFoundryContractService.updateUserNftFoundryContract(userNftFoundryContract));
    }

    /**
     * 删除userContract
     */
    @PreAuthorize(hasPermi = "system:contract:remove")
    @Log(title = "userContract", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userNftFoundryContractService.deleteUserNftFoundryContractByIds(ids));
    }
}
