package view;

import com.google.gson.Gson;
import control.LoginSignupControl;
import javafx.scene.layout.HBox;
import model.Game;
import model.user.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class scoreBoardView extends JFrame {
    private JScrollPane scrollPane;
    private JPanel panel;
    private int dataCount = Game.getPlayers().size();
    private int visibleCount = 10;
    private int loadedCount = 0;

    public scoreBoardView() {
        LoginSignupControl.sort(Game.getPlayers());
        setTitle("Score board");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setBackground(Color.green);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(panel);
        JButton back = new JButton("back to profile menu");
        //  panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), StartGame.class.getResource("/fxml/ProfileView.fxml").toExternalForm());
            }
        });
        scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                JScrollBar scrollBar = (JScrollBar) e.getSource();
                int extent = scrollBar.getModel().getExtent();
                int maximum = scrollBar.getModel().getMaximum();
                int value = scrollBar.getValue();
                if (value + extent == maximum) {
                    try {
                        loadMoreData();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        add(scrollPane);
        setVisible(true);
    }

    private void loadMoreData() throws IOException {
        for (int i = loadedCount; i < loadedCount + visibleCount && i < dataCount; i++) {
            JLabel label = new JLabel("Rank:  " + (i + 1) + "  Username: " + Game.getPlayers().get(i).getUsername() + " Points: " + Game.getPlayers().get(i).getScore());
            EmptyBorder border = new EmptyBorder(10, 20, 15, 20);
            label.setBorder(border);
            MouseListener mouseListener = new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        setImageForUser(label);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            };
            label.addMouseListener(mouseListener);
            if (Game.getPlayers().get(i).getChooseImageAddress() != null) {
                ImageIcon imageIcon = new ImageIcon(Game.getPlayers().get(i).getChooseImageAddress()); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);
                label.setIcon(imageIcon);
            } else if (Game.getPlayers().get(i).getAvatarImageAddress() != null) {
                ImageIcon imageIcon = new ImageIcon(StartGame.class.getResource(Game.getPlayers().get(i).getAvatarImageAddress()));
                Image image = imageIcon.getImage();
                Image newImg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(newImg);
                label.setIcon(imageIcon);
            }
            FileWriter fileWriter = new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(view.adam.adams));
            fileWriter.close();
            panel.add(label);
            loadedCount += visibleCount;
            panel.revalidate();
            panel.repaint();
        }
    }

    private void setImageForUser(JLabel label) throws IOException {
        String regex = "^Rank:\\s+(?<number>[\\d]+)";
        Matcher matcher = Pattern.compile(regex).matcher(label.getText());
        if (matcher.find()) {
            int number = Integer.parseInt(matcher.group("number"));
            User user = Game.getPlayers().get(number - 1);
            if (user.getChooseImageAddress() != null) {
                Game.getCurrentUser().setChooseImageAddress(user.getChooseImageAddress());
                for (adam adam : adam.adams) {
                    if (adam.getUsername().equals(Game.getCurrentUser().getUsername())) {
                        adam.chooseImageAddress=user.getChooseImageAddress();

                    }
                }
            } else if (user.getAvatarImageAddress() != null) {
                Game.getCurrentUser().setAvatarImageAddress(user.getAvatarImageAddress());
                for (adam adam : adam.adams) {
                    if (adam.getUsername().equals(Game.getCurrentUser().getUsername())) {
                        adam.avatarImageAddress=user.getAvatarImageAddress();

                    }
                }

            }
            FileWriter fileWriter = new FileWriter("users.json");
            fileWriter.write(new Gson().toJson(view.adam.adams));
            fileWriter.close();
        }
    }


    public void run() {
        new scoreBoardView();
    }
}
