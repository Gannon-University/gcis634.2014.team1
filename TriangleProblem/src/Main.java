package zodicSign;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class Main{

	public static void main(String[] args) throws IOException, SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		String URL = "~/zodicSign.txt";
		FileInPathReader fileReader = new FileInPathReader();
		
		String[] fileContent = fileReader.convertFileToText(URL);
		ArrayList<ArrayList <String>> filePaths = new ArrayList<ArrayList <String>>(); 
		filePaths = PathsFinder.allPathsFinder(fileContent);
		
		for (ArrayList<String> paths : filePaths) {
			System.out.println(paths);
		}
		
		ArrayList<String> blocks = new ArrayList<String>();
		blocks = BlockBuilder.blockBuilder(fileContent);
				
		CommandsHandler.doCommand(fileContent);
		GraphBuilder.buildGraph(fileContent);
		GraphBuilder.buildGraphForAPath(filePaths.get(0));
	}

}


