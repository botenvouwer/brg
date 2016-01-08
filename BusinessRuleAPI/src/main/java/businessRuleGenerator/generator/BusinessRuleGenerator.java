package businessRuleGenerator.generator;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.template.Template;
import businessRuleGenerator.domain.template.TemplateException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by melvin on 28-12-2015.
 */
public abstract class BusinessRuleGenerator{

    Template template;

    public BusinessRuleGenerator(Template template){
        this.template = template;
    }

    public abstract String generate(ArrayList<BusinessRule> businessRules) throws IOException, GeneratorException, TemplateException;

}
