package app.entities;

public class Conference {
    private String confName;
    private String date;
    private String beginsAt;
    private String endsAt;
    private String location;
    private Boolean acceptedByModer = false;
    private Boolean acceptedBySpeaker = false;
    private int speakerId;
    private int conferenceId;

    public Conference(int conferenceId, int speakerId, String confName, String date,
                      String beginsAt, String endsAt, String location,
                      Boolean acceptedByModer, Boolean acceptedBySpeaker){

        this.confName = confName;
        this.date = date;
        this.beginsAt = beginsAt;
        this.endsAt = endsAt;
        this.location = location;
        this.acceptedByModer = acceptedByModer;
        this.acceptedBySpeaker = acceptedBySpeaker;
        this.speakerId = speakerId;
        this.conferenceId = conferenceId;
    }

    public Conference(int speakerId, String confName, String date,
                      String beginsAt, String endsAt, String location,
                      Boolean acceptedByModer, Boolean acceptedBySpeaker){

        this.confName = confName;
        this.date = date;
        this.beginsAt = beginsAt;
        this.endsAt = endsAt;
        this.location = location;
        this.acceptedByModer = acceptedByModer;
        this.acceptedBySpeaker = acceptedBySpeaker;
        this.speakerId = speakerId;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBeginsAt() {
        return beginsAt;
    }

    public void setBeginsAt(String beginsAt) {
        this.beginsAt = beginsAt;
    }

    public String getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(String endsAt) {
        this.endsAt = endsAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getAcceptedByModer() {
        return acceptedByModer;
    }

    public void setAcceptedByModer(Boolean acceptedByModer) {
        this.acceptedByModer = acceptedByModer;
    }

    public Boolean getAcceptedBySpeaker() {
        return acceptedBySpeaker;
    }

    public void setAcceptedBySpeaker(Boolean acceptedBySpeaker) {
        this.acceptedBySpeaker = acceptedBySpeaker;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "name'" + confName + '\'' +
                ", date'" + date + '\'' +
                ", begins at'" + beginsAt + '\'' +
                ", ends at'" + endsAt + '\'' +
                ", location'" + location + '\'' +
                '}';
    }
}
