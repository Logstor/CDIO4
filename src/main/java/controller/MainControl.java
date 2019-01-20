package controller;

import controller.extraActionManagment.ExtraActionController;
import model.board.Board;
import model.board.Field;
import model.board.FieldTypeEnum;
import model.board.fields.PropertyField;
import model.chancecard.Deck;
import model.cup.Cup;
import model.player.Player;
import view.gui.Gui;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Alfred Röttger Rydahl
 */

public class MainControl {

    /*
    --------------------------- Fields ---------------------------
     */
    private Gui gui;
    private GuiController guiController;
    private Board board;
    private Player[] players;
    private ArrayList<Player> playerArrayList;
    private Deck deck;
    private Cup cup;
    private TurnController turnController;
    private ExtraActionController extraActionController;
    private GeneralActionController generalActionController;
    private HashMap<String, String> messageMap;

    /*
    ------------------------ Constructors ------------------------
     */
    
    public MainControl ()
	{
		board = new Board();
		deck = new Deck();
		cup = new Cup();
    	messageMap = new HashMap<>();
    	playerArrayList = new ArrayList<>();
	}
    
    /*
    ----------------------- Public Methods -----------------------
     */
	
	/**
	 * This method starts the game.
	 * @return Returns non-zero if an unexpected shutdown happens.
	 */
	public int letsFuckingGo()
	{

		try
		{
			// Set the game up, and display
			setup();

			//region GameLoop
			do {
                for (Player currentPlayer : playerArrayList) {

                    turn(currentPlayer);

                    if (extraActionChecker(currentPlayer)){
						extraActions(currentPlayer);
					}
                }

                // Clean and removes a player if hasLost=True.
				findingHasLostPlayersAndRemovesThem();

            }
			// Continue while there's more than 1 player left
			while (playerArrayList.size()>1);

			// ANNOUNCES THE WINNER.
			announceWinner();

			//endregion

			return 0;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return -1;
		}
	}
    /*
    ----------------------- Support Methods ----------------------
     */
	
	/**
	 * This method sets the board, and takes care of getting information from user.
	 */
	private void setup ()
	{
		// Instantiate everything
		
		// Creating the board with all Fields
		SetupControl setupControl = new SetupControl();
		setupControl.boardCreator(board);
		setupControl.deckCreator(deck);
		setupControl.messageMapCreator(messageMap);
		gui = new Gui(board.getBoard());
		guiController = new GuiController(gui);
		
		players = setupControl.playerSetup(guiController, messageMap);
		setupControl.createGUIPlayers(guiController,players);
		guiController.showMessage(messageMap.get("GetReady"));

		//TODO: Er det her okay? :D // RSL
		generalActionController = new GeneralActionController();
		extraActionController = new ExtraActionController(playerArrayList,board,guiController,messageMap,generalActionController);
		turnController = new TurnController(guiController, board,cup, players, deck, messageMap,extraActionController);


		// Adds Players for Player[] to ArrayList<Player> playerArraylist.
 		for (Player player : players) {
			playerArrayList.add(player);
		}
	}

	private void turn (Player player)
	{
	    turnController.playTurn(player);
    }


	/**
	 * Does ExtraActions if it is valid.
	 * @param currentPlayer The Player.
	 */
	private void extraActions (Player currentPlayer) {
		if (extraActionController.isExtraActionsValid(currentPlayer)){
			extraActionController.doExtraAction(currentPlayer);
		}
	}

	private boolean extraActionChecker (Player currentPlayer) {

		if (!currentPlayer.isHasLost()){
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Announces the Winner of the game and actives the WINNER/PARTYMODE!
	 */
	public void announceWinner () throws InterruptedException	{

		for (Player winningPlayer : playerArrayList) {
			// Announce Winner of the game
			guiController.showMessage(messageMap.get("Winner").replace("%name", winningPlayer.getName()));
		}
		guiController.WinnerMode();
	}

	//region Handling of hasLost Players

	/**
	 * Finds and removes the Players that hasLost. Remove from GUI and as Players in game.
	 */
	private void findingHasLostPlayersAndRemovesThem () {

		// Finds players that has lost and sell there Fields and reset them. Remove Player GUI_Car from board.
		for (Player player : playerArrayList) {
			if (player.isHasLost()){
				// Declare player FALLIT in GUI Message
				fallitMessage(player);
				cleanAPlayersFields(player);
			}
		}

		// Removes player from the Arraylist of Players that runs the Turn()
		removesHasLostPlayersFromPlayerArrayList();

	}

	/**
	 * Show Message on GUI that declares player Fallit
	 * @param player Fallit Player.
	 */
	private void fallitMessage (Player player) {

		StringBuilder fallitBuilder = new StringBuilder();
		// Player declared "Fallit"
		fallitBuilder.append(messageMap.get("PlayerFallit").replace("%name", player.getName()) + "\n");
		// Fallit description.
		fallitBuilder.append(messageMap.get("PlayerHasLostAndOutOfGame"));

		// Show message to GUI.
		guiController.showMessage(fallitBuilder.toString());
	}

	/**
	 * Resets Fields and GUI_Fields of the inputted Player.OwnedFields Fields.
	 * @param player Dome to this player.
	 */
    private void cleanAPlayersFields (Player player) {

    	// List of Fields to reset.
        ArrayList<Field> fieldsToRemoveFromPlayer = new ArrayList<>();

        // Runs through all Players Owned Fields.
        for(Field field: player.getOwnedFields()){
        	// Set GUI_field to default.
            guiController.clearFieldForInfo(field);

            // Removes houses on Field and GUI_Field, resets rent to basic rent.
            if (field.getFieldType() == FieldTypeEnum.Property) {
				((PropertyField)field).setNoOfHousesOnProperty(0);
				guiController.setOwnableRent(field,((PropertyField) field).getFieldRent());
                guiController.setHousesAndHotels(((PropertyField) field).getNoOfHousesOnProperty(), field);

            }
			field.setFieldOwner(null);
            // Adds Field to List of FieldsToRemoveFromPlayer
            fieldsToRemoveFromPlayer.add(field);
        }
        // Removes fields from Player OwnedFields.
        for (Field fieldToRemove : fieldsToRemoveFromPlayer) {
            player.removeFieldFromOwnedFields(fieldToRemove);
        }

        // Removes Players GUI_Car From Board and Sets GUI_Player Balance to 0.
        guiController.removeGUICarFromField(player);
        generalActionController.updatePlayerBalanceInclGui(guiController,player,-player.getAccount().getBalance());
    }

	/**
	 * Runs through all Players in Player[] players and remove the player from the Arraylist if Player Has Lost.
	 */
	private void removesHasLostPlayersFromPlayerArrayList () {
		for (Player player : players) {
			if (player.isHasLost()) {
				playerArrayList.remove(player);
			}
		}
	}

	//endregion
}
