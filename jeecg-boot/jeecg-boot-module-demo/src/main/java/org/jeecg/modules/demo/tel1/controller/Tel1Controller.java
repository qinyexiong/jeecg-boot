package org.jeecg.modules.demo.tel1.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.date.DateUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.demo.tel1.entity.Tel1;
import org.jeecg.modules.demo.tel1.service.ITel1Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 测试表
 * @Author: jeecg-boot
 * @Date: 2021-03-26
 * @Version: V1.0
 */
@Slf4j
@Api(tags = "测试表")
@RestController
@RequestMapping("/tel1")
public class Tel1Controller extends JeecgController<Tel1, ITel1Service> {
    @Autowired
    private ITel1Service tel1Service;

    /**
     * 分页列表查询
     *
     * @param tel1
     * @param pageNo
     * @param pageSize
     * @param req
     * @return
     */
    @AutoLog(value = "测试表-分页列表查询")
    @ApiOperation(value = "测试表-分页列表查询", notes = "测试表-分页列表查询")
    @GetMapping(value = "/list")
    public Result<?> queryPageList(Tel1 tel1,
                                   @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                   @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                   HttpServletRequest req) {
        if (!ObjectUtils.anyNotNull(tel1)) {
            tel1.setCreateTime(new Date());
            QueryWrapper<Tel1> queryWrapper = new QueryWrapper();
            queryWrapper.lambda().select().between(
                    Tel1::getCreateTime, new Date(), DateUtil.offsetMillisecond(new Date(), 1));
            Page<Tel1> page = new Page<Tel1>(pageNo, pageSize);
            IPage<Tel1> pageList = tel1Service.page(page, queryWrapper);
            return Result.OK(pageList);
        }
        QueryWrapper<Tel1> queryWrapper = QueryGenerator.initQueryWrapper(tel1, req.getParameterMap());
        Page<Tel1> page = new Page<Tel1>(pageNo, pageSize);
        IPage<Tel1> pageList = tel1Service.page(page, queryWrapper);
        return Result.OK(pageList);
    }

    /**
     * 添加
     *
     * @param tel1
     * @return
     */
    @AutoLog(value = "测试表-添加")
    @ApiOperation(value = "测试表-添加", notes = "测试表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Tel1 tel1) {
        tel1Service.save1(tel1);
        return Result.OK("添加成功！");
    }

    /**
     * 编辑
     *
     * @param tel1
     * @return
     */
    @AutoLog(value = "测试表-编辑")
    @ApiOperation(value = "测试表-编辑", notes = "测试表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Tel1 tel1) {
        tel1Service.updateById(tel1);
        return Result.OK("编辑成功!");
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @AutoLog(value = "测试表-通过id删除")
    @ApiOperation(value = "测试表-通过id删除", notes = "测试表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name = "id", required = true) String id) {
        tel1Service.removeById(id);
        return Result.OK("删除成功!");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @AutoLog(value = "测试表-批量删除")
    @ApiOperation(value = "测试表-批量删除", notes = "测试表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        this.tel1Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.OK("批量删除成功！");
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @AutoLog(value = "测试表-通过id查询")
    @ApiOperation(value = "测试表-通过id查询", notes = "测试表-通过id查询")
    @GetMapping(value = "/queryById")
    public Result<?> queryById(@RequestParam(name = "id", required = true) String id) {
        Tel1 tel1 = tel1Service.getById(id);
        return Result.OK(tel1);
    }

    /**
     * 导出excel
     *
     * @param request
     * @param tel1
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Tel1 tel1) {
        return super.exportXls(request, tel1, Tel1.class, "测试表");
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
        return super.importExcel(request, response, Tel1.class);
    }

}
