package controller;

import javax.websocket.server.PathParam;

import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
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
import entity.Cat;
import util.Regular;

@Controller
@RequestMapping("admin/contact")
public class AdminContactController {
	@Autowired
	private ContactDao contactDao;
	@RequestMapping({"{page}",""})
	public String index(ModelMap modelMap, @PathVariable(value = "page", required = false) Integer currentPage){
		if(currentPage == null) {
			currentPage = 1;
		}
		int sumPage = (int)Math.ceil((float)contactDao.countContact()/Defines.ROW_COUNT);
		int offset = (currentPage-1)*Defines.ROW_COUNT;
		modelMap.addAttribute("sumPage",sumPage);
		modelMap.addAttribute("listContact",contactDao.getListContact(offset));
		modelMap.addAttribute("currentPage",currentPage);
		return "admin.contact.index";
	}
	
	@RequestMapping("del/{id}")
	public String edit(@PathVariable("id") String lid, RedirectAttributes ra) {
		try {
			int id = Integer.parseInt(lid);
			if(contactDao.getCheckID(id).size() == 0) {
				ra.addFlashAttribute("err","ID không tồn tại !");
				return "redirect:/admin/contact";
			}else {
				if (contactDao.getDelContact(id) > 0) {
					ra.addFlashAttribute("msg", "Bạn đã xóa thành công !");
				} else {
					ra.addFlashAttribute("err", "Bạn đã xóa không thành công !");
				}
				return "redirect:/admin/contact";
			}
		} catch (NumberFormatException e) {
			ra.addFlashAttribute("err","Lỗi địa chỉ URL!");
			return "redirect:/admin/contact";
		}
	}
}
