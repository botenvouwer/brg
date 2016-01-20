package businessRuleGenerator.generator;

import businessRuleGenerator.domain.template.DefaultTemplate;
import businessRuleGenerator.domain.template.PLSQLTemplate;
import businessRuleGenerator.domain.template.Template;

/**
 * Created by melvin on 28-12-2015.
 */
public class GeneratorFactory {

    public static BusinessRuleGenerator build(String generatorName, Template template) throws GeneratorException {
        return initiate(generatorName, template);
    }

    private static BusinessRuleGenerator initiate(String generatorName, Template template) throws GeneratorException {

        BusinessRuleGenerator generator;
        switch (generatorName.toLowerCase()) {
            case "plsql":
                generator = new PLSQLGenerator((PLSQLTemplate) template);
                break;
            case "default":
            case "def":
                generator = new DefaultGenerator((DefaultTemplate) template);
                break;
            default:
                throw new GeneratorException("There is no BusinessRuleGenerator with the name: "+generatorName);
        }

        return generator;

    }

}
