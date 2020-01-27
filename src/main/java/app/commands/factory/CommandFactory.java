package app.commands.factory;

import app.commands.command.EnumCommand;
import app.commands.command.EmptyCommand;
import app.commands.command.SignInCommand;
import app.commands.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CommandFactory {


    private static final Logger logger = Logger.getLogger(CommandFactory.class);

    private static final String COMMAND = "command";

    private CommandFactory instance;

    public CommandFactory(){
        if(this.instance != null){
            this.instance = new CommandFactory();
        }
    }

    public ICommand defineCommand(HttpServletRequest request) {

        ICommand current = new EmptyCommand();

        String action = request.getParameter(COMMAND);

        if (action == null || action.isEmpty()) {
            logger.info("In Factory defined like EMPTY!");
            return current;
        }

        try {
            EnumCommand currentEnum = EnumCommand.valueOf(action.toUpperCase());   //SIGN_IN USER_CABINET
            current = currentEnum.getCurrentCommand();
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
