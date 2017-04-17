package springMozi.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class WebController{

	
	@GetMapping(path="/form")
    public String showForm() {
		return "form";
    }

}
