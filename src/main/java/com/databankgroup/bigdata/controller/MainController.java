package com.databankgroup.bigdata.controller;

import com.databankgroup.bigdata.model.CustomQueryParam;
import com.databankgroup.bigdata.model.SearchParameters;
import com.databankgroup.bigdata.model.Table;
import com.databankgroup.bigdata.repository.TableRepository;
import com.databankgroup.bigdata.view.ExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private TableRepository tableRepository;

    private static HashMap<String, String> optionMap = new HashMap<String, String>();
    static {
        optionMap.put("name", "WHERE fullname LIKE '%?%' or client_name  like '%?%'");
        optionMap.put("miles", "where milesclientcode LIKE '%?%'");
        optionMap.put("backconnect", "where bcclientcode LIKE '%?%' ");
    }

    @RequestMapping(value={"/","/index"},method= RequestMethod.GET)
    public String showMainPage(Model model){

        model.addAttribute("searchParameters",new SearchParameters());

        return "index";

    }

    @RequestMapping(value="/customquery",method=RequestMethod.GET)
    public String showCustomQuery(Model model){
        model.addAttribute("customQueryParam",new CustomQueryParam());

        return "query";
    }


    @ModelAttribute("productList")
    public ArrayList<String>populateProductList(){
        return (ArrayList<String>) tableRepository.getAllProducts();


    }

    @RequestMapping(value="/customquery",method=RequestMethod.POST)
    public ModelAndView showCustomQueryResults(@ModelAttribute("customQueryParam") CustomQueryParam customQueryParam,Model model
            ){

        List<String>includeList=null;
        if (customQueryParam.getIncludeItems()!=null){
           includeList = Arrays.asList(customQueryParam.getIncludeItems().split("\\s*,\\s*"));
        }
        List<String> excludeList=null;

        if (customQueryParam.getExcludeItems() != null) {
            excludeList=Arrays.asList(customQueryParam.getExcludeItems().split("\\s*,\\s*"));
        }
        //now let's write the where the clause

        String includePart=" ";

        if (includeList !=null){
            includePart=includePart + " where (";
            for (String item : includeList) {
                includePart= includePart + " planid like '%"+item+"%' and";
            }
            includePart=includePart+" 1=1)";
        }

        String excludePart=" ";
        if(excludeList !=null){
            excludePart=excludePart + " (";
            for (String item: excludeList){
                excludePart=excludePart + " planid not like '%"+item+"%' and";
            }
            excludePart= excludePart+" 1=1)";

        }

        String whereClause=includePart + (includePart == " "?" where " :" and ") +
                (excludePart==" "?" 1=1 " : excludePart);

        List<Table> custList=tableRepository.searchForClient(whereClause);

        model.addAttribute("clientlist",custList);


        return new ModelAndView(new ExcelView(), model.asMap());

      //  return new ModelAndView()


    }


    @RequestMapping(value="/customers",method=RequestMethod.POST)
    public String showSearchResults(@ModelAttribute("searchParameters") SearchParameters searchParameters, BindingResult result, Model model,
                                    final RedirectAttributes redirectAttributes){



        String searchValue = searchParameters.getValue().replace(" ","%");

        String searchClause= optionMap.get(searchParameters.getOption());

        searchClause = searchClause.replace("?",searchValue);



        List<Table> custList=tableRepository.searchForClient
                (searchClause);

        model.addAttribute("clientlist",custList);


        return "index";

    }




}
