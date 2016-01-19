package businessRuleGenerator.domain.template;

import businessRuleGenerator.domain.ValidatorException;
import businessRuleGenerator.util.ListParser;

import java.util.Map;

/**
 * Created by william on 31/12/2015.
 */
public class PLSQLTemplate extends Template {

    public Map<String, String> CRUDMode;
    public String CRUDStatement;

    public PLSQLTemplate(String name, Map<String, String> templateData) throws TemplateException {
        super(name, templateData);

        try{
            CRUDMode = ListParser.loadList(templateData.get("CRUDModes"), getSeperator());
        } catch (Exception e) {
            throw new TemplateException("Could not parse CRUDModes file. Check the syntax.");
        }

        CRUDStatement = templateData.get("CRUDStatement");
    }

    @Override
    public void validate() throws ValidatorException {
        super.validate();

        if(CRUDStatement == null || CRUDStatement.equals("")){
            throw new ValidatorException("CRUDStatement is not specified");
        }

        if(CRUDMode == null || CRUDMode.size() == 0){
            throw new ValidatorException("Template "+ name +" does not contain CRUDModes");
        }
    }
}
