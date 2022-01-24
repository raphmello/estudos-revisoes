package com.raphael.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean() {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        return getMappingJacksonValue(someBean, "SomeBeanFilter","value1","value2");
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBean() {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value11", "value22", "value33"));
        return getMappingJacksonValue(someBeans, "SomeBeanFilter","value2","value3");
    }

    private MappingJacksonValue getMappingJacksonValue(Object someBeans, String filterName,String... args) {
        MappingJacksonValue mapping = new MappingJacksonValue(someBeans);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(args);
        FilterProvider filters = new SimpleFilterProvider().addFilter(filterName,filter);
        mapping.setFilters(filters);
        return mapping;
    }
}
