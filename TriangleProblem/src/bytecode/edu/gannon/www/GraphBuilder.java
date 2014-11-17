package bytecode.edu.gannon.www;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

/**
 * @author hassanalrawi This class handles generating nodes, vertexes of a given
 *         path or paths, and draw them in a J-Frame window.
 */
public class GraphBuilder {

	private static SparseMultigraph<Integer, String> graph;

	/*
	 * This method is responsible of adding Edges and Vertexes to the nodes on
	 * the JVM Instructions, The node labels are the line numbers in the
	 * instructions, and the edge label is the command
	 */
	public static void buildGraph(String[] fileContent) {
		graph = new SparseMultigraph<Integer, String>();
		int lastindex = 0;

		for (int i = 0; i < fileContent.length; i++) {
			if (fileContent[i] == null) {
				continue;
			}

			String command = CommandsAndValuesConverter
					.getCommand(fileContent[i]);
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

	/*
	 * This method is responsible of generating a single path elements (edges
	 * and vertexes)
	 */
	public static void buildGraphForAPath(ArrayList<String> fileContent) {
		graph = new SparseMultigraph<Integer, String>();
		int lastindex = 0;

		for (int i = 0; i < fileContent.size(); i++) {
			if (fileContent.get(i) == null) {
				continue;
			}

			graph.addVertex(Integer.parseInt(fileContent.get(i)));
			if (i != 0) {
				try {
					graph.addEdge(fileContent.get(lastindex) + " to "
							+ fileContent.get(i),
							Integer.parseInt(fileContent.get(lastindex)),
							Integer.parseInt(fileContent.get(i)),
							EdgeType.DIRECTED);

				} finally {
					System.out.println("Edge exists");
				}
			}
			lastindex = i;
		}

		graphViewer();

	}

	/*
	 * This method is responsible of drawing the graph Property into a J-Frame
	 */
	public static void graphViewer() {
		Layout<Integer, String> layout = new CircleLayout<Integer, String>(
				graph);
		layout.setSize(new Dimension(800, 800));
		VisualizationViewer<Integer, String> vv = new VisualizationViewer<Integer, String>(
				layout);
		vv.setPreferredSize(new Dimension(900, 900));
		vv.getRenderContext().setVertexLabelTransformer(
				new ToStringLabeller<Integer>());
		vv.getRenderContext().setEdgeLabelTransformer(
				new ToStringLabeller<String>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		DefaultModalGraphMouse<Integer, String> m_graphmouse = new DefaultModalGraphMouse<Integer, String>();
		m_graphmouse.setMode(ModalGraphMouse.Mode.PICKING);

		vv.setGraphMouse(m_graphmouse);
		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

}
