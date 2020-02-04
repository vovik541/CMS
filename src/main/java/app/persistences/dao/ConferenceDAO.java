package app.persistences.dao;

import app.entities.Conference;
import app.entities.ConferenceInfo;
import app.entities.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConferenceDAO {

    private static final Logger logger = Logger.getLogger(ConferenceDAO.class);

    private static final String GET_CONF_AFTER_DAY_TIME = "SELECT conferences.conference_id, conferences.conference_name, conferences.date,\n" +
            "conferences.begins_at, conferences.ends_at, conferences.speaker_id, conferences.is_accepted_moder,\n" +
            "conferences.is_accepted_speaker, conferences.location,\n" +
            "customers.first_name, customers.last_name\n" +
            "FROM conferences\n" +
            "INNER JOIN customers ON conferences.speaker_id = customers.customer_id\n" +
            "WHERE (((date > {d?}) OR " +
            "(ends_at > {t?} AND date = {d?})) " +
            "AND is_accepted_speaker = '1' AND is_accepted_moder = '1');";
    private static final String GET_CURRENT_CONFERENCES = "SELECT conferences.conference_id, conferences.conference_name, conferences.date,\n" +
            "conferences.begins_at, conferences.ends_at, conferences.speaker_id, conferences.is_accepted_moder,\n" +
            "conferences.is_accepted_speaker, conferences.location,\n" +
            "customers.first_name, customers.last_name\n" +
            "FROM conferences\n" +
            "INNER JOIN customers ON conferences.speaker_id = customers.customer_id\n" +
            "WHERE (date > {d?}) OR " +
            "(ends_at > {t?} AND date = {d?});";
    private static final String GET_CONF_BEFORE_DAY_TIME = "SELECT conferences.conference_id, conferences.conference_name, conferences.date,\n" +
            "conferences.begins_at, conferences.ends_at, conferences.speaker_id, conferences.is_accepted_moder,\n" +
            "conferences.is_accepted_speaker, conferences.location,\n" +
            "customers.first_name, customers.last_name\n" +
            "FROM conferences\n" +
            "INNER JOIN customers ON conferences.speaker_id = customers.customer_id\n" +
            "WHERE (date < {d?}) OR " +
            "(ends_at < {t?} AND date = {d?});";
    private static final String GET_CONFERENCES_USER_GOT_PART_IN = "SELECT conferences.conference_id, conferences.conference_name, conferences.date,\n" +
            "conferences.begins_at, conferences.ends_at, conferences.speaker_id, conferences.location, rate,\n" +
            "customers.first_name, customers.last_name\n" +
            "FROM conferences\n" +
            "INNER JOIN customers on conferences.speaker_id = customers.customer_id\n" +
            "INNER JOIN registered_in_conference on conferences.conference_id = registered_in_conference.conference_id\n" +
            "WHERE ((date < {d?}) OR \n" +
            "(ends_at < {t?} AND date = {d?}))" +
            "AND registered_in_conference.is_present = '1' AND user_id = ?;";
    private static final String USER_IS_REGISTERED_IN_CONF = "SELECT * FROM registered_in_conference " +
            "WHERE user_id = ? AND conference_id = ?";
    private static final String ADD_CONFERENCE = "INSERT INTO conferences (conference_name, date, begins_at, ends_at, " +
            "location, is_accepted_moder, is_accepted_speaker, speaker_id) " +
            "VALUES (?,{d?},{t?},{t?},?,?,?,?)";
    private static final String GET_CONFERENCE_BY_SPEAKER_ID = "SELECT * FROM conferences WHERE speaker_id = ?";
    private static final String DELETE_REGISTERED_IN_CONFERENCE = "DELETE FROM registered_in_conference WHERE conference_id = ?";
    private static final String DELETE_CONFERENCE_BY_ID = "DELETE FROM conferences WHERE conference_id = ?";
    private static final String SET_AGREEMENT_SPEAKER = "UPDATE conferences SET is_accepted_speaker = ? WHERE conference_id = ?";
    private static final String SET_AGREEMENT_MODER = "UPDATE conferences SET is_accepted_moder = ? WHERE conference_id = ?";
    private static final String REGISTER_IN_CONFERENCE = "INSERT INTO registered_in_conference " +
            "(user_id, conference_id) VALUES (?,?);";
    private static final String GET_CONF_ID_USER_IS_REG_IN = "SELECT * FROM registered_in_conference " +
            "WHERE user_id = ?";
    private static final String GIVE_RATE = "UPDATE registered_in_conference  SET rate = ? WHERE conference_id = ? AND user_id = ?;";
    private static final String GET_SPEAKER_RATE = "SELECT rate FROM registered_in_conference \n" +
            "INNER JOIN conferences ON registered_in_conference.conference_id = conferences.conference_id\n" +
            "WHERE speaker_id = ? AND rate != '0';";
    private static final String GET_MORE_CONFERENCE_INFO = "SELECT user_id, is_present FROM registered_in_conference " +
            "WHERE conference_id = ?;";
    private static final String CHANGE_SPEAKER = "UPDATE conferences SET speaker_id = ? WHERE conference_id = ?;";
    private static final String CHANGE_CONFERENCE_NAME = "UPDATE conferences SET conference_name = ? WHERE conference_id = ?";
    private static final String CHANGE_LOCATION = "UPDATE conferences SET location = ? WHERE conference_id = ?";
    private static final String CHANGE_DATE_TIME = "UPDATE conferences SET date = ?, begins_at = ?, ends_at = ?  WHERE conference_id = ?;";
    private static final String GET_EMAILS_BY_CONF = "SELECT email FROM customers INNER JOIN registered_in_conference ON customers.customer_id = registered_in_conference.user_id\n" +
            "WHERE registered_in_conference.conference_id = ?;";

    public List<String> getEmailsByConf(int conferenceId){

        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {conferenceId};
        List<String> emails = new LinkedList<>();

        ResultSet resultSet = executor.getResultSet(GET_EMAILS_BY_CONF, arguments);

        try {
            while (resultSet.next()){
                emails.add(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        executor.close();

        return emails;
    }

    public void changeSpeaker(int speakerId, int conferenceId){
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {speakerId, conferenceId};

        executor.executeStatement(CHANGE_SPEAKER, arguments);

        executor.close();
    }

    public void changeLocation(String location, int conferenceId){
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {location, conferenceId};

        executor.executeStatement(CHANGE_LOCATION, arguments);

        executor.close();
    }
    public void changeConferenceName(String conferenceName, int conferenceId){
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {conferenceName, conferenceId};

        executor.executeStatement(CHANGE_CONFERENCE_NAME, arguments);

        executor.close();
    }

    public void changeDateTime(int conferenceId, String date,
                               String beginsAtTime, String endsAtTime){

        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {date, beginsAtTime, endsAtTime, conferenceId};

        executor.executeStatement(CHANGE_DATE_TIME, arguments);

        executor.close();
    }

    public void addConference(Conference conference){

        Object[] arguments = {conference.getConfName(),conference.getDate(),
                conference.getBeginsAt(),conference.getEndsAt(),conference.getLocation(),
                conference.getAcceptedByModer(),conference.getAcceptedBySpeaker(),
                conference.getSpeakerId()};

        QueryExecutor executor = new QueryExecutor();
        executor.executeStatement(ADD_CONFERENCE,arguments);
        executor.close();
    }

    public List<Conference> getConfBySpeakerId(int id){

        List<Conference> conferences = null;
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {id};

        ResultSet resultSet = executor.getResultSet(GET_CONFERENCE_BY_SPEAKER_ID, arguments);

        conferences = getConferencesFromResSet(resultSet,3);
        executor.close();

        return conferences;
    }

    //delete conference by id

    public void deleteRegisteredInConf(int conferenceId){
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {conferenceId};

        executor.executeStatement(DELETE_REGISTERED_IN_CONFERENCE,arguments);
        executor.close();
    }

    public void deleteById(int id) {

        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {id};

        executor.executeStatement(DELETE_CONFERENCE_BY_ID,arguments);
        executor.close();
    }

    //set agreement by admin || speaker || moder about conference

    public void setAgreement(Role role, int id, Boolean agreement){

        QueryExecutor executor = new QueryExecutor();

        if(role == Role.SPEAKER){

            Object[] arguments = {agreement, id};
            executor.executeStatement(SET_AGREEMENT_SPEAKER, arguments);
            executor.close();

        }else if(role == Role.ADMIN || role == Role.MODER){

            Object[] arguments = {agreement, id};
            executor.executeStatement(SET_AGREEMENT_MODER, arguments);
            executor.close();

        }
    }

    public List<Conference> getCurrentConferences(java.sql.Date currentDate, String currentTime){

        List<Conference> conferences;
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {currentDate,currentTime,currentDate};

        ResultSet resSet = executor.getResultSet(GET_CURRENT_CONFERENCES,arguments);
        conferences = getConferencesFromResSet(resSet,2);

        return conferences;
    }

    //returns conferences that will happen

    public List<Conference> getConfAfterDateTime(java.sql.Date currentDate, String currentTime){

        List<Conference> conferences;
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {currentDate,currentTime,currentDate};

        ResultSet resSet = executor.getResultSet(GET_CONF_AFTER_DAY_TIME,arguments);
        conferences = getConferencesFromResSet(resSet,2);

        return conferences;
    }

    public List<Conference> getConfBeforeDateTime(java.sql.Date currentDate, String currentTime){

        List<Conference> conferences;
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {currentDate,currentTime,currentDate};

        ResultSet resSet = executor.getResultSet(GET_CONF_BEFORE_DAY_TIME,arguments);
        conferences = getConferencesFromResSet(resSet,2);

        return conferences;
    }

    //returns true if user is registered in current conference

    public Boolean isRegisteredInConf(int userId, int conferenceId){

        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {userId, conferenceId};
        ResultSet resultSet;
        Boolean isRegistered = false;
        try {
            resultSet = executor.getResultSet(USER_IS_REGISTERED_IN_CONF, arguments);
            if(resultSet.next()){
                isRegistered = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        executor.close();
        return isRegistered;
    }

    public void registerInConf(int userId, int conferenceId){
        Object[] arguments = {userId, conferenceId};
        QueryExecutor executor = new QueryExecutor();
        executor.executeStatement(REGISTER_IN_CONFERENCE, arguments);
        executor.close();
    }

    //returns conferences ids that user with userId is registered in

    public ArrayList<Integer> getRegisteredConfId(int userId) {

        ArrayList<Integer> confId = new ArrayList<>();

        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {userId};

        ResultSet resultSet = executor.getResultSet(GET_CONF_ID_USER_IS_REG_IN, arguments);

        try {
            while (resultSet.next()){
                confId.add(resultSet.getInt("conference_id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return confId;
    }
    public List<Conference> getConfUserWasPresentIn(int userId, java.sql.Date currentDate, String currentTime){

        QueryExecutor executor = new QueryExecutor();
        List<Conference> conferences;
        Object[] arguments = {currentDate,currentTime,currentDate,userId};

        ResultSet resSet = executor.getResultSet(GET_CONFERENCES_USER_GOT_PART_IN,arguments);
        conferences = getConferencesFromResSet(resSet,1);
        executor.close();

        return conferences;
    }

    public void setRate(int rate, int conferenceId, int userId){

        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {rate, conferenceId, userId};
        executor.executeStatement(GIVE_RATE, arguments);
        executor.close();

    }

    public List<Integer> getSpeakerRates(int speaker_id){
        List<Integer> speakerRates = new LinkedList<>();

        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {speaker_id};

        ResultSet resultSet = executor.getResultSet(GET_SPEAKER_RATE, arguments);
        try {
            while (resultSet.next()){
                speakerRates.add(resultSet.getInt("rate"));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return speakerRates;
    }

    //  how many users were registered and present

    public ConferenceInfo getMoreConfInfo(int conferenceId){
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {conferenceId};

        ResultSet resultSet = executor.getResultSet(GET_MORE_CONFERENCE_INFO, arguments);


        int registered = 0;
        int presented = 0;

        try {

            while (resultSet.next()){
                if(resultSet.getInt("is_present") != 0){
                    presented++;
                }
            }

            if (resultSet.last() ) {
                registered = resultSet.getRow();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        executor.close();

        ConferenceInfo conferenceInfo = new ConferenceInfo(
                conferenceId, registered, presented);

        return conferenceInfo;
    }

    private List<Conference> getConferencesFromResSet(ResultSet resSet, int initType){

        List<Conference> conferences = new LinkedList<>();

        String confName;
        String date;
        String beginsAtTime;
        String endsAtTime;
        String location;
        String speakerFirstName;
        String speakerLastName;
        Boolean acceptedByModer = false;
        Boolean acceptedBySpeaker = true;
        int speakerId;
        int conferenceId;
        int rate;

        try {
            switch (initType){
                case 1:
                    while (resSet.next()){

                        conferenceId = resSet.getInt("conference_id");
                        speakerId = resSet.getInt("speaker_id");
                        confName = resSet.getString("conference_name");
                        date = resSet.getDate("date").toString();
                        beginsAtTime = resSet.getTime("begins_at").toString();
                        endsAtTime = resSet.getTime("ends_at").toString();
                        location = resSet.getString("location");
                        speakerFirstName = resSet.getString("first_name");
                        speakerLastName = resSet.getString("last_Name");
                        rate = resSet.getInt("rate");

                        conferences.add(
                                new Conference.Builder(speakerId, confName, location,
                                        date, beginsAtTime, endsAtTime)
                                        .setConferenceId(conferenceId)
                                        .setSpeakerFirstName(speakerFirstName)
                                        .setSpeakerLastName(speakerLastName)
                                        .setRate(rate)
                                        .build()
                        );

                    }
                    return conferences;
                case 2:
                    while (resSet.next()){

                        conferenceId = resSet.getInt("conference_id");
                        speakerId = resSet.getInt("speaker_id");
                        confName = resSet.getString("conference_name");
                        date = resSet.getDate("date").toString();
                        beginsAtTime = resSet.getTime("begins_at").toString();
                        endsAtTime = resSet.getTime("ends_at").toString();
                        location = resSet.getString("location");
                        speakerFirstName = resSet.getString("first_name");
                        speakerLastName = resSet.getString("last_Name");
                        acceptedByModer = resSet.getBoolean("is_accepted_moder");
                        acceptedBySpeaker = resSet.getBoolean("is_accepted_speaker");

                        conferences.add(
                                new Conference.Builder(speakerId, confName, location,
                                        date, beginsAtTime, endsAtTime)
                                        .setConferenceId(conferenceId)
                                        .setSpeakerFirstName(speakerFirstName)
                                        .setSpeakerLastName(speakerLastName)
                                        .setAcceptedBySpeaker(acceptedBySpeaker)
                                        .setAcceptedByModer(acceptedByModer)
                                        .build()
                        );
                    }
                    return conferences;
                case 3:
                    while (resSet.next()){
                        conferenceId = resSet.getInt("conference_id");
                        speakerId = resSet.getInt("speaker_id");
                        confName = resSet.getString("conference_name");
                        date = resSet.getDate("date").toString();
                        beginsAtTime = resSet.getTime("begins_at").toString();
                        endsAtTime = resSet.getTime("ends_at").toString();
                        location = resSet.getString("location");
                        acceptedByModer = resSet.getBoolean("is_accepted_moder");
                        acceptedBySpeaker = resSet.getBoolean("is_accepted_speaker");

                        conferences.add(new Conference.Builder(speakerId, confName, location,
                                date, beginsAtTime, endsAtTime)
                                .setConferenceId(conferenceId)
                                .setAcceptedBySpeaker(acceptedBySpeaker)
                                .setAcceptedByModer(acceptedByModer)
                                .build());
                    }
                    return conferences;
                default:return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}