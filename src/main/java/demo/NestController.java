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
import java.util.Date;

@RestController
public class NestController extends HttpServlet {
	private ArrayList<Message> messageAr = new ArrayList<Message>();


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
		for(int i=0;i<removing.size();i++)
		{
			//messageAr.remove(removing.get(i).intValue());
			messageAr.remove(0);
			System.out.println("!!!"+removing.size()+"!!!");
		}
		
		return response;
	}
}