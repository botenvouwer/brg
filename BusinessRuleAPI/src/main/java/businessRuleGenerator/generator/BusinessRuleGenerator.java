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
        code = new ArrayList<String>();

        for(Map.Entry<String, ArrayList<BusinessRule>> entry : rulesByTable.entrySet()){
            code.add(buildBody(entry.getKey(), entry.getValue()));
        }

        return code;
    }

    protected abstract String buildBody(String tableName, ArrayList<BusinessRule> rules);
    protected abstract String buildRules(BusinessRule rule);
    protected abstract String buildStatements(Statement statement);

}
