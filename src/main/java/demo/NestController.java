package demo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServlet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@RestController
public class NestController extends HttpServlet {
	private ArrayList<Message> messageAr = new ArrayList<Message>();
    private ArrayList<Integer> idAr=new ArrayList<Integer>();

	private GsonBuilder builder = new GsonBuilder();
	private Gson gson = builder.create();;

	@RequestMapping("/send")
	public ServResponse sendMessage(
			@RequestParam(name = "message") String message,
			@RequestParam(name = "authorId") int id,
			@RequestParam(name = "adrId") int adrId,
			@RequestParam(name = "key") String key)
	{
		Message mes = new Message(id, adrId, message, key);

		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("(HH:mm:ss)");
		System.out.println(dateFormat.format(date) + "id:" + id + ":" + message);
		mes.setTime(date.getTime());
		messageAr.add(mes);
		ServResponse resp = new ServResponse();
		resp.setMessage("Message received");
		return resp;
	}

	@RequestMapping("/get")
	public ArrayList<Message> getMessages(@RequestParam(name = "id") int id) {
		ArrayList<Message> response = new ArrayList<Message>();
        ArrayList<Integer> removing=new ArrayList<Integer>();
        
		
		for (Message message : messageAr) {
			if (message.adrId == id) {
				String temp=gson.toJson(message);
				response.add(gson.fromJson(temp, Message.class));
				removing.add(messageAr.indexOf(message));
			}
		}
		System.out.println(response.size());
		System.out.println(messageAr.size());
		System.out.println(removing);
		if(!response.isEmpty()) {
		Collections.sort(removing);
		for(int i=removing.size()-1;i>=0;i--)
		{
			System.out.println(i);
			messageAr.remove(removing.get(i).intValue());
			
			//System.out.println("!!!"+removing.size()+"!!!");
		}
		}
		
		return response;
	}
	@RequestMapping("/register")
	  public Integer register(@RequestParam(name="id") int id)
	  {
		boolean isExist=false;
		for(int i=0;i<idAr.size();i++)
		{
			if(id==idAr.get(i))
			{
				isExist=true;
			}
			
		}
		if(isExist)
		{
			return 0;
		}
		else
		{
			idAr.add(id);
			return 1;
		}
	  }
}