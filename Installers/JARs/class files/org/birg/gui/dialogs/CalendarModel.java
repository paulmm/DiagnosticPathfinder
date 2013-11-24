/*     */ package org.birg.gui.dialogs;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.TimeZone;
/*     */ import javax.swing.event.TableModelEvent;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ 
/*     */ public class CalendarModel extends AbstractTableModel
/*     */ {
/*     */   private GregorianCalendar c;
/*     */   private GregorianCalendar scroller;
/*     */   private String[] dayNames;
/*     */   private String[] monthNames;
/*  18 */   private String[][] dayMatrix = new String[6][7];
/*     */ 
/*  20 */   public CalendarModel(Date initialDate, String[] months, String[] days) { this.dayNames = days;
/*  21 */     this.monthNames = months;
/*  22 */     this.c = new GregorianCalendar();
/*  23 */     this.c.setTime(initialDate);
/*  24 */     this.c.set(13, 0);
/*  25 */     this.scroller = new GregorianCalendar();
/*  26 */     this.scroller.setTime(initialDate);
/*  27 */     this.scroller.set(13, 0);
/*  28 */     int thisMonth = this.scroller.get(2);
/*  29 */     int thisYear = this.scroller.get(1);
/*  30 */     this.scroller.set(5, 1);
/*  31 */     while (this.scroller.get(2) == thisMonth) {
/*  32 */       this.dayMatrix[(this.scroller.get(4) - 1)][
/*  33 */         (this.scroller.get(7) - 1)] = 
/*  34 */         this.scroller.get(5);
/*  35 */       this.scroller.add(5, 1);
/*     */     }
/*  37 */     this.scroller.set(2, thisMonth);
/*  38 */     this.scroller.set(1, thisYear);
/*     */ 
/*  41 */     this.scroller.set(10, this.scroller.get(10) % 12);
/*  42 */     this.c.set(10, this.c.get(10) % 12);
/*     */   }
/*     */ 
/*     */   public String[] getLocalizedMonthNames()
/*     */   {
/*  48 */     return this.monthNames;
/*     */   }
/*     */ 
/*     */   public void setMinute(int minute) {
/*  52 */     this.scroller.set(12, minute);
/*  53 */     this.c.set(12, minute);
/*     */   }
/*     */ 
/*     */   public void setHour(int hour) {
/*  57 */     this.scroller.set(10, hour % 12);
/*  58 */     this.c.set(10, hour % 12);
/*     */   }
/*     */ 
/*     */   public void setAmPm(int amPm) {
/*  62 */     this.scroller.set(9, amPm);
/*  63 */     this.c.set(9, amPm);
/*     */   }
/*     */ 
/*     */   public void setMonth(int month)
/*     */   {
/*  68 */     this.scroller.set(2, month);
/*  69 */     this.c.set(2, month);
/*  70 */     updateMatrix();
/*  71 */     fireTableChanged(new TableModelEvent(this));
/*     */   }
/*     */ 
/*     */   public void setYear(int year) {
/*  75 */     this.scroller.set(1, year);
/*  76 */     this.scroller.set(2, this.c.get(2));
/*  77 */     this.c.set(1, year);
/*  78 */     updateMatrix();
/*  79 */     fireTableChanged(new TableModelEvent(this));
/*     */   }
/*     */ 
/*     */   public void setDayMatrix(int row, int column) {
/*  83 */     if (row < 0) {
/*  84 */       return;
/*     */     }
/*  86 */     if (column < 0) {
/*  87 */       return;
/*     */     }
/*  89 */     if (this.dayMatrix[row][column] != null) {
/*  90 */       int newDate = Integer.parseInt(this.dayMatrix[row][column]);
/*  91 */       this.scroller.set(5, newDate);
/*  92 */       this.c.set(5, newDate);
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getLocalizedMonthName()
/*     */   {
/*  98 */     return this.monthNames[this.scroller.get(2)];
/*     */   }
/*     */ 
/*     */   public String getYear() {
/* 102 */     return this.scroller.get(1);
/*     */   }
/*     */ 
/*     */   public String getHour() {
/* 106 */     return this.scroller.get(10);
/*     */   }
/*     */ 
/*     */   public String getMinute() {
/* 110 */     return this.scroller.get(12);
/*     */   }
/*     */ 
/*     */   public String getAmPm() {
/* 114 */     int amPm = this.scroller.get(9);
/* 115 */     if (amPm == 0) {
/* 116 */       return "AM";
/*     */     }
/* 118 */     return "PM";
/*     */   }
/*     */ 
/*     */   public String getTimeZone() {
/* 122 */     return this.scroller.getTimeZone().getDisplayName(true, 0);
/*     */   }
/*     */ 
/*     */   public Date getSelectedDate() {
/* 126 */     return this.c.getTime();
/*     */   }
/*     */ 
/*     */   public boolean isCellEditable(int row, int column) {
/* 130 */     setDayMatrix(row, column);
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   public int getRowCount() {
/* 135 */     return 6;
/*     */   }
/*     */ 
/*     */   public int getColumnCount() {
/* 139 */     return 
/* 140 */       7;
/*     */   }
/*     */ 
/*     */   public String getColumnName(int columnIndex) {
/* 144 */     return this.dayNames[columnIndex];
/*     */   }
/*     */ 
/*     */   public Object getValueAt(int row, int column) {
/* 148 */     return this.dayMatrix[row][column];
/*     */   }
/*     */ 
/*     */   private void updateMatrix() {
/* 152 */     this.dayMatrix = new String[6][7];
/* 153 */     int thisMonth = this.scroller.get(2);
/* 154 */     this.scroller.set(5, 1);
/* 155 */     while (this.scroller.get(2) == thisMonth) {
/* 156 */       this.dayMatrix[(this.scroller.get(4) - 1)][
/* 157 */         (this.scroller.get(7) - 1)] = 
/* 158 */         this.scroller.get(5);
/* 159 */       this.scroller.add(5, 1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           /Users/paulmangiamele/Desktop/BIRG_API 2/
 * Qualified Name:     org.birg.gui.dialogs.CalendarModel
 * JD-Core Version:    0.6.2
 */