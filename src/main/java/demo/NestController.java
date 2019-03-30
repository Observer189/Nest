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
	private ArrayList<Key> keyAr = new ArrayList<>();

	private GsonBuilder builder = new GsonBuilder();
	private Gson gson;

	@RequestMapping("/send")
	public ServResponse sendMessage(
			@RequestParam(name = "message") String message,
			@RequestParam(name = "authorId") int id,
			@RequestParam(name = "adrId") int adrId)
	{
		Message mes = new Message(id, adrId, message);

		Date date = new Date(System.currentTimeMillis() + 3600000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("(HH:mm:ss)");
		System.out.println(dateFormat.format(date) + "id:" + id + ":" + message);
		mes.setTime(date.getTime());
		messageAr.add(mes);
		ServResponse resp = new ServResponse();
		resp.setMessage("Message received");
		return resp;
	}

	@RequestMapping("/sendKey")
	public ServResponse sendKey(
			@RequestParam(name = "key") String key,
			@RequestParam(name = "authorId") int id,
			@RequestParam(name = "adrId") int adrId) {


		builder.registerTypeAdapter(SecretKey.class, new SecretKeyAdapter());
		gson = builder.create();

		SecretKey key1 = gson.fromJson(key, SecretKey.class);
		Key key2 = new Key(id, adrId, key1);

		if (keyAr.size() == 0){
			keyAr.add(key2);
		}

		for (int i = 0; i < keyAr.size()-1; i++) {
			if ((keyAr.get(i).getAdrId() != adrId)&&(keyAr.get(i).getAuthorId() != id)){
				keyAr.add(key2);
			}
		}

		System.out.println(keyAr.size());

		System.out.println("DONE!");

		ServResponse resp = new ServResponse();
		resp.setMessage("Key received");
		return resp;
	}


	@RequestMapping("/get")
	public ArrayList<Message> getMessages(@RequestParam(name = "id") int id) {
		ArrayList<Message> response = new ArrayList<Message>();
		for (Message message : messageAr) {
			if (message.adrId == id) {
				response.add(message);
			}
		}
		return response;
	}

	@RequestMapping("/getKey")
	public String sendKey(@RequestParam(name = "id") int id) {
		SecretKey key = null;
		for (int i = 0; i < keyAr.size(); i++) {
			if (keyAr.get(i).getAdrId() == id) {
				key = keyAr.get(i).getKey();
				keyAr.remove(i);
			}
		}
		builder.registerTypeAdapter(SecretKey.class, new SecretKeyAdapter());
		gson = builder.create();

		return gson.toJson(key);
	}
}