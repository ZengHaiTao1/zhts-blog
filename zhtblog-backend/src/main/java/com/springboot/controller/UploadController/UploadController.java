package com.springboot.controller.UploadController;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * 文件上传控制器
 */
@Controller
public class UploadController {

    @Value("")
    private String accessKey;
    @Value("")
    private String secretKey;
    @Value("")
    private String bucket;
    @Value("")
    private String bucketName;
    @Value("")
    private String path;

     //上传道腾讯云服务器（https://cloud.tencent.com/document/product/436/10199）
    @RequestMapping(value = "/tengxun", method = RequestMethod.POST)
    @ResponseBody
    public JSONResult Upload(@RequestParam(value = "file") MultipartFile file, HttpSession session) {
        if (file == null) {
            return JSONResult.successData("文件为空！");
        }
        String oldFileName = file.getOriginalFilename();
        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + eName;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(accessKey, secretKey);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(bucket));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = this.bucketName;

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try {
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            // 指定要上传到 COS 上的路径
            String key = "/"   + year + "-" + (month+1) + "/" + day + "/" + newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            return  JSONResult.successData(this.path + putObjectRequest.getKey());
        } catch (IOException e) {
            return  JSONResult.failMsg("失败");
        } finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
    }
}