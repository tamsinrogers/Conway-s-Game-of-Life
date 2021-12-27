/**
 * File: LifeSimulation.java
 * Author: Tamsin Rogers
 * Date: 2/24/20
 */	
import java.util.*; 

public class LifeSimulation
{
	private Landscape scape;											// initialize scape
	private int time;													// initialize the time int
	
	public static void main(String[] args) throws InterruptedException 
	{
		Scanner inputTime = new Scanner(System.in);						// set up a new scanner
        System.out.print("Enter the number of milliseconds you would like the program to pause for between frames: ");
        int time = inputTime.nextInt();									// set time to the user input value
        inputTime.close();

        Landscape scape = new Landscape(100,100);						// set up the landscape in a display window
        LandscapeDisplay display = new LandscapeDisplay(scape, 8);
        scape.advance();

        for(int i=0; i<100; i++)										// repeat 100 times (100 frames)
        {
                scape.advance();										// advance the animation to the next frame
                display.repaint();										// repaint the display
                Thread.sleep(time);										// pause for the amount of time specified by the user
                scape.pattern();										// call the Landscape pattern method
                
                display.saveImage( "data/life_frame_" + String.format( "%03d", i ) + ".png" );	// save frames to data folder
        }
    }
}