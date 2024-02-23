package devotion.controller;

import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/1")
    @PreAuthorize("hasAuthority('write')")
    public String demo1() {
        return "demo 1";
    }
    @PreAuthorize("hasAuthority('read')")
    @GetMapping("/2")
    public String demo2() {
        return "demo 2";
    }

    @GetMapping("/3")
    @PreAuthorize("hasAnyAuthority('write', 'read')")
    public String demo3() {
        return "demo 3";
    }

    @GetMapping("/4")
    @PreAuthorize("permitAll")
    public String demo4() {
        return "demo 4";
    }

    @GetMapping("/5/{something}")
    @PreAuthorize("#something == authentication.name or hasAuthority('write')") // Evaluation usin speL - Spring Special Language
    public String demo5(@PathVariable("something") String something) {
        return something;
    }

    @GetMapping("/6")
    @PreAuthorize("@demoConditionEvaluation.evaluate()")
    public String demo6() {
        return "demo 6";
    }

    // PreFilter - works with arrays and collections
    // Basically it will filter the collection and pass only the values which make the evaluation
    // returns true. In this case, only "hello" would pass
    @PostMapping("/7")
    @PreFilter("filterObject.contains('hello')")
    public String demo7(@RequestBody List<String> values) {
        return values.toString();
    }

    // PostFilter - do something similar, but instead, only after the request.
    // only accepts arrays or collection
    @GetMapping("/8")
    @PostFilter("filterObject.toString().contains('mia') || filterObject.toString().contains('edu')")
    public List<String> demo8() {
        var list = new ArrayList<String>();
        list.add("mia");
        list.add("edu");
        return list;
    }
}
