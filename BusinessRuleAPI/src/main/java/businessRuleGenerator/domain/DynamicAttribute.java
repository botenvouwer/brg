package businessRuleGenerator.domain;

/**
 * Created by melvin on 28-12-2015.
 */
public class DynamicAttribute {
    public String attribute;
    public String foreignKey;
    public String table;

    public DynamicAttribute(String attribute, String foreignKey, String table) {
        this.attribute = attribute;
        this.foreignKey = foreignKey;
        this.table = table;
    }
}
