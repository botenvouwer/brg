package restService.response;

import java.util.Objects;

/**
 * Created by william on 15-Jan-16.
 */
public class Result {

    private ResponseStatus status;
    private String error;
    private Object result;

    public Result(){
        status = ResponseStatus.SUCCESS;
    }

    public void setError(String error){
        status = ResponseStatus.ERROR;
        this.error = error;
    }

    public void setResult(Object o){
        result = o;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public Object getResult() {
        return result;
    }
}
