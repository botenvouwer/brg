package businessRuleGenerator.domain;

/**
 * Created by melvin on 18-12-2015.
 */
public class Statement {
    public int code;
    public String attribute;
    public int order;
    public String logicalOperator;
    public String comparisonOperator;
    public DynamicAttribute dynamicAttribute;
    public StaticAttribute staticAttribute;

    public Statement(int code, String attribute, int order, String logicalOperator, String comparisonOperator) {
        this.code = code;
        this.attribute = attribute;
        this.order = order;
        this.logicalOperator = logicalOperator;
        this.comparisonOperator = comparisonOperator;
    }

    public Statement() {

    }
}
