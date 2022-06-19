// Name: Trevor Lilly
// Date: 02/19/2022
// Program Name: Lilly_Fibonnaci
// Description: A program that compares the time between a recursive and iterative fibonnaci sequence and prints out said results, then displays them on a graph.

package fibonacci;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;  


public class Lilly_Fibonnaci extends Application {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static ArrayList<Double> fibRTime = new ArrayList<>();
	static ArrayList<Double> fibITime = new ArrayList<>();
	static ArrayList<Double> fibRIndex = new ArrayList<>();
	static ArrayList<Double> fibIIndex = new ArrayList<>();
	
	public void start(Stage primaryStage) {
		
		// very creative title
        primaryStage.setTitle("line chart");
        
        // we only ran 9 elements, so it made sense to have it go up to nine
        final NumberAxis xAxis = new NumberAxis(0,9,1);
        // the times i got went up to 7000000 nanosecnods, but never got much higher than that.
        final NumberAxis yAxis = new NumberAxis(0,800000,10000);
        
        // labelling the axis
        xAxis.setLabel("nth Element");
        yAxis.setLabel("Time in nanoseconds");
        
        LineChart lineChart = new LineChart(xAxis,yAxis);
        XYChart.Series<Double, Double> series = new XYChart.Series();
        series.setName("Recursive");
        lineChart.setTitle("Fibonnaci Recursive vs Iterative");
        // the entire series of recursive items
        series.getData().add(new XYChart.Data<Double, Double>(fibRIndex.get(0), fibRTime.get(0)));
        series.getData().add(new XYChart.Data<Double, Double>(fibRIndex.get(1), fibRTime.get(1)));
        series.getData().add(new XYChart.Data<Double, Double>(fibRIndex.get(2), fibRTime.get(2)));
        series.getData().add(new XYChart.Data<Double, Double>(fibRIndex.get(3), fibRTime.get(3)));
        series.getData().add(new XYChart.Data<Double, Double>(fibRIndex.get(4), fibRTime.get(4)));
        series.getData().add(new XYChart.Data<Double, Double>(fibRIndex.get(5), fibRTime.get(5)));
        series.getData().add(new XYChart.Data<Double, Double>(fibRIndex.get(6), fibRTime.get(6)));
        series.getData().add(new XYChart.Data<Double, Double>(fibRIndex.get(7), fibRTime.get(7)));
        series.getData().add(new XYChart.Data<Double, Double>(fibRIndex.get(8), fibRTime.get(8)));
        series.getData().add(new XYChart.Data<Double, Double>(fibRIndex.get(9), fibRTime.get(9)));
        
        XYChart.Series<Double, Double> series2 = new XYChart.Series();
        series2.setName("Iterative");
        //everybody here is iterative
        series2.getData().add(new XYChart.Data<Double, Double>(fibIIndex.get(0), fibITime.get(0)));
        series2.getData().add(new XYChart.Data<Double, Double>(fibIIndex.get(1), fibITime.get(1)));
        series2.getData().add(new XYChart.Data<Double, Double>(fibIIndex.get(2), fibITime.get(2)));
        series2.getData().add(new XYChart.Data<Double, Double>(fibIIndex.get(3), fibITime.get(3)));
        series2.getData().add(new XYChart.Data<Double, Double>(fibIIndex.get(4), fibITime.get(4)));
        series2.getData().add(new XYChart.Data<Double, Double>(fibIIndex.get(5), fibITime.get(5)));
        series2.getData().add(new XYChart.Data<Double, Double>(fibIIndex.get(6), fibITime.get(6)));
        series2.getData().add(new XYChart.Data<Double, Double>(fibIIndex.get(7), fibITime.get(7)));
        series2.getData().add(new XYChart.Data<Double, Double>(fibIIndex.get(8), fibITime.get(8)));
        series2.getData().add(new XYChart.Data<Double, Double>(fibIIndex.get(9), fibITime.get(9)));
        
        // plotting them both on the chart. legitimately forgot this line of code and i got confused as to why evetything was blank. 
        lineChart.getData().add(series);
        lineChart.getData().add(series2);
        
        Group root = new Group(lineChart);
        Scene scene = new Scene(root,1024,512);
        lineChart.setPrefHeight(450);
        lineChart.setPrefWidth(950);
        primaryStage.setScene(scene);
        primaryStage.show();
    
	}
	
	public static void main(String[] args) {
				
		System.out.println("Printing recursive fib\n");
		// it's a hard requirement to implement the System.nanoTime function.
		long start = System.nanoTime();
		for (double i = 0; i < 10; i++) {
			System.out.println(fibR(i));
			// so, for every iteration that we get - it prints how long the programs been working minus the start time. 
			long iteration = System.nanoTime() - start;
			System.out.println(iteration);
			// this is all added to a list, of course. this'll form our handy-dandy y axis which contains all the time
			fibRTime.add((double) iteration);
			// we'd also like to have a nice X axis that represents every step.
			fibRIndex.add(i);
		}
		System.out.println("\n"+fibRTime);
		System.out.println(fibRIndex);
		
		int n = 9;
		fibI(n);
		System.out.println("\n"+fibITime);
		// I thought it'd be cute to programatically add the numbers within the fibI loop... 
		// but it wound up messing up performance so bad, so I just iterated up to 10 with this loop in main. YOLO.
		for (double i = 0; i < n+1; i++) {
		fibIIndex.add(i);
	}
		System.out.println(fibIIndex);
		launch(args);
		
	}
	
	static double fibR(double i) {
		// the fibonacci sequence is special where the first two numbers are going to be equal to itself, so we have to add in this condition first
		if ( i <= 1)
			return i;
		else
			// this is where the leap of faith happens and pretty much recursion takes the wheel
			return  (fibR(i-1) + fibR(i-2));
	}

	 public static void fibI(double i) {
		 // starting the time in the method
		long start = System.nanoTime();
		
		System.out.println("\nStarting iterative fib: \n");
		// the first numbers 
		double f = 0;
		double s = 1;
		
		// so, much like the previous equation, the first two numbers are special cases
		// since we're not iterating two numbers in the regular loop, i decided to subtract 2 from the upper bounds of the loop
		// since we have a loop that's nested within with a conditional, trying to increment numbers for the X axis got way too unwieldly it also messed up performance.
		for (int j = 0; j <= i-2; j++) {
			if ( j < 2) {
				System.out.println(f);
				long iteration = (System.nanoTime() - start);
				System.out.println(iteration);
				fibITime.add((double) iteration);
			}
			// the disco 3, also known as the beastie boys. i'd say the fat boys, but they're too memory efficient
			// so, the element is the summation of the first and second number. 
			// the second number soon becomes the first, and the previous element becomes the 2nd number calculated. 
			double element = f + s;
			f = s;
			s = element;
			System.out.println(element);
			long iteration = (System.nanoTime() - start);
			System.out.println(iteration);
			// of course, all of the times are added into 
			fibITime.add((double) iteration);
		}
	}
}
