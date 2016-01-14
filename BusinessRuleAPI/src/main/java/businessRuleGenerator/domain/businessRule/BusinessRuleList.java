package businessRuleGenerator.domain.businessRule;

import businessRuleGenerator.domain.Validator;
import businessRuleGenerator.domain.ValidatorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public Map<String, ArrayList<BusinessRule>> getBusinessRulesByTable(){

        //Sort Business rules by table name because business rules are generated in blocks per table
        Map<String, ArrayList<BusinessRule>> businessRulesByTable = new HashMap<String, ArrayList<BusinessRule>>();
        ArrayList<BusinessRule> tempBusinessRules = null;
        String previousTableName = "";
        for(BusinessRule rule : businessRules){
            String tableName = rule.table;

            if(!businessRules.contains(tableName)){
                businessRulesByTable.put(tableName, new ArrayList<BusinessRule>());
            }

            if(!tableName.equals(previousTableName)){
                tempBusinessRules = businessRulesByTable.get(tableName);
            }

            tempBusinessRules.add(rule);

            previousTableName = tableName;
        }

        return businessRulesByTable;
    }

    @Override
    public void validate() throws ValidatorException {

        //todo: contoleer of er uberhaubt wel 1 business rule is

        for(BusinessRule br : businessRules) {

            //first of all we sort all statements in the businessrule based on the statements order-number
            br.sortStatements();
            br.validate();
        }
    }


}
