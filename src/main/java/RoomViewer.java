import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class RoomViewer extends Application {

	public void start(Stage stage) {
		
		try {

			String viewerFxml = "WorldViewer.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource(viewerFxml));
			AnchorPane page = (AnchorPane) fxmlLoader.load();
			Scene scene = new Scene(page);
			
			stage.setScene(scene);
			
			WorldController controller = (WorldController) fxmlLoader.getController();      			
			controller.Initialise();

			stage.show();
        
		} catch (IOException ex) {
		   Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
		   System.exit(1);
		}
	}
	
    public static void main(String args[]) {
     	launch(args);
     	System.exit(0);
    }
}
