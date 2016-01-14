package businessRuleGenerator.domain.template;

import businessRuleGenerator.domain.ValidatorException;
import businessRuleGenerator.util.ListParser;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by william on 31/12/2015.
 */
public class TemplateFactory {

    public static Template build(String templateDirectory, String templateName) throws TemplateException, ValidatorException {

        if(templateName == null || templateName.equals("")){
            throw new TemplateException("templateName is not specified");
        }

        if(templateDirectory == null || templateDirectory.equals("")){
            throw new TemplateException("templateDirectory is not specified");
        }

        Map<String, String> templateFiles = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);

        File templateDir = new File(templateDirectory+"\\"+templateName);

        if(!templateDir.exists() || !templateDir.isDirectory()){
            throw new TemplateException("Template '"+templateName+"' not found in: "+templateDir.getAbsolutePath());
        }

        File[] listOfFiles = templateDir.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {

                File file = listOfFiles[i];
                FileInputStream fis = null;

                try {
                    fis = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    throw new TemplateException("Template file: " + file.getName()+ " not found");
                }

                byte[] data = new byte[(int) file.length()];

                try {
                    fis.read(data);
                    fis.close();
                } catch (IOException e) {
                    throw new TemplateException("IOException when loading template file: " + file.getName());
                }

                String str = null;
                try {
                    str = new String(data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new TemplateException("Unsupported Encoding when loading template file: " + file.getName());
                }



                templateFiles.put(file.getName(), str);
            }
        }

        Map <String, String> config = null;

        System.out.println(templateFiles.get("config"));

        try {
            config = ListParser.loadList(templateFiles.get("config"), Template.getSeperator());
        } catch (Exception e) {
            throw new TemplateException("Can not parse template config. Check your template config file.");
        }

        System.out.println(config);

        return initiate(config, templateFiles);
    }

    private static Template initiate(Map<String, String> templateConfig, Map<String, String> templateFiles) throws TemplateException, ValidatorException {

        Template template;
        switch (templateConfig.get("TemplateClass")) {
            case "plsql":
                template = new PLSQLTemplate(templateConfig.get("TemplateName"), templateFiles);
                break;
            default:
                template = new DefaultTemplate(templateConfig.get("TemplateName"), templateFiles);
                break;
        }

        template.validate();

        return template;
    }

}
