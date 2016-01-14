package businessRuleGenerator.generator;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.template.Template;
import businessRuleGenerator.domain.template.TemplateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by melvin on 28-12-2015.
 */
public abstract class BusinessRuleGenerator{

    protected Template template;
    protected ArrayList<String> code = new ArrayList<String>();

    public BusinessRuleGenerator(Template template){
        this.template = template;
    }

    public ArrayList<String> generate(ArrayList<BusinessRule> businessRules) throws GeneratorException, TemplateException{

        Map<String, ArrayList<BusinessRule>> rulesByTable = sortRulesOnTableName(businessRules);
        code = new ArrayList<String>();

        for(Map.Entry<String, ArrayList<BusinessRule>> entry : rulesByTable.entrySet()){
            buildBody(entry.getKey(), entry.getValue());
        }

        return code;
    }

    protected Map<String, ArrayList<BusinessRule>> sortRulesOnTableName(ArrayList<BusinessRule> businessRules){

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

    protected abstract void buildBody(String tableName, ArrayList<BusinessRule> rules);
    protected abstract void buildRules();
    protected abstract void buildStatements();

}
