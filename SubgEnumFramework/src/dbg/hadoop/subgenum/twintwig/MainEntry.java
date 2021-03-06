package dbg.hadoop.subgenum.twintwig;

import org.apache.log4j.Logger;

import dbg.hadoop.subgenum.hypergraph.triangle.Triangle;
import dbg.hadoop.subgenum.star.Clique;
import dbg.hadoop.subgenum.star.CliqueB;
import dbg.hadoop.subgraphs.utils.InputInfo;

public class MainEntry{
	private static InputInfo inputInfo = null;
	private static Logger log = Logger.getLogger(MainEntry.class);
	
	public static void main(String[] args) throws Exception{
		inputInfo = new InputInfo(args);
		String query = inputInfo.query.toLowerCase();
		long startTime = 0;
		long endTime = 0;
		
		if(query.compareTo("triangle") == 0 || query.compareTo("q1") == 0){
			log.info("TwinTwig: Start enumerating square...");
			startTime = System.currentTimeMillis();	
			Triangle.run(inputInfo);
			endTime=System.currentTimeMillis();
			log.info("[TwinTwig-square] Time elapsed: " + (endTime - startTime) / 1000 + "s");
		}
		// Square is query: q1
		else if(query.compareTo("square") == 0 || query.compareTo("q1") == 0){
			log.info("TwinTwig: Start enumerating square...");
			startTime = System.currentTimeMillis();	
			Square.run(inputInfo);
			endTime=System.currentTimeMillis();
			log.info("[TwinTwig-square] Time elapsed: " + (endTime - startTime) / 1000 + "s");
		}
		// Chordal Square is query: q2
		else if(query.compareTo("chordalsquare") == 0 || query.compareTo("q2") == 0){
			log.info("TwinTwig: Start enumerating chordal square...");
			startTime = System.currentTimeMillis();
			ChordalSquare.run(inputInfo);
			endTime=System.currentTimeMillis();
			log.info("[TwinTwig-chordalsquare] Time elapsed: " + (endTime - startTime) / 1000 + "s");
		}
		// k-clique is query: q3
		else if (query.compareTo("clique") == 0) {
			int k = Integer.parseInt(inputInfo.cliqueNumVertices);
			log.info("TwinTwig: Start enumerating " + k + "clique...");
			startTime = System.currentTimeMillis();
			if (inputInfo.useStar) { // Use star instead of TwinTwig
				if(inputInfo.isBottomUp) CliqueB.run(inputInfo);
				else Clique.run(inputInfo);
			}
			else { // Using stars
				if (k == 4) {
					FourClique.run(inputInfo);
				} else if (k == 5) {
					FiveClique.run(inputInfo);
				} else if (k == 6) {
					SixClique.run(inputInfo);
				} else {
					System.err.println("Specify invalid clique size: " + k);
					System.exit(0);
				}
			}
			endTime=System.currentTimeMillis();
			if(inputInfo.useStar){
				log.info("[Star-" + k + "Clique] Time elapsed: " + (endTime - startTime) / 1000 + "s");
			}
			else {
				log.info("[TwinTwig-" + k + "Clique] Time elapsed: " + (endTime - startTime) / 1000 + "s");
			}
			
		}
		// House is query: q4
		else if (query.compareTo("house") == 0 || query.compareTo("q4") == 0) {
			log.info("TwinTwig: Start enumerating house...");
			startTime = System.currentTimeMillis();
			House.run(inputInfo);
			endTime=System.currentTimeMillis();
			log.info("[TwinTwig-house] Time elapsed: " + (endTime - startTime) / 1000 + "s");
		}
		// Solar Square is query: q5
		else if (query.compareTo("solarsquare") == 0 || query.compareTo("q5") == 0) {
			log.info("TwinTwig: Start enumerating solar square...");
			startTime = System.currentTimeMillis();
			SolarSquare.run(inputInfo);
			endTime=System.currentTimeMillis();
			log.info("[TwinTwig-solarsquare] Time elapsed: " + (endTime - startTime) / 1000 + "s");
		}
		// Twin Triangle is q6
		else if (query.compareTo("twintriangle") == 0 || query.compareTo("q6") == 0) {
			log.info("TwinTwig: Start enumerating twin triangle...");
			startTime = System.currentTimeMillis();
			TwinTriangle.run(inputInfo);
			endTime=System.currentTimeMillis();
			log.info("[TwinTwig-twintriangle] Time elapsed: " + (endTime - startTime) / 1000 + "s");
		}
		// Near5Clique is q7
		else if (query.compareTo("near5clique") == 0 || query.compareTo("q7") == 0) {
			log.info("TwinTwig: Start enumerating twin triangle...");
			startTime = System.currentTimeMillis();
			Near5Clique.run(inputInfo);
			endTime = System.currentTimeMillis();
			log.info("[TwinTwig-near5clique] Time elapsed: "
					+ (endTime - startTime) / 1000 + "s");
		}
		else {
			System.err.println("Please specify enum.query=[...];");
			System.err.println("Supported queries are: triangle, square, " +
					"chordalsquare, clique, house, solarsquare, " +
					"twintriangle, near5clique;");
			System.exit(0);
		}
	}
}