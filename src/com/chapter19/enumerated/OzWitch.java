package com.chapter19.enumerated;

import static util.Print.*;

public enum OzWitch {
	// Instances must be defined first, before methods:
	WEST("Miss Gulch, aka the Wicked Witch of the West"),
	NORTH("north"),
	EAST("east"),
	SOUTH("south");
	
	private String description;

	private OzWitch(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static void main(String[] args) {
		for(OzWitch witch : OzWitch.values())
			print(witch + ": " + witch.getDescription());
	}
	
}
