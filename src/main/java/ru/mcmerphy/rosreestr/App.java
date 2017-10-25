package ru.mcmerphy.rosreestr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.mcmerphy.rosreestr.controllers.ApplicationController;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("/Root.fxml"));

        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();

        ApplicationController applicationController = loader.getController();
        applicationController.setStage(primaryStage);
        applicationController.setHostServices(getHostServices());
        applicationController.start();
    }

}
