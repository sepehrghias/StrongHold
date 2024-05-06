package model.government.people.units;

import javafx.scene.control.Button;

import java.util.ArrayList;

public class UnitButton {
    private ArrayList<Units> units;
    private Button button;

    public UnitButton(ArrayList<Units> units, Button button) {
        this.units=units;
        this.button = button;
    }

    public Button getButton() {
        return button;
    }

    public void setUnits(ArrayList<Units> units) {
        this.units = units;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public ArrayList<Units> getUnits() {
        return units;
    }
}
