package tr.sevalsenturk.ilService.exception;

public class IlAlreadyExistsException extends RuntimeException{
    public IlAlreadyExistsException(String msg){
        super(msg);
    }
}
