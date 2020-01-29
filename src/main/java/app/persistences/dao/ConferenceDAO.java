package app.persistences.dao;

import app.entities.Conference;
import app.persistences.ConnectionDB;
import app.persistences.ConnectionPool;

import java.sql.*;

public class ConferenceDAO {

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
                "," + conference.getSpeaker_id() + ")";

        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.execute(sql);
            connection.commit();

        } catch (SQLException e) {
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

    public static void main(String[] args)  {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String confName = "ConfName";
        String dateStr = "2020-01-01";
        String beginsAt = "12:20:00";
        String endsAt = "13:40:00";
        String location = "location";
        Boolean is_accepted_moder = true;
        Boolean is_accepted_speaker = false;
        int speaker_id = 1;

        String sql = "INSERT INTO cms_db.conferences (conference_name, date, begins_at, ends_at, " +
                "location, is_accepted_moder, is_accepted_speaker, speaker_id) " +

                "VALUES ('" + confName +
                "',{d'"+ dateStr +
                "'},{t'"+ beginsAt +
                "'},{t'"+ endsAt +
                "'},'" + location +
                "'," + is_accepted_moder +
                "," + is_accepted_speaker+
                "," + speaker_id+ ")";

        try {
            connection = ConnectionDB.getConnection();
            Statement statement = connection.createStatement();
            statement.execute(sql);

//            String query = "SELECT * FROM cms_db.conferences";
//            ResultSet resultSet = statement.executeQuery(query);
//            while (resultSet.next()){
//                System.out.println(resultSet.getDate("date"));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
