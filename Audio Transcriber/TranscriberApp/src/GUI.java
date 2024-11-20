import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GUI extends GridPane {

    private Label lblPrompt;
    private TextField txtURL;
    private Button btnSendRequest;
    private Label lblResponse;
    private TextArea txtResponse;

    public GUI() {
        // Initialize and configure all UI components within the constructor
        lblPrompt = new Label("Enter audio URL:");
        txtURL = new TextField();
        btnSendRequest = new Button("Generate Transcription");
        lblResponse = new Label("Response:");
        txtResponse = new TextArea();

        // Set default properties
        setupComponents();

        // Add components to the GridPane layout
        setupLayout();
        
        
        btnSendRequest.setOnAction(e->{
        	
        	txtResponse = new TextArea(TranscriberApplication.createTranscript(txtURL.toString()));
        	
        	
        });
    }

    private void setupComponents() {
        // Label styling
        lblPrompt.setTextFill(Color.WHITE);
        lblPrompt.setFont(new Font("Arial", 16));
        lblResponse.setTextFill(Color.WHITE);
        lblResponse.setFont(new Font("Arial", 16));

        // TextField styling
        txtURL.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: white; -fx-border-color: #555555; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5;");
        txtURL.setPromptText("Enter the API URL here...");

        // Button styling
        btnSendRequest.setStyle("-fx-background-color: #5a5aff; -fx-text-fill: white; -fx-font-weight: bold; -fx-border-radius: 5px; -fx-background-radius: 5px;");
        btnSendRequest.setOnMouseEntered(e -> btnSendRequest.setStyle("-fx-background-color: #7676ff; -fx-text-fill: white;"));
        btnSendRequest.setOnMouseExited(e -> btnSendRequest.setStyle("-fx-background-color: #5a5aff; -fx-text-fill: white;"));

        // TextArea styling
        txtResponse.setStyle("-fx-control-inner-background: #3c3f41; -fx-text-fill: white; -fx-border-color: #555555; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5;");
        txtResponse.setFont(new Font("Courier New", 14));
        txtResponse.setEditable(false);

        // Apply GridPane dark mode styling
        setStyle("-fx-background-color: #2b2b2b;");
    }

    private void setupLayout() {
        // Set layout properties for the GridPane
        setPadding(new Insets(20));
        setHgap(10);
        setVgap(15);
        setAlignment(Pos.CENTER);

        // Add components to the GridPane
        add(lblPrompt, 0, 0);
        add(txtURL, 1, 0);
        add(btnSendRequest, 2, 0);
        add(lblResponse, 0, 1);
        add(txtResponse, 0, 2, 3, 1); // Spanning the TextArea across 3 columns
    }

    // Getters for the UI components (if needed for event handling)
    public TextField getTxtURL() {
        return txtURL;
    }

    public Button getBtnSendRequest() {
        return btnSendRequest;
    }

    public TextArea getTxtResponse() {
        return txtResponse;
    }
}


