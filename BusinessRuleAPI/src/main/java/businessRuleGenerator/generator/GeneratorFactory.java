package businessRuleGenerator.generator;

import businessRuleGenerator.template.Template;

/**
 * Created by melvin on 28-12-2015.
 */
public class GeneratorFactory {

    static BusinessRuleGenerator build(String generatorName, Template template) throws GeneratorException {
        return initiate(generatorName, template);
    }

    private static BusinessRuleGenerator initiate(String generatorName, Template template) throws GeneratorException {

        BusinessRuleGenerator generator;
        switch (generatorName) {
            case "plsql":
                generator = new PLSQLGenerator(template);
                break;
            default:
                throw new GeneratorException("There is no BusinessRuleGenerator with the name: "+generatorName);
        }

        return generator;

    }

}
