package final_project;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.synth.SynthSplitPaneUI;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.*;

class JoinUI extends JFrame implements ActionListener {
   private JPanel contentPane;
   private JLabel joinLabel;
   
   private JLabel idLabel;
   private JTextField txtId;
   private JLabel idNote;
   private JButton idCheck;
   
   private JLabel pwLabel;
   private JPasswordField txtPw;
   private JLabel pwNote;
   
   private JLabel nameLabel;
   private JTextField txtName;
   
   private JLabel hpLabel;
   private JTextField txtHp;
   private JLabel hpNote;
   
   private boolean idUse = false; //사용 가능한 아이디일 때 true
   
   private final ButtonGroup buttonGroup = new ButtonGroup();
   
   
   public JoinUI() {
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setTitle("Snutway");
      
      setBounds(550, 100, 305, 460);
      contentPane = new JPanel();
      
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      JPanel panel = new JPanel();
      panel.setBounds(0, 0, 289, 535);
      contentPane.add(panel);
      panel.setLayout(null);
      //panel.setBackground(new Color(255,255,255));
      
      joinLabel = new JLabel();
      joinLabel.setFont(new Font("굴림체", Font.BOLD, 18));
      joinLabel.setText("회원가입");
      joinLabel.setBounds(105, 21, 80, 31);
      joinLabel.setBorder(null);
      panel.add(joinLabel);
      
      //아이디 
      idLabel = new JLabel();
      idLabel.setText("아이디");
      idLabel.setBounds(40, 85, 50, 21);
      panel.add(idLabel);
      
      txtId = new JTextField();
      txtId.setBounds(110, 85, 125, 21);
      panel.add(txtId);
      txtId.setColumns(10);
      
      idCheck = new JButton();
      idCheck.setText("ID 중복 확인");
      idCheck.setBounds(40, 120, 200, 21);
      panel.add(idCheck);
      
      idNote = new JLabel();
      idNote.setFont(new Font("굴림체", Font.BOLD, 8));
      idNote.setText("아이디는 4자 이상 10자 미만으로 영문과 숫자만 사용 가능");
      //idNote.setText("사용 가능 한 ID입니다");
      //idNote.setText("이미 존재하는 ID입니다");
      idNote.setBounds(40, 145, 250, 21);
      idNote.setForeground(new Color(255,50,50));
      panel.add(idNote);
      
      //아이디 중복 확인 버튼 이벤트 처리
      idCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               try {
           
                  //File memberFile = new File("member.txt");
                  FileReader fe = new FileReader("member.txt");
                  BufferedReader br = new BufferedReader(fe);
                  
                  //id 정규식(숫자 & 영문 입력 만 가능)
                  Pattern idp = Pattern.compile("^[0-9a-zA-Z]*$");
                  Matcher idm = idp.matcher(txtId.getText());
                  
                  String idCheckString;
                  
                  String idSave = null;
    
                     while((idCheckString = br.readLine()) != null) {
                     
                        StringTokenizer idCheckST = new StringTokenizer(idCheckString);
                     
                        String idPart = idCheckST.nextToken();
                        idSave += " ";
                        idSave += idPart;
                        }
                  
                     String[] idArr = (idSave.split(" "));
                  
                        if(Arrays.asList(idArr).contains(txtId.getText())) {
                           idNote.setText("이미 존재하는 ID입니다");
                           idUse = false;
                        }else {
                           if(idm.find() == true && (txtId.getText().length() > 4 && txtId.getText().length() < 16)){
                              idNote.setText("사용 가능한 ID입니다");
                              idUse = true;
                           }else {
                              idNote.setText("아이디 형식이 맞지 않습니다");
                              idUse = false;
                           }
                        }
                  //}
                  br.close();
               }catch(Exception ide) {
                  
                  Pattern idp = Pattern.compile("^[0-9a-zA-Z]*$");
                  Matcher idm = idp.matcher(txtId.getText());
                  
                  if(idm.find() == true && (txtId.getText().length() > 4 && txtId.getText().length() < 16)) {
                     idNote.setText("사용 가능한 ID 입니다");
                    idUse = true;
                  }else {
                    idNote.setText("아이디 형식이 맞지 않습니다");
                    idUse = false;
                 }
          
               }
                
            }
        });
      
      
      //비밀번호
      pwLabel = new JLabel();
      pwLabel.setText("비밀번호");
      pwLabel.setBounds(40, 185, 55, 21);
      panel.add(pwLabel);
      
      txtPw = new JPasswordField();
      txtPw.setColumns(10);
      txtPw.setBounds(110, 185, 125, 21);
      panel.add(txtPw);
      
      pwNote = new JLabel();
      pwNote.setFont(new Font("굴림체", Font.BOLD, 8));
      pwNote.setText("비밀번호는 5자 이상 16자 미만의 비밀번호 사용 가능");
      pwNote.setBounds(40, 200, 250, 21);
      pwNote.setForeground(new Color(255,50,50));
      panel.add(pwNote);
      
      
      //성명
      nameLabel = new JLabel();
      nameLabel.setText("성명");
      nameLabel.setBounds(40, 240, 55, 21);
      panel.add(nameLabel);
      
      txtName = new JTextField();
      txtName.setColumns(10);
      txtName.setBounds(110, 240, 125, 21);
      panel.add(txtName);
      
      //전화번호
      hpLabel = new JLabel();
      hpLabel.setText("전화번호");
      hpLabel.setBounds(40, 290, 55, 21);
      panel.add(hpLabel);
      
      txtHp = new JTextField();
      txtHp.setColumns(10);
      txtHp.setBounds(110, 290, 125, 21);
      panel.add(txtHp);
      
      hpNote = new JLabel();
      hpNote.setFont(new Font("굴림체", Font.BOLD, 8));
      hpNote.setText("전화번호는 '-' 포함없이 적어주세요");
      hpNote.setBounds(40, 315, 250, 21);
      hpNote.setForeground(new Color(255,50,50));
      panel.add(hpNote);
      
      //가입 버튼
      JButton btnJoin = new JButton("가입");
      btnJoin.setBounds(50, 350, 73, 23);
      panel.add(btnJoin);
      btnJoin.addActionListener(this);
      
      //취소 버튼
      JButton btnCancel = new JButton("취소");
      btnCancel.setBounds(155, 350, 73, 23);
      panel.add(btnCancel);
      
      //취소 버튼 이벤트 처리
      btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               dispose();
                LoginUI login = new LoginUI();
                login.setVisible(true);
                
            }
        });
   }
   
   //가입 버튼 이벤트 처리
   public void actionPerformed(ActionEvent ae) {
      try {
         //txt file 생성
         FileWriter fw = new FileWriter("member.txt", true);
         BufferedWriter bw = new BufferedWriter(fw);
         
         //id 정규식(숫자 & 영문 입력 만 가능)
         Pattern idp = Pattern.compile("^[0-9a-zA-Z]*$");
         Matcher idm = idp.matcher(txtId.getText()); 
         
         //pw 정규식(숫자 & 영문 & 특수문자)
         Pattern pwp = Pattern.compile("^[a-zA-Z0-9~!@#$%^&*()]");
         Matcher pwm = pwp.matcher(txtPw.getText());
         
         //성명 부분 정규식(한글만 가능 2~20자)
         Pattern namep = Pattern.compile("[가-힣]{2,20}"); 
         Matcher namem = namep.matcher(txtName.getText());
         
         //전화번호 정규식
         Pattern hpp = Pattern.compile("[0-9]{2,3}[0-9]{3,4}[0-9]{4}");
         Matcher hpm = hpp.matcher(txtHp.getText());                       
         
         //아이디, 패스워드, 성명, 전화번호가 정규식에 맞고, 아이디 길이가 4~16, 패스워드 길이가 5~16일떄 write
         if(pwm.find() && namem.find() && hpm.find() == true && (idUse == true) && (txtPw.getText().length() > 5 && txtPw.getText().length() < 16)){
            
            bw.write(txtId.getText() + " ");
            bw.write(txtPw.getText() + " ");
            bw.write(txtName.getText() + " ");
            bw.write(txtHp.getText() + "\r\n");
            
            
            //조건에 만족하면 회원가입 메세지 출력 후 로그인 화면으로 이동
            JOptionPane.showMessageDialog(null, "회원가입 완료", "Snutway", JOptionPane.PLAIN_MESSAGE);
            dispose();
            LoginUI login = new LoginUI();
            login.setVisible(true);
         }else if(idUse == false){
            JOptionPane.showMessageDialog(null, "아이디 중복 확인 하세요", "Snutway", JOptionPane.WARNING_MESSAGE);
            
         }else{
            //조건에 만족하지 않을 시 메세지
            JOptionPane.showMessageDialog(null, "회원 가입 정보가 올바르지 않습니다", "Snutway", JOptionPane.WARNING_MESSAGE);
            
         }
         
         bw.close();
         
      }
      catch(Exception e) {
         
      }
   }
   
}

public class Join{
   

   public static void main(String[] args) {
	   
  }
}

