package com.platformnexus.data.justCopy.controller;

import com.platformnexus.data.justCopy.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class DebugController {

    @Autowired
    private ProcessService processService;

    @RequestMapping(method = RequestMethod.GET)
    public void processService() {
        processService.process();
    }

}
