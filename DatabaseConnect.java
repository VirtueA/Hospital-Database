import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.Date;

public class DatabaseConnect {

    public static void main(String[] args) throws SQLException {

        //connecting to local database
        String url = "jdbc:mysql://localhost:3306/hospitaldb";
        Connection con = DriverManager.getConnection(url, "root", "admin");

        //sample insert and delete patient queries
        //insertPatient(con, "John Doe", 1234567890, 100000000, "11/12/2022");
        //deletePatient(con, 1234567890);

        /* example on how to process result set data
        iterating through result set
        all i know is that this is how i'll get the data. I can also return the result set into main if we need it
        ResultSet result = retrieveDoctorsExpereience(con, 5);
        while(results.next()) {
            String doctorID = results.getString("doctorID");
            String FullName = results.getString("FullName");
            String DeptName = results.getString("DeptName");

            //printing results
            System.out.println(doctorID + "|" + FullName + "|" + DeptName);
        }
         */
        //retrieveDoctorsExpereience(con, 5);

        //GenderDocs(con, "F");

        //numBloodTypes(con);

        NursesPatients(con);

        con.close();
    }

    //function to insert a patient
    //date needs to be in form MM/DD/YYYY
    private static void insertPatient(Connection con, String name, String SSN, String ID, String date) throws SQLException {
        Date patientDOB = new Date(date);
        java.sql.Date sqlDate = new java.sql.Date(patientDOB.getTime());
        PreparedStatement ptInsert = con.prepareStatement("INSERT INTO PATIENTS VALUES (?, ?, ?, ?)");
        ptInsert.setString(1, SSN);
        ptInsert.setString(2, ID);
        ptInsert.setString(3, name);
        ptInsert.setDate(4, sqlDate);
        ptInsert.execute();
    }

    //function to delete a patient
    //deletes patient by finding the patient's ssn
    private static void deletePatient(Connection con, String SSN) throws SQLException {
        PreparedStatement ptDelete = con.prepareStatement("DELETE FROM PATIENTS WHERE SSN = ?");
        ptDelete.setString(1, SSN);
        ptDelete.execute();
    }

    private static void updateDoctorPhone(Connection con, String docID, String newPhoneNum) throws SQLException {
        PreparedStatement updateDoctorPhone = con.prepareStatement("UPDATE DOCTORS SET PhoneNum = ? where doctorID = ? ");
        updateDoctorPhone.setString(1, newPhoneNum);
        updateDoctorPhone.setString(2, docID);
        updateDoctorPhone.execute();
    }

    //return all the doctors with a given amount of years of experience
    private static ResultSet retrieveDoctorsExpereience(Connection con, int yearsExperience) throws SQLException {
        PreparedStatement retrieveDocs = con.prepareStatement("Select DoctorID, FullName, DeptName FROM doctors Where doctors.YrsExperience >= ?");
        retrieveDocs.setInt(1, yearsExperience);
        //getting the result set
        ResultSet results = retrieveDocs.executeQuery();
        //returning the result set
        return results;

    }

    private static ResultSet GenderDocs(Connection con, String gender) throws SQLException {
        PreparedStatement retrieveMaleDocs = con.prepareStatement("SELECT doctorID, FullName FROM Doctors Where gender = ?");
        retrieveMaleDocs.setString(1, gender);
        ResultSet result = retrieveMaleDocs.executeQuery();
        
        //you can remove this while loop i was just using it for testing
        //this while loop is how you'll have to process the data
        while(result.next()) {
            String doctorID = result.getString("doctorID");
            String FullName = result.getString("FullName");

            //printing results
            System.out.println(doctorID + "|" + FullName);
        }
        
        
        return result;
    }

    //counts the number of patients with certain bloodtypes
    private static ResultSet numBloodTypes(Connection con) throws SQLException {
        PreparedStatement bloodTypes = con.prepareStatement("SELECT BloodType, COUNT(*) FROM MedicalRecord GROUP BY BloodType");
        ResultSet results = bloodTypes.executeQuery();
        
        //you can remove this while loop i was just using it for testing
        //this while loop is how you'll have to process the data
        while(results.next()) {
            String bloodType = results.getString("BloodType");
            int count = results.getInt("COUNT(*)");

            System.out.println(bloodType + " | " + count);
        }
        
        return results;
    }

    //shows the name, department, patient name, and patient diagnosis for every nurse in the hospital
    private static ResultSet NursesPatients(Connection con) throws SQLException {
        PreparedStatement nursePatients = con.prepareStatement("SELECT N.FullName, N.DeptName, p.PatientName, M.CurrentDiagnosis FROM nurses as N INNER JOIN Patients AS p ON N.PatientID = p.PatientID INNER JOIN MedicalRecord AS M ON P.PatientID = M.PatientID");
        ResultSet results = nursePatients.executeQuery();
        System.out.println("Nurse Name | Department | Patient Name | Patient Diagnosis");
        
        
        //you can remove this while loop i was just using it for testing
        //this while loop is how you'll have to process the data
        while(results.next()) {
            String nurse = results.getString("N.FullName");
            String department = results.getString("N.DeptName");
            String patientName = results.getString("p.PatientName");
            String patientDiagnosis = results.getString("M.CurrentDiagnosis");

            System.out.println(nurse + " | " + department + " | " + patientName + " | " + patientDiagnosis);
        }
        return results;
    }



}
