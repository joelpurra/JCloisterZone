package com.jcloisterzone.wsio.message;

import com.jcloisterzone.game.CustomRule;

public class SetRuleMessage {

    String gameId;
      CustomRule rule;
    boolean enabled;

    public SetRuleMessage(String gameId, CustomRule rule, boolean enabled) {
        this.gameId = gameId;
        this.rule = rule;
        this.enabled = enabled;
    }

    public String getGameId() {
        return gameId;
    }
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
    public CustomRule getRule() {
        return rule;
    }

    public void setRule(CustomRule rule) {
        this.rule = rule;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
