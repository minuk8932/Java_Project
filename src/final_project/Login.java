package final_project;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;

import java.util.*;
import java.io.*;

class LoginUI extends JFrame {
   private JPanel contentPane;
   
   private JLabel idLabel;
   private JTextField txtId;
   
   private JLabel pwLabel;
   private JPasswordField txtPw;
   
   private JButton btnLogin;
   private JButton btnJoin;
   
   public static final int FRAME_WIDTH = 1150;
   public static final int FRAME_HEIGHT = 650;
   public static final int X_LOCATION = 200;
   public static final int Y_LOCATION = 100;
   
   static String get_name;
   static String get_num;
   
   
   
   public LoginUI() {
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setTitle("Snutway");
      
      setBounds(550, 250, 274, 230);
      
      contentPane = new JPanel();
      
      setContentPane(contentPane);
      
      contentPane.setLayout(null);
      
      JPanel panel = new JPanel();
      
      panel.setBounds(0, 0, 258, 492);
      contentPane.add(panel);
      panel.setLayout(null);
      
      //id
      idLabel = new JLabel();
      idLabel.setText("아이디");
      idLabel.setFont(new Font("굴림체", Font.BOLD, 15));
      idLabel.setBounds(32, 18, 56, 21);
      panel.add(idLabel);
      
      txtId = new JTextField();
      txtId.setBounds(111, 18, 116, 21);
      panel.add(txtId);
      txtId.setColumns(10);
      
      //비밀번호
      pwLabel = new JLabel();
      pwLabel.setText("비밀번호");
      pwLabel.setFont(new Font("굴림체", Font.BOLD, 15));
      pwLabel.setBounds(32, 62, 67, 21);
      panel.add(pwLabel);
      
      txtPw = new JPasswordField();
      txtPw.setBounds(111, 62, 116, 21);
      panel.add(txtPw);
         
      //로그인 버튼
      btnLogin = new JButton("로그인");
      btnLogin.setBounds(32, 100, 195, 23);
      panel.add(btnLogin);
      
      btnLogin.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               FileReader fe = new FileReader("member.txt");
                  BufferedReader br = new BufferedReader(fe);
                  
                  String loginCheck;
                               
                  String idBuf = null;
                  String pwBuf = null;
                  String nameBuf = null;
                  String numBuf = null;
                  
                  while((loginCheck = br.readLine()) != null) {
                     
                     StringTokenizer loginCheckST = new StringTokenizer(loginCheck);
                     
                    String idPart = loginCheckST.nextToken();
                    String pwPart = loginCheckST.nextToken();
                    String namePart = loginCheckST.nextToken();
                    String numPart = loginCheckST.nextToken();
                    
                    idBuf += (" ");
                    idBuf += idPart;
                    
                    
                    pwBuf += (" ");
                    pwBuf += pwPart;
                    
                    nameBuf += (" ");
                    nameBuf += namePart;
                    
                    numBuf += (" ");
                    numBuf += numPart;
                    
                    

                     }
                  
                  String[] idArr = (idBuf.split(" "));
                  String[] pwArr = (pwBuf.split(" "));
                  String[] nameArr = (nameBuf.split(" "));
                  String[] numArr = (numBuf.split(" "));
                  
               
                  
                  int mp;
                  
                  if(Arrays.asList(idArr).contains(txtId.getText()) && (txtId.getText().length() > 4 && txtId.getText().length() < 16)) {
                     mp = idArr.length;
                     
                     if(pwArr.length == mp && Arrays.asList(pwArr).contains(txtPw.getText())) {
                        JOptionPane.showMessageDialog(null, "로그인 완료", "Snutway", JOptionPane.PLAIN_MESSAGE);
                        
                        SnutWayClient swClient = new SnutWayClient();
                        
                        swClient.setVisible(true);
                        
                        swClient.setTitle("SnutWay");
                        swClient.setSize(FRAME_WIDTH, FRAME_HEIGHT);
                        swClient.setLocation(X_LOCATION, Y_LOCATION);
                        
                        swClient.addWindowListener(new WindowAdapter() {
                           public void windowClosing(WindowEvent wEvent){
                              swClient.stop();
                           }
                        });
                        
                        swClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        swClient.setVisible(true);
                        
                        
                        
                     }else {
                        JOptionPane.showMessageDialog(null, "비밀번호가 틀립니다", "Snutway", JOptionPane.WARNING_MESSAGE);
                     }
                  }else {
                     JOptionPane.showMessageDialog(null, "존재하는 아이디가 없습니다", "Snutway", JOptionPane.WARNING_MESSAGE);
                  }
                  

                  
                  br.close();
            } catch(Exception le) {
               
            }
         }
      });
      
      //회원가입 버튼
      btnJoin = new JButton("회원가입");
      btnJoin.setBounds(32, 140, 195, 23);
      panel.add(btnJoin);
      
      // 회원가입 버튼 이벤트 처리
      btnJoin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               dispose();
                JoinUI join = new JoinUI();
                join.setVisible(true);
                
            }
        });
      
   }   
   
}


public class Login{
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               LoginUI frame = new LoginUI();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }
   
}