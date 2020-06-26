package com.Bleedy;

import com.Bleedy.sourse.GetXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class GreetingController {

    @Autowired
    private GetXML xml;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World")String name, Model model) throws IOException, SAXException, ParserConfigurationException {
        model.addAttribute("exchangeRates",xml.getXML());
        return "greeting";
    }

    @RequestMapping("filter")
    public String filter (@RequestParam String filter,Model model){
        Map<String,String> filteredRates= new TreeMap<String,String>();
        if (filter != null && !filter.isEmpty()) {
            for (Map.Entry<String,String> value : xml.getXML().entrySet()){
                if (value.getKey().contains(filter)){
                    filteredRates.put(value.getKey(), value.getValue());
                }
            }
        }else {
            filteredRates = xml.getXML();
        }
        model.addAttribute("exchangeRates",filteredRates);
        return "greeting";
    }
}
