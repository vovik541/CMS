package app.commands.factory;

import app.Managers.EnumManager;
import app.commands.command.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {


    private static final Logger logger = Logger.getLogger(CommandFactory.class);

    private CommandFactory instance;

    public CommandFactory(){
        if(this.instance != null){
            this.instance = new CommandFactory();
        }
    }

    public ICommand defineCommand(HttpServletRequest request) {

        ICommand current = new EmptyCommand();

        String commandFromFront = request.getParameter(EnumManager.COMMAND.toString());  //COMMAND = "command";
        logger.info("COMMAND IN FACTORY "+ commandFromFront);

        if (commandFromFront == null || commandFromFront.isEmpty()) {
            logger.info("In Factory defined like EMPTY!");
            return current;
        }

        try {
            EnumCommand currentEnum = EnumCommand.valueOf(commandFromFront.toUpperCase());
//            logger.info(currentEnum.getCurrentCommand().toString());
            current = currentEnum.getCurrentCommand();
            logger.info(current.toString());
        } catch (IllegalArgumentException e) {
            logger.warn("WRONG ACTION in CommandFactory. Impossible action/command/path !!!");
//            request.setAttribute("wrongAction", action
//                    + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }


    public SignInCommand getCommandLogin(){
        return new SignInCommand();
    }
}
