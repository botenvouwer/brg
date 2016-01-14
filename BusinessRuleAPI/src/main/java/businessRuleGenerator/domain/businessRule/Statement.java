package businessRuleGenerator.domain.businessRule;

import businessRuleGenerator.domain.Validator;
import businessRuleGenerator.domain.ValidatorException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by melvin on 18-12-2015.
 */
public class Statement implements Validator {
    public String attribute;
    public int order;
    public String logicalOperator;
    public String comparisonOperator; //opt

    //Een van deze twee moet zijn gevult
    public DynamicAttribute dynamicAttribute;
    public StaticAttribute staticAttribute;

    public Statement(String attribute, int order, String logicalOperator, String comparisonOperator, DynamicAttribute dynamicAttribute, StaticAttribute staticAttribute) throws ValidatorException {
        this.attribute = attribute;
        this.order = order;
        this.logicalOperator = logicalOperator;
        this.comparisonOperator = comparisonOperator;
        this.dynamicAttribute = dynamicAttribute;
        this.staticAttribute = staticAttribute;
    }

    public Statement() {

    }

    @Override
    public void validate() throws ValidatorException {
        if(attribute == null) throw new ValidatorException("Statement with order-number " + order + " requires an attribute.");
        if(logicalOperator == null) throw new ValidatorException("Statement with order-number " + order + " requires a logical operator.");
        if(dynamicAttribute == null && staticAttribute == null) throw new ValidatorException("Statement with order-number " + order + " requires to have either a dynamic attribute or a static attribute.");
        if(dynamicAttribute != null && staticAttribute != null) throw new ValidatorException("Statement with order-number " + order + " cannot have both a dynamic attribute and a static attribute.");
        (dynamicAttribute == null ? staticAttribute : dynamicAttribute).validate();
    }

    @Override
    public String toString() {
        return "Statement{" +
                "attribute='" + attribute + '\'' +
                ", order=" + order +
                ", logicalOperator='" + logicalOperator + '\'' +
                ", comparisonOperator='" + comparisonOperator + '\'' +
                ", dynamicAttribute=" + dynamicAttribute +
                ", staticAttribute=" + staticAttribute +
                '}';
    }
}
