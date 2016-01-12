package restService.JSONConverter;

import businessRuleGenerator.domain.businessRule.BusinessRule;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by melvin on 10-1-2016.
 */
public class BusinessRuleJSON extends BusinessRule {

    public BusinessRuleJSON(@JsonProperty("category")String category, @JsonProperty("type")String type, @JsonProperty("code")String code, @JsonProperty("table")String table, @JsonProperty("CRUDmode")String CRUDmode, @JsonProperty("ruleDescription")String ruleDescription, @JsonProperty("typeDescription")String typeDescription, @JsonProperty("errorMessage")String errorMessage){
        this.category = category;
        this.type = type;
        this.code = code;
        this.table = table;
        this.CRUDmode = CRUDmode;
        this.ruleDescription = ruleDescription;
        this.typeDescription = typeDescription;
        //this.statements = statements;
        this.errorMessage = errorMessage;
        //sortStatements();
    }

}
