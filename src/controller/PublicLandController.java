package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import constant.Defines;
import dao.CatDao;
import dao.ContactDao;
import dao.LandsDao;
import entity.Contact;
import entity.User;
import util.Regular;
import util.SlugUtil;
import util.StringUtil;

@Controller
public class PublicLandController {
	@Autowired
	private CatDao catDao;
	@Autowired
	private LandsDao landsDao;
	@Autowired
	private StringUtil stringUtil;
	@Autowired
	private ContactDao contactDao;
	@Autowired
	private Regular regular;
	@Autowired
	private SlugUtil slugUtil;
	
	@ModelAttribute
	public void addCommonObject(ModelMap modelMap) {
		modelMap.addAttribute("listCat", catDao.getListCat());
		modelMap.addAttribute("listView", landsDao.listView());
		modelMap.addAttribute("listCare", landsDao.listCare());
		modelMap.addAttribute("catDao", catDao);
		modelMap.addAttribute("stringUtil", stringUtil);
		modelMap.addAttribute("slugUtil", slugUtil);

	}

	@RequestMapping(value = { "{page}", "" })
	public String index(ModelMap modelMap, @PathVariable(value = "page", required = false) Integer currentPage) {
		if (currentPage == null) {
			currentPage = 1;
		}
		int sumPage = (int) Math.ceil((float) landsDao.countItem() / Defines.ROW_COUNT);
		int offset = (currentPage - 1) * Defines.ROW_COUNT;
		modelMap.addAttribute("sumPage", sumPage);
		modelMap.addAttribute("slidefirst", landsDao.getslidefirst());
		modelMap.addAttribute("slidesecond", landsDao.getslidesecond());
		modelMap.addAttribute("listLands", landsDao.getlistLands(offset));
		modelMap.addAttribute("stringUtil", stringUtil);
		modelMap.addAttribute("currentPage", currentPage);
		modelMap.addAttribute("slugUtil", slugUtil);
		return "public.land.index";
	}
	
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(@RequestParam("search") String search, ModelMap modelMap) {
		System.out.println(search);
		modelMap.addAttribute("objLandCat", landsDao.getListSearchPuclic(search));
		 return "public.land.cat";
	}

	@RequestMapping(value = {"danh-muc/{name}-{id}/{page}","danh-muc/{name}-{id}"})
	public String cat(@PathVariable("id") String cid, RedirectAttributes ra, ModelMap modelMap, @PathVariable(value = "page", required = false) Integer currentPage) {
		try {
			 int id = Integer.parseInt(cid);
			 if(catDao.checkID(id).size() == 0) {
				 return "redirect:/404";
			 }else {
				 if (currentPage == null) {
						currentPage = 1;
					}
				 int sumPage = (int) Math.ceil((float) landsDao.countItem(id) / Defines.ROW_COUNT);
				 int offset = (currentPage - 1) * Defines.ROW_COUNT;
				 modelMap.addAttribute("sumPage", sumPage); 
				 modelMap.addAttribute("currentPage", currentPage);
				 modelMap.addAttribute("objLandCat", landsDao.getLandCat(id,offset));
				 modelMap.addAttribute("objCat", catDao.getObjCat(id));
				 return "public.land.cat";
			}
		 } catch (NumberFormatException e) {
			 return "redirect:/404";
		 }
	}

	@RequestMapping("chi-tiet/{name}-{id}.html")
	public String detail(ModelMap modelMap, @PathVariable("id") String did) {
		try {
			 int id = Integer.parseInt(did);
			 if(landsDao.checkID(id).size() == 0) {
				 return "redirect:/404";
			 }else {
				landsDao.View(id);
				modelMap.addAttribute("objLands", landsDao.getObjLand(id));
				modelMap.addAttribute("listLQ", landsDao.getlistLQ(id));
				return "public.land.detail";
			}
		 } catch (NumberFormatException e) {
			 return "redirect:/404";
		 }
	}
	
	@RequestMapping("lien-he")
	public String contact() {
		return "public.land.contact";
	}
	
	@RequestMapping("gioi-thieu")
	public String gioithieu() {
		return "public.land.gioithieu";
	}
	
	@RequestMapping(value="lien-he", method=RequestMethod.POST)
	public String contact(ModelMap modelMap, @ModelAttribute("objContact") Contact objContact, RedirectAttributes ra) {
		List<String> err = new ArrayList<String>();
		checkErrContact(objContact, err);
		if (err.size() > 0) {
			modelMap.addAttribute("listErr", err);
			return "public.land.contact";
		} else {
			String fullname = regular.checkSpace(objContact.getFullname());
			if (contactDao.getAddContact(objContact, fullname) > 0) {
				ra.addFlashAttribute("msg", "Cảm ơn bạn đã liên hệ chúng tôi! Chúng tôi sẽ trả lời bạn sớm nhất có thể.");
			} else {
				ra.addFlashAttribute("err", "Bạn đã liên hệ không thành công !");
			}
			return "redirect:/contact";
		}
	}
	
	@RequestMapping(value = "quantam", method = RequestMethod.POST)
	public void active(@RequestParam("aid") int id, @RequestParam("astatus") int status,
			@RequestParam("aqTam") int qTam, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		int quanTam = 0;

		if (status == 0) {
			quanTam = qTam + 1;
			landsDao.getQuanTam0(id, quanTam);
			out.print(
					"<a  href='javascript:void(0)' title='' class='btn btn-primary'  style='color: white;margin-left: 18px;'  onclick='return getQuantam("
							+ id + ",1)'>Quan Tâm</a>");
		} else {
			quanTam = qTam - 1;
			landsDao.getQuanTam1(id, quanTam);
			out.print(
					"<a  href='javascript:void(0)' title='' class='btn btn-warning' style='color: white;margin-left: 18px;'  onclick='return getQuantam("
							+ id + ",0)'>Quan Tâm</a>");
		}
		out.print("<span id='valu' style='margin: 15px;'>" + quanTam + "</span>");
	}
	
	@RequestMapping("/404")
	public String err404(){
		return "err.404";
	}
	
	private void checkErrContact(Contact objContact, List<String> err) {
		if (regular.checkNameEmpty(objContact.getFullname()) == true) {
			err.add("Họ tên không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkFullName(objContact.getFullname()) == false) {
				err.add("Họ tên có độ dài tối thiểu 3 ký tự và tối đa 32 ký tự và không có kí tự số !");
			}
		}
		
		if (regular.checkNameEmpty(objContact.getEmail()) == true) {
			err.add("Email không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} 
		
		if (regular.checkNameEmpty(objContact.getContent()) == true) {
			err.add("Nội dung không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} 
		
	}
}
