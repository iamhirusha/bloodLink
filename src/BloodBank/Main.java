package BloodBank;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;


public class Main extends JFrame {

    private JPanel contentPane;
    private JTextField name;
    private JTextField age;
    private JTextField address;
    private JTextField phone_number;
    private JTabbedPane tabbedPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     *
     */

    Connection c= null;
    private JTable search_table;
    private JTextField nat;  //name
    private JTextField agt;  //age
    private JTextField bgt; // blood group
    private JTextField adt;  //address
    private JTextField pht;  //phone
    private JTable update_table;
    private JTextField quantity; //address
    private JTextField qt;

    public Main() throws SQLException {
        c=DriverManager.getConnection("jdbc:mysql://localhost:3306/bloodbank?user=root&password=");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1050, 700);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(25, 25, 112));
        contentPane.setBackground(new Color(33, 72, 138));/* Blue  */
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);


        JLabel lblBloodDonation = new JLabel("BloodLink.");
        lblBloodDonation.setBackground(new Color(0, 0, 0));
        lblBloodDonation.setForeground(new Color(255, 94, 94));/* red */
        lblBloodDonation.setHorizontalAlignment(SwingConstants.CENTER);
        lblBloodDonation.setFont(new Font("Tabarra Black", Font.BOLD ,50));
        lblBloodDonation.setBounds(0, 39, 1032, 94);
        contentPane.add(lblBloodDonation);

        /* tabbed pane */
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.setBorder(null);
        tabbedPane.setBackground(new Color(245, 255, 250));
        tabbedPane.setFont(new Font("Tahoma", Font.BOLD, 17));
        tabbedPane.setBounds(10, 132, 1010, 508);
        contentPane.add(tabbedPane);

        /* add pane  */
        JPanel add = new JPanel();
        add.setBackground(new Color(233, 236, 235));
        tabbedPane.addTab("ADD DONOR", null, add, null);
        add.setFont(new Font("Tahoma", Font.BOLD, 20));
        add.setLayout(null);

        JLabel lblAddInformation = new JLabel("Add Information");
        lblAddInformation.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddInformation.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblAddInformation.setBounds(12, 13, 878, 42);
        add.add(lblAddInformation);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblName.setBounds(58, 68, 196, 27);
        add.add(lblName);

        name = new JTextField();
        name.setBackground(new Color(255, 255, 255));
        name.setBounds(281, 68, 478, 27);
        add.add(name);
        name.setColumns(10);

        JLabel label = new JLabel("Age");
        label.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label.setBounds(58, 119, 196, 27);
        add.add(label);

        age = new JTextField();
        age.setColumns(10);
        age.setBounds(281, 119, 478, 27);
        add.add(age);

        JLabel label_1 = new JLabel("Blood Group");
        label_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_1.setBounds(58, 175, 196, 27);
        add.add(label_1);

        JLabel label_2 = new JLabel("Address");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_2.setBounds(58, 232, 196, 27);
        add.add(label_2);

        address = new JTextField();
        address.setColumns(10);
        address.setBounds(281, 232, 478, 27);
        add.add(address);

        JLabel label_3 = new JLabel("Phone number");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_3.setBounds(58, 287, 196, 27);
        add.add(label_3);

        phone_number = new JTextField();
        phone_number.setColumns(10);
        phone_number.setBounds(281, 287, 478, 27);
        add.add(phone_number);



        quantity = new JTextField();
        quantity.setColumns(10);
        quantity.setBounds(281, 342, 478, 27);
        add.add(quantity);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblQuantity.setBounds(58, 342, 196, 27);
        add.add(lblQuantity);

        JComboBox <String> comboBox = new JComboBox<>();
        comboBox.setForeground(new Color(0, 0, 0));
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setToolTipText("Select your blood group");
        comboBox.setBackground(new Color(255, 255, 255));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}));
        comboBox.setBounds(281, 175, 478, 22);
        add.add(comboBox);

        JButton submit = new JButton("SUBMIT");
        submit.setForeground(new Color(255, 255, 255));
        submit.setBackground(new Color(33, 72, 138, 255));
        submit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                String nam= name.getText();
                String addres= address.getText();
                String phone= phone_number.getText();
                //String bg = blood_group.getText().toUpperCase();
                String bg = (String) comboBox.getSelectedItem();
                String ag = age.getText();
                String q=quantity.getText();

                // add validation
                if (nam.isEmpty() || addres.isEmpty() || phone.isEmpty() || bg.isEmpty() || ag.isEmpty() || q.isEmpty() || q == null || ag == null || bg == null || phone == null || addres == null || nam == null) {
                    JOptionPane.showMessageDialog(null, "Please fill all the fields");
                    return;
                }

                //String query4="Update blood Set Name='"+ nam +"',Age='"+ ag + "',BloodGroup='"+ bg + "',Address='"+addres+"',PhoneNumber='"+phone + "',Quantity='"+q+"' where Name='" +nam+"'";
                String queryInsert = "INSERT INTO blood (Name, Age, BloodGroup, Address, PhoneNumber, Quantity) VALUES (?, ?, ?, ?, ?, ?)";

                try {
                    PreparedStatement preparedStatement = c.prepareStatement(queryInsert);
                    preparedStatement.setString(1, nam);
                    preparedStatement.setString(2, ag);
                    preparedStatement.setString(3, bg);
                    preparedStatement.setString(4, addres);
                    preparedStatement.setString(5, phone);
                    preparedStatement.setString(6, q);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Donor inserted successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to insert donor");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred while inserting donor: " + e.getMessage());
                }






                name.setText("");
                address.setText("");
                phone_number.setText("");
                age.setText("");
                quantity.setText("");
                //blood_group.setText("");

            }
        });
        submit.setFont(new Font("Verdana", Font.BOLD, 12));
        submit.setBounds(442, 398, 97, 32);
        add.add(submit);


        /* search pane  */
        JPanel search = new JPanel();
        search.setBackground(new Color(245, 255, 250));
        tabbedPane.addTab("SEARCH", null, search, null);
        search.setFont(new Font("Times New Roman", Font.BOLD, 20));
        search.setLayout(null);

        JLabel label_4 = new JLabel("Blood Group");
        label_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_4.setBounds(98, 57, 196, 27);
        search.add(label_4);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 156, 878, 334);
        search.add(scrollPane);

        search_table = new JTable();
        scrollPane.setViewportView(search_table);

        JComboBox <String> searchComboBox = new JComboBox<>();
        searchComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        searchComboBox.setBackground(Color.WHITE);
        searchComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"}));
        searchComboBox.setBounds(254, 61, 350, 22);
        search.add(searchComboBox);

        JButton send = new JButton("SEARCH");
        send.setForeground(new Color(255, 255, 255));
        send.setBackground(new Color(33, 72, 138));
        send.setFont(new Font("Verdana", Font.BOLD, 12));
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("Search button clicked");
                String bg1 = (String) searchComboBox.getSelectedItem();
                System.out.println("Blood group: " + bg1);

                String query1 = "select * from blood where BloodGroup=?";
                try {
                    PreparedStatement preparedStatement = c.prepareStatement(query1);
                    preparedStatement.setString(1, bg1);
                    ResultSet rs = preparedStatement.executeQuery();

                    // Get column names
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    Vector<String> columnNames = new Vector<>();
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames.add(metaData.getColumnName(i));
                    }

                    // Get data rows
                    Vector<Vector<Object>> data = new Vector<>();
                    while (rs.next()) {
                        Vector<Object> row = new Vector<>();
                        for (int i = 1; i <= columnCount; i++) {
                            row.add(rs.getObject(i));
                        }
                        data.add(row);
                    }

                    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
                    search_table.setModel(tableModel);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });
        send.setBounds(349, 109, 97, 33);
        search.add(send);

        /* update pane  */

        JPanel update = new JPanel();
        update.setBackground(new Color(245, 255, 250));
        update.setFont(new Font("Times New Roman", Font.BOLD, 20));
        tabbedPane.addTab("UPDATE", null, update, null);
        update.setLayout(null);

        JLabel label_5 = new JLabel("Name");
        label_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_5.setBounds(12, 13, 196, 27);
        update.add(label_5);

        nat = new JTextField();
        nat.setEditable(false);
        nat.setColumns(10);
        nat.setBounds(235, 13, 478, 27);
        update.add(nat);

        JLabel label_6 = new JLabel("Age");
        label_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_6.setBounds(12, 53, 196, 27);
        update.add(label_6);

        agt = new JTextField();
        agt.setColumns(10);
        agt.setBounds(235, 53, 478, 27);
        update.add(agt);

        JLabel label_7 = new JLabel("Blood Group");
        label_7.setFont(new Font("Thoma", Font.PLAIN, 18));
        label_7.setBounds(12, 93, 196, 27);
        update.add(label_7);

        bgt = new JTextField();
        bgt.setColumns(10);
        bgt.setBounds(235, 93, 478, 27);
        update.add(bgt);

        JLabel label_8 = new JLabel("Address");
        label_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_8.setBounds(12, 133, 196, 27);
        update.add(label_8);

        adt = new JTextField();
        adt.setColumns(10);
        adt.setBounds(235, 133, 478, 27);
        update.add(adt);

        JLabel label_9 = new JLabel("Phone number");
        label_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_9.setBounds(12, 173, 196, 27);
        update.add(label_9);

        pht = new JTextField();
        pht.setColumns(10);
        pht.setBounds(235, 173, 478, 27);
        update.add(pht);

        JButton btnUpdate = new JButton("UPDATE");
        btnUpdate.setBackground(new Color(33, 72, 138));
        btnUpdate.setForeground(new Color(255, 255, 255));

        JButton btnNewButton = new JButton("DELETE");
        btnNewButton.setForeground(new Color(255, 255, 255));
        btnNewButton.setBackground(new Color(33, 72, 138));

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int row=-1;
                row = update_table.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a Name to update");
                } else {

                    System.out.println("Row selected");
                    String name =(String) update_table.getValueAt(row, 1);

                    System.out.println("Selected name: " + name);



                    // usinge PreparedStatement to prevent SQL injection
                    String query = "DELETE FROM blood WHERE Name=?";

                    try (PreparedStatement st = c.prepareStatement(query)) {
                        st.setString(1, name);

                        int rowsAffected = st.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Donor deleted");
                            JOptionPane.showMessageDialog(null, "Donor deleted");
                            //new Main();
                        } else {
                            System.out.println("No donor found with the given name");
                            JOptionPane.showMessageDialog(null, "No donor found with the given name", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                        // Handle the exception appropriately
                        JOptionPane.showMessageDialog(null, "Error deleting donor: Error" + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    try{
                        // new RESULT(QNO);

                    }
                    catch (Exception e1)
                    {
                        e1.printStackTrace();

                    }
                }
            }
        });

        btnUpdate.addActionListener(e ->  {


            System.out.println("Update button clicked");
//---------------------

            int row = update_table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a question number");
            } else {
                System.out.println("Row selected");
                String name = (String) update_table.getValueAt(row, 1);
                System.out.println("Selected name: " + name);

                String address = adt.getText();
                String phone = pht.getText();
                String bloodGroup = bgt.getText();
                String age = agt.getText();
                String quantity = qt.getText();

                // Construct the base query
                String query = "UPDATE blood SET ";

                boolean hasUpdates = false; // Flag to check if any updates are made

                // Check each field and append to the query if not empty
                if (!age.isEmpty()) {
                    query += "Age='" + age + "', ";
                    hasUpdates = true;
                }
                if (!bloodGroup.isEmpty()) {
                    query += "BloodGroup='" + bloodGroup + "', ";
                    hasUpdates = true;
                }
                if (!address.isEmpty()) {
                    query += "Address='" + address + "', ";
                    hasUpdates = true;
                }
                if (!phone.isEmpty()) {
                    query += "PhoneNumber='" + phone + "', ";
                    hasUpdates = true;
                }
                if (!quantity.isEmpty()) {
                    query += "Quantity='" + quantity + "', ";
                    hasUpdates = true;
                }

                // Remove the trailing comma and space if there are updates
                if (hasUpdates) {
                    query = query.substring(0, query.length() - 2); // Remove the last ", "
                    query += " WHERE Name='" + name + "'";
                } else {
                    JOptionPane.showMessageDialog(null, "No fields to update", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop execution if no fields to update
                }

                try (PreparedStatement st = c.prepareStatement(query)) {
                    int rowsAffected = st.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Donor updated");
                        JOptionPane.showMessageDialog(null, "Donor updated");
                    } else {
                        System.out.println("No donor found with the given name");
                        JOptionPane.showMessageDialog(null, "No donor found with the given name", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating donor: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                try {
                    // new RESULT(QNO);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }



            //-----------------






        });

        btnUpdate.setFont(new Font("Verdana", Font.BOLD, 12));
        btnUpdate.setBounds(292, 253, 97, 27);
        update.add(btnUpdate);

        btnNewButton.setFont(new Font("Verdana", Font.BOLD, 12));
        btnNewButton.setBounds(442, 253, 97, 27);
        update.add(btnNewButton);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(12, 291, 878, 199);
        update.add(scrollPane_1);

        update_table = new JTable();
        update_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                update_table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent arg0) {
                        int row = update_table.getSelectedRow();
                        if (row != -1) { // Check if a row is selected
                            String n = update_table.getModel().getValueAt(row, 0).toString();
                            try {
                                Statement st3 = c.createStatement();
                                String query3 = "select * from blood where Name='" + n + "'";
                                ResultSet rs3 = st3.executeQuery(query3);

                                while (rs3.next()) {
                                    nat.setText(rs3.getString("Name"));
                                    agt.setText(rs3.getString("Age"));
                                    adt.setText(rs3.getString("Address"));
                                    pht.setText(rs3.getString("PhoneNumber"));
                                    bgt.setText(rs3.getString("BloodGroup"));
                                    qt.setText(rs3.getString("Quantity"));
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });


            }
        });
        scrollPane_1.setViewportView(update_table);

        JButton btnShow = new JButton("SHOW");
        btnShow.setForeground(new Color(255, 255, 255));
        btnShow.setBackground(new Color(33, 72, 138));
        btnShow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    String query2 = "select * from blood";
                    Statement st2 = c.createStatement();
                    ResultSet rs2 = st2.executeQuery(query2);

                    DefaultTableModel model = new DefaultTableModel();
                    ResultSetMetaData metaData = rs2.getMetaData();

                    // Get column count
                    int columnCount = metaData.getColumnCount();

                    // Add columns to the model
                    for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                        model.addColumn(metaData.getColumnLabel(columnIndex));
                    }

                    // Add rows to the model
                    while (rs2.next()) {
                        Object[] row = new Object[columnCount];
                        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                            row[columnIndex - 1] = rs2.getObject(columnIndex);
                        }
                        model.addRow(row);
                    }

                    // Set the model to the table
                    update_table.setModel(model);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });
        btnShow.setFont(new Font("Verdana", Font.BOLD, 12));
        btnShow.setBounds(570, 253, 97, 27);
        update.add(btnShow);

        qt = new JTextField();
        qt.setColumns(10);
        qt.setBounds(235, 213, 478, 27);
        update.add(qt);

        JLabel label_10 = new JLabel("Quantity");
        label_10.setFont(new Font("Tahoma", Font.PLAIN, 18));
        label_10.setBounds(12, 213, 196, 27);
        update.add(label_10);

    }
}