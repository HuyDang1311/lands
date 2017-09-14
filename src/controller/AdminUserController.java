package controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import dao.RankDao;
import dao.UserDao;
import entity.Cat;
import entity.User;
import util.BCrypt;
import util.Regular;

@Controller
@RequestMapping("admin/user")
public class AdminUserController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private RankDao rankDao;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private Regular regular;

	@RequestMapping({ "{page}", "" })
	public String index(ModelMap modelMap, @PathVariable(value = "page", required = false) Integer currentPage, HttpSession session) {
		if (currentPage == null) {
			currentPage = 1;
		}
		int sumPage = (int) Math.ceil((float) userDao.countUser() / Defines.ROW_COUNT);
		int offset = (currentPage - 1) * Defines.ROW_COUNT;
		modelMap.addAttribute("sumPage", sumPage);
		modelMap.addAttribute("listUser", userDao.getListUser(offset));
		modelMap.addAttribute("currentPage", currentPage);
		User user = (User) session.getAttribute("user");
		modelMap.addAttribute("user",user);
		return "admin.user.index";
	}
	
	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search(@RequestParam("search") String search, ModelMap modelMap,HttpSession session) {
		System.out.println(search);
		modelMap.addAttribute("listUser", userDao.getListSearch(search));
		User user = (User) session.getAttribute("user");
		modelMap.addAttribute("user",user);
		return "admin.user.index";
	}

	@RequestMapping("add")
	public String add(ModelMap modelMap,HttpSession session, RedirectAttributes ra) {
		User user = (User) session.getAttribute("user");
		if(user.getRank_id() == 1) {
			modelMap.addAttribute("listRank", userDao.getListRank());
			return "admin.user.add";
		} else {
			ra.addFlashAttribute("err", "Bạn không có quyền thêm user!");
			return "redirect:/admin/user";
		}
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(@ModelAttribute("objUser") User objUser, RedirectAttributes ra, ModelMap modelMap,
			@RequestParam("rid") String id_rank) {
		String password = util.BCrypt.hashpw(objUser.getPassword(), util.BCrypt.gensalt());
		List<String> err = new ArrayList<String>();
		checkErrAdd(objUser, err, id_rank);
		if (err.size() > 0) {
			modelMap.addAttribute("err", err);
			modelMap.addAttribute("listRank", userDao.getListRank());
			return "admin.user.add";
		} else {
			int rid = Integer.parseInt(id_rank);
			String fullname = regular.checkSpace(objUser.getFullname());
			if (userDao.getAddUser(objUser, fullname, password, rid) > 0) {
				ra.addFlashAttribute("msg", "Bạn đã thêm thành công !");
			} else {
				ra.addFlashAttribute("err", "Bạn đã thêm không thành công !");
			}
			return "redirect:/admin/user";
		}
	}

	@RequestMapping("edit/{id}")
	public String edit(ModelMap modelMap, @PathVariable("id") String uid, RedirectAttributes ra, HttpSession session) {
		int id1 = Integer.parseInt(uid);
		User user = (User) session.getAttribute("user");
		if(user.getRank_id() == 1 || user.getId() == id1 ) {
		try {
			int id = Integer.parseInt(uid);
			if (userDao.checkID(id).size() == 0) {
				ra.addFlashAttribute("err", "ID không tồn tại !");
				return "redirect:/admin/user";
			} else {
				modelMap.addAttribute("listRank", userDao.getListRank());
				modelMap.addAttribute("objUser", userDao.getObjUser(id));
				return "admin.user.edit";
			}
		} catch (NumberFormatException e) {
			ra.addFlashAttribute("err", "Lỗi địa chỉ URL!");
			return "redirect:/admin/user";
		}
		}else {
			ra.addFlashAttribute("err", "Bạn không có quyền sửa user!");
			return "redirect:/admin/user";
		}
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") int id, Principal principal, @ModelAttribute("objUser") User objUser,
			RedirectAttributes ra, ModelMap modelMap, @RequestParam("rid") String id_rank,
			@RequestParam("oldpassword") String oldpassword) {
		List<String> err = new ArrayList<String>();
		checkErrEdit(principal, objUser, err, id_rank, oldpassword, id);
		if (err.size() > 0) {
			modelMap.addAttribute("err", err);
			modelMap.addAttribute("listRank", userDao.getListRank());
			modelMap.addAttribute("objUser", userDao.getObjUser(id));
			return "admin.user.edit";
		} else {
			String fullname = regular.checkSpace(objUser.getFullname());
			int rid = Integer.parseInt(id_rank);
			String password = "";
			if ("".equals(objUser.getRepassword()) && "".equals(objUser.getPassword())) {
				password = userDao.getObjUser(id).getPassword();
			} else {
				password = util.BCrypt.hashpw(objUser.getPassword(), util.BCrypt.gensalt());
			}
			if (userDao.getEditUser(id, fullname, password, rid) > 0) {
				ra.addFlashAttribute("msg", "Bạn đã sửa thành công !");
			} else {
				ra.addFlashAttribute("err", "Bạn đã sửa không thành công !");
			}
			return "redirect:/admin/user";
		}
	}

	@RequestMapping("del/{id}")
	public String edit(@PathVariable("id") String uid, RedirectAttributes ra,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user.getRank_id() == 1) {
			try {
				int id = Integer.parseInt(uid);
				if (userDao.checkID(id).size() == 0) {
					ra.addFlashAttribute("err", "ID không tồn tại !");
					return "redirect:/admin/user";
				} else {
					if (userDao.getDelUser(id) > 0) {
						ra.addFlashAttribute("msg", "Bạn đã xóa thành công !");
					} else {
						ra.addFlashAttribute("err", "Bạn đã xóa không thành công !");
					}
					return "redirect:/admin/user";
				}
			} catch (NumberFormatException e) {
				ra.addFlashAttribute("err", "Lỗi địa chỉ URL!");
				return "redirect:/admin/user";
			}
		} else {
			ra.addFlashAttribute("err", "Bạn không có quyền xóa user!");
			return "redirect:/admin/user";
		}
	}

	private void checkErrAdd(User objUser, List<String> err, String id_rank) {
		if (regular.checkNameEmpty(objUser.getUsername()) == true) {
			err.add("Username không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkUserName(objUser.getUsername()) == false) {
				err.add("Username có độ dài tối thiểu 5 ký tự và tối đa 32 ký tự, không có khoảng trắng, dấu tiếng việt và kí tự đặc biệt.!");
			} else {
				if (userDao.checkName(objUser.getUsername()).size() > 0) {
					err.add("Trùng username !");
				}
			}
		}

		if (regular.checkNameEmpty(objUser.getFullname()) == true) {
			err.add("Fullname không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkFullName(objUser.getFullname()) == false) {
				err.add("Fullname có độ dài tối thiểu 3 ký tự và tối đa 32 ký tự và không có kí tự số !");
			}
		}

		try {
			int rid = Integer.parseInt(id_rank);
			if (rid == 0) {
				err.add("Vui lòng chọn cấp bậc cho user!");
			} else {
				if (rankDao.checkID(rid).size() == 0) {
					err.add("Không tồn tại ID cấp bậc!");
				}
			}
		} catch (NumberFormatException e) {
			err.add("Lỗi ID cấp bậc!");
		}

		if (regular.checkNameEmpty(objUser.getPassword()) == true) {
			err.add("Password không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkPassword(objUser.getPassword()) == false) {
				err.add("Password có độ dài tối thiểu 8 ký tự và tối đa 32 ký tự, có chứa ít nhất 1 ký tự số từ 0 – 9, 1 ký tự chữ thường, 1 ký tự chữ hoa và 1 ký tự trong tập các ký tự đặc biệt như: @;#;$;!;%");
			}
		}

		if (regular.checkNameEmpty(objUser.getRepassword()) == true) {
			err.add("Xác nhận Password không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkPassword(objUser.getRepassword()) == false) {
				err.add("Repassword có độ dài tối thiểu 8 ký tự và tối đa 32 ký tự, có chứa ít nhất 1 ký tự số từ 0 – 9, 1 ký tự chữ thường, 1 ký tự chữ hoa và 1 ký tự trong tập các ký tự đặc biệt như: @;#;$;!;%");
			} else {
				if (!objUser.getPassword().equals(objUser.getRepassword())) {
					err.add("Xác nhận mật khẩu không khớp! Mời bạn nhập lại");
				}
			}
		}
	}

	private void checkErrEdit(Principal principal, User objUser, List<String> err, String id_rank, String oldpassword,
			int id) {
		if (regular.checkNameEmpty(objUser.getFullname()) == true) {
			err.add("Fullname không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
		} else {
			if (regular.checkFullName(objUser.getFullname()) == false) {
				err.add("Fullname có độ dài tối thiểu 3 ký tự và tối đa 32 ký tự và không có kí tự số !");
			}
		}

		try {
			int rid = Integer.parseInt(id_rank);
			if (rankDao.checkID(rid).size() == 0) {
				err.add("Không tồn tại ID cấp bậc!");
			}
		} catch (NumberFormatException e) {
			err.add("Lỗi ID cấp bậc!");
		}
		if ("".equals(oldpassword)) {
			err.add("Mời bạn nhập mật khẩu cũ !");
		} else {
			String passcu = userDao.getcheckOldPass(principal.getName()).getPassword();
			if (BCrypt.checkpw(oldpassword, passcu) == false) {
				err.add("Mật khẩu cũ không đúng! Mời bạn nhập lại");
			}
		}

		if ("".equals(objUser.getPassword()) && !"".equals(objUser.getRepassword())) {
			err.add("Password không được trống! Mời bạn nhập lại");
		}

		if (!"".equals(objUser.getPassword()) && "".equals(objUser.getRepassword())) {
			err.add("Mời bạn xác nhận lại Password");
		}

		if (!"".equals(objUser.getPassword()) && !"".equals(objUser.getRepassword())) {
			if (regular.checkNameEmpty(objUser.getPassword()) == true) {
				err.add("Password không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
			} else {
				if (regular.checkPassword(objUser.getPassword()) == false) {
					err.add("Password có độ dài tối thiểu 8 ký tự và tối đa 32 ký tự, có chứa ít nhất 1 ký tự số từ 0 – 9, 1 ký tự chữ thường, 1 ký tự chữ hoa và 1 ký tự trong tập các ký tự đặc biệt như: @;#;$;!;%");
				}
			}
			if (regular.checkNameEmpty(objUser.getRepassword()) == true) {
				err.add("Xác nhận Password không được chứa toàn bộ khoảng trắng! Mời bạn nhập lại");
			} else {
				if (regular.checkPassword(objUser.getRepassword()) == false) {
					err.add("Xác nhận Password có độ dài tối thiểu 8 ký tự và tối đa 32 ký tự, có chứa ít nhất 1 ký tự số từ 0 – 9, 1 ký tự chữ thường, 1 ký tự chữ hoa và 1 ký tự trong tập các ký tự đặc biệt như: @;#;$;!;%");
				} else {
					if (!objUser.getPassword().equals(objUser.getRepassword())) {
						err.add("Xác nhận mật khẩu không khớp! Mời bạn nhập lại");
					}
				}
			}
		}
	}
}
