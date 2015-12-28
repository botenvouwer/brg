package businessRuleGenerator.generator;

import businessRuleGenerator.domain.BusinessRule;

import java.util.ArrayList;

/**
 * Created by melvin on 28-12-2015.
 */
public interface BusinessRuleGenerator {

    public String generate(ArrayList<BusinessRule> businessRules);

}
