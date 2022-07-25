package com.example.demo.contoller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;

@Controller
public class IndexController {
    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private ResourceLoader resourceLoader;

//    private final AmazonS3 s3Client;
//
//    public IndexController(AmazonS3 s3Client) {
//        this.s3Client = s3Client;
//    }
    @GetMapping("/index")
    //public String home() {
    public String home(Model model) throws IOException {
       String Hello ="aaa";
       String Hell = "bbad";
       String HELLO_NAME = "HELLO_NAME";
       String Hello_ATTRIBUTE = "hello";
        File file = new File("s3upload/test.PNG");
        amazonS3.putObject("ddadas","aaaasnnna.png",file);


        return "staff/staff_list";
    }
    public enum ATTRIBUTE
    {
        ID
    }





}
