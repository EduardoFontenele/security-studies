package devotion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @GetMapping("/1")
    public String demo1() {
        return "demo 1";
    }

    @GetMapping("/2")
    public String demo2() {
        return "demo 2";
    }

    @GetMapping("/3")
    public String demo3() {
        return "demo 3";
    }

    @GetMapping("/4")
    public String demo4() {
        return "demo 4";
    }
}
