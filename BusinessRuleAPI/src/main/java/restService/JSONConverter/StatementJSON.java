package restService.JSONConverter;

import businessRuleGenerator.domain.ValidatorException;
import businessRuleGenerator.domain.businessRule.DynamicAttribute;
import businessRuleGenerator.domain.businessRule.Statement;
import businessRuleGenerator.domain.businessRule.StaticAttribute;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by melvin on 12-1-2016.
 */
public class StatementJSON extends Statement {

    public StatementJSON(@JsonProperty("attribute")String attribute, @JsonProperty("order")int order, @JsonProperty("logicalOperator")String logicalOperator, @JsonProperty("comparisonOperator")String comparisonOperator, @JsonProperty("dynamicAttribute")DynamicAttributeJSON dynamicAttribute, @JsonProperty("staticAttribute")StaticAttributeJSON staticAttribute) {
        this.attribute = attribute;
        this.order = order;
        this.logicalOperator = logicalOperator;
        this.comparisonOperator = comparisonOperator;
        this.dynamicAttribute = dynamicAttribute;
        this.staticAttribute = staticAttribute;
    }
}
