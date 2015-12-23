package businessRuleGenerator.domain;

/**
 * Created by melvin on 18-12-2015.
 */
public class Statement {
    public int code;
    public String attribute;
    public String logicalOperator;
    public String comparisonOperator;
    public String dynamicAttribute;
    public StaticAttribute staticAttribute = new StaticAttribute("25", "Number");

    public Statement(int code, String attribute, String logicalOperator, String comparisonOperator, String dynamicAttribute) {
        this.code = code;
        this.attribute = attribute;
        this.logicalOperator = logicalOperator;
        this.comparisonOperator = comparisonOperator;
        this.dynamicAttribute = dynamicAttribute;
    }
}
