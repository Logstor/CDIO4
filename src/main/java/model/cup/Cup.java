package model.cup;

/**
 * @author Rasmus Sander Larsen
 * @date 07-01-2019
 */
public class Cup {

    /*
    -------------------------- Fields --------------------------
     */

	private int cupValue;
	private Die[] dies;
    
    /*
    ----------------------- Constructor -------------------------
     */
	
	/**
	 * Default constructor which sets two dies which six sides each.
	 */
	public Cup ()
	{
		// Initialize Die array
		dies = new Die[2];
		
		// Fill the array with 2 dies with 6 sides
		for( int i=0 ; i < dies.length ; i++) {
			dies[i] = new Die(6);
		}
	}
	
	/**
	 * Alternative constructor. Puts the specified amount of dies in the Cup
	 * with the specified amount of sides.
	 * @param noOfDies Amount of dies the Cup shall hold.
	 * @param sidesOnDies Amount of sides each Die will have.
	 */
	public Cup (int noOfDies, int sidesOnDies)
	{
		// Initialize
		dies = new Die[noOfDies];

		// Fill the array with noOfDies dies
		for( int i=0 ; i < dies.length ; i++) {
			dies[i] = new Die(sidesOnDies);
		}
	}
    
    
    /*
    ------------------------ Properties -------------------------
     */

	// <editor-folder desc="Properties"

	public int getCupValue() {
		return cupValue;
	}

	public void setCupValue(int cupValue) {
		this.cupValue = cupValue;
	}

	public Die[] getDies() {
		return dies;
	}

	public void setDies(Die[] dies) { this.dies = dies; }




	// </editor-folder>
    
    /*
    ---------------------- Public Methods -----------------------
     */

	/**
	 * This method rolls every model.die in the cup
	 * @return Returns the total value of the facevalues in the cup
	 */
	public int cupRoll () {

		int cupValue = 0;
		// Iterate through all dies and roll
		for (Die die : dies) {
			// Roll the current model.die
			die.roll();

			// Update cupValue
			cupValue += die.getFaceValue();

		}

		// Update this.cupValue and return it
		this.cupValue = cupValue;
		return 20;
	}
    
    /*
    ---------------------- Support Methods ----------------------
     */


}
