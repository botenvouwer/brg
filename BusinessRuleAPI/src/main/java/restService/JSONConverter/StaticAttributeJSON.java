package restService.JSONConverter;

import businessRuleGenerator.domain.businessRule.StaticAttribute;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by melvin on 12-1-2016.
 */
public class StaticAttributeJSON extends StaticAttribute {

    public StaticAttributeJSON(@JsonProperty("value")String value, @JsonProperty("dataType")String dataType){
        this.value = value;
        this.dataType = dataType;
    }
}
