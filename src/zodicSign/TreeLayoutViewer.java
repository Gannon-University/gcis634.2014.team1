package zodicSign;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;
import edu.uci.ics.jung.visualization.subLayout.TreeCollapser;

public class TreeLayoutViewer {

	private static ArrayList<Integer> blockList;
	private static String[] contentArray2;
	private static BufferedReader reader;
	// private static SparseMultigraph<Integer, String> myGraph;
	static Forest<String, Integer> graph;

	public static void builder() throws IOException {
		// Block for tree Layout Testing

		reader = new BufferedReader(new FileReader(
				"/Users/hassanalrawi/Desktop/zodicSign.txt"));
		String line = null;
		ArrayList<String> listArray = new ArrayList<String>();
		// myGraph = new SparseMultigraph<Integer, String>();

		graph = new DelegateForest<String, Integer>();

		while ((line = reader.readLine()) != null) {
			listArray.add(line);
		}

		// Defining Block Lines
		blockList = new ArrayList<Integer>();
		blockList.add(0);

		// Checking the file content
		String lastLine = listArray.get(listArray.size() - 1);
		String[] lastIndex = lastLine.split("\\s");

		// Adding the content of the file to an array
		contentArray2 = new String[Integer.parseInt(lastIndex[0]) + 1];

		// Splitting the line code to indexes in the array, and the rest of the
		// line as a string
		for (String lines : listArray) {
			String[] parts = lines.split("\\s");

			if (parts.length == 2) {
				contentArray2[Integer.parseInt(parts[0])] = parts[1];
			} else if (parts.length == 3) {
				contentArray2[Integer.parseInt(parts[0])] = parts[1] + " "
						+ parts[2];
			} else if (parts.length == 4) {
				contentArray2[Integer.parseInt(parts[0])] = parts[1] + " "
						+ parts[2] + " " + parts[3];
			}

		}

		buildingBlocks();

		// for (Integer blockLine : blockList) {
		// System.out.println(blockLine);
		// }
		//
		// System.out.println(solution);

		graphViewer();

	} // End of Main

	// Building Blocks For the Graph
	private static void buildingBlocks() {
		int previousLineIndex = 0;
		int nextLineIndex = 0;
		for (int i = 0; i < contentArray2.length; i++) {
			if (contentArray2[i] == null) { // if the index is null continue, i
											// used this to avoid empty indexes
											// in the array
				continue;
			}

			for (int j = i - 1; j > 0; j--) {
				if (contentArray2[j] != null) { // if the index is null
												// continue, i used this to
												// avoid empty indexes in the
												// array
					previousLineIndex = j;
					break;
				}
			}

			for (int j = i + 1; j < contentArray2.length; j++) {
				if (contentArray2[j] != null) { // if the index is null
												// continue, i used this to
												// avoid empty indexes in the
												// array
					nextLineIndex = j;
					break;
				}
			}

			String[] commandsAndVlaues = contentArray2[i].split("\\s");
			String command = commandsAndVlaues[0];

			if (i == 0) {
				graph.addVertex(String.valueOf(i));
			}
			if (i != 0) {
				 graph.addEdge(i, String.valueOf(previousLineIndex),
				 String.valueOf(i), EdgeType.DIRECTED);

			}

			String value = " ";
			if (commandsAndVlaues.length > 1) {
				value = commandsAndVlaues[1];
			}

			// Printing the command and the index and whatever value after the
			// command, just to make sure my code is working correctly
			 System.out.println(i + " " + command + " " + value);

			// If statements for every command in the homework, i wanted to use
			// Reflection to run the functions, but for simplicity i avoided
			// that
			if (command.equalsIgnoreCase("if_icmplt")
					|| command.equalsIgnoreCase("if_icmpgt")
					|| command.equalsIgnoreCase("if_icmple")
					|| command.equalsIgnoreCase("if_icmpne")
					|| command.equalsIgnoreCase("if_icmpeq")
					|| command.equalsIgnoreCase("if_icmpge")) {
				blockList.add(i);
				blockList.add(nextLineIndex);
				graph.addVertex(String.valueOf(value));
				 graph.addEdge(Integer.parseInt(value), value,
				 String.valueOf(i), EdgeType.DIRECTED);

			} else if (command.equalsIgnoreCase("ireturn")) {
				blockList.add(previousLineIndex);
			} else if (command.equalsIgnoreCase("goto")) {
				blockList.add(i);
				blockList.add(nextLineIndex);
			}

		}
	}

	public static void graphViewer() {
		// The Layout<V, E> is parameterized by the vertex and edge types
		// Layout<Integer, String> layout = new CircleLayout<Integer, String>(
		// myGraph);
		TreeLayout<String, Integer> layout = new TreeLayout<String, Integer>(
				graph);
//		FRLayout layout1;	
//		TreeCollapser collapser = new TreeCollapser();
		RadialTreeLayout<String, Integer> radialLayout;

		radialLayout = new RadialTreeLayout<String, Integer>(graph);
		radialLayout.setSize(new Dimension(700, 700));
		// layout.setSize(new Dimension(800, 800)); // sets the initial size of
		// the
		// layout space
		// The BasicVisualizationServer<V,E> is parameterized by the vertex and
		// edge types
		VisualizationViewer<String, Integer> vv = new VisualizationViewer<String, Integer>(
				layout, new Dimension(600, 600));

		vv.setPreferredSize(new Dimension(900, 900)); // Sets the viewing area
		// // size
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
}
