package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class NestController extends HttpServlet {
    private ArrayList<Message> messageAr = new ArrayList<>();
    private ArrayList<Key> keyAr = new ArrayList<>();



    @RequestMapping("/send")
    public ServResponse sendMessage(
            @RequestParam(name = "message") String message,
            @RequestParam(name = "authorId") int id,
            @RequestParam(name = "adrId") int adrId) {
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
            @RequestParam(name = "key") String key) {

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(SecretKey.class, new SecretKeyAdapter());
        Gson gson = builder.create();
        SecretKey key1 = gson.fromJson(key, SecretKey.class);

        ServResponse resp = new ServResponse();
        resp.setMessage("DONE");
        return resp;
    }

    @RequestMapping("/get")
    public ArrayList<Message> getMessages(@RequestParam(name = "id") int id) {
        ArrayList<Message> response = new ArrayList<>();
        for (Message aMessageAr : messageAr) {
            if (aMessageAr.adrId == id) {
                response.add(aMessageAr);
            }
        }
        return response;
    }

    @RequestMapping("/getKey")
    public Key sendKey(@RequestParam(name = "id") int id) {
        Key key = null;
        for (int i = 0; i < keyAr.size(); i++) {
            if (keyAr.get(i).getAdrId() == id) {
                key = keyAr.get(i);
                keyAr.remove(i);
            }
        }
        return key;
    }
}
