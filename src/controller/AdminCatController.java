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
import entity.Cat;
import util.Regular;

@Controller
@RequestMapping("admin/cat")
public class AdminCatController {
	@Autowired
	private CatDao catDao;
	@Autowired
	private Regular regular;
	@RequestMapping({"{page}",""})
	public String index(ModelMap modelMap, @PathVariable(value = "page", required = false) Integer currentPage){
		if(currentPage == null) {
			currentPage = 1;
		}
		int sumPage = (int)Math.ceil((float)catDao.countCat()/Defines.ROW_COUNT);
		int offset = (currentPage-1)*Defines.ROW_COUNT;
		modelMap.addAttribute("sumPage",sumPage);
		modelMap.addAttribute("listCat",catDao.getListCat(offset));
		modelMap.addAttribute("currentPage",currentPage);
		return "admin.cat.index";
	}
	
	@RequestMapping("add")
	public String add(){
		return "admin.cat.add";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String add(@RequestParam("name") String name, RedirectAttributes ra){
		if(regular.checkNameEmpty(name) == true) {
			ra.addFlashAttribute("err","Tên danh mục không được trống!");
			return "redirect:/admin/cat/add";
		}else if(regular.checkSoKiTu(name) == false) {
			ra.addFlashAttribute("err","Tên danh mục tối thiểu phải 3 kí tự!");
			return "redirect:/admin/cat/add";
		}else if(catDao.checkTrungTen(regular.checkSpace(name)).size() > 0) {
			ra.addFlashAttribute("err","Trùng tên danh mục tin !");
			return "redirect:/admin/cat/add";
		} else {
			String cname = regular.checkSpace(name);
			if(catDao.getAddCat(cname)>0) {
				ra.addFlashAttribute("msg","Bạn đã thêm thành công !");
				return "redirect:/admin/cat";
			}else {
				ra.addFlashAttribute("err","Bạn đã thêm không thành công !");
				return "redirect:/admin/cat";
			}
		}
		
	}
	
	@RequestMapping("edit/{cid}")
	public String edit(ModelMap modelMap, @PathVariable("cid") String cid, RedirectAttributes ra){
			try {
				int id = Integer.parseInt(cid);
				if(catDao.checkID(id).size() == 0) {
					ra.addFlashAttribute("err","ID không tồn tại !");
					return "redirect:/admin/cat";
				}else {
					modelMap.addAttribute("objCat", catDao.getObjCat(id));
					return "admin.cat.edit";
				}
			} catch (NumberFormatException e) {
				ra.addFlashAttribute("err","Lỗi địa chỉ URL!");
				return "redirect:/admin/cat";
			}
	}
	
	@RequestMapping(value="edit/{cid}", method=RequestMethod.POST)
	public String edit(@PathVariable("cid") int cid, RedirectAttributes ra, @RequestParam("cname") String name) {
			if(regular.checkNameEmpty(name) == true) {
				ra.addFlashAttribute("err","Tên danh mục không được trống !");
				return "redirect:/admin/cat/edit/{cid}";
			}else if(regular.checkSoKiTu(name) == false) {
				ra.addFlashAttribute("err","Tên danh mục tối thiểu phải 3 kí tự!");
				return "redirect:/admin/cat/edit/{cid}";
			}else if(catDao.checkTrungTen(cid, regular.checkSpace(name)).size() > 0) {
				ra.addFlashAttribute("err","Trùng tên danh mục tin !");
				return "redirect:/admin/cat/edit/{cid}";
			} else {
				String cname = regular.checkSpace(name);
				System.out.println(cname);
				if(catDao.getEditCat(new Cat(cid, cname))>0) {
					ra.addFlashAttribute("msg","Bạn đã sửa thành công !");
				}else {
					ra.addFlashAttribute("err","Bạn đã sửa không thành công !");
				}
				return "redirect:/admin/cat";
			}
	}
	
	@RequestMapping("del/{cid}")
	public String del(@PathVariable("cid") String cid, RedirectAttributes ra){
		try {
			int id = Integer.parseInt(cid);
			if(catDao.checkID(id).size() == 0) {
				ra.addFlashAttribute("err","ID không tồn tại !");
				return "redirect:/admin/cat";
			}else {
				if(catDao.getDelCat(id) > 0) {
					ra.addFlashAttribute("msg","Bạn đã xóa thành công !");
				}else {
					ra.addFlashAttribute("err","Bạn đã xóa không thành công !");
				}
				return "redirect:/admin/cat";
			}
		} catch (NumberFormatException e) {
			ra.addFlashAttribute("err","Lỗi địa chỉ URL!");
			return "redirect:/admin/cat";
		}
	}
	
	
	
	
	
	
	
}
