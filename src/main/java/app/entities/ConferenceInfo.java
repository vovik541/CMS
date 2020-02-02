package app.entities;

public class ConferenceInfo {
    private int conferenceId;
    private int numberOfRegisteredUsers;
    private int numberOfPresentUsers;

    public ConferenceInfo(){

    }

    public ConferenceInfo(int conferenceId, int numberOfRegisteredUsers, int numberOfPresentUsers) {
        this.conferenceId = conferenceId;
        this.numberOfRegisteredUsers = numberOfRegisteredUsers;
        this.numberOfPresentUsers = numberOfPresentUsers;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public int getNumberOfRegisteredUsers() {
        return numberOfRegisteredUsers;
    }

    public void setNumberOfRegisteredUsers(int numberOfRegisteredUsers) {
        this.numberOfRegisteredUsers = numberOfRegisteredUsers;
    }

    public int getNumberOfPresentUsers() {
        return numberOfPresentUsers;
    }

    public void setNumberOfPresentUsers(int numberOfPresentUsers) {
        this.numberOfPresentUsers = numberOfPresentUsers;
    }

    @Override
    public String toString(){
        return "id: " + conferenceId +
                "users registered: " + numberOfRegisteredUsers +
                "users present:" + numberOfPresentUsers;
    }
}
