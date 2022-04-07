package pl.umk.mat.planner.lecturer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class LecturerNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(LecturerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String lecturerNotFoundHandler(LecturerNotFoundException ex) {
        return ex.getMessage();
    }
}
