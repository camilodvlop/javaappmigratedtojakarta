package co.com.application.appvalidation.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import co.com.application.appvalidation.dto.Request;


import co.com.application.appvalidation.service.ValidationService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "/", produces = "application/json;charset=UTF-8")
public class MainControl {

    @Value("${inputdata.tokens}")
    private String tokenstored;

    @Autowired
    ValidationService validationService;

    @GetMapping()
    public <T> ResponseEntity<T> obtenerinfo(@RequestHeader("Authorization") String token) {
        if (token != null && !token.isEmpty() && (validauth(token))) {
        return validationService.obtainInfo();
        } else {
            return new ResponseEntity("{\"status\":\"no autorizado\"}", HttpStatus.UNAUTHORIZED);
        }
        
    }

    

    @PostMapping("postinfo")
    public <T> ResponseEntity<T> enviarInfo(@RequestHeader("Authorization") String token, @RequestBody() Request inputdata) {
        if (token != null && !token.isEmpty()) {
        return validationService.postInfo(token,inputdata);
        } else {
            return new ResponseEntity("{\"status\":\"no autorizado\"}", HttpStatus.UNAUTHORIZED);
        }
        
    }



    private boolean validauth(String xtipeauth) {
        return tokenstored.contains("|".concat(xtipeauth).concat("|"));

    }

}
