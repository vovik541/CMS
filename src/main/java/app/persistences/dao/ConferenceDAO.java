package app.persistences.dao;

import app.entities.Conference;
import app.persistences.ConnectionDB;
import app.persistences.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ConferenceDAO {

    private static final Logger logger = Logger.getLogger(ConferenceDAO.class);

    public void addConference(Conference conference, Boolean accepted_by_moder, Boolean accepted_by_speaker){

        Connection connection = null;
        Statement statement = null;

        conference.setAcceptedByModer(accepted_by_moder);
        conference.setAcceptedBySpeaker(accepted_by_speaker);

        String sql = "INSERT INTO cms_db.conferences (conference_name, date, begins_at, ends_at, " +
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

        String query = "SELECT * FROM cms_db.conferences WHERE speaker_id = ?";

        try {
            connection = ConnectionDB.getConnection();
            preparedStatement = connection.prepareStatement(query);
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

    public static void main(String[] args)  {

    }
}
