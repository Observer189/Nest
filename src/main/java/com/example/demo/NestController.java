package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServlet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class NestController extends HttpServlet {
	@RequestMapping("/home")
	public ServResponse getMessage(@RequestParam(name="message") String message,@RequestParam(name="authorId") int id)
	{
		Date date=new Date(System.currentTimeMillis()+3600000);
		SimpleDateFormat dateFormat=new SimpleDateFormat("(HH:mm:ss)");
		System.out.println(dateFormat.format(date)+"id:"+id+":"+message);
		ServResponse resp=new ServResponse();
		resp.setMessage("Message received");
		return resp;
	}
}
