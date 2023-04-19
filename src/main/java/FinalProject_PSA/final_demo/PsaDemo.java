package FinalProject_PSA.final_demo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PsaDemo {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        // Create a new JFrame
        JFrame frame = new JFrame("Four Button Swing UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a new JPanel with a BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        
        panel.setPreferredSize(new Dimension(700, 200));
        panel.setBackground(Color.CYAN);

        // Create four JButton instances
        JButton button1 = new JButton("ChristoFides");
        button1.setBackground(Color.RED);
        button1.setOpaque(true);
        button1.setBorderPainted(false);
        JButton button2 = new JButton("Simulated Annealing");
        button2.setBackground(Color.YELLOW);
        button2.setOpaque(true);
        button2.setBorderPainted(false);
        JButton button3 = new JButton("Ant Colony");
        button3.setBackground(Color.GREEN);
        button3.setOpaque(true);
        button3.setBorderPainted(false);
        JButton button4 = new JButton("Random Swapping");
        button4.setBackground(Color.ORANGE);
        button4.setOpaque(true);
        button4.setBorderPainted(false);
        JButton button5 = new JButton("3 OPT");
        button5.setBackground(Color.PINK);
        button5.setOpaque(true);
        button5.setBorderPainted(false);
        // Add event handlers for the buttons
        Driver d = new Driver();
        
        button1.addActionListener((ActionEvent e) -> {
            System.out.println("Button 1 clicked");
//            Driver d = new Driver();
    		d.travellingSalesman("christofides");
        });

        button2.addActionListener((ActionEvent e) -> {
            System.out.println("Button 2 clicked");
//            Driver d = new Driver();
    		d.travellingSalesman("simulatedAnnealing");
        });

        button3.addActionListener((ActionEvent e) -> {
            System.out.println("Button 3 clicked");
//            Driver d = new Driver();
    		d.travellingSalesman("antColony");
        });

        button4.addActionListener((ActionEvent e) -> {
            System.out.println("Button 4 clicked");
//            Driver d = new Driver();
    		d.travellingSalesman("randomSwapping");
        });
        
        button5.addActionListener((ActionEvent e) -> {
            System.out.println("Button 5 clicked");
//            Driver d = new Driver();
        	d.travellingSalesman("3opt");
        });

        // Add buttons to the panel
        JPanel topPanel = new JPanel();
        topPanel.add(button1);
        topPanel.add(button2);
        topPanel.add(button3);
        topPanel.add(button4);
        topPanel.add(button5);
        panel.add(topPanel, BorderLayout.NORTH);


        // Add the panel to the frame
        frame.getContentPane().add(panel);

        // Display the frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
   
}
