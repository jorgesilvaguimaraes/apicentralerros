package br.com.apicentralerros.domain.exception;

public class EntityNotFound extends DefaultException {
    private static final long serialVersionUID = 1L;

    public EntityNotFound(String message){
        super(message);
    }
}
