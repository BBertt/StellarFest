package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
	private PropertyValueFactory<Event, String> idFactory;
	private PropertyValueFactory<Event, String> nameFactory;
	private PropertyValueFactory<Event, String> dateFactory;
	private PropertyValueFactory<Event, String> locationFactory;
	
	
	private void init() {
		root = new VBox(10);
		
		tableView = new TableView<>();
		idColumn = new TableColumn<>("Id");
		nameColumn = new TableColumn<>("Name");
		dateColumn = new TableColumn<>("Date");
		locationColumn = new TableColumn<>("Location");
		idFactory = new PropertyValueFactory<>("id");
		nameFactory = new PropertyValueFactory<>("name");
		dateFactory = new PropertyValueFactory<>("date");
		locationFactory = new PropertyValueFactory<>("location");
		
		scene = new Scene(root, 1000, 700);
	}
	
	private void setPosition() {
		root.setAlignment(Pos.CENTER);
		
		idColumn.setCellValueFactory(idFactory);
		nameColumn.setCellValueFactory(nameFactory);
		dateColumn.setCellValueFactory(dateFactory);
		locationColumn.setCellValueFactory(locationFactory);
		
		tableView.getColumns().add(idColumn);
		tableView.getColumns().add(nameColumn);
		tableView.getColumns().add(dateColumn);
		tableView.getColumns().add(locationColumn);
	}
	
	private void setStyle() {
		
	}
	
	private void events() {
		
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
		
	}

}
