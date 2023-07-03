package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ActDetailCate;
import com.example.dto.ClassProduct;
import com.example.dto.ClassUnit;
import com.example.dto.ClassUnitView;
import com.example.dto.LocalCate;
import com.example.service.classproduct.ClassInquiryService;
import com.example.service.classproduct.ClassSelectService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/class")
@Slf4j
public class RestClassController {
    
    @Autowired ClassSelectService cService;
    @Autowired ClassInquiryService inquiryService;
    
    @Value("${classselect.page}") int pageEach;

    final String format = "RestClassController => {}";

    @GetMapping(value = "/selectcatelist.json")
    public Map<String, Object> selectcatelistGET(
        @RequestParam(name = "chk") String chk, 
        @RequestParam(name = "refcode") int refcode
        ) {
        
        //log.info(format, chk);
        //log.info(format, refcode);

        Map<String, Object> retmap = new HashMap<>();

        if(chk.equals("classcate")) {
			
			List<ActDetailCate> list = cService.selectActDetailCateList(refcode);
			
            //log.info(format, list.toString());

			retmap.put("list", list);
			
		}
		
		else if(chk.equals("citycate")) {
			
			List<LocalCate> list = cService.selectLocalCateList(refcode);

            //log.info(format, list.toString());

			retmap.put("list", list);
		
		}

        return retmap;

    }

    @GetMapping(value = "/selectclasslist.json")
    public Map<String, Object> selectclassGET(@RequestParam Map<String, Object> map) {

        Map<String, Object> retMap = new HashMap<>();

        log.info(format, map.toString());

        String optionStr = (String)map.get("option");
        int option = Integer.parseInt(optionStr);

        String pageStr = (String)map.get("page");
        int page = Integer.parseInt(pageStr);

        //log.info(format, page);

        map.put("first", (page*pageEach)-(pageEach-1));
        map.put("last", page*pageEach);

        String classdate = (String)map.get("classdate");

        if(option == 1) {

            if(classdate.equals("")) {

                // 1. 타입 A 조회 -> 날짜가 없는 경우
    
                //log.info(format, map.toString());
    
                List<ClassProduct> list = cService.selectClassProductViewList(map);
                long total = cService.selectClassCountTotalV2(map);
    
                //log.info(format, list.toString());
                //log.info(format, total);
    
                retMap.put("ret", 1);
                retMap.put("type", "A");
                retMap.put("list", list);
                retMap.put("pages", ((total-1)/pageEach)+1);
    
            }
    
            else {
    
                // 1. 타입 B 조회 -> 날짜가 있는 경우
    
                //log.info(format, map.toString());
    
                List<ClassUnitView> list = cService.selectClassUnitViewList(map);
                long total = cService.selectClassCountTotal(map);
    
                //log.info(format, list.toString());
                //log.info(format, total);
    
                retMap.put("ret", 1);
                retMap.put("type", "B");
                retMap.put("list", list);
                retMap.put("pages", ((total-1)/pageEach)+1);
    
            }
        }
        else if(option == 2) {

            if(classdate.equals("")) {

                // 1. 타입 A 조회 -> 날짜가 없는 경우
    
                //log.info(format, map.toString());
    
                List<ClassProduct> list = cService.selectClassProductViewList2(map);
                long total = cService.selectClassCountTotalV2(map);
    
                //log.info(format, list.toString());
                //log.info(format, total);
    
                retMap.put("ret", 1);
                retMap.put("type", "A");
                retMap.put("list", list);
                retMap.put("pages", ((total-1)/pageEach)+1);
    
            }
    
            else {
    
                // 1. 타입 B 조회 -> 날짜가 있는 경우
    
                //log.info(format, map.toString());
    
                List<ClassUnitView> list = cService.selectClassUnitViewList2(map);
                long total = cService.selectClassCountTotal(map);
    
                //log.info(format, list.toString());
                //log.info(format, total);
    
                retMap.put("ret", 1);
                retMap.put("type", "B");
                retMap.put("list", list);
                retMap.put("pages", ((total-1)/pageEach)+1);
    
            }

        }

        return retMap;

    }

    @GetMapping(value = "selectunit.json")
    public Map<String, Object> selectunitGET(@ModelAttribute ClassUnit obj) {

        Map<String, Object> retMap = new HashMap<>();

        //log.info(format, obj.toString());

        List<ClassUnit> list = cService.selectClassUnitList(obj);

        //log.info(format, list.toString());

        retMap.put("ret", 0);

        if(!list.isEmpty()) {
            
            retMap.put("ret", 1);
            retMap.put("list", list);

        }

        return retMap;
    }

    @GetMapping(value = "/selectunitone.json")
    public Map<String, Object> selectunitoneGET(@RequestParam(name = "no") long no) {

        Map<String, Object> retMap = new HashMap<>();

        //log.info(format, no);

        ClassUnit obj = cService.selectClassUnitOne(no);

        retMap.put("ret", 0);

        if(obj != null) {

           //log.info(format, obj.toString());
           
           retMap.put("ret", 1);
           retMap.put("obj", obj); 

        }
        
        return retMap;

    }

    @PutMapping(value = "/updatehit.json")
    public Map<String, Object> updatehitPUT(@RequestBody com.example.entity.ClassProduct obj) {

        Map<String, Object> retMap = new HashMap<>();

        log.info(format, obj.toString());

        int ret = cService.updateClassProductHit(obj);

        retMap.put("ret", ret);

        return retMap;

    }


}
