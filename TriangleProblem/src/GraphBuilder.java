import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class GraphBuilder{

	private static SparseMultigraph<Integer, String> graph;
	
	public static void buildGraph(String[] fileContent) {
		graph = new SparseMultigraph<Integer, String>();
		int lastindex = 0;
		
		for (int i = 0; i < fileContent.length; i++) {
			if (fileContent[i] == null) {
				continue;
			}
			
			String command = CommandsAndValuesConverter.getCommand(fileContent[i]);
			String value = CommandsAndValuesConverter.getValue(fileContent[i]);
			
			graph.addVertex(i);
			if (i != 0) {
				graph.addEdge(i + " " + command, lastindex, i,
						EdgeType.DIRECTED);
			}
			
			
			if (command.equalsIgnoreCase("if_icmplt")
					|| command.equalsIgnoreCase("if_icmpgt")
					|| command.equalsIgnoreCase("if_icmple")
					|| command.equalsIgnoreCase("if_icmpne")
					|| command.equalsIgnoreCase("if_icmpeq")
					|| command.equalsIgnoreCase("if_icmpge")) {
				graph.addEdge(value + " " + command, Integer.parseInt(value),
						i, EdgeType.DIRECTED);

			}
			
			lastindex = i;
		}
		
		graphViewer();
		
	}
	
	public static void buildGraphForAPath(ArrayList<String> fileContent) {
		graph = new SparseMultigraph<Integer, String>();
		int lastindex = 0;
		
		for (int i = 0; i < fileContent.size(); i++) {
			if (fileContent.get(i) == null) {
				continue;
			}
			
			graph.addVertex(Integer.parseInt(fileContent.get(i)));
			if (i != 0) {
				graph.addEdge(fileContent.get(lastindex) + " to " + fileContent.get(i), Integer.parseInt(fileContent.get(lastindex)), Integer.parseInt(fileContent.get(i)),
						EdgeType.DIRECTED);
			}
			
			lastindex = i;
		}
		
		graphViewer();
		
	}
	
	
//	public static void buildGraphForEverypath(ArrayList<ArrayList<String>> fileContent) {
//		for (ArrayList<String> path : fileContent) {
//			graph = new SparseMultigraph<Integer, String>();
//			int lastindex = 0;
//			for (int i = 0; i < path.size(); i++) {
//				if (path.get(i) == null) {
//					continue;
//				}
//
//				graph.addVertex(Integer.parseInt(path.get(i)));
//				if (i != 0) {
//					graph.addEdge(fileContent.get(lastindex) + " to "
//							+ fileContent.get(i),
//							Integer.parseInt(path.get(lastindex)),
//							Integer.parseInt(path.get(i)), EdgeType.DIRECTED);
//				}
//
//				lastindex = i;
//			}
//
//			graphViewer();
//		}
//	}
	
	public static void graphViewer() {
		// The Layout<V, E> is parameterized by the vertex and edge types
				Layout<Integer, String> layout = new CircleLayout<Integer, String>(
						graph);
//				Layout layout = new FRLayout2(myGraph);
				layout.setSize(new Dimension(800, 800)); // sets the initial size of the
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
