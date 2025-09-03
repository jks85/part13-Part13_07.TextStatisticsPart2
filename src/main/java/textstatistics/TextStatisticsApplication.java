package textstatistics;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;


public class TextStatisticsApplication extends Application {


    public static void main(String[] args) {
        System.out.println("Displaying Text Stats UI");
        launch(TextStatisticsApplication.class);
    }

    public void start(Stage window){
        // initialize layout elements
        BorderPane borderPane = new BorderPane();
        HBox hBox = new HBox();

        // create text elements
        TextArea textArea = new TextArea();
        Label numLettersLabel = new Label("Letters: 0");
        Label numWordsLabel = new Label("Words: 0");
        Label longestWordLabel = new Label("The longest word is:");

        // add label elements to hBox layout
        hBox.getChildren().addAll(numLettersLabel, numWordsLabel, longestWordLabel);
        hBox.setSpacing(10);

        // add elements to borderPane
        borderPane.setCenter(textArea);
        borderPane.setBottom(hBox);

        // add event handlers providing user typing statistics
        textArea.textProperty().addListener(
            (change, oldValue, newValue) -> {
                // compute text stats
                int numCharacters = newValue.length();
                String[] wordArray = newValue.split(" ");
                int numWords = wordArray.length;
                
                List<String> wordList = Arrays.stream(wordArray)
                    .sorted((word1,word2) -> word2.length()-word1.length())
                    .collect(Collectors.toList());

                String longestWord = wordList.get(0);

                // update display
                numLettersLabel.setText("Letters: " + numCharacters);
                numWordsLabel.setText("Words: " + numWords);
                longestWordLabel.setText("The longest word is: " + longestWord);
            }

        );

        // set scene and stage
        Scene scene = new Scene(borderPane);
        window.setScene(scene);
        window.show();

    }
}
