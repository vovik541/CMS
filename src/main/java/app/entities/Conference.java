package app.entities;

public class Conference {

    private String speakerFirstName;
    private String speakerLastName;

    private String confName;
    private String date;
    private String beginsAt;
    private String endsAt;
    private String location;
    private Boolean acceptedByModer = false;
    private Boolean acceptedBySpeaker = false;
    private int speakerId;
    private int conferenceId;
    private int rate;

    private boolean isPresent;
    private boolean isRegistered;

    public Conference(int conferenceId, int speakerId, String confName, String date,
                      String beginsAt, String endsAt, String location,
                      Boolean acceptedByModer, Boolean acceptedBySpeaker,
                      String speakerFirstName, String speakerLastName,
                      boolean isRegistered, boolean isPresent, int rate){

        this.confName = confName;
        this.date = date;
        this.beginsAt = beginsAt;
        this.endsAt = endsAt;
        this.location = location;
        this.acceptedByModer = acceptedByModer;
        this.acceptedBySpeaker = acceptedBySpeaker;
        this.speakerId = speakerId;
        this.conferenceId = conferenceId;
        this.speakerFirstName = speakerFirstName;
        this.speakerLastName = speakerLastName;
        this.isRegistered = isRegistered;
        this.isPresent = isPresent;
        this.rate = rate;
    }

    public static class Builder {
        private String speakerFirstName = null;
        private String speakerLastName = null;

        private String confName;
        private String date;
        private String beginsAt;
        private String endsAt;
        private String location;
        private Boolean acceptedByModer = false;
        private Boolean acceptedBySpeaker = false;
        private int speakerId;
        private int conferenceId = 0;
        private int rate = 0;

        private boolean isPresent = false;
        private boolean isRegistered = false;

        public Builder(int speakerId, String conferenceName, String location, String date, String beginsAt, String endsAtTime) {
            this.speakerId = speakerId;
            this.confName = conferenceName;
            this.location = location;
            this.date = date;
            this.beginsAt = beginsAt;
            this.endsAt = endsAtTime;
        }

        public Builder setAcceptedByModer(Boolean acceptedByModer) {
            this.acceptedByModer = acceptedByModer;
            return this;
        }

        public Builder setAcceptedBySpeaker(Boolean acceptedBySpeaker) {
            this.acceptedBySpeaker = acceptedBySpeaker;
            return this;
        }

        public Builder setSpeakerFirstName(String speakerFirstName) {
            this.speakerFirstName = speakerFirstName;
            return this;
        }

        public Builder setSpeakerLastName(String speakerLastName) {
            this.speakerLastName = speakerLastName;
            return this;
        }

        public Builder setConferenceId(int conferenceId) {
            this.conferenceId = conferenceId;
            return this;
        }

        public Builder setRate(int rate) {
            this.rate = rate;
            return this;
        }

        public Builder setPresent(Boolean present) {
            this.isPresent = present;
            return this;
        }

        public Builder setRegistered(Boolean registered) {
            this.isRegistered = registered;
            return this;
        }

        public Conference build() {
            return new Conference(this.conferenceId, this.speakerId, this.confName,
                    this.date, this.beginsAt, this.endsAt, this.location,
                    this.acceptedByModer, this.acceptedBySpeaker,
                    this.speakerFirstName, this.speakerLastName, this.isRegistered,
                    this.isPresent, this.rate);
        }
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

    public String getSpeakerFirstName() {
        return speakerFirstName;
    }

    public void setSpeakerFirstName(String speakerFirstName) {
        this.speakerFirstName = speakerFirstName;
    }

    public String getSpeakerLastName() {
        return speakerLastName;
    }

    public void setSpeakerLastName(String speakerLastName) {
        this.speakerLastName = speakerLastName;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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
