package pkg_main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.*;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import net.proteanit.sql.DbUtils;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;

public class jam_review extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jam_review frame = new jam_review();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JTextField tablename;
	private JTable table;
	private JLabel lblDebit;
	private JLabel lblCredit;
	private JLabel lblBalance;
	private JButton button;
	String cr; String db; String bal;
	String newLine = System.getProperty("line.separator");
	float credit; 
	float debit;
	float balance;
	private JTextField code;
	private JLabel lblId;
	private JLabel lblDate;
	private JLabel lblCode_1;
	private JLabel lblNoOfPieces;
	private JLabel lblWeight;
	private JLabel lblCredit_1;
	private JLabel lblDebit_1;
	private JLabel lblParticular;
	private JButton btnSave;
	private JButton btnUpdate;
	private JTextField textId;
	private JTextField textDate;
	private JTextField textCode;
	private JTextField textNo;
	private JTextField textWt;
	private JTextField textPart;
	private JTextField textDb;
	private JTextField textCr;
	String c;
	
	public void refresh(){
		try{
			String view_query="select * from "+tablename.getText();
			PreparedStatement pst4=connection.prepareStatement(view_query);
			ResultSet rs3=pst4.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs3));
			rs3.close();
			pst4.close();
		}
		catch(Exception ds){
			ds.printStackTrace();
		}
	}
	public void ref1(){
		try{
			String view_query2="select * from "+tablename.getText();
			PreparedStatement pst5=connection.prepareStatement(view_query2);
			ResultSet rs4=pst5.executeQuery();
			while(rs4.next()){
			 c = rs4.getString("ID");
			credit=credit + rs4.getFloat("CREDIT");
		
			debit=debit + rs4.getFloat("DEBIT");
			
		}
		
			DecimalFormat df = new DecimalFormat("0.00");
			df.setMaximumFractionDigits(2);
	
					
		cr=df.format(credit);
		lblCredit.setText(cr);
		db=df.format(debit);
		lblDebit.setText(db);
		balance=credit-debit;
		bal=df.format(balance);
		lblBalance.setText(bal);
		credit=0;
		debit=0;
		balance=0;
		int ci=Integer.parseInt(c);
		textId.setText(String.valueOf(++ci));
		rs4.close();
		pst5.close();
		
	}
		catch(Exception de){
			de.printStackTrace();
		}
	}
	public void ref2(){
		try{
			String view_query2="select * from "+tablename.getText()+" where CODE='"+code.getText()+"'";
			PreparedStatement pst5=connection.prepareStatement(view_query2);
			ResultSet rs4=pst5.executeQuery();
			while(rs4.next()){
				credit=credit + rs4.getFloat("CREDIT");
				debit=debit + rs4.getFloat("DEBIT");
				
			}
			
			DecimalFormat df = new DecimalFormat("0.00");
			df.setMaximumFractionDigits(2);
	
		cr=df.format(credit);;
		lblCredit.setText(cr);
		db=df.format(debit);
		lblDebit.setText(db);
		balance=credit-debit;
		bal=df.format(balance);
		lblBalance.setText(bal);
		credit=0;
		debit=0;
		balance=0;
		rs4.close();
		pst5.close();
		}
		catch(Exception de){
			de.printStackTrace();
		}
	}
	public float c_cred;
	public float c_deb;
	public String c_part=null;
	public String c_date=null;
	public float credFinal;
	public float debFinal;
	public List<String> dateList=new ArrayList<String>();
	public List<String> partList=new ArrayList<String>();
	public List<Float> creditList=new ArrayList<Float>();
	public List<Float> debitList=new ArrayList<Float>();
	private JButton btnDel;
	
	public void clearList() {
	   
		dateList.clear();
		partList.clear();
		creditList.clear();
		debitList.clear();
	}	
	
public void selectCode(String codeName){
	try{
	String codeQuery2="select * from "+tablename.getText()+" where CODE='"+codeName+"'";
	PreparedStatement pst2=connection.prepareStatement(codeQuery2);
	ResultSet rs2=pst2.executeQuery();
	clearList();
	while(rs2.next()){
		
		c_cred=c_cred + rs2.getFloat("CREDIT");
		creditList.add(rs2.getFloat("CREDIT"));
		c_deb=c_deb + rs2.getFloat("DEBIT");
		debitList.add(rs2.getFloat("DEBIT"));
		
		c_part=rs2.getString("PARTICULAR");
		partList.add(c_part);
		c_date=rs2.getString("DATE");
		dateList.add(c_date);
	}
		credFinal=c_cred;
		debFinal=c_deb;
		c_cred=0;
		c_deb=0;
	}
	catch(Exception r){
		r.printStackTrace();
	}
}
public void createDocFile(String fileName) {

		try {
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());

			XWPFDocument doc = new XWPFDocument();
			
			XWPFParagraph tempParagraph = doc.createParagraph();
			XWPFRun tempRun = tempParagraph.createRun();
			tempRun.setText(tablename.getText());
			tempRun.setBold(true);
			tempRun.setFontSize(16);

			DecimalFormat df = new DecimalFormat("0.00");
			df.setMaximumFractionDigits(2);  
			
			
		//	List<XWPFParagraph> paras = new ArrayList<XWPFParagraph>();
		//	List<XWPFRun> runs = new ArrayList<XWPFRun>();
			
			List<String> codeList=new ArrayList<String>();
			String codeName=null;
			
			String codeQuery="select DISTINCT CODE from "+tablename.getText();
			PreparedStatement pst=connection.prepareStatement(codeQuery);
			ResultSet rs=pst.executeQuery();
			while(rs.next()){
				codeName=rs.getString("CODE");
				codeList.add(codeName);
				System.out.println(codeName);
			}
			List<XWPFTable> tables = new ArrayList<XWPFTable>();
			List<XWPFTableRow> rows = new ArrayList<XWPFTableRow>();
			
			for(int codeCount=0;codeCount<codeList.size();codeCount++){
				
			selectCode(codeList.get(codeCount));	
		
		/*	for(int recordCount = 0; recordCount < partList.size(); recordCount++) {
			    paras.add(doc.createParagraph());
			}
			for(int recordCount = 0; recordCount < partList.size(); recordCount++) {
			    runs.add(paras.get(recordCount).createRun());
			    runs.get(recordCount).setFontSize(14);
			     
			    
			}*/

			//TABLE LESS APPROACH
		//	tempRun.addBreak();
		//	tempRun.setText("CODE:"+codeList.get(codeCount));
		//	tempRun.addCarriageReturn();
			
		/*	runs.get(0).addBreak();
			runs.get(0).setText("CODE:"+codeList.get(codeCount));
			runs.get(0).addCarriageReturn();
			runs.get(0).setText("DATE          PARTICULAR                                           DEBIT             CREDIT ");	
			runs.get(0).addCarriageReturn();
			runs.get(0).setText("-------------------------------------------------------------------------------------------- ");	
			runs.get(0).addBreak();
			for(int itemCount=0;itemCount<partList.size();itemCount++){	
			runs.get(0).setText(dateList.get(itemCount)+"   "+partList.get(itemCount)+"   																														    "+debitList.get(itemCount)+"    			  " +creditList.get(itemCount));
			runs.get(0).addCarriageReturn();
			}
			runs.get(0).setText("-------------------------------------------------------------------------------------------- ");	
			runs.get(0).addCarriageReturn();
			runs.get(0).setText(">>                                                                                  	  " +df.format(debFinal)+"   		          "+df.format(credFinal));
			runs.get(0).addCarriageReturn();
			runs.get(0).setText(">>				 											     																																																						          " +String.valueOf(credFinal-debFinal));
			
			runs.get(0).addCarriageReturn();
			
			runs.get(0).setText("-------------------------------------------------------------------------------------------- ");	
			
			runs.get(0).addBreak();
				
			*/ 
			
			//CREATE A TABLE
			
			
			
			
			    tables.add(doc.createTable());
			    
			    rows.add(tables.get(codeCount).getRow(0));
			    rows.get(codeCount).getCell(0).setText(">>>");
			    rows.get(codeCount).createCell().setText("CODE");
			    rows.get(codeCount).createCell().setText(codeList.get(codeCount));
			    rows.get(codeCount).createCell().setText("<<<");
			    
			    XWPFTableRow r = rows.get(codeCount);
			    r =tables.get(codeCount).createRow();
				
				
				r.getCell(0).setText("DATE");
				r.getCell(1).setText("PARTICULAR");
				r.getCell(2).setText("DEBIT");
				r.getCell(3).setText("CREDIT");
				
			//    r.setHeight(20);
			 //   XWPFTable t = tables.get(codeCount);
			  //  t.setWidth(100);
			    
			for(int recordCount = 0; recordCount < partList.size(); recordCount++) {
				r =tables.get(codeCount).createRow();
				
				r.getCell(0).setText(dateList.get(recordCount));
				r.getCell(1).setText(partList.get(recordCount));
				r.getCell(2).setText(df.format(debitList.get(recordCount)));
				r.getCell(3).setText(df.format(creditList.get(recordCount)));
			 
			}
			r =tables.get(codeCount).createRow();
			
			r.getCell(2).setText(df.format(debFinal));
			
			r.getCell(3).setText(df.format(credFinal));
			r =tables.get(codeCount).createRow();
			r.getCell(2).setText(df.format(credFinal-debFinal));       
			
			
			}
					
			doc.write(fos);
			fos.close();
			
			System.out.println(file.getAbsolutePath()+ " created successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * Create the frame.
	 */
	public jam_review() {
		credit=0;
		debit=0;
		connection=db_helper.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1192, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tablename = new JTextField();
		tablename.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		tablename.setBounds(76, 27, 123, 20);
		contentPane.add(tablename);
		tablename.setColumns(10);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		lblName.setBounds(24, 28, 46, 14);
		contentPane.add(lblName);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 831, 231);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setAlignmentX(Component.RIGHT_ALIGNMENT);
		table.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		table.setFont(new Font("Tahoma", Font.BOLD, 16));
		scrollPane.setViewportView(table);
		
		lblDebit = new JLabel("DEBIT");
		lblDebit.setFont(new Font("DialogInput", Font.BOLD, 15));
		lblDebit.setBounds(620, 326, 100, 23);
		contentPane.add(lblDebit);
		
		lblCredit = new JLabel("CREDIT");
		lblCredit.setFont(new Font("DialogInput", Font.BOLD, 15));
		lblCredit.setBounds(741, 326, 100, 23);
		contentPane.add(lblCredit);
		
		
		lblBalance = new JLabel("BAL");
		lblBalance.setFont(new Font("DialogInput", Font.BOLD, 15));
		lblBalance.setBounds(620, 367, 100, 20);
		contentPane.add(lblBalance);
		 
		JButton btnShow = new JButton("Show ");
		btnShow.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String view_query="select * from "+tablename.getText();
					PreparedStatement pst4=connection.prepareStatement(view_query);
					ResultSet rs3=pst4.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs3));
					rs3.close();
					pst4.close();
					ref1();
				}
				catch(Exception ds){
					ds.printStackTrace();
				}
			}

			
		});
		
		btnShow.setBounds(219, 26, 89, 23);
		contentPane.add(btnShow);
		
	
		
		button = new JButton("<< EXIT");
		button.setFont(new Font("Trebuchet MS", Font.ITALIC, 18));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jam_login jl1=new jam_login();
				setVisible(false);
				jl1.setVisible(true);
			}
		});
		button.setBounds(10, 357, 100, 41);
		contentPane.add(button);
		
		JLabel lblCode = new JLabel("CODE");
		lblCode.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 16));
		lblCode.setBounds(390, 28, 46, 14);
		contentPane.add(lblCode);
		
		code = new JTextField();
		code.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}		
		});
		code.setBounds(435, 27, 112, 20);
		contentPane.add(code);
		code.setColumns(10);
		
		JButton btnShow_1 = new JButton("Show");
		btnShow_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
		btnShow_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String view_query3="select * from "+tablename.getText()+" where CODE='"+code.getText()+"'";
			//		System.out.print(view_query3);
					PreparedStatement pst7=connection.prepareStatement(view_query3);
			//		pst5.setString(1, code.getText());
					ResultSet rs7=pst7.executeQuery(); 
					table.setModel(DbUtils.resultSetToTableModel(rs7));
					rs7.close();
					pst7.close();
					ref2();
					
				}
				catch(Exception ds){
					ds.printStackTrace();
				}
			}
			
		});
		btnShow_1.setBounds(557, 26, 89, 23);
		contentPane.add(btnShow_1);
		
		lblId = new JLabel("ID");
		lblId.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		lblId.setBounds(900, 87, 79, 14);
		contentPane.add(lblId);
		
		lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		lblDate.setBounds(900, 112, 79, 14);
		contentPane.add(lblDate);
		
		lblCode_1 = new JLabel("Code");
		lblCode_1.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		lblCode_1.setBounds(900, 137, 79, 14);
		contentPane.add(lblCode_1);
		
		lblNoOfPieces = new JLabel("No of Pieces");
		lblNoOfPieces.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		lblNoOfPieces.setBounds(900, 162, 79, 14);
		contentPane.add(lblNoOfPieces);
		
		lblWeight = new JLabel("Weight");
		lblWeight.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		lblWeight.setBounds(900, 187, 79, 14);
		contentPane.add(lblWeight);
		
		lblCredit_1 = new JLabel("Credit");
		lblCredit_1.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		lblCredit_1.setBounds(900, 262, 79, 14);
		contentPane.add(lblCredit_1);
		
		lblDebit_1 = new JLabel("Debit");
		lblDebit_1.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		lblDebit_1.setBounds(900, 237, 79, 14);
		contentPane.add(lblDebit_1);
		
		lblParticular = new JLabel("Particular");
		lblParticular.setFont(new Font("Trebuchet MS", Font.BOLD, 11));
		lblParticular.setBounds(900, 212, 79, 14);
		contentPane.add(lblParticular);
		
		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query="insert into "+tablename.getText()+" (ID,DATE,CODE,NumOfPieces,WEIGHT,PARTICULAR,DEBIT,CREDIT) values(?,?,?,?,?,?,?,?)";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, textId.getText());
					pst.setString(2, textDate.getText());
					pst.setString(3, textCode.getText());
					pst.setString(4, textNo.getText());
					pst.setString(5, textWt.getText());
					pst.setString(6, textPart.getText());
					pst.setString(7, textDb.getText());
					pst.setString(8, textCr.getText());
					pst.executeUpdate();
				
				//	JOptionPane.showMessageDialog(null, "Record Inserted Successfully");
					int count = Integer.parseInt(textId.getText());
					textId.setText(String.valueOf(++count));
					textNo.setText("");
					textCode.setText("DEF");
					textWt.setText("");
					textPart.setText("");
					textDb.setText("");
					textCr.setText("");
					pst.close();
					refresh();
					ref1();
				}
				catch(Exception ds){
					JOptionPane.showMessageDialog( null,"Sorry, Insert Failed. Click Update to Modify","Jarvis Accounts Manager", JOptionPane.ERROR_MESSAGE);
					ds.printStackTrace();
				}
			}
		});
		btnSave.setBounds(1031, 292, 89, 30);
		contentPane.add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 11));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					//String query="UPDATE "+tablename.getText()+" set DATE='"+ textDate.getText() +"' ,CODE='"+ textCode.getText() +"' ,NumOfPieces="+ textNo.getText()+" ,WEIGHT="+textWt.getText() +" ,PARTICULAR='"+ textPart.getText()+"' ,CREDIT="+textCr.getText() +" ,DEBIT="+textDb.getText()+" WHERE ID='"+ textId.getText()+"'";
					String query="UPDATE "+tablename.getText()+" set DATE='"+ textDate.getText() +"',CODE='"+ textCode.getText() +"' ,NumOfPieces=? ,WEIGHT=? ,PARTICULAR='"+ textPart.getText()+"' ,DEBIT=? ,CREDIT=? WHERE ID='"+ textId.getText()+"'";
							
					//System.out.println(query);
					PreparedStatement pst=connection.prepareStatement(query);
					
					if(textNo.getText()==null){
					pst.setString(1, "0");
					}
					else{
					pst.setString(1, textNo.getText());
					}
					if(textWt.getText()==null){
						pst.setString(2, "0");
						}
						else{
						pst.setString(2, textWt.getText());
						}
					if(textDb.getText()==null){
						pst.setString(3, "0");
						}
						else{
						pst.setString(3, textDb.getText());
						}
					if(textCr.getText()==null){
						pst.setString(4, "0");
						}
						else{
						pst.setString(4, textCr.getText());
						}
					
					textId.setText("");
					
					textNo.setText("");
					textWt.setText("");
					textPart.setText("");
					textDb.setText("");
					textCr.setText("");
					
					
					pst.executeUpdate();
					pst.close();
					refresh();
					ref1();
				//	JOptionPane.showMessageDialog(null, "Record Updated!");
					
				}
				catch(Exception ds){
					ds.printStackTrace();
					JOptionPane.showMessageDialog(null,"Sorry, Update Failed. Enter the Correct ID","Jarvis Accounts Manager", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUpdate.setBounds(1031, 333, 89, 30);
		contentPane.add(btnUpdate);
		
		textId = new JTextField();
		textId.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		//textId.setText("7");
		textId.setBounds(1006, 84, 86, 20);
		contentPane.add(textId);
		textId.setColumns(10);
		
		textDate = new JTextField();
		textDate.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		textDate.setText("01/01/2016");
		textDate.setBounds(1006, 109, 86, 20);
		contentPane.add(textDate);
		textDate.setColumns(10);
		
		textCode = new JTextField();
		textCode.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		textCode.setText("DEF");
		textCode.setBounds(1006, 134, 86, 20);
		contentPane.add(textCode);
		textCode.setColumns(10);
		
		textNo = new JTextField();
		textNo.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		textNo.setBounds(1006, 159, 86, 20);
		contentPane.add(textNo);
		textNo.setColumns(10);
		
		textWt = new JTextField();
		textWt.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		textWt.setBounds(1006, 184, 86, 20);
		contentPane.add(textWt);
		textWt.setColumns(10);
		
		textPart = new JTextField();
		textPart.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		textPart.setBounds(1006, 209, 148, 20);
		contentPane.add(textPart);
		textPart.setColumns(10);
		
		textDb = new JTextField();
		textDb.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		textDb.setBounds(1006, 235, 86, 20);
		contentPane.add(textDb);
		textDb.setColumns(10);
		
		textCr = new JTextField();
		textCr.setFont(new Font("Trebuchet MS", Font.BOLD, 12));
		textCr.setBounds(1006, 259, 86, 20);
		contentPane.add(textCr);
		textCr.setColumns(10);
		
		JButton export = new JButton("");
		export.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//EXPORT TABLES TO WORD DOCUMENT
				
				createDocFile("C:\\db\\"+ tablename.getText() +".docx");

				
				//OPEN
				  Desktop desktop = Desktop.getDesktop();  
		          try {
		            File f = new File( "C:\\db\\" + tablename.getText()  +  ".docx");
		             desktop.open(f);  // opens application (MSWord) associated with .doc file
		          }
		          catch(Exception ex) {
					// WordDocument.this is to refer to outer class's instance from inner class
		            JOptionPane.showMessageDialog(null,"File Not Found","Jarvis Accounts Manager", JOptionPane.ERROR_MESSAGE);
		          }
			}
		});
		Image img = new ImageIcon(getClass().getResource("/icon_export.png")).getImage();
		export.setIcon(new ImageIcon(img));
		export.setBounds(787, 11, 54, 49);
		contentPane.add(export);
		
		btnDel = new JButton("");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (JOptionPane.showConfirmDialog(null, "Are you sure on deleting this record?", "WARNING",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					    // yes option
					
					
					
					String query="DELETE from "+tablename.getText()+" where ID='"+ textId.getText() +"'";
					PreparedStatement pst=connection.prepareStatement(query);
					
					textId.setText("");
					
					pst.executeUpdate();
					pst.close();
					refresh();
					ref1();
				//	JOptionPane.showMessageDialog(null, "Record Updated!");
					} else {
					    // no option
					}
					
				}
				catch(Exception ds){
					ds.printStackTrace();
					}
			}
		
		});
		Image img2 = new ImageIcon(getClass().getResource("/delete.png")).getImage();
		btnDel.setIcon(new ImageIcon(img2));
		btnDel.setBounds(1102, 84, 33, 20);
		contentPane.add(btnDel);
		
		JButton btnDel2 = new JButton("");
		btnDel2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if (JOptionPane.showConfirmDialog(null, "Are you sure on deleting related records?", "WARNING",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					    // yes option
					
					
					
					String query="DELETE from "+tablename.getText()+" where CODE='"+ code.getText() +"'";
					//System.out.println(query);
					PreparedStatement pst=connection.prepareStatement(query);
					
					textId.setText("");
					
					pst.executeUpdate();
					pst.close();
					refresh();
					ref1();
				//	JOptionPane.showMessageDialog(null, "Record Updated!");
					} else {
					    // no option
					}
					
				}
				catch(Exception ds){
					ds.printStackTrace();
					}
				
			}
		});
		btnDel2.setBounds(665, 26, 33, 23);
		Image img3 = new ImageIcon(getClass().getResource("/delete.png")).getImage();
		btnDel2.setIcon(new ImageIcon(img3));
		contentPane.add(btnDel2);
	}
}
