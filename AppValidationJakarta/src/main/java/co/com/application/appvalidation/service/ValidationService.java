package co.com.application.appvalidation.service;

import co.com.application.appvalidation.dto.Request;


import org.springframework.http.ResponseEntity;

public interface ValidationService {

    public  <T> ResponseEntity<T> obtainInfo();
    public  <T> ResponseEntity<T> postInfo(String token,Request inputdata);
    
    

}
