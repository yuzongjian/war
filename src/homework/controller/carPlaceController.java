package homework.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import homework.model.carPlace;
import homework.model.PageBean;
import homework.service.carService;
import homework.service.carPlaceService;
import homework.util.PageUtil;
import homework.util.ResponseUtil;
import homework.util.StringUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/carPlace")
public class carPlaceController {

	@Autowired
	private carPlaceService carPlaceService;
	
	@Autowired
	private carService equipmentService;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,carPlace carPlace,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("carPlace", carPlace);
		}else{
			carPlace=(carPlace) session.getAttribute("carPlace");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		List<carPlace> carPlaceList=carPlaceService.find(pageBean, carPlace);
		int total=carPlaceService.count(carPlace);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/carPlace/list.do", total, Integer.parseInt(page), 3);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "停车位管理");
		mav.addObject("carPlaceList", carPlaceList);
		mav.addObject("mainPage", "carPlace/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		mav.addObject("mainPage", "carPlace/save.jsp");
		mav.addObject("modeName", "停车位管理");
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "停车位管理");
			carPlace carPlace=carPlaceService.loadById(Integer.parseInt(id));
			mav.addObject("carPlace", carPlace);
		}else{
			mav.addObject("actionName", "停车位管理");			
		}
		return mav;
	}
	
	@RequestMapping("/save")
	public String save(carPlace carPlace){
		if(carPlace.getId()==null){
			carPlaceService.add(carPlace);			
		}else{
			carPlaceService.update(carPlace);
		}
		return "redirect:/carPlace/list.do";
	}
	
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		if(equipmentService.existEquipmentByTypeId(Integer.parseInt(id))){
			result.put("errorInfo", "���豸�����´����豸������ɾ����");
		}else{
			carPlaceService.delete(Integer.parseInt(id));
			result.put("success", true);						
		}
		ResponseUtil.write(result, response);
	}
}
