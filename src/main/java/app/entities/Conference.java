package app.entities;

public class Conference {
    private String confName;
    private String date;
    private String beginsAt;
    private String endsAt;
    private String location;
    private Boolean acceptedByModer = false;
    private Boolean acceptedBySpeaker = false;
    private int speaker_id;
    private int conference_id;

    public Conference(int conference_id, int speaker_id, String confName, String date,
                      String beginsAt, String endsAt, String location,
                      Boolean acceptedByModer, Boolean acceptedBySpeaker){

        this.confName = confName;
        this.date = date;
        this.beginsAt = beginsAt;
        this.endsAt = endsAt;
        this.location = location;
        this.acceptedByModer = acceptedByModer;
        this.acceptedBySpeaker = acceptedBySpeaker;
        this.speaker_id = speaker_id;
        this.conference_id = conference_id;
    }

    public Conference(int speaker_id, String confName, String date,
                      String beginsAt, String endsAt, String location){

        this.confName = confName;
        this.date = date;
        this.beginsAt = beginsAt;
        this.endsAt = endsAt;
        this.location = location;
        this.speaker_id = speaker_id;
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

    public int getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(int speaker_id) {
        this.speaker_id = speaker_id;
    }

    public int getConference_id() {
        return conference_id;
    }

    public void setConference_id(int conference_id) {
        this.conference_id = conference_id;
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
