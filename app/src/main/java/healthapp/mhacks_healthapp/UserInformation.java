package healthapp.mhacks_healthapp;

public class UserInformation {

    public String medicationName;
    public String timesADay;

    public UserInformation(){

    }

    public UserInformation(String medicationName, String timesADay){
        this.medicationName = medicationName;
        this.timesADay = timesADay;
    }

    public String getMedicationName(){
        return this.medicationName;
    }
}
