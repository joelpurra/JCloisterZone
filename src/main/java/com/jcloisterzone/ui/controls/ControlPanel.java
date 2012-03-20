package com.jcloisterzone.ui.controls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.List;

import com.jcloisterzone.Player;
import com.jcloisterzone.action.PlayerAction;
import com.jcloisterzone.ui.Client;

public class ControlPanel extends FakeComponent {

    public static final Color BG_COLOR = new Color(0, 0, 0, 30);
    public static final Color ACTIVE_BG_COLOR = new Color(0, 0, 0, 45);
    public static final Color SHADOW_COLOR = new Color(0, 0, 0, 60);
    public static final int CORNER_DIAMETER = 20;

    private final Client client;

    private boolean canPass;

    private ActionPanel actionPanel;
    private PlayerPanel[] playerPanels;

    public static final int PANEL_WIDTH = 220;

    public ControlPanel(final Client client) {
        this.client = client;

        actionPanel = new ActionPanel(client);

        Player[] players = client.getGame().getAllPlayers();
        PlayerPanelImageCache cache = new PlayerPanelImageCache(client);
        playerPanels = new PlayerPanel[players.length];

        for (int i = 0; i < players.length; i++) {
            playerPanels[i] = new PlayerPanel(client, players[i], cache);
        }
    }

//    //TODO clean coupling and component initialization (GridPanel, MainPanel & ControlPanel
//    public void registerMouseListener() {
//        client.getGridPanel().addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int w = client.getGridPanel().getWidth();
//                if (e.getX() > w-PANEL_WIDTH) {
//                    //click on panel
//
//                }
//            }
//        });
//    }

    @Override
    public void paintComponent(Graphics2D g2) {
        AffineTransform origTransform = g2.getTransform();

//		GridPanel gp = client.getGridPanel();

        g2.translate(0, 40);
        actionPanel.paintComponent(g2);

//		gp.profile("action panel");

        g2.translate(0, 70);
        for (PlayerPanel pp : playerPanels) {
            pp.paintComponent(g2);
        }

//		gp.profile("players");

        g2.setTransform(origTransform);
    }

    public void pass() {
        if (canPass) {
            client.getServer().pass();
        }
    }

    public ActionPanel getActionPanel() {
        return actionPanel;
    }

    public void selectAction(List<PlayerAction> actions, boolean canPass) {
        //direct collection sort can be unsupported - so copy to array first!
        int i = 0;
        PlayerAction[] arr = new PlayerAction[actions.size()];
        for(PlayerAction pa : actions) {
            pa.setClient(client);
            arr[i++] = pa;
        }
        Arrays.sort(arr);
        actionPanel.setActions(arr);
        this.canPass = canPass;
        client.getGridPanel().repaint(); //players only
    }

    public void clearActions() {
        actionPanel.clearActions();
        canPass = false;
    }

    public void playerActivated(Player turn, Player active) {
        client.getGridPanel().repaint(); //players only
    }

    public void closeGame() {
        clearActions();
        canPass = false;
    }

}

