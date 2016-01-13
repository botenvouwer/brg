package restService.JSONConverter;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

/**
 * Created by melvin on 13-1-2016.
 */
public class BusinessRuleList {

    public ArrayList<BusinessRule> businessRules;

    public BusinessRuleList(){
    }

    @Override
    public String toString() {
        return "BusinessRuleList{" +
                "businessRules=" + businessRules +
                '}';
    }
}
