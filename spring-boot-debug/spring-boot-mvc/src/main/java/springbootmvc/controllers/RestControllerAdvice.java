package springbootmvc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class RestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e){
        String message = e.getMessage();
        return ResponseEntity.ok(message);
    }
}
