package model;

import java.util.ArrayList;

public class Vendor extends User{
	private ArrayList<String> accepted_invitations;

	public Vendor(String user_id, String user_email, String user_name, String user_password, String user_role,
			ArrayList<String> accepted_invitations) {
		super(user_id, user_email, user_name, user_password, user_role);
		this.accepted_invitations = accepted_invitations;
	}

	public ArrayList<String> getAccepted_invitations() {
		return accepted_invitations;
	}

	public void setAccepted_invitations(ArrayList<String> accepted_invitations) {
		this.accepted_invitations = accepted_invitations;
	}
}
