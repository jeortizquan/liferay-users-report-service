package com.lighthouse.controller;

import com.lighthouse.lib.GenerateUserList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class UsersReportServiceController {

    private final Logger LOG = LoggerFactory.getLogger(UsersReportServiceController.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String index() {
        LOG.info("Welcome");
        return "Welcome to Liferay Users Report Service!";
    }


    @GetMapping(value = "/generate")
    public void method(HttpServletResponse httpServletResponse) {
        LOG.info("Inside generate");
        try {
            String baseUrl = env.getProperty("base.url");
            String response = (String) restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class).getBody();
            LOG.info("The response received by method is " + response);
            GenerateUserList userList = new GenerateUserList();
            httpServletResponse.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=lifeuserlist"+new SimpleDateFormat("YYYYMMdd-HHmmss" ).format(new Date())+".xlsx");
            httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            userList.processResponse(response).write(httpServletResponse.getOutputStream());
        } catch (Exception e) {
            LOG.error("while processing", e);
        }
    }

}
