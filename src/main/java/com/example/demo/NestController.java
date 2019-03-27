package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServlet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class NestController extends HttpServlet {
	ArrayList<Message> messageAr=new ArrayList<Message>();
	@RequestMapping("/send")
	public ServResponse sendMessage(
			@RequestParam(name="message") String message,
			@RequestParam(name="authorId") int id,
			@RequestParam(name="adrId")int adrId)
	{
		Message mes=new Message(id,adrId,message);
		
		
		Date date=new Date(System.currentTimeMillis()+3600000);
		SimpleDateFormat dateFormat=new SimpleDateFormat("(HH:mm:ss)");
		System.out.println(dateFormat.format(date)+"id:"+id+":"+message);
		mes.setTime(date.getTime());
		messageAr.add(mes);
		ServResponse resp=new ServResponse();
		resp.setMessage("Message received");
		return resp;
	}
	@RequestMapping("/get")
	public ArrayList<Message> getMessages(@RequestParam(name="id") int id)
	{
		ArrayList<Message> response=new ArrayList<Message>();
		for(int i=0;i<messageAr.size();i++)
		{
			if(messageAr.get(i).adrId==id)
			{
				response.add(messageAr.get(i));
			}
		}
		return response;
	}
}
