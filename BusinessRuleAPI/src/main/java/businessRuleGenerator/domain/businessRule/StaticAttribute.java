package businessRuleGenerator.domain.businessRule;

/**
 * Created by melvin on 18-12-2015.
 */
public class StaticAttribute {
    public String value;
    public String dataType;

    public StaticAttribute(String value, String dataType) {
        this.value = value;
        this.dataType = dataType;
    }

    public StaticAttribute() {

    }

    @Override
    public String toString() {
        return "StaticAttribute{" +
                "value='" + value + '\'' +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
