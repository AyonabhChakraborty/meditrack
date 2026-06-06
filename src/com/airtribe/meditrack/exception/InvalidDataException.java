package com.airtribe.meditrack.exception;

public class InvalidDataException extends RuntimeException{

    public InvalidDataException(String message){
        super(message);
    }

    public InvalidDataException(String message, Throwable cause){
        super(message,cause);
    }

    // What is happening above?
    // The first constructor takes a message and passes it to the RuntimeException constructor.
    // The second constructor takes both a message and a cause (another Throwable) and passes them.
    // This is called constructor overloading, allowing us to create exceptions 
    // with just a message or with both a message and an underlying cause.


}
