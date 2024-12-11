package view;

import java.util.ArrayList;

import controller.EventController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Event;

public class ViewEventsPage implements EventHandler<ActionEvent>{
	public Scene scene;
	
	private VBox root;
	
	private TableView<Event> tableView;
	private TableColumn<Event, String> idColumn;
	private TableColumn<Event, String> nameColumn;
	private TableColumn<Event, String> dateColumn;
	private TableColumn<Event, String> locationColumn;
	private TableColumn<Event, String> viewDetailsColumn;
	private PropertyValueFactory<Event, String> idFactory;
	private PropertyValueFactory<Event, String> nameFactory;
	private PropertyValueFactory<Event, String> dateFactory;
	private PropertyValueFactory<Event, String> locationFactory;
	
	private Button viewDetailsBtn;
	
	private EventController ec;
	private ArrayList<Event> events;
	
	private ObservableList<Event> data;
	
	private Label title;
	private Button homeBtn;
	
	private void init() {
		root = new VBox(10);
		
		tableView = new TableView<>();
		idColumn = new TableColumn<>("Id");
		nameColumn = new TableColumn<>("Name");
		dateColumn = new TableColumn<>("Date");
		locationColumn = new TableColumn<>("Location");
		idFactory = new PropertyValueFactory<>("event_id");
		nameFactory = new PropertyValueFactory<>("event_name");
		dateFactory = new PropertyValueFactory<>("event_date");
		locationFactory = new PropertyValueFactory<>("event_location");
		
		viewDetailsColumn = new TableColumn<>("Action");
		viewDetailsBtn = new Button("View Details");
		
		ec = new EventController();
		events = new ArrayList<Event>();
		
		events = ec.getEvents();
		data = FXCollections.observableArrayList(events);
		
		title = new Label("Your Events");
		homeBtn = new Button("Home");
		
		scene = new Scene(root, 1000, 700);
	}
	
	private void setPosition() {
		root.setAlignment(Pos.CENTER);
		
		tableView.setPrefWidth(800);
		tableView.setMaxWidth(801);
		idColumn.setPrefWidth(100);
        nameColumn.setPrefWidth(200);
        dateColumn.setPrefWidth(200);
        locationColumn.setPrefWidth(200);
        viewDetailsColumn.setPrefWidth(100);
		
		idColumn.setCellValueFactory(idFactory);
		nameColumn.setCellValueFactory(nameFactory);
		dateColumn.setCellValueFactory(dateFactory);
		locationColumn.setCellValueFactory(locationFactory);
		
		viewDetailsColumn.setCellFactory(col -> new TableCell<>() {
            {
                viewDetailsBtn.setOnAction(e -> {
                    Event currentEvent = getTableView().getItems().get(getIndex());
                });
            }
        });
		
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(dateColumn);
		tableView.getColumns().add(locationColumn);
		
		tableView.setItems(data);
		
		root.getChildren().addAll(title, tableView, homeBtn);
	}
	
	private void setStyle() {
		title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
	}
	
	private void events() {
		homeBtn.setOnAction(e -> handle(e));
	}

	public ViewEventsPage() {
		init();
		setPosition();
		setStyle();
		events();
		
		Main.redirect(scene);
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() == homeBtn) {
			new EventOrganizerPage();
		}
	}

}
