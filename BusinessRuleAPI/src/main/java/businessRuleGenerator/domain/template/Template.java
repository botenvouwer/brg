package businessRuleGenerator.domain.template;

import businessRuleGenerator.domain.Validator;
import businessRuleGenerator.domain.ValidatorException;
import businessRuleGenerator.util.ListParser;

import java.util.Map;

/**
 * Created by william on 31/12/2015.
 */
public abstract class Template implements Validator{

    public String name;
    public String body;
    public String statement;
    public Map<String, String> comparisonOperator;
    public Map<String, String> logicalOperator;
    public Map<String, String> dataType;
    public Map<String, String> stringOperator;
    private static String seperator = ":-:";

    public Template(){

    }

    public static String getSeperator() {
        return seperator;
    }

    public Template(String name, Map<String, String> templateData) throws TemplateException {
        this.name = name;
        body = templateData.get("body");
        statement = templateData.get("statement");

        try {
            comparisonOperator = ListParser.loadList(templateData.get("comparisonOperators"), seperator);
            logicalOperator = ListParser.loadList(templateData.get("logicalOperators"), seperator);
            dataType = ListParser.loadList(templateData.get("dataTypes"), seperator);
            stringOperator = ListParser.loadList(templateData.get("stringOperators"), seperator);
        } catch (Exception e) {
            throw new TemplateException("Could not parse template files. Check the list files in the template.");
        }
    }

    public void validate() throws ValidatorException {

        if(name == null || name.equals("")){
            throw new ValidatorException("templateName is not specified");
        }

        if(body == null || body.equals("")){
            throw new ValidatorException("Template "+ name +" does not contain body template");
        }

        if(statement == null || statement.equals("")){
            throw new ValidatorException("Template "+ name +" does not contain statement template");
        }

        if(comparisonOperator == null || comparisonOperator.size() == 0){
            throw new ValidatorException("Template "+ name +" does not contain comparisonOperators");
        }

        if(logicalOperator == null || logicalOperator.size() == 0){
            throw new ValidatorException("Template "+ name +" does not contain logicalOperators");
        }

        if(dataType == null || dataType.size() == 0){
            throw new ValidatorException("Template "+ name +" does not contain dataTypes");
        }

        if(stringOperator == null || stringOperator.size() == 0){
            throw new ValidatorException("Template "+ name +" does not contain stringOperators");
        }

    }

    @Override
    public String toString() {
        return "Template{" +
                "name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", statement='" + statement + '\'' +
                ", comparisonOperator=" + comparisonOperator +
                ", logicalOperator=" + logicalOperator +
                ", dataType=" + dataType +
                ", stringOperator=" + stringOperator +
                '}';
    }
}
