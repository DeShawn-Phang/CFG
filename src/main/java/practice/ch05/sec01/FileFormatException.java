package practice.ch05.sec01;

import java.io.IOException;

public class FileFormatException extends IOException {
    public FileFormatException(){}
    private FileFormatException(String message) {
        super(message);
    }
}