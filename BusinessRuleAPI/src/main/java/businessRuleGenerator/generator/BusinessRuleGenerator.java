package businessRuleGenerator.generator;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.template.Template;
import businessRuleGenerator.domain.template.TemplateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by melvin on 28-12-2015.
 */
public abstract class BusinessRuleGenerator{

    protected Template template;
    protected String code;
    protected Map<String, ArrayList<BusinessRule>> businessRules = new HashMap<String, ArrayList<BusinessRule>>();

    public BusinessRuleGenerator(Template template){
        this.template = template;
    }

    public String generate(ArrayList<BusinessRule> businessRules) throws GeneratorException, TemplateException{

        sortRulesOnTableName(businessRules);
        code = "";
        buildBody();
        buildRules();

        return code;
    }

    protected void sortRulesOnTableName(ArrayList<BusinessRule> businessRules){

    }

    protected abstract void buildBody();
    protected abstract void buildRules();
    protected abstract void buildStatements();

}
