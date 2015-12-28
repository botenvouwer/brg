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

    public Statement() {

    }
}
