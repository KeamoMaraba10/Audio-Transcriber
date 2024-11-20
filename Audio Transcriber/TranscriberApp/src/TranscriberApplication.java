import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TranscriberApplication extends Application {

	public static void main(String[] args) {
		
		
		//audio link: http://thepodcastexchange.ca/s/Porsche-Macan-July-5-2018-1.mp3
		//createTranscript("http://thepodcastexchange.ca/s/Porsche-Macan-July-5-2018-1.mp3");

		launch(args);
	}
	
	public static String createTranscript(String URL) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		
				//api key: ec1ada3283654ad0af5dcab421e69ac3
				//Request URL:
				
				//Transcript transcript = new Transcript();
				Transcript t1 = new Transcript();
				
				t1.setAudio_url(URL);
				
				//transcript.setAudio_url();
				
				Gson gson = new Gson();
				String jsonRequest = gson.toJson(t1);
				
				sb.append(jsonRequest);
				System.out.println(jsonRequest);
				
				//Post request first
				try {
					
					
					HttpRequest postRequest =  HttpRequest.newBuilder()
						.uri(new URI("https://api.assemblyai.com/v2/transcript")).header("Authorization", "ec1ada3283654ad0af5dcab421e69ac3")
						.POST(BodyPublishers.ofString(jsonRequest)).build();
					
					HttpClient httpClient = HttpClient.newHttpClient();
					HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
					postResponse.body();
					sb.append(postRequest);
					System.out.println(postResponse.body());
					
					
					//Get request
					//Id: 5495fc0a-798a-4734-baeb-0c9807a33ff5
					
					//pull the id of the object we created
					t1 = gson.fromJson(postResponse.body(), Transcript.class);
					sb.append(t1.getId());
					System.out.println(t1.getId());
					
					//make the get request
					HttpRequest getRequest =  HttpRequest.newBuilder()
							.uri(new URI("https://api.assemblyai.com/v2/transcript/"+t1.getId())).header("Authorization", "ec1ada3283654ad0af5dcab421e69ac3")
						.GET().build();
					
					while(true) {
						HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
						postResponse.body();
						t1 = gson.fromJson(getResponse.body(), Transcript.class);
						sb.append(t1.getStatus());
						System.out.println(t1.getStatus());
						
						//if(t1.getStatus().equals("completed") || t1.getStatus().equals("error")) {
						if("completed".equals(t1.getStatus())|| "error".equals(t1.getStatus())) {
						break;	
						
						}
						Thread.sleep(5000);
					}
					
					sb.append("Transcription is done");
					sb.append(t1.getText());
					System.out.println("Transcription is done!");
					System.out.println(t1.getText());
					
					
					
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return sb.toString();
				
				
				
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	
		GUI gp = new GUI();
		Scene root = new Scene(gp,500,500);
		arg0.setScene(root);
		arg0.setTitle("Transcriber");
		arg0.show();
		
		
		
		
	}

}
