import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class work_adjustment extends JFrame implements ActionListener,MouseListener {
	
	Font font1,font2,font3,font4;
	
	JPanel panel_login,panel_login2,panel_cover,panel_titleBar,panel_home,panel_Subhome;
	JPanel panel_option,panel_edit,panel_Subedit1,panel_Subedit2;
	JPanel panel_report,panel_subReport,panel_sort;
	JPanel panel_profile,panel_subProfile1,panel_subProfile2;
	
	JLabel clg_logo,clg_name,clg_city,login_title;
	JLabel label_username,label_password,eyeOpen,eyeClose,error_msg1,error_msg2,error_msg3,error_msg4,error_msg5,error_msg6,error_msg7;
	JLabel label_Stafflogo,label_Title1,label_Title2;
	JLabel workingLogo,staffOnleave,date,time,Ad_Class,staffOnadjustment,infoText,dotLabel;
	JLabel infoText2,staffOnleave2,date4,time2,Ad_Class2,staffOnadjustment2;
	JLabel infoText3,infoText4,profile_Clgtitle,profile_Clgcity,profile_StaffName,profile_StaffInitial,profile_Staffdept,profile_gain,profile_loss,profile_total,profile_title;
	
	JTextField usernameTF;
	JTextField staffOnleaveTF,dateTF,timeTF,Ad_ClassTF,staffOnadjustmentTF,search_byStaffTF,search_byDateTF;
	JTextField dateTF2,timeTF2,Ad_ClassTF2,staffOnadjustmentTF2;
	JTextField report_searchTF;
	JPasswordField passwordTF;
	
	JButton exit,btn_login,btn_cancel;
	JButton home_submit,home_cancel,home_btn,edit_btn,report_btn,exit_btn,profile_btn,logout_btn,btn_search;
	JButton edit_submit,edit_cancel,report_searchBtn,report_sort,sort_none,sort_AtoZ,sort_ZtoA,sort_byGain,sort_byLoss,sort_byTotal;
	JButton profileBack_btn;
	
	JScrollPane scrollablePanelEdit;
	
	Date Date;  
	
	SimpleDateFormat formatter;
	
	Connection con;
	
	Statement statement;
	
	DatabaseMetaData databaseMetaData;
	
	JLabel[] date_label = new JLabel[50];
	JLabel[] hour_label = new JLabel[50];
	JLabel[] class_label = new JLabel[50];
	JLabel[] leave_label = new JLabel[50];
	JLabel[] adjust_label = new JLabel[50];
	JButton[] edit_button = new JButton[150];
	JButton[] delete_button = new JButton[150];
	
	JLabel[] date_label2 = new JLabel[50];
	JLabel[] hour_label2 = new JLabel[50];
	JLabel[] class_label2 = new JLabel[50];
	JLabel[] leave_label2 = new JLabel[50];
	JLabel[] adjust_label2 = new JLabel[50];
	
	JLabel[] staff_label = new JLabel[50];
	JLabel[] gain_label = new JLabel[50];
	JLabel[] loss_label = new JLabel[50];
	JLabel[] total_label = new JLabel[50];

	Vector<String> date_vec = new Vector<String>();
	Vector<String> leave_vec = new Vector<String>();
	Vector<String> hour_vec = new Vector<String>();
	Vector<String> class_vec = new Vector<String>();
	Vector<String> adjust_vec = new Vector<String>();
	
	Vector<String> date_vec2 = new Vector<String>();
	Vector<String> leave_vec2 = new Vector<String>();
	Vector<String> hour_vec2 = new Vector<String>();
	Vector<String> class_vec2 = new Vector<String>();
	Vector<String> adjust_vec2 = new Vector<String>();
	
	Vector<String> staff_vec = new Vector<String>();
	Vector<Integer> gain_vec = new Vector<Integer>();
	Vector<Integer> loss_vec = new Vector<Integer>();
	Vector<Integer> total_vec = new Vector<Integer>();
	
	char defaultEye;
	
	int id,i=1,loss=0,gain=0,total=0,a=1;
	
	String login_username,login_password,username,password;
	String staff_leave,staff_adjust,leave_date,class_time,className,date2="",date5;
	String searchStaff="",searchDate="",date3="";
	String LStaffName2,AStaffName2,LDate2,class_time3,Ad_class2;
	
	ResultSet checker;
	ResultSet rst2;
	
	boolean check=false,check2=true,check3=false;
	
	JFrame jframe;
	
	work_adjustment() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(null);
		setTitle("Work Adjustment");
		 
		font1 = new Font("forte",Font.BOLD,28);
		font2 = new Font("forte",Font.BOLD,22);
		font3 = new Font("Times New Roman",Font.BOLD,18);
		font4 = new Font("Times New Roman",Font.BOLD,16);
		
		UIManager.put("ToolTip.background", Color.YELLOW);
        UIManager.put("ToolTip.foreground", Color.BLACK);
		
		formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		jframe = new JFrame();
		
		panel_login = new JPanel();
		panel_login.setSize(1380,765);
		panel_login.setBackground(Color.lightGray);
		panel_login.setLayout(null);
		
		add(panel_login);
		
		clg_logo = new JLabel("",JLabel.CENTER);
		clg_logo.setBounds(30,80,600,600);
		clg_logo.setOpaque(false);
		clg_logo.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/clg_logo.png"));
		
		clg_name = new JLabel("AYYA NADAR JANAKI AMMAL COLLEGE",JLabel.CENTER);
		clg_name.setBounds(340,20,600,30);
		clg_name.setBackground(Color.lightGray);
		clg_name.setForeground(Color.black);
		clg_name.setFont(new Font("monospaced",Font.BOLD,28));
		
		clg_city = new JLabel("SIVAKASI - 626123.",JLabel.CENTER);
		clg_city.setBounds(480,60,300,30);
		clg_city.setBackground(Color.lightGray);
		clg_city.setForeground(Color.black);
		clg_city.setFont(new Font("monospaced",Font.BOLD,22));
		
		panel_login2 = new JPanel();
		panel_login2.setBounds(740,160,450,450);
		panel_login2.setBackground(Color.white);
		panel_login2.setBorder(BorderFactory.createLineBorder(Color.black,1,true));
		panel_login2.setLayout(null);
		
		exit = new JButton("Exit");
		exit.setBounds(30,30,100,40);
		exit.addActionListener(this);
		exit.setBackground(Color.white);
		exit.setForeground(Color.black);
		exit.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),2,true));
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.setFont(font4);
		exit.setFocusPainted(false);
		
		login_title = new JLabel("LOGIN",JLabel.CENTER);
		login_title.setBounds(140,35,300,30);
		login_title.setBackground(Color.white);
		login_title.setForeground(new Color(241, 0, 134));
		login_title.setFont(new Font("Times New Roman",Font.BOLD,24));
		
		error_msg1 = new JLabel();
		error_msg1.setBounds(50,195,140,20);
		error_msg1.setBackground(Color.white);
		error_msg1.setForeground(Color.red);
		error_msg1.setFont(new Font("Times New Roman",Font.BOLD,12));
		error_msg1.setVisible(false);
		
		label_username = new JLabel(" Username");
		label_username.setBounds(50,120,130,30);
		label_username.setBackground(Color.white);
		label_username.setForeground(Color.black);
		label_username.setFont(font3);
		label_username.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/userIcon.jpg"));
		
		usernameTF = new JTextField();
        usernameTF.setBounds(50,160,300,30);
		usernameTF.setFont(new Font("Times New Roman",Font.BOLD,14));
		usernameTF.setForeground(Color.black);
        usernameTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
        usernameTF.addFocusListener(new FocusListener() {
			@Override
            public void focusGained(FocusEvent e) {
                usernameTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1,true));
				error_msg1.setVisible(false);
            }
            @Override
            public void focusLost(FocusEvent e) {
                usernameTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
				error_msg1.setVisible(false);
            }
        });	
        usernameTF.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                
                error_msg1.setVisible(false);

            }

        });
		
		error_msg2 = new JLabel();
		error_msg2.setBounds(50,290,140,20);
		error_msg2.setBackground(Color.white);
		error_msg2.setForeground(Color.red);
		error_msg2.setFont(new Font("Times New Roman",Font.BOLD,12));
		error_msg2.setVisible(false);
				
		label_password = new JLabel(" Password");
		label_password.setBounds(50,215,100,30);
		label_password.setBackground(Color.white);
		label_password.setForeground(Color.black);
		label_password.setFont(font3);
		label_password.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/lockIcon.png"));
		
		passwordTF = new JPasswordField();
        passwordTF.setBounds(50,255,300,30);
		passwordTF.setFont(new Font("Times New Roman",Font.BOLD,14));
		passwordTF.setForeground(Color.black);
		defaultEye = passwordTF.getEchoChar();
		passwordTF.setEchoChar(defaultEye);
        passwordTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
        passwordTF.addFocusListener(new FocusListener() {
			@Override
            public void focusGained(FocusEvent e) {
                passwordTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1,true));
				error_msg2.setVisible(false);
            }
            @Override
            public void focusLost(FocusEvent e) {
                passwordTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
				error_msg2.setVisible(false);
            }
        });
		passwordTF.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				
				error_msg2.setVisible(false);
				
			}
			
		});
		
		eyeOpen = new JLabel("",new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/eyeOpen.png"),JLabel.CENTER);
		eyeOpen.setBounds(355,258,40,23);
		eyeOpen.setVisible(false);
		eyeOpen.addMouseListener(this);
		
		eyeClose = new JLabel("",new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/eyeClose.png"),JLabel.CENTER);
		eyeClose.setBounds(355,258,40,23);
		eyeClose.addMouseListener(this);
		
		btn_login = new JButton("LOGIN");
		btn_login.setBounds(60,360,150,40);
		btn_login.addActionListener(this);
	    btn_login.setBackground(new Color(241, 0, 134));
		btn_login.setForeground(Color.white);
		btn_login.setBorder(BorderFactory.createLineBorder(Color.pink,2,true));
		btn_login.setFont(font4);
		btn_login.setFocusPainted(false);
		btn_login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		btn_cancel = new JButton("CANCEL");
		btn_cancel.setBounds(240,360,150,40);
		btn_cancel.addActionListener(this);
	    btn_cancel.setBackground(new Color(241, 0, 134));
		btn_cancel.setForeground(Color.white);
		btn_cancel.setBorder(BorderFactory.createLineBorder(Color.pink,2,true));
		btn_cancel.setFont(font4);
		btn_cancel.setFocusPainted(false);
		btn_cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panel_login2.add(exit);
		panel_login2.add(login_title);
		panel_login2.add(error_msg1);
		panel_login2.add(label_username);
		panel_login2.add(usernameTF);
		panel_login2.add(label_password);
		panel_login2.add(passwordTF);
		panel_login2.add(error_msg2);
		panel_login2.add(eyeOpen);
		panel_login2.add(eyeClose);
		panel_login2.add(btn_login);
		panel_login2.add(btn_cancel);
		
		panel_login.add(clg_logo);
		panel_login.add(clg_name);
		panel_login.add(clg_city);
		panel_login.add(panel_login2);
		
		panel_cover = new JPanel();
		panel_cover.setBounds(0,0,1380,765);
	    panel_cover.setLayout(null);
		panel_cover.setVisible(false);
		
		add(panel_cover);
		
		panel_titleBar = new JPanel();
		panel_titleBar.setBounds(0,0,1380,100);
		panel_titleBar.setBorder(BorderFactory.createLineBorder(Color.black,1,true));
		panel_titleBar.setBackground(Color.white);
		panel_titleBar.setLayout(null);
		
		panel_cover.add(panel_titleBar);
		
		panel_home = new JPanel();
		panel_home.setBounds(0,101,1380,665);
		panel_home.setLayout(null);
		panel_home.setBackground(new Color(240, 240, 240,120));          //(new Color(232, 234, 230));
		
		panel_cover.add(panel_home);
		
		label_Stafflogo = new JLabel();
		label_Stafflogo.setBounds(20,15,70,70);
		label_Stafflogo.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/staffIcon2.png"));
		
		label_Title1 = new JLabel("STAFF");
		label_Title1.setBounds(120,30,80,20);
		label_Title1.setFont(new Font("monospaced",Font.BOLD,16));
		label_Title1.setBackground(Color.white);
		label_Title1.setForeground(Color.black);
		
		label_Title2 = new JLabel("WORK ADJUSTMENT");
		label_Title2.setBounds(120,55,250,20);
		label_Title2.setFont(new Font("monospaced",Font.BOLD,18));
		label_Title2.setBackground(Color.white);
		label_Title2.setForeground(Color.black);
		
		profile_btn = new JButton(" PROFILE");
		profile_btn.setBounds(950,35,130,30);
		profile_btn.setBackground(Color.white);
		profile_btn.setForeground(new Color(0,0,139));
		profile_btn.setFont(new Font("monospaced",Font.BOLD,18));
		profile_btn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		profile_btn.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/userIcon.jpg"));
		profile_btn.setFocusPainted(false);
		profile_btn.addActionListener(this);
		profile_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		logout_btn = new JButton(" LOGOUT");
		logout_btn.setBounds(1140,35,130,30);
		logout_btn.setBackground(Color.white);
		logout_btn.setForeground(new Color(0,0,139));
		logout_btn.setFont(new Font("monospaced",Font.BOLD,18));
		logout_btn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		logout_btn.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/logoutIcon.jpg"));
		logout_btn.setFocusPainted(false);
		logout_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		logout_btn.addActionListener(this);
		
		panel_titleBar.add(label_Stafflogo);
		panel_titleBar.add(label_Title1);
		panel_titleBar.add(label_Title2);
		panel_titleBar.add(profile_btn);
		panel_titleBar.add(logout_btn);
		
		workingLogo = new JLabel("",JLabel.CENTER);
		workingLogo.setBounds(630,60,640,480);
		workingLogo.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/workLogo.gif"));
		
		panel_Subhome = new JPanel();
		panel_Subhome.setBounds(60,50,520,460);
		panel_Subhome.setBackground(Color.white);
		panel_Subhome.setLayout(null);
		panel_Subhome.setBorder(BorderFactory.createLineBorder(new Color(178, 190, 181),4));	
		
		panel_home.add(panel_Subhome);
		
		staffOnleave = new JLabel(" Staff on Leave/Duty :");
		staffOnleave.setBounds(60,50,200,30);
		staffOnleave.setBackground(Color.lightGray);
		staffOnleave.setForeground(Color.black);
		staffOnleave.setFont(font3);
		
		error_msg3 = new JLabel();
		error_msg3.setBounds(260,85,200,20);
		error_msg3.setBackground(Color.white);
		error_msg3.setForeground(Color.red);
		error_msg3.setFont(new Font("Times New Roman",Font.BOLD,12));
		error_msg3.setVisible(false);
		
		staffOnleaveTF = new JTextField();
		staffOnleaveTF.setBounds(260,50,200,30);
		staffOnleaveTF.setFont(new Font("Times New Roman",Font.BOLD,14));
		staffOnleaveTF.setForeground(Color.black);
        staffOnleaveTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
		staffOnleaveTF.addFocusListener(new FocusListener() {
			@Override
            public void focusGained(FocusEvent e) {
                staffOnleaveTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1,true));
				error_msg3.setVisible(false);
				
            }
            @Override
            public void focusLost(FocusEvent e) {
                staffOnleaveTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
				error_msg3.setVisible(false);
				
            }
        });
		
		date = new JLabel(" Date on Leave/Duty :");
		date.setBounds(60,110,180,30);
		date.setBackground(Color.lightGray);
		date.setForeground(Color.black);
		date.setFont(font3);
		
		error_msg4 = new JLabel();
		error_msg4.setBounds(260,145,230,20);
		error_msg4.setBackground(Color.white);
		error_msg4.setForeground(Color.red);
		error_msg4.setFont(new Font("Times New Roman",Font.BOLD,12));
		error_msg4.setVisible(false);
		
		dateTF = new JTextField(" dd-mm-yyyy");
		dateTF.setBounds(260,110,200,30);
		dateTF.setFont(new Font("Times New Roman",Font.BOLD,14));
		dateTF.setForeground(Color.black);
        dateTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
		dateTF.addFocusListener(new FocusListener() {
			@Override
            public void focusGained(FocusEvent e) {
                dateTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1,true));
				if ( dateTF.getText().toString().equals(" dd-mm-yyyy") ) {
				    dateTF.setText("");
				}
				error_msg4.setVisible(false);
				
            }
            @Override
            public void focusLost(FocusEvent e) {
                dateTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
				if ( dateTF.getText().toString().equals("") ) {
				    dateTF.setText(" dd-mm-yyyy");
				}
				error_msg4.setVisible(false);
				
            }
        });
		
		time = new JLabel(" Time :",JLabel.RIGHT);
		time.setBounds(60,170,170,30);
		time.setBackground(Color.lightGray);
		time.setForeground(Color.black);
		time.setFont(font3);
	    
		error_msg5 = new JLabel();
		error_msg5.setBounds(260,205,200,20);
		error_msg5.setBackground(Color.white);
		error_msg5.setForeground(Color.red);
		error_msg5.setFont(new Font("Times New Roman",Font.BOLD,12));
		error_msg5.setVisible(false);
		
		timeTF = new JTextField(" hh-hh");
		timeTF.setBounds(260,170,200,30);
		timeTF.setFont(new Font("Times New Roman",Font.BOLD,14));
		timeTF.setForeground(Color.black);
        timeTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
		timeTF.addFocusListener(new FocusListener() {
			@Override
            public void focusGained(FocusEvent e) {
                timeTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1,true));
				if ( timeTF.getText().toString().equals(" hh-hh") ) {
				    timeTF.setText("");
				}
				error_msg5.setVisible(false);
            }
            @Override
            public void focusLost(FocusEvent e) {
                timeTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
				if ( timeTF.getText().toString().equals("") ) {
				    timeTF.setText(" hh-hh");
				}
				error_msg5.setVisible(false);
				
            }
        });
		
		Ad_Class = new JLabel(" Class :",JLabel.RIGHT);
		Ad_Class.setBounds(60,230,170,30);
		Ad_Class.setBackground(Color.lightGray);
		Ad_Class.setForeground(Color.black);
		Ad_Class.setFont(font3);
		
		error_msg6 = new JLabel();
		error_msg6.setBounds(260,265,200,20);
		error_msg6.setBackground(Color.white);
		error_msg6.setForeground(Color.red);
		error_msg6.setFont(new Font("Times New Roman",Font.BOLD,12));
		error_msg6.setVisible(false);
		
		Ad_ClassTF = new JTextField("");
		Ad_ClassTF.setBounds(260,230,200,30);
		Ad_ClassTF.setFont(new Font("Times New Roman",Font.BOLD,14));
		Ad_ClassTF.setForeground(Color.black);
        Ad_ClassTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
		Ad_ClassTF.addFocusListener(new FocusListener() {
			@Override
            public void focusGained(FocusEvent e) {
                Ad_ClassTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1,true));
				error_msg6.setVisible(false);
				
            }
            @Override
            public void focusLost(FocusEvent e) {
                Ad_ClassTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
				error_msg6.setVisible(false);
				
            }
        });
		
		staffOnadjustment = new JLabel(" Staff on Adjustment :");
		staffOnadjustment.setBounds(60,290,200,30);
		staffOnadjustment.setBackground(Color.lightGray);
		staffOnadjustment.setForeground(Color.black);
		staffOnadjustment.setFont(font3);
		
		error_msg7 = new JLabel();
		error_msg7.setBounds(260,325,300,20);
		error_msg7.setBackground(Color.white);
		error_msg7.setForeground(Color.red);
		error_msg7.setFont(new Font("Times New Roman",Font.BOLD,12));
		error_msg7.setVisible(false);
		
		staffOnadjustmentTF = new JTextField("");
		staffOnadjustmentTF.setBounds(260,290,200,30);
		staffOnadjustmentTF.setFont(new Font("Times New Roman",Font.BOLD,14));
		staffOnadjustmentTF.setForeground(Color.black);
        staffOnadjustmentTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
		staffOnadjustmentTF.addFocusListener(new FocusListener() {
			@Override
            public void focusGained(FocusEvent e) {
                staffOnadjustmentTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1,true));
				error_msg7.setVisible(false);
				
            }
            @Override
            public void focusLost(FocusEvent e) {
                staffOnadjustmentTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
				error_msg7.setVisible(false);
				
            }
        });
		
		home_submit = new JButton("SUBMIT");
		home_submit.setBounds(80,380,150,30);
		home_submit.addActionListener(this);
	    home_submit.setBackground(new Color(241, 0, 134));
		home_submit.setForeground(Color.white);
		home_submit.setBorder(BorderFactory.createLineBorder(Color.pink,2,true));
		home_submit.setFont(font4);
		home_submit.setFocusPainted(false);
		home_submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		home_cancel = new JButton("CANCEL");
		home_cancel.setBounds(290,380,150,30);
		home_cancel.addActionListener(this);
	    home_cancel.setBackground(new Color(241, 0, 134));
		home_cancel.setForeground(Color.white);
		home_cancel.setBorder(BorderFactory.createLineBorder(Color.pink,2,true));
		home_cancel.setFont(font4);
		home_cancel.setFocusPainted(false);
		home_cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		panel_home.add(workingLogo);
		panel_Subhome.add(staffOnleave);
		panel_Subhome.add(staffOnleaveTF);
		panel_Subhome.add(error_msg3);
		panel_Subhome.add(date);
		panel_Subhome.add(error_msg4);
		panel_Subhome.add(dateTF);
		panel_Subhome.add(time);
		panel_Subhome.add(error_msg5);
		panel_Subhome.add(timeTF);
		panel_Subhome.add(Ad_Class);
		panel_Subhome.add(error_msg6);
		panel_Subhome.add(Ad_ClassTF);
		panel_Subhome.add(staffOnadjustment);
		panel_Subhome.add(error_msg7);
		panel_Subhome.add(staffOnadjustmentTF);
		panel_Subhome.add(home_submit);
		panel_Subhome.add(home_cancel);
		
		panel_option = new JPanel();
		panel_option.setBounds(450,550,450,60);
		panel_option.setLayout(new GridLayout(1,3));
		panel_option.setBackground(Color.white);
		panel_option.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1,true));
		panel_option.setVisible(false);
		
		panel_home.add(panel_option);
		
		home_btn = new JButton("");
		home_btn.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/homeIcon.png"));
		home_btn.setBackground(Color.white);
		home_btn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		home_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		home_btn.addMouseListener(this);
		home_btn.addActionListener(this);
		
		edit_btn = new JButton("");
		edit_btn.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/editIcon.png"));
		edit_btn.setBackground(Color.white);
		edit_btn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		edit_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		edit_btn.addMouseListener(this);
		edit_btn.addActionListener(this);
		
		report_btn = new JButton("");
		report_btn.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/reportIcon.jpg"));
		report_btn.setBackground(Color.white);
		report_btn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		report_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		report_btn.addMouseListener(this);
		report_btn.addActionListener(this);
		
		exit_btn = new JButton("EXIT");
		exit_btn.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/exitIcon.jpg"));
		exit_btn.setBackground(Color.white);
		exit_btn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		exit_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit_btn.addMouseListener(this);
		exit_btn.addActionListener(this);
		
		panel_option.add(home_btn);
		panel_option.add(edit_btn);
		panel_option.add(report_btn);
		panel_option.add(exit_btn);
		
		panel_edit = new JPanel();
		panel_edit.setBounds(0,101,1380,665);
		panel_edit.setLayout(null);
		panel_edit.setBackground(Color.lightGray);
		panel_edit.setVisible(false);
		
		panel_cover.add(panel_edit);
		
		panel_Subedit1 = new JPanel();
		panel_Subedit1.setBounds(15,50,650,460);
		panel_Subedit1.setBackground(Color.white);
		panel_Subedit1.setLayout(null);
		panel_Subedit1.setBorder(BorderFactory.createLineBorder(new Color(178, 190, 181),4));
		
		panel_Subedit2 = new JPanel();
		panel_Subedit2.setBounds(675,50,650,460);
		panel_Subedit2.setBackground(Color.white);
		panel_Subedit2.setLayout(null);
		panel_Subedit2.setBorder(BorderFactory.createLineBorder(new Color(178, 190, 181),4));
        
		search_byStaffTF = new JTextField(" Search by Staff");
		search_byStaffTF.setBounds(15,10,200,25);
		search_byStaffTF.setBackground(Color.white);
		search_byStaffTF.setForeground(Color.lightGray);
		//search_byStaffTF.setBorder(BorderFactory.createLineBorder(Color.black,1);
		search_byStaffTF.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {
				
				if ( search_byStaffTF.getText().toString().equals(" Search by Staff") ) {

                    search_byStaffTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1));
					search_byStaffTF.setForeground(Color.black);
                    search_byStaffTF.setText("");					
				}
			}
			@Override
            public void focusLost(FocusEvent fe) {
				search_byStaffTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
				if ( search_byStaffTF.getText().toString().equals("") ) {

                    search_byStaffTF.setText(" Search by Staff");
					search_byStaffTF.setForeground(Color.lightGray);
				}
			}
		});			
		
		search_byDateTF = new JTextField(" Search by Date");
		search_byDateTF.setBounds(220,10,200,25);
		search_byDateTF.setBackground(Color.white);
		search_byDateTF.setForeground(Color.lightGray);
		search_byDateTF.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {
				
				if ( search_byDateTF.getText().toString().equals(" Search by Date") ) {

                    search_byDateTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1));
					search_byDateTF.setForeground(Color.black);
                    search_byDateTF.setText("");					
				}
			}
			@Override
            public void focusLost(FocusEvent fe) {
				search_byDateTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
				if ( search_byDateTF.getText().toString().equals("") ) {

                    search_byDateTF.setText(" Search by Date");
					search_byDateTF.setForeground(Color.lightGray);
				}
			}
		});	
		
		infoText = new JLabel("");
		infoText.setBounds(260,200,250,30);
		infoText.setFont(new Font("Times New Roman",Font.BOLD,14));
		infoText.setBackground(Color.white);
		infoText.setVisible(false);
	
		btn_search = new JButton("");
		btn_search.setBounds(425,11,23,23);
		btn_search.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/searchIcon.jpg"));
		btn_search.setBackground(Color.white);
		btn_search.setFont(new Font("Times New Roman",Font.PLAIN,12));
		btn_search.setBorder(BorderFactory.createLineBorder(Color.black,1));
		btn_search.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_search.addActionListener(this);
		
		infoText2 = new JLabel("Nothing to Show");
		infoText2.setBounds(260,200,250,30);
		infoText2.setFont(new Font("Times New Roman",Font.BOLD,14));
		infoText2.setBackground(Color.white);
		
		staffOnleave2 = new JLabel("");
		staffOnleave2.setBounds(60,50,280,30);
		staffOnleave2.setBackground(Color.lightGray);
		staffOnleave2.setForeground(Color.black);
		staffOnleave2.setFont(font3);
		staffOnleave2.setVisible(false);
		
		date4 = new JLabel("");
		date4.setBounds(60,110,380,30);
		date4.setBackground(Color.lightGray);
		date4.setForeground(Color.black);
		date4.setFont(font3);
		date4.setVisible(false);
	
		time2 = new JLabel("",JLabel.RIGHT);
		time2.setBounds(60,170,240,30);
		time2.setBackground(Color.lightGray);
		time2.setForeground(Color.black);
		time2.setFont(font3);
		time2.setVisible(false);
	    
		Ad_Class2 = new JLabel(" Class :",JLabel.RIGHT);
		Ad_Class2.setBounds(60,230,170,30);
		Ad_Class2.setBackground(Color.lightGray);
		Ad_Class2.setForeground(Color.black);
		Ad_Class2.setFont(font3);
		Ad_Class2.setVisible(false);
		
		Ad_ClassTF2 = new JTextField("");
		Ad_ClassTF2.setBounds(260,230,200,30);
		Ad_ClassTF2.setFont(new Font("Times New Roman",Font.BOLD,14));
		Ad_ClassTF2.setForeground(Color.black);
        Ad_ClassTF2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
		Ad_ClassTF2.setVisible(false);
		Ad_ClassTF2.addFocusListener(new FocusListener() {
			@Override
            public void focusGained(FocusEvent e) {
                Ad_ClassTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1,true));
				error_msg6.setVisible(false);
				
            }
            @Override
            public void focusLost(FocusEvent e) {
                Ad_ClassTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
				error_msg6.setVisible(false);
				
            }
        });
		
		staffOnadjustment2 = new JLabel(" Staff on Adjustment :");
		staffOnadjustment2.setBounds(60,290,200,30);
		staffOnadjustment2.setBackground(Color.lightGray);
		staffOnadjustment2.setForeground(Color.black);
		staffOnadjustment2.setFont(font3);
		staffOnadjustment2.setVisible(false);
		
		staffOnadjustmentTF2 = new JTextField("");
		staffOnadjustmentTF2.setBounds(260,290,200,30);
		staffOnadjustmentTF2.setFont(new Font("Times New Roman",Font.BOLD,14));
		staffOnadjustmentTF2.setForeground(Color.black);
        staffOnadjustmentTF2.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
		staffOnadjustmentTF2.setVisible(false);
		staffOnadjustmentTF2.addFocusListener(new FocusListener() {
			@Override
            public void focusGained(FocusEvent e) {
                staffOnadjustmentTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1,true));
				error_msg7.setVisible(false);
				
            }
            @Override
            public void focusLost(FocusEvent e) {
                staffOnadjustmentTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1,true));
				error_msg7.setVisible(false);
				
            }
        });
		
		edit_submit = new JButton("SUBMIT");
		edit_submit.setBounds(80,380,150,30);
		edit_submit.addActionListener(this);
	    edit_submit.setBackground(new Color(241, 0, 134));
		edit_submit.setForeground(Color.white);
		edit_submit.setBorder(BorderFactory.createLineBorder(Color.pink,2,true));
		edit_submit.setFont(font4);
		edit_submit.setFocusPainted(false);
		edit_submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		edit_submit.setVisible(false);
		
		edit_cancel = new JButton("CANCEL");
		edit_cancel.setBounds(290,380,150,30);
		edit_cancel.addActionListener(this);
	    edit_cancel.setBackground(new Color(241, 0, 134));
		edit_cancel.setForeground(Color.white);
		edit_cancel.setBorder(BorderFactory.createLineBorder(Color.pink,2,true));
		edit_cancel.setFont(font4);
		edit_cancel.setFocusPainted(false);
		edit_cancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		edit_cancel.setVisible(false);
		
		panel_edit.add(search_byStaffTF);
		panel_edit.add(search_byDateTF);
		panel_edit.add(btn_search);
		panel_Subedit1.add(infoText);
		panel_edit.add(panel_Subedit1);
		panel_edit.add(panel_Subedit2);
		
		panel_Subedit2.add(infoText2);
		panel_Subedit2.add(staffOnleave2);
		panel_Subedit2.add(date4);
		panel_Subedit2.add(time2);
		panel_Subedit2.add(Ad_Class2);
		panel_Subedit2.add(Ad_ClassTF2);
		panel_Subedit2.add(staffOnadjustment2);
		panel_Subedit2.add(staffOnadjustmentTF2);
		panel_Subedit2.add(edit_submit);
		panel_Subedit2.add(edit_cancel);
		
		panel_report = new JPanel();
		panel_report.setBounds(0,101,1380,665);
		panel_report.setLayout(null);
		panel_report.setBackground(Color.lightGray);
		panel_report.setVisible(false);
		
		panel_cover.add(panel_report);
		
		infoText3 = new JLabel("Nothing to Show");
		infoText3.setBounds(360,210,250,30);
		infoText3.setFont(new Font("Times New Roman",Font.BOLD,14));
		infoText3.setBackground(Color.white);
		infoText3.setVisible(false);
		
		panel_subReport = new JPanel();
		panel_subReport.setBounds(200,50,900,460);
		panel_subReport.setBackground(Color.white);
		panel_subReport.setLayout(null);
		panel_subReport.setBorder(BorderFactory.createLineBorder(new Color(178, 190, 181),4));
		
		panel_report.add(panel_subReport);
		
		report_searchTF = new JTextField(" Search Staff");
		report_searchTF.setBounds(203,10,200,25);
		report_searchTF.setBackground(Color.white);
		report_searchTF.setForeground(Color.lightGray);
		report_searchTF.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {
				
				if ( report_searchTF.getText().toString().equals(" Search Staff") ) {

                    report_searchTF.setBorder(BorderFactory.createLineBorder(new Color(0,0,139),1));
					report_searchTF.setForeground(Color.black);
                    report_searchTF.setText("");					
				}
			}
			@Override
            public void focusLost(FocusEvent fe) {
				report_searchTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
				if ( report_searchTF.getText().toString().equals("") ) {

                    report_searchTF.setText(" Search Staff");
					report_searchTF.setForeground(Color.lightGray);
				}
			}
		});	
		
		report_searchBtn = new JButton("");
		report_searchBtn.setBounds(405,11,23,23);
		report_searchBtn.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/searchIcon.jpg"));
		report_searchBtn.setBackground(Color.white);
		report_searchBtn.setFont(new Font("Times New Roman",Font.PLAIN,12));
		report_searchBtn.setBorder(BorderFactory.createLineBorder(Color.black,1));
		report_searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		report_searchBtn.addActionListener(this);
		
		report_sort = new JButton("SORT ");
		report_sort.setBounds(1010,16,80,20);
		report_sort.setBackground(Color.lightGray);
		report_sort.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/RightArrow.png"));
		report_sort.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		report_sort.setForeground(new Color(0,0,139));
		report_sort.setFont(new Font("Times New Roman",Font.BOLD,14));
		report_sort.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		report_sort.setFocusPainted(false);
		report_sort.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		report_sort.addActionListener(this);
		
		panel_sort = new JPanel();
		panel_sort.setBounds(1100,7,220,90);
		panel_sort.setLayout(null);
		panel_sort.setBackground(Color.white);
		panel_sort.setVisible(false);
		
		sort_none = new JButton("none");
		sort_none.setBounds(10,10,60,30);
		sort_none.setFont(new Font("Times New Roman",Font.BOLD,12));
		sort_none.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		sort_none.setBackground(Color.lightGray);
		sort_none.setFocusPainted(false);
		sort_none.setForeground(Color.black);
		sort_none.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sort_none.addActionListener(this);
		
		sort_AtoZ = new JButton("A to Z");
		sort_AtoZ.setBounds(80,10,60,30);
		sort_AtoZ.setFont(new Font("Times New Roman",Font.BOLD,12));
		sort_AtoZ.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		sort_AtoZ.setBackground(Color.lightGray);
		sort_AtoZ.setFocusPainted(false);
		sort_AtoZ.setForeground(Color.black);
		sort_AtoZ.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sort_AtoZ.addActionListener(this);
		
		sort_ZtoA = new JButton("Z to A");
		sort_ZtoA.setBounds(150,10,60,30);
		sort_ZtoA.setFont(new Font("Times New Roman",Font.BOLD,12));
		sort_ZtoA.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		sort_ZtoA.setBackground(Color.lightGray);
		sort_ZtoA.setFocusPainted(false);
		sort_ZtoA.setForeground(Color.black);
		sort_ZtoA.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sort_ZtoA.addActionListener(this);
		
		sort_byGain = new JButton("by Gain");
		sort_byGain.setBounds(10,50,60,30);
		sort_byGain.setFont(new Font("Times New Roman",Font.BOLD,12));
		sort_byGain.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		sort_byGain.setBackground(Color.lightGray);
		sort_byGain.setFocusPainted(false);
		sort_byGain.setForeground(Color.black);
		sort_byGain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sort_byGain.addActionListener(this);
		
		sort_byLoss = new JButton("by Loss");
		sort_byLoss.setBounds(80,50,60,30);
		sort_byLoss.setFont(new Font("Times New Roman",Font.BOLD,12));
		sort_byLoss.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		sort_byLoss.setBackground(Color.lightGray);
		sort_byLoss.setFocusPainted(false);
		sort_byLoss.setForeground(Color.black);
		sort_byLoss.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sort_byLoss.addActionListener(this);
		
		sort_byTotal = new JButton("by Total");
		sort_byTotal.setBounds(150,50,60,30);
		sort_byTotal.setFont(new Font("Times New Roman",Font.BOLD,12));
		sort_byTotal.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		sort_byTotal.setBackground(Color.lightGray);
		sort_byTotal.setFocusPainted(false);
		sort_byTotal.setForeground(Color.black);
		sort_byTotal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sort_byTotal.addActionListener(this);
		
		panel_report.add(report_searchTF);
		panel_report.add(report_searchBtn);
		panel_report.add(report_sort);
		panel_report.add(panel_sort);
		
		panel_sort.add(sort_none);
		panel_sort.add(sort_AtoZ);
		panel_sort.add(sort_ZtoA);
		panel_sort.add(sort_byGain);
		panel_sort.add(sort_byLoss);
		panel_sort.add(sort_byTotal);
		
        panel_subReport.add(infoText3);
		
		panel_profile = new JPanel();
		panel_profile.setBounds(0,101,1380,665);
		panel_profile.setLayout(null);
		panel_profile.setBackground(Color.lightGray);
		panel_profile.setVisible(false);
		
		panel_cover.add(panel_profile);
		
		panel_subProfile1 = new JPanel();
		panel_subProfile1.setBounds(15,80,650,460);
		panel_subProfile1.setBackground(Color.white);
		panel_subProfile1.setLayout(null);
		panel_subProfile1.setBorder(BorderFactory.createLineBorder(new Color(178, 190, 181),4));
		
		panel_subProfile2 = new JPanel();
		panel_subProfile2.setBounds(675,80,650,460);
		panel_subProfile2.setBackground(Color.white);
		panel_subProfile2.setLayout(null);
		panel_subProfile2.setBorder(BorderFactory.createLineBorder(new Color(178, 190, 181),4));
		
		infoText4 = new JLabel("");
		infoText4.setBounds(255,220,250,30);
		infoText4.setFont(new Font("monospaced",Font.BOLD,14));
		infoText4.setBackground(Color.white);
		infoText4.setVisible(false);
		
		profile_title = new JLabel("TODAY ADJUSTMENTS");
		profile_title.setBounds(220,20,600,30);
		profile_title.setBackground(Color.white);
		profile_title.setForeground(Color.black);
		profile_title.setFont(new Font("monospaced",Font.BOLD,18));
		
		panel_subProfile2.add(infoText4);
		panel_subProfile2.add(profile_title);
		
		panel_profile.add(panel_subProfile1);
		panel_profile.add(panel_subProfile2);
		
		profileBack_btn = new JButton(" PROFILE");
		profileBack_btn.setBounds(10,30,130,30);
		profileBack_btn.setBackground(Color.lightGray);
		profileBack_btn.setFocusPainted(false);
		profileBack_btn.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/BackArrow.png"));
		profileBack_btn.setForeground(new Color(0,0,139));
		profileBack_btn.setFont(new Font("monospaced",Font.BOLD,18));
		profileBack_btn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		profileBack_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		profileBack_btn.addActionListener(this);
		
		panel_profile.add(profileBack_btn);
		
		profile_Clgtitle = new JLabel("AYYA NADAR JANAKI AMMAL COLLEGE");
		profile_Clgtitle.setBounds(150,20,600,30);
		profile_Clgtitle.setBackground(Color.white);
		profile_Clgtitle.setForeground(Color.black);
		profile_Clgtitle.setFont(new Font("monospaced",Font.BOLD,18));
		
		profile_Clgcity = new JLabel("SIVAKASI - 626123.");
		profile_Clgcity.setBounds(240,60,300,30);
		profile_Clgcity.setBackground(Color.white);
		profile_Clgcity.setForeground(Color.black);
		profile_Clgcity.setFont(new Font("monospaced",Font.BOLD,18));
		
		profile_StaffName = new JLabel("",JLabel.CENTER);
		profile_StaffName.setBounds(10,120,600,30);
		profile_StaffName.setBackground(Color.white);
		profile_StaffName.setForeground(Color.red);
		profile_StaffName.setFont(new Font("monospaced",Font.BOLD,20));
		
		profile_StaffInitial =  new JLabel("");
		profile_StaffInitial.setBounds(45,180,300,30);
		profile_StaffInitial.setBackground(Color.white);
		profile_StaffInitial.setForeground(Color.black);
		profile_StaffInitial.setFont(new Font("monospaced",Font.BOLD,18));
		
		profile_Staffdept =  new JLabel("");
		profile_Staffdept.setBounds(45,230,600,30);
		profile_Staffdept.setBackground(Color.white);
		profile_Staffdept.setForeground(Color.black);
		profile_Staffdept.setFont(new Font("monospaced",Font.BOLD,18));
		
		profile_gain =  new JLabel("");
		profile_gain.setBounds(45,280,300,30);
		profile_gain.setBackground(Color.white);
		profile_gain.setForeground(Color.black);
		profile_gain.setFont(new Font("monospaced",Font.BOLD,18));
		
		profile_loss =  new JLabel("");
		profile_loss.setBounds(45,330,300,30);
		profile_loss.setBackground(Color.white);
		profile_loss.setForeground(Color.black);
		profile_loss.setFont(new Font("monospaced",Font.BOLD,18));
		
		profile_total =  new JLabel("");
		profile_total.setBounds(45,380,300,30);
		profile_total.setBackground(Color.white);
		profile_total.setForeground(Color.black);
		profile_total.setFont(new Font("monospaced",Font.BOLD,18));
		
		panel_subProfile1.add(profile_Clgtitle);
		panel_subProfile1.add(profile_Clgcity);
		panel_subProfile1.add(profile_StaffName);
		panel_subProfile1.add(profile_StaffInitial);
		panel_subProfile1.add(profile_Staffdept);
		panel_subProfile1.add(profile_gain);
		panel_subProfile1.add(profile_loss);
		panel_subProfile1.add(profile_total);
		
		setSize(1380,765);
		setResizable(false);
		setVisible(true);
		
		connect_database();
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		if ( ae.getSource() == exit ) {
			
			int n = JOptionPane.showConfirmDialog(this,"Are you sure to exit?","Alert",JOptionPane.YES_NO_OPTION);
			
			if( n == JOptionPane.YES_OPTION) {
				
				try {
					
					con.close();
				    dispose();
					
				} catch ( Exception e ) { }
			}
		
        }
		
		if ( ae.getSource() == btn_login ) {
			
			login_username = usernameTF.getText().toString().trim();
			login_password = String.valueOf(passwordTF.getPassword());
			
			if ( login_username.isEmpty() ) {
				
				usernameTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg1.setText("Please enter username");
				error_msg1.setVisible(true);
				
			} else if ( login_password.isEmpty() ) {
				
				passwordTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg2.setText("Please enter password");
				error_msg2.setVisible(true);
				
			} else {
				
				login_username = login_username.toUpperCase();
				login();
				
			}
			
		}
		
		if ( ae.getSource() == btn_cancel ) {
			
			usernameTF.setText("");
			passwordTF.setText("");
			
			usernameTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
			error_msg1.setVisible(false);
			
			passwordTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
			error_msg2.setVisible(false);
			
		}
		
		if ( ae.getSource() == home_cancel ) {
			
			staffOnleaveTF.setText(""); dateTF.setText(" dd-mm-yyyy"); timeTF.setText(" hh-hh");
            Ad_ClassTF.setText(""); staffOnadjustmentTF.setText("");
			
			staffOnleaveTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
			error_msg3.setVisible(false);
			
			dateTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
			error_msg4.setVisible(false);
			
			timeTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
			error_msg5.setVisible(false);
			
			Ad_ClassTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
			error_msg6.setVisible(false);
			
			staffOnadjustmentTF.setBorder(BorderFactory.createLineBorder(Color.lightGray,1));
			error_msg7.setVisible(false);
			
		}

        if ( ae.getSource() == home_submit ) {

            staff_leave = staffOnleaveTF.getText().toString().trim().toUpperCase();
		    staff_adjust = staffOnadjustmentTF.getText().toString().trim().toUpperCase();
            leave_date = dateTF.getText().toString().trim();
			class_time = timeTF.getText().toString().trim();
			className = Ad_ClassTF.getText().toString().trim();
		    
			if ( staff_leave.isEmpty() && staff_adjust.isEmpty() && leave_date.equals("dd-mm-yyyy") 
				 && class_time.equals("hh-hh") && className.isEmpty() ) {
				
				staffOnleaveTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg3.setText("Please enter name");
				error_msg3.setVisible(true);
				
				dateTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg4.setText("Please enter date");
				error_msg4.setVisible(true);
				
				timeTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg5.setText("Please enter time");
				error_msg5.setVisible(true);
				
				Ad_ClassTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg6.setText("Please enter class name");
				error_msg6.setVisible(true);
				
				staffOnadjustmentTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg7.setText("Please enter name");
				error_msg7.setVisible(true);
				
				
			} else if ( staff_leave.isEmpty() ) {
				
				staffOnleaveTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg3.setText("Please enter name");
				error_msg3.setVisible(true);
				
			} else if ( staff_adjust.isEmpty() ) {
				
				staffOnadjustmentTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg7.setText("Please enter name");
				error_msg7.setVisible(true);
				
			} else if ( leave_date.equals("dd-mm-yyyy") ) {
				
				dateTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg4.setText("Please enter date");
				error_msg4.setVisible(true);
				
			} else if ( class_time.equals("hh-hh") ) {
				
				timeTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg5.setText("Please enter time");
				error_msg5.setVisible(true);
				
			} else if ( className.isEmpty() ) {
				
				Ad_ClassTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg6.setText("Please enter class name");
				error_msg6.setVisible(true);
				
			} else if ( staff_adjust.equals(staff_leave) ) {
				
				staffOnadjustmentTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				error_msg7.setText("Leave and Adjustment Staff names are same");
				error_msg7.setVisible(true);
				
			} else {
				if ( !(leave_date.isEmpty()) ) {
				
				    for ( int j=0; j<leave_date.length(); j++ ) {
					
					    if ( j==2 ) {
						
						    char c = leave_date.charAt(j);
						
						    if ( !(c == '-') ) {
							
							    dateTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				                error_msg4.setText("Incorrect Format");
				                error_msg4.setVisible(true);
							
							    break;
							
						    }
						
						    char c2 = class_time.charAt(j);
						
						    if ( !(c2 == '-') ) {
							
							    timeTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				                error_msg5.setText("Incorrect Format");
				                error_msg5.setVisible(true);
							
							    break;
							
						    } 
						    String str = class_time.substring((j+1),class_time.length());
						
						    if ( str.length() > 2 ) {
							
							    timeTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				                error_msg5.setText("Incorrect Format");
				                error_msg5.setVisible(true);
							
							    break;
							
						    } else if ( str.length() < 2 ) {
							
							    timeTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				                error_msg5.setText("Incorrect Format");
				                error_msg5.setVisible(true);
							
							    break;
							
						    }
						
						
					    } else if ( j == 5 ) {
						
						    char c = leave_date.charAt(j);
						
						    if ( !(c == '-') ) {
							
							    dateTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				                error_msg4.setText("Incorrect Format");
				                error_msg4.setVisible(true);
							
							    break;
							
						    }
						
						    String str2 = leave_date.substring((j+1),leave_date.length());
						
						    if ( !(str2.length() == 4) ) {
							
							    dateTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				                error_msg4.setText("Incorrect Format");
				                error_msg4.setVisible(true);
							
							    break;
							
						    } else if ( str2.length() > 4 ) {
							
						        dateTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				                error_msg4.setText("Incorrect Format");
				                error_msg4.setVisible(true);
							
							    break;
							
					        }
						
					    } else if ( j == leave_date.length()-1 ) {
						
						    Date = new Date();
						    date5 = formatter.format(Date);
						
						    String date = leave_date;
					
					        String day = date.substring(0,2);
					        int day2 = Integer.parseInt(day);
					
					        String month = date.substring(3,5);
					        int month2 = Integer.parseInt(month);
					
					        String year = date.substring(6,date.length());
					        int year2 = Integer.parseInt(year);
					
					        String day3 = date5.substring(0,2);
					        int day4 = Integer.parseInt(day3);
					
					        String month3 = date5.substring(3,5);
					        int month4 = Integer.parseInt(month3);
					
					        String year3 = date5.substring(6,date.length());
					        int year4 = Integer.parseInt(year3);
					
					        String t1 = class_time.substring(0,2);
					        String t2 = class_time.substring(3,5);
						
						    if ( login_username.equals(staff_leave) ) {
							    staffOnleaveTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				                error_msg3.setText("Currently Logged In");
				                error_msg3.setVisible(true);
							
				            } else if ( (day2 < day4) || (month2 < month4) || (year2 < year4) ) {
							
							    dateTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				                error_msg4.setText("Please enter current or following Date");
				                error_msg4.setVisible(true);
							
							    break;
							
					        } else if ( t1.equals(t2) ) {
							
							    timeTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				                error_msg5.setText("Incorrect Format");
				                error_msg5.setVisible(true);
							
							    break;
							
						    } else {   
				            
						        check2=true;
						        String hr="";
						
						        try {
						
						            Statement st1 = con.createStatement();
						
						            ResultSet rs1 = st1.executeQuery("select date from staff_adjustment where date='"+ leave_date + "'");
						
						            if ( rs1 != null ) {
							
							            Statement st2 = con.createStatement();
						
						                ResultSet rs2 = st2.executeQuery("select date,hour from staff_adjustment where staffOn_Leave='"+ staff_leave + "'");
							
							            if ( rs2 != null ) {
								
								            while ( rs2.next() ) {
									
									            hr = rs2.getString(2);
									
									            if ( hr.equals(class_time) && leave_date.equals(rs2.getString(1)) ) {
										
										            JOptionPane.showMessageDialog(this,"Cannot be add record.\nReason : Same Time.","WARNING",JOptionPane.WARNING_MESSAGE);
										            check2=false;
										            break;
									            } 
									
								            }
										
								            if ( check2 == true ) {		
								          
										        staffAdjustment();
								            }
							            } else {
								
								            staffAdjustment();
								
							            }
							
						            } else {
							
							            staffAdjustment();
							
						            }
						
					            } catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
					
						    }
					
				        }
			        }
		        }
			
		    }
		
		}
        
		if ( ae.getSource() == home_btn ) {

            panel_edit.setVisible(false);
			panel_report.setVisible(false);
			
            panel_home.setVisible(true);
			panel_home.add(panel_option);
			
			search_byStaffTF.setText(" Search by Staff");
			search_byStaffTF.setForeground(Color.lightGray);
					
			search_byDateTF.setText(" Search by Date");
			search_byDateTF.setForeground(Color.lightGray);
		
		}
		
        if ( ae.getSource() == edit_btn ) {
			
			search_byStaffTF.setText(" Search by Staff");
			search_byStaffTF.setForeground(Color.lightGray);
					
			search_byDateTF.setText(" Search by Date");
			search_byDateTF.setForeground(Color.lightGray);
			
			if ( check == true ) {
				
		        for ( int j=0; j<date_vec.size();j++) {
								
			        date_label[j].setVisible(false);
			        leave_label[j].setVisible(false);
			        hour_label[j].setVisible(false);
			        class_label[j].setVisible(false);
			        adjust_label[j].setVisible(false);
			        edit_button[j].setVisible(false);
			        delete_button[j].setVisible(false);
								
		        }
		    }
			
            date_vec.clear();
			leave_vec.clear();
			hour_vec.clear();
			class_vec.clear();
			adjust_vec.clear();
			
			infoText2.setVisible(true);
			staffOnleave2.setVisible(false);
		    date4.setVisible(false);
		    time2.setVisible(false);
			Ad_Class2.setVisible(false);
			Ad_ClassTF2.setVisible(false);
			staffOnadjustment2.setVisible(false);
			staffOnadjustmentTF2.setVisible(false);
			edit_submit.setVisible(false);
			edit_cancel.setVisible(false);
			
			Date = new Date();
			date2 = formatter.format(Date);
				
			Date = new Date();
			date5 = formatter.format(Date);
				
				try {
			    
				    Statement statement = con.createStatement();
				
				    ResultSet getInfo = statement.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where date='" + date2 + "'" );
				
				    if ( getInfo != null ) {
						
				        infoText.setVisible(false);
						
				        while( getInfo.next() ) {
					
					        date_vec.addElement(getInfo.getString(1));
					        leave_vec.addElement(getInfo.getString(2));
					        hour_vec.addElement(getInfo.getString(3));
					        class_vec.addElement(getInfo.getString(4));
					        adjust_vec.addElement(getInfo.getString(5));
					
				        }
				        if ( !(date_vec.size() == 0 ) ){
					
				            editOption();
							
				        } else {
							
					        infoText.setText("Nothing to Show");
					        infoText.setVisible(true);
					        panel_home.setVisible(false);
					        panel_report.setVisible(false);
			                panel_edit.setVisible(true);
			                panel_edit.add(panel_option);
				        }
				    } else { 
				
				        infoText.setText("Nothing to Show");
					    infoText.setVisible(true);
					    panel_home.setVisible(false);
					    panel_report.setVisible(false);
			            panel_edit.setVisible(true);
			            panel_edit.add(panel_option);
					
				    }
				
		        } catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }                           								
	    }
	
	    if ( ae.getSource() == btn_search ) {
		
		    searchStaff = search_byStaffTF.getText().toString().trim().toUpperCase();
		    searchDate = search_byDateTF.getText().toString().trim();
        
		    if ( searchStaff.equals("SEARCH BY STAFF") && searchDate.equals("Search by Date") ) {
			
			    search_byStaffTF.setBorder(BorderFactory.createLineBorder(Color.red,1));
			    search_byDateTF.setBorder(BorderFactory.createLineBorder(Color.red,1));
			
		    } else if ( searchStaff.equals("SEARCH BY STAFF") ) {
			
			    search_byStaffTF.setBorder(BorderFactory.createLineBorder(Color.red,1));
			
		    } else if ( searchDate.equals("Search by Date") ) {
			
			    search_byDateTF.setBorder(BorderFactory.createLineBorder(Color.red,1));
			
		    } else if ( !searchStaff.isEmpty() && !searchDate.isEmpty() ) {
			
		        for ( int j=0; j<date_vec.size();j++) {
								
			        date_label[j].setVisible(false);
			        leave_label[j].setVisible(false);
			        hour_label[j].setVisible(false);
			        class_label[j].setVisible(false);
			        adjust_label[j].setVisible(false);
			        edit_button[j].setVisible(false);
			        delete_button[j].setVisible(false);
								
		        }
		
		        date_vec.clear();
		        leave_vec.clear();
		        hour_vec.clear();
		        class_vec.clear();
		        adjust_vec.clear();
		        infoText.setVisible(false);
		
                Date = new Date();
			    date5 = formatter.format(Date);
			
                date2 = searchDate;
            
                try {
			    
				    Statement statement = con.createStatement();
				
				    ResultSet getInfo2 = statement.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where date='" + searchDate + "'" );
				
                    if ( getInfo2 != null ) {
						
					    infoText.setVisible(false);
						
					    while ( getInfo2.next() ) {
						
						    date3 = getInfo2.getString(1);
						
					    }

                        Statement statement2 = con.createStatement();
				
				        ResultSet getInfo3 = statement2.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where staffOn_Leave='" + searchStaff + "'" );
			        
                        if ( getInfo3 != null ) {
							
                            infoText.setVisible(false);
						
                            while ( getInfo3.next() ) {

                                if ( getInfo3.getString(1).equals(date3) ) {
								
								    date_vec.add(getInfo3.getString(1));
								    leave_vec.add(getInfo3.getString(2));
								    hour_vec.add(getInfo3.getString(3));
								    class_vec.add(getInfo3.getString(4));
								    adjust_vec.add(getInfo3.getString(5));
								
							    } 	
						    }
				
						    if ( !(date_vec.size() == 0) ){
								
						        editOption();
								
					        } else { 
							
						        infoText.setText("No Results Found");
						        infoText.setVisible(true);
						    }
						    
						
					    } else {
						
						    infoText.setText("No Results Found");
						    infoText.setVisible(true);
						
					    }
					
				    } else {
						
				        infoText.setText("No Results Found");
				        infoText.setVisible(true);
						
				    }
				
			    } catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
			
		    }
		
	    }
	
	    if ( ae.getSource() == edit_submit ) {
		
		    String classAdjust = Ad_ClassTF2.getText().toString().trim();
		    String adStaff = staffOnadjustmentTF2.getText().toString().trim().toUpperCase();
			
		    if ( adStaff.equals(LStaffName2) ) {
			
			    JOptionPane.showMessageDialog(this,"Leave and Adjustment staff names are same!!","Message",JOptionPane.WARNING_MESSAGE);
		
	        } else if ( classAdjust.equals(Ad_class2) && adStaff.equals(AStaffName2) ) {
			
			    JOptionPane.showMessageDialog(this,"Nothing Changed!!","Message",JOptionPane.WARNING_MESSAGE);
			
		    } else if ( !classAdjust.equals(Ad_class2) && !adStaff.equals(AStaffName2) ) {
			
			    try {
			
			        Statement st1 = con.createStatement();
			
			        ResultSet rst = st1.executeQuery("select id,date,hour from staff_adjustment where staffOn_Leave ='" + LStaffName2 +"'");
			
			        while ( rst.next() ) {
				
				        if ( LDate2.equals(rst.getString(2)) && class_time3.equals(rst.getString(3)) ) {
					
					        Statement st2 = con.createStatement();
					
					        int j = st2.executeUpdate("UPDATE staff_adjustment SET class = '" + classAdjust + "', staffOn_adjustment = '" + adStaff + "' WHERE id = '" + rst.getInt(1) + "'");
	                
					        if ( j == 1 ) {
						
						        Statement st3 = con.createStatement();
						
						        ResultSet getReport = st3.executeQuery("select gain,loss from staff_report where staff_name='" + AStaffName2 + "'");
								
						        while ( getReport.next() ) {
									
							        gain = getReport.getInt(1);
							        loss = getReport.getInt(2);
									
						        }
						
						        gain = gain - 1;
                                total = gain - loss;
						
						        Statement st4 = con.createStatement();
						
                                st4.executeUpdate("UPDATE staff_report SET gain = '" + gain + "', loss = '" + loss + "', Total = '" + total + "' WHERE staff_name = '" + AStaffName2 + "'"); 
                        
						        gain=0;total=0;loss=0;
						
                                Statement st5 = con.createStatement();
						
						        ResultSet getReport2 = st5.executeQuery("select gain,loss from staff_report where staff_name='" + adStaff + "'");
								
						        while ( getReport2.next() ) {
									
							        gain = getReport2.getInt(1);
							        loss = getReport2.getInt(2);
									
						        }
						
						        gain = gain + 1;
                                total = gain - loss;
						
						        Statement st6 = con.createStatement();
						
                                int k = st6.executeUpdate("UPDATE staff_report SET gain = '" + gain + "', loss = '" + loss + "', Total = '" + total + "' WHERE staff_name = '" + adStaff + "'");						
						
						        gain=0;loss=0;total=0;
							
						        if ( k == 1 ) {
							
							        JOptionPane.showMessageDialog(this,"Record Changed!!","Message",JOptionPane.WARNING_MESSAGE);
								
							        infoText2.setVisible(true);
							        staffOnleave2.setVisible(false);
							        date4.setVisible(false);
							        time2.setVisible(false);
							        Ad_Class2.setVisible(false);
							        Ad_ClassTF2.setVisible(false);
							        staffOnadjustment2.setVisible(false);
							        staffOnadjustmentTF2.setVisible(false);
							        edit_submit.setVisible(false);
							        edit_cancel.setVisible(false);
							
							        for ( int c=0; c<date_vec.size();c++ ) {
								
			                            date_label[c].setVisible(false);
			                            leave_label[c].setVisible(false);
			                            hour_label[c].setVisible(false);
			                            class_label[c].setVisible(false);
			                            adjust_label[c].setVisible(false);
			                            edit_button[c].setVisible(false);
			                            delete_button[c].setVisible(false);
								
		                            }
							
							        date_vec.clear();
			                        leave_vec.clear();
									hour_vec.clear();
									class_vec.clear();
									adjust_vec.clear();
							
									if ( searchDate.equals("") && searchStaff.equals("") ) {
					
										Statement statement = con.createStatement();
				
										ResultSet getInfo2 = statement.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where date='" + date2 + "'" );
				
										if ( getInfo2 != null ) {
										
											infoText.setVisible(false);
										
											while ( getInfo2.next() ) {
						
												date3 = getInfo2.getString(1);
						
											}

											Statement statement2 = con.createStatement();
				
											ResultSet getInfo3 = statement2.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where staffOn_Leave='" + LStaffName2 + "'" );
			        
											if ( getInfo3 != null ) {
										   
												infoText.setVisible(false);
						
												while ( getInfo3.next() ) {

													if ( getInfo3.getString(1).equals(date3) ) {
								
														date_vec.add(getInfo3.getString(1));
														leave_vec.add(getInfo3.getString(2));
														hour_vec.add(getInfo3.getString(3));
														class_vec.add(getInfo3.getString(4));
														adjust_vec.add(getInfo3.getString(5));
								
													} 	
												}
				
												if ( !(date_vec.size() == 0) ){
												
													editOption();
												
												} else { 
											
													infoText.setText("No Results Found");
													infoText.setVisible(true);
												
												}
						    
						
											} else {
						
												infoText.setText("No Results Found");
												infoText.setVisible(true);
						 
											}
					
										} else {
						
											infoText.setText("No Results Found");
											infoText.setVisible(true);
						
										}
						
									} else {
                                
										Statement statement = con.createStatement();
				
										ResultSet getInfo2 = statement.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where date='" + searchDate + "'" );
				
										if ( getInfo2 != null ) {
										
											infoText.setVisible(false);
										
											while ( getInfo2.next() ) {
						
												date3 = getInfo2.getString(1);
						
											}

											Statement statement2 = con.createStatement();
				
											ResultSet getInfo3 = statement2.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where staffOn_Leave='" + searchStaff + "'" );
			        
											if ( getInfo3 != null ) {
											
												infoText.setVisible(false);
						
												while ( getInfo3.next() ) {

													if ( getInfo3.getString(1).equals(date3) ) {
								
														date_vec.add(getInfo3.getString(1));
														leave_vec.add(getInfo3.getString(2));
														hour_vec.add(getInfo3.getString(3));
														class_vec.add(getInfo3.getString(4));
														adjust_vec.add(getInfo3.getString(5));
								
													} 	
												}
				
												if ( !(date_vec.size() == 0) ){
												
													editOption();
												
												} else { 
											
													infoText.setText("No Results Found");
													infoText.setVisible(true);
												}
						    
						
											} else {
						
												infoText.setText("No Results Found");
												infoText.setVisible(true);
						
											}
					
										} else {
						
											infoText.setText("No Results Found");
											infoText.setVisible(true);
						
										}

									}								
								}
						
							}
						
							break;
						
						}
				
					}
			
				} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
			
			} else if ( !classAdjust.equals(Ad_class2) ) {
			
				try {
			
					Statement st7 = con.createStatement();
			
					ResultSet rst2 = st7.executeQuery("select id,date,hour from staff_adjustment where staffOn_Leave ='" + LStaffName2 +"'");
			
					while ( rst2.next() ) {
				
						if ( LDate2.equals(rst2.getString(2)) && class_time3.equals(rst2.getString(3)) ) {
					
							Statement st8 = con.createStatement();
					
							int l = st8.executeUpdate("UPDATE staff_adjustment SET class = '" + classAdjust + "' WHERE id = '" + rst2.getInt(1) + "'");
	                
							if ( l == 1 ) {
						
								JOptionPane.showMessageDialog(this,"Record Changed!!","Message",JOptionPane.WARNING_MESSAGE);
							
								infoText2.setVisible(true);
								staffOnleave2.setVisible(false);
								date4.setVisible(false);
								time2.setVisible(false);
								Ad_Class2.setVisible(false);
								Ad_ClassTF2.setVisible(false);
								staffOnadjustment2.setVisible(false);
								staffOnadjustmentTF2.setVisible(false);
								edit_submit.setVisible(false);
								edit_cancel.setVisible(false);
							
								for ( int b=0; b<date_vec.size();b++) {
								
									date_label[b].setVisible(false);
									leave_label[b].setVisible(false);
									hour_label[b].setVisible(false);
									class_label[b].setVisible(false);
									adjust_label[b].setVisible(false);
									edit_button[b].setVisible(false);
									delete_button[b].setVisible(false);
								
								} 
							
								date_vec.clear();
								leave_vec.clear();
								hour_vec.clear();
								class_vec.clear();
								adjust_vec.clear();
							
								if ( searchDate.equals("") && searchStaff.equals("") ) {
						
									Statement statement20 = con.createStatement();
				
									ResultSet getInfo4 = statement20.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where date='" + date2 + "'" );
				
									if ( getInfo4 != null ) {
									
										infoText.setVisible(false);
									
										while ( getInfo4.next() ) {
						
											date3 = getInfo4.getString(1);
						
										}

										Statement statement21 = con.createStatement();
				
										ResultSet getInfo5 = statement21.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where staffOn_Leave='" + LStaffName2 + "'" );
			        
										if ( getInfo5 != null ) {
										
											infoText.setVisible(false);
						
											while ( getInfo5.next() ) {

												if ( getInfo5.getString(1).equals(date3) ) {
								
													date_vec.add(getInfo5.getString(1));
													leave_vec.add(getInfo5.getString(2));
													hour_vec.add(getInfo5.getString(3));
													class_vec.add(getInfo5.getString(4));
													adjust_vec.add(getInfo5.getString(5));
								
												} 	
											}
				
											if ( !(date_vec.size() == 0) ){
											
												editOption();
											
											} else { 
										
												infoText.setText("No Results Found");
												infoText.setVisible(true);
											
											}
						    
						
										} else {
						
											infoText.setText("No Results Found");
											infoText.setVisible(true);
						
										}
					
									} else {
						
										infoText.setText("No Results Found");
										infoText.setVisible(true);
						
									}
				
								} else {

									Statement statement20 = con.createStatement();
				
									ResultSet getInfo4 = statement20.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where date='" + searchDate + "'" );
				
									if ( getInfo4 != null ) {
									
										infoText.setVisible(false);
									
										while ( getInfo4.next() ) {
						
											date3 = getInfo4.getString(1);
						
										}

										Statement statement21 = con.createStatement();
				
										ResultSet getInfo5 = statement21.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where staffOn_Leave='" + searchStaff + "'" );
			        
										if ( getInfo5 != null ) {
										
											infoText.setVisible(false);
						
											while ( getInfo5.next() ) {

												if ( getInfo5.getString(1).equals(date3) ) {
								
													date_vec.add(getInfo5.getString(1));
													leave_vec.add(getInfo5.getString(2));
													hour_vec.add(getInfo5.getString(3));
													class_vec.add(getInfo5.getString(4));
													adjust_vec.add(getInfo5.getString(5));
								
												} 	
											}
				
											if ( !(date_vec.size() == 0) ){
											
												editOption();
											
											} else { 
										
												infoText.setText("No Results Found");
												infoText.setVisible(true);
											
											}
					
										} else {
						
											infoText.setText("No Results Found");
											infoText.setVisible(true);
						
										}
					
									} else {
						
										infoText.setText("No Results Found");
										infoText.setVisible(true);
						
									}

								}								
							}
					
						}
				
					}
			
				} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
			
			} else if ( !adStaff.equals(AStaffName2) ) {
			
				try {
			
					Statement st13 = con.createStatement();
			
					ResultSet rst4 = st13.executeQuery("select id,date,hour from staff_adjustment where staffOn_Leave ='" + LStaffName2 +"'");
			
					while ( rst4.next() ) {
				
						if ( LDate2.equals(rst4.getString(2)) && class_time3.equals(rst4.getString(3)) ) {
					
							Statement st14 = con.createStatement();
					
							int n = st14.executeUpdate("UPDATE staff_adjustment SET staffOn_adjustment = '" + adStaff + "' WHERE id = '" + rst4.getInt(1) + "'");
	               
							if ( n == 1 ) {
			
								Statement st9 = con.createStatement();
						
								ResultSet getReport6 = st9.executeQuery("select gain,loss from staff_report where staff_name='" + AStaffName2 + "'");
								
								while ( getReport6.next() ) {
									
									gain = getReport6.getInt(1);
									loss = getReport6.getInt(2);
									
								}
						
								gain = gain - 1;
								total = gain - loss;
						
								Statement st10 = con.createStatement();
						
								st10.executeUpdate("UPDATE staff_report SET gain = '" + gain + "', loss = '" + loss + "', Total = '" + total + "' WHERE staff_name = '" + AStaffName2 + "'"); 
                        
								gain=0;total=0;loss=0;
			
								Statement st11 = con.createStatement();
						
								ResultSet getReport7 = st11.executeQuery("select gain,loss from staff_report where staff_name='" + adStaff + "'");
								
								while ( getReport7.next() ) {
									
									gain = getReport7.getInt(1);
									loss = getReport7.getInt(2);
									
								}
						
								gain = gain + 1;
								total = gain - loss;
					
								Statement st12 = con.createStatement();	 
				
								int m = st12.executeUpdate("UPDATE staff_report SET gain = '" + gain + "', loss = '" + loss + "', Total = '" + total + "' WHERE staff_name = '" + adStaff + "'");						
			
								gain=0;loss=0;total=0;			
			
								if ( m == 1 ) {
							
									JOptionPane.showMessageDialog(this,"Record Changed!!","Message",JOptionPane.WARNING_MESSAGE);
								
									infoText2.setVisible(true);
									staffOnleave2.setVisible(false);
									date4.setVisible(false);
									time2.setVisible(false);
									Ad_Class2.setVisible(false);
									Ad_ClassTF2.setVisible(false);
									staffOnadjustment2.setVisible(false);
									staffOnadjustmentTF2.setVisible(false);
									edit_submit.setVisible(false);
									edit_cancel.setVisible(false);
							
									for ( int a=0; a<date_vec.size();a++) {
								
										date_label[a].setVisible(false);
										leave_label[a].setVisible(false);
										hour_label[a].setVisible(false);
										class_label[a].setVisible(false);
										adjust_label[a].setVisible(false);
										edit_button[a].setVisible(false);
										delete_button[a].setVisible(false);
								
									}
							
									date_vec.clear();
									leave_vec.clear();
									hour_vec.clear();
									class_vec.clear();
									adjust_vec.clear();
							
									if ( searchDate.equals("") && searchStaff.equals("") ) {
			    
										Statement statement22 = con.createStatement();
				
										ResultSet getInfo8 = statement22.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where date='" + date2 + "'" );
				
										if ( getInfo8 != null ) {
										
											infoText.setVisible(false);
										
											while ( getInfo8.next() ) {
						
												date3 = getInfo8.getString(1);
						
											}
 
											Statement statement23 = con.createStatement();
				
											ResultSet getInfo9 = statement23.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where staffOn_Leave='" + LStaffName2 + "'" );
			        
											if ( getInfo9 != null ) {
											
												infoText.setVisible(false);
						
												while ( getInfo9.next() ) {

													if ( getInfo9.getString(1).equals(date3) ) {
								
														date_vec.add(getInfo9.getString(1));
														leave_vec.add(getInfo9.getString(2));
														hour_vec.add(getInfo9.getString(3));
														class_vec.add(getInfo9.getString(4));
														adjust_vec.add(getInfo9.getString(5));
								
													} 	
												}
				
												if ( !(date_vec.size() == 0) ){
											
													editOption();
											
												} else { 
										
													infoText.setText("No Results Found");
													infoText.setVisible(true);
												}
								
											} else {
						
												infoText.setText("No Results Found");
												infoText.setVisible(true);
						
											}
					
										} else {
						
											infoText.setText("No Results Found");
											infoText.setVisible(true);
						
										}
				
									} else {
							  
										Statement statement22 = con.createStatement();
				
										ResultSet getInfo8 = statement22.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where date='" + searchDate + "'" );
				 
										if ( getInfo8 != null ) {
									
											infoText.setVisible(false);
									
											while ( getInfo8.next() ) {
						
												date3 = getInfo8.getString(1);
						
											}

											Statement statement23 = con.createStatement();
				
											ResultSet getInfo9 = statement23.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where staffOn_Leave='" + searchStaff + "'" );
			        
											if ( getInfo9 != null ) {
										
												infoText.setVisible(false);
						
												while ( getInfo9.next() ) {

													if ( getInfo9.getString(1).equals(date3) ) {
								
														date_vec.add(getInfo9.getString(1));
														leave_vec.add(getInfo9.getString(2));
														hour_vec.add(getInfo9.getString(3));
														class_vec.add(getInfo9.getString(4));
														adjust_vec.add(getInfo9.getString(5));
								
													} 	
												}
				
												if ( !(date_vec.size() == 0) ){
											
													editOption();
											
												} else { 
										
													infoText.setText("No Results Found");
													infoText.setVisible(true);
											
												}
						  
											} else {
						
												infoText.setText("No Results Found");
												infoText.setVisible(true);
						
											}
					
										} else {
						
											infoText.setText("No Results Found");
											infoText.setVisible(true);
						
										}

									}            							  
								}
			
							}
		
						}
	
					}
			
				} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
		
			} 
		
		} 
	
		if ( ae.getSource() == edit_cancel ) {
		
			Ad_ClassTF2.setText(Ad_class2);
			staffOnadjustmentTF2.setText(AStaffName2);
		
		}
	
		if ( ae.getSource() == report_btn ) {
		
			panel_home.setVisible(false);
			panel_edit.setVisible(false);
		
			panel_report.setVisible(true);
			panel_report.add(panel_option);
		
			report_searchTF.setText(" Search Staff");
		
			if ( check3 == true ) {
			
				for ( int j=0; j<staff_vec.size();j++) {
			
					staff_label[j].setVisible(false);
					gain_label[j].setVisible(false);
					loss_label[j].setVisible(false);
					total_label[j].setVisible(false);
				}
			}
		
			staff_vec.clear();
			gain_vec.clear();
			loss_vec.clear();
			total_vec.clear();
		
			try {
		
				Statement st1 = con.createStatement();
		
				ResultSet rst1 = st1.executeQuery("select staff_name,gain,loss,Total from staff_report");
		
				if ( rst1 != null ) {
		
		            infoText3.setVisible(false);
					
					while ( rst1.next() ) {
			
						staff_vec.add(rst1.getString(1));
						gain_vec.add(rst1.getInt(2));
						loss_vec.add(rst1.getInt(3));
						total_vec.add(rst1.getInt(4));
			
					}
					if ( staff_vec.size() != 0 ) {
				
						reportOption();
					
					} else {
							
						infoText3.setText("No Results Found.");
						infoText3.setVisible(true);
					
					}
					
				} else {
			
					infoText3.setVisible(true);
			
				}
			
			} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }			
		
		}
	
		if ( ae.getSource() == report_searchBtn ) {
		
			String search_staff = report_searchTF.getText().toString().trim().toUpperCase();
		
			if ( search_staff.equals("SEARCH STAFF") ) {
			
				report_searchTF.setBorder(BorderFactory.createLineBorder(Color.red,1));
			
			} else {
				
				infoText3.setVisible(false);
				
				for ( int j=0; j<staff_vec.size();j++) {
			
					staff_label[j].setVisible(false);
					gain_label[j].setVisible(false);
					loss_label[j].setVisible(false);
					total_label[j].setVisible(false);
				}
		
				staff_vec.clear();
				gain_vec.clear();
				loss_vec.clear();
				total_vec.clear();
			
				try {
					
					Statement st1 = con.createStatement();
			
					ResultSet rst1 = st1.executeQuery("select staff_name,gain,loss,Total from staff_report where staff_name = '" + search_staff + "'");
			
					if ( rst1 != null ) {
				
				        infoText3.setVisible(false);
				
						while ( rst1.next() ) {
					
							staff_vec.add(rst1.getString(1));
							gain_vec.add(rst1.getInt(2));
							loss_vec.add(rst1.getInt(3));
							total_vec.add(rst1.getInt(4));
					
						}
						if ( staff_vec.size() != 0 ) {
				
							reportOption();
					
						} else {
							
							infoText3.setText("No Results Found.");
							infoText3.setVisible(true);
					
						}
				
					} else {
				
						infoText3.setText("No Results Found.");
						infoText3.setVisible(true);
				
					}
			
				} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
			
			}
		
		}
		
		if ( ae.getSource() == report_sort ) {
		
			if ( a%2 == 0 ) {
			
				panel_sort.setVisible(false);
				report_sort.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/RightArrow.png"));
				a++;
			
			} else {
			
				panel_sort.setVisible(true);
				report_sort.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/LeftArrow.png"));
				a++; 
			}
		
		}
	
		if ( ae.getSource() == sort_none ) {
		
			panel_sort.setVisible(false);
			a++;
			infoText3.setVisible(false);
			report_searchTF.setText(" Search Staff");
			report_sort.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/RightArrow.png"));
			
			if ( check3 == true ) {
			
				for ( int j=0; j<staff_vec.size();j++) {
			
					staff_label[j].setVisible(false);
					gain_label[j].setVisible(false);
					loss_label[j].setVisible(false);
					total_label[j].setVisible(false);
				}
			}
		
			staff_vec.clear();
			gain_vec.clear();
			loss_vec.clear();
			total_vec.clear();
		
			try {
		
				Statement st1 = con.createStatement();
		
				ResultSet rst1 = st1.executeQuery("select staff_name,gain,loss,Total from staff_report");
		
				if ( rst1 != null ) {
		
					while ( rst1.next() ) {
			
						staff_vec.add(rst1.getString(1));
						gain_vec.add(rst1.getInt(2));
						loss_vec.add(rst1.getInt(3));
						total_vec.add(rst1.getInt(4));
			
					}
		            if ( staff_vec.size() != 0 ) {
				
						reportOption();
					
					} else {
						
						infoText3.setText("No Results Found.");
						infoText3.setVisible(true);
					
					}
					
				} else {
			
					infoText3.setVisible(true);
			
				}
			
			} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
			
		}
	
		if ( ae.getSource() == sort_AtoZ ) {
			
			panel_sort.setVisible(false);
			a++;
			report_searchTF.setText(" Search Staff");
			report_sort.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/RightArrow.png"));
			infoText3.setVisible(false);
			
			for ( int j=0; j<staff_vec.size();j++) {
			
			    staff_label[j].setVisible(false);
			    gain_label[j].setVisible(false);
			    loss_label[j].setVisible(false);
			    total_label[j].setVisible(false);
		    }
		
		    staff_vec.clear();
		    gain_vec.clear();
		    loss_vec.clear();
		    total_vec.clear();
			
			try {
				
				Statement st1 = con.createStatement();
			
				ResultSet rst1 = st1.executeQuery("select staff_name,gain,loss,Total from staff_report order by staff_name");
			
				if ( rst1 != null ) {
				
					while ( rst1.next() ) {
					
						staff_vec.add(rst1.getString(1));
						gain_vec.add(rst1.getInt(2));
						loss_vec.add(rst1.getInt(3));
						total_vec.add(rst1.getInt(4));
					
					}
					if ( staff_vec.size() != 0 ) {
				
						reportOption();
					
					} else {
						
						infoText3.setText("No Results Found.");
						infoText3.setVisible(true);
					
					}
				
				} else {
				
					infoText3.setText("No Results Found.");
					infoText3.setVisible(true);
				
				}
			
			} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
		
		}
	
		if ( ae.getSource() == sort_ZtoA ) {
		
			panel_sort.setVisible(false);
			a++;
			report_searchTF.setText(" Search Staff");
			report_sort.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/RightArrow.png"));
			infoText3.setVisible(false);
			
			for ( int j=0; j<staff_vec.size();j++) {
			
			    staff_label[j].setVisible(false);
			    gain_label[j].setVisible(false);
			    loss_label[j].setVisible(false);
			    total_label[j].setVisible(false);
		    }
		
		    staff_vec.clear();
		    gain_vec.clear();
		    loss_vec.clear();
		    total_vec.clear();
			
			try {
				
				Statement st1 = con.createStatement();
			
				ResultSet rst1 = st1.executeQuery("select staff_name,gain,loss,Total from staff_report order by staff_name desc");
			
				if ( rst1 != null ) {
				
					while ( rst1.next() ) {
					
						staff_vec.add(rst1.getString(1));
						gain_vec.add(rst1.getInt(2));
						loss_vec.add(rst1.getInt(3));
						total_vec.add(rst1.getInt(4));
					
					}
					if ( staff_vec.size() != 0 ) {
				
						reportOption();
					
					} else {
						
						infoText3.setText("No Results Found.");
						infoText3.setVisible(true);
					
					}
				
				} else {
				
					infoText3.setText("No Results Found.");
					infoText3.setVisible(true);
				
				}
			
			} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
			
		}
	
		if ( ae.getSource() == sort_byGain ) {
		
			panel_sort.setVisible(false);
			a++;
			report_searchTF.setText(" Search Staff");
			report_sort.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/RightArrow.png"));
			infoText3.setVisible(false);
			
			for ( int j=0; j<staff_vec.size();j++) {
			
			    staff_label[j].setVisible(false);
			    gain_label[j].setVisible(false);
			    loss_label[j].setVisible(false);
			    total_label[j].setVisible(false);
		    }
		
		    staff_vec.clear();
		    gain_vec.clear();
		    loss_vec.clear();
		    total_vec.clear();
			
			try {
				
				Statement st1 = con.createStatement();
			
				ResultSet rst1 = st1.executeQuery("select staff_name,gain,loss,Total from staff_report order by gain desc");
			
				if ( rst1 != null ) {
				
					while ( rst1.next() ) {
					
						staff_vec.add(rst1.getString(1));
						gain_vec.add(rst1.getInt(2));
						loss_vec.add(rst1.getInt(3));
						total_vec.add(rst1.getInt(4));
					
					}
					if ( staff_vec.size() != 0 ) {
				
						reportOption();
					
					} else {
						infoText3.setText("No Results Found.");
						infoText3.setVisible(true);
					
					}
				
				} else {
				
					infoText3.setText("No Results Found.");
					infoText3.setVisible(true);
				
				}
			
			} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
			
		}
	
		if ( ae.getSource() == sort_byLoss ) {
		
			panel_sort.setVisible(false);
			a++;
			report_searchTF.setText(" Search Staff");
			report_sort.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/RightArrow.png"));
			infoText3.setVisible(false);
			
			for ( int j=0; j<staff_vec.size();j++) {
			
			    staff_label[j].setVisible(false);
			    gain_label[j].setVisible(false);
			    loss_label[j].setVisible(false);
			    total_label[j].setVisible(false);
		    }
		
		    staff_vec.clear();
		    gain_vec.clear();
		    loss_vec.clear();
		    total_vec.clear();
			
			try {
				
				Statement st1 = con.createStatement();
			
				ResultSet rst1 = st1.executeQuery("select staff_name,gain,loss,Total from staff_report order by loss desc");
			
				if ( rst1 != null ) {
				
					while ( rst1.next() ) {
					
						staff_vec.add(rst1.getString(1));
						gain_vec.add(rst1.getInt(2));
						loss_vec.add(rst1.getInt(3));
						total_vec.add(rst1.getInt(4));
					
					}
					if ( staff_vec.size() != 0 ) {
				
						reportOption();
					
					} else {
						
						infoText3.setText("No Results Found.");
						infoText3.setVisible(true);
					
					}
				
				} else {
				
					infoText3.setText("No Results Found.");
					infoText3.setVisible(true);
				
				}
			
			} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
		
		}
	
		if ( ae.getSource() == sort_byTotal ) {
		
			panel_sort.setVisible(false);
			a++;
			report_searchTF.setText(" Search Staff");
			report_sort.setIcon(new ImageIcon("D:/JDK 8/bin/work_adjustmentPics/RightArrow.png"));
			infoText3.setVisible(false);
			
			for ( int j=0; j<staff_vec.size();j++) {
			
			    staff_label[j].setVisible(false);
			    gain_label[j].setVisible(false);
			    loss_label[j].setVisible(false);
			    total_label[j].setVisible(false);
		    }
		
		    staff_vec.clear();
		    gain_vec.clear();
		    loss_vec.clear();
		    total_vec.clear();
			
			try {
				
				Statement st1 = con.createStatement();
			
				ResultSet rst1 = st1.executeQuery("select staff_name,gain,loss,Total from staff_report order by Total desc");
			
				if ( rst1 != null ) {
				
					while ( rst1.next() ) {
					
						staff_vec.add(rst1.getString(1));
						gain_vec.add(rst1.getInt(2));
						loss_vec.add(rst1.getInt(3));
						total_vec.add(rst1.getInt(4));
					
					}
					if ( staff_vec.size() != 0 ) {
				
						reportOption();
					
					} else {
						
						infoText3.setText("No Results Found.");
						infoText3.setVisible(true);
					
					}
				
				} else {
				
					infoText3.setText("No Results Found.");
					infoText3.setVisible(true);
				
				}
			
			} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
		
		}
	
		if ( ae.getSource() == profile_btn ) {
			
			try {
				
				Statement st1 = con.createStatement();
		
				ResultSet rst1 = st1.executeQuery("select staff_name,initial,department from staff_entry where initial='" + login_username + "'");
		
				while ( rst1.next() ) {
			
					profile_StaffName.setText(rst1.getString(1));
					profile_StaffInitial.setText("Initial : " + rst1.getString(2));
					profile_Staffdept.setText("Department : " + rst1.getString(3));
			
				}
		
				for ( int b=0; b<date_vec2.size();b++) {
								
					date_label2[b].setVisible(false);
			        leave_label2[b].setVisible(false);
			        hour_label2[b].setVisible(false);
			        class_label2[b].setVisible(false);
			        adjust_label2[b].setVisible(false);
			                   
								
				} 
							
				date_vec2.clear();
			    leave_vec2.clear();
			    hour_vec2.clear();
			    class_vec2.clear();
			    adjust_vec2.clear();
		
				Statement st2 = con.createStatement();
		
				ResultSet rst2 = st2.executeQuery("select gain,loss,Total from staff_report where staff_name='" + login_username +"'");
		
				while ( rst2.next() ) {
			
					profile_gain.setText("Gain : " + rst2.getInt(1));
					profile_loss.setText("Loss : " + rst2.getInt(2));
					profile_total.setText("Total : " + rst2.getInt(3));
			
				}
		
				Date = new Date();
				date5 = formatter.format(Date);
		
				Statement st3 = con.createStatement();
		
				ResultSet rst3 = st3.executeQuery("select id,staffOn_adjustment from staff_adjustment where date='" + date5 +"'");
	    
				if ( rst3 != null ) {
					
					infoText4.setVisible(false);
					
					while ( rst3.next() ) {
				
						if ( login_username.equals(rst3.getString(2)) ) {
				
							Statement st4 = con.createStatement();
		
							ResultSet rst4 = st4.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where id='" + rst3.getInt(1) +"'");

							if ( rst4 != null ) {
								
								infoText4.setVisible(false);
								
								while ( rst4.next() ) {
						
									date_vec2.add(rst4.getString(1));
									leave_vec2.add(rst4.getString(2));
									hour_vec2.add(rst4.getString(3));
									class_vec2.add(rst4.getString(4));
									adjust_vec2.add(rst4.getString(5));
						
								}
					
							} else {
					
								infoText4.setText("No Adjustments.");
								infoText4.setVisible(true);
					
							}
				
						}
                				
					}
					
					if ( date_vec2.size() == 0 ) {
						
						infoText4.setText("No Adjustments.");
						infoText4.setVisible(true);
						
					} else {
				
						profileOption();
				
					}
			
				} else {
			
					infoText4.setText("No Adjustments.");
					infoText4.setVisible(true);
			
				}
		
			} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
		
				panel_home.setVisible(false);
				panel_edit.setVisible(false);
				panel_report.setVisible(false);
		
				panel_profile.setVisible(true);
		
		}
	
		if ( ae.getSource() == profileBack_btn ) {
		
			panel_profile.setVisible(false);
			panel_edit.setVisible(false);
			panel_report.setVisible(false);
		
			panel_home.setVisible(true);
			panel_home.add(panel_option);
		
		}
	
		if ( ae.getSource() == logout_btn ) {
		
			int n = JOptionPane.showConfirmDialog(jframe,"Are sure to logout?","Warning",JOptionPane.YES_NO_OPTION);
							
			if( n == JOptionPane.YES_OPTION) {
		
				panel_cover.setVisible(false);
		    
				usernameTF.setText("");
				passwordTF.setText("");
				staffOnleaveTF.setText("");
				staffOnadjustmentTF.setText("");
				staffOnadjustmentTF.setText("");
				dateTF.setText(" dd-mm-yyyy");
				timeTF.setText(" hh-hh");
				Ad_ClassTF.setText("");
				Ad_ClassTF2.setText("");
			
				search_byDateTF.setText(" Search by Date");
				search_byStaffTF.setText(" Search by Staff");
				report_searchTF.setText(" Search Staff");
			
				panel_login.setVisible(true);
		
			}
		
		}
	
		if ( ae.getSource() == exit_btn ) {
		
			int n = JOptionPane.showConfirmDialog(jframe,"Are sure to exit?","Warning",JOptionPane.YES_NO_OPTION);
							
			if( n == JOptionPane.YES_OPTION) {
				
				try {
				
					con.close();
					dispose();
				
				} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); } 
			
			}
		
		}
				
	}
	
	public void mouseEntered(MouseEvent me) {
		
		if ( me.getSource() == home_btn ) {
			
			home_btn.setToolTipText("Home");
			
		}
		
		if ( me.getSource() == edit_btn ) {
			
			edit_btn.setToolTipText("Edit");
			
		}
		
		if ( me.getSource() == report_btn ) {
			
			report_btn.setToolTipText("Report");
			
		}
		
		if ( me.getSource() == exit_btn ) {
			
			exit_btn.setToolTipText("Exit");
			
		}
		
		
	}
	public void mouseExited(MouseEvent me2) {
		
		
	}
    public void mouseReleased(MouseEvent me3) {
		
		if ( me3.getSource() == eyeOpen ) {
			
			eyeOpen.setVisible(false);
			eyeClose.setVisible(true);
			
			passwordTF.setEchoChar(defaultEye);
			
		}
		
		if ( me3.getSource() == eyeClose ) {
			
			eyeOpen.setVisible(true);
			eyeClose.setVisible(false);
			
			passwordTF.setEchoChar((char)0);
			
		}
		
	}
	public void mouseClicked(MouseEvent me4) {
	
    }
    public void mousePressed(MouseEvent me5) {
		
	}
	
	public void connect_database() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/staff-work_adjustment","root","");
		
		    databaseMetaData = con.getMetaData();
		
		    statement = con.createStatement();
			
		} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
		
	}
	
	public void login() {
		
		try {
			
			Statement statement2 = con.createStatement();
		    
			checker = statement2.executeQuery("select username,password from staff_entry where username= '" + login_username + "'");
			
				while ( checker.next() ) {	
				    
					username = checker.getString(1);
					password = checker.getString(2);
					
				}
                	
				if ( !login_username.equals(username) && !login_password.equals(password) ) {
				    
					usernameTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
					error_msg1.setText("Username Incorrect");
				    error_msg1.setVisible(true);
					
					passwordTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
				    error_msg2.setText("Password Incorrect");
				    error_msg2.setVisible(true);
					
					
			    } else if ( !login_password.equals(password) ) {
					
					passwordTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
					error_msg2.setText("Password Incorrect");
				    error_msg2.setVisible(true);
					
					
				} else if ( !login_username.equals(username) ) {
					
					usernameTF.setBorder(BorderFactory.createLineBorder(Color.red,1,true));
					error_msg1.setText("Username Incorrect");
				    error_msg1.setVisible(true);
					
					
				}else if ( login_password.equals(password) ) {
					
					JOptionPane.showMessageDialog(this,"Login Successfully!!","Message",JOptionPane.WARNING_MESSAGE);
				
				    panel_cover.setVisible(true);
					panel_option.setVisible(true);
				    panel_login.setVisible(false);
					
				}
						
		} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); } 
		
	}
	
	public void staffAdjustment() {
		
		try {

            ResultSet adjust = statement.executeQuery("select initial from staff_entry where initial ='" + staff_leave + "'" );
                
            if ( adjust.next() ) {
				
			    Statement statement3 = con.createStatement();
					
				ResultSet adjust2 = statement3.executeQuery("select initial from staff_entry where initial ='" + staff_adjust + "'" );
					
				if ( adjust2.next() ) {
						
                    Statement statement4 = con.createStatement();
					
					String query = "insert into staff_adjustment(staffOn_Leave,date,hour,class,staffOn_adjustment) values ('" + staff_leave + "','" + leave_date + "','" + class_time + "','" + className + "','" + staff_adjust + "')";
						
                    int i = statement4.executeUpdate(query);

                    if ( i == 1 ) {
                        
						Statement statement5 = con.createStatement();
						
						ResultSet staffCheck = statement5.executeQuery("select staff_name from staff_report where staff_name='" + staff_leave + "'");
                        
                        if ( staffCheck.next() ) {

                            Statement statement6 = con.createStatement();

                            ResultSet staffCheck2 = statement6.executeQuery("select staff_name from staff_report where staff_name='" + staff_adjust + "'");

                            if ( staffCheck2.next() ) {

                                Statement statement7 = con.createStatement();

                                ResultSet getReport = statement7.executeQuery("select gain,loss from staff_report where staff_name='" + staff_leave + "'");
								
								while ( getReport.next() ) {
									
									gain = getReport.getInt(1);
									loss = getReport.getInt(2);
									
								}
								loss = loss + 1;
								total = gain - loss;
								
								Statement statement8 = con.createStatement();
								
								statement8.executeUpdate("UPDATE staff_report SET gain = '" + gain + "', loss = '" + loss + "', Total = '" + total + "' WHERE staff_name = '" + staff_leave + "'"); 
                                
                                gain=0; loss=0; total=0;
                                
								Statement statement9 = con.createStatement();
								
                                ResultSet getReport2 = statement9.executeQuery("select gain,loss from staff_report where staff_name='" + staff_adjust + "'");
                                
                                while ( getReport2.next() ) {

                                    gain = getReport2.getInt(1);
                                    loss = getReport2.getInt(2);
									
								}									
						        gain = gain + 1;
								total  = gain - loss;
								
								Statement statement10 = con.createStatement();
								
								statement10.executeUpdate("UPDATE staff_report SET gain = '" + gain + "', loss = '" + loss + "', Total = '" + total + "' WHERE staff_name = '" + staff_adjust + "'"); 
                                
                                gain=0; loss=0; total=0;
								
                                JOptionPane.showMessageDialog(this,"Details Added Successfully!!","Message",JOptionPane.WARNING_MESSAGE);
						
						        staffOnleaveTF.setText(""); staffOnadjustmentTF.setText(""); dateTF.setText(" dd-mm-yyyy");
                                timeTF.setText(" hh-hh"); Ad_ClassTF.setText("");						
                            
							} else {
								
								Statement statement11 = con.createStatement();
								
								gain = 1;
								total = gain + loss;
								
								statement11.executeUpdate("insert into staff_report(staff_name,gain,loss,Total) values ('" + staff_adjust + "','" + gain + "','" + loss + "','" + total + "')");
								
								gain=0; total=0;
								
								Statement statement12 = con.createStatement();
								
								ResultSet getReport3 = statement12.executeQuery("select gain,loss from staff_report where staff_name='" + staff_leave + "'");
								
								while ( getReport3.next() ) {
									
									gain = getReport3.getInt(1);
									loss = getReport3.getInt(2);
									
								}
								
								loss = loss + 1;
								total = gain - loss;
								
								Statement statement13 = con.createStatement();
							
								statement13.executeUpdate("UPDATE staff_report SET gain = '" + gain + "', loss = '" + loss + "', Total = '" + total + "' WHERE staff_name = '" + staff_leave + "'"); 
                                
                                gain=0; loss=0; total=0;
								
								JOptionPane.showMessageDialog(this,"Details Added Successfully!!","Message",JOptionPane.WARNING_MESSAGE);
						
						        staffOnleaveTF.setText(""); staffOnadjustmentTF.setText(""); dateTF.setText(" dd-mm-yyyy");
                                timeTF.setText(" hh-hh"); Ad_ClassTF.setText("");
									
							}
							
						} else {
							
							Statement statement14 = con.createStatement();
								
							loss = 1;
							total = gain - loss;
							
							statement14.executeUpdate("insert into staff_report(staff_name,gain,loss,Total) values ('" + staff_leave + "','" + gain + "','" + loss + "','" + total + "')");
								
							loss=0; total=0;
									
							Statement statement15 = con.createStatement();
							
                            ResultSet staffCheck3 = statement15.executeQuery("select staff_name from staff_report where staff_name='" + staff_adjust + "'");

                            if ( staffCheck3.next() ) {
								
								Statement statement16 = con.createStatement();
								
							    ResultSet getReport4 = statement16.executeQuery("select gain,loss from staff_report where staff_name='" + staff_adjust + "'");
								
							    while ( getReport4.next() ) {
									
								    gain = getReport4.getInt(1);
								    loss = getReport4.getInt(2);
									
							    }
							    gain = gain + 1;
							    total = gain - loss;
								
								Statement statement17 = con.createStatement();
								
							    statement17.executeUpdate("UPDATE staff_report SET gain = '" + gain + "', loss = '" + loss + "', Total = '" + total + "' WHERE staff_name = '" + staff_adjust + "'"); 
                                
                                gain=0; loss=0; total=0;
								
								JOptionPane.showMessageDialog(this,"Details Added Successfully!!","Message",JOptionPane.WARNING_MESSAGE);
						
						        staffOnleaveTF.setText(""); staffOnadjustmentTF.setText(""); dateTF.setText(" dd-mm-yyyy");
                                timeTF.setText(" hh-hh"); Ad_ClassTF.setText("");
								
							} else {
								
								Statement statement18 = con.createStatement();
								
							    gain = 1;
							    total = gain - loss;
								
							    statement18.executeUpdate("insert into staff_report(staff_name,gain,loss,Total) values ('" + staff_adjust + "','" + gain + "','" + loss + "','" + total + "')");
								
							    gain=0; total=0;
								
								JOptionPane.showMessageDialog(this,"Details Added Successfully!!","Message",JOptionPane.WARNING_MESSAGE);
						
						        staffOnleaveTF.setText(""); staffOnadjustmentTF.setText(""); dateTF.setText(" dd-mm-yyyy");
                                timeTF.setText(" hh-hh"); Ad_ClassTF.setText("");
							}
					    }
						
					}
                } else {
                    
					staffOnadjustmentTF.setBorder(BorderFactory.createLineBorder(Color.red,1));
                    error_msg7.setText("Staff Not Found");
                    error_msg7.setVisible(true);
                }
			} else {
                
				staffOnleaveTF.setBorder(BorderFactory.createLineBorder(Color.red,1));
                error_msg3.setText("Staff Not Found");
				error_msg3.setVisible(true);				
			}
			
		} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
		
	}
	
	public void editOption() {
		  
		for ( int i=0;i<date_vec.size();i++){
					
		    String LStaffName = leave_vec.get(i);
			String AStaffName = adjust_vec.get(i);
			String LDate = date_vec.get(i);
			String class_time2 = hour_vec.get(i);
			String Ad_class = class_vec.get(i);
					
			date_label[i] = new JLabel("Date : " + date_vec.get(i));
			date_label[i].setBounds(20+((0*40)+10),10+((i*80)+30),200,30);
			date_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			date_label[i].setBackground(Color.white);
					
			leave_label[i] = new JLabel("Leave Staff : " + leave_vec.get(i));
			leave_label[i].setBounds(20+((0*40)+160),10+((i*80)+30),200,30);
			leave_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			leave_label[i].setBackground(Color.white);
					
			hour_label[i] = new JLabel("Hour : " + hour_vec.get(i));
			hour_label[i].setBounds(20+((0*40)+300),10+((i*80)+30),100,30);
			hour_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			hour_label[i].setBackground(Color.white);
					
			class_label[i] = new JLabel("Class : " + class_vec.get(i));
			class_label[i].setBounds(20+((0*40)+10),10+((i*80)+60),200,30);
			class_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			class_label[i].setBackground(Color.white);
					
			adjust_label[i] = new JLabel("Adjustment Staff : " + adjust_vec.get(i));
			adjust_label[i].setBounds(20+((0*40)+180),10+((i*80)+60),300,30);
			adjust_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			adjust_label[i].setBackground(Color.white);
					
			String date = date_vec.get(i);
					
			String day = date.substring(0,2);
			int day2 = Integer.parseInt(day);
			
			String month = date.substring(3,5);
			int month2 = Integer.parseInt(month);
			
			String year = date.substring(6,date.length());
			int year2 = Integer.parseInt(year);
					
			String day3 = date5.substring(0,2);
			int day4 = Integer.parseInt(day3);
					
			String month3 = date5.substring(3,5);
			int month4 = Integer.parseInt(month3);
			
			String year3 = date5.substring(6,date.length());
			int year4 = Integer.parseInt(year3);
					
			if ( (day2 < day4) || (month2 < month4) || (year2 < year4) ) {
						
				edit_button[i] = new JButton("EDIT");
				edit_button[i].setBounds(20+((0*40)+430),10+((i*80)+50),60,20);
				edit_button[i].setFont(new Font("Times New Roman",Font.BOLD,12));
				edit_button[i].setForeground(Color.red);
				edit_button[i].setBackground(Color.white);
				edit_button[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				edit_button[i].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
				edit_button[i].setFocusPainted(false);
				edit_button[i].setEnabled(false);
				edit_button[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
							
						infoText2.setVisible(false);
						staffOnleave2.setVisible(false);
						date4.setVisible(false);
						time2.setVisible(false);
						Ad_Class2.setVisible(false);
						Ad_ClassTF2.setVisible(false);
						staffOnadjustment2.setVisible(false);
						staffOnadjustmentTF2.setVisible(false);
						edit_submit.setVisible(false);
						edit_cancel.setVisible(false);
							
						staffOnleave2.setText(" Staff On Leave/Duty :      " + LStaffName);
						staffOnleave2.setVisible(true);
							
						date4.setVisible(true);
						date4.setText(" Date on Leave/Duty :      " + LDate);
						
						time2.setVisible(true);
						time2.setText("              Time :      "+ class_time2);
							
						Ad_Class2.setVisible(true);
						Ad_ClassTF2.setText(Ad_class);
						Ad_ClassTF2.setVisible(true);
							
						staffOnadjustment2.setVisible(true);
						staffOnadjustmentTF2.setText(AStaffName);
						staffOnadjustmentTF2.setVisible(true);
							
						edit_submit.setVisible(true);
						edit_cancel.setVisible(true);
							
						LStaffName2 = LStaffName;
					    AStaffName2 = AStaffName;
					    LDate2 = LDate;
					    class_time3 = class_time2;
					    Ad_class2 = Ad_class;
							
					}
						
				});
					
				delete_button[i] = new JButton("DELETE");
				delete_button[i].setBounds(20+((0*40)+490),10+((i*80)+50),80,20);
				delete_button[i].setFont(new Font("Times New Roman",Font.BOLD,12));
				delete_button[i].setForeground(Color.red);
				delete_button[i].setBackground(Color.white);
				delete_button[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				delete_button[i].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
				delete_button[i].setFocusPainted(false);
				delete_button[i].setEnabled(false);
				delete_button[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae){
						    
						int n = JOptionPane.showConfirmDialog(jframe,"Are sure to delete this record?","Warning",JOptionPane.YES_NO_OPTION);
							
						if( n == JOptionPane.YES_OPTION) {
								
							LStaffName2 = LStaffName;
					        AStaffName2 = AStaffName;
					        LDate2 = LDate;
					        class_time3 = class_time2;
					        Ad_class2 = Ad_class;
								
							deleteOption();
								
						}
						
					}
				});
					
			} else {
						
				edit_button[i] = new JButton("EDIT");
				edit_button[i].setBounds(20+((0*40)+430),10+((i*80)+50),60,20);
				edit_button[i].setFont(new Font("Times New Roman",Font.BOLD,12));
				edit_button[i].setForeground(Color.red);
				edit_button[i].setBackground(Color.white);
				edit_button[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				edit_button[i].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
				edit_button[i].setFocusPainted(false);
				edit_button[i].setEnabled(true);
				edit_button[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
							
						infoText2.setVisible(false);
						staffOnleave2.setVisible(false);
						date4.setVisible(false);
						time2.setVisible(false);
						Ad_Class2.setVisible(false);
						Ad_ClassTF2.setVisible(false);
						staffOnadjustment2.setVisible(false);
						staffOnadjustmentTF2.setVisible(false);
						edit_submit.setVisible(false);
						edit_cancel.setVisible(false);
							
						staffOnleave2.setText(" Staff On Leave/Duty :      " + LStaffName);
						staffOnleave2.setVisible(true);
							
						date4.setVisible(true);
						date4.setText(" Date on Leave/Duty :      " + LDate);
							
						time2.setVisible(true);
						time2.setText("              Time :      "+ class_time2);
							
						Ad_Class2.setVisible(true);
						Ad_ClassTF2.setText(Ad_class);
						Ad_ClassTF2.setVisible(true);
							
						staffOnadjustment2.setVisible(true);
						staffOnadjustmentTF2.setText(AStaffName);
						staffOnadjustmentTF2.setVisible(true);
							
						edit_submit.setVisible(true);
						edit_cancel.setVisible(true);
							
						LStaffName2 = LStaffName;
					    AStaffName2 = AStaffName;
					    LDate2 = LDate;
					    class_time3 = class_time2;
					    Ad_class2 = Ad_class;
							
					}
						
				});
					
				delete_button[i] = new JButton("DELETE");
				delete_button[i].setBounds(20+((0*40)+490),10+((i*80)+50),80,20);
				delete_button[i].setFont(new Font("Times New Roman",Font.BOLD,12));
				delete_button[i].setForeground(Color.red);
				delete_button[i].setBackground(Color.white);
				delete_button[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				delete_button[i].setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
				delete_button[i].setFocusPainted(false);
				delete_button[i].setEnabled(true);
				delete_button[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae){
						    
						int n = JOptionPane.showConfirmDialog(jframe,"Are sure to delete this record?","Warning",JOptionPane.YES_NO_OPTION);
							
						if( n == JOptionPane.YES_OPTION) {
								
							LStaffName2 = LStaffName;
					        AStaffName2 = AStaffName;
					        LDate2 = LDate;
					        class_time3 = class_time2;
					        Ad_class2 = Ad_class;
								
							deleteOption();
								
						}
						
					}
				});
						
						
			}
			date_label[i].setVisible(false);
			leave_label[i].setVisible(false);
			hour_label[i].setVisible(false);
			class_label[i].setVisible(false);
			adjust_label[i].setVisible(false);
			edit_button[i].setVisible(false);
			delete_button[i].setVisible(false);
					
		    panel_Subedit1.add(date_label[i]);
			panel_Subedit1.add(leave_label[i]);
			panel_Subedit1.add(hour_label[i]);
		    panel_Subedit1.add(class_label[i]);
			panel_Subedit1.add(adjust_label[i]);
			panel_Subedit1.add(edit_button[i]);
			panel_Subedit1.add(delete_button[i]);
					
			date_label[i].setVisible(true);
			leave_label[i].setVisible(true);
			hour_label[i].setVisible(true);
			class_label[i].setVisible(true);
			adjust_label[i].setVisible(true);
			edit_button[i].setVisible(true);
			delete_button[i].setVisible(true);
					
				
		}
			
		check=true;
		panel_home.setVisible(false);
		panel_edit.setVisible(true);
		panel_edit.add(panel_option);
				
	}
	
	public void deleteOption() {
		
		try {
			
			Statement st1 = con.createStatement();
			
			ResultSet rst1 = st1.executeQuery("select id,date,staffOn_Leave,class,hour,staffOn_adjustment from staff_adjustment where staffOn_Leave='" + LStaffName2 + "'");
			
			while ( rst1.next() ) {
				
				if ( rst1.getString(2).equals(LDate2) && rst1.getString(3).equals(LStaffName2) && rst1.getString(4).equals(Ad_class2) &&
					rst1.getString(5).equals(class_time3) && rst1.getString(6).equals(AStaffName2) ) {
						
						Statement s1 = con.createStatement();
						
						ResultSet r1 = s1.executeQuery("select gain,loss from staff_report where staff_name = '" + LStaffName2 +"'");
						
						while ( r1.next() ) {
							
							gain = r1.getInt(1);
							loss = r1.getInt(2);
							
						}
						
						loss = loss - 1;
						total = gain - loss;
						
						Statement s2 = con.createStatement();
								
					    s2.executeUpdate("UPDATE staff_report SET gain = '" + gain + "', loss = '" + loss + "', Total = '" + total + "' WHERE staff_name = '" + LStaffName2 + "'");
						
						gain=0; loss=0; total=0;
						
						Statement s3 = con.createStatement();
						
						ResultSet r2 = s3.executeQuery("select gain,loss from staff_report where staff_name = '" + AStaffName2 +"'");
						
						while ( r2.next() ) {
							
							gain = r2.getInt(1);
							loss = r2.getInt(2);
							
						}
						
						gain = gain - 1;
						total = gain - loss;
						
						Statement s4 = con.createStatement();
								
					    s4.executeUpdate("UPDATE staff_report SET gain = '" + gain + "', loss = '" + loss + "', Total = '" + total + "' WHERE staff_name = '" + AStaffName2 + "'");
						
						gain=0; loss=0; total=0;
						
						Statement st2 = con.createStatement();
						
						int n = st2.executeUpdate("delete from staff_adjustment where id='" + rst1.getString(1) + "'");
						
						if ( n == 1 ) {
							
							JOptionPane.showMessageDialog(this,"Deleted Successfully","Message",JOptionPane.WARNING_MESSAGE);
									
							for ( int j=0; j<date_vec.size();j++) {
								
								date_label[j].setVisible(false);
								leave_label[j].setVisible(false);
								hour_label[j].setVisible(false);
								class_label[j].setVisible(false);
								adjust_label[j].setVisible(false);
								edit_button[j].setVisible(false);
								delete_button[j].setVisible(false);
								
							}
		
							date_vec.clear();
							leave_vec.clear();
							hour_vec.clear();
							class_vec.clear();
							adjust_vec.clear();
							infoText.setVisible(false);
		
							infoText2.setVisible(true);
							staffOnleave2.setVisible(false);
							date4.setVisible(false);
							time2.setVisible(false);
							Ad_Class2.setVisible(false);
							Ad_ClassTF2.setVisible(false);
							staffOnadjustment2.setVisible(false);
							staffOnadjustmentTF2.setVisible(false);
							edit_submit.setVisible(false);
							edit_cancel.setVisible(false);
		
		
							if ( searchDate.equals("") && searchStaff.equals("") ) {
			
								Date = new Date();
								date2 = formatter.format(Date);
				
			    
								Statement statement = con.createStatement();
				
								ResultSet getInfo = statement.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where date='" + date2 + "'" );
				
								if ( getInfo != null ) {
									
									infoText.setVisible(false);
					
									while(getInfo.next()) {
					
										date_vec.addElement(getInfo.getString(1));
										leave_vec.addElement(getInfo.getString(2));
										hour_vec.addElement(getInfo.getString(3));
										class_vec.addElement(getInfo.getString(4));
										adjust_vec.addElement(getInfo.getString(5));
								
									}
									
									if ( !(date_vec.size() == 0 ) ){
					
										editOption();
										
									} else {
										
										infoText.setText("Nothing to Show");
										infoText.setVisible(true);
					
									}
									
								} else { 
				
									infoText.setText("Nothing to Show");
									infoText.setVisible(true);
					
								}
			
			
							} else {
								
								date2 = searchDate;
            
								Statement statement = con.createStatement();
				
								ResultSet getInfo2 = statement.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where date='" + date2 + "'" );
				
								if ( getInfo2 != null ) {
									
									infoText.setVisible(false);
					
									while ( getInfo2.next() ) {
						
										date3 = getInfo2.getString(1);
						
									}

									Statement statement2 = con.createStatement();
				
									ResultSet getInfo3 = statement2.executeQuery("select date,staffOn_Leave,hour,class,staffOn_adjustment from staff_adjustment where staffOn_Leave='" + searchStaff + "'" );
			        
									if ( getInfo3 != null ) {
										
										infoText.setVisible(false);
						
										while ( getInfo3.next() ) {

											if ( getInfo3.getString(1).equals(date3) ) {
								
												date_vec.add(getInfo3.getString(1));
												leave_vec.add(getInfo3.getString(2));
												hour_vec.add(getInfo3.getString(3));
												class_vec.add(getInfo3.getString(4));
												adjust_vec.add(getInfo3.getString(5));
								
											} 	
										}
				
										if ( !(date_vec.size() == 0) ){
											
											editOption();
											
										} else { 
										
											infoText.setText("Nothing to Show");
											infoText.setVisible(true);
											
										}
						   
									} else {
						
										infoText.setText("Nothing to Show");
										infoText.setVisible(true);
						
									}
					
								} else {
						
									infoText.setText("No Results Found");
									infoText.setVisible(true);
						
								}
							}
						}
						
					break;	
				}
						
			}
					
		} catch ( Exception e ) { JOptionPane.showMessageDialog(this,e,"ERROR",JOptionPane.ERROR_MESSAGE); }
		
	}
	
	public void reportOption() {
		
		int j=0;
		
		for ( int i=0; i < staff_vec.size(); i++ ) {
			
			if ( i > 4 ) {
				
				staff_label[i] = new JLabel("Staff Name : " + staff_vec.get(i));
			    staff_label[i].setBounds(20+((0*40)+480),10+((j*80)+30),200,30);
			    staff_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			    staff_label[i].setBackground(Color.white);
					
			    gain_label[i] = new JLabel("Gain : " + gain_vec.get(i));
			    gain_label[i].setBounds(20+((0*40)+640),10+((j*80)+30),200,30);
			    gain_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			    gain_label[i].setBackground(Color.white);
					
			    loss_label[i] = new JLabel("Loss : " + loss_vec.get(i));
			    loss_label[i].setBounds(20+((0*40)+760),10+((j*80)+30),100,30);
			    loss_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			    loss_label[i].setBackground(Color.white);
					
			    total_label[i] = new JLabel("Total : " + total_vec.get(i));
			    total_label[i].setBounds(20+((0*40)+480),10+((j*80)+60),200,30);
		        total_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			    total_label[i].setBackground(Color.white);
				
				j++;
				
			} else {
			
			staff_label[i] = new JLabel("Staff Name : " + staff_vec.get(i));
			staff_label[i].setBounds(20+((0*40)+30),10+((i*80)+30),200,30);
			staff_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			staff_label[i].setBackground(Color.white);
					
			gain_label[i] = new JLabel("Gain : " + gain_vec.get(i));
			gain_label[i].setBounds(20+((0*40)+190),10+((i*80)+30),200,30);
			gain_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			gain_label[i].setBackground(Color.white);
					
			loss_label[i] = new JLabel("Loss : " + loss_vec.get(i));
			loss_label[i].setBounds(20+((0*40)+310),10+((i*80)+30),100,30);
			loss_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			loss_label[i].setBackground(Color.white);
					
			total_label[i] = new JLabel("Total : " + total_vec.get(i));
			total_label[i].setBounds(20+((0*40)+30),10+((i*80)+60),200,30);
		    total_label[i].setFont(new Font("Times New Roman",Font.BOLD,14));
			total_label[i].setBackground(Color.white);
			
			}
			
			staff_label[i].setVisible(false);
			gain_label[i].setVisible(false);
			loss_label[i].setVisible(false);
			total_label[i].setVisible(false);
					
			panel_subReport.add(staff_label[i]);
			panel_subReport.add(gain_label[i]);
			panel_subReport.add(loss_label[i]);
			panel_subReport.add(total_label[i]);
					
			staff_label[i].setVisible(true);
			gain_label[i].setVisible(true);
			loss_label[i].setVisible(true);
			total_label[i].setVisible(true);
			
			check3 = true;
			
		}
		
	}
	
	public void profileOption() {
		
		for ( int i=0; i < date_vec2.size(); i++) {
		
			date_label2[i] = new JLabel("Date : " + date_vec2.get(i));
			date_label2[i].setBounds(20+((0*40)+20),10+((i*80)+50),200,30);
			date_label2[i].setFont(new Font("monospaced",Font.BOLD,14));
			date_label2[i].setBackground(Color.white);
			date_label2[i].setForeground(Color.black);
					
		    leave_label2[i] = new JLabel("Leave Staff : " + leave_vec2.get(i));
			leave_label2[i].setBounds(20+((0*40)+190),10+((i*80)+50),200,30);
			leave_label2[i].setFont(new Font("monospaced",Font.BOLD,14));
			leave_label2[i].setBackground(Color.white);
			leave_label2[i].setForeground(Color.black);
					
			hour_label2[i] = new JLabel("Hour : " + hour_vec2.get(i));
			hour_label2[i].setBounds(20+((0*40)+350),10+((i*80)+50),100,30);
			hour_label2[i].setFont(new Font("monospaced",Font.BOLD,14));
			hour_label2[i].setBackground(Color.white);
			hour_label2[i].setForeground(Color.black);
					
			class_label2[i] = new JLabel("Class : " + class_vec2.get(i));
			class_label2[i].setBounds(20+((0*40)+20),10+((i*80)+80),200,30);
			class_label2[i].setFont(new Font("monospaced",Font.BOLD,14));
			class_label2[i].setBackground(Color.white);
			class_label2[i].setForeground(Color.black);
					
			adjust_label2[i] = new JLabel("Adjustment Staff : " + adjust_vec2.get(i));
			adjust_label2[i].setBounds(20+((0*40)+230),10+((i*80)+80),300,30);
			adjust_label2[i].setFont(new Font("monospaced",Font.BOLD,14));
			adjust_label2[i].setBackground(Color.white);
			adjust_label2[i].setForeground(Color.black);
				
			date_label2[i].setVisible(false);
			leave_label2[i].setVisible(false);
			hour_label2[i].setVisible(false);
			class_label2[i].setVisible(false);
			adjust_label2[i].setVisible(false);
					
			panel_subProfile2.add(date_label2[i]);
			panel_subProfile2.add(leave_label2[i]);
			panel_subProfile2.add(hour_label2[i]);
			panel_subProfile2.add(class_label2[i]);
			panel_subProfile2.add(adjust_label2[i]);
					
			date_label2[i].setVisible(true);
			leave_label2[i].setVisible(true);
			hour_label2[i].setVisible(true);
			class_label2[i].setVisible(true);
			adjust_label2[i].setVisible(true);
			
		}
		
	}
	
	public static void main(String a[]) {
		
		new work_adjustment();
		
	}
	
}