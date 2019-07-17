package hr.vjezbe.glavna;

import hr.java.vjezbe.niti.DatumRodjenjaNit;
import hr.java.vjezbe.niti.NajboljiStudentNit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	public static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Pocetna.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Najbolji student još nije odreðen!");
			Timeline prikazSlavljenika = new Timeline(
					new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							Platform.runLater(new DatumRodjenjaNit());
						}
					}));
			prikazSlavljenika.setCycleCount(Timeline.INDEFINITE);
			prikazSlavljenika.play();
			Timeline prikazNajboljeg = new Timeline(
					new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent event) {
							NajboljiStudentNit nit = new NajboljiStudentNit();
							//Platform.runLater(new NajboljiStudentNit());
							Platform.runLater(nit);
							primaryStage.setTitle(nit.getIme());
						}
					}));
			prikazNajboljeg.setCycleCount(Timeline.INDEFINITE);
			prikazNajboljeg.play();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void setMainPage(BorderPane root) {
		Scene scene = new Scene(root, 400, 500);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
