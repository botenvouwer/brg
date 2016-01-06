package businessRuleGenerator.domain;

/**
 * Created by melvin on 18-12-2015.
 */
public class Statement {
    public String attribute;
    public int order;
    public String logicalOperator;
    public String comparisonOperator;
    public DynamicAttribute dynamicAttribute;
    public StaticAttribute staticAttribute;

    public Statement(String attribute, int order, String logicalOperator, String comparisonOperator, DynamicAttribute dynamicAttribute, StaticAttribute staticAttribute) {
        this.attribute = attribute;
        this.order = order;
        this.logicalOperator = logicalOperator;
        this.comparisonOperator = comparisonOperator;
        this.dynamicAttribute = dynamicAttribute;
        this.staticAttribute = staticAttribute;
    }

    public Statement() {

    }
}
