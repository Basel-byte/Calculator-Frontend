package com.calculatorapplication.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@CrossOrigin
@RequestMapping

public class CalcaulatorController {


    @GetMapping("/calculate")
    public String evaluateExpression(@RequestParam String arithmeticExpression) throws UnsupportedEncodingException {
        String decodedExpression = java.net.URLDecoder.decode(arithmeticExpression, StandardCharsets.UTF_8.name());
        Evaluator evaluator = new Evaluator(decodedExpression);
        String result = evaluator.evaluateExpression();
        return result;
    }


}
