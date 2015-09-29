/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		addComponentListener(this);
		// You fill in the rest //
		//oneEntry = null;
		entries = new HashMap<String, NameSurferEntry>();
		count = 0;
	}
	
	
	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		// You fill this in //
		entries.clear();
	}
	
	
	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display.
	 * Note that this method does not actually draw the graph, but
	 * simply stores the entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		if(entry != null) {
			count++;
			if(!entries.containsKey(entry.getName())) {
				entries.put(entry.getName(), entry);
			}
			
			//oneEntry = entry;
			int[] x = new int[NDECADES];
			int[] y = new int[NDECADES];
			String rank = "";
			for(int i = 0; i < NDECADES; i++) {
				x[i] = i * (getWidth() / NDECADES);
				if(entry.getRank(i) != 0) {
					int get_rank = entry.getRank(i);
					int y_add = get_rank * (getHeight() - 2 * GRAPH_MARGIN_SIZE) / MAX_RANK;
					y[i] = GRAPH_MARGIN_SIZE + y_add;
					rank = Integer.toString(entry.getRank(i));
				} else {
					y[i] = getHeight() - GRAPH_MARGIN_SIZE;
					rank = "*";
				}
				GLabel label = new GLabel(entry.getName() + rank, x[i], y[i]);
				GLine line = null;
				if(i > 0) {
					line = new GLine(x[i - 1], y[i - 1], x[i], y[i]);
				}
				
				if(count % 5 == 0) {
					label.setColor(Color.BLACK);
					if(line != null) line.setColor(Color.BLACK);
				} else if(count % 5 == 1) {
					label.setColor(Color.BLUE);
					if(line != null) line.setColor(Color.BLUE);
				} else if(count % 5 ==2) {
					label.setColor(Color.GREEN);
					if(line != null) line.setColor(Color.GREEN);
				} else if(count % 5 == 3) {
					label.setColor(Color.RED);
					if(line != null) line.setColor(Color.RED);
				} else if(count % 5 == 4) {
					label.setColor(Color.YELLOW);
					if(line != null) line.setColor(Color.YELLOW);
				}
				add(label);
				if(line != null) add(line);

			}
		}
	}
	
	
	/**
	 * Updates the display image by deleting all the graphical objects
	 * from the canvas and then reassembling the display according to
	 * the list of entries. Your application must call update after
	 * calling either clear or addEntry; update is also called whenever
	 * the size of the canvas changes.
	 */
	public void update() {
		// You fill this in //
		removeAll();
		for(int i = 0; i < NDECADES; i++) {
			double x = i * (getWidth() / NDECADES);
			GLine backgroundLine = new GLine(x, 0, x, getHeight());
			GLabel decades = new GLabel(Integer.toString(START_DECADE + i * 10), x, getHeight());
			GLine horizontalLineAbove = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
			GLine horizontalLineBtm = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
			add(backgroundLine);
			add(decades);
			add(horizontalLineAbove);
			add(horizontalLineBtm);
		}
		for(String name: entries.keySet()) {
			addEntry(entries.get(name));
		}
		//addEntry(oneEntry);
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); count = 0;}
	public void componentShown(ComponentEvent e) { }
	
	//private ArrayList<NameSurferEntry> entries;
	private HashMap<String, NameSurferEntry> entries;
	private int count;
	//private NameSurferEntry oneEntry;
}
