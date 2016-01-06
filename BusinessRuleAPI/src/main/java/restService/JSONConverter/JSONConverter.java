package restService.JSONConverter;

import org.json.JSONObject;

/**
 * Created by melvin on 28-12-2015.
 */
public interface JSONConverter {

    public Object importObject(String json);
    public JSONObject exportObject(Object object);

}
