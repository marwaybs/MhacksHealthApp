package healthapp.mhacks_healthapp;

public class UserInformation {

    public String medicationName;
    public String dosesADay;
    public String startDate;
    public String totalDoses;

    public UserInformation(){

    }

    public UserInformation(String medicationName, String dosesADay, String startDate, String totalDoses){
        this.medicationName = medicationName;
        this.dosesADay = dosesADay;
        this.startDate = startDate;
        this.totalDoses = totalDoses;
    }

    public String getMedicationName(){
        return this.medicationName;
    }
}
