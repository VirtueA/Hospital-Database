import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.Date;

public class DatabaseConnect {
    //public static void main(String[] args) throws SQLException, IOException {

        //connecting to local database
        //String url = "jdbc:mysql://localhost:3306/hospitaldb";
        //Connection con = DriverManager.getConnection(url, "root", "admin");

        //DB_gui DBProject = new DB_gui();//DB_gui object to run the gui

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

        //NursesPatients(con);

        //doctorsWorking(con, "2022/11/14");

        //docSpecialization(con, "pulmonology");

        //con.close();
   // }

    //function to insert a patient
    //date needs to be in form MM/DD/YYYY
    //NEED THIS
    public static void insertPatient(Connection con, String name, String SSN, String ID, String date) throws SQLException {
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
    //NEED THIS
    public static void deletePatient(Connection con, String SSN) throws SQLException {
        PreparedStatement ptDelete = con.prepareStatement("DELETE FROM PATIENTS WHERE SSN = ?");
        ptDelete.setString(1, SSN);
        ptDelete.execute();
    }

    //finds patient using their patientID
    //NEED THIS
    public static String findPatient(Connection con, String patientID) throws SQLException {
        PreparedStatement findPt = con.prepareStatement("SELECT PatientID, SSN, PatientName, DOB FROM patients where PatientID = ?");
        findPt.setString(1, patientID);
        ResultSet results = findPt.executeQuery();
        String result = "Patient ID | SSN | Patient Name | DOB \n";
        if(!results.isBeforeFirst()) {
            return "Error: No matching data found";
        }
        else {
            while(results.next()) {
                String ptID = results.getString("PatientID");
                String ssn = results.getString("SSN");
                String ptName = results.getString("PatientName");
                java.sql.Time dob = results.getTime("DOB");

                result += ptID + " | " + ssn + " | " + ptName + " | " + dob + " \n";
            }
            return result;
        }
    }

    //counts the number of patients with certain bloodtypes
    //NEED THIS
    public static String numBloodTypes(Connection con) throws SQLException {
        PreparedStatement bloodTypes = con.prepareStatement("SELECT BloodType, COUNT(*) FROM MedicalRecord GROUP BY BloodType");
        ResultSet results = bloodTypes.executeQuery();
        String result = "Blood Type | Count\n";
        if(!results.isBeforeFirst()) {
            return "Error: No data found";
        }
        else {
            while (results.next()) {
                String bloodType = results.getString("BloodType");
                int count = results.getInt("COUNT(*)");
                result += bloodType + " | " + count;
            }
        }
        return result;
    }

    //shows the name, department, patient name, and patient diagnosis for every nurse in the hospital
    //NEED THIS
    public static String NursesPatients(Connection con) throws SQLException {
        PreparedStatement nursePatients = con.prepareStatement("SELECT N.FullName, N.DeptName, p.PatientName, M.CurrentDiagnosis FROM nurses as N INNER JOIN Patients AS p ON N.PatientID = p.PatientID INNER JOIN MedicalRecord AS M ON P.PatientID = M.PatientID");
        ResultSet results = nursePatients.executeQuery();
        String result = "Nurse Name | Department | Patient Name | Patient Diagnosis\n";
        if(!results.isBeforeFirst()) {
            return "Error: No matching data";
        }
        else {
            while (results.next()) {
                String nurse = results.getString("N.FullName");
                String department = results.getString("N.DeptName");
                String patientName = results.getString("p.PatientName");
                String patientDiagnosis = results.getString("M.CurrentDiagnosis");

                result += nurse + " | " + department + " | " + patientName + " | " + patientDiagnosis + " \n";
            }
            return result;
        }

    }

    //retrieve names and doctorids of doctors working on a given day
    //NEED THIS
    public static String doctorsWorking(Connection con, String date) throws SQLException {
        Date patientDOB = new Date(date);
        java.sql.Date sqlDate = new java.sql.Date(patientDOB.getTime());
        PreparedStatement docsWorking = con.prepareStatement("SELECT Schedule.StartTime, Doctors.DoctorID, Doctors.FullName FROM (Doctors JOIN Schedule ON Doctors.DoctorID = Schedule.DoctorID) WHERE Schedule.Date = ?" +
                " GROUP BY Schedule.StartTime, Doctors.DoctorID, Doctors.FullName");
        docsWorking.setDate(1, sqlDate);
        ResultSet results = docsWorking.executeQuery();
        String result = "Start Time | DoctorID | Full Name\n";

        if(!results.isBeforeFirst()) {
            return "Error: No matching data";
        }
        else {
            while(results.next()) {
                java.sql.Time startTime = results.getTime("Schedule.StartTime");
                String doctorID = results.getString("Doctors.DoctorID");
                String fullName = results.getString("Doctors.FullName");

                result += startTime + " | " + doctorID + " | " + fullName + " \n";
            }
        }
        return result;
    }

    //retrieve doctors with a certain specialization, grouped by years in specialization
    //NEED THIS
    public static String docSpecialization(Connection con, String specialization) throws SQLException {
        PreparedStatement getDocSpecialization = con.prepareStatement("Select Doctors.YrsExperience, Doctors.DoctorID, Doctors.FullName, COUNT(*) FROM (Doctors JOIN Specialization ON Doctors.DoctorID = Specialization.DoctorID) " +
                "WHERE Specialization.Specialization = ? GROUP BY Doctors.YrsExperience, Doctors.DoctorID, Doctors.FullName");
        getDocSpecialization.setString(1, specialization);
        ResultSet results = getDocSpecialization.executeQuery();

        String result = "Years Experience | Doctor ID | Full Name | Count\n";
        if(!results.isBeforeFirst()) {
            return "Error: No matching data";
        }
        else {
            while(results.next()) {
                int yrsExp = results.getInt("Doctors.YrsExperience");
                String docID = results.getString("Doctors.DoctorID");
                String fName = results.getString("Doctors.FullName");
                int count = results.getInt("COUNT(*)");

                result += yrsExp + " | " + docID + " | " + fName + " | " + count;
            }
        }

        return result;
    }

}

