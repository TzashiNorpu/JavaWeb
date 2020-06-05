package com.tz.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tz.pojo.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jsonTest {
    @Test
    public void test1(){
        Person p = new Person(1, "HH");
        Gson gson = new Gson();
        String s = gson.toJson(p);
        System.out.println(s);
        Person person = gson.fromJson(s, Person.class);
        System.out.println(person);
    }

    @Test
    public void test2(){
        List<Person> personList = new ArrayList<>();

        personList.add(new Person(1, "国哥"));
        personList.add(new Person(2, "康师傅"));

        Gson gson = new Gson();

        // 把List转换为json字符串
        String personListJsonString = gson.toJson(personList);
        System.out.println(personListJsonString);

        List<Person> list = gson.fromJson(personListJsonString, new PersonListType().getType());
        System.out.println(list);
        Person person = list.get(0);
        System.out.println(person);
    }


    //    1.2.3、map 和json的互转
    @Test
    public void test3(){
        Map<Integer,Person> personMap = new HashMap<>();

        personMap.put(1, new Person(1, "国哥好帅"));
        personMap.put(2, new Person(2, "康师傅也好帅"));

        Gson gson = new Gson();
        // 把 map 集合转换成为 json字符串
        String personMapJsonString = gson.toJson(personMap);
        System.out.println(personMapJsonString);

//        Map<Integer,Person> personMap2 = gson.fromJson(personMapJsonString, new PersonMapType().getType());
        // 匿名内部类的使用
        Map<Integer,Person> personMap2 = gson.fromJson(personMapJsonString, new TypeToken<HashMap<Integer,Person>>(){}.getType());

        System.out.println(personMap2);
        Person p = personMap2.get(1);
        System.out.println(p);

    }
}
