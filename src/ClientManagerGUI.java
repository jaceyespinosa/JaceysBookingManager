/***************************************************************
 * Name : JaceysClientManager
 * Author: Jacey Espinosa
 * Created : 05/04/2023
 * Course: CIS 152 - Data Structure
 * Version: 1.0
 * OS: Windows 10
 * IDE: Eclipse 2022-03 (4.23.0)
 * Copyright : This is my own original work
 * based on specifications issued by our instructor
 * Description : An app that constructs two DFS/BFS methods.
 *
 Input: Final project data
 *
 Output: Jaceys Client Manager
 * Academic Honesty: I attest that this is my original work.
 * I have not used unauthorized source code, either modified or
 * unmodified. I have not given other fellow student(s) access
 * to my program.
 ***************************************************************
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ClientManagerGUI {
    private JFrame frame;
    private ClientManager clientManager;
    private JButton btnShowAllBookings;

    // Main method to run the application
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ClientManagerGUI window = new ClientManagerGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // Constructor that initializes the ClientManager object and sets up the UI
    public ClientManagerGUI() {
        clientManager = new ClientManager();
        initialize();
    }

    // Error handler making sure that the clients name is a first and last name
    private boolean isValidClientName(String clientName) {
        if (clientName == null || clientName.trim().isEmpty()) {
            return false;
        }
        String[] nameParts = clientName.trim().split("\\s+");
        return nameParts.length >= 2;
    }

    // Method to initialize the UI components and layout
    private void initialize() {
        // Set up the main JFrame
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        // Add the Client Name label and text field
        JLabel lblClientName = new JLabel("Client Name:");
        c.gridx = 0;
        c.gridy = 0;
        frame.getContentPane().add(lblClientName, c);

        JTextField txtClientName = new JTextField();
        c.gridx = 1;
        c.gridy = 0;
        frame.getContentPane().add(txtClientName, c);

        // Add the Reservation Date label and text field
        JLabel lblReservationDate = new JLabel("Reservation Date (yyyy-MM-dd):");
        c.gridx = 0;
        c.gridy = 1;
        frame.getContentPane().add(lblReservationDate, c);

        JTextField txtReservationDate = new JTextField();
        c.gridx = 1;
        c.gridy = 1;
        frame.getContentPane().add(txtReservationDate, c);

        // Add the Add Booking button
        JButton btnAddBooking = new JButton("Add Booking");
        c.gridx = 0;
        c.gridy = 2;
        frame.getContentPane().add(btnAddBooking, c);

        // Add the Show Next Booking button
        JButton btnNextBooking = new JButton("Show Next Booking");
        c.gridx = 1;
        c.gridy = 2;
        frame.getContentPane().add(btnNextBooking, c);

        // Add the Remove Booking button
        JButton btnRemoveBooking = new JButton("Remove Booking");
        c.gridx = 1;
        c.gridy = 3;
        frame.getContentPane().add(btnRemoveBooking, c);

        // Add the output JTextArea with a JScrollPane
        JTextArea txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOutput);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        frame.getContentPane().add(scrollPane, c);

        // Create a DateTimeFormatter to parse and format LocalDate objects
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Add the Show All Bookings button
        btnShowAllBookings = new JButton("Show All Bookings");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 1;
        frame.getContentPane().add(btnShowAllBookings, c);

        // Add action listener for the Add Booking button
        btnAddBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get input values and create a new booking
                    String clientName = txtClientName.getText();
                    if (!isValidClientName(clientName)) {
                        txtOutput.append("Error: Invalid client name\n");
                        return;
                    }
                    LocalDate reservationDate = LocalDate.parse(txtReservationDate.getText(), dateFormatter);
                    clientManager.addBooking(clientName, reservationDate);
                    txtOutput.append("Added booking for " + clientName + " on " + reservationDate + "\n");
                } catch (Exception ex) {
                    txtOutput.append("Error: Invalid date format\n");
                }
            }
        });

        // Add action listener for the Show Next Booking button
        btnNextBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get and display the next booking
                LocalDate reservationDate = clientManager.getNextBookingReservationDate();
                String clientName = clientManager.getNextBookingClientName();
                txtOutput.append("Next Booking: " + clientName + " on " + reservationDate + "\n");
            }
        });

        // Add action listener for the Show All Bookings button
        btnShowAllBookings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Display all bookings
                txtOutput.append("All Bookings:\n");
                for (Map.Entry<String, LocalDate> entry : clientManager.getClientBookings().entrySet()) {
                    String clientName = entry.getKey();
                    LocalDate reservationDate = entry.getValue();
                    txtOutput.append("Client: " + clientName + " on " + reservationDate + "\n");
                }
                txtOutput.append("\n");
            }
        });

        // Add action listener for the Remove Booking button
        btnRemoveBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get input values and remove the specified booking
                    String clientName = txtClientName.getText();
                    if (!isValidClientName(clientName)) {
                        txtOutput.append("Error: Invalid client name\n");
                        return;
                    }
                    LocalDate reservationDate = LocalDate.parse(txtReservationDate.getText(), dateFormatter);
                    clientManager.removeBooking(clientName, reservationDate);
                    txtOutput.append("Removed booking for " + clientName + " on " + reservationDate + "\n");
                } catch (Exception ex) {
                    txtOutput.append("Error: Invalid date format or booking not found\n");
                }
            }
        });
    }
}