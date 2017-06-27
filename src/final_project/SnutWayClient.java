package final_project;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;


public class SnutWayClient extends JFrame implements ActionListener {
   private static final long serialVersionUID = 42L;
   
   public static final String IPAddress = "172.30.1.5";
   
   private ButtonGroup mainBtnGrp;
   private ButtonGroup breadBtnGrp;
   private ButtonGroup ingredientBtnGrp;
   private ButtonGroup sauceBtnGrp;
   
   private JOptionPane jopPopup;
   
   // main
   private JToggleButton jtbChicken;
   private JToggleButton jtbBacon;
   private JToggleButton jtbSpicy;
   private JToggleButton jtbSteakCheese;
   private JToggleButton jtbChickenBacon;
   private JToggleButton jtbRoastChicken;
   private JToggleButton jtbRoastBeef;
   private JToggleButton jtbBaconAvocado;
   
   //bread
   private JToggleButton jtbHoneyOat;
   private JToggleButton jtbHeartyItalian;
   private JToggleButton jtbWheat;
   private JToggleButton jtbParmesanOregano;
   private JToggleButton jtbWhite;
   private JToggleButton jtbFlatBread;
   
   //sauce, multi selection
   private JToggleButton jtbMayonnaise;
   private JToggleButton jtbSweetOnion;
   private JToggleButton jtbHoneyMustard;
   private JToggleButton jtbSweetChilli;
   private JToggleButton jtbBBQ;
   private JToggleButton jtbHotChilli;
   
   //ingredient
   private JToggleButton jtbLettuce;
   private JToggleButton jtbCumcumber;
   private JToggleButton jtbPepper;
   private JToggleButton jtbOnion;
   private JToggleButton jtbPickle;
   private JToggleButton jtbCheese;
   
   //side menu, multi selection
   private JToggleButton jtbCookie;
   private JToggleButton jtbCoffee;
   private JToggleButton jtbChip;
   private JToggleButton jtbDrink;
   
   
   private JButton clickBtn;
   private JButton popupBtn;
   
   // divide sector
   private JPanel jpInform;
   private JPanel jpHeaderImg;
   private JPanel jpOrder;
   
   private JPanel jpMain;
   private JPanel jpBread;
   private JPanel jpSauce;
   private JPanel jpIngredient;
   private JPanel jpSide;
   private JPanel jpBtn;
   private JPanel jpTotal;
   
   
   // for input member data
//   private JTextArea jtaName;
   private JTextArea jtaAddress;
   private JLabel jlAddress;
//   private JTextArea jtaPhone;
   
   private JLabel label;
   
   private Socket socket;
   private DataInputStream dataIn;
   private DataOutputStream dataOut;
   
   private static SnutWayClient swClient;
   
   // radio button & check box text
   private String mainText;
   private String breadText;
   private String ingredientText;
   private String sauceText1;
   private String sauceText2;
   private String sauceText3;
   private String sauceText4;
   private String sauceText5;
   private String sauceText6;
   private String sideText1;
   private String sideText2;
   private String sideText3;
   private String sideText4;
   
   // for input BkgImg
   private ImageIcon BackgroundImg;
   
   // icons
   private ImageIcon iconMain1;
   private ImageIcon iconMain2;
   private ImageIcon iconMain3;
   private ImageIcon iconMain4;
   private ImageIcon iconMain5;
   private ImageIcon iconMain6;
   private ImageIcon iconMain7;
   private ImageIcon iconMain8;
   
   private ImageIcon iconBread1;
   private ImageIcon iconBread2;
   private ImageIcon iconBread3;
   private ImageIcon iconBread4;
   private ImageIcon iconBread5;
   private ImageIcon iconBread6;
   
   private ImageIcon iconSauce1;
   private ImageIcon iconSauce2;
   private ImageIcon iconSauce3;
   private ImageIcon iconSauce4;
   private ImageIcon iconSauce5;
   private ImageIcon iconSauce6;
   
   private ImageIcon iconIngredient1;
   private ImageIcon iconIngredient2;
   private ImageIcon iconIngredient3;
   private ImageIcon iconIngredient4;
   private ImageIcon iconIngredient5;
   private ImageIcon iconIngredient6;
   
   private ImageIcon iconSide1;
   private ImageIcon iconSide2;
   private ImageIcon iconSide3;
   private ImageIcon iconSide4;
   
   public SnutWayClient(){
      mainBtnGrp = new ButtonGroup();
      breadBtnGrp = new ButtonGroup();
      ingredientBtnGrp = new ButtonGroup();
      
      // main icon
      iconMain1 = new ImageIcon("chicken.png");
      iconMain2 = new ImageIcon("bacon.png");
      iconMain3 = new ImageIcon("spicy.png");
      iconMain4 = new ImageIcon("steak_cheese.png");
      iconMain5 = new ImageIcon("chicken_bacon.png");
      iconMain6 = new ImageIcon("roast_chicken.png");
      iconMain7 = new ImageIcon("roast_beef.png");
      iconMain8 = new ImageIcon("bacon_avocado.png");
      
      // bread icon
      iconBread1 = new ImageIcon("honey_oat.png");
      iconBread2 = new ImageIcon("hearty_italian.png");
      iconBread3 = new ImageIcon("wheat.png");
      iconBread4 = new ImageIcon("parmesan_oregano.png");
      iconBread5 = new ImageIcon("white.png");
      iconBread6 = new ImageIcon("flatbread.png");
      
      // suace icon
      iconSauce1 = new ImageIcon("mayonnaise.png");
      iconSauce2 = new ImageIcon("sweet_onion.png");
      iconSauce3 = new ImageIcon("honey_mustard.png");
      iconSauce4 = new ImageIcon("sweet_chilli.png");
      iconSauce5 = new ImageIcon("bbq.png");
      iconSauce6 = new ImageIcon("hot_chilli.png");
      
      // sauce icon
      iconIngredient1 = new ImageIcon("lettuce.png");
      iconIngredient2 = new ImageIcon("cucumber.png");
      iconIngredient3 = new ImageIcon("pepper.png");
      iconIngredient4 = new ImageIcon("onion.png");
      iconIngredient5 = new ImageIcon("pickle.png");
      iconIngredient6 = new ImageIcon("cheese.png");
      
      // side icon
      iconSide1 = new ImageIcon("cookie.png");
      iconSide2 = new ImageIcon("coffee.png");
      iconSide3 = new ImageIcon("chip.png");
      iconSide4 = new ImageIcon("drink.png");
      
      jtbChicken = new JToggleButton("치킨", iconMain1);
      jtbBacon = new JToggleButton("베이컨", iconMain2);
      jtbSpicy = new JToggleButton("이탈리안스파이시", iconMain3);
      jtbSteakCheese = new JToggleButton("스테이크&치즈", iconMain4);
      jtbChickenBacon = new JToggleButton("치킨베이컨", iconMain5);
      jtbRoastChicken = new JToggleButton("로스트치킨", iconMain6);
      jtbRoastBeef = new JToggleButton("로스트비프", iconMain7);
      jtbBaconAvocado = new JToggleButton("베이컨아보카도", iconMain8);
      
      jtbHoneyOat = new JToggleButton("허니오트", iconBread1);
      jtbHeartyItalian = new JToggleButton("하티이탈리안", iconBread2);
      jtbWheat = new JToggleButton("위트", iconBread3);
      jtbParmesanOregano = new JToggleButton("파마산오레가노", iconBread4);
      jtbWhite = new JToggleButton("화이트", iconBread5);
      jtbFlatBread = new JToggleButton("플랫브레드", iconBread6);
      
      jtbMayonnaise = new JToggleButton("마요네즈", iconSauce1);
      jtbSweetOnion = new JToggleButton("스위트어니언", iconSauce2);
      jtbHoneyMustard = new JToggleButton("허니머스타드", iconSauce3);
      jtbSweetChilli = new JToggleButton("스위트칠리", iconSauce4);
      jtbBBQ = new JToggleButton("바베큐", iconSauce5);
      jtbHotChilli = new JToggleButton("핫칠리", iconSauce6);
      
      jtbLettuce = new JToggleButton("양상추", iconIngredient1);
      jtbCumcumber = new JToggleButton("오이", iconIngredient2);
      jtbPepper = new JToggleButton("피망", iconIngredient3);
      jtbOnion = new JToggleButton("양파", iconIngredient4);
      jtbPickle = new JToggleButton("피클", iconIngredient5);
      jtbCheese = new JToggleButton("치즈", iconIngredient6);
      
      jtbCookie = new JToggleButton("쿠키", iconSide1);
      jtbCoffee = new JToggleButton("커피", iconSide2);
      jtbChip = new JToggleButton("칩", iconSide3);
      jtbDrink = new JToggleButton("음료수", iconSide4);
      
      //grouping
      mainBtnGrp.add(jtbChicken);
      mainBtnGrp.add(jtbBacon);
      mainBtnGrp.add(jtbSpicy);
      mainBtnGrp.add(jtbSteakCheese);
      mainBtnGrp.add(jtbChickenBacon);
      mainBtnGrp.add(jtbRoastChicken);
      mainBtnGrp.add(jtbRoastBeef);
      mainBtnGrp.add(jtbBaconAvocado);
      
      breadBtnGrp.add(jtbHoneyOat);
      breadBtnGrp.add(jtbHeartyItalian);
      breadBtnGrp.add(jtbWheat);
      breadBtnGrp.add(jtbParmesanOregano);
      breadBtnGrp.add(jtbWhite);
      breadBtnGrp.add(jtbFlatBread);
      
      ingredientBtnGrp.add(jtbLettuce);
      ingredientBtnGrp.add(jtbCumcumber);
      ingredientBtnGrp.add(jtbPepper);
      ingredientBtnGrp.add(jtbOnion);
      ingredientBtnGrp.add(jtbPickle);
      ingredientBtnGrp.add(jtbCheese);
      
      jtaAddress = new JTextArea(1, 26);
      jtaAddress.setToolTipText("배달 주소");
      jtaAddress.setFont(new Font("Serif", Font.BOLD, 20));
      jlAddress = new JLabel("배달 주소 : ");
      
      jpMain = new JPanel();
      jpBread = new JPanel();
      jpSauce = new JPanel();
      jpIngredient = new JPanel();
      jpSide = new JPanel();
      jpBtn = new JPanel();
      
      jpInform = new JPanel();
      jpHeaderImg = new JPanel();
      jpOrder = new JPanel();
      
      //input bkgImg
      BackgroundImg = new ImageIcon("logo.png");
      
      jpTotal = new JPanel(){                     //paintComponent overriding
         
         public void paintComponent(Graphics bkgImg){
            super.paintComponent(bkgImg);
            
            // frame 크기에 맞게 이미지 크기 조절을 위해 getSize() 선언.
            // set background img size
            Dimension dSize = getSize();            
            bkgImg.drawImage(BackgroundImg.getImage(), 0, 0, dSize.width, dSize.height, this);
            setOpaque(false);
            
         }
      };
      
        
      clickBtn = new JButton();
      
      label = new JLabel();
      
      jpInform.setLayout(new BorderLayout(5, 3));
      jpHeaderImg.setLayout(new BorderLayout(5,3));   
      
      jpOrder.add(jlAddress);
      jpOrder.add(jtaAddress);
      
      jpHeaderImg.setBackground(new Color(255, 243, 198, 0));
      jpInform.add(jpOrder, BorderLayout.WEST);
      jpInform.add(jpHeaderImg, BorderLayout.EAST);
      add(jpInform, BorderLayout.NORTH);
      
      
      jpTotal.setLayout(new FlowLayout());
      
      
      jpMain.setLayout(new GridLayout(4, 2, 5, 5));    // (row, col, ,horizontal, vertical)
      jpMain.setBackground(new Color(255, 0, 0, 0));      // rgb color (int), set unvisible
      jpTotal.add(jpMain);
      
      jpBread.setLayout(new GridLayout(3, 2, 5, 5));
      jpBread.setBackground(new Color(255, 0, 0, 0));      
      jpTotal.add(jpBread);
      
      jpSauce.setLayout(new GridLayout(2, 3, 5, 5));
      jpSauce.setBackground(new Color(255, 0, 0, 0));      
      jpTotal.add(jpSauce);
      
      jpIngredient.setLayout(new GridLayout(2, 3, 5, 5));
      jpIngredient.setBackground(new Color(255, 0, 0, 0));      
      jpTotal.add(jpIngredient);
      
      jpSide.setLayout(new GridLayout(1, 4, 5, 5));
      jpSide.setBackground(new Color(255, 0, 0, 0));      
      jpTotal.add(jpSide);
      
      // add menu
      jpMain.add(jtbChicken);
      jpMain.add(jtbBacon);
      jpMain.add(jtbSpicy);
      jpMain.add(jtbSteakCheese);
      jpMain.add(jtbChickenBacon);
      jpMain.add(jtbRoastChicken);
      jpMain.add(jtbRoastBeef);
      jpMain.add(jtbBaconAvocado);
      
      jpBread.add(jtbHoneyOat);
      jpBread.add(jtbHeartyItalian);
      jpBread.add(jtbWheat);
      jpBread.add(jtbParmesanOregano);
      jpBread.add(jtbWhite);
      jpBread.add(jtbFlatBread);
      
      jpSauce.add(jtbMayonnaise);
      jpSauce.add(jtbSweetOnion);
      jpSauce.add(jtbHoneyMustard);
      jpSauce.add(jtbSweetChilli);
      jpSauce.add(jtbBBQ);
      jpSauce.add(jtbHotChilli);
      
      jpIngredient.add(jtbLettuce);
      jpIngredient.add(jtbCumcumber);
      jpIngredient.add(jtbPepper);
      jpIngredient.add(jtbOnion);
      jpIngredient.add(jtbPickle);
      jpIngredient.add(jtbCheese);
      
      jpSide.add(jtbCookie);
      jpSide.add(jtbCoffee);
      jpSide.add(jtbChip);
      jpSide.add(jtbDrink);
      
      add(jpTotal, BorderLayout.CENTER);
      
      clickBtn.setText("주문 완료");
      
      
      clickBtn.addActionListener(this);
      jpBtn.add(clickBtn, BorderLayout.CENTER);
      
      label.setBackground(new Color(255, 0, 0, 0));
      
      jpBtn.add(label, BorderLayout.SOUTH);
      add(jpBtn, BorderLayout.SOUTH);
      
      jopPopup = new JOptionPane();
      
      
      try{
         socket = new Socket(IPAddress, 8000);
         
         dataIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
         dataOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
         
         
      }
      catch(IOException ioe) {
         stop();
      }
      
      
      
      
   }
   
   @Override
   public void actionPerformed(ActionEvent acEvent) {       // event 발생
      
      //main
      if(jtbChicken.isSelected()){
         mainText = jtbChicken.getText();
      }
      
      else if(jtbBacon.isSelected()){
         mainText = jtbBacon.getText();
      }
      
      else if(jtbSteakCheese.isSelected()){
         mainText = jtbSteakCheese.getText();
      }
      else if(jtbSpicy.isSelected()){
         mainText = jtbSpicy.getText();
      }
      else if(jtbChickenBacon.isSelected()){
         mainText = jtbChickenBacon.getText();
      }
      
      else if(jtbRoastChicken.isSelected()){
         mainText = jtbRoastChicken.getText();
      }
      
      else if(jtbRoastBeef.isSelected()){
         mainText = jtbRoastBeef.getText();
      }
      else if(jtbBaconAvocado.isSelected()){
         mainText = jtbBaconAvocado.getText();
      }
      
      // 잘못된 입력은 오류메세지를 띄우므로 else 설정은 하지 않음.
      
      
      //bread
      if(jtbHoneyOat.isSelected()){
         breadText = jtbHoneyOat.getText();
      }
      
      else if(jtbHeartyItalian.isSelected()){
         breadText = jtbHeartyItalian.getText();
      }
      
      else if(jtbWheat.isSelected()){
         breadText = jtbWheat.getText();
      }
      
      else if(jtbParmesanOregano.isSelected()){
         breadText = jtbParmesanOregano.getText();
      }
      
      else if(jtbWhite.isSelected()){
         breadText = jtbWhite.getText();
      }
      
      else if(jtbFlatBread.isSelected()){
         breadText = jtbFlatBread.getText();
      }
      
      //sauce
      if(jtbMayonnaise.isSelected()){
         sauceText1 = "(" + jtbMayonnaise.getText() + " O)";
      }
      else{
         sauceText1 = "(X)";
      }
      
      if(jtbSweetOnion.isSelected()){
         sauceText2 = "(" + jtbSweetOnion.getText() + " O)";
      }
      else{
         sauceText2 = "(X)";
      }
      
      if(jtbHoneyMustard.isSelected()){
         sauceText3 = "(" + jtbHoneyMustard.getText() + " O)";
      }
      else{
         sauceText3 = "(X)";
      }
      
      if(jtbSweetChilli.isSelected()){
         sauceText4 = "(" + jtbSweetChilli.getText() + " O)";
      }
      else{
         sauceText4 = "(X)";
      }
      
      if (jtbBBQ.isSelected()){
         sauceText5 = "(" + jtbBBQ.getText() + " O)";
      }
      else{
         sauceText5 = "(X)";
      }
      
      if (jtbHotChilli.isSelected()){
         sauceText6 = "(" + jtbHotChilli.getText() + " O)";
      }
      else{
         sauceText6 = "(X)";
      }
      
      //ingredient
      if(jtbLettuce.isSelected()){
         ingredientText = jtbLettuce.getText();
      }
      
      else if(jtbCumcumber.isSelected()){
         ingredientText = jtbCumcumber.getText();
      }
      
      else if(jtbPepper.isSelected()){
         ingredientText = jtbPepper.getText();
      }
      
      else if(jtbOnion.isSelected()){
         ingredientText = jtbOnion.getText();
      }
      
      else if(jtbPickle.isSelected()){
         ingredientText = jtbPickle.getText();
      }
      
      else if(jtbCheese.isSelected()){
         ingredientText = jtbCheese.getText();
      }
      
      else{
         ingredientText = "추가 안함";
      }
      
      //side
      if(jtbCookie.isSelected()){
         sideText1 = "(" + jtbCookie.getText() + " O)";
      }
      else {
         sideText1 = "(X)";
      }
      
      if(jtbCoffee.isSelected()){
         sideText2 = "(" + jtbCoffee.getText() + " O)";
      }
      else {
         sideText2 = "(X)";
      }
      
      if(jtbChip.isSelected()){
         sideText3 = "(" + jtbChip.getText() + " O)";
      }
      else {
         sideText3 = "(X)";
      }
      
      if(jtbDrink.isSelected()){
         sideText4 = "(" + jtbDrink.getText() + " O)";
      }
      else {
         sideText4 = "(X)";
      }
      
      
      if(acEvent.getActionCommand().equals("주문 완료")){
         
         try{
            
            
            dataOut.writeUTF( jtaAddress.getText() + "|" +
                  mainText+ "|" +breadText+ "|" +sauceText1+" "+sauceText2+" "+sauceText3+" "+sauceText4+" "+sauceText5+" "+sauceText6+ "|"
                  +ingredientText+ "|"
                  +sideText1+"  "+sideText2+"  "+sideText3+"  "+sideText4);
            

             System.out.println(JOptionPane.showConfirmDialog(this, "주문이 완료되었습니다.", "주문확인", 
                   0, 0, new ImageIcon("snutway_logo.jpg")));
            
            
            dataOut.flush();
            
         }
         
         catch (Exception e){
            label.setText("문제가 발생했습니다." + e.toString());
         }
      }
   }
   
   
   
   public void stop(){
      try{
         
         dataIn.close();
         dataOut.close();
         socket.close();
      }
      
      catch (IOException ioe){
         label.setText("문제 발생" + ioe.toString());
      }
      
   }
   
   
   public static void main(String[] args){
      
   }
   
}
