package businessRuleGenerator.template;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by william on 31/12/2015.
 */
public abstract class Template {

    public String name;
    public String body;
    public String statement;
    public Map<String, String> comparisonOperator;
    public Map<String, String> logicalOperator;
    public Map<String, String> dataType;
    public Map<String, String> stringOperator;

    public Template(Map<String, String> templateData, String name) throws TemplateException {
        this.name = name;
        body = templateData.get("body");
        statement = templateData.get("statement");

        if(body == null || body.equals("")){
            throw new TemplateException("Template "+ name +" does not contain body template");
        }

        if(statement == null || statement.equals("")){
            throw new TemplateException("Template "+ name +" does not contain statement template");
        }

        comparisonOperator = loadList(templateData.get("comparisonOperators"));
        logicalOperator = loadList(templateData.get("logicalOperators"));
        dataType = loadList(templateData.get("dataTypes"));
        stringOperator = loadList(templateData.get("stringOperators"));

        if(comparisonOperator == null || comparisonOperator.size() == 0){
            throw new TemplateException("Template "+ name +" does not contain comparisonOperators");
        }

        if(logicalOperator == null || logicalOperator.size() == 0){
            throw new TemplateException("Template "+ name +" does not contain logicalOperators");
        }

        if(dataType == null || dataType.size() == 0){
            throw new TemplateException("Template "+ name +" does not contain dataTypes");
        }

        if(stringOperator == null || stringOperator.size() == 0){
            throw new TemplateException("Template "+ name +" does not contain stringOperators");
        }

    }

    protected Map<String, String> loadList(String templateFile) throws TemplateException {
        Map<String, String> list = new HashMap<String, String>();
        try {
            String[] splitList = templateFile.split("\\r");

            for (String element : splitList) {
                String[] splitElement = element.split("\\s+");
                list.put(splitElement[0], splitElement[1]);
            }
        }
        catch (ArrayIndexOutOfBoundsException ex){
            throw new TemplateException("Could not load template file with LoadList (check file endings in your template list files)");
        }
        return list;
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
