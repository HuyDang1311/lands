package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import constant.Defines;
import dao.CatDao;
import dao.LandsDao;

@Controller
public class PublicLandController {
	@Autowired
	private CatDao catDao;
	@Autowired
	private LandsDao landsDao;
	@ModelAttribute
	public void addCommonObject(ModelMap modelMap){
		modelMap.addAttribute("listCat",catDao.getListCat());
		
	}
	
	@RequestMapping(value={"{page}",""})
	public String index(ModelMap modelMap, @PathVariable(value="page",required=false) Integer currentPage){
		if(currentPage == null) {
			currentPage = 1;
		}
		int sumPage = (int)Math.ceil((float)landsDao.countItem()/Defines.ROW_COUNT);
		int offset = (currentPage -1)*Defines.ROW_COUNT;
		modelMap.addAttribute("sumPage",sumPage);
		modelMap.addAttribute("listLands",landsDao.getlistLands(offset));
		modelMap.addAttribute("currentPage",currentPage);
		return "public.land.index";
	}
	
	@RequestMapping("cat")
	public String cat(){
		return "public.land.cat";
	}
	
	@RequestMapping("detail/{id}")
	public String detail(ModelMap modelMap, @PathVariable("id") int id){
		landsDao.View(id);
		modelMap.addAttribute("objLands",landsDao.getObjLand(id));
		modelMap.addAttribute("listLQ",landsDao.getlistLQ(id));
		return "public.land.detail";
	}
}
