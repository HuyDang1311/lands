package controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.CatDao;
import dao.LandsDao;
import dao.UserDao;

@Controller
@RequestMapping("admin")
public class AdminIndexController {
	@Autowired
	private CatDao catDao;
	@Autowired
	private LandsDao landsDao;
	@Autowired
	private UserDao userDao;
	
	@ModelAttribute
	public void addCommon(ModelMap modelMap, Principal principal){
		modelMap.addAttribute("Fullname",userDao.getcheckOldPass(principal.getName()).getFullname());
	}
	@RequestMapping("")
	public String index(Principal principal, ModelMap modelMap){
		modelMap.addAttribute("countCat",catDao.countCat());
		modelMap.addAttribute("countLands",landsDao.countItem() );
		modelMap.addAttribute("countUser",userDao.countUser());
		System.out.println("username: "+principal.getName());
		return "admin.index.index";
	}
}
