package com.czm.controller;

import com.czm.entity.Infotable;
import com.czm.service.InfotableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * test
 *
 * @author xiaoyin
 * @create 2017-06-02
 */
@RestController
@RequestMapping("/table")

public class InfotableController {
    @Autowired
    private InfotableService infotableService;

    @PostMapping("/getAll")
    public List<Infotable> getInfotableAll() {
        return this.infotableService.getInfotableAll();
    }
}
