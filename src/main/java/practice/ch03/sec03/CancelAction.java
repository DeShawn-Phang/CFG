package practice.ch03.sec03;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CancelAction implements EventHandler<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        System.out.println("oh noes!");
    }
}