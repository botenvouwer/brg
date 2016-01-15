package businessRuleGenerator.generator;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.businessRule.BusinessRuleList;
import businessRuleGenerator.domain.businessRule.Statement;
import businessRuleGenerator.domain.template.Template;
import businessRuleGenerator.domain.template.TemplateException;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by melvin on 28-12-2015.
 */
public abstract class BusinessRuleGenerator{

    protected Template template;

    public BusinessRuleGenerator(Template template){
        this.template = template;
    }

    public ArrayList<String> generate(BusinessRuleList businessRules) throws GeneratorException, TemplateException{

        ArrayList<String> code = new ArrayList<String>();
        Map<String, ArrayList<BusinessRule>> rulesByTable = businessRules.getBusinessRulesByTable();

        for(Map.Entry<String, ArrayList<BusinessRule>> entry : rulesByTable.entrySet()){

            String body = buildBody(entry.getKey(), entry.getValue());
            code.add(body);
        }

        return code;
    }

    protected String buildStaticValue(String value, String dataType){
        if(dataType.equals("Int")){
            //If dataType is Int we make sure that we really create an int value
            return value.replaceAll("[^\\d.]", "");
        }
        else{
            //Or else we create String (or other data format)
            return template.dataType.get(dataType).replace("{$value}", value);
        }
    }

    protected abstract String buildBody(String tableName, ArrayList<BusinessRule> rules);
    protected abstract String buildRule(BusinessRule rule);
    protected abstract String buildStatements(Statement statement);

}
