package de.metafinanz.mam.backend.controller.impl;

import de.metafinanz.mam.backend.controller.MatchesController;

public class MatchesControllerImpl implements MatchesController {

	public String getMatches() {
		return "matches";
	}

	public String addMatch() {
		return "add match";
	}

}