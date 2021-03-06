package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController extends TechJobsController {

    @RequestMapping(value = "")
    public String search(Model model) {
        return "search";
    }

    @RequestMapping(value = "/results")
    public String displayResults(Model model, @RequestParam String searchType,
                                 @RequestParam String searchTerm) {
        ArrayList<HashMap<String, String>> jobs;
        if(searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("searchType", searchType);
        model.addAttribute("jobs", jobs);
        return "search";
    }

}
