package controller;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class AuthController {
	/*@Autowired
	private CatDao catDao;*/
	
	@RequestMapping("/login")
	public String login(){
		return "auth.login";
	}
	
	@RequestMapping("/403")
	public String err403(){
		return "auth.403";
	}
}
