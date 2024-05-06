package model.government.resource;

import javafx.scene.shape.Rectangle;

public class resourcesImage extends Rectangle {
    private String resourcePath;

    public resourcesImage(double v, double v1) {
        super(v, v1);
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
