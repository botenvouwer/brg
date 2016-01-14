package businessRuleGenerator.domain.businessRule;

import businessRuleGenerator.domain.Validator;
import businessRuleGenerator.domain.ValidatorException;
import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.businessRule.Statement;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

/**
 * Created by melvin on 13-1-2016.
 */
public class BusinessRuleList implements Validator {

    public ArrayList<BusinessRule> businessRules;

    @Override
    public String toString() {
        return "BusinessRuleList{" +
                "businessRules=" + businessRules +
                '}';
    }

    @Override
    public void validate() throws ValidatorException {
        for(BusinessRule br : businessRules) {

            //first of all we sort all statements in the businessrule based on the statements order-number
            br.sortStatements();

            //validate each businessRule in the list
            br.validate();

            //
            for(Statement s : br.statements) {

                //validate each statement in each businessrule
                s.validate();

                //depending on what kind of attribute the statement uses, we also want to validate that
                if(s.staticAttribute != null) s.staticAttribute.validate();
                if(s.dynamicAttribute != null) s.dynamicAttribute.validate();
            }
        }
    }
}
