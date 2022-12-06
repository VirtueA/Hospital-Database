//import java gui classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//main gui class
class DB_gui implements ActionListener{


    //create frame, panels, and buttons
    JFrame mainframe;
    JPanel input = new JPanel();
    JPanel exit = new JPanel();
    JLabel pic;

    JPanel options = new JPanel();
    JButton add = new JButton("ADD PATIENT");
    JButton delete = new JButton("DELETE PATIENT");
    JButton find = new JButton("FIND PATIENT");
    JButton blood = new JButton("GET BLOODTYPES");
    JButton docSpec = new JButton("SEARCH BY SPECIALIZATION");
    JButton nurse = new JButton("SHOW NURSE INFO");
    JButton date = new JButton("DOCTORS ON SCHEDULE");
    JButton quit = new JButton("Quit");
    
    //THE INPUT FIELDS WILL BE USED TO GENERATE QUERIES********
    JTextField input_1 = new JTextField();
    JTextField input_2 = new JTextField();
    JTextField input_3 = new JTextField();
    JTextField input_4 = new JTextField();

    //constructor for the class frame
    DB_gui() throws IOException{
        //the main frame for the gui
        mainframe = new JFrame("Database Project");
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setSize(new Dimension (Toolkit.getDefaultToolkit().getScreenSize()));
        mainframe.getContentPane().setBackground(Color.pink);
        options.setBackground(Color.pink);
        input.setOpaque(false);

        //insert nurse image
        BufferedImage image = ImageIO.read(
                new URL("https://play-lh.googleusercontent.com/3AS7LSQkFi9P_1LOi_uKBvZ7p5C7qODcTvRYQCaZgFAthISYnOpL8iPx50KCwB4Pb9s"));
        pic = new JLabel(new ImageIcon(image));
        pic.setBounds(100, 100, pic.getPreferredSize().width, pic.getPreferredSize().height);
        mainframe.getContentPane().add(pic);


        //buttons for the options
        options.add(add);
        options.add(find);
        options.add(delete);
        options.add(docSpec);
        options.add(date);
        options.add(nurse);
        options.add(blood);

        delete.setBackground(Color.red);
        exit.add(quit);
        exit.setOpaque(false);

        //add actions listeners to the buttons
        add.addActionListener(this);
        delete.addActionListener(this);
        find.addActionListener(this);
        blood.addActionListener(this);
        docSpec.addActionListener(this);
        nurse.addActionListener(this);
        date.addActionListener(this);
        quit.addActionListener(this);

        //set action commands for the buttons
        add.setActionCommand("insert");
        delete.setActionCommand("delete");
        find.setActionCommand("find");
        blood.setActionCommand("blood");
        docSpec.setActionCommand("spec");
        nurse.setActionCommand("nurse");
        date.setActionCommand("date");
        quit.setActionCommand("quit");


        //add the elements to the main frame
        mainframe.getContentPane().add(BorderLayout.SOUTH, exit);
        mainframe.getContentPane().add(BorderLayout.NORTH,options);

        //make the frame visible
        mainframe.setVisible(true);
    }

    //event listener for the buttons
    @Override
    public void actionPerformed(ActionEvent click){
        String url = "jdbc:mysql://localhost:3306/hospitaldb";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, "root", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        mainframe.remove(options);//remove the buttons at the top
        mainframe.remove(pic);//remove the previous picture
        mainframe.setSize(450, 500);//resize the window
        JButton command = new JButton();
        command.addActionListener(this);

        //add a new image
        BufferedImage image2 = null;
        try {
            image2 = ImageIO.read(
                    new URL("https://www.redcross.org/content/dam/redcross/icons/disaster-services/disaster-specialist/Disaster-Specialist-1000x1000-G-Pl.png.transform/282/q70/feature/image.png"));
        }catch(Exception e) {
            System.out.println(e);
        }
        JLabel pic2 = new JLabel(new ImageIcon(image2), JLabel.CENTER);
        pic2.setBounds(100, 150, pic2.getPreferredSize().width, pic2.getPreferredSize().height);
        mainframe.getContentPane().add(pic2);


        //add a button to go back to the home page
        JButton back = new JButton("Back to Main Page");
        back.addActionListener(this);
        back.setActionCommand("back");
        quit.setBackground(Color.red);


        //create a panel and some text fields for input
        mainframe.getContentPane().add(BorderLayout.CENTER, input);
        
        //get the button that was pressed
        String buttonPressed = click.getActionCommand();

        //change ui and perform actions based on the button clicked
        if(buttonPressed == "quit")//if the user chooses quit, exit the program
        {
            System.exit(0);
        }
        else if(buttonPressed == "back")//if the user chooses back, close this instance of the frame and create a new one
        {
            mainframe.dispose();
            try {
                DB_gui.main(new String[1]);}
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        else if(buttonPressed == "insert")
        {
            //text field labels
            JLabel name = new JLabel("Enter the patient's full name");
            JLabel ssn = new JLabel("Enter the patient's SSN");
            JLabel id = new JLabel("Enter the patient's ID");
            JLabel dob = new JLabel("Enter the patient's date of birth");

            //text fields
            input_1 = new JTextField("Eg. John Smith",25);
            input_2 = new JTextField("Enter a 10-digit number",25);
            input_3 = new JTextField("Enter a 9-digit number", 25);
            input_4 = new JTextField("YYYY-MM-DD", 25);

            //add text fields and labels
            input.add(name); 	input.add(input_1);
            input.add(ssn); 	input.add(input_2);
            input.add(id); 		input.add(input_3);
            input.add(dob); 	input.add(input_4);

            //change button view depending on the command and execute the command
            command.setText("INSERT");
            command.setActionCommand("inserted");

            //add buttons
            input.add(command);
            exit.add(back);
        }
        else if (buttonPressed == "find")
        {
            //text field labels and text fields
            JLabel id = new JLabel("Enter the patient's ID");
            input_1 = new JTextField("Enter a 10-digit number", 25);

            input.add(id);		input.add(input_1);

            command.setText("FIND");
            command.setActionCommand("found");

            //add buttons
            input.add(command);
            exit.add(back);
        }
        else if (buttonPressed == "delete")
        {
            //text field labels and text fields
            JLabel ssn = new JLabel("Enter the patient's SSN");
            input_1 = new JTextField("Enter a 9-digit number", 25);

            input.add(ssn);		input.add(input_1);

            command.setText("DELETE");
            command.setActionCommand("deleted");

            //add buttons
            input.add(command);
            exit.add(back);
        }
        else if(buttonPressed == "spec") {
            //text field labels and text fields
            JLabel spec = new JLabel("Enter the specialization");
            input_1 = new JTextField("Eg. Pediatrics",25);

            input.add(spec);	input.add(input_1);

            command.setText("DISPLAY DOCTORS");
            command.setActionCommand("specFound");

            //add buttons
            input.add(command);
            exit.add(back);
        }
        else if(buttonPressed == "date") {
            //text field labels and text fields
            JLabel date = new JLabel("Enter the desired date");
            input_1 = new JTextField("YYYY-MM-DD", 25);
            input.add(date);
            input.add(input_1);
            command.setText("SHOW DOCTORS ON STAFF");
            command.setActionCommand("dateFound");

            //add buttons
            input.add(command);
            exit.add(back);
        }
        else {
            mainframe.remove(input);
            JTextArea results = new JTextArea();
            switch (buttonPressed) {
                case "inserted":
                    try {
                        DatabaseConnect.insertPatient(con, input_1.getText(), input_2.getText(), input_3.getText(), input_4.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "deleted":
                    try {
                        DatabaseConnect.deletePatient(con, input_1.getText());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "found":
                    try {
                        results.append(DatabaseConnect.findPatient(con, input_1.getText()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "nurse":
                    try {
                        results.append(DatabaseConnect.NursesPatients(con));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "blood":
                    try {
                        results.append(DatabaseConnect.numBloodTypes(con));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "specFound":
                    try {
                        results.append(DatabaseConnect.docSpecialization(con, input_1.getText()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case "dateFound":
                    try {
                        results.append(DatabaseConnect.doctorsWorking(con, input_1.getText()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
            }

            mainframe.getContentPane().add(results);
            results.setBackground(Color.lightGray);
            mainframe.revalidate();
        }

    }

    public static void main(String args[]) throws IOException{
        DB_gui DBProject = new DB_gui();//DB_gui object to run the gui

    }
}
