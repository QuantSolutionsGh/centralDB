package com.databankgroup.bigdata.controller;

import com.databankgroup.bigdata.model.SearchParameters;
import com.databankgroup.bigdata.model.Table;
import com.databankgroup.bigdata.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private TableRepository tableRepository;

    private static HashMap<String, String> optionMap = new HashMap<String, String>();
    static {
        optionMap.put("name", "WHERE fullname LIKE ");
        optionMap.put("miles", "where milesclientcode LIKE ");
        optionMap.put("backconnect", "where bcclientcode LIKE ");
    }

    @RequestMapping(value="/",method= RequestMethod.GET)
    public String showMainPage(Model model){

        model.addAttribute("searchParameters",new SearchParameters());

        return "index";

    }


    @RequestMapping(value="/customers",method=RequestMethod.POST)
    public String showSearchResults(@ModelAttribute("searchParameters") SearchParameters searchParameters, BindingResult result, Model model,
                                    final RedirectAttributes redirectAttributes){

        String searchValue = searchParameters.getValue().replace(" ","%");

        List<Table> custList=tableRepository.searchForClient
                (optionMap.get(searchParameters.getOption()) + "'%" + searchValue + "%'");

        model.addAttribute("clientlist",custList);


        return "index";

    }




}
