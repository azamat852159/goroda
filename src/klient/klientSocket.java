package klient;


import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class klientSocket {

    public static void main(String[] args) {
        connectToServer c = new connectToServer();
        c.createConnect();
        gui g = new gui();
    }
}

class connectToServer {
    private static Socket socket;
    private static BufferedReader bufferedReader;
    private static PrintWriter printWriter;

    public static BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public static PrintWriter getPrintWriter() {
        return printWriter;
    }

    void createConnect() {
        socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("localhost", 8189), 2000);
            System.out.println("соединение прошло успешно");
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Не удалось подключиться к серверу");
        }
    }



    public Socket getSocket() {
        return socket;
    }
}


class gui extends JFrame {
    private JPanel jPanel;
    private JButton jButton;
    private JTextField jTextField;
    private JLabel jLabel;

    {
        this.jPanel = new JPanel();
        this.jButton = new JButton("submit");
        this.jButton.setBounds(390,
                330,
                80,
                20);
        this.jTextField = new JTextField();
        jTextField.setBounds(10, 330, 360, 20);
        this.jLabel = new JLabel("<html>First Line<br/>Second Line</html>");
        jLabel.setBounds(10, 10, 360, 300);
        jLabel.getVerticalTextPosition();
        jLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        this.jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder stringBuilder = new StringBuilder();
                String str= jLabel.getText();

                connectToServer.getPrintWriter().print(str);

                str = (String) str.subSequence(0,str.length()-7);
                stringBuilder.append(jTextField.getText());
                jLabel.setText(str + "<br/>"+ stringBuilder.toString() + "</html>" );
                stringBuilder.delete(0,stringBuilder.length());
                jTextField.setText("");


            }
        });
    }

     gui() {                                       //создалось окно
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setTitle("the Game");
        this.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 500) / 2, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 400) / 2);
        this.add(jPanel);
        jPanel.setLayout(null);
        jPanel.add(jButton);
        jPanel.add(jTextField);
        jPanel.add(jLabel);

    }

   JLabel getjLabel() {
        return jLabel;
    }

    JTextField getjTextField() {
        return jTextField;
    }

    JButton getjButton() {
        return jButton;
    }
}



//class checkThisField implements ActionListener{
//
//    private gui gui;
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//       if( gui.getjTextField() != null){
//           getText();
//       }
//    }
//
//    private String getText(){
//        return gui.getjTextField().getText();
//    }
//
//    public checkThisField(klient.gui gui) {
//        this.gui = gui;
//    }
//
//}
//
//class bridge{
//    public checkThisField checkThisField;
//    public gui gui;
//
//    public bridge(klient.checkThisField checkThisField, klient.gui gui) {
//        this.checkThisField = checkThisField;
//        this.gui = gui;
//    }
//
//    private void setListenerToTheButtom(){
//        gui.getjButton().addActionListener(checkThisField);
//    }
//}



