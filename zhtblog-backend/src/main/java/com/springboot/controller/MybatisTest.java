package com.springboot.controller;

import com.springboot.mapper.LabelDao;
import com.springboot.pojo.Label;
import com.springboot.result.JSONResult;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Controller
public class MybatisTest {

    @Autowired
    private LabelDao labelDao;

    @ResponseBody
    @RequestMapping("/a")
    public  String aa(){
        return new SQL()
                .INSERT_INTO("label")
                .INTO_COLUMNS("name", "href")
                .INTO_VALUES("312", "222").toString();
    }

    @RequestMapping("/lable")
    @ResponseBody
    public List<Label>  mylabel(){
        List<Label> labels = labelDao.getLabels();
        Label label1 = labelDao.selectById(2);
        List<Label> labels1 = labelDao.selectList(null);
        return  labels1;
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<Map<String,Object>> getUser() throws IOException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name", "user");
        return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/text", method = RequestMethod.GET)
    public JSONResult Text(){
        return new JSONResult("404","路径不存在");
    }

}
