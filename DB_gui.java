package db_project;

//import java gui classes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
import java.net.URL;


//main gui class
class DB_gui implements ActionListener {
 	
	//create frame, panels, and buttons
	JFrame mainframe;
	JPanel input = new JPanel();
	JPanel exit = new JPanel();
	JLabel pic;
	
	JPanel options = new JPanel();
    JButton add = new JButton("ADD PATIENT");
    JButton delete = new JButton("DELETE PATIENT");
    JButton find = new JButton("FIND PATIENT");
    JButton assign = new JButton("ASSIGN PATIENT");
    JButton docSpec = new JButton("SEARCH BY SPECIALIZATION");
    JButton docSched = new JButton("DOCTOR'S SCHEDULE");
    JButton date = new JButton("DOCTORS ON SCHEDULE");
    JButton quit = new JButton("Quit");
    
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
	     options.add(assign);
	     options.add(docSched);
	     options.add(date);
	     
	     delete.setBackground(Color.red);
	     exit.add(quit);
	     exit.setOpaque(false);
	     
	     //add actions listeners to the buttons
	     add.addActionListener(this);
	     delete.addActionListener(this);
	     find.addActionListener(this);
	     assign.addActionListener(this);
	     docSpec.addActionListener(this);
	     docSched.addActionListener(this);
	     date.addActionListener(this);
	     quit.addActionListener(this);
	     
	     //set action commands for the buttons
	     add.setActionCommand("insert");
	     delete.setActionCommand("delete");
	     find.setActionCommand("find");
	     assign.setActionCommand("assign");
	     docSpec.setActionCommand("spec");
	     docSched.setActionCommand("sched");
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
	    JTextField input_1;//THE INPUT FIELDS WILL BE USED TO GENERATE QUERIES********
	    JTextField input_2;
	    JTextField input_3;
	    JTextField input_4;

	    
	    
	    
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
	    else if(buttonPressed == "insert" || buttonPressed == "find" || buttonPressed == "delete")
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
	    	input_4 = new JTextField("MM-DD-YYYY", 25); 	    	
	    	
	    	//add text fields and labels
	    	input.add(name); 	input.add(input_1);
	    	input.add(ssn); 	input.add(input_2);
	    	input.add(id); 		input.add(input_3);
	    	input.add(dob); 	input.add(input_4);
	    	
	    	//change button view depending on the command and execute the command
	    	if(buttonPressed == "insert")
	    	{
	    		command.setText("INSERT");
	    		command.setActionCommand("inserted");
	    	}
	    	else if(buttonPressed == "delete")
	    	{
	    		command.setText("DELETE");
	    		command.setActionCommand("deleted");
	    	}
	    	else if(buttonPressed == "find")
	    	{
	    		command.setText("FIND");
	    		command.setActionCommand("found");
	    	}
	    	
	    	//add buttons
	    	input.add(command);
	    	exit.add(back);
	    }
	    else if(buttonPressed == "assign") {
	    	//text field labels and text fields
	    	JLabel id = new JLabel("Enter the patient's ID");
	    	input_1 = new JTextField("Enter a 9-digit number", 25);
	    	JLabel years = new JLabel("Minimum years of doctor experience");
	    	input_2 = new JTextField(2);
	    	
	    	input.add(id);		input.add(input_1);
	    	input.add(years); 	input.add(input_2);
	    	
	    	command.setText("ASSIGN");
	    	command.setActionCommand("assigned");
	    	
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
	    else if(buttonPressed == "sched") {
	    	//text field labels and text fields
	    	JLabel schedL = new JLabel("Enter the doctor's ID");
	    	input_1 = new JTextField("Enter a 9-digit number", 25);
	    	input.add(schedL);
	    	input.add(input_1);
	    	command.setText("DISPLAY SCHEDULE");
	    	command.setActionCommand("schedFound");
	    	
	    	//add buttons
	    	input.add(command);
	    	exit.add(back);
	    }
	    else if(buttonPressed == "date") {
	    	//text field labels and text fields
	    	JLabel dateL = new JLabel("Enter the desired date");
	    	input_1 = new JTextField("YYYY-MM-DD", 25);
	    	input.add(dateL);
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
	    	results.append("SUCCESS!");//PLACEHOLDER FOR ACTUAL RESULTS; WILL CALL FUNCTION BASED ON THE BUTTON CLICKED AND DISPLAY RESULTS*************** 
	    	mainframe.getContentPane().add(results);
	    	results.setBackground(Color.lightGray);
	    	mainframe.revalidate();
	    }
		
	}
	
	public static void main(String args[]) throws IOException{
		DB_gui DBProject = new DB_gui();//DB_gui object to run the gui
 }
}