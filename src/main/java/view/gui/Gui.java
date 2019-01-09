package view.gui;

import gui_fields.*;
import gui_main.GUI;
import model.board.Field;
import model.board.fields.*;
import model.player.Player;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Alfred Röttger Rydahl
 * @date 19-11-2018
 */
public class Gui {

    /*
    ---------------------------------- Fields ----------------------------------
     */

    //<editor-fold desc="Fields">
    private GUI gui;
    private GUI_Field[] fields;
    private ArrayList<GUI_Player> players;
    private Color backgroundColor = Color.GRAY;
    private Color textColor = Color.BLACK;
    //</editor-fold>

    /*
    ------------------------------ Constructors --------------------------------
     */

    //<editor-fold desc="Constructors">
    /**
     * This Constructor creates a visual board, with the given fields.
     * @param fields The list of Field's as Field[]
     */
    public Gui ( Field[] fields ) {

        // Initialize Player ArrayList
        this.players = new ArrayList<>();

        // Create the GUI_Field array
        this.fields = createFields(fields);

        // Start GUI
        gui = new GUI(this.fields, backgroundColor);

    }

    /**
     * Constructor to use if the player list is known, and then creates
     * a visual board with the given fields.
     * @param players The Player list as an ArrayList<Player>
     * @param fields The list of Field's as Field[]
     */
    public Gui ( ArrayList<Player> players, Field[] fields ) {

        // Create the GUI_Field array
        this.fields = createFields(fields);

        // Start GUI
        gui = new GUI(this.fields, backgroundColor);

        // Create the GUI_Player array
        this.players = createPlayers(players);

        for (GUI_Player player : this.players) { gui.addPlayer(player); }
    }
    //</editor-fold>


    /*
    ------------------------------ Properties ----------------------------------
     */

    //<editor-fold desc="BackgroundColor"
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    //</editor-fold>

    //<editor-fold desc="TextColor"
    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
    //</editor-fold>

    /*
    ---------------------------- Public Methods --------------------------------
     */

    /**
     * Shows a message to the players
     * @param message The message to show as String
     */
    public void showMessage (String message) {

        // Show the message in the gui
        gui.showMessage(message);
    }

    /**
     * This method sets a Die on the board with the
     * given facevalue.
     * @param faceValue The value of the face on the Die
     */
    public void setDie (int faceValue) {

        // Set the dice on the board
        gui.setDie(faceValue);
    }

    //<editor-fold desc="Chancecard Methods">

    /**
     * This method sets the next Chancecard, so that
     * the displayChancecard method can show the card,
     * or you can press the deck to see it.
     * @param text The title to the Chancecard
     */
    public void setChanceCard (String text) {

        // Ready the next Chancecard
        gui.setChanceCard(text);
    }

    /**
     * This method displays the next Chancecard.
     * Requires the setChancecard() method to be called
     * before, to show the desired card.
     */
    public void displayChanceCard () {

        // Display the card
        gui.displayChanceCard();
    }

    //</editor-fold>

    //<editor-fold desc="User Input">

    public String getUserChoice (String message, String ... options) {

        // Return the String
        return gui.getUserSelection(message, options);
    }

    /**
     * Gets an Integer from the user in a specified range.
     * @param message The message to inform the user
     * @param min The minimum possible valid Integer
     * @param max The maximum possible valid Integer
     * @return Returns an Integer between min and max
     */
    public int getUserInteger (String message, int min, int max) {

        // Get the Integer and Return
        return gui.getUserInteger(message, min, max);
    }

    /**
     * Getting an Integer from the user, and displays a message.
     * @param message The message to inform the user
     * @return Returns the inputted Integer
     */
    public int getUserInteger (String message) {

        // Get the Integer and Return
        return gui.getUserInteger(message);
    }

    /**
     * This method prompts the user to input a String into
     * a textfield, and returns the inputted String.
     * @param message An informal message to the user.
     * @return Returns the input as a String
     */
    public String getUserString (String message) {

        // Get the String and Return
        return gui.getUserString(message);
    }

    //</editor-fold>

    //<editor-fold desc="Player Methods">
    /**
     * Updates the given player visual balance.
     * @param player The player whos balance will get updated
     * @param newBalance The absolute balance of the player
     */
    public void updatePlayerBalance ( Player player, int newBalance ) {

        // Find the player in the GUI_Player list
        GUI_Player playerToUpdate = findPlayer(player);

        // Update the visual balance for the player
        playerToUpdate.setBalance(newBalance);
    }

    /**
     * This method moves the given player to the field
     * according to the index.
     * @param player The player which shall be moved
     * @param theFieldIndex The index of the field in the array
     */
    public void movePlayer ( Player player, int theFieldIndex ) {

        // Find the player in the GUI_Player list
        GUI_Player playerToMove = findPlayer(player);

        // First remove the player from its current field
        for ( GUI_Field field : fields ) {
            field.setCar(playerToMove, false);
        }

        // Move the player on the board
        fields[theFieldIndex].setCar (playerToMove, true);
    }

    /**
     * This method adds an ArrayList of players to the game.
     * @param players An ArrayList<Player></Player>
     */
    public void addPlayers ( ArrayList<Player> players ) {

        // Iterate over the players list and add the player
        for ( Player player : players ) {
            addPlayer(player);
        }
    }

    /**
     * This method adds a new Player to the game.
     * @param player The Player to add
     */
    public void addPlayer ( Player player ) {

        // Create the GUI_Player
        GUI_Player newPlayer = setPlayer(player);

        // Add the player to the gui and set them on the start field
        addGUIPlayer(newPlayer);
        fields[player.getPosition()].setCar(newPlayer, true);
    }
    //</editor-fold>

    //<editor-fold desc="Field Methods"

    /**
     * This method changes the owner of the given field
     * to the given Player.
     * @param player The player which shall own the field
     * @param theField The field in focus
     */
    public void setFieldOwner (Player player, Field theField) {

        // Cast the GUI_Field to a GUI_Street as we know it will be a GUI_Street object
        ((GUI_Street)fields[theField.getFieldNumber()]).setOwnerName(player.getName());

    }

    /**
     * This methods makes all fields blink between
     * gold and gray eternally
     * @throws InterruptedException
     */
    public void partyMode () throws InterruptedException {

        short sleepTime = 400;

        Color color1 = new Color(255,223,0);
        Color color2 = new Color(153,153,153);

        // Loop
        while (true) {

            // Make all fields gold
            for (GUI_Field field : fields) {
                field.setBackGroundColor(color1);
            }


            Thread.sleep(sleepTime);

            // Make all fields gray
            for (GUI_Field field : fields) {
                field.setBackGroundColor(color2);
            }


            Thread.sleep(sleepTime);
        }
    }

    //</editor-fold>
    
    /*
    ----------------------------- Support Methods ------------------------------
     */

    /**
     * Finds the corresponding GUI_Player in the list
     * @param player The player to find
     * @return Returns the found GUI_Player or null
     */
    private GUI_Player findPlayer ( Player player ) {

        // Declare GUI_Player variable
        GUI_Player playerToFind = null;

        // Find the player
        for ( GUI_Player p : players ) {

            // Check if the name corresponds
            if ( p.getName().equals(player.getName()) ) {
                playerToFind = p;
                break;
            }
        }

        // Return the found GUI_Player
        return playerToFind;
    }

    //<editor-fold desc="GUI_Player creation"
    /**
     * This method helps with the creation of a GUI_Player.
     * Primarily has the responsibility for given the Player
     * the correct Car type
     * @param player The Player which shall be created as a GUI_Player
     */
    private GUI_Player setPlayer ( Player player ) {

        // Declare GUI_Player variable
        GUI_Player newPlayer;

        // Create the player with the correct Car type
        switch (player.getToken()) {

            case "Bil":
                newPlayer = new GUI_Player( player.getName(),
                        player.getAccount().getBalance(),
                        new GUI_Car(Color.RED, Color.BLACK, GUI_Car.Type.CAR, GUI_Car.Pattern.DOTTED));
                break;

            case "Racerbil":
                newPlayer = new GUI_Player( player.getName(),
                        player.getAccount().getBalance(),
                        new GUI_Car(Color.RED, Color.BLACK, GUI_Car.Type.RACECAR, GUI_Car.Pattern.DOTTED));
                break;

            case "Traktor":
                newPlayer = new GUI_Player( player.getName(),
                        player.getAccount().getBalance(),
                        new GUI_Car(Color.RED, Color.BLACK, GUI_Car.Type.TRACTOR, GUI_Car.Pattern.DOTTED));
                break;

            case "UFO":
                newPlayer = new GUI_Player( player.getName(),
                        player.getAccount().getBalance(),
                        new GUI_Car(Color.RED, Color.BLACK, GUI_Car.Type.UFO, GUI_Car.Pattern.DOTTED));
                break;

            /*
            The Code will default to a Yellow car with a Zebra pattern
            which indicates that there's a fault in Token String in the
            player.
             */
            default:
                newPlayer = new GUI_Player( player.getName(),
                        player.getAccount().getBalance(),
                        new GUI_Car(Color.YELLOW, Color.BLACK, GUI_Car.Type.CAR, GUI_Car.Pattern.ZEBRA));
                break;
        }

        // Return the newly created GUI_Player
        return newPlayer;

    }

    /**
     * This method has the responsibility to add a new player to
     * this objects GUI_Player list and to the visual board.
     * @param player A GUI_Player to add
     */
    private void addGUIPlayer ( GUI_Player player ) {

        // Add to the GUI_Player list
        players.add(player);
        // Add the GUI_Player to the visual gui
        gui.addPlayer(player);
    }
    /**
     * This method helps the constructor create an Array of GUI_Player's
     * @param players An ArrayList<Player>
     * @return Return an ArrayList<GUI_Player>
     */
    private ArrayList<GUI_Player> createPlayers (ArrayList<Player> players) {

        // Create the players arraylist as the right size
        ArrayList<GUI_Player> guiPlayers = new ArrayList<>(players.size());

        // Iterate over the players list and create a GUI_Player for each
        for ( int i=0 ; i < players.size() ; i++ ) {
            GUI_Player newPlayer = new GUI_Player(  players.get(i).getName(),
                    players.get(i).getAccount().getBalance(),
                    new GUI_Car(Color.RED, Color.BLACK, GUI_Car.Type.CAR, GUI_Car.Pattern.DOTTED)
            );

            // Add the created player to the player array and set the player on the start field
            guiPlayers.add(newPlayer);
            fields[players.get(i).getPosition()].setCar(newPlayer, true);
        }

        // Return the GUI_Player array
        return guiPlayers;
    }

    //</editor-fold>

    //<editor-fold desc="Field Creation"
    /**
     * This method helps the constructor create an Array of GUI_Field's
     * @param fields An Array of type Field
     * @return Return an Array of type GUI_Field
     */
    private GUI_Field[] createFields(Field[] fields ) {

        // Create the GUI_Field array
        GUI_Field[] newFields = new GUI_Field[fields.length];

        // Iterate through fields and create GUI_Field for each
        for ( int i=0 ; i < fields.length ; i++ ) {

            if ( fields[i] instanceof Property ) {

                // Create new GUI_Street
                GUI_Street ownable = new GUI_Street(fields[i].getTitle(), "Beløb: " + fields[i].getCost(),
                        fields[i].getDescription(),
                        Integer.toString(fields[i].getCost()), fields[i].getColor(), textColor);

                // Put the GUI_Field into the newFields array
                newFields[i] = ownable;
            }

            else if ( fields[i] instanceof Chancefield ) {

                // Create new Field
                GUI_Chance chance = new GUI_Chance("?", fields[i].getTitle(), fields[i].getDescription(),
                                                    fields[i].getColor(), textColor);

                // Put the GUI_Field into the newFields array
                newFields[i] = chance;
            }

            else if ( fields[i] instanceof Prison ) {

                // Create new Field
                GUI_Jail jail = new GUI_Jail();

                // Change the title of the field
                jail.setSubText("Fængsel");
                jail.setDescription(fields[i].getDescription());

                // Put the GUI_Field into the newFields array
                newFields[i] = jail;

            }

            else if ( fields[i] instanceof Visiting ) {

                // Create new Field
                GUI_Refuge refuge = new GUI_Refuge();

                // Change the title of the field
                refuge.setTitle(fields[i].getTitle());
                refuge.setDescription(fields[i].getDescription());
                refuge.setSubText("Gratis");

                // Put the GUI_Field into the newFields array
                newFields[i] = refuge;

            }

            else if ( fields[i] instanceof model.board.fields.Start ) {

                // Create new Field
                GUI_Start start = new GUI_Start(fields[i].getTitle(), "", fields[i].getDescription(),
                                                fields[i].getColor(), textColor);

                // Put the GUI_Field into the newFields array
                newFields[i] = start;
            }
        }

        // Return the newly created array
        return newFields;
    }
    //</editor-fold>
}
