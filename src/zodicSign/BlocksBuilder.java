package zodicSign;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.FRLayout2;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class BlocksBuilder {
	private static ArrayList<Integer> blockList;
	private static String[] contentArray2;
	private static BufferedReader reader;
	private static SparseMultigraph<Integer, String> myGraph;
	
	public static void builder() throws IOException {
		
		
		reader = new BufferedReader(new FileReader(
				"/Users/hassanalrawi/Desktop/zodicSign.txt"));
		String line = null;
		ArrayList<String> listArray = new ArrayList<String>();
		myGraph = new SparseMultigraph<Integer, String>();

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

			myGraph.addVertex(i);
			if (i != 0) {
				myGraph.addEdge(i + " " + command, previousLineIndex, i,
						EdgeType.DIRECTED);
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
				myGraph.addEdge(value + " " + command, Integer.parseInt(value),
						i, EdgeType.DIRECTED);

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
		Layout<Integer, String> layout = new CircleLayout<Integer, String>(
				myGraph);
//		Layout layout = new FRLayout2(myGraph);
		layout.setSize(new Dimension(600, 600)); // sets the initial size of the
													// layout space
		// The BasicVisualizationServer<V,E> is parameterized by the vertex and
		// edge types
		BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer, String>(
				layout);
		vv.setPreferredSize(new Dimension(900, 900)); // Sets the viewing area
														// size
		vv.getRenderContext().setVertexLabelTransformer(
				new ToStringLabeller<Integer>());
		vv.getRenderContext().setEdgeLabelTransformer(
				new ToStringLabeller<String>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
}
