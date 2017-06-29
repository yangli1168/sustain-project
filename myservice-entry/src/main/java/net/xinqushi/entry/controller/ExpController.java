package net.xinqushi.entry.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExpController implements ErrorController {

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@ResponseBody
	@RequestMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> error(HttpServletRequest request) {
		return ResponseEntity.status(200).body("{\"msg\":\"系统忙，请稍后重试\", \"code\":500}");
    }
	
}
