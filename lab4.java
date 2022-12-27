import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
//https://docs.oracle.com/javase/7/docs/api/java/net/Socket.html
public class b2tc
{
    public static void main(String argv[] )
    {
        MyFrame f = new MyFrame("The Window");
        f.setVisible(true);
        f.setSize(400,700);
    }
}

class MyFrame extends JFrame implements ActionListener
{
    //объявление эл-ов
    JTextField f1;
    JTextField f2;
    JLabel loginLabel;
    JLabel passwordLabel;
    JButton b;
    byte by[] = new byte[256];
    JTextField f1_;
    JTextField f2_;
    JLabel loginLabel_;
    JLabel passwordLabel_;
    JButton b_;

    JLabel afterLogin;
    JTextField money;

    JButton status;
    JButton remove;
    JButton deposit;
    JButton send;
    JPanel blok1;
    JPanel blok2;
    JPanel blok0;
    String serverOut;
    JLabel adress;
    JLabel port;
    JTextField adressFild;
    JTextField portFild;
    JButton conect;
    TextArea outArea;
    String toServer;
    Socket client;
    InputStream is;
    OutputStream os;
    BufferedWriter bw;
    MyFrame(String s)
    {
        super(s);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(3,0,0,0));
        //инициализация эл-ов
        adress = new JLabel("adress: ");
        port = new JLabel("port: ");
        adressFild = new JTextField(20);
        portFild = new JTextField(20);
        conect = new JButton("Connect");

        b = new JButton("Registration");
        f1 = new JTextField(20);
        f2 = new JTextField(20);
        loginLabel = new JLabel("Login");
        passwordLabel = new JLabel("Password");

        b_ = new JButton("login");
        f1_ = new JTextField(20);
        f2_ = new JTextField(20);
        loginLabel_ = new JLabel("Login too");
        passwordLabel_ = new JLabel("Password too");

        afterLogin = new JLabel("login and money: ");
        money = new JTextField(50);

        status = new JButton("status");
        remove = new JButton("remove");
        deposit = new JButton("deposit");
        send = new JButton("send to");

        GridBagConstraints c0 = new GridBagConstraints();
        blok0 = new JPanel();
        blok0.setLayout(new GridBagLayout());

        //добавление эл-ов в GUI с помощью GridBagLayout, расположение эл-ов в котором мы настраиваем GridBagConstraints
        c0.fill = GridBagConstraints.HORIZONTAL;
        c0.weightx = 0.5;
        c0.gridx = 0;
        c0.gridy = 0;
        blok0.add(adress, c0);

        c0.fill = GridBagConstraints.HORIZONTAL;
        c0.weightx = 0.5;
        c0.gridx = 1;
        c0.gridy = 0;
        blok0.add(adressFild, c0);

        c0.fill = GridBagConstraints.HORIZONTAL;
        c0.ipady = 40;      //make this component tall
        c0.weightx = 0.0;
        c0.gridheight = 2;
        c0.gridx = 2;
        c0.gridy = 0;
        blok0.add(conect, c0);

        c0.fill = GridBagConstraints.HORIZONTAL;
        c0.ipady = 0;       //reset to default
        c0.weighty = 0.0;   //request any extra vertical space
        c0.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c0.insets = new Insets(10,0,0,0);  //top padding
        c0.gridx = 0;    //aligned with button 2
        c0.gridheight = 1;
        c0.gridwidth = 1;   //2 columns wide
        c0.gridy = 1;       //third row
        blok0.add(port, c0);

        c0.fill = GridBagConstraints.HORIZONTAL;
        c0.weightx = 0.5;
        c0.gridx = 1;
        c0.gridy = 1;
        blok0.add(portFild, c0);

	//второй блок эл-ов, которые будут появляться после конекта
        GridBagConstraints c = new GridBagConstraints();
        blok1 = new JPanel();
        blok1.setLayout(new GridBagLayout());

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        blok1.add(loginLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        blok1.add(f1, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridheight = 2;
        c.gridx = 2;
        c.gridy = 0;
        blok1.add(b, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 0.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 0;    //aligned with button 2
        c.gridheight = 1;
        c.gridwidth = 1;   //2 columns wide
        c.gridy = 1;       //third row
        blok1.add(passwordLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 1;
        blok1.add(f2, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 2;
        blok1.add(loginLabel_, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 2;
        blok1.add(f1_, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;      //make this component tall
        c.weightx = 0.0;
        c.gridheight = 2;
        c.gridx = 2;
        c.gridy = 2;
        blok1.add(b_, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 0.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 0;    //aligned with button 2
        c.gridheight = 1;
        c.gridwidth = 1;   //2 columns wide
        c.gridy = 3;       //third row
        blok1.add(passwordLabel_, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 3;
        blok1.add(f2_, c);
	
	//третий блок эл-ов
        GridBagConstraints c1 = new GridBagConstraints();
        blok2 = new JPanel();
        blok2.setLayout(new GridBagLayout());

        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 0.5;
        c1.gridx = 1;
        c1.gridy = 0;
        blok2.add(afterLogin, c1);

        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 0.5;
        c1.gridx = 2;
        c1.gridy = 0;
        blok2.add(money, c1);

        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 0.5;
        c1.gridx = 0;
        c1.gridy = 1;
        blok2.add(status, c1);

        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 0.5;
        c1.gridx = 1;
        c1.gridy = 1;
        blok2.add(remove, c1);

        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 0.5;
        c1.gridx = 2;
        c1.gridy = 1;
        blok2.add(deposit, c1);

        c1.fill = GridBagConstraints.HORIZONTAL;
        c1.weightx = 0.5;
        c1.gridx = 3;
        c1.gridy = 1;
        blok2.add(send, c1);

        outArea = new TextArea();
        
        c1.fill = GridBagConstraints.HORIZONTAL;
        //c1.ipady = 20;      //make this component tall
        c1.weightx = 0.0;
        c1.gridheight = 2;
        c1.gridwidth = 4;
        c1.gridx = 0;
        c1.gridy = 3;
        blok2.add(outArea, c1);
	
	//добавляем блоки в нашу панель
        contentPane.add(blok0);
        contentPane.add(blok1);
        contentPane.add(blok2);

	//скрываем второй и третий блок
        blok1.setVisible(false);
        blok2.setVisible(false);

        //название команд кнопок
        conect.setActionCommand("conect");
        b.setActionCommand("create");
        b_.setActionCommand("open");
        status.setActionCommand("view");
        remove.setActionCommand("get");
        deposit.setActionCommand("put");
        send.setActionCommand("send");
	
        //вызов acnionPerfomend по нажатию кнопок
        conect.addActionListener(this);
        b.addActionListener(this);
        b_.addActionListener(this);
        status.addActionListener(this);
        remove.addActionListener(this);
        deposit.addActionListener(this);
        send.addActionListener(this);
	
	//листнер окна. шаблон.
        WindowListener w1 = new WindowAdapter()
        {
            public void windowActivated(WindowEvent e)
            {
                setBackground(Color.lightGray);
            }
            public void windowClosed(WindowEvent e)
            {
                System.exit(0);
            }
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
            public void windowDeactivated(WindowEvent e)
            {
                setBackground(Color.lightGray);
            }
        };//attention semicolon!

        addWindowListener(w1);
    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();//узнаем какая кнопка нажата
        switch (s) {
            case "conect" -> {
                try {
		    //соединение сокета и создание потоков
                    client = new Socket (adressFild.getText(),Integer.parseInt( portFild.getText()));
                    is = client.getInputStream();
                    os = client.getOutputStream();
                    bw = new BufferedWriter(new OutputStreamWriter(os));

                    blok0.setVisible(false);
                    blok1.setVisible(true);
                    blok2.setVisible(true);

                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            
            case "create" -> {
                //добавление пользователя на сервер
                toServer = "create " + f1.getText() + " " + f2.getText() + "\n";

                try {
                    bw.write(toServer);
                    bw.flush();
                    int counter = is.read(by);
                    serverOut = new String(by, 0, counter);
                    serverOut = serverOut + "\n";
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                outArea.append(serverOut);

            }
            case "open" -> {
                //активизация текущего пользователя
                toServer = "open " + f1_.getText() + " " + f2_.getText() + "\n";
                try {
                    bw.write(toServer);
                    bw.flush();
                    int counter = is.read(by);
                    serverOut = new String(by, 0, counter);
                    serverOut = serverOut + "\n";
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                outArea.append(serverOut);
                blok2.setVisible(true);
            }
            case "view" -> {
                //запрос на получение текущей суммы пользователя
                toServer = "view\n";
                String serverOut;
                try {
                    bw.write(toServer);
                    bw.flush();
                    int counter = is.read(by);
                    serverOut = new String(by, 0, counter);
                    serverOut = serverOut + "\n";
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                outArea.append(serverOut);
            }
            case "get" -> {
                //запрос на снятие денег с текущего пользователя
                toServer = "get " + afterLogin.getText() + "\n";
                try {
                    bw.write(toServer);
                    bw.flush();
                    int counter = is.read(by);
                    serverOut = new String(by, 0, counter);
                    serverOut = serverOut + "\n";
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                outArea.append(serverOut);
            }
            case "put" -> {
                //запрос на пополнение счёта текущего пользователя
                toServer = "put " + afterLogin.getText() + "\n";

                try {
                    bw.write(toServer);
                    bw.flush();
                    int counter = is.read(by);
                    serverOut = new String(by, 0, counter);
                    serverOut = serverOut + "\n";
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                outArea.append(serverOut);
            }
            case "send" -> {
                //снятие денег с текущего пользователя и пополнение счёта указанного пользователя
                String tmpStr = afterLogin.getText();
                String[] words = tmpStr.split(" ");
                toServer = "send " + words[0] + " " + words[1] + "\n";

                try {
                    bw.write(toServer);
                    bw.flush();
                    int counter = is.read(by);
                    serverOut = new String(by, 0, counter);
                    serverOut = serverOut + "\n";
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                outArea.append(serverOut);
            }
        }
        //здесь отправляется строка toServer на сервер для обработки
    }
}
