package app.persistences.dao;

import app.entities.Conference;
import app.entities.Role;
import app.persistences.ConnectionDB;
import app.persistences.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConferenceDAO {

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


    private static final Logger logger = Logger.getLogger(ConferenceDAO.class);

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

        List<Conference> conferences = new LinkedList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resSet = null;

        String query = "SELECT * FROM conferences WHERE speaker_id = ?";

        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resSet = preparedStatement.executeQuery();

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
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conferences;
    }

    //delete conference by id

    public void deleteById(int id) {

        String sql = "DELETE FROM conferences WHERE conference_id = '"+id+"'";
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute(sql);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //set agreement by admin || speaker || moder about conference

    public void setAgreement(Role role, int id, Boolean agreement){

        String agreementColumn = null;

        if(role == Role.SPEAKER){
            agreementColumn = "is_accepted_speaker";
        }else if(role == Role.SPEAKER || role == Role.MODER){
            agreementColumn = "is_accepted_moder";
        }

        String sql = "UPDATE conferences SET " +
                agreementColumn + " = " + agreement + " WHERE conference_id = '" + id + "'";
        Connection connection = null;
        Statement statement = null;

        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute(sql);
            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //returns conferences that will happen

    public List<Conference> getConfBeforeDateTime(java.sql.Date currentDate, String currentTime){

        List<Conference> conferences = new LinkedList<>();
        QueryExecutor executor = new QueryExecutor();
        Object[] arguments = {currentDate,currentTime,currentDate};

        try {

            ResultSet resSet = executor.getResultSet(GET_CONF_BEFORE_DAY_TIME,arguments);
            conferences = getConferencesFromResSet(resSet,2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        return isRegistered;
    }

    public void registerInConf(int userId, int conferenceId){

//        logger.info("USER ID AND CONFERENCE ID:" +userId + " " +conferenceId);

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO registered_in_conference (user_id, conference_id) " +
                "VALUES (?,?);";

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,conferenceId);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
//            logger.warn("connection wasn't closed!! Error in SignUpModel.createUser()");
                e.printStackTrace();
            }
        }
    }

    //returns conferences ids that user with userId is registered in

    public ArrayList<Integer> getRegisteredConfId(int userId) {

        ArrayList<Integer> confId = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet= null;
        Boolean isRegistered = false;

        String sql = "SELECT * FROM registered_in_conference " +
                "WHERE user_id = ?";

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);

            resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                confId.add(resultSet.getInt("conference_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return confId;
    }
    public List<Conference> getConfUserWasPresentIn(int userId, String currentDate, String currentTime){

        QueryExecutor executor = new QueryExecutor();
        List<Conference> conferences = null;
        Object[] arguments = {currentDate,currentTime,currentDate,userId};

        try {
            ResultSet resSet = executor.getResultSet(GET_CONFERENCES_USER_GOT_PART_IN,arguments);
            conferences = getConferencesFromResSet(resSet,1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                default:return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}



