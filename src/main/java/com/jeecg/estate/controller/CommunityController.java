package com.jeecg.estate.controller;
import com.jeecg.estate.entity.CommunityEntity;
import com.jeecg.estate.entity.ManagerareaEntity;
import com.jeecg.estate.service.CommunityServiceI;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**   
 * @Title: Controller  
 * @Description: 小区表
 * @author onlineGenerator
 * @date 2019-01-18 15:27:28
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/communityController")
public class CommunityController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(CommunityController.class);

	@Autowired
	private CommunityServiceI communityService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 小区表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request, String fatherId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("com/jeecg/estate/communityList");
		mav.addObject("fatherId", fatherId);
		return mav;
	}
	/**
	 * 返回上一层
	 * @param request
	 * @param response
	 * @param fatherId
	 * @return
	 */
	@RequestMapping(params="goLastOne")
	public ModelAndView goLastOne(HttpServletRequest request,HttpServletResponse response,String fatherId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("com/jeecg/estate/managerareaList");
		List<ManagerareaEntity> mana = systemService.findByProperty(ManagerareaEntity.class, "id", fatherId);
		fatherId=mana.get(0).getFatherId();
		mav.addObject("fatherId", fatherId);
		return mav;
	}
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(String fatherId, CommunityEntity community,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CommunityEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, community, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.eq("fatherId", fatherId);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.communityService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除小区表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CommunityEntity community, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		community = systemService.getEntity(CommunityEntity.class, community.getId());
		message = "小区表删除成功";
		try{
			communityService.delete(community);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "小区表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除小区表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "小区表删除成功";
		try{
			for(String id:ids.split(",")){
				CommunityEntity community = systemService.getEntity(CommunityEntity.class, 
				id
				);
				communityService.delete(community);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "小区表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加小区表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CommunityEntity community, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "小区表添加成功";
		try{
			communityService.save(community);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "小区表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新小区表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CommunityEntity community, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "小区表更新成功";
		CommunityEntity t = communityService.get(CommunityEntity.class, community.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(community, t);
			communityService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "小区表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 小区表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CommunityEntity community, HttpServletRequest req, String fatherId) {
		if (StringUtil.isNotEmpty(community.getId())) {
			community = communityService.getEntity(CommunityEntity.class, community.getId());
			req.setAttribute("community", community);
		}
		//System.out.println("获得到的fatherId:"+fatherId);
		ManagerareaEntity area = systemService.get(ManagerareaEntity.class, fatherId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("com/jeecg/estate/community-add");
		mav.addObject("fatherId", fatherId);
		mav.addObject("fatherName", area.getAreaName());
		return mav;
	}
	/**
	 * 小区表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CommunityEntity community, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(community.getId())) {
			community = communityService.getEntity(CommunityEntity.class, community.getId());
			req.setAttribute("community", community);
		}
		return new ModelAndView("com/jeecg/estate/community-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","communityController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CommunityEntity community,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CommunityEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, community, request.getParameterMap());
		List<CommunityEntity> communitys = this.communityService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"小区表");
		modelMap.put(NormalExcelConstants.CLASS,CommunityEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("小区表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,communitys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CommunityEntity community,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"小区表");
    	modelMap.put(NormalExcelConstants.CLASS,CommunityEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("小区表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<CommunityEntity> listCommunityEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CommunityEntity.class,params);
				for (CommunityEntity community : listCommunityEntitys) {
					communityService.save(community);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
}
