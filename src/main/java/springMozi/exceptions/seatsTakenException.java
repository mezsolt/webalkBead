package springMozi.exceptions;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class seatsTakenException extends RuntimeException{

	public seatsTakenException(String message) {
        super(message);
    }
	
}
