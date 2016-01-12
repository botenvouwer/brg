package restService.JSONConverter;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

/**
 * Created by melvin on 10-1-2016.
 */
public class BusinessRuleListJSON {

    public ArrayList<BusinessRuleJSON> businessRules;

    public BusinessRuleListJSON(@JsonProperty("businessRules")ArrayList<BusinessRuleJSON> businessRules){

        this.businessRules = businessRules;


    }

    @Override
    public String toString() {
        return "BusinessRuleListJSON{" +
                "businessRules=" + businessRules +
                '}';
    }
}
