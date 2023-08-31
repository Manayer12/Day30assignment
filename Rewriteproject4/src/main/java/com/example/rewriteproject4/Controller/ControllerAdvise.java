package com.example.rewriteproject4.Controller;


import com.example.rewriteproject4.Api.ApiException;
import com.example.rewriteproject4.Api.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice

public class ControllerAdvise {
        @ExceptionHandler(value = DataIntegrityViolationException.class)//against check condition
        public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e){

            String message=e.getMessage();
            return ResponseEntity.status(400).body(message);}

        @ExceptionHandler(value = ApiException.class)
        public ResponseEntity apiException(ApiException e1) {
            String message=e1.getMessage();
            return ResponseEntity.status(400).body(message);

        }

        @ExceptionHandler(value = IncorrectResultSizeDataAccessException.class)
        public ResponseEntity IncorrectResultSizeDataAccessException(IncorrectResultSizeDataAccessException i){
            String message=i.getMessage();
            return ResponseEntity.status(400).body(message);
        }
    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class )
      public ResponseEntity<ApiResponse> InvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e){
          String msg=e.getMessage();
          return ResponseEntity.status(200).body(new ApiResponse(msg));
    }


    @ExceptionHandler(value = NullPointerException.class )
    public ResponseEntity<ApiResponse> NullPointerException(NullPointerException e){
        String msg=e.getMessage();
        return ResponseEntity.status(200).body(new ApiResponse(msg));
    }

    @ExceptionHandler(value = JpaSystemException.class )
    public ResponseEntity<ApiResponse> JpaSystemException(JpaSystemException e){
        String msg=e.getMessage();
        return ResponseEntity.status(200).body(new ApiResponse(msg));
    }
}
