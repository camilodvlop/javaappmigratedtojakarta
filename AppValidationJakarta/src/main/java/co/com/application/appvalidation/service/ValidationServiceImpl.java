package co.com.application.appvalidation.service;


import co.com.application.appvalidation.dto.Request;


import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;



import java.net.http.HttpResponse.ResponseInfo;


@Service
public class ValidationServiceImpl implements ValidationService {

    @Value("${path_url}")
    private String URL_SERVICE;
    @Value("${userid_proxy}")
    private String usercall;
    @Value("${password_proxy}")
    private String passcall;  

    @Autowired
    private RestTemplate restTemplate;

    public <T> ResponseEntity<T> obtainInfo() {

        String url = URL_SERVICE + "/getToken";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "text/plain");
        headers.set("Authorization", "OAuth 2.0");
        // Crear el cuerpo de la solicitud
        String requestBody = "grant_type=password&username=" + usercall + "&password=" + passcall;
        // Crear la entidad HTTP (cuerpo + encabezados)
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        // Hacer la solicitud GET
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        
        return new ResponseEntity(response.getBody(), HttpStatus.OK);
    }

    public <T> ResponseEntity<T> postInfo(String token, Request request) {

        

        // URL del servicio
        String url = URL_SERVICE + "/apis";

        // Configurar los encabezados HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Cambiar el tipo de contenido a JSON
        headers.set("Authorization", "Bearer " + token);

        // Serializar el objeto Request a JSON (usando Jackson)
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL); // Omitir
                                                                                                               // campos
                                                                                                               // nulos
        String requestBody;
        try {
            requestBody = objectMapper.writeValueAsString(request);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al serializar el objeto Request", e);
        }

        // Crear la entidad HTTP (cuerpo + encabezados)
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        try {
            // Hacer la solicitud POST y recibir la respuesta
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            // Retornar la respuesta del servicio
            return new ResponseEntity(response.getBody(), HttpStatus.OK);

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            // Manejar errores del servidor o cliente
            throw new RuntimeException("Error en la llamada al servicio: " + e.getMessage(), e);
        } catch (Exception e) {
            // Manejar cualquier otra excepción
            throw new RuntimeException("Error inesperado en la llamada al servicio: " + e.getMessage(), e);
        }
    }

  
}
