package app.commands.command;

public enum EnumCommand {
    SIGN_IN{
        {
            this.command = new SignInCommand();
        }
    },
    SIGN_UP{
        {
            this.command = new SignUpCommand();
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
    MODER_CABINET{
        {
            this.command = new ModerCabinetCommand();
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
