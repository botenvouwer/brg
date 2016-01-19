package businessRuleGenerator.domain.businessRule;

import businessRuleGenerator.domain.Validator;
import businessRuleGenerator.domain.ValidatorException;

/**
 * Created by melvin on 18-12-2015.
 */
public class StaticAttribute implements Validator{
    public String value;
    public String dataType;

    public StaticAttribute(String value, String dataType){
        this.value = value;
        this.dataType = dataType;
    }

    public StaticAttribute() {

    }

    @Override
    public void validate() throws ValidatorException {
        if(value == null) throw new ValidatorException("A static attribute requires a value.");
        if(dataType == null) throw new ValidatorException("A static attribute requires a data-type.");
    }

    @Override
    public String toString() {
        return "StaticAttribute{" +
                "value='" + value + '\'' +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
