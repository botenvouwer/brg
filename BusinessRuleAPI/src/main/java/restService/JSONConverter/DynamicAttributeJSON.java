package restService.JSONConverter;

import businessRuleGenerator.domain.ValidatorException;
import businessRuleGenerator.domain.businessRule.DynamicAttribute;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by melvin on 12-1-2016.
 */
public class DynamicAttributeJSON extends DynamicAttribute {

    public DynamicAttributeJSON(@JsonProperty("attribute")String attribute, @JsonProperty("foreignKey")String foreignKey, @JsonProperty("table")String table) {
        this.attribute = attribute;
        this.foreignKey = foreignKey;
        this.table = table;
    }
}
