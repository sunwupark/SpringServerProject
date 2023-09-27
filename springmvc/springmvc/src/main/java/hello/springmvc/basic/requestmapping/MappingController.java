package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/hello-basic")
    public String helloBasic(){
        logger.info("hello-basic");
        return "ok";
    }

    @GetMapping("/mapping-get-v2")
    public String mappedGetV2(){
        logger.info("mapping-get-v2");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        logger.info("mapping userid = {}", data);
        return data;

    }
    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId){
        logger.info("mapping Path userId = {} orderID = {}",userId, orderId);
        return "ok";
    }

    @GetMapping(value = "/mapping-param", params= "mode=debug")
    public String mappingParam(){
        logger.info("mappingParam");
        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers= "mode=debug")
    public String mappingHeader(){
        logger.info("mappingHeader");
        return "ok";
    }

    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes(){
        logger.info("mappingConsumes");
        return "ok";
    }

    @PostMapping(value = "/mapping-produces", produces = "text/html")
    public String mappingProduces(){
        logger.info("mappingProduces");
        return "ok";
    }
}
