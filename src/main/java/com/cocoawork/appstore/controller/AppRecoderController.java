package com.cocoawork.appstore.controller;

import com.cocoawork.appstore.service.AppRecoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class AppRecoderController {

    @Autowired
    private AppRecoderService appRecoderService;



}
