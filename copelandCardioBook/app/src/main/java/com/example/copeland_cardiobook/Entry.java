package com.example.copeland_cardiobook;
import android.os.Parcel;
import android.os.Parcelable;

/**the entry class represents a measurement in
 * the display list (and its many...many fields)
 * class entry implements parcelable so it can
 * be sent through the intent
 */
public class Entry implements Parcelable {


    /***parcel creation stuff***/

    /**CITATION:  StackOverflow post by UMAR https://stackoverflow.com/users/249991/umar,
     *Answer https://stackoverflow.com/questions/2139134/how-to-send-an-object-from-one-android-activity-to-another-using-intents#comment14174896_2141166**/

    /**CITATION: Android, Parcelable, Apache2.0,  https://developer.android.com/reference/android/os/Parcelable.html **/
    public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry>() {
        public Entry createFromParcel(Parcel in) {
            return new Entry(in);
        }

        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };


    /***normal class stuff (class attributes of object Entry)***/
    private int systolicP;
    private int diastolicP;
    private int heartRate;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private String comment;


    public Entry(int systolicP, int diastolicP, int heartRate, int year, int month, int day, int hour, int minute, String comment){
        this.setSysPressure(systolicP);
        this.setDiaPressure(diastolicP);
        this.setHeartRate(heartRate);
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        this.setHour(hour);
        this.setMinute(minute);
        this.setComment(comment);

    }


    public void setSysPressure(int systolicP){

        this.systolicP = systolicP;
    }

    public int getSysPressure(){

        return systolicP;
    }

    public void setDiaPressure(int diastolicP){

        this.diastolicP = diastolicP;
    }

    public int getDiaPressure(){

        return diastolicP;
    }

    public void setHeartRate(int heartRate){

        this.heartRate = heartRate;
    }

    public int getHeartRate(){

        return heartRate;
    }

    public void setYear(int year){

        this.year = year;
    }

    public int getYear(){
        return year;
    }

    public void setMonth(int month){

        this.month = month;
    }

    public int getMonth(){
        return month;
    }

    public void setDay(int day){

        this.day = day;
    }

    public int getDay(){
        return day;
    }


    public void setHour(int hour){
        this.hour = hour;
    }

    public int getHour(){
        return hour;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }

    public int getMinute(){
        return minute;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getComment(){
        return comment;
    }




    /*****parcel part*****/
    public Entry(Parcel in) {
        this.systolicP = in.readInt();
        this.diastolicP = in.readInt();
        this.heartRate = in.readInt();
        this.year = in.readInt();
        this.month = in.readInt();
        this.day = in.readInt();
        this.hour = in.readInt();
        this.minute = in.readInt();
        this.comment = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.systolicP);
        dest.writeInt(this.diastolicP);
        dest.writeInt(this.heartRate);
        dest.writeInt(this.year);
        dest.writeInt(this.month);
        dest.writeInt(this.day);
        dest.writeInt(this.hour);
        dest.writeInt(this.minute);
        dest.writeString(this.comment);

    }


}
