package controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import constant.Defines;
import dao.CatDao;
import dao.LandsDao;
import entity.Cat;
import entity.Lands;
import entity.User;
import util.Regular;

@Controller
@RequestMapping("admin/lands")
public class AdminLandsController {
	@Autowired
	private LandsDao landsDao;
	@Autowired
	private CatDao catDao;
	@Autowired
	private Regular regular;
	@RequestMapping(value = { "{page}", "" })
	public String index(@PathVariable(value = "page", required = false) Integer currentPage, ModelMap modelMap) {
		if (currentPage == null) {
			currentPage = 1;
		}
		int sumPage = (int) Math.ceil((float) landsDao.countItem() / Defines.ROW_COUNT);
		int offset = (currentPage - 1) * Defines.ROW_COUNT;
		modelMap.addAttribute("listLands", landsDao.getlistLands(offset));
		modelMap.addAttribute("sumPage", sumPage);
		modelMap.addAttribute("currentPage", currentPage);
		return "admin.lands.index";
	}

	@RequestMapping("add")
	public String add(ModelMap modelMap) {
		modelMap.addAttribute("listCat", catDao.getListCat());
		return "admin.lands.add";
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("objLands") Lands objLands, ModelMap modelMap, @RequestParam("ciddd") String id_danhmuc, RedirectAttributes ra, @RequestParam("hinhanh") CommonsMultipartFile cmf, HttpServletRequest request) {
		List<String> err = new ArrayList<String>();
		checkErrAdd(err, objLands, id_danhmuc);	
		if(err.size() > 0) {
			modelMap.addAttribute("err",err);
			modelMap.addAttribute("listCat", catDao.getListCat());
			return "admin.lands.add";
		} else {
			String filename = cmf.getOriginalFilename();
			if (!filename.isEmpty()) {
				String appPath = request.getServletContext().getRealPath("");
				String dirPath = appPath + "files";
				File saveDir = new File(dirPath);
				if (!saveDir.exists()) {
					saveDir.mkdir();
				}
				String filePath = dirPath + File.separator + filename;
				System.out.println(filePath);
				File file = new File(filePath);
				try {
					cmf.transferTo(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				filename = "noimage.jpg";
			}
			int cid = Integer.parseInt(id_danhmuc);
			Timestamp date = new Timestamp(new Date().getTime());
			String lname = regular.checkSpace(objLands.getLname());
			String address = regular.checkSpace(objLands.getAddress());
			if (landsDao.getAddLands(objLands, filename, date, cid, lname, address) > 0) {
				ra.addFlashAttribute("msg", "Bạn đã thêm thành công !");
				return "redirect:/admin/lands";
			} else {
				ra.addFlashAttribute("err", "Bạn đã thêm không thành công !");
				return "redirect:/admin/lands";
			}
		}
			
		
	}
	
	@RequestMapping("edit/{id}")
	public String edit(ModelMap modelMap, @PathVariable("id") String lid, RedirectAttributes ra) {
		try {
			int id = Integer.parseInt(lid);
			if(landsDao.checkID(id).size() == 0) {
				ra.addFlashAttribute("err","ID không tồn tại !");
				return "redirect:/admin/lands";
			}else {
				modelMap.addAttribute("listCat", catDao.getListCat());
				modelMap.addAttribute("objLand",landsDao.getObjLand(id));
				return "admin.lands.edit";
			}
		} catch (NumberFormatException e) {
			ra.addFlashAttribute("err","Lỗi địa chỉ URL!");
			return "redirect:/admin/lands";
		}
	}
	
	@RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") int id, @ModelAttribute("objLands") Lands objLands, RedirectAttributes ra, ModelMap modelMap, @RequestParam("ciddd") String id_danhmuc, @RequestParam("hinhanh") CommonsMultipartFile cmf, HttpServletRequest request) {
		List<String> err = new ArrayList<String>();
		checkErrEdit(err,objLands,id_danhmuc);	
		if(err.size() > 0) {
			modelMap.addAttribute("err",err);
			modelMap.addAttribute("listCat", catDao.getListCat());
			modelMap.addAttribute("objLand",landsDao.getObjLand(id));
			return "admin.lands.edit";
		} 
		else {
			String filename = cmf.getOriginalFilename();
			if (!filename.isEmpty()) {
				String appPath = request.getServletContext().getRealPath("");
				String dirPath = appPath + "files";
				File saveDir = new File(dirPath);
				if (!saveDir.exists()) {
					saveDir.mkdir();
				}
				String filePath = dirPath + File.separator + filename;
				System.out.println(filePath);
				File file = new File(filePath);
				try {
					cmf.transferTo(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				filename = landsDao.getObjLand(id).getPicture();
			}
			int cid = Integer.parseInt(id_danhmuc);
			String lname = regular.checkSpace(objLands.getLname());
			String address = regular.checkSpace(objLands.getAddress());
			Timestamp date = new Timestamp(new Date().getTime());
			if (landsDao.getEditLands(id, objLands,date, cid, lname, address, filename) > 0) {
				ra.addFlashAttribute("msg", "Bạn đã sửa thành công !");
			} else {
				ra.addFlashAttribute("err", "Bạn đã sửa không thành công !");
			}
			return "redirect:/admin/lands";
		}
	}
	
	@RequestMapping("del/{id}")
	public String edit(@PathVariable("id") String lid, RedirectAttributes ra) {
		try {
			int id = Integer.parseInt(lid);
			if(landsDao.checkID(id).size() == 0) {
				ra.addFlashAttribute("err","ID không tồn tại !");
				return "redirect:/admin/lands";
			}else {
				if (landsDao.getDelUser(id) > 0) {
					ra.addFlashAttribute("msg", "Bạn đã xóa thành công !");
				} else {
					ra.addFlashAttribute("err", "Bạn đã xóa không thành công !");
				}
				return "redirect:/admin/lands";
			}
		} catch (NumberFormatException e) {
			ra.addFlashAttribute("err","Lỗi địa chỉ URL!");
			return "redirect:/admin/lands";
		}
	}
		
	private void checkErrAdd(List<String> err, Lands objLands, String id_danhmuc) {
		if (regular.checkNameEmpty(objLands.getLname()) == true) {
			err.add("Tên tin không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkTenTin(objLands.getLname()) == false) {
				err.add("Tên tin tối thiểu phải 5 kí tự! Mời bạn nhập lại");
			}
		}
		
		try {
			int cid = Integer.parseInt(id_danhmuc);
			if(cid == 0) {
				err.add("Vui lòng chọn danh mục nhà đất!");
			} else {
				if(catDao.checkID(cid).size() == 0) {
					err.add("Không tồn tại ID danh mục tin!");
				}
			}
		} catch (NumberFormatException e) {
			err.add("Lỗi ID danh mục tin!");
		}
		
		if (regular.checkNameEmpty(objLands.getAddress()) == true) {
			err.add("Địa chỉ không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} 
		
		if (regular.checkNameEmpty(objLands.getTenlh()) == true) {
			err.add("Tên liên hệ không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkFullName(objLands.getTenlh()) == false) {
				err.add("Tên liên hệ có độ dài tối thiểu 3 ký tự và tối đa 32 ký tự và không có kí tự số !");
			}
		}
		
		if (regular.checkNameEmpty(objLands.getDclienhe()) == true) {
			err.add("Địa chỉ liên hệ không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} 
		
		if (regular.checkNameEmpty(objLands.getSdt()) == true) {
			err.add("Số điện thoại không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkSdt(objLands.getSdt()) == false) {
				err.add("Số điện thoại không đúng! Mời bạn nhập lại");
			}
		}
		
		if ("".equals(objLands.getDescription())) {
			err.add("Mô tả không được trống! Mời bạn nhập lại");
		} 
	}
	
	private void checkErrEdit(List<String> err, Lands objLands, String id_danhmuc) {
		if (regular.checkNameEmpty(objLands.getLname()) == true) {
			err.add("Tên tin không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkTenTin(objLands.getLname()) == false) {
				err.add("Tên tin tối thiểu phải 5 kí tự! Mời bạn nhập lại");
			}
		}
		
		try {
			int cid = Integer.parseInt(id_danhmuc);
			if(catDao.checkID(cid).size() == 0) {
				err.add("Không tồn tại ID danh mục tin!");
			}
		} catch (NumberFormatException e) {
			err.add("Lỗi ID danh mục tin!");
		}
		
		if (regular.checkNameEmpty(objLands.getAddress()) == true) {
			err.add("Địa chỉ không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} 
		
		if (regular.checkNameEmpty(objLands.getTenlh()) == true) {
			err.add("Tên liên hệ không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkFullName(objLands.getTenlh()) == false) {
				err.add("Tên liên hệ có độ dài tối thiểu 3 ký tự và tối đa 32 ký tự và không có kí tự số !");
			}
		}
		
		if (regular.checkNameEmpty(objLands.getDclienhe()) == true) {
			err.add("Địa chỉ liên hệ không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} 
		
		if (regular.checkNameEmpty(objLands.getSdt()) == true) {
			err.add("Số điện thoại không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkSdt(objLands.getSdt()) == false) {
				err.add("Số điện thoại không đúng! Mời bạn nhập lại");
			}
		}
		
		if ("".equals(objLands.getDescription())) {
			err.add("Mô tả không được trống! Mời bạn nhập lại");
		} 
		
	}
}
