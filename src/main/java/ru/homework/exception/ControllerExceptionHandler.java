package ru.homework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.homework.dto.result.Error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class.getName());

    /** Обработка кастомных исключений
     * @param ex исключение
     * @return Error
     */
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomException.class)
    public Error handlerCustomException(CustomException ex) {
        String message = ex.getParam();
        this.log(message, ex.getEx());
        return new Error(message);
    }

    /** Обработка прочих исключений
     * @param ex исключение
     * @return Error
     */
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Error handlerException(Exception ex) {
        return new Error(String.format("There is an Exception Error: %s", ex.getMessage()));
    }

    /** Обработка исключений при проверке входящих данных json
     * @param ex исключение
     * @param headers заголовок
     * @param status статус
     * @param request запрос
     * @return ResponseEntity с текстом ошибки
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error(String.format("Throw exception binding : \n %s", ex.getBindingResult().toString()));
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        Error error = new Error();
        if (!errors.isEmpty()) {
            error.setError(errors.get(0));
        }
        return super.handleExceptionInternal(ex, error, headers, HttpStatus.NOT_ACCEPTABLE, request);
    }

    /** Обработка исключений парсинга json
     * @param ex исключение
     * @param headers заголовок
     * @param status статус
     * @param request запрос
     * @return ResponseEntity с текстом ошибки
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Error error = new Error("Error parsing json");
        return super.handleExceptionInternal(ex, error, headers, HttpStatus.NOT_ACCEPTABLE, request);
    }

    private void log(String message, Exception ex) {
        if (ex != null) {
            LOGGER.debug(Arrays.toString(ex.getStackTrace()));
        }
        LOGGER.error(String.format("%s : parent exception - %s", message, ex));
    }
}
