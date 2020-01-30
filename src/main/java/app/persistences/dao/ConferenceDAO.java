package app.persistences.dao;

import app.entities.Conference;
import app.entities.Role;
import app.persistences.ConnectionDB;
import app.persistences.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class ConferenceDAO {

    private static final Logger logger = Logger.getLogger(ConferenceDAO.class);

    public void addConference(Conference conference, Boolean accepted_by_moder, Boolean accepted_by_speaker){

        Connection connection = null;
        Statement statement = null;

        conference.setAcceptedByModer(accepted_by_moder);
        conference.setAcceptedBySpeaker(accepted_by_speaker);

        String sql = "INSERT INTO conferences (conference_name, date, begins_at, ends_at, " +
                "location, is_accepted_moder, is_accepted_speaker, speaker_id) " +

                "VALUES ('" + conference.getConfName() +
                "',{d'"+ conference.getDate() +
                "'},{t'"+ conference.getBeginsAt() +
                "'},{t'"+ conference.getEndsAt() +
                "'},'" + conference.getLocation() +
                "'," + conference.getAcceptedByModer() +
                "," + conference.getAcceptedBySpeaker()+
                "," + conference.getSpeakerId() + ")";

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
        }finally {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
//            logger.warn("connection wasn't closed!! Error in SignUpModel.createUser()");
            e.printStackTrace();
        }
    }
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
                conferences.add(new Conference(resSet.getInt("conference_id"),
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

    public List<Conference> getConfBeforeDateTime(String currentDate, String currentTime){


        List<Conference> conferences = new LinkedList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resSet = null;

        String query = "select conferences.conference_id, conferences.conference_name, conferences.date,\n" +
                "conferences.begins_at, conferences.ends_at, conferences.speaker_id, conferences.is_accepted_moder,\n" +
                "conferences.is_accepted_speaker, conferences.location,\n" +
                "customers.first_name, customers.last_name\n" +
                "FROM conferences\n" +
                "INNER JOIN customers on conferences.speaker_id = customers.customer_id\n" +
                "WHERE ((date > '" + currentDate + "') OR " +
                "(ends_at > '" + currentDate + "' AND date = '" + currentDate + "') " +
                "AND is_accepted_speaker = '1' AND is_accepted_moder = '1');";
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.createStatement();
            resSet = statement.executeQuery(query);

            while (resSet.next()){
                conferences.add(new Conference(resSet.getInt("conference_id"),
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
                System.out.println(conferences.get(0).toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                resSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return conferences;
    }

    public static void main(String[] args) throws SQLException {



//        System.out.println(time);
    }


}
