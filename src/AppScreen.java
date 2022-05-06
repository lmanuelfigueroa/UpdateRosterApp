import javax.swing.JComboBox;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import java.util.Scanner;

public class AppScreen implements ActionListener {

    private java.lang.String[] eventList = {"", "Student Union", "SPB", "Tabling Event", "Student Government" };
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
    private String roster_filepath = null;
    private List<String> peopleList = new ArrayList<String>();
    private static final int STUDENT_UNION_POINTS = 15;
    private static final int SPB_POINTS = 25;
    private static final int TABLING_EVENT_POINTS = 10;
    private static final int STUDENT_GOVERMENT_POINTS = 20;
    private int updatedRecords = 0;

    void GUI() {

        initializeFrame();
        setTopPanel();
        setLeftPanel();
        setRightPanel();
        setBottomPanel();

        this.mainFrame.setVisible(true);
    }

    private void initializeFrame() {
        this.mainFrame.setSize(400, 400);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.getContentPane().setBackground(Color.LIGHT_GRAY);

    }

    private void setLeftPanel() {
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints leftgbc = new GridBagConstraints();

        leftgbc.gridx = 0;
        leftgbc.gridy = 0;
        leftgbc.gridheight = 1;
        leftgbc.gridwidth = 1;
        JLabel dropLabel = new JLabel("Select Roster Excel File");
        leftPanel.add(dropLabel, leftgbc);

        leftgbc.gridx = 0;
        leftgbc.gridy = 1;
        leftgbc.gridheight = 1;
        leftgbc.gridwidth = 1;
        this.rosterButton.setSize(100, 50);
        this.rosterButton.addActionListener(this);
        leftPanel.add(this.rosterButton, leftgbc);
        this.mainFrame.add(leftPanel, BorderLayout.WEST);
    }

    private void setRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setLayout(new GridBagLayout());
        GridBagConstraints rightgbc = new GridBagConstraints();

        rightgbc.gridx = 0;
        rightgbc.gridy = 0;
        rightgbc.gridheight = 1;
        rightgbc.gridwidth = 1;
        JLabel dropLabel = new JLabel("Select Event Excel File");
        rightPanel.add(dropLabel, rightgbc);

        rightgbc.gridx = 0;
        rightgbc.gridy = 1;
        rightgbc.gridheight = 1;
        rightgbc.gridwidth = 1;
        this.eventButton.setSize(100, 50);
        this.eventButton.addActionListener(this);
        rightPanel.add(this.eventButton, rightgbc);
        this.mainFrame.add(rightPanel, BorderLayout.EAST);

    }

    /* Event panel contains the dropdown options of the events */
    private void setTopPanel() {

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        JLabel dropLabel = new JLabel("Select the Event Type");
        topPanel.add(dropLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        this.eventComboBox.setSize(100, 50);
        this.eventComboBox.addActionListener(this);
        topPanel.add(this.eventComboBox, gbc);
        this.mainFrame.add(topPanel, BorderLayout.NORTH);

    }

    private void setBottomPanel() {
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        GridBagConstraints bottomgbc = new GridBagConstraints();

        bottomgbc.gridx = 0;
        bottomgbc.gridy = 0;
        bottomgbc.gridheight = 1;
        bottomgbc.gridwidth = 1;
        this.submitButton.setSize(100, 50);
        this.submitButton.addActionListener(this);
        bottomPanel.add(this.submitButton, bottomgbc);
        this.mainFrame.add(bottomPanel, BorderLayout.SOUTH);

    }

    public void updatedInfoDisplay() {
        JOptionPane.showMessageDialog(null, this.updatedRecords + " Records were Updated ", "Updates", JOptionPane.INFORMATION_MESSAGE);
    }

    private int pointsFromEvent() {

        int event_value = -1;
        switch (this.eventName) {

            case "Student Union":
                event_value = STUDENT_UNION_POINTS;
                break;

            case "SPB":
                event_value = SPB_POINTS;
                break;

            case "Student Government":
                event_value = STUDENT_GOVERMENT_POINTS;
                break;

            case "Tabling Event":
                event_value = TABLING_EVENT_POINTS;
                break;
        }

        //System.out.println("Points of event " + event_value);
        return event_value;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == eventComboBox) {
            this.eventName = (String) eventComboBox.getSelectedItem();
        }

        else if (e.getSource() == this.rosterButton) {
            System.out.println("Roster Button");
            this.rosterFileChooser.setCurrentDirectory(new File(this.path));
            int roster_response = this.rosterFileChooser.showOpenDialog(null);
            if (roster_response == JFileChooser.APPROVE_OPTION) {
                this.roster_filepath = this.rosterFileChooser.getSelectedFile().getAbsolutePath();
                this.roster_file = new File(this.roster_filepath);

            }
        }

        else if (e.getSource() == this.eventButton) {
            System.out.println("Event Button");
            this.eventFileChooser.setCurrentDirectory(new File(this.path));
            int event_response = this.eventFileChooser.showOpenDialog(null);

            if (event_response == JFileChooser.APPROVE_OPTION) {
                this.event_file = new File(this.eventFileChooser.getSelectedFile().getAbsolutePath());

                BufferedReader bf;
                try {
                    bf = new BufferedReader(new FileReader(this.event_file));

                    String line = bf.readLine();
                    while ((line = bf.readLine()) != null) {
                        String[] elements = line.split(",");
                        this.peopleList.add(elements[0] + " " + elements[1]);

                    }

                } catch (FileNotFoundException e1) {

                    e1.printStackTrace();
                } catch (IOException e1) {

                    e1.printStackTrace();
                }

            }

        } else if (e.getSource() == this.submitButton) {
            System.out.println("Submit button\n\n");
            String tempfile = path + "\\temp.csv";
            File newFile = new File(tempfile);
            int records_updated = 0;

            BufferedReader rosterReader;

            try {
                rosterReader = new BufferedReader((new FileReader(this.roster_file)));

                rosterReader.readLine();

                FileWriter fw = new FileWriter(tempfile, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                Scanner scanner = new Scanner(this.roster_file);
                scanner.useDelimiter("[,\n]");
                String temp_id = null;
                String temp_first_name = null;
                String temp_last_name = null;
                String temp_email = null;
                String temp_gender = null;
                String temp_points = null;
                String temp_full_name = null;

                String id_field = scanner.next();
                String first_name_field = scanner.next();
                String last_name_field = scanner.next();
                String email_field = scanner.next();
                String gender_field = scanner.next();
                String points_field = scanner.next();

                pw.print(id_field + "," + first_name_field + "," + last_name_field + "," + email_field + ","
                        + gender_field + "," + points_field);
                while (scanner.hasNext()) {
                    temp_id = scanner.next();
                    temp_first_name = scanner.next();
                    temp_last_name = scanner.next();
                    temp_email = scanner.next();
                    temp_gender = scanner.next();
                    temp_points = scanner.next();
                    temp_full_name = temp_first_name + " " + temp_last_name;

                    boolean found_person = false;
                    int i = 0;
                

                    while (!found_person && i < (this.peopleList.size())) {

                        if (temp_full_name.equals(this.peopleList.get(i))) {
                            found_person = true;
                            records_updated ++;

                            temp_points = temp_points.replaceAll("\\s", "");

                            int points = Integer.parseInt(temp_points) + pointsFromEvent();

                            pw.println(temp_id + "," + temp_first_name + "," + temp_last_name + "," + temp_email + ","
                                    + temp_gender + "," + Integer.toString(points));
                        } else {
                            i++;

                        }

                    }
                    if (found_person == false) {
                        pw.print(temp_id + "," + temp_first_name + "," + temp_last_name + "," + temp_email + ","
                                + temp_gender + "," + temp_points);
                    }

                }
                this.updatedRecords = records_updated;
                scanner.close();
                rosterReader.close();
                pw.flush();
                pw.close();
                bw.close();
                fw.close();
                updatedInfoDisplay();
                // this.roster_file.delete();

                // File dump = new File(this.roster_filepath);

                // newFile.renameTo(dump);

            }

            catch (FileNotFoundException e1) {

                System.out.println("File Not Found");
                e1.printStackTrace();
            } catch (IOException e1) {

                System.out.println("Input Error");
                e1.printStackTrace();
            }
        }
    }
    
}
