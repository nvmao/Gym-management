package com.mao;

import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class MyTooltip extends Tooltip {

    MyTooltip(String text){
        this.setText(text);
        this.setWrapText(true);
        this.setShowDelay(Duration.seconds(0.1));
        this.setPrefWidth(150);
        this.setStyle("-fx-text-fill:#f69;");
    }

}
