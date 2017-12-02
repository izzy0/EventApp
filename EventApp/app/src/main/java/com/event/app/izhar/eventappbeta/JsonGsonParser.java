package com.event.app.izhar.eventappbeta;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;

/**
 * Created by Izhar on 10/30/2017.
 */

public class JsonGsonParser implements Serializable {

    Gson gson = new Gson();

    public JsonGsonParser(){}

    public void SerializeFile(User user){

        try (Writer writer = new FileWriter("c:\\users.json")) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(user, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User DeserializeFile(){

        try (Reader reader = new FileReader("D:\\users.json")) {

            // Convert JSON to Java Object
            User user = gson.fromJson(reader, User.class);
            System.out.println(user);

            // Convert JSON to JsonElement, and later to String
            /*JsonElement json = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(json);
            System.out.println(jsonInString);*/

            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
