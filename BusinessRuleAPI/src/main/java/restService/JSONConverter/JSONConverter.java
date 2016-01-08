package restService.JSONConverter;

import businessRuleGenerator.domain.ValidatorException;
import org.json.JSONObject;

/**
 * Created by melvin on 28-12-2015.
 */
public interface JSONConverter {

    public Object importObject(String json) throws JSONConverterException, ValidatorException;
    public JSONObject exportObject(Object object);

}
