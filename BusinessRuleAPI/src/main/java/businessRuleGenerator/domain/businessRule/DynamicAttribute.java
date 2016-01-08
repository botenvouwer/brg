package businessRuleGenerator.domain.businessRule;

/**
 * Created by melvin on 28-12-2015.
 */
public class DynamicAttribute {
    public String attribute;
    public String foreignKey; //opt
    public String table; //opt

    public DynamicAttribute(String attribute, String foreignKey, String table) {
        this.attribute = attribute;
        this.foreignKey = foreignKey;
        this.table = table;
    }

    public DynamicAttribute() {

    }

    @Override
    public String toString() {
        return "DynamicAttribute{" +
                "attribute='" + attribute + '\'' +
                ", foreignKey='" + foreignKey + '\'' +
                ", table='" + table + '\'' +
                '}';
    }
}
