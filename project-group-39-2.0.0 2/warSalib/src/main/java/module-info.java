module warSalib {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;
    requires passay;
    requires jdk.jdi;
    requires com.google.gson;


    exports view;
    opens view to javafx.fxml;
    opens control to com.google.gson;
    exports control;
    opens model to com.google.gson;
    exports model;
//    opens model.government to com.google.gson;
//    exports model.government;
//    opens model.government.people.units to com.google.gson;
//    exports model.government.people.units;
//    opens model.map to com.google.gson;
//    exports model.map;
//    opens model.wartool to com.google.gson;
//    exports model.wartool;
//    opens model.crops to com.google.gson;
//    exports model.crops;
//    opens model.animal to com.google.gson;
//    exports model.animal;
}