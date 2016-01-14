package businessRuleGenerator.generator;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import businessRuleGenerator.domain.businessRule.Statement;
import businessRuleGenerator.domain.template.Template;
import businessRuleGenerator.domain.template.TemplateException;

import java.util.ArrayList;

/**
 * Created by melvin on 28-12-2015.
 */
public class PLSQLGenerator extends BusinessRuleGenerator {

    public PLSQLGenerator(Template template) {
        super(template);
    }

    @Override
    protected String buildBody(String tableName, ArrayList<BusinessRule> rules) {
        return null;
    }

    @Override
    protected String buildRules(BusinessRule rule) {
        return null;
    }

    @Override
    protected String buildStatements(Statement statement) {
        return null;
    }





    /*
    * //genereer busniness rule plsql code
        //String statements = "IF 1=1 THEN raise_application_error(-30000, 'haha');";
        //String template = new String(Files.readAllBytes(Paths.get("../../../webapp/plsql/body.txt")));
        //template.replace("{&body}", statements);
        return null;
    *
    * */
}
