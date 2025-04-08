package example.bankaccount;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


	@GetMapping("/")
	String redirect() {
		return "redirect:/accounts/1";
	}
}
