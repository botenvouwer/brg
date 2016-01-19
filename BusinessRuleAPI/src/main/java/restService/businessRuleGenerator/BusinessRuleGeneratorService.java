package restService.businessRuleGenerator;

import businessRuleGenerator.domain.ValidatorException;
import businessRuleGenerator.domain.businessRule.BusinessRuleList;
import businessRuleGenerator.domain.template.Template;
import businessRuleGenerator.domain.template.TemplateException;
import businessRuleGenerator.domain.template.TemplateFactory;
import businessRuleGenerator.generator.BusinessRuleGenerator;
import businessRuleGenerator.generator.GeneratorException;
import businessRuleGenerator.generator.GeneratorFactory;
import restService.response.Result;
import restService.response.Rule;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;

/**
 * Created by william on 17-Dec-15.
 */

@Path("/businessrulegenerator")
public class BusinessRuleGeneratorService {

    @Context
    ServletContext servletContext;

    @POST
    @Path("/{templateName}/{generatorName}")
    @Consumes("application/json")
    @Produces("application/json")
    public Result index(BusinessRuleList rulesList, @PathParam("templateName") String templateName, @PathParam("generatorName") String generatorName){
        return getJSON(rulesList, templateName, generatorName);
    }

    @POST
    @Path("/generate/{templateName}/{generatorName}")
    @Consumes("application/json")
    @Produces("application/json")
    public Result getJSON(BusinessRuleList rulesList, @PathParam("templateName") String templateName, @PathParam("generatorName") String generatorName) {

        Result result = new Result();
        result.status = "success";

        //haal het path naar de template directory op
        String templateRoot = servletContext.getRealPath("templates");

        try{
            rulesList.validate();
        }
        catch (ValidatorException e){
            result.error = "ValidatorException: "+ e.getMessage();
            result.status = "error";
        }

        //Maak een template aan
        Template template = null;
        try {
            template = TemplateFactory.build(templateRoot, templateName);
        }
        catch (TemplateException e){
            result.error = "TemplateException: "+ e.getMessage();
            result.status = "error";
        }
        catch (ValidatorException e) {
            result.error = "ValidatorException: "+ e.getMessage();
            result.status = "error";
        }

        //Maak generator aan en genereer
        ArrayList<String> code = null;
        BusinessRuleGenerator generator = null;
        try {
            generator = GeneratorFactory.build(generatorName,template);
            code = generator.generate(rulesList);
        }
        catch (GeneratorException e) {
            result.error = "GeneratorException: "+ e.getMessage();
            result.status = "error";
        }
        catch (TemplateException e) {
            result.error = "TemplateException: "+ e.getMessage();
            result.status = "error";
        }

        //Anders kan dat kut apex het weer niet parsen
        ArrayList<Rule> finalCode = new ArrayList<Rule>();
        for (String s : code){
            Rule rule = new Rule();
            rule.rule = s;
            finalCode.add(rule);
        }

        result.result = finalCode;

        return result;
    }

}
