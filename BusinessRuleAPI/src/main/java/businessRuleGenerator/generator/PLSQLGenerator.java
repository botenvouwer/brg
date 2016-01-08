package businessRuleGenerator.generator;

import businessRuleGenerator.domain.BusinessRule;
import businessRuleGenerator.template.Template;
import businessRuleGenerator.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by melvin on 28-12-2015.
 */
public class PLSQLGenerator extends BusinessRuleGenerator {

    public PLSQLGenerator(Template template) {
        super(template);
    }

    @Override
    public String generate(ArrayList<BusinessRule> businessRules) throws GeneratorException, TemplateException {
        //genereer busniness rule plsql code
        //String statements = "IF 1=1 THEN raise_application_error(-30000, 'haha');";
        //String template = new String(Files.readAllBytes(Paths.get("../../../webapp/plsql/body.txt")));
        //template.replace("{&body}", statements);
        return null;
    }
}
