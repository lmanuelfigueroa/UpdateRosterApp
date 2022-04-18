import javax.swing.JComboBox;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;
import javax.swing.*;
import java.util.List;




public class Main implements ActionListener {
    private java.lang.String[] eventList = { "Student Union","SPB","Tabling Event", "Student Goverment"  };
    private JComboBox<String> eventComboBox = new JComboBox<String>(eventList);
    private JFrame mainFrame = new JFrame("Update Roster");
    private JButton rosterButton = new JButton("Insert Roster File");
    private JButton eventButton = new JButton("Insert Event File");
    private JButton submitButton = new JButton("Submit");
    private File event_file = null;
    private File roster_file = null;
    private String eventName = null;
    private JFileChooser rosterFileChooser = new JFileChooser();
    private JFileChooser eventFileChooser = new JFileChooser();
    private String path = "C:\\Users\\lmanu\\OneDrive\\Desktop\\Project";

    void GUI() {
        

        initializeFrame();
        setTopPanel();
        setLeftPanel();
        setRightPanel();
        setBottomPanel();
        
        this.mainFrame.setVisible(true);
    }

    private void initializeFrame(){
       this.mainFrame.setSize(400, 400);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);

    }


    private void setLeftPanel(){
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints leftgbc = new GridBagConstraints();

        leftgbc.gridx = 0;
        leftgbc.gridy = 0;
        leftgbc.gridheight = 1;
        leftgbc.gridwidth = 1;
        JLabel dropLabel = new JLabel("Select Roster Excel File");
        leftPanel.add(dropLabel,leftgbc);

        leftgbc.gridx = 0;
        leftgbc.gridy = 1;
        leftgbc.gridheight = 1;
        leftgbc.gridwidth = 1;
        this.rosterButton.setSize(100, 50);
        this.rosterButton.addActionListener(this);
        leftPanel.add(this.rosterButton,leftgbc);
        this.mainFrame.add(leftPanel,BorderLayout.WEST);
    }

    private void setRightPanel(){
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints rightgbc = new GridBagConstraints();

        rightgbc.gridx = 0;
        rightgbc.gridy = 0;
        rightgbc.gridheight = 1;
        rightgbc.gridwidth = 1;
        JLabel dropLabel = new JLabel("Select Event Excel File");
        rightPanel.add(dropLabel,rightgbc);

        rightgbc.gridx = 0;
        rightgbc.gridy = 1;
        rightgbc.gridheight = 1;
        rightgbc.gridwidth = 1;
        this.eventButton.setSize(100, 50);
        this.eventButton.addActionListener(this);
        rightPanel.add(this.eventButton,rightgbc);
        this.mainFrame.add(rightPanel,BorderLayout.EAST);

    }

    /* Event panel contains the dropdown options of the events */
    private void setTopPanel(){

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JLabel dropLabel = new JLabel("Select the Event Type");
        topPanel.add(dropLabel,gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.eventComboBox.setSize(100, 50);
        this.eventComboBox.addActionListener(this);
        topPanel.add(this.eventComboBox,gbc);
        this.mainFrame.add(topPanel,BorderLayout.NORTH);
        
    }

    private void setBottomPanel(){
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints bottomgbc = new GridBagConstraints();

        bottomgbc.gridx = 0;
        bottomgbc.gridy = 0;
        bottomgbc.gridheight = 1;
        bottomgbc.gridwidth = 1;
        this.submitButton.setSize(100,50);
        this.submitButton.addActionListener(this);
        bottomPanel.add(this.submitButton,bottomgbc);
        this.mainFrame.add(bottomPanel,BorderLayout.SOUTH);
        


    }

    @Override
    public void actionPerformed(ActionEvent e ){
        

        if(e.getSource() == eventComboBox){
            this.eventName = (String) eventComboBox.getSelectedItem();
            System.out.println(this.eventName);
        }

        else if(e.getSource() == this.rosterButton){
            this.rosterFileChooser.setCurrentDirectory(new File(this.path));
            int  roster_response = this.rosterFileChooser.showOpenDialog(null);
            if(roster_response == JFileChooser.APPROVE_OPTION){
                this.roster_file = new File(this.rosterFileChooser.getSelectedFile().getAbsolutePath());
                System.out.println(this.roster_file);
            }
        }
        
        else if (e.getSource() == this.eventButton){
            System.out.println("event Button");
            this.eventFileChooser.setCurrentDirectory(new File(this.path));
            int event_response = this.eventFileChooser.showOpenDialog(null);

            if(event_response == JFileChooser.APPROVE_OPTION){
                this.event_file = new File(this.eventFileChooser.getSelectedFile().getAbsolutePath());
                BufferedReader br;
                List<Person> peopleList = new ArrayList<Person>();
                try {
                    br = new BufferedReader((new FileReader(this.event_file)));
                    String line = null;
                    br.readLine();
                    while((line = br.readLine()) != null){
                        String[] elements = line.split(",");
                        Person newPerson = new Person(elements[0],elements[1],elements[2]);
                        peopleList.add(newPerson);    

                    }
                    
    
                } catch (FileNotFoundException e1) {
                   
                    System.out.println("File Not Found");
                    e1.printStackTrace();
                } catch (IOException e1) {
                    
                    System.out.println("Input Error");
                    e1.printStackTrace();
                }
                
            
            }
            
        }
        else if (e.getSource() == this.submitButton){
            System.out.println("submit button");
        }
    }

    public static void main(String args[]) {
        Main app = new Main();
        app.GUI();

    }

}