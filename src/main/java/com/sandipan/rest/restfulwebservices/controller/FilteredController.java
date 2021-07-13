package com.sandipan.rest.restfulwebservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.sandipan.rest.restfulwebservices.model.DynamicFilteredModel;
import com.sandipan.rest.restfulwebservices.model.StaticFilteredModel;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/filtered")
public class FilteredController {

    //static filtering

    @GetMapping("/filteredResponse")
    public StaticFilteredModel filter(){
        return new StaticFilteredModel("Sandy", "s123");
    }

    @GetMapping("/filteredListResponse")
    public List<StaticFilteredModel> filterList(){
        return Arrays.asList(
                new StaticFilteredModel("Sandy", "s123"),
                new StaticFilteredModel("Kaka", "K123")
        );
    }

    //Dynamic Filtering

    @GetMapping("/dynamicfilteredResponse")
    public MappingJacksonValue dynamicfilter(){
        DynamicFilteredModel model = new DynamicFilteredModel("Sandy", "s123");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");
        FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(model);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/dynamicfilteredListResponse")
    public MappingJacksonValue dynamicfilterList(){
        List<DynamicFilteredModel> models =  Arrays.asList(
                new DynamicFilteredModel("Sandy", "s123"),
                new DynamicFilteredModel("Kaka", "K123")
        );

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("password");
        FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(models);
        mapping.setFilters(filters);
        return mapping;
    }

}
