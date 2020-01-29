package app.commands.command;

public enum EnumCommand {
    SIGN_IN{
        {
            this.command = new SignInCommand();
        }
    },
    USER_CABINET{
        {
            this.command = new UserCabinetCommand();
        }
    },
    SPEAKER_CABINET{
        {
            this.command = new SpeakerCabinetCommand();
        }
    },
    SIGN_OUT{
        {
            this.command = new SignOutCommand();
        }
    };

    ICommand command;
    public ICommand getCurrentCommand(){
        return command;
    }
}
