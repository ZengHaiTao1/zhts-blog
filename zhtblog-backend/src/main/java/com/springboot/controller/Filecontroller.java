package com.springboot.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
public class Filecontroller {

    @ResponseBody
    @RequestMapping("/load")
    public String[] filecontroller() {
        File file = new File("E:\\前端啊\\特效\\images");
        String[] fileNamelist = file.list();
        return fileNamelist;
    }

    @RequestMapping("/fileDownload/{filepath}")
    public String fileDownload(@PathVariable("filepath") String filepath, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        System.out.println(filepath);
        // 配置文件下载
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filepath, "UTF-8"));
        OutputStream os = response.getOutputStream();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("E:\\前端啊\\特效\\images\\"+filepath));
        byte[] c = new byte[1024];
        int len = 0;
        while ((len=bis.read(c))!=-1){
            os.write(c);
        }
        os.close();
        bis.close();
        return null;
    }

    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@Param("file") MultipartFile file,HttpServletRequest request) throws Exception {
        if(file.isEmpty()){
            return  "false";
        }
        String path = request.getServletContext().getRealPath("/upload/");
        // 获取文件后缀
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String newfilename = UUID.randomUUID().toString().replace("-", "") + suffix;
        File filepath = new File(path, newfilename);
        // 判断路径是否存在，不存在则新创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        file.transferTo(new File(path + File.separator + newfilename));
        return "OK";
    }
}
