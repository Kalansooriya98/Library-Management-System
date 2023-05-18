/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Vihanga Kalansooriya
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    Color mouseEnterColor = new Color(0,0,0);
    Color mouseExitColor = new Color(51,51,51);
    DefaultTableModel model;
    public Home() {
        initComponents();
        showPieChart();
        setStudentDetailsToTable();
        setBookDetailsToTable();
        setDataToCards();
    }
    
    public void setDataToCards(){
       Statement st = null;
       ResultSet rs =null;
       
       long l = System.currentTimeMillis();
       Date todayDate = new Date(l);
       
       try{
           Connection con = DBConnection.getConnection();
           st = con.createStatement();
           rs = st.executeQuery("select * from book_details");
           rs.last();
           lbl_noofbook.setText(Integer.toString(rs.getRow()));
           
           rs = st.executeQuery("select * from student_details");
           rs.last();
           lbl_noofstudents.setText(Integer.toString(rs.getRow()));
           
           rs = st.executeQuery("select * from issue_book_details where status = '"+"pending"+"'");
           rs.last();
           lbl_IssuedBooks.setText(Integer.toString(rs.getRow()));
          
           rs = st.executeQuery("select * from issue_book_details where where due_date < '"+todayDate+"' and status = '"+"pending"+"'");
           rs.last();
           lbl_defaulterlist.setText(Integer.toString(rs.getRow()));
       }catch(Exception e){
           
       }
       
    }
    public void showPieChart(){
        
        //create dataset
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      
      try{
         Connection con = DBConnection.getConnection();
         String sql = "select book_name ,count(*) as issue_count from issue_book_details group by book_id ";
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql);
         while(rs.next()){
            barDataset.setValue(rs.getString( "book_name") , new Double( rs.getDouble("issue_count") ) );
         }
      }catch(Exception e){
          
      }
        
       
      
      //create chart
       JFreeChart piechart = ChartFactory.createPieChart("Issue Book Details",barDataset, true,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("IPhone 5s", new Color(255,255,102));
        piePlot.setSectionPaint("SamSung Grand", new Color(102,255,102));
        piePlot.setSectionPaint("MotoG", new Color(255,102,153));
        piePlot.setSectionPaint("Nokia Lumia", new Color(0,204,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        PanelPieChart.removeAll();
        PanelPieChart.add(barChartPanel, BorderLayout.CENTER);
        PanelPieChart.validate();
    }
    
    public void setStudentDetailsToTable(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student_details");
            
            while(rs.next()){
                String StudentId = rs.getString("student_id");
                String StudentName = rs.getString("name");
                String faculty = rs.getString("faculty");
                String year = rs.getString("year");
                
                Object[] obj = {StudentId,StudentName,faculty,year}; 
                model = (DefaultTableModel) tbl_studentDetails.getModel();
                model.addRow(obj);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }   
    }
    
    public void setBookDetailsToTable(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from book_details");
            
            while(rs.next()){
                String bookId = rs.getString("book_id");
                String bookName = rs.getString("book_name");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                
                Object[] obj = { bookId,bookName,author,quantity}; 
                model = (DefaultTableModel) tbl_bookDetails.getModel();
                model.addRow(obj);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lbl_noofbook = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbl_noofstudents = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lbl_IssuedBooks = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lbl_defaulterlist = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_studentDetails = new rojerusan.RSTableMetro();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_bookDetails = new rojerusan.RSTableMetro();
        PanelPieChart = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel1.setText("Welcome,Admin");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 10, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic Light", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Colombo Library Management System");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("X");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 10, 30, 40));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/male_user_50px.png"))); // NOI18N
        jLabel5.setText("Welcome,Admin");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 70));

        jPanel3.setBackground(new java.awt.Color(255, 0, 51));
        jPanel3.setForeground(new java.awt.Color(51, 51, 51));
        jPanel3.setToolTipText("");
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabel6.setText(" Home Page");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, 30));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 64, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Features");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, 30));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabel8.setText(" Manage Student");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, 30));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Library_26px_1.png"))); // NOI18N
        jLabel9.setText(" LMS Dashboard");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, 30));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Book_26px.png"))); // NOI18N
        jLabel10.setText(" Manage Book");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel10MouseExited(evt);
            }
        });
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, 30));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Sell_26px.png"))); // NOI18N
        jLabel11.setText("  Issue Book");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, -1, 30));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Return_Purchase_26px.png"))); // NOI18N
        jLabel12.setText("  Return Book");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, -1, 30));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_View_Details_26px.png"))); // NOI18N
        jLabel13.setText(" View Records");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, 30));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Books_26px.png"))); // NOI18N
        jLabel14.setText(" View Issued Books");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, -1, 30));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        jLabel15.setText(" Defaulter List");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, -1, 30));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Exit_26px.png"))); // NOI18N
        jLabel16.setText(" Logout");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, -1, 30));

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 217, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 230, 650));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noofbook.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lbl_noofbook.setForeground(new java.awt.Color(102, 102, 102));
        lbl_noofbook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Book_Shelf_50px.png"))); // NOI18N
        lbl_noofbook.setText("10");
        jPanel6.add(lbl_noofbook, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, 50));

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 66, 168, 78));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("No of Books");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 28, -1, -1));

        jPanel7.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(51, 153, 0)));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_noofstudents.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lbl_noofstudents.setForeground(new java.awt.Color(102, 102, 102));
        lbl_noofstudents.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_People_50px.png"))); // NOI18N
        lbl_noofstudents.setText("10");
        jPanel7.add(lbl_noofstudents, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, 50));

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 168, 78));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("No of Students");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_IssuedBooks.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lbl_IssuedBooks.setForeground(new java.awt.Color(102, 102, 102));
        lbl_IssuedBooks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Sell_50px.png"))); // NOI18N
        lbl_IssuedBooks.setText("10");
        jPanel8.add(lbl_IssuedBooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, 50));

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 168, 78));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Issued Books");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, -1, -1));

        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(51, 204, 0)));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_defaulterlist.setFont(new java.awt.Font("SansSerif", 1, 48)); // NOI18N
        lbl_defaulterlist.setForeground(new java.awt.Color(102, 102, 102));
        lbl_defaulterlist.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_List_of_Thumbnails_50px.png"))); // NOI18N
        lbl_defaulterlist.setText("10");
        jPanel9.add(lbl_defaulterlist, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, 50));

        jPanel5.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 168, 78));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Defaulter List");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, -1, -1));

        tbl_studentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name", "Course", "Branch"
            }
        ));
        tbl_studentDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_studentDetails.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_studentDetails.setRowHeight(30);
        jScrollPane1.setViewportView(tbl_studentDetails);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 487, 162));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Student Details");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("Book Details");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        tbl_bookDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Name", "Author", "Quantity"
            }
        ));
        tbl_bookDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_bookDetails.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbl_bookDetails.setRowHeight(30);
        jScrollPane2.setViewportView(tbl_bookDetails);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 499, 157));

        PanelPieChart.setLayout(new java.awt.BorderLayout());
        jPanel5.add(PanelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 230, 316, 350));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("IIT Technoogy");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 620, -1, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 950, 650));

        setSize(new java.awt.Dimension(1180, 724));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        ManageBooks books = new ManageBooks();
        books.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseEntered
        jLabel10.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel10MouseEntered

    private void jLabel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseExited
        jLabel10.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel10MouseExited

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        IssueBook book = new IssueBook();
        book.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        ManageStudents student = new ManageStudents();
        student.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        ViewAllRecord book = new ViewAllRecord();
        book.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        ReturnBook book = new ReturnBook();
        book.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        IssueBookDetails ibook = new IssueBookDetails();
        ibook.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        DefaulterList dbook = new DefaulterList();
        dbook.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        LoginPage login = new LoginPage();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        Home home = new Home();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelPieChart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_IssuedBooks;
    private javax.swing.JLabel lbl_defaulterlist;
    private javax.swing.JLabel lbl_noofbook;
    private javax.swing.JLabel lbl_noofstudents;
    private rojerusan.RSTableMetro tbl_bookDetails;
    private rojerusan.RSTableMetro tbl_studentDetails;
    // End of variables declaration//GEN-END:variables
}
