package antreman;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;

public class Country {

    private String id;
    private String name;
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public HashMap<String,Object> expectedDataTest(){

        HashMap<String,Object> data = new HashMap<>();
        data.put("username","richfield.edu");
        data.put("password","Richfield2020!");
        data.put("rememberMe","true");

        return data;
    }

    @Override
    public String toString() {
        return "****Country****" +
                "id : " + id +
                "name : " + name +
                "code : " + code;
    }
    public HashMap<String,Object> ülkeData(){
        String randomName = RandomStringUtils.randomAlphabetic(6);
        String randomCode = RandomStringUtils.randomAlphabetic(2);

        HashMap<String,Object> data = new HashMap<>();

        return data;
    }
}
