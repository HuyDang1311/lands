package controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.CatDao;
import dao.LandsDao;
import dao.UserDao;
import entity.User;

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
	public void addCommon(ModelMap modelMap, Principal principal, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("user", userDao.getcheckOldPass(principal.getName()));
	}

	@RequestMapping("")
	public String index(Principal principal, ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute("countCat", catDao.countCat());
		modelMap.addAttribute("countLands", landsDao.countItem());
		modelMap.addAttribute("countUser", userDao.countUser());
		User user = (User) session.getAttribute("user");
		modelMap.addAttribute("user",user);
		return "admin.index.index";
	}
}
