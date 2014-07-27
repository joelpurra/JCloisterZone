package com.jcloisterzone.wsio.message;

public class JoinGameMessage {
    private String gameId;

    public JoinGameMessage(String gameId) {
        super();
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
}
