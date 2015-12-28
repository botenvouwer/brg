package restService.JSONGenerator;

import businessRuleGenerator.domain.BusinessRule;
import org.json.JSONObject;

/**
 * Created by melvin on 28-12-2015.
 */
public class BusinessRuleConverter implements JSONConverter {

    @Override
    public void importObject(String json) {



    }

    @Override
    public JSONObject exportObject(Object object) {

        if(object instanceof BusinessRule){



        }

        return null;
    }

}
