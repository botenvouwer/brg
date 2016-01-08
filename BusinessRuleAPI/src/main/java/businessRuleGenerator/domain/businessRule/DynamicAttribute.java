package businessRuleGenerator.domain.businessRule;

import businessRuleGenerator.domain.Validator;
import businessRuleGenerator.domain.ValidatorException;

/**
 * Created by melvin on 28-12-2015.
 */
public class DynamicAttribute implements Validator {
    public String attribute;
    public String foreignKey; //opt
    public String table; //opt

    public DynamicAttribute(String attribute, String foreignKey, String table) throws ValidatorException {
        this.attribute = attribute;
        this.foreignKey = foreignKey;
        this.table = table;
        validate();
    }

    public DynamicAttribute() {

    }

    @Override
    public void validate() throws ValidatorException {
        if(attribute == null) throw new ValidatorException("A dynamic attribute requires an attribute.");
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
