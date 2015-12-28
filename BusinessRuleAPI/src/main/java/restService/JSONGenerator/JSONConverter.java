package restService.JSONGenerator;

import org.json.JSONObject;

/**
 * Created by melvin on 28-12-2015.
 */
public interface JSONConverter {

    public void importObject(String json);
    public JSONObject exportObject(Object object);

}
