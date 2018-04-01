package homework.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import homework.model.PageBean;
import homework.model.car;
import homework.model.carPlace;
import homework.service.carPlaceService;
import homework.service.carService;
import homework.util.PageUtil;
import homework.util.ResponseUtil;
import homework.util.StringUtil;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/car")
public class carController {

	@Autowired
	private carService carService;
	
	@Autowired
	private carPlaceService carPlaceService;
	
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value="page",required=false)String page,car car,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("car", car);
		}else{
			car=(car) session.getAttribute("car");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		List<car> carList=carService.find(pageBean, car);
		int total=carService.count(car);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/car/list.do", total, Integer.parseInt(page), 3);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "停车管理");
		mav.addObject("carList", carList);
		mav.addObject("mainPage", "car/list.jsp");
		mav.setViewName("main");
		return mav;
	}
	
	
	@RequestMapping("/delete")
	public void delete(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		carService.delete(Integer.parseInt(id));
		result.put("success", true);			
		ResponseUtil.write(result, response);
	}
	
	

	@RequestMapping("/giveFee")
	public void giveFee(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		carService.giveFee(Integer.parseInt(id));
		result.put("success", true);			
		ResponseUtil.write(result, response);
	}
	
	@RequestMapping("/preSave")
	public ModelAndView preSave(@RequestParam(value="id",required=false)String id){
		ModelAndView mav=new ModelAndView();
		List<carPlace> carPlaceList=carPlaceService.find(null, null);
		mav.addObject("mainPage", "car/save.jsp");
		mav.addObject("modeName", "车辆入库");
		mav.addObject("carPlaceList",carPlaceList);
		mav.setViewName("main");
		if(StringUtil.isNotEmpty(id)){
			mav.addObject("actionName", "车辆修改");
			car car=carService.loadById(Integer.parseInt(id));
			mav.addObject("car", car);
		}else{
			mav.addObject("actionName", "车辆添加");			
		}
		return mav;
	}
	
	@RequestMapping("/save")
	public String save(car car,Map<String,String> context)throws Exception{
		
		if(carService.existPlaceByTypeId(car) ){
			carService.add(car);	
		return "redirect:/car/preSave.do"; 
			
		}else{
			
			context.put("error","error");   // url传车位已停满错误过去
			
			return "redirect:/car/preSave.do"; 
		}
		
		
	}
	
	
	
	@RequestMapping("/userList")
	public ModelAndView userList(@RequestParam(value="page",required=false)String page,car car,HttpServletRequest request){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		if(StringUtil.isEmpty(page)){
			page="1";
			session.setAttribute("car", car);
		}else{
			car=(car) session.getAttribute("car");
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),3);
		List<car> carList=carService.find(pageBean, car);
		int total=carService.count(car);
		String pageCode=PageUtil.getPagation(request.getContextPath()+"/car/userList.do", total, Integer.parseInt(page), 3);
		mav.addObject("pageCode", pageCode);
		mav.addObject("modeName", "车辆管理");
		mav.addObject("carList", carList);
		mav.addObject("mainPage", "car/userList.jsp");
		mav.setViewName("main");
		return mav;
	}
	
}
