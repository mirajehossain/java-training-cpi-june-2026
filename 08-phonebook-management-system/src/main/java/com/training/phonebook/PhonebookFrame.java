package com.training.phonebook;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PhonebookFrame extends JFrame {
    private final JTextField nameField;
    private final JTextField phoneField;
    private final JTextField emailField;
    private final JTextField addressField;
    private final JTextField searchField;
    private final JTable table;
    private final DefaultTableModel tableModel;
    private int selectedId = -1;

    public PhonebookFrame() {
        setTitle("Phonebook Management System");
        setSize(900, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        addressField = new JTextField();

        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);

        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);

        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);

        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton clearButton = new JButton("Clear");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Phone", "Email", "Address"}, 0);
        table = new JTable(tableModel);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(25);
        JButton searchButton = new JButton("Search");
        JButton refreshButton = new JButton("Refresh");

        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(refreshButton);

        add(searchPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addContact());
        updateButton.addActionListener(e -> updateContact());
        deleteButton.addActionListener(e -> deleteContact());
        clearButton.addActionListener(e -> clearForm());
        searchButton.addActionListener(e -> searchContacts());
        refreshButton.addActionListener(e -> loadContacts());

        table.getSelectionModel().addListSelectionListener(e -> fillFormFromSelectedRow());

        loadContacts();
    }

    private boolean validateForm() {
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name is required.");
            return false;
        }

        if (phoneField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Phone is required.");
            return false;
        }

        return true;
    }

    private void addContact() {
        if (!validateForm()) return;

        String sql = "INSERT INTO contacts(name, phone, email, address) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nameField.getText().trim());
            ps.setString(2, phoneField.getText().trim());
            ps.setString(3, emailField.getText().trim());
            ps.setString(4, addressField.getText().trim());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Contact added successfully.");
            clearForm();
            loadContacts();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Insert error: " + ex.getMessage());
        }
    }

    private void loadContacts() {
        tableModel.setRowCount(0);

        String sql = "SELECT * FROM contacts ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address")
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Load error: " + ex.getMessage());
        }
    }

    private void fillFormFromSelectedRow() {
        int row = table.getSelectedRow();

        if (row >= 0) {
            selectedId = Integer.parseInt(tableModel.getValueAt(row, 0).toString());
            nameField.setText(valueAt(row, 1));
            phoneField.setText(valueAt(row, 2));
            emailField.setText(valueAt(row, 3));
            addressField.setText(valueAt(row, 4));
        }
    }

    private String valueAt(int row, int column) {
        Object value = tableModel.getValueAt(row, column);
        return value == null ? "" : value.toString();
    }

    private void updateContact() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a contact first.");
            return;
        }

        if (!validateForm()) return;

        String sql = "UPDATE contacts SET name=?, phone=?, email=?, address=? WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nameField.getText().trim());
            ps.setString(2, phoneField.getText().trim());
            ps.setString(3, emailField.getText().trim());
            ps.setString(4, addressField.getText().trim());
            ps.setInt(5, selectedId);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Contact updated successfully.");
            clearForm();
            loadContacts();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Update error: " + ex.getMessage());
        }
    }

    private void deleteContact() {
        if (selectedId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a contact first.");
            return;
        }

        String sql = "DELETE FROM contacts WHERE id=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, selectedId);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Contact deleted successfully.");
            clearForm();
            loadContacts();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Delete error: " + ex.getMessage());
        }
    }

    private void searchContacts() {
        tableModel.setRowCount(0);

        String keyword = searchField.getText().trim();
        String sql = "SELECT * FROM contacts WHERE name LIKE ? OR phone LIKE ? OR email LIKE ? ORDER BY id DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchValue = "%" + keyword + "%";
            ps.setString(1, searchValue);
            ps.setString(2, searchValue);
            ps.setString(3, searchValue);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address")
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Search error: " + ex.getMessage());
        }
    }

    private void clearForm() {
        selectedId = -1;
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
        addressField.setText("");
        table.clearSelection();
    }
}
