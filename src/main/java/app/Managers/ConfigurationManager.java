package app.Managers;

import org.apache.log4j.Logger;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static final Logger logger = Logger.getLogger(ConfigurationManager.class);

    private static ConfigurationManager instance = null;

    private ResourceBundle resourceBundle = ResourceBundle.getBundle(EnumManager.BUNDLE_NAME.toString());

    private ConfigurationManager(){}

    public static ConfigurationManager getInstance(){
//        logger.info("getConfigManager");
        if(instance == null){
           instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resourceBundle.getObject(key);
    }

}
