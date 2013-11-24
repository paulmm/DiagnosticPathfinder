/*     */ package org.birg.gui.dialogs;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.Date;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ 
/*     */ public class DateTimePanel extends JPanel
/*     */ {
/*  23 */   private String[] defaultYears = { "2011", "2012", "2013", "2014", "2015", "2016" };
/*  24 */   private String[] defaultMonths = { 
/*  25 */     "January", "February", "March", "April", "May", "June", "July", "August", 
/*  26 */     "September", "October", "November", "December" };
/*     */ 
/*  27 */   private String[] defaultDays = { 
/*  28 */     "Sun", "Mon", "Tues", "Wed", "Thur", "Fri", 
/*  29 */     "Sat" };
/*     */ 
/*  30 */   private String[] defaultHours = { 
/*  31 */     "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
/*     */   private String[] defaultMins;
/*  34 */   private String[] amPm = { "AM", "PM" };
/*     */   protected CalendarModel cm;
/*     */   private JComboBox monthList;
/*     */   private JComboBox yearList;
/*     */   private JComboBox hourList;
/*     */   private JComboBox minuteList;
/*     */   private JComboBox amPmList;
/*     */   JLabel timeZone;
/*     */   private JTable table;
/*     */   private Date d;
/*     */ 
/*     */   public DateTimePanel(Date initialDate)
/*     */   {
/*  45 */     this.d = initialDate;
/*  46 */     this.cm = new CalendarModel(this.d, this.defaultMonths, this.defaultDays);
/*     */ 
/*  48 */     this.defaultMins = new String[60];
/*  49 */     for (int i = 0; i < 60; i++) {
/*  50 */       if (i < 10)
/*  51 */         this.defaultMins[i] = ("0" + i);
/*     */       else {
/*  53 */         this.defaultMins[i] = (i + ".0");
/*     */       }
/*     */     }
/*     */ 
/*  57 */     this.table = new JTable(this.cm);
/*  58 */     this.table.setShowGrid(false);
/*  59 */     this.table.setRowSelectionAllowed(false);
/*  60 */     this.table.setCellSelectionEnabled(true);
/*  61 */     JScrollPane pane = new JScrollPane(this.table);
/*  62 */     pane.setPreferredSize(new Dimension(300, 120));
/*     */ 
/*  64 */     this.monthList = new JComboBox(this.cm.getLocalizedMonthNames());
/*  65 */     this.monthList.setSelectedItem(this.cm.getLocalizedMonthName());
/*  66 */     this.yearList = new JComboBox(this.defaultYears);
/*  67 */     this.yearList.setSelectedItem(this.cm.getYear());
/*  68 */     JPanel topPanel = new JPanel(new FlowLayout());
/*  69 */     topPanel.add(this.monthList);
/*  70 */     topPanel.add(this.yearList);
/*     */ 
/*  72 */     this.hourList = new JComboBox(this.defaultHours);
/*  73 */     this.hourList.setSelectedItem(this.cm.getHour());
/*  74 */     this.minuteList = new JComboBox(this.defaultMins);
/*  75 */     this.minuteList.setSelectedItem(this.cm.getMinute());
/*  76 */     this.amPmList = new JComboBox(this.amPm);
/*  77 */     this.amPmList.setSelectedItem(this.cm.getAmPm());
/*     */ 
/*  79 */     JPanel bottomPanel = new JPanel(new FlowLayout());
/*  80 */     bottomPanel.add(this.hourList);
/*  81 */     bottomPanel.add(new JLabel(":"));
/*  82 */     bottomPanel.add(this.minuteList);
/*  83 */     bottomPanel.add(this.amPmList);
/*     */ 
/*  86 */     setLayout(new BorderLayout());
/*  87 */     add(topPanel, "North");
/*  88 */     add(pane, "Center");
/*  89 */     add(bottomPanel, "South");
/*     */ 
/*  92 */     this.monthList.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  94 */         DateTimePanel.this.cm.setMonth(DateTimePanel.this.monthList.getSelectedIndex());
/*     */       }
/*     */     });
/*  98 */     this.yearList.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 100 */         String aYear = DateTimePanel.this.yearList.getSelectedItem().toString();
/* 101 */         DateTimePanel.this.cm.setYear(Integer.parseInt(aYear));
/*     */       }
/*     */     });
/* 105 */     this.hourList.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 107 */         String hour = DateTimePanel.this.hourList.getSelectedItem().toString();
/* 108 */         DateTimePanel.this.cm.setHour(Integer.parseInt(hour));
/*     */       }
/*     */     });
/* 112 */     this.minuteList.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 114 */         String minute = DateTimePanel.this.minuteList.getSelectedItem().toString();
/* 115 */         DateTimePanel.this.cm.setMinute(Integer.parseInt(minute));
/*     */       }
/*     */     });
/* 119 */     this.amPmList.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 121 */         String amPm = DateTimePanel.this.amPmList.getSelectedItem().toString();
/* 122 */         if (amPm.equals("AM"))
/* 123 */           DateTimePanel.this.cm.setAmPm(0);
/*     */         else
/* 125 */           DateTimePanel.this.cm.setAmPm(1);
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public Date getDate()
/*     */   {
/* 135 */     return this.cm.getSelectedDate();
/*     */   }
/*     */ }

/* Location:           /Users/paulmangiamele/Desktop/BIRG_API/
 * Qualified Name:     org.birg.gui.dialogs.DateTimePanel
 * JD-Core Version:    0.6.2
 */