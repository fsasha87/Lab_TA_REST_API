package entities;

import com.google.gson.Gson;

public class Error {
    public String timeStamp;
    public int statusCode;
    public String error;
    public String errorMessage;

    public Error(String timeStamp, int statusCode, String error, String errorMessage) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public Error(){}

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
