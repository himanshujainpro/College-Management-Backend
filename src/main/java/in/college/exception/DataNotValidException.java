package in.college.exception;

public class DataNotValidException extends RuntimeException{
    public DataNotValidException(String message){
        super(message);
    }
}
