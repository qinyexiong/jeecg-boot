package org.jeecg.modules.demo.tel.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.http.HttpRequest;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;


import lombok.extern.slf4j.Slf4j;


import org.jeecg.modules.demo.tel.entity.CallIndexDel;
import org.jeecg.modules.demo.tel.service.ICallIndexDelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: index_del页面
 * @Author: jeecg-boot
 * @Date: 2021-03-29
 * @Version: V1.0
 */
@Api(tags = "index_del页面")
@RestController
@RequestMapping("/callIndexDel")
@Slf4j
public class CallIndexDelController extends JeecgController<CallIndexDel, ICallIndexDelService> {
    @Autowired
    private ICallIndexDelService callIndexDelService;

    /**
     * 分页列表查询
     *
     * @param callIndexDel
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "index_del页面-分页列表查询")
    @ApiOperation(value = "index_del页面-分页列表查询", notes = "index_del页面-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(CallIndexDel callIndexDel,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        QueryWrapper<CallIndexDel> queryWrapper = QueryGenerator.initQueryWrapper(callIndexDel, req.getParameterMap());
        Page<CallIndexDel> page = new Page<CallIndexDel>(pageNo, pageSize);
        IPage<CallIndexDel> pageList = callIndexDelService.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param callIndexDel
     * @return
     */
    @AutoLog(value = "index_del页面-添加")
    @ApiOperation(value = "index_del页面-添加", notes = "index_del页面-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody CallIndexDel callIndexDel) {
        int size = callIndexDelService.pull(callIndexDel);
        return Result.OK("添加成功！" + size + "条信息");
    }
    /**
     * 添加AddMq
     *
     * @param
     * @return
     */
    @AutoLog(value = "index_del页面-AddMq")
    @ApiOperation(value = "index_del页面-AddMq", notes = "index_del页面-AddMq")
    @PostMapping(value = "/AddMq")
    public Result<?> AddMq() {
        int size = callIndexDelService.AddMq();
        return Result.OK("添加成功！" + size + "条信息");
    }
    /**
     * 编辑
     *
     * @param callIndexDel
     * @return
     */
    @AutoLog(value = "index_del页面-编辑")
    @ApiOperation(value = "index_del页面-编辑", notes = "index_del页面-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody CallIndexDel callIndexDel) {
        callIndexDelService.updateById(callIndexDel);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "index_del页面-通过id删除")
    @ApiOperation(value = "index_del页面-通过id删除", notes = "index_del页面-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        callIndexDelService.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "index_del页面-批量删除")
    @ApiOperation(value = "index_del页面-批量删除", notes = "index_del页面-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.callIndexDelService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功!");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "index_del页面-通过id查询")
    @ApiOperation(value = "index_del页面-通过id查询", notes = "index_del页面-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        CallIndexDel callIndexDel = callIndexDelService.getById(id);
        if (callIndexDel == null) {
            return Result.error("未找到对应数据");
        }
        return Result.OK(callIndexDel);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param callIndexDel
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, CallIndexDel callIndexDel) {
        return super.exportXls(request, callIndexDel, CallIndexDel.class, "index_del页面");
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, CallIndexDel.class);
    }

}
