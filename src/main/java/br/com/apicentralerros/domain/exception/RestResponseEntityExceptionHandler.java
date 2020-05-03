package br.com.apicentralerros.domain.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(EntityNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(EntityNotFound ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ResourceNotFoundDetails problema = new ResourceNotFoundDetails();
        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(LocalDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DefaultException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<Object> handleNegocio(DefaultException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ResourceNotFoundDetails problema = new ResourceNotFoundDetails();
        problema.setStatus(status.value());
        problema.setTitulo(ex.getMessage());
        problema.setDataHora(LocalDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }

    @ExceptionHandler( NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<Object> handleNegocio(NoSuchElementException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ResourceNotFoundDetails problema = new ResourceNotFoundDetails();
        problema.setStatus(status.value());
        problema.setTitulo("Resource not found");
        problema.setDataHora(LocalDateTime.now());

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
    }


    @Override
    @ResponseBody
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ArrayList<ResourceNotFoundDetails.Campo> campos = new ArrayList<ResourceNotFoundDetails.Campo>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new ResourceNotFoundDetails.Campo(nome, mensagem));
        }

        ResourceNotFoundDetails problema = new ResourceNotFoundDetails();
        problema.setStatus(status.value());
        problema.setTitulo("Um ou mais campos estão inválidos. "
                + "Faça o preenchimento correto e tente novamente");
        problema.setDataHora(LocalDateTime.now());
        problema.setCampos(campos);

        return super.handleExceptionInternal(ex, problema, headers, status, request);
    }






}
