package model.government.people.workingpersons;

import model.government.people.People;
import model.user.User;

public class WorkingPerson extends People {


    public WorkingPerson(int xLocation, int yLocation, JobsName jobsName, User ownerPerson) {
        super(xLocation,yLocation,jobsName,ownerPerson);
    }
}


