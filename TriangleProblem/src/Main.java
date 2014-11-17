import java.awt.List;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import bytecode.edu.gannon.www.BlockBuilder;
import bytecode.edu.gannon.www.CommandsHandler;
import bytecode.edu.gannon.www.FileInPathReader;
import bytecode.edu.gannon.www.GraphBuilder;
import bytecode.edu.gannon.www.PathsFinder;

public class Main{

	public static void main(String[] args) throws IOException, SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		String URL ="C:/Users/Vihaan/Documents/Dipti/634/GIT/try/file.txt";
		
			
		FileInPathReader fileReader = new FileInPathReader();
		
		String[] fileContent = fileReader.convertFileToText(URL);
		
		ArrayList<ArrayList <String>> filePaths = new ArrayList<ArrayList <String>>(); 
		filePaths = PathsFinder.allPathsFinder(fileContent);
		
    	/*for (ArrayList<String> paths : filePaths) {
			System.out.println(paths);
		}*/
		
		List blocks = new List();
		blocks = BlockBuilder.blockBuilder(fileContent);
				
	    CommandsHandler.doCommand(fileContent);
		GraphBuilder.buildGraph(fileContent);
		GraphBuilder.buildGraphForAPath(filePaths.get(0));
	}

}


