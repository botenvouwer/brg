package businessRuleGenerator.generator;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.businessRule.BusinessRuleList;
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

    public ArrayList<String> generate(BusinessRuleList businessRules) throws GeneratorException, TemplateException{

        Map<String, ArrayList<BusinessRule>> rulesByTable = businessRules.getBusinessRulesByTable();
        code = new ArrayList<String>();

        for(Map.Entry<String, ArrayList<BusinessRule>> entry : rulesByTable.entrySet()){
            code.add(buildBody(entry.getKey(), entry.getValue()));
        }

        return code;
    }

    protected abstract String buildBody(String tableName, ArrayList<BusinessRule> rules);
    protected abstract String buildRules();
    protected abstract String buildStatements();

}
