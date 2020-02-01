package app.persistences.dao;

import app.entities.Conference;
import app.entities.Role;
import app.persistences.ConnectionDB;
import app.persistences.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConferenceDAO {

    private static final Logger logger = Logger.getLogger(ConferenceDAO.class);

    private static final String sql = "select conferences.conference_id, conferences.conference_name, conferences.date,\n" +
            "conferences.begins_at, conferences.ends_at, conferences.speaker_id, conferences.location, rate,\n" +
            "customers.first_name, customers.last_name\n" +
            "FROM conferences\n" +
            "INNER JOIN customers on conferences.speaker_id = customers.customer_id\n" +
            "INNER JOIN registered_in_conference on conferences.conference_id = registered_in_conference.conference_id\n" +
            "WHERE ((date < {d?}) OR \n" +
            "(ends_at < {t?} AND date = {d?}))" +
            "AND registered_in_conference.is_present = '1' AND user_id = ?;";
    private static final String GET_CONF_BEFORE_DAY_TIME = "select conferences.conference_id, conferences.conference_name, conferences.date,\n" +
            "conferences.begins_at, conferences.ends_at, conferences.speaker_id, conferences.is_accepted_moder,\n" +
            "conferences.is_accepted_speaker, conferences.location,\n" +
            "customers.first_name, customers.last_name\n" +
            "FROM conferences\n" +
            "INNER JOIN customers on conferences.speaker_id = customers.customer_id\n" +
            "WHERE ((date > {d?}) OR " +
            "(ends_at > {t?} AND date = {d?}) " +
            "AND is_accepted_speaker = '1' AND is_accepted_moder = '1');";
    private static final String GET_CONFERENCES_USER_GOT_PART_IN = "select conferences.conference_id, conferences.conference_name, conferences.date,\n" +
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
    private static final String DELETE_CONFERENCE_BY_ID = "DELETE FROM conferences WHERE conference_id = ?";
    private static final String SET_AGREEMENT = "UPDATE conferences SET ? = ? WHERE conference_id = ?";
    private static final String REGISTER_IN_CONFERENCE = "INSERT INTO registered_in_conference " +
            "(user_id, conference_id) VALUES (?,?);";
    private static final String GET_CONF_ID_USER_IS_REG_IN = "SELECT * FROM registered_in_conference " +
            "WHERE user_id = ?";

    public void addConference(Conference conference, Boolean accepted_by_moder,
                              Boolean accepted_by_speaker){

        Object[] arguments = {conference.getConfName(),conference.getDate(),
                conference.getBeginsAt(),conference.getEndsAt(),conference.getLocation(),
                accepted_by_moder,accepted_by_speaker,
                conference.getSpeakerId()};

        QueryExecutor executor = new QueryExecutor();
        executor.executeStatement(ADD_CONFERENCE,arguments);
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

    public void deleteById(int id) {

        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {id};

        executor.executeStatement(DELETE_CONFERENCE_BY_ID,arguments);
        executor.close();
    }

    //set agreement by admin || speaker || moder about conference

    public void setAgreement(Role role, int id, Boolean agreement){

        String agreementColumn = null;
        QueryExecutor executor = new QueryExecutor();

        if(role == Role.SPEAKER){
            agreementColumn = "is_accepted_speaker";
        }else if(role == Role.SPEAKER || role == Role.MODER){
            agreementColumn = "is_accepted_moder";
        }

        Object[] arguments = {agreementColumn, agreement, id};

        executor.executeStatement(SET_AGREEMENT, arguments);
        executor.close();
    }

    //returns conferences that will happen

    public List<Conference> getConfBeforeDateTime(java.sql.Date currentDate, String currentTime){

        List<Conference> conferences = new LinkedList<>();
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

        ArrayList<Integer> confId = null;

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
    public List<Conference> getConfUserWasPresentIn(int userId, String currentDate, String currentTime){

        QueryExecutor executor = new QueryExecutor();
        List<Conference> conferences = null;
        Object[] arguments = {currentDate,currentTime,currentDate,userId};

        ResultSet resSet = executor.getResultSet(GET_CONFERENCES_USER_GOT_PART_IN,arguments);
        conferences = getConferencesFromResSet(resSet,1);
        executor.close();

        return conferences;
    }
    private List<Conference> getConferencesFromResSet(ResultSet resSet, int initType){

        List<Conference> conferences = new LinkedList<>();

        try {
            switch (initType){
                case 1:
                    while (resSet.next()){
                        conferences.add(new Conference(
                                resSet.getInt("conference_id"),
                                resSet.getInt("speaker_id"),
                                resSet.getString("conference_name"),
                                resSet.getDate("date").toString(),
                                resSet.getTime("begins_at").toString(),
                                resSet.getTime("ends_at").toString(),
                                resSet.getString("location"),
                                resSet.getString("first_name"),
                                resSet.getString("last_Name"),
                                resSet.getInt("rate")
                        ));
                    }return conferences;
                case 2:
                    while (resSet.next()){
                        conferences.add(new Conference(
                                resSet.getInt("conference_id"),
                                resSet.getInt("speaker_id"),
                                resSet.getString("conference_name"),
                                resSet.getDate("date").toString(),
                                resSet.getTime("begins_at").toString(),
                                resSet.getTime("ends_at").toString(),
                                resSet.getString("location"),
                                resSet.getBoolean("is_accepted_moder"),
                                resSet.getBoolean("is_accepted_speaker"),
                                resSet.getString("first_name"),
                                resSet.getString("last_Name")
                        ));
                    }return conferences;
                case 3:
                    while (resSet.next()){
                    conferences.add(new Conference(
                            resSet.getInt("conference_id"),
                            resSet.getInt("speaker_id"),
                            resSet.getString("conference_name"),
                            resSet.getDate("date").toString(),
                            resSet.getTime("begins_at").toString(),
                            resSet.getTime("ends_at").toString(),
                            resSet.getString("location"),
                            resSet.getBoolean("is_accepted_moder"),
                            resSet.getBoolean("is_accepted_speaker")));
                    }return conferences;
                default:return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}